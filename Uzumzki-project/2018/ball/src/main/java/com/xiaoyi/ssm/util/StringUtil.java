package com.xiaoyi.ssm.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 字符串操作工具类
 * @author song
 * @date 2018年7月20日 上午11:04:25
 */
public class StringUtil {

	/**
	 * @Description: 比较字符串是否相同
	 * @author song
	 * @param beCompared 被比较的字符串
	 * @param toCompare  进行比较的字符串
	 * @date 2018年7月20日 上午11:09:08
	 * @return 相同则返回true,不同则反之
	 */
	public static boolean toCompare(String beCompared, String toCompare) {
		if (StringUtils.isBlank(beCompared) || StringUtils.isBlank(toCompare)) {
			return false;
		}
		return beCompared.equals(toCompare);
	}

	/**
	 * @Description: string转unicode编码
	 * @author song
	 * @date 2018年7月24日 下午2:54:09
	 */
	public static String stringToUnicode(String s) {
		try {
			StringBuffer out = new StringBuffer("");
			// 直接获取字符串的unicode二进制
			byte[] bytes = s.getBytes("unicode");
			// 然后将其byte转换成对应的16进制表示即可
			for (int i = 0; i < bytes.length - 1; i += 2) {
				out.append("\\u");
				String str = Integer.toHexString(bytes[i + 1] & 0xff);
				for (int j = str.length(); j < 2; j++) {
					out.append("0");
				}
				String str1 = Integer.toHexString(bytes[i] & 0xff);
				out.append(str1);
				out.append(str);
			}
			return out.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description: unicode编码转string
	 * @author song
	 * @date 2018年7月24日 下午2:54:09
	 */
	public static String unicodeToString(String str) {

		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			// group 6728
			String group = matcher.group(2);
			// ch:'木' 26408
			ch = (char) Integer.parseInt(group, 16);
			// group1 \u6728
			String group1 = matcher.group(1);
			str = str.replace(group1, ch + "");
		}
		return str;
	}

	/**
	 * @Description: 判断传入的字符串中是否有空值，有则返回true
	 * @author 宋高俊
	 * @date 2018年8月17日 下午6:03:08
	 */
	public static boolean isBank(String... string) {
		for (int i = 0; i < string.length; i++) {
			if (StringUtils.isBlank(string[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @Description: 将连续的时间编号数组转换为时间段
	 * @author 宋高俊
	 * @return 例如：10:00-13:00
	 * @date 2018年8月18日 上午10:15:04
	 */
	public static String timeToTimestr(String[] timestrs) {
		if (timestrs.length != 0) {
			String datestr = "";
			int time = Integer.valueOf(timestrs[0]) - 1;
			datestr += time / 2 + ":";
			int flag = time % 2;
			if (flag == 1) {
				datestr += "30";
			} else {
				datestr += "00";
			}
			int time2 = Integer.valueOf(timestrs[timestrs.length - 1]) - 1;
			int flag2 = time2 % 2;
			if (flag2 == 1) {
				datestr += "-" + (time2 / 2 + 1) + ":00";
			} else {
				datestr += "-" + time2 / 2 + ":30";
			}
			return datestr;
		} else {
			return "";
		}
	}
	
	/**
	 * @Description: 将时间编号转换为时间段
	 * @author 宋高俊
	 * @return 例如：10:00-13:00
	 * @date 2018年8月18日 上午10:15:04
	 */
	public static String timeToTimestr(String timestr) {
		String datestr = "";
		int time = Integer.valueOf(timestr) - 1;
		datestr += time / 2 + ":";
		int flag = time % 2;
		if (flag == 1) {
			datestr += "30";
		} else {
			datestr += "00";
		}
		int time2 = Integer.valueOf(timestr) - 1;
		int flag2 = time2 % 2;
		if (flag2 == 1) {
			datestr += "-" + (time2 / 2 + 1) + ":00";
		} else {
			datestr += "-" + time2 / 2 + ":30";
		}
		return datestr;
	}

	public static void main(String[] args) {
		String[] array={"1","2"};
		System.out.println(StringUtil.timeToTimestr(array));
		/*
			int[] array={12,13,14,23,24,34,45,46,47,48,56};
			List<String> arrays=StringUtil.getContinuousNumber(array);
			for(int i=0;i<arrays.size();i++) {
				String[] data = arrays.get(i).split(",");
				System.out.println(arrays.get(i));
			}*/
//		int[] array={12,13,14,23,24,34,45,46,47,48,56};
//		int[][] arrays=StringUtil.getContinuousNumber2(array);
//		for(int i=0;i<arrays.length;i++)
//		{
//			System.out.println(Arrays.toString(arrays[i]));
//		}
	}
	
	public static int[][] getContinuousNumber2(int[] array)
	{
	    int[][]arrays=new int[array.length][array.length];
	    //二维数组的i和j
		int i=0;
		int j=0;
		//记录当前最大值为数组的第一个，减一是为了保证后面数组的第一个大于最大值，添加进入二维数组
		int max=array[0]-1;
		for(int k=0;k<array.length;k++)
		{
			//当不是连续数字的时候，放到下一个数组里面，并且把j设置为0，最大值为当前值-1
			if(array[k]!=max+1)
			{
				i++;
				j=0;
				max=array[k]-1;
			}
			arrays[i][j]=array[k];
			max=array[k];
			j++;

		}
		return arrays;
	}
	public static List<String> getContinuousNumber(int[] array) {
		List<String> list = new ArrayList<>();
		int[][] arrays = new int[array.length][array.length];
		// 二维数组的i和j
		int i = 0;
		int j = 0;
		// 记录当前最大值为数组的第一个，减一是为了保证后面数组的第一个大于最大值，添加进入二维数组
		int max = array[0] - 1;
		String time = "";
		for (int k = 0; k < array.length; k++) {
			// 当不是连续数字的时候，放到下一个数组里面，并且把j设置为0，最大值为当前值-1
			if (array[k] != max + 1) {
				i++;
				j = 0;
				max = array[k] - 1;
				list.add(time);
				time = "";
			}
			arrays[i][j] = array[k];
			max = array[k];
			time += array[k] + ",";
			j++;
			if (k == array.length-1) {
				list.add(time);
			}
		}
		
		return list;
	}
}
