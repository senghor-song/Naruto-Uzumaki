package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.dto.FieldTemplateDto;
import com.xiaoyi.ssm.model.VenueTeach;

public interface VenueTeachMapper extends BaseMapper<VenueTeach, String>{

	/**  
	 * @Description: 根据管理员id查询不重复内容
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月1日 下午4:49:33 
	 */ 
	List<String> selectContentByManager(String id);
	
	/**  
	 * @Description: 根据日期和场地ID查询占用时段
	 * @author 宋高俊  
	 * @param fieldTemplateDto 
	 * @return 
	 * @date 2018年9月4日 下午8:54:30 
	 */ 
	List<VenueTeach> selectByNowDate(FieldTemplateDto fieldTemplateDto);

	/**  
	 * @Description: 根据选择时段判断该时段是否被占用
	 * @author 宋高俊  
	 * @date 2018年8月20日 下午3:02:24 
	 */ 
	Integer selectIsVenueTeach(@Param("id")String id, @Param("time")String idtime);
}