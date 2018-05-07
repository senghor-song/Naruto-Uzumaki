package com.ruiec.web.service;

import java.util.List;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.entity.DbTable;

/**
 * 数据导入表服务接口
 * @author qinzhitian<br>
 * @date 2017年12月18日 下午4:47:35
 */
public interface DbTableService extends BaseService<DbTable, Integer> {
	/**
	 * 查询是否已提供所有必须字段
	 * @author qinzhitian<br>
	 * @date 2017年12月28日 上午8:58:23
	 */
	public boolean checkField(List<DbTable> dbTables);
	
	/**
	 * 导入数据（表）
	 * @author qinzhitian<br>
	 * @date 2017年12月22日 下午1:49:14
	 */
	public JsonReturn tableImport(DbTable dbTable, String startDate, String endDate);
}
