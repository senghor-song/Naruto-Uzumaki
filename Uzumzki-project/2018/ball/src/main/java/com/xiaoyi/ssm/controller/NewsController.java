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
import com.xiaoyi.ssm.dao.NewsLogMapper;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.News;
import com.xiaoyi.ssm.model.NewsError;
import com.xiaoyi.ssm.model.NewsLog;
import com.xiaoyi.ssm.model.Permission;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.service.NewsErrorService;
import com.xiaoyi.ssm.service.NewsService;
import com.xiaoyi.ssm.service.OperationLogService;
import com.xiaoyi.ssm.service.PermissionService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;

/**  
 * @Description: 资讯接口控制器
 * @author 宋高俊  
 * @date 2018年10月24日 下午2:10:12 
 */ 
@Controller(value = "adminNewsController")
@RequestMapping("admin/news")
public class NewsController {

	@Autowired
	private NewsService newsService;
	@Autowired
	private NewsLogMapper newsLogMapper;
	@Autowired
	private NewsErrorService newsErrorService;
	@Autowired
	private PermissionService permissionService;
    @Autowired
    private OperationLogService operationLogService;

	/**
	 * @Description: 资讯列表页面
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/listview")
	public String listview(HttpServletRequest request, Model model) {
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		List<Permission> list = permissionService.selectByBtu(staff.getPower(), "33");
		for (int i = 0; i < list.size(); i++) {
			model.addAttribute("btn"+list.get(i).getId(), "1");
		}
		return "admin/news/list";
	}

	/**
	 * @Description: 资讯数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<News> list = newsService.selectByAdminAll();
		PageInfo<News> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			News news = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", news.getId());// id
			map.put("newsNo", news.getNewsNo());// 编号
			map.put("headImage", news.getHeadImage());// 封面
			map.put("title", news.getTitle());// 标题
			map.put("content", news.getContentSimple());// 摘要
			map.put("newsLogSum", newsLogMapper.countByNews(news.getId()));// 日志
			map.put("newsErrorSum", newsErrorService.countByNews(news.getId()));// 纠错
			map.put("showFlag", news.getShowFlag() == 0 ? "否" : "是");// 使能
			map.put("remark", news.getRemark());// 备注
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 资讯日志数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/newsLog/list")
	@ResponseBody
	public AdminMessage newsLoglist(String id) {
		List<NewsLog> list = newsLogMapper.selectByNews(id);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			NewsLog newsLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", newsLog.getId());// id
			map.put("logtime", DateUtil.getFormat(newsLog.getCreateTime()));// 时间
			map.put("staff", newsLog.getStaff().getName());// 操作人
			map.put("content", newsLog.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(200, list.size(), listMap);
	}
	
	/**
	 * @Description: 资讯纠错数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/newsError/list")
	@ResponseBody
	public AdminMessage newsErrorlist(String id) {
		List<NewsError> list = newsErrorService.selectByNews(id);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			NewsError newsError = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", newsError.getId());// id
			map.put("createTime", DateUtil.getFormat(newsError.getCreateTime()));// 时间
			map.put("member", newsError.getMember().getAppnickname());// 操作人
			map.put("content", newsError.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(200, list.size(), listMap);
	}

	/**
	 * @Description: 资讯编辑页面
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月21日 下午2:56:42
	 */
	@RequestMapping("/edit")
	public String edit(Model model, String id) {
		News news = newsService.selectByPrimaryKey(id);
		model.addAttribute("id", news.getId());
		model.addAttribute("newsNo", news.getNewsNo());// 编号
		model.addAttribute("remark", news.getRemark());// 备注
		model.addAttribute("title", news.getTitle());// 标题
		model.addAttribute("contentSimple", news.getContentSimple());// 摘要
		model.addAttribute("content", news.getContent());// 内容
		model.addAttribute("headImage", news.getHeadImage());// 封面
		return "admin/news/edit";
	}
	
	/**
	 * @Description: 资讯新增页面
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月21日 下午2:56:42
	 */
	@RequestMapping("/add")
	public String add() {
		return "admin/news/add";
	}
	
	/**  
	 * @Description: 保存的修改的资讯
	 * @author 宋高俊  
	 * @param request
	 * @param news
	 * @return 
	 * @date 2018年10月24日 下午4:42:39 
	 */ 
	@RequestMapping("/saveNews")
	@ResponseBody
	public ApiMessage saveNews(HttpServletRequest request, News news) {
		// 登录用户
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");

		NewsLog newsLog = new NewsLog();
		newsLog.setId(Utils.getUUID());
		newsLog.setCreateTime(new Date());
		newsLog.setStaffId(staff.getId());
		
		if (StringUtil.isBank(news.getId())) {
			news.setId(Utils.getUUID());
			news.setCreateTime(new Date());
			news.setModifyTime(new Date());
			newsService.insertSelective(news);
			operationLogService.saveLog(staff.getId(), "资讯：新增"+news.getTitle(), Utils.getIpAddr(request));
			newsLog.setContent("资讯：新增"+news.getTitle());
		}else {
			News lodNews = newsService.selectByPrimaryKey(news.getId());
			
			String content = "";
			if (!lodNews.getRemark().equals(news.getRemark())) {
				content += "备注修改为" + news.getRemark() + "。";
			}
			if (!lodNews.getTitle().equals(news.getTitle())) {
				content += "标题修改为" + news.getTitle() + "。";
			}
			if (!lodNews.getContentSimple().equals(news.getContentSimple())) {
				content += "摘要修改为" + news.getContentSimple() + "。";
			}
			if (!lodNews.getHeadImage().equals(news.getHeadImage())) {
				content += "封面图修改为" + news.getHeadImage() + "。";
			}
			if (!lodNews.getContent().equals(news.getContent())) {
				content += "修改内容" + "。";
			}

			operationLogService.saveLog(staff.getId(), "资讯："+content, Utils.getIpAddr(request));
			newsLog.setContent(content);
			
			news.setModifyTime(new Date());
			newsService.updateByPrimaryKeySelective(news);
		}
		newsLog.setNewsId(news.getId());
		newsLogMapper.insertSelective(newsLog);
		
		return new ApiMessage(200, "修改成功");
	}
	
	

}
