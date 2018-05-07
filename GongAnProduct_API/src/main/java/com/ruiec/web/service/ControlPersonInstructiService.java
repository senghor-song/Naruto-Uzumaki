package com.ruiec.web.service;

import java.util.Map;

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.entity.ControlPersonInstructi;

/**
 * 重点人预警指令服务接口
 * @author Senghor<br>
 * @date 2017年12月1日 上午9:14:58
 */
public interface ControlPersonInstructiService extends BaseService<ControlPersonInstructi, Integer>{
	
	/**
	 * 预警签收记录api
	 * @author qinzhitian<br>
	 * @date 2018年1月9日 下午2:30:04
	 * @param alarmId 预警信息id
	 */
	public Map<String,Object> instructionRecord(Integer alarmId, Page page);
}
