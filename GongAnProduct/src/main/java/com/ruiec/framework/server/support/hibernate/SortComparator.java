/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.framework.server.support.hibernate;

import java.util.Comparator;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 实体比较器
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2016年1月4日
 */
public class SortComparator implements Comparator<BaseEntity> {
	@Override
	public int compare(BaseEntity o1, BaseEntity o2) {
		if(o1.getCreateDate() != null && o2.getCreateDate() != null){
			return o1.getCreateDate().compareTo(o2.getCreateDate());
		}else if(o1.getId() != null && o2.getId() == null){
			return 1;
		}else if(o1.getId() == null && o2.getId() != null){
			return -1;
		}
		return 0;
	}


}
