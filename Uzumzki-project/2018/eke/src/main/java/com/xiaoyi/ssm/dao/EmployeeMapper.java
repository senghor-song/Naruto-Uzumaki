package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.dto.AdminEmpLoyeeDto;
import com.xiaoyi.ssm.dto.EmployeeDto;
import com.xiaoyi.ssm.model.Employee;

/**  
 * @Description: 经纪人数据访问接口
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:21:47 
 */ 
public interface EmployeeMapper extends BaseMapper<Employee, String>{
	/**  
	 * @Description: 登录
	 * @author 宋高俊  
	 * @date 2018年6月25日 下午7:22:10 
	 */ 
	Employee login(Employee employee);

	/**  
	 * @Description: 修改密码
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午2:33:30 
	 */ 
	int updatePassword(EmployeeDto employeeDto);

	/**  
	 * @Description: 查询手机号是否存在
	 * @author 宋高俊  
	 * @date 2018年7月9日 上午11:41:22 
	 */ 
	Employee getPhoneRegister(@Param("phone")String phone);

	/**  
	 * @Description: 根据手机号修改密码
	 * @author 宋高俊  
	 * @date 2018年7月9日 下午2:57:09 
	 */ 
	int updatePhonePassword(Employee employee);
	
	/**  
	 * @Description: 根据商户查询下属经纪人
	 * @author 宋高俊  
	 * @date 2018年8月9日 上午9:58:05 
	 */ 
	List<Employee> selectByStore(String id);
	
	/**  
	 * @Description: 条件筛选经纪人
	 * @author 宋高俊  
	 * @date 2018年8月9日 下午2:35:41 
	 */ 
	List<Employee> selectBySearch(AdminEmpLoyeeDto adminEmpLoyeeDto);
}