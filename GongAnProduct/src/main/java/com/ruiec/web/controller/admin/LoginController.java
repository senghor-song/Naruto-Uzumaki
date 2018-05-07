package com.ruiec.web.controller.admin;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.LoginLog;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.UnitType;
import com.ruiec.web.entity.User;
import com.ruiec.web.enumeration.UnitRank;
import com.ruiec.web.interceptor.MySessionContext;
import com.ruiec.web.service.LoginLogService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UnitTypeService;
import com.ruiec.web.service.UserService;

/**
 * 登录控制器
 * 
 * @author 陈靖原<br>
 * @date 2017年11月29日 下午10:03:11
 */
@Controller
@RequestMapping("/admin")
public class LoginController extends BaseAdminController {
	private static final Logger LOGGER = Logger.getLogger(LoginController.class);
	@Resource
	private UserService userService;
	@Resource
	private UnitService unitService;
	@Resource
	private LoginLogService loginLogService;
	@Resource
	private OperationLogService operationLogService;
	@Resource
	private UnitTypeService unitTypeService;

	/**
	 * 登录
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年11月30日 上午8:43:45
	 */
	@RequestMapping("/view")
	public String login(Model model) {
		return "/admin/login";
	}

	/**
	 * 登录
	 * 
	 * @author 陈靖原<br>
	 * @throws UnknownHostException
	 * @date 2017年11月30日 上午8:43:45
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/login")
	@ResponseBody
	public JsonReturn login(User user, HttpServletRequest request, HttpSession session) throws UnknownHostException {
		try {
			if (StringUtils.isBlank(user.getIdCard()) || StringUtils.isBlank(user.getPassword())) {
				return new JsonReturn(400, "帐号或密码不能为空", "/admin/view.shtml");
			}
			// 密码加密
			user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(Filter.eq("idCard", user.getIdCard()));
			filters.add(Filter.eq("password", user.getPassword()));
			// 查询此用户是否存在
			User loginuser = userService.get(filters);
			if (loginuser == null) {
				return new JsonReturn(400, "帐号或密码错误", "/admin/view.shtml");
			}

			// 修改登录状态
			MySessionContext.DelSession(MySessionContext.getSession(loginuser.getIsOnline()));
			MySessionContext.AddSession(session);
			loginuser.setIsOnline(session.getId());
			userService.updateInclude(loginuser, new String[] { "isOnline" }, null);
			// 保存警员信息到session
			session = request.getSession();
			session.setAttribute("user", loginuser);
			// 保存警员管理员信息到session中
			LoginUserUnit loginUserUnit = new LoginUserUnit();
			// 获取当前用户所管理的单位
			List<Map<String, Object>> userUnits = (List<Map<String, Object>>) RedisUtil.getRedisOne(GlobalUnit.USER_UNIT_MAP, loginuser.getId());
			if (userUnits != null && userUnits.size() > 0) {

				// 直辖单位id
				List<Integer> unitIds = new ArrayList<Integer>();
				// 获取缓存数据
				Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, userUnits.get(0).get("id"));
				List<UnitType> unitTypes = unitTypeService.findList(null, Filter.eq("unitId", unit.getId()), null);
				for (int j = 0; j < unitTypes.size(); j++) {
					Dictionary di = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, unitTypes.get(j).getDictionaryId());
					if (di != null && "InformationCentre".equals(di.getItemValue())) {
						loginUserUnit.setUrgency(true);
					}
				}
				if (loginuser.getId().equals(1)) {
					loginUserUnit.setUrgency(true);
				}
				// 现在管理员只可管理一个单位
				loginUserUnit.setUnitId(unit.getId());

				// 判断管理的单位级别
				if (unit.getUnitRank().equals("city")) {
					loginUserUnit.setUnitRank(UnitRank.city);
					loginUserUnit.setUserName("市局管理员");
				} else if (unit.getUnitRank().equals("area")) {
					// 判断是否是警员
					if (unit.getIsPoliceSection() == 1) {
						loginUserUnit.setUserName("支队管理员");
					} else {
						loginUserUnit.setUserName("分县局管理员");
					}
					loginUserUnit.setUnitRank(UnitRank.area);
				} else if (unit.getUnitRank().equals("town")) {
					// 判断是否是警员
					if (unit.getIsPoliceSection() == 1) {
						loginUserUnit.setUserName("大队管理员");
					} else {
						loginUserUnit.setUserName("派出所管理员");
					}
					loginUserUnit.setUnitRank(UnitRank.town);
				}
				unitIds.add(unit.getId());
				loginUserUnit.setUnitIds(unitIds);
				loginUserUnit.setUnitSonIds(unitService.findSonIds(unit.getId()));
			} else {
				loginUserUnit.setUserName("警员");
			}
			session.setAttribute("loginUserUnit", loginUserUnit);
			// 插入登录日志
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, loginuser.getUnitId());
			LoginLog loginlog = new LoginLog();
			loginlog.setIp(request.getRemoteHost());
			loginlog.setRemark("登录");
			loginlog.setUserName(loginuser.getPoliceName());
			loginlog.setUnitName(unit.getUnitName());
			loginLogService.save(loginlog);
			RedisUtil.addRedis(GlobalUnit.USER_MAP, loginuser.getId().toString(), loginuser);
			return new JsonReturn(200, "登录成功", "/admin/common/main.shtml");
		} catch (Exception e) {
			LOGGER.error("登录失败"+e);
			return new JsonReturn(400, "登录失败");
		}
	}

	/**
	 * 注销
	 * 
	 * @author 陈靖原<br>
	 * @throws UnknownHostException
	 * @date 2017年11月30日 上午8:43:45
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request) throws UnknownHostException {
		User loginuser = (User) session.getAttribute("user");
		// 修改登录状态
		loginuser.setIsOnline(session.getId());
		userService.updateInclude(loginuser, new String[] { "isOnline" }, null);
		// 插入登出日志
		Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, loginuser.getUnitId());
		LoginLog loginlog = new LoginLog();
		loginlog.setIp(request.getRemoteHost());
		loginlog.setRemark("登出");
		loginlog.setUserName(loginuser.getPoliceName());
		loginlog.setUnitName(unit.getUnitName());
		loginLogService.save(loginlog);
		session.invalidate();
		return "redirect:/admin/view.shtml";
	}

	/**
	 * 修改密码
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年11月30日 上午8:43:45
	 */
	@RequestMapping("/pwview")
	public String pwview() {
		return "/admin/user/password";
	}

	/**
	 * 修改密码
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年11月30日 上午8:43:45
	 */
	@RequestMapping("/password")
	@ResponseBody
	public JsonReturn editPassword(String oldPw, String newPw, HttpServletRequest request, HttpSession session) {
		User loginUser = (User) session.getAttribute("user");
		if (null == loginUser) {
			return new JsonReturn(400, "尚未登录");
		}
		loginUser = userService.get(loginUser.getId());
		if (null == loginUser) {
			return new JsonReturn(400, "帐号不存在");
		}
		if (!loginUser.getPassword().equals(DigestUtils.md5Hex(oldPw))) {
			return new JsonReturn(400, "原密码输入错误");
		}
		if (loginUser.getPassword().equals(DigestUtils.md5Hex(newPw))) {
			return new JsonReturn(400, "新密码不能与原密码相同");
		}
		loginUser.setPassword(DigestUtils.md5Hex(newPw));
		userService.updateInclude(loginUser, new String[] { "password" }, null);
		operationLogService.insertOperationLogs(loginUser, 4, request.getRequestURL().toString(), loginUser.getPoliceName() + "修改了登录密码",
				LogUtil.getData(request.getParameterMap()));
		return new JsonReturn(200, "修改成功", "/admin/view.shtml");
	}
}
