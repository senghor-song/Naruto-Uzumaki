package com.xiaoyi.ssm.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.OperationLogMapper;
import com.xiaoyi.ssm.model.OperationLog;
import com.xiaoyi.ssm.service.OperationLogService;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 操作日志业务逻辑实现层
 * @author 宋高俊
 * @date 2018年11月7日 上午11:36:32
 */
@Service
public class OperationLogServiceImpl extends AbstractService<OperationLog,String> implements OperationLogService{

	@Autowired
	private OperationLogMapper operationLogMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(operationLogMapper);
	}
	
	/**  
	 * @Description: 记录日志数据
	 * @author 宋高俊  
	 * @param staffId
	 * @param content
	 * @param ip
	 * @return 
	 * @date 2018年11月7日 上午11:37:47 
	 */ 
	@Override
	public int saveLog(String staffId, String content, String ip) {
		OperationLog operationLog = new OperationLog();
		operationLog.setId(Utils.getUUID());
		operationLog.setCreateTime(new Date());
		operationLog.setStaffId(staffId);
		operationLog.setContent(content);
		operationLog.setIp(ip);
		return operationLogMapper.insertSelective(operationLog);
	}
}
