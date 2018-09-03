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
import com.xiaoyi.ssm.dao.TrainJoinMapper;
import com.xiaoyi.ssm.dao.TrainLogMapper;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.model.Train;
import com.xiaoyi.ssm.model.TrainLog;
import com.xiaoyi.ssm.service.TrainService;
import com.xiaoyi.ssm.util.DateUtil;

/**
 * @Description: 培训控制器
 * @author 宋高俊  
 * @date 2018年8月20日 上午11:20:56 
 */ 
@Controller(value = "adminTrainController")
@RequestMapping(value = "/admin/train")
public class TrainController {

	@Autowired
	private TrainService trainService;
	@Autowired
	private TrainJoinMapper trainJoinMapper;
	@Autowired
	private TrainLogMapper trainLogMapper;

	/**
	 * @Description: 培训页面
	 * @author 宋高俊  
	 * @date 2018年8月20日 上午11:20:56 
	 */
	@RequestMapping(value = "/listview")
	public String listview() {
		return "admin/train/list";
	}

	/**
	 * @Description: 培训数据
	 * @author 宋高俊  
	 * @date 2018年8月20日 上午11:20:56 
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Train> trains = trainService.selectByAll(null);
		PageInfo<Train> pageInfo = new PageInfo<>(trains);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < trains.size(); i++) {
			Train train = trains.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", train.getId());// ID
			map.put("createtime", DateUtil.getFormat(train.getCreatetime()));// 申请时间
			map.put("trainno", train.getTrainno());// 编号
			map.put("city", train.getCity().getCity());// 城市
			map.put("venue", train.getVenue().getName());// 场馆
			map.put("manager", train.getManager().getName());// 创建人
			map.put("trainJoinSum", trainJoinMapper.countByTrain(train.getId()));// 报名人数
			map.put("trainLogSum", trainLogMapper.countByTrain(train.getId()));// 日志
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}
	
	/**
	 * @Description: 培训日志数据
	 * @author song
	 * @date 2018年8月14日 下午7:04:09
	 */
	@RequestMapping(value = "/trainloglist")
	@ResponseBody
	public AdminMessage trainloglist(String id) {
		List<TrainLog> list = trainLogMapper.selectByTrain(id);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			TrainLog trainLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", trainLog.getId());// ID
			map.put("createtime", DateUtil.getFormat(trainLog.getCreatetime()));// 时间
			map.put("type", trainLog.getType() == 0 ? "系统" : "人工" );// 类别
			map.put("content", trainLog.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(100, list.size(), listMap);
	}
}
