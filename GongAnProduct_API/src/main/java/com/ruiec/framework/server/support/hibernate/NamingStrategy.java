/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.framework.server.support.hibernate;

import org.hibernate.cfg.DefaultNamingStrategy;

/**
 * hibernate命名策略
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月19日
 */
public class NamingStrategy extends DefaultNamingStrategy {

	private static final long serialVersionUID = -351432227888747140L;

	@Override
	public String propertyToColumnName(String propertyName) {
		String firstStr = propertyName.substring(0, 1).toUpperCase();
		String afterStr = propertyName.substring(1, propertyName.length());
		return "F" + firstStr + afterStr;
	}

	@Override
	public String collectionTableName(String ownerEntity,
			String ownerEntityTable, String associatedEntity,
			String associatedEntityTable, String propertyName) {
		return super.collectionTableName(ownerEntity, ownerEntityTable,
				associatedEntity, associatedEntityTable, propertyName);
	}

	@Override
	public String joinKeyColumnName(String joinedColumn, String joinedTable) {
		return super.joinKeyColumnName(joinedColumn, joinedTable);
	}

	@Override
	public String foreignKeyColumnName(String propertyName,
			String propertyEntityName, String propertyTableName,
			String referencedColumnName) {

		String firstStr = propertyEntityName.substring(propertyEntityName.lastIndexOf(".") + 1);
		return "F" + firstStr + "_" + referencedColumnName;
	}

	
	
}
