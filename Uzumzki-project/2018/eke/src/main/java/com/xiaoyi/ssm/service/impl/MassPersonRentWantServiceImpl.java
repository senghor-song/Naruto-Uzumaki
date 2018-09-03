package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MassPersonRentWantEmpCondiMapper;
import com.xiaoyi.ssm.dao.MassPersonRentWantMapper;
import com.xiaoyi.ssm.dto.MassPersonDto;
import com.xiaoyi.ssm.model.MassPersonRentWant;
import com.xiaoyi.ssm.model.MassPersonRentWantEmpCondi;
import com.xiaoyi.ssm.service.MassPersonRentWantService;

/**  
 * @Description: 个人求租业务逻辑实现
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:16:41 
 */ 
@Service
public class MassPersonRentWantServiceImpl extends AbstractService<MassPersonRentWant,String> implements MassPersonRentWantService{

	@Autowired
	private MassPersonRentWantMapper massPersonRentWantMapper;
	@Autowired
	private MassPersonRentWantEmpCondiMapper massPersonRentWantEmpCondiMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(massPersonRentWantMapper);
	}
	
	/**  
	 * @Description: 条件筛选个人求租数据
	 * @author 宋高俊  
	 * @date 2018年6月27日 下午4:30:47 
	 */ 
	@Override
	public List<MassPersonRentWant> selectAll(MassPersonDto massPersonDto) {
		return massPersonRentWantMapper.selectAll(massPersonDto);
	}
	
	/**  
	 * @Description: 查询属于经济人的常用搜索条件数据
	 * @author 宋高俊  
	 * @date 2018年6月27日 下午4:31:04 
	 */
	@Override
	public List<MassPersonRentWantEmpCondi> selectAllSearch(String empId) {
		return massPersonRentWantEmpCondiMapper.selectAllSearch(empId);
	}
	
	/**  
	 * @Description: 保存个人求租的搜索条件
	 * @author 宋高俊  
	 * @date 2018年6月30日 上午10:04:36 
	 */ 
	@Override
	public int insertMassPersonRentWantEmpCondi(MassPersonRentWantEmpCondi massPersonRentWantEmpCondi) {
		return massPersonRentWantEmpCondiMapper.insertSelective(massPersonRentWantEmpCondi);
	}

}
