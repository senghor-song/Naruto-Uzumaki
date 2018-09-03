package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.dto.AdminEmpLoyeeDto;
import com.xiaoyi.ssm.dto.EmployeeDto;
import com.xiaoyi.ssm.model.EmpStore;
import com.xiaoyi.ssm.model.Employee;

/**  
 * @Description: 经纪人业务逻辑接口
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:15:42 
 */ 
public interface EmployeeService extends BaseService<Employee, String> {
	/**  
	 * @Description: 登录方法
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午2:30:09 
	 */ 
	Employee login(Employee employee);

	/**  
	 * @Description: 根据ID修改密码
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午2:30:02 
	 */ 
	int updatePassword(EmployeeDto employeeDto);

	/**  
	 * @Description: 查询手机号是否存在
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午2:30:02 
	 */ 
	Employee getPhoneRegister(String phone);
	
	/**  
	 * @Description: 根据手机号修改密码
	 * @author 宋高俊  
	 * @date 2018年7月9日 下午2:56:15 
	 */ 
	int  updatePhonePassword(Employee employee);
	
	/**  
	 * @Description: 根据城市查询商户数据
	 * @author 宋高俊  
	 * @date 2018年7月17日 下午3:05:01 
	 */ 
	List<EmpStore> selectByCity(String city, String content);

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
