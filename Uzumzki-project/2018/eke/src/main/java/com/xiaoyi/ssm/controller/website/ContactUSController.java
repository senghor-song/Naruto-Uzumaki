package com.xiaoyi.ssm.controller.website;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.WebsitePropertyDto;
import com.xiaoyi.ssm.model.Cust;
import com.xiaoyi.ssm.model.PageBean;
import com.xiaoyi.ssm.model.Proposal;
import com.xiaoyi.ssm.service.ProposalService;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 联系我们接口控制器
 * @author 宋高俊
 * @date 2018年8月15日 上午11:19:42
 */
@Controller("websiteContactUSController")
@RequestMapping("/website/contactUS")
public class ContactUSController {

	private final Logger Logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProposalService proposalService;

	/**
	 * @Description: 改善意见接口接口
	 * @author 宋高俊
	 * @date 2018年8月15日 上午9:46:09
	 */
	@RequestMapping(value = "/proposal", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage proposal(HttpServletRequest request, String content) {
		//官网提交意见
		Cust cust = (Cust) request.getSession().getAttribute("websitecust");
		if (cust != null) {
			Proposal proposal = new Proposal();
			proposal.setId(Utils.getUUID());
			proposal.setUserid(cust.getId());
			proposal.setType(0);
			proposal.setDetail(content);
			proposal.setRevtime(new Date());
			proposalService.insertSelective(proposal);
			return ApiMessage.succeed();
		}else {
			return new ApiMessage(400, "请登录后再预约");
		}
	}
}
