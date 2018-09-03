package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.dto.HouseEnterDto;
import com.xiaoyi.ssm.model.Estate;

/**  
 * @Description: 小区数据访问接口
 * @author 宋高俊  
 * @date 2018年7月10日 上午9:37:44 
 */ 
public interface EstateMapper extends BaseMapper<Estate, String>{
	
	/**  
	 * @Description: 模糊查询小区信息
	 * @author 宋高俊  
	 * @date 2018年7月10日 上午9:37:56 
	 */ 
	List<Estate> selectByName(HouseEnterDto houseEnterDto);

	/**  
	 * @Description: 根据小区查询所属商户数
	 * @author 宋高俊  
	 * @date 2018年7月26日 下午6:17:49 
	 */ 
	Integer selectByStoreCount(String estateid);

	/**  
	 * @Description: 根据小区查询仓库图数量
	 * @author 宋高俊  
	 * @date 2018年7月26日 下午6:17:49 
	 */ 
	Integer selectByImageStorageCount(String estateid);

	/**  
	 * @Description: 根据小区查询展示图数量
	 * @author 宋高俊  
	 * @date 2018年7月26日 下午6:55:40 
	 */ 
	Integer selectByEstateImageCount(String estateid);
	
	/**  
	 * @Description: 根据小区名查询
	 * @author 宋高俊  
	 * @date 2018年8月13日 下午3:56:24 
	 */ 
	Estate selectByEstateName(String name);
	
	/**  
	 * @Description: 根据片区ID查询小区
	 * @author 宋高俊  
	 * @date 2018年8月15日 下午2:37:29 
	 */ 
	List<Estate> selectByArea(String id);
}