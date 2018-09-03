package com.xiaoyi.ssm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.dto.EstateImageDto;
import com.xiaoyi.ssm.model.HouseTypeImageEmp;

/**  
 * @Description: 房型图数据访问接口
 * @author 宋高俊  
 * @date 2018年7月11日 上午11:05:02 
 */ 
public interface HouseTypeImageEmpMapper extends BaseMapper<HouseTypeImageEmp, String>{
	
	/**  
	 * @Description: 根据条件查询房型图片
	 * @author 宋高俊  
	 * @date 2018年7月10日 上午11:58:14 
	 */ 
	List<HouseTypeImageEmp> selectByEmpImage(EstateImageDto estateImageDto);

	/**  
	 * @Description: 根据经纪人查询已保存图片的小区
	 * @author 宋高俊  
	 * @date 2018年7月10日 下午12:06:12 
	 */ 
	List<EstateImageDto> selectByEmpEstate(String empid);

	/**
	 * @Description: 根据经纪人和房源ID获取图片
	 * @author 宋高俊
	 * @date 2018年7月10日 下午12:06:12
	 */
	List<Map<String, Object>> selectByImageUrl(@Param("empid")String empid, @Param("houseid")String houseid);
}