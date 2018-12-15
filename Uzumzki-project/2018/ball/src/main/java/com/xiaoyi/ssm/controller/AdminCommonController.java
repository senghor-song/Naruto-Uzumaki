package com.xiaoyi.ssm.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.OperationLog;
import com.xiaoyi.ssm.model.Permission;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.model.StaffApply;
import com.xiaoyi.ssm.service.OperationLogService;
import com.xiaoyi.ssm.service.PermissionService;
import com.xiaoyi.ssm.service.StaffApplyService;
import com.xiaoyi.ssm.service.StaffService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.HttpUtils;
import com.xiaoyi.ssm.util.MoblieMessageUtil;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.WXConfig;

/**
 * @Description: 后台公共页面
 * @author 宋高俊
 * @date 2018年8月16日 下午4:49:49
 */
@Controller
@RequestMapping("admin/common")
public class AdminCommonController {

    private static Logger logger = Logger.getLogger(AdminCommonController.class.getName());
    @Autowired
    private StaffService staffService;
    @Autowired
    private StaffApplyService staffApplyService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private OperationLogService operationLogService;
    
    /**
	 * @Description: 后台登录页面
	 * @author song
	 * @date 2018年7月25日 下午12:15:57
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testLogin(Model model, HttpServletRequest request, String id) {
//		Staff staff = staffService.selectByPrimaryKey("72f2571706694e3fa0f10f451be7fad1");
		Staff staff = new Staff();
		if (StringUtil.isBank(id)) {
			staff = staffService.selectByPrimaryKey("1");
		}else {
			staff = staffService.selectByPrimaryKey(id);
		}
		request.getSession().setAttribute("loginStaffInfo", staff);
		return "redirect:/admin/common/index";
	}
    
	/**
	 * @Description: 后台登录页面
	 * @author song
	 * @date 2018年7月25日 下午12:15:57
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("dateFlag", Global.DATE_STRING);
		return "admin/WXlogin";
	}
	
	/**
	 * @Description: 静默登录
	 * @author 宋高俊  
	 * @param code
	 * @param request
	 * @return 
	 * @date 2018年10月19日 下午5:04:12 
	 */ 
	@RequestMapping(value = "/authLogin")
	public String authLogin(Model model, String code, String state, HttpServletRequest request) {
		model.addAttribute("dateFlag", Global.DATE_STRING);
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code"
				.replace("APPID", WXConfig.appid_web).replace("APPSECRET", WXConfig.appSecret_web).replace("CODE", code);

		String requestResult = HttpUtils.sendGet(requestUrl, null);// 我们需要自己写或者在网上找一个doGet方法发送doGet请求
		JSONObject getCodeResultJson = JSONObject.fromObject(requestResult);// 把请求成功后的结果转换成JSON对象
		if (getCodeResultJson == null || getCodeResultJson.get("errcode") != null || getCodeResultJson.getString("openid") == null) {
			logger.error("", new Exception("获取回调异常"));
			return "redirect:/admin/WXlogin";
		}

		String openid = getCodeResultJson.getString("openid");// 拿到openid
		String unionid = getCodeResultJson.getString("unionid");// 拿到unionid
		String access_token = getCodeResultJson.getString("access_token");// 拿到unionid
		logger.info("本次登录后台信息:");
		logger.info("openid:" + openid);
		logger.info("unionid:" + unionid);
		logger.info("getCodeResultJson:" + getCodeResultJson.toString());
		
		// 判断是否有state
		if (StringUtil.isBank(state)) {
			Staff staff = new Staff();
			staff.setUnionid(unionid);
			Staff loginStaff = staffService.login(staff);
			
			if (loginStaff != null) {
				if (!"正常".equals(loginStaff.getStatusFlag())) {
					model.addAttribute("message", "您的账号处于" + loginStaff.getStatusFlag() + "状态");
					return "admin/WXlogin";
				}
				
				// 获取用户信息
				String userinfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID"
						.replace("ACCESS_TOKEN", getCodeResultJson.getString("access_token")).replace("OPENID", openid);
				String userinfoReturn = HttpUtils.sendGet(userinfo, null);// 我们需要自己写或者在网上找一个doGet方法发送doGet请求
				
				JSONObject getUserinfoJson = JSONObject.fromObject(userinfoReturn);// 把请求成功后的结果转换成JSON对象
				if (getUserinfoJson == null || getUserinfoJson.get("errcode") != null || getUserinfoJson.getString("openid") == null) {
					logger.error("", new Exception("获取回调异常"));
				}else {
					loginStaff.setHeadImage(getUserinfoJson.getString("headimgurl"));
					loginStaff.setSex(getUserinfoJson.getString("sex") == "2" ? "男" : "女");
					loginStaff.setNickname(getUserinfoJson.getString("nickname"));
				}
				loginStaff.setOpenid(openid);
				loginStaff.setLoginChange(new Date());
				staffService.updateByPrimaryKeySelective(loginStaff);
				// 保存登录信息
				request.getSession().setAttribute("loginStaffInfo", loginStaff);
				operationLogService.saveLog(loginStaff.getId(), "登录系统", Utils.getIpAddr(request));
				return "redirect:/admin/common/index";
			}else {
				model.addAttribute("message", "您的账号未注册!");
				return "admin/WXlogin";
			}
		}else {
			// 有state则是注册用户
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("openid", openid);
			map.put("unionid", unionid);
			map.put("access_token", access_token);
			RedisUtil.setRedis(state+"_openid", openid, 300);
			RedisUtil.setRedis(state+"_unionid", unionid, 300);
			RedisUtil.setRedis(state+"_access_token", access_token, 300);
			return "admin/wxcode";
		}
	}
	
	/**
	 * @Description: 后台注册获取验证码
	 * @author 宋高俊
	 * @date 2018年7月25日 下午1:43:06
	 */
	@RequestMapping(value = "/getSMSCode")
	@ResponseBody
	public ApiMessage getSMSCode(String phone, HttpServletRequest request) {
		String smsCode = Utils.getCode();
		if (Utils.getPhone(phone)) {
			try {
				StaffApply staffApply = staffApplyService.selectByPhone(phone);
				if (staffApply != null) {
					return new ApiMessage(400, "您已申请,请等待审核");
				}
				Staff staff = staffService.getPhoneRegister(phone);
				if (staff != null) {
					return new ApiMessage(400, "该手机号码已被注册");
				}
				MoblieMessageUtil.sendIdentifyingCode(phone, smsCode);
				RedisUtil.setRedis(Global.web_staff_register_SmsCode_ + phone, smsCode, 120);
			} catch (Exception e) {
				return ApiMessage.error("发送次数已达上限,请次日使用验证功能");
			}
		} else {
			return new ApiMessage(400, "请输入正确的手机号码");
		}
		return ApiMessage.succeed();
	}

	
	/**
	 * @Description: 后台登录页面
	 * @author song
	 * @date 2018年7月25日 下午12:15:57
	 */
	@RequestMapping(value = "/testlogin", method = RequestMethod.GET)
	public String testlogin() {
		return "admin/login";
	}

	/**
	 * @Description: 后台注册页面
	 * @author song
	 * @date 2018年7月25日 下午12:15:57
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("state", Utils.getUUID());
		return "admin/register";
	}

	/**
	 * @Description: 左侧菜单页面
	 * @author song
	 * @date 2018年7月25日 下午1:43:06
	 */
	@RequestMapping(value = "/left")
	public String left(Model model, HttpServletRequest request) {
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		// 根据权限查询菜单
		List<Permission> permissions = permissionService.selectByMenu(staff.getPower());
		List<Map<String, Object>> listMaps = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < permissions.size(); i++) {
			// 没有子菜单则不显示父级菜单
			if (permissions.get(i).getPermissions().size() > 0) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("menuTitle", permissions.get(i).getMenuTitle());
				map.put("menuIcon", permissions.get(i).getMenuIcon());
				map.put("menus", permissions.get(i).getPermissions());
				listMaps.add(map);
			}
		}
		model.addAttribute("permissions", listMaps);
		return "admin/layout/leftSidebar";
	}

	/**
	 * @Description: 顶部菜单页面
	 * @author song
	 * @date 2018年7月25日 下午1:43:06
	 */
	@RequestMapping(value = "/top")
	public String top() {
		return "admin/layout/topSidebar";
	}

	/**
	 * @Description: 首页
	 * @author song
	 * @date 2018年7月25日 下午1:43:06
	 */
	@RequestMapping(value = "/index")
	public String index() {
		return "admin/index";
	}

	/**
	 * 顶部页面数据
	 * 
	 * @Description:
	 * @author 宋高俊
	 * @date 2018年8月20日 下午6:55:06
	 */
	@RequestMapping(value = "/countTop")
	@ResponseBody
	public ApiMessage countTop() {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) RedisUtil.getRedisOne(Global.REDIS_TOP_TAG_MAP,
				Global.REDIS_TOP_TAG_MAP);
		return ApiMessage.succeed(map);
	}
	

	private String getContent(InputStream... ises) throws IOException {
		if (ises != null) {
			StringBuilder result = new StringBuilder();
			BufferedReader br;
			String line;
			for (InputStream is : ises) {
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				while ((line = br.readLine()) != null) {
					result.append(line);
				}
			}
			return result.toString();
		}
		return null;
	}
	
	/**  
	 * @Description: 
	 * @author 宋高俊  
	 * @date 2018年8月29日 下午7:45:29 
	 */ 
	@SuppressWarnings({ "resource", "unused" })
	@RequestMapping("/htmlToWord")
	public void htmlToWord(HttpServletRequest request, HttpServletResponse response, String html) throws IOException {

//		String content = "<html>" + html + "</html>";

		InputStream bodyIs = new FileInputStream("D:\\abc.html");
		InputStream cssIs = new FileInputStream("D:\\abc.css");
		
		String body = this.getContent(bodyIs);
		String css = this.getContent(cssIs);
//		// 拼一个标准的HTML格式文档
		String content = "<html><head><style>" + css + "</style></head><body>" + body + "</body></html>";
		byte b[] = content.getBytes("utf-8"); // 这里是必须要设置编码的，不然导出中文就会乱码。
		ByteArrayInputStream bais = new ByteArrayInputStream(b);// 将字节数组包装到流中
		/*
		 * 关键地方 生成word格式
		 */
		POIFSFileSystem poifs = new POIFSFileSystem();
		DirectoryEntry directory = poifs.getRoot();
		DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
		// 输出文件
		String fileName = DateUtil.getFormat(new Date(), "yyyy-MM-dd")+"账单审核表";
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/msword");// 导出word格式
		response.addHeader("Content-Disposition",
				"attachment;filename=" + new String((fileName + ".doc").getBytes(), "iso-8859-1"));
		OutputStream ostream = response.getOutputStream();
		poifs.writeFilesystem(ostream);
		bais.close();
		ostream.close();
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
			return ApiMessage.succeed(Utils.getImageUrl(file));
		} catch (Exception e) {
			return ApiMessage.error();
		}
	}

	/**  
	 * @Description: 获取上传进度数据
	 * @author 宋高俊  
	 * @date 2018年8月14日 上午10:23:30 
	 */ 
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/redisupload", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage redisupload(HttpServletRequest request, String redisname) {
		Map<String, Object> map = (Map<String, Object>) RedisUtil.getRedisOne(Global.REDIS_SESSION_UPLOAD_MAP, request.getSession().getId() + redisname);
		return ApiMessage.succeed(map);
	}
	
	/**  
	 * @Description: 删除上传进度数据
	 * @author 宋高俊  
	 * @date 2018年8月14日 上午10:23:30 
	 */ 
	@RequestMapping(value = "/deleteRedisupload", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage deleteRedisupload(HttpServletRequest request, String redisname) {
		RedisUtil.delRedis(Global.REDIS_SESSION_UPLOAD_MAP, request.getSession().getId() + redisname);
		return ApiMessage.succeed();
	}
	
	/**  
	 * @Description: 操作日志数据
	 * @author 宋高俊  
	 * @param adminPage
	 * @return 
	 * @date 2018年11月7日 下午2:53:15 
	 */ 
	@RequestMapping(value = "/operationLog/list")
	@ResponseBody
	public AdminMessage operationLoglist(AdminPage adminPage) {
		
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		
		List<OperationLog> list = operationLogService.selectByAll(null);
		
		PageInfo<OperationLog> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			OperationLog operationLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", operationLog.getId());// ID
			map.put("content", DateUtil.getFormat(operationLog.getCreateTime()) + " " + operationLog.getStaff().getName() + " " + operationLog.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**  
	 * @Description: 根据Url获取图片地址
	 * @author 宋高俊  
	 * @param urlStr
	 * @return 
	 * @date 2018年11月7日 下午8:19:05 
	 */ 
	@RequestMapping(value = "/getImageHttpUrl")
	@ResponseBody
	public ApiMessage getImageHttpUrl(String urlStr) {
		return new ApiMessage(200,"获取成功",Utils.getImageHttpUrl(urlStr));
	}
}
