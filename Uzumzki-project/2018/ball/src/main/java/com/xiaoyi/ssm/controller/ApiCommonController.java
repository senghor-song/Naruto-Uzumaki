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
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
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
import com.xiaoyi.ssm.dao.MemberHabitMapper;
import com.xiaoyi.ssm.dao.OrderLogMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.PageBean;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.NewsBanner;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.OrderLog;
import com.xiaoyi.ssm.model.TrainTeam;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.DistrictService;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.NewsBannerService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.TrainTeamService;
import com.xiaoyi.ssm.service.VenueErrorService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.CheckUtil;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.HttpUtils;
import com.xiaoyi.ssm.util.IDCard;
import com.xiaoyi.ssm.util.MapUtils;
import com.xiaoyi.ssm.util.MoblieMessageUtil;
import com.xiaoyi.ssm.util.PropertiesUtil;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.MsgXMLUtil;
import com.xiaoyi.ssm.wxPay.ReplyTextMsg;
import com.xiaoyi.ssm.wxPay.WXConfig;
import com.xiaoyi.ssm.wxPay.WechatMessageUtil;
import com.xiaoyi.ssm.zfhPay.AlipayConfig;

/**
 * @Description: 微信公共接口
 * @author 宋高俊
 * @date 2018年8月16日 下午4:29:00
 */
@Controller
@RequestMapping("api/common")
public class ApiCommonController {
	
    private static Logger logger = Logger.getLogger(ApiCommonController.class.getName());

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberHabitMapper memberHabitMapper;
	@Autowired
	private CityService cityService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private VenueService venueService;
	@Autowired
	private NewsBannerService newsBannerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderLogMapper orderMapper;
	@Autowired
	private VenueErrorService venueErrorService;
	@Autowired
	private TrainTeamService trainTeamService;
	
	/**
	 * @Description: 用于验证身份证号是否正确
	 * @author 宋高俊
	 * @param idcard
	 * @return
	 * @date 2018年9月4日 上午11:40:35
	 */
	@RequestMapping(value = "/idcard")
	@ResponseBody
	public ApiMessage idcard(String idcard) {

		if (IDCard.IDCardValidate(idcard)) {
			return new ApiMessage(200, "验证通过");
		} else {
			return new ApiMessage(400, "验证不通过");
		}
	}

	/**
	 * @Description: 会员登录接口
	 * @author 宋高俊
	 * @date 2018年8月16日 下午4:51:39
	 */
	@RequestMapping(value = "/member/login")
	@ResponseBody
	public ApiMessage memberlogin(String phone, String password, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		String unionid = (String) session.getAttribute("unionid");

		logger.info("openid:"+openid);
		logger.info("unionid:"+unionid);
		
		Member member = new Member();
		member.setPhone(phone);
		Member loginmember = memberService.login(member);

		if (loginmember != null) {
			//判断这个openid是否已经被使用过
			Member oldMember = memberService.selectByOpenid(openid);
			if (oldMember != null) {
				oldMember.setOpenid(Utils.getUUID());
				oldMember.setUnionid(Utils.getUUID());
				memberService.updateByPrimaryKeySelective(oldMember);
			}
			
			loginmember.setOpenid(openid);
			loginmember.setUnionid(unionid);
			memberService.updateByPrimaryKeySelective(loginmember);

			Map<String, Object> map = new HashMap<>();
			// 获取上一次登录的token
			// String token = (String)
			// RedisUtil.getRedisOne(Global.redis_token_member,
			// loginmember.getId());
			// if (!StringUtil.isBank(token)) {
			// RedisUtil.delRedis(Global.redis_member, token);
			// }
			// 本次登录使用的token
			// String loginToken = SHA1.encode(UUID.randomUUID().toString());
			String loginToken = loginmember.getId();
			// RedisUtil.addRedis(Global.redis_token_member,
			// loginmember.getId(), loginToken);
			RedisUtil.addRedis(Global.redis_member, openid, loginmember);
			map.put("token", loginToken);
			// map.put("isopenid", loginmember.getOpenid() != null ? 1 : 0);

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
		if (!StringUtil.toCompare(RedisUtil.getRedis(Global.api_member_register_SmsCode_ + member.getPhone()), smsCode)) {
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
		if (Utils.getPhone(phone)) {
			Member oldmember = memberService.selectByPhone(phone);
			String smsCode = Utils.getCode();
			try {
				if ("0".equals(type)) {
					if (oldmember != null) {
						return new ApiMessage(400, "该手机号码已被注册");
					}
					MoblieMessageUtil.sendIdentifyingCode(phone, smsCode);
					RedisUtil.setRedis(Global.api_member_register_SmsCode_ + phone, smsCode, 120);
				} else if ("1".equals(type)) {
					if (oldmember == null) {
						return new ApiMessage(400, "该手机号码未注册");
					}
					MoblieMessageUtil.sendIdentifyingCode(phone, smsCode);
					RedisUtil.setRedis(Global.api_member_findPassword_SmsCode_ + phone, smsCode, 120);
				} else {
					return new ApiMessage(400, "参数错误");
				}
				return new ApiMessage(200, "发送成功");
			} catch (Exception e) {
				return ApiMessage.error("发送次数已达上限,请次日使用验证功能");
			}
		} else {
			return new ApiMessage(400, "请输入正确的手机号码");
		}
	}

	/**
	 * @Description: 获取所有城市列表
	 * @author 宋高俊
	 * @date 2018年8月16日 下午5:45:46
	 */
	@RequestMapping(value = "/getCityList")
	@ResponseBody
	public ApiMessage getCityList() {
		String[] letter = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		
		List<Map<String, Object>> listmap = new ArrayList<>();

		List<City> hotCity = cityService.selectByHotCity();
		Map<String, Object> hotMap = new HashMap<>();
		hotMap.put("name", "★热门城市");// 城市字母
		for (int i = 0; i < letter.length; i++) {
			JSONArray jsonArray = new JSONArray();
			for (City city : hotCity) {
				jsonArray.add(JSONObject.fromObject("{\"name\":\"" + city.getCity() + "\",\"cityid\":\"" + city.getId() + "\"}"));
			}
			hotMap.put("cities", jsonArray);// 城市名称
		}
		listmap.add(hotMap);
//		Map<String, Object> map = new HashMap<String, Object>();
		
		List<City> cities = cityService.selectByInitial();
		for (int i = 0; i < letter.length; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("name", letter[i]);// 城市字母
			JSONArray jsonArray = new JSONArray();
			for (City city : cities) {
				if (city.getInitial().equals(letter[i])) {
					jsonArray.add(JSONObject.fromObject("{\"name\":\"" + city.getCity() + "\",\"cityid\":\"" + city.getId() + "\"}"));
				}
			}
			if (jsonArray.size() > 0 ) {
				map.put("cities", jsonArray);// 城市名称
				listmap.add(map);
			}
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
	public ApiMessage getDistrictList(String name) {
		List<Map<String, Object>> listmap = new ArrayList<>();
		List<District> districts = districtService.selectByCityName(name);
		for (District district : districts) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", district.getId());// ID
			map.put("name", district.getDistrict());// 区县名称
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
	public ApiMessage list(PageBean pageBean,Integer ballType, String cityName, String districtid, Double lng, Double lat) {
		List<Map<String, Object>> listmap = new ArrayList<>();
		
		List<Venue> venues = new ArrayList<Venue>();
		if (!StringUtil.isBank(cityName)) {
			City city = cityService.selectByName(cityName);
			PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
			if (city != null) {
				venues = venueService.selectByAllVenue(city.getId(), districtid, lng, lat, ballType);
			}else {
				venues = venueService.selectByAllVenue(null, districtid, lng, lat, ballType);
			}
		}else {
			PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
			venues = venueService.selectByAllVenue(null, districtid, lng, lat, ballType);
		}
		
		for (Venue venue : venues) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", venue.getId());// id
			if (StringUtil.isBank(venue.getImage())) {
				map.put("image", "https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/baseImage/venue-null.jpg");// 图片
			}else {
				map.put("image", venue.getImage());// 图片
			}
			map.put("name", venue.getName());// 名称
			map.put("address", venue.getAddress());// 地址
			// map.put("phone", venue.getTel());// 电话
			map.put("type", venue.getType());// 球场类型
			map.put("warmreminder", venue.getWarmreminder());// 温馨提示
			if (lng != null && lat != null) {
				map.put("area", (int) MapUtils.getDistance(lng, lat, venue.getLongitude(), venue.getLatitude()));// 距离
			}

			List<TrainTeam> list =trainTeamService.selectByVenue(venue.getId());
			if (list.size() > 0) {
				map.put("showTrain", 1);
			}else {
				map.put("showTrain", 0);
			}
			map.put("minAmount", venue.getMinAmount());// 球场类型
			listmap.add(map);
		}
		return new ApiMessage(200, "查询成功", listmap);
	}
	
	/**  
	 * @Description: 根据场馆ID获取手机号
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月26日 上午9:30:47 
	 */ 
	@RequestMapping(value = "/venue/getPhone")
	@ResponseBody
	public ApiMessage venueGetPhone(String id) {
		Venue venue = venueService.selectByPrimaryKey(id);
		if (venue != null) {
			return new ApiMessage(200, "查询成功", venue.getTel());
		}else {
			return new ApiMessage(400, "场馆不存在");
		}
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
			map.put("contentpath", list.get(i).getContentpath());
			listmap.add(map);
		}
		return ApiMessage.succeed(listmap);
	}

	@RequestMapping(value = "/infologin")
	@ResponseBody
	public ApiMessage infologin() {
		return ApiMessage.succeed();
	}

	/**
	 * @Description: 获取微信的openid
	 * @author 宋高俊
	 * @date 2018年8月24日 下午5:47:21
	 */
//	@RequestMapping(value = "/weixinLogin")
//	public void weixinLogin(HttpServletRequest request, HttpServletResponse response, String state) {
//		logger.info("开始执行从微信获取openid的回调");
//
//		String openid = "";
//		String unionid = "";
//		try {
//			Map<String, String[]> params = request.getParameterMap();// 针对get获取get参数
//			String[] codes = params.get("code");// 拿到的code的值
//			String code = codes[0];// code
//			logger.info("开始执行用微信回调的Code获取openid");
//			// 这一步就是拼写微信api请求地址并 通过微信的appid 和 微信公众号的AppSecret
//			// 以及我们获取到的针对用户授权回调的code 拿到 这个用户的
//			// openid和access_token
//			String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code"
//					.replace("APPID", WXConfig.appid).replace("APPSECRET", WXConfig.appSecret).replace("CODE", code);
//
//			String requestResult = HttpUtils.sendGet(requestUrl, null);// 我们需要自己写或者在网上找一个doGet方法发送doGet请求
//			JSONObject getCodeResultJson = JSONObject.fromObject(requestResult);// 把请求成功后的结果转换成JSON对象
//			if (getCodeResultJson == null || getCodeResultJson.get("errcode") != null || getCodeResultJson.getString("openid") == null) {
//				logger.error("", new Exception("获取回调异常"));
//			}
//
//			openid = getCodeResultJson.getString("openid");// 拿到openid
//			unionid = getCodeResultJson.getString("unionid");// 拿到unionid
//			logger.info("openid:" + openid);
//			logger.info("unionid:" + unionid);
//			logger.info("getCodeResultJson:" + getCodeResultJson.toString());
//
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		try {
//			Member member = memberService.selectByOpenid(openid);
//			
//			// 设置session数据
//			request.getSession().setAttribute("openid", openid);
//			request.getSession().setAttribute("unionid", unionid);
//			// 设置有效期
//			request.getSession().setMaxInactiveInterval(12 * 60 * 60);
//			// 判断用户是否存在
//			if (member != null) {
//				
//				//判断这个openid是否已经被使用过
//				member.setOpenid(openid);
//				member.setUnionid(unionid);
//				memberService.updateByPrimaryKeySelective(member);
//				
//				// 存在则将用户信息存入缓存
//				RedisUtil.addRedis(Global.redis_member, openid, member);
//				// 判断用户是否管理员
//				Manager manager = managerService.selectByPhone(member.getPhone());
//				if (manager != null) {
//					// 是管理员则将管理员信息存入缓存
//					RedisUtil.addRedis(Global.redis_manager, openid, manager);
//				}
//				response.sendRedirect(state);
//			}else {
//				response.sendRedirect("https://ball.ekeae.com:8443/dist/login");
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}

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
	@SuppressWarnings("unchecked")
	public void replyMessage(String message, PrintWriter out) {
		logger.info("开始处理消息");
		Document document = MsgXMLUtil.readString2XML(message);
		Element root = document.getRootElement();
		
		String MsgType = MsgXMLUtil.readNode(root, "MsgType");
		
		// 回复给微信所需的参数
		ReplyTextMsg textMsg = new ReplyTextMsg();
		textMsg.setFromUserName(MsgXMLUtil.readNode(root, "ToUserName"));
		textMsg.setToUserName(MsgXMLUtil.readNode(root, "FromUserName"));
		textMsg.setCreateTime();
		textMsg.setMsgType(WechatMessageUtil.MESSAGE_TEXT);

		textMsg.setContent("请点击下方菜单进行操作");
		if (MsgType.equals(WechatMessageUtil.MESSAGE_TEXT)) {
			logger.info("判断消息类型是否为文本类型");
//			MsgXMLUtil.content = "";
//			String nodeString = MsgXMLUtil.readNodes(root);
		}else if (MsgType.equals(WechatMessageUtil.MESSAGE_EVENT)) {
			logger.info("判断消息类型是否为事件推送消息");
			if ("subscribe".equals(MsgXMLUtil.readNode(root, "Event"))) {
				// 关注事件
				String openid = MsgXMLUtil.readNode(root, "FromUserName");
				logger.info(openid + "用户关注了公众号");

				// 根据openid获取用户的unionid
				Map<String, Object> access_token = (Map<String, Object>) RedisUtil.getRedisOne(Global.REDIS_ACCESS_TOKEN, WXConfig.appid);
				String userinfo = HttpUtils.sendGet("https://api.weixin.qq.com/cgi-bin/user/info", "access_token=" + access_token.get("access_token")
						+ "&openid=" + openid + "&lang=zh_CN");
				// 判断是否有数据返回
				if (!StringUtil.isBank(userinfo)) {
					logger.info("根据openid获取到用户数据" + userinfo);
					JSONObject jsonObject = JSONObject.fromObject(userinfo);
					// 更新用户的微信数据
					Member member = memberService.selectByOpenid(openid);

					// 判断该用户是否是第一次关注
					if (member != null) {
						
						logger.info(openid+"用户再次关注");
						
						//非首次关注仅更新头像，性别，昵称
						member.setAppavatarurl(jsonObject.getString("headimgurl"));
						member.setAppgender(jsonObject.getInt("sex"));
						member.setAppnickname(jsonObject.getString("nickname"));
						member.setModifytime(new Date());
						
						// 开发期间更新unionid
						member.setUnionid(jsonObject.getString("unionid"));
						memberService.updateByPrimaryKeySelective(member);
						textMsg.setContent("欢迎再次关注易订场公众号");
						
					}else {
						//首次关注需判断是否已使用过小程序
						member = memberService.selectByUnionid(jsonObject.getString("unionid"));
						if (member != null) {
							logger.info(openid+"已使用过小程序，更新小程序用户的数据");
							// 已使用过小程序，更新小程序用户的数据
							member.setOpenid(openid);
							member.setAppavatarurl(jsonObject.getString("headimgurl"));
							member.setAppgender(jsonObject.getInt("sex"));
							member.setAppnickname(jsonObject.getString("nickname"));
							member.setModifytime(new Date());

							// 开发期间更新unionid
							member.setUnionid(jsonObject.getString("unionid"));
							memberService.updateByPrimaryKeySelective(member);
							
						}else {
							logger.info(openid+"未使用过小程序,则创建新用户");
							// 未使用过小程序,则创建新用户
							member = new Member();
							member.setCreatetime(new Date());
							member.setModifytime(new Date());
							member.setId(Utils.getUUID());
							member.setOpenid(openid);
							member.setUnionid(jsonObject.getString("unionid"));
							member.setAppavatarurl(jsonObject.getString("headimgurl"));
							member.setAppgender(jsonObject.getInt("sex"));
							member.setName(jsonObject.getString("nickname"));
							member.setAppnickname(jsonObject.getString("nickname"));
							memberService.insertSelective(member);
						}
						textMsg.setContent("欢迎关注易订场公众号");
					}
					RedisUtil.addRedis(Global.redis_member, jsonObject.getString("unionid"), member);
				}
			} else if ("CLICK".equals(MsgXMLUtil.readNode(root, "Event"))) {
				logger.info("消息类型为公众号菜单点击事件：" + MsgXMLUtil.readNode(root, "EventKey"));
				if ("online_service".equals(MsgXMLUtil.readNode(root, "EventKey"))) {
					textMsg.setContent("有任何问题可在聊天框内发送，专属客服会为你解答！");
				}
			}
		} else {
			logger.info("消息类型为" + MsgType + ",默认处理");
		}
		try {
			// 将对象转化为XML
			String replyMsg = textMsg.Msg2Xml();
			out.println(replyMsg);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**  
	 * @Description: 分享需要的验证参数
	 * @author 宋高俊  
	 * @param req
	 * @param resp
	 * @param url
	 * @return 
	 * @date 2018年9月18日 上午10:20:07 
	 */ 
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/share")
	@ResponseBody
	public ApiMessage share(HttpServletRequest req, HttpServletResponse resp, String url) {

		Map<String, Object> map = (Map<String, Object>) RedisUtil.getRedisOne(Global.REDIS_ACCESS_TOKEN, WXConfig.appid);
		Map<String, Object> ret = new HashMap<>();
		String nonce_str = create_nonce_str(); // 随机串
		String timestamp = create_timestamp(); // 时间戳
		String string1;
		String signature = "";
		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + map.get("jsapi_ticket") + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
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
		// signature = SHA1.encode(string1);

		ret.put("url", url);
		// logger.info("url:" + url);
		ret.put("jsapi_ticket", map.get("jsapi_ticket"));
		// logger.info("jsapi_ticket:" + map.get("jsapi_ticket"));
		ret.put("nonceStr", nonce_str);
		// logger.info("nonceStr:" + nonce_str);
		ret.put("timestamp", timestamp);
		// logger.info("timestamp:" + timestamp);
		ret.put("signature", signature);
		// logger.info("signature:" + signature);
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
	public void alipay(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String orderId) throws IOException {
		if (orderId == null) {
			return;
		}
		logger.info("开始支付订单：" + orderId);
		Order order = orderService.selectByPrimaryKey(orderId);
		if (order != null) {
			httpResponse.sendRedirect("https://ball.ekeae.com:8443/dist/home");
		}

		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.ServiceUrl, AlipayConfig.app_id, AlipayConfig.private_key, "json",
				AlipayConfig.input_charset, AlipayConfig.zfb_public_key, "RSA2"); // 获得初始化的AlipayClient
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();// 创建API对应的request
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);// 在公共参数中设置回跳和通知地址
		alipayRequest.setBizContent("{" + " \"out_trade_no\":\"" + orderId + "\"," + " \"total_amount\":\"" + order.getPrice() + "\","
				+ " \"subject\":\"预定场地预付金额\"," + " \"product_code\":\"QUICK_WAP_PAY\",\"timeout_express\":\"5m\"}");// 填充业务参数
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
	public void AlipayTradePayNotify(Map<String, String[]> requestParams, HttpServletResponse response, HttpServletRequest request) throws AlipayApiException,
			UnsupportedEncodingException {
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
		 * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		 * 3、校验通知中的seller_id（或者seller_email)
		 * 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		 * 4、验证app_id是否为该商户本身。
		 */
		// if(signVerified) {//验证成功
		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		// 支付宝交易号
//		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

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
			// 获取场馆数据
			Venue venue = venueService.selectByPrimaryKey(order.getVenueid());
			if (venue == null) {
				return;
			}
			if (venue.getOrderverify() == 0) {
				order.setType(5);
				// 支付宝支付成功给用户发送推送消息
//				Member member = memberService.selectByPrimaryKey(order.getMemberid());
//				String openid = member.getOpenid();
			} else {
				order.setType(6);
				// 支付宝支付成功给用户发送推送消息

			}
			order.setPaytype(0);
			orderService.updateByPrimaryKeySelective(order);

			// 记录日志
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
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.ServiceUrl, AlipayConfig.app_id, AlipayConfig.private_key, "json",
				AlipayConfig.input_charset, AlipayConfig.zfb_public_key);
		AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
		// 如果使用app_auth_code换取token，则为authorization_code，如果使用refresh_token换取新的token，则为refresh_token
		// 与refresh_token二选一，用户对应用授权后得到，即第一步中开发者获取到的app_auth_code值
		// 与code二选一，可为空，刷新令牌时使用
		request.setBizContent("{\"grant_type\":\"authorization_code\",\"code\":\"" + app_auth_code + "\"}");
		try {
			AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
			logger.info("本次获取的app_auth_token:" + response.getAppAuthToken());
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 用于验证是否通过实名认证
	 * @author 宋高俊
	 * @param token
	 * @return
	 * @date 2018年9月4日 上午11:46:34
	 */
//	@RequestMapping(value = "/verifyRealname")
//	@ResponseBody
//	public ApiMessage verifyRealname(String token) {
//		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, token);
//		if (manager == null) {
//			return new ApiMessage(400, "验证不通过");
//		}
//		if (manager.getRealname() == 1) {
//			return new ApiMessage(200, "已验证通过");
//		} else {
//			return new ApiMessage(400, "验证不通过");
//		}
//	}

	/**
	 * @Description: 支付宝认证页面
	 * @author 宋高俊
	 * @param httpRequest
	 * @param httpResponse
	 * @param name 姓名
	 * @param idcard 身份证号
	 * @throws AlipayApiException
	 * @date 2018年9月3日 下午8:20:35
	 */
//	@RequestMapping("/zhima")
//	public void zhima(String token, HttpServletRequest httpRequest, HttpServletResponse httpResponse, String name, String idcard) throws AlipayApiException {
//		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, token);
//		if (manager == null) {
//			return;
//		}
//		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.ServiceUrl, AlipayConfig.app_id, AlipayConfig.private_key, "json",
//				AlipayConfig.input_charset, AlipayConfig.zfb_public_key, "RSA2");
//		ZhimaCustomerCertificationInitializeRequest request = new ZhimaCustomerCertificationInitializeRequest();
//		request.setBizContent("{" + "\"transaction_id\":\"" + Utils.getUUID() + "\"," + "\"product_code\":\"w1010100000000002978\"," + "\"biz_code\":\"FACE\","
//				+ "\"identity_param\":\"{\\\"identity_type\\\":\\\"CERT_INFO\\\",\\\"cert_type\\\":\\\"IDENTITY_CARD\\\"," + "\\\"cert_name\\\":\\\"" + name
//				+ "\\\",\\\"cert_no\\\":\\\"" + idcard + "\\\"}\"," + "\"merchant_config\":\"{}\"," + "\"ext_biz_param\":\"{}\","
//				+ "\"linked_merchant_id\":\"\"" + "  }");
//		ZhimaCustomerCertificationInitializeResponse response = alipayClient.execute(request);
//		logger.info("BizNo" + response.getBizNo());
//		manager.setAppauthtoken(response.getBizNo());
//		manager.setIdcard(idcard);
//		managerService.updateByPrimaryKeySelective(manager);
//		ZhimaCustomerCertificationCertifyRequest zhiamrequest = new ZhimaCustomerCertificationCertifyRequest();
//		zhiamrequest.setBizContent("{\"biz_no\":\"" + response.getBizNo() + "\"}");
//		zhiamrequest.setReturnUrl("https://ball.ekeae.com/WebBackAPI/api/common/zhimaCallBack");
//		// ZhimaCustomerCertificationCertifyResponse response2 =
//		// alipayClient.pageExecute(request2);
//		String form = "";
//		try {
//			form = alipayClient.pageExecute(zhiamrequest).getBody(); // 调用SDK生成表单
//			httpResponse.setContentType("text/html;charset=" + AlipayConfig.input_charset);
//			httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
//			httpResponse.getWriter().flush();
//			httpResponse.getWriter().close();
//		} catch (AlipayApiException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 支付宝支付成功回调
	 * 
	 * @throws UnsupportedEncodingException
	 */
//	@RequestMapping(value = "/zhimaCallBack")
//	public String zhimaCallBack(HttpServletResponse response, HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
//		logger.info("处理支付宝发送的回调");
//		// 获取支付宝POST过来反馈信息
//		Map<String, String> params = new HashMap<String, String>();
//		Map<String, String[]> requestParams1 = request.getParameterMap();
//		for (Iterator<String> iter = requestParams1.keySet().iterator(); iter.hasNext();) {
//			String name = (String) iter.next();
//			String[] values = (String[]) requestParams1.get(name);
//			String valueStr = "";
//			for (int i = 0; i < values.length; i++) {
//				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
//			}
//			// 乱码解决，这段代码在出现乱码时使用
//			valueStr = new String(valueStr.getBytes("utf-8"));
//			params.put(name, valueStr);
//		}
//		params.get("biz_content");
//		JSONObject jsonObject = JSONObject.fromObject(params.get("biz_content"));
//		logger.info("返回数据:" + params.toString());
//		String signVerified = AlipaySignature.getSignCheckContentV1(params);// 调用SDK验证签名
//		logger.info("签名:" + signVerified);
//		String bizno = jsonObject.get("biz_no").toString();
//		Manager manager = managerService.selectByBizno(bizno);
//
//		if ("true".equals(jsonObject.get("passed").toString())) {
//			// 实名认证通过
//			Manager updateManager = new Manager();
//			updateManager.setId(manager.getId());
//			updateManager.setModifytime(new Date());
//			updateManager.setRealname(1);
//			managerService.updateByPrimaryKeySelective(updateManager);
//		}
//
//		// ——请在这里编写您的程序（以下代码仅作参考）——
//		return "admin/zhima";
//
//	}

	@RequestMapping(value = "/zhimaTest")
	public String zhimaTest() {
		return "admin/zhima";
	}

	@RequestMapping(value = "/sql")
	@ResponseBody
	public ApiMessage sql(String sql) {
		PropertiesUtil.updatePro(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "jdbc.properties", "sqlLogger", sql);
		return new ApiMessage(200, "修改成功");
	}

	/**
	 * @Description: 小程序接口
	 * @author 宋高俊
	 * @param number
	 * @date 2018年9月8日 下午4:27:58
	 */
	@RequestMapping(value = "/setNumber")
	@ResponseBody
	public ApiMessage setNumber(Integer number, HttpServletRequest request) {
		Global.number = number;
		return new ApiMessage(200, "成功", number);
	}

	/**
	 * @Description: 小程序接口
	 * @author 宋高俊
	 * @param number
	 * @date 2018年9月8日 下午4:27:58
	 */
	@RequestMapping(value = "/getNumber")
	@ResponseBody
	public ApiMessage getNumber(Integer number, HttpServletRequest request) {
		return new ApiMessage(200, "成功", Global.number);
	}
}
