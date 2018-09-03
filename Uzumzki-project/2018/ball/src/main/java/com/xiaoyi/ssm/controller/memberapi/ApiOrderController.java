package com.xiaoyi.ssm.controller.memberapi;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dao.OrderLogMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Coach;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.OrderLog;
import com.xiaoyi.ssm.model.Reserve;
import com.xiaoyi.ssm.service.CoachService;
import com.xiaoyi.ssm.service.FieldService;
import com.xiaoyi.ssm.service.FieldTemplateService;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.ReserveService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.HttpUtil;
import com.xiaoyi.ssm.wxPay.WXConfig;
import com.xiaoyi.ssm.wxPay.WXUtil;
import com.xiaoyi.ssm.wxPay.XMLUtil;

/**
 * @Description: 订单接口控制器
 * @author 宋高俊
 * @date 2018年8月22日 上午11:21:03
 */
@Controller
@RequestMapping("memberapi/order")
public class ApiOrderController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FieldService fieldService;
	@Autowired
	private FieldTemplateService fieldTemplateService;
	@Autowired
	private ReserveService reserveService;
	@Autowired
	private CoachService coachService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderLogMapper orderLogMapper;
	@Autowired
	private MemberService memberService;
	@Autowired
	private OrderLogMapper orderMapper;

	// 获取线程池连接
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	/**
	 * @Description: 订单列表数据
	 * @author 宋高俊
	 * @date 2018年8月22日 上午11:21:40
	 */
	@RequestMapping(value = "/orderlist")
	@ResponseBody
	public ApiMessage orderlist(String token, Integer type) {
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		List<Order> list = orderService.selectAll(member.getId(), type);
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Order order = list.get(i);
			Map<String, Object> map = new HashMap<>();
			map.put("orderId", order.getId());// id
			map.put("orderNo", order.getOrderno());// 订单号
			map.put("price", order.getPrice());// 总价格
			List<Map<String, Object>> reservelist = new ArrayList<>();

			String timeSumStr = "";
			for (int j = 0; j < order.getReserves().size(); j++) {
				Reserve reserve = order.getReserves().get(j);
				Map<String, Object> reservemap = new HashMap<>();
				reservemap.put("name", order.getVenue().getName());// 场馆名称
				reservemap.put("image", order.getVenue().getImage());// 图片
				String[] timestrs = reserve.getReservetimeframe().split(",");
				reservemap.put("timestr", DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + " "
						+ StringUtil.timeToTimestr(timestrs) + " " + reserve.getField().getName());// 时间数据
				timeSumStr += StringUtil.timeToTimestr(timestrs) + " ";
				reservemap.put("price", reserve.getReserveamount());// 单价
				reservelist.add(reservemap);
			}
			// 单独选择一个教练数据
			if (order.getCoachid() != null) {
				Coach coach = coachService.selectByPrimaryKey(order.getCoachid());
				Map<String, Object> reservemap = new HashMap<>();
				reservemap.put("name", coach.getName());// 场馆名称
				reservemap.put("image", coach.getImage());// 图片
				reservemap.put("timestr",
						DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + " " + timeSumStr + " 教练");// 时间数据

				reservemap.put("price", order.getCoachamount());// 单价
				reservelist.add(reservemap);
			}

			if (order.getType() == 1 || order.getType() == 5 || order.getType() == 6) {
				map.put("type", 1);// 已完成
			} else if (order.getType() == 0) {
				map.put("type", 2);// 待支付
			} else if (order.getType() == 4) {
				map.put("type", 3);// 已退款
			} else if (order.getType() == 2 || order.getType() == 3) {
				map.put("type", 4);// 已取消
			}
			map.put("reservelist", reservelist);
			listmap.add(map);
		}
		return ApiMessage.succeed(listmap);
	}

	/**
	 * @Description: 订单支付数据
	 * @author 宋高俊
	 * @date 2018年8月22日 下午2:53:00
	 */
	@RequestMapping(value = "/orderPay")
	@ResponseBody
	public ApiMessage orderPay(String token, String orderid) {
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		Order order = orderService.selectByMemberOrder(member.getId(), orderid);
		if (order == null) {
			return new ApiMessage(400, "订单查询失败");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("orderId", order.getId());// id
		map.put("orderNo", order.getOrderno());// 订单号
		map.put("price", order.getPrice());// 总价格
		map.put("startDate", new Date().getTime());
		map.put("endDate", DateUtil.getPreTime(order.getCreatetime(), 1, 5).getTime());
		List<Map<String, Object>> reservelist = new ArrayList<>();

		String timeSumStr = "";
		for (int j = 0; j < order.getReserves().size(); j++) {
			Reserve reserve = order.getReserves().get(j);
			Map<String, Object> reservemap = new HashMap<>();
			reservemap.put("name", order.getVenue().getName());// 场馆名称
			reservemap.put("image", order.getVenue().getImage());// 图片
			String[] timestrs = reserve.getReservetimeframe().split(",");
			reservemap.put("timestr", DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + " "
					+ StringUtil.timeToTimestr(timestrs) + " " + reserve.getField().getName());// 时间数据
			timeSumStr += StringUtil.timeToTimestr(timestrs) + " ";
			reservemap.put("price", reserve.getReserveamount());// 单价
			reservelist.add(reservemap);
		}
		if (order.getCoachid() != null) {
			Coach coach = coachService.selectByPrimaryKey(order.getCoachid());
			Map<String, Object> reservemap = new HashMap<>();
			reservemap.put("name", coach.getName());// 场馆名称
			reservemap.put("image", coach.getImage());// 图片
			reservemap.put("timestr",
					DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + " " + timeSumStr + " 教练");// 时间数据

			reservemap.put("price", order.getCoachamount());// 单价
			reservelist.add(reservemap);
		}
		map.put("reservelist", reservelist);
		return ApiMessage.succeed(map);
	}

	/**
	 * @Description: 取消订单接口
	 * @author 宋高俊
	 * @date 2018年8月22日 下午4:01:02
	 */
	@RequestMapping(value = "/deleteOrder")
	@ResponseBody
	public ApiMessage deleteOrder(String token, String orderid) {
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		Order order = orderService.selectByMemberOrder(member.getId(), orderid);
		if (order == null) {
			return new ApiMessage(400, "订单查询失败");
		}
		if (order.getType() == 0) {
			order.setType(2);
			order.setModifytime(new Date());
			orderService.updateByPrimaryKeySelective(order);
			return new ApiMessage(200, "取消成功");
		} else {
			return new ApiMessage(400, "该订单已取消");
		}

	}

	/**
	 * @Description: 获取支付参数
	 * @author 宋高俊
	 * @date 2018年8月24日 下午3:34:38
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping(value = "/toPay", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage dopay(HttpServletRequest request, String orderId, String token)
			throws JDOMException, IOException {
//		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		Member member = memberService.selectByPrimaryKey(token);
		// 判断orderId
		if (orderId == null) {
			return new ApiMessage(400, "订单号为空");
		}
		Order order = orderService.selectByMemberOrder(member.getId(), orderId);

		// 判断订单是否为空
		if (order == null) {
			return new ApiMessage(400, "订单不存在");
		}

		/**
		 * 商品描述
		 */
		String body = "订场预定金额";

		/**
		 * 商户订单号
		 */
		String out_trade_no = order.getId();

		/**
		 * openId
		 */
		String openid = member.getOpenid();
		logger.info("openid = " + openid);

		/**
		 * 标价金额 把元转化成分 ，千万不要转化成string类型，要转化成int类型， 要不然会报错，一定要跟着文档走，要不然会报total_fee不正确的错误
		 */
//	    int total_fee = (new BigDecimal(Double.toString(order.getPrice())).multiply(new BigDecimal("100"))).intValue();
		int total_fee = 1;
		/**
		 * 终端IP(订单生成的机器IP)
		 */
		String spbill_create_ip = request.getRemoteAddr();

		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", WXConfig.appid);// 微信公众号id
		packageParams.put("mch_id", WXConfig.mch_id);// 商户号
		packageParams.put("nonce_str", WXConfig.nonce_str);// 随机字符串
		packageParams.put("body", body);// 商品描述
		packageParams.put("out_trade_no", out_trade_no);// 商户订单号
		packageParams.put("total_fee", total_fee);// 订单金额
		packageParams.put("spbill_create_ip", spbill_create_ip);// 用户的终端IP
		packageParams.put("notify_url", WXConfig.notify_url);// 通知地址
		packageParams.put("openid", openid);// 用户的openid
		packageParams.put("trade_type", WXConfig.trade_type);

		// 获取签名
		String sign = WXUtil.createSign(packageParams, WXConfig.paternerKey);
		logger.info("sign = " + sign);
		packageParams.put("sign", sign);// 签名
		String requestXML = WXUtil.mapToXml(packageParams);
		logger.info(requestXML);
		String resXml = HttpUtil.postData(WXConfig.url, requestXML);
		Map xmlMap = XMLUtil.doXMLParse(resXml);
		logger.info("xmlMap = " + xmlMap);

		String returnCode = (String) xmlMap.get("return_code");
		String returnMsg = (String) xmlMap.get("return_msg");
		if ("SUCCESS".equals(returnCode)) {
			String resultCode = (String) xmlMap.get("result_code");
			String errCodeDes = (String) xmlMap.get("err_code_des");
			if ("SUCCESS".equals(resultCode)) {
				// 获取预支付交易会话标识
				String prepay_id = (String) xmlMap.get("prepay_id");
				logger.info("prepay_id = " + prepay_id);

				// 把prepay_id保存到订单中,这一步 根据的自己的业务需求，是否需要保存到数据库中

				// 下面是公众号内调起的h5支付，看清楚和上边获取prepay_id的时候，注意单词的大小写
				// 字符串
				String nonceStr = packageParams.get("nonce_str").toString();
				// 订单详情扩展字符串
				String packages = "prepay_id=" + prepay_id;
				SortedMap<Object, Object> finalpackage = new TreeMap<Object, Object>();
				finalpackage.put("appId", WXConfig.appid);
				finalpackage.put("timeStamp", WXConfig.timeStamp);
				finalpackage.put("nonceStr", nonceStr);
				finalpackage.put("package", packages);
				finalpackage.put("signType", WXConfig.signType);
				sign = WXUtil.createSign(finalpackage, WXConfig.paternerKey);
				finalpackage.put("paySign", sign);

				finalpackage.put("orderNo", out_trade_no);
				finalpackage.put("totalFee", total_fee);
				finalpackage.put("errCodeDes", errCodeDes);
				logger.info("finalpackage = " + finalpackage);
				return new ApiMessage(200, "获取成功", finalpackage);
			}
		}

		return null;
	}

	/**
	 * 手机支付完成回调,这个方法主要是完成支付后，项目后台业务需要
	 * 
	 * @param request
	 * @param response 这个方法是用流传递的 这个方法是用流传递的 这个方法是用流传递的
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/weixinNotify", method = RequestMethod.POST)
	public void WXPayBack(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException {
		// 读取参数
		InputStream inputStream = request.getInputStream();
		StringBuffer sb = new StringBuffer();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		logger.info("in.toString() = " + in.toString());
		while ((s = in.readLine()) != null) {
			sb.append(s);
		}
		in.close();
		inputStream.close();
		// 解析xml成map
		Map<String, String> m = new HashMap<String, String>();
		m = XMLUtil.doXMLParse(sb.toString());

		Iterator it = m.keySet().iterator();
		logger.info("it =" + it);
		// 过滤空 设置 TreeMap
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();

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
		String key = WXConfig.paternerKey; // key
		// 判断签名是否正确
		if (WXUtil.isTenpaySign("UTF-8", packageParams, key)) {
			logger.info("微信支付成功回调");
			// ------------------------------
			// 处理业务开始
			// ------------------------------
			String resXml = "";
			if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
				// 这里是支付成功
				String orderNo = (String) packageParams.get("out_trade_no");
				logger.info("微信订单号{}付款成功", orderNo);
				// 这里 根据实际业务场景 做相应的操作
				// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
						+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
				// 这一步开始就是写公司业务需要的代码了，不用参考我的 没有价值
				Order order = orderService.selectByPrimaryKey(orderNo);
				// 修改订单状态支付成功
				order.setModifytime(new Date());
				order.setType(6);
				order.setPaytype(1);
				orderService.updateByPrimaryKeySelective(order);

				OrderLog orderLog = new OrderLog();
				orderLog.setId(Utils.getUUID());
				orderLog.setCreatetime(new Date());
				orderLog.setOrderid(orderNo);
				orderLog.setType(0);
				orderLog.setContent("接收到微信通知，订单已支付成功");
				orderMapper.insert(orderLog);

				logger.info("微信订单号{}付款成功", orderNo);
			} else {
				logger.info("支付失败,错误信息：{}", packageParams.get("err_code"));
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
						+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
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
	 * 微信退款 此处用的 自己生成的订单号 就是上面支付中生成的订单号
	 * @param merchantNumber 商户这边的订单号
     * @param wxTransactionNumber 微信那边的交易单号
     * @param totalFee 订单的金额
	 * @return 返回参数见微信支付查询订单
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/weiXinPayOrderRefund")
	@ResponseBody
	public ApiMessage qxwxsign(String orderid,HttpServletResponse response, HttpServletRequest request) throws IOException {
        Order order = orderService.selectByPrimaryKey(orderid);

		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
		String nonceStr = WXUtil.genNonceStr();// 随机字符串
		parameters.put("appid", WXConfig.appid);
		parameters.put("mch_id", WXConfig.mch_id);
		parameters.put("nonce_str", nonceStr);
		parameters.put("out_trade_no", order.getId());
		parameters.put("transaction_id", "");
		parameters.put("out_refund_no", nonceStr);
		parameters.put("fee_type", "CNY");
		parameters.put("total_fee", String.valueOf((int)(1)));
		parameters.put("refund_fee", String.valueOf((int)(1)));
		parameters.put("sign", WXUtil.createSign(parameters, WXConfig.paternerKey));
		String xml = WXUtil.mapToXml(parameters);
		
		logger.info(xml);
		
        String result = "";
        String url = "https://api.mch.weixin.qq.com/secapi/pay/refund";
        try {
        	File file = new File(this.getClass().getResource("/cert/apiclient_cert.p12").getPath());
            result = WXUtil.payHttps(url, xml , file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = new HashMap<>();
        try {
    		logger.info("返回参数："+result);
			map = XMLUtil.doXMLParse(result);
		} catch (JDOMException e) {
			e.printStackTrace();
		}
        //result_code 返回SUCCESS/FAIL, SUCCESS
        if ("SUCCESS".equals(map.get("result_code"))) {
        	//修改订单状态为已退款
        	order.setModifytime(new Date());
        	order.setType(4);
        	orderService.updateByPrimaryKeySelective(order);
        	
        	OrderLog orderLog = new OrderLog();
        	orderLog.setId(Utils.getUUID());
        	orderLog.setCreatetime(new Date());
        	orderLog.setType(0);
        	orderLog.setOrderid(orderid);
        	orderLog.setContent("发起退款申请，已全额退款");
        	return new ApiMessage(200,"退款成功");
		}else {
			return new ApiMessage(400,map.get("err_code_des").toString());
		}
    }
}
