package com.xiaoyi.ssm.controller.memberapi;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
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
import javax.servlet.http.HttpSession;

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
	public ApiMessage orderlist(Integer type, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, openid);
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
	public ApiMessage orderPay(String orderid, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, openid);
		Order order = orderService.selectByMemberOrder(member.getId(), orderid);
		if (order == null) {
			return new ApiMessage(400, "订单查询失败");
		}
		if (order.getType() != 0) {
			return new ApiMessage(400, "订单已不能支付");
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
	public ApiMessage deleteOrder(String orderid, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, openid);
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
	 * @Description: 获取微信支付参数
	 * @author 宋高俊
	 * @date 2018年8月24日 下午3:34:38
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value = "/toPay", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage dopay(HttpServletRequest request, String orderId) throws JDOMException, IOException {
		// 判断orderId
		if (orderId == null) {
			return new ApiMessage(400, "订单号为空");
		}
		
		HttpSession session = request.getSession();
		String loginopenid = (String) session.getAttribute("openid");

		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, loginopenid);
		Order order = orderService.selectByMemberOrder(member.getId(), orderId);

		// 判断订单是否为空
		if (order == null) {
			return new ApiMessage(400, "订单不存在");
		}

		Map map = WXUtil.wxToPay("订场预定金额", order.getId(), member.getOpenid(), order.getPrice(), request.getRemoteAddr(), WXConfig.NOTIFY_URL1);

		return new ApiMessage(200, "支付参数", map);
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
	 * @Description: 获取微信支付参数
	 * @author 宋高俊
	 * @date 2018年8月24日 下午3:34:38
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value = "/toPayTest", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage toPayTest(HttpServletRequest request, double amount) throws JDOMException, IOException {
		
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");

		Map map = WXUtil.wxToPay("订场预定金额", Utils.getUUID(), openid, amount, request.getRemoteAddr(), WXConfig.NOTIFY_URL1);

		return new ApiMessage(200, "支付参数", map);
	}

	/**
	 * 手机支付完成回调,这个方法主要是完成支付后，项目后台业务需要
	 * 
	 * @param request
	 * @param response 这个方法是用流传递的 这个方法是用流传递的 这个方法是用流传递的
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/weixinNotifyTest", method = RequestMethod.POST)
	public void weixinNotifyTest(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException {
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

}
