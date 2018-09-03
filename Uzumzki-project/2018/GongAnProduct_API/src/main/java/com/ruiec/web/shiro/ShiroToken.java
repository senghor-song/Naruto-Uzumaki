package com.ruiec.web.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ShiroToken extends UsernamePasswordToken {

	private static final long serialVersionUID = -5747272948404480791L;

	private String captcha;
	private String captchaId;
	
	ShiroToken(String userName,String password,String captchaId,String captcha){
		setUsername(userName);
		setPassword(password.toCharArray());
		this.captcha = captcha;
		this.captchaId = captchaId;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getCaptchaId() {
		return captchaId;
	}

	public void setCaptchaId(String captchaId) {
		this.captchaId = captchaId;
	}
}
