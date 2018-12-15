package com.xiaoyi.ssm.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * @Description: 发送QQ邮件的工具类
 * @author 宋高俊
 * @date 2018年12月5日下午5:14:23
 */
public class SendEmail {

	/**
	 * @Description: 
	 * @author 宋高俊
	 * @param sendUser 发送人邮件地址
	 * @param title 邮件标题
	 * @param content 邮件内容
	 * @return
	 * @date 2018年12月5日下午5:14:19
	 */
	public static boolean sendEmail(String sendUser, String title, String content) {
		// 收件人电子邮箱
		String to = sendUser;

		// 发件人电子邮箱
		String from = "service@ekeae.com";

		// 指定发送邮件的主机为 smtp.qq.com
		String host = "smtp.qq.com"; // QQ 邮件服务器

		// 获取系统属性
		Properties properties = System.getProperties();

		// 设置邮件服务器
		properties.setProperty("mail.smtp.host", host);

		properties.put("mail.smtp.auth", "true");
		MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e1) {
			e1.printStackTrace();
			return false;
		}
		sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);
		// 获取默认session对象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				// 密码需要在QQ邮箱设置中获取授权码
				return new PasswordAuthentication("service@ekeae.com", "bikinlbglesfebdj"); // 发件人邮件用户名、密码
			}
		});

		try {
			String nick = "";
			try {
				nick = javax.mail.internet.MimeUtility.encodeText("【小易运维】");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);

			// Set From: 头部头字段
			message.setFrom(new InternetAddress(nick + "<" + from + ">"));

			// Set To: 头部头字段
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: 头部头字段
			message.setSubject(title);

			// 设置消息体
			message.setText(content);
			// 发送消息
			Transport.send(message);
			return true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}
	}
}