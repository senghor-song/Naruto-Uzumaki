package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.dto.WXFundflowDto;
import com.xiaoyi.ssm.model.WXFundflow;

/**
 * @Description: 
 * @author 宋高俊
 * @date 2018年11月29日下午7:19:23
 */
public interface WXFundflowService extends BaseService<WXFundflow, String> {

	/**
	 * @Description: 统计基本户资金账单数据
	 * @author 宋高俊
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 * @date 2018年11月29日下午7:19:27
	 */
	WXFundflowDto countWXFundflowBasic(String dateStart, String dateEnd);

	/**
	 * @Description: 统计运营户资金账单数据
	 * @author 宋高俊
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 * @date 2018年11月29日下午7:19:27
	 */
	WXFundflowDto countWXFundflowOperation(String dateStart, String dateEnd);

	/**
	 * @Description: 根据账户类型查询资金账单数据
	 * @author 宋高俊
	 * @param accountType
	 * @return
	 * @date 2018年11月30日上午10:58:33
	 */
	List<WXFundflow> selectByAccountType(Integer accountType);

	/**
	 * @Description: 根据日期查询是否有对账数据
	 * @author 宋高俊
	 * @param dateStr
	 * @param dateEnd 
	 * @return
	 * @date 2018年12月6日下午7:25:12
	 */
	WXFundflow selectByDate(String dateStart, String dateEnd);

	/**
	 * @Description:  根据时间删除数据
	 * @author 宋高俊
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 * @date 2018年12月7日上午11:13:25
	 */
	int deleteByDate(String dateStart, String dateEnd);

	/**
	 * @Description: 根据日期统计数量
	 * @author 宋高俊
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 * @date 2018年12月8日下午2:42:28
	 */
	Integer countByDateFlag(String dateStart, String dateEnd);

}
