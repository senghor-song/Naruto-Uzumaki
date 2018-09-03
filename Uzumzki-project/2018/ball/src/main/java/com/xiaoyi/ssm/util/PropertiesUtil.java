package com.xiaoyi.ssm.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * 获取properties配置文件的数据
 * @author 宋高俊<br>
 * @date 2018年4月9日 下午2:35:06
 */
public class PropertiesUtil {
	private static PropertiesUtil getPropValueIstance; // 本类自己实例
	private static Properties properties = new Properties();
	private static InputStream is;

	/**
	 * 获取本类对象(单例模式)
	 * 
	 * @return 本类对象
	 */
	public static PropertiesUtil getInstance() {
		if (getPropValueIstance == null) {
			getPropValueIstance = new PropertiesUtil();
		}
		return getPropValueIstance;
	}

	/**
	 * 返回properties文件中属性的值
	 * 
	 * @param path
	 *            properties文件路径
	 * @param PropertyName
	 *            properties文件中的属性名
	 * @return properties文件中属性的值
	 */
	public static String getValue(String path, String PropertyName) throws Exception{
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(path);
			properties.load(is);
			is.close();
		} catch (Exception e) {
			new Exception("异常:路径或文件名有误!路径前请不要加\"/\"");
		}
		String propertyValue = (String) properties.get(PropertyName);
		return propertyValue;
	}
	
//	/**
//	 * 示例
//	 * @author 宋高俊<br>
//	 * @date 2018年4月8日 上午9:02:45
//	 */
//	public static void main(String[] args) {
//		String configUrl="openfire.properties";  
//	    System.out.println(PropertiesUtil.getValue(configUrl, "openfire.server.ip"));
//	    System.out.println(PropertiesUtil.getValue(configUrl, "openfire.server.port"));
//	    System.out.println(PropertiesUtil.getValue(configUrl, "openfire.server.name"));
//	}
}