package com.xiaoyi.ssm.controller.pc;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.coreFunction.CoreUtil;
import com.xiaoyi.ssm.dao.MassSiteNewMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.model.MassBind;
import com.xiaoyi.ssm.model.MassSiteNew;
import com.xiaoyi.ssm.model.WebSite;
import com.xiaoyi.ssm.service.MassBindService;
import com.xiaoyi.ssm.service.WebSiteService;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 站点管理控制器
 * @author 宋高俊
 * @date 2018年6月29日 下午12:09:37
 */
@Controller
@RequestMapping("/siteManage")
public class SiteManageController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MassBindService massBindService;
	@Autowired
	private MassSiteNewMapper massSiteNewMapper;
	@Autowired
	private WebSiteService sebSiteService;

	/**
	 * @Description: 站点管理页面
	 * @author 宋高俊
	 * @date 2018年6月29日 下午12:11:12
	 */
	@RequestMapping(value = "/siteManage")
	public String siteManage(Model model, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		List<WebSite> list = sebSiteService.selectByEmpAll(employee.getId());
		model.addAttribute("list", list);
		return "/personManage/siteManage/siteManage";
	}

	/**
	 * @Description: 添加账号页面
	 * @author 宋高俊
	 * @date 2018年6月29日 下午12:11:12
	 */
	@RequestMapping(value = "/siteAccount")
	public String siteAccount(HttpServletRequest request) {

		return "/personManage/siteManage/siteAccount";
	}

	/**
	 * @Description: 保存站点信息
	 * @author 宋高俊
	 * @date 2018年6月29日 下午12:11:12
	 */
	@RequestMapping(value = "/saveSiteAccount")
	@ResponseBody
	public ApiMessage saveSiteAccount(HttpServletRequest request, MassBind massBind, String encryptedPassword) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		List<MassBind> massBinds = massBindService.selectByEmpPlatform(employee.getId(), massBind.getPlatform());
		if (massBinds.size() < 3) {
			for (int i = 0; i < massBinds.size(); i++) {
				if (massBinds.get(i).getAccount().equals(massBind.getAccount())) {
					return new ApiMessage(400, "不可重复添加账号");
				}
			}
			//去验证账号密码是否正确
			if (CoreUtil.loginFlag(massBind.getAccount(), encryptedPassword, massBind.getPlatform())) {
				
			} else {
				return new ApiMessage(400, "账号密码错误");
			}
			
			massBind.setId(Utils.getUUID());
			massBind.setEmpid(employee.getId());
			massBind.setRemark(encryptedPassword);
			int flag = massBindService.insertSelective(massBind);
			if (flag == 1) {
				return new ApiMessage(200, "添加成功,剩余添加数量为" + (2 - massBinds.size()) + "个账号");
			} else {
				return new ApiMessage(400, "添加失败");
			}
		} else {
			return new ApiMessage(400, "可添加数量为0");
		}
	}

	/**
	 * @Description: 删除账号信息
	 * @author 宋高俊
	 * @date 2018年6月29日 下午3:41:25
	 */
	@RequestMapping(value = "/delSiteAccount")
	@ResponseBody
	public ApiMessage delSiteAccount(HttpServletRequest request, MassBind massBind) {
		int flag = massBindService.deleteByPrimaryKey(massBind.getId());
		if (flag == 1) {
			return new ApiMessage(200, "删除成功");
		} else {
			return new ApiMessage(400, "删除失败");
		}
	}
	
	/**
	 * @Description: 查看网站密码时验证登录密码
	 * @author 宋高俊
	 * @date 2018年6月29日 下午3:41:25
	 */
	@RequestMapping(value = "/getAccountPwd")
	@ResponseBody
	public ApiMessage getAccountPwd(HttpServletRequest request, String id,String userPwd) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		MassBind massBind = massBindService.selectByPrimaryKey(id);
		if (employee.getPassword().equals(userPwd)) {
			return new ApiMessage(200, "密码正确",massBind);
		}else {
			return new ApiMessage(400, "密码错误");
		}
	}
	
	/**  
	 * @Description: 向小蜜书推荐网站页
	 * @author 宋高俊  
	 * @date 2018年6月29日 下午4:34:44 
	 */ 
	@RequestMapping(value = "/getReWebPage")
	public String getReWebPage() {
		return "/personManage/siteManage/reWebPage";
	}
	
	/**  
	 * @Description: 向小蜜书推荐网站页
	 * @author 宋高俊  
	 * @date 2018年6月29日 下午4:34:44 
	 */ 
	@RequestMapping(value = "/saveReWebPage")
	@ResponseBody
	public ApiMessage saveReWebPage(MassSiteNew massSiteNew, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		massSiteNew.setId(Utils.getUUID());
		massSiteNew.setEmpid(employee.getId());
		massSiteNew.setAddtime(new Date());
		int flag = massSiteNewMapper.insertSelective(massSiteNew);
		if (flag == 1) {
			return ApiMessage.succeed();
		}else {
			return ApiMessage.error();
		}
	}
}
