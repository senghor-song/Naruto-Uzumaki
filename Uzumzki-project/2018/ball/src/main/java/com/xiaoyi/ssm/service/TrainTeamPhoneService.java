package com.xiaoyi.ssm.service;

import java.util.Date;

import com.xiaoyi.ssm.model.TrainTeamPhone;

/**  
 * @Description: 培训机构致电记录逻辑层
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:04:23 
 */ 
public interface TrainTeamPhoneService extends BaseService<TrainTeamPhone, String> {


	/**  
	 * @Description: 根据培训机构ID统计60天培训机构致电记录
	 * @author 宋高俊  
	 * @param id
	 * @param date 
	 * @return 
	 * @date 2018年10月10日 下午5:14:49 
	 */ 
	int countByTeamId(String id, Date date);
}
