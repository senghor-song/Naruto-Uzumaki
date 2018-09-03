/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateUtils;

/**
 * 日期工具类
 * 
 * @author 熊华松<br>
 * Version 1.0<br>
 * Date: 2015年12月31日
 */
public class RuiecDateUtils {
	
	/**
	 * 获取当前月的第一秒
	 * @return
	 * @author 熊华松
	 */
	public static Date getMinForMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		String year = format_yyyy(date);
		String month = format_MM(date);
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month));
		StringBuilder min = new StringBuilder(year + "-" + month); 
		min.append("-" + cal.getMinimum(Calendar.DAY_OF_MONTH));
		min.append(" " + cal.getMinimum(Calendar.HOUR_OF_DAY));
		min.append(":" + cal.getMinimum(Calendar.MINUTE));
		min.append(":" + cal.getMinimum(Calendar.SECOND));
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(min.toString());
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 获取昨天的第一秒
	 * 
	 * @author 杨龙香<br>
	 * Date:2016年07月07日
	 */
	public static Date getFirstSecondOfYesterday(){
		try {
			Date now=new Date();
			String year=RuiecDateUtils.format_yyyy(now);
			String month=RuiecDateUtils.format_MM(now);
			String day=RuiecDateUtils.format_dd(now);
			StringBuilder stringBuilder=new StringBuilder();
			stringBuilder.append(year);
			stringBuilder.append("-");
			stringBuilder.append(month);
			stringBuilder.append("-");
			stringBuilder.append(day);
			stringBuilder.append(" 00:00:00");
			return DateUtils.addDays(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stringBuilder.toString()),-1);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取昨天的最后一秒
	 * 
	 * @author 杨龙香<br>
	 * Date:2016年07月07日
	 */
	public static Date getLastSecondOfYesterday(){
		try {
			Date now=new Date();
			String year=RuiecDateUtils.format_yyyy(now);
			String month=RuiecDateUtils.format_MM(now);
			String day=RuiecDateUtils.format_dd(now);
			StringBuilder stringBuilder=new StringBuilder();
			stringBuilder.append(year);
			stringBuilder.append("-");
			stringBuilder.append(month);
			stringBuilder.append("-");
			stringBuilder.append(day);
			stringBuilder.append(" 23:59:59");
			return DateUtils.addDays(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stringBuilder.toString()),-1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * yyyyMM格式转换为日期
	 */
	public static Date parse_yyyyMM(String yyyyMMString){
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMM").parse(yyyyMMString);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return date;
	}
	
	/**
	 * yyyy格式转换为日期
	 */
	public static Date parse_yyyy(String yyyyStr){
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy").parse(yyyyStr);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return date;
	}
	
	/**
	 * yyyyMMdd格式转换为日期
	 */
	public static Date parse_yyyyMMdd(String yyyyMMddString){
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMMdd").parse(yyyyMMddString);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return date;
	}
	
	/**
	 * 将yyyy-MM-dd的字符串转换为日期
	 * @author 刘立雯
	 * Date：2016年09月06日
	 */
	public static Date parse_yyyy_MM_dd(String yyyyMMddString){
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(yyyyMMddString);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return date;
	}
	
	/**
	 * yyyyMMddHHmm格式转换为日期
	 * @author 熊华松
	 * @param yyyyMMddHHmmString
	 * @return
	 * Date: 2016-06-14
	 */
	public static Date parse_yyyyMMddHHmm(String yyyyMMddHHmmString) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(yyyyMMddHHmmString);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return date;
	}
	
	/**
	 * yyyyMMddHHmmss格式转换为日期
	 * @author 熊华松
	 * @param yyyyMMddHHmmssString
	 * @return
	 * Date: 2016-06-14
	 */
	public static Date parse_yyyyMMddHHmmss(String yyyyMMddHHmmssString) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(yyyyMMddHHmmssString);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return date;
	}
	
	/**
	 * ××时××分格式转换为××年××月××日 ××时××分
	 * @author 熊华松
	 * @param time
	 * @return
	 * Date: 2016-06-14
	 */
	public static Date parse_timeToyyyyMMddHHmm(String time) {
		Date date = new Date();
		String yyyyMMddHHmmString = RuiecDateUtils.format_yyyy_MM_dd(date) + " " + time;
		return parse_yyyyMMddHHmm(yyyyMMddHHmmString);
	}
	
	/**
	 * 日期转换为yyyyMM格式
	 */
	public static String format_yyyyMM(Date date){
		return new SimpleDateFormat("yyyyMM").format(date);
	}
	
	/**
	 * 日期转换为yyyy-MM格式
	 */
	public static String format_yyyy_MM(Date date){
		return new SimpleDateFormat("yyyy-MM").format(date);
	}
	
	/**
	 * 日期转换为yyyy-MM-dd HH:mm:ss格式
	 */
	public static String format_yyyy_MM_dd_HH_mm_ss(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	/**
	 * 日期转换为yyyy-MM-dd HH:mm格式
	 */
	public static String format_yyyy_MM_dd_HH_mm(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}
	
	/**
	 * 日期转换为HH:mm格式
	 */
	public static String format_HH_mm(Date date){
		return new SimpleDateFormat("HH:mm").format(date);
	}
	
	/**
	 * 日期转换为dd格式
	 */
	public static String format_dd(Date date){
		return new SimpleDateFormat("dd").format(date);
	}
	
	/**
	 * 日期转换为HH格式
	 */
	public static String format_HH(Date date){
		return new SimpleDateFormat("HH").format(date);
	}
	
	/**
	 * 日期转换为MM格式
	 */
	public static String format_MM(Date date){
		return new SimpleDateFormat("MM").format(date);
	}
	
	/**
	 * 日期转换为yyyy格式
	 */
	public static String format_yyyy(Date date){
		return new SimpleDateFormat("yyyy").format(date);
	}
	/**
	 * 日期转换为yyyyMMdd格式
	 */
	public static String format_yyyyMMdd(Date date){
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}
	
	/**
	 * 日期转换为yyyyMMddHH格式
	 */
	public static String format_yyyyMMddHH(Date date){
		return new SimpleDateFormat("yyyyMMddHH").format(date);
	}
	
	/**
	 * 日期转换为yyyy-MM-dd格式
	 */
	public static String format_yyyy_MM_dd(Date date){
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	/**
	 * 日期转换为yyMMddHHmm格式
	 */
	public static String format_yyMMddHHmm(Date date){
		return new SimpleDateFormat("yyMMddHHmm").format(date);
	}
	
	
	
	/**
	 * 获取当前月的最后一秒
	 * @return
	 * @author 熊华松
	 */
	public static Date getMaxForMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		String year = format_yyyy(date);
		String month = format_MM(date);
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month));
		StringBuilder max = new StringBuilder(year + "-" + month); 
		max.append("-" + cal.getMaximum(Calendar.DAY_OF_MONTH));
		max.append(" " + cal.getMaximum(Calendar.HOUR_OF_DAY));
		max.append(":" + cal.getMaximum(Calendar.MINUTE));
		max.append(":" + cal.getMaximum(Calendar.SECOND));
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(max.toString());
		} catch (ParseException e) {
			return null;
		}
	}
	
	
	public static Date transfer2Date(String year, String month, String day)
			throws ParseException {
		StringBuilder sb = new StringBuilder();
		sb.append(year).append("-").append(month).append("-").append(day);
		return new SimpleDateFormat("yyyy-MM-dd").parse(sb.toString());
	}
	/**
	 * yyyy-MM-dd
	 * @author 肖学良<br>
	 * Date: 2015年8月28日
	 */
	public static String formatYyyy_MM_dd(Date date){
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	/**
	 * yyyy-MM-dd
	 * @author 肖学良<br>
	 * Date: 2015年8月28日
	 */
	public static String transferDate2String(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	public static String transferDate2String(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat dateFormate = new SimpleDateFormat(pattern);
		return dateFormate.format(date);
	}


	/**
	 * 验证日期字符串是否是YYYY-MM-DD格式
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDataFormat(String str) {
		boolean flag = false;
		// String
		// regxStr="[1-9][0-9]{3}-[0-1][0-2]-((0[1-9])|([12][0-9])|(3[01]))";
		String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern pattern1 = Pattern.compile(regxStr);
		Matcher isNo = pattern1.matcher(str);
		if (isNo.matches()) {
			flag = true;
		}
		return flag;
	}
	
	/**
     * 获取凌晨时间
     * @param date
     * @flag 0 返回yyyy-MM-dd 00:00:00日期<br>
     *       1 返回yyyy-MM-dd 23:59:59日期
     * @return
     */
    public static Date getWeeHours(Date date, int flag) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        //时分秒（毫秒数）
        long millisecond = hour*60*60*1000 + minute*60*1000 + second*1000;
        //凌晨00:00:00
        cal.setTimeInMillis(cal.getTimeInMillis()-millisecond);
         
        if (flag == 0) {
            return cal.getTime();
        } else if (flag == 1) {
            //凌晨23:59:59
            cal.setTimeInMillis(cal.getTimeInMillis()+23*60*60*1000 + 59*60*1000 + 59*1000);
        }
        return cal.getTime();
    }
	
    /**
     * 获取n天后的时间
     * @author 刘立雯
     * 
     * @param date 开始时间
     * @param n 几天
     * 
     * Date：2016年11月04日
     */
    public static Date getNDaysTime(Date date,int n) {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, n);
        date = calendar.getTime();
        return date;
	}
    
    /**
     * 求两个时间之间的时间差
     * @author 刘立雯
     * 
     * @param startTime 开始时间（减数）
     * @param endTime 结束时间（被减数）
     * @param unit 单位   0:秒；  1:分；  2:时；  3:天；<br>
     * 
     * Date：2016年11月04日
     */
    public static long getTimeDifference (Date startTime,Date endTime,int unit) {
    	long timeDifference=0;
    	long time=endTime.getTime()-startTime.getTime();
		switch (unit) {
		case 0:
			timeDifference=time/1000;//秒
			break;
		case 1:
			timeDifference=time/(1000*60);//分
			break;
		case 2:
			timeDifference=time/(1000*60*60);//时
			break;
		case 3:
			timeDifference=time/(1000*60*60*24);//天
			break;
		}
		return timeDifference;
	}
}
