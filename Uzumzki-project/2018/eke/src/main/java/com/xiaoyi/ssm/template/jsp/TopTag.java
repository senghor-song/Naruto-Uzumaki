package com.xiaoyi.ssm.template.jsp;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang3.StringUtils;

import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;


/**  
 * @Description: 主页顶部标签
 * @author 宋高俊  
 * @date 2018年7月23日 上午10:05:03 
 */ 
public class TopTag extends SimpleTagSupport {
	
	/** ID */
	private String topId;
	
	/** 对象的自定义名称 */
	private String var;
	
	@Override
	public void doTag() throws JspException, IOException {
		String str = "0";
		if (!StringUtils.isBlank(topId)) {
			str = (String) RedisUtil.getRedisOne(Global.REDIS_TOP_TAG_MAP, topId);
		}
		PageContext pageContext = (PageContext) getJspContext();
	    pageContext.setAttribute(var, str);
	    getJspBody().invoke(null);
	}

	public String getTopId() {
		return topId;
	}

	public void setTopId(String topId) {
		this.topId = topId;
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
