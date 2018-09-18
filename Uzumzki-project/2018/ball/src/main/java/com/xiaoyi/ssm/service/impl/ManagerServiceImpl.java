package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.ManagerMapper;
import com.xiaoyi.ssm.model.Manager;
import com.xiaoyi.ssm.service.ManagerService;

/**  
 * @Description: 管理员业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月18日 下午2:00:29 
 */ 
@Service
public class ManagerServiceImpl extends AbstractService<Manager, String> implements ManagerService {

	@Autowired
	private ManagerMapper managerMapper;

	@Override
	public void setBaseMapper() {
		super.setBaseMapper(managerMapper);
	}
	
	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	@Override
	public Integer countByVenue(String id) {
		return managerMapper.countByVenue(id);
	}

	/**  
	 * @Description: 管理员登录接口
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午4:46:37 
	 */ 
//	@Override
//	public Manager login(Manager manager) {
//		return managerMapper.login(manager);
//	}

	/**  
	 * @Description: 根据场馆ID查询管理员
	 * @author 宋高俊  
	 * @date 2018年8月20日 下午2:22:29 
	 */ 
	@Override
	public List<Manager> selectByVenue(String venueid) {
		return managerMapper.selectByVenue(venueid);
	}

	/**  
	 * @Description: 管理登录方法
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午4:45:02 
	 */ 
	@Override
	public Manager login(String phone, String password) {
		return managerMapper.login(phone, password);
	}
	
	/**  
	 * @Description: 根据管理员和场馆查询该管理员是否管理该场馆
	 * @author 宋高俊  
	 * @date 2018年8月21日 上午9:22:50 
	 */ 
	@Override
	public Integer selectByManagerAndVenue(String managerid, String venueid) {
		return managerMapper.selectByManagerAndVenue(managerid, venueid);
	}

	/**  
	 * @Description: 根据bizno获取管理员数据
	 * @author 宋高俊  
	 * @param bizno
	 * @return 
	 * @date 2018年9月4日 上午11:09:36 
	 */ 
	@Override
	public Manager selectByBizno(String bizno) {
		return managerMapper.selectByBizno(bizno);
	}

	/**  
	 * @Description: 根据手机号查询管理员是否存在
	 * @author 宋高俊  
	 * @param phone
	 * @return 
	 * @date 2018年9月6日 下午4:28:19 
	 */ 
	@Override
	public Manager selectByPhone(String phone) {
		return managerMapper.selectByPhone(phone);
	}


}
