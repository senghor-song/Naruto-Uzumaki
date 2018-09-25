/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.query.Sort;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.dto.ControlPersonDTO;
import com.ruiec.web.entity.ControlPerson;
import com.ruiec.web.entity.ControlPersonExtend;
import com.ruiec.web.entity.ControlPersonType;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.ControlPersonExtendService;
import com.ruiec.web.service.ControlPersonService;
import com.ruiec.web.service.ControlPersonTypeService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.DictionaryTypeService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.util.ImportExcelUtil;
import com.ruiec.web.util.RuiecDateUtils;

/**
 * 重点人员服务接口实现类
 * 
 * @author Senghor<br>
 * @date 2017年11月30日 上午9:01:37
 */
@Service("controlPersonServiceImpl")
public class ControlPersonServiceImpl extends BaseServiceImpl<ControlPerson, String> implements ControlPersonService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControlPersonServiceImpl.class);

	@Resource
	private UnitService unitService;
	@Resource
	private UserService usertService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private DictionaryTypeService dictionaryTypeService;
	@Resource
	private ControlPersonTypeService controlPersonTypeService;
	@Resource
	private ControlPersonExtendService controlPersonExtendService;

	/**
	 * 根据条件查询page返回页面
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月22日 下午1:43:46
	 */
	@Override
	@Transactional(readOnly = true)
	public void findByPage(Page page, ControlPerson controlPerson, ControlPersonDTO controlPersonDTO) {
		// 复杂查询条件
		DetachedCriteria cp = DetachedCriteria.forClass(ControlPerson.class);
		cp.setFetchMode("controlPersonTypes", FetchMode.SELECT);
		// 根据人员类型查询数据
        if (controlPerson.getPersonnelType() != null) {
            page.add(Filter.eq("personnelType", controlPerson.getPersonnelType()));
        }
		// 根据姓名查询
		if (StringUtils.isNotBlank(controlPerson.getName())) {
			page.add(Filter.like("name", controlPerson.getName()));
		}
		// 根据身份证号查询
		if (StringUtils.isNotBlank(controlPerson.getIdCard())) {
			page.add(Filter.like("idCard", controlPerson.getIdCard()));
		}
		// 根据单位id查询单位下的重点人
        if (controlPersonDTO.getAreaUnitId() != null || controlPersonDTO.getUnitIds() != null && controlPersonDTO.getUnitIds().size() > 0) {
            if (controlPersonDTO.getAreaUnitId() != null) {
                // 根据单位得到所有下级单位id
                controlPersonDTO.getUnitIds().addAll(unitService.findSonIds((controlPersonDTO.getAreaUnitId())));
            }
            cp.add(Restrictions.or(Restrictions.in("unitId", controlPersonDTO.getUnitIds().toArray()),
                    Restrictions.eq("userId", controlPersonDTO.getUser().getId())));
        }
		// 默认查询没有被逻辑删除的代码
		page.add(Filter.eq("isDelete", 0));
		
		// 根据Id倒序排序
		page.add(Sort.desc("id"));
		super.findByPage(page, cp, Fetch.alias("controlPersonExtend", "controlPersonExtendAlias", JoinType.LEFT_OUTER_JOIN));
	}

	/**
	 * 保存重点人数据
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月11日 上午10:10:12
	 */
	@Override
	@Transactional
	public JsonReturn save(ControlPerson controlPerson, ControlPersonExtend controlPersonExtend, ControlPersonDTO controlPersonDTO) {
		String personnlType = "";
		if (controlPerson.getPersonnelType() != null) {
			if (controlPerson.getPersonnelType() == 1) {
				personnlType = "重点人员";
			} else if (controlPerson.getPersonnelType() == 2) {
				personnlType = "关注人员";
			} else if (controlPerson.getPersonnelType() == 3) {
				personnlType = "外地人员";
			}
		}
		controlPerson.setColumnTubeState("1");// 默认列管
		controlPerson.setColumnDate(new Date());// 列管时间
		// 如果第二级单位有数据则使用第二级的单位
		if (controlPersonDTO.getTownUnitId() != null) {
			controlPersonDTO.setAreaUnitId(controlPersonDTO.getTownUnitId());
		}
		// 判断身份证号是否被添加过
		List<Filter> filters1 = new ArrayList<Filter>();
		filters1.add(Filter.eq("idCard", controlPerson.getIdCard()));
		if (super.get(filters1) != null) {
			return new JsonReturn(400, "该身份证号已被添加过!");
		}
		// 获取人员类别
		String personTypes[] = new String[] {};
		if (controlPersonDTO.getCategories() != null) {
			personTypes = controlPersonDTO.getCategories().split(",");
		}
		controlPerson.setColumnTubeState("1");
		controlPerson.setIsAuditKeyPersonColumnTub("1");
		controlPersonExtend.setControlPerson(controlPerson);
		// 将重点人扩展放在重点人里进行
		controlPerson.setControlPersonExtend(controlPersonExtend);

		// 责任民警数据
		if (controlPersonDTO.getPoliceUserId() != null) {
			controlPerson.setUserId(controlPersonDTO.getPoliceUserId());
		}
		// 责任单位数据
		if (controlPersonDTO.getAreaUnitId() != null) {
			controlPerson.setUnitId(controlPersonDTO.getAreaUnitId());
		}

		if (controlPersonDTO.getAreaUnitId() == 1 && controlPersonDTO.getPoliceUserId() == null) {
			// 未下发
			controlPerson.setRegisterState("1");
		} else {
			if (!controlPerson.emiptyControlPerson()) {
				// 已下发未完善
				controlPerson.setRegisterState("3");
			} else {
				if (controlPerson.emiptyControlPerson()) {
					// 已下发已完善
					controlPerson.setRegisterState("4");
				}
			}
		}
		// 判断是否有填写必填项
		if (!controlPerson.emiptyRequiredControlPerson()) {
			return new JsonReturn(400, personnlType + "添加失败，请填写必填的数据");
		}
		try {
			// 保存重点人数据
			controlPerson = super.save(controlPerson);
			// 防止数据重复插入，先删除该重点人所有的类别
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(Filter.eq("controlPerson", controlPerson));
			// 先删除对应的所有人员类别，然后重新添加
			controlPersonTypeService.delete(filters);
			// 保存重点人对应的人员类别数据
			for (int i = 0; i < personTypes.length; i++) {
				// 添加中间表数据
				ControlPersonType controlPersonType = new ControlPersonType();
				controlPersonType.setControlPerson(controlPerson);
				controlPersonType.setDictionaryId(Integer.valueOf(personTypes[i]));
				controlPersonTypeService.save(controlPersonType);
			}
			// 返回至对应的人员类型列表
			return new JsonReturn(200, personnlType + "添加成功", "/admin/controlPerson/list.shtml?personnelType=" + controlPerson.getPersonnelType());
		} catch (Exception e) {
			LOGGER.error(personnlType + "添加失败", e);
			return new JsonReturn(400, personnlType + "添加失败");
		}
	}

	/**
	 * 重写保存重点人员修改信息
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月11日 上午10:10:12
	 */
	@Override
	@Transactional
	public ControlPerson update(ControlPerson controlPerson, ControlPersonExtend controlPersonExtend, ControlPersonDTO controlPersonDTO) {
		/*
		 * if (controlPerson == null || controlPersonExtend == null) { return
		 * new JsonReturn(400, "重点人员修改失败,请完善信息!"); } // 如果第二级单位有数据则使用第二级的单位 if
		 * (controlPersonDTO.getUnitId2() != null) {
		 * controlPersonDTO.setUnitId(controlPersonDTO.getUnitId2()); } //
		 * 获取修改之前的数据 ControlPerson formerControlPerson =
		 * super.get(controlPerson.getId()); // 判断新省份证号和旧身份证号是否一致，一致则不判断是否被添加过
		 * if
		 * (!formerControlPerson.getIdCard().equals(controlPerson.getIdCard()))
		 * { List<Filter> filters1 = new ArrayList<Filter>(); filters1.add(new
		 * Filter().eq("idCard", controlPerson.getIdCard())); if
		 * (super.get(filters1) != null) { return new JsonReturn(400,
		 * "该身份证号已被添加过!"); } } if
		 * ("3".equals(formerControlPerson.getIsAuditKeyPersonColumnTub())) {
		 * controlPerson.setIsAuditKeyPersonColumnTub("1"); } // 获取人员类别 String
		 * personTypes[] = new String[] {}; if (controlPersonDTO.getCategories()
		 * != null) { personTypes = controlPersonDTO.getCategories().split(",");
		 * }
		 */
		// 修改修改数据时间
		controlPersonExtend.setControlPerson(controlPerson);
		controlPerson.setControlPersonExtend(controlPersonExtend);

		// 责任民警数据
		if (controlPersonDTO.getPoliceUserId() != null) {
			controlPerson.setUserId(controlPersonDTO.getPoliceUserId());
		}
		// 责任单位数据
		controlPerson.setUnitId(controlPersonDTO.getAreaUnitId());

		if (controlPersonDTO.getAreaUnitId() == 1 && controlPersonDTO.getPoliceUserId() == null) {
			// 未下发
			controlPerson.setRegisterState("1");
		} else {
			if (!controlPerson.emiptyControlPerson()) {
				// 已下发未完善
				controlPerson.setRegisterState("3");
			} else {
				if (controlPerson.emiptyControlPerson()) {
					// 已下发已完善
					controlPerson.setRegisterState("4");
				}
			}
		}
		return controlPerson;
		/*
		 * try { // 保存重点人数据 controlPerson.setControlPersonExtend(null);
		 * update(controlPerson);
		 * controlPersonExtendService.update(controlPersonExtend);
		 * //该操作是防止扩展表的信息被删除，信息丢失导致不能修改这部分数据做的处理 if
		 * (controlPersonExtendService.get(controlPerson.getId()) == null) {
		 * controlPersonExtend.setId(controlPerson.getId());
		 * controlPersonExtendService.save(controlPersonExtend); } //
		 * 先删除改重点人所有的类别 List<Filter> filters = new ArrayList<Filter>();
		 * filters.add(new Filter().eq("controlPerson", controlPerson)); //
		 * 先删除对应的所有人员类别，然后重新添加 controlPersonTypeService.delete(filters); //
		 * 保存重点人对应的人员类别数据 for (int i = 0; i < personTypes.length; i++) { //
		 * 添加中间表数据 ControlPersonType controlPersonType = new
		 * ControlPersonType();
		 * controlPersonType.setControlPerson(controlPerson);
		 * controlPersonType.setDictionaryId(Integer.valueOf(personTypes[i]));
		 * controlPersonTypeService.save(controlPersonType); } // 返回至对应的人员类型列表
		 * return new JsonReturn(200, "重点人员修改成功",
		 * "/admin/controlPerson/list.shtml?personnelType=" +
		 * controlPerson.getPersonnelType()); } catch (Exception e) {
		 * e.printStackTrace(); LOGGER.error("重点人员修改失败", e); return new
		 * JsonReturn(400, "重点人员修改失败"); }
		 */
	}

	/**
	 * 根据单位id获取下级所有id
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月20日 下午10:24:24
	 */
	private List<Integer> getAdminUnits(Integer unitId) {
		List<Integer> adminUnits = new ArrayList<Integer>();
		List<Object> unitIds = unitService.findSonId(unitId);
		if (unitIds.size() > 0) {
			for (int i = 0; i < unitIds.size(); i++) {
				adminUnits.add(Integer.valueOf(unitIds.get(i).toString()));
			}
		}
		return adminUnits;
	}

	/**
	 * 审核重点人
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月22日 下午4:35:46
	 */
	@Override
	@Transactional
	public JsonReturn isColumn(Integer id, Integer isColumn) {
		// 判断重点人id和审核状态是否有值
		if (id == null || isColumn == null) {
			return new JsonReturn(400, "审核失败，请稍后再试");
		}
		// 新建重点人对象，存储重点人id和审核状态
		ControlPerson controlPerson = new ControlPerson();
		controlPerson.setId(id);
		// 判断要修改的审核状态 1不通过审核 2为通过审核状态 3审核失败
		if (isColumn == 2) {
			controlPerson.setIsAuditKeyPersonColumnTub("2");
			super.updateInclude(controlPerson, new String[] { "isAuditKeyPersonColumnTub" }, null);
			return new JsonReturn(200, "已设置为通过审核");
		} else {
			controlPerson.setIsAuditKeyPersonColumnTub("3");
			super.updateInclude(controlPerson, new String[] { "isAuditKeyPersonColumnTub" }, null);
			return new JsonReturn(200, "已设置为未通过审核");
		}
	}

	/**
	 * 导入execl表格数据
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月22日 下午4:34:36
	 */
	@Override
	@Transactional
	public JsonReturn toLeadExcel(MultipartFile execlFile, Integer personnelType) {
		int row = 0, column = 0;
		LOGGER.info("开始导入重点人员execl表格");
		try {
			if (execlFile.isEmpty()) {
				return new JsonReturn(400, "导入失败,文件不存在!");
			}
			InputStream in = null;
			List<List<Object>> listob = null;
			in = execlFile.getInputStream();
			try {
				listob = new ImportExcelUtil().getBankListByExcel(in, execlFile.getOriginalFilename());
			} catch (Exception e) {
				return new JsonReturn(400, "请上传execl文件");
			}
			long time1 = System.currentTimeMillis();/*
													 * List<List<Object>> list =
													 * ExcelUtil
													 * .readExcel(file);
													 */
			LOGGER.info("重点人员execl表格读取花费时间：" + (System.currentTimeMillis() - time1) / 1000.0);
			if (listob != null && listob.size() > 0) {
				ControlPerson controlPerson = new ControlPerson();
				ControlPersonExtend controlPersonExtend = new ControlPersonExtend();
				controlPerson.setPersonnelType(personnelType);// 人员类型
				controlPerson.setColumnTubeMode("公开");// 列控方式
				for (List<Object> list2 : listob) {
					row++;
					// 优先读取表格中的数据，表格中的数据没有问题则去添加其他数据
					column = 1;
					if ("无".equals(list2.get(0).toString())) {
						return new JsonReturn(400, "导入失败，第" + row + "行,第" + column + "列没有数据，插入失败!");
					}
					controlPerson.setName(list2.get(0).toString());// 姓名
					column = 2;
					if ("无".equals(list2.get(1).toString())) {
						return new JsonReturn(400, "导入失败，第" + row + "行,第" + column + "列没有数据，插入失败!");
					} else if ("男".equals(list2.get(1).toString()) || "女".equals(list2.get(1).toString())) {
						controlPerson.setSex(list2.get(1).toString());// 性别
					} else {
						return new JsonReturn(400, "导入失败，第" + row + "行,第" + column + "列数据不是正确的格式，插入失败!");
					}
					column = 3;
					if ("无".equals(list2.get(2).toString())) {
						return new JsonReturn(400, "导入失败，第" + row + "行,第" + column + "列没有数据，插入失败!");
					}
					controlPerson.setIdCard(list2.get(2).toString());// 身份证号
					column = 4;
					controlPerson.setPhone(list2.get(3).toString());// 手机号
					column = 5;
					if ("无".equals(list2.get(4).toString())) {
						return new JsonReturn(400, "导入失败，第" + row + "行,第" + column + "列没有数据，插入失败!");
					}
					Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_CODE_MAP, list2.get(4));// 根据提供的单位编码获取单位数据
					if (unit != null) {
						controlPerson.setUnitId(unit.getId());// 单位数据
					}
					// 逻辑判断存储的数据
					controlPerson.setIsAuditKeyPersonColumnTub("1");
					// 将重点人扩展放在重点人里进行
					controlPersonExtend.setControlPerson(controlPerson);
					controlPerson.setControlPersonExtend(controlPersonExtend);
					// 保存重点人数据
					try {
						column = 0;
						super.save(controlPerson);
					} catch (Exception e) {
						LOGGER.info("第" + row + "行,第" + column + "列数据异常，插入失败!" + e);
						return new JsonReturn(400, "导入失败，第" + row + "行身份证号重复数据异常，插入失败!");
					}
				}
			}
		} catch (Exception e) {
			LOGGER.info("第" + row + "行,第" + column + "数据异常，插入失败!" + e);
			return new JsonReturn(400, "导入失败，第" + row + "行,第" + column + "列数据异常，插入失败!");
		}
		return new JsonReturn(200, "导入成功", "/admin/controlPerson/list.shtml?personnelType=" + personnelType);
	}

	/**
	 * 是否有权限操作改人员
	 * 
	 * @param id
	 *            =人员id,user对象,登录用户管理单位对象
	 * @author Senghor<br>
	 * @date 2017年12月28日 下午4:43:12
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isPower(Integer id, User user, LoginUserUnit loginUserUnit) {
		// 超管直接则允许
		if (user.getId() == 1) {
			return true;
		}
		// 得到当前操作的人员
		ControlPerson controlPerson = super.get(id, null);
		if (controlPerson != null) {
			if (loginUserUnit.getUnitIds() != null && loginUserUnit.getUnitIds().size() > 0) {
				for (int i = 0; i < loginUserUnit.getUnitSonIds().size(); i++) {
					// 市局管理员直接则允许
					if (loginUserUnit.getUnitSonIds().get(i) == 1) {
						return true;
					} else if (loginUserUnit.getUnitSonIds().get(i).equals(controlPerson.getUnitId())) {
						return true;
					}
				}
			} 
		    if (user.getId().equals(controlPerson.getUserId())) {
                return true;
            }
		}
		return false;
	}

	/**
	 * 根据id查询重点人详情（api）
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月10日 上午9:51:54
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> findById(Integer id) {
		Map<String, Object> map = new HashMap<>();
		ControlPerson controlPerson = super.get(id, null, Fetch.alias("user", "userAlias", JoinType.LEFT_OUTER_JOIN),
				Fetch.alias("unit", "unitAlias", JoinType.LEFT_OUTER_JOIN),
				Fetch.alias("controlPersonExtend", "controlPersonExtendAlias", JoinType.LEFT_OUTER_JOIN));
		if (controlPerson != null) {
			map.put("photo", ObjectUtils.firstNonNull(controlPerson.getControlPersonExtend().getPhoto(),""));
			map.put("name", controlPerson.getName());
			map.put("phone", ObjectUtils.firstNonNull(controlPerson.getPhone(), GlobalUnit.NULLMSG));
			map.put("idCard", controlPerson.getIdCard());
			map.put("sex", controlPerson.getSex());
			// 入库时间
			map.put("createTime", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(controlPerson.getCreateDate()));
			// 籍贯
			map.put("nativePlace", ObjectUtils.firstNonNull(controlPerson.getControlPersonExtend().getNativePlace(), GlobalUnit.NULLMSG));
			// 户籍地区
			map.put("householdRegisterPlace", ObjectUtils.firstNonNull(controlPerson.getControlPersonExtend().getHouseholdRegisterPlace(), GlobalUnit.NULLMSG));
			// 现住地区
			map.put("nowLiveArea", ObjectUtils.firstNonNull(controlPerson.getControlPersonExtend().getNowLiveArea(), GlobalUnit.NULLMSG));
			// 民族
			Dictionary dictionary = null;
			if (controlPerson.getControlPersonExtend().getNation() != null) {
				dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPerson.getControlPersonExtend().getNation());
				if (dictionary == null) {
					dictionary = dictionaryService.get(controlPerson.getControlPersonExtend().getNation());
				}
				if (dictionary != null) {
					map.put("nation", dictionary.getItemName());
				} else {
					map.put("nation", GlobalUnit.NULLMSG);
				}
			} else {
				map.put("nation", GlobalUnit.NULLMSG);
			}
			// 人员类别
			List<ControlPersonType> list = controlPerson.getControlPersonTypes();
			String personTypes = new String();
			if (list != null && list.size() > 0) {
				for (ControlPersonType controlPersonType : list) {
					dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPersonType.getDictionaryId());
					if (dictionary == null) {
						dictionary = dictionaryService.get(controlPersonType.getDictionaryId());
					}
					personTypes = personTypes + dictionary.getItemName() + ", ";
				}
				// 移除最后一个，
				personTypes = personTypes.substring(0, personTypes.length() - 2);
			} else {
				personTypes = GlobalUnit.NULLMSG;
			}
			map.put("controlPersonTypes", personTypes);
			// 单位名称
			if (controlPerson.getUnitId() != null) {
				Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, controlPerson.getUnitId());
				map.put("unitName", unit.getUnitName());
			} else {
				map.put("unitName", GlobalUnit.NULLMSG);
			}
			if (controlPerson.getUserId() != null) {
				User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, controlPerson.getUserId());
				map.put("policeName", user.getPoliceName());
			} else {
				map.put("policeName", GlobalUnit.NULLMSG);
			}
			map.put("reason", ObjectUtils.firstNonNull(controlPerson.getControlPersonExtend().getReason(), GlobalUnit.NULLMSG));
		}
		return map;
	}
}
