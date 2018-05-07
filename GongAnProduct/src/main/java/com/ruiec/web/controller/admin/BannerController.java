package com.ruiec.web.controller.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.query.Set;
import com.ruiec.framework.server.support.query.Sort;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.entity.Banner;
import com.ruiec.web.entity.DictionaryType;
import com.ruiec.web.entity.Message;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.BannerService;
import com.ruiec.web.service.MessageService;
import com.ruiec.web.service.OperationLogService;

/**
 * 登录背景图控制器
 * 
 * @author yuankai Date 2017年12月01
 * */
@Controller
@RequestMapping("/admin/banner")
public class BannerController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(BannerController.class);

	@Resource
	private BannerService bannerService;
	@Resource
	private OperationLogService operationLogService;
    @Resource
    private MessageService messageService;
    @Resource(name="taskExecutor")
    private TaskExecutor taskExecutor;

	/**
	 * 登录背景图分页查询
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:04:44
	 */
	@RequestMapping(value = "list")
	public String list(Model model, Page page, Banner banner, HttpSession session, HttpServletRequest request) {
		// 获取当前登录用户
		session = request.getSession();
		try {
			Sort sort = Sort.desc("createDate");
			Filter filter = Filter.eq("isshow", 1);
			List<Banner> list = bannerService.findList(null, filter, sort);
			model.addAttribute("list", list);
			page = bannerService.findByPage(page);
			model.addAttribute("page", page);
		} catch (Exception e) {
			LOGGER.error("登录背景图分页查询失败" + e);
		}
		/*operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看了登录图片列表",
				LogUtil.getData(request.getParameterMap()));*/
		return "/admin/banner/list";
	}

	/**
	 * 删除登录背景图
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:05:15
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public JsonReturn delete(Integer[] ids, HttpSession session, HttpServletRequest request) {
		// 获取当前登录用户
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Banner> list = bannerService.deleteIsShow();
		for (int i = 0; i < list.size(); i++) {
			if (Arrays.asList(ids).contains(list.get(i).getId())) {
				return new JsonReturn(400, "删除失败!至少需要一张默认图片!");
			}
		}
		try {
			bannerService.delete(ids);
			operationLogService.insertOperationLogs(user, 2, request.getRequestURL().toString(), user.getPoliceName() + "删除登录背景图信息",
					LogUtil.getData(request.getParameterMap()));
			return new JsonReturn(200, "删除成功");
		} catch (Exception e) {
			return new JsonReturn(400, "删除失败!该条数据被其他地方所引用!");
		}
	}

	/**
	 * 添加登录背景图
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:05:38
	 */
	@RequestMapping("/add")
	public String add(Model model, DictionaryType dictionaryType) {
		return "/admin/banner/add";
	}

	/**
	 * 保存登录背景图
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:05:57
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public JsonReturn save(Banner banner, HttpSession session, HttpServletRequest request) {
		// 获取当前登录用户
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		try {
			bannerService.saveBanner(banner);
		} catch (Exception e) {
			LOGGER.error("添加登录背景图失败" + e);
			return new JsonReturn(400, "保存失败！");
		}
		operationLogService.insertOperationLogs(user, 1, request.getRequestURL().toString(), user.getPoliceName() + "新增登录背景图信息",
				LogUtil.getData(request.getParameterMap()));
		return new JsonReturn(200, "保存成功！", "/admin/banner/list.shtml");
	}

	/**
	 * 更新登录背景图片初始页
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:06:10
	 */
	@RequestMapping("/edit")
	public String edit(Model model, Integer id) {
		Banner banner = bannerService.get(id);
		model.addAttribute("id", id);
		model.addAttribute("banner", banner);
		return "/admin/banner/edit";
	}

	/**
	 * 更新登录背景图片
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:06:25
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public JsonReturn update(Model model, Banner banners, Integer id, HttpSession session, HttpServletRequest request) {
		// 获取当前登录用户
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		Banner banner = bannerService.get(id);
		try {
			if (id == null) {
				return new JsonReturn(400, "更新失败！");
			} else {
				if (banners.getIsshow() == 0 && banner.getIsshow() == 1) {
					return new JsonReturn(400, "至少需要一张默认图片");
				}
				banner.setIsshow(banners.getIsshow());
				banner.setImage(banners.getImage());
				banner.setIsNew(1);
				bannerService.updateIsShow(banner);
			}
		} catch (Exception e) {
			LOGGER.error("更新登录背景图片" + e);
			return new JsonReturn(400, "更新登录背景图片失败！");
		}
		operationLogService.insertOperationLogs(user, 3, request.getRequestURL().toString(), user.getPoliceName() + "更新登录背景图信息",
				LogUtil.getData(request.getParameterMap()));
		return new JsonReturn(200, "更新登录背景图片成功！", "/admin/banner/list.shtml");
	}

	/**
	 * 首页默认图
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:06:42
	 */
	@RequestMapping(value = "/defaultPic", method = RequestMethod.POST)
	@ResponseBody
	public JsonReturn defaultPic() {
		List<Banner> list = bannerService.defaultPic();
		JsonReturn jsonReturn = new JsonReturn(200, "/resources/admin/img/m_loginbg.png");

		if (list.size() > 0) {
			jsonReturn.setMsg(list.get(0).getImage());
		}
		return jsonReturn;
	}
	
	/**
	 * 设置app显示的banner图
	 * @author Senghor<br>
	 * @date 2018年4月12日 上午9:25:56
	 */
	@RequestMapping("/setAppBanner")
	@ResponseBody
	public JsonReturn setAppBanner(Integer[] ids, HttpSession session, HttpServletRequest request) {
		// 获取当前登录用户
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		try {
		    if (ids.length == 0) {
	            return new JsonReturn(400, "至少设置一张图片！");
	        }else {
		        List<Banner> banners = bannerService.findList(null, Filter.eq("isShowApp", 1), null);
		        if (banners.size()>0) {
		            Integer[] bannerIds = new Integer[banners.size()];
	                for (int i = 0; i < banners.size(); i++) {
	                    bannerIds[i]=banners.get(i).getId();
	                }
	                List<Set> sets = new ArrayList<Set>();
	                List<Filter> filters = new ArrayList<Filter>();
	                filters.add(Filter.in("id", bannerIds));
	                sets.add(Set.single("isShowApp", 0));
	                bannerService.update(sets, filters);
                }
            }
			List<Set> sets = new ArrayList<Set>();
			List<Filter> filters= new ArrayList<Filter>();
			sets.add(Set.single("isShowApp", 1));
			sets.add(Set.single("isNew", 1));
			filters.add(Filter.in("id", ids));
			bannerService.update(sets, filters);
			//生成一条广播消息
			Message message = new Message();
            message.setMessageType(5);
            message.setSourceId(0);
            message.setReleaseId(user.getId());
            message.setReceiveId(0);
            message.setIsPush(0);
            messageService.save(message);
			
            // 发送推送消息
            taskExecutor.execute(messageService.sendMessage());
			operationLogService.insertOperationLogs(user, 3, request.getRequestURL().toString(), user.getPoliceName() + "修改app的Banner图信息",
					LogUtil.getData(request.getParameterMap()));
			return new JsonReturn(200, "修改成功");
		} catch (Exception e) {
			return new JsonReturn(400, "修改失败!");
		}
	}
}
