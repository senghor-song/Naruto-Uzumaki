package com.ruiec.web.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.entity.DictionaryType;
import com.ruiec.web.service.DictionaryTypeService;

/**
 * @author yuankai date 2017年11月30 字典类型服务类
 * */
@Service("dictionaryTypeServiceImpl")
public class DictionaryTypeServiceImpl extends BaseServiceImpl<DictionaryType, Integer> implements DictionaryTypeService {

	/**
	 * 修改字典数据默认状态
	 * 
	 * @author yuankai<br>
	 * @date 2017年12月2日 上午9:49:17
	 */
	@Override
	@Transactional
	public void updateIsUse(Integer id) {
		// 查询字典类型
		DictionaryType dictionaryType = get(id);
		if (dictionaryType.getisUse() == 1) {
			dictionaryType.setisUse(0);
		} else {
			dictionaryType.setisUse(1);
		}
		// 修改字典类型
		update(dictionaryType);
	}

	/**
	 * 查询所有的字典名称，防止中文名称重复
	 * 
	 * @author yuankai 
	 * @date 2017年12月6日 下午10:50:06
	 */
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<DictionaryType> repeat() {
		String sql = "select ITEMNAME from  T_SYS_DICTIONARY_TYPE";
		Query query = getSession().createSQLQuery(sql);
		List<DictionaryType> list = query.list();
		return list;
	}

	/**
	 * 查询所有的字典名称，防止英文名称重复
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:17:37
	 */
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<DictionaryType> repeatEnglish() {
		String sql = "select ITEMCODE from  T_SYS_DICTIONARY_TYPE";
		Query query = getSession().createSQLQuery(sql);
		List<DictionaryType> list = query.list();
		return list;
	}

	/**
	 * 查询所有的字典名称，防止修改时 中文名称重复
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:17:57
	 */
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<DictionaryType> repeatList(Integer id) {
		String sql = "select ITEMNAME from  T_SYS_DICTIONARY_TYPE where PRIKEY=" + id;
		Query query = getSession().createSQLQuery(sql);
		List<DictionaryType> list = query.list();
		return list;
	}
}
