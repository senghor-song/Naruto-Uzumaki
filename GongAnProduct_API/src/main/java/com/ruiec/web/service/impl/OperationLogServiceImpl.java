package com.ruiec.web.service.impl;

import java.net.InetAddress;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.OperationLog;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.OperationLogService;

/**
 * 操作日志服务实现类
 * 
 * @author yuankai Date 217-12-05
 * */
@Service("operationLogServiceImpl")
public class OperationLogServiceImpl extends BaseServiceImpl<OperationLog, String> implements OperationLogService {

	/**
	 * 插入操作日志<br>
	 * user对象<br>
	 * 操作类型 1.增2.删3.改4.查<br>
	 * 请求URL<br>
	 * 备注最多30字<br>
	 * 请求数据<br>
	 * 
	 * @author yuankai date 2017-12-22
	 * @param policeName
	 * */
	@Override
	@Transactional
	public boolean insertOperationLogs(User user, Integer type, String url, String remark, String content) {

		try {
			OperationLog operationLog = new OperationLog();

			if (user.getPoliceName() != null) {
				operationLog.setUserId(user.getPoliceName());
				operationLog.setType(type);
				operationLog.setUrl(url);
				operationLog.setRemark(remark);
				if (null != content && !"".equals(content)) {
					content = content.trim().replace("<", "《").replace(">", "》").replace("'", "‘").replace("\"", "”");
				}
				if (content != null && !"".equals(content) && content.length() > 0) {
					operationLog.setContent(content);
				} else {
					operationLog.setContent("无");
				}
				Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
				operationLog.setUnitName(unit.getUnitName());
				operationLog.setIp(InetAddress.getLocalHost().getHostAddress());// ip地址
				super.save(operationLog);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
