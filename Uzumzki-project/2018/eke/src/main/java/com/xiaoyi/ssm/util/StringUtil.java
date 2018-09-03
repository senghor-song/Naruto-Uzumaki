package com.xiaoyi.ssm.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.xiaoyi.ssm.model.Estate;
import com.xiaoyi.ssm.model.MassProperty;
import com.xiaoyi.ssm.model.MassPropertyDescTemplate;
import com.xiaoyi.ssm.model.MassPropertyTitleLib;
import com.xiaoyi.ssm.service.MassPropertyTemplateService;

/**
 * @Description: 字符串操作工具类
 * @author 宋高俊
 * @date 2018年7月20日 上午11:04:25
 */
public class StringUtil {

	/**
	 * @Description: 比较字符串是否相同
	 * @author 宋高俊
	 * @param beCompared
	 *            被比较的字符串
	 * @param toCompare
	 *            进行比较的字符串
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
	 * @Description: 用于房源标题生成
	 * @author 宋高俊
	 * @date 2018年7月23日 下午3:19:09
	 */
	public static List<String> replaceTitle(MassProperty massProperty, int type) {
		List<String> list = new ArrayList<String>();
		MassPropertyTemplateService mptls = SpringUtils.getBean("massPropertyTitleLibServiceImpl", MassPropertyTemplateService.class);
		List<MassPropertyTitleLib> mptlsList = mptls.selectByEstateTypeTitle(massProperty.getEstateid(), type);
		for (int i = 0; i < mptlsList.size(); i++) {
			String title = mptlsList.get(i).getTitle();
			for (Field field : massProperty.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (title.indexOf(field.getName()) != -1) {
					try {
						title = title.replace("{" + field.getName() + "}", field.get(massProperty) != null ? field.get(massProperty).toString() : "");
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}

			}
			list.add(title);
		}
		return list;
	}

	/**
	 * @Description: 用于房源描述生成
	 * @author 宋高俊
	 * @date 2018年7月23日 下午3:19:09
	 */
	public static List<String> replaceDesc(MassProperty massProperty, int type) {
		List<String> list = new ArrayList<String>();
		MassPropertyTemplateService mptls = SpringUtils.getBean("massPropertyTitleLibServiceImpl", MassPropertyTemplateService.class);
		List<MassPropertyDescTemplate> mpdtsList = mptls.selectByEstateTypeDesc(type);
		Estate estate = (Estate) RedisUtil.getRedisOne(Global.REDIS_ESTATE_MAP, massProperty.getEstateid());
		for (int i = 0; i < mpdtsList.size(); i++) {
			String desc = mpdtsList.get(i).getTemplate();
			for (Field field : estate.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (desc.indexOf(field.getName()) != -1) {
					try {
						desc = desc.replace("{" + field.getName() + "}", field.get(estate) != null ? field.get(estate).toString() : "");
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}

			list.add(desc);
		}
		return list;

	}


	/**  
	 * @Description: string转unicode编码
	 * @author 宋高俊  
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
	 * @author 宋高俊  
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
}
