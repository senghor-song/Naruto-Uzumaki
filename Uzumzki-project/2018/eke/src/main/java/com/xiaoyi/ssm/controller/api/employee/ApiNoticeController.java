package com.xiaoyi.ssm.controller.api.employee;

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
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.model.MassNotice;
import com.xiaoyi.ssm.model.PageBean;
import com.xiaoyi.ssm.service.MassNoticeService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;

/**  
 * @Description: 公告控制器
 * @author 宋高俊  
 * @date 2018年7月18日 下午4:05:38 
 */ 
@Controller
@RequestMapping("api/employee/notice")
public class ApiNoticeController {
	
	@Autowired
	private MassNoticeService massNoticeService;

	/**  
	 * @Description: 公告列表
	 * @author 宋高俊  
	 * @date 2018年7月18日 下午4:50:55 
	 */ 
	@RequestMapping(value = "/list" , method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage list(MassNotice massNotice, PageBean pageBean, String token) {
		Employee employee = (Employee) RedisUtil.getRedisOne(Global.redis_employee, token);
		//最终返回的数据
		Map<String, Object> pageMap = new HashMap<String, Object>();
		//返回的集合数据
		List<Map<String, String>> listmMaps = new ArrayList<Map<String,String>>();
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		//获取公告列表
		List<MassNotice> list = massNoticeService.selectByEmpLIst(employee.getId(), null);
		
		PageInfo<MassNotice> pageInfo = new PageInfo<>(list);
		
		for (int i = 0; i < pageInfo.getList().size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", list.get(i).getId());
			map.put("type", list.get(i).getType() == 0 ? "系统公告" : list.get(i).getType() == 1 ? "网站公告" : list.get(i).getType() == 2 ? "客户公告" : list.get(i).getType() == 3 ? "公司公告" : "站内信息");
			map.put("title", list.get(i).getTitle());
			map.put("createtime", DateUtil.getFormat(list.get(i).getModifytime(),"yyyy-MM-dd HH:mm:ss"));
			listmMaps.add(map);
		}
		pageMap.put("pageNum", pageInfo.getPageNum());
		pageMap.put("pages", pageInfo.getPages());
		pageMap.put("list", listmMaps);

		return ApiMessage.succeed(pageMap);
	}
	
	/**  
	 * @Description: 公告详情
	 * @author 宋高俊  
	 * @date 2018年7月18日 下午4:50:55 
	 */ 
	@RequestMapping(value = "/details" , method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage details(String id) {
		MassNotice notice = massNoticeService.selectByPrimaryKey(id);
		return ApiMessage.succeed(notice);
	}
}
