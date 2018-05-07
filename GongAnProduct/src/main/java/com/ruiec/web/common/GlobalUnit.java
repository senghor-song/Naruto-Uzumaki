package com.ruiec.web.common;

import org.apache.commons.lang3.StringUtils;

/**
 * 全局静态变量
 * @author qinzhitian<br>
 * @date 2018年1月8日 下午4:14:34
 */
public class GlobalUnit {

    public static final String GONGAN = "智慧公安";
    
	public static final String NULLMSG = "暂无";
	
	public static final String STEADY_ITEMCODE = "steady";//稳控状态字典类型别名
	
	public static final String TRAJECTORY_CODE = "track";// 字典类型（轨迹）别名
	
	public static final String PERSON_CLASS_CODE = "personClass";// 字典类型（人员类别）别名
	
	public static final String PERSON_CLASS_MAP = "PERSON_CLASS_MAP";// 人员类别
	
	public static final String DICTIONARY_MAP = "DICTIONARY_MAP";//字典
	
	public static final String USER_MAP = "USER_MAP";//用户
	
	public static final String API_CONFIG_MAP = "API_CONFIG_MAP";//api配置
	
	public static final String UNIT_MAP = "UNIT_MAP";//单位id
	
	public static final String UNIT_CODE_MAP = "UNIT_CODE_MAP";//单位编码
	
	public static final String USER_UNIT_MAP = "USER_UNIT_MAP";//管理员

	public static final Integer CITY_UNIT_ID = new Integer(1);//市局单位id
	
	public static final Integer ADMIN_USER_ID = new Integer(1);//超级管理员
	
	// 数据导入成功条数
	public static int successTotal = 0;
	// 数据导入失败条数
	public static int failTotal = 0;
	// 导入总条数
	public static long count = 0;
	// 已导入总条数
	public static int total = 0;
	
	public static boolean isOK=false;
	
	/**
	 * 验证数据是否为空，空返回暂无，非空返回输入值
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
