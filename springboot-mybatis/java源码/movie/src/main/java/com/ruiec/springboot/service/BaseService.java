package com.ruiec.springboot.service;

import java.util.List;
import java.util.Map;

import com.ruiec.springboot.util.ResponseDTO;

/**
 * 基础服务实现类接口
 * @author qinzhitian<br>
 * @date 2017年11月15日 下午8:31:56
 */
public interface BaseService {

	/**
     * 插入记录（选择字段）
     * @author qinzhitian<br>
     * @date 2017年11月15日 下午8:45:20
     */
	ResponseDTO add(Map<String, Object> obj);
	
	/**
     * 根据ID删除记录
     * @author qinzhitian<br>
     * @date 2017年11月15日 下午8:45:35
     */
	ResponseDTO delete(String[] ids);
	
	/**
     * 更新记录（选择字段）
     * @author qinzhitian<br>
     * @date 2017年11月15日 下午8:46:12
     */
	ResponseDTO update(Map<String, Object> obj);
	
	/**
     * 根据ID获取记录
     * @author qinzhitian<br>
     * @date 2017年11月15日 下午8:46:17
     */
	ResponseDTO selectById(Long id);
	
	/**
	 * 查询所有数据
	 * @author qinzhitian<br>
	 * @date 2017年11月28日 下午9:14:26
	 */
	ResponseDTO selectAll();
	
	 /**
     * 分页查询记录
     * @author qinzhitian<br>
     * @date 2017年11月15日 下午8:46:17
     */
	ResponseDTO selectPaged(Map<String, Object> obj);
}
