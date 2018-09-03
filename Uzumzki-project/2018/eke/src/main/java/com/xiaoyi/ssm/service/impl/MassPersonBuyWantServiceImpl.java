package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MassPersonBuyWantEmpCondiMapper;
import com.xiaoyi.ssm.dao.MassPersonBuyWantMapper;
import com.xiaoyi.ssm.dto.MassPersonDto;
import com.xiaoyi.ssm.model.MassPersonBuyWant;
import com.xiaoyi.ssm.model.MassPersonBuyWantEmpCondi;
import com.xiaoyi.ssm.service.MassPersonBuyWantService;

/**  
 * @Description: 个人求购业务逻辑实现
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:16:41 
 */ 
@Service
public class MassPersonBuyWantServiceImpl extends AbstractService<MassPersonBuyWant,String> implements MassPersonBuyWantService{

	@Autowired
	private MassPersonBuyWantMapper massPersonBuyWantMapper;
	@Autowired
	private MassPersonBuyWantEmpCondiMapper massPersonBuyWantEmpCondiMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(massPersonBuyWantMapper);
	}
	
	/**  
	 * @Description: 条件筛选个人求购数据
	 * @author 宋高俊  
	 * @date 2018年6月27日 下午4:30:47 
	 */ 
	@Override
	public List<MassPersonBuyWant> selectAll(MassPersonDto massPersonDto) {
		return massPersonBuyWantMapper.selectAll(massPersonDto);
	}

	/**  
	 * @Description: 查询属于经济人的常用搜索条件数据
	 * @author 宋高俊  
	 * @date 2018年6月27日 下午4:31:04 
	 */
	@Override
	public List<MassPersonBuyWantEmpCondi> selectAllSearch(String empId) {
		return massPersonBuyWantEmpCondiMapper.selectAllSearch(empId);
	}
	/**  
	 * @Description: 保存个人求购的搜索条件
	 * @author 宋高俊  
	 * @date 2018年6月30日 上午10:04:36 
	 */ 
	@Override
	public int insertMassPersonBuyWantEmpCondi(MassPersonBuyWantEmpCondi massPersonBuytWantEmpCondi) {
		return massPersonBuyWantEmpCondiMapper.insertSelective(massPersonBuytWantEmpCondi);
	}

}
