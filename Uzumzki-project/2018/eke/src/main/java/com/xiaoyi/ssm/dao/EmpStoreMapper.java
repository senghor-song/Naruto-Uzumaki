package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.dto.AdminStoreDto;
import com.xiaoyi.ssm.model.EmpStore;

/**  
 * @Description: 商户数据访问接口
 * @author 宋高俊  
 * @date 2018年7月17日 下午3:10:59 
 */ 
public interface EmpStoreMapper extends BaseMapper<EmpStore, String> {

	/**  
	 * @Description: 根据城市和公司名查询
	 * @author 宋高俊
	 * @date 2018年7月17日 下午3:11:14 
	 */ 
	List<EmpStore> selectByCity(@Param("city")String city, @Param("content")String content);
	
	/**  
	 * @Description: 根据商户ID统计下属经纪人在售房源
	 * @author 宋高俊  
	 * @date 2018年7月31日 上午11:43:33 
	 */ 
	Integer countPropertyByStoreId(String id);
	
	/**  
	 * @Description: 根据商户ID统计下属经纪人在售房源
	 * @author 宋高俊  
	 * @date 2018年7月31日 上午11:43:33 
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
	List<EmpStore> selectByType(@Param("type")String type);
	
	/**  
	 * @Description: 根据条件筛选数据
	 * @author 宋高俊  
	 * @date 2018年8月8日 下午7:55:54 
	 */ 
	List<EmpStore> selectBySearch(AdminStoreDto adminStore);
}