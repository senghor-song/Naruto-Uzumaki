package com.xiaoyi.ssm.dto;

import java.io.Serializable;

/**  
 * @Description: Admin后台分页参数
 * @author 宋高俊  
 * @date 2018年7月27日 上午11:37:23 
 */ 
public class AdminPage implements Serializable {
	
	private static final long serialVersionUID = 8656597559014685635L;
	
	/** 第几页 */
	private int page = 1;
	/** 每页记录数 */
	private int limit = 10;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
