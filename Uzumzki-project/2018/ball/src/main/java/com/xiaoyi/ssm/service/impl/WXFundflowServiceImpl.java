package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.WXFundflowMapper;
import com.xiaoyi.ssm.dto.WXFundflowDto;
import com.xiaoyi.ssm.model.WXFundflow;
import com.xiaoyi.ssm.service.WXFundflowService;

@Service("wxFundflowServiceImpl")
public class WXFundflowServiceImpl extends AbstractService<WXFundflow,String> implements WXFundflowService{

	@Autowired
	private WXFundflowMapper wxFundflowMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(wxFundflowMapper);
	}
	
	/**
	 * @Description: 统计基本户资金账单数据
	 * @author 宋高俊
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 * @date 2018年11月29日下午7:19:27
	 */

	@Override
	public WXFundflowDto countWXFundflowBasic(String dateStart, String dateEnd) {
		return wxFundflowMapper.countWXFundflowBasic(dateStart, dateEnd);
	}

	/**
	 * @Description: 统计运营户资金账单数据
	 * @author 宋高俊
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 * @date 2018年11月29日下午7:19:27
	 */
	@Override
	public WXFundflowDto countWXFundflowOperation(String dateStart, String dateEnd) {
		return wxFundflowMapper.countWXFundflowOperation(dateStart, dateEnd);
	}

	/**
	 * @Description: 根据账户类型查询资金账单数据
	 * @author 宋高俊
	 * @param accountType
	 * @return
	 * @date 2018年11月30日上午10:58:33
	 */
	@Override
	public List<WXFundflow> selectByAccountType(Integer accountType) {
		return wxFundflowMapper.selectByAccountType(accountType);
	}

	/**
	 * @Description: 根据日期查询是否有对账数据
	 * @author 宋高俊
	 * @param dateStr
	 * @return
	 * @date 2018年12月6日下午7:25:12
	 */
	@Override
	public WXFundflow selectByDate(String dateStart, String dateEnd) {
		return wxFundflowMapper.selectByDate(dateStart, dateEnd);
	}
	
	/**
	 * @Description:  根据时间删除数据
	 * @author 宋高俊
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 * @date 2018年12月7日上午11:13:25
	 */
	@Override
	public int deleteByDate(String dateStart, String dateEnd) {
		return wxFundflowMapper.deleteByDate(dateStart, dateEnd);
	}
	
	/**
	 * @Description: 根据日期统计数量
	 * @author 宋高俊
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 * @date 2018年12月8日下午2:42:28
	 */
	@Override
	public Integer countByDateFlag(String dateStart, String dateEnd) {
		return wxFundflowMapper.countByDateFlag(dateStart, dateEnd);
	}

}
