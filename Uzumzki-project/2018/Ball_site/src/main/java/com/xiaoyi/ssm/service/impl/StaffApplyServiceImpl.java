package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.StaffApplyMapper;
import com.xiaoyi.ssm.model.StaffApply;
import com.xiaoyi.ssm.service.StaffApplyService;

/**  
 * @Description: 申请加入管理员逻辑实现
 * @author 宋高俊  
 * @date 2018年10月22日 上午11:26:41 
 */ 
@Service
public class StaffApplyServiceImpl extends AbstractService<StaffApply,String> implements StaffApplyService{

	@Autowired
	private StaffApplyMapper staffApplyMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(staffApplyMapper);
	}

	/**  
	 * @Description: 根据审核状态查询
	 * @author 宋高俊  
	 * @param checkFlag
	 * @return 
	 * @date 2018年10月22日 上午11:39:07 
	 */ 
	@Override
	public List<StaffApply> selectByApply(Integer checkFlag) {
		return staffApplyMapper.selectByApply(checkFlag);
	}

	/**  
	 * @Description: 根据手机号查询申请加入数据
	 * @author 宋高俊  
	 * @param phone
	 * @return 
	 * @date 2018年10月22日 下午4:49:20 
	 */ 
	@Override
	public StaffApply selectByPhone(String phone) {
		return staffApplyMapper.selectByPhone(phone);
	}

}
