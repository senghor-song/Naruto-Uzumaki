package com.xiaoyi.ssm.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.model.EstateFind;
import com.xiaoyi.ssm.model.MassSiteNew;
import com.xiaoyi.ssm.model.Proposal;
import com.xiaoyi.ssm.service.EstateFindService;
import com.xiaoyi.ssm.service.MassSiteNewService;
import com.xiaoyi.ssm.service.ProposalService;
import com.xiaoyi.ssm.util.DateUtil;

/**
 * @Description: 声音控制器
 * @author 宋高俊
 * @date 2018年7月26日 下午10:44:28
 */
@Controller(value = "adminIdeaController")
@RequestMapping("admin/idea")
public class IdeaController {

	@Autowired
	private EstateFindService estateFindService;
	@Autowired
	private MassSiteNewService massSiteNewService;
	@Autowired
	private ProposalService proposalService;

	/**
	 * @Description: 增加小区页面
	 * @author 宋高俊
	 * @date 2018年7月26日 下午7:46:48
	 */
	@RequestMapping("/listview")
	public String listview() {
		return "admin/idea/list";
	}

	/**
	 * @Description: 增加小区数据
	 * @author 宋高俊
	 * @date 2018年7月26日 下午7:46:48
	 */
	@RequestMapping("/estateAdd/list")
	@ResponseBody
	public AdminMessage estateAddList(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<EstateFind> list = estateFindService.selectByAll(null);
		PageInfo<EstateFind> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			EstateFind estateFind = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", estateFind.getId());// ID
			map.put("estate", estateFind.getEstate());// 建议创建小区
			map.put("createtime", DateUtil.getFormat(estateFind.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));// 提交时间
			map.put("emp", estateFind.getEmployee().getEmp());// 经纪人
			map.put("city", estateFind.getCity().getCity());// 城市
			map.put("store", estateFind.getEmployee().getStore());// 商户
			map.put("masstype", estateFind.getEmployee().getMasstype());// 账户类型
			map.put("empTel", estateFind.getEmployee().getTel());// 经纪人电话
			map.put("disposeStaff", estateFind.getStaff().getRname());// 处理人
			map.put("disposeOpinion", estateFind.getDisposeopinion());// 处理意见
			map.put("disposeTime", DateUtil.getFormat(estateFind.getDisposetime()));// 处理时间
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 增加网站数据
	 * @author 宋高俊
	 * @date 2018年7月26日 下午7:46:48
	 */
	@RequestMapping("/webAdd/list")
	@ResponseBody
	public AdminMessage webAddList(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<MassSiteNew> list = massSiteNewService.selectByAll(null);
		PageInfo<MassSiteNew> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			MassSiteNew massSiteNew = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", massSiteNew.getId());// id
			map.put("siteName", massSiteNew.getSitename());// 网站名
			map.put("createTime", massSiteNew.getAddtime());// 提交时间
			map.put("emp", massSiteNew.getEmployee().getEmp());// 经纪人
			map.put("city", massSiteNew.getEmployee().getCityname());// 城市
			map.put("store", massSiteNew.getEmployee().getStore());// 商户
			map.put("type", massSiteNew.getType() == 0 ? "采集" : "发布");// 类型
			map.put("siteUrl", massSiteNew.getSiteurl());// 网址
			map.put("mes", massSiteNew.getMes());// 说明
			map.put("disposeStaff", massSiteNew.getStaff().getRname());// 处理人
			map.put("disposeOpinion", massSiteNew.getDisposeopinion());// 处理意见
			map.put("disposeTime", DateUtil.getFormat(massSiteNew.getDisposetime()));// 处理时间
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 建议数据
	 * @author 宋高俊
	 * @date 2018年7月26日 下午7:46:48
	 */
	@RequestMapping("/proposal/list")
	@ResponseBody
	public AdminMessage proposalList(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Proposal> list = proposalService.selectByAll(null);
		PageInfo<Proposal> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Proposal proposal = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", proposal.getId());// ID
			map.put("revtime", DateUtil.getFormat(proposal.getRevtime()));// 建议时间
			map.put("type", proposal.getType());// 提交途径
			map.put("proposalno", proposal.getProposalno());// 编号
			map.put("detail", proposal.getDetail());// 内容
			map.put("disposeStaff", proposal.getStaff().getRname());// 处理人
			map.put("disposeOpinion", proposal.getDisposeopinion());// 处理意见
			map.put("disposeTime", DateUtil.getFormat(proposal.getDisposetime()));// 处理时间
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}
}
