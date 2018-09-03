package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.VenueLock;

public interface VenueLockMapper extends BaseMapper<VenueLock, String>{
	
	/**  
	 * @Description: 根据管理员id查询不重复内容
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月1日 下午4:49:33 
	 */ 
	List<String> selectContentByManager(String id);
	
}