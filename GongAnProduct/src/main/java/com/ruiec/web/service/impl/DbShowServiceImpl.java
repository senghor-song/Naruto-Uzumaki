package com.ruiec.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.entity.DbField;
import com.ruiec.web.entity.DbShow;
import com.ruiec.web.entity.DbTable;
import com.ruiec.web.service.DbFieldService;
import com.ruiec.web.service.DbShowService;
import com.ruiec.web.service.DbTableService;

/**
 * 数据展示服务实现类
 * 
 * @author qinzhitian<br>
 * @date 2017年12月18日 下午4:49:03
 */
@Service("dbShowServiceImpl")
public class DbShowServiceImpl extends BaseServiceImpl<DbShow, Integer> implements DbShowService {

	@Resource
	private DbTableService dbTableService;
	@Resource
	private DbFieldService dbFieldService;

	/**
	 * 根据数据导入表id查询数据展示子集
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月21日 下午5:53:49
	 */
	@Override
	@Transactional
	public List<DbShow> findSon(Integer tid) {
		// 查询数据导入表详情
		DbTable dbTable = dbTableService.get(tid);
		// 根据父级id查询子集
		List<DbShow> dbShows = super.findList(null, Filter.eq("parentId", dbTable.getDbShow().getId()), null);
		return dbShows;
	}

	/**
	 * 查询未添加字段
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月28日 下午3:22:11
	 */
	@Override
	@Transactional
	public List<DbShow> findAddShow(Integer tid) {
		// 根据数据导入表id查询数据展示子集
		List<DbShow> dbShows = findSon(tid);
		// 查询已添加字段
		List<DbField> dbFields = dbFieldService.findList(null, Filter.eq("dbTable.id", tid), null);
		for (DbField dbField : dbFields) {
			dbShows.remove(dbField.getDbShow());
		}
		return dbShows;
	}

	/**
	 * 批量删除
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月22日 上午9:32:15
	 */
	@Override
	@Transactional
	public void deleteByIds(Integer[] ids) {
		// 查询表内字段
		List<DbShow> dbShows = super.findList(null, Filter.in("parentId", ids), null);
		// 删除字段
		for (DbShow dbShow : dbShows) {
			delete(dbShow);
		}
		// 删除表
		super.delete(ids);

	}

}
