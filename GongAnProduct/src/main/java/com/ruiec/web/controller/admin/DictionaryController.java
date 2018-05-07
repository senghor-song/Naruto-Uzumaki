package com.ruiec.web.controller.admin;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.query.Sort;
import com.ruiec.framework.web.support.controller.BaseController;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.DictionaryType;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.DictionaryTypeService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.util.ImportExcelUtil;

/**
 * 字典数据后台控制器
 * 
 * @author qinzhitian<br>
 * @date 2017年11月30日 上午8:50:58
 */
@Controller
@RequestMapping("/admin/dictionary")
public class DictionaryController extends BaseController {

	private static final Logger LOGGER = Logger.getLogger(DictionaryController.class);

	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private DictionaryTypeService dictionaryTypeService;
	@Resource
	private OperationLogService operationLogService;

	/**
	 * 分页查询字典数据
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年11月30日 下午2:21:19
	 * @param dataItemId
	 *            字典类型id
	 */
	@RequestMapping("/list")
	public String list(Model model, Page page, Integer dataItemId, String itemCode, HttpServletRequest request) {
		// 获取登录用户
		User user = (User) request.getSession().getAttribute("user");
		if (user.getId() != 1) {
			LOGGER.info("权限不足");
			return "redirect:/admin/login.shtml";
		}
		try {
			// 查询默认字典类型
			Filter filter = Filter.eq("isUse", 1);
			Sort sort = Sort.asc("sort");
			List<DictionaryType> dictionaryTypes = dictionaryTypeService.findList(null, filter, sort);
			model.addAttribute("dictionaryTypes", dictionaryTypes);

			if (!dictionaryTypes.isEmpty()) {
				if (dataItemId == null || dataItemId == 0) {
					dataItemId = dictionaryTypes.get(0).getId();
				}
				page = dictionaryService.findPage(page, dataItemId);
			}
			model.addAttribute("dataItemId", dataItemId);
			model.addAttribute("itemCode", itemCode);
			model.addAttribute("page", page);

		} catch (Exception e) {
			LOGGER.error("分页查询字典数据错误" + e);
			throw new RuntimeException("分页查询字典数据错误");
		}
		// 插入日志
//		operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "查看字典数据列表",
//				LogUtil.getData(request.getParameterMap()));
		return "/admin/dictionary/list";
	}

	/**
	 * 根据父级id查询子集
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月1日 上午10:04:32
	 * @param id
	 *            父级id
	 * @param gpId
	 *            父级的父级id
	 */
	@ResponseBody
	@RequestMapping("/subSet")
	public JsonReturn subSet(Integer id, Integer gpId) {
		JsonReturn json;
		try {
			if (id == null) {
				return new JsonReturn(400, "查询子集失败!");
			}
			List<Map<String, Object>> dictionarieList = dictionaryService.findSubSet(id, gpId);
			json = new JsonReturn(200, "查询子集成功!", dictionarieList);
		} catch (Exception e) {
			LOGGER.error("字典数据查询子集失败" + e);
			json = new JsonReturn(400, "查询子集失败!");
		}
		return json;
	}

	/**
	 * 新增字典数据初始页
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年11月30日 下午3:00:42
	 * @param dataItemId
	 *            字典类型id
	 */
	@RequestMapping("/add")
	public String add(Model model, Integer id, Integer dataItemId, Integer level, String itemCode) {
		try {
			// 查询字典类型是否为人员类别
			DictionaryType dictionaryType = dictionaryTypeService.get(dataItemId);
			if (GlobalUnit.PERSON_CLASS_CODE.equals(dictionaryType.getItemCode())) {
				model.addAttribute("isPersonClass", 1);
			} else {
				model.addAttribute("isPersonClass", 0);
			}
			// 查询字典类型是否为预警级别
			DictionaryType dt = dictionaryTypeService.get(dataItemId);
			if ("warningLevel".equals(dt.getItemCode())) {
				model.addAttribute("type", "warningLevel");
			}
			model.addAttribute("itemCode",itemCode);
			model.addAttribute("dataItemId", dataItemId);
			model.addAttribute("level", level);
			if (id != null && id != 0) {
				model.addAttribute("parentId", id);
			}
		} catch (Exception e) {
			LOGGER.error("新增字典数据初始化失败" + e);
			throw new RuntimeException("新增字典数据初始化失败");
		}
		return "/admin/dictionary/add";
	}

	/**
	 * 新增字典数据
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年11月30日 下午3:00:42
	 */
	@ResponseBody
	@RequestMapping("/save")
	public JsonReturn save(Dictionary dictionary, HttpServletRequest request) {
		// 获取登录用户
		User user = (User) request.getSession().getAttribute("user");
		if (user.getId() != 1) {
			LOGGER.info("权限不足");
			return new JsonReturn(400, "权限不足");
		}
		try {
			if (StringUtils.isBlank(dictionary.getItemName()) || StringUtils.isBlank(dictionary.getDescription()) || dictionary.getSortCode() == null
					|| dictionary.getIsDef() == null) {
				return new JsonReturn(400, "字典数据不能为空");
			}
			if (dictionary.getItemName().length() > 20) {
				return new JsonReturn(400, "字典名称不得多于20字");
			}
			if (dictionary.getDescription().length() > 100) {
				return new JsonReturn(400, "字典描述不得多于100字");
			}
			if (StringUtils.isNotBlank(dictionary.getItemValue()) && dictionary.getItemValue().length() > 50) {
				return new JsonReturn(400, "字典值不得多于50字");
			}
			DetachedCriteria dca1 = DetachedCriteria.forClass(Dictionary.class);
			dca1.createAlias("dictionaryType", "dt");
			dca1.add(Restrictions.eq("dt.id", dictionary.getDictionaryType().getId()));
			dca1.add(Restrictions.eq("itemName", dictionary.getItemName()));
			List<Dictionary> dictionaries = dictionaryService.findList(dca1, null, null, null);
			if (dictionaries.size() > 0) {
				return new JsonReturn(400, "字典名称不能一样");
			}
			// 查询字典类型是否为人员类别
			DictionaryType dictionaryType = dictionaryTypeService.get(dictionary.getDictionaryType().getId());
			if (GlobalUnit.PERSON_CLASS_CODE.equals(dictionaryType.getItemCode())) {
				if (dictionary.getItemValue().length() != 12) {
					return new JsonReturn(400, "人员类别字典值必须为12位编码");
				}
				// 查询人员类别字典值是否已存在
				DetachedCriteria dca2 = DetachedCriteria.forClass(Dictionary.class);
				dca2.createAlias("dictionaryType", "dt");
				dca2.add(Restrictions.eq("dt.itemCode", GlobalUnit.PERSON_CLASS_CODE));
				dca2.add(Restrictions.eq("itemValue", dictionary.getItemValue()));
				dictionaries = dictionaryService.findList(dca2, null, null, null);
				if (dictionaries != null && dictionaries.size() > 0) {
					return new JsonReturn(400, "人员类别字典值已存在");
				}
			}
			dictionary = dictionaryService.save(dictionary);

			// 更新缓存
			RedisUtil.addRedis(GlobalUnit.DICTIONARY_MAP, dictionary.getId().toString(), dictionary);
			if (GlobalUnit.PERSON_CLASS_CODE.equals(dictionaryType.getItemCode())) {
				RedisUtil.addRedis(GlobalUnit.PERSON_CLASS_MAP, dictionary.getItemValue(), dictionary);
			}
			LOGGER.info("---------------新增字典数据----------------" + dictionary.getItemName());
		} catch (Exception e) {
			LOGGER.error("字典数据保存失败" + e);
			return new JsonReturn(400, "字典数据保存失败");
		}
		// 插入日志
		operationLogService.insertOperationLogs(user, 1, request.getRequestURI().toString(), user.getPoliceName() + "新增一条字典数据",
				LogUtil.getData(request.getParameterMap()));
		return new JsonReturn(200, "字典数据保存成功", "/admin/dictionary/list.shtml?dataItemId=" + dictionary.getDictionaryType().getId());
	}

	/**
	 * 修改字典数据初始页
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年11月30日 下午3:00:42
	 * @param dataItemId
	 *            字典类型id
	 */
	@RequestMapping("/edit")
	public String edit(Model model, Integer id, Integer dataItemId, Integer level, String itemCode) {
		try {
			// 根据id查询字典详情
			Dictionary dictionary = dictionaryService.get(id);
			// 查询字典类型是否为人员类别
			DictionaryType dictionaryType = dictionary.getDictionaryType();
			if (GlobalUnit.PERSON_CLASS_CODE.equals(dictionaryType.getItemCode())) {
				model.addAttribute("isPersonClass", 1);
			} else {
				model.addAttribute("isPersonClass", 0);
			}
			// 查询字典类型是否为预警级别
			DictionaryType dt = dictionaryTypeService.get(dataItemId);
			if ("warningLevel".equals(dt.getItemCode())) {
				model.addAttribute("type", "warningLevel");
			}
			model.addAttribute("itemCode", itemCode);
			model.addAttribute("dictionary", dictionary);
			model.addAttribute("level", level);
			return "/admin/dictionary/edit";
		} catch (Exception e) {
			LOGGER.error("修改字典数据初始化失败" + e);
			return "error";
		}
	}

	/**
	 * 修改字典数据
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年11月30日 下午3:00:42
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonReturn update(Dictionary dictionary, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user.getId() != 1) {
			LOGGER.info("权限不足");
			return new JsonReturn(400, "权限不足");
		}
		try {
			if (StringUtils.isBlank(dictionary.getItemName()) || StringUtils.isBlank(dictionary.getDescription()) || dictionary.getSortCode() == null
					|| dictionary.getIsDef() == null) {
				return new JsonReturn(400, "字典数据不能为空");
			}
			if (dictionary.getItemName().length() > 20) {
				return new JsonReturn(400, "字典名称不得多于20字");
			}
			if (dictionary.getDescription().length() > 100) {
				return new JsonReturn(400, "字典描述不得多于100字");
			}
			if (StringUtils.isNotBlank(dictionary.getItemValue()) && dictionary.getItemValue().length() > 50) {
				return new JsonReturn(400, "字典值不得多于50字");
			}
			// 查询字典名称是否唯一
			DetachedCriteria dca1 = DetachedCriteria.forClass(Dictionary.class);
			dca1.createAlias("dictionaryType", "dt");
			dca1.add(Restrictions.eq("dt.id", dictionary.getDictionaryType().getId()));
			dca1.add(Restrictions.eq("itemName", dictionary.getItemName()));
			List<Dictionary> dictionaries = dictionaryService.findList(dca1, null, null, null);
			if (dictionaries.size() > 0) {
				if (dictionaries != null && dictionaries.size() > 1) {
					return new JsonReturn(400, "字典名称不能一样");
				} else if (dictionaries.size() == 1 && !dictionaries.get(0).getId().equals(dictionary.getId())) {
					return new JsonReturn(400, "字典名称不能一样");
				}
			}
			// 查询字典类型是否为人员类别
			DictionaryType dictionaryType = dictionaryTypeService.get(dictionary.getDictionaryType().getId());
			if (GlobalUnit.PERSON_CLASS_CODE.equals(dictionaryType.getItemCode())) {
				if (dictionary.getItemValue().length() != 12) {
					return new JsonReturn(400, "人员类别字典值必须为12位编码");
				}
				// 查询人员类别字典值是否已存在
				DetachedCriteria dca = DetachedCriteria.forClass(Dictionary.class);
				dca.createAlias("dictionaryType", "dt");
				dca.add(Restrictions.eq("dt.itemCode", GlobalUnit.PERSON_CLASS_CODE));
				dca.add(Restrictions.eq("itemValue", dictionary.getItemValue()));
				dictionaries = dictionaryService.findList(dca, null, null, null);
				if (dictionaries != null && dictionaries.size() > 1) {
					return new JsonReturn(400, "人员类别字典值已存在");
				} else if (dictionaries.size() == 1 && !dictionaries.get(0).getId().equals(dictionary.getId())) {
					return new JsonReturn(400, "人员类别字典值已存在");
				}
			}
			dictionaryService.update(dictionary);
			// 更新缓存
			RedisUtil.addRedis(GlobalUnit.DICTIONARY_MAP, dictionary.getId().toString(), dictionary);
			if (GlobalUnit.PERSON_CLASS_CODE.equals(dictionaryType.getItemCode())) {
				RedisUtil.addRedis(GlobalUnit.PERSON_CLASS_MAP, dictionary.getItemValue(), dictionary);
			}
			LOGGER.info("---------------修改字典数据----------------" + dictionary.getItemName());
		} catch (Exception e) {
			LOGGER.error("字典数据修改失败" + e);
			return new JsonReturn(400, "字典数据修改失败");
		}
		// 插入日志
		operationLogService.insertOperationLogs(user, 3, request.getRequestURI().toString(), user.getPoliceName() + " 修改字典数据信息",
				LogUtil.getData(request.getParameterMap()));
		return new JsonReturn(200, "字典数据修改成功", "/admin/dictionary/list.shtml?dataItemId=" + dictionary.getDictionaryType().getId());
	}

	/**
	 * 修改启用状态
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月2日 下午5:36:31
	 */
	@ResponseBody
	@RequestMapping(value = "/updateDef")
	public JsonReturn updateDef(Model model, Integer id, Integer dataItemId, Integer pageNumber) {
		try {
			dictionaryService.updateIsDef(id);
			model.addAttribute("dataItemId", dataItemId);
		} catch (Exception e) {
			LOGGER.error("修改启用状态失败" + e);
			return new JsonReturn(400, "启用状态修改失败");
		}
		return new JsonReturn(200, "启用状态修改成功");
	}

	/**
	 * 删除字典数据
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年11月30日 下午3:00:42
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public JsonReturn delete(HttpServletRequest request, Integer[] ids) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user.getId() != 1) {
				LOGGER.info("权限不足");
				return new JsonReturn(400, "权限不足");
			}
			if (ids != null && ids.length > 0) {
				dictionaryService.deleteByIds(ids);
				// 更新缓存
				for (Integer id : ids) {
					Dictionary dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, id);
					if (dictionary==null) {
						continue;
					}
					DictionaryType dictionaryType = dictionaryTypeService.get(dictionary.getDictionaryType().getId());
					// 删除人员类别缓存
					if (GlobalUnit.PERSON_CLASS_CODE.equals(dictionaryType.getItemCode()) && StringUtils.isNotBlank(dictionary.getItemValue())) {
						RedisUtil.delRedis(GlobalUnit.PERSON_CLASS_MAP, dictionary.getItemValue());
						LOGGER.info("---------------删除人员类别" + dictionary.getItemName() + "----------------" + id);
					}
					RedisUtil.delRedis(GlobalUnit.DICTIONARY_MAP, id.toString());
					LOGGER.info("---------------删除字典数据" + dictionary.getItemName() + "----------------" + id);
				}
			} else {
				return new JsonReturn(400, "请选择需要删除的选项");
			}
			// 插入日志
			operationLogService.insertOperationLogs(user, 2, request.getRequestURI().toString(), user.getPoliceName() + " 删除字典数据信息",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("删除字典数据失败!" + e);
			return new JsonReturn(400, "删除失败,该数据在其它地方被调用");
		}
		return new JsonReturn(200, "删除成功");
	}

	/**
	 * 查询字典数据是否包含子集
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月14日 上午9:46:55
	 */
	@RequestMapping(value = "/check")
	@ResponseBody
	public JsonReturn check(Integer[] ids) {
		try {
			if (ids != null && ids.length > 0) {
				boolean result = dictionaryService.checkByIds(ids);
				if (result) {
					return new JsonReturn(200, "ok");
				}
			} else {
				return new JsonReturn(400, "请选择需要删除的选项");
			}
			return new JsonReturn(300, "无子集");
		} catch (Exception e) {
			LOGGER.error("查询字典数据子集失败!" + e);
			return new JsonReturn(400, "删除失败，数据被引用");
		}
	}

	/**
	 * 根据字典类型查询所有字典数据
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月6日 下午3:36:53
	 * @param dataItemId
	 *            字典类型id
	 */
	@RequestMapping(value = "/findByDataItemId")
	@ResponseBody
	public JsonReturn findByDataItemId(Integer dataItemId) {
		try {
			List<Map<String, Object>> list = dictionaryService.findByDataItemId(dataItemId);
			return new JsonReturn(200, "查询成功", list);
		} catch (Exception e) {
			LOGGER.error("字典类型查询所有字典数据失败!" + e);
			return new JsonReturn(400, "查询失败");
		}
	}

	/**
	 * 跳转到导入人员类别页面
	 * 
	 * @author 陈靖原
	 * @date 2017年12月19日 下午3:42:37
	 */
	@RequestMapping("/goToLeadExcel")
	public String goToLeadExcel(Model model) {
		return "/admin/dictionary/toLeadExcel";
	}

	/**
	 * 导入人员类别数据
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月19日 下午3:42:37
	 */
	@ResponseBody
	@RequestMapping("/toLeadExcel")
	public JsonReturn toLeadExcel(MultipartFile file, HttpServletRequest request) {// 获取当前登录用户
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		// 成功数量
		int suc = 0;
		// 失败数量
		int err = 0;
		// 成功的人员类别集合
		List<Dictionary> suclist = new ArrayList<Dictionary>();
		// 错误信息
		StringBuilder errInfo = new StringBuilder();
		LOGGER.info("开始导入人员类别execl表格");
		try {
			if (file.isEmpty()) {
				LOGGER.info("导入失败,文件不存在!");
				errInfo.append("导入失败,文件不存在!");
				return new JsonReturn(400, errInfo.toString());
			}
			InputStream in = null;
			List<List<Object>> listob = null;
			in = file.getInputStream();
			try {
				listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
			} catch (Exception e) {
				return new JsonReturn(400, "请上传execl文件");
			}
			long time1 = System.currentTimeMillis();
			LOGGER.info("人员类别execl表格读取花费时间：" + (System.currentTimeMillis() - time1) / 1000.0);
			if (listob != null && listob.size() > 0) {
				// 字典类型为人员类别
				List<Filter> filters = new ArrayList<Filter>();
				filters.add(Filter.eq("itemCode", "personClass"));
				DictionaryType dc = dictionaryTypeService.get(filters);
				// 数据库字典值
				List<Filter> filter = new ArrayList<Filter>();
				filter.add(Filter.eq("dictionaryType", dc));
				List<Dictionary> dis = dictionaryService.findList(null, filter, null, null, Fetch.join("dictionaryType"));
				for (List<Object> list2 : listob) {
					if (dc == null) {
						LOGGER.info("暂无人员类别字典类型!");
						errInfo.append("暂无人员类别字典类型!");
						err = listob.size();
						break;
					}
					if (list2 == null || list2.size() == 0) {
						continue;
					}
					if ("无".equals(list2.get(0).toString()) && "无".equals(list2.get(1).toString())) {
						err++;
						continue;
					}
					if ("无".equals(list2.get(0).toString())) {
						LOGGER.info("第" + (suc + err + 2) + "行错误,请填写类别编码!");
						errInfo.append("第" + (suc + err + 2) + "行错误,请填写类别编码!" + " <br>");
						err++;
						continue;
					}
					if (!isCode(list2.get(0).toString())) {
						LOGGER.info("第" + (suc + err + 2) + "行错误,请按格式填写类别编码!");
						errInfo.append("第" + (suc + err + 2) + "行错误,请按格式填写类别编码!" + " <br>");
						err++;
						continue;
					}
					if ("无".equals(list2.get(1).toString())) {
						LOGGER.info("第" + (suc + err + 2) + "行错误,请填写类别名称!");
						errInfo.append("第" + (suc + err + 2) + "行错误,请填写类别名称!" + " <br>");
						err++;
						continue;
					}
					boolean flag = false;
					for (Dictionary dic : dis) {
						if (StringUtils.isNotBlank(dic.getItemName())
								&& dic.getItemName().equals(list2.get(1).toString())
								&& StringUtils.isNotBlank(dic.getItemValue())
								&& dic.getItemValue().equals(list2.get(0).toString())) {
							LOGGER.info("第" + (suc + err + 2) + "行错误,已存在相同人员类别!");
							errInfo.append("第" + (suc + err + 2) + "行错误,已存在相同人员类别!" + " <br>");
							flag = true;
							break;
						}
					}
					if (flag) {
						err++;
						continue;
					}
					Dictionary dictionary = new Dictionary();
					// 字典名称
					dictionary.setItemName(list2.get(1).toString());
					// 字典描述
					dictionary.setDescription(list2.get(1).toString());
					// 字典排序
					dictionary.setSortCode(0);
					// 可以添加下级
					dictionary.setIsDef(1);
					// 字典值
					dictionary.setItemValue(list2.get(0).toString());
					String level = level(list2.get(0).toString().substring(0, 6));
					if (level.equals("level3")) {
						// 不可以添加下级
						dictionary.setIsDef(0);
					}
					dictionary.setDictionaryType(dc);
					// 保存重点人数据
					try {
						Dictionary d = dictionaryService.save(dictionary);
						RedisUtil.addRedis(GlobalUnit.DICTIONARY_MAP, d.getId().toString(), d);
						suclist.add(d);
						suc++;
					} catch (Exception e) {
						LOGGER.error("第" + (suc + err + 2) + "行错误,该类别已经存在" + e);
						errInfo.append("第" + (suc + err + 2) + "行错误,该类别已经存在" + " <br>");
						err++;
						continue;
					}
				}
				if (suclist != null && suclist.size() > 0) {
					// 根据保存起来的key修改父级id
					try {
						for (Dictionary str : suclist) {
							String code = str.getItemValue();
							String level = level(code);
							for (Dictionary di : dis) {
								String dicode = di.getItemValue();
								if (StringUtils.isNotBlank(dicode) && dicode.length() == 12) {
									String dlevel = level(dicode);
									if (level.equals("level2")) {
										if ("level1".equals(dlevel) && code.substring(0, 2).equals(dicode.substring(0, 2))) {
											str.setParentId(di.getId());
											dictionaryService.update(str);
											RedisUtil.addRedis(GlobalUnit.DICTIONARY_MAP, str.getId().toString(), str);
										}
									} else if (level.equals("level3")) {
										if ("level2".equals(dlevel) && code.substring(0, 4).equals(dicode.substring(0, 4))) {
											str.setParentId(di.getId());
											dictionaryService.update(str);
											RedisUtil.addRedis(GlobalUnit.DICTIONARY_MAP, str.getId().toString(), str);
										}
									} else if (level.equals("error")) {
										LOGGER.info("编码错误");
										continue;
									}
								}
							}
						}
					} catch (Exception e) {
						LOGGER.error("修改人员类别父级id失败" + e);
					}
				}
			} else {
				return new JsonReturn(400, "请按模板表格填写内容!");
			}
		} catch (Exception e) {
			LOGGER.error(suc + "行导入成功，第" + err + "行错误" + e);
			return new JsonReturn(400, suc + "行导入成功，第" + err + "行错误");
		}
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 1, request.getRequestURL().toString(), user.getPoliceName() + "导入了" + suc + "行" + "人员类别" + "数据",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		LOGGER.info(suc + "条导入成功," + err + "条导入失败");
		errInfo.append(suc + "条导入成功," + err + "条导入失败");
		return new JsonReturn(200, errInfo.toString());
	}

	/**
	 * 判断人员类别的层级
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月18日 下午3:53:39
	 */
	private static String level(String code) {
		if (StringUtils.isNotBlank(code) && !"00".equals(code.substring(0, 2)) && code.length() == 12) {
			if ("00".equals(code.substring(2, 4))) {
				return "level1";
			} else if ("00".equals(code.substring(4, 6))) {
				return "level2";
			} else {
				return "level3";
			}
		}
		return "error";
	}

	/**
	 * 判断编码是否为数字，且长度小于等于12位
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月18日 下午3:53:07
	 */
	private boolean isCode(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			if (str.length() == 12) {
				return true;
			}
		}
		return false;
	}
}
