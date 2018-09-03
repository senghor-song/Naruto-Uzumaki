package com.xiaoyi.ssm.dao;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.EstateMatch;

public interface EstateMatchMapper extends BaseMapper<EstateMatch, String>{

	/**  
	 * @Description: 查询该网站是否已经匹配过
	 * @author 宋高俊  
	 * @date 2018年8月1日 下午3:58:04 
	 */ 
	EstateMatch selectByEstateWeb(@Param("estateId")String estateId, @Param("webId")String webId);

}