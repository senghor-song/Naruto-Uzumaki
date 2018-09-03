package com.xiaoyi.ssm.controller.memberapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;

/**
 * @Description: 会员控制器
 * @author 宋高俊
 * @date 2018年8月28日 下午3:42:36
 */
@Controller
@RequestMapping("memberapi/member")
public class ApiMemberController {

	/**
	 * @Description: 退出登录
	 * @author 宋高俊
	 * @date 2018年8月28日 下午3:44:31
	 */
	@RequestMapping(value = "/loginout")
	@ResponseBody
	public ApiMessage loginout(String token) {
		RedisUtil.delRedis(Global.redis_member, token);
		return new ApiMessage(200, "退出成功");
	}
}
