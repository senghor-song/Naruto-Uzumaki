package com.xiaoyi.ssm.controller.api.cust;

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
import com.xiaoyi.ssm.model.NewsCust;
import com.xiaoyi.ssm.model.PageBean;
import com.xiaoyi.ssm.service.NewsCustService;

/**  
 * @Description: 新闻接口控制器
 * @author 宋高俊  
 * @date 2018年8月4日 下午2:06:39 
 */ 
@Controller
@RequestMapping("api/newsCust")
public class ApiNewsCustController {

	@Autowired
	private NewsCustService newsCustService;

	/**  
	 * @Description: 新闻列表数据
	 * @author 宋高俊  
	 * @date 2018年8月4日 下午2:07:23 
	 */ 
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage list(PageBean pageBean) {
		//最终返回的数据
		Map<String, Object> pageMap = new HashMap<String, Object>();
		//返回的集合数据
		List<Map<String, Object>> listmMaps = new ArrayList<Map<String,Object>>();
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		//获取公告列表
		List<NewsCust> list = newsCustService.selectByAll(null);
		
		PageInfo<NewsCust> pageInfo = new PageInfo<>(list);
		
		for (int i = 0; i < pageInfo.getList().size(); i++) {
			NewsCust newsCust = pageInfo.getList().get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", newsCust.getId());
			map.put("title", newsCust.getTitle());//标题
			map.put("coverpath", newsCust.getCoverpath());//封面图片
			listmMaps.add(map);
		}
		pageMap.put("pageNum", pageInfo.getPageNum());
		pageMap.put("pages", pageInfo.getPages());
		pageMap.put("list", listmMaps);

		return ApiMessage.succeed(pageMap);
	}
	
}
