package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.NewsHeadLog;

/**  
 * @Description: 楼讯修改次数
 * @author 宋高俊  
 * @date 2018年7月30日 上午11:02:03 
 */ 
public interface NewsHeadLogMapper extends BaseMapper<NewsHeadLog, String>{

	/**  
	 * @Description: 根据楼讯统计修改日志
	 * @author 宋高俊  
	 * @date 2018年7月30日 上午11:01:31 
	 */ 
	Integer countLogByNewsHead(String newsHeadId);
	
}