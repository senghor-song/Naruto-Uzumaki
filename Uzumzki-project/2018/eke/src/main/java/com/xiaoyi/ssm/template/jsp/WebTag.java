package com.xiaoyi.ssm.template.jsp;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang3.StringUtils;

import com.xiaoyi.ssm.model.WebSite;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;

/**  
 * @Description: 网站标签
 * @author 宋高俊  
 * @date 2018年7月23日 上午10:05:03 
 */ 
public class WebTag extends SimpleTagSupport {
	
	/** 网站ID */
	private String webId;
	
	/** 对象的自定义名称 */
	private String var;
	
	@Override
	public void doTag() throws JspException, IOException {
		WebSite webSite = new WebSite();
		if (!StringUtils.isBlank(webId)) {
			webSite = (WebSite) RedisUtil.getRedisOne(Global.REDIS_WEB_MAP, webId);
		}
		PageContext pageContext = (PageContext) getJspContext();
	    pageContext.setAttribute(var, webSite);
	    getJspBody().invoke(null);
	}

	public String getWebId() {
		return webId;
	}

	public void setWebId(String webId) {
		this.webId = webId;
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
