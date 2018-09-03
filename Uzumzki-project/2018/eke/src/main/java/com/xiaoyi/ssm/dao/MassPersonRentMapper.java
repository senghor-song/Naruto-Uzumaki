package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.dto.MassPersonDto;
import com.xiaoyi.ssm.model.MassPersonRent;

/**  
 * @Description: 个人出租数据访问接口
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:21:47 
 */
public interface MassPersonRentMapper extends BaseMapper<MassPersonRent, String>{

	/**  
	 * @Description: 条件查询个人出租数据
	 * @author 宋高俊  
	 * @date 2018年7月4日 下午7:24:30 
	 */ 
	List<MassPersonRent> selectAll(MassPersonDto massPersonDto);
}