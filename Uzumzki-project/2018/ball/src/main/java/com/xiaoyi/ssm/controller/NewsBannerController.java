package com.xiaoyi.ssm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.NewsBanner;
import com.xiaoyi.ssm.model.NewsBannerLog;
import com.xiaoyi.ssm.model.Permission;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.service.NewsBannerLogService;
import com.xiaoyi.ssm.service.NewsBannerService;
import com.xiaoyi.ssm.service.OperationLogService;
import com.xiaoyi.ssm.service.PermissionService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 横幅接口控制器
 * @author 宋高俊
 * @date 2018年7月26日 下午4:17:14
 */
@Controller(value = "adminNewsBannerController")
@RequestMapping("admin/newsBanner")
public class NewsBannerController {

	@Autowired
	private NewsBannerService newsBannerService;
	@Autowired
	private NewsBannerLogService newsBannerLogService;
	@Autowired
	private PermissionService permissionService;
    @Autowired
    private OperationLogService operationLogService;

	/**
	 * @Description: 横幅列表页面
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/listview")
	public String listview(HttpServletRequest request, Model model) {
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		List<Permission> list = permissionService.selectByBtu(staff.getPower(), "31");
		for (int i = 0; i < list.size(); i++) {
			model.addAttribute("btn"+list.get(i).getId(), "1");
		}
		return "admin/newsBanner/list";
	}

	/**
	 * @Description: 横幅数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/list")
	@ResponseBody
	public AdminMessage list(Model model, AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<NewsBanner> list = newsBannerService.selectByAll(null);
		PageInfo<NewsBanner> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			NewsBanner newsBanner = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", newsBanner.getId());// id
			map.put("bannerno", newsBanner.getBannerno());// 编号
			map.put("coverpath", newsBanner.getCoverpath());// 横幅
			map.put("content", newsBanner.getBanner());// 容器
			map.put("status", newsBanner.getShowway());// 类别
			map.put("newsBannerLogSum", newsBannerLogService.countByBanner(newsBanner.getId()));// 日志
			map.put("remark", newsBanner.getRemark());// 备注
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 横幅日志数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/newsBannerLog/list")
	@ResponseBody
	public AdminMessage newsBannerLoglist(String id) {
		NewsBannerLog oldNewsBannerLog = new NewsBannerLog();
		oldNewsBannerLog.setNewsbannerid(id);
		List<NewsBannerLog> list = newsBannerLogService.selectByAll(oldNewsBannerLog);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			NewsBannerLog newsBannerLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", newsBannerLog.getId());// id
			map.put("logtime", DateUtil.getFormat(newsBannerLog.getLogtime()));// 时间
			map.put("staff", newsBannerLog.getStaff().getName());// 操作人
			map.put("content", newsBannerLog.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(200, list.size(), listMap);
	}

	/**
	 * @Description: 编辑页面
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月21日 下午2:56:42
	 */
	@RequestMapping("/edit")
	public String edit(Model model, String id) {
		NewsBanner newsBanner = newsBannerService.selectByPrimaryKey(id);
		model.addAttribute("id", newsBanner.getId());
		model.addAttribute("image", newsBanner.getCoverpath());
		model.addAttribute("bannerno", newsBanner.getBannerno());
		model.addAttribute("banner", newsBanner.getBanner());
		model.addAttribute("showway", newsBanner.getShowway());
		model.addAttribute("remark", newsBanner.getRemark());
		model.addAttribute("contentpath", newsBanner.getContentpath());
		return "admin/newsBanner/edit";
	}

	/**
	 * @Description: 保存图片
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月21日 下午2:56:42
	 */
	@RequestMapping(value = "/saveImage")
	@ResponseBody
	public ApiMessage saveImage(HttpServletRequest request, String id, String url) {
		// 登录用户
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		
		NewsBanner newsBanner = newsBannerService.selectByPrimaryKey(id);
		newsBanner.setCoverpath(url);
		newsBannerService.updateByPrimaryKeySelective(newsBanner);

		// 新增横幅日志
		NewsBannerLog newsBannerLog = new NewsBannerLog();
		newsBannerLog.setId(Utils.getUUID());
		newsBannerLog.setEditid(staff.getId());
		newsBannerLog.setContent("横幅地址修改为：" + url);
		newsBannerLog.setLogtime(new Date());
		newsBannerLog.setNewsbannerid(id);
		newsBannerLogService.insertSelective(newsBannerLog);
		return ApiMessage.succeed();
	}

	/**
	 * @Description: 保存修改的信息
	 * @author 宋高俊
	 * @param id
	 * @param remark
	 * @param contentpath
	 * @return
	 * @date 2018年10月22日 上午10:08:24
	 */
	@RequestMapping(value = "/saveNewsBanner")
	@ResponseBody
	public ApiMessage saveNewsBanner(HttpServletRequest request, String id, String remark, String contentpath, String coverpath) {
		// 登录用户
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		// 修改横幅信息
		NewsBanner newsBanner = newsBannerService.selectByPrimaryKey(id);
		String content = "";

		if (!contentpath.equals(newsBanner.getContentpath())) {
			content += "内容Url修改为" + contentpath + "。";
		}
		if (!remark.equals(newsBanner.getRemark())) {
			content += "备注修改为" + remark + "。";
		}
		if (!coverpath.equals(newsBanner.getCoverpath())){
			content += "封面Url修改为" + contentpath + "。";
		}
		if (StringUtil.isBank(content)) {
			return ApiMessage.succeed();
		}
		
		newsBanner.setContentpath(contentpath);
		newsBanner.setRemark(remark);
		newsBanner.setCoverpath(coverpath);
		newsBannerService.updateByPrimaryKeySelective(newsBanner);

		// 新增横幅日志
		NewsBannerLog newsBannerLog = new NewsBannerLog();
		newsBannerLog.setId(Utils.getUUID());
		newsBannerLog.setEditid(staff.getId());
		newsBannerLog.setContent(content);
		newsBannerLog.setLogtime(new Date());
		newsBannerLog.setNewsbannerid(id);
		newsBannerLogService.insertSelective(newsBannerLog);

		operationLogService.saveLog(staff.getId(), "横幅："+content, Utils.getIpAddr(request));
		return ApiMessage.succeed();
	}

}
