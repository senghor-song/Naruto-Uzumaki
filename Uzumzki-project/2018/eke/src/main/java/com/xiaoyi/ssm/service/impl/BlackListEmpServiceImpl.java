package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.BlackListEmpMapper;
import com.xiaoyi.ssm.model.BlackListEmp;
import com.xiaoyi.ssm.service.BlackListEmpService;

/**  
 * @Description: 黑名单业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月5日 下午7:44:44 
 */ 
@Service
public class BlackListEmpServiceImpl extends AbstractService<BlackListEmp,String> implements BlackListEmpService{

	@Autowired
	private BlackListEmpMapper blackListEmpMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(blackListEmpMapper);
	}
	
	/**  
	 * @Description: 根据经纪人ID查询黑名单
	 * @author 宋高俊  
	 * @date 2018年7月5日 下午7:50:44 
	 */ 
	@Override
	public List<BlackListEmp> selectAll(String empId) {
		return blackListEmpMapper.selectAll(empId);
	}

}
