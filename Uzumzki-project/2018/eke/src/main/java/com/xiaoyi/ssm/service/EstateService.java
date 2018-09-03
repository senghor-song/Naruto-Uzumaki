package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.Estate;
/**  
 * @Description: 小区业务逻辑接口
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:13:35 
 */ 
public interface EstateService extends BaseService<Estate, String> {
	
	/**  
	 * @Description: 获取小区所属商户数
	 * @author 宋高俊  
	 * @date 2018年7月26日 下午6:16:54 
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
	 * @date 2018年7月26日 下午6:17:49 
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
