package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.WXBillMapper;
import com.xiaoyi.ssm.dto.WXBillDto;
import com.xiaoyi.ssm.model.WXBill;
import com.xiaoyi.ssm.service.WXBillService;

@Service("wxBillServiceImpl")
public class WXBillServiceImpl extends AbstractService<WXBill,String> implements WXBillService{

	@Autowired
	private WXBillMapper wxBillMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(wxBillMapper);
	}

	/**
	 * @Description: 获取当日订场数据
	 * @author 宋高俊
	 * @param dateStr
	 * @return
	 * @date 2018年12月4日上午11:07:44
	 */
	@Override
	public List<WXBillDto> selectByNowDate(String dateStr) {
		return wxBillMapper.selectByNowDate(dateStr);
	}

	/**
	 * @Description: 统计当天实际消费金额
	 * @author 宋高俊
	 * @param dateStr
	 * @return
	 * @date 2018年12月5日下午4:09:22
	 */
	@Override
	public Double countByDate(String dateStr) {
		return wxBillMapper.countByDate(dateStr);
	}

	/**
	 * @Description: 根据日期删除
	 * @author 宋高俊
	 * @param datestr
	 * @date 2018年12月7日上午10:53:57
	 */
	@Override
	public int deleteByDate(String dateStart, String dateEnd) {
		return wxBillMapper.deleteByDate(dateStart, dateEnd);
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
		return wxBillMapper.countByDateFlag(dateStart, dateEnd);
	}

}
