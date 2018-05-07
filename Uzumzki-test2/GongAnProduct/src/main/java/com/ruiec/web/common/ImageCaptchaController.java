/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;

/**
 * 图片验证码控制器
 * @author 熊华松<br>
 * Version 1.0<br>
 * Date: 2016年7月25日
 */
@Controller("imageCaptchaController")
public class ImageCaptchaController {
	
	@Resource(name="imageCaptchaService")
	private CaptchaService captchaService;
	
	/**
	 * 输出图片验证码
	 * @author 熊华松<br>
	 * Date: 2016年7月25日
	 */	
	@RequestMapping("/image")
	public void image(HttpServletRequest request, HttpServletResponse response) {
		String sessionId = request.getSession(true).getId();
		BufferedImage localBufferedImage = (BufferedImage) captchaService.getChallengeForID(sessionId);
		response.setHeader("pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("expires", 0);
		response.setContentType("image/jpeg");
		try {
			ImageIO.write(localBufferedImage, "JPEG", response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 校验验证码是否输入正确
	 * @author 熊华松<br>
	 * Date: 2016年7月25日
	 */	
	@RequestMapping("/valid")
	@ResponseBody
	public Message valid(HttpServletRequest request, String captcha) {
		try {
			if (captcha != null && captchaService.validateResponseForID(request.getSession(true).getId(), captcha.trim().toUpperCase())) {
				return Message.info("true");
			} else {
				return Message.error("false");
			}
		} catch (CaptchaServiceException e) {
			return Message.error("false");
		}
	}
	
	/**
	 * 校验验证码是否输入正确, 并放入一个UUID到request域
	 * @author 熊华松<br>
	 * Date: 2016年7月25日
	 */	
	@RequestMapping("/validUuid")
	@ResponseBody
	public Message validUuid(HttpServletRequest request, String captcha) {
		try {
			if (captcha != null && captchaService.validateResponseForID(request.getSession(true).getId(), captcha.trim().toUpperCase())) {
				String uuid = UUID.randomUUID().toString();
				request.getSession().setAttribute(CommonParam.SESSION_CAPTCHA_KEY, uuid);
				return Message.info(uuid);
			} else {
				return Message.error("验证出错!");
			}
		} catch (CaptchaServiceException e) {
			return Message.error("验证出错!");
		}
	}
	
}
