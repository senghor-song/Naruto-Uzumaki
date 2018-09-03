package com.xiaoyi.ssm.controller.pc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 网络精耕控制器
 * @author 宋高俊
 * @date 2018年7月5日 上午11:38:28
 */
@Controller
@RequestMapping("/snatch")
public class SnatchWebController {

	/**
	 * @Description: 房源秘书-列表
	 * @author 宋高俊
	 * @date 2018年7月5日 上午11:40:17
	 */
	@RequestMapping("/houseSecretary")
	public String houseSecretary() {
		return "snatchWeb/houseSecretary";
	}
	
	/**
	 * @Description: 房源秘书-重复房源
	 * @author 宋高俊
	 * @date 2018年7月5日 上午11:40:17
	 */
	@RequestMapping("/repetitionHouse")
	public String repetitionHouse() {
		return "snatchWeb/repetitionHouse";
	}
	
	/**
	 * @Description: 房源秘书-操作日志
	 * @author 宋高俊
	 * @date 2018年7月5日 上午11:40:17
	 */
	@RequestMapping("/snatchLog")
	public String snatchLog() {
		return "snatchWeb/snatchLog";
	}

	/**
	 * @Description: 房源精推
	 * @author 宋高俊
	 * @date 2018年7月5日 上午11:40:17
	 */
	@RequestMapping("/anjukeJTView")
	public String anjukeJTView() {
		return "snatchWeb/anjukeJTView";
	}

	/**
	 * @Description: 预约重发-列表
	 * @author 宋高俊
	 * @date 2018年7月5日 上午11:40:17
	 */
	@RequestMapping("/reReleasePlan")
	public String reReleasePlan() {
		return "snatchWeb/reReleasePlan";
	}
	
	/**
	 * @Description: 预约重发-重发日志
	 * @author 宋高俊
	 * @date 2018年7月5日 上午11:40:17
	 */
	@RequestMapping("/reReleaseTask")
	public String reReleaseTask() {
		return "snatchWeb/reReleaseTask";
	}
	
	/**
	 * @Description: 预约重发-操作日志
	 * @author 宋高俊
	 * @date 2018年7月5日 上午11:40:17
	 */
	@RequestMapping("/reReleaseOpenLog")
	public String reReleaseOpenLog() {
		return "snatchWeb/reReleaseOpenLog";
	}

	/**
	 * @Description: 小区透视
	 * @author 宋高俊
	 * @date 2018年7月5日 上午11:40:17
	 */
	@RequestMapping("/cellExact")
	public String cellExact() {
		return "snatchWeb/cellExact";
	}

	/**
	 * @Description: 小区签到-列表
	 * @author 宋高俊
	 * @date 2018年7月5日 上午11:40:17
	 */
	@RequestMapping("/cellSign")
	public String cellSign() {
		return "snatchWeb/cellSign";
	}

	/**
	 * @Description: 小区签到-签到结果
	 * @author 宋高俊
	 * @date 2018年7月5日 上午11:40:17
	 */
	@RequestMapping("/cellSignResult")
	public String cellSignResult() {
		return "snatchWeb/cellSignResult";
	}
	
	/**
	 * @Description: 房源搬家-列表
	 * @author 宋高俊
	 * @date 2018年7月5日 上午11:40:17
	 */
	@RequestMapping("/houseMove")
	public String houseMove() {
		return "snatchWeb/houseMove";
	}
	
	/**
	 * @Description: 房源搬家-搬家列表
	 * @author 宋高俊
	 * @date 2018年7月5日 上午11:40:17
	 */
	@RequestMapping("/houseList")
	public String houseList() {
		return "snatchWeb/houseList";
	}
	
	/**
	 * @Description: 房源搬家-搬家日志
	 * @author 宋高俊
	 * @date 2018年7月5日 上午11:40:17
	 */
	@RequestMapping("/moveOperLog")
	public String moveOperLog() {
		return "snatchWeb/moveOperLog";
	}
}
