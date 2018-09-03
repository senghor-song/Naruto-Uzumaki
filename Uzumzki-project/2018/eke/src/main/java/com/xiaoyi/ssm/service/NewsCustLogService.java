package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.NewsCustLog;

/**  
 * @Description: 新闻日志业务逻辑接口
 * @author 宋高俊  
 * @date 2018年7月31日 下午5:13:08 
 */ 
public interface NewsCustLogService extends BaseService<NewsCustLog, String> {
	/**  
	 * @Description: 根据新闻统计修改日志
	 * @author 宋高俊  
	 * @date 2018年7月31日 下午5:22:44 
	 */ 
	Integer countLogByNewsCust(String id);
}
