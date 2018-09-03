package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.dto.AdminStoreDto;
import com.xiaoyi.ssm.model.EmpStore;

/**  
 * @Description: 商户业务逻辑
 * @author 宋高俊  
 * @date 2018年7月30日 下午3:45:45 
 */ 
public interface EmpStoreService extends BaseService<EmpStore, String> {

	/**  
	 * @Description: 根据商户ID统计下属经纪人在售房源
	 * @author 宋高俊  
	 * @date 2018年7月31日 上午11:43:33 
	 */ 
	Integer countPropertyByStoreId(String id);

	/**  
	 * @Description: 根据商户ID统计下属经纪人
	 * @author 宋高俊  
	 * @date 2018年7月31日 下午2:23:39 
	 */ 
	Integer countEmpByStoreId(String id);
	
	/**  
	 * @Description: 根据商户统计平均积分
	 * @author 宋高俊  
	 * @date 2018年7月31日 下午2:45:50 
	 */ 
	Double avgStoreByStoreId(String id);

	/**  
	 * @Description: 根据状态查询商户
	 * @author 宋高俊  
	 * @date 2018年7月31日 下午3:09:57 
	 */ 
	List<EmpStore> selectByType(String type);

	/**  
	 * @Description: 根据条件筛选数据
	 * @author 宋高俊  
	 * @date 2018年8月8日 下午7:55:54 
	 */ 
	List<EmpStore> selectBySearch(AdminStoreDto adminStore);
}
