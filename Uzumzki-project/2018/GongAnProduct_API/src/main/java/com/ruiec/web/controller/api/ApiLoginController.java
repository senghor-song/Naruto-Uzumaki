/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.controller.api;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Sort;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.ApiReturn;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.Banner;
import com.ruiec.web.entity.LoginLog;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.enumeration.UnitRank;
import com.ruiec.web.service.BannerService;
import com.ruiec.web.service.LoginLogService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;

/**
 * 登录控制器
 * 
 * @author 陈靖原<br>
 * @modify bingo<br>
 * @date 2018年1月8日 下午1:57:42
 */
@Controller
@RequestMapping("/api/admin")
public class ApiLoginController extends BaseAdminController {

	@Resource
	private UserService userService;
	@Resource
	private UnitService unitService;
	@Resource
	private LoginLogService loginLogService;
	@Resource
	private OperationLogService operationLogService;
	@Resource
	private BannerService bannerService;

	/**
	 * 登录
	 * 
	 * @author 陈靖原<br>
	 * @modify bingo<br>
	 * @throws UnknownHostException
	 * @date 2018年1月8日 下午3:56:52
	 */
	@ResponseBody
	@RequestMapping("/login")
	@SuppressWarnings("unchecked")
	public ApiReturn login(User user, HttpServletRequest request, HttpSession session) throws UnknownHostException {

		if (StringUtils.isBlank(user.getIdCard()) || StringUtils.isBlank(user.getPassword())) {
			return new ApiReturn(400, "帐号或密码不能为空");
		}

		// 密码加密
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("idCard", user.getIdCard()));
		filters.add(Filter.eq("password", user.getPassword()));
		// 查询此用户是否存在
		User loginuser = userService.get(filters, null, Fetch.join("unit"));
		if (loginuser == null) {
			return new ApiReturn(400, "帐号或密码错误");
		}

		// API返回数据
		Map<String, Object> data = new HashMap<>();

//		// 保存警员信息到session
//		session = request.getSession();
//		session.setAttribute("user", loginuser);
		// 保存警员管理员信息到session中
		LoginUserUnit loginUserUnit = new LoginUserUnit();
		loginUserUnit.setUser(loginuser);
		// 获取当前用户所管理的单位
		List<Map<String, Object>> userUnits = (List<Map<String, Object>>) RedisUtil.getRedisOne(GlobalUnit.USER_UNIT_MAP, loginuser.getId());
		if (userUnits != null && userUnits.size() > 0) {
			// 直辖单位id
			List<Integer> unitIds = new ArrayList<Integer>();
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, userUnits.get(0).get("id"));
			// 现在管理员只可管理一个单位
			loginUserUnit.setUnitId(unit.getId());
			if (unit.getUnitRank().equals("city")) {
				loginUserUnit.setUnitRank(UnitRank.city);
				loginUserUnit.setUserName("市局管理员");
			} else if (unit.getUnitRank().equals("area")) {
				if (unit.getIsPoliceSection() == 1) {
					loginUserUnit.setUserName("支队管理员");
				} else {
					loginUserUnit.setUserName("分县局管理员");
				}
				loginUserUnit.setUnitRank(UnitRank.area);
			} else if (unit.getUnitRank().equals("town")) {
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
			// 用户权限（0非管理员;1管理员）
			data.put("authority", 1);
		} else {
			loginUserUnit.setUserName("警员");
			// 用户权限（0非管理员;1管理员）
			data.put("authority", 0);
		}
//		session.setAttribute("loginUserUnit", loginUserUnit);
		Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, loginuser.getUnitId());
		if (unit == null) {
			unit = unitService.get(loginuser.getUnitId());
		}
		// 插入登录日志
		LoginLog loginlog = new LoginLog();
		loginlog.setIp(request.getRemoteHost());
		loginlog.setRemark("登录");
		loginlog.setUserName(loginuser.getPoliceName());
		loginlog.setUnitName(unit.getUnitName());
		loginLogService.save(loginlog);

		//保存用户的token
        String token = DigestUtils.md5Hex(session.getId()+loginuser.getId()+new Date().getTime());
		data.put("name", loginuser.getPoliceName());
		data.put("sex", loginuser.getSex());
		data.put("unitName", unit.getUnitName());
		data.put("token", token);
        //单点登录的token
//		String outToken = (String) RedisUtil.getRedisOne(GlobalUnit.LOGIN_USER_TOKEN, loginuser.getId());
//		if (!StringUtils.isBlank(outToken)) {
//		    RedisUtil.delRedis(GlobalUnit.LOGIN_USER_MAP, outToken);
//        }
		//以UserId为key，token为值
        RedisUtil.addRedis(GlobalUnit.LOGIN_USER_TOKEN, loginuser.getId().toString(), token);
        //以token为key，loginUserUnit为值
		RedisUtil.addRedis(GlobalUnit.LOGIN_USER_MAP, token, loginUserUnit);
		return new ApiReturn(200, "登录成功！", data);
	}

	/**
	 * 注销
	 * 
	 * @author 陈靖原<br>
	 * @modify bingo<br>
	 * @throws UnknownHostException
	 * @date 2018年1月8日 下午3:56:20
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public ApiReturn logout(HttpSession session) throws UnknownHostException {
		User loginuser = (User) session.getAttribute("user");
		Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, loginuser.getUnitId());
		if (unit == null) {
			unit = unitService.get(loginuser.getUnitId());
		}
		// 修改登录状态
		loginuser.setIsOnline(session.getId());
		userService.updateInclude(loginuser, new String[] { "isOnline" }, null);
		// 插入登出日志
		LoginLog loginlog = new LoginLog();
		loginlog.setIp(InetAddress.getLocalHost().getHostAddress());
		loginlog.setRemark("登出");
		loginlog.setUserName(loginuser.getPoliceName());
		loginlog.setUnitName(unit.getUnitName());
		loginLogService.save(loginlog);
		session.invalidate();
		String outToken = (String) RedisUtil.getRedisOne(GlobalUnit.LOGIN_USER_TOKEN, loginuser.getId());
        if (!StringUtils.isBlank(outToken)) {
            RedisUtil.delRedis(GlobalUnit.LOGIN_USER_MAP, outToken);
        }
		return new ApiReturn(200, "退出登录成功！");
	}

	/**
	 * 修改密码
	 * 
	 * @author 陈靖原<br>
	 * @modify bingo<br>
	 * @date 2018年1月8日 下午3:57:31
	 */
	@ResponseBody
	@RequestMapping("/editPassword")
	public ApiReturn editPassword(String oldPw, String newPw, HttpServletRequest request, HttpSession session) {
		User loginUser = (User) session.getAttribute("user");
		if (null == loginUser) {
			return new ApiReturn(400, "尚未登录");
		}
		loginUser = userService.get(loginUser.getId());
		if (null == loginUser) {
			return new ApiReturn(400, "帐号不存在");
		}
		if (!loginUser.getPassword().equals(DigestUtils.md5Hex(oldPw))) {
			return new ApiReturn(400, "原密码输入错误");
		}
		if (loginUser.getPassword().equals(DigestUtils.md5Hex(newPw))) {
			return new ApiReturn(400, "新密码不能与原密码相同");
		}
		loginUser.setPassword(DigestUtils.md5Hex(newPw));
		userService.updateInclude(loginUser, new String[] { "password" }, null);
		operationLogService.insertOperationLogs(loginUser, 4, request.getRequestURL().toString(), loginUser.getPoliceName() + "修改了登录密码",
				LogUtil.getData(request.getParameterMap()));
		return new ApiReturn(200, "修改成功");
	}
	
	/**
	 * banner图片接口
	 * @author Senghor<br>
	 * @date 2018年4月12日 上午9:01:07
	 */
	@RequestMapping("/banner")
	@ResponseBody
	public ApiReturn banner(HttpSession session){
		List<Banner> banners = bannerService.findList(4, Filter.eq("isShowApp", 1), Sort.desc("modifyDate"));
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < banners.size(); i++) {
			jsonArray.add(banners.get(i).getImage());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("images", jsonArray);
		return new ApiReturn(200, "请求成功",map);
	}
}
