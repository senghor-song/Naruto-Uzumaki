package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MassBindMapper;
import com.xiaoyi.ssm.model.MassBind;
import com.xiaoyi.ssm.service.MassBindService;

/**  
 * @Description: 经纪人业务逻辑实现
 * @author 宋高俊  
 * @date 2018年6月28日 下午2:32:54 
 */ 
@Service
public class MassBindServiceImpl extends AbstractService<MassBind,String> implements MassBindService{

	@Autowired
	private MassBindMapper massBindMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(massBindMapper);
	}

	/**  
	 * @Description: 根据经纪人查询绑定账号
	 * @author 宋高俊  
	 * @date 2018年6月29日 下午2:38:20 
	 */ 
	@Override
	public List<MassBind> selectByEmp(String empId) {
		return massBindMapper.selectByEmp(empId);
	}

	/**  
	 * @Description: 根据经纪人和网站查询数据
	 * @author 宋高俊  
	 * @date 2018年6月29日 下午2:38:20 
	 */ 
	@Override
	public List<MassBind> selectByEmpPlatform(String empid, String platform) {
		return massBindMapper.selectByEmpPlatform(empid, platform);
	}
	
}
