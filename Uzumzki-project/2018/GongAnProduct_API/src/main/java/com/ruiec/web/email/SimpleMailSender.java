/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.email;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 简单邮件（不带附件的邮件）发送器
 * 
 * @author 熊华松<br>
 * Version 1.0<br>
 * Date: 2016年3月29日
 */
public class SimpleMailSender  { 
	
	private static final Logger logger = Logger.getLogger(SimpleMailSender.class);
	
	/** 
	  * 以文本格式发送邮件 
	  * @param mailInfo 待发送的邮件的信息 
	  */ 
	public static boolean sendTextMail(MailInfo mailInfo) { 
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        // 设定mail server
        senderImpl.setHost(mailInfo.getMailServerHost());
        senderImpl.setPort(Integer.parseInt(mailInfo.getMailServerPort()));
        // 建立邮件消息,发送简单邮件和html邮件的区别
        MimeMessage mailMessage = senderImpl.createMimeMessage();
 
        // 设置收件人，寄件人
        try {
        	MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
			messageHelper.setTo(mailInfo.getToAddress());
			messageHelper.setFrom(mailInfo.getFromAddress());
			messageHelper.setSubject(mailInfo.getSubject());
			// true 表示启动HTML格式的邮件
			messageHelper.setText( mailInfo.getContent(),false);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
        senderImpl.setUsername(mailInfo.getUserName()); // 根据自己的情况,设置username
        senderImpl.setPassword(mailInfo.getPassword()); // 根据自己的情况, 设置password
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", "45000");
        senderImpl.setJavaMailProperties(prop);
        // 发送邮件
        senderImpl.send(mailMessage);
        logger.info("邮件发送成功,邮件内容是: " + mailInfo);
        return true;
	} 
	
	/** 
	  * 以HTML格式发送邮件 
	  * @param mailInfo 待发送的邮件信息 
	  */ 
	public static boolean sendHtmlMail(MailInfo mailInfo){ 
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        // 设定mail server
        senderImpl.setHost(mailInfo.getMailServerHost());
        senderImpl.setPort(Integer.parseInt(mailInfo.getMailServerPort()));
        // 建立邮件消息,发送简单邮件和html邮件的区别
        MimeMessage mailMessage = senderImpl.createMimeMessage();
 
        // 设置收件人，寄件人
        try {
        	MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
			messageHelper.setTo(mailInfo.getToAddress());
			messageHelper.setFrom(mailInfo.getFromAddress());
			messageHelper.setSubject(mailInfo.getSubject());
			// true 表示启动HTML格式的邮件
			messageHelper.setText( mailInfo.getContent(),true);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
        senderImpl.setUsername(mailInfo.getUserName()); // 根据自己的情况,设置username
        senderImpl.setPassword(mailInfo.getPassword()); // 根据自己的情况, 设置password
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", "45000");
        senderImpl.setJavaMailProperties(prop);
        // 发送邮件
        senderImpl.send(mailMessage);
        logger.info("邮件发送成功,邮件内容是: " + mailInfo);
        return true;
	}
	
} 


