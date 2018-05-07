package com.ruiec.web.service;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.entity.OperationLog;
import com.ruiec.web.entity.User;
/**
 * 操作日志接口
 * @author yuankai
 * Date  2017-12-05
 * */
public interface OperationLogService extends BaseService<OperationLog, String>{
	 /**
	   * 插入操作日志<br>
	   * user对象<br>
	   * 操作类型 1.增2.删3.改4.查<br>
	   * 请求URL<br>
	   * 备注最多30字<br>
	   * 请求数据<br>
	   * @author yuankai
	   * @date 2017年12月6日 下午10:50:06
	   * */
	 public boolean insertOperationLogs(User user,Integer type,String url,String remark,String content);
}
