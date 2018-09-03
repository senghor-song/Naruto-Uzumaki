package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.MassPropertyTitleLib;

/**  
 * @Description: 标题模板数据访问层
 * @author 宋高俊  
 * @date 2018年7月23日 下午3:30:03 
 */ 
public interface MassPropertyTitleLibMapper extends BaseMapper<MassPropertyTitleLib, String>{

	/**  
	 * @Description: 根据小区和房源类型获取标题模板
	 * @author 宋高俊  
	 * @date 2018年7月23日 下午3:27:01 
	 */ 
	List<MassPropertyTitleLib> selectByEstateTypeAll(@Param("estateid")String estateid, @Param("type")int type);
	
}