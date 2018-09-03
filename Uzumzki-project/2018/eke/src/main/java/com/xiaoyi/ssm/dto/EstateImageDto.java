package com.xiaoyi.ssm.dto;

/**  
 * @Description: 小区图片参数传递对象
 * @author 宋高俊  
 * @date 2018年7月10日 上午11:56:16 
 */ 
public class EstateImageDto {
	/** 小区ID */
	private String empid;
	/** 小区ID */
	private String estateid;
	/** 小区名 */
	private String estate;
	/** 小区户型数 */
	private Integer countf;
	/** 小区图类型 */
	private Integer type;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getEstateid() {
		return estateid;
	}
	public void setEstateid(String estateid) {
		this.estateid = estateid;
	}
	public String getEstate() {
		return estate;
	}
	public void setEstate(String estate) {
		this.estate = estate;
	}
	public Integer getCountf() {
		return countf;
	}
	public void setCountf(Integer countf) {
		this.countf = countf;
	}
	
}
