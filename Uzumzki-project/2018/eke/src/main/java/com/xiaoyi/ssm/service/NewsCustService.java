package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.dto.AdminDto;
import com.xiaoyi.ssm.model.NewsCust;

/**  
 * @Description: 新闻业务逻辑接口
 * @author 宋高俊  
 * @date 2018年7月31日 下午5:13:08 
 */ 
public interface NewsCustService extends BaseService<NewsCust, String> {

	/**  
	 * @Description: 条件查询新闻
	 * @author 宋高俊  
	 * @date 2018年8月10日 下午2:40:20 
	 */ 
	List<NewsCust> selectBySearch(AdminDto adminDto);
	
}
