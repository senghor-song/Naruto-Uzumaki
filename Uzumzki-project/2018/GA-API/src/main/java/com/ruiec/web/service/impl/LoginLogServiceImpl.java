package com.ruiec.web.service.impl;

import org.springframework.stereotype.Service;

import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.entity.LoginLog;
import com.ruiec.web.service.LoginLogService;

/**
 * 登录日志服务实现
 * 
 * @author qinzhitian<br>
 * @date 2018年1月4日 上午11:22:09
 */
@Service("loginLogServiceImpl")
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLog, Integer> implements LoginLogService {

}
