package com.xiaoyi.ssm.controller.pc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**  
 * @Description: 云发布控制器
 * @author 宋高俊  
 * @date 2018年7月5日 上午11:07:36 
 */ 
@Controller
@RequestMapping("/cloudRelease")
public class CloudReleaseController {
	
	/**  
	 * @Description: 预约管理
	 * @author 宋高俊  
	 * @date 2018年7月5日 上午11:08:43 
	 */ 
	@RequestMapping("/appointmentView")
	public String appointmentView(Model model) {
		
		return "cloudRelease/appointment/appointmentView";
	}
	
	/**  
	 * @Description: 发布日志
	 * @author 宋高俊  
	 * @date 2018年7月5日 上午11:08:43 
	 */ 
	@RequestMapping("/releaseLog")
	public String releaseLog(Model model) {
		
		return "cloudRelease/releaseLog/releaseLog";
	}
}
