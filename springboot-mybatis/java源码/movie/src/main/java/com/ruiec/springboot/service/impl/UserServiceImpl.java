package com.ruiec.springboot.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.springboot.dao.UserMapper;
import com.ruiec.springboot.service.UserService;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 电视剧服务实现类
 * @author qinzhitian<br>
 * @date 2017年11月16日 上午11:28:56
 */
@Service("userServiceImpl")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	private Locale locale = Locale.SIMPLIFIED_CHINESE;
	private UserMapper userMapper;
	
	@Resource
	public void setUserMapper(UserMapper userMapper) {
		this.baseMapper = this.userMapper = userMapper;
	}
	
	/**
	 * 登录
	 * @author Senghor<br>
	 * @date 2017年12月26日 下午4:59:06
	 */
	@Override
	public ResponseDTO login(Map<String, Object> map) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> maps = userMapper.login(map);
		doReturnResult(responseDTO, maps, locale);
		return responseDTO;
	}
}
