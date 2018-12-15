package com.xiaoyi.ssm.controller.wxapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.xiaoyi.ssm.dao.MemberHabitMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.PageBean;
import com.xiaoyi.ssm.model.InviteBall;
import com.xiaoyi.ssm.model.InviteImage;
import com.xiaoyi.ssm.model.InviteJoin;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.Reserve;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.service.InviteBallService;
import com.xiaoyi.ssm.service.InviteImageService;
import com.xiaoyi.ssm.service.InviteJoinService;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.NewsCollectService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.ReserveService;
import com.xiaoyi.ssm.service.TrainCoachService;
import com.xiaoyi.ssm.service.TrainCourseCollectService;
import com.xiaoyi.ssm.service.TrainOrderService;
import com.xiaoyi.ssm.service.TrainTeamCollectService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.MoblieMessageUtil;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.AES;
import com.xiaoyi.ssm.wxPay.WXConfig;

import net.sf.json.JSONObject;

/**
 * @Description: 微信小程序公共接口
 * @author 宋高俊
 * @date 2018年9月11日 下午4:22:23
 */
@Controller("wxappMemberController")
@RequestMapping("wxapp/member")
public class ApiMemberController {

    private static Logger logger = Logger.getLogger(ApiMemberController.class.getName());
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private InviteBallService inviteBallService;
	@Autowired
	private InviteImageService inviteImageService;
	@Autowired
	private InviteJoinService inviteJoinService;
	@Autowired
	private TrainOrderService trainOrderService;
	@Autowired
	private TrainCoachService trainCoachService;
	@Autowired
	private MemberHabitMapper memberHabitMapper;
	@Autowired
	private TrainTeamCollectService trainTeamCollectService;
	@Autowired
	private TrainCourseCollectService trainCourseCollectService;
	@Autowired
	private NewsCollectService newsCollectService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private VenueService venueService;
	@Autowired
	private ReserveService reserveService;

	/**
	 * @Description: 查询我参与的约球
	 * @author 宋高俊
	 * @param request
	 * @return
	 * @date 2018年9月18日 下午5:41:33
	 */
	@RequestMapping(value = "/getMyJoinInviteBall", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage getMyJoinInviteBall(HttpServletRequest request, PageBean pageBean) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		// 开始分页
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<InviteBall> list = inviteBallService.selectByMyInviteBall(member.getId());
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			InviteBall inviteBall = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", inviteBall.getId());// ID
			InviteImage inviteImage = inviteImageService.selectByHeadID(inviteBall.getId());
			if (inviteImage != null) {
				map.put("image", inviteImage.getUrl());// 封面图片
			} else {
				map.put("image", "");// 封面图片
			}
			map.put("title", inviteBall.getTitle());// 标题
			map.put("activeTime", DateUtil.getFormat(inviteBall.getActiveTime(),"yyyy-MM-dd HH:mm"));// 活动时间
			map.put("ballType", inviteBall.getBallType());// 约球状态(0=发起中1=到期截止2=提前截止3=取消活动)

			Integer exit = 0;
			if (inviteBall.getExitFlag() == 1) {
				InviteJoin inviteJoin = inviteJoinService.selectByJoinMemberKey(inviteBall.getId(), member.getId());
				// 没有提出退出申请的
				if (inviteJoin.getRefundFlag() != 2) {
					long nowDate = new Date().getTime();// 当前时间
					long nowDateEnd = DateUtil.getWeeHours(inviteBall.getActiveTime(), 1).getTime();// 活动时间当天的23:59:59
					// 活动状态
					if (nowDateEnd > nowDate) {
						if (inviteBall.getExitType() == 1) {
							exit = 1;// 活动状态，当前时间未过当天23:59:59可以退出
						}else {
							exit = 2;// 活动状态，当前时间未过当天23:59:59可以退出
						}
					}
				}
			}
			map.put("exit", exit);// 是否显示退出按钮
			// 根据约球ID查询加入人员
			List<InviteJoin> inviteJoins = inviteJoinService.selectByJoinMember(inviteBall.getId());
			List<String> joinList = new ArrayList<String>();
			for (int j = 0; j < inviteJoins.size(); j++) {
				joinList.add(inviteJoins.get(j).getMember().getAppavatarurl());
			}
			map.put("joinMember", joinList);// 已加入人员头像
			listmap.add(map);
		}
		return new ApiMessage(200, "登录成功", listmap);
	}
	
	/**
	 * @Description: 查询我发起的约球
	 * @author 宋高俊
	 * @param request
	 * @return
	 * @date 2018年9月18日 下午5:41:33
	 */
	@RequestMapping(value = "/getMyApplyInviteBall", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage getMyApplyInviteBall(HttpServletRequest request, PageBean pageBean) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		// 开始分页
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<InviteBall> list = inviteBallService.selectByMyApplyInviteBall(member.getId());
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", list.get(i).getId());// ID
			InviteImage inviteImage = inviteImageService.selectByHeadID(list.get(i).getId());
			if (inviteImage != null) {
				map.put("image", inviteImage.getUrl());// 封面图片
			} else {
				map.put("image", "");// 封面图片
			}
			map.put("title", list.get(i).getTitle());// 标题
			map.put("activeTime", DateUtil.getFormat(list.get(i).getActiveTime(),"yyyy-MM-dd HH:mm"));// 活动时间
			map.put("ballType", list.get(i).getBallType());// 约球状态(0=发起中1=到期截止2=提前截止3=取消活动)
			// 根据约球ID查询加入人员
			List<InviteJoin> inviteJoins = inviteJoinService.selectByJoinMember(list.get(i).getId());
			List<String> joinList = new ArrayList<String>();
			for (int j = 0; j < inviteJoins.size(); j++) {
				joinList.add(inviteJoins.get(j).getMember().getAppavatarurl());
			}
			map.put("joinMember", joinList);// 已加入人员头像
			listmap.add(map);
		}
		return new ApiMessage(200, "登录成功", listmap);
	}

	/**  
	 * @Description: 统计数量接口
	 * @author 宋高俊  
	 * @param request
	 * @param pageBean
	 * @return 
	 * @date 2018年9月21日 上午9:12:12 
	 */ 
	@RequestMapping(value = "/getMyJoinCount", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage getMyJoinCount(HttpServletRequest request, PageBean pageBean) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		Integer myJoinBall = inviteJoinService.countByMyJoinBall(member.getId());
		Integer myJoinApplyBall = inviteBallService.countByMyApplyBall(member.getId());
		Integer myTrainOrder = trainOrderService.countByMyTrainOrder(member.getId());
		Integer venueSum = orderService.countByMember(member.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("myJoinBall", myJoinBall);// 我参与的约球总数
		map.put("myJoinApplyBall", myJoinApplyBall);// 我发起的约球总数
		map.put("myTrainOrder", myTrainOrder);// 我的私教总数
		map.put("venueSum", venueSum);// 我的订场总数
		int collectSum = 0;
		collectSum += memberHabitMapper.countOften(member.getId());
		collectSum += trainTeamCollectService.countByMember(member.getId());
		collectSum += trainCourseCollectService.countByMember(member.getId());
		collectSum += newsCollectService.countByMember(member.getId());
		map.put("collectSum", collectSum);// 收藏总数
		
		TrainCoach trainCoach = trainCoachService.selectByMember(member.getId());
		if (trainCoach != null ) {
			map.put("coachFlag", 1);// 是教练
		}else {
			map.put("coachFlag", 0);// 不是教练
		}
		map.put("coverimage", member.getCoverimage());// 封面背景图
		
		return new ApiMessage(200, "查询成功", map);
	}
	
	/**
	 * @Description: 微信解密数据接口
	 * @author 宋高俊
	 * @param request
	 * @throws Exception
	 * @date 2018年9月11日 下午4:24:23
	 */
	@RequestMapping(value = "/getPhone", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage getPhone(HttpServletRequest request, String encryptedData, String iv){
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		logger.info(encryptedData + "->" + member.getSessionKey() + "->" + iv);
		String result = "";
		try {
			result = AES.decrypt(encryptedData, member.getSessionKey(), iv, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != result && result.length() > 0) {
			JSONObject json = JSONObject.fromObject(result);
			member.setPhone(json.getString("phoneNumber"));
			memberService.updateByPrimaryKeySelective(member);
			RedisUtil.addRedis(Global.redis_member, token, member);
			return new ApiMessage(200, "获取成功", json);
		}
		return new ApiMessage(400, "获取失败");
	}
	
	/**
	 * @Description: 后台注册获取验证码
	 * @author 宋高俊
	 * @date 2018年7月25日 下午1:43:06
	 */
	@RequestMapping(value = "/getSMSCode")
	@ResponseBody
	public ApiMessage getSMSCode(String phone, HttpServletRequest request) {
		if (Utils.getPhone(phone)) {
			Member oldmember = memberService.selectByPhone(phone);
			String smsCode = Utils.getCode();
			try {
				if (oldmember != null) {
					return new ApiMessage(400, "该手机号码已被使用");
				}
				MoblieMessageUtil.sendIdentifyingCode(phone, smsCode);
				RedisUtil.setRedis(Global.wxapp_member_SmsCode_ + phone, smsCode, 120);
				return new ApiMessage(200, "发送成功");
			} catch (Exception e) {
				return ApiMessage.error("发送次数已达上限,请次日使用验证功能");
			}
		} else {
			return new ApiMessage(400, "请输入正确的手机号码");
		}
	}

	/**
	 * @Description: 绑定手机号
	 * @author 宋高俊
	 * @param phone
	 * @param code
	 * @param request
	 * @return
	 * @date 2018年9月22日 下午5:05:00
	 */
	@RequestMapping(value = "/bindPhone")
	@ResponseBody
	public ApiMessage bindPhone(String phone, String code, HttpServletRequest request) {
		if (StringUtil.isBank(phone, code)) {
			return new ApiMessage(400, "手机号和验证码必填");
		}

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		String flagCode = (String) RedisUtil.getRedis(Global.wxapp_member_SmsCode_ + phone);
		if (code.equals(flagCode)) {
			// 验证成功
			member.setPhone(phone);
			int flag = memberService.updateByPrimaryKeySelective(member);
			RedisUtil.addRedis(Global.redis_member, token, member);
			if (flag > 0) {
				return new ApiMessage(200, "绑定成功");
			} else {
				return new ApiMessage(400, "绑定失败");
			}
		} else {
			return new ApiMessage(400, "验证码不正确");
		}
	}
	
	/**  
	 * @Description: 个人设置数据
	 * @author 宋高俊  
	 * @param request
	 * @return 
	 * @date 2018年10月12日 上午11:30:37 
	 */ 
	@RequestMapping(value = "/getMember")
	@ResponseBody
	public ApiMessage getMember(HttpServletRequest request) {

		String token = (String) request.getAttribute("token");
		Member oldMember = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		Member member = memberService.selectByPrimaryKey(oldMember.getId());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberno", member.getMemberno()); //用户编号
		map.put("phone", member.getPhone()); //绑定手机号
		map.put("showphone", member.getShowphone()); //活动发起人展示手机号
		map.put("showhistory", member.getShowhistory()); //是否显示历史活动
		map.put("wechatid", member.getWechatid()); //微信号
		map.put("showwechatid", member.getShowwechatid()); //微信号-发起报名时允许查看
		map.put("wxpayment", member.getWxpayment() < 0 ? 0 : member.getWxpayment()); // 免手续费额度
		return new ApiMessage(200, "获取成功", map);
	}
	
	/**  
	 * @Description: 修改个人设置数据
	 * @author 宋高俊  
	 * @param request
	 * @return 
	 * @date 2018年10月12日 上午11:30:37 
	 */ 
	@RequestMapping(value = "/updateMember")
	@ResponseBody
	public ApiMessage updateMember(HttpServletRequest request, Integer showphone, Integer showhistory, String wechatid, Integer showwechatid) {

		String token = (String) request.getAttribute("token");
		Member oldMember = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		Member member = memberService.selectByPrimaryKey(oldMember.getId());
		member.setShowphone(showphone); //活动发起人展示手机号
		member.setShowhistory(showhistory); //是否显示历史活动
		member.setWechatid(wechatid); //微信号
		member.setShowwechatid(showwechatid); //微信号-发起报名时允许查看
		try {
			memberService.updateByPrimaryKeySelective(member);
			RedisUtil.addRedis(Global.redis_member, token, member);
			return new ApiMessage(200, "修改成功");
		} catch (Exception e) {
			return new ApiMessage(400, "修改失败");
		}
	}
	
	/**  
	 * @Description: 发送预定通知
	 * @author 宋高俊  
	 * @param request
	 * @return 
	 * @date 2018年11月8日 上午11:59:17 
	 */ 
	@RequestMapping(value = "/sendTemplateMessage")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ApiMessage sendTemplateMessage(HttpServletRequest request,String page, String formId, String orderId) {
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		Order order = orderService.selectByPrimaryKey(orderId);
		
		JSONObject json = new JSONObject();
		json.put("touser", member.getAppopenid());
		json.put("template_id", Global.template_id);
		json.put("page", page);
		json.put("form_id", formId);

		// 获取订单数据
		Venue venue = venueService.selectByPrimaryKey(order.getVenueid());
		List<Reserve> listReserve = reserveService.selectByOrder(order.getId());
		String area = "";
		String time = ""; 
		if (listReserve.size() > 0) {
			area = listReserve.get(0).getField().getName();
			time = StringUtil.timeToTimestr(listReserve.get(0).getReservetimeframe().split(","));
		}

		JSONObject jsonObject = new JSONObject();
		JSONObject valueObject1 = new JSONObject();
		valueObject1.put("value", DateUtil.getFormat(order.getCreatetime(), "yyyy年MM月dd日  HH:mm"));// 预约时间：预约时间段
		jsonObject.put("keyword1", valueObject1);
		JSONObject valueObject2 = new JSONObject();
		valueObject2.put("value", venue.getName() + "   " + area);// 预约地点：场馆名+场地
		jsonObject.put("keyword2", valueObject2);
		JSONObject valueObject3 = new JSONObject();
		valueObject3.put("value", DateUtil.getFormat(order.getOrderdate(), "yyyy年MM月dd日") + " " + time);// 预约时间：预约时间段
		jsonObject.put("keyword3", valueObject3);
		JSONObject valueObject4 = new JSONObject();
		valueObject4.put("value", "预约完成，请及时支付，5分钟超时将自动取消。");// 备注
		jsonObject.put("keyword4", valueObject4);
		json.put("data", jsonObject);
		json.put("emphasis_keyword", "");
        logger.info(json.toString());
        
        BufferedReader reader = null;
        try {

    		Map<String, Object> tokenMap = (Map<String, Object>) RedisUtil.getRedisOne(Global.REDIS_ACCESS_TOKEN, WXConfig.wxAppAppid);
            URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+tokenMap.get("access_token"));// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            //一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(json.toString());
            out.flush();
            out.close();
            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            String res = "";
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            reader.close();
    		return new ApiMessage(200, "获取成功",res);
        } catch (IOException e) {
            e.printStackTrace();
    		return new ApiMessage(200, "获取成功");
        }
	}
	
	/**  
	 * @Description: 发送支付通知
	 * @author 宋高俊  
	 * @param request
	 * @return 
	 * @date 2018年11月8日 上午11:59:17 
	 */ 
	@RequestMapping(value = "/sendTemplateMessage2")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ApiMessage sendTemplateMessage2(HttpServletRequest request,String page, String formId, String orderId) {
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		Order order = orderService.selectByPrimaryKey(orderId);
		
		JSONObject json = new JSONObject();
		json.put("touser", member.getAppopenid());
		json.put("template_id", Global.template_id2);
		json.put("page", page);
		json.put("form_id", formId);
		
		// 获取订单数据
		Venue venue = venueService.selectByPrimaryKey(order.getVenueid());
		List<Reserve> listReserve = reserveService.selectByOrder(order.getId());
		String area = "";
		String time = ""; 
		if (listReserve.size() > 0) {
			area = listReserve.get(0).getField().getName();
			time = StringUtil.timeToTimestr(listReserve.get(0).getReservetimeframe().split(","));
		}

		JSONObject jsonObject = new JSONObject();
		
		JSONObject valueObject = new JSONObject();
		valueObject.put("value", DateUtil.getFormat(order.getModifytime(), "yyyy年MM月dd日   HH时mm分"));// 支付时间
		jsonObject.put("keyword1", valueObject);
		
		JSONObject valueObject2 = new JSONObject();
		valueObject2.put("value", order.getOrderno());// 订单编号
		jsonObject.put("keyword2", valueObject2);
		
		JSONObject valueObject3 = new JSONObject();
		valueObject3.put("value", order.getPrice() + "元");// 金额：
		jsonObject.put("keyword3", valueObject3);
		
		JSONObject valueObject4 = new JSONObject();
		valueObject4.put("value", venue.getName() + "   " + area);// 场地名称：场馆名+场地
		jsonObject.put("keyword4", valueObject4);

		JSONObject valueObject5 = new JSONObject();
		valueObject5.put("value", DateUtil.getFormat(order.getOrderdate(), "yyyy年MM月dd日") + " " + time);// 预约时间：
		jsonObject.put("keyword5", valueObject5);

		JSONObject valueObject6 = new JSONObject();
		valueObject6.put("value", "稍候，场馆将在30分钟内反馈预定是否成功...");// 温馨提示：
		jsonObject.put("keyword6", valueObject6);
		
		json.put("data", jsonObject);
		json.put("emphasis_keyword", "");
        logger.info(json.toString());
        
        BufferedReader reader = null;
        try {

    		Map<String, Object> tokenMap = (Map<String, Object>) RedisUtil.getRedisOne(Global.REDIS_ACCESS_TOKEN, WXConfig.wxAppAppid);
            URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+tokenMap.get("access_token"));// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            //一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(json.toString());
            out.flush();
            out.close();
            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            String res = "";
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            reader.close();
    		return new ApiMessage(200, "获取成功",res);
        } catch (IOException e) {
            e.printStackTrace();
    		return new ApiMessage(200, "获取成功");
        }
	}
	
	/**  
	 * @Description: 获取用户数据
	 * @author 宋高俊  
	 * @param request
	 * @return 
	 * @date 2018年10月12日 上午11:30:37 
	 */ 
	@RequestMapping(value = "/getMemberDetails")
	@ResponseBody
	public ApiMessage getMemberDetails(HttpServletRequest request) {
		String token = (String) request.getAttribute("token");
		Member oldMember = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		Member member = memberService.selectByPrimaryKey(oldMember.getId());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subscribeFlag", !StringUtil.isBank(member.getOpenid()));// 是否关注公众号 true 已关注 false未关注
		map.put("phoneFlag", !StringUtil.isBank(member.getPhone()));// 是否绑定手机号 true 已绑定 false未绑定
		map.put("appavatarurl", member.getAppavatarurl());// 头像
		map.put("appnickname", member.getAppnickname());// 昵称
		
		Map<String, Object> dayCountMap = (Map<String, Object>) RedisUtil.getRedisOne(Global.REDIS_DAY_REFUND_COUNT, member.getId());
		String date = DateUtil.getFormat(new Date(), "yyyy-MM-dd");
		// 判断是否有过统计次数
		if (dayCountMap != null && date.equals(dayCountMap.get("date").toString())) {
			// 有统计过则加1
			Integer count = (Integer) dayCountMap.get("count");
			map.put("refundCount", count);
		} else {
			// 无统计过则初始化次数
			dayCountMap = new HashMap<String, Object>();
			dayCountMap.put("date", date);
			dayCountMap.put("count", 1);
			RedisUtil.addRedis(Global.REDIS_DAY_REFUND_COUNT, member.getId(), dayCountMap);
			map.put("refundCount", 1);
		}
		return new ApiMessage(200, "获取成功", map);
	}
	
	/**
	 * @Description: 修改背景图
	 * @author 宋高俊
	 * @param request
	 * @param coverimage
	 * @return
	 * @date 2018年11月26日 上午9:47:59
	 */
	@RequestMapping(value = "/updateMemberCoverimage")
	@ResponseBody
	public ApiMessage updateMemberCoverimage(HttpServletRequest request, String coverimage) {

		String token = (String) request.getAttribute("token");
		Member oldMember = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		Member member = memberService.selectByPrimaryKey(oldMember.getId());
		member.setCoverimage(coverimage);//封面背景
		try {
			memberService.updateByPrimaryKeySelective(member);
			RedisUtil.addRedis(Global.redis_member, token, member);
			return new ApiMessage(200, "修改成功");
		} catch (Exception e) {
			return new ApiMessage(400, "修改失败");
		}
	}
}
