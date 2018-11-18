package com.xiaoyi.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Permission;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.service.PermissionService;

/**  
 * @Description: 权限控制器
 * @author 宋高俊  
 * @date 2018年11月3日 上午10:09:37 
 */ 
@Controller(value = "adminACLController")
@RequestMapping(value = "/admin/permission")
public class ACLController {
	
	@Autowired
	private PermissionService permissionService;
	
	/**  
	 * @Description: 权限控制页面
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年11月3日 上午10:12:42 
	 */ 
	@RequestMapping(value = "/listview")
	public String listview(Model model) {
		List<Permission> list = permissionService.selectByAdmin();
		model.addAttribute("list", list);
		return "admin/acl/list";
	}
	
	/**  
	 * @Description: 修改权限
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年11月3日 上午10:12:42 
	 */ 
	@RequestMapping(value = "/update")
	@ResponseBody
	public ApiMessage update(HttpServletRequest request, Integer check , String dataid, Integer rightType) {
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		if (staff.getPower() == 7) {
			Permission permission = permissionService.selectByPrimaryKey(dataid);
			switch (rightType) {
			case 1:
				permission.setRightType1(check);
				break;
			case 2:
				permission.setRightType2(check);
				break;
			case 3:
				permission.setRightType3(check);
				break;
			case 4:
				permission.setRightType4(check);
				break;
			case 5:
				permission.setRightType5(check);
				break;
			case 6:
				permission.setRightType6(check);
				break;
			default:
				break;
			}
			permissionService.updateByPrimaryKeySelective(permission);
			return new ApiMessage(200);
		}else {
			return new ApiMessage(400, "权限不足");
		}
	}
}
