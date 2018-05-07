package com.ruiec.web.template.jsp.directive;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;


/**
 * 角色权限判断标签
 * @author 肖学良
 *
 */
public class HasPermsTag extends SimpleTagSupport {

	public static final String ROLE_PERMS_MAP_KEY = "role_perms_map_key_xxl";
	/**
	 * 权限字符串
	 */
	private String perms;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
	    Map<String, String> permsMap = (Map<String, String>) pageContext.getRequest().getAttribute(ROLE_PERMS_MAP_KEY);
	    if(permsMap.get(perms) != null){
	    	//pageContext.getResponse().getWriter().write("checked=\"checked\"");
	    	//pageContext.getResponse().getWriter().flush();
	    	pageContext.getOut().print("checked=\"checked\"");
	    }
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}
	
}
