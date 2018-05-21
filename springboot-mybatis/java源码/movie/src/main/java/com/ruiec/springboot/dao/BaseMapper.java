package com.ruiec.springboot.dao;

import java.util.List;
import java.util.Map;

/**
 * 基础数据访问服务接口
 * @author qinzhitian<br>
 * @date 2017年11月16日 上午11:30:04
 */
public interface BaseMapper {
	 
	/**
     * 插入记录（选择字段）
     * @author qinzhitian<br>
     * @date 2017年11月15日 下午8:45:27
     */
	int insert(Map<String, Object> obj);
    
    /**
     * 根据ID删除记录
     * @author qinzhitian<br>
     * @date 2017年11月15日 下午8:45:35
     */
	int delete(String[] ids);
	
	/**
     * 更新记录（选择字段）
     * @author qinzhitian<br>
     * @date 2017年11月15日 下午8:46:12
     */
    int update(Map<String, Object> obj);
	
	/**
     * 根据ID获取记录
     * @author qinzhitian<br>
     * @date 2017年11月15日 下午8:46:17
     */
    Map<String, Object> selectById(Long id);
    
    /**
     * 分页查询记录
     * @author qinzhitian<br>
     * @date 2017年11月15日 下午8:46:17
     */
	List<Map<String, Object>> selectPaged(Map<String, Object> obj);
   
	/**
	 * 查询所有数据
	 * @author qinzhitian<br>
	 * @date 2017年11月28日 下午9:14:26
	 */
	List<Map<String, Object>> selectAll();
}
