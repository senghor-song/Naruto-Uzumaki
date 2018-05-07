/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.util;

import java.util.Hashtable;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author 熊华松<br>
 * Version 1.0<br>
 * Date: 2015年12月31日
 */
public class RuiecStringUtil {
	
	/**
	 * 往指定字符串的指定位置插入指定字符串
	 * 
	 * @param source
	 * @param offset
	 * @param str
	 * @return
	 * @author 熊华松
	 */
	public static String insert(String source, int offset, String str) {
		if (source == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer(source);
		sb = sb.insert(offset, str);
		return sb.toString();
	}

	/**
	 * 判空函数
	 * 
	 * @param args
	 * @return
	 * @author 熊华松
	 */
	public static boolean checkNull(String... args) {
		for (String arg : args) {
			if (arg == null || arg.trim().length() == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 校验用户名
	 * 
	 * @param username
	 * @return
	 * @author 刘立雯
	 */
	public static boolean isUsername(String username) {
		if (checkNull(username)) {
			return false;
		}
		Pattern p = Pattern
				.compile("^[0-9a-zA-Z_]{6,20}$");
		Matcher m = p.matcher(username);
		return m.matches();
	}
	
	/**
	 * 校验真实姓名
	 * 
	 * @param name
	 * @return
	 * @author 刘立雯
	 */
	public static boolean isName(String name) {
		if (checkNull(name)) {
			return false;
		}
		Pattern p = Pattern
				.compile("^([\u4e00-\u9fa5]{1,20}|[a-zA-Z\\.\\s]{1,20})$");
		Matcher m = p.matcher(name);
		return m.matches();
	}
	
	/**
	 * 校验银行卡号
	 * 
	 * @param bankcardNo
	 * @return
	 * @author 刘立雯
	 */
	public static boolean isBankcardNo(String bankcardNo) {
		if (checkNull(bankcardNo)) {
			return false;
		}
		Pattern p = Pattern
				.compile("^[0-9]{16,20}$");
		Matcher m = p.matcher(bankcardNo);
		return m.matches();
	}

	/**
	 * 校验手机号
	 * 
	 * @param mobiles
	 * @return
	 * @author 熊华松
	 */
	public static boolean isMobileNO(String mobiles) {
		if (checkNull(mobiles)) {
			return false;
		}
		Pattern p = Pattern
				.compile("^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 校验邮箱
	 * 
	 * @param email
	 * @return
	 * @author 熊华松
	 */
	public static boolean isEmail(String email) {
		if (checkNull(email)) {
			return false;
		}
		boolean tag = true;
		final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(email);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	/**
	 * 验证手机号
	 * 
	 * @param number
	 * @return
	 * @author 熊华松
	 */
	public static boolean checkmobile(String number) { // *Regexp匹配模式 //
		if (checkNull(number)) {
			return true;
		}
		Pattern pattern = Pattern
				.compile("^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}$");
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}

	/**
	 * 验证QQ
	 * 
	 * @param number
	 * @return
	 * @author 熊华松
	 */
	public static boolean checkQq(String number) { // *Regexp匹配模式 //
		if (checkNull(number)) {
			return true;
		}
		String mobileRegexp = "\\d{5,10}"; // 手机的匹配模式
		Pattern pattern = Pattern.compile(mobileRegexp);
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}

	/**
	 * 获取不带连接符的uuid
	 * 
	 * @return
	 * @author 熊华松
	 */
	public static String getUuidWithoutSeparator() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replace("-", "");
	}

	/**
	 * 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。   
	 * 校验方法：  （1）十七位数字本体码加权求和公式  S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和 
	 * 			Ai:表示第i位置上的身份证号码数字值 
	 *          Wi:表示第i位置上的加权因子 
	 *          Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2   
	 * （2）计算模 Y = mod(S, 11)   
	 * （3）通过模得到对应的校验码     
	 * 	     Y: 0 1 2 3 4 5 6 7 8 9 10 
	 *    校验码: 1 0 X 9 8 7 6 5 4 3 2 
	 * @param idStr
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException
	 * @author 熊华松
	 */
//	public static String validateIDCard(String idStr)
//			throws NumberFormatException, ParseException {
//		String errorInfo = "";// 记录错误信息
//		if (checkNull(idStr)) {
//			errorInfo = "身份证号码不能为空";
//			return errorInfo;
//		}
//		// 校验码
//		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
//				"3", "2" };
//		// 加权因子
//		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
//				"9", "10", "5", "8", "4", "2" };
//		String Ai = "";
//
//		// ================ 号码的长度 15位或18位 ================
//		if (idStr.length() != 15 && idStr.length() != 18) {
//			errorInfo = "身份证号码长度应该为15位或18位。";
//			return errorInfo;
//		}
//
//		// ================ 数字 除最后以为都为数字 ================
//		if (idStr.length() == 18) {
//			Ai = idStr.substring(0, 17);
//		} else if (idStr.length() == 15) {
//			Ai = idStr.substring(0, 6) + "19" + idStr.substring(6, 15);
//		}
//		if (isNumeric(Ai) == false) {
//			errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
//			return errorInfo;
//		}
//
//		// ================ 出生年月是否有效 ================
//		String strYear = Ai.substring(6, 10);// 年份
//		String strMonth = Ai.substring(10, 12);// 月份
//		String strDay = Ai.substring(12, 14);// 月份
//		if (isDataFormat(strYear + "-" + strMonth + "-" + strDay) == false) {
//			errorInfo = "身份证生日无效。";
//			return errorInfo;
//		}
//		GregorianCalendar gc = new GregorianCalendar();
//		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
//		if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
//				|| (gc.getTime().getTime() - s.parse(
//						strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
//			errorInfo = "身份证生日不在有效范围。";
//			return errorInfo;
//		}
//		if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
//			errorInfo = "身份证月份无效";
//			return errorInfo;
//		}
//		if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
//			errorInfo = "身份证日期无效";
//			return errorInfo;
//		}
//
//		// ================ 地区码时候有效 ================
//		Hashtable<String, String> h = getAreaCode();
//		if (h.get(Ai.substring(0, 2)) == null) {
//			errorInfo = "身份证地区编码错误。";
//			return errorInfo;
//		}
//
//		// ================ 判断最后一位的值 ================
//		int TotalmulAiWi = 0;
//		for (int i = 0; i < 17; i++) {
//			TotalmulAiWi = TotalmulAiWi
//					+ Integer.parseInt(String.valueOf(Ai.charAt(i)))
//					* Integer.parseInt(Wi[i]);
//		}
//		int modValue = TotalmulAiWi % 11;
//		String strVerifyCode = ValCodeArr[modValue];
//		Ai = Ai + strVerifyCode;
//
//		if (idStr.length() == 18) {
//			if (Ai.equals(idStr) == false) {
//				errorInfo = "身份证无效，不是合法的身份证号码";
//				return errorInfo;
//			}
//		} else {
//			return "";
//		}
//
//		return "";
//
//	}

	/**
	 * 功能：判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 功能：设置地区编码
	 * 
	 * @return Hashtable 对象
	 */
	private static Hashtable<String, String> getAreaCode() {
		Hashtable<String, String> hashtable = new Hashtable<String, String>();
		hashtable.put("11", "北京");
		hashtable.put("12", "天津");
		hashtable.put("13", "河北");
		hashtable.put("14", "山西");
		hashtable.put("15", "内蒙古");
		hashtable.put("21", "辽宁");
		hashtable.put("22", "吉林");
		hashtable.put("23", "黑龙江");
		hashtable.put("31", "上海");
		hashtable.put("32", "江苏");
		hashtable.put("33", "浙江");
		hashtable.put("34", "安徽");
		hashtable.put("35", "福建");
		hashtable.put("36", "江西");
		hashtable.put("37", "山东");
		hashtable.put("41", "河南");
		hashtable.put("42", "湖北");
		hashtable.put("43", "湖南");
		hashtable.put("44", "广东");
		hashtable.put("45", "广西");
		hashtable.put("46", "海南");
		hashtable.put("50", "重庆");
		hashtable.put("51", "四川");
		hashtable.put("52", "贵州");
		hashtable.put("53", "云南");
		hashtable.put("54", "西藏");
		hashtable.put("61", "陕西");
		hashtable.put("62", "甘肃");
		hashtable.put("63", "青海");
		hashtable.put("64", "宁夏");
		hashtable.put("65", "新疆");
		hashtable.put("71", "台湾");
		hashtable.put("81", "香港");
		hashtable.put("82", "澳门");
		hashtable.put("91", "国外");
		return hashtable;
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

	public static void main(String[] args) throws Exception {
		/*
		 * System.out.println(checkQq("1234"));
		 * System.out.println(checkQq("12345"));
		 * System.out.println(checkQq("1234567890"));
		 * System.out.println(checkQq("12345678901"));
		 */

	}
	
	/**
	 * 过滤掉字符串中的HTML标签
	 * @author 刘立雯
	 * Date：2017年02月09日
	 */
	public static String HtmlToText(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;
 
        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
                                                                                                        // }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
                                                                                                    // }
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
 
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签
 
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签
 
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签
 
            textStr = htmlStr;
 
        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }
 
        return textStr;// 返回文本字符串
    }
	
	/**
	 * 功能：判断字符串是否为数字字母组成
	 * @author Senghor<br>
	 * @date 2018年1月4日 下午4:52:52
	 */
	public static boolean isNumerLetter(String str) {
		Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 拼接字符串用指定字符拼接
	 * strings数组
	 * joint指定字符
	 * @author Senghor<br>
	 * @date 2018年1月12日 上午9:53:44
	 */
	public static String pingString(String[] strings,String joint){
		String string = "";
		if (strings!=null) {
			for (int i = 0; i < strings.length; i++) {
				string += strings[i] + joint;
			}
		}
		return string;
	}
}
