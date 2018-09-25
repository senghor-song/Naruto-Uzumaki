package com.ruiec.web.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseController;
import com.ruiec.web.common.ApiReturn;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.entity.Notice;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.NoticeService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.util.RuiecDateUtils;

/**
 * 公告查询api控制器
 * 
 * @author yuankai<br>
 * @date 2018年1月6日 下午7:39:58
 */
@Controller
@RequestMapping("/api/admin/notice")
public class ApiNoticeController extends BaseController {

	private static final Logger LOGGER = Logger.getLogger(ApiNoticeController.class);

	@Resource
	private NoticeService noticeService;
	@Resource
	private UserService userService;
	@Resource
	private OperationLogService operationLogService;

	/**
	 * 查询所有公告列表
	 * 
	 * @author yuankai<br>
	 *         Date: 2018年1月8号
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ApiReturn selectAll(Page page, Notice notice) {
		try {
			page = noticeService.findByPage(page);
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < page.getList().size(); i++) {
				Notice newNotice = (Notice) page.getList().get(i);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", newNotice.getId());
				map.put("title", ObjectUtils.firstNonNull(newNotice.getTitle(), GlobalUnit.NULLMSG));
				map.put("createDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(newNotice.getCreateDate()));
				mapList.add(map);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mapList", mapList);
			map.put("totalCount", page.getTotalCount());
			map.put("pageNumber", page.getPageNumber());
			map.put("pageSize", page.getPageSize());
			return new ApiReturn(200, "查询成功", map);
		} catch (Exception e) {
			return new ApiReturn(400, "查询失败");
		}
	}

	/**
	 * 查看公告信息详情
	 * 
	 * @author yuankai<br>
	 *         Date: 2018年1月8号
	 */
	@ResponseBody
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public ApiReturn add(Integer id, HttpServletRequest request) {
		try {
			User loginUser = (User) request.getSession().getAttribute("user");
			if (StringUtils.isNotBlank(loginUser.getUnreadNotice())) {
				loginUser.setUnreadNotice(loginUser.getUnreadNotice() + "," + id.toString());
			} else {
				loginUser.setUnreadNotice(id.toString());
			}
			userService.updateInclude(loginUser, new String[] { "readNotice" }, null);
			Notice notice = noticeService.get(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", ObjectUtils.firstNonNull(notice.getTitle(),GlobalUnit.NULLMSG));
			map.put("publisher", ObjectUtils.firstNonNull(notice.getPublisher(),GlobalUnit.NULLMSG));
			map.put("createDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(notice.getCreateDate()));
			map.put("content", ObjectUtils.firstNonNull(notice.getContent(),GlobalUnit.NULLMSG));
			// 插入日志
			operationLogService.insertOperationLogs(loginUser, 4, request.getRequestURL().toString(), loginUser.getPoliceName() + "查询公告信息详情",
					LogUtil.getData(request.getParameterMap()));
			return new ApiReturn(200, "查询成功", map);
		} catch (Exception e) {
			return new ApiReturn(400, "查看公告信息失败");
		}
	}

	/**
	 * 新增公告信息
	 * 
	 * @author yuankai <br>
	 *         Date: 2018年1月8号
	 */
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ApiReturn save(Notice notice, HttpServletRequest request, HttpSession session, User user) {
		try {
			user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) session.getAttribute("loginUserUnit");
			if (notice.getTitle() == null || "".equals(notice.getTitle())) {
				return new ApiReturn(400, "新增公告信息标题不得为空");
			}
			if (notice.getTitle().length() > 20) {
				return new ApiReturn(400, "新增公告信息标题不得多余20字");
			}
			if (user.getId() == 1 || "city".equals(loginUserUnit.getUnitRank().getName())) {
				notice.setPublisher(user.getPoliceName());
				noticeService.save(notice);
				// 插入日志
				operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查询公告信息详情",
						LogUtil.getData(request.getParameterMap()));
				return new ApiReturn(200, "添加公告成功！");
			} else {
				return new ApiReturn(400, "权限不够，请联系管理员!");
			}
		} catch (Exception e) {
			LOGGER.error("添加公告失败" + e.getMessage());
			return new ApiReturn(400, "添加公告失败！");
		}
	}
}
