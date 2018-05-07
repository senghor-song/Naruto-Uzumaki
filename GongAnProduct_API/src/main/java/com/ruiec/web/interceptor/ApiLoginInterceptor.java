package com.ruiec.web.interceptor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.web.common.ApiReturn;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.LoginLog;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.enumeration.UnitRank;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.util.SpringUtils;

/**
 * 登录拦截器
 * 
 * @author 陈靖原<br>
 * @date 2017年12月18日 下午5:59:15
 */
public class ApiLoginInterceptor implements HandlerInterceptor {
    /**
     * 在请求处理之前执行
     * 
     * @author 陈靖原<br>
     * @date 2017年12月18日 下午6:00:22
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//      HttpSession session = request.getSession();
//      UnitService unitService = SpringUtils.getBean("unitServiceImpl", UnitService.class);
//      UserService userService = SpringUtils.getBean("userServiceImpl", UserService.class);
//      User user = new User();
//      // 密码加密
//      user.setIdCard("admin");
//      user.setPassword(DigestUtils.md5Hex("admin"));
//      List<Filter> filters = new ArrayList<Filter>();
//      filters.add(Filter.eq("idCard", user.getIdCard()));
//      filters.add(Filter.eq("password", user.getPassword()));
//      // 查询此用户是否存在
//      User loginuser = userService.get(filters, null, Fetch.join("unit"));
//
//      // API返回数据
//      Map<String, Object> data = new HashMap<>();
//
//      // 保存警员信息到session
//      session = request.getSession();
//      session.setAttribute("user", loginuser);
//      // 保存警员管理员信息到session中
//      LoginUserUnit loginUserUnit = new LoginUserUnit();
//      loginUserUnit.setUser(loginuser);
//      // 获取当前用户所管理的单位
//      List<Map<String, Object>> userUnits = (List<Map<String, Object>>) RedisUtil.getRedisOne(GlobalUnit.USER_UNIT_MAP, loginuser.getId());
//      if (userUnits != null && userUnits.size() > 0) {
//          // 直辖单位id
//          List<Integer> unitIds = new ArrayList<Integer>();
//          Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, userUnits.get(0).get("id"));
//          // 现在管理员只可管理一个单位
//          loginUserUnit.setUnitId(unit.getId());
//          if (unit.getUnitRank().equals("city")) {
//              loginUserUnit.setUnitRank(UnitRank.city);
//              loginUserUnit.setUserName("市局管理员");
//          } else if (unit.getUnitRank().equals("area")) {
//              if (unit.getIsPoliceSection() == 1) {
//                  loginUserUnit.setUserName("支队管理员");
//              } else {
//                  loginUserUnit.setUserName("分县局管理员");
//              }
//              loginUserUnit.setUnitRank(UnitRank.area);
//          } else if (unit.getUnitRank().equals("town")) {
//              if (unit.getIsPoliceSection() == 1) {
//                  loginUserUnit.setUserName("大队管理员");
//              } else {
//                  loginUserUnit.setUserName("派出所管理员");
//              }
//              loginUserUnit.setUnitRank(UnitRank.town);
//          }
//          unitIds.add(unit.getId());
//          loginUserUnit.setUnitIds(unitIds);
//
//          loginUserUnit.setUnitSonIds(unitService.findSonIds(unit.getId()));
//          // 用户权限（0非管理员;1管理员）
//          data.put("authority", 1);
//      } else {
//          loginUserUnit.setUserName("警员");
//          // 用户权限（0非管理员;1管理员）
//          data.put("authority", 0);
//      }
//      session.setAttribute("loginUserUnit", loginUserUnit);
//      Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, loginuser.getUnitId());
//      if (unit == null) {
//          unit = unitService.get(loginuser.getUnitId());
//      }
//
//      String token = DigestUtils.md5Hex(session.getId() + loginuser.getId() + new Date().getTime());
//      data.put("name", loginuser.getPoliceName());
//      data.put("sex", loginuser.getSex());
//      data.put("unitName", unit.getUnitName());
//      data.put("token", token);
//      String outToken = (String) RedisUtil.getRedisOne(GlobalUnit.LOGIN_USER_TOKEN, loginuser.getId());
//      if (!StringUtils.isBlank(outToken)) {
//          RedisUtil.delRedis(GlobalUnit.LOGIN_USER_MAP, outToken);
//      }
//      // 以UserId为key，token为值
//      RedisUtil.addRedis(GlobalUnit.LOGIN_USER_TOKEN, loginuser.getId().toString(), token);
//      // 以token为key，loginUserUnit为值
//      RedisUtil.addRedis(GlobalUnit.LOGIN_USER_MAP, token, loginUserUnit);
//      return true;
        // 获取Session
        HttpSession session = request.getSession();
        String token = request.getParameter("token");
        LoginUserUnit loginUserUnit = (LoginUserUnit) RedisUtil.getRedisOne(GlobalUnit.LOGIN_USER_MAP, token);
        if (loginUserUnit != null) {
            session.setAttribute("user", loginUserUnit.getUser());
            session.setAttribute("loginUserUnit", loginUserUnit);
            return true;
        }
        ApiReturn apiReturn = new ApiReturn(401, "未登录,请重新登录");
        JSONObject json = JSONObject.fromObject(apiReturn);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(json);
        return false;
    }

    /**
     * 该方法将在Controller执行之后
     * 
     * @author 陈靖原<br>
     * @date 2017年12月18日 下午6:00:22
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 该方法将在整个请求完成之后
     * 
     * @author 陈靖原<br>
     * @date 2017年12月18日 下午6:00:22
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
