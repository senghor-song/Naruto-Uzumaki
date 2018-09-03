package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.dto.MassPersonDto;
import com.xiaoyi.ssm.model.MassPersonBuyWant;

/**  
 * @Description: 个人求购数据访问接口
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:21:47 
 */
public interface MassPersonBuyWantMapper extends BaseMapper<MassPersonBuyWant, String>{

	/**  
	 * @Description: 条件查询个人求购数据
	 * @author 宋高俊  
	 * @date 2018年7月4日 下午7:24:30 
	 */ 
	List<MassPersonBuyWant> selectAll(MassPersonDto massPersonDto);
}