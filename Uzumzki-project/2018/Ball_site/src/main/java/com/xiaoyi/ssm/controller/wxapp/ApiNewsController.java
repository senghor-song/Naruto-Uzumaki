package com.xiaoyi.ssm.controller.wxapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.PageBean;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.News;
import com.xiaoyi.ssm.model.NewsCollect;
import com.xiaoyi.ssm.model.NewsError;
import com.xiaoyi.ssm.service.NewsCollectService;
import com.xiaoyi.ssm.service.NewsErrorService;
import com.xiaoyi.ssm.service.NewsService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.Utils;

/**  
 * @Description: 资讯数据接口
 * @author 宋高俊  
 * @date 2018年10月23日 下午5:34:40 
 */ 
@Controller("wxappApiNewsController")
@RequestMapping("wxapp/news")
public class ApiNewsController {

	@Autowired
	private NewsService newsService;
	@Autowired
	private NewsCollectService newsCollectService;
	@Autowired
	private NewsErrorService newsErrorService;

	/**  
	 * @Description: 获取资讯数据
	 * @author 宋高俊  
	 * @param request
	 * @param pageBean
	 * @return 
	 * @date 2018年10月23日 下午5:15:12 
	 */ 
	@RequestMapping(value = "/list")
	@ResponseBody
	public ApiMessage list(HttpServletRequest request, PageBean pageBean) {
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<News> list = newsService.selectByAll(null);
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("id", list.get(i).getId());
			map.put("title", list.get(i).getTitle());
			map.put("headImage", list.get(i).getHeadImage());
			map.put("content", list.get(i).getContentSimple());
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
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		News news = newsService.selectByPrimaryKey(id);
		NewsCollect newsCollect = newsCollectService.selectByNews(id, member.getId());
		if (newsCollect != null) {
			news.setCollectFlag(1);
		}else {
			news.setCollectFlag(0);
		}
		return new ApiMessage(200, "获取成功", news);
	}
	
	/**  
	 * @Description: 收藏/取消收藏资讯
	 * @author 宋高俊  
	 * @param collectFlag 1 = 收藏  0 = 取消收藏
	 * @return 
	 * @date 2018年10月23日 下午8:00:33 
	 */ 
	@RequestMapping(value = "/collectNews")
	@ResponseBody
	public ApiMessage collectNews(String id, Integer collectFlag, HttpServletRequest request) {
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		if (collectFlag == 1) {
			NewsCollect nc = new NewsCollect();
			nc.setId(Utils.getUUID());
			nc.setCreateTime(new Date());
			nc.setNewsId(id);
			nc.setMemberId(member.getId());
			newsCollectService.insertSelective(nc);
		} else {
			newsCollectService.deleteByIdAndMember(id, member.getId());
		}
		return new ApiMessage(200, "操作成功");
	}
	
	/**  
	 * @Description: 获取我收藏的资讯
	 * @author 宋高俊  
	 * @param request
	 * @return 
	 * @date 2018年10月24日 下午5:36:23 
	 */ 
	@RequestMapping(value = "/getNewsCollect")
	@ResponseBody
	public ApiMessage getNewsCollect(HttpServletRequest request) {
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		List<News> list = newsService.selectByCollect(member.getId());
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("id", list.get(i).getId());
			map.put("title", list.get(i).getTitle());
			map.put("headImage", list.get(i).getHeadImage());
			map.put("content", list.get(i).getContentSimple());
			map.put("showFlag", list.get(i).getShowFlag());
			listMaps.add(map);
		}
		return new ApiMessage(200, "获取成功", listMaps);
	}
	
	/**  
	 * @Description: 提交纠错信息
	 * @author 宋高俊  
	 * @param request
	 * @return 
	 * @date 2018年10月25日 下午4:17:18 
	 */ 
	@RequestMapping(value = "/saveNewsError")
	@ResponseBody
	public ApiMessage saveNewsError(HttpServletRequest request, NewsError newsError) {
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		newsError.setId(Utils.getUUID());
		newsError.setCreateTime(new Date());
		newsError.setMemberId(member.getId());
		newsErrorService.insertSelective(newsError);
		
		return new ApiMessage(200, "提交成功");
	}
}
