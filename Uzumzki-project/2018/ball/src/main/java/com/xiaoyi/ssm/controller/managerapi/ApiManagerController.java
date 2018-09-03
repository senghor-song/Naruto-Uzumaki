package com.xiaoyi.ssm.controller.managerapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.service.ManagerService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;

/**
 * @Description: 管理员控制器接口
 * @author 宋高俊
 * @date 2018年8月18日 下午4:10:29
 */
@Controller
@RequestMapping("managerapi/manager")
public class ApiManagerController {

	@Autowired
	private ManagerService managerService;
	
	/**
	 * @Description: 退出登录
	 * @author 宋高俊
	 * @date 2018年8月28日 下午3:44:31
	 */
	@RequestMapping(value = "/loginout")
	@ResponseBody
	public ApiMessage loginout(String token) {
		RedisUtil.delRedis(Global.redis_manager, token);
		return new ApiMessage(200, "退出成功");
	}

}
