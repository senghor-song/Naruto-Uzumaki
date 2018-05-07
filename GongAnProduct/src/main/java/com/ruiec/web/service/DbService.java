package com.ruiec.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.entity.Db;
import com.ruiec.web.entity.DbTable;

/**
 * 数据源服务接口
 * @author qinzhitian<br>
 * @date 2017年12月18日 下午4:47:35
 */
public interface DbService extends BaseService<Db, Integer> {
	/**
	 * 保存数据源
	 * @author qinzhitian<br>
	 * @date 2017年12月18日 下午5:46:15
	 */
	public JsonReturn saveDb(Db db);
	
	/**
	 * 修改数据源
	 * @author qinzhitian<br>
	 * @date 2017年12月18日 下午5:46:15
	 */
	public JsonReturn updateDb(Db db);
	
	/**
	 * 数据导入（库）
	 * @author qinzhitian<br>
	 * @date 2017年12月20日 上午11:04:21
	 */
	public JsonReturn dataImport(Integer id, String startDate, String endDate);
	/**
	 * 获取数据库连接
	 * @author qinzhitian<br>
	 * @date 2017年12月22日 下午1:54:09
	 */
	public Connection getConnection(Db db) throws ClassNotFoundException, SQLException;
	/**
	 * 查询总条数
	 * @author qinzhitian<br>
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 * @date 2017年12月21日 上午9:56:12
	 */
	public Long getCount(DbTable dbTable, String startDate, String endDate) throws SQLException, ClassNotFoundException, ParseException;
	/**
	 * 抓取数据
	 * @author qinzhitian<br>
	 * @throws Exception 
	 * @date 2017年12月23日 下午2:11:00
	 */
	public List<Map<String,Object>> getDataList(Connection conn, PreparedStatement pst, ResultSet res, long begin, long end, DbTable dbTable, String startDate, String endDate) throws Exception;
	/**
	 * 保存单位数据
	 * @author qinzhitian<br>
	 * @date 2017年12月20日 下午3:12:08
	 */
	public boolean saveUnitData(Long count,DbTable dbTable, String startDate, String endDate) throws SQLException;
	/**
	 * 修改导入单位父级id
	 * @author qinzhitian<br>
	 * @date 2017年12月26日 下午5:09:18
	 */
	public boolean updateUnitParentId();
	/**
	 * 保存警员信息
	 * @author qinzhitian<br>
	 * @throws Exception 
	 * @date 2017年12月25日 上午9:45:07
	 */
	public boolean saveUserData(Long count,DbTable dbTable, String startDate, String endDate);
	/**
	 * 导入重点人
	 * @author qinzhitian<br>
	 * @date 2017年12月25日 下午2:44:41
	 */
	public boolean saveControlPersonData(Long count,DbTable dbTable, String startDate, String endDate);
	/**
	 * 缓存单位信息
	 * @author qinzhitian<br>
	 * @date 2018年1月19日 上午9:02:36
	 */
	public void setUnitMap();
	/**
	 * 缓存警员信息
	 * @author qinzhitian<br>
	 * @date 2018年1月19日 上午9:04:43
	 */
	public void setUserMap();
	/**
	 * 清除缓存
	 * @author qinzhitian<br>
	 * @date 2018年1月19日 上午9:07:17
	 */
	public void clear();
	/**
	 * 关闭连接
	 * @author qinzhitian<br>
	 * @date 2018年1月23日 上午9:00:26
	 */
	public void close(Connection conn, PreparedStatement pst, ResultSet res);
	/**
	 * 查询所有启用表信息
	 * @author qinzhitian<br>
	 * @date 2017年12月21日 上午9:56:40
	 * @param id 数据源id
	 */
	public List<DbTable> getTableList(Integer id);
}
