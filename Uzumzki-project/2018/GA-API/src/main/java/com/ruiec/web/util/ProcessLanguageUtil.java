package com.ruiec.web.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

/**
 * 语言处理工具类
 * @author 刘立雯
 * Date：2016年09月12日
 */
public class ProcessLanguageUtil {

	/**
	 * 获取国际化参数
	 * @author 刘立雯
	 * Date: 2016年09月09日
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static String getLanguageValue(String languageType,String name) throws FileNotFoundException, IOException{
		ResourceBundle rb = null;
		if(StringUtils.isBlank(languageType))
			rb = ResourceBundle.getBundle("messages_zh_CN");
		else{
			//选择语言类型为中文
			if(languageType.equals("zh"))
				rb = ResourceBundle.getBundle("messages_zh_CN");
			//选择语言类型为英文
			else if(languageType.equals("en"))
				rb = ResourceBundle.getBundle("messages_en_US");  
			//没有选择或选择其他的默认显示中文
			else
				rb = ResourceBundle.getBundle("messages_zh_CN");
		}
		return rb.getString(name);
	}
}
