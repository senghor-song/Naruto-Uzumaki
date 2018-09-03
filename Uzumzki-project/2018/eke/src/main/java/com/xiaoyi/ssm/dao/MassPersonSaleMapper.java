package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.dto.MassPersonDto;
import com.xiaoyi.ssm.model.MassPersonSale;

/**  
 * @Description: 个人出售数据访问接口
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:21:47 
 */
public interface MassPersonSaleMapper extends BaseMapper<MassPersonSale, String>{
	
	/**  
	 * @Description: 条件查询个人出售数据
	 * @author 宋高俊  
	 * @date 2018年7月4日 下午7:24:30 
	 */ 
	List<MassPersonSale> selectAll(MassPersonDto massPersonDto);
	

}