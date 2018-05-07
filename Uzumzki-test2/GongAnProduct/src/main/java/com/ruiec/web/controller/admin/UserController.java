package com.ruiec.web.controller.admin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.interceptor.MySessionContext;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.util.IDCard;

import net.sf.json.JSONObject;

/**
 * 用户管理控制器
 * @author 陈靖原<br>
 * @date 2017年11月30日 下午4:22:27
 */
/**
 * @author 陈靖原<br>
 * @date 2018年1月15日 上午10:00:57
 */
@Controller("userController")
@RequestMapping("/admin/user")
public class UserController extends BaseAdminController {
	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	@Resource
	private UserService userService;
	@Resource
	private UnitService unitService;

	/**
	 * 全部用户分页列表
	 * 
	 * @author 陈靖原<br>
	 * @Date: 2017年11月30日 下午4:22:27
	 */
	@RequestMapping(value = "/list")
	public String list(Page page, Model model, String search, String forSearch, Integer unitId) {
		page = userService.findUsers(page, search, forSearch, unitId);
		model.addAttribute("page", page);
		model.addAttribute("unitlist", unitService.getUnitArea(1));
		model.addAttribute("forSearch", forSearch);
		model.addAttribute("unitId", unitId);
		model.addAttribute("search", search);
		return "/admin/user/list";
	}

	/**
	 * 姓名或者身份证模糊查询用户（接口使用）
	 * 
	 * @author 贺云<br>
	 * @date 2017年12月11日 下午4:22:42
	 */
	@RequestMapping(value = "/searchList")
	@ResponseBody
	public JsonReturn searchList(Page page, Model model, String searchContent) {
		JSONObject json = new JSONObject();
		List<Map<String, Object>> listMap = userService.findUserList(page, searchContent);
		json.put("townList", listMap);
		return new JsonReturn(200, "成功", json);
	}

	/**
	 * 跳转到用户添加或编辑页面
	 * 
	 * @author 陈靖原<br>
	 * @Date: 2017年11月30日 下午4:22:27
	 */
	@RequestMapping(value = "/edit")
	public String edit(Model model, Integer id) {
		// 获取父级单位
		List<Unit> unitOne = unitService.getUnitArea(1);
		model.addAttribute("unitOne", unitOne);
		// 获取当前用户的父级单位id和当前单位id
		if (id == null) {
			return "/admin/user/add";
		}
		User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, id);
		if (user == null) {
			return "redirect:/admin/user/list.shtml";
		}
		model.addAttribute("userz", user);
		// 根据单位查询
		if (null != user.getUnitId()) {
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
			if ("area".equals(unit.getUnitRank()) || "city".equals(unit.getUnitRank())) {
				model.addAttribute("unit1", unit.getId());
			} else {
				if (unit.getParentId() != null) {
					model.addAttribute("unit1", unit.getParentId());
				}else {
					model.addAttribute("unit1", unit.getPoliceTypesParentId());
				}
				model.addAttribute("unit2", unit.getId());
			}
		}
		return "/admin/user/add";
	}

	/**
	 * 用户添加或编辑
	 * 
	 * @author 陈靖原<br>
	 * @Date: 2017年11月30日 下午4:22:27
	 * @throws ParseException
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public JsonReturn save(Model model, Integer id, User user, Integer unitName1, Integer unitName2, Unit unit) throws ParseException {
		try {
			if (StringUtils.isBlank(user.getPoliceName()) || StringUtils.isBlank(user.getPhone()) || StringUtils.isBlank(user.getIdCard())
					|| StringUtils.isBlank(user.getSex()) || null == unitName1) {
				return new JsonReturn(400, "请完整填写信息");
			}
			if (!IDCard.IDCardValidate(user.getIdCard())) {
				return new JsonReturn(400, "请填写正确身份证");
			}
			// 添加单位
			if (null == unitName2 || -1 == unitName2) {
				user.setUnitId(unitName1);
			} else {
				user.setUnitId(unitName2);
			}
			if (id == null) {
				if (StringUtils.isBlank((user.getPassword()))) {
					return new JsonReturn(400, "请完整填写信息");
				}
				if (userService.findList(1, Filter.eq("idCard", user.getIdCard()), null).size() > 0) {
					return new JsonReturn(400, "身份证重复，请重新填写");
				}
				if (userService.findList(1, Filter.eq("phone", user.getPhone()), null).size() > 0) {
					return new JsonReturn(400, "电话号码重复，请重新填写");
				}
				user.setPassword(DigestUtils.md5Hex(user.getPassword()));
				user = userService.save(user);
				RedisUtil.addRedis(GlobalUnit.USER_MAP, user.getId().toString(), user);
			} else {
				User usr = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, id);
				List<Filter> filters = new ArrayList<Filter>();
				boolean flag = false;
				if (!usr.getIdCard().equals(user.getIdCard())) {
					filters.add(Filter.eq("idCard", user.getIdCard()));
					if (userService.getCount(filters) > 0) {
						return new JsonReturn(400, "身份证重复，请重新填写");
					}
					flag = true;
				}
				if (!usr.getPhone().equals(user.getPhone())) {
					filters.add(Filter.eq("phone", user.getPhone()));
					if (userService.getCount(filters) > 0) {
						return new JsonReturn(400, "电话号码重复，请重新填写");
					}
				}
				usr.setUnitId(user.getUnitId());
				usr.setIdCard(user.getIdCard());
				usr.setPhone(user.getPhone());
				usr.setPoliceName(user.getPoliceName());
				usr.setSex(user.getSex());
				userService.updateIgnore(user, new String[] { "grade", "password" }, null);
				RedisUtil.addRedis(GlobalUnit.USER_MAP, id.toString(), usr);
				if (flag) {
					MySessionContext.DelSession(MySessionContext.getSession(usr.getIsOnline()));
				}
			}
			return new JsonReturn(200, "编辑成功", "/admin/user/list.shtml");
		} catch (Exception e) {
			LOGGER.error("编辑成功" + e);
			return new JsonReturn(400, "编辑失败");
		}
	}

	/**
	 * 用户删除
	 * 
	 * @author 陈靖原<br>
	 * @Date: 2017年11月30日 下午4:22:27
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public JsonReturn delete(Model model, Integer[] ids) {
		try {
			if (ids != null && ids.length > 0) {
				userService.delete(ids);
				for (Integer id : ids) {
					User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, id);
					MySessionContext.DelSession(MySessionContext.getSession(user.getIsOnline()));
					RedisUtil.delRedis(GlobalUnit.USER_MAP, id.toString());
				}
			}
			return new JsonReturn(200, "删除成功");
		} catch (Exception e) {
			return new JsonReturn(400, "删除失败");
		}
	}

	/**
	 * 重置密码
	 * 
	 * @author 陈靖原<br>
	 * @Date: 2017年11月30日 下午4:22:27
	 * @throws ParseException
	 */
	@RequestMapping(value = "/reset")
	@ResponseBody
	public JsonReturn reset(Integer id, User user) {
		try {
			user = userService.get(id);
			if (null == user) {
				return new JsonReturn(400, "该用户不存在", "/admin/user/list.shtml");
			}
			user.setPassword(DigestUtils.md5Hex("root"));
			if (id != null) {
				userService.updateInclude(user, new String[] { "password" }, new String[] {});
				User outUser = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, user.getId());
				outUser.setPassword(user.getPassword());
				RedisUtil.addRedis(GlobalUnit.USER_MAP, user.getId().toString(), outUser);
				MySessionContext.DelSession(MySessionContext.getSession(outUser.getIsOnline()));
				return new JsonReturn(200, "重置密码成功,默认为root", "/admin/user/list.shtml");
			}
			return new JsonReturn(400, "重置密码失败,用户不存在");
		} catch (Exception e) {
			LOGGER.error("重置密码失败" + e);
			return new JsonReturn(400, "重置密码失败");
		}
	}

}
