package com.xiaoyi.ssm.quartz;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.model.InviteBall;
import com.xiaoyi.ssm.service.InviteBallService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.SpringUtils;

@Service
public class CheckBallTimeOutJob {

	private static Logger logger = LoggerFactory.getLogger(CheckBallTimeOutJob.class);

	/**
	 * 定时任务，每隔一小时获取一次微信的 access_token
	 */
	@Scheduled(cron = "0 0/10 * * * ? ")
	public void getWXAccess_token() {
		logger.info("开始查询过去20分钟中有无已到期的约球,start------------->");
		InviteBallService inviteBallService = SpringUtils.getBean("inviteBallServiceImpl", InviteBallService.class);
		Date date = new Date();
		Date startDate = DateUtil.getPreTime(date, 1, -20);// 开始时间，20分钟前
		Date endDate = date;// 结束时间，现在时间
		List<InviteBall> list = inviteBallService.selectByTimeOut(startDate, endDate);
		logger.info("查询到{}条已到期的约球", list.size());
		int flag = 0;
		for (InviteBall inviteBall : list) {
			inviteBall.setBallType(1);// 设置为到期截止
			inviteBallService.updateByPrimaryKeySelective(inviteBall);
		}
		logger.info("已处理{}条已到期的约球", flag);
		logger.info("开始查询过去20分钟中有无已到期的约球,end------------->");
	}
}
