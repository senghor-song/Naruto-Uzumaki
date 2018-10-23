package com.xiaoyi.ssm.quartz;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.model.TrainOrderComment;
import com.xiaoyi.ssm.model.TrainTeam;
import com.xiaoyi.ssm.service.TrainOrderCommentService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.SpringUtils;

@Service
public class CheckTrainOrderCommentJob {

	private static Logger logger = Logger.getLogger(CheckTrainOrderCommentJob.class.getName());

	/**
	 * 定时任务，每分钟执行检查是否有超过24小时的评论未评论任务
	 */
	// @Scheduled(cron = "1 0 0 1/1 * ? ")
	@Scheduled(cron = "0 0/1 * * * ? ")
	public void countOneDayAmount() {
		// logger.info("开始执行检查是否有超过24小时的评论未评论任务------------------->");

		TrainOrderCommentService trainOrderCommentService = SpringUtils.getBean("trainOrderCommentServiceImpl", TrainOrderCommentService.class);
		List<TrainOrderComment> list = trainOrderCommentService.selectByTimeOut(DateUtil.getPreTime(new Date(), 3, -1));
		for (int i = 0; i < list.size(); i++) {
			TrainOrderComment trainOrderComment = list.get(i);
			trainOrderComment.setState(2);
			trainOrderComment.setCommentSelect(1);
			trainOrderComment.setContent("第" + trainOrderComment.getClassHour() + "课时默认好评");
			trainOrderComment.setModifyTime(new Date());
			trainOrderCommentService.updateByPrimaryKeySelective(trainOrderComment);
		}

		// logger.info("结束执行检查是否有超过24小时的评论未评论任务------------------->");
	}
}
