package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.ReserveMapper;
import com.xiaoyi.ssm.dto.FieldTemplateDto;
import com.xiaoyi.ssm.model.Reserve;
import com.xiaoyi.ssm.service.ReserveService;

/**  
 * @Description: 订场业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月17日 上午11:07:39 
 */ 
@Service
public class ReserveServiceImpl extends AbstractService<Reserve,String> implements ReserveService{

	@Autowired
	private ReserveMapper reserveMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(reserveMapper);
	}

	/**  
	 * @Description: 获取当天所有已预约成功的时段数据
	 * @author 宋高俊  
	 * @date 2018年8月17日 下午8:49:33 
	 */ 
	@Override
	public List<Reserve> selectByFieldTemplateDto(FieldTemplateDto fieldTemplateDto) {
		return reserveMapper.selectByFieldTemplateDto(fieldTemplateDto);
	}

}
