package com.ruiec.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.query.Sort;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.util.RuiecDateUtils;

/**
 * 字典数据服务实现
 * 
 * @author qinzhitian<br>
 * @date 2018年1月4日 上午11:22:34
 */
@Service("dictionaryServiceImpl")
public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary, Integer> implements DictionaryService {

	/**
	 * 修改字典数据默认状态
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月2日 上午9:49:17
	 */
	@Override
	@Transactional
	public void updateIsDef(Integer id) {
		// 查询字典数据
		Dictionary dictionary = get(id);
		if (dictionary.getIsDef() == 1) {
			dictionary.setIsDef(0);
		} else {
			dictionary.setIsDef(1);
		}
		// 修改字典数据
		update(dictionary);
	}

	/**
	 * 根据字典类型查询所有字典数据
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月6日 下午3:36:53
	 */
	@Override
	@Transactional
	public List<Map<String, Object>> findByDataItemId(Integer dataItemId) {
		Sort sort = Sort.asc("sortCode");
		Filter filter = Filter.eq("dictionaryType.id", dataItemId);
		List<Dictionary> dictionarys = findList(null, filter, sort);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		for (Dictionary dictionary : dictionarys) {
			map = new HashMap<String, Object>();
			map.put("id", dictionary.getId());
			map.put("itemName", dictionary.getItemName());
			list.add(map);
		}
		return list;
	}

	/**
	 * 查询所有字典数据id
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月6日 下午3:36:53
	 */
	@Override
	@Transactional
	public Integer[] findAllId() {
		List<Dictionary> dictionarys = findList(null, null, Sort.asc("sortCode"));
		Integer[] ids = new Integer[dictionarys.size()];
		int i = 0;
		for (Dictionary dictionary : dictionarys) {
			ids[i] = dictionary.getId();
			i++;
		}
		return ids;
	}

	/**
	 * 根据字典类型id及父级id查询所有字典数据<br>
	 * parentId为0则查询第一级数据，为null则查询所有
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月6日 下午3:36:53
	 */
	@Override
	@Transactional
	public List<Map<String, Object>> findByTypeId(Integer typeId, Integer parentId) {
		List<Sort> sorts = new ArrayList<Sort>();
		List<Filter> filters = new ArrayList<Filter>();// 查询条件
		filters.add(Filter.eq("dictionaryTypeAlias.id", typeId));
		if (parentId != null) {
			if (parentId == 0) {
				filters.add(Filter.isNull("parentId"));
			} else {
				filters.add(Filter.eq("parentId", parentId));
			}
		}
		sorts.add(Sort.asc("sortCode"));// 排序
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Dictionary.class).createAlias("dictionaryType", "dictionaryTypeAlias",
				JoinType.LEFT_OUTER_JOIN);
		List<Dictionary> dictionarys = findList(detachedCriteria, null, filters, sorts);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		for (Dictionary dictionary : dictionarys) {
			map = new HashMap<String, Object>();
			map.put("id", dictionary.getId());
			map.put("itemName", dictionary.getItemName());
			list.add(map);
		}
		return list;
	}

	/**
	 * 根据字典类型名字及父级id查询所有字典数据<br>
	 * parentId为0则查询第一级数据，为null则查询所有
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月6日 下午3:36:53
	 */
	@Override
	@Transactional
	public List<Map<String, Object>> findByItemName(String itemName, Integer parentId) {
		List<Sort> sorts = new ArrayList<Sort>();
		List<Filter> filters = new ArrayList<Filter>();// 查询条件
		filters.add(Filter.eq("dictionaryTypeAlias.itemName", itemName));
		if (parentId != null) {
			if (parentId == 0) {
				filters.add(Filter.isNull("parentId"));
			} else {
				filters.add(Filter.eq("parentId", parentId));
			}
		}
		sorts.add(Sort.asc("sortCode"));// 排序
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Dictionary.class).createAlias("dictionaryType", "dictionaryTypeAlias",
				JoinType.LEFT_OUTER_JOIN);
		List<Dictionary> dictionarys = findList(detachedCriteria, null, filters, sorts);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		for (Dictionary dictionary : dictionarys) {
			map = new HashMap<String, Object>();
			map.put("id", dictionary.getId());
			map.put("itemName", dictionary.getItemName());
			list.add(map);
		}
		return list;
	}

	/**
	 * 根据字典类型别名和父级id查询字典数据<br>
	 * parentId为0则查询第一级数据，为null则查询所有
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月6日 下午3:36:53
	 */
	@Override
	@Transactional
	public List<Map<String, Object>> findByItemCode(String itemCode, Integer parentId) {
		List<Sort> sorts = new ArrayList<Sort>();
		List<Filter> filters = new ArrayList<Filter>();// 查询条件
		filters.add(Filter.eq("dictionaryTypeAlias.itemCode", itemCode));
		if (parentId != null) {
			if (parentId == 0) {
				filters.add(Filter.isNull("parentId"));
			} else {
				filters.add(Filter.eq("parentId", parentId));
			}
		}
		sorts.add(Sort.asc("sortCode"));// 排序
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Dictionary.class).createAlias("dictionaryType", "dictionaryTypeAlias",
				JoinType.LEFT_OUTER_JOIN);
		List<Dictionary> dictionarys = findList(detachedCriteria, null, filters, sorts);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		for (Dictionary dictionary : dictionarys) {
			map = new HashMap<String, Object>();
			map.put("id", dictionary.getId());
			map.put("itemName", dictionary.getItemName());
			map.put("itemValue", dictionary.getItemValue());
			list.add(map);
		}
		return list;
	}

	/**
	 * 根据父级id字典数据子集
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月6日 下午3:36:53
	 */
	@Override
	@Transactional
	public List<Map<String, Object>> findSubSet(Integer parentId, Integer gpId) {
		List<Dictionary> dictionarieList = new ArrayList<Dictionary>();
		// 查询子集
		Filter filter = Filter.eq("parentId", parentId);
		Sort sort = Sort.asc("sortCode");
		dictionarieList = findList(null, filter, sort);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		Date modifyDate = null;
		// 类型转换
		for (Dictionary dictionary : dictionarieList) {
			map = new HashMap<>();
			map.put("id", dictionary.getId());
			map.put("name", dictionary.getItemName());
			map.put("itemValue", ObjectUtils.firstNonNull(dictionary.getItemValue(), "暂无"));
			map.put("sortCode", dictionary.getSortCode());
			map.put("description", dictionary.getDescription());
			map.put("isDef", dictionary.getIsDef());
			map.put("parentId", dictionary.getParentId());
			map.put("dataItemId", dictionary.getDictionaryType().getId());
			map.put("typeName", dictionary.getDictionaryType().getItemName());
			map.put("gpId", gpId);
			// 父级字典名字
			Dictionary parentDictionary = super.get(dictionary.getParentId());
			map.put("parentName", parentDictionary.getItemName());
			modifyDate = dictionary.getModifyDate();
			if (modifyDate != null) {
				map.put("modifyDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(modifyDate));
			} else {
				map.put("modifyDate", "暂无");
			}
			// 查询字典数据是否含有下级
			List<Dictionary> dictionaries = findList(null, Filter.eq("parentId", dictionary.getId()), null);
			if (dictionaries.size() > 0) {
				map.put("state", 1);
			} else {
				map.put("state", 0);
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * 删除字典数据
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月13日 下午3:10:56
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional
	public int deleteByIds(Integer[] ids) {
		int result = 0;
		Assert.notEmpty(ids);
		List<Integer> list = new ArrayList<Integer>();

		String hql = "SELECT PRIKEY FROM T_SYS_DICTIONARY WHERE PRIKEY in (:ids)  " + " union all "
				+ " select PRIKEY from T_SYS_DICTIONARY where PARENTID in (:ids) " + " union all "
				+ " select PRIKEY from T_SYS_DICTIONARY where PARENTID in (select PRIKEY from T_SYS_DICTIONARY where PARENTID in (:ids))";
		Query query = getSession().createSQLQuery(hql);
		query.setParameterList("ids", ids);
		list = (List<Integer>) query.list();
		// 去重
		list = new ArrayList<Integer>(new LinkedHashSet<>(list));
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			BigDecimal id = (BigDecimal) iterator.next();
			result = delete(id.intValue());
			if (result == 0) {
				throw new RuntimeException("字典数据删除失败");
			}
		}
		/*
		 * for (Integer id : list) { result = delete(id); if (result == 0) {
		 * throw new RuntimeException("字典数据删除失败"); } }
		 */
		return result;
	}

	/**
	 * 删除单条字典数据
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月13日 下午3:10:56
	 */
	// @Override
	@Transactional
	public int deleteById(Integer id) {
		Assert.notNull(id);
		int result = delete(id);
		if (result == 0) {
			// 删除失败，返回结果
			return result;
		}
		// 查询字典数据子集
		List<Dictionary> list = findList(null, Filter.eq("parentId", id), null);
		for (Dictionary dictionary : list) {
			// 删除子集
			result = deleteById(dictionary.getId());
			if (result == 0) {
				// 删除失败，返回结果
				return result;
			}
		}
		return result;
	}

	/**
	 * 根据人员类别获取子孙级
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月9日 上午10:45:06
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Integer[] findSonId(Integer id) {
		List<Object> list = new ArrayList<Object>();
		String hql = "SELECT PRIKEY FROM T_SYS_DICTIONARY WHERE PRIKEY = :id  " + " union all " + " select PRIKEY from T_SYS_DICTIONARY where PARENTID = :id "
				+ " union all " + " select PRIKEY from T_SYS_DICTIONARY where PARENTID in (select PRIKEY from T_SYS_DICTIONARY where PARENTID = :id)";
		Query query = getSession().createSQLQuery(hql);
		query.setParameter("id", id);
		list = (List<Object>) query.list();
		// 判断是否有下级数据
		if (list.size() > 0) {
			Integer[] personType = new Integer[list.size()];
			for (int i = 0; i < list.size(); i++) {
				personType[i] = Integer.parseInt(list.get(i).toString());
			}
			return personType;
		}
		return null;
	}

	/**
	 * 查询字典数据是否包含子集
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月14日 上午9:46:55
	 */
	@Transactional
	public boolean checkByIds(Integer[] ids) {
		Assert.notEmpty(ids);
		// 查询字典数据子集
		List<Dictionary> list = findList(null, Filter.in("parentId", ids), null);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 分页查询（并查询是否含有下级数据）
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月26日 上午10:56:54
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Page findPage(Page page, Integer dataItemId) {
		// 分页查询一级字典数据
		page.add(Filter.eq("dictionaryType.id", dataItemId));
		page.add(Filter.isNull("parentId"));
		page.add(Sort.asc("sortCode"));
		page.add(Sort.asc("createDate"));
		super.findByPage(page);
		List<Dictionary> dictionaries = (List<Dictionary>) page.getList();
		// 类型转换
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (Dictionary dictionary : dictionaries) {
			map = new HashMap<>();
			map.put("id", dictionary.getId());
			map.put("name", dictionary.getItemName());
			map.put("itemValue", ObjectUtils.firstNonNull(dictionary.getItemValue(), "暂无"));
			map.put("sortCode", dictionary.getSortCode());
			map.put("description", dictionary.getDescription());
			map.put("isDef", dictionary.getIsDef());
			map.put("parentId", dictionary.getParentId());
			map.put("dataItemId", dictionary.getDictionaryType().getId());
			map.put("typeName", dictionary.getDictionaryType().getItemName());
			Date modifyDate = dictionary.getModifyDate();
			if (modifyDate != null) {
				map.put("modifyDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(modifyDate));
			} else {
				map.put("modifyDate", "暂无");
			}
			// 查询字典数据是否含有下级
			List<Dictionary> list = findList(null, Filter.eq("parentId", dictionary.getId()), null);
			if (list.size() > 0) {
				map.put("state", 1);
			} else {
				map.put("state", 0);
			}
			mapList.add(map);
		}
		page.setList(mapList);
		return page;
	}
}
