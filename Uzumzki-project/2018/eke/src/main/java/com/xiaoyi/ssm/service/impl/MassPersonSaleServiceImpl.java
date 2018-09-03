package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MassPersonBuyWantEmpCondiMapper;
import com.xiaoyi.ssm.dao.MassPersonRentEmpCondiMapper;
import com.xiaoyi.ssm.dao.MassPersonRentWantEmpCondiMapper;
import com.xiaoyi.ssm.dao.MassPersonSaleEmpCondiMapper;
import com.xiaoyi.ssm.dao.MassPersonSaleMapper;
import com.xiaoyi.ssm.dto.MassPersonDto;
import com.xiaoyi.ssm.model.MassPersonSale;
import com.xiaoyi.ssm.model.MassPersonSaleEmpCondi;
import com.xiaoyi.ssm.service.MassPersonSaleService;

/**  
 * @Description: 个人出售业务逻辑实现
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:16:41 
 */ 
@Service
public class MassPersonSaleServiceImpl extends AbstractService<MassPersonSale,String> implements MassPersonSaleService{

	@Autowired
	private MassPersonSaleMapper massPersonSaleMapper;
	@Autowired
	private MassPersonSaleEmpCondiMapper massPersonSaleEmpCondiMapper;
	@Autowired
	private MassPersonRentEmpCondiMapper massPersonRentEmpCondiMapper;
	@Autowired
	private MassPersonBuyWantEmpCondiMapper massPersonBuyWantEmpCondiMapper;
	@Autowired
	private MassPersonRentWantEmpCondiMapper massPersonRentWantEmpCondiMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(massPersonSaleMapper);
	}
	
	/**  
	 * @Description: 条件筛选个人出售数据
	 * @author 宋高俊  
	 * @date 2018年6月27日 下午4:30:47 
	 */ 
	@Override
	public List<MassPersonSale> selectAll(MassPersonDto massPersonDto) {
		return massPersonSaleMapper.selectAll(massPersonDto);
	}
	
	/**  
	 * @Description: 查询属于经济人的常用搜索条件数据
	 * @author 宋高俊  
	 * @date 2018年6月27日 下午4:31:04 
	 */
	@Override
	public List<MassPersonSaleEmpCondi> selectAllSearch(String empId) {
		return massPersonSaleEmpCondiMapper.selectAllSearch(empId);
	}
	
	/**  
	 * @Description: 保存个人出售的搜索条件
	 * @author 宋高俊  
	 * @date 2018年6月30日 上午10:04:36 
	 */ 
	@Override
	public int insertMassPersonSaleEmpCondi(MassPersonSaleEmpCondi massPersonSaleEmpCondi) {
		return massPersonSaleEmpCondiMapper.insertSelective(massPersonSaleEmpCondi);
	}

}
