package com.ruiec.web.template.jsp.directive;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.util.SpringUtils;

/**
 * 字典标签
 * @author Senghor<br>
 * @date 2017年12月7日 下午2:50:50
 */
public class DictionaryTag extends SimpleTagSupport {
	
	private String dictionaryId;
	
	/** 对象的自定义名称 */
	private String var;
	
	@Override
	public void doTag() throws JspException, IOException {
		Dictionary dictionary = null;
		if (dictionaryId!=null && !"".equals(dictionaryId)) {
			dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, dictionaryId);
			if(dictionary==null){
				DictionaryService dictionaryService=(DictionaryService) SpringUtils.getBean("dictionaryServiceImpl");
				dictionary=dictionaryService.get(Integer.valueOf(dictionaryId));
			}
		}
		PageContext pageContext = (PageContext) getJspContext();
	    pageContext.setAttribute(var, dictionary);
	    getJspBody().invoke(null);
	}

	public String getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(String dictionaryId) {
		this.dictionaryId = dictionaryId;
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
