package com.xiaoyi.ssm.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 图片上传控制器
 * @author 宋高俊
 * @date 2018年7月31日 下午6:38:12
 */
@Controller("adminImageController")
@RequestMapping("/admin/image")
public class ImageController {

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

}
