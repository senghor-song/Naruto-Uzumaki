package com.xiaoyi.ssm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.WXBill;
import com.xiaoyi.ssm.service.WXBillService;
import com.xiaoyi.ssm.service.WXFundflowService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.wxPay.WXPayUtil;

/**
 * @Description: 场馆统计数据
 * @author 宋高俊
 * @date 2018年9月20日 下午5:03:09
 */
@Controller(value = "adminwxBillController")
@RequestMapping(value = "/admin/wxBill")
public class WXBillController {

	private static Logger logger = Logger.getLogger(WXBillController.class.getName());
	
	@Autowired
	private WXBillService wxBillService;
	@Autowired
	private WXFundflowService wxFundflowService;
	
	/**
	 * @Description: 获取对账单数据以及资金账单
	 * @author 宋高俊
	 * @param date
	 * @return
	 * @throws IOException
	 * @date 2018年12月7日上午10:50:09
	 */
	@RequestMapping(value = "/getBillData")
	@ResponseBody
	public ApiMessage getBillData(String dateStr, Boolean deleteFlag) throws IOException {
		if (deleteFlag == null) {
			deleteFlag = true;
		}
		Date nowDate = new Date();
		// 判断是否有传入日期
		if (StringUtil.isBank(dateStr)) {
			// 没有日期则默认为昨天
			nowDate = DateUtil.getPreTime(new Date(), 3, -1);
		} else {
			// 有传入日期则使用传入日期
			nowDate = DateUtil.getParse(dateStr, "yyyy-MM-dd");
		}
		
		// 格式化请求参数
		String nowDateStr = DateUtil.getFormat(nowDate, "yyyyMMdd");
		String dateStart = DateUtil.getFormat(DateUtil.getWeeHours(nowDate, 0),
				"yyyy-MM-dd HH:mm:ss");
		String dateEnd = DateUtil.getFormat(DateUtil.getWeeHours(nowDate, 1),
				"yyyy-MM-dd HH:mm:ss");
		
		Integer countBill = wxBillService.countByDateFlag(dateStart,dateEnd);
		Integer countFundflow = wxFundflowService.countByDateFlag(dateStart,dateEnd);

		if (deleteFlag && (countBill + countFundflow) > 0) {
			return new ApiMessage(400, "已有数据");
		}
		
		wxBillService.deleteByDate(dateStart,dateEnd);
		wxFundflowService.deleteByDate(dateStart,dateEnd);
		logger.info("开始执行昨天的账单查询");
		WXPayUtil.downloadfundflow(nowDateStr, "Basic");
		WXPayUtil.downloadfundflow(nowDateStr, "Operation");
		WXPayUtil.downloadbill(nowDateStr);
		logger.info("结束执行昨天的账单查询");
		return new ApiMessage(200);
	}

	/**
	 * @Description: 场馆统计数据页面
	 * @author 宋高俊
	 * @return
	 * @date 2018年11月26日下午7:37:13
	 */
	@RequestMapping(value = "/listview")
	public String listview(Model model) {
		return "admin/wxBill/list";
	}

	/**
	 * @Description: 对账单数据
	 * @author 宋高俊
	 * @param adminPage
	 * @return
	 * @date 2018年11月29日下午2:32:43
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<WXBill> list = wxBillService.selectByAll(null);
		PageInfo<WXBill> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			WXBill wxBill = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", wxBill.getId());// ID
			map.put("createtime", DateUtil.getFormat(wxBill.getCreateTime()));// 
			map.put("dealtime", DateUtil.getFormat(wxBill.getDealTime()));// 
			map.put("wxAppid", wxBill.getWxAppid());// 
			map.put("wxMchid", wxBill.getWxMchid());// 
			map.put("sonMchid", wxBill.getSonMchid());// 
			map.put("deviceInfo", wxBill.getDeviceInfo());// 
			map.put("wxOrderid", wxBill.getWxOrderid());// 
			map.put("storeOrderid", wxBill.getStoreOrderid());// 
			map.put("userFlag", wxBill.getUserFlag());// 
			map.put("payType", wxBill.getPayType());// 
			map.put("payStatus", wxBill.getPayStatus());// 
			map.put("paymentBank", wxBill.getPaymentBank());// 
			map.put("currencyType", wxBill.getCurrencyType());// 
			map.put("oughtOrderAmount", wxBill.getOughtOrderAmount());// 
			map.put("cashCoupon", wxBill.getCashCoupon());// 
			map.put("wxRefundOrderid", wxBill.getWxRefundOrderid());// 
			map.put("storeRefundOrderid", wxBill.getStoreRefundOrderid());// 
			map.put("refundAmount", wxBill.getRefundAmount());// 
			map.put("cashCouponRefund", wxBill.getCashCouponRefund());// 
			map.put("refundType", wxBill.getRefundType());// 
			map.put("refundStatus", wxBill.getRefundStatus());// 
			map.put("goodsName", wxBill.getGoodsName());// 
			map.put("storeData", wxBill.getStoreData());// 
			map.put("feeAmount", wxBill.getFeeAmount());// 
			map.put("rate", wxBill.getRate());// 
			map.put("orderAmount", wxBill.getOrderAmount());// 
			map.put("applyRefundAmount", wxBill.getApplyRefundAmount());// 
			map.put("rateRemark", wxBill.getRateRemark());// 
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}
	
}
