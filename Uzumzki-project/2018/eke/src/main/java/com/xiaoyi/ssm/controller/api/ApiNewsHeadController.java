package com.xiaoyi.ssm.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.NewsHead;
import com.xiaoyi.ssm.model.PageBean;
import com.xiaoyi.ssm.service.NewsHeadService;
import com.xiaoyi.ssm.util.DateUtil;

/**  
 * @Description: 楼讯控制器
 * @author 宋高俊  
 * @date 2018年7月18日 下午4:05:38 
 */ 
@Controller("apiNewsHeadController")
@RequestMapping("api/newsHead")
public class ApiNewsHeadController {
	
	@Autowired
	private NewsHeadService newsHeadService;

	/** 
	 * @Description: 楼讯列表
	 * @author 宋高俊  
	 * @date 2018年7月18日 下午4:50:55 
	 */ 
	@RequestMapping(value = "/list" , method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage list(PageBean pageBean, String token) {
		//最终返回的数据
		Map<String, Object> pageMap = new HashMap<String, Object>();
		//返回的集合数据
		List<Map<String, Object>> listmMaps = new ArrayList<Map<String,Object>>();
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		//获取公告列表
		List<NewsHead> list = newsHeadService.selectByAll(null);
		
		PageInfo<NewsHead> pageInfo = new PageInfo<>(list);
		
		for (int i = 0; i < pageInfo.getList().size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", list.get(i).getId());
			map.put("image", list.get(i).getCoverpath());//图片
			map.put("title", list.get(i).getTitle());//标题
			map.put("source", list.get(i).getSource());//来源
			map.put("showdate", DateUtil.getFormat(list.get(i).getShowdate(),"yyyy-MM-dd HH:mm:ss"));//显示时间
			map.put("viewCount", list.get(i).getViewcount());//查看次数
			map.put("sharecount", list.get(i).getSharecount());//分享次数
			listmMaps.add(map);
		}
		pageMap.put("pageNum", pageInfo.getPageNum());
		pageMap.put("pages", pageInfo.getPages());
		pageMap.put("list", listmMaps);

		return ApiMessage.succeed(pageMap);
	}
	
	/**
	 * @Description: 楼讯详情
	 * @author 宋高俊  
	 * @date 2018年7月18日 下午4:50:55 
	 */
	@RequestMapping(value = "/details" , method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage details(String id) {
		NewsHead newsHead = newsHeadService.selectByPrimaryKey(id);
		return ApiMessage.succeed(newsHead);
	}

	/** 
	 * @Description: 收藏楼讯
	 * @author 宋高俊
	 * @date 2018年7月18日 下午4:50:55
	 */ 
	@RequestMapping(value = "/collection" , method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage collection(String id,String token) {
		NewsHead newsHead = newsHeadService.selectByPrimaryKey(id);
		return ApiMessage.succeed(newsHead);
	}
}
