package com.xiaoyi.ssm.quartz;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.wxPay.WXPayUtil;

@Service
public class EverydayGetBillJob {
	
	private static Logger logger = Logger.getLogger(EverydayGetBillJob.class.getName());

	/**
	 * 每天10:01开始获取微信账单数据
	 */
//	@Scheduled(cron = "0 1 10 * * ? ")
//	@Scheduled(cron = "0/10 * * * * ? ")
	public void oneHourGetJob() {
		String datestr = DateUtil.getFormat(DateUtil.getPreTime(new Date(), 3, -1), "yyyyMMdd");
		logger.info("开始执行昨天的账单查询");
		WXPayUtil.downloadfundflow(datestr, "Basic");
		WXPayUtil.downloadfundflow(datestr, "Operation");
		WXPayUtil.downloadbill(datestr);
		logger.info("结束执行昨天的账单查询");
	}
}
