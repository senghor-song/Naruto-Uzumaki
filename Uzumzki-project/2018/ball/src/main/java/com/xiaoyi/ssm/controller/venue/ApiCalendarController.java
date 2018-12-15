package com.xiaoyi.ssm.controller.venue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.FieldTemplate;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueTemplate;
import com.xiaoyi.ssm.service.FieldTemplateService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.service.VenueTemplateService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;

/**
 * @Description: 日历统计数据控制器
 * @author 宋高俊
 * @date 2018年9月6日 下午8:25:56
 */
@Controller
@RequestMapping("venue/manager/calendar")
public class ApiCalendarController {

	@Autowired
	private VenueService venueService;
	@Autowired
	private VenueTemplateService venueTemplateService;
	@Autowired
	private FieldTemplateService fieldTemplateService;

	/**
	 * @Description: 根据月份获取日历的统计数据
	 * @author 宋高俊
	 * @param date  2018-08
	 * @return
	 * @date 2018年9月6日 下午8:28:12
	 */
	@RequestMapping(value = "/calendar")
	@ResponseBody
	public ApiMessage calendar(String date, HttpServletRequest request, String venueid, String fieldid) {
		
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		Venue venue = venueService.selectByPrimaryKey(venueid);
		
		//获取当前月份的第一天和最后一天
		Date startDate = DateUtil.dateGetFirst(date);
		Date endDate = DateUtil.dateGetLast(date);
		
		List<FieldTemplate> list = fieldTemplateService.selectByNowDate(venue.getId(), startDate, endDate, fieldid);
		SortedMap<String, Object> sortmap =  new TreeMap<>();
		for (FieldTemplate fieldTemplate : list) {
			
			Map<String, Object> map = new HashMap<>();
			map.put("id", fieldTemplate.getId());// id
			map.put("template", fieldTemplate.getVenueTemplate().getName());// 模板名称
			
			sortmap.put(DateUtil.getFormat(fieldTemplate.getFieldtime(), "yyyy-M-d"), map);
		}
		return new ApiMessage(200, "查询成功", sortmap);
	}
	
	/**  
	 * @Description: 选配模板
	 * @author 宋高俊  
	 * @param date
	 * @return 
	 * @date 2018年9月7日 上午10:55:16 
	 */ 
	@RequestMapping(value = "/setTemplate")
	@ResponseBody
	public ApiMessage setTemplate(String venueid, String date, String templateid, HttpServletRequest request, String fieldid) {
		//根据模板ID修改
		VenueTemplate venueTemplate = venueTemplateService.selectByPrimaryKey(templateid);
		return fieldTemplateService.saveFieldTemplate(venueid, venueTemplate, date, fieldid);
	}
	
}
