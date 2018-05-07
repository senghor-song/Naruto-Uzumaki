/* 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.service;

import java.util.List;
import java.util.Map;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.entity.Unit;

/**
 * 单位服务接口
 * @author Senghor<br>
 * @date 2017年11月30日 上午8:50:31
 */
public interface UnitService extends BaseService<Unit, Integer>{
	
	/**
	 * 获取单位列表的数据
	 * @author Senghor<br>
	 * @date 2018年1月4日 下午2:23:57
	 */
	public List<Map<String, Object>> unitListMaps() ;
	
	/**
	 * 获取子孙级id
	 * @author Senghor<br>
	 * @date 2017年12月9日 上午10:45:06
	 */
	public List<Integer> findSonIds(Integer id);

	/**
	 * 获取子孙级id不包含自己
	 * @author Senghor<br>
	 * @date 2018年1月16日 下午4:22:45
	 */
	public List<Integer> findSonIdsNoThis(Integer id);

	/**
	 * 获取区级单位的数据
	 * isCity : 1将市级单位获取   ，0不获取市级单位
	 * @author Senghor<br>
	 * @date 2017年11月30日 上午8:52:54
	 */
	public List<Unit> getUnitArea(Integer isCity);

	/**
	 * 获取镇级单位的数据
	 * @author Senghor<br>
	 * @date 2017年11月30日 上午8:52:54
	 */
	public List<Map<String, Object>> getUnitTown(Integer areaPrikey, Integer isPolice);
	
	/**
	 * 根据警员所属单位级别查询可下发部门
	 * @author qinzhitian<br>
	 * @date 2017年12月7日 下午9:25:06
	 */
	public List<Unit> getUnitByRank(String unitRank, int isPoliceSection);

	/**
	 * 根据单位查询下级单位
	 * @author Senghor<br>
	 * @date 2017年12月11日 上午9:55:19
	 */
	public List<Map<String, Object>> getNextUnit(Integer areaId);
	
	/**
	 * 重写保存单位添加信息
	 * @author Senghor<br>
	 * @date 2017年12月11日 下午2:19:44
	 */
	public Unit save(Unit addUnit, Integer parentPrikey, String code ,Integer policeUnitId);

	/**
	 * 查询所有区县级和乡镇级单位
	 * @author 贺云<br>
	 * @date 2017年12月11日 下午3:18:44
	 */
	List<Unit> findUnitList();
	
	/**
	 * 获取所有的区级警种单位
	 * @author Senghor<br>
	 * @date 2017年12月13日 上午11:10:35
	 */
	public List<Map<String, Object>> getPoliceUnit(Integer isPolice);
	
	/**
	 * 保存单位数据
	 * @author Senghor<br>
	 * @date 2018年1月4日 下午2:26:33
	 */
	public JsonReturn saveUnit(Unit addUnit, Integer parentPrikey, String code ,Integer policeUnitId);
	
	/**
	 * 修改单位数据
	 * @author Senghor<br>
	 * @date 2018年1月4日 下午2:26:33
	 */
	public JsonReturn updateUnit(Unit unit, String code, Integer policeUnitId);

	/**
	 * 获取区级和镇级单位的数据
	 * @author Senghor<br>
	 * @date 2017年12月11日 上午9:55:19
	 */
	public List<Map<String, Object>> getAdminUnit(Integer userId);
	
	/**
	 * 根据单位id查询下级单位
	 * @author Senghor<br>
	 * @date 2018年1月6日 上午10:01:09
	 */
	public List<Map<String, Object>> getUnitIsPolice(Integer id);
	
}
