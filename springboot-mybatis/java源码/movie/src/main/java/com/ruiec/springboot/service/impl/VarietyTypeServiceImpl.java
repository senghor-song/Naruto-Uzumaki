package com.ruiec.springboot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ruiec.springboot.dao.VarietyTypeMapper;
import com.ruiec.springboot.service.VarietyTypeService;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 综艺类型接口服务实现类
 * @author Senghor<br>
 * @date 2017年11月20日 上午11:16:35
 */
@Service("varietyTypeServiceImpl")
public class VarietyTypeServiceImpl extends BaseServiceImpl implements VarietyTypeService {

	private Locale locale = Locale.SIMPLIFIED_CHINESE;
	
	private VarietyTypeMapper varietyTypeMapper;
	
	@Resource
	public void setVarietyTypeMapper(VarietyTypeMapper varietyTypeMapper) {
		this.baseMapper = this.varietyTypeMapper = varietyTypeMapper;
	}
	
	/**
	 * 根据条件查询列表
	 * @author Senghor<br>
	 * @date 2017年11月26日 下午5:05:34
	 */
	@Override
	public ResponseDTO selectByPage(Map<String, Object> map) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> obj = varietyTypeMapper.selectByPage(map);
		doReturnResult(responseDTO, obj, locale);
		return responseDTO;
	}
	/**
	 * 修改综艺资源信息
	 * @author Senghor<br>
	 * @date 2017年11月26日 下午5:05:34
	 */
	@Override
	public ResponseDTO update(HttpServletRequest request) {
		ResponseDTO responseDTO = new ResponseDTO();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", request.getParameter("id"));
		map.put("typeName", request.getParameter("typeName"));
		map.put("sort", request.getParameter("sort"));
		int count = varietyTypeMapper.update(map);
		doReturnResult(responseDTO, count, locale);
		return responseDTO;
	}
}
