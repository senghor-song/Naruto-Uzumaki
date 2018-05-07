package com.ruiec.web.service;

import java.util.List;
import java.util.Map;

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.entity.Dictionary;
/**
 * 字典数据服务接口
 * @author qinzhitian<br>
 * @date 2018年1月4日 上午11:23:04
 */
public interface DictionaryService extends BaseService<Dictionary, Integer>{

	/**
	 * 修改字典数据是否默认
	 * @author qinzhitian<br>
	 * @date 2017年12月2日 上午9:49:17
	 */
	public void updateIsDef(Integer id);

	/**
	 * 根据字典类型id查询所有字典数据
	 * @author qinzhitian<br>
	 * @date 2017年12月6日 下午3:36:53
	 */
	public List<Map<String, Object>> findByDataItemId(Integer dataItemId);
	
	/**
	 * 根据父级id字典数据子集
	 * @author qinzhitian<br>
	 * @date 2017年12月6日 下午3:36:53
	 */
	public List<Map<String, Object>> findSubSet(Integer parentId, Integer gpId);
	
	/**
	 * 查询所有字典数据id
	 * @author qinzhitian<br>
	 * @date 2017年12月6日 下午3:36:53
	 */
	public Integer[] findAllId();
	
	/**
	 * 根据字典类型id和父级id查询字典数据<br>
	 * parentId为0则查询第一级数据，为null则查询所有
	 * @author qinzhitian<br>
	 * @date 2017年12月6日 下午3:36:53
	 */
	List<Map<String, Object>> findByTypeId(Integer typeId, Integer parentId);
	
	/**
	 * 根据字典类型名字及父级id查询所有字典数据<br>
	 * parentId为0则查询第一级数据，为null则查询所有
	 * @author qinzhitian<br>
	 * @date 2017年12月6日 下午3:36:53
	 */
	public List<Map<String, Object>> findByItemName(String itemName, Integer parentId);
	
	/**
	 * 根据字典类型别名和父级id查询字典数据<br>
	 * parentId为0则查询第一级数据，为null则查询所有
	 * @author qinzhitian<br>
	 * @date 2017年12月6日 下午3:36:53
	 */
	List<Map<String, Object>> findByItemCode(String itemCode, Integer parentId);
	
	/**
	 * 删除字典数据
	 * @author qinzhitian<br>
	 * @date 2017年12月13日 下午3:10:56
	 */
	public int deleteByIds(Integer[] ids);
	
	/**
	 * 根据人员类别id获取子孙级
	 * @author Senghor<br>
	 * @date 2017年12月9日 上午10:45:06
	 */
	public Integer[] findSonId(Integer id);
	
	/**
	 * 查询字典数据是否包含子集
	 * @author qinzhitian<br>
	 * @date 2017年12月14日 上午9:46:55
	 */
	public boolean checkByIds(Integer[] ids);
	/**
	 * 分页查询
	 * @author qinzhitian<br>
	 * @date 2017年12月26日 上午10:33:38
	 */
	public Page findPage(Page page);
	

}
