/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.query.Sort;
import com.ruiec.web.common.ApiReturn;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.entity.Message;
import com.ruiec.web.service.MessageService;
import com.ruiec.web.util.RuiecDateUtils;

/**
 * 消息接口控制器
 * 
 * @author bingo<br>
 * @date 2018年1月8日 下午5:26:57
 */
@Controller
@RequestMapping("/api/admin/message")
public class ApiMessageController {

	protected static final Logger LOGGER = LoggerFactory.getLogger(ApiMessageController.class);
	@Resource
	private MessageService messageService;

	/**
	 * 消息列表接口
	 * 
	 * @author bingo<br>
	 * @date 2018年1月8日 下午8:15:08
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/list")
	public ApiReturn list(Integer messageType, Page page) {
		try {
			messageService.findByPage(page.add(Filter.eq("messageType", messageType)));
			// 返回数据
			Map<String, Object> data = new HashMap<>();
			data.put("pageSize", page.getPageSize());
			data.put("totalCount", page.getTotalCount());
			data.put("pageNumber", page.getPageNumber());
			List<Map<String, Object>> messages = new ArrayList<>();
			for (Message message : (List<Message>) page.getList()) {
				Map<String, Object> map = new HashMap<>();
				map.put("createTime", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(message.getCreateDate()));
				map.put("title", ObjectUtils.firstNonNull(message.getTitle(),GlobalUnit.NULLMSG));
				map.put("content", ObjectUtils.firstNonNull(message.getContent(),GlobalUnit.NULLMSG));
				map.put("sourceId", ObjectUtils.firstNonNull(message.getSourceId(),GlobalUnit.NULLMSG));
				messages.add(map);
			}
			data.put("mapList", messages);
			return new ApiReturn(200, "获取消息列表成功", data);
		} catch (Exception e) {
			LOGGER.error("获取消息列表失败", e);
			return new ApiReturn(400, "获取消息列表失败");
		}
	}

	/**
	 * 未读消息统计接口
	 * 
	 * @author bingo<br>
	 * @date 2018年1月9日 下午4:08:31
	 */
	@ResponseBody
	@RequestMapping("/unReadMessages")
	public ApiReturn unReadMessages() {
		try {
			// 返回数据
			Map<String, Object> data = new HashMap<>();
			List<Map<String, Object>> messages = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				List<Message> list = messageService.findList(1, Filter.eq("messageType", i + 1), Sort.desc("id"));
				if (null != list && list.size() > 0) {
					// 最新一条消息
					Message newMessage = list.get(0);
					// 当前消息类型未读数量
					List<Filter> filters = new ArrayList<>();
					filters.add(Filter.eq("isRead", 0));
					filters.add(Filter.eq("messageType", i + 1));
					long unReadCount = messageService.getCount(filters);

					// 封装消息类型、未读数量、最新一条消息
					Map<String, Object> map = new HashMap<>();
					map.put("messageType", newMessage.getMessageType());
					map.put("unReadCount", unReadCount);
					map.put("createTime", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(newMessage.getCreateDate()));
					map.put("title", ObjectUtils.firstNonNull(newMessage.getTitle(),GlobalUnit.NULLMSG));
					map.put("content", ObjectUtils.firstNonNull(newMessage.getContent(),GlobalUnit.NULLMSG));
					messages.add(map);
				}
			}
			data.put("mapList", messages);
			return new ApiReturn(200, "获取未读消息统计成功", data);
		} catch (Exception e) {
			LOGGER.error("获取消息列表失败", e);
			return new ApiReturn(400, "获取未读消息统计失败");
		}
	}

}
