/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.ruiec.web.email.MailInfo;
import com.ruiec.web.email.SimpleMailSender;


/**
 * 邮件工具类
 * 
 * @author 熊华松<br>
 * Version 1.0<br>
 * Date: 2015年12月31日
 */
public class RuiecMailUtil {
	
	private static final Logger logger = Logger.getLogger(RuiecMailUtil.class);
	
//	private static String host = SettingUtils.get().getSmtp(); // 邮件服务器
//	private static String username = SettingUtils.get().getSystemEmail(); // 发送人用户名
//	private static String password = SettingUtils.get().getEmailPwd(); // 发送人密码
//	private static String from = SettingUtils.get().getSystemEmail(); // 发送人
	
	public static boolean sendSimpleMail(String[] tos, String subject, String content) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(SettingUtils.get().getSmtp());
		mailSender.setPort(25);
		mailSender.setUsername(SettingUtils.get().getSystemEmail()) ;  //  根据自己的情况,设置username 
		mailSender.setPassword(SettingUtils.get().getEmailPwd()) ;  //  根据自己的情况, 设置password 
		
		MimeMessage mailMessage = mailSender.createMimeMessage(); 
	   
	    try {
	    	 MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8"); 
	    	 messageHelper.setFrom(SettingUtils.get().getSystemEmail());
	 	     messageHelper.setTo(tos);
	 	     messageHelper.setSubject(subject); 
			 messageHelper.setText(content, true);
		} catch (MessagingException e) {
			e.printStackTrace();
		} 
	    
	    Properties prop  =   new  Properties() ;
	    prop.put( "mail.smtp.auth" ,  true) ;  //  将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确 
	    prop.put( "mail.smtp.timeout" ,  "45000") ; 
	    mailSender.setJavaMailProperties(prop);  
	        // 发送邮件  
	    mailSender.send(mailMessage); 
	    logger.info("邮件发送成功,邮件内容是: " + content);
	    return true;
	}

	/**
	 * 系统邮件发送工具：发送html内容的邮件
	 * @param htmlContent
	 * @return
	 * @author 熊华松
	 */
	public static boolean sendHtmlMail(String receiver, String subject, String htmlContent) {
		if (RuiecStringUtil.checkNull(receiver, subject, htmlContent)) {
			return false;
		} else {
			MailInfo mailInfo = new MailInfo();
			mailInfo.setToAddress(receiver);
			mailInfo.setSubject(subject);
			mailInfo.setContent(htmlContent);
			return SimpleMailSender.sendHtmlMail(mailInfo);
		}
	}
	
	
	/**
	 * 系统邮件发送工具：发送text内容的邮件
	 * @param textContent
	 * @return
	 * @author 熊华松
	 */
	public static boolean sendTextMail(String receiver, String subject, String textContent) {
		if (RuiecStringUtil.checkNull(receiver, subject, textContent)) {
			return false;
		} else {
			MailInfo mailInfo = new MailInfo();
			mailInfo.setToAddress(receiver);
			mailInfo.setSubject(subject);
			mailInfo.setContent(textContent);
			return SimpleMailSender.sendTextMail(mailInfo);
		}
	}
	
	/**
	 * 获取邮箱验证内容
	 * @param domainName 域名
	 * @param link 带后缀的action名字，如 list.shtml
	 * @param checkCode 验证码
	 * @param email 要验证的邮箱
	 * @param userName 用户名
	 * @return
	 * @author 熊华松
	 */
	public static String generateMailContent(String domainName, String link, String checkCode, String email, String userName) {
		/*return "<a href=\'" + domainName + "/" + link + "?checkCode=" + checkCode +"&email="+email+"\'>"
				+ domainName + "/" + link + "?checkcode=" + checkCode + "&email=" + email
				+ "</a>";*/
		StringBuilder htmlContent = new StringBuilder();
		String url = domainName + "/" + link + "?checkCode=" + checkCode +"&email="+email;
		String sendTime = RuiecDateUtils.transferDate2String(new Date(), "yyyy年MM月dd日  HH时mm分ss秒");
		String hotline = SettingUtils.get().getHotline();
		htmlContent.append("<div><p>").append("尊敬的用户").append(userName).append("：</p>")
		.append("<p style='line-height:30px'>")
		.append("&nbsp;&nbsp;&nbsp;").append("您于 <span style='border-bottom-width: 1px; border-bottom-style: dashed; border-bottom-color: rgb(204, 204, 204);'>")
		.append(sendTime).append(" 申请注册塑金所，点击以下链接，即可完成注册：<br>")
		.append("<a href='").append(url).append("'>").append(url).append("</a><br>")
		.append("您也可以将链接复制到浏览器地址栏访问。<br>").append("为保障您的帐号安全，请在24小时内点击该链接。若您没有申请过验证邮箱 ，请您忽略此邮件 ，由此给您带来的不便请谅解。</p>")
		.append("<p>").append("塑金所").append("</p>").append("<hr><p>").append("注：此邮件由系统自动发送，请勿回复。</p><p>")
		.append("如果您有任何疑问，请 联系客服电话<span style='border-bottom-width: 1px; border-bottom-style: dashed; border-bottom-color: rgb(204, 204, 204); z-index: 1; position: static;'")
		.append("onclick='return false;' onmouseover='getTop().openMenu(this);return false;' onmouseout='getTop().hideMenu(event,this);return false;'>")
		.append(hotline).append("</span>。</p>").append("<p></p></div>");
		return htmlContent.toString();
	}
	
}
