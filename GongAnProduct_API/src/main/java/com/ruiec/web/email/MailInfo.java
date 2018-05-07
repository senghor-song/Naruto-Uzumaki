/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.email;

import java.util.Arrays;
import java.util.Properties;

import com.ruiec.web.util.SettingUtils;

/**
 * 发送邮件需要使用的基本信息
 * 
 * @author 熊华松<br>
 * Version 1.0<br>
 * Date: 2016年3月29日
 */
public class MailInfo {
	// 发送邮件的服务器的IP和端口
	// private String mailServerHost = "smtp.126.com";
	private String mailServerHost = SettingUtils.get().getSmtp();
	private String mailServerPort = SettingUtils.get().getSmtpPort() + "";
	// 邮件发送者的地址
	private String fromAddress = SettingUtils.get().getSystemEmail();// "ruiec_dev@126.com";
	// 邮件接收者的地址
	private String toAddress;
	// 登陆邮件发送服务器的用户名和密码
	private String userName = SettingUtils.get().getSystemEmail();
	private String password = SettingUtils.get().getEmailPwd();// "ruiec.com";
	// 是否需要身份验证
	private boolean validate = true;
	// 邮件主题
	private String subject;
	// 邮件的文本内容
	private String content;
	// 邮件附件的文件名
	private String[] attachFileNames;

	/**
	 * 获得邮件会话属性
	 */
	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] fileNames) {
		this.attachFileNames = fileNames;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String textContent) {
		this.content = textContent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MailInfo [mailServerHost=");
		builder.append(mailServerHost);
		builder.append(", mailServerPort=");
		builder.append(mailServerPort);
		builder.append(", fromAddress=");
		builder.append(fromAddress);
		builder.append(", toAddress=");
		builder.append(toAddress);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", validate=");
		builder.append(validate);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", content=");
		builder.append(content);
		builder.append(", attachFileNames=");
		builder.append(Arrays.toString(attachFileNames));
		builder.append("]");
		return builder.toString();
	}

}
