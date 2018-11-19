package com.xiaoyi.ssm.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.TrainTeamPhone;

public interface TrainTeamPhoneMapper extends BaseMapper<TrainTeamPhone, String>{
	
	/**  
	 * @Description: 根据培训机构ID统计培训机构致电记录
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月10日 下午5:14:49 
	 */ 
	int countByTeamId(@Param("id")String id, @Param("date")Date date);
}