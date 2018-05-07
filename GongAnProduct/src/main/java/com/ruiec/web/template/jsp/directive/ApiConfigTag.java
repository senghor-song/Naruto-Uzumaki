package com.ruiec.web.template.jsp.directive;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.APIConfig;
import com.ruiec.web.service.APIConfigService;
import com.ruiec.web.util.SpringUtils;

/**
 * api配置标签
 * 
 * @author 陈靖原<br>
 * @date 2018年1月23日 上午10:19:03
 */
public class ApiConfigTag extends SimpleTagSupport {

	private String apiId;

	/** 对象的自定义名称 */
	private String var;

	@Override
	public void doTag() throws JspException, IOException {
		APIConfig apiConfig = null;
		if (apiId != null && !"".equals(apiId)) {
			apiConfig = (APIConfig) RedisUtil.getRedisOne(GlobalUnit.API_CONFIG_MAP, apiId);
			if (apiConfig == null) {
				APIConfigService apiConfigService = (APIConfigService) SpringUtils.getBean("apiConfigServiceImpl");
				apiConfig = apiConfigService.get(Integer.valueOf(apiId));
			}
		}
		PageContext pageContext = (PageContext) getJspContext();
		pageContext.setAttribute(var, apiConfig);
		getJspBody().invoke(null);
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
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
