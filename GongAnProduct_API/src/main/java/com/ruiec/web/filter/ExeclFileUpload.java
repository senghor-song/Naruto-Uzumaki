package com.ruiec.web.filter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruiec.framework.web.file.FileClient;
//import com.ruiec.web.util.SettingUtils;

/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 文件上传过滤器
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月22日
 */
public class ExeclFileUpload implements Filter {
	
	public static final ObjectMapper om = new ObjectMapper();

	@SuppressWarnings("unused")
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		

		//文件保存目录路径
		String savePath = request.getSession().getServletContext().getRealPath("/") + "attached/";

		//文件保存目录URL
		String saveUrl  = request.getContextPath() + "/attached/";

		//定义允许上传的文件扩展名
		HashMap<String, String[]> extMap = new HashMap<String, String[]>();
		/*extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");*/
		String imgTypeStr = null/*SettingUtils.get().getImgType()*/;
		String resourceTypeStr = null/*SettingUtils.get().getResourceType()*/;
		if(StringUtils.isBlank(imgTypeStr)){
			extMap.put("image", new String[]{});
		}else{
			extMap.put("image", imgTypeStr.split(","));
		}
		extMap.put("flash", new String[]{"swf", "flv"});
		extMap.put("media", new String[]{"swf", "flv", "mp3", "wav", "wma", "wmv", "mid", "avi", "mpg", "asf", "rm", "rmvb" });
		if(StringUtils.isBlank(resourceTypeStr)){
			extMap.put("file", new String[]{});
		}else{
			extMap.put("file", resourceTypeStr.split(","));
		}
		

		response.setContentType("text/html; charset=UTF-8");

		if(!ServletFileUpload.isMultipartContent(request)){
			response.getWriter().println(getError("请选择文件。"));
			return;
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isFile() && !uploadDir.exists()){
			uploadDir.mkdirs();
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			response.getWriter().println(getError("上传目录没有写权限。"));
			return;
		}

		String dirName = request.getParameter("dir");
		if (dirName == null || dirName.trim().equals("")) {
			dirName = "image";
		}
		
		if(!extMap.containsKey(dirName)){
			response.getWriter().println(getError("目录名不正确。"));
			return;
		}
		
		//最大文件大小
		long maxSize = 20000;
		if(dirName.equals("image")){
			maxSize = 1024000000/*SettingUtils.get().getImgMaxSize()* 1024*/;
		}else{
			maxSize = 1024000000/*SettingUtils.get().getMaxSize() * 1024*/;
		}
		
		//创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = itr.next();
				String fileName = item.getName();
				if (!item.isFormField()) {
					//检查文件大小
					if(item.getSize() > maxSize){
						response.getWriter().println(getError("上传文件大小不能超过"+maxSize/1024+"K"+"。"));
						return;
					}
					//检查扩展名
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					boolean exist = false;
					for(String tmp : extMap.get(dirName)){
						if(tmp.equals(fileExt)){
							exist = true;
							break;
						}
					}
					/*if(!exist){
						response.getWriter().println(getError("上传文件扩展名是不允许的扩展名。只允许" + Arrays.<String>asList(extMap.get(dirName)).toString() + "格式。"));
						return;
					}*/
					String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
						File uploadedFile = new File(savePath, newFileName);
						item.write(uploadedFile);
						String content = FileClient.backgroundFileUpload(uploadedFile, dirName, "http://115.29.106.174:8091"/*SettingUtils.get().getFileServerUrl()*/);
						//System.out.println(content.substring(content.indexOf("h"),content.indexOf("}")-1));
						response.getWriter().println(content);
						uploadedFile.delete();
				}
			}
		}catch(Exception e){
			response.getWriter().println(getError("上传文件失败。"));
			e.printStackTrace();
			return;
		}
		return;
		//chain.doFilter(req, res);
	}

	private String getError(String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", new Integer(1));
		map.put("message", message);
		String message2 = null;
		try {
			message2 = om.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return message2;
	}	
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}
}
