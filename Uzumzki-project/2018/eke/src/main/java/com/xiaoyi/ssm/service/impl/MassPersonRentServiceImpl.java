package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MassPersonRentEmpCondiMapper;
import com.xiaoyi.ssm.dao.MassPersonRentMapper;
import com.xiaoyi.ssm.dto.MassPersonDto;
import com.xiaoyi.ssm.model.MassPersonRent;
import com.xiaoyi.ssm.model.MassPersonRentEmpCondi;
import com.xiaoyi.ssm.service.MassPersonRentService;

/**  
 * @Description: 个人出租业务逻辑实现
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:16:41 
 */ 
@Service
public class MassPersonRentServiceImpl extends AbstractService<MassPersonRent,String> implements MassPersonRentService{

	@Autowired
	private MassPersonRentMapper massPersonRentMapper;
	@Autowired
	private MassPersonRentEmpCondiMapper massPersonRentEmpCondiMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(massPersonRentMapper);
	}
	
	/**  
	 * @Description: 条件筛选个人出租数据
	 * @author 宋高俊  
	 * @date 2018年6月27日 下午4:30:47 
	 */ 
	@Override
	public List<MassPersonRent> selectAll(MassPersonDto massPersonDto) {
		return massPersonRentMapper.selectAll(massPersonDto);
	}

	/**  
	 * @Description: 查询属于经济人的常用搜索条件数据
	 * @author 宋高俊  
	 * @date 2018年6月27日 下午4:31:04 
	 */
	@Override
	public List<MassPersonRentEmpCondi> selectAllSearch(String empId) {
		return massPersonRentEmpCondiMapper.selectAllSearch(empId);
	}
	
	/**  
	 * @Description: 保存个人出租的搜索条件
	 * @author 宋高俊  
	 * @date 2018年6月30日 上午10:04:36 
	 */ 
	@Override
	public int insertMassPersonRentEmpCondi(MassPersonRentEmpCondi massPersonRentEmpCondi) {
		return massPersonRentEmpCondiMapper.insertSelective(massPersonRentEmpCondi);
	}
}
