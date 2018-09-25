package com.ruiec.web.template.jsp.directive;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.UserService;
import com.ruiec.web.util.SpringUtils;

/**
 * 单位标签
 * @author Senghor<br>
 * @date 2017年12月7日 下午2:50:50
 */
public class UserTag extends SimpleTagSupport {
	
	private String userId;
	
	/** 对象的自定义名称 */
	private String var;
	
	@Override
	public void doTag() throws JspException, IOException {
		User user = null;
		if (userId!=null && userId!="" && userId.length()>0) {
			user=(User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, userId);
			if(user==null){
				UserService userService=(UserService) SpringUtils.getBean("userServiceImpl");
				user=userService.get(Integer.valueOf(userId));
			}
		}
		PageContext pageContext = (PageContext) getJspContext();
	    pageContext.setAttribute(var, user);
	    getJspBody().invoke(null);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/** 对象的自定义名称 */
	public String getVar() {
		return var;
	}
	
	/** 对象的自定义名称 */
	public void setVar(String var) {
		this.var = var;
	}
	
}
