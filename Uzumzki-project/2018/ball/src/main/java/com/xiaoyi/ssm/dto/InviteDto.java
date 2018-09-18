package com.xiaoyi.ssm.dto;

/**  
 * @Description: 
 * @author 宋高俊  
 * @date 2018年9月13日 下午3:51:16 
 */ 
public class InviteDto {
	/** 最大经度 */
	private double maxlng;
	/** 最小经度 */
	private double minlng;
	/** 最大纬度 */
	private double maxlat;
	/** 最小纬度 */
	private double minlat;
	
	public double getMaxlng() {
		return maxlng;
	}
	public void setMaxlng(double maxlng) {
		this.maxlng = maxlng;
	}
	public double getMinlng() {
		return minlng;
	}
	public void setMinlng(double minlng) {
		this.minlng = minlng;
	}
	public double getMaxlat() {
		return maxlat;
	}
	public void setMaxlat(double maxlat) {
		this.maxlat = maxlat;
	}
	public double getMinlat() {
		return minlat;
	}
	public void setMinlat(double minlat) {
		this.minlat = minlat;
	}

}
