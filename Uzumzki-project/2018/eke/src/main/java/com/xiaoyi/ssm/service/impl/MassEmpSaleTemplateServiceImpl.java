package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MassEmpSaleTemplateMapper;
import com.xiaoyi.ssm.model.MassEmpSaleTemplate;
import com.xiaoyi.ssm.service.MassEmpSaleTemplateService;

/**  
 * @Description: 公告业务逻辑接口
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:15:42 
 */ 
@Service
public class MassEmpSaleTemplateServiceImpl extends AbstractService<MassEmpSaleTemplate,String> implements MassEmpSaleTemplateService{

	@Autowired
	private MassEmpSaleTemplateMapper massEmpSaleTemplateMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(massEmpSaleTemplateMapper);
	}

	@Override
	public List<MassEmpSaleTemplate> selectByEmp(String empId, String title) {
		return massEmpSaleTemplateMapper.selectByEmp(empId,title);
	}

	@Override
	public int updateByEmp(MassEmpSaleTemplate massEmpSaleTemplate) {
		return massEmpSaleTemplateMapper.updateByEmp(massEmpSaleTemplate);
	}

}
