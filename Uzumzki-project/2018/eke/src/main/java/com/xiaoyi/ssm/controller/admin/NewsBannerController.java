package com.xiaoyi.ssm.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.model.NewsBanner;
import com.xiaoyi.ssm.model.NewsBannerLog;
import com.xiaoyi.ssm.service.NewsBannerLogService;
import com.xiaoyi.ssm.service.NewsBannerService;
import com.xiaoyi.ssm.util.DateUtil;

/**
 * @Description: 公告接口控制器
 * @author 宋高俊
 * @date 2018年7月26日 下午4:17:14
 */
@Controller(value = "adminNewsBannerController")
@RequestMapping("admin/newsBanner")
public class NewsBannerController {

	@Autowired
	private NewsBannerService newsBannerService;
	@Autowired
	private NewsBannerLogService newsBannerLogService;

	/**
	 * @Description: 公告列表页面
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/listview")
	public String list() {
		return "admin/newsBanner/list";
	}

	/**
	 * @Description: 公告数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/list")
	@ResponseBody
	public AdminMessage list(Model model, AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<NewsBanner> list = newsBannerService.selectByAll(null);
		PageInfo<NewsBanner> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			NewsBanner newsBanner = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", newsBanner.getId());// id
			map.put("bannerno", newsBanner.getBannerno());// 编号
			map.put("coverpath", newsBanner.getCoverpath());// 横幅
			map.put("content", newsBanner.getBanner());// 容器
			map.put("status", newsBanner.getShowway());// 类别
			map.put("newsBannerLogSum", newsBannerLogService.countByBanner(newsBanner.getId()));// 日志
			map.put("remark", newsBanner.getRemark());// 备注
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 公告日志数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/newsBannerLog/list")
	@ResponseBody
	public AdminMessage newsBannerLoglist(AdminPage adminPage, String id) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		NewsBannerLog oldNewsBannerLog = new NewsBannerLog();
		oldNewsBannerLog.setNewsbannerid(id);
		List<NewsBannerLog> list = newsBannerLogService.selectByAll(oldNewsBannerLog);
		PageInfo<NewsBannerLog> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			NewsBannerLog newsBannerLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", newsBannerLog.getId());// id
			map.put("logtime", DateUtil.getFormat(newsBannerLog.getLogtime()));// 时间
			map.put("staff", newsBannerLog.getStaff().getRname());// 操作人
			map.put("content", newsBannerLog.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(200, pageInfo.getTotal(), listMap);
	}
	
	
	
}
