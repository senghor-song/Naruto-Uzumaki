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
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.UnitType;
import com.ruiec.web.entity.User;
import com.ruiec.web.entity.UserUnit;
import com.ruiec.web.interceptor.MySessionContext;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UnitTypeService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.service.UserUnitService;

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
	@Resource
	private UserService userService;
	@Resource
	private UserUnitService userUnitService;

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
			List<Integer> statis = findSonIds(unit.getId());
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
	 * 保存单位添加信息
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月11日 下午2:18:33
	 */
	@Override
	@Transactional
	public Unit save(Unit addUnit, Integer parentPrikey, String code, Integer policeUnitId) {
		// 程序严谨性判断，判断获取的值是否有空
		if (addUnit == null) {
			return null;
		}
		if (code == null) {
			return null;
		}
		if (parentPrikey == null) {
			parentPrikey = 1;
		}
		// 根据id查询父级单位
		Unit parentUnit = super.get(parentPrikey);
		if (parentUnit == null) {
			// 根据id获取不到单位
			return null;
		}
		// 获取省级编码
		addUnit.setProvinceCode(parentUnit.getProvinceCode());
		// 获取市级编码
		addUnit.setCityCode(parentUnit.getCityCode());
		// 判断要添加的单位是否警种
		if (addUnit.getIsPoliceSection() == 1) {
			// 警种添加下级则只存警种父级id
			if (parentUnit.getIsPoliceSection() == 0) {
				// 获取要添加的父级id
				addUnit.setParentId(parentPrikey);
			}
			// 获取要添加的警种父级id
			addUnit.setPoliceTypesParentId(policeUnitId);
		} else {
			// 获取要添加的父级id
			addUnit.setParentId(parentPrikey);
		}
		if (parentUnit.getIsPoliceSection() == 1 || (addUnit.getIsPoliceSection() == 1 && parentUnit.getId() == 1)) {
			if (parentUnit.getUnitRank().equals("city")) {
				// 添加区级单位
				addUnit.setUnitRank("area");
				addUnit.setAreaCode("00");
				addUnit.setTownCode(code.length() == 1 ? "0" + code : code);
				addUnit.setOther1Code("00");
				addUnit.setOther2Code("00");
			} else if (parentUnit.getUnitRank().equals("area")) {
				// 添加镇级单位
				addUnit.setUnitRank("town");
				addUnit.setAreaCode("00");
				addUnit.setTownCode(parentUnit.getTownCode());
				addUnit.setOther1Code(code.length() == 1 ? "0" + code : code);
				addUnit.setOther2Code("00");
			} else {
				// 添加其他单位则不添加
				return null;
			}
		} else {
			if (parentUnit.getUnitRank().equals("city")) {
				// 添加区级单位
				addUnit.setUnitRank("area");
				addUnit.setAreaCode(code.length() == 1 ? "0" + code : code);
				addUnit.setTownCode("00");
				addUnit.setOther1Code("00");
				addUnit.setOther2Code("00");
			} else if (parentUnit.getUnitRank().equals("area")) {
				// 添加镇级单位
				addUnit.setUnitRank("town");
				addUnit.setAreaCode(parentUnit.getAreaCode());
				addUnit.setTownCode(code.length() == 1 ? "0" + code : code);
				addUnit.setOther1Code("00");
				addUnit.setOther2Code("00");
			} else {
				// 添加其他单位则不添加
				return null;
			}
		}
		return addUnit;
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
	 *//*
	private List<Integer> getAdminUnits(Integer unitId) {
		List<Integer> adminUnits = new ArrayList<Integer>();
		List<Object> unitIds = this.findSonId(unitId);
		if (unitIds.size() > 0) {
			for (int i = 0; i < unitIds.size(); i++) {
				adminUnits.add(Integer.valueOf(unitIds.get(i).toString()));
			}
		}
		return adminUnits;
	}*/

	/**
	 * 保存单位数据
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月4日 下午2:33:27
	 */
	@Override
	@Transactional
	public JsonReturn saveUnit(Unit addUnit, Integer parentPrikey, String code, Integer policeUnitId) {
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("unitName", addUnit.getUnitName()));
		List<Unit> units = super.findList(null, Filter.eq("unitName", addUnit.getUnitName()), null);
		if (units.size() > 0) {
			return new JsonReturn(400, "该单位名称已被使用!");
		}
		// 程序严谨性判断，判断获取的值是否有空
		if (code == null) {
			return null;
		}
		if (parentPrikey == null) {
			parentPrikey = 1;
		}
		//判断是否值填写的一位编码
		code = code.length() == 1 ? "0" + code : code;
		// 根据id查询父级单位
		Unit parentUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, parentPrikey);
		if (parentUnit == null) {
			// 根据id获取不到单位
			return null;
		}
		// 获取省级编码
		addUnit.setProvinceCode(parentUnit.getProvinceCode());
		// 获取市级编码
		addUnit.setCityCode(parentUnit.getCityCode());
		// 判断要添加的单位是否警种
		if (addUnit.getIsPoliceSection() == 1) {
			// 警种添加下级则只存警种父级id
			if (parentUnit.getIsPoliceSection() == 0) {
				// 获取要添加的父级id
				addUnit.setParentId(parentPrikey);
			}
			// 获取要添加的警种父级id
			addUnit.setPoliceTypesParentId(policeUnitId);
		} else {
			// 获取要添加的父级id
			addUnit.setParentId(parentPrikey);
		}
		//添加区级单位
		if (parentUnit.getUnitRank().equals("city")) {
			addUnit.setUnitRank("area");
			//添加警种单位
			if (addUnit.getIsPoliceSection() == 1) {
				// 添加区级单位
				addUnit.setAreaCode("00");
				addUnit.setTownCode(code);
			}else {
				// 添加区级单位
				addUnit.setAreaCode(code);
				addUnit.setTownCode("00");
			}
			addUnit.setOther1Code("00");
			addUnit.setOther2Code("00");
		} else if (parentUnit.getUnitRank().equals("area")) {
			//添加镇级数据
			addUnit.setUnitRank("town");
			//添加警种单位
			if (parentUnit.getIsPoliceSection() == 1) {
				addUnit.setAreaCode("00");
				addUnit.setTownCode(parentUnit.getTownCode());
				addUnit.setOther1Code(code);
			}else {
				addUnit.setAreaCode(parentUnit.getAreaCode());
				addUnit.setTownCode(code);
				addUnit.setOther1Code("00");
			}
			addUnit.setOther2Code("00");
		}
		
		// 判断单位编码是否被使用了
		Unit isUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_CODE_MAP, Unit.getUnitCode(addUnit));
		if (isUnit != null) {
			return new JsonReturn(400, "该单位编码已被使用!");
		}
		try {
			// 单位添加成功
			super.save(addUnit);
		} catch (Exception e) {
			LOGGER.error("单位添加失败" + e);
			// 单位添加失败
			return new JsonReturn(400, "单位添加失败");
		}
		// 新增单位id缓存
		RedisUtil.addRedis(GlobalUnit.UNIT_MAP, addUnit.getId().toString(), addUnit);
		// 新增单位编码缓存
		RedisUtil.addRedis(GlobalUnit.UNIT_CODE_MAP, Unit.getUnitCode(addUnit), addUnit);
		return new JsonReturn(200, "单位添加成功", "/admin/unit/list.shtml");
	}

	/**
	 * 修改单位数据
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月4日 下午2:33:27
	 */
	@Override
	@Transactional
	public JsonReturn updateUnit(Unit unit, String code, Integer parentUnitId) {
		// 获取未修改之前的单位数据
		Unit outUnit = super.get(unit.getId());
		if (code==null) {
			return null;
		}
		code = code.length() == 1 ? "0" + code : code;
		// 获取到下级元素的单位id
		List<Integer> unitIds = this.findSonIdsNoThis(unit.getId());
		// 判断是否有修改是否警种字段
		if (outUnit.getIsPoliceSection() != unit.getIsPoliceSection() && outUnit.getUnitRank().equals("area")) {
			// 下属单位检查是否有不能显示的数据
			for (int i = 0; i < unitIds.size(); i++) {
				Unit sonUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unitIds.get(i));
				if (unit.getIsPoliceSection() == 1) {
					// 单位修改为警种时判断这个下级单位是否非警种
					if (sonUnit.getIsPoliceSection() != 1) {
						return new JsonReturn(400, "单位修改失败,请确保下级单位没有非警种单位");
					}
				} else {
					// 单位修改为非警种时判断这个下级单位是否警种
					if (sonUnit.getIsPoliceSection() != 0) {
						return new JsonReturn(400, "单位修改失败,请确保下级单位没有警种单位");
					}
				}
			}
		}
		// 获取省级单位编码
		unit.setProvinceCode(outUnit.getProvinceCode());
		// 获取市级单位编码
		unit.setCityCode(outUnit.getCityCode());
		
		// 修改区级单位
		if (outUnit.getUnitRank().equals("area")) {
			//父级id不变
			unit.setParentId(GlobalUnit.CITY_UNIT_ID);
			//修改编码
			if(unit.getIsPoliceSection().equals(0)){
				unit.setAreaCode(code);
				unit.setTownCode("00");
			}else {
				unit.setAreaCode("00");
				unit.setTownCode(code);
			}
			unit.setOther1Code("00");
			unit.setOther2Code("00");
		} else {
		//修改镇级单位
			//修改对应的父级id
			if (unit.getIsPoliceSection() == 0) {
				unit.setParentId(parentUnitId);
				unit.setPoliceTypesParentId(outUnit.getPoliceTypesParentId());
			}else {
				unit.setParentId(outUnit.getParentId());
				unit.setPoliceTypesParentId(parentUnitId);
			}
			Unit parentUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, parentUnitId);
			if (parentUnit.getIsPoliceSection()==0) {
				//修改编码
				unit.setAreaCode(parentUnit.getAreaCode());
				unit.setTownCode(code);
				unit.setOther1Code(parentUnit.getOther1Code());
				unit.setOther2Code("00");
			}else {
				//修改编码
				if(outUnit.getParentId()!=null){
					unit.setAreaCode(outUnit.getAreaCode());
					unit.setTownCode(code);
					unit.setOther1Code(parentUnit.getOther1Code());
				}else {
					unit.setAreaCode(parentUnit.getAreaCode());
					unit.setTownCode(parentUnit.getTownCode());
					unit.setOther1Code(code);
				}
				unit.setOther2Code("00");
			}
		}
		Unit isUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_CODE_MAP, Unit.getUnitCode(unit));
		if (!Unit.getUnitCode(outUnit).equals(Unit.getUnitCode(isUnit))) {
			if (isUnit != null) {
				return new JsonReturn(400, "该单位编码已被使用!");
			}
		}
		try {
			String outUnitCode = Unit.getUnitCode(outUnit);
			// 修改成功
			super.updateIgnore(unit, new String[] { "unitRank", "provinceCode", "cityCode" }, null);
			//下线用户
			outLogin(unit);
			// 只修改区级下面的单位编码
			String newUnitCode = Unit.getUnitCode(unit);
			if (outUnit.getUnitRank().equals("area") && !newUnitCode.equals(outUnitCode)) {
				for (int i = 0; i < unitIds.size(); i++) {
					Unit sonUnit = (Unit)RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unitIds.get(i));
					if (outUnit.getIsPoliceSection() == 1){
						if (sonUnit.getParentId()==null) {
							sonUnit.setTownCode(code);
							super.updateInclude(sonUnit, new String[] { "townCode" }, null);
						}
					}else {
						sonUnit.setAreaCode(code);
						super.updateInclude(sonUnit, new String[] { "areaCode" }, null);
					}
					// 新增单位id缓存
					RedisUtil.addRedis(GlobalUnit.UNIT_MAP, sonUnit.getId().toString(), sonUnit);
					// 新增单位编码缓存
					RedisUtil.addRedis(GlobalUnit.UNIT_CODE_MAP, Unit.getUnitCode(sonUnit), sonUnit);
				}
			}
		} catch (Exception e) {
			LOGGER.error("单位修改失败" + e);
			// 添加失败
			return new JsonReturn(400, "单位修改失败");
		}
		Unit newUnit = super.get(unit.getId());
		// 新增单位id缓存
		RedisUtil.addRedis(GlobalUnit.UNIT_MAP, newUnit.getId().toString(), newUnit);
		// 新增单位编码缓存
		RedisUtil.addRedis(GlobalUnit.UNIT_CODE_MAP, Unit.getUnitCode(newUnit), newUnit);
		return new JsonReturn(200, "单位修改成功", "/admin/unit/list.shtml");
	}

	/**
	 * 根据警员id获取所能管理的单位
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月4日 下午7:55:35
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getAdminUnit(Integer userId) {
		List<Unit> list = new ArrayList<Unit>();
		User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, userId);
		Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
		if (unit.getUnitRank().equals("city")) {
			// 市局民警则能管理所有的单位
			list = super.getAll();
		} else {
			Unit cityUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, GlobalUnit.CITY_UNIT_ID);// 市局单位
			list.add(cityUnit);
			list.add(unit);
			if (unit.getUnitRank().equals("area")) {
				// 分县局民警则能上级市局和下级所有的单位
				if (unit.getIsPoliceSection() == 0) {
					list.addAll(super.findList(null, Filter.eq("parentId", unit.getId()), null));
				} else {
					list.addAll(super.findList(null, Filter.eq("policeTypesParentId", unit.getId()), null));
				}
			} else if (unit.getUnitRank().equals("town")) {
				// 派出所民警则能管理上级分县局和上上级市局单位
				Unit areaUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unit.getParentId());// 普通父级
				list.add(areaUnit);
				if (unit.getIsPoliceSection() == 1) {
					Unit policeAreaUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unit.getPoliceTypesParentId());// 警种父级
					list.add(policeAreaUnit);
				}
			}
		}
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", list.get(i).getId());
			map.put("unitName", list.get(i).getUnitName());
			listMap.add(map);
		}
		return listMap;
	}

	/**
	 * 根据单位id查询下级单位
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
		Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, id);
		if (unit.getIsPoliceSection().equals(0)) {
			filters.add(Filter.eq("parentId", id));
		}else {
			filters.add(Filter.isNull("parentId"));
			filters.add(Filter.eq("policeTypesParentId", id));
		}
		List<Sort> sorts = new ArrayList<Sort>();
		sorts.add(Sort.asc("id"));
		list = super.findList(null, filters, sorts);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", list.get(i).getId());
			map.put("name", list.get(i).getUnitName());
			map.put("unitCode", Unit.getUnitCode(list.get(i)));
			listMap.add(map);
		}
		return listMap;
	}
	
	/**
	 * 使该单位的警员和管理员下线，重新登录
	 * @author Senghor<br>
	 * @date 2018年2月7日 上午11:28:04
	 */
	private void outLogin(Unit unit) {
		if (unit==null) {
			return;
		}
		//修改单位，该单位的所有警员都需要重新登录
		List<User> userList = userService.findList(null, Filter.eq("unitId", unit.getId()), null);
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getIsOnline()!=null) {
				MySessionContext.DelSession(MySessionContext.getSession(userList.get(i).getIsOnline()));
			}
		}
		//修改单位，管理该单位的所有管理员需要重新登录
		List<UserUnit> userUnitList = userUnitService.findList(null, Filter.eq("unit", unit), null);
		for (int i = 0; i < userUnitList.size(); i++) {
			if (userUnitList.get(i).getUser().getIsOnline()!=null) {
				MySessionContext.DelSession(MySessionContext.getSession(userUnitList.get(i).getUser().getIsOnline()));
			}
		}
	}
}
