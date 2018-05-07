package com.ruiec.web.common;

public class CommonParam {
	public static final String[] DATA_PATTERNS = { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss"};
	public static final String FLASH_MESSAGE_KEY = "flashMessageKey";
	public static final String SAVE_SUCCESS = "message.save.success";
	public static final String DELETE_SUCCESS = "message.delete.success";
	public static final String DELETE_FAIL = "message.delete.fail";
	public static final String UPDATE_SUCCESS = "message.update.success";
	public static final String UPDATE_FAIL = "message.update.fail";
	public static final String BUILDING = "message.building.fail";
	public static final String BUILDINGNOW = "message.building.now";
	public static final String BUILDINGING = "message.building.ing";
	
	public static final String SESSION_USER = "session_user";//登录用户标识key
	public static final String REDIRECT_URL = "redirect_url";
	
	public static final String ARTICLES_OF_AGREEMENT = "articles_of_agreement";  // 记住我功能标识
	
	public static final String COOKIE_USER_NAME = "flag";  // 记住我功能标识
	
	public static final String SESSION_CAPTCHA_KEY = "session_captcha_key_xxl";//二次验证码key
	public static final String SESSION_UUID = "session_uuid"; // 用于不允许多人登录
	
	public static final String SESSION_PAY_PWD_ERROR_NUM = "session_pay_pwd_error_num";//支付密码错误次数
	
	public static final String SELECT_STAR_DATE = "2016-01-01 00:00:00";//查询默认的开始时间
}
