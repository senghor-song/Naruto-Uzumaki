package com.xiaoyi.ssm.template.jsp;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang3.StringUtils;

import com.xiaoyi.ssm.model.Estate;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;

/**  
 * @Description: 小区标签
 * @author 宋高俊  
 * @date 2018年7月23日 上午10:05:03 
 */ 
public class EstateTag extends SimpleTagSupport {
	
	/** 小区ID */
	private String estateId;
	
	/** 对象的自定义名称 */
	private String var;
	
	@Override
	public void doTag() throws JspException, IOException {
		Estate estate = new Estate();
		if (!StringUtils.isBlank(estateId)) {
			estate = (Estate)RedisUtil.getRedisOne(Global.REDIS_ESTATE_MAP, estateId);
		}
		PageContext pageContext = (PageContext) getJspContext();
	    pageContext.setAttribute(var, estate);
	    getJspBody().invoke(null);
	}

	public String getEstateId() {
		return estateId;
	}

	public void setEstateId(String estateId) {
		this.estateId = estateId;
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
