package com.ruiec.web.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class CommonGenerator {

	public static void main(String[] args) throws IOException, TemplateException {
		Configuration configuration = new Configuration();  
		configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\Administrator\\Desktop\\template"));  
		configuration.setObjectWrapper(new DefaultObjectWrapper());  
		configuration.setDefaultEncoding("UTF-8");   //这个一定要设置，不然在生成的页面中 会乱码  
		File entityFile = new File("C:\\Users\\Administrator\\Desktop\\template\\entity");
		File[] entityFiles = entityFile.listFiles();
		for(File file : entityFiles){
			String name = file.getName().substring(0, file.getName().indexOf("."));//实体名称
			if(name.equals("BaseEntity") || name.equals("SortEntity")){
				continue;
			}
			String anno = null;//注释名称
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			for(int i = 0; i < 30; i++)
				anno = br.readLine();
			anno = anno.substring(3);
			br.close();
			
			//获取或创建一个模版。  
//			Template templateDao = configuration.getTemplate("dao.ftl");
//			Template templateDaoImpl = configuration.getTemplate("daoImpl.ftl");
			Template templateService = configuration.getTemplate("service.ftl");
			Template templateServiceImpl = configuration.getTemplate("serviceImpl.ftl");
			Map<String, Object> paramMap = new HashMap<String, Object>();  
			paramMap.put("name", name);
			paramMap.put("anno", anno);
			paramMap.put("auth", "肖学良");
			paramMap.put("time", new Date());
			paramMap.put("lowName", name.substring(0, 1).toLowerCase() + name.substring(1));
			
//			Writer writer1 = new OutputStreamWriter(new FileOutputStream("D:\\用户目录\\Desktop\\template\\dao\\" + name + "Dao.java"),"UTF-8");
//			Writer writer2 = new OutputStreamWriter(new FileOutputStream("D:\\用户目录\\Desktop\\template\\daoImpl\\" + name + "DaoImpl.java"),"UTF-8");
			Writer writer3 = new OutputStreamWriter(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\template\\service\\" + name + "Service.java"),"UTF-8");
			Writer writer4 = new OutputStreamWriter(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\template\\serviceImpl\\" + name + "ServiceImpl.java"),"UTF-8");
//			templateDao.process(paramMap, writer1);
//			templateDaoImpl.process(paramMap, writer2);
			templateService.process(paramMap, writer3);
			templateServiceImpl.process(paramMap, writer4);
			
//			writer1.close();
//			writer2.close();
			writer3.close();
			writer4.close();
		}
	}
	
	public static void gene() throws IOException, TemplateException{
		Configuration configuration = new Configuration();  
		configuration.setDirectoryForTemplateLoading(new File("D:\\用户目录\\Desktop\\template"));  
		configuration.setObjectWrapper(new DefaultObjectWrapper());  
		configuration.setDefaultEncoding("UTF-8");   //这个一定要设置，不然在生成的页面中 会乱码  
		File entityFile = new File("D:\\用户目录\\Desktop\\template\\entity");
		File[] entityFiles = entityFile.listFiles();
		for(File file : entityFiles){
			String name = file.getName().substring(0, file.getName().indexOf("."));//实体名称
			if(name.equals("BaseEntity") || name.equals("SortEntity")){
				continue;
			}
			String anno = null;//注释名称
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			for(int i = 0; i < 30; i++)
				anno = br.readLine();
			anno = anno.substring(3);
			br.close();
			
			//获取或创建一个模版。  
//			Template templateDao = configuration.getTemplate("dao.ftl");
//			Template templateDaoImpl = configuration.getTemplate("daoImpl.ftl");
			Template templateService = configuration.getTemplate("service.ftl");
			Template templateServiceImpl = configuration.getTemplate("serviceImpl.ftl");
			Map<String, Object> paramMap = new HashMap<String, Object>();  
			paramMap.put("name", name);
			paramMap.put("anno", anno);
			paramMap.put("auth", "肖学良");
			paramMap.put("time", new Date());
			paramMap.put("lowName", name.substring(0, 1).toLowerCase() + name.substring(1));
			
//			Writer writer1 = new OutputStreamWriter(new FileOutputStream("D:\\用户目录\\Desktop\\template\\dao\\" + name + "Dao.java"),"UTF-8");
//			Writer writer2 = new OutputStreamWriter(new FileOutputStream("D:\\用户目录\\Desktop\\template\\daoImpl\\" + name + "DaoImpl.java"),"UTF-8");
			Writer writer3 = new OutputStreamWriter(new FileOutputStream("D:\\用户目录\\Desktop\\template\\service\\" + name + "Service.java"),"UTF-8");
			Writer writer4 = new OutputStreamWriter(new FileOutputStream("D:\\用户目录\\Desktop\\template\\serviceImpl\\" + name + "ServiceImpl.java"),"UTF-8");
//			templateDao.process(paramMap, writer1);
//			templateDaoImpl.process(paramMap, writer2);
			templateService.process(paramMap, writer3);
			templateServiceImpl.process(paramMap, writer4);
			
//			writer1.close();
//			writer2.close();
			writer3.close();
			writer4.close();
		}
	}
	
	public static void rename() throws IOException{
		File entityFile = new File("C:\\Users\\Administrator\\Desktop\\二元期权");
		File rename = new File("C:\\Users\\Administrator\\Desktop\\rename");
		File[] files = entityFile.listFiles();
		for(File file : files){
			String name = file.getName();
			String reName = camelName(name);
			reName = reName.substring(2);
			System.out.println(reName);
			File newfile = new File(rename, reName);
			file.renameTo(newfile);
			
//			fw.write("");
//			fw.close();
			
			FileReader fr = new FileReader(newfile);
			BufferedReader br = new BufferedReader(fr);
			String join = "";
			String tmp = null;
			while((tmp = br.readLine()) != null){
				String newStr = camelName(tmp);
//				bos.write(newStr.getBytes());
//				fos.
//				bos.
				join += newStr;
				join += "\r\n";
				System.out.println(newStr);
//				osw.append(newStr);
			}
			FileWriter fw =  new FileWriter(newfile);
//			osw.close();
			br.close();
			fw.write(join);
			fw.close();
//			bos.close();
		}
	}

	
	/**
	 * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
	 * 例如：HELLO_WORLD->HelloWorld
	 * @param name 转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String camelName(String name) {
	    StringBuilder result = new StringBuilder();
	    // 快速检查
	    if (name == null || name.isEmpty()) {
	        // 没必要转换
	        return "";
	    } else if (!name.contains("_")/* &&!name.contains("public")*/) {
	        // 不含下划线，仅将首字母小写
	        return name.substring(0, 1).toLowerCase() + name.substring(1);
	    }
	    // 用下划线将原始字符串分割
	    String camels[] = name.split("_");
	    for (String camel :  camels) {
	        // 跳过原始字符串中开头、结尾的下换线或双重下划线
	        if (camel.isEmpty()) {
	            continue;
	        }
	        // 处理真正的驼峰片段
	        if (result.length() == 0) {
	            // 第一个驼峰片段，全部字母都小写
	            result.append(camel.toLowerCase());
	        } else {
	            // 其他的驼峰片段，首字母大写
	            result.append(camel.substring(0, 1).toUpperCase());
	            result.append(camel.substring(1).toLowerCase());
	        }
	    }
	    return result.toString();
	}
}
