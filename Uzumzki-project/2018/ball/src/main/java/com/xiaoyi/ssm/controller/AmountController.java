package com.xiaoyi.ssm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dao.AmountLogMapper;
import com.xiaoyi.ssm.dao.TrainJoinMapper;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.model.Amount;
import com.xiaoyi.ssm.model.AmountLog;
import com.xiaoyi.ssm.model.Combine;
import com.xiaoyi.ssm.model.CombineJoin;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.Reserve;
import com.xiaoyi.ssm.model.TrainJoin;
import com.xiaoyi.ssm.service.AmountService;
import com.xiaoyi.ssm.service.CombineService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.StringUtil;

/**
 * @Description: 提现申请控制器
 * @author 宋高俊
 * @date 2018年8月18日 上午11:35:49
 */
@Controller(value = "adminAmountController")
@RequestMapping(value = "/admin/amount")
public class AmountController {

	@Autowired
	private AmountService amountService;
	@Autowired
	private AmountLogMapper amountLogMapper;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CombineService combineService;
	@Autowired
	private TrainJoinMapper trainJoinMapper;

	/**
	 * @Description: 提现申请页面
	 * @author song
	 * @date 2018年8月14日 下午7:02:41
	 */
	@RequestMapping(value = "/listview")
	public String listview() {
		return "admin/amount/list";
	}

	/**
	 * @Description: 提现申请数据
	 * @author song
	 * @date 2018年8月14日 下午7:04:09
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Amount> amounts = amountService.selectByAll(null);
		PageInfo<Amount> pageInfo = new PageInfo<>(amounts);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < amounts.size(); i++) {
			Amount amount = amounts.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", amount.getId());// ID
			map.put("createtime", DateUtil.getFormat(amount.getCreatetime()));// 申请时间
			map.put("amountno", amount.getAmountno());// 编号
			map.put("city", amount.getCity().getCity());// 城市
			map.put("venue", amount.getVenue().getName());// 场馆
			map.put("manager", amount.getManager().getName());// 申请人
			map.put("managertype", amount.getManager().getType() == 0 ? "超级管理员" : "管理员");// 身份
			map.put("amount", amount.getAmount());// 提现金额
			map.put("status", amount.getStatus() == 0 ? "等待审核" : amount.getStatus() == 1 ? "审核通过" : "审核拒绝");// 状态
			map.put("amountlog", amount.getId());// 日志
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 提现申请日志数据
	 * @author 宋高俊
	 * @date 2018年8月20日 下午5:00:12
	 */
	@RequestMapping(value = "/amountloglist")
	@ResponseBody
	public AdminMessage amountloglist(String id) {
		List<AmountLog> list = amountLogMapper.selectByAmount(id);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			AmountLog amountLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", amountLog.getId());// ID
			map.put("createtime", DateUtil.getFormat(amountLog.getCreatetime()));// 时间
			map.put("staff", amountLog.getStaff().getRname());// 操作人
			map.put("type", amountLog.getType());// 类别
			map.put("content", amountLog.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(100, list.size(), listMap);
	}

	/**
	 * @Description: 提现打印内容
	 * @author 宋高俊
	 * @date 2018年8月20日 下午3:34:33
	 */
	@RequestMapping(value = "/check")
	public String check(Model model, String amountid) {
		Amount amount = amountService.selectByPrimaryKey(amountid);

		Map<String, Object> map = new HashMap<>();
		map.put("amount", amount.getAmount() * 0.98);// 转账金额
		map.put("createTime", DateUtil.getFormat(amount.getCreatetime()));// 申请时间
		map.put("city", amount.getCity().getCity());// 城市
		map.put("venue", amount.getVenue().getName());// 场馆
		map.put("managerType",
				amount.getManager().getName() + " " + (amount.getManager().getType() == 0 ? "超级管理员" : "管理员"));// 申请提现
		map.put("realname", amount.getManager().getRealname() == 0 ? "否" : "是");// 实名认证
		map.put("amountSum", amount.getAmount());// 提现金额
		map.put("amountRate", "2%");// 手续费率
		map.put("amountFee", amount.getAmount() * 0.02);// 手续费
		map.put("amountbank", amount.getVenue().getAmountbank());// 回款银行
		map.put("amountaccount", amount.getVenue().getAmountaccount());// 回款账户
		map.put("amountaccountnumber", amount.getVenue().getAmountaccountnumber());// 回款账号

		List<Map<String, Object>> orderList = new ArrayList<>();
		List<Order> orders = orderService.selectByAmount(amountid);
		double orderAmount = 0.0;// 订单总金额
		for (Order order : orders) {
			for (Reserve reserve : order.getReserves()) {
				Map<String, Object> orderMap = new HashMap<>();
				orderMap.put("orderno", order.getOrderno());// 订单编号
				orderMap.put("modifytime", DateUtil.getFormat(order.getModifytime()));// 支付时间
				orderMap.put("member", order.getMember().getName());// 会员
				orderMap.put("orderdate", DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd"));// 订场日期
				if (order.getPaytype() != null) {
					orderMap.put("paytype", order.getPaytype() == 0 ? "支付宝" : "微信");// 支付方式
				} else {
					orderMap.put("paytype", "未知");// 支付方式
				}
				orderMap.put("field", reserve.getField().getName());// 场地
				orderMap.put("times", StringUtil.timeToTimestr(reserve.getReservetimeframe().split(",")));// 事件
				orderMap.put("price", order.getPrice());// 金额
				orderList.add(orderMap);
				orderAmount += order.getPrice();
			}
		}
		map.put("orderList", orderList);// 订场列表
		map.put("orderAmount", orderAmount);// 订场总金额

		List<Map<String, Object>> combineList = new ArrayList<>();
		List<Combine> combines = combineService.selectByAmount(amountid);
		double combineAmount = 0.0;// 散拼总金额
		for (Combine combine : combines) {
			for (CombineJoin combineJoin : combine.getCombineJoins()) {
				Map<String, Object> combineMap = new HashMap<>();
				combineMap.put("combineno", combine.getCombineno());// 散拼编号
				combineMap.put("createtimetime", DateUtil.getFormat(combineJoin.getCreatetimetime()));// 支付时间
				combineMap.put("member", combineJoin.getMember().getName());// 会员
				combineMap.put("combinedate", DateUtil.getFormat(combine.getCombinedate(), "yyyy-MM-dd"));// 订场日期
				combineMap.put("fieldName", combine.getField().getName());// 场地
				combineMap.put("times", StringUtil.timeToTimestr(combine.getCombinetimeframe().split(",")));// 事件
				if (combineJoin.getPaytype() != null) {
					combineMap.put("paytype", combineJoin.getPaytype() == 0 ? "支付宝" : "微信");// 支付方式
				} else {
					combineMap.put("paytype", "未知");// 支付方式
				}
				combineMap.put("price", combineJoin.getPrice());// 金额
				combineList.add(combineMap);
				combineAmount += combineJoin.getPrice();
			}
		}

		map.put("combineList", combineList);// 散拼列表
		map.put("combineAmount", combineAmount);// 散拼总金额

		List<Map<String, Object>> trainList = new ArrayList<>();
		double trainAmount = 0.0;// 培训总金额
		List<TrainJoin> trainJoins = trainJoinMapper.selectByAmount(amountid);
		for (TrainJoin trainJoin : trainJoins) {
			Map<String, Object> trainMap = new HashMap<>();
			trainMap.put("trainno", trainJoin.getTrain().getTrainno());// 培训编号
			trainMap.put("createtime", DateUtil.getFormat(trainJoin.getCreatetime()));// 支付时间
			trainMap.put("member", trainJoin.getMember().getName());// 会员
			trainMap.put("content", trainJoin.getContent());// 课程时间
			if (trainJoin.getPaytype() != null) {
				trainMap.put("paytype", trainJoin.getPaytype() == 0 ? "支付宝" : "微信");// 支付方式
			} else {
				trainMap.put("paytype", trainJoin.getPaytype());// 支付方式
			}
			trainMap.put("price", trainJoin.getPrice());// 金额
			trainList.add(trainMap);
			trainAmount += trainJoin.getPrice();
		}
		
		
		map.put("trainList", trainList);// 培训列表
		map.put("trainAmount", trainAmount);// 培训总金额

		List<Map<String, Object>> amountLogList = new ArrayList<>();
		List<AmountLog> amountLogs = amountLogMapper.selectByAmount(amountid);
		for (AmountLog amountLog : amountLogs) {
			Map<String, Object> amountLogMap = new HashMap<>();
			amountLogMap.put("createtime", DateUtil.getFormat(amountLog.getCreatetime()));// 日志时间
			amountLogMap.put("content", amountLog.getContent());// 内容
			amountLogList.add(amountLogMap);
		}
		map.put("amountLogList", amountLogList);// 提现日志列表

		List<Map<String, Object>> historyLogList = new ArrayList<>();
		List<Amount> amounts = amountService.selectByVenue(amount.getVenueid());
		for (Amount historyAmount : amounts) {
			Map<String, Object> amountMap = new HashMap<>();
			amountMap.put("createtime", DateUtil.getFormat(historyAmount.getCreatetime(), "yyyy-MM-dd"));// 日期
			amountMap.put("name", historyAmount.getManager().getName());// 管理员名称
			amountMap.put("type", historyAmount.getManager().getType() == 0 ? "超级管理员" : "管理员" );// 身份
			amountMap.put("amount", historyAmount.getAmount());// 金额
			historyLogList.add(amountMap);
		}
		
		map.put("historyLogList", historyLogList);// 历史列表

		map.put("printDate", DateUtil.getFormat(new Date())); // 打印时间
		model.addAttribute("amount", map);
		return "/admin/amount/check";
	}
}
