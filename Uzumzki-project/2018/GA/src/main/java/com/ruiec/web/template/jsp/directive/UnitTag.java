package com.ruiec.web.template.jsp.directive;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.util.SpringUtils;

/**
 * 单位标签
 * @author Senghor<br>
 * @date 2017年12月7日 下午2:50:50
 */
public class UnitTag extends SimpleTagSupport {
	
	private String unitId;
	
	/** 对象的自定义名称 */
	private String var;
	
	@Override
	public void doTag() throws JspException, IOException {
	    Unit unit = null;
	    if (unitId!=null && unitId!="" && unitId.length()>0) {
	    	unit=(Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unitId);
	    	if(unit==null){
				UnitService unitService=(UnitService) SpringUtils.getBean("unitServiceImpl");
				unit=unitService.get(Integer.valueOf(unitId));
			}
	    }
		PageContext pageContext = (PageContext) getJspContext();
	    pageContext.setAttribute(var, unit);
	    getJspBody().invoke(null);
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
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
