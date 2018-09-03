package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.dto.MassPersonDto;
import com.xiaoyi.ssm.model.MassPersonRentWant;

/**  
 * @Description: 个人求租数据访问接口
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:21:47 
 */
public interface MassPersonRentWantMapper extends BaseMapper<MassPersonRentWant, String>{

	/**  
	 * @Description: 条件查询个人求租数据
	 * @author 宋高俊  
	 * @date 2018年7月4日 下午7:24:30 
	 */ 
	List<MassPersonRentWant> selectAll(MassPersonDto massPersonDto);
	
}