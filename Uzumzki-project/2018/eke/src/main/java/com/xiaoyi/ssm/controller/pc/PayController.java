package com.xiaoyi.ssm.controller.pc;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.model.MassEmpPay;
import com.xiaoyi.ssm.service.EmployeeService;
import com.xiaoyi.ssm.service.MassEmpPayService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.GetWeixinUrlUtil;
import com.xiaoyi.ssm.wxPay.PayCommonUtil;
import com.xiaoyi.ssm.wxPay.WeiXinPayConfigUtil;
import com.xiaoyi.ssm.wxPay.XMLUtil;
import com.xiaoyi.ssm.zfhPay.AlipayConfig;

/**
 * @Description: 支付接口回调控制器
 * @author 宋高俊
 * @date 2018年7月6日 下午12:13:05
 */
@Controller
@RequestMapping("/pay")
public class PayController {

	/** 套餐价格数组 */
//	private Double[] price = { 960.00, 580.00, 0.00, 680.00, 400.00, 240.00 };

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MassEmpPayService massEmpPayService;
	@Autowired
	private EmployeeService employeeService;

	/**
	 * @Description: 续费页面
	 * @author 宋高俊
	 * @date 2018年7月6日 下午7:05:00
	 */
	@RequestMapping("/payOrder")
	public String payOrder(Model model ,PageInfo pageInfo, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		PageHelper.startPage(pageInfo.getPageNum(), 5);
		List<MassEmpPay> list = massEmpPayService.selectAll(employee.getId());
		pageInfo = new PageInfo<>(list);
		model.addAttribute("page", pageInfo);
		return "pay/payOrder";
	}
	
	/**  
	 * @Description: 取消订单
	 * @author 宋高俊  
	 * @date 2018年7月7日 下午2:22:44 
	 */ 
	@RequestMapping("/delOrder")
	@ResponseBody
	public ApiMessage delOrder(String orderId) {
		MassEmpPay massEmpPay = new MassEmpPay();
		massEmpPay.setId(orderId);
		massEmpPay.setPayresult(2);
		int flag = massEmpPayService.updateByPrimaryKeySelective(massEmpPay);
		if (flag == 1) {
			return ApiMessage.succeed();
		}else {
			return ApiMessage.error();
		}
	}
	
	/**
	 * @Description: 订单流水页面
	 * @author 宋高俊
	 * @date 2018年7月6日 下午7:05:00
	 */
	@RequestMapping("/payRenew")
	public String payRenew() {
		return "pay/pay";
	}
	
	/**
	 * @Description: 支付确认页面
	 * @author 宋高俊
	 * @date 2018年7月6日 下午7:05:00
	 */
	@RequestMapping("/payVerify")
	public String payVerify(Model model, MassEmpPay massEmpPay, HttpServletRequest request, Integer payType) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		//当前用户是否为新用户
		if (employee.getMassvalidity() == null) {
			Date date = new Date();
			String massvalidity = DateUtil.getFormat(DateUtil.getPreTime(date, 4, payType == 0 || payType == 3 ? 14 : payType == 1 || payType == 4 ? 7 : payType == 5 ? 3 : 0),
					"yyyy-MM-dd");
			model.addAttribute("massvalidity", DateUtil.getFormat(date, "yyyy-MM-dd") + "至" + massvalidity);
		}else {
			String massvalidity = DateUtil.getFormat(DateUtil.getPreTime(employee.getMassvalidity(), 4, payType == 0 || payType == 3 ? 14 : payType == 1 || payType == 4 ? 7 : payType == 5 ? 3 : 0),
					"yyyy-MM-dd");
			model.addAttribute("massvalidity", DateUtil.getFormat(employee.getMassvalidity(), "yyyy-MM-dd") + "至" + massvalidity);
		}
			
		model.addAttribute("massType", payType == 0 || payType == 1 || payType == 2 ? "视频看房版" : "青英版");
		String[] price = massEmpPayService.selectByPay().split(",");
		model.addAttribute("price", price[payType]);
		model.addAttribute("payType", payType);
		
		model.addAttribute("uuid", Utils.getUUID());
		return "pay/payVerify";
	}

	/**
	 * @Description: 新增支付订单
	 * @author 宋高俊
	 * @date 2018年7月6日 下午7:05:00
	 */
	@RequestMapping("/addPayOrder")
	public String addPayOrder(Model model, MassEmpPay massEmpPay, HttpServletRequest request, Integer payType, Integer paymentType) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		massEmpPay.setId(Utils.getUUID());
		massEmpPay.setPayresult(0);
		massEmpPay.setEmpid(employee.getId());
		massEmpPay.setPaytype(paymentType);
		massEmpPayService.insertSelective(massEmpPay);
		return "pay/payWXVerify";
	}

	/**
	 * @Description: 微信支付页面
	 * @author 宋高俊
	 * @date 2018年7月6日 下午7:05:00
	 */
	@RequestMapping("/payWXVerify")
	public String payWXVerify(Model model,HttpServletRequest request, Integer payType, Integer paymentType) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		MassEmpPay massEmpPay = new MassEmpPay();
		String type = payType == 0 || payType == 1 || payType == 2 ? "视频看房版" : "青英版";
		String buyduration = payType == 0 || payType == 3 ? "年付" : payType == 1 || payType == 4 ? "半年付" : "季付";
		String uuid = Utils.getUUID();
		massEmpPay.setId(uuid);
		massEmpPay.setEmpid(employee.getId());
		massEmpPay.setType(type);
		massEmpPay.setBuyduration(buyduration);
		massEmpPay.setPaytime(new Date());
		if (employee.getMassvalidity() == null) {
			massEmpPay.setEndtime(DateUtil.getPreTime(new Date(), 4, payType == 0 || payType == 3 ? 14 : payType == 1 || payType == 4 ? 7 : payType == 5 ? 3 : 0));
		}else{
			massEmpPay.setEndtime(DateUtil.getPreTime(employee.getMassvalidity(), 4, payType == 0 || payType == 3 ? 14 : payType == 1 || payType == 4 ? 7 : payType == 5 ? 3 : 0));
		}
		massEmpPay.setPaytype(0);
		String[] price = massEmpPayService.selectByPay().split(",");
		massEmpPay.setPayset(Double.valueOf(price[payType]));
		massEmpPay.setPayact(Double.valueOf(price[payType]));
		massEmpPay.setPayresult(0);

		massEmpPayService.insertSelective(massEmpPay);
		try {
			model.addAttribute("QRCode", GetWeixinUrlUtil.weixin_pay(uuid, Double.valueOf(price[payType])));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pay/payWXVerify";
	}

	/**
	 * @Description: 计算价格
	 * @author 宋高俊
	 * @date 2018年7月6日 下午7:05:00
	 */
	@RequestMapping("/getUpgradeMoney")
	@ResponseBody
	public ApiMessage getUpgradeMoney(Model model, MassEmpPay massEmpPay, HttpServletRequest request, Integer payType) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isChange", false);
		map.put("changePrice", 0);
		String[] price = massEmpPayService.selectByPay().split(",");
		map.put("price", price[payType]);
		if (employee.getMassvalidity() == null) {
			String massvalidity = DateUtil.getFormat(DateUtil.getPreTime(new Date(), 4, payType == 0 || payType == 3 ? 14 : payType == 1 || payType == 4 ? 7 : payType == 5 ? 3 : 0),
					"yyyy-MM-dd");
			map.put("massvalidity", DateUtil.getFormat(new Date(), "yyyy-MM-dd") + "至" + massvalidity);
		}else {
			map.put("changeDate", DateUtil.getFormat(new Date(), "yyyy-MM-dd") + "至" + DateUtil.getFormat(employee.getMassvalidity(), "yyyy-MM-dd"));
			String massvalidity = DateUtil.getFormat(DateUtil.getPreTime(employee.getMassvalidity(), 4, payType == 0 || payType == 3 ? 14 : payType == 1 || payType == 4 ? 7 : payType == 5 ? 3 : 0),
					"yyyy-MM-dd");
			map.put("massvalidity", DateUtil.getFormat(employee.getMassvalidity(), "yyyy-MM-dd") + "至" + massvalidity);
		}
		map.put("massType", employee.getMasstype());
		map.put("changeMassType", payType == 0 || payType == 1 || payType == 2 ? "视频看房版" : "青英版");
		return ApiMessage.succeed(map);
	}

	/**
	 * @Description: 微信异步回调
	 * @author 宋高俊
	 * @date 2018年7月6日 下午12:18:16
	 */
	@RequestMapping("/WeiXinPay")
	public void weixin_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 读取参数
		InputStream inputStream;
		StringBuffer sb = new StringBuffer();
		inputStream = request.getInputStream();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while ((s = in.readLine()) != null) {
			sb.append(s);
		}
		in.close();
		inputStream.close();

		// 解析xml成map
		Map<String, String> m = new HashMap<String, String>();
		m = XMLUtil.doXMLParse(sb.toString());

		// 过滤空 设置 TreeMap
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		Iterator it = m.keySet().iterator();
		while (it.hasNext()) {
			String parameter = (String) it.next();
			String parameterValue = m.get(parameter);

			String v = "";
			if (null != parameterValue) {
				v = parameterValue.trim();
			}
			packageParams.put(parameter, v);
		}

		// 账号信息
		String key = WeiXinPayConfigUtil.API_KEY; // key
		logger.info(packageParams.toString());
		// 判断签名是否正确
		if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
			// ------------------------------
			// 处理业务开始
			// ------------------------------
			String resXml = "";
			if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
				// 这里是支付成功
				// ////////执行自己的业务逻辑////////////////

				String out_trade_no = (String) packageParams.get("out_trade_no");
				
				// 支付宝支付成功逻辑
				MassEmpPay massEmpPay = massEmpPayService.selectByPrimaryKey(out_trade_no);
				// 判断订单是否存在
				if (massEmpPay != null && massEmpPay.getPayresult() != 1) {
					// 获取该订单的用户
					Employee employee = employeeService.selectByPrimaryKey(massEmpPay.getEmpid());
					employee.setMassvalidity(massEmpPay.getEndtime());
					employee.setMasstype(massEmpPay.getType());
					employeeService.updateByPrimaryKeySelective(employee);
					HttpSession session = request.getSession();
					session.setAttribute("loginuser", employee);

					massEmpPay.setId(out_trade_no);
					massEmpPay.setPayresult(1);
					massEmpPay.setRemark(packageParams.toString());
					massEmpPayService.updateByPrimaryKeySelective(massEmpPay);
					logger.info("支付成功");
				} else {
					if (massEmpPay == null) {
						logger.info(out_trade_no + "订单不存在");
					}
				}

				// ////////执行自己的业务逻辑////////////////

				logger.info("支付成功");
				// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
			} else {
				logger.info("支付失败,错误信息：" + packageParams.get("err_code"));
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			}
			// ------------------------------
			// 处理业务完毕
			// ------------------------------
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
			out.close();
		} else {
			logger.info("通知签名验证失败");
		}

	}

	/**
	 * @name 预下单请求，阿里获取二维码接口
	 * @throws AlipayApiException
	 */
	@RequestMapping(value = "/alipay")
	@ResponseBody
	private void alipay(HttpSession session, HttpServletRequest request, HttpServletResponse response, Integer payType, Integer paymentType) {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.ServiceUrl, AlipayConfig.app_id, AlipayConfig.private_key, "json", AlipayConfig.input_charset, AlipayConfig.zfb_public_key,
				"RSA2"); // 获得初始化的AlipayClient
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		// 到期时间
		MassEmpPay massEmpPay = new MassEmpPay();
		String type = payType == 0 || payType == 1 || payType == 2 ? "视频看房版" : "青英版";
		String buyduration = payType == 0 || payType == 3 ? "年付" : payType == 1 || payType == 4 ? "半年付" : "季付";
		String uuid = Utils.getUUID();
		massEmpPay.setId(uuid);
		massEmpPay.setEmpid(employee.getId());
		massEmpPay.setType(type);
		massEmpPay.setBuyduration(buyduration);
		massEmpPay.setPaytime(new Date());
		if (employee.getMassvalidity() == null) {
			massEmpPay.setEndtime(DateUtil.getPreTime(new Date(), 4, payType == 0 || payType == 3 ? 14 : payType == 1 || payType == 4 ? 7 : payType == 5 ? 3 : 0));
		}else {
			massEmpPay.setEndtime(DateUtil.getPreTime(employee.getMassvalidity(), 4, payType == 0 || payType == 3 ? 14 : payType == 1 || payType == 4 ? 7 : payType == 5 ? 3 : 0));
		}
		massEmpPay.setPaytype(1);
		String[] price = massEmpPayService.selectByPay().split(",");
		massEmpPay.setPayset(Double.valueOf(price[payType]));
		massEmpPay.setPayact(Double.valueOf(price[payType]));
		massEmpPay.setPayresult(0);

		massEmpPayService.insertSelective(massEmpPay);
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		// 发起支付宝支付
		// 设置支付完成后的返回地址
		alipayRequest.setReturnUrl("http://ball.ekeae.com/WebRelease/common/main");
		// 设置回跳和通知地址
		alipayRequest.setNotifyUrl("http://ball.ekeae.com/WebRelease/pay/aliPayCallBack");
		String buyduration2 = payType == 0 || payType == 3 ? "一年时长" : payType == 1 || payType == 4 ? "半年时长" : "三个月时长";
		alipayRequest.setBizContent("{\"out_trade_no\":\"" + uuid + "\"," + "\"total_amount\":\"" + price[payType] + "\"," + "\"subject\":\"" + employee.getEmp() + "购买" + type + "\"," + "\"body\":\""
				+ employee.getEmp() + "购买了" + type + buyduration2 + "\"," + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\",\"timeout_express\":\"15m\"}");
		String form = "";
		try {
			// 调用SDK生成表单
			form = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			form = "err";
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		// 直接将完整的表单html输出到页面
		try {
			response.getWriter().write(form);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 支付宝支付成功回调
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "aliPayCallBack")
	@ResponseBody
	public void AlipayTradePayNotify(Map<String, String[]> requestParams, HttpServletResponse response, HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams1 = request.getParameterMap();
		for (Iterator<String> iter = requestParams1.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams1.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("utf-8"));
			params.put(name, valueStr);
		}
		logger.info(params.toString());
		String signVerified = AlipaySignature.getSignCheckContentV1(params);// 调用SDK验证签名
		logger.info(signVerified);
		// ——请在这里编写您的程序（以下代码仅作参考）——

		/*
		 * 实际验证过程建议商户务必添加以下校验： 1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		 * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		 * 3、校验通知中的seller_id（或者seller_email)
		 * 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		 * 4、验证app_id是否为该商户本身。
		 */
		// if(signVerified) {//验证成功
		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		// 支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

		// 交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

		if (trade_status.equals("TRADE_FINISHED")) {
			// 判断该笔订单是否在商户网站中已经做过处理
			// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
			// 如果有做过处理，不执行商户的业务程序

			// 注意：
			// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
		} else if (trade_status.equals("TRADE_SUCCESS")) {
			// 支付宝支付成功逻辑
			MassEmpPay massEmpPay = massEmpPayService.selectByPrimaryKey(out_trade_no);
			// 判断订单是否存在
			if (massEmpPay != null && massEmpPay.getPayresult() != 1) {
				// 获取该订单的用户
				Employee employee = employeeService.selectByPrimaryKey(massEmpPay.getEmpid());
				employee.setMassvalidity(massEmpPay.getEndtime());
				employee.setMasstype(massEmpPay.getType());
				employeeService.updateByPrimaryKeySelective(employee);
				HttpSession session = request.getSession();
				session.setAttribute("loginuser", employee);

				massEmpPay.setId(out_trade_no);
				massEmpPay.setPayresult(1);
				massEmpPay.setRemark(params.toString());
				massEmpPayService.updateByPrimaryKeySelective(massEmpPay);
				logger.info("支付成功");
			} else {
				if (massEmpPay != null) {
					logger.info(out_trade_no + "订单不存在");
				}
			}
		}

	}

	/**
	 * 将异步通知的参数转化为Map
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> toMap(HttpServletRequest request) {
		//System.out.println(">>>>" + request.getQueryString());
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		return params;
	}
}
