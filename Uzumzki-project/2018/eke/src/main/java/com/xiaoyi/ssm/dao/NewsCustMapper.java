package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.dto.AdminDto;
import com.xiaoyi.ssm.model.NewsCust;

public interface NewsCustMapper extends BaseMapper<NewsCust, String>{

	/**  
	 * @Description: 条件查询新闻
	 * @author 宋高俊  
	 * @date 2018年8月10日 下午2:40:20 
	 */ 
	List<NewsCust> selectBySearch(AdminDto adminDto);
}