package com.xiaoyi.ssm.util;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseXMLUtils {
    /**
     * @Description: 将Document对象转为Map（String→Document→Map）
     * @author 宋高俊
     * @param doc 文档内容
     * @param flag 是否保留空值的kay
     * @return
     * @date 2018年11月23日 上午11:40:03
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Object> Dom2Map(Document doc, boolean flag){  
        Map<String, Object> map = new HashMap<String, Object>();  
        if(doc == null)  
            return map;  
        Element root = doc.getRootElement();  
        for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {  
            Element e = (Element) iterator.next();  
            //System.out.println(e.getName());  
            List list = e.elements();  
            if(list.size() > 0){  
                map.put(e.getName(), Dom2Map(e, flag));  
            }else  
                map.put(e.getName(), e.getText());  
        }  
        return map;  
    }

    /**
     * @Description: 将Element对象转为Map（String→Document→Element→Map）
     * @author 宋高俊
     * @param doc 文档内容
     * @param flag 是否保留空值的kay
     * @return
     * @date 2018年11月23日 上午11:40:03
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map Dom2Map(Element e, boolean flag) {
		Map map = new HashMap();
		List list = e.elements();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Element iter = (Element) list.get(i);
				List mapList = new ArrayList();
				if (iter.elements().size() > 0) {
					Map m = Dom2Map(iter, flag);
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(m);
						}
						if (obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = (List) obj;
							mapList.add(m);
						}
						map.put(iter.getName(), mapList);
					} else {
						map.put(iter.getName(), m);
					}
				} else {
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(iter.getText());
						}
						if (obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = (List) obj;
							mapList.add(iter.getText());
						}
						map.put(iter.getName(), mapList);
					} else {
						if (flag) {
							map.put(iter.getName(), iter.getText());// 公共map // resultCode=0
						} else {
							if (iter.getText().length() > 0) {
								map.put(iter.getName(), iter.getText());// 公共map // resultCode=0
							}
						}
					}
				}
			}
		} else {
			if (flag) {
				map.put(e.getName(), e.getText());
			} else {
				if (e.getText().length() > 0) {
					map.put(e.getName(), e.getText());
				}
			}
		}
		return map;
	}

    @SuppressWarnings({ "rawtypes", "unused" })
	public static void main(String[] args) {   
        String str1 = "<HEADER>" +
                "       <POOL_ID>2</POOL_ID>" +
                "       <DB_ID>EUR</DB_ID>" +
                "       <CHANNEL_ID>11</CHANNEL_ID>" +
                "       <USERNAME>tom</USERNAME>" +
                "       <PASSWORD>sss</PASSWORD>" +
                "   </HEADER>";
        String str2 = "<ROOT>" +
                     "  <HEADER>" +
                     "      <POOL_ID>2</POOL_ID>" +
                     "      <CHANNEL_ID>11</CHANNEL_ID>" +
                     "      <USERNAME>tom</USERNAME>" +
                     "      <PASSWORD>sss</PASSWORD>" +
                     "  </HEADER>" +
                     "  <BODY>" +
                     "      <BUSLIST>" +
                     "          <PHONE_NO>7107300212</PHONE_NO>" +
                     "          <TRACE_ID>97D2C7D26224A2DAE9A1CB501E60F395</TRACE_ID>" +
                     "          <TENANT_ID>EUR</TENANT_ID>" +
                     "          <LANG>zh_CN</LANG>" +
                     "      </BUSLIST>" +
                     "      <BUSLIST>" +
                     "          <PHONE_NO>2222300212</PHONE_NO>" +
                     "          <TRACE_ID>444424A2DAE9A1CB501E60F395</TRACE_ID>" +
                     "          <TENANT_ID>USA</TENANT_ID>" +
                     "          <LANG>zh_CN</LANG>" +
                     "      </BUSLIST>" +
                     "  </BODY>" +
                     "</ROOT>";
        SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(new ByteArrayInputStream(str1.getBytes()));
            Map map = Dom2Map(document,false);
            System.out.println("map>>> " + map);
            /*
            {DB_ID=EUR, CHANNEL_ID=11, USERNAME=tom, PASSWORD=sss, POOL_ID=2}
            {BODY={BUSLIST=[{TRACE_ID=97D2C7D26224A2DAE9A1CB501E60F395, PHONE_NO=7107300212, LANG=zh_CN, TENANT_ID=EUR}, {TRACE_ID=444424A2DAE9A1CB501E60F395, PHONE_NO=2222300212, LANG=zh_CN, TENANT_ID=USA}]}, HEADER={CHANNEL_ID=11, USERNAME=tom, PASSWORD=sss, POOL_ID=2}}
            */
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
