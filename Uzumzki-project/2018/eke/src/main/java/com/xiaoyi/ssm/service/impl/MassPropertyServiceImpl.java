package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MassHouseMapper;
import com.xiaoyi.ssm.dao.MassPropertyMapper;
import com.xiaoyi.ssm.dto.HouseEnterDto;
import com.xiaoyi.ssm.dto.MassPropertyDto;
import com.xiaoyi.ssm.model.MassHouse;
import com.xiaoyi.ssm.model.MassProperty;
import com.xiaoyi.ssm.service.MassPropertyService;
import com.xiaoyi.ssm.util.Utils;

/**  
 * @Description: 房源录入业务逻辑实现
 * @author 宋高俊  
 * @date 2018年6月28日 上午10:41:15 
 */ 
@Service
public class MassPropertyServiceImpl extends AbstractService<MassProperty,String> implements MassPropertyService{

	@Autowired
	private MassPropertyMapper massPropertyMapper;
	@Autowired
	private MassHouseMapper massHouseMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(massPropertyMapper);
	}

	/**  
	 * @Description: 根据经纪人ID查询房源录入信息
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午12:02:00 
	 */ 
	@Override
	public MassPropertyDto selectByEmp(MassPropertyDto massPropertyDto) {
		return massPropertyMapper.selectByEmp(massPropertyDto);
	}
	
	/**  
	 * @Description: 新增住宅信息
	 * @author 宋高俊  
	 * @date 2018年7月2日 下午1:40:06 
	 */ 
	@Override
	public int insertHouse(MassHouse massHouse, HouseEnterDto houseEnterDto) {
		massHouse.setAdvequip(Utils.getString(houseEnterDto.getAdvequipList()));
		massHouse.setBasicequip(Utils.getString(houseEnterDto.getBasicequipList()));
		return massHouseMapper.insertSelective(massHouse);
	}
	
	/**  
	 * @Description: 修改住宅信息
	 * @author 宋高俊  
	 * @date 2018年7月2日 下午1:40:06 
	 */ 
	@Override
	public int updateHouse(MassHouse massHouse, HouseEnterDto houseEnterDto) {
		massHouse.setAdvequip(Utils.getString(houseEnterDto.getAdvequipList()));
		massHouse.setBasicequip(Utils.getString(houseEnterDto.getBasicequipList()));
		return massHouseMapper.updateByPrimaryKeySelective(massHouse);
	}
	
	/**  
	 * @Description: 获取经纪人的房库
	 * @author 宋高俊  
	 * @date 2018年7月3日 下午12:57:24 
	 */ 
	@Override
	public List<MassProperty> selectByEmpAll(HouseEnterDto houseEnterDto) {
		return massPropertyMapper.selectByEmpAll(houseEnterDto);
	}

	/**  
	 * @Description: 统计经纪人的房库
	 * @author 宋高俊  
	 * @date 2018年7月3日 下午12:57:24 
	 */ 
	@Override
	public MassPropertyDto selectCountSum(MassPropertyDto massPropertyDto) {
		return massPropertyMapper.selectCountSum(massPropertyDto);
	}

	/**  
	 * @Description: 删除所有回收站中的房源
	 * @author 宋高俊  
	 * @date 2018年7月4日 上午10:11:18 
	 */ 
	@Override
	public int deleteAllRecycle(String postType) {
		return massPropertyMapper.deleteAllRecycle(postType);
	}

}
