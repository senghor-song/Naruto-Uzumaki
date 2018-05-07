/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.web.common.ApiReturn;

/**
 * 图表分析接口控制器
 * 
 * @author bingo<br>
 * @date 2018年1月9日 下午4:51:06
 */
@Controller
@RequestMapping("/api/admin")
public class ApiGraphAnalysisController {

	/**
	 * 图表分析接口
	 * 
	 * @author bingo<br>
	 * @date 2018年1月9日 下午4:53:40
	 */
	@ResponseBody
	@RequestMapping("/graphAnalysis")
	public ApiReturn graphAnalysis() {
		return new ApiReturn(400, "该功能正在开发中，敬请期待...");
	}

}
