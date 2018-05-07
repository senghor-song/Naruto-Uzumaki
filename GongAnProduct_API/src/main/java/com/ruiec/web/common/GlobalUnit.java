package com.ruiec.web.common;

import org.apache.commons.lang3.StringUtils;

/**
 * 全局静态变量
 * 
 * @author qinzhitian<br>
 * @date 2018年1月8日 下午4:14:34
 */
public class GlobalUnit {

	public static final String NULLMSG = "暂无";

	public static final String STEADY_ITEMCODE = "steady";// 稳控状态字典类型别名

	public static final String TRAJECTORY_CODE = "track";// 字典类型（轨迹）别名

	public static final String PERSON_CLASS_CODE = "personClass";// 字典类型（人员类别）别名

	/** 
     */
	public static final String PERSON_CLASS_MAP = "PERSON_CLASS_MAP";// 人员类别

	/** 
     * getRedisOne为Dictionary对象<br>
     * getRedisAll为List(Dictionary)集合
     */
	public static final String DICTIONARY_MAP = "DICTIONARY_MAP";// 字典

	/** 
     * getRedisOne为User对象<br>
     * getRedisAll为List(User)集合
     */
	public static final String USER_MAP = "USER_MAP";// 用户

	/** 
     * getRedisOne为APIConfig对象<br>
     * getRedisAll为List(APIConfig)集合
     */
	public static final String API_CONFIG_MAP = "API_CONFIG_MAP";// api配置

    /** 
     * getRedisOne为Unit对象<br>
     * getRedisAll为List(Unit)集合
     */
	public static final String UNIT_MAP = "UNIT_MAP";// 单位id

	/** 
     * getRedisOne为Unit对象<br>
     * getRedisAll为List(Unit)集合
     */
	public static final String UNIT_CODE_MAP = "UNIT_CODE_MAP";// 单位编码

	/** 
     * getRedisOne为List(Map(String,Object))集合<br>
     * getRedisAll为List(Map(String,List(Map(String, Object))))集合
     */
	public static final String USER_UNIT_MAP = "USER_UNIT_MAP";// 管理员
	
    /** 
     * getRedisOne为LoginUserUnit对象<br>
     * getRedisAll为List(LoginUserUnit)集合
     */
    public static final String LOGIN_USER_MAP = "LOGIN_USER_MAP";// 登录用户
    
    /** 
     * getRedisOne为String数据<br>
     * getRedisAll为List(String)集合
     */
    public static final String LOGIN_USER_TOKEN = "LOGIN_USER_TOKEN";// 登录用户

	public static final Integer CITY_UNIT_ID = new Integer(1);// 市局单位id

	public static final Integer ADMIN_USER_ID = new Integer(1);// 超级管理员

	// 每次数据导入条数
	public static int successTotal = 0;
	// 每次数据导入条数
	public static int failTotal = 0;
	// 导入总条数
	public static long count = 0;
	// 已导入总条数
	public static int total = 0;

	/**
	 * 验证数据是否为空，空返回暂无，非空返回输入值
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月16日 上午10:11:09
	 */
	public static String checkValue(String value) {
		if (StringUtils.isBlank(value)) {
			return NULLMSG;
		} else {
			return value;
		}
	}
}
