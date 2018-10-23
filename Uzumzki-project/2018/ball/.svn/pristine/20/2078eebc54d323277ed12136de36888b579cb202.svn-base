package com.xiaoyi.ssm.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 获取properties配置文件的数据
 * 
 * @author 宋高俊<br>
 * @date 2018年4月9日 下午2:35:06
 */
public class PropertiesUtil {
	private static Logger logger = Logger.getLogger(PropertiesUtil.class.getName());
	
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
	 * @param path         properties文件路径
	 * @param PropertyName properties文件中的属性名
	 * @return properties文件中属性的值
	 */
	public static String getValue(String path, String PropertyName){
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(path);
			properties.load(is);
			is.close();
		} catch (Exception e) {
			logger.error("", new Exception("异常:路径或文件名有误!路径前请不要加\"/\""));
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
//	    logger.info(PropertiesUtil.getValue(configUrl, "openfire.server.ip"));
//	    logger.info(PropertiesUtil.getValue(configUrl, "openfire.server.port"));
//	    logger.info(PropertiesUtil.getValue(configUrl, "openfire.server.name"));
//	}

	private static Properties prop;

	public static void load(String path) {
		// 这里的path是项目文件的绝对路径
		// 先获取项目绝对路径：Thread.currentThread().getContextClassLoader().getResource("").getPath();
		// 然后在项目路径后面拼接"properties/sysConfig.properties";
		prop = new Properties();// 属性集合对象
		FileInputStream fis;
		try {
			logger.info(path);
			fis = new FileInputStream(path);
			prop.load(fis);
			fis.close();// 关闭流
		} catch (FileNotFoundException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	// 参数为要修改的文件路径 以及要修改的属性名和属性值
	public static Boolean updatePro(String path, String key, String value) {
		if (prop == null) {
			load(path);
			logger.info("修改前重新加载一遍");
		}
		logger.info("获取添加或修改前的属性值：" + key + "=" + prop.getProperty(key));
		prop.setProperty(key, value);
		// 文件输出流
		try {
			FileOutputStream fos = new FileOutputStream(path);
			// 将Properties集合保存到流中
			prop.store(fos, "Copyright (c) Boxcode Studio");
			fos.close();// 关闭流
		} catch (FileNotFoundException e) {
			logger.error("", e);
			return false;
		} catch (IOException e) {
			logger.error("", e);
			return false;
		}
		logger.info("获取添加或修改后的属性值：" + key + "=" + prop.getProperty(key));
		return true;
	}

	// 参数为要修改的文件路径 以及要修改的属性名和属性值
	public static String getPro(String path, String key) {
		if (prop == null) {
			load(path);
			logger.info("重新加载一遍");
		}
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			prop.load(fis);// 将属性文件流装载到Properties对象中
			fis.close();// 关闭流
		} catch (FileNotFoundException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		}
		logger.info("查询到的" + key + "的值：" + prop.getProperty(key));
		return prop.getProperty(key);
	}

	public static void main(String[] args) {
		PropertiesUtil.updatePro(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "jdbc.properties","sqlLogger","true");
	}

}