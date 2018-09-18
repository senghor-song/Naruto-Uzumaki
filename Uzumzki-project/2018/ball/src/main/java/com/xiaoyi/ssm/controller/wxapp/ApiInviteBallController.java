package com.xiaoyi.ssm.controller.wxapp;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.xiaoyi.ssm.dao.InviteJoinMapper;
import com.xiaoyi.ssm.dao.InviteLogMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.InviteDto;
import com.xiaoyi.ssm.dto.PageBean;
import com.xiaoyi.ssm.model.InviteBall;
import com.xiaoyi.ssm.model.InviteImage;
import com.xiaoyi.ssm.model.InviteJoin;
import com.xiaoyi.ssm.model.InviteLog;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.OrderLog;
import com.xiaoyi.ssm.service.InviteBallService;
import com.xiaoyi.ssm.service.InviteImageService;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.util.Arith;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.MapUtils;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.HttpUtil;
import com.xiaoyi.ssm.wxPay.WXConfig;
import com.xiaoyi.ssm.wxPay.WXUtil;
import com.xiaoyi.ssm.wxPay.XMLUtil;

/**
 * @Description: 微信小程序约球接口
 * @author 宋高俊
 * @date 2018年9月13日 下午2:50:07
 */
@Controller
@RequestMapping("wxapp/inviteBall")
public class ApiInviteBallController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MemberService memberService;
	@Autowired
	private InviteBallService inviteBallService;
	@Autowired
	private InviteImageService inviteImageService;
	@Autowired
	private InviteLogMapper inviteLogMapper;
	@Autowired
	private InviteJoinMapper inviteJoinMapper;

	/**
	 * @Description: 创建约球的接口
	 * @author 宋高俊
	 * @param token
	 * @param inviteBall
	 * @param urls
	 * @return
	 * @date 2018年9月13日 下午3:03:51
	 */
	@RequestMapping(value = "/saveInviteBall")
	@ResponseBody
	public ApiMessage saveInviteBall(HttpServletRequest request, InviteBall inviteBall, String urls, String endTimeStr) {
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		// 用于返回给前端的ID
		String id = Utils.getUUID();
		
		if (member != null) {
			if(inviteBall.getToRequire() == 0){
				
			}
			
			inviteBall.setEndTime(DateUtil.getParse(endTimeStr, "yyyy-MM-dd HH:mm"));
			inviteBall.setId(id);
			inviteBall.setMemberId(member.getId());
			inviteBall.setCreateTime(new Date());
			inviteBall.setModifyTime(new Date());
			inviteBallService.insertSelective(inviteBall);

			if (urls != null) {
				// 保存图片数据
				String[] url = urls.split(";");
				for (int i = 0; i < url.length; i++) {
					InviteImage inviteImage = new InviteImage();
					inviteImage.setId(Utils.getUUID());
					inviteImage.setCreateTime(new Date());
					inviteImage.setInviteId(id);
					inviteImage.setUrl(url[i]);
					// 将第一张图片设置为封面
					if (i == 0) {
						inviteImage.setHeadImage(1);
					}
					inviteImageService.insertSelective(inviteImage);
				}
			}

			return new ApiMessage(200, "发起成功", id);
		}
		return new ApiMessage(400, "操作超时");
	}

	/**
	 * @Description: 获取附近的约球活动
	 * @author 宋高俊
	 * @param token
	 * @param inviteBall
	 * @param urls
	 * @return
	 * @date 2018年9月13日 下午3:39:13
	 */
	@RequestMapping(value = "/getInviteBall")
	@ResponseBody
	public ApiMessage getInviteBall(HttpServletRequest request, PageBean pageBean, Double lng, Double lat) {

		if (lng == null || lat == null) {
			return new ApiMessage(400, "需要经纬度查询");
		}

		InviteDto inviteDto = new InviteDto();
		inviteDto.setMaxlng(Arith.add(lng, 0.5));
		inviteDto.setMaxlat(Arith.add(lat, 0.5));

		inviteDto.setMinlng(Arith.sub(lng, 0.5));
		inviteDto.setMinlat(Arith.sub(lat, 0.5));

		// 开始分页
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<InviteBall> list = inviteBallService.selectByNearby(inviteDto);
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Date date = new Date();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", list.get(i).getId());// ID

			map.put("area", (int)MapUtils.getDistance(lng, lat, list.get(i).getLongitude(), list.get(i).getLatitude()));// 距离
			InviteImage inviteImage = inviteImageService.selectByHeadID(list.get(i).getId());
			if (inviteImage != null) {
				map.put("image", inviteImage.getUrl());// 图片
			} else {
				map.put("image", "");// 图片
			}
			map.put("title", list.get(i).getTitle());// 标题

			long time = DateUtil.getTimeDifference(date, list.get(i).getEndTime(), 3);
			if (time == 0) {
				time = DateUtil.getTimeDifference(date, list.get(i).getEndTime(), 2);
				if (time == 0) {
					time = DateUtil.getTimeDifference(date, list.get(i).getEndTime(), 1);
					if (time == 0) {
						time = DateUtil.getTimeDifference(date, list.get(i).getEndTime(), 0);
						if (time == 0) {
							map.put("time", "报名已截止");// 截止时间
						} else {
							map.put("time", time + "秒");// 截止时间
						}
					} else {
						map.put("time", time + "分钟");// 截止时间
					}
				} else {
					map.put("time", time + "小时");// 截止时间
				}
			} else {
				map.put("time", time + "天");// 截止时间
			}
			listmap.add(map);
		}

		return new ApiMessage(200, "获取成功", listmap);
	}

	/**
	 * @Description: 创建留言
	 * @author 宋高俊
	 * @param token
	 * @param id
	 * @return
	 * @date 2018年9月14日 上午11:48:17
	 */
	@RequestMapping(value = "/addInviteLog")
	@ResponseBody
	public ApiMessage addInviteLog(HttpServletRequest request, String id, String content) {

		String token = (String) request.getAttribute("token");

		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		// 创建留言数据
		InviteLog inviteLog = new InviteLog();
		inviteLog.setId(Utils.getUUID());
		inviteLog.setCreateTime(new Date());
		inviteLog.setInviteballId(id);
		inviteLog.setContent(content);
		inviteLog.setMemberId(member.getId());
		inviteLog.setType(0);
		int flag = inviteLogMapper.insert(inviteLog);

		if (flag > 0) {
			return new ApiMessage(200, "留言成功");
		} else {
			return new ApiMessage(400, "留言失败");
		}
	}

	/**
	 * @Description: 根据约球ID获取日志
	 * @author 宋高俊
	 * @param pageBean
	 * @param token
	 * @param lng
	 * @param lat
	 * @return
	 * @date 2018年9月13日 下午6:03:39
	 */
	@RequestMapping(value = "/getInviteLog")
	@ResponseBody
	public ApiMessage getInviteLog(HttpServletRequest request, String id) {

		String token = (String) request.getAttribute("token");

		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		// 约球数据
		InviteBall inviteBall = inviteBallService.selectByPrimaryKey(id);

		List<InviteLog> list = new ArrayList<InviteLog>();
		// 判断是否是发起人
		if (member.getId().toString().equals(inviteBall.getMemberId().toString())) {
			// 发起人有权限看到更多的信息
			list = inviteLogMapper.selectByInviteAdmin(id);
		} else {
			list = inviteLogMapper.selectByInvite(id);
		}

		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", list.get(i).getId());// ID
			map.put("url", list.get(i).getMember().getAppavatarurl());// 头像URL
			map.put("name", list.get(i).getMember().getAppnickname());// 姓名
			map.put("time", DateUtil.getFormat(list.get(i).getCreateTime()));// 时间
			map.put("type", list.get(i).getType() == 1 ? "系统通知" : "留言");// 类别
			map.put("content", list.get(i).getContent());// 内容
			listmap.add(map);
		}

		return new ApiMessage(200, "获取成功", listmap);
	}

	/**
	 * @Description: 根据约球ID获取详情数据
	 * @author 宋高俊
	 * @param token
	 * @param id
	 * @return
	 * @date 2018年9月13日 下午8:29:02
	 */
	@RequestMapping(value = "/getInviteBallDetails")
	@ResponseBody
	public ApiMessage getInviteBallDetails(HttpServletRequest request, String id) {

		String token = (String) request.getAttribute("token");

		Map<String, Object> map = new HashMap<String, Object>();
		// 约球数据
		InviteBall inviteBall = inviteBallService.selectByPrimaryKey(id);

		map.put("isAdmin", "0");
		if (!StringUtil.isBank(token)) {
			Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
			if (member != null) {
				InviteJoin inviteJoin = inviteJoinMapper.selectByJoinMemberKey(id, member.getId());
				if (inviteJoin != null) {
					map.put("applyFlag", 1);// 当前用户是否报名
				}else {
					map.put("applyFlag", 0);// 当前用户是否报名
				}
				// 判断是否是发起人
				if (member.getId().toString().equals(inviteBall.getMemberId().toString())) {
					// 发起人有权限看到更多的信息
					map.put("isAdmin", "1");
				} else {
					map.put("isAdmin", "0");
				}
			}
		}

		// 根据约球ID查询宣传图
		List<InviteImage> inviteImages = inviteImageService.selectByInviteBallId(id);
		List<String> urls = new ArrayList<String>();
		for (InviteImage inviteImage : inviteImages) {
			urls.add(inviteImage.getUrl());
		}
		// 图片集合
		map.put("urls", urls);

		// 根据约球ID查询加入人员
		List<InviteJoin> inviteJoins = inviteJoinMapper.selectByJoinMember(id);
		List<String> joinList = new ArrayList<String>();
		for (int i = 0; i < inviteJoins.size(); i++) {
			joinList.add(inviteJoins.get(i).getMember().getAppavatarurl());
		}
		map.put("joinMember", joinList);// 已加入人员头像
		map.put("nowJoinMember", inviteJoins.size() + "/" + inviteBall.getMaxBoy());// 当前报名人数

		map.put("title", inviteBall.getTitle());// 标题
		map.put("time", DateUtil.getFormat(inviteBall.getEndTime(), "yyyy年M月dd日 HH:mm"));// 活动时间
		map.put("venuename", inviteBall.getVenueName());// 场馆名称
		map.put("lng", inviteBall.getLongitude());// 经度
		map.put("lat", inviteBall.getLatitude());// 纬度
		map.put("endTime", DateUtil.getFormat(inviteBall.getEndTime()));// 结束时间

		map.put("receiveFlag", inviteBall.getReceiveFlag());// 是否预收费用(1=是0=否)
		map.put("receiveAmount", inviteBall.getReceiveAmount());// 预收金额
		map.put("receiveType", inviteBall.getReceiveType());// 预收类型(1=固定收费2=AA收费)
		
		map.put("exitFlag", inviteBall.getExitFlag());// 是否允许退出(1=是0=否)
		map.put("exitFee", inviteBall.getExitFee());// 退出手续费
		map.put("exitType", inviteBall.getExitType());// 退出类型(1=直接退出2=需发起人同意)

		map.put("minBoy", inviteBall.getMinBoy());// 最小人数
		map.put("content", inviteBall.getContent());// 约球详情
		
		map.put("id", inviteBall.getId());// id

		return new ApiMessage(200, "获取成功", map);
	}

	/**
	 * @Description: 立即申请报名
	 * @author 宋高俊
	 * @param token 登录状态
	 * @param id 约球ID
	 * @param name 姓名
	 * @param wechatid 微信号
	 * @param phone 手机号
	 * @param content 备注
	 * @return
	 * @date 2018年9月13日 下午9:01:32
	 */
	@RequestMapping(value = "/applyInviteBall")
	@ResponseBody
	public ApiMessage applyInviteBall(HttpServletRequest request, String id, String name, String wechatid, String phone, String content) {

		String token = (String) request.getAttribute("token");

		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		// 约球数据
		InviteBall inviteBall = inviteBallService.selectByPrimaryKey(id);

		if (inviteBall.getNameFlag() == 1 && StringUtil.isBank(name)) {
			return new ApiMessage(400, "发起人要求,姓名号必填");
		}
		if (inviteBall.getWechatFlag() == 1 && StringUtil.isBank(wechatid)) {
			return new ApiMessage(400, "发起人要求,微信号必填");
		}
		if (inviteBall.getPhoneFlag() == 1 && StringUtil.isBank(phone)) {
			return new ApiMessage(400, "发起人要求,手机号必填");
		}

		// 创建预支付订单
		InviteJoin inviteJoin = new InviteJoin();
		inviteJoin.setId(Utils.getUUID());
		inviteJoin.setCreateTime(new Date());
		inviteJoin.setMemberId(member.getId());
		inviteJoin.setContent(content);
		inviteJoin.setName(name);
		inviteJoin.setPhone(phone);
		inviteJoin.setWechatId(wechatid);
		inviteJoin.setInviteId(id);
		
		// 判断是否需要收费
		if (inviteBall.getReceiveFlag() == 1) {
			inviteJoin.setAmount(inviteBall.getReceiveAmount().doubleValue());
			inviteJoin.setPayType(0);
		} else {
			inviteJoin.setPayType(4);
		}
		
		int flag = inviteJoinMapper.insertSelective(inviteJoin);
		if (flag > 0) {
			String inviteJoinID = inviteJoin.getId();
			if (inviteBall.getReceiveFlag() == 1) {
				// 开启线程处理延时任务
				new Timer(inviteJoinID + "订单支付超时处理中").schedule(new TimerTask() {
					@Override
					public void run() {
						InviteJoin inviteJoin = inviteJoinMapper.selectByPrimaryKey(inviteJoinID);
						if (inviteJoin.getPayType() == 0) {
							logger.info("检查状态--->订单没有支付成功");
							inviteJoin.setPayType(2);
							inviteJoinMapper.updateByPrimaryKeySelective(inviteJoin);
						}
					}
				}, 300000);
			}
			return new ApiMessage(200, "报名成功", inviteJoin.getId());
		} else {
			return new ApiMessage(400, "报名失败");
		}
	}
	
	/**
	 * @Description: 小程序获取支付参数
	 * @author 宋高俊
	 * @param token
	 * @return
	 * @throws IOException
	 * @throws JDOMException
	 * @date 2018年9月14日 上午9:14:40
	 */
	@RequestMapping(value = "/wx/getPayment", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage wxGetPayment(HttpServletRequest request, String inviteJoinId) throws JDOMException, IOException {

		String token = (String) request.getAttribute("token");

		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		// 查询订单
		InviteJoin inviteJoin = inviteJoinMapper.selectByPrimaryKey(inviteJoinId);
		
		if (inviteJoin == null) {
			return new ApiMessage(400, "订单不存在");
		}
		if (inviteJoin.getPayType() == 4) {
			return new ApiMessage(400, "订单无需支付");
		}
		if (inviteJoin.getPayType() == 1 || inviteJoin.getPayType() == 2 || inviteJoin.getPayType() == 3 ) {
			return new ApiMessage(400, "订单已完成，不能支付");
		}
		Map map = WXUtil.wxToPay("参与约球支付金额", inviteJoinId, member.getOpenid(), inviteJoin.getAmount(), request.getRemoteAddr(), WXConfig.APPNOTIFY_URL);
		return new ApiMessage(200, "获取预支付订单成功", map);
	}

	/**
	 * @Description: 小程序获取支付参数
	 * @author 宋高俊
	 * @param token
	 * @return
	 * @throws IOException
	 * @throws JDOMException
	 * @date 2018年9月14日 上午9:14:40
	 */
	@RequestMapping(value = "/wxapp/getPayment", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage wxappGetPayment(HttpServletRequest request, String inviteJoinId) throws JDOMException, IOException {

		String token = (String) request.getAttribute("token");

		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		// 查询订单
		InviteJoin inviteJoin = inviteJoinMapper.selectByPrimaryKey(inviteJoinId);
		
		if (inviteJoin == null) {
			return new ApiMessage(400, "订单不存在");
		}
		if (inviteJoin.getPayType() == 4) {
			return new ApiMessage(400, "订单无需支付");
		}
		if (inviteJoin.getPayType() == 1 || inviteJoin.getPayType() == 2 || inviteJoin.getPayType() == 3 ) {
			return new ApiMessage(400, "订单已完成，不能支付");
		}

		// 预下单处理
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", WXConfig.wxAppAppid);// 小程序ID
		packageParams.put("mch_id", WXConfig.mch_id);// 商户号
		packageParams.put("nonce_str", WXUtil.getNonceStr());// 随机字符串
//		packageParams.put("sign_type", "HMAC-SHA256");// 签名类型
		packageParams.put("body", "123456");// 商品描述
		packageParams.put("out_trade_no", inviteJoin.getId());// 商户订单号
		packageParams.put("total_fee", Utils.doubleToInt(inviteJoin.getAmount()));// 标价金额
		packageParams.put("spbill_create_ip", request.getRemoteAddr());// 终端IP
		packageParams.put("time_start", DateUtil.getFormat(new Date(), "yyyyMMddHHmmss"));// 交易起始时间
		packageParams.put("time_expire", DateUtil.getFormat(DateUtil.getPreTime(new Date(), 1, 5), "yyyyMMddHHmmss"));// 交易结束时间
		packageParams.put("notify_url", WXConfig.APPNOTIFY_URL);// 通知地址
		packageParams.put("trade_type", "JSAPI");// 交易类型
		packageParams.put("openid", member.getAppopenid());// 用户标识

		// 获取签名
		String sign = WXUtil.createSign(packageParams, WXConfig.paternerKey);
		logger.info("sign = " + sign);
		packageParams.put("sign", sign);// 签名
		// 请求参数封装成xml
		String requestXML = WXUtil.mapToXml(packageParams);
		logger.info(requestXML);
		// 获取微信预支付订单数据
		String resXml = HttpUtil.postData(WXConfig.url, requestXML);
		Map xmlMap = XMLUtil.doXMLParse(resXml);
		logger.info("xmlMap = " + xmlMap);

		// 订单支付异常
		if (!xmlMap.get("return_code").toString().equals("SUCCESS")) {
			logger.info("订单交易错误");
			return new ApiMessage(400, xmlMap.get("return_msg").toString());
		}
		if (!xmlMap.get("result_code").toString().equals("SUCCESS")) {
			logger.info("订单业务结果错误");
			return new ApiMessage(400, xmlMap.get("err_code_des").toString());
		}

		// 用于生成支付签名参数的数据
		SortedMap<Object, Object> paymap = new TreeMap<Object, Object>();
		long time = new Date().getTime() / 1000;
		String nonceStr = WXUtil.getNonceStr();

		// 获取预支付交易会话标识
		String prepay_id = (String) xmlMap.get("prepay_id");
		logger.info("prepay_id = " + prepay_id);

		paymap.put("appId", WXConfig.wxAppAppid);
		paymap.put("nonceStr", nonceStr);
		paymap.put("package", "prepay_id=" + xmlMap.get("prepay_id"));
		paymap.put("signType", "MD5");
		paymap.put("timeStamp", time);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("timeStamp", time);
		map.put("nonceStr", nonceStr);
		map.put("package", "prepay_id=" + xmlMap.get("prepay_id"));
		map.put("signType", "MD5");
		map.put("paySign", WXUtil.createSign(paymap, WXConfig.paternerKey));
		
		return new ApiMessage(200, "获取预支付订单成功", map);
	}

	/**
	 * @Description: 小程序支付完成回调
	 * @author 宋高俊
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws JDOMException
	 * @date 2018年9月14日 上午10:43:55
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/weixinNotify", method = RequestMethod.POST)
	public void weixinNotify(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException {
		logger.info("小程序支付回调");
		// 读取参数
		InputStream inputStream = request.getInputStream();
		StringBuffer sb = new StringBuffer();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		logger.info("所有参数  = " + in.toString());
		while ((s = in.readLine()) != null) {
			sb.append(s);
		}
		in.close();
		inputStream.close();
		// 解析xml成map
		Map<String, String> m = new HashMap<String, String>();
		m = XMLUtil.doXMLParse(sb.toString());

		Iterator it = m.keySet().iterator();
		logger.info("xml中的参数 = " + it);
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
				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
				// 这一步开始就是写公司业务需要的代码了，不用参考我的 没有价值
				// 查询订单
				InviteJoin inviteJoin = inviteJoinMapper.selectByPrimaryKey(orderNo);
				if (inviteJoin == null) {
					logger.info("订单不存在");
				}
				if (inviteJoin.getPayType() == 0) {
					// 修改支付成功的订单
					inviteJoin.setPayTime(new Date());
					inviteJoin.setPayType(1);
					inviteJoinMapper.updateByPrimaryKeySelective(inviteJoin);
				}

				logger.info("微信订单号{}付款成功", orderNo);
			} else {
				logger.info("支付失败,错误信息：{}", packageParams.get("err_code"));
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
	 * @Description: 获取报名名单人
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年9月14日 下午4:51:47
	 */
	@RequestMapping(value = "/getInviteJoin")
	@ResponseBody
	public ApiMessage getInviteJoin(String id) {

		// 根据约球ID查询加入人员
		List<InviteJoin> inviteJoins = inviteJoinMapper.selectByJoinMember(id);
		List<Map<String, Object>> joinList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < inviteJoins.size(); i++) {
			InviteJoin inviteJoin = inviteJoins.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", inviteJoin.getId());// ID
			map.put("appavatarurl", inviteJoin.getMember().getAppavatarurl());// 头像地址
			map.put("appnickname", inviteJoin.getMember().getAppnickname());// 昵称
			map.put("createTime", DateUtil.getFormat(inviteJoin.getCreateTime(), "M月d日 HH:mm"));// 报名时间
			map.put("joinsum", "0");// 转发邀请数量
			joinList.add(map);
		}
		return new ApiMessage(200, "获取成功", joinList);
	}
	
	/**  
	 * @Description: 获取要修改的数据/显示模板的数据
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月15日 上午9:42:27 
	 */ 
	@RequestMapping(value = "/editInviteBall")
	@ResponseBody
	public ApiMessage editInviteBall(String id) {

		// 约球数据
		InviteBall inviteBall = inviteBallService.selectByPrimaryKey(id);
				
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("inviteBall", inviteBall);// 约球信息
		
		List<InviteImage> inviteImages = inviteImageService.selectByInviteBallId(id);
		List<String> urls = new ArrayList<String>();
		for (InviteImage inviteImage : inviteImages) {
			urls.add(inviteImage.getUrl());
		}
		map.put("urls", urls);// 图片集合
		return new ApiMessage(200, "获取成功", map);
	}
	
	/**
	 * @Description: 修改约球的接口
	 * @author 宋高俊
	 * @param token
	 * @param inviteBall
	 * @param urls
	 * @return
	 * @date 2018年9月13日 下午3:03:51
	 */
	@RequestMapping(value = "/updateInviteBall")
	@ResponseBody
	public ApiMessage updateInviteBall(HttpServletRequest request, InviteBall inviteBall, String urls, String endTimeStr) {
		
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		if (member != null) {
			inviteBall.setEndTime(DateUtil.getParse(endTimeStr, "yyyy-MM-dd HH:mm"));
			inviteBall.setMemberId(member.getId());
			inviteBall.setCreateTime(new Date());
			inviteBall.setModifyTime(new Date());
			inviteBallService.updateByPrimaryKeySelective(inviteBall);

			inviteImageService.deleteByInviteBall(inviteBall.getId());
			
			if (urls != null) {
				// 保存图片数据
				String[] url = urls.split(";");
				for (int i = 0; i < url.length; i++) {
					InviteImage inviteImage = new InviteImage();
					inviteImage.setId(Utils.getUUID());
					inviteImage.setCreateTime(new Date());
					inviteImage.setInviteId(inviteBall.getId());
					inviteImage.setUrl(url[i]);
					// 将第一张图片设置为封面
					if (i == 0) {
						inviteImage.setHeadImage(1);
					}
					inviteImageService.insertSelective(inviteImage);
				}
			}
			return new ApiMessage(200, "发起成功");
		}
		return new ApiMessage(400, "操作超时");
	}
	
	/**  
	 * @Description: 取消活动
	 * @author 宋高俊  
	 * @param request
	 * @param id
	 * @param content
	 * @return 
	 * @date 2018年9月15日 下午4:14:17 
	 */ 
	@RequestMapping(value = "/cancelInviteBall")
	@ResponseBody
	public ApiMessage cancelInviteBall(HttpServletRequest request, String id, String content) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		// 将活动状态改为取消
		InviteBall inviteBall = inviteBallService.selectByPrimaryKey(id);
		inviteBall.setBallType(3);
		int flag = inviteBallService.updateByPrimaryKeySelective(inviteBall);
		if (flag > 0) {
			// 记录日志
			InviteLog inviteLog = new InviteLog();
			inviteLog.setId(Utils.getUUID());
			inviteLog.setContent(content);
			inviteLog.setCreateTime(new Date());
			inviteLog.setInviteballId(id);
			inviteLog.setType(1);
			inviteLog.setMemberId(member.getId());
			// 活动取消后需发送微信公众号消息所有已加入的活动的人
			
			
			JSONObject datajson = new JSONObject();
			
			
			WXUtil.sendTemplate(member.getUnionid(), "", "", datajson);
			
			
			
			
			
			
			
			return new ApiMessage(200, "取消成功");
		} else {
			return new ApiMessage(400, "取消失败");
		}
	}
	
	/**  
	 * @Description: 提前截止活动
	 * @author 宋高俊  
	 * @param request
	 * @param id
	 * @param content
	 * @return 
	 * @date 2018年9月15日 下午4:14:59 
	 */ 
	@RequestMapping(value = "/aheadInviteBall")
	@ResponseBody
	public ApiMessage aheadInviteBall(HttpServletRequest request, String id, String content) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		//将活动状态改为取消
		InviteBall inviteBall = inviteBallService.selectByPrimaryKey(id);
		inviteBall.setBallType(3);
		int flag = inviteBallService.updateByPrimaryKeySelective(inviteBall);
		if (flag > 0) {
			// 记录日志
			InviteLog inviteLog = new InviteLog();
			inviteLog.setId(Utils.getUUID());
			inviteLog.setContent(content);
			inviteLog.setCreateTime(new Date());
			inviteLog.setInviteballId(id);
			inviteLog.setType(1);
			inviteLog.setMemberId(member.getId());
			//提前截止活动需发送微信公众号消息所有已加入的活动的人
			
			
			
			
			
			
			
			
			return new ApiMessage(200, "取消成功");
		}else {
			return new ApiMessage(400, "取消失败");
		}
	}
	
	/**  
	 * @Description: 群发通知
	 * @author 宋高俊  
	 * @param request
	 * @param id
	 * @param content
	 * @return 
	 * @date 2018年9月15日 下午4:14:59 
	 */ 
	@RequestMapping(value = "/massNotification")
	@ResponseBody
	public ApiMessage massNotification(HttpServletRequest request, String id, String content) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		// 记录日志
		InviteLog inviteLog = new InviteLog();
		inviteLog.setId(Utils.getUUID());
		inviteLog.setContent(content);
		inviteLog.setCreateTime(new Date());
		inviteLog.setInviteballId(id);
		inviteLog.setType(1);
		inviteLog.setMemberId(member.getId());
		//群发通知需发送微信公众号消息所有已加入的活动的人
		
		
		
		
		
		
		
		return new ApiMessage(200, "群发成功");
	}
	
	
	/**  
	 * @Description: 用户取消报名
	 * @author 宋高俊  
	 * @param request
	 * @param id
	 * @param content
	 * @return 
	 * @date 2018年9月17日 上午9:25:17 
	 */ 
	@RequestMapping(value = "/member/cancelInviteBall")
	@ResponseBody
	public ApiMessage memberCancelInviteBall(HttpServletRequest request, String id) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		InviteBall inviteBall = inviteBallService.selectByPrimaryKey(id);

		if (inviteBall == null) {
			return new ApiMessage(400, "活动不存在");
		}
		if (inviteBall.getEndTime().getTime() <= new Date().getTime()) {
			return new ApiMessage(400, "活动已开始,不允许退出");
		}
		
		if (inviteBall.getExitFlag() == 0) {
			return new ApiMessage(400, "不允许退出");
		}
		
		InviteJoin inviteJoin = inviteJoinMapper.selectByJoinMemberKey(id, member.getId());
		if (inviteJoin == null) {
			return new ApiMessage(400, "您还没有报名");
		}
		
		inviteJoin.setPayTime(new Date());
		//判断是否预收费用
		if (inviteBall.getReceiveFlag() == 1) {
			//如果已预收费用则退款
			inviteJoin.setPayType(3);
			float fee = (float) inviteBall.getExitFee() / 100;
			DecimalFormat df = new DecimalFormat("0.00");// 格式化小数
			try {
				Number refundAmount = df.parse(df.format(inviteJoin.getAmount() * fee));
				inviteJoin.setRefundAmount(refundAmount.doubleValue());
				inviteJoin.setRefundFee(inviteBall.getExitFee());
				
				//执行退款代码
				
				
				
				
				
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else {
			//如果未收费用则直接修改状态
			inviteJoin.setPayType(2);
		}
		int flag = inviteJoinMapper.updateByPrimaryKeySelective(inviteJoin);
		if (flag > 0) {
			return new ApiMessage(200, "取消成功");
		}else {
			return new ApiMessage(400, "取消失败");
		}
	}
	
}
