package com.xiaoyi.ssm.controller.admin;

import java.util.ArrayList;
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
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.model.MassEmpPay;
import com.xiaoyi.ssm.service.MassEmpPayService;
import com.xiaoyi.ssm.util.DateUtil;

/**
 * @Description: 订单接口控制器
 * @author 宋高俊
 * @date 2018年7月26日 下午4:17:14
 */
@Controller(value = "adminOrderController")
@RequestMapping("admin/order")
public class OrderController {

	@Autowired
	private MassEmpPayService massEmpPayService;

	/**
	 * @Description: 订单列表页面
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/listview")
	public String list() {
		return "admin/order/list";
	}

	/**
	 * @Description: 订单数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/list")
	@ResponseBody
	public AdminMessage list(Model model, AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<MassEmpPay> list = massEmpPayService.selectByAll(null);
		PageInfo<MassEmpPay> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			MassEmpPay massEmpPay = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", massEmpPay.getId());
			map.put("paytime", DateUtil.getFormat(massEmpPay.getPaytime()));// 订单时间
			map.put("type", massEmpPay.getType());// 购买会员
			map.put("tel", massEmpPay.getEmployee().getTel());// 充值账号
			map.put("buyduration", massEmpPay.getBuyduration());// 购买时间
			map.put("endtime", DateUtil.getFormat(massEmpPay.getEndtime(), "yyyy-MM-dd"));// 预计到期时间
			map.put("paytype", massEmpPay.getPaytype() == 0 ? "微信" : "支付宝");// 支付方式
			map.put("payset", massEmpPay.getPayset());// 订单金额
			map.put("payact", massEmpPay.getPayact());// 需付金额
			map.put("emp", massEmpPay.getEmployee().getEmp());// 下单用户
			map.put("payresult", massEmpPay.getPayresult() == 0 ? "待支付"
					: massEmpPay.getPayresult() == 1 ? "支付成功" : massEmpPay.getPayresult() == 2 ? "订单取消" : "订单超时");// 支付状态
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

}
