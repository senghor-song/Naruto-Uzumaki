package com.ruiec.springboot.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.springboot.dao.VarietyMapper;
import com.ruiec.springboot.service.VarietyService;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 综艺接口服务实现类
 * @author Senghor<br>
 * @date 2017年11月20日 上午11:16:35
 */
@Service("varietyServiceImpl")
public class VarietyServiceImpl extends BaseServiceImpl implements VarietyService {

	private Locale locale = Locale.SIMPLIFIED_CHINESE;
	
	private VarietyMapper varietyMapper;
	
	@Resource
	public void setVarietyMapper(VarietyMapper varietyMapper) {
		this.baseMapper = this.varietyMapper = varietyMapper;
	}
	
	/**
	 * 根据条件查询列表
	 * @author Senghor<br>
	 * @date 2017年11月26日 下午5:05:34
	 */
	@Override
	public ResponseDTO selectByPage(Map<String, Object> map) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> obj = varietyMapper.selectByPage(map);
		doReturnResult(responseDTO, obj, locale);
		return responseDTO;
	}
	
}
