package com.xiaoyi.ssm.controller.admin;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dao.NewsHeadLogMapper;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminNewsHeadDto;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.NewsHead;
import com.xiaoyi.ssm.model.NewsHeadLog;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.service.NewsHeadService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.ImportExcelUtil;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 楼讯接口控制器
 * @author 宋高俊
 * @date 2018年7月26日 下午4:17:14
 */
@Controller(value = "adminNewsHeadController")
@RequestMapping("admin/newsHead")
public class NewsHeadController {

	private final Logger LOGGER = LoggerFactory.getLogger(NewsHeadController.class);

	@Autowired
	private NewsHeadService newsHeadService;
	@Autowired
	private NewsHeadLogMapper newsHeadLogMapper;

	/**
	 * @Description: 楼讯列表页面
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/listview")
	public String list() {
		return "admin/newsHead/list";
	}

	/**
	 * @Description: 楼讯数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/list")
	@ResponseBody
	public AdminMessage list(Model model, AdminPage adminPage, AdminNewsHeadDto adminNewsHeadDto) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<NewsHead> list = newsHeadService.selectBySearch(adminNewsHeadDto);
		PageInfo<NewsHead> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			NewsHead newHead = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", newHead.getId());
			map.put("regdate", DateUtil.getFormat(newHead.getRegdate()));// 登记时间
			map.put("status", newHead.getStatus());// 状态
			map.put("newsheadno", newHead.getNewsheadno());// 编号
			map.put("title", newHead.getTitle());// 标题
			map.put("sharecount", newHead.getSharecount());// 分享
			map.put("newsHeadLogSum", newsHeadLogMapper.countLogByNewsHead(newHead.getId()));// 日志
			map.put("sort", newHead.getSort());// 排序
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 楼讯日志数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/newsHeadLog/list")
	@ResponseBody
	public AdminMessage newsHeadLogList(Model model, AdminPage adminPage, String id) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		NewsHeadLog newsHeadLog = new NewsHeadLog();
		newsHeadLog.setNewsheadid(id);
		List<NewsHeadLog> list = newsHeadLogMapper.selectByAll(newsHeadLog);
		PageInfo<NewsHeadLog> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			NewsHeadLog nhl = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", nhl.getId());
			map.put("logtime", DateUtil.getFormat(nhl.getLogtime()));// 时间
			map.put("rname", nhl.getStaff().getRname());// 操作人
			map.put("content", nhl.getContent());// 修改内容
			listMap.add(map);
		}
		return new AdminMessage(100, pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 删除楼讯数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/delNewsHead")
	@ResponseBody
	public ApiMessage delNewsHead(String id) {
		int flag = newsHeadService.deleteByPrimaryKey(id);
		if (flag > 0) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 楼讯详情页面
	 * @author 宋高俊
	 * @date 2018年8月10日 上午9:35:44
	 */
	@RequestMapping("/editNewsHead")
	public String editNewsHead(Model model, String id, String type) {
		NewsHead newsHead = newsHeadService.selectByPrimaryKey(id);
		model.addAttribute("newsHead", newsHead);
		if ("look".equals(type)) {
			return "/admin/newsHead/look";
		} else {
			return "/admin/newsHead/update";
		}
	}

	/**
	 * @Description: 楼讯内容详情页面
	 * @author 宋高俊
	 * @date 2018年8月10日 上午9:35:44
	 */
	@RequestMapping("/detailsNewsHead")
	public String detailsNewsHead(Model model, String id) {
		NewsHead newsHead = newsHeadService.selectByPrimaryKey(id);
		model.addAttribute("newsHead", newsHead);
		return "/admin/newsHead/detailsNewsHead";
	}

	/**
	 * @Description: 修改楼讯数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/updateNewsHead")
	@ResponseBody
	public ApiMessage updateNewsHead(NewsHead newsHead) {
		int flag = newsHeadService.updateByPrimaryKeySelective(newsHead);
		if (flag > 0) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 楼讯导入表格
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/importExcel")
	@ResponseBody
	public ApiMessage importExcel(HttpServletRequest request, MultipartFile file, Model model, AdminPage adminPage) {
		Staff staff = (Staff) request.getSession().getAttribute("adminloginuser");
		Map<String, Object> redismap = new HashMap<>();
		int countLine = 0;
		Date date = new Date();// 导入表格时间
		int row = 1;
		LOGGER.info("开始导入重点人员execl表格");
		Map<String, Object> map = new HashMap<>();
		List<String> errorlist = new ArrayList<String>();
		List<String> succeedlist = new ArrayList<String>();
		if (file.isEmpty()) {
			LOGGER.info("文件不存在,停止导入");
			return new ApiMessage(400, "导入失败,文件不存在!");
		}
		InputStream in = null;
		List<List<Object>> listob = null;
		try {
			in = file.getInputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
		} catch (Exception e) {
			LOGGER.info("文件格式不正确,停止导入!");
			return new ApiMessage(400, "请上传execl文件");
		}
		// 创建一个数值格式化对象
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(2);
		if (listob != null && listob.size() > 0) {
			countLine = listob.size();
			for (List<Object> excelList : listob) {
				row++;
				String result = numberFormat.format((float) row / (float) (countLine + 1) * 100);
				redismap.put("current", result);
				RedisUtil.addRedis(Global.REDIS_SESSION_UPLOAD_MAP, request.getSession().getId() + "newshead", redismap);
				NewsHead newsHead = new NewsHead();
				newsHead.setId(Utils.getUUID());// 生成UUID
				newsHead.setRegdate(date);// 创建时间
				newsHead.setStaffid(staff.getStaffid());// 登记人
				newsHead.setShowdate(date);// 显示时间
				if ("无".equals(excelList.get(0).toString()) || "无".equals(excelList.get(1).toString())
						|| "无".equals(excelList.get(2).toString()) || "无".equals(excelList.get(3).toString())
						|| "无".equals(excelList.get(4).toString()) || "无".equals(excelList.get(5).toString())
						|| "无".equals(excelList.get(6).toString())) {
					// 有错误数据则存入错误集合
					errorlist.add("第" + row + "行有空数据");
					continue;
				}
				try {
					String status = excelList.get(0).toString();
					String title = excelList.get(1).toString();
					String source = excelList.get(2).toString();
					String path = excelList.get(3).toString();
					String content = excelList.get(4).toString();
					Byte flagtop = new Byte(excelList.get(5).toString());
					Integer sort = Integer.valueOf(excelList.get(6).toString());

					newsHead.setStatus(status);
					newsHead.setTitle(title);
					newsHead.setSource(source);
					newsHead.setCoverpath(path);
					newsHead.setContent(content);
					newsHead.setFlagtop(flagtop);
					newsHead.setSort(sort);
				} catch (Exception e) {
					errorlist.add("第" + row + "行有类型错误数据");
					continue;
				}
				// 保存楼讯数据
				try {
					newsHeadService.insertSelective(newsHead);
					succeedlist.add("第" + row + "行导入成功");
				} catch (Exception e) {
					errorlist.add("第" + row + "行未知错误");
				}
			}
		} else {
			LOGGER.info("文件没有内容,停止导入!");
			return new ApiMessage(400, "请填写的模板表格内容!");
		}
		map.put("count", countLine);// 总条数
		map.put("succeed", succeedlist.size());// 成功条数
		map.put("error", errorlist.size());// 失败条数

		map.put("succeedlist", succeedlist);// 成功条数
		map.put("errorlist", errorlist);// 失败条数
		return new ApiMessage(200, "导入成功", map);
	}
}
