package com.xiaoyi.ssm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 时间工具类
 * @author 宋高俊<br>
 * @date 2018年5月7日 下午2:33:38
 */
public class DateUtil {
    
    /**
     * 时间格式字符串转换为日期类型
     * <br>yyyy：年  
     * <br>MM：月  
     * <br>dd：日  
     * <br>hh：1~12小时制(1-12)  
     * <br>HH：24小时制(0-23)  
     * <br>mm：分  
     * <br>ss：秒  
     * <br>S：毫秒  
     * <br>E：星期几  
     * <br>D：一年中的第几天  
     * <br>F：一月中的第几个星期(会把这个月总共过的天数除以7)  
     * <br>w：一年中的第几个星期  
     * <br>W：一月中的第几星期(会根据实际情况来算)  
     * <br>a：上下午标识  
     * <br>k：和HH差不多，表示一天24小时制(1-24)。  
     * <br>K：和hh差不多，表示一天12小时制(0-11)。  
     * <br>z：表示时区  
     * @param dateStr 传入的时间字符串
     * @param parseInto 要格式化的类型
     * @author 宋高俊<br>
     * @date 2018年5月7日 下午1:33:53
     */
    public static Date getParse(String dateStr,String parseInto){
    	if (StringUtils.isBlank(dateStr)) {
			return null;
		}
        Date date = null;
        try {
            date = new SimpleDateFormat(parseInto).parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("时间与格式不匹配");
        }
        return date;
    }
    

    /**  
     * @Description: 默认匹配yyyy-MM-dd HH:mm:ss格式
     * @author 宋高俊  
     * @date 2018年7月27日 上午9:52:27 
     */ 
    public static Date getParse(String dateStr){
    	if (StringUtils.isBlank(dateStr)) {
			return null;
		}
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("时间与格式不匹配");
        }
        return date;
    }

    /**
     * 日期转换为自定义字符串格式
     * <br>yyyy：年  
     * <br>MM：月  
     * <br>dd：日  
     * <br>hh：1~12小时制(1-12)  
     * <br>HH：24小时制(0-23)  
     * <br>mm：分  
     * <br>ss：秒  
     * <br>S：毫秒  
     * <br>E：星期几  
     * <br>D：一年中的第几天  
     * <br>F：一月中的第几个星期(会把这个月总共过的天数除以7)  
     * <br>w：一年中的第几个星期  
     * <br>W：一月中的第几星期(会根据实际情况来算)  
     * <br>a：上下午标识  
     * <br>k：和HH差不多，表示一天24小时制(1-24)。  
     * <br>K：和hh差不多，表示一天12小时制(0-11)。  
     * <br>z：表示时区  
     * @param date 传入的时间
     * @param parseInto 要格式化的类型
	 * @author 宋高俊<br>
	 * @date 2018年5月7日 下午1:36:30
	 */
	public static String getFormat(Date date,String parseInto){
		try {
			if (date == null) {
				return "";
			}
		    String dateStr = new SimpleDateFormat(parseInto).format(date);
	        return dateStr;
		} catch (Exception e) {
			return "";
		}
	}

	/**  
	 * @Description: 默认转换yyyy-MM-dd HH:mm:ss格式字符串
	 * @author 宋高俊  
	 * @date 2018年7月27日 上午9:51:37 
	 */ 
	public static String getFormat(Date date){
		try {
			if (date == null) {
				return "";
			}
			String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			return dateStr;
		} catch (Exception e) {
			return "";
		}
	}
	
    /**
     * 获取这天时间的00:00:00或23:59:59
     * @param date 时间
     * @param flag 0 返回yyyy-MM-dd 00:00:00日期 <br>
     *             1 返回yyyy-MM-dd 23:59:59日期
     * @author 宋高俊<br>
     * @date 2018年5月7日 下午1:53:16
     * @return Date
     */
    public static Date getWeeHours(Date date, int flag) {
    	if (date == null) {
			return date;
		}
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
     * 求两个时间之间的时间差
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param unit 单位   0:秒;  1:分;  2:时;  3:天;<br>
     * @author 宋高俊<br>
     * @return timeDifference 绝对值
     * @date 2018年5月7日 下午1:52:41
     */
    public static long getTimeDifference (Date startTime,Date endTime,int unit) {
    	if (startTime == null || endTime == null) {
			return 0l;
		}
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
		return Math.abs(timeDifference);
	}
    
    /**
     * 对时间进行增加和减少操作
     * @param date 时间
     * @param accuracy 0=秒; 1=分; 2=时; 3=天; 4=月; 5=年
     * @param size 要操作的大小(正整数则增加,负数则减少)
     * @author 宋高俊<br>
     * @date 2018年5月7日 下午1:56:35
     */
    public static Date getPreTime(Date date,int accuracy, int size){
    	if (date == null) {
			return date;
		}
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);
        switch (accuracy) {
            case 0:
                calendar.add(Calendar.SECOND, size);// 时间+size秒
                break;
            case 1:
                calendar.add(Calendar.MINUTE, size);// 时间+size分
                break;
            case 2:
                calendar.add(Calendar.HOUR_OF_DAY, size);// 时间+size小时  
                break;
            case 3:
                calendar.add(Calendar.DAY_OF_MONTH, size);// 时间+size天  
                break;
            case 4:
                calendar.add(Calendar.MONTH, size);// 时间+size月  
                break;
            case 5:
                calendar.add(Calendar.YEAR, size);// 时间+size年  
                break;
            default:
                break;
        }
        return calendar.getTime();
    }
    
}
