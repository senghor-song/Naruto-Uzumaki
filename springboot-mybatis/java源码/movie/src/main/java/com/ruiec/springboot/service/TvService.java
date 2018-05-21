package com.ruiec.springboot.service;

import com.ruiec.springboot.util.ResponseDTO;

/**
 * 电视剧管理业务逻辑接口类
 * @author qinzhitian<br>
 * @date 2017年11月15日 下午8:26:39
 */
public interface TvService extends BaseService {

	/**
	 * 查询所有电视剧类型
	 * @author qinzhitian<br>
	 * @date 2017年11月28日 下午9:14:26
	 */
	ResponseDTO selectAll();
}
