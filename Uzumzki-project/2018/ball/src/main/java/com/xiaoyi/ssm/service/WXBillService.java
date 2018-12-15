package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.dto.WXBillDto;
import com.xiaoyi.ssm.model.WXBill;

public interface WXBillService extends BaseService<WXBill, String> {

	/**
	 * @Description: 获取当日订场数据
	 * @author 宋高俊
	 * @param dateStr
	 * @return
	 * @date 2018年12月4日上午11:07:44
	 */
	List<WXBillDto> selectByNowDate(String dateStr);

	/**
	 * @Description: 统计当天实际消费金额
	 * @author 宋高俊
	 * @param dateStr
	 * @return
	 * @date 2018年12月5日下午4:09:22
	 */
	Double countByDate(String dateStr);

	/**
	 * @Description: 根据日期删除
	 * @author 宋高俊
	 * @param datestr
	 * @param dateEnd 
	 * @date 2018年12月7日上午10:53:57
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
