package com.ruiec.web.controller.admin;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.query.Sort;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonData;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.dto.ControlPersonDTO;
import com.ruiec.web.entity.APIConfig;
import com.ruiec.web.entity.ControlPerson;
import com.ruiec.web.entity.ControlPersonAlarm;
import com.ruiec.web.entity.ControlPersonExtend;
import com.ruiec.web.entity.ControlPersonType;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.DynamicInfo;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.APIConfigService;
import com.ruiec.web.service.ControlPersonAlarmService;
import com.ruiec.web.service.ControlPersonExtendService;
import com.ruiec.web.service.ControlPersonRelationService;
import com.ruiec.web.service.ControlPersonService;
import com.ruiec.web.service.ControlPersonTypeService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.DictionaryTypeService;
import com.ruiec.web.service.DynamicInfoService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.service.UserUnitService;
import com.ruiec.web.util.IDCard;
import com.ruiec.web.util.ImportExcelUtil;
import com.ruiec.web.util.RuiecDateUtils;
import com.ruiec.web.util.RuiecStringUtil;

/**
 * 重点人员控制器
 * 
 * @author Senghor<br>
 * @date 2017年12月1日 上午9:05:41
 */
@Controller("controlPersonController")
@RequestMapping("/admin/controlPerson")
public class ControlPersonController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(ControlPersonController.class);

	@Resource
	private ControlPersonService controlPersonService;
	@Resource
	private ControlPersonExtendService controlPersonExtendService;
	@Resource
	private ControlPersonRelationService controlPersonRelationService;
	@Resource
	private ControlPersonAlarmService controlPersonAlarmService;
	@Resource
	private ControlPersonTypeService controlPersonTypeService;
	@Resource
	private UnitService unitService;
	@Resource
	private UserService userService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private DictionaryTypeService dictionaryTypeService;
	@Resource
	private UserUnitService userUnitService;
	@Resource
	private OperationLogService operationLogService;
	@Resource
	private DynamicInfoService dynamicInfoService;
	@Resource
	private APIConfigService apiConfigService;

	/**
	 * 获取重点人员列表
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月1日 上午9:30:08
	 */
	@RequestMapping("/list")
	public String list(Page page, Model model, ControlPerson controlPerson, ControlPersonDTO controlPersonDTO, HttpServletRequest request) {
		if (controlPerson.getPersonnelType() == null) {
			return "redirect:/admin/controlPerson/list.shtml?personnelType=1";
		}
		// 人员类型
		String personnelTypeName = getPersonnelTypeName(controlPerson.getPersonnelType());
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		controlPersonDTO.setUser(user);
		// 用来保存要查询单位的子单位id
		List<Integer> adminUnits = new ArrayList<Integer>();
		if (user.getId() == 1) {
			// 最高级管理员
			// 最高级权限管理
			model.addAttribute("units", unitService.getUnitArea(0));
		} else if (loginUserUnit != null && loginUserUnit.getUnitIds() != null && loginUserUnit.getUnitIds().size() > 0) {
			// 管理员
			if ("city".equals(loginUserUnit.getUnitRank().getName())) {
				// 市局管理员
				// 区级公安局信息
				model.addAttribute("units", unitService.getUnitArea(0));
			} else {
				// 普通管理员
				// 获取当前用户所管理的单位
				List<Integer> unitIds = loginUserUnit.getUnitIds();
				List<Unit> userUnits = new ArrayList<Unit>();
				for (int i = 0; i < unitIds.size(); i++) {
					Unit unit=(Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unitIds.get(i));
					if(unit==null){
						continue;
					}
					userUnits.add(unit);
				}
				// 当前用管理单位则是管理员,没有则是警员
				if (userUnits != null && userUnits.size() > 0) {
					// 得到所管理单位的下级单位id
					adminUnits = loginUserUnit.getUnitSonIds();
					// 所管辖的公安局信息
					model.addAttribute("units", userUnits);
				}
			}
		} else {
			// 警员则只查询所管理的重点人
			page.add(Filter.eq("userId", user.getId()));
		}
		controlPersonDTO.setUnitIds(adminUnits);
		// 在控状态
		model.addAttribute("isControls", getDictionary("isControl"));
		// 危险等级
		model.addAttribute("dangerousLevels", getDictionary("dangerousLevel"));
		// 人员类别一级大类
		model.addAttribute("dataitemIds", getDictionary("personClass"));
		controlPersonService.findByPage(page, controlPerson, controlPersonDTO);
		// 分页数据
		model.addAttribute("page", page);
		// 重点人参数
		model.addAttribute("controlPerson", controlPerson);
		// 重点人查询dao条件
		model.addAttribute("controlPersonDTO", controlPersonDTO);
		try {
			// 插入操作日志
			/*operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看了" + personnelTypeName + "列表",
					LogUtil.getData(request.getParameterMap()));*/
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		return "/admin/controlPerson/list";
	}

	/**
	 * 批量撤管列管
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月11日 上午10:53:50
	 */
	@RequestMapping("/columnTubeState")
	@ResponseBody
	public JsonReturn columnTubeState(String ids, Integer state, Integer personnelType, HttpServletRequest request) {// 插入操作日志
		String personnelTypeName = getPersonnelTypeName(personnelType);
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		// 获取要撤管列管的id数组
		String idss[] = ids.split(",");
		List<String> names = new ArrayList<String>();
		String stateString = state == 1 ? "撤管" : "列管";
		// 有id则执行对应的操作
		if (idss != null && idss.length > 0) {
			for (int i = 0; i < idss.length; i++) {
				ControlPerson controlPerson = controlPersonService.get(Integer.valueOf(idss[i]));
				if (!controlPersonService.isPower(Integer.valueOf(idss[i]), user, loginUserUnit)) {
					return new JsonReturn(400, "无权限操作");
				}
				// 判断是撤管还是列管 state 1是撤管 2是列管 controlPerson.setColumnTubeState
				// 0是撤管 1是列管
				if (state == 1) {
					if ("0".equals(controlPerson.getColumnTubeState())) {
						continue;
					}
					controlPerson.setColumnTubeState("0");
					controlPerson.setColumnDate(new Date());
				} else {
					if ("1".equals(controlPerson.getColumnTubeState())) {
						continue;
					}
					controlPerson.setColumnTubeState("1");
					controlPerson.setColumnDate(new Date());
				}
				try {
					names.add(controlPerson.getName());
					// 只修改列管状态字段
					controlPersonService.updateInclude(controlPerson, new String[] { "columnTubeState", "columnDate" }, null);
				} catch (Exception e) {
					LOGGER.error(personnelTypeName + stateString + "操作失败" + e);
					return new JsonReturn(400, "操作失败");
				}
			}
		} else {
			return new JsonReturn(400, "操作失败,没有ID");
		}
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 3, request.getRequestURL().toString(), user.getPoliceName() + "对" + names.toString() + stateString
					+ "操作", LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		if (names.size() > 0) {
			if (names.size() == idss.length) {
				return new JsonReturn(200, "操作成功", "/admin/controlPerson/list.shtml?personnelType=" + personnelType);
			} else {
				return new JsonReturn(200, names.toString() + " 人员" + stateString + "成功 ,其他人员已是" + stateString + "状态",
						"/admin/controlPerson/list.shtml?personnelType=" + personnelType);
			}
		} else {
			return new JsonReturn(400, "所选中人员已经是" + stateString + "状态");
		}
	}

	/**
	 * 跳转重点人员添加页面
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/add")
	public String add(Model model, Integer personnelType, HttpServletRequest request) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		if (user.getId() == 1) {
			// 最高级管理员
			// 最高级权限管理
			model.addAttribute("units", unitService.getUnitArea(1));
		} else if (loginUserUnit != null && loginUserUnit.getUnitIds() != null && loginUserUnit.getUnitIds().size() > 0) {
			// 管理员
			if ("city".equals(loginUserUnit.getUnitRank().getName())) {
				// 市局管理员
				// 区级公安局信息
				model.addAttribute("units", unitService.getUnitArea(1));
			} else {
				// 普通管理员
				// 获取当前用户所管理的单位
				List<Integer> unitIds = loginUserUnit.getUnitIds();
				List<Unit> userUnits = new ArrayList<Unit>();
				for (int i = 0; i < unitIds.size(); i++) {
					userUnits.add((Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unitIds.get(i)));
				}
				// 当前用管理单位则是管理员,没有则是警员
				if (userUnits != null && userUnits.size() > 0) {
					// 所管辖的公安局信息
					model.addAttribute("units", userUnits);
				}
			}
		} else {
			// 警员
			// 警员则只查询所管理的重点人
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
			List<Unit> units = new ArrayList<Unit>();
			units.add(unit);
			model.addAttribute("isUser", 1);
			model.addAttribute("units", units);
			// 判断该警员属于那种单位的,显示不同的单位数据
			if ("city".equals(unit.getUnitRank()) || "area".equals(unit.getUnitRank())) {
				// 市级警员和区级警员则只显示第一单位数据
				model.addAttribute("unit1", unit);
			} else if ("town".equals(unit.getUnitRank())) {
				// 镇级警员则要显示父级单位数据,和镇级单位
				if (unit.getIsPoliceSection() == 1) {
					// 警种单位则查询警种父级单位
					model.addAttribute("unit1", (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unit.getPoliceTypesParentId()));
				} else {
					// 非警种单位则查询父级单位
					model.addAttribute("unit1", (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unit.getParentId()));
				}
				model.addAttribute("unit2", unit);
			}
			// 警员信息
			model.addAttribute("policeUser", user);
		}
		// 民族
		model.addAttribute("nations", getDictionary("nation"));
		// 在控状态
		model.addAttribute("isControls", getDictionary("isControl"));
		// 列控级别
		model.addAttribute("columnTubeLevels", getDictionary("columnTubeLevel"));
		// 危险级别
		model.addAttribute("dangerousLevels", getDictionary("dangerousLevel"));
		// 人员类别一级大类
		model.addAttribute("dataitemIds", getDictionary("personClass"));
		// 判断人员类型是否为空
		if (personnelType != null) {
			model.addAttribute("personnelType", personnelType);
		}
		return "/admin/controlPerson/add";
	}

	/**
	 * 保存重点人员添加信息
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonReturn save(ControlPerson controlPerson, ControlPersonExtend controlPersonExtend, ControlPersonDTO controlPersonDTO, HttpSession session,
			HttpServletRequest request) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		String personnelTypeName = getPersonnelTypeName(controlPerson.getPersonnelType());
		// 当填写了生日时间需要判断时间是否真实
		if (controlPersonExtend.getBirthDate() != null) {
			if (RuiecDateUtils.parse_yyyy_MM_dd(controlPersonExtend.getBirthDate()).getTime() > new Date().getTime()) {
				return new JsonReturn(400, "请不要随意填写生日时间!");
			}
		}
		try {
			if (!validate(controlPerson) || !validate(controlPersonExtend)) {
				return new JsonReturn(400, personnelTypeName + "添加失败,请填写正确数据");
			}
		} catch (Exception e) {
			LOGGER.error(personnelTypeName + "添加失败" + e);
			return new JsonReturn(400, personnelTypeName + "添加失败,请填写正确数据");
		}
		JsonReturn jsonReturn = controlPersonService.save(controlPerson, controlPersonExtend, controlPersonDTO);
		// 获取当前登录用户
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 1, request.getRequestURL().toString(), user.getPoliceName() + "新增了" + controlPerson.getName()
					+ personnelTypeName, LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		return jsonReturn;
	}

	/**
	 * 跳转重点人员修改页面
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/edit")
	public String edit(Model model, Integer id, Integer personnelType, HttpServletRequest request) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		if (!controlPersonService.isPower(id, user, loginUserUnit)) {
			return "/admin/controlPerson/list.shtml?personnelType=1";
		}
		// 判断人员类型是否为空
		if (personnelType != null) {
			model.addAttribute("personnelType", personnelType);
		}
		// 民族
		model.addAttribute("nations", getDictionary("nation"));
		// 在控状态
		model.addAttribute("isControls", getDictionary("isControl"));
		// 列控级别
		model.addAttribute("columnTubeLevels", getDictionary("columnTubeLevel"));
		// 危险等级
		model.addAttribute("dangerousLevels", getDictionary("dangerousLevel"));
		// 人员类别一级大类
		model.addAttribute("dataitemIds", getDictionary("personClass"));
		// 区级公安局信息
		model.addAttribute("units", unitService.getUnitArea(0));
		ControlPerson controlPerson = controlPersonService.get(id, null,
				Fetch.alias("controlPersonExtend", "controlPersonExtendAlias", JoinType.LEFT_OUTER_JOIN));
		// 人员类别数据
		String Categories = "";
		for (int i = 0; i < controlPerson.getControlPersonTypes().size(); i++) {
			Categories += controlPerson.getControlPersonTypes().get(i).getDictionaryId() + ",";
		}
		// 人员类别数据
		model.addAttribute("Categories", Categories);
		// 重点人数据
		model.addAttribute("controlPerson", controlPerson);
		if (loginUserUnit != null && loginUserUnit.getUnitIds() != null && loginUserUnit.getUnitIds().size() > 0 || user.getId() == 1) {
			// 管理员
			if (user.getId() != 1) {
				if ("city".equals(loginUserUnit.getUnitRank().getName())) {
					// 市局管理员
					// 区级公安局信息
					model.addAttribute("units", unitService.getUnitArea(1));
				} else {
					// 普通管理员
					// 获取当前用户所管理的单位
					List<Integer> unitIds = loginUserUnit.getUnitIds();
					List<Unit> userUnits = new ArrayList<Unit>();
					for (int i = 0; i < unitIds.size(); i++) {
						userUnits.add((Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unitIds.get(i)));
					}
					// 当前用管理单位则是管理员,没有则是警员
					if (userUnits != null && userUnits.size() > 0) {
						// 所管辖的公安局信息
						model.addAttribute("units", userUnits);
					}
				}
			} else {
				// 区级公安局信息
				model.addAttribute("units", unitService.getUnitArea(1));
			}
			// 判断所属单位是否为区级单位
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, controlPerson.getUnitId());
			if (unit != null) {
				if ("area".equals(unit.getUnitRank())) {
					model.addAttribute("unit1", unit.getId());
				} else {
					// 判断是否为警种单位,取父级id
					if (unit.getIsPoliceSection() == 1) {
						model.addAttribute("unit1", unit.getPoliceTypesParentId());
					} else {
						model.addAttribute("unit1", unit.getParentId());
					}
					model.addAttribute("unit2", unit.getId());
				}
			}
		} else {
			// 警员
			// 警员则只查询所管理的重点人
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
			List<Unit> units = new ArrayList<Unit>();
			units.add(unit);
			model.addAttribute("isUser", 1);
			model.addAttribute("units", units);
			// 判断该警员属于那种单位的,显示不同的单位数据
			if ("city".equals(unit.getUnitRank()) || "area".equals(unit.getUnitRank())) {
				// 市级警员和区级警员则只显示第一单位数据
				model.addAttribute("Unit1", unit);
			} else if ("town".equals(unit.getUnitRank())) {
				// 镇级警员则要显示父级单位数据,和镇级单位
				if (unit.getIsPoliceSection() == 1) {
					// 警种单位则查询警种父级单位
					model.addAttribute("Unit1", (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unit.getPoliceTypesParentId()));
				} else {
					// 非警种单位则查询父级单位
					model.addAttribute("Unit1", (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unit.getParentId()));
				}
				model.addAttribute("Unit2", unit);
			}
			// 警员信息
			model.addAttribute("policeUser", user);
		}
		return "/admin/controlPerson/edit";
	}

	/**
	 * 保存重点人员修改信息
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/update")
	@ResponseBody
	public JsonReturn update(ControlPerson controlPerson, ControlPersonExtend controlPersonExtend, ControlPersonDTO controlPersonDTO,
			HttpServletRequest request, int edit) {
		// 当填写了生日时间需要判断时间是否真实
		if (controlPersonExtend.getBirthDate() != null) {
		    try {
	            if (RuiecDateUtils.parse_yyyy_MM_dd(controlPersonExtend.getBirthDate()).getTime() > new Date().getTime()) {
	                return new JsonReturn(400, "请不要填写未来的时间!");
	            }
            } catch (Exception e) {
                return new JsonReturn(400, "请重新选择生日!");
            }
		}
		
		// 操作人员的类型
		String personnelTypeName = getPersonnelTypeName(controlPerson.getPersonnelType());
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		if (!controlPersonService.isPower(controlPerson.getId(), user, loginUserUnit)) {
			return new JsonReturn(400, "没有权限!");
		}
		// 如果第二级单位有数据则使用第二级的单位
		if (controlPersonDTO.getTownUnitId() != null && controlPersonDTO.getTownUnitId() != -1) {
			controlPersonDTO.setAreaUnitId(controlPersonDTO.getTownUnitId());
		}else {
			if (controlPersonDTO.getAreaUnitId()==null) {
				controlPersonDTO.setAreaUnitId(user.getUnitId());
			}
		}

		IDCard IDCard=new IDCard();
		if (!IDCard.IDCardValidate(controlPerson.getIdCard())) {
			return new JsonReturn(400, "请输入15至18位真实身份证号");
		}
		// 获取修改之前的数据
		ControlPerson formerControlPerson = controlPersonService.get(controlPerson.getId());
		// 判断新省份证号和旧身份证号是否一致,一致则不判断是否被添加过
		if (!formerControlPerson.getIdCard().equals(controlPerson.getIdCard())) {
			List<Filter> filters1 = new ArrayList<Filter>();
			filters1.add(Filter.eq("idCard", controlPerson.getIdCard()));
			if (controlPersonService.get(filters1) != null) {
				return new JsonReturn(400, "该身份证号已被添加过!");
			}
		}
		// 修改为待审核状态
		if ("3".equals(formerControlPerson.getIsAuditKeyPersonColumnTub()) || edit == 1) {
			controlPerson.setIsAuditKeyPersonColumnTub("1");
		}
		// 获取人员类别
		String personTypes[] = new String[] {};
		if (controlPersonDTO.getCategories() != null) {
			personTypes = controlPersonDTO.getCategories().split(",");
		}
		controlPerson = controlPersonService.update(controlPerson, controlPersonExtend, controlPersonDTO);
		//return controlPersonService.update(controlPerson, controlPersonExtend,controlPersonDTO);
		try {
			if (!validate(controlPerson) || !validate(controlPersonExtend)) {
				return new JsonReturn(400, personnelTypeName + "修改失败,请填写正确数据");
			}
		} catch (Exception e) {
			LOGGER.error(personnelTypeName + "修改失败" + e);
			return new JsonReturn(400, personnelTypeName + "修改失败,请填写正确数据");
		}
		try {
		    ControlPerson outControlPerson = controlPersonService.get(controlPerson.getId());
		    controlPerson.setColumnDate(outControlPerson.getColumnDate());
			// 保存重点人数据
			controlPersonService.update(controlPerson);
			// 该操作是防止扩展表的信息被删除,信息丢失导致不能修改这部分数据做的处理
			if (controlPersonExtendService.get(controlPerson.getId()) == null) {
				controlPersonExtend.setId(controlPerson.getId());
				controlPersonExtendService.save(controlPersonExtend);
			}
			// 先删除改重点人所有的类别
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(Filter.eq("controlPerson", controlPerson));
			// 先删除对应的所有人员类别,然后重新添加
			controlPersonTypeService.delete(filters);
			// 保存重点人对应的人员类别数据
			for (int i = 0; i < personTypes.length; i++) {
				// 添加中间表数据
				ControlPersonType controlPersonType = new ControlPersonType();
				controlPersonType.setControlPerson(controlPerson);
				controlPersonType.setDictionaryId(Integer.valueOf(personTypes[i]));
				controlPersonTypeService.save(controlPersonType);
			}
		} catch (Exception e) {
			LOGGER.error(personnelTypeName + "修改失败" + e);
			return new JsonReturn(400, personnelTypeName + "修改失败");
		}
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 3, request.getRequestURL().toString(), user.getPoliceName() + "编辑了" + controlPerson.getName()
					+ personnelTypeName, LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		// 返回至对应的人员类型列表
		return new JsonReturn(200, personnelTypeName + "修改成功", "/admin/controlPerson/list.shtml?personnelType=" + controlPerson.getPersonnelType());

	}

	/**
	 * 根据id删除重点人员信息
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public JsonReturn delete(Model model, Integer[] ids, HttpServletRequest request) {
		ControlPerson controlPerson = new ControlPerson();
		if (ids.length > 0) {
			controlPerson = controlPersonService.get(ids[0]);
		}
		String personnelTypeName = getPersonnelTypeName(controlPerson.getPersonnelType());
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		List<String> nameStrings = new ArrayList<String>();
		try {
			// 遍历需要删除的重点人员,做逻辑删除,即修改状态为已删除
			for (int i = 0; i < ids.length; i++) {
				if (!controlPersonService.isPower(controlPerson.getId(), user, loginUserUnit)) {
					return new JsonReturn(400, "没有权限!");
				}
				// 对已经逻辑删除的数据做处理,将身份证号
				ControlPerson delControlPerson = controlPersonService.get(ids[i]);
				delControlPerson.setIdCard(delControlPerson.getIdCard() + "_" + RuiecDateUtils.format_yyMMddHHmmss(new Date()));
				delControlPerson.setIsDelete(1);
				controlPersonService.updateInclude(delControlPerson, new String[] { "isDelete", "idCard" }, null);
				nameStrings.add(delControlPerson.getName());
			}
		} catch (Exception e) {
			LOGGER.error(personnelTypeName + "删除失败" + e);
			return new JsonReturn(400, personnelTypeName + "删除失败");
		}
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 2, request.getRequestURL().toString(), user.getPoliceName() + "删除了" + nameStrings.toString()
					+ personnelTypeName + "信息", LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		return new JsonReturn(200, personnelTypeName + "删除成功", "/admin/controlPerson/list.shtml?personnelType=" + personnelTypeName);
	}

	/**
	 * 跳转重点人员查看页面
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/look")
	public String look(Page page, Model model, HttpServletRequest request, Integer id, Integer personnelType, Integer viewType,String name) {
		String personnelTypeName = getPersonnelTypeName(personnelType);
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		if (StringUtils.isBlank(name)) {
			if (!controlPersonService.isPower(id, user, loginUserUnit)) {
				return "redirect:/admin/controlPerson/list.shtml?personnelType" + personnelType;
			}
		}
		// 根据id获取重点人员数据
		ControlPerson controlPerson = controlPersonService.get(id, null,
				Fetch.alias("controlPersonExtend", "controlPersonExtendAlias", JoinType.LEFT_OUTER_JOIN));
		model.addAttribute("controlPerson", controlPerson);
		// 根据重点人员ID获取对应的关系列表
		model.addAttribute("controlPersonRelations", controlPersonRelationService.findList(null, Filter.eq("controlPersonId", id), null));
		if (loginUserUnit != null && loginUserUnit.getUnitIds() != null && loginUserUnit.getUnitIds().size() > 0) {
			// 管理员
			if ("town".equals(loginUserUnit.getUnitRank().getName())) {
				// 区级管理员
				// 不能审核重点人
				model.addAttribute("isColumn", 1);
			}
			Unit thisUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, controlPerson.getUnitId());
			// 如果是市局警员添加的重点人,只有市局管理员才能审核
			User thisUser = new User();
			if (controlPerson.getUserId() != null) {
				thisUser = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, controlPerson.getUserId());
				if (thisUser != null && "city".equals(thisUnit.getUnitRank())) {
					if (!"city".equals(loginUserUnit.getUnitRank().getName())) {
						// 不能审核重点人
						model.addAttribute("isColumn", 1);
					}
				}
			}
		} else {
			// 警员
			// 不能审核重点人
			model.addAttribute("isColumn", 1);
		}
		// 分页数据
		model.addAttribute("page", page);
		// 人员类型
		model.addAttribute("personnelType", personnelType);
		// 左侧菜单栏显示的内容
		model.addAttribute("viewType", viewType);
		// 重点人id
		model.addAttribute("controlPersonId", id);
		// 预警总数
		model.addAttribute("alarmCount", controlPersonAlarmService.selectByPersonAlarmCount(id));
		// 操作日志
		operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看了" + controlPerson.getName()
				+ personnelTypeName + "信息", LogUtil.getData(request.getParameterMap()));
		return "/admin/controlPerson/look";
	}

	/**
	 * 统计轨迹数据
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月17日 下午4:50:02
	 */
	@RequestMapping("/countTypeList")
	public String countTypeList(Model model, Integer id) {
		// 预警总数
		model.addAttribute("alarmCount", controlPersonAlarmService.selectByPersonAlarmCount(id));
		// 轨迹统计数据
		List<Map<String, Object>> dynamicInfoCountMaps = dynamicInfoService.dynamicInfoCount(id);
		// 获取所有的轨迹类型数据
		List<APIConfig> apiConfigs = apiConfigService.getAll();
		List<Map<String, Object>> dynamicInfos = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < apiConfigs.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			int count = 0;
			for (int j = 0; j < dynamicInfoCountMaps.size(); j++) {
				if (apiConfigs.get(i).getId().equals(dynamicInfoCountMaps.get(j).get("type"))) {
					count = Integer.valueOf(dynamicInfoCountMaps.get(j).get("count").toString()).intValue();
				}
			}
			map.put("id", apiConfigs.get(i).getId());
			map.put("count", count);
			dynamicInfos.add(map);
		}
		model.addAttribute("dynamicInfos", dynamicInfos);
		return "/admin/controlPerson/typeList";
	}

	/**
	 * 根据轨迹类型id和重点人id获取轨迹分页数据
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月17日 下午2:36:28
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getDynamicInfo")
	public String getDynamicInfo(Page page, Model model, Integer id, Integer typeId) {
		if (typeId == null || typeId == 0) {
			// 根据重点人Id查询该重点人所有的预警信息
			DetachedCriteria cpa = DetachedCriteria.forClass(ControlPersonAlarm.class);
			DetachedCriteria cp = cpa.createCriteria("controlPerson");
			cp.setFetchMode("controlPersonTypes", FetchMode.SELECT);
			cp.add(Restrictions.eq("id", id));
			controlPersonAlarmService.findByPage(page, cpa);
			
		} else {
			page.add(Sort.desc("createDate"));
			page.add(Filter.eq("controlPersonId", id));
			page.add(Filter.eq("type", typeId));
			dynamicInfoService.findByPage(page);
			List<DynamicInfo> dynamicInfos = (List<DynamicInfo>) page.getList();
			// 标题集合
			List<String> titles = new ArrayList<String>();
			// 轨迹数据集合
			List<List<JsonData>> datas = new ArrayList<List<JsonData>>();
			// 定义获取数据库json数据json对象
			JSONObject json = new JSONObject();
			// 定义获取标题json数据json对象
			JSONObject titleJsonObject = new JSONObject();
			// 定义获取轨迹json数据json对象
			JSONArray valueJsonObject = new JSONArray();
			if (dynamicInfos != null && dynamicInfos.size() > 0) {
				for (int i = 0; i < dynamicInfos.size(); i++) {
					// 将json字符串转换为json格式
					json = JSONObject.fromObject(dynamicInfos.get(i).getInformation().toString());
					// 获取title标题数据
					titleJsonObject = (JSONObject) json.get("title");
					// 获取value内容数据集合
					valueJsonObject = json.getJSONArray("value");
					List<JsonData> jsonDatas = new ArrayList<JsonData>();
					// 迭代标题数据
					for (Iterator<?> iter = titleJsonObject.keys(); iter.hasNext();) {
						// 只获取一次标题的值,获取一次就不在获取
						if (i > 0) {
							iter.next();
							continue;
						} else {
							// 获取标题的key
							String key = (String) iter.next();
							// 根据标题的key获取value
							String value = titleJsonObject.get(key).toString();
							// 给标题集合赋值
							titles.add(value);
						}
					}
					// 将标题对应的值获取到
					for (int j = 0; j < valueJsonObject.size(); j++) {
						JsonData jsonData = (JsonData) JSONObject.toBean(valueJsonObject.getJSONObject(j), JsonData.class);
						jsonDatas.add(jsonData);
					}
					// 得到一条完整的轨迹数据就存入轨迹数据集合
					datas.add(jsonDatas);
				}
			}
			page.setList(datas);
			model.addAttribute("titles", titles);// 轨迹标题数据
			model.addAttribute("datas", datas);// 轨迹数据
		}
		// 轨迹类型id（预警信息固定为0）
		model.addAttribute("typeId", typeId);
		// 重点人id
		model.addAttribute("id", id);
		return "/admin/controlPerson/dynamicInfo";
	}

	/**
	 * 审核重点人
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月14日 上午11:22:43
	 */
	@RequestMapping("/isColumn")
	@ResponseBody
	public JsonReturn isColumn(Integer id, Integer isColumn, HttpServletRequest request) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		if (!controlPersonService.isPower(id, user, loginUserUnit)) {
			return new JsonReturn(400, "没有权限");
		}
		JsonReturn jsonReturn = controlPersonService.isColumn(id, isColumn);
		ControlPerson controlPerson = controlPersonService.get(id);
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 3, request.getRequestURL().toString(), user.getPoliceName() + "审核了" + controlPerson.getName(),
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		return jsonReturn;
	}

	/**
	 * 新增重点人审核拒绝理由页面
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月21日 下午8:42:02
	 */
	/*
	 * @RequestMapping("/setRefuseToPass") public String setRefuseToPass(Integer
	 * id, Model model) { model.addAttribute("id", id); return
	 * "/admin/controlPerson/refuseToPass"; }
	 *//**
	 * 新增重点人审核拒绝理由
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月21日 下午8:42:02
	 */
	/*
	 * @RequestMapping("/refuseToPass")
	 * @ResponseBody public JsonReturn refuseToPass(Integer id) { //
	 * 根据重点人id新增拒绝理由 ControlPerson controlPerson = new ControlPerson();
	 * controlPersonService.get(id); try {
	 * controlPersonService.updateInclude(controlPerson, new String[] { "" },
	 * null); } catch (Exception e) { return new JsonReturn(400,
	 * "出现异常,请联系管理员反馈该问题"); } return new JsonReturn(200, "已拒绝该审核",
	 * "/admin/controlPerson/look.shtml?id=" + id + "&personnelType=" +
	 * controlPerson.getPersonnelType()); }
	 */

	/**
	 * 跳转到导入重点人数据页面
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月19日 下午3:42:37
	 */
	@RequestMapping("/goToLeadExcel")
	public String goToLeadExcel(Model model, Integer personnelType) {
		model.addAttribute("personnelType", personnelType);
		return "/admin/controlPerson/toLeadExcel";
	}

	/**
	 * 导入重点人数据
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月19日 下午3:42:37
	 */
	@ResponseBody
	@RequestMapping("/toLeadExcel")
	public JsonReturn toLeadExcel(MultipartFile file, Integer personnelType, HttpServletRequest request) {// 获取当前登录用户
		String personnelTypeName = getPersonnelTypeName(personnelType);
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		int row = 1;
		LOGGER.info("开始导入重点人员execl表格");
		try {
			if (file.isEmpty()) {
				LOGGER.info("文件不存在,停止导入");
				return new JsonReturn(400, "导入失败,文件不存在!");
			}
			InputStream in = null;
			List<List<Object>> listob = null;
			in = file.getInputStream();
			try {
				listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
			} catch (Exception e) {
				LOGGER.info("文件格式不正确,停止导入!");
				return new JsonReturn(400, "请上传execl文件");
			}
			if (listob != null && listob.size() > 0) {
				for (List<Object> excelList : listob) {
					if ("无".equals(excelList.get(0).toString()) && "无".equals(excelList.get(1).toString()) && "无".equals(excelList.get(2).toString())
							&& "无".equals(excelList.get(3).toString()) && "无".equals(excelList.get(4).toString())) {
						continue;
					}
					ControlPerson controlPerson = new ControlPerson();
					ControlPersonExtend controlPersonExtend = new ControlPersonExtend();
					controlPerson.setPersonnelType(personnelType);// 人员类型
					controlPerson.setColumnTubeMode("公开");// 列控方式
					controlPerson.setColumnTubeState("1");// 默认列管
					controlPerson.setColumnDate(new Date());// 列管时间
					controlPerson.setRegisterState("1");// 未下发
					controlPerson.setIsAuditKeyPersonColumnTub("1");//默认未审核
					// 优先读取表格中的数据,表格中的数据没有问题则去添加其他数据

					// 姓名验证
					if ("无".equals(excelList.get(0).toString())) {
						return new JsonReturn(400, (row - 1) + "行导入成功,表格第" + (row + 1) + "行错误,请填写姓名!", "/admin/controlPerson/list.shtml?personnelType=" + personnelType);
					}
					if (RuiecStringUtil.isName(excelList.get(0).toString())) {
						controlPerson.setName(excelList.get(0).toString());// 姓名
					} else {
						return new JsonReturn(400, (row - 1) + "行导入成功,表格第" + (row + 1) + "行错误,姓名格式错误!", "/admin/controlPerson/list.shtml?personnelType=" + personnelType);
					}

					// 性别验证
					if ("无".equals(excelList.get(1).toString())) {
						return new JsonReturn(400, (row - 1) + "行导入成功,表格第" + (row + 1) + "行错误,请填写性别!", "/admin/controlPerson/list.shtml?personnelType=" + personnelType);
					} else if ("男".equals(excelList.get(1).toString()) || "女".equals(excelList.get(1).toString())) {
						controlPerson.setSex(excelList.get(1).toString());// 性别
					} else {
						return new JsonReturn(400, (row - 1) + "行导入成功,表格第" + (row + 1) + "行错误,请填写正确性别!", "/admin/controlPerson/list.shtml?personnelType=" + personnelType);
					}

					// 身份证号验证
					IDCard IDCard=new IDCard();
					if (IDCard.IDCardValidate(excelList.get(2).toString())) {
						controlPerson.setIdCard(excelList.get(2).toString());// 身份证号
					} else {
						return new JsonReturn(400, (row - 1) + "行导入成功,表格第" + (row + 1) + "行错误,身份证号格式不正确", "/admin/controlPerson/list.shtml?personnelType=" + personnelType);
					}

					// 手机号不验证
					controlPerson.setPhone(excelList.get(3).toString());// 手机号

					// 单位编码验证
					if ("无".equals(excelList.get(4).toString())) {
						return new JsonReturn(400, (row - 1) + "行导入成功,表格第" + (row + 1) + "行错误,请填写单位编码!", "/admin/controlPerson/list.shtml?personnelType=" + personnelType);
					}
					if (excelList.get(4).toString().length() != 12 || !RuiecStringUtil.isNumerLetter(excelList.get(4).toString())) {
						return new JsonReturn(400, (row - 1) + "行导入成功,表格第" + (row + 1) + "行错误,请填写正确的单位编码!", "/admin/controlPerson/list.shtml?personnelType=" + personnelType);
					}
					Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_CODE_MAP, excelList.get(4));// 根据提供的单位编码获取单位数据
					if (unit != null) {
						controlPerson.setUnitId(unit.getId());// 单位数据
					} else {
						// 查询不到单位则统一放在市局下面
						controlPerson.setUnitId(GlobalUnit.CITY_UNIT_ID);// 单位数据
					}
					//出生日期
					if (!"无".equals(excelList.get(6).toString())) {
						controlPersonExtend.setBirthDate(excelList.get(6).toString());
					}
					//经常居住地
					if (!"无".equals(excelList.get(7).toString())) {
						controlPersonExtend.setHabitualResidence(excelList.get(7).toString());
					}
					//现住地址
					if (!"无".equals(excelList.get(8).toString())) {
						controlPersonExtend.setNowLiveArea(excelList.get(8).toString());
					}
					//QQ号码
					if (!"无".equals(excelList.get(9).toString())) {
						controlPersonExtend.setQq(excelList.get(9).toString());
					}
					//户籍地址
					if (!"无".equals(excelList.get(10).toString())) {
						controlPersonExtend.setHouseholdRegisterPlace(excelList.get(10).toString());
					}
					//车牌号	
					if (!"无".equals(excelList.get(11).toString())) {
						controlPersonExtend.setPlateNumber(excelList.get(11).toString());
					}
					//微信号码
					if (!"无".equals(excelList.get(12).toString())) {
						controlPersonExtend.setWechat(excelList.get(12).toString());
					}
					//单位（职业）
					if (!"无".equals(excelList.get(13).toString())) {
						controlPersonExtend.setOccupation(excelList.get(13).toString());
					}
					//其他住址
					if (!"无".equals(excelList.get(14).toString())) {
						controlPersonExtend.setOtherAddress(excelList.get(14).toString());
					}
					//列控方式
					if (!"无".equals(excelList.get(15).toString())) {
						controlPerson.setColumnTubeMode(excelList.get(15).toString());
					}
					//列控理由
					if (!"无".equals(excelList.get(16).toString())) {
						controlPersonExtend.setReason(excelList.get(16).toString());
					}
					// 将重点人扩展放在重点人里进行
					controlPersonExtend.setControlPerson(controlPerson);
					controlPerson.setControlPersonExtend(controlPersonExtend);
					// 保存重点人数据
					try {
						controlPersonService.save(controlPerson);
						//人员类别
						if (!"无".equals(excelList.get(5).toString())) {
							String[] personCode=excelList.get(5).toString().replaceAll("，", ",").split(",");
							for (int i = 0; i < personCode.length; i++) {
								Dictionary dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.PERSON_CLASS_MAP, personCode[i]);
								if (dictionary!=null) {
									// 添加中间表数据
									ControlPersonType controlPersonType = new ControlPersonType();
									controlPersonType.setControlPerson(controlPerson);
									controlPersonType.setDictionaryId(dictionary.getId());
									controlPersonTypeService.save(controlPersonType);
								}
							}
						}
						row++;
					} catch (Exception e) {
						LOGGER.error((row - 1) + "行导入成功,表格第" + (row + 1) + "行错误,该人员已经存在" + e);
						return new JsonReturn(400, (row - 1) + "行导入成功,第" + (row + 1) + "行错误,该人员已经存在", "/admin/controlPerson/list.shtml?personnelType=" + personnelType);
					}
				}
			} else {
				LOGGER.info("文件没有内容,停止导入!");
				return new JsonReturn(400, "请填写的模板表格内容!");
			}
		} catch (Exception e) {
			LOGGER.error((row - 1) + "行导入成功,表格第" + (row + 1) + "行错误" + e);
			return new JsonReturn(400, (row - 1) + "行导入成功,表格第" + (row + 1) + "行错误", "/admin/controlPerson/list.shtml?personnelType=" + personnelType);
		}
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 1, request.getRequestURL().toString(), user.getPoliceName() + "导入了" + row + "行" + personnelTypeName
					+ "数据", LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		return new JsonReturn(200, (row - 1) + "行导入成功", "/admin/controlPerson/list.shtml?personnelType=" + personnelType);
	}

	/**
	 * 根据字典类型id查询简单字典类型数据
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月8日 下午3:01:51
	 */
	private List<Map<String, Object>> getDictionary(String name) {
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		if (name != null) {
			// 根据类型id查询简单类型数据
			maps = dictionaryService.findByItemCode(name, 0);
		}
		return maps;
	}

	/**
	 * 获取当前操作的人员类型
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月27日 下午2:23:21
	 */
	private String getPersonnelTypeName(Integer personnelTypeId) {
		String personnelType = "";
		if (personnelTypeId != null) {
			if (personnelTypeId == 1) {
				personnelType = "重点人员";
			} else if (personnelTypeId == 2) {
				personnelType = "关注人员";
			} else if (personnelTypeId == 3) {
				personnelType = "外地人员";
			}
		}
		return personnelType;
	}

	/**
	 * 验证身份证号是否重复
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月6日 上午9:16:16
	 */
	@ResponseBody
	@RequestMapping("/isIdCard")
	public JsonReturn isIdCard(String idCard,String outIdCard) {
		IDCard IDCard=new IDCard();
		if (!IDCard.IDCardValidate(idCard)) {
			return new JsonReturn(400, "身份证号不存在");
		} else {
			if (outIdCard!=null && (idCard.equals(outIdCard))) {
				return new JsonReturn(200, "身份证号正确");
			}else {
				List<Filter> filters = new ArrayList<Filter>();
				filters.add(Filter.eq("idCard", idCard));
				ControlPerson controlPerson = controlPersonService.get(filters);
				if (controlPerson != null) {
					return new JsonReturn(400, "身份证号重复");
				} else {
					return new JsonReturn(200, "身份证号正确");
				}
			}
		}
	}

	/**
	 * 根据ID获取重点人信息
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月6日 下午3:54:07
	 */
	@ResponseBody
	@RequestMapping("/getControllerPerson")
	public JsonReturn getControllerPerson(Integer id) {
		if (id == null) {
			return new JsonReturn(400, "重点人id不能为空");
		}
		// 获取到重点人信息
		ControlPerson controlPerson = controlPersonService.get(id);
		JSONObject jsonObject = JSONObject.fromObject(controlPerson);
		return new JsonReturn(200, "人员查询成功", jsonObject);
	}
}
