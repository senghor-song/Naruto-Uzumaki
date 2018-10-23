package com.xiaoyi.ssm.controller.wxapp;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.PageBean;
import com.xiaoyi.ssm.model.News;
import com.xiaoyi.ssm.service.NewsService;

/**  
 * @Description: 咨询数据接口
 * @author 宋高俊  
 * @date 2018年10月23日 下午5:34:40 
 */ 
@Controller("wxappApiNewsController")
@RequestMapping("wxapp/news")
public class ApiNewsController {

	@Autowired
	private NewsService newsService;

	/**  
	 * @Description: 获取咨询数据
	 * @author 宋高俊  
	 * @param request
	 * @param pageBean
	 * @return 
	 * @date 2018年10月23日 下午5:15:12 
	 */ 
	@RequestMapping(value = "/list")
	@ResponseBody
	public ApiMessage list(HttpServletRequest request, PageBean pageBean) {
		
		List<News> list = newsService.selectByAll(null);
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("id", list.get(i).getId());
			map.put("title", list.get(i).getTitle());
			map.put("headImage", list.get(i).getHeadImage());
			map.put("content", list.get(i).getContent());
			listMaps.add(map);
		}
		return new ApiMessage(200, "获取成功", listMaps);
	}
	
	/**  
	 * @Description: 详情接口
	 * @author 宋高俊  
	 * @param request
	 * @param id
	 * @return 
	 * @date 2018年10月23日 下午5:35:37 
	 */ 
	@RequestMapping(value = "/details")
	@ResponseBody
	public ApiMessage details(HttpServletRequest request, String id) {
		News news = newsService.selectByPrimaryKey(id);
		return new ApiMessage(200, "获取成功", news);
	}
	
}
