package com.xiaoyi.ssm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/***
 * 汉字工具类
 * 
 * @author csharper
 * @since 2014.12.26
 *
 */
public class ChineseCharacterUtil {
	/***
	 * 将汉字转成拼音(取首字母或全拼)
	 * 
	 * @param hanzi
	 * @param full 是否全拼
	 * @return
	 */
	public static String convertHanzi2Pinyin(String hanzi, boolean full) {
		/***
		 * ^[\u2E80-\u9FFF]+$ 匹配所有东亚区的语言 
		 * ^[\u4E00-\u9FFF]+$ 匹配简体和繁体
		 * ^[\u4E00-\u9FA5]+$ 匹配简体
		 */
		String regExp = "^[\u4E00-\u9FFF]+$";
		StringBuffer sb = new StringBuffer();
		if (hanzi == null || "".equals(hanzi.trim())) {
			return "";
		}
		String pinyin = "";
		for (int i = 0; i < hanzi.length(); i++) {
			char unit = hanzi.charAt(i);
			if (match(String.valueOf(unit), regExp))// 是汉字，则转拼音
			{
				pinyin = convertSingleHanzi2Pinyin(unit);
				if (full) {
					sb.append(pinyin);
				} else {
					sb.append(pinyin.charAt(0));
				}
			} else {
				sb.append(unit);
			}
		}
		return sb.toString();
	}

	/***
	 * 将单个汉字转成拼音
	 * 
	 * @param hanzi
	 * @return
	 */
	private static String convertSingleHanzi2Pinyin(char hanzi) {
		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		String[] res;
		StringBuffer sb = new StringBuffer();
		try {
			res = PinyinHelper.toHanyuPinyinStringArray(hanzi, outputFormat);
			sb.append(res[0]);// 对于多音字，只用第一个拼音
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return sb.toString();
	}

	/***
	 * @param str
	 *            源字符串
	 * @param regex
	 *            正则表达式
	 * @return 是否匹配
	 */
	public static boolean match(String str, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}
	
	/**  
	 * @Description: 获取字符串首字母大写
	 * @author 宋高俊  
	 * @param hanzi
	 * @param full
	 * @return 
	 * @date 2018年9月22日 下午2:00:47 
	 */ 
	public static String getPinYingLetter(String hanzi) {
		if (StringUtil.isBank(hanzi)) {
			return "";
		}
		/***
		 * ^[\u2E80-\u9FFF]+$ 匹配所有东亚区的语言 
		 * ^[\u4E00-\u9FFF]+$ 匹配简体和繁体
		 * ^[\u4E00-\u9FA5]+$ 匹配简体
		 */
		String regExp = "^[\u4E00-\u9FFF]+$";
		StringBuffer sb = new StringBuffer();
		String pinyin = "";
		char unit = hanzi.charAt(0);
		if (match(String.valueOf(unit), regExp)) {
			pinyin = convertSingleHanzi2Pinyin(unit);
				sb.append(pinyin.charAt(0));
		} else {
			sb.append(unit);
		}
		return sb.toString().toUpperCase();
	}

//	public static void main(String[] args) {
//		System.out.println(getPinYingLetter("我是中国人", false));
//	}
}