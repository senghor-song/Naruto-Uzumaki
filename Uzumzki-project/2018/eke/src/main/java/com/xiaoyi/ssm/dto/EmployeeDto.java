package com.xiaoyi.ssm.dto;

/**  
 * @Description: 经纪人传递参数对象
 * @author 宋高俊  
 * @date 2018年6月28日 下午2:26:34 
 */ 
public class EmployeeDto {

	/** 经纪人id */
	private String id;
	/** 旧密码 */
	private String oldPwd;
	/** 新密码 */
	private String newPwd;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
}
