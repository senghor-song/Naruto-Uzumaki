package com.ruiec.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;


/**
 * 流工具类
 * @author 刘立雯
 * Date：2016年09月20日
 */
public class StreamsUtils {

	/**
	 * 指定字符编码格式，读取文件
	 * @throws IOException 
	 */
	public static StringBuffer getInputStreamsContent(InputStream in,String charset) throws IOException {
		StringBuffer sb=new StringBuffer();
		InputStreamReader inReader;
		//设置字符编码格式
		if(StringUtils.isBlank(charset)){
			charset="GBK";
		}
		inReader=new InputStreamReader(in,charset);
		char[] ch=new char[1024];
		int readCount=0;
		while ((readCount = inReader.read(ch)) != -1) {  
            sb.append(ch, 0, readCount);  
        }  
        inReader.close();  
        in.close();  
        return sb;  
	}
}
