package com.xiaoyi.ssm.dto;

/**  
 * @Description: 后台通用查询对象
 * @author 宋高俊  
 * @date 2018年8月9日 下午2:26:51 
 */ 
public class AdminDto {
	
	/** 类型 */
	private int selectType;
	/** 关键字 */
	private String keyword;
	
	public int getSelectType() {
		return selectType;
	}
	public void setSelectType(int selectType) {
		this.selectType = selectType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
