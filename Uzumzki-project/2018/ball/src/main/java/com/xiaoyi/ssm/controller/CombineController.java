package com.xiaoyi.ssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dao.CombineJoinMapper;
import com.xiaoyi.ssm.dao.CombineLogMapper;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.model.Combine;
import com.xiaoyi.ssm.model.CombineLog;
import com.xiaoyi.ssm.service.CombineService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.StringUtil;

/**
 * @Description: 散拼控制器
 * @author 宋高俊
 * @date 2018年8月18日 上午11:35:49
 */
@Controller(value = "adminCombineController")
@RequestMapping(value = "/admin/combine")
public class CombineController {

	@Autowired
	private CombineService combineService;
	@Autowired
	private CombineLogMapper combineLogMapper;
	@Autowired
	private CombineJoinMapper combineJoinMapper;

	/**
	 * @Description: 散拼页面
	 * @author song
	 * @date 2018年8月14日 下午7:02:41
	 */
	@RequestMapping(value = "/listview")
	public String listview() {
		return "admin/combine/list";
	}

	/**
	 * @Description: 散拼数据
	 * @author song
	 * @date 2018年8月14日 下午7:04:09
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Combine> combines = combineService.selectByAll(null);
		PageInfo<Combine> pageInfo = new PageInfo<>(combines);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < combines.size(); i++) {
			Combine combine = combines.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", combine.getId());// ID
			map.put("createtime", DateUtil.getFormat(combine.getCreatetime()));// 发起时间
			map.put("combineno", combine.getCombineno());// 编号
			map.put("city", combine.getCity().getCity());// 城市
			map.put("venue", combine.getVenue().getName());// 场馆
			map.put("manager", combine.getManager().getName());// 发起人
			map.put("boy", combine.getBoy());// 最大人数
			map.put("joinSum", combineJoinMapper.countByCombine(combine.getId()));// 报名参加
			if (!StringUtil.isBank(combine.getCombinetimeframe())) {
				map.put("combinetimeframe", combine.getCombinetimeframe().split(",").length);// 时间片
			} else {
				map.put("combinetimeframe", "0");// 时间片
			}
			map.put("combinelog", combineLogMapper.countByCombine(combine.getId()));// 日志
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 散拼日志数据
	 * @author song
	 * @date 2018年8月14日 下午7:04:09
	 */
	@RequestMapping(value = "/combineloglist")
	@ResponseBody
	public AdminMessage combineloglist(String id) {
		List<CombineLog> list = combineLogMapper.selectByCombine(id);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			CombineLog combineLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", combineLog.getId());// ID
			map.put("createtime", DateUtil.getFormat(combineLog.getCreatetime()));// 时间
			map.put("type", combineLog.getType() == 0 ? "系统" : "人工" );// 类别
			map.put("content", combineLog.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(100, list.size(), listMap);
	}

}
