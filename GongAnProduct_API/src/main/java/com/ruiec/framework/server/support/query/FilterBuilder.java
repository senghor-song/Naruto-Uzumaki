/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.framework.server.support.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 过滤组装类
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月24日
 */
public class FilterBuilder implements Serializable {

	private static final long serialVersionUID = -2836924226070292163L;

	private List<Filter> filters = new ArrayList<Filter>();
	
	/**
	 * 添加过滤条件
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public FilterBuilder add(Filter filter){
		filters.add(filter);
		return this;
	}
	/**
	 * 返回过滤集
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public List<Filter> build(){
		return filters;
	}
}
