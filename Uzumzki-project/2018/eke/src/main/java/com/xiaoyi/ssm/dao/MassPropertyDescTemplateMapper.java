package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.MassPropertyDescTemplate;

/**  
 * @Description: 描述模板数据访问层
 * @author 宋高俊  
 * @date 2018年7月24日 上午9:56:33 
 */ 
public interface MassPropertyDescTemplateMapper extends BaseMapper<MassPropertyDescTemplate, String> {
	
	/**  
	 * @Description: 根据小区和房源类型获取描述模板
	 * @author 宋高俊  
	 * @date 2018年7月23日 下午3:27:01 
	 */ 
	List<MassPropertyDescTemplate> selectByEstateTypeAll(@Param("type")int type);
}