package com.ruiec.web.service;

import java.util.List;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.entity.DictionaryType;
/**
 * 字典类型接口
 * @author yuankai
 * date 2017年11月30
 * */
public interface DictionaryTypeService extends BaseService<DictionaryType, Integer>{

	/**
	 * 修改字典数据是否默认
	 * @author yuankai<br>
	 * @date 2017年12月2日 上午9:49:17
	 */
	public void updateIsUse(Integer id);
   
	/**
	 * 查询所有的字典名称，防止中文名称重复
	 * @author yuankai 
	 * @date 2017年12月6日 下午10:50:06
	 */
	public List<DictionaryType> repeat();
	
	/**
	 * 查询所有的字典名称，防止英文名称重复
	 * @author yuankai 
	 * @date 2017年12月6日 下午10:50:06
 	 */
	public List<DictionaryType> repeatEnglish();
	
	/**
	 * 查询所有的字典名称，防止中文名称重复
	 * @author yuankai 
	 * @date 2017年12月6日 下午10:50:06
	 */
	public List<DictionaryType> repeatList(Integer id);
}
