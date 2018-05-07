package com.ruiec.web.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 自写工具类
 * @author Senghor<br>
 * @date 2017年12月18日 上午9:13:19
 */
public class DateUtil {
	
	/**
	 * 时间操作方法，
	 * date:需要操作的时间
	 * num:要增加的秒数
	 * @author Senghor<br>
	 * @date 2017年12月18日 上午9:12:07
	 */
	public static Date addDateSecond(Date date,int num) {
		Calendar calendar = Calendar.getInstance();    
	    calendar.setTime(date);    
	    calendar.add(Calendar.SECOND, num);    
	    return calendar.getTime();  
	}
	
	/**
	 * 判断时间是否为空
	 * date:需要操作的时间
	 * @author Senghor<br>
	 * @date 2017年12月18日 上午9:20:03
	 */
	public static boolean isNotDate(Date date) {
		if (date != null) {
		    return true;  
		}
		return false;
	}
}
