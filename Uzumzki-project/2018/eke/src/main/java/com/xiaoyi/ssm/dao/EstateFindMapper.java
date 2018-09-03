package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.EstateFind;

public interface EstateFindMapper extends BaseMapper<EstateFind, String>{

	/**  
	 * @Description: 增加小区未处理数量
	 * @author 宋高俊  
	 * @date 2018年7月31日 上午11:16:33 
	 */ 
	Integer countByNoDispose();
}