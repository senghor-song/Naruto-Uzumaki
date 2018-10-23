package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.StaffApply;

public interface StaffApplyMapper extends BaseMapper<StaffApply, String>{

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