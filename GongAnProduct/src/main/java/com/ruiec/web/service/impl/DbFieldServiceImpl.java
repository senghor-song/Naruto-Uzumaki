package com.ruiec.web.service.impl;

import org.springframework.stereotype.Service;

import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.entity.DbField;
import com.ruiec.web.service.DbFieldService;

/**
 * 数据导入字段服务实现类
 * @author qinzhitian<br>
 * @date 2017年12月18日 下午4:49:03
 */
@Service("dbFieldServiceImpl")
public class DbFieldServiceImpl extends BaseServiceImpl<DbField, Integer> implements DbFieldService {

}
