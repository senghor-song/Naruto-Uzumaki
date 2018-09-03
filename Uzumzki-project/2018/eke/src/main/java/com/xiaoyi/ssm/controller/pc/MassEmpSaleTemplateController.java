package com.xiaoyi.ssm.controller.pc;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.model.MassEmpSaleTemplate;
import com.xiaoyi.ssm.service.MassEmpSaleTemplateService;
import com.xiaoyi.ssm.util.Utils;

/**  
 * @Description: 描述信息控制器
 * @author 宋高俊  
 * @date 2018年6月28日 下午3:52:39 
 */ 
@Controller
@RequestMapping("/massEmpSaleTemplate")
public class MassEmpSaleTemplateController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MassEmpSaleTemplateService massEmpSaleTemplateService;
	
	/**  
	 * @Description: 描述列表
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午3:54:36 
	 */ 
	@RequestMapping(value = "/describe")
	public String describe(Model model, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		List<MassEmpSaleTemplate> list = massEmpSaleTemplateService.selectByEmp(employee.getId(),null);
		model.addAttribute("list", list);
		return "/personManage/describeManage/describe";
	}
	
	/**  
	 * @Description: 新增描述页面
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午3:54:36 
	 */ 
	@RequestMapping(value = "/addDescribe")
	public String addDescribe() {
		return "/personManage/describeManage/addDescribe";
	}
	
	/**  
	 * @Description: 新增描述信息
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午3:54:36 
	 */ 
	@RequestMapping(value = "/saveDescribe")
	@ResponseBody
	public ApiMessage saveDescribe(MassEmpSaleTemplate massEmpSaleTemplate , HttpServletRequest request, String postType) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		massEmpSaleTemplate.setId(Utils.getUUID());
		massEmpSaleTemplate.setEmpid(employee.getId());
		massEmpSaleTemplate.setAddtime(new Date());
		if (!StringUtils.isBlank(postType)) {
			massEmpSaleTemplate.setType(Integer.valueOf(postType));
		}
		int flag = massEmpSaleTemplateService.insertSelective(massEmpSaleTemplate);
		if (flag == 1) {
			return new ApiMessage(200, "新增成功");
		}else {
			return new ApiMessage(400, "新增失败");
		}
	}
	
	/**  
	 * @Description: 修改描述页面
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午3:54:36 
	 */ 
	@RequestMapping(value = "/updateDescribe")
	public String updateDescribe(Model model, String id) {
		MassEmpSaleTemplate massEmpSaleTemplate = massEmpSaleTemplateService.selectByPrimaryKey(id);
		model.addAttribute("massEmpSaleTemplate", massEmpSaleTemplate);
		return "/personManage/describeManage/updateDescribe";
	}
	
	/**  
	 * @Description: 保存修改的描述信息
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午3:54:36 
	 */ 
	@RequestMapping(value = "/editDescribe")
	@ResponseBody
	public ApiMessage editDescribe(MassEmpSaleTemplate massEmpSaleTemplate) {
		int flag = massEmpSaleTemplateService.updateByPrimaryKeySelective(massEmpSaleTemplate);
		if (flag == 1) {
			return new ApiMessage(200, "修改成功");
		}else {
			return new ApiMessage(400, "修改失败");
		}
	}
	
	/**  
	 * @Description: 删除描述信息
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午3:54:36 
	 */ 
	@RequestMapping(value = "/delDescribe")
	@ResponseBody
	public ApiMessage delDescribe(String id, HttpServletRequest request) {
		int flag = massEmpSaleTemplateService.deleteByPrimaryKey(id);
		if (flag == 1) {
			return new ApiMessage(200, "删除成功");
		}else {
			return new ApiMessage(400, "删除失败");
		}
	}
	
	/**  
	 * @Description: 获取描述模板
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午3:54:36 
	 */ 
	@RequestMapping(value = "/getDescribeList")
	public String getDescribeList(Model model, HttpServletRequest request, PageInfo pageInfo, String templateType, String title) {
		PageHelper.startPage(pageInfo.getPageNum(), 3);
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		List<MassEmpSaleTemplate> list = massEmpSaleTemplateService.selectByEmp(employee.getId(),title);
		pageInfo = new PageInfo<>(list);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("templateType", templateType == null ? "describedata" : templateType);
		model.addAttribute("title", title);
		return "personManage/describeManage/getDescribe";
	}

	/**  
	 * @Description: 获取描述模板
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午3:54:36 
	 */ 
	@RequestMapping(value = "/getPageDescribeList")
	public String getPageDescribeList(Model model, HttpServletRequest request, PageInfo pageInfo, String templateType, String title) {
		PageHelper.startPage(pageInfo.getPageNum(), 3);
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		List<MassEmpSaleTemplate> list = massEmpSaleTemplateService.selectByEmp(employee.getId(),title);
		pageInfo = new PageInfo<>(list);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("templateType", templateType == null ? "describedata" : templateType);
		model.addAttribute("title", title);
		return "personManage/describeManage/getDescribeList";
	}
	
}
