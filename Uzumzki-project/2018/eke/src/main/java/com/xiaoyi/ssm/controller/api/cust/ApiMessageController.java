package com.xiaoyi.ssm.controller.api.cust;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.PageBean;

/**
 * @Description: 消息接口控制器
 * @author 宋高俊
 * @date 2018年7月18日 下午4:05:38
 */
@Controller
@RequestMapping("api/message")
public class ApiMessageController {

	/**
	 * @Description: 消息数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ApiMessage list(Model model, PageBean pageBean) {
		/*PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<NewsHead> list = newsHeadService.selectByAll(null);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			NewsHead newHead = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", newHead.getId());
			map.put("regdate", DateUtil.getFormat(newHead.getRegdate()));// 登记时间
			listMap.add(map);
		}*/
		return ApiMessage.succeed();
	}

	/**
	 * @Description: 消息数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/details")
	@ResponseBody
	public ApiMessage details(String id) {
		return ApiMessage.succeed();
	}	
}
