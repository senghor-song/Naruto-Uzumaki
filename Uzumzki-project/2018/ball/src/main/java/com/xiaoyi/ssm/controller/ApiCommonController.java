package com.xiaoyi.ssm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.github.pagehelper.PageHelper;
import com.xiaoyi.ssm.dao.OrderLogMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.PageBean;
import com.xiaoyi.ssm.model.Area;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.model.Manager;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.NewsBanner;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.OrderLog;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.service.AreaService;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.DistrictService;
import com.xiaoyi.ssm.service.ManagerService;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.NewsBannerService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.CheckUtil;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.HttpUtils;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.MsgXMLUtil;
import com.xiaoyi.ssm.wxPay.ReplyTextMsg;
import com.xiaoyi.ssm.wxPay.WXConfig;
import com.xiaoyi.ssm.wxPay.WechatMessageUtil;
import com.xiaoyi.ssm.zfhPay.AlipayConfig;

import net.sf.json.JSONObject;

/**
 * @Description: 微信公共接口
 * @author 宋高俊
 * @date 2018年8月16日 下午4:29:00
 */
@Controller
@RequestMapping("api/common")
public class ApiCommonController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MemberService memberService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private CityService cityService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private VenueService venueService;
	@Autowired
	private NewsBannerService newsBannerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderLogMapper orderMapper;

	/**
	 * @Description: 会员登录接口
	 * @author 宋高俊
	 * @date 2018年8月16日 下午4:51:39
	 */
	@RequestMapping(value = "/member/login")
	@ResponseBody
	public ApiMessage memberlogin(String phone, String password) {
		Member member = new Member();
		member.setPhone(phone);
		member.setPassword(password);
		Member loginmember = memberService.login(member);
		if (loginmember != null) {
			Map<String, Object> map = new HashMap<>();
			// 获取上一次登录的token
			String token = (String) RedisUtil.getRedisOne(Global.redis_token_member, loginmember.getId());
			if (!StringUtil.isBank(token)) {
				RedisUtil.delRedis(Global.redis_member, token);
			}
			// 本次登录使用的token
//			String loginToken = SHA1.encode(UUID.randomUUID().toString());
			String loginToken = loginmember.getId();
			RedisUtil.addRedis(Global.redis_token_member, loginmember.getId(), loginToken);
			RedisUtil.addRedis(Global.redis_member, loginToken, loginmember);
			map.put("token", loginToken);
			map.put("isopenid", loginmember.getOpenid() != null ? 1 : 0);

			return new ApiMessage(200, "登录成功", map);
		} else {
			return new ApiMessage(400, "账号或密码错误");
		}
	}

	/**
	 * @Description: 管理员登录接口
	 * @author 宋高俊
	 * @date 2018年8月16日 下午4:51:39
	 */
	@RequestMapping(value = "/manager/login")
	@ResponseBody
	public ApiMessage managerlogin(String phone, String password) {
		Manager loginmanager = managerService.login(phone, password);
		if (loginmanager != null) {
			Map<String, Object> map = new HashMap<>();
			// 获取上一次登录的token
			String token = (String) RedisUtil.getRedisOne(Global.redis_token_manager, loginmanager.getId());
			if (!StringUtil.isBank(token)) {
				RedisUtil.delRedis(Global.redis_manager, token);
			}
			// 本次登录使用的token
//			String loginToken = SHA1.encode(SHA1.encode(UUID.randomUUID().toString()));
			String loginToken = loginmanager.getId();
			RedisUtil.addRedis(Global.redis_token_manager, loginmanager.getId(), loginToken);
			RedisUtil.addRedis(Global.redis_manager, loginToken, loginmanager);
			map.put("token", loginToken);
			map.put("isopenid", 1);
			return new ApiMessage(200, "登录成功", map);
		} else {
			return new ApiMessage(400, "账号或密码错误");
		}
	}

	/**
	 * @Description: 注册接口
	 * @author 宋高俊
	 * @date 2018年8月16日 下午4:51:39
	 */
	@RequestMapping(value = "/member/register")
	@ResponseBody
	public ApiMessage register(HttpServletRequest request, Member member, String smsCode) {
		if (!StringUtil.toCompare(RedisUtil.getRedis(Global.api_member_register_SmsCode_ + member.getPhone()),
				smsCode)) {
			return new ApiMessage(400, "短信验证码不正确");
		}
		// 验证成功则将验证码提前删除
		RedisUtil.delRedis(Global.api_member_register_SmsCode_ + member.getPhone());

		Member oldmember = memberService.selectByPhone(member.getPhone());
		if (oldmember != null) {
			return new ApiMessage(400, "该手机号已注册");
		} else {
			member.setId(Utils.getUUID());
			member.setCreatetime(new Date());
			member.setModifytime(new Date());
			int flag = memberService.insertSelective(member);
			if (flag > 0) {
				Map<String, Object> map = new HashMap<>();
				map.put("token", member.getId());
				RedisUtil.addRedis(Global.redis_member, member.getId(), member);
				return new ApiMessage(200, "注册成功", map);
			} else {
				return new ApiMessage(400, "系统繁忙，注册失败");
			}
		}
	}

	/**
	 * @Description: 找回密码接口
	 * @author 宋高俊
	 * @date 2018年8月16日 下午4:51:39
	 */
	@RequestMapping(value = "/member/findPassword")
	@ResponseBody
	public ApiMessage findPassword(HttpServletRequest request, String phone, String password, String smsCode) {
		if (!StringUtil.toCompare(RedisUtil.getRedis(Global.api_member_findPassword_SmsCode_ + phone), smsCode)) {
			return new ApiMessage(400, "短信验证码不正确");
		}
		// 验证成功则将验证码提前删除
		RedisUtil.delRedis(Global.api_member_findPassword_SmsCode_ + phone);

		int flag = memberService.updateByPhone(phone, password);
		if (flag > 0) {
			return new ApiMessage(200, "修改成功");
		} else {
			return new ApiMessage(400, "修改失败");
		}
	}

	/**
	 * @Description: 后台注册获取验证码
	 * @author 宋高俊
	 * @param type = 0 注册验证码 1 = 找回密码验证码
	 * @date 2018年7月25日 下午1:43:06
	 */
	@RequestMapping(value = "/member/getSMSCode")
	@ResponseBody
	public ApiMessage getSMSCode(String phone, HttpServletRequest request, String type) {
//		if (Utils.getPhone(phone)) {
		Member oldmember = memberService.selectByPhone(phone);
		String smsCode = Utils.getCode();
		try {
			if ("0".equals(type)) {
				if (oldmember != null) {
					return new ApiMessage(400, "该手机号码已被注册");
				}
//					MoblieMessageUtil.sendIdentifyingCode(phone, smsCode);
				RedisUtil.setRedis(Global.api_member_register_SmsCode_ + phone, "123456", 120);
			} else if ("1".equals(type)) {
				if (oldmember == null) {
					return new ApiMessage(400, "该手机号码未注册");
				}
//					MoblieMessageUtil.sendIdentifyingCode(phone, smsCode);
				RedisUtil.setRedis(Global.api_member_findPassword_SmsCode_ + phone, "123456", 120);
			} else {
				return new ApiMessage(400, "参数错误");
			}
			return new ApiMessage(200, "发送成功");
		} catch (Exception e) {
			return ApiMessage.error("发送次数已达上限,请次日使用验证功能");
		}
//		} else {
//			return new ApiMessage(400, "请输入正确的手机号码");
//		}
	}

	/**
	 * @Description: 获取所有城市列表
	 * @author 宋高俊
	 * @date 2018年8月16日 下午5:45:46
	 */
	@RequestMapping(value = "/getCityList")
	@ResponseBody
	public ApiMessage getCityList() {
		List<Map<String, Object>> listmap = new ArrayList<>();
		List<City> cities = cityService.selectByAll(null);
		for (City city : cities) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", city.getId());// ID
			map.put("name", city.getCity());// 城市名称
			listmap.add(map);
		}
		return new ApiMessage(200, "查询成功", listmap);
	}

	/**
	 * @Description: 获取所有区县列表
	 * @author 宋高俊
	 * @date 2018年8月16日 下午5:45:46
	 */
	@RequestMapping(value = "/getDistrictList")
	@ResponseBody
	public ApiMessage getDistrictList(String id) {
		List<Map<String, Object>> listmap = new ArrayList<>();
		District d = new District();
		d.setCityid(id);
		List<District> districts = districtService.selectByAll(d);
		for (District district : districts) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", district.getId());// ID
			map.put("name", district.getDistrict());// 区县名称
			listmap.add(map);
		}
		return new ApiMessage(200, "查询成功", listmap);
	}

	/**
	 * @Description: 获取所有片区列表
	 * @author 宋高俊
	 * @date 2018年8月16日 下午5:45:46
	 */
	@RequestMapping(value = "/getAreaList")
	@ResponseBody
	public ApiMessage getAreaList(String id) {
		List<Map<String, Object>> listmap = new ArrayList<>();
		Area a = new Area();
		a.setDistrictid(id);
		List<Area> areas = areaService.selectByAll(a);
		for (Area area : areas) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", area.getId());// ID
			map.put("name", area.getArea());// 片区名称
			listmap.add(map);
		}
		return new ApiMessage(200, "查询成功", listmap);
	}

	/**
	 * @Description: 场馆列表
	 * @author 宋高俊
	 * @date 2018年8月17日 上午11:16:24
	 */
	@RequestMapping(value = "/venue/list")
	@ResponseBody
	public ApiMessage list(PageBean pageBean, HttpServletRequest request, String cityid, String districtid,
			String areaid) {
		Venue oldvenue = new Venue();
		oldvenue.setCityid(cityid);
		oldvenue.setDistrictid(districtid);
		oldvenue.setAreaid(areaid);
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<Map<String, Object>> listmap = new ArrayList<>();
		List<Venue> venues = venueService.selectByAll(oldvenue);
		for (Venue venue : venues) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", venue.getId());// id
			map.put("image", venue.getImage());// 图片
			map.put("name", venue.getName());// 名称
			map.put("address", venue.getAddress());// 地址
			map.put("phone", venue.getTel());// 电话
			listmap.add(map);
		}
		return new ApiMessage(200, "查询成功", listmap);
	}

	/**
	 * @Description: 获取日期数据
	 * @author 宋高俊
	 * @date 2018年8月17日 下午2:41:12
	 */
	@RequestMapping(value = "/datelist")
	@ResponseBody
	public ApiMessage datelist() {
		// 当前日期
		Date date = new Date();
		// 获取当天的00:00:00时间
		date = DateUtil.getWeeHours(date, 0);
		List<Map<String, Object>> datelistmap = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			Map<String, Object> datemap = new HashMap<>();
			datemap.put("date", DateUtil.getFormat(date, "yyyy-MM-dd"));// 日期
			datemap.put("week", DateUtil.dateToWeek(date));// 星期
			datelistmap.add(datemap);
			date = DateUtil.getPreTime(date, 3, 1);
		}
		return ApiMessage.succeed(datelistmap);
	}

	@RequestMapping(value = "/404")
	@ResponseBody
	public ApiMessage error() {
		return new ApiMessage(400, "找不到该请求");
	}

	/**
	 * @Description: 上传图片
	 * @author 宋高俊
	 * @date 2018年7月31日 下午6:40:17
	 */
	@RequestMapping(value = "/uploadImage")
	@ResponseBody
	public ApiMessage uploadImage(MultipartFile file, HttpServletRequest request) {
		try {
			return ApiMessage.succeed(Utils.getImageUrl2(file));
		} catch (Exception e) {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 获取城市所有数据
	 * @author 宋高俊
	 * @date 2018年8月23日 下午4:58:34
	 */
	@RequestMapping(value = "/getCityAllList")
	@ResponseBody
	public ApiMessage getCityAllList() {
		// 第一级城市
		List<Map<String, Object>> listmap = new ArrayList<>();
		List<City> cities = cityService.selectByAll(null);
		for (City city : cities) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", city.getId());// ID
			map.put("name", city.getCity());// 城市名称
			// 第二级区县
			List<Map<String, Object>> listmap2 = new ArrayList<>();
			District d = new District();
			d.setCityid(city.getId());
			List<District> districts = districtService.selectByAll(d);
			for (District district : districts) {
				Map<String, Object> map2 = new HashMap<>();
				map2.put("id", district.getId());// ID
				map2.put("name", district.getDistrict());// 区县名称
				// 第三级片区
				List<Map<String, Object>> listmap3 = new ArrayList<>();
				Area a = new Area();
				a.setDistrictid(district.getId());
				List<Area> areas = areaService.selectByAll(a);
				for (Area area : areas) {
					Map<String, Object> map3 = new HashMap<>();
					map3.put("id", area.getId());// ID
					map3.put("name", area.getArea());// 片区名称
					listmap3.add(map3);
				}
				map2.put("list", listmap3);
				listmap2.add(map2);
			}
			map.put("list", listmap2);
			listmap.add(map);
		}
		return new ApiMessage(200, "查询成功", listmap);
	}

	/**
	 * @Description: 获取微信的Banner图
	 * @author 宋高俊
	 * @date 2018年8月24日 下午5:47:21
	 */
	@RequestMapping(value = "/getBanner")
	@ResponseBody
	public ApiMessage getBanner() {
		NewsBanner newsBanner = new NewsBanner();
		newsBanner.setShowway("易订场");
		List<NewsBanner> list = newsBannerService.selectByAll(null);
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("image", list.get(i).getCoverpath());
			listmap.add(map);
		}
		return ApiMessage.succeed(listmap);
	}

	/**
	 * @Description: 获取微信的Banner图
	 * @author 宋高俊
	 * @date 2018年8月24日 下午5:47:21
	 */
	@RequestMapping(value = "/weixinLogin")
	public void weixinLogin(HttpServletRequest request, HttpServletResponse response, String state) throws Exception {
		Member member = memberService.selectByPrimaryKey(state);

		logger.info("开始执行微信回调");
		Map<String, String[]> params = request.getParameterMap();// 针对get获取get参数
		String[] codes = params.get("code");// 拿到的code的值
		String code = codes[0];// code
		logger.info("开始执行用微信回调的Code获取openid");
		// 这一步就是拼写微信api请求地址并 通过微信的appid 和 微信公众号的AppSecret 以及我们获取到的针对用户授权回调的code 拿到 这个用户的
		// openid和access_token
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code"
				.replace("APPID", WXConfig.appid).replace("APPSECRET", WXConfig.appSecret).replace("CODE", code);
		String requestResult = HttpUtils.sendGet(requestUrl, null);// 我们需要自己写或者在网上找一个 doGet 方法 发送doGet请求
		JSONObject getCodeResultJson = JSONObject.fromObject(requestResult);// 把请求成功后的结果转换成JSON对象
		if (getCodeResultJson == null || getCodeResultJson.get("errcode") != null
				|| getCodeResultJson.getString("openid") == null) {
			throw new Exception("获取回调异常");
		}
		String openid = getCodeResultJson.getString("openid");// 拿到openid
		logger.info("openid:" + openid);
		logger.info("getCodeResultJson:" + getCodeResultJson.toString());
		member.setOpenid(openid);
		member.setModifytime(new Date());
		memberService.updateByPrimaryKeySelective(member);
//		// 获取微信的ACCESS_TOKEN
//		String wxgzhToken = (String) RedisUtil.getRedisOne(Global.REDIS_ACCESS_TOKEN, WXConfig.appid);
//
//		// 这里是获取用户在我们公众里面的信息 如果没有关注公众号那么就没有办法获取详细信息 参数需要 微信公众号通用token 和 用户openid
//		String requestUrl1 = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN"
//				.replace("ACCESS_TOKEN", wxgzhToken).replace("OPENID", openid);
//		String requestResult1 = HttpUtils.sendGet(requestUrl1, null);
//		JSONObject user = JSONObject.fromObject(requestResult1);
//		if (user == null || user.get("errcode") != null) {
//		}
//
//		Integer subscribe = user.getInt("subscribe");// 是否有关注我们公众号
//		if (subscribe == 0) {// 没有关注我们公众号
//			response.sendRedirect(
//					"https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzIyNTc1MDQ2Mw==&scene=110#wechat_redirect");// 跳到我们公众号关注页面
//																															// url地址需要你么你自己去截取
//		} else {// 关注了
//		}

//		return "redirect:http://ball.ekeae.com/Ball/dist/#/";
	}

	/**
	 * @Description: 用于响应微信发送的token数据,验证token
	 * @author 宋高俊
	 * @date 2018年8月25日 上午10:58:03
	 */
	@RequestMapping(value = "/checkSignature")
	public void getCheckSignature(HttpServletRequest req, HttpServletResponse resp) {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");

		PrintWriter out;
		// 首次请求申请验证,返回echostr
		if (echostr != null) {
			// 验证首次响应微信的token
			logger.info("验证首次响应微信的token");
			try {
				out = resp.getWriter();
				if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
					out.print(echostr);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		// 不是验证token，收到微信服务器消息
		logger.info("收到微信服务器消息");
		try {
			// 接受微信服务器发送过来的XML形式的消息
			InputStream in = req.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String sReqData = "";
			String itemStr = "";// 作为输出字符串的临时串，用于判断是否读取完毕
			while ((itemStr = reader.readLine()) != null) {
				sReqData += itemStr;
			}
			in.close();
			reader.close();
			logger.info("收到消息：" + sReqData);
			// 防止中文乱码
			resp.setCharacterEncoding("UTF-8");
			this.replyMessage(sReqData, resp.getWriter());
		} catch (Exception e) {
			logger.info("处理消息失败");
		}

	}

	/**
	 * @Description: 处理消息数据
	 * @author 宋高俊
	 * @date 2018年8月27日 下午7:08:30
	 */
	public void replyMessage(String message, PrintWriter out) {
		logger.info("开始处理消息");
		Document document = MsgXMLUtil.readString2XML(message);
		Element root = document.getRootElement();
		String MsgType = MsgXMLUtil.readNode(root, "MsgType");
		if (MsgType.equals(WechatMessageUtil.MESSAGE_TEXT)) {
			logger.info("判断消息类型是否为文本类型");
			ReplyTextMsg textMsg = new ReplyTextMsg();
			textMsg.setFromUserName(MsgXMLUtil.readNode(root, "ToUserName"));
			textMsg.setToUserName(MsgXMLUtil.readNode(root, "FromUserName"));
			textMsg.setCreateTime();
			// 将XML消息的参数都转化内容回复给微信
			MsgXMLUtil.content = "";
			String nodeString = MsgXMLUtil.readNodes(root);
			textMsg.setContent(nodeString);
			textMsg.setMsgType(WechatMessageUtil.MESSAGE_TEXT);
			try {
				// 将对象转化为XML
				String replyMsg = textMsg.Msg2Xml();
				out.println(replyMsg);
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
//
//
//	/**  
//	 * @Description: 响应用户消息
//	 * @author 宋高俊  
//	 * @date 2018年8月27日 上午10:24:56 
//	 */ 
//	@RequestMapping(value = "/checkSignature", method = RequestMethod.POST)
//	public void postCheckSignature(HttpServletRequest req, HttpServletResponse resp) {
//		try {
//			// 接受微信服务器发送过来的XML形式的消息
//			InputStream in = req.getInputStream();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
//			String sReqData = "";
//			String itemStr = "";// 作为输出字符串的临时串，用于判断是否读取完毕
//			while ((itemStr = reader.readLine()) != null) {
//				sReqData += itemStr;
//			}
//			in.close();
//			reader.close();
//			logger.info("收到消息：" + sReqData);
//			// 防止中文乱码
//			resp.setCharacterEncoding("UTF-8");
//			this.replyMessage(sReqData, resp.getWriter());
//		} catch (Exception e) {
//			logger.info("处理消息失败");
//		}
//	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/share")
	@ResponseBody
	public ApiMessage share(HttpServletRequest req, HttpServletResponse resp, String url) {

		Map<String, Object> map = (Map<String, Object>) RedisUtil.getRedisOne(Global.REDIS_ACCESS_TOKEN,
				WXConfig.appid);
		Map<String, Object> ret = new HashMap<>();
		String nonce_str = create_nonce_str(); // 随机串
		String timestamp = create_timestamp(); // 时间戳
		String string1;
		String signature = "";
		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + map.get("jsapi_ticket") + "&noncestr=" + nonce_str + "&timestamp=" + timestamp
				+ "&url=" + url;
		logger.info("分享内容=" + string1);
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		signature = SHA1.encode(string1);

		ret.put("url", url);
//		logger.info("url:" + url);
		ret.put("jsapi_ticket", map.get("jsapi_ticket"));
//		logger.info("jsapi_ticket:" + map.get("jsapi_ticket"));
		ret.put("nonceStr", nonce_str);
//		logger.info("nonceStr:" + nonce_str);
		ret.put("timestamp", timestamp);
//		logger.info("timestamp:" + timestamp);
		ret.put("signature", signature);
//		logger.info("signature:" + signature);
		ret.put("appId", WXConfig.appid);
		return new ApiMessage(200, "获取成功", ret);
	}

	/**
	 * 随机加密
	 * 
	 * @param hash
	 * @return
	 */
	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	/**
	 * 产生随机串--由程序自己随机产生
	 * 
	 * @return
	 */
	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 由程序自己获取当前时间
	 * 
	 * @return
	 */
	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	/**
	 * @Description: 支付宝手机支付接口
	 * @author 宋高俊
	 * @throws IOException
	 * @date 2018年8月29日 上午10:21:10
	 */
	@RequestMapping("/alipay")
	public void alipay(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String orderId)
			throws IOException {
		if (orderId == null) {
			return;
		}
		logger.info("开始支付订单：" + orderId);
		Order order = orderService.selectByPrimaryKey(orderId);

		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.ServiceUrl, AlipayConfig.app_id,
				AlipayConfig.private_key, "json", AlipayConfig.input_charset, AlipayConfig.zfb_public_key, "RSA2"); // 获得初始化的AlipayClient
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();// 创建API对应的request
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);// 在公共参数中设置回跳和通知地址
		alipayRequest.setBizContent("{" + " \"out_trade_no\":\"" + orderId + "\"," + " \"total_amount\":\""
				+ order.getPrice() + "\"," + " \"subject\":\"预定场地预付金额\","
				+ " \"product_code\":\"QUICK_WAP_PAY\",\"timeout_express\":\"5m\"}");// 填充业务参数
		String form = "";
		try {
			form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		logger.info("订单创建成功");
		httpResponse.setContentType("text/html;charset=" + AlipayConfig.input_charset);
		httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}

	/**
	 * 支付宝支付成功回调
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/aliPayCallBack")
	public void AlipayTradePayNotify(Map<String, String[]> requestParams, HttpServletResponse response,
			HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
		logger.info("处理支付宝发送的回调");
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
		logger.info("反馈信息:" + params.toString());
		String signVerified = AlipaySignature.getSignCheckContentV1(params);// 调用SDK验证签名
		logger.info("签名:" + signVerified);
		// ——请在这里编写您的程序（以下代码仅作参考）——

		/*
		 * 实际验证过程建议商户务必添加以下校验： 1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		 * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）， 3、校验通知中的seller_id（或者seller_email)
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

			logger.info("订单正在处理中：" + out_trade_no);
			// 注意：
			// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
		} else if (trade_status.equals("TRADE_SUCCESS")) {
			// 支付宝支付成功逻辑
			logger.info("支付成功：" + out_trade_no);
			Order order = orderService.selectByPrimaryKey(out_trade_no);
			order.setModifytime(new Date());
			order.setType(6);
			order.setPaytype(0);
			orderService.updateByPrimaryKey(order);

			OrderLog orderLog = new OrderLog();
			orderLog.setId(Utils.getUUID());
			orderLog.setCreatetime(new Date());
			orderLog.setOrderid(out_trade_no);
			orderLog.setType(0);
			orderLog.setContent("接收到支付宝通知，订单已支付成功");
			orderMapper.insert(orderLog);
		}
	}

	/**
	 * 将异步通知的参数转化为Map
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> toMap(HttpServletRequest request) {
		// System.out.println(">>>>" + request.getQueryString());
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

	/**
	 * @Description:
	 * @author 宋高俊
	 * @param app_auth_code
	 * @date 2018年9月1日 下午3:50:06
	 */
	@RequestMapping("/toAuthPage")
	public void toMap(String app_auth_code) {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.ServiceUrl, AlipayConfig.app_id,
				AlipayConfig.private_key, "json", AlipayConfig.input_charset, AlipayConfig.zfb_public_key);
		AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
		// 如果使用app_auth_code换取token，则为authorization_code，如果使用refresh_token换取新的token，则为refresh_token
		// 与refresh_token二选一，用户对应用授权后得到，即第一步中开发者获取到的app_auth_code值
		// 与code二选一，可为空，刷新令牌时使用
		request.setBizContent("{\"grant_type\":\"authorization_code\",\"code\":\"" + app_auth_code + "\"}");
		try {
			AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
			logger.info("本次获取的app_auth_token:{}",response.getAppAuthToken());
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}
}
