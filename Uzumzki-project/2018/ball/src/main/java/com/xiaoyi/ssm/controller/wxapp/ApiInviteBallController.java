package com.xiaoyi.ssm.controller.wxapp;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

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
	public ApiMessage saveInviteBall(HttpServletRequest request, InviteBall inviteBall, String urls, String endTimeStr, String activeTimeStr) {
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		
		if (member != null) {
			if (inviteBall.getReceiveFlag() == 1 && StringUtil.isBank(member.getPhone())) {
				return new ApiMessage(401, "手机号未绑定");
			}
			
			// 用于返回给前端的ID
			String id = Utils.getUUID();
			// 需要根据条件判断是否允许为空
			if(inviteBall.getToRequire() == 0){
				
			}
			inviteBall.setEndTime(DateUtil.getParse(endTimeStr, "yyyy-MM-dd HH:mm"));
			inviteBall.setId(id);
			inviteBall.setMemberId(member.getId());
			inviteBall.setCreateTime(new Date());
			inviteBall.setModifyTime(new Date());
			inviteBall.setActiveTime(DateUtil.getParse(activeTimeStr, "yyyy-MM-dd HH:mm"));// 活动时间默认等于报名截止时间
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
					inviteImage.setHeadImage(i+1);
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
//		inviteDto.setMaxlng(Arith.add(lng, 0.5));
//		inviteDto.setMaxlat(Arith.add(lat, 0.5));
//
//		inviteDto.setMinlng(Arith.sub(lng, 0.5));
//		inviteDto.setMinlat(Arith.sub(lat, 0.5));
		
		inviteDto.setLng(lng);
		inviteDto.setLat(lat);

		// 开始分页
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<InviteBall> list = inviteBallService.selectByNearby(inviteDto);
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Date date = new Date();
		for (int i = 0; i < list.size(); i++) {
			InviteBall inviteBall = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", inviteBall.getId());// ID

			map.put("area", (int)MapUtils.getDistance(lng, lat, inviteBall.getLongitude(), inviteBall.getLatitude()));// 距离
			InviteImage inviteImage = inviteImageService.selectByHeadID(inviteBall.getId());
			if (inviteImage != null) {
				map.put("image", inviteImage.getUrl());// 图片
			} else {
				map.put("image", "");// 图片
			}
			map.put("title", inviteBall.getTitle());// 标题

			long time = DateUtil.getTimeDifference(date, inviteBall.getEndTime(), 3);
			if (time == 0) {
				time = DateUtil.getTimeDifference(date, inviteBall.getEndTime(), 2);
				if (time == 0) {
					time = DateUtil.getTimeDifference(date, inviteBall.getEndTime(), 1);
					if (time == 0) {
						time = DateUtil.getTimeDifference(date, inviteBall.getEndTime(), 0);
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
			
			// 根据约球ID查询加入人员
			List<InviteJoin> inviteJoins = inviteJoinMapper.selectByJoinMember(inviteBall.getId());
			List<String> joinList = new ArrayList<String>();
			for (int j = 0; j < inviteJoins.size(); j++) {
				joinList.add(inviteJoins.get(j).getMember().getAppavatarurl());
			}
			map.put("joinMember", joinList);// 已加入人员头像
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

		InviteJoin inviteJoin = inviteJoinMapper.selectByJoinMemberKey(id, member.getId());
		if (inviteJoin == null) {
			return new ApiMessage(400, "请报名后留言");
		}
		
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
	 * @param request
	 * @param id
	 * @return 
	 * @date 2018年9月19日 下午2:17:26 
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
					if (inviteJoin.getRefundFlag() == 0) {
						map.put("applyFlag", 1);// 已报名
					} else if (inviteJoin.getRefundFlag() == 2) {
						map.put("applyFlag", 2);// 取消报名申请中
					} else if (inviteJoin.getRefundFlag() == 1) {
						map.put("applyFlag", 0);// 未报名
					}
				}else {
					map.put("applyFlag", 0);// 未报名
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
		map.put("nowJoinMember", inviteJoins.size());// 当前报名人数
		map.put("maxJoinMember", inviteBall.getMaxBoy());// 最大报名人数

		map.put("title", inviteBall.getTitle());// 标题
		map.put("time", DateUtil.getFormat(inviteBall.getEndTime(), "yyyy年M月dd日 HH:mm"));// 活动时间
		map.put("venuename", inviteBall.getVenueName());// 场馆名称
		map.put("venuenAddress", inviteBall.getVenueAddress());// 场馆名称
		map.put("lng", inviteBall.getLongitude());// 经度
		map.put("lat", inviteBall.getLatitude());// 纬度
		map.put("endTime", DateUtil.getFormat(inviteBall.getEndTime()));// 结束时间

		map.put("receiveFlag", inviteBall.getReceiveFlag());// 是否预收费用(1=是0=否)
		map.put("receiveAmount", inviteBall.getReceiveAmount());// 预收金额
		map.put("receiveType", inviteBall.getReceiveType());// 预收类型(1=固定收费2=AA收费)
		
		map.put("exitFlag", inviteBall.getExitFlag());// 是否允许退出(1=是0=否)
		map.put("exitFee", inviteBall.getExitFee());// 退出手续费
		map.put("exitType", inviteBall.getExitType());// 退出类型(1=直接退出2=需发起人同意)

		if (inviteBall.getMinBoy() != null && joinList.size() >= inviteBall.getMinBoy()) {
			map.put("teamFlag", 1);// 是否成团
		}else {
			map.put("teamFlag", 0);// 是否成团
		}
		map.put("content", inviteBall.getContent());// 约球详情
		map.put("ballType", inviteBall.getBallType());// 活动状态0=发起中1=到期截止2=提前截止3=取消活动
		
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

		Integer joinSum = inviteJoinMapper.countByJoinBall(id);
		
		if (inviteBall.getMaxBoy() <= joinSum) {
			return new ApiMessage(400, "报名人数已满");
		}

		if (inviteBall.getNameFlag() == 1 && StringUtil.isBank(name)) {
			return new ApiMessage(400, "发起人要求,姓名号必填");
		}
		if (inviteBall.getWechatFlag() == 1 && StringUtil.isBank(wechatid)) {
			return new ApiMessage(400, "发起人要求,微信号必填");
		}
		if (inviteBall.getPhoneFlag() == 1 && StringUtil.isBank(phone)) {
			return new ApiMessage(400, "发起人要求,手机号必填");
		}
		InviteJoin nowInviteJoin = inviteJoinMapper.selectByJoinMemberKey(id, member.getId());
		
		if (nowInviteJoin != null) {
			return new ApiMessage(400, "不允许重复报名");
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
			final String inviteJoinID = inviteJoin.getId();
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
			}else {
				// 发送报名成功通知
				JSONObject datajson = new JSONObject();
				datajson.put("first", JSONObject.parseObject("{\"value\":\"恭喜您报名成功\"}"));
				datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + inviteBall.getTitle() + "\"}"));
				datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"" + "暂无" + "\"}"));
				datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(inviteBall.getActiveTime(),"yyyy-MM-dd HH:mm") + "\"}"));
				datajson.put("keyword4", JSONObject.parseObject("{\"value\":\"" + inviteBall.getVenueName() + "\"}"));
				datajson.put("keyword5", JSONObject.parseObject("{\"value\":\"" + "暂无" + "\"}"));
				datajson.put("remark", JSONObject.parseObject("{\"value\":\"请按时参加活动\"}"));
				logger.info(WXUtil.sendWXappTemplate(member.getOpenid(), WXConfig.templateId1, "/pages/index/index?id="+id, datajson));

				// 创建约球加入日志
				InviteLog inviteLog = new InviteLog(Utils.getUUID(), new Date(), member.getId(), id, member.getAppnickname() + "加入约球", 1);
				inviteLogMapper.insertSelective(inviteLog);
			}
			return new ApiMessage(200, "报名成功", inviteJoin.getId());
		} else {
			return new ApiMessage(400, "报名失败");
		}
	}
	
	/**
	 * @Description: 公众号获取支付参数
	 * @author 宋高俊
	 * @param token
	 * @return
	 * @throws IOException
	 * @throws JDOMException
	 * @date 2018年9月14日 上午9:14:40
	 */
	@SuppressWarnings("rawtypes")
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
	@SuppressWarnings("rawtypes")
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

		Map map = WXUtil.wxappToPay("报名活动费用", inviteJoin.getId(), member.getAppopenid(), inviteJoin.getAmount(), request.getRemoteAddr(), WXConfig.APPNOTIFY_URL);
		
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
	@SuppressWarnings({ "unchecked"})
	@RequestMapping(value = "/weixinNotify", method = RequestMethod.POST)
	public void weixinNotify(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException {
		logger.info("小程序支付回调");
		// 读取参数
		InputStream inputStream = request.getInputStream();
		StringBuffer sb = new StringBuffer();
		String string;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while ((string = in.readLine()) != null) {
			sb.append(string);
		}
		in.close();
		inputStream.close();
		logger.info("xml参数 = " + sb.toString());
		// 解析xml成map
		Map<String, String> packageParams = new HashMap<String, String>();
		packageParams = XMLUtil.doXMLParse(sb.toString());

		// 判断签名是否正确
		if (WXUtil.isTenpaySign("UTF-8", packageParams, WXConfig.paternerKey)) {

			logger.info("微信支付成功回调");
			// ------------------------------
			// 处理业务开始
			// ------------------------------
			String resXml = "";
			if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
				// 这里是支付成功
				String orderNo = (String) packageParams.get("out_trade_no");
				String total_fee = (String) packageParams.get("total_fee");
				logger.info("商户订单号{}付款成功,金额{}", orderNo, total_fee);
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

					// 获取报名人数据
					Member member = memberService.selectByPrimaryKey(inviteJoin.getMemberId());
					// 获取约球数据
					InviteBall inviteBall = inviteBallService.selectByPrimaryKey(inviteJoin.getInviteId());
					
					// 发送报名成功通知
					JSONObject datajson = new JSONObject();
					datajson.put("first", JSONObject.parseObject("{\"value\":\"恭喜您报名成功\"}"));
					datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + inviteBall.getTitle() + "\"}"));
					datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"" + "暂无" + "\"}"));
					datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(inviteBall.getActiveTime(),"yyyy-MM-dd HH:mm") + "\"}"));
					datajson.put("keyword4", JSONObject.parseObject("{\"value\":\"" + inviteBall.getVenueName() + "\"}"));
					datajson.put("keyword5", JSONObject.parseObject("{\"value\":\"" + "暂无" + "\"}"));
					datajson.put("remark", JSONObject.parseObject("{\"value\":\"请按时参加活动\"}"));
					logger.info(WXUtil.sendWXappTemplate(member.getOpenid(), WXConfig.templateId1, "/pages/index/index?id="+inviteBall.getId(), datajson));
					
					// 创建约球加入日志
					InviteLog inviteLog = new InviteLog(Utils.getUUID(), new Date(),inviteJoin.getMemberId(), inviteJoin.getInviteId(), member.getAppnickname() + "加入约球", 1);
					inviteLogMapper.insertSelective(inviteLog);
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
	 * @Description: 获取报名名单人员
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
			map.put("appnickname", inviteJoin.getMember().getAppnickname());// 昵称
			map.put("content", inviteJoin.getContent());// 加入备注
			map.put("joinsum", "0");// 转发邀请数量
			if (inviteJoin.getRefundFlag() == 2) {
				map.put("exitFlag", "1");// 是否有取消报名申请
			}else {
				map.put("exitFlag", "0");// 是否有取消报名申请
			}
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
					inviteImage.setHeadImage(i + 1);
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
			datajson.put("first", JSONObject.parseObject("{\"value\":\"你报名的活动已经取消\"}"));
			datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + inviteBall.getTitle() + "\"}"));
			datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"" + inviteBall.getVenueName() + "\"}"));
			datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(inviteBall.getActiveTime(), "yyyy年MM月dd日 HH:mm") + "\"}"));
			datajson.put("remark", JSONObject.parseObject("{\"value\":\"活动已取消，欢迎您下次参加活动\"}"));

			// 根据约球ID查询加入人员
			List<InviteJoin> list = inviteJoinMapper.selectByJoinMember(id);
			for (int i = 0; i < list.size(); i++) {
				InviteJoin inviteJoin = list.get(i);
				if (!StringUtil.isBank(inviteJoin.getMember().getOpenid())) {
					logger.info(WXUtil.sendWXappTemplate(inviteJoin.getMember().getOpenid(), WXConfig.templateId3, "/pages/index/index?id=" + id, datajson));
					if (inviteBall.getReceiveFlag() == 1) {
						// 如果已预收费用则退款
						inviteJoin.setPayType(3);
						inviteJoin.setRefundAmount(inviteJoin.getAmount());
						inviteJoin.setRefundFee(0);
						inviteJoin.setRefundFeeamount(0.0);

						// 发起人取消活动全额退款
						WXUtil.weiXinRefund(inviteJoin.getId(), inviteJoin.getAmount(), inviteJoin.getAmount(), "发起人取消活动", 1);

						JSONObject datajson2 = new JSONObject();
						datajson2.put("first", JSONObject.parseObject("{\"value\":\"您报名的活动已退款\"}"));
						datajson2.put("keyword1", JSONObject.parseObject("{\"value\":\"" + inviteJoin.getAmount() + "元\"}"));
						datajson2.put("keyword2", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(inviteJoin.getPayTime(), "yyyy年MM月dd日 HH:mm") + "\"}"));
						datajson2.put("keyword3", JSONObject.parseObject("{\"value\":\"" + inviteJoin.getId() + "\"}"));
						datajson2.put("remark", JSONObject.parseObject("{\"value\":\"已完成退款\"}"));
						logger.info(WXUtil.sendWXappTemplate(inviteJoin.getMember().getOpenid(), WXConfig.templateId2, "/pages/index/index?id=" + id, datajson2));
					} else {
						inviteJoin.setPayType(2);
					}
					inviteJoinMapper.updateByPrimaryKeySelective(inviteJoin);
				}
			}
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
		inviteBall.setEndTime(new Date());
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
	public ApiMessage memberCancelInviteBall(HttpServletRequest request, String id, String content) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		InviteBall inviteBall = inviteBallService.selectByPrimaryKey(id);

		if (inviteBall == null) {
			return new ApiMessage(400, "活动不存在");
		}
		if (inviteBall.getEndTime().getTime() <= new Date().getTime()) {
			return new ApiMessage(400, "活动已开始,不允许退出");
		}
		InviteJoin inviteJoin = inviteJoinMapper.selectByJoinMemberKey(id, member.getId());
		if (inviteJoin == null) {
			return new ApiMessage(400, "您还没有报名");
		}
		if (inviteBall.getExitFlag() == 0) {
			return new ApiMessage(400, "不允许退出");
		}
		
		inviteJoin.setPayTime(new Date());
		if (inviteBall.getExitType() == 1) {
			inviteJoin.setRefundFlag(1);
			//判断是否预收费用
			if (inviteBall.getReceiveFlag() == 1) {
				//如果已预收费用则退款
				inviteJoin.setPayType(3);
				// 计算手续费
		    	float fee = (float) inviteBall.getExitFee() / 100;
				// 计算金额
				BigDecimal a = new BigDecimal(String.valueOf(fee));
				BigDecimal b = new BigDecimal(inviteJoin.getAmount());
				// 手续费金额
				Double feesum= (double)Math.round(a.multiply(b).doubleValue()*100)/100;
				// 退费金额
				double refund_fee = Arith.sub(inviteJoin.getAmount(), feesum);
				
				inviteJoin.setRefundAmount(refund_fee);
				inviteJoin.setRefundFee(inviteBall.getExitFee());
				inviteJoin.setRefundFeeamount(feesum);

				JSONObject datajson = new JSONObject();
				datajson.put("first", JSONObject.parseObject("{\"value\":\"您已取消报名活动\"}"));
				datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + inviteBall.getTitle() + "\"}"));
				datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"" + inviteBall.getVenueName() + "\"}"));
				datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(inviteBall.getActiveTime(),"yyyy年MM月dd日 HH:mm") + "\"}"));
				datajson.put("remark", JSONObject.parseObject("{\"value\":\"取消成功，欢迎您下次参加活动\"}"));
				logger.info(WXUtil.sendWXappTemplate(member.getOpenid(), WXConfig.templateId3, "/pages/index/index?id="+id, datajson));
				
				//执行退款代码
				WXUtil.weiXinRefund(inviteJoin.getId(), inviteJoin.getAmount(), refund_fee, "报名人退出报名", 1);
				
				JSONObject datajson2 = new JSONObject();
				datajson2.put("first", JSONObject.parseObject("{\"value\":\"您报名的活动已退款\"}"));
				datajson2.put("keyword1", JSONObject.parseObject("{\"value\":\"" + refund_fee + "元(扣除手续费"+feesum+"元)\"}"));
				datajson2.put("keyword2", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(inviteJoin.getPayTime(),"yyyy年MM月dd日 HH:mm") + "\"}"));
				datajson2.put("keyword3", JSONObject.parseObject("{\"value\":\"" + inviteJoin.getId() + "\"}"));
				datajson2.put("remark", JSONObject.parseObject("{\"value\":\"已完成退款\"}"));
				logger.info(WXUtil.sendWXappTemplate(member.getOpenid(), WXConfig.templateId2, "/pages/index/index?id="+id, datajson2));
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
		}else {
			if(content == null){
				return new ApiMessage(400, "请填写退出理由");
			}
			inviteJoin.setRefundFlag(2);
			inviteJoin.setRefundContent(content);
			
			JSONObject datajson = new JSONObject();
			datajson.put("first", JSONObject.parseObject("{\"value\":\"您正在申请取消报名活动\"}"));
			datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + inviteBall.getTitle() + "\"}"));
			datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"" + inviteBall.getVenueName() + "\"}"));
			datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(inviteBall.getActiveTime(),"yyyy年MM月dd日 HH:mm") + "\"}"));
			datajson.put("remark", JSONObject.parseObject("{\"value\":\"请关注后续消息\"}"));
			logger.info(WXUtil.sendWXappTemplate(member.getOpenid(), WXConfig.templateId3, "/pages/index/index?id="+id, datajson));
			
			int flag = inviteJoinMapper.updateByPrimaryKeySelective(inviteJoin);
			if (flag > 0) {
				// 创建约球退出日志
				InviteLog inviteLog = new InviteLog(Utils.getUUID(), new Date(),inviteJoin.getMemberId(), inviteJoin.getInviteId(), member.getAppnickname() + "申请退出报名", 1);
				inviteLogMapper.insertSelective(inviteLog);
				return new ApiMessage(200, "退出申请已发送给发起人");
			}else {
				return new ApiMessage(400, "退出申请失败");
			}
		}
	}
	
	/**  
	 * @Description: 处理退出请求
	 * @author 宋高俊  
	 * @param request 验证身份
	 * @param id 加入ID
	 * @param type 0=取消报名资格1=处理退出申请
	 * @return 
	 * @date 2018年9月19日 下午2:54:11 
	 */ 
	@RequestMapping(value = "/disposeExit")
	@ResponseBody
	public ApiMessage disposeExir(HttpServletRequest request,String id, Integer type) {

		InviteBall inviteBall = inviteBallService.selectByJoinKey(id);
		InviteJoin inviteJoin = inviteJoinMapper.selectByPrimaryKey(id);
		Member member = memberService.selectByPrimaryKey(inviteJoin.getMemberId());

		inviteJoin.setPayType(2);
		inviteJoin.setPayTime(new Date());
		inviteJoin.setRefundFlag(1);
		if (type == 0) {
			JSONObject datajson = new JSONObject();
			datajson.put("first", JSONObject.parseObject("{\"value\":\"您报名的活动已被取消资格\"}"));
			datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + inviteBall.getTitle() + "\"}"));
			datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"" + inviteBall.getVenueName() + "\"}"));
			datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(inviteBall.getActiveTime(),"yyyy年MM月dd日 HH:mm") + "\"}"));
			datajson.put("remark", JSONObject.parseObject("{\"value\":\"如有疑问请联系发起人，欢迎您下次参加活动\"}"));
			logger.info(WXUtil.sendWXappTemplate(member.getOpenid(), WXConfig.templateId3, "/pages/index/index?id="+id, datajson));
			
			// 创建约球退出日志
			InviteLog inviteLog = new InviteLog(Utils.getUUID(), new Date(),inviteJoin.getMemberId(), inviteJoin.getInviteId(), member.getAppnickname() + "被取消资格", 1);
			inviteLogMapper.insertSelective(inviteLog);
		}else {
			// 创建约球退出日志
			InviteLog inviteLog = new InviteLog(Utils.getUUID(), new Date(),inviteJoin.getMemberId(), inviteJoin.getInviteId(), member.getAppnickname() + "申请退出同意", 1);
			inviteLogMapper.insertSelective(inviteLog);
		}
		//判断是否预收费用
		if (inviteBall.getReceiveFlag() == 1) {
			//如果已预收费用则退款
			inviteJoin.setPayType(3);
			// 计算手续费
	    	float fee = (float) inviteBall.getExitFee() / 100;
			// 计算金额
			BigDecimal a = new BigDecimal(String.valueOf(fee));
			BigDecimal b = new BigDecimal(inviteJoin.getAmount());
			// 手续费金额
			Double feesum= (double)Math.round(a.multiply(b).doubleValue()*100)/100;
			// 退费金额
			double refund_fee = Arith.sub(inviteJoin.getAmount(), feesum);
			
			inviteJoin.setRefundAmount(refund_fee);
			inviteJoin.setRefundFee(inviteBall.getExitFee());
			inviteJoin.setRefundFeeamount(feesum);
			
			if (type == 0) {
				//发起人取消资格全额退款
				WXUtil.weiXinRefund(inviteJoin.getId(), inviteJoin.getAmount(), inviteJoin.getAmount(), "发起人取消报名资格", 1);
			}else {
				//执行退款代码
				WXUtil.weiXinRefund(inviteJoin.getId(), inviteJoin.getAmount(), refund_fee, "发起人取消报名资格", 1);
			}
			
			JSONObject datajson2 = new JSONObject();
			datajson2.put("first", JSONObject.parseObject("{\"value\":\"您报名的活动已退款\"}"));
			datajson2.put("keyword1", JSONObject.parseObject("{\"value\":\"" + refund_fee + "元(扣除手续费"+feesum+"元)\"}"));
			datajson2.put("keyword2", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(inviteJoin.getPayTime(),"yyyy年MM月dd日 HH:mm") + "\"}"));
			datajson2.put("keyword3", JSONObject.parseObject("{\"value\":\"" + inviteJoin.getId() + "\"}"));
			datajson2.put("remark", JSONObject.parseObject("{\"value\":\"已完成退款\"}"));
			logger.info(WXUtil.sendWXappTemplate(member.getOpenid(), WXConfig.templateId2, "/pages/index/index?id="+id, datajson2));
			

		}
		int flag = inviteJoinMapper.updateByPrimaryKey(inviteJoin);
		if (flag > 0) {
			return new ApiMessage(200, "操作成功");
		}else {
			return new ApiMessage(400, "退出申请失败");
		}
	}
	

	/**  
	 * @Description: 处理退出请求详情
	 * @author 宋高俊  
	 * @param request 验证身份
	 * @param id 加入ID
	 * @return 
	 * @date 2018年9月19日 下午2:54:11 
	 */ 
	@RequestMapping(value = "/disposeExitDetails")
	@ResponseBody
	public ApiMessage disposeExitDetails(HttpServletRequest request,String id) {

		InviteBall inviteBall = inviteBallService.selectByJoinKey(id);
		InviteJoin inviteJoin = inviteJoinMapper.selectByPrimaryKey(id);
		Member member = memberService.selectByPrimaryKey(inviteJoin.getMemberId());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", inviteJoin.getId());// 头像URL
		map.put("appavatarurl", member.getAppavatarurl());// 头像URL
		map.put("appnickname", member.getAppnickname());// 昵称
		map.put("time", DateUtil.getFormat(inviteJoin.getPayTime()));// 申请时间
		map.put("content", inviteJoin.getRefundContent());// 理由
		
		if (inviteBall.getReceiveFlag() == 1) {
			map.put("fee", inviteBall.getExitFee());// 费率
			
			// 计算手续费
			float fee = (float) inviteBall.getExitFee() / 100;
			// 计算金额
			BigDecimal a = new BigDecimal(String.valueOf(fee));
			BigDecimal b = new BigDecimal(inviteJoin.getAmount());
			// 手续费金额
			Double feesum= (double)Math.round(a.multiply(b).doubleValue()*100)/100;
			// 退费金额
			double refund_fee = Arith.sub(inviteJoin.getAmount(), feesum);
			
			map.put("amount", refund_fee);// 最终返回金额
		}
		return new ApiMessage(200, "获取成功", map);
	}
	
}
