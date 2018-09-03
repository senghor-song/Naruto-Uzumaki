package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.MassPropertyDescTemplate;
import com.xiaoyi.ssm.model.MassPropertyTitleLib;

/**  
 * @Description: 用于获取标题和描述模板业务逻辑接口
 * @author 宋高俊  
 * @date 2018年7月23日 下午3:25:42 
 */ 
public interface MassPropertyTemplateService extends BaseService<MassPropertyTitleLib, String>{
	
	/**  
	 * @Description: 根据小区和房源类型获取标题模板
	 * @author 宋高俊  
	 * @date 2018年7月23日 下午3:27:01 
	 */ 
	List<MassPropertyTitleLib> selectByEstateTypeTitle(String estateid, int type);
	
	/**  
	 * @Description: 根据小区和房源类型获取标题模板
	 * @author 宋高俊  
	 * @date 2018年7月23日 下午3:27:01 
	 */ 
	List<MassPropertyDescTemplate> selectByEstateTypeDesc(int type);
	
}
