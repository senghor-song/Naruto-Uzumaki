package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.StaffApply;

/**  
 * @Description: 申请加入管理员逻辑层
 * @author 宋高俊  
 * @date 2018年10月22日 上午11:25:34 
 */ 
public interface StaffApplyService extends BaseService<StaffApply, String> {

	/**  
	 * @Description: 根据审核状态查询
	 * @author 宋高俊  
	 * @param checkFlag
	 * @return 
	 * @date 2018年10月22日 上午11:39:07 
	 */ 
	List<StaffApply> selectByApply(Integer checkFlag);

	/**  
	 * @Description: 根据手机号查询申请加入数据
	 * @author 宋高俊  
	 * @param phone
	 * @return 
	 * @date 2018年10月22日 下午4:49:20 
	 */ 
	StaffApply selectByPhone(String phone);

}
