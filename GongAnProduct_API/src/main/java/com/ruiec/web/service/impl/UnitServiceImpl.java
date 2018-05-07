/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Sort;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.UnitType;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UnitTypeService;

/**
 * 单位服务接口实现类
 * 
 * @author Senghor<br>
 * @date 2017年11月30日 上午9:01:37
 */
@Service("unitServiceImpl")
public class UnitServiceImpl extends BaseServiceImpl<Unit, Integer> implements UnitService {

	private static final Logger LOGGER = Logger.getLogger(UnitServiceImpl.class);
	@Resource
	private OperationLogService operationLogService;
	@Resource
	private UnitTypeService unitTypeService;

	/**
	 * 获取单位列表的数据
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月4日 下午2:24:09
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> unitListMaps() {
		// 得到市局公安局下面的所有区级单位
		List<Unit> list = super.findList(null, Filter.eq("parentId", 1), Sort.desc("id"));
		// 将得到的数据进行重组
		List<Map<String, Object>> areaList = new ArrayList<Map<String, Object>>();
		for (Unit unit : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", unit.getId());// 单位id
			map.put("unitName", unit.getUnitName());// 单位名称
			map.put("unitRank", unit.getUnitRank());// 单位级别
			map.put("parentId", unit.getParentId());// 单位父级id
			map.put("isPoliceSection", unit.getIsPoliceSection());// 单位是否是警种
			map.put("policeTypesParentId", unit.getPoliceTypesParentId());// 单位的警种父级id
			map.put("unitCode", Unit.getUnitCode(unit));// 单位编码
			List<Integer> statis = getAdminUnits(unit.getId());
			if (statis.size() > 1) {
				map.put("statis", statis.size());// 单位的下级数量
			} else {
				map.put("statis", 0);// 单位的下级数量
			}
			List<UnitType> unitTypes = unitTypeService.findList(null, Filter.eq("unitId", unit.getId()), null);
			String typeName = "";
			for (int j = 0; j < unitTypes.size(); j++) {
				Dictionary di = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, unitTypes.get(j).getDictionaryId());
				if (di == null) {
					continue;
				}
				typeName += di.getItemName();
				if (j != unitTypes.size() - 1) {
					typeName += ",";
				}
			}
			map.put("polices", typeName);// 关联警种
			areaList.add(map);
		}
		return areaList;
	}

	/**
	 * 获取子孙级 方法过期，请使用findSonIds()
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月9日 上午10:45:06
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	@Deprecated
	public List<Object> findSonId(Integer id) {
		List<Object> list = new ArrayList<Object>();
		Unit unit = super.get(id);
		String sql = null;
		// 判断是否是警种单位，执行不同的查询语句
		if (unit.getIsPoliceSection() == 1) {
			sql = " select * from (select PRIKEY from T_SYS_UNIT where PRIKEY = :id " + " union all "
					+ " select PRIKEY from T_SYS_UNIT where POLICE_TYPES_PARENT_ID = :id " + " union all "
					+ " select PRIKEY from T_SYS_UNIT unit where POLICE_TYPES_PARENT_ID in (select PRIKEY from T_SYS_UNIT where POLICE_TYPES_PARENT_ID = :id) "
					+ ") ORDER BY PRIKEY ";
		} else {
			sql = " select * from (select PRIKEY from T_SYS_UNIT where PRIKEY = :id "
					+ " union all "
					+ " select PRIKEY from T_SYS_UNIT where PARENT_ID = :id or POLICE_TYPES_PARENT_ID = :id "
					+ " union all "
					+ " select PRIKEY from T_SYS_UNIT unit where PARENT_ID in (select PRIKEY from T_SYS_UNIT where PARENT_ID = :id) or POLICE_TYPES_PARENT_ID in (select PRIKEY from T_SYS_UNIT where POLICE_TYPES_PARENT_ID = :id)"
					+ ") ORDER BY PRIKEY ";
		}
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("id", id);
		list = (List<Object>) query.list();
		List<Integer> adminUnits = new ArrayList<Integer>();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				adminUnits.add(Integer.valueOf(list.get(i).toString()));
			}
		}

		return list;
	}

	/**
	 * 获取子孙级id
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月9日 上午10:45:06
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Integer> findSonIds(Integer id) {
		List<Object> list = new ArrayList<Object>();
		Unit unit = super.get(id);
		String sql = null;
		// 判断是否是警种单位，执行不同的查询语句
		if (unit.getIsPoliceSection() == 1) {
			sql = " select * from (select PRIKEY from T_SYS_UNIT where PRIKEY = :id " + " union all "
					+ " select PRIKEY from T_SYS_UNIT where POLICE_TYPES_PARENT_ID = :id " + " union all "
					+ " select PRIKEY from T_SYS_UNIT unit where POLICE_TYPES_PARENT_ID in (select PRIKEY from T_SYS_UNIT where POLICE_TYPES_PARENT_ID = :id) "
					+ ") ORDER BY PRIKEY ";
		} else {
			sql = " select * from (select PRIKEY from T_SYS_UNIT where PRIKEY = :id "
					+ " union all "
					+ " select PRIKEY from T_SYS_UNIT where PARENT_ID = :id or POLICE_TYPES_PARENT_ID = :id "
					+ " union all "
					+ " select PRIKEY from T_SYS_UNIT unit where PARENT_ID in (select PRIKEY from T_SYS_UNIT where PARENT_ID = :id) or POLICE_TYPES_PARENT_ID in (select PRIKEY from T_SYS_UNIT where POLICE_TYPES_PARENT_ID = :id)"
					+ " or POLICE_TYPES_PARENT_ID in (select PRIKEY from T_SYS_UNIT where PARENT_ID = :id) or POLICE_TYPES_PARENT_ID in (select PRIKEY from T_SYS_UNIT where POLICE_TYPES_PARENT_ID = :id) "
					+ ") ORDER BY PRIKEY ";
		}
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("id", id);
		list = (List<Object>) query.list();
		List<Integer> adminUnits = new ArrayList<Integer>();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				adminUnits.add(Integer.valueOf(list.get(i).toString()));
			}
		}
		return adminUnits;
	}

	/**
	 * 获取子孙级id不包含自己
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月9日 上午10:45:06
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Integer> findSonIdsNoThis(Integer id) {
		List<Object> list = new ArrayList<Object>();
		Unit unit = super.get(id);
		String sql = null;
		// 判断是否是警种单位，执行不同的查询语句
		if (unit.getIsPoliceSection() == 1) {
			sql = " select * from ( select PRIKEY from T_SYS_UNIT where POLICE_TYPES_PARENT_ID = :id " + " union all "
					+ " select PRIKEY from T_SYS_UNIT unit where POLICE_TYPES_PARENT_ID in (select PRIKEY from T_SYS_UNIT where POLICE_TYPES_PARENT_ID = :id) "
					+ ") ORDER BY PRIKEY ";
		} else {
			sql = " select * from ( select PRIKEY from T_SYS_UNIT where PARENT_ID = :id or POLICE_TYPES_PARENT_ID = :id "
					+ " union all "
					+ " select PRIKEY from T_SYS_UNIT unit where PARENT_ID in (select PRIKEY from T_SYS_UNIT where PARENT_ID = :id) or POLICE_TYPES_PARENT_ID in (select PRIKEY from T_SYS_UNIT where POLICE_TYPES_PARENT_ID = :id)"
					+ " or POLICE_TYPES_PARENT_ID in (select PRIKEY from T_SYS_UNIT where PARENT_ID = :id) or POLICE_TYPES_PARENT_ID in (select PRIKEY from T_SYS_UNIT where POLICE_TYPES_PARENT_ID = :id) "
					+ ") ORDER BY PRIKEY ";
		}
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("id", id);
		list = (List<Object>) query.list();
		List<Integer> adminUnits = new ArrayList<Integer>();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				adminUnits.add(Integer.valueOf(list.get(i).toString()));
			}
		}
		return adminUnits;
	}

	/**
	 * 获取区级所有的单位 isCity : 1将市级单位获取 ，0不获取市级单位
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月11日 上午10:44:04
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Unit> getUnitArea(Integer isCity) {
		List<Unit> areaList = new ArrayList<Unit>();
		// 获取到市级公安局的主键id
		Unit unit = super.get(1);
		if (isCity == 1) {
			areaList.add(unit);
		}
		// 查询市级公安局，没有市级公安局则不查询下级单位
		if (unit != null) {
			Integer cityPrikey = unit.getId();
			areaList.addAll(super.findList(null, Filter.eq("parentId", cityPrikey), Sort.asc("id")));
		}
		// 根据市级公安局主键id查询下属区级公安局信息
		return areaList;
	}

	/**
	 * 镇级单位列表查询方法
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月11日 下午1:39:15
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getUnitTown(Integer areaPrikey, Integer isPolice) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		// 存储镇级单位list
		List<Unit> townList = new ArrayList<Unit>();
		// 判断是否根据警种查询镇级单位
		if (isPolice == 0) {
			// 根据区级公安局主键id查询镇级公安局信息
			townList = super.findList(null, Filter.eq("parentId", areaPrikey), Sort.asc("id"));
		} else if (isPolice == 1) {
			// 根据区级公安局主键id和是警钟部门的查询镇级公安局信息
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(Filter.eq("policeTypesParentId", areaPrikey));
			filters.add(Filter.eq("isPoliceSection", isPolice));
			List<Sort> sorts = new ArrayList<Sort>();
			sorts.add(Sort.asc("id"));
			townList = super.findList(null, filters, sorts);
		}
		if (townList.size() > 0) {
			for (int i = 0; i < townList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", townList.get(i).getId());
				map.put("unitName", townList.get(i).getUnitName());
				map.put("isPolice", townList.get(i).getIsPoliceSection());
				listMap.add(map);
			}
		}
		return listMap;
	}

	/**
	 * 根据警员所属单位级别查询可下发警种部门
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月7日 下午9:25:06
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Unit> getUnitByRank(String unitRank, int isPoliceSection) {
		List<Filter> filters = new ArrayList<Filter>();
		// 是否为警种
		filters.add(Filter.eq("isPoliceSection", isPoliceSection));
		if ("city".equals(unitRank) || "area".equals(unitRank)) {// 区级以上
			filters.add(Filter.eq("unitRank", "area"));
		} else if ("town".equals(unitRank)) {// 镇级
			filters.add(Filter.eq("unitRank", "town"));
		}
		List<Sort> sorts = new ArrayList<Sort>();
		sorts.add(Sort.asc("id"));
		return findList(null, filters, sorts);
	}

	/**
	 * 根据单位查询下级单位
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月11日 上午9:54:10
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getNextUnit(Integer areaId) {
		List<Unit> list = new ArrayList<Unit>();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		if (areaId != null) {
			Unit unit = super.get(areaId);
			if (unit.getIsPoliceSection() == 0) {
				list = super.findList(null, Filter.eq("parentId", areaId), Sort.asc("id"));
			} else {
				list = super.findList(null, Filter.eq("policeTypesParentId", areaId), Sort.asc("id"));
			}
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", list.get(i).getId());
				map.put("name", list.get(i).getUnitName());
				map.put("unitCode", Unit.getUnitCode(list.get(i)));
				List<UnitType> unitTypes = unitTypeService.findList(null, Filter.eq("unitId", list.get(i).getId()), null);
				String typeName = "";
				for (int j = 0; j < unitTypes.size(); j++) {
					Dictionary di = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, unitTypes.get(j).getDictionaryId());
					if (di == null) {
						continue;
					}
					typeName += di.getItemName();
					if (j != unitTypes.size() - 1) {
						typeName += ",";
					}
				}
				map.put("polices", typeName);// 关联警种
				listMap.add(map);
			}
		}
		return listMap;
	}

	/**
	 * 查询所有区县级和乡镇级单位
	 * 
	 * @author 贺云<br>
	 * @date 2017年12月11日 下午3:19:37
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Unit> findUnitList() {
		String hql = "from Unit where unitRank=:unitRank1 or unitRank=:unitRank2";
		Query query = getSession().createQuery(hql);
		query.setParameter("unitRank1", "area");
		query.setParameter("unitRank2", "town");
		return query.list();
	}

	/**
	 * 获取所有的区级警种单位
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月11日 上午9:54:10
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getPoliceUnit(Integer isPolice) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("parentId", 1));
		filters.add(Filter.eq("isPoliceSection", isPolice));
		List<Unit> list = super.findList(null, filters, null);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", list.get(i).getId());
			map.put("name", list.get(i).getUnitName());
			listMap.add(map);
		}
		return listMap;
	}

	/**
	 * 根据单位id获取下级所有id
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月20日 下午10:24:24
	 */
	private List<Integer> getAdminUnits(Integer unitId) {
		List<Integer> adminUnits = new ArrayList<Integer>();
		List<Object> unitIds = this.findSonId(unitId);
		if (unitIds.size() > 0) {
			for (int i = 0; i < unitIds.size(); i++) {
				adminUnits.add(Integer.valueOf(unitIds.get(i).toString()));
			}
		}
		return adminUnits;
	}

	/**
	 * 根据单位id查询下级单位区分警种
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月6日 上午10:01:09
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getUnitIsPolice(Integer id) {
		List<Unit> list = new ArrayList<Unit>();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("parentId", id));
		List<Sort> sorts = new ArrayList<Sort>();
		sorts.add(Sort.asc("id"));
		list = super.findList(null, filters, sorts);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", list.get(i).getId());
			map.put("name", list.get(i).getUnitName());
			listMap.add(map);
		}
		return listMap;
	}

}
