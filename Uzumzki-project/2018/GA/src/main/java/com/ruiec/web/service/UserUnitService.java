package com.ruiec.web.service;

import java.util.List;
import java.util.Map;

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.dto.UserUnitDTO;
import com.ruiec.web.entity.User;
import com.ruiec.web.entity.UserUnit;

/**
 * 管理员管理接口
 * @author 贺云<br>
 * @date 2017年12月5日 下午4:01:56
 */
public interface UserUnitService extends BaseService<UserUnit, Integer>{


	/**
	 * 查询单位管理员
	 * @author qinzhitian<br>
	 * @date 2017年12月8日 上午10:12:29
	 */
	public JsonReturn checkByIds(Integer[] ids,Integer userId);
	/**
	 * 查询单位管理员是否存在
	 * @author qinzhitian<br>
	 * @date 2017年12月8日 上午10:12:29
	 */
	boolean checkById(Integer id);
	/**
	 * 新增管理员
	 * @author 贺云<br>
	 * @date 2017年12月12日 上午10:24:53
	 */
	public JsonReturn saveUserUnit(Integer userId, Integer unitId,User loginUser);
	
	/**
	 * 查询管理员列表
	 * @author 贺云<br>
	 * @date 2017年12月13日 上午9:35:35
	 */
	public void findUserUnitList(Page page, UserUnitDTO dto);
	
	/**
	 * 查询用户关联管理单位
	 * @author qinzhitian<br>
	 * @date 2017年12月17日 下午2:50:55
	 */
	public List<Map<String, Object>> findListByUser(User user);
	
	/**
	 * 获取用户对应单位的数据
	 * @author Senghor<br>
	 * @date 2017年12月20日 下午4:38:52
	 */
	public Map<String, List<Map<String, Object>>> getUserUnits();
	
	/**
	 * 编辑管理员
	 * @author 贺云<br>
	 * @date 2017年12月22日 上午9:31:56
	 */
	public JsonReturn updateUserUnit(Integer id, Integer unitId);
	
}
