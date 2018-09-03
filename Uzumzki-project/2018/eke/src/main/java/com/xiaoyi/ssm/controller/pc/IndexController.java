package com.xiaoyi.ssm.controller.pc;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dao.ProposalMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Proposal;
import com.xiaoyi.ssm.util.Utils;

/**  
 * @Description: 前台首页控制器
 * @author 宋高俊  
 * @date 2018年7月6日 下午6:42:51 
 */ 
@Controller
@RequestMapping("index")
public class IndexController {
	@Autowired
	private ProposalMapper proposalMapper;
	
	/**  
	 * @Description: 首页
	 * @author 宋高俊  
	 * @date 2018年7月6日 下午6:43:29 
	 */ 
	@RequestMapping("/index")
	public String index() {
		return "index/index";
	}

	/**  
	 * @Description: 产品介绍
	 * @author 宋高俊  
	 * @date 2018年7月6日 下午6:43:29 
	 */ 
	@RequestMapping("/introduce")
	public String introduce() {
		return "index/introduce";
	}
	
	/**  
	 * @Description: 客户见证
	 * @author 宋高俊  
	 * @date 2018年7月6日 下午6:43:29 
	 */ 
	@RequestMapping("/client")
	public String client() {
		return "index/client";
	}
	
	/**  
	 * @Description: 代理加盟
	 * @author 宋高俊  
	 * @date 2018年7月6日 下午6:43:29 
	 */ 
	@RequestMapping("/league")
	public String league() {
		return "index/league";
	}

	/**
	 * @Description: 文档介绍
	 * @author 宋高俊
	 * @date 2018年7月6日 下午6:49:58
	 */
	@RequestMapping("/document")
	public String document() {
		return "index/document/document";
	}

	/**
	 * @Description: 视频教程
	 * @author 宋高俊
	 * @date 2018年7月6日 下午6:49:58
	 */
	@RequestMapping("/videoHelp")
	public String videoHelp() {
		return "index/document/videoHelp";
	}

	/**  
	 * @Description: 产品评价页面
	 * @author 宋高俊  
	 * @date 2018年7月9日 下午7:26:03 
	 */ 
	@RequestMapping("/leaveWord")
	public String leaveWord() {
		return "leaveWord";
	}

	/**  
	 * @Description: 保存评价方法
	 * @author 宋高俊  
	 * @date 2018年7月9日 下午7:26:03 
	 */ 
	@RequestMapping("/saveLeaveWord")
	@ResponseBody
	public ApiMessage saveLeaveWord(Proposal proposal) {
		proposal.setId(Utils.getUUID());
		proposal.setRevtime(new Date());
		proposal.setType(0);
		proposalMapper.insertSelective(proposal);
		return ApiMessage.succeed();
	}
}
