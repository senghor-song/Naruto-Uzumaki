/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.framework.server.support.hibernate;

/**
 * 动态sql替换数据封装类
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月11日
 */
public class TableNameInfo {

	private String from;
	private String to;
	
	
	public TableNameInfo(String from, String to) {
		this.from = from;
		this.to = to;
	}


	/**
	 * 将被替换的表名
	 * @author 肖学良<br>
	 * Date: 2015年12月8日
	 */
	public String getFrom() {
		return from;
	}


	/**
	 * 将被替换的表名
	 * @author 肖学良<br>
	 * Date: 2015年12月8日
	 */
	public void setFrom(String from) {
		this.from = from;
	}


	/**
	 * 替换后的表名
	 * @author 肖学良<br>
	 * Date: 2015年12月8日
	 */
	public String getTo() {
		return to;
	}


	/**
	 * 替换后的表名
	 * @author 肖学良<br>
	 * Date: 2015年12月8日
	 */
	public void setTo(String to) {
		this.to = to;
	}
	
	/*
	 * 重写equals方法
	 * @author 杨龙香<br>
	 * Date: 2016年06月22日
	 */
	@Override
	public boolean equals(Object obj){
		if(obj instanceof TableNameInfo){
			TableNameInfo t=(TableNameInfo)obj;
			return (t.getFrom().equals(this.getFrom()) && t.getTo().equals(this.getTo()));
		}
		return super.equals(obj);
	}
	
	/*
	 * 重写hashCode方法
	 * @author 杨龙香<br>
	 * Date: 2016年06月22日
	 */
	@Override
	public int hashCode(){
		return (to.hashCode()+from.hashCode())*13;/*乘于13是为了尽量减少to和from不一样而hashCode返回值一样，这么做效果不一定很好，以后有待改进*/
	}
}
