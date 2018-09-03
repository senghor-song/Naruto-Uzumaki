package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.NewsBannerLog;

/**  
 * @Description: 横幅日志数据访问层
 * @author 宋高俊  
 * @date 2018年8月3日 上午11:19:34 
 */ 
public interface NewsBannerLogMapper extends BaseMapper<NewsBannerLog, String>{

	/**  
	 * @Description: 根据横幅统计日志次数
	 * @author 宋高俊  
	 * @date 2018年8月3日 上午11:38:53 
	 */ 
	Integer countByBanner(String id);

}