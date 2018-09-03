package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MassEmpImgMapper;
import com.xiaoyi.ssm.model.MassEmpImg;
import com.xiaoyi.ssm.service.MassEmpImgService;

/**  
 * @Description: 推房图库业务逻辑接口
 * @author 宋高俊  
 * @date 2018年7月30日 下午4:07:17 
 */ 
@Service
public class MassEmpImgServiceImpl extends AbstractService<MassEmpImg,String> implements MassEmpImgService{

	@Autowired
	private MassEmpImgMapper massEmpImgMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(massEmpImgMapper);
	}

	@Override
	public Integer countEmpImgByEmp(String empId) {
		return massEmpImgMapper.countEmpImgByEmp(empId);
	}

}
