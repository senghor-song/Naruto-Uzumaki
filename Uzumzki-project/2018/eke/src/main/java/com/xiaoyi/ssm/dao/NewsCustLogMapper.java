package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.NewsCustLog;

/**  
 * @Description: 新闻日志数据访问层
 * @author 宋高俊  
 * @date 2018年7月31日 下午5:22:00 
 */ 
public interface NewsCustLogMapper extends BaseMapper<NewsCustLog, String>{
	/**  
	 * @Description: 根据新闻统计修改日志
	 * @author 宋高俊  
	 * @date 2018年7月31日 下午5:22:44 
	 */ 
	Integer countLogByNewsCust(String id);
}