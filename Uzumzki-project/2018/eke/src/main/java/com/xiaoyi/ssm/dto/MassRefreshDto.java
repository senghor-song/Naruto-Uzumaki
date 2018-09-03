package com.xiaoyi.ssm.dto;

/**  
 * @Description: 刷新参数
 * @author 宋高俊  
 * @date 2018年7月4日 下午1:45:41 
 */ 
public class MassRefreshDto {
	/** 经纪人ID */
	private String empId;
	/** 网站名 */
	private String webName;
	/** 账号 */
	private String account;
	/** 结果 */
	private String result;
	/** 模式 */
	private String pattern;
	/** 计划号 */
	private String project;
	/** 日期0=一天前1=两天前2=三天前3=四天前4=五天前5=六天前6=七天前7=一周内 */
	private String date;
	/** 开始日期 */
	private String startDate;
	/** 结束日期 */
	private String endDate;
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getWebName() {
		return webName;
	}
	public void setWebName(String webName) {
		this.webName = webName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
