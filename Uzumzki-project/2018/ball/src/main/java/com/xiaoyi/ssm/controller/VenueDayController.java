package com.xiaoyi.ssm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.MemberDayDto;
import com.xiaoyi.ssm.dto.WXBillDto;
import com.xiaoyi.ssm.dto.WXFundflowDto;
import com.xiaoyi.ssm.model.AmountRefund;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.MemberDay;
import com.xiaoyi.ssm.model.MemberDayLog;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.Reserve;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueCoach;
import com.xiaoyi.ssm.model.VenueDayStatis;
import com.xiaoyi.ssm.model.VenueDayStatisLog;
import com.xiaoyi.ssm.model.WXCompanyPayment;
import com.xiaoyi.ssm.model.WXFundflow;
import com.xiaoyi.ssm.service.AmountRefundService;
import com.xiaoyi.ssm.service.MemberDayLogService;
import com.xiaoyi.ssm.service.MemberDayService;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.ReserveService;
import com.xiaoyi.ssm.service.StaffService;
import com.xiaoyi.ssm.service.TrainCoachService;
import com.xiaoyi.ssm.service.VenueCoachService;
import com.xiaoyi.ssm.service.VenueDayStatisLogService;
import com.xiaoyi.ssm.service.VenueDayStatisService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.service.WXBillService;
import com.xiaoyi.ssm.service.WXFundflowService;
import com.xiaoyi.ssm.util.Arith;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.MoblieMessageUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.WXConfig;
import com.xiaoyi.ssm.wxPay.WXPayUtil;

/**
 * @Description: 场馆统计数据
 * @author 宋高俊
 * @date 2018年9月20日 下午5:03:09
 */
@Controller(value = "adminVenueDayController")
@RequestMapping(value = "/admin/venueDay")
public class VenueDayController {

	private static Logger logger = Logger.getLogger(VenueDayController.class.getName());
	
	@Autowired
	private MemberDayService memberDayService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private VenueService venueService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private VenueDayStatisService venueDayStatisService;
	@Autowired
	private WXFundflowService wxFundflowService;
	@Autowired
	private VenueDayStatisLogService venueDayStatisLogService;
	@Autowired
	private WXBillService wxBillService;
	@Autowired
	private MemberDayLogService memberDayLogService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private AmountRefundService amountRefundService;
	@Autowired
	private ReserveService reserveService;
	@Autowired
	private VenueCoachService venueCoachService;
	@Autowired
	private TrainCoachService trainCoachService;

	/**
	 * @Description: 场馆统计数据页面
	 * @author 宋高俊
	 * @return
	 * @date 2018年11月26日下午7:37:13
	 */
	@RequestMapping(value = "/listview")
	public String listview(Model model, String dateStr, Integer size) {
		
		Date nowDate = new Date();
		
		// 没有日期默认昨天
		if (StringUtil.isEmpty(dateStr)) {
			dateStr = DateUtil.getFormat(DateUtil.getPreTime(new Date(), 3, -1), "yyyy-MM-dd");
		} else {
			nowDate = DateUtil.getParse(dateStr, "yyyy-MM-dd");
			
			Date flagDate = DateUtil.getPreTime(nowDate, 3, size);
			if(flagDate.getTime() > DateUtil.getWeeHours(DateUtil.getPreTime(new Date(), 3, -1), 0).getTime()) {
				dateStr = DateUtil.getFormat(DateUtil.getPreTime(new Date(), 3, -1), "yyyy-MM-dd");
			} else {
				// 有日期 判断是否需要处理
				if (size != null) {
					dateStr = DateUtil.getFormat(DateUtil.getPreTime(nowDate, 3, size), "yyyy-MM-dd");
				}
			}
		}

		String dateStart = DateUtil.getFormat(DateUtil.getWeeHours(DateUtil.getPreTime(nowDate, 3, -1), 0));
		String dateEnd = DateUtil.getFormat(DateUtil.getWeeHours(DateUtil.getPreTime(nowDate, 3, -1), 1));
		model.addAttribute("nowDate", dateStr);// 交易日

		MemberDayDto memberDayDto = memberDayService.countMemberDay(dateStr);
		model.addAttribute("realityAmount", memberDayDto.getRealityAmount());// 拟转用户
		model.addAttribute("paySubsidy", memberDayDto.getPaySubsidy());// 额度补贴
		model.addAttribute("realityAmountSum", memberDayDto.getRealityAmountSum());// 全部留存待转
		model.addAttribute("paySubsidyFlag", memberDayDto.getPaySubsidy() <= Arith.mul(memberDayDto.getRealityAmount(), 0.01));// 额度补贴状态
		model.addAttribute("profitAmount", Arith.sub(memberDayDto.getOughtAmount(), memberDayDto.getRealityAmount()));// 当日盈亏
		
		WXFundflow wxFundflow = wxFundflowService.selectByDate(dateStart, dateEnd);
		if (wxFundflow != null) {
			// 判断是否有对账
			VenueDayStatis venueDayStatis = venueDayStatisService.selectByNowDate(dateStr);
			if (venueDayStatis == null) {
				model.addAttribute("confirmFlag", "");// 是否对账
			} else {
				model.addAttribute("confirmFlag", venueDayStatis.getUserFlag());// 是否对账
			}
			
			WXFundflowDto wxFundflowDto = wxFundflowService.countWXFundflowBasic(dateStart, dateEnd);
			model.addAttribute("dealIncome", String.format("%.2f", wxFundflowDto.getDealIncome()));
			model.addAttribute("dealRefund", String.format("%.2f", wxFundflowDto.getDealRefund()));
			model.addAttribute("dealFee", String.format("%.2f", wxFundflowDto.getDealFee()));
			model.addAttribute("withdrawalCash", String.format("%.2f", wxFundflowDto.getWithdrawalCash()));
			model.addAttribute("basicToOperation", String.format("%.2f", wxFundflowDto.getBasicToOperation()));
			model.addAttribute("operationToBasic", String.format("%.2f", wxFundflowDto.getOperationToBasic()));
			model.addAttribute("restsIncome", String.format("%.2f", wxFundflowDto.getRestsIncome()));
			model.addAttribute("accountAmount", String.format("%.2f", wxFundflowDto.getAccountAmount()));
			model.addAttribute("nowDayAmount", String.format("%.2f", wxFundflowDto.getNowDayAmount()));
			model.addAttribute("oldDayAmount", String.format("%.2f", wxFundflowDto.getOldDayAmount()));
			model.addAttribute("nowDayOrderAmount", String.format("%.2f", wxFundflowDto.getNowDayOrderAmount()));
			model.addAttribute("paymentAmountOperation", String.format("%.2f", wxFundflowDto.getPaymentAmountOperation()));
			model.addAttribute("basicToOperationOperation", String.format("%.2f", wxFundflowDto.getBasicToOperationOperation()));
			model.addAttribute("operationToBasicOperation", String.format("%.2f", wxFundflowDto.getOperationToBasicOperation()));
			model.addAttribute("accountAmountOperation", String.format("%.2f", wxFundflowDto.getAccountAmountOperation()));
			model.addAttribute("withdrawalCashOperation", String.format("%.2f", wxFundflowDto.getWithdrawalCashOperation()));
			model.addAttribute("restsIncomeOperation", String.format("%.2f", wxFundflowDto.getRestsIncomeOperation()));
			model.addAttribute("dealSumAmount", Arith.sub(Arith.sub(wxFundflowDto.getDealIncome(), wxFundflowDto.getDealRefund()),
					wxFundflowDto.getDealFee()));
			model.addAttribute("sumAmount",
					Arith.add(Arith.sub(Arith.sub(wxFundflowDto.getDealIncome(), wxFundflowDto.getDealRefund()),
							wxFundflowDto.getDealFee()), wxFundflowDto.getRestsIncome()));
			
			Double daySumAmount = wxBillService.countByDate(dateStr);
			if (daySumAmount != null ) {
				model.addAttribute("daySumAmount", daySumAmount);// 当日实际消费
				model.addAttribute("systemFlag", daySumAmount >= memberDayDto.getRealityAmount() ? "正常" : "异常");// 系统风评
			} else {
				model.addAttribute("daySumAmount", "-----");// 当日实际消费
				model.addAttribute("systemFlag", "-----");// 系统风评
			}
			
			model.addAttribute("showFlag", true);// 是否显示金额
		} else {
			model.addAttribute("confirmFlag", "-----");// 是否对账
			model.addAttribute("systemFlag", "-----");// 系统风评

			model.addAttribute("wxFundflowDto", new WXFundflowDto());// 基本户数据
			model.addAttribute("dealSumAmount", "-----");
			model.addAttribute("sumAmount", "-----");
			model.addAttribute("daySumAmount", "-----");// 当日实际消费
			model.addAttribute("showFlag", false);// 是否显示金额
		}
		
		return "admin/venueDay/list";
	}

	/**
	 * @Description: 场馆统计数据列表
	 * @author 宋高俊
	 * @param adminPage
	 * @return
	 * @date 2018年11月26日下午7:40:35
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage, String dateStr) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		
		List<MemberDay> list = memberDayService.selectByNowDate(dateStr);
		PageInfo<MemberDay> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			MemberDay memberDay = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", memberDay.getId());// ID
			map.put("createTime", DateUtil.getFormat(memberDay.getCreateTime()));// 结算时间
			
			Member member = memberService.selectByPrimaryKey(memberDay.getMemberId());
			if (member == null) {
				continue;
			}
			map.put("appnickname", member.getAppnickname());// 回款用户
			map.put("memberno", member.getMemberno());// 用户编号
			map.put("oughtAmount", String.format("%.2f", memberDay.getRealityAmount()));// 回款应付
			if (memberDay.getTypeFlag() == 1) {
				map.put("realityAmount", String.format("%.2f", memberDay.getRealityAmount()));// 回款实付
			}
			map.put("orderFee", String.format("%.2f", memberDay.getOrderFee()));// 平台费
			map.put("paySubsidy", String.format("%.2f", memberDay.getPaySubsidy()));// 补贴额度
			map.put("memberFee", String.format("%.2f", memberDay.getMemberLimit()));// 剩余额度
			map.put("typeFlag", memberDay.getTypeFlag() == 1 ? "成功"
					: memberDay.getTypeFlag() == 2 ? "失败"
							: memberDay.getTypeFlag() == 3 ? "结束"
									: memberDay.getTypeFlag() == 4 ? "其他" : "");// 转账状态
			if (memberDay.getTypeFlag() == 0) {
				map.put("typeMsg", "");// 状态信息
			} else {
				MemberDayLog memberDayLog = memberDayLogService.selectByNowDate(memberDay.getId());
				if (memberDayLog != null) {
					map.put("typeMsg", memberDayLog.getContent());// 状态信息
				}
			}
			
			map.put("memberDayLogSum", memberDayLogService.countMemberDay(memberDay.getId()));// 日志
			map.put("orderCount", memberDay.getOrderCount());// 子订单
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}
	
	/**
	 * @Description: 日志列表
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年11月30日下午3:35:33
	 */
	@RequestMapping(value = "/log/list")
	@ResponseBody
	public AdminMessage logList(String id) {
		List<MemberDayLog> list = memberDayLogService.selectByMemberDay(id);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			MemberDayLog memberDayLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", memberDayLog.getId());// ID
			map.put("createTime", DateUtil.getFormat(memberDayLog.getCreateTime()));// 记录时间
			Staff staff = staffService.selectByPrimaryKey(memberDayLog.getStaffId());
			map.put("name", staff.getName());// 记录人
			map.put("typeFlag", memberDayLog.getTypeFlag() == 0 ? "自动" : "后台");// 方式
			map.put("payType", memberDayLog.getMemberDayType() == 1 ? "成功"
					: memberDayLog.getMemberDayType() == 2 ? "失败" 
							: memberDayLog.getMemberDayType() == 3 ? "结束" : "其他");// 方式
			map.put("payMsg", memberDayLog.getContent());// 信息
			listMap.add(map);
		}
		return new AdminMessage(0, listMap);
	}

	/**
	 * @Description: 批量转账
	 * @author 宋高俊
	 * @return
	 * @date 2018年11月27日下午4:55:57
	 */
	@RequestMapping(value = "/venuePay")
	@ResponseBody
	public ApiMessage venuePay(HttpServletRequest request, String dateStr) {
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		List<MemberDay> list = memberDayService.selectByNowDate(dateStr);
		for (MemberDay memberDay : list) {
			if (memberDay.getTypeFlag() != 0) {
				continue;
			}

			Member member = memberService.selectByPrimaryKey(memberDay.getMemberId());

			// 未关注公众号则发送通知短信
			if (StringUtil.isEmpty(member.getOpenid())) {
				List<Venue> venues = venueService.selectByMemberAndOrderDate(member.getId(),dateStr);
				if (venues.size() > 1) {
					MoblieMessageUtil.sendTemplateSms6(member.getPhone(), venues.get(0).getName() + "等场馆", dateStr);
				} else {
					MoblieMessageUtil.sendTemplateSms6(member.getPhone(), venues.get(0).getName(), dateStr);
				}
			}

			// 需要调用企业支付，支付订场金额
			WXCompanyPayment wxCompanyPayment = WXPayUtil.wxCompanyPayment(memberDay.getId(), member.getOpenid(), memberDay.getRealityAmount(), "场馆收入");
			if (wxCompanyPayment.getPayType() == 1) {
				// 企业支付成功
				memberDay.setTypeFlag(1);
				// 预定通知消息
				JSONObject datajson = new JSONObject();
				datajson.put("first", JSONObject.parseObject("{\"value\":\"您有提现到账,请注意查收\"}"));
				datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" +  String.format("%.2f", memberDay.getRealityAmount()) + "\"}"));
				datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"" +  DateUtil.getFormat(new Date()) + "\"}"));
				datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"" +  DateUtil.getFormat(new Date()) + "\"}"));
				// 支付成功发送给用户
				datajson.put( "remark",
						JSONObject.parseObject("{\"value\":\"点击查看钱包\"}"));
				logger.info(WXPayUtil.sendWXappTemplate(member.getOpenid(), WXConfig.wxTemplateId2, "/pages/index/index", datajson));

				// 支付成功才修改使用免手续费额度
				// 12月11号上午修改，不再扣手续费额度
//				member.setWxpayment(Arith.sub(member.getWxpayment(), memberDay.getRealityAmount()));
//				memberService.updateByPrimaryKeySelective(member);
				
				//额度明细
//				memberWXPaymentService.saveMemberWXPayment(member.getId(), "机构回款(订场)", -memberDay.getRealityAmount(), member.getWxpayment());
			} else {
				// 企业支付失败
				memberDay.setTypeFlag(2);
			}
			memberDayService.updateByPrimaryKeySelective(memberDay);
			
			// 添加日志
			MemberDayLog memberDayLog = new MemberDayLog();
			memberDayLog.setId(Utils.getUUID());
			memberDayLog.setCreateTime(new Date());
			memberDayLog.setStaffId(staff.getId());
			memberDayLog.setTypeFlag(0);
			memberDayLog.setMemberDayId(memberDay.getId());
			memberDayLog.setMemberDayType(wxCompanyPayment.getPayType());
			memberDayLog.setContent(wxCompanyPayment.getPayMsg());
			memberDayLogService.insertSelective(memberDayLog);
		}
		return new ApiMessage(200,"操作成功");
	}
	
	/**
	 * @Description: 单独转账
	 * @author 宋高俊
	 * @return
	 * @date 2018年11月27日下午4:55:57
	 */
	@RequestMapping(value = "/venuePayOne")
	@ResponseBody
	public ApiMessage venuePayOne(HttpServletRequest request, String venueDayId) {
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		MemberDay memberDay = memberDayService.selectByPrimaryKey(venueDayId);
		
		if (memberDay.getTypeFlag() == 1) {
			return new ApiMessage(400,"该订单已支付");
		}

		Member member = memberService.selectByPrimaryKey(memberDay.getMemberId());

		// 未关注公众号则发送通知短信
		if (StringUtil.isEmpty(member.getOpenid())) {
			List<Venue> venues = venueService.selectByMemberAndOrderDate(member.getId(),
					DateUtil.getFormat(memberDay.getCountDay(), "yyyy-MM-dd"));
			if (venues.size() > 1) {
				MoblieMessageUtil.sendTemplateSms6(member.getPhone(), venues.get(0).getName() + "等场馆",
						DateUtil.getFormat(memberDay.getCountDay(), "yyyy-MM-dd"));
			} else {
				MoblieMessageUtil.sendTemplateSms6(member.getPhone(), venues.get(0).getName(),
						DateUtil.getFormat(memberDay.getCountDay(), "yyyy-MM-dd"));
			}
		}

//		memberDay.setMemberLimit(member.getWxpayment());
//		// 即时判断用户剩余额度
//		if (member.getWxpayment() > memberDay.getRealityAmount()) {
//			memberDay.setPaySubsidy(Arith.halfUp(Arith.mul(memberDay.getRealityAmount(), 0.01), 2));
//			memberDay.setOrderFee(0.0);
//		} else {
//			// 判断用户余额是否大于0
//			if (member.getWxpayment() > 0) {
//				memberDay.setPaySubsidy(Arith.halfUp(Arith.mul(member.getWxpayment(), 0.01), 2));
//				memberDay.setOrderFee(Arith
//						.halfUp(Arith.mul(Arith.sub(memberDay.getRealityAmount(), member.getWxpayment()), 0.01), 2));
//			} else {
//				memberDay.setPaySubsidy(0.0);
//				memberDay.setOrderFee(Arith.halfUp(Arith.mul(memberDay.getRealityAmount(), 0.01), 2));
//			}
//		}
		
		// 需要调用企业支付，支付订场金额
		WXCompanyPayment wxCompanyPayment = WXPayUtil.wxCompanyPayment(memberDay.getId(), member.getOpenid(), memberDay.getRealityAmount(), "场馆收入");
		if (wxCompanyPayment.getPayType() == 1) {
			// 企业支付成功
			memberDay.setTypeFlag(1);

			
			// 预定通知消息
			JSONObject datajson = new JSONObject();
			datajson.put("first", JSONObject.parseObject("{\"value\":\"您有提现到账,请注意查收\"}"));
			datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" +  String.format("%.2f", memberDay.getRealityAmount()) + "\"}"));
			datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"" +  DateUtil.getFormat(new Date()) + "\"}"));
			datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"" +  DateUtil.getFormat(new Date()) + "\"}"));
			
			// 支付成功发送给用户
			datajson.put( "remark",
					JSONObject.parseObject("{\"value\":\"点击查看钱包\"}"));
			logger.info(WXPayUtil.sendWXappTemplate(member.getOpenid(), WXConfig.wxTemplateId2, "/pages/index/index", datajson));

			// 支付成功才修改使用免手续费额度
			// 12月11号上午修改，不再扣手续费额度
//			member.setWxpayment(Arith.sub(member.getWxpayment(), memberDay.getRealityAmount()));
//			memberService.updateByPrimaryKeySelective(member);
			
//			memberWXPaymentService.saveMemberWXPayment(member.getId(), "机构回款(订场)", -memberDay.getRealityAmount(), member.getWxpayment());
		} else {
			// 企业支付失败
			memberDay.setTypeFlag(2);
		}
		memberDayService.updateByPrimaryKeySelective(memberDay);
		
		// 添加日志
		MemberDayLog memberDayLog = new MemberDayLog();
		memberDayLog.setId(Utils.getUUID());
		memberDayLog.setCreateTime(new Date());
		memberDayLog.setStaffId(staff.getId());
		memberDayLog.setTypeFlag(1);
		memberDayLog.setMemberDayId(memberDay.getId());
		memberDayLog.setMemberDayType(wxCompanyPayment.getPayType());
		memberDayLog.setContent(wxCompanyPayment.getPayMsg());
		memberDayLogService.insertSelective(memberDayLog);
		
		return new ApiMessage(200, "操作成功");
	}

	/**
	 * @Description: 审核单页面
	 * @author 宋高俊
	 * @param model
	 * @return
	 * @date 2018年11月27日下午4:47:53
	 */
	@RequestMapping(value = "/check")
	public String check(Model model) {
		return "admin/venueDay/check";
	}
	
	/**
	 * @Description: 保存对账数据
	 * @author 宋高俊
	 * @param model
	 * @return
	 * @date 2018年11月30日下午5:43:45
	 */
	@RequestMapping(value = "/saveVenueDay")
	@ResponseBody
	public ApiMessage saveVenueDay(HttpServletRequest request, Model model, String selectType, String content, String dateStr) {
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");

		// 判断是否有对账
		VenueDayStatis oldVenueDayStatis = venueDayStatisService.selectByNowDate(dateStr);
		if (oldVenueDayStatis != null) {
			return new ApiMessage(400, "不能重复对账");
		}

		Date nowDate = DateUtil.getParse(dateStr, "yyyy-MM-dd");
		String dateStart = DateUtil.getFormat(DateUtil.getWeeHours(nowDate, 0));
		String dateEnd = DateUtil.getFormat(DateUtil.getWeeHours(nowDate, 1));

		// 保存对账数据
		VenueDayStatis venueDayStatis = new VenueDayStatis();
		venueDayStatis.setId(Utils.getUUID());
		venueDayStatis.setCreateTime(new Date());
		venueDayStatis.setNowDate(nowDate);

		MemberDayDto memberDayDto = memberDayService.countMemberDay(dateStr);
		venueDayStatis.setOughtAmount(memberDayDto.getOughtAmount());// 应付金额
		venueDayStatis.setRealityAmount(memberDayDto.getRealityAmount());// 实付金额
		venueDayStatis.setPaySubsidy(memberDayDto.getPaySubsidy());// 额度补贴
		venueDayStatis.setProfitAmount(Arith.sub(memberDayDto.getOughtAmount(), memberDayDto.getRealityAmount()));// 当日盈亏
		venueDayStatis.setSystemFlag(
				Arith.sub(memberDayDto.getOughtAmount(), memberDayDto.getRealityAmount()) >= 0 ? "正常" : "异常");// 系统风评
		venueDayStatis.setUserFlag(selectType);// 人工风评

		// 资金账单统计数据基本户
		WXFundflowDto wxFundflowDto = wxFundflowService.countWXFundflowBasic(dateStart, dateEnd);
		venueDayStatis.setDealIncome(wxFundflowDto.getDealIncome());
		venueDayStatis.setDealRefund(wxFundflowDto.getDealRefund());
		venueDayStatis.setDealFee(wxFundflowDto.getDealFee());
		venueDayStatis.setWithdrawalCash(wxFundflowDto.getWithdrawalCash());
		venueDayStatis.setBasicToOperation(wxFundflowDto.getBasicToOperation());
		venueDayStatis.setOperationToBasic(wxFundflowDto.getOperationToBasic());
		venueDayStatis.setRestsIncome(wxFundflowDto.getRestsIncome());
		venueDayStatis.setAccountAmount(wxFundflowDto.getAccountAmount());
		venueDayStatis.setNowDayAmount(wxFundflowDto.getNowDayAmount());
		venueDayStatis.setOldDayAmount(wxFundflowDto.getOldDayAmount());
		venueDayStatis.setNowDayOrderAmount(wxFundflowDto.getNowDayOrderAmount());
		venueDayStatis.setPaymentAmountOperation(wxFundflowDto.getPaymentAmountOperation());
		venueDayStatis.setBasicToOperationOperation(wxFundflowDto.getBasicToOperationOperation());
		venueDayStatis.setOperationToBasicOperation(wxFundflowDto.getOperationToBasicOperation());
		venueDayStatis.setAccountAmountOperation(wxFundflowDto.getAccountAmountOperation());
		venueDayStatisService.insertSelective(venueDayStatis);

		VenueDayStatisLog venueDayStatisLog = new VenueDayStatisLog();
		venueDayStatisLog.setId(Utils.getUUID());
		venueDayStatisLog.setCreateTime(new Date());
		venueDayStatisLog.setContent(content);
		venueDayStatisLog.setStaffId(staff.getId());
		venueDayStatisLog.setVenueDayStatisId(venueDayStatis.getId());
		venueDayStatisLogService.insertSelective(venueDayStatisLog);
		return new ApiMessage(200, "对账完成");
	}
	
	
	/**
	 * @Description: 当日订场数据
	 * @author 宋高俊
	 * @param adminPage
	 * @param dateStr
	 * @return
	 * @date 2018年12月4日上午9:23:16
	 */
	@RequestMapping(value = "/orderDate/list")
	@ResponseBody
	public AdminMessage orderDateList(AdminPage adminPage, String dateStr) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		
		List<WXBillDto> list = wxBillService.selectByNowDate(dateStr);
		PageInfo<WXBillDto> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			WXBillDto wxBillDto = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("wxOrderid", wxBillDto.getWxOrderid());// 微信订单ID
			map.put("soreOrderid", wxBillDto.getSoreOrderid());// 商户订单号
			map.put("orderAmount", String.format("%.2f", wxBillDto.getOrderAmount()));// 订单金额
			map.put("refundAmount", String.format("%.2f", wxBillDto.getRefundAmount()));// 退款金额
			double amount = Arith.sub(wxBillDto.getOrderAmount(), wxBillDto.getRefundAmount());
			map.put("amount", String.format("%.2f", amount));// 实际金额

			if (amount > 0) {
				map.put("payAmount", String.format("%.2f", Arith.sub(amount, wxBillDto.getOrderFee())));// 回款金额
			} else {
				map.put("payAmount", String.format("%.2f", 0.0));// 回款金额
			}
			map.put("orderFee", String.format("%.2f", wxBillDto.getOrderFee()));// 平台费
			map.put("orderType", "订场");// 订单类型
			map.put("memberNo", wxBillDto.getMemberNo());// 用户编号
			map.put("payTime", DateUtil.getFormat(wxBillDto.getPayTime()));// 支付时间
			map.put("orderDate", DateUtil.getFormat(wxBillDto.getOrderDate(), "yyyy-MM-dd"));// 订场日期
			map.put("venueNo", wxBillDto.getVenueNo());// 场馆编号
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}
	
	/**
	 * @Description: 修改账单状态
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年12月12日下午8:14:32
	 */
	@RequestMapping(value = "/updateMemeberDayType")
	@ResponseBody
	public ApiMessage updateMemeberDayType(HttpServletRequest request, String content, Integer selectType, String memberDayId) {
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		
		MemberDay memberDay = memberDayService.selectByPrimaryKey(memberDayId);
		if (memberDay != null) {
			memberDay.setTypeFlag(selectType);
			memberDay.setModifyTime(new Date());
			memberDayService.updateByPrimaryKeySelective(memberDay);
			
			// 添加日志
			MemberDayLog memberDayLog = new MemberDayLog();
			memberDayLog.setId(Utils.getUUID());
			memberDayLog.setCreateTime(new Date());
			memberDayLog.setStaffId(staff.getId());
			memberDayLog.setTypeFlag(1);
			memberDayLog.setMemberDayId(memberDay.getId());
			memberDayLog.setMemberDayType(selectType);
			memberDayLog.setContent(content);
			
			memberDayLogService.insertSelective(memberDayLog);
			return new ApiMessage(200, "修改成功");
		} else {
			return new ApiMessage(400, "该账单不存在");
		}
	}
	
	
	/**
	 * @Description: 查询子订单
	 * @author 宋高俊
	 * @param amountid
	 * @return
	 * @date 2018年12月14日下午3:57:16
	 */
	@RequestMapping(value = "/sonOrder/list")
	@ResponseBody
	public AdminMessage sonOrderList(String amountid) {
		List<Order> list = orderService.selectByAmount(amountid);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Order order = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderNo", order.getOrderno());// 订单编号
			map.put("orderType", "订场");// 类别
			map.put("oughtAmount", String.format("%.2f", order.getShowPrice()));// 订单应付
			map.put("realityAmount", String.format("%.2f", order.getPrice()));// 订单实付
			map.put("priceFee", String.format("%.2f", order.getPriceFee()));// 平台费
			
			if (order.getAmounttype() == 1) {
				double orderPrice = Arith.sub(order.getPrice(), order.getPriceFee());
				MemberDay memberDay = memberDayService.selectByPrimaryKey(order.getAmountid());
				if(memberDay != null && memberDay.getTypeFlag() == 1) {
					if (order.getType() == 1) {
						map.put("type", "已回款");// 已回款状态
						map.put("venuePayAmount", String.format("%.2f", orderPrice));
					} else if(order.getType() == 4){
						map.put("type", "已退款");// 订单状态
						AmountRefund amountRefund = amountRefundService.selectByNowSourceId(order.getId());
						if (amountRefund != null && orderPrice > amountRefund.getAmount()) {
							map.put("venuePayAmount", String.format("%.2f", Arith.sub(orderPrice, amountRefund.getAmount())));
						}
					}
				}
			}

			AmountRefund amountRefund = amountRefundService.selectByNowSourceId(order.getId());
			if (amountRefund != null) {
				map.put("amountRefund", String.format("%.2f", amountRefund.getAmount()));// 退款金额
			}else{
				map.put("amountRefund", String.format("%.2f", 0.0));// 退款金额
			}
			
			String content = "";
			Venue venue = venueService.selectByPrimaryKey(order.getVenueid());
			if (venue != null) {
				content += venue.getName() + ",";
			}
			List<Reserve> listReserve = reserveService.selectByOrder(order.getId());
			String time = ""; 
			if (listReserve.size() > 0) {
				time = com.xiaoyi.ssm.util.StringUtil.timeToTimestr(listReserve.get(0).getReservetimeframe().split(","));
			}
			content += time;
			
			if (order.getCoachid() != null) {
				VenueCoach venueCoach = venueCoachService.selectByPrimaryKey(order.getCoachid());
				TrainCoach trainCoach = trainCoachService.selectByTrainCoachId(venueCoach.getTrainCoachId());
				content += ",陪练" +trainCoach.getName();
			}
			map.put("content", content);// 内容
			listMap.add(map);
		}
		return new AdminMessage(100, list.size(), listMap);
	}
	
	
	/**
	 * @Description: 导出excel数据
	 * @author 宋高俊
	 * @param amountid
	 * @return
	 * @date 2018年12月14日下午3:57:16
	 */
	@RequestMapping(value = "/orderDate/excel")
	@ResponseBody
	public ApiMessage orderDateExcel(String dateStr) {
		
		List<WXBillDto> list = wxBillService.selectByNowDate(dateStr);
		PageInfo<WXBillDto> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			WXBillDto wxBillDto = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("wxOrderid", wxBillDto.getWxOrderid());// 微信订单ID
			map.put("soreOrderid", wxBillDto.getSoreOrderid());// 商户订单号
			map.put("orderAmount", String.format("%.2f", wxBillDto.getOrderAmount()));// 订单金额
			map.put("refundAmount", String.format("%.2f", wxBillDto.getRefundAmount()));// 退款金额
			double amount = Arith.sub(wxBillDto.getOrderAmount(), wxBillDto.getRefundAmount());
			map.put("amount", String.format("%.2f", amount));// 实际金额

			if (amount > 0) {
				map.put("payAmount", String.format("%.2f", Arith.sub(amount, wxBillDto.getOrderFee())));// 回款金额
			} else {
				map.put("payAmount", String.format("%.2f", 0.0));// 回款金额
			}
			map.put("orderFee", String.format("%.2f", wxBillDto.getOrderFee()));// 平台费
			map.put("orderType", "订场");// 订单类型
			map.put("memberNo", wxBillDto.getMemberNo());// 用户编号
			map.put("payTime", DateUtil.getFormat(wxBillDto.getPayTime()));// 支付时间
			map.put("orderDate", DateUtil.getFormat(wxBillDto.getOrderDate(), "yyyy-MM-dd"));// 订场日期
			map.put("venueNo", wxBillDto.getVenueNo());// 场馆编号
			listMap.add(map);
		}
		return new ApiMessage(200, "", listMap);
	}
}
