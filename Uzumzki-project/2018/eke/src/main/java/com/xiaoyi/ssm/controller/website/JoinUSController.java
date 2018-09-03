package com.xiaoyi.ssm.controller.website;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.PageBean;
import com.xiaoyi.ssm.service.PropertyService;

/**  
 * @Description: 加入我们控制器
 * @author 宋高俊  
 * @date 2018年8月15日 上午10:29:08 
 */ 
@Controller("websiteJoinUSController")
@RequestMapping("/website/joinUS")
public class JoinUSController {

	private final Logger Logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PropertyService propertyService;

	/**  
	 * @Description: 加入我们下载链接
	 * @author 宋高俊  
	 * @date 2018年8月15日 上午10:29:02 
	 */ 
	@RequestMapping(value = "/propertyRecommend", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage propertyRecommend(PageBean pageBean, HttpServletRequest request, Double lng, Double lat) {
			Map<String, Object> map = new HashMap<>();
			map.put("ios", "http"); // IOS下载地址
			map.put("android", "http"); // 安卓下载地址
		return ApiMessage.succeed(map);
	}
}
