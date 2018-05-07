package com.ruiec.web.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseController;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.entity.ControlPerson;
import com.ruiec.web.entity.ControlPersonRelation;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.ControlPersonRelationService;
import com.ruiec.web.service.ControlPersonService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.DictionaryTypeService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.util.IDCard;

/**
 * 重点人员关系控制器
 * 
 * @author Senghor<br>
 * @date 2017年12月1日 上午9:05:41
 */
@Controller("controlPersonRelationController")
@RequestMapping("/admin/controlPersonRelation")
public class ControlPersonRelationController extends BaseController {

	private static final Logger LOGGER = Logger.getLogger(ControlPersonRelationController.class);

	@Resource
	private ControlPersonRelationService controlPersonRelationService;
	@Resource
	private ControlPersonService controlPersonService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private DictionaryTypeService dictionaryTypeService;
	@Resource
	private OperationLogService operationLogService;

	/**
	 * 获取重点人员关系列表
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月1日 上午9:30:08
	 */
	@RequestMapping("/list")
	public String list(Page page, Model model, Integer controlPersonId, Integer personnelType, HttpServletRequest request) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		// 没有重点人id访问则重定向到重点人页面
		if (controlPersonId == null) {
			return "redirect:/admin/controlPerson/list.shtml";
		}
		ControlPerson controlPerson = controlPersonService.get(controlPersonId);
		// 根据重点人员ID获取对应的关系列表
		page.add(Filter.eq("controlPersonId", controlPersonId));
		controlPersonRelationService.findByPage(page);
		model.addAttribute("page", page);
		// 将重点人员id传递过去
		model.addAttribute("controlPersonId", controlPersonId);
		model.addAttribute("personnelType", personnelType);
		try {
			// 插入操作日志
			/*operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看了" + controlPerson.getName()
					+ "关系人员列表", LogUtil.getData(request.getParameterMap()));*/
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		return "/admin/controlPersonRelation/list";
	}

	/**
	 * 跳转重点人员关系添加页面
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/add")
	public String add(Model model, Integer controlPersonId, Integer personnelType) {
		// 没有重点人id访问则重定向到重点人页面
		if (controlPersonId == null) {
			return "redirect:/admin/controlPerson/list.shtml";
		}
		// 获取重点人关系类型
		model.addAttribute("personRelations", getDictionary("personRelation"));

		// 将重点人员id传递过去
		model.addAttribute("controlPersonId", controlPersonId);
		model.addAttribute("personnelType", personnelType);
		return "/admin/controlPersonRelation/add";
	}

	/**
	 * 保存重点人员关系添加信息
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonReturn save(ControlPersonRelation controlPersonRelation, HttpServletRequest request, Integer personnelType) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		ControlPerson controlPerson = controlPersonService.get(controlPersonRelation.getControlPersonId());
		String personType = getPersonnelTypeName(controlPerson.getPersonnelType());
		if (controlPerson.getIdCard().equals(controlPersonRelation.getIdCard())) {
			return new JsonReturn(400, "不可填写当前" + personType + "作为关系人，请重新填写");
		}
		
		IDCard IDCard=new IDCard();
		if (!IDCard.IDCardValidate(controlPersonRelation.getIdCard())) {
			return new JsonReturn(400, "请输入15至18位真实身份证号");
		}
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("idCard", controlPersonRelation.getIdCard()));
		filters.add(Filter.eq("controlPersonId", controlPersonRelation.getControlPersonId()));
		if (controlPersonRelationService.get(filters) != null) {
			return new JsonReturn(400, "该身份证号已被添加过!");
		}
		// 验证数据
		if (controlPersonRelation.getId() != null) {
			return new JsonReturn(400, "非法请求数据");
		}
		if (controlPersonRelation.getPhone() == null && controlPersonRelation.getPhone().length() > 11) {
			return new JsonReturn(400, "非法请求数据");
		}
		if (controlPersonRelation.getIdCard() == null && controlPersonRelation.getIdCard().length() < 15 || controlPersonRelation.getIdCard().length() < 18) {
			return new JsonReturn(400, "非法请求数据");
		}
		if (controlPersonRelation.getRemark() == null) {
			return new JsonReturn(400, "非法请求数据");
		} else {
			// 装换特殊字符，防止sql注入
			controlPersonRelation.getRemark().replace("%", "");
		}
		try {
			controlPersonRelationService.save(controlPersonRelation);
		} catch (Exception e) {
			LOGGER.error(personType + "关系添加失败" + e);
			return new JsonReturn(400, personType + "关系添加失败");
		}

		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 1, request.getRequestURL().toString(), user.getPoliceName() + "给" + controlPerson.getName() + "新增了"
					+ controlPersonRelation.getName() + "关系人", LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		return new JsonReturn(200, personType + "关系添加成功");
	}

	/**
	 * 跳转重点人员关系修改页面
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/edit")
	public String edit(Model model, Integer id) {
		// 没有重点人id访问则重定向到重点人页面
		if (id == null) {
			return "redirect:list.shtml";
		}
		// 获取重点人关系类型
		model.addAttribute("personRelations", getDictionary("personRelation"));
		model.addAttribute("controlPersonRelation", controlPersonRelationService.get(id));
		return "/admin/controlPersonRelation/edit";
	}

	/**
	 * 保存重点人员关系修改信息
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/update")
	@ResponseBody
	public JsonReturn update(ControlPersonRelation controlPersonRelation, HttpServletRequest request) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		ControlPerson controlPerson = controlPersonService.get(controlPersonRelation.getControlPersonId());
		String personType = getPersonnelTypeName(controlPerson.getPersonnelType());

		IDCard IDCard=new IDCard();
		if (!IDCard.IDCardValidate(controlPersonRelation.getIdCard())) {
			return new JsonReturn(400, "请输入15至18位真实身份证号");
		}
		// 获取修改之前的数据
		ControlPersonRelation formerControlPersonRelation = controlPersonRelationService.get(controlPersonRelation.getId());
		// 判断新省份证号和旧身份证号是否一致，一致则不判断是否被添加过
		if (!formerControlPersonRelation.getIdCard().equals(controlPersonRelation.getIdCard())) {
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(Filter.eq("idCard", controlPersonRelation.getIdCard()));
			filters.add(Filter.eq("controlPersonId", controlPersonRelation.getControlPersonId()));
			if (controlPersonRelationService.get(filters) != null) {
				return new JsonReturn(400, "该身份证号已被添加过!");
			}
		}
		// 验证数据
		if (controlPersonRelation.getPhone() == null && controlPersonRelation.getPhone().length() > 11) {
			return new JsonReturn(400, "非法请求数据");
		}
		if (controlPersonRelation.getIdCard() == null && controlPersonRelation.getIdCard().length() < 15 || controlPersonRelation.getIdCard().length() < 18) {
			return new JsonReturn(400, "非法请求数据");
		}
		if (controlPersonRelation.getRemark() == null) {
			return new JsonReturn(400, "非法请求数据");
		} else {
			// 装换特殊字符，防止sql注入
			controlPersonRelation.getRemark().replace("%", "");
		}
		try {
			controlPersonRelationService.update(controlPersonRelation);
		} catch (Exception e) {
			LOGGER.error(personType + "关系修改失败" + e);
			return new JsonReturn(400, personType + "关系修改失败");
		}
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 3, request.getRequestURL().toString(), user.getPoliceName() + "修改了" + controlPerson.getName() + "他的"
					+ controlPersonRelation.getName() + "关系人", LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		return new JsonReturn(200, personType + "关系修改成功");
	}

	/**
	 * 根据id删除重点人员关系信息
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public JsonReturn delete(Integer[] ids, HttpServletRequest request) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		ControlPersonRelation controlPersonRelation = new ControlPersonRelation();
		ControlPerson controlPerson = new ControlPerson();
		if (ids.length > 0) {
			controlPersonRelation = controlPersonRelationService.get(ids[0]);
			controlPerson = controlPersonService.get(controlPersonRelation.getControlPersonId());
		}
		String personType = getPersonnelTypeName(controlPerson.getPersonnelType());
		try {
			controlPersonRelationService.delete(ids);
		} catch (Exception e) {
			LOGGER.error(personType + "关系删除失败" + e);
			return new JsonReturn(400, personType + "关系删除失败");
		}
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 2, request.getRequestURL().toString(), user.getPoliceName() + "删除了" + controlPerson.getName()
					+ "的关系人", LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		return new JsonReturn(200, personType + "关系删除成功");
	}

	/**
	 * 根据字典类型id查询简单字典类型数据
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月8日 下午3:01:51
	 */
	public List<Map<String, Object>> getDictionary(String name) {
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
	public JsonReturn isIdCard(String idCard,String outIdCard,Integer personId) {
		IDCard IDCard=new IDCard();
		if (!IDCard.IDCardValidate(idCard)) {
			return new JsonReturn(400, "身份证号不存在");
		} else {
			if (outIdCard!=null && (idCard.equals(outIdCard))) {
				return new JsonReturn(200, "身份证号正确");
			}else {
				List<Filter> filters = new ArrayList<Filter>();
				filters.add(Filter.eq("idCard", idCard));
				filters.add(Filter.eq("controlPersonId", personId));
				ControlPersonRelation controlPersonRelation = controlPersonRelationService.get(filters);
				if (controlPersonRelation != null) {
					return new JsonReturn(400, "身份证号已被使用");
				} else {
					return new JsonReturn(200, "身份证号正确");
				}
			}
		}
	}
}
