package com.ruiec.framework.web.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 图片/文件服务器客户端
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月22日
 */
@SuppressWarnings("deprecation")
@Component
public class FileClient implements ServletContextAware{
	
	private static final ObjectMapper om = new ObjectMapper();
	private static ServletContext context = null;

	
	/**
	 * 用于上传对外公开且无需管理的图片
	 * @param src 要上传的图片file对象<br>
	 * @param dir 用于指定图片存放文件夹(如广告可以指定为：ad )<br>
	 * @param url 图片服务器域名或IP+端口（如http://xxxxx/xxxxx/）<br>
	 * @return 返回图片相对于图片服务器的相对路径， 返回Null说明上传失败
	 */
	public static String publicFileUpload(File src, String dir, String url){
		CloseableHttpClient httpclient = HttpClients.createDefault();  
		if(url.endsWith("/")){
			url = url.substring(0, url.length()-1);
		}
		url += "/homeFileUpload";
        HttpPost post = new HttpPost(url);
        FileBody fileBody = new FileBody(src);
        StringBody stringBody = null;
		try {
			if(dir != null && !dir.trim().equals(""))
			stringBody = new StringBody(dir);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        MultipartEntity entity = new MultipartEntity();  
        entity.addPart("file", fileBody);
        if(stringBody != null){
        	entity.addPart("dir", stringBody);  
        }
        post.setEntity(entity);  
        CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String content = null;
        if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){  
            HttpEntity entitys = response.getEntity(); 
            if (entitys != null) {  
                try {
					content = EntityUtils.toString(entitys);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        }  
		ResultBean resultBean = null;
		if(content == null){
			return null;
		}
        try {
        	resultBean = om.readValue(content, ResultBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(resultBean.getError() != null && resultBean.getError().equals("0")){
			return resultBean.getUrl();
		}
		return null;
	}
	
	/**
	 * 用于上传对外公开且无需管理的图片
	 * @param src 要上传的图片file对象<br>
	 * @param dir 用于指定图片存放文件夹(如广告可以指定为：ad )<br>
	 * @param srcFileName 文件源名字
	 * @param url 图片服务器域名或IP+端口（如http://xxxxx/xxxxx/）<br>
	 * @return 返回图片相对于图片服务器的相对路径， 返回Null说明上传失败
	 */
	public static String publicFileUpload(File src, String srcFileName, String dir, String url){
		File file2 = new File(src.getParent(), UUID.randomUUID().toString() + srcFileName.substring(srcFileName.lastIndexOf(".")));
		src.renameTo(file2);
		CloseableHttpClient httpclient = HttpClients.createDefault();  
		if(url.endsWith("/")){
			url = url.substring(0, url.length()-1);
		}
		url += "/homeFileUpload";
        HttpPost post = new HttpPost(url);
        FileBody fileBody = new FileBody(file2);
        StringBody stringBody = null;
		try {
			if(dir != null && !dir.trim().equals(""))
			stringBody = new StringBody(dir);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        MultipartEntity entity = new MultipartEntity();  
        entity.addPart("file", fileBody);
        if(stringBody != null){
        	entity.addPart("dir", stringBody);  
        }
        post.setEntity(entity);  
        CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		file2.delete();
		String content = null;
        if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){  
            HttpEntity entitys = response.getEntity(); 
            if (entitys != null) {  
                try {
					content = EntityUtils.toString(entitys);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        }  
		ResultBean resultBean = null;
		if(content == null){
			return null;
		}
        try {
        	resultBean = om.readValue(content, ResultBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(resultBean.getError() != null && resultBean.getError().equals("0")){
			return resultBean.getUrl();
		}
		return null;
	}
	
	/**
	 * 用于上传非对外公开的文件(如合同文档上传后外面是无法直接访问的，需由p2p系统从文件服务器去拿取响应给用户)
	 * @param src 要上传的文件file对象<br>
	 * @param dir 用于指定文件存放文件夹(如合同可以指定为：agreement)
	 * @param url 图片服务器域名或IP+端口（如http://xxxxx/xxxxx/）
	 * @return 返回文件相对于文件服务器的相对路径， 返回Null说明上传失败
	 */
	public static String privateFileUpload(File src, String dir, String url){
		CloseableHttpClient httpclient = HttpClients.createDefault(); 
		if(url.endsWith("/")){
			url = url.substring(0, url.length()-1);
		}
		url += "/privateFileUpload";
        HttpPost post = new HttpPost(url);
        FileBody fileBody = new FileBody(src);
        StringBody stringBody = null;
		try {
			if(dir != null && !dir.trim().equals(""))
			stringBody = new StringBody(dir);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        MultipartEntity entity = new MultipartEntity();  
        entity.addPart("file", fileBody);
        if(stringBody != null){
        	entity.addPart("dir", stringBody);  
        }
        post.setEntity(entity);  
        CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String content = null;
        if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){  
            HttpEntity entitys = response.getEntity(); 
            if (entitys != null) {  
                try {
					content = EntityUtils.toString(entitys);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        }  
		ResultBean resultBean = null;
		if(content == null){
			return null;
		}
        try {
        	resultBean = om.readValue(content, ResultBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(resultBean.getError() != null && resultBean.getError().equals("0")){
			return resultBean.getUrl();
		}
		return null;
	}
	
	/**
	 * 用于从文件服务器上获取非对外公开的文件，并保存到临时文件中，同时返回临时文件的存放路径
	 * @param path 待获取文件 相对于文件服务器的相对路径（如：/xxxxx/xxxxxx.xxx）
	 * @param url 文件服务器域名或IP+端口（如http://xxxxx/xxxxx/）
	 * @return 返回获取到的文件存放路径
	 */
	public static File privateFileDownload(String path, String url){
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		if(url.endsWith("/")){
			url = url.substring(0, url.length()-1);
		}
		url += "/privateFileDownload" + "?path=" + path;
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		InputStream inputStream = null;
		File tempFile = null;
		try {
			response = httpclient.execute(httpGet);
			if (response != null) {
				HttpEntity entity = response.getEntity();
				inputStream = entity.getContent();
				String tempFileName = UUID.randomUUID().toString() + ".txt";
				File tempdir = new File(context.getRealPath("/tempFile"));
				if (!tempdir.exists()) {
					tempdir.mkdirs();
				}
				tempFile = new File(tempdir, tempFileName);
				if (!tempFile.exists()) {
					tempFile.createNewFile();
				}
				FileUtils.copyInputStreamToFile(inputStream, tempFile);
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
				if(inputStream!=null){
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return tempFile;
	}

	/**
	 * 用于配合后台文件上传插件使用
	 * @param src 待上传文件
	 * @param dir 上传目录（如：image，flash）
	 * @param url 文件服务器域名或IP+端口（如http://xxxxx/xxxxx/）
	 * @return
	 */
	public static String backgroundFileUpload(File src, String dir, String url){
		CloseableHttpClient httpclient = HttpClients.createDefault();  
		if(url.endsWith("/")){
			url = url.substring(0, url.length()-1);
		}
		url += "/backgroundFileUpload";
        HttpPost post = new HttpPost(url);
        FileBody fileBody = new FileBody(src);
        StringBody stringBody = null;
		try {
			if(dir != null && !dir.trim().equals(""))
			stringBody = new StringBody(dir);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        MultipartEntity entity = new MultipartEntity();  
        entity.addPart("file", fileBody);
        if(stringBody != null){
        	entity.addPart("dir", stringBody);  
        }
        post.setEntity(entity);  
        CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String content = null;
        if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){  
            HttpEntity entitys = response.getEntity(); 
            if (entitys != null) {  
                try {
					content = EntityUtils.toString(entitys);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        }  
		return content;
	}
	
	public static String backgroundFileManager(String dir, String path, String order, String url){
		CloseableHttpClient httpclient = HttpClients.createDefault();  
		if(url.endsWith("/")){
			url = url.substring(0, url.length()-1);
		}
		url += "/backgroundFileManager";
        HttpPost post = new HttpPost(url);
        List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        
		try {
			if(dir != null && !dir.trim().equals("")){
				pairs.add(new BasicNameValuePair("dir", dir));
			}
			if(path != null && !path.trim().equals("")){
				pairs.add(new BasicNameValuePair("path", path));
			}
			if(order != null && !order.trim().equals("")){
				pairs.add(new BasicNameValuePair("order", order));
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs);
			post.setEntity(entity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String content = null;
        if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){  
            HttpEntity entitys = response.getEntity(); 
            if (entitys != null) {  
                try {
					content = EntityUtils.toString(entitys);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        }  
        return content;
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		context = servletContext;
	}
		
}
	
	

