package com.xiaoyi.ssm.controller.pc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Area;
import com.xiaoyi.ssm.service.CityService;

/**  
 * @Description: 片区控制器
 * @author 宋高俊  
 * @date 2018年6月30日 上午10:46:24 
 */ 
@Controller
@RequestMapping("/area")
public class AreaController {
	
	@Autowired
	private CityService cityService;
	
	/**  
	 * @Description: 根据区县ID获取片区
	 * @author 宋高俊  
	 * @date 2018年6月30日 上午11:07:12 
	 */ 
	@RequestMapping(value = "/getAreaList")
	@ResponseBody
    public ApiMessage getAreaList(String district) {
		List<Area> areas = cityService.selectByDistrict(district);
        return ApiMessage.succeed(areas);
    }
	
}
