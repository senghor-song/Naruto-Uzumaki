package com.ruiec.web.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Map集合工具类
 * @author 刘立雯
 * Date：2016年09月19日
 */
public class MapUtils {
	
	/**
	 * TreeMap集合拼接字符串
	 * @author 刘立雯
	 * Date：2016年09月19日
	 */
	public static StringBuffer getTreeMapString(TreeMap<?, ?> treeMap) {
		StringBuffer sb=new StringBuffer();
		Iterator it=treeMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry=(Entry) it.next();
			sb.append(entry.getKey());
			sb.append("=");
			sb.append(entry.getValue());
			sb.append("&");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb;
	}
	
	/**
	 * TreeMap集合转换XML(参数带<![CDATA[]])
	 * @author 刘立雯
	 * Date：2016年09月19日
	 */
	public static String getTreeMapToXmlCDATA(TreeMap<?, ?> treeMap) {
		String start="<![CDATA[";
		String end="]]";
		Document document=DocumentHelper.createDocument();
		Element xml=document.addElement("xml");
		Iterator it=treeMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry=(Entry) it.next();
			Element xml_sub=xml.addElement((String) entry.getKey());
			xml_sub.setText(start+entry.getValue()+end);//拼接字符串组成xml值
		}
		return document.asXML();
	}
	
	/**
	 * TreeMap集合转换XML
	 * @author 刘立雯
	 * Date：2016年09月19日
	 */
	public static String getTreeMapToXml(TreeMap<?, ?> treeMap) {
		Document document=DocumentHelper.createDocument();
		Element xml=document.addElement("xml");
		Iterator it=treeMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry=(Entry) it.next();
			Element xml_sub=xml.addElement((String) entry.getKey());
			xml_sub.setText(""+entry.getValue()+"");
		}
		return document.asXML();
	}
	
	/**
	 * json数据转Map集合
	 * @author 刘立雯
	 * Date：2016年10月19日
	 */
	public static Map<String, Object> getJsonToMap(String jsonStr) {
		Map<String, Object> data=new HashMap<String, Object>();
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
	    Iterator ite = jsonObject.keys();
	    // 遍历jsonObject数据,添加到Map对象
	    while (ite.hasNext()) {
	        String key = ite.next().toString();
	        String value = jsonObject.get(key).toString();
	        data.put(key, value);
	    }
	    return data;
	}
}
