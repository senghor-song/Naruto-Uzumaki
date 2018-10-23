package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.TrainEnter;

/**  
 * @Description: 培训机构入驻申请业务逻辑层
 * @author 宋高俊  
 * @date 2018年10月17日 下午3:33:11 
 */ 
public interface TrainEnterService extends BaseService<TrainEnter, String> {

	/**  
	 * @Description: 根据审核状态查询入驻申请
	 * @author 宋高俊  
	 * @param checkFlag
	 * @return 
	 * @date 2018年10月17日 下午5:33:32 
	 */ 
	List<TrainEnter> selectByEnterAll(Integer checkFlag);

	/**  
	 * @Description: 统计未处理的培训机构申请数据
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年10月18日 下午2:10:31 
	 */ 
	Integer countEnter();

}
