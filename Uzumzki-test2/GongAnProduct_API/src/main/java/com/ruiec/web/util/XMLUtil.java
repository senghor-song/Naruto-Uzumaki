package com.ruiec.web.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * XML操作类
 * @author 刘立雯
 * Date：2016年10月10日
 */
public class XMLUtil {

   /**
    * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
    * @param strxml
    * @return
    * @throws JDOMException
    * @throws IOException
    */
   public static Map doXMLParse(String strxml) throws JDOMException, IOException {
      strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

      if(null == strxml || "".equals(strxml)) {
         return null;
      }

      Map m = new HashMap();

      InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
      SAXBuilder builder = new SAXBuilder();
      Document doc = builder.build(in);
      Element root = doc.getRootElement();
      List list = root.getChildren();
      Iterator it = list.iterator();
      while(it.hasNext()) {
         Element e = (Element) it.next();
         String k = e.getName();
         String v = "";
         List children = e.getChildren();
         if(children.isEmpty()) {
            v = e.getTextNormalize();
         } else {
            v = XMLUtil.getChildrenText(children);
         }

         m.put(k, v);
      }

      //关闭流
      in.close();

      return m;
   }

   /**
    * 获取子结点的xml
    * @param children
    * @return String
    */
   public static String getChildrenText(List children) {
      StringBuffer sb = new StringBuffer();
      if(!children.isEmpty()) {
         Iterator it = children.iterator();
         while(it.hasNext()) {
            Element e = (Element) it.next();
            String name = e.getName();
            String value = e.getTextNormalize();
            List list = e.getChildren();
            sb.append("<" + name + ">");
            if(!list.isEmpty()) {
               sb.append(XMLUtil.getChildrenText(list));
            }
            sb.append(value);
            sb.append("</" + name + ">");
         }
      }

      return sb.toString();
   }

   /**
    * XML转字符串   原样取出  
    * @param doc 
    * @return
    */
   public static String getXmlString(org.w3c.dom.Document doc){  
       TransformerFactory tf = TransformerFactory.newInstance();  
       try {  
           Transformer t = tf.newTransformer();  
           t.setOutputProperty(OutputKeys.ENCODING,"UTF-8");//解决中文问题，试过用GBK不行  
           t.setOutputProperty(OutputKeys.METHOD, "html");    
           t.setOutputProperty(OutputKeys.VERSION, "4.0");    
           t.setOutputProperty(OutputKeys.INDENT, "no");    
           ByteArrayOutputStream bos = new ByteArrayOutputStream();  
           t.transform(new DOMSource(doc), new StreamResult(bos));  
           return bos.toString();  
       } catch (TransformerConfigurationException e) {  
           e.printStackTrace();  
       } catch (TransformerException e) {  
           e.printStackTrace();  
       }  
       return "";  
   } 
}