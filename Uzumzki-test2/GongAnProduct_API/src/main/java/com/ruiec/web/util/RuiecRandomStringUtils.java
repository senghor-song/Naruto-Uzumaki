package com.ruiec.web.util;

import java.util.Random;

/**
 * 随机数工具类
 * @author 刘立雯
 * Date：2016年09月19日
 */
public class RuiecRandomStringUtils {
	
	/**
	 * 获取随机字符串
	 * @author 刘立雯
	 * @param type 类型（0：纯阿拉伯数字；1：小写字母+大写字母+阿拉伯数字）
	 * @param length 字符串长度
	 * 
	 * Date：2016年09月06日
	 */
	public static String getRandomString(int type,int length) { 
		String base="";
		if(type==0){
			base = "0123456789";
		}
		if(type==1){
			base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";   
		}
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	} 
	
}
