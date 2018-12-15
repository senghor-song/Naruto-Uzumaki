package com.xiaoyi.ssm.quartz;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dto.FieldTemplateDto;
import com.xiaoyi.ssm.model.Field;
import com.xiaoyi.ssm.model.FieldTemplate;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.service.FieldService;
import com.xiaoyi.ssm.service.FieldTemplateService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.SpringUtils;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 每天补足三十天模板
 * @author 宋高俊
 * @date 2018年11月18日 上午11:06:22
 */
@Service
public class EverydayGenerateTemplateJob {

	private static Logger logger = Logger.getLogger(EverydayGenerateTemplateJob.class.getName());

	/**
	 * 定时任务，每天01:00补足三十天模板
	 */
	@Scheduled(cron = "1 0 1 1/1 * ? ")
//	@Scheduled(cron = "0/1 * * * * ? ")
	public void oneDayGenerateTemplateJob() {
		logger.info("开始执行补足三十天模板------------------->");
		
		VenueService venueService = SpringUtils.getBean("venueServiceImpl", VenueService.class);
		FieldService fieldService = SpringUtils.getBean("fieldServiceImpl", FieldService.class);
		FieldTemplateService fieldTemplateService = SpringUtils.getBean("fieldTemplateServiceImpl", FieldTemplateService.class);
		// 获取所有场馆
		List<Venue> list = venueService.selectByAll(null);
		for (int i = 0; i < list.size(); i++) {
			Venue venue = list.get(i);
			
			// 获取场馆下的场地
			List<Field> fields = fieldService.selectByVenue(venue.getId());
			for (int j = 0; j < fields.size(); j++) {
				Field field = fields.get(j);
				// 场地使用模板数据
				FieldTemplate fieldTemplate = new FieldTemplate();
				fieldTemplate.setCreatetime(new Date());
				fieldTemplate.setFieldid(field.getId());
				fieldTemplate.setTemplateid(field.getDefaultTemplate());
				fieldTemplate.setVenueid(venue.getId());
				
				// 新增三十天的模板
				Date nowDate = new Date();
				for (int k = 0; k < 7; k++) {
					
					// 判断当前日期是否已有数据
					FieldTemplateDto ftd = new FieldTemplateDto();
					ftd.setDate(DateUtil.getWeeHours(nowDate, 0));
					ftd.setFieldid(field.getId());
					ftd.setVenueid(venue.getId());
					FieldTemplate oldFieldTemplate = fieldTemplateService.selectByVenueAndField(ftd);
					if (oldFieldTemplate != null) {
						nowDate = DateUtil.getPreTime(nowDate, 3, 1);
						continue;
					}
					
					fieldTemplate.setId(Utils.getUUID());
					fieldTemplate.setFieldtime(nowDate);
					fieldTemplateService.insertSelective(fieldTemplate);

					nowDate = DateUtil.getPreTime(nowDate, 3, 1);
				}
			}
		}
		logger.info("结束执行补足三十天模板------------------->");
	}
}
