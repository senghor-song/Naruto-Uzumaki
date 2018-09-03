package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.CombineMapper;
import com.xiaoyi.ssm.model.Combine;
import com.xiaoyi.ssm.service.CombineService;

/**  
 * @Description: 散拼业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月17日 上午10:43:11 
 */ 
@Service
public class CombineServiceImpl extends AbstractService<Combine,String> implements CombineService{

	@Autowired
	private CombineMapper combineMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(combineMapper);
	}

	/**  
	 * @Description: 根据场馆ID查询总收入
	 * @author 宋高俊  
	 * @date 2018年8月23日 下午7:41:52 
	 */
	@Override
	public Double countAmountByVenue(String venueid) {
		return combineMapper.countAmountByVenue(venueid);
	}

	/**  
	 * @Description: 根据场馆ID查询记录
	 * @author 宋高俊  
	 * @date 2018年8月24日 上午9:54:19 
	 */ 
	@Override
	public List<Combine> selectByVenue(String venueid) {
		return combineMapper.selectByVenue(venueid);
	}
	
	/**  
	 * @Description: 根据提现ID获取散拼数据
	 * @author 宋高俊  
	 * @date 2018年8月31日 上午10:58:51 
	 */ 
	@Override
	public List<Combine> selectByAmount(String amountid) {
		return combineMapper.selectByAmount(amountid);
	}

}
