package com.xiaoyi.ssm.wxPay;

import java.util.Map;

public class WxTemplate {
	private String template_id;

	private String touser;

	private String form_id;// 请自行添加上set,get方法

	private String url;

	private String topcolor;

	private Map<String, TemplateData> data;

	public String getTemplate_id() {

		return template_id;

	}

	public void setTemplate_id(String template_id) {

		this.template_id = template_id;

	}

	public String getTouser() {

		return touser;

	}

	public void setTouser(String touser) {

		this.touser = touser;

	}

	public String getUrl() {

		return url;

	}

	public void setUrl(String url) {

		this.url = url;

	}

	public String getTopcolor() {

		return topcolor;

	}

	public void setTopcolor(String topcolor) {

		this.topcolor = topcolor;

	}

	public Map<String, TemplateData> getData() {

		return data;

	}

	public void setData(Map<String, TemplateData> data) {

		this.data = data;

	}

	public String getForm_id() {
		return form_id;
	}

	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}

}
