package com.xiaoyi.ssm.controller.venue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueCoach;
import com.xiaoyi.ssm.service.TrainCoachService;
import com.xiaoyi.ssm.service.VenueCoachService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 陪练控制器接口
 * @author 宋高俊
 * @date 2018年11月5日 下午4:39:09
 */
@Controller
@RequestMapping("venue/manager/coach")
public class ApiCoachController {

	@Autowired
	private TrainCoachService trainCoachService;
	@Autowired
	private VenueService venueService;
	@Autowired
	private VenueCoachService venueCoachService;

	/**
	 * @Description: 获取陪练数据
	 * @author 宋高俊
	 * @param request
	 * @param venueid
	 * @return
	 * @date 2018年11月5日 下午4:38:45
	 */
	@RequestMapping(value = "/coachlist")
	@ResponseBody
	public ApiMessage coachlist(HttpServletRequest request, String venueid) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		Venue venue = venueService.selectByPrimaryKey(venueid);
		
		List<TrainCoach> list = trainCoachService.selectByVenue(venueid, venue.getTrainteam());
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			TrainCoach trainCoach = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("trainCoachId", trainCoach.getId());// 教练名称
			map.put("trainCoachName", trainCoach.getName());// 教练名称
			map.put("ballType", venue.getType() == 1 ? "网球" : venue.getType() == 2 ? "足球" : venue.getType() == 3 ? "羽毛球" : "篮球");// 类别
			if (list.get(i).getVenueCoach() != null) {
				map.put("venueCoachId", trainCoach.getVenueCoach().getId());// 陪练ID
				map.put("price", trainCoach.getVenueCoach().getPrice());// 价格
				map.put("typeFlag", trainCoach.getVenueCoach().getTypeFlag());// 状态0=禁用1=正常

				String[] labels = new String[]{};
				if (!StringUtil.isBank(trainCoach.getVenueCoach().getLabel())) {
					labels = trainCoach.getVenueCoach().getLabel().split(",");
				}
				map.put("label", labels);// 教练标签
			}else {
				map.put("venueCoachId", "");// 陪练ID
				map.put("price", "");// 价格
				map.put("typeFlag", 0);// 状态0=禁用1=正常
			}
			listMaps.add(map);
		}

		return new ApiMessage(200, "查询成功", listMaps);
	}
	
	/**  
	 * @Description: 修改教练数据
	 * @author 宋高俊  
	 * @param request
	 * @param coachId
	 * @return 
	 * @date 2018年11月5日 下午7:51:22 
	 */ 
	@RequestMapping(value = "/updateCoach")
	@ResponseBody
	public ApiMessage updateCoach(HttpServletRequest request, String venueCoachId, Double price, Integer typeFlag, String venueId, String trainCoachId, String labelStr) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		VenueCoach venueCoach = new VenueCoach();
		if (StringUtil.isBank(venueCoachId)) {
			venueCoach.setId(Utils.getUUID());
			venueCoach.setCreateTime(new Date());
			venueCoach.setModifyTime(new Date());
			venueCoach.setPrice(price);
			venueCoach.setTypeFlag(typeFlag);
			venueCoach.setVenueId(venueId);
			venueCoach.setTrainCoachId(trainCoachId);
			venueCoach.setLabel(labelStr);
			int flag = venueCoachService.insertSelective(venueCoach);
			if (flag > 0) {
				return new ApiMessage(200, "新增成功");
			}else {
				return new ApiMessage(400, "新增失败");
			}
		}else {
			venueCoach.setId(venueCoachId);
			venueCoach.setModifyTime(new Date());
			venueCoach.setPrice(price);
			venueCoach.setTypeFlag(typeFlag);
			venueCoach.setLabel(labelStr);
			int flag = venueCoachService.updateByPrimaryKeySelective(venueCoach);
			if (flag > 0) {
				return new ApiMessage(200, "修改成功");
			}else {
				return new ApiMessage(400, "修改失败");
			}
		}
		
	}

}
