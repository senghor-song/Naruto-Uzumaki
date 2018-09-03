package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.NewsCustMapper;
import com.xiaoyi.ssm.dto.AdminDto;
import com.xiaoyi.ssm.model.NewsCust;
import com.xiaoyi.ssm.service.NewsCustService;

/**  
 * @Description: 新闻业务逻辑实现
 * @author 宋高俊  
 * @date 2018年6月26日 下午7:11:07 
 */ 
@Service
public class NewsCustServiceImpl extends AbstractService<NewsCust,String> implements NewsCustService{

	@Autowired
	private NewsCustMapper newsCustMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(newsCustMapper);
	}
	
	/**  
	 * @Description: 条件查询新闻
	 * @author 宋高俊  
	 * @date 2018年8月10日 下午2:40:20 
	 */
	@Override
	public List<NewsCust> selectBySearch(AdminDto adminDto) {
		return newsCustMapper.selectBySearch(adminDto);
	} 
}
