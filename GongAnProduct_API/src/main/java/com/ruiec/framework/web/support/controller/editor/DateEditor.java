package com.ruiec.framework.web.support.controller.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 日期转换器
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月24日
 */
public class DateEditor extends PropertyEditorSupport {
	
	public static final String[] DATE_PATTERNS = { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "HH:mm", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };
	private boolean emptyAsNull;
	private String dateFormat = "yyyy-MM-dd HH:mm:ss";

	public DateEditor(boolean emptyAsNull) {
		this.emptyAsNull = emptyAsNull;
	}

	public DateEditor(boolean emptyAsNull, String dateFormat) {
		this.emptyAsNull = emptyAsNull;
		this.dateFormat = dateFormat;
	}

	public String getAsText() {
		Date localDate = (Date) getValue();
		return localDate != null ? new SimpleDateFormat(this.dateFormat).format(localDate) : "";
	}

	public void setAsText(String text) {
		if (text == null) {
			setValue(null);
		} else {
			String str = text.trim();
			if ((this.emptyAsNull) && ("".equals(str))) {
				setValue(null);
			} else {
				try {
					setValue(DateUtils.parseDate(str, DATE_PATTERNS));
				} catch (ParseException localParseException) {
					setValue(null);
				}
			}
				
		}
	}
}
