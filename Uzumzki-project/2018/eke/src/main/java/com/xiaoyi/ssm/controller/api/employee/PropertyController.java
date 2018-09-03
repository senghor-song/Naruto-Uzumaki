package com.xiaoyi.ssm.controller.api.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.PageBean;
import com.xiaoyi.ssm.model.Property;
import com.xiaoyi.ssm.service.PropertyService;

/**  
 * @Description: 房源租售代码接口控制器
 * @author 宋高俊  
 * @date 2018年7月18日 下午4:05:38 
 */ 
public class PropertyController {
	
	@Autowired
	private PropertyService propertyService;
	
	/**  
	 * @Description: 租售房源展示
	 * @author 宋高俊  
	 * @date 2018年8月23日 上午10:54:14 
	 */ 
	@RequestMapping(value = "/list" , method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage list(PageBean pageBean) {
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPages());
		List<Property> list = propertyService.selectByAll(null);
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Property property = list.get(i);
			Map<String, Object> map = new HashMap<>();
			map.put("", property.getId()); // 房源ID
			map.put("", property.getId()); // 房源ID
			map.put("", property.getId()); // 房源ID
			
			listmap.add(map);
		}
		return ApiMessage.succeed();
	}
	
	/**  
	 * @Description: 房源详情详情
	 * @author 宋高俊  
	 * @date 2018年7月18日 下午4:50:55 
	 */ 
	@RequestMapping(value = "/details" , method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage details(String id) {
		return ApiMessage.succeed();
	}
}
