package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.BlackListEmp;

public interface BlackListEmpMapper extends BaseMapper<BlackListEmp, String> {
	
	/**
	 * @Description: 根据经纪人ID查询黑名单
	 * @author 宋高俊
	 * @date 2018年7月5日 下午7:50:44
	 */
	List<BlackListEmp> selectAll(String empId);

}