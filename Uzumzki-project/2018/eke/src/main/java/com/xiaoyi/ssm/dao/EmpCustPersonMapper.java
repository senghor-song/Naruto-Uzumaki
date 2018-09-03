package com.xiaoyi.ssm.dao;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.EmpCustPerson;

public interface EmpCustPersonMapper extends BaseMapper<EmpCustPerson, String> {
	
	int delEmpCustPerson(@Param("houseId") String houseId, @Param("empId") String empId);
	
}