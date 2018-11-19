package com.xiaoyi.ssm.quartz;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.model.TrainTeam;
import com.xiaoyi.ssm.service.TrainTeamService;
import com.xiaoyi.ssm.util.SpringUtils;

@Service
public class CountTrainTeamVenueJob {

	private static Logger logger = Logger.getLogger(CountTrainTeamVenueJob.class.getName());

	/**
	 * 定时任务，每天0:00:01执行统计金额任务
	 */
	 @Scheduled(cron = "1 0 0 1/1 * ? ")
//	@Scheduled(cron = "0 0/1 * * * ? ")
	public void countOneDayAmount() {
//		logger.info("开始执行统计培训机构拥有场馆数据------------------->");
		
		TrainTeamService trainTeamService = SpringUtils.getBean("trainTeamServiceImpl", TrainTeamService.class);
		List<TrainTeam> list = trainTeamService.selectByAll(null);
		for (int i = 0; i < list.size(); i++) {
			TrainTeam trainTeam = list.get(i);
			int venueNumber = trainTeamService.countByTeamId(trainTeam.getId());
			trainTeam.setVenueNumber(venueNumber);
			trainTeamService.updateByPrimaryKeySelective(trainTeam);
		}
		
//		logger.info("结束执行统计培训机构拥有场馆数据------------------->");
	}
}
