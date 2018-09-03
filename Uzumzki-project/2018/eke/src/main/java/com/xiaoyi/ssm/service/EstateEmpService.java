package com.xiaoyi.ssm.service;

import java.util.List;
import java.util.Map;

import com.xiaoyi.ssm.dto.EstateImageDto;
import com.xiaoyi.ssm.dto.HouseEnterDto;
import com.xiaoyi.ssm.model.Estate;
import com.xiaoyi.ssm.model.EstateImageEmp;
import com.xiaoyi.ssm.model.HouseTypeImageEmp;
import com.xiaoyi.ssm.model.PropertyImageEmp;

/**  
 * @Description: 小区业务逻辑接口
 * @author 宋高俊  
 * @date 2018年7月10日 上午9:26:48 
 */ 
public interface EstateEmpService extends BaseService<Estate, String> {

	/**  
	 * @Description: 模糊查询小区信息
	 * @author 宋高俊  
	 * @date 2018年7月10日 上午9:35:26 
	 */ 
	List<Estate> selectByName(HouseEnterDto houseEnterDto);

	/**  
	 * @Description: 根据条件查询室内图片
	 * @author 宋高俊  
	 * @date 2018年7月10日 上午11:58:14 
	 */ 
	List<PropertyImageEmp> selectByEmpPropertyImage(EstateImageDto estateImageDto);

	/**  
	 * @Description: 根据条件查询房型图片
	 * @author 宋高俊  
	 * @date 2018年7月10日 上午11:58:14 
	 */ 
	List<HouseTypeImageEmp> selectByEmpHouseTypeImage(EstateImageDto estateImageDto);
	
	/**  
	 * @Description: 根据条件查询小区图片
	 * @author 宋高俊  
	 * @date 2018年7月10日 上午11:58:14 
	 */ 
	List<EstateImageEmp> selectByEmpEstateImage(EstateImageDto estateImageDto);

	/**  
	 * @Description: 根据经纪人查询已保存图片的小区
	 * @author 宋高俊  
	 * @date 2018年7月10日 下午12:06:12 
	 */ 
	List<EstateImageDto> selectByEmpEstate(String empid,Integer type);
	
	/**  
	 * @Description: 批量删除小区图片
	 * @author 宋高俊  
	 * @date 2018年7月10日 下午5:16:49 
	 */ 
	int delImageManage(String[] ids,Integer type);
	
	/**  
	 * @Description: 根据经纪人和房源ID,房源类型获取图片
	 * @author 宋高俊  
	 * @date 2018年7月10日 下午12:06:12 
	 */ 
	List<Map<String, Object>> selectByImageUrl(String empid,String houseId, Integer type);
}
