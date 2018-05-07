package com.ruiec.web.controller.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.entity.Message;
import com.ruiec.web.entity.Notice;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.MessageService;
import com.ruiec.web.service.NoticeService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.util.RuiecDateUtils;

/**
 * 公告控制器
 * @author yuankai
 * @Date 2017年11月29日
 * */
@Controller
@RequestMapping("/admin/notice")
public class NoticeController extends BaseAdminController{
   
	private static final Logger LOGGER = Logger.getLogger(NoticeController.class);
	
	@Resource
	private NoticeService noticeService;
	@Resource
	private UserService userService;
	@Resource
	private OperationLogService operationLogService;
    @Resource
    private MessageService messageService;
    @Resource(name="taskExecutor")
    private TaskExecutor taskExecutor;
	
	/**
	 * 查询所有公告
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:10:15
	 */
	@RequestMapping("/list")
	public String selectAll(Model model, String title, Page page,
			String startDate, String endDate, HttpSession session,
			HttpServletRequest request) {
		// 获取当前登录用户
		session = request.getSession();
		User user = (User) session.getAttribute("user");
//		LoginUserUnit loginUserUnit = (LoginUserUnit) session.getAttribute("loginUserUnit");
		try {
			if (!StringUtils.isBlank(title)) {
				page.add(Filter.like("title", title));
			}
			// 开始时间
			if (StringUtils.isNotBlank(startDate)) {
				model.addAttribute("startDate", startDate);
				startDate = startDate + ":00";
				Date beginDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(startDate);
				page.add(Filter.ge("createDate", beginDate));
			}
			// 结束时间
			if (StringUtils.isNotBlank(endDate)) {
				model.addAttribute("endDate", endDate);
				endDate = endDate + ":59";
				Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(endDate);
				page.add(Filter.le("createDate", endTime));
			}
		} catch (Exception e) {
			LOGGER.error("查询所有公告" + e);
		}
		page = noticeService.findByPage(page);
		model.addAttribute("title", title);
		model.addAttribute("page", page);
		// 插入操作日志
//		operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看了公告列表",LogUtil.getData(request.getParameterMap()));
		return "/admin/notice/list";
	}

	/**
	 * 查看公告信息
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:10:26
	 */
	@RequestMapping("/detail")
	public String add(Model model, Integer id, HttpSession session, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (id == null) {
			return "redirect:/admin/notice/list";
		}
		if (null != user) {
			if (StringUtils.isNotBlank(user.getUnreadNotice())) {
				String newUnreads = removeUnread(id, user);
				user.setUnreadNotice(newUnreads);
			}
			userService.updateInclude(user, new String[] { "unreadNotice" }, null);
		}
		Notice notice = noticeService.get(id);
		model.addAttribute("notice", notice);
		model.addAttribute("id", id);
		// 插入操作日志
		operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看了公告详情",LogUtil.getData(request.getParameterMap()));
		return "/admin/notice/detail";
	}

	/**
	 * 添加公告信息
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:10:41
	 */
	@RequestMapping("/add")
	public String add(Model model, Notice notice) {
		return "/admin/notice/add";
	}

	/**
	 * 保存公告信息
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:10:48
	 */
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public JsonReturn save(Notice notice, HttpServletRequest request,User user,String content,String title) {
		try {
			user = (User) request.getSession().getAttribute("user");
			//防止html代码的注入
			if (null != notice.getTitle() && !"".equals(notice.getTitle())) {
			     title = title.trim().replace("<", "《").replace(">", "》").replace("'", "‘").replace("\"", "”");
			}
			if (null != notice.getContent() && !"".equals(notice.getContent())) {
			     content = content.trim().replace("<", "《").replace(">", "》").replace("'", "‘").replace("\"", "”");
			}
			if (notice.getTitle()==null || "".equals(notice.getTitle())) {
				return new JsonReturn(400, "新增公告信息标题不得为空");
			}
			if (notice.getTitle().length()>=30) {
				return new JsonReturn(400, "新增公告信息标题不得多余30字");
			}
			notice.setPublisher(user.getPoliceName());
			notice.setTitle(title);
			notice.setContent(content);
			notice = noticeService.save(notice);
			noticeService.updateUnreadNotice(notice.getId());
			//生成一条广播消息
            Message message = new Message();
            message.setMessageType(4);
            message.setSourceId(0);
            message.setReleaseId(user.getId());
            message.setReceiveId(0);
            message.setIsPush(0);
            messageService.save(message);
            
            // 发送推送消息
            taskExecutor.execute(messageService.sendMessage());
		} catch (Exception e) {
			LOGGER.error("添加公告失败" + e);
			return new JsonReturn(400, "添加公告失败！");
		}
		// 插入操作日志
		operationLogService.insertOperationLogs(user, 1, request.getRequestURL().toString(), user.getPoliceName() + "添加公告信息",LogUtil.getData(request.getParameterMap()));
		return new JsonReturn(200, "添加公告成功！", "/admin/notice/list.shtml");
	}

	/**
	 * 删除公告信息
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:11:01
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public JsonReturn delete(Integer[] ids, HttpServletRequest request,
			HttpSession session) {
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		try {
			noticeService.delete(ids);
			operationLogService.insertOperationLogs(user, 2, request.getRequestURL().toString(), user.getPoliceName()+ "删除公告信息", LogUtil.getData(request.getParameterMap()));
			return new JsonReturn(200, "删除成功！");
			// 插入操作日志
		} catch (Exception e) {
			return new JsonReturn(400, "删除失败！");
		}
	}

	/**
	 * 更新公告初始页
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:11:11
	 */
	@RequestMapping("/edit")
	public String edit(Model model, Integer id) {
		Notice notice = null;
		if (id != null) {
			notice = noticeService.get(id);
		}
		model.addAttribute("notice", notice);
		model.addAttribute("id", id);
		return "/admin/notice/edit";
	}

	/**
	 * 更新公告信息
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:11:25
	 */
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public JsonReturn update(Notice notice, Model model, Integer id,HttpServletRequest request, HttpSession session, User user,String content,String title) {
		StringBuffer str = new StringBuffer();
		if (id == null) {
			return new JsonReturn(400, "修改失败！");
		}
		//防止html代码的注入
		if (null != notice.getTitle() && !"".equals(notice.getTitle())) {
		     title = title.trim().replace("<", "《").replace(">", "》").replace("'", "‘").replace("\"", "”");
		}
		//防止html代码的注入
		if (null != notice.getContent() && !"".equals(notice.getContent())) {
		     content = content.trim().replace("<", "《").replace(">", "》").replace("'", "‘").replace("\"", "”");
		}
		//判断标题和内容不为空
		if (notice.getTitle()==null || notice.getContent()==null) {
			return new JsonReturn(400, "更新公告信息标题和内容不为空");
		}
		if (notice.getTitle().length()>=30) {
			return new JsonReturn(400, "更新公告信息标题不得多余30字");
		}
		user = (User) request.getSession().getAttribute("user");
		Notice locanotice = noticeService.get(notice.getId());
		str.append(locanotice);
		notice.setPublisher(user.getPoliceName());
		notice.setTitle(title);
		notice.setContent(content);
		noticeService.update(notice);
		model.addAttribute("notice", notice);
		operationLogService.insertOperationLogs(user, 3, request.getRequestURL().toString(), user.getPoliceName() + "更新公告信息",LogUtil.getData(request.getParameterMap()));
		return new JsonReturn(200, "修改成功！", "/admin/notice/list.shtml");
	}

	/**
	 * 警员查看的公告列表
	 * 
	 * @author 陈靖原<br>
	 *  Date: 2017年11月29日
	 */
	@RequestMapping("/noticelist")
	public String list(Model model, String title, Page page, String startDate,
			String endDate, HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		User user1 = (User) session.getAttribute("user");
		try {
			// 是否查看过
			User user = (User) session.getAttribute("user");
			user = userService.get(user.getId());
			String unreadNotice = user.getUnreadNotice();
			if (StringUtils.isNotBlank(unreadNotice)) {
				String[] unread = unreadNotice.split(",");
				model.addAttribute("unread", unread);
			}
			if (!StringUtils.isBlank(title)) {
				page.add(Filter.like("title", title));
			}
			// 开始时间
			if (StringUtils.isNotBlank(startDate)) {
				model.addAttribute("startDate", startDate);
				startDate = startDate + ":00";
				Date beginDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(startDate);
				page.add(Filter.ge("createDate", beginDate));
			}
			// 结束时间
			if (StringUtils.isNotBlank(endDate)) {
				model.addAttribute("endDate", endDate);
				endDate = endDate + ":59";
				Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(endDate);
				page.add(Filter.le("createDate", endTime));
			}
			//只允许查看用户创建日期之后的公告
			page.add(Filter.ge("createDate", user.getCreateDate()));
		} catch (Exception e) {
			LOGGER.error("查询公告失败"+e);
		}

		page = noticeService.findByPage(page);
		model.addAttribute("title", title);
		model.addAttribute("page", page);
//		operationLogService.insertOperationLogs(user1, 4, request
//				.getRequestURL().toString(), user1.getPoliceName() + "查看了公告列表",
//				LogUtil.getData(request.getParameterMap()));
		return "/admin/notice/noticelist";
	}

	/**
	 * 查询导航公告
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月20日 下午9:03:56
	 */
	@ResponseBody
	@RequestMapping(value = "nlist", method = RequestMethod.POST)
	public JsonReturn nlist(HttpSession session) {
		//未查看次数
		int count = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		// 未看过
		User user = (User) session.getAttribute("user");
		user = userService.get(user.getId());
		if (StringUtils.isNotBlank(user.getUnreadNotice())) {
			String[] unreadNotices = user.getUnreadNotice().split(",");
			if (unreadNotices != null) {
				// 未读公告集合
				List<Notice> list = new ArrayList<Notice>();
				String newNotice = user.getUnreadNotice();
				count = unreadNotices.length;
				for (int i = 0; i < unreadNotices.length; i++) {
					Notice notice = null;
					if (StringUtils.isNotBlank(unreadNotices[i])) {
						notice = noticeService.get(Integer.valueOf(unreadNotices[i]));
					}else {
						count--;
						continue;
					}
					if (notice == null) {
						count--;
						newNotice = removeUnread(Integer.valueOf(unreadNotices[i]), user);
						continue;
					}
					if (list.size() < 3) {
						list.add(notice);
					}
				}
				if (list != null && list.size() > 0) {
					user.setUnreadNotice(newNotice);
					userService.updateInclude(user, new String[] { "unreadNotice" }, null);	
				}else {
					user.setUnreadNotice("");
					userService.updateInclude(user, new String[] { "unreadNotice" }, null);	
				}
				// 未读公告所有需要的字段集合
				List<Map<String, Object>> nlist = new ArrayList<>();
				if (null != list) {
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> mp = new HashMap<String, Object>();
						mp.put("id", list.get(i).getId());
						mp.put("title", list.get(i).getTitle());
						mp.put("createDate", RuiecDateUtils.format_yyyy_MM_dd(list.get(i).getCreateDate()));
						nlist.add(mp);
					}
				}
				map.put("data", nlist);
			}
		}
		map.put("count", count);
		return new JsonReturn(map);
	}
	
	/**
	 * 查看公告时,移除用户未读公告id
	 * @author 陈靖原<br>
	 * @date 2018年1月24日 下午5:38:00
	 */
	private static String removeUnread(Integer id, User user) {
		List<Integer> unreadList = new ArrayList<Integer>();
		String[] unreads = user.getUnreadNotice().split(",");
		for (String unread : unreads) {
			if (StringUtils.isNotBlank(unread) && !id.equals(Integer.valueOf(unread))) {
				unreadList.add(Integer.valueOf(unread));
			}
		}
		String newUnreads = "";
		for (int i = 0; i < unreadList.size(); i++) {
			if (i == unreadList.size() - 1) {
				newUnreads += unreadList.get(i).toString();
			} else {
				newUnreads += unreadList.get(i).toString() + ",";
			}
		}
		return newUnreads;
	}
}
