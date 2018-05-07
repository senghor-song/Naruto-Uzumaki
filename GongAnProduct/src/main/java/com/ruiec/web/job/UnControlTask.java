package com.ruiec.web.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.web.entity.Monitor;
import com.ruiec.web.service.MonitorService;


/**
 * 十分钟检查一次是否有时间到期的布控的定时任务
 * @author 陈靖原<br>
 * @date 2018年1月11日 下午1:38:42
 */
@Component
@EnableScheduling
public class UnControlTask {
	
	private static final Logger logger = LoggerFactory.getLogger(UnControlTask.class);
	
	@Resource
	private MonitorService monitorService;
	
	/**
	 * 十分钟检查一次是否有时间到期的布控的定时任务
	 * @author 陈靖原<br>
	 * @throws ParseException 
	 * @date 2018年1月11日 下午1:38:42
	 */
	@Scheduled(cron = "0 0/10 * * * ? ")
	private void unControlTask() throws ParseException {
		logger.info("-------------十分钟检查一次是否有时间到期的布控的定时任务开始---------------------------");
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("isRevoke", 0));
		filters.add(Filter.eq("status", 1));
		List<Monitor> monitors = monitorService.findList(null, filters, null);
		List<Integer> list = new ArrayList<Integer>();
		int count = 0;
		for (int i = 0; i < monitors.size(); i++) {
			Monitor monitor = (Monitor) monitors.get(i);
			// 审批时间
			String Atime = "";
			if (StringUtils.isNotBlank(monitor.getApprovalTime())) {
				String[] time = monitor.getApprovalTime().split(",");
				Atime = time[time.length - 1];
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
				Date beginDate = sdf.parse(Atime);
				Date nowDate = new Date();
				long day = (nowDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
				if (StringUtils.isBlank(monitor.getDuration())
						|| day > Long.valueOf(monitor.getDuration()).longValue() || day < 0) {
					// 过期
					monitor.setIsRevoke(1);
					list.add(monitor.getId());
					count++;
				} else {
					// 未过期
					monitor.setIsRevoke(0);
				}
			}
		}
		boolean flag = monitorService.updateIsExpired(list);
		if (flag && count != 0) {
			logger.info("-------------已撤控"+count+"人---------------------------");
		}else {
			logger.info("-------------当前暂无可撤控人员---------------------------");
		}
		logger.info("-------------十分钟检查一次是否有时间到期的布控的定时任务结束---------------------------");
	}
}
