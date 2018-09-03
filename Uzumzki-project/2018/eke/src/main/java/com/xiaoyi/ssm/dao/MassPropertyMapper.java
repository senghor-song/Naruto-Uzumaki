package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.dto.HouseEnterDto;
import com.xiaoyi.ssm.dto.MassPropertyDto;
import com.xiaoyi.ssm.model.MassHouse;
import com.xiaoyi.ssm.model.MassProperty;

/**  
 * @Description: 
 * @author 宋高俊  
 * @date 2018年8月7日 下午5:55:20 
 */ 
/**  
 * @Description: 
 * @author 宋高俊  
 * @date 2018年8月7日 下午5:55:24 
 */ 
public interface MassPropertyMapper extends BaseMapper<MassProperty, String>{
	/**  
	 * @Description: 根据经纪人ID查询房源录入信息
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午12:02:00 
	 */ 
	public MassPropertyDto selectByEmp(MassPropertyDto massPropertyDto);
	
	/**  
	 * @Description: 新增住宅信息
	 * @author 宋高俊  
	 * @date 2018年7月2日 下午1:40:06 
	 */ 
	public int insertHouse(MassHouse massHouse, HouseEnterDto houseEnterDto);
	
	/**  
	 * @Description: 修改住宅信息
	 * @author 宋高俊  
	 * @date 2018年7月2日 下午1:40:06 
	 */ 
	public int updateHouse(MassHouse massHouse, HouseEnterDto houseEnterDto);
	
	/**  
	 * @Description: 获取经纪人的房库
	 * @author 宋高俊  
	 * @date 2018年7月3日 下午12:57:24 
	 */ 
	public List<MassProperty> selectByEmpAll(HouseEnterDto houseEnterDto);

	/**  
	 * @Description: 统计经纪人的房库
	 * @author 宋高俊  
	 * @date 2018年7月3日 下午12:57:24 
	 */ 
	public MassPropertyDto selectCountSum(MassPropertyDto massPropertyDto);

	/**  
	 * @Description: 删除所有回收站中的房源
	 * @author 宋高俊  
	 * @date 2018年7月4日 上午10:11:18 
	 */ 
	public int deleteAllRecycle(String postType);

}