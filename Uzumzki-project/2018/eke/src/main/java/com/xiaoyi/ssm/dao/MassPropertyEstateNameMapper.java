package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.MassPropertyEstateName;

/**  
 * @Description: 房源群发设置小区信息数据访问层
 * @author 宋高俊  
 * @date 2018年7月24日 下午4:12:02 
 */ 
public interface MassPropertyEstateNameMapper extends BaseMapper<MassPropertyEstateName, String>{
	
	/**  
	 * @Description: 根据房源ID获取网站发布对应小区信息
	 * @author 宋高俊  
	 * @date 2018年7月24日 下午4:16:01 
	 */ 
	List<MassPropertyEstateName> selectByPropertyId(String masspropertyid);
	
}