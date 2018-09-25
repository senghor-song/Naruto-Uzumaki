package com.ruiec.web.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.web.entity.Task;
import com.ruiec.web.service.TaskDetailsService;
import com.ruiec.web.service.TaskService;

/**
 * 一天检查一次是否有时间到期的合成研判的定时任务
 * @author Senghor<br>
 * @date 2018年1月6日 下午3:39:24
 */
@Component
@EnableScheduling
public class DayTaskJob {
	
	private static final Logger logger = LoggerFactory.getLogger(DayTaskJob.class);

	@Resource
	private TaskService taskService;
	@Resource
	private TaskDetailsService taskDetailsService;
	/**
	 * 一天检查一次是否有时间到期的合成研判的定时任务
	 * @author Senghor<br>
	 * @date 2018年1月6日 下午3:38:08
	 */
//	@Scheduled(cron = "0 0 0 1/1 * ? ")//一天
// 	@Scheduled(cron = "0/1 * * * * ? ")//一秒
 	@Scheduled(cron = "0 0 0/1 * * ? ")//一小时
	private void dayTask() {
		logger.info("-------------一天检查一次是否有时间到期的合成研判的定时任务开始---------------------------");
		List<Task> tasks = new ArrayList<Task>();
		try {
			List<Filter> filters=new ArrayList<Filter>();
			filters.add(Filter.ne("status", "3"));//筛选未结束的任务
			filters.add(Filter.le("handleTime", new Date()));//筛选处置时间在当前时间之前的任务
			tasks = taskService.findList(null, filters, null);
			for (int i = 0; i < tasks.size(); i++) {
				Task task = tasks.get(i);
				task.setStatus("3");
				task.setOverReason("任务时长已到期限,自动结束!");
				taskService.updateInclude(task, new String[]{"status"}, null);//修改为已结束
			}
		} catch (Exception e) {
			logger.error("结束任务异常："+e);
		}
		logger.info("发现了" + tasks.size() + "个合成研判任务已到期,自动结束"+ tasks.size() + "个合成研判任务!");
		logger.info("-------------一天检查一次是否有时间到期的合成研判的定时任务结束---------------------------");
	}
}
