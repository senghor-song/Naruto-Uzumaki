package com.xiaoyi.ssm.controller.pc;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aliyun.oss.OSSClient;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.EstateImageDto;
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.service.EstateEmpService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 图片控制器
 * @author 宋高俊
 * @date 2018年7月7日 下午2:35:39
 */
@Controller
@RequestMapping("/image")
public class ImageController {
	@Autowired
	private EstateEmpService estateEmpService;

	/**
	 * @Description: 图库管理
	 * @author 宋高俊
	 * @date 2018年7月10日 上午11:42:27
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/imageManage")
	public String imageManage(Model model, HttpServletRequest request, PageInfo pageInfo, EstateImageDto estateImageDto) {
		// 获取登录用户
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		// 获取已保存的图片小区
		List<EstateImageDto> estateImageDtos = estateEmpService.selectByEmpEstate(employee.getId(), estateImageDto.getType());
		model.addAttribute("estateImageDtos", estateImageDtos);
		estateImageDto.setEmpid(employee.getId());
		PageHelper.startPage(pageInfo.getPageNum(), 18);
		// 获取小区图片
		List list = new ArrayList<>();
		if (estateImageDto.getType() == 0) {
			list = estateEmpService.selectByEmpEstateImage(estateImageDto);
		}else if (estateImageDto.getType() == 1) {
			list = estateEmpService.selectByEmpPropertyImage(estateImageDto);
		}else if (estateImageDto.getType() == 2) {
			list = estateEmpService.selectByEmpHouseTypeImage(estateImageDto);
		}
		pageInfo = new PageInfo<>(list);
		model.addAttribute("page", pageInfo);
		model.addAttribute("estateImageDto", estateImageDto);
		return "image/imageManage";
	}

	/**
	 * @Description: 图库管理
	 * @author 宋高俊
	 * @date 2018年7月10日 上午11:42:27
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/ajaxImageManage")
	public String ajaxImageManage(Model model, HttpServletRequest request, PageInfo pageInfo, EstateImageDto estateImageDto) {
		// 获取登录用户
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		estateImageDto.setEmpid(employee.getId());
		PageHelper.startPage(pageInfo.getPageNum(), 18);
		// 获取小区图片
		List list = new ArrayList<>();
		if (estateImageDto.getType() == 0) {
			list = estateEmpService.selectByEmpEstateImage(estateImageDto);
		}else if (estateImageDto.getType() == 1) {
			list = estateEmpService.selectByEmpPropertyImage(estateImageDto);
		}else if (estateImageDto.getType() == 2) {
			list = estateEmpService.selectByEmpHouseTypeImage(estateImageDto);
		}
		pageInfo = new PageInfo<>(list);
		model.addAttribute("page", pageInfo);
		model.addAttribute("estateImageDto", estateImageDto);
		return "image/ajaxImageManage";
	}

	/**
	 * @Description: 删除图库
	 * @author 宋高俊
	 * @date 2018年7月10日 上午11:42:27
	 */
	@RequestMapping("/delImageManage")
	@ResponseBody
	public ApiMessage delImageManage(String[] ids, Integer type) {
		int flag = estateEmpService.delImageManage(ids, type);
		if (flag > 0) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 多图片上传控制器
	 * @author 宋高俊
	 * @date 2018年7月7日 下午2:37:37
	 */
	@RequestMapping("/uploadImages")
	@ResponseBody
	public static ApiMessage uploadFiles(MultipartHttpServletRequest request) {
		try {
			List<String> urls = new ArrayList<>();

			MultiValueMap<String, MultipartFile> map = request.getMultiFileMap();
			List<MultipartFile> list = map.get("file");// 获取到文件的列表
			// String path= getFilePath(username,directory); //忽略掉，为文件最终上传的地址
			List<String> filenameList = new ArrayList<>();//
			// 将图片进行存储
			for (int i = 0; i < list.size(); i++) {
				MultipartFile mFile = list.get(i);

				CommonsMultipartFile cf = (CommonsMultipartFile) mFile;
				DiskFileItem fileItem = (DiskFileItem) cf.getFileItem();
				if (fileItem.getSize() == 0) {
					// 没有文件进行下一个文件
					continue;
				}
				InputStream fileContent = fileItem.getInputStream();
				String fileName = fileItem.getName();
				fileName = Utils.getUUID() + fileName.substring(fileName.lastIndexOf("."));
				// 域名根节点
				String endpoint = Global.aliyunOssEndpoint;
				// 账户ID
				String accessKeyId = Global.aliyunOssAccessKeyId;
				// 账户密码
				String accessKeySecret = Global.aliyunOssAccessKeySecret;
				// 创建OSSClient实例。
				OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
				// 设置上传文件保存目录，按照年月日分文件夹存放
				Calendar now = Calendar.getInstance();
				String remoteFilePath = now.get(Calendar.YEAR) + "/" + (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.DAY_OF_MONTH) + "/";

				String bucketName = Global.aliyunOssBucketName;

				remoteFilePath += fileName;
				// 上传文件流。
				ossClient.putObject(bucketName, remoteFilePath, fileContent);
				// 关闭OSSClient。
				ossClient.shutdown();
				String imageUrl = Global.aliyunOssIpAddress + remoteFilePath;
				urls.add(imageUrl);
			}
			return ApiMessage.succeed(urls);
		} catch (Exception e) {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 多图片上传控制器
	 * @author 宋高俊
	 * @date 2018年7月7日 下午2:37:37
	 */
	@RequestMapping("/uploadImage")
	@ResponseBody
	public static ApiMessage uploadFile(@Param("uploadify") MultipartFile mFile, HttpServletRequest request) {
		request.getAttribute("uploadify");
		try {
			if (mFile == null) {
				return ApiMessage.error("");
			}
			CommonsMultipartFile cf = (CommonsMultipartFile) mFile;
			DiskFileItem fileItem = (DiskFileItem) cf.getFileItem();
			if (fileItem.getSize() == 0) {
				// 没有文件进行下一个文件
				return ApiMessage.succeed("");
			}
			InputStream fileContent = fileItem.getInputStream();
			String fileName = fileItem.getName();
			fileName = Utils.getUUID() + fileName.substring(fileName.lastIndexOf("."));
			// 域名根节点
			String endpoint = Global.aliyunOssEndpoint;
			// 账户ID
			String accessKeyId = Global.aliyunOssAccessKeyId;
			// 账户密码
			String accessKeySecret = Global.aliyunOssAccessKeySecret;
			// 创建OSSClient实例。
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			// 设置上传文件保存目录，按照年月日分文件夹存放
			Calendar now = Calendar.getInstance();
			String remoteFilePath = now.get(Calendar.YEAR) + "/" + (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.DAY_OF_MONTH) + "/";

			String bucketName = Global.aliyunOssBucketName;

			remoteFilePath += fileName;
			// 上传文件流。
			ossClient.putObject(bucketName, remoteFilePath, fileContent);
			// 关闭OSSClient。
			ossClient.shutdown();
			String imageUrl = Global.aliyunOssIpAddress + remoteFilePath;
			return ApiMessage.succeed(imageUrl);
		} catch (Exception e) {
			return ApiMessage.error();
		}
	}

	@RequestMapping("image")
	public String name() {
		return "image";
	}

}
