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
import com.xiaoyi.ssm.dto.AdminDto;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.NewsCust;
import com.xiaoyi.ssm.model.NewsCustLog;
import com.xiaoyi.ssm.service.NewsCustLogService;
import com.xiaoyi.ssm.service.NewsCustService;
import com.xiaoyi.ssm.util.DateUtil;

/**
 * @Description: 新闻接口控制器
 * @author 宋高俊
 * @date 2018年7月26日 下午4:17:14
 */
@Controller(value = "adminNewsCustController")
@RequestMapping("admin/newsCust")
public class NewsCustController {

	@Autowired
	private NewsCustService newsCustService;
	@Autowired
	private NewsCustLogService newsCustLogService;

	/**
	 * @Description: 新闻列表页面
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/listview")
	public String list() {
		return "admin/newsCust/list";
	}

	/**
	 * @Description: 新闻数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/list")
	@ResponseBody
	public AdminMessage list(Model model, AdminPage adminPage, AdminDto adminDto) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<NewsCust> list = newsCustService.selectBySearch(adminDto);
		PageInfo<NewsCust> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			NewsCust newsCust = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", newsCust.getId());
			map.put("newscustno", newsCust.getNewscustno());// 编号
			map.put("status", newsCust.getStatus());// 状态
			map.put("title", newsCust.getTitle());// 标题
			map.put("viewcount", newsCust.getViewcount());// 查看
			map.put("logSum", newsCustLogService.countLogByNewsCust(newsCust.getId()));// 日志
			map.put("sort", newsCust.getSort());// 排序
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 新闻日志数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/newsCustLog/list")
	@ResponseBody
	public AdminMessage newsCustLogList(Model model, AdminPage adminPage, String id) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		NewsCustLog newsCustLog = new NewsCustLog();
		newsCustLog.setNewscustid(id);
		List<NewsCustLog> list = newsCustLogService.selectByAll(newsCustLog);
		PageInfo<NewsCustLog> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			NewsCustLog nhl = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", nhl.getId());
			map.put("time", DateUtil.getFormat(nhl.getLogtime()));// 时间
			map.put("rname", nhl.getStaff().getRname());// 操作人
			map.put("content", nhl.getContent());// 修改内容
			listMap.add(map);
		}
		return new AdminMessage(100, pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 删除新闻数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/delNewsCust")
	@ResponseBody
	public ApiMessage delNewsCust(String id) {
		int flag = newsCustService.deleteByPrimaryKey(id);
		if (flag > 0) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 新闻详情页面
	 * @author 宋高俊
	 * @date 2018年8月10日 上午9:35:44
	 */
	@RequestMapping("/editNewsCust")
	public String editNewsCust(Model model, String id, String type) {
		NewsCust newsCust = newsCustService.selectByPrimaryKey(id);
		model.addAttribute("newsCust", newsCust);
		if ("look".equals(type)) {
			return "/admin/newsCust/look";
		} else {
			return "/admin/newsCust/update";
		}
	}
	
	/**
	 * @Description: 新闻详情页面
	 * @author 宋高俊
	 * @date 2018年8月10日 上午9:35:44
	 */
	@RequestMapping("/saveNewsCust")
	@ResponseBody
	public ApiMessage saveNewsCust(NewsCust newsCust) {
		int flag = newsCustService.insertSelective(newsCust);
		if (flag > 0) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}
}
