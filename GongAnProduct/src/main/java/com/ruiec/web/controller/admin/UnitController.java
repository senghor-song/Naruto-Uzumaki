package com.ruiec.web.controller.admin;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.UnitType;
import com.ruiec.web.entity.User;
import com.ruiec.web.entity.UserUnit;
import com.ruiec.web.interceptor.MySessionContext;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.DictionaryTypeService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UnitTypeService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.service.UserUnitService;
import com.ruiec.web.util.ImportExcelUtil;

import net.sf.json.JSONObject;

/**
 * 单位控制器
 * 
 * @author Senghor<br>
 * @date 2017年11月29日 下午4:25:59
 */
@Controller("unitController")
@RequestMapping("/admin/unit")
public class UnitController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(UnitController.class);

	@Resource
	private UnitService unitService;
	@Resource
	private UnitTypeService unitTypeService;
	@Resource
	private UserService userService;
	@Resource
	private UserUnitService userUnitService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private DictionaryTypeService dictionaryTypeService;
	@Resource
	private OperationLogService operationLogService;

	/**
	 * 区级单位列表查询方法
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/list")
	public String list(Page page, Model model, HttpServletRequest request) {
//		// 获取当前登录用户
//		User user = (User) request.getSession().getAttribute("user");
		// 获取单位列表数据
		model.addAttribute("areaList", unitService.unitListMaps());
//		try {
//			// 插入操作日志
//			operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看了单位列表",
//					LogUtil.getData(request.getParameterMap()));
//		} catch (Exception e) {
//			LOGGER.error("插入日志失败!" + e);
//		}
		return "/admin/unit/list";
	}

	/**
	 * 查询所有区县级和乡镇级单位
	 * 
	 * @author 贺云<br>
	 * @date 2017年12月11日 下午4:35:18
	 */
	@RequestMapping("/getAdminUnit")
	@ResponseBody
	public JsonReturn getAdminUnit(Integer userId) {
		// 存储返回的数据map
		JSONObject json = new JSONObject();
		List<Map<String, Object>> listMap = unitService.getAdminUnit(userId);
		json.put("townList", listMap);
		return new JsonReturn(200, "成功", json);
	}

	/**
	 * 跳转单位添加页面
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/add")
	public String add(Model model, Integer parentId) {
		// id没有值说明需要添加区级单位，有则可以选择警种
		if (parentId != null) {
			model.addAttribute("parentId", parentId);
			model.addAttribute("isNextSelect", 1);
		} else {
			parentId = 1;
			model.addAttribute("isNextSelect", 0);
		}
		Unit unit = new Unit();
		unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, parentId);
		// 判断父级单位是否是警种，是警种则只能添加警种下级单位
		if (unit.getIsPoliceSection() == 1) {
			model.addAttribute("isPoliceSection", 1);
		} else {
			model.addAttribute("isPoliceSection", 0);
		}
		model.addAttribute("unit", unit);
		return "/admin/unit/add";
	}

	/**
	 * 保存单位添加信息
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonReturn save(Unit addUnit, Integer parentPrikey, String code, Integer policeUnitId, HttpServletRequest request) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		if (!validate(addUnit)) {
			return new JsonReturn(400, "单位添加失败,请填写完整数据");
		}
		if ("00".equals(code)) {
			return new JsonReturn(400, "该编码不可使用");
		}
		JsonReturn jsonReturn = unitService.saveUnit(addUnit, parentPrikey, code, policeUnitId);
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(user, 1, request.getRequestURL().toString(), user.getPoliceName() + "新增了" + addUnit.getUnitName() + "单位信息",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		return jsonReturn;
	}

	/**
	 * 跳转单位修改页面
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/edit")
	public String edit(Model model, Integer parentId) {
		Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, parentId);

		if ("area".equals(unit.getUnitRank())) {
			// 修改区级单位
			if (unit.getIsPoliceSection() == 0) {
				model.addAttribute("code", unit.getAreaCode());
			} else {
				model.addAttribute("code", unit.getTownCode());
			}
			model.addAttribute("isNextSelect", 0);
		} else if ("town".equals(unit.getUnitRank())) {
			// 修改镇级单位
			if (unit.getParentId() != null) {
				model.addAttribute("code", unit.getTownCode());
			} else {
				model.addAttribute("code", unit.getOther1Code());
			}
			model.addAttribute("isNextSelect", 1);
		}
		model.addAttribute("isPoliceSection", unit.getIsPoliceSection());
		model.addAttribute("unit", unit);
		return "/admin/unit/edit";
	}

	/**
	 * 保存单位修改信息
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/update")
	@ResponseBody
	public JsonReturn update(Unit unit, String code, Integer policeUnitId, HttpServletRequest request) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		if (!validate(unit)) {
			return new JsonReturn(400, "单位修改失败,请填写完整数据");
		}
		if (code.equals("00")) {
			return new JsonReturn(400, "该编码不可使用");
		}
		JsonReturn jsonReturn = unitService.updateUnit(unit, code, policeUnitId);
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(user, 3, request.getRequestURL().toString(), user.getPoliceName() + "修改了" + unit.getUnitName() + "单位信息",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		return jsonReturn;
	}

	/**
	 * 根据id删除单位信息
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public JsonReturn delete(Integer id, HttpServletRequest request) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, id);
		try {
			List<Integer> unitIds = unitService.findSonIds(id);
			if (unitIds.size() > 1) {
				return new JsonReturn(400, "单位删除失败,请先删除下级所属单位");
			}
			unitService.delete(id);
		} catch (Exception e) {
			LOGGER.error("单位删除失败" + e);
			return new JsonReturn(400, "单位删除失败,请检查该单位是否被使用");
		}
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(user, 2, request.getRequestURL().toString(), user.getPoliceName() + "删除了" + unit.getUnitName() + "单位信息",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		//下线用户
		outLogin(unit);
		// 删除单位id缓存
		RedisUtil.delRedis(GlobalUnit.UNIT_MAP, unit.getId().toString());
		// 单位编码缓存
		RedisUtil.delRedis(GlobalUnit.UNIT_CODE_MAP, Unit.getUnitCode(unit));
		return new JsonReturn(200, "单位删除成功", "/admin/unit/list.shtml");
	}

	/**
	 * 跳转关联警种页面
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/linkPolice")
	public String linkPolice(Model model, Integer id) {
		model.addAttribute("unitId", id);
		model.addAttribute("polices", dictionaryService.findByItemCode("policeSpecies", 0));
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("unitId", id));
		model.addAttribute("linkPolices", unitTypeService.findList(null, filters, null));
		return "/admin/unit/linkPolice";
	}

	/**
	 * 保存关联警种
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/saveLinkPolice")
	@ResponseBody
	public JsonReturn saveLinkPolice(Integer[] policesIds, Integer unitId) {
		try {
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(Filter.eq("unitId", unitId));
			unitTypeService.delete(filters);
			if (policesIds != null) {
				if (policesIds.length>10) {
					return new JsonReturn(400, "最多关联10个警种");
				}
				for (int i = 0; i < policesIds.length; i++) {
					// 保存关联警种的数据
					UnitType unitType = new UnitType();
					unitType.setUnitId(unitId);
					unitType.setDictionaryId(policesIds[i]);
					unitTypeService.save(unitType);
				}
			}
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unitId);
			//下线用户
			outLogin(unit);
		} catch (Exception e) {
			LOGGER.error("关联警种失败" + e);
			return new JsonReturn(400, "关联警种失败");
		}
		return new JsonReturn(200, "关联成功");
	}

	/**
	 * 根据单位查询下级单位
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/getNextUnit")
	@ResponseBody
	public JsonReturn getNextUnit(Integer id) {
		if (id == null) {
			return new JsonReturn(400, "单位查询失败,缺少单位id");
		}
		try {
			List<Map<String, Object>> listMap = unitService.getNextUnit(id);
			return new JsonReturn(200, "单位查询成功", listMap);
		} catch (Exception e) {
			LOGGER.error("单位查询失败" + e);
			return new JsonReturn(400, "单位查询失败");
		}
	}

	/**
	 * 根据单位查询下面所面所有的警员
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/getUnitPerson")
	@ResponseBody
	public JsonReturn getUnitPerson(Integer areaId, Integer townId) {
		if (areaId == null) {
			return new JsonReturn(400, "警员查询失败,缺少单位id");
		}
		try {
			List<Map<String, Object>> listMap = userService.getUnitPerson(areaId, townId);
			return new JsonReturn(200, "警员查询成功", listMap);
		} catch (Exception e) {
			LOGGER.error("警员查询失败" + e);
			return new JsonReturn(400, "警员查询失败");
		}
	}

	/**
	 * 获取所有的区级警种单位
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/getPoliceUnit")
	@ResponseBody
	public JsonReturn getPoliceUnit(Integer isPolice) {
		try {
			List<Map<String, Object>> listMap = unitService.getPoliceUnit(isPolice);
			return new JsonReturn(200, "单位查询成功", listMap);
		} catch (Exception e) {
			LOGGER.error("单位查询失败" + e);
			return new JsonReturn(400, "单位查询失败");
		}
	}

	/**
	 * 根据单位id查询下级单位
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/getUnitIsPolice")
	@ResponseBody
	public JsonReturn getUnitIsPolice(Integer id) {
		if (id == null) {
			return new JsonReturn(400, "单位查询失败,缺少单位id");
		}
		try {
			List<Map<String, Object>> listMap = unitService.getUnitIsPolice(id);
			return new JsonReturn(200, "单位查询成功", listMap);
		} catch (Exception e) {
			LOGGER.error("单位查询失败" + e);
			return new JsonReturn(400, "单位查询失败");
		}
	}

	/**
	 * 跳转到导入单位页面
	 * 
	 * @author Senghor<br>
	 * @modify 陈靖原
	 * @date 2017年12月19日 下午3:42:37
	 */
	@RequestMapping("/goToLeadExcel")
	public String goToLeadExcel(Model model) {
		return "/admin/unit/toLeadExcel";
	}

	/**
	 * 导入单位数据
	 * 
	 * @author Senghor <br>
	 * @modify 陈靖原
	 * @date 2017年12月19日 下午3:42:37
	 */
	@ResponseBody
	@RequestMapping("/toLeadExcel")
	public JsonReturn toLeadExcel(MultipartFile file, HttpServletRequest request) {// 获取当前登录用户
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		//成功数量
		int suc = 0;
		//失败数量
		int err = 0;
		//成功的单位集合
		List<Unit> suclist = new ArrayList<Unit>();
		//错误信息
		StringBuilder erroInfo = new StringBuilder();
		LOGGER.info("开始导入单位execl表格");
		try {
			if (file.isEmpty()) {
				LOGGER.info("导入失败，文件不存在！");
				erroInfo.append("导入失败,文件不存在!");
				return new JsonReturn(400, erroInfo.toString());
			}
			InputStream in = null;
			List<List<Object>> listob = null;
			in = file.getInputStream();
			try {
				listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
			} catch (Exception e) {
				LOGGER.info("请上传execl文件");
				erroInfo.append("请上传execl文件");
				return new JsonReturn(400, erroInfo.toString());
			}
			long time1 = System.currentTimeMillis();
			LOGGER.info("单位execl表格读取花费时间：" + (System.currentTimeMillis() - time1) / 1000.0);
			if (listob != null && listob.size() > 0) {
				// 获取所有缓存单位
				Map<String, Object> map = RedisUtil.getRedisAll(GlobalUnit.UNIT_MAP);
				List<Unit> ulist = new ArrayList<Unit>();
				if (map != null) {
					// 获取map集合中的所有键的Set集合
					Set<String> keySet = map.keySet();
					Iterator<String> it = keySet.iterator();
					while (it.hasNext()) {
						String key1 = it.next();
						Unit value = (Unit) map.get(key1);
						ulist.add(value);
					}
				}
				if (ulist == null || ulist.size() == 0) {
					LOGGER.info("读取缓存数据失败");
					erroInfo.append("读取缓存数据失败");
					return new JsonReturn(400, erroInfo.toString());
				}
				// 读取市局单位编码(省市)
				Unit un = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, "1");
				String pcCode = un.getProvinceCode()+un.getCityCode();
				for (List<Object> list2 : listob) {
					if (list2 == null || list2.size() == 0) {
						continue;
					}
					if ("无".equals(list2.get(0).toString()) && "无".equals(list2.get(1).toString())) {
						err++;
						continue;
					}
					Unit unit = new Unit();
					if ("无".equals(list2.get(0).toString())) {
						LOGGER.error("第" + (suc + err + 2) + "行错误,请填写单位编码!");
						erroInfo.append("第" + (suc + err + 2) + "行错误,请填写单位编码!" + " <br>");
						err++;
						continue;
					}
					if ("无".equals(list2.get(1).toString())) {
						LOGGER.error("第" + (suc + err + 2) + "行错误,请填写单位名称!");
						erroInfo.append("第" + (suc + err + 2) + "行错误,请填写单位名称!" + " <br>");
						err++;
						continue;
					}
					boolean flag = false;
					// 单位编码
					String code = list2.get(0).toString();
					if (code.length() != 12) {
						LOGGER.error("第" + (suc + err + 2) + "行错误,请填写正确格式单位编码!");
						erroInfo.append("第" + (suc + err + 2) + "行错误,请填写正确格式单位编码!" + " <br>");
						err++;
						continue;
					}
					if (!pcCode.equals(code.substring(0, 4))) {
						LOGGER.error("第" + (suc + err + 2) + "行错误,该单位编码不在我市范围!");
						erroInfo.append("第" + (suc + err + 2) + "行错误,该单位编码不在我市范围!" + " <br>");
						err++;
						continue;
					}
					for (int i = 0; i < ulist.size(); i++) {
						// 判断单位名称是否重复
						if (StringUtils.isNotBlank(ulist.get(i).getUnitName())
								&& ulist.get(i).getUnitName().equals(list2.get(1).toString())) {
							LOGGER.error("第" + (suc + err + 2) + "行错误,单位名称不可重复!");
							erroInfo.append("第" + (suc + err + 2) + "行错误,单位名称不可重复!" + " <br>");
							flag = true;
							break;
						}
						// 判断单位编码是否被使用了
						if (StringUtils.isNotBlank(Unit.getUnitCode(ulist.get(i)))
								&& code.equals(Unit.getUnitCode(ulist.get(i)))) {
							LOGGER.error("第" + (suc + err + 2) + "行错误,该单位编码已被使用!");
							erroInfo.append("第" + (suc + err + 2) + "行错误,该单位编码已被使用!" + " <br>");
							flag = true;
							break;
						}
					}
					if (flag) {
						err++;
						continue;
					}
					// 单位名称
					unit.setUnitName(list2.get(1).toString());
					unit.setProvinceCode(code.substring(0, 2));
					unit.setCityCode(code.substring(2, 4));
					unit.setAreaCode(code.substring(4, 6));
					unit.setTownCode(code.substring(6, 8));
					unit.setOther1Code(code.substring(8, 10));
					unit.setOther2Code(code.substring(10, 12));
					// 单位级别，是否警种
					unit.setIsPoliceSection(0);
					if ("city".equals(level(code))) {
						unit.setUnitRank("city");
					} else if ("area".equals(level(code))) {
						unit.setUnitRank("area");
					} else if ("brTeam".equals(level(code))) {
						unit.setUnitRank("area");
						unit.setIsPoliceSection(1);
					} else if ("townTeam".equals(level(code))) {
						unit.setUnitRank("town");
					} else {
						LOGGER.error("第" + (suc + err + 2) + "行错误,请填写正确格式单位编码!");
						erroInfo.append("第" + (suc + err + 2) + "行错误,请填写正确格式单位编码!" + " <br>");
						err++;
						continue;
					}
					// 保存单位数据
					try {
						Unit u = unitService.save(unit);
						// 新增单位id缓存
						RedisUtil.addRedis(GlobalUnit.UNIT_MAP, u.getId().toString(), u);
						// 新增单位编码缓存
						RedisUtil.addRedis(GlobalUnit.UNIT_CODE_MAP, Unit.getUnitCode(u), u);
						// 将单位加入成功单位集合
						suclist.add(u);
						suc++;
					} catch (Exception e) {
						LOGGER.error("第" + (suc + err + 2) + "行错误,该单位已经存在" + e);
						erroInfo.append("第" + (suc + err + 2) + "行错误,该单位已经存在!" + " <br>");
						err++;
						continue;
					}
				}
				if (suclist != null && suclist.size() > 0) {
					// 根据保存起来的key修改父级id
					try {
						for (Unit str : suclist) {
							String code = Unit.getUnitCode(str);
							String level = level(code);
							for (Unit ui : ulist) {
								String uicode = Unit.getUnitCode(ui);
								String ulevel = level(uicode);
								if ("area".equals(level) || "brTeam".equals(level)) {
									/*if ("city".equals(ulevel)) {
										if (code.substring(0, 4).equals(uicode.substring(0, 4))) {*/
											str.setParentId(1/*ui.getId()*/);
											unitService.update(str);
											// 新增单位id缓存
											RedisUtil.addRedis(GlobalUnit.UNIT_MAP, str.getId().toString(), str);
											// 新增单位编码缓存
											RedisUtil.addRedis(GlobalUnit.UNIT_CODE_MAP, Unit.getUnitCode(str), str);
									/*	}
									}*/
								} else if ("townTeam".equals(level)) {
									if ("area".equals(ulevel)) {
										if (code.substring(4, 6).equals(uicode.substring(4, 6))) {
											str.setParentId(ui.getId());
											unitService.update(str);
											// 新增单位id缓存
											RedisUtil.addRedis(GlobalUnit.UNIT_MAP, str.getId().toString(), str);
											// 新增单位编码缓存
											RedisUtil.addRedis(GlobalUnit.UNIT_CODE_MAP, Unit.getUnitCode(str), str);
										}
									}
								} else if ("error".equals(level)) {
									LOGGER.info("编码错误");
									continue;
								}
							}
						}
					} catch (Exception e) {
						LOGGER.error("修改人员类别父级id失败" + e);
					}
				}
			} else {
				LOGGER.info("请按模板表格填写内容!");
				erroInfo.append("请按模板表格填写内容!");
				return new JsonReturn(400, erroInfo.toString(), "/admin/unit/list.shtml");
			}
		} catch (Exception e) {
			LOGGER.error("导入异常"+e);
			LOGGER.error(suc + "行导入成功," + err + "行导入失败");
			erroInfo.append(suc + "行导入成功," + err + "行导入失败" + " <br>");
			return new JsonReturn(400, erroInfo.toString(), "/admin/unit/list.shtml");
		}
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 1, request.getRequestURL().toString(), user.getPoliceName() + "导入了" + suc + "行" + "单位" + "数据",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		erroInfo.append(suc + "行导入成功," + err + "行导入失败" + " <br>");
		return new JsonReturn(200, erroInfo.toString(), "/admin/unit/list.shtml");
	}

	/**
	 * 判断单位级别
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月18日 下午3:53:39
	 */
	private static String level(String code) {
		if (StringUtils.isNotBlank(code) && !"0000".equals(code.substring(0, 4)) && code.length() == 12) {
			if ("00".equals(code.substring(4, 6))) {
				// 市或支队
				if ("000000".equals(code.substring(6, 12))) {
					// 市
					return "city";
				}
				// 支队
				return "brTeam";
			} else {
				// 区或大队或派出所
				if ("000000".equals(code.substring(6, 12))) {
					// 区
					return "area";
				}
				// 大队或派出所
				return "townTeam";
			}
		}
		return "error";
	}

	
	/**
	 * 使该单位的警员和管理员下线，重新登录
	 * @author Senghor<br>
	 * @date 2018年2月7日 上午11:28:04
	 */
	private void outLogin(Unit unit) {
		if (unit==null) {
			return;
		}
		//修改单位，该单位的所有警员都需要重新登录
		List<User> userList = userService.findList(null, Filter.eq("unitId", unit.getId()), null);
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getIsOnline()!=null) {
				MySessionContext.DelSession(MySessionContext.getSession(userList.get(i).getIsOnline()));
			}
		}
		//修改单位，管理该单位的所有管理员需要重新登录
		List<UserUnit> userUnitList = userUnitService.findList(null, Filter.eq("unit", unit), null);
		for (int i = 0; i < userUnitList.size(); i++) {
			if (userUnitList.get(i).getUser().getIsOnline()!=null) {
				MySessionContext.DelSession(MySessionContext.getSession(userUnitList.get(i).getUser().getIsOnline()));
			}
		}
	}
}
