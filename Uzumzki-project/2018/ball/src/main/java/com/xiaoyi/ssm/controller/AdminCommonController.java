package com.xiaoyi.ssm.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 后台公共页面
 * @author 宋高俊
 * @date 2018年8月16日 下午4:49:49
 */
@Controller
@RequestMapping("admin/common")
public class AdminCommonController {

	/**
	 * @Description: 后台登录页面
	 * @author song
	 * @date 2018年7月25日 下午12:15:57
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "admin/login";
	}

	/**
	 * @Description: 后台注册页面
	 * @author song
	 * @date 2018年7月25日 下午12:15:57
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "admin/register";
	}

	/**
	 * @Description: 左侧菜单页面
	 * @author song
	 * @date 2018年7月25日 下午1:43:06
	 */
	@RequestMapping(value = "/left")
	public String left() {
		return "admin/layout/leftSidebar";
	}

	/**
	 * @Description: 顶部菜单页面
	 * @author song
	 * @date 2018年7月25日 下午1:43:06
	 */
	@RequestMapping(value = "/top")
	public String top() {
		return "admin/layout/topSidebar";
	}

	/**
	 * @Description: 首页
	 * @author song
	 * @date 2018年7月25日 下午1:43:06
	 */
	@RequestMapping(value = "/index")
	public String index() {
		return "admin/index";
	}

	/**
	 * 顶部页面数据
	 * 
	 * @Description:
	 * @author 宋高俊
	 * @date 2018年8月20日 下午6:55:06
	 */
	@RequestMapping(value = "/countTop")
	@ResponseBody
	public ApiMessage countTop() {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) RedisUtil.getRedisOne(Global.REDIS_TOP_TAG_MAP,
				Global.REDIS_TOP_TAG_MAP);
		return ApiMessage.succeed(map);
	}
	

	private String getContent(InputStream... ises) throws IOException {
		if (ises != null) {
			StringBuilder result = new StringBuilder();
			BufferedReader br;
			String line;
			for (InputStream is : ises) {
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				while ((line = br.readLine()) != null) {
					result.append(line);
				}
			}
			return result.toString();
		}
		return null;
	}
	
	/**  
	 * @Description: 
	 * @author 宋高俊  
	 * @date 2018年8月29日 下午7:45:29 
	 */ 
	@SuppressWarnings({ "resource", "unused" })
	@RequestMapping("/htmlToWord")
	public void htmlToWord(HttpServletRequest request, HttpServletResponse response, String html) throws IOException {

//		String content = "<html>" + html + "</html>";

		InputStream bodyIs = new FileInputStream("D:\\abc.html");
		InputStream cssIs = new FileInputStream("D:\\abc.css");
		
		String body = this.getContent(bodyIs);
		String css = this.getContent(cssIs);
//		// 拼一个标准的HTML格式文档
		String content = "<html><head><style>" + css + "</style></head><body>" + body + "</body></html>";
		byte b[] = content.getBytes("utf-8"); // 这里是必须要设置编码的，不然导出中文就会乱码。
		ByteArrayInputStream bais = new ByteArrayInputStream(b);// 将字节数组包装到流中
		/*
		 * 关键地方 生成word格式
		 */
		POIFSFileSystem poifs = new POIFSFileSystem();
		DirectoryEntry directory = poifs.getRoot();
		DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
		// 输出文件
		String fileName = DateUtil.getFormat(new Date(), "yyyy-MM-dd")+"账单审核表";
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/msword");// 导出word格式
		response.addHeader("Content-Disposition",
				"attachment;filename=" + new String((fileName + ".doc").getBytes(), "iso-8859-1"));
		OutputStream ostream = response.getOutputStream();
		poifs.writeFilesystem(ostream);
		bais.close();
		ostream.close();
	}
	
	
	/**
	 * @Description: 上传图片
	 * @author 宋高俊
	 * @date 2018年7月31日 下午6:40:17
	 */
	@RequestMapping(value = "/uploadImage")
	@ResponseBody
	public ApiMessage uploadImage(MultipartFile file, HttpServletRequest request) {
		try {
			return ApiMessage.succeed(Utils.getImageUrl(file));
		} catch (Exception e) {
			return ApiMessage.error();
		}
	}

	/**  
	 * @Description: 获取上传进度数据
	 * @author 宋高俊  
	 * @date 2018年8月14日 上午10:23:30 
	 */ 
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/redisupload", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage redisupload(HttpServletRequest request, String redisname) {
		Staff staff = (Staff) request.getSession().getAttribute("adminloginuser");
		Map<String, Object> map = (Map<String, Object>) RedisUtil.getRedisOne(Global.REDIS_SESSION_UPLOAD_MAP, staff.getStaffid() + redisname);
		return ApiMessage.succeed(map);
	}
	
	/**  
	 * @Description: 删除上传进度数据
	 * @author 宋高俊  
	 * @date 2018年8月14日 上午10:23:30 
	 */ 
	@RequestMapping(value = "/deleteRedisupload", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage deleteRedisupload(HttpServletRequest request, String redisname) {
		Staff staff = (Staff) request.getSession().getAttribute("adminloginuser");
		RedisUtil.delRedis(Global.REDIS_SESSION_UPLOAD_MAP, staff.getStaffid() + redisname);
		return ApiMessage.succeed();
	}
}
