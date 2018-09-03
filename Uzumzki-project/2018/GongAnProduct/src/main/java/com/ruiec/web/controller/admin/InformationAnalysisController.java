package com.ruiec.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruiec.framework.web.support.controller.BaseAdminController;

/**
 * 数据分析控制器
 * 
 * @author Senghor<br>
 * @date 2017年12月18日 上午10:07:45
 */
@Controller("informationAnalysis")
@RequestMapping("/admin/informationAnalysis")
public class InformationAnalysisController extends BaseAdminController {
	/**
	 * 数据分析页面
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月18日 上午10:08:45
	 */
	@RequestMapping("/information")
	public String information() {
		return "/admin/informationAnalysis/information";
	}

	/**
	 * 数据资源汇总
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月18日 上午10:08:45
	 */
	@RequestMapping("/dateCollect")
	public String dateCollect(Model model) {
		model.addAttribute("url", "/admin/informationAnalysis/dateCollect.shtml");
		return "/admin/informationAnalysis/dateCollect";
	}

	/**
	 * 离市人员趋势
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月18日 上午10:08:45
	 */
	@RequestMapping("/leaveTendency")
	public String leaveTendency(Model model) {
		model.addAttribute("url", "/admin/informationAnalysis/leaveTendency.shtml");
		return "/admin/informationAnalysis/leaveTendency";
	}

	/**
	 * 重点人预警趋势分析
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月18日 上午10:08:45
	 */
	@RequestMapping("/personAlarm")
	public String personAlarm(Model model) {
		model.addAttribute("url", "/admin/informationAnalysis/personAlarm.shtml");
		return "/admin/informationAnalysis/personAlarm";
	}

	/**
	 * 重点人关联分析图
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月18日 上午10:08:45
	 */
	@RequestMapping("/personRelevance")
	public String personRelevance(Model model) {
		model.addAttribute("url", "/admin/informationAnalysis/personRelevance.shtml");
		return "/admin/informationAnalysis/personRelevance";
	}
}
