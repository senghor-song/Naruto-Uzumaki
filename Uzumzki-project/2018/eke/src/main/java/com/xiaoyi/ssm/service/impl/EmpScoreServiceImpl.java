package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.EmpScoreMapper;
import com.xiaoyi.ssm.model.EmpScore;
import com.xiaoyi.ssm.service.EmpScoreService;

/**  
 * @Description: 积分业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月31日 上午9:19:09 
 */ 
@Service
public class EmpScoreServiceImpl extends AbstractService<EmpScore,String> implements EmpScoreService{

	@Autowired
	private EmpScoreMapper empScoreMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(empScoreMapper);
	}
	
	/**  
	 * @Description: 根据经纪人查询积分明细
	 * @author 宋高俊  
	 * @date 2018年8月9日 下午5:01:08 
	 */ 
	@Override
	public List<EmpScore> selectScoreByEmp(String id) {
		return empScoreMapper.selectScoreByEmp(id);
	}
	
}
