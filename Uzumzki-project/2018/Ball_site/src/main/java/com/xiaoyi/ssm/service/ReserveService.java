package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.dto.FieldTemplateDto;
import com.xiaoyi.ssm.model.Reserve;

/**  
 * @Description: 预约业务逻辑层
 * @author 宋高俊  
 * @date 2018年8月16日 下午8:37:32 
 */ 
public interface ReserveService extends BaseService<Reserve, String> {

	/**  
	 * @Description: 获取当天所有已预约成功的时段数据
	 * @author 宋高俊  
	 * @date 2018年8月17日 下午8:49:33 
	 */ 
	List<Reserve> selectByFieldTemplateDto(FieldTemplateDto fieldTemplateDto);

	/**  
	 * @Description: 根据订单ID查询预约数据
	 * @author 宋高俊  
	 * @param orderid
	 * @return 
	 * @date 2018年11月2日 上午11:55:54 
	 */ 
	List<Reserve> selectByOrder(String orderid);

}
