package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.EmpStoreMapper;
import com.xiaoyi.ssm.dao.EmployeeMapper;
import com.xiaoyi.ssm.dto.AdminEmpLoyeeDto;
import com.xiaoyi.ssm.dto.EmployeeDto;
import com.xiaoyi.ssm.model.EmpStore;
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.service.EmployeeService;

/**  
 * @Description: 经纪人业务逻辑实现
 * @author 宋高俊  
 * @date 2018年6月28日 下午2:32:54 
 */ 
@Service
public class EmployeeServiceImpl extends AbstractService<Employee,String> implements EmployeeService{

	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private EmpStoreMapper empStoreMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(employeeMapper);
	}
	
	/**  
	 * @Description: 登录方法
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午2:30:09 
	 */ 
	@Override
	public Employee login(Employee employeeFrom) {
		return employeeMapper.login(employeeFrom);
	}

	/**  
	 * @Description: 修改密码
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午2:30:02 
	 */ 
	@Override
	public int updatePassword(EmployeeDto employeeDto) {
		return employeeMapper.updatePassword(employeeDto);
	}
	
	/**  
	 * @Description: 查询手机号是否存在
	 * @author 宋高俊  
	 * @date 2018年7月9日 上午11:41:22 
	 */ 
	@Override
	public Employee getPhoneRegister(String phone) {
		return employeeMapper.getPhoneRegister(phone);
	}

	/**  
	 * @Description: 根据手机号修改密码
	 * @author 宋高俊  
	 * @date 2018年7月9日 下午2:56:15 
	 */ 
	@Override
	public int updatePhonePassword(Employee employee) {
		return employeeMapper.updatePhonePassword(employee);
	}

	/**  
	 * @Description: 根据城市查询商户数据
	 * @author 宋高俊  
	 * @date 2018年7月17日 下午3:05:01 
	 */ 
	@Override
	public List<EmpStore> selectByCity(String city, String content) {
		return empStoreMapper.selectByCity(city, content);
	}

	/**  
	 * @Description: 根据商户查询下属经纪人
	 * @author 宋高俊  
	 * @date 2018年8月9日 上午9:59:25 
	 */ 
	@Override
	public List<Employee> selectByStore(String id) {
		return employeeMapper.selectByStore(id);
	}

	/**  
	 * @Description: 条件筛选经纪人
	 * @author 宋高俊  
	 * @date 2018年8月9日 下午2:35:41 
	 */ 
	@Override
	public List<Employee> selectBySearch(AdminEmpLoyeeDto adminEmpLoyeeDto) {
		return employeeMapper.selectBySearch(adminEmpLoyeeDto);
	}

}
