package com.ruiec.web.service;

import java.util.List;
import java.util.Map;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.common.JsonReturn;
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
	
}
