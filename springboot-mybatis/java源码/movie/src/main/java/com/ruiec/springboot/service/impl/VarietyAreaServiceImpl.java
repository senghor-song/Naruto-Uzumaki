package com.ruiec.springboot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ruiec.springboot.dao.VarietyAreaMapper;
import com.ruiec.springboot.service.VarietyAreaService;
import com.ruiec.springboot.util.IdWorker;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 综艺地区接口服务实现类
 * @author Senghor<br>
 * @date 2017年11月20日 上午11:16:35
 */
@Service("varietyAreaServiceImpl")
public class VarietyAreaServiceImpl extends BaseServiceImpl implements VarietyAreaService {

	private Locale locale = Locale.SIMPLIFIED_CHINESE;
	private VarietyAreaMapper varietyAreaMapper;
	
	@Resource
	public void setVarietyAreaMapper(VarietyAreaMapper varietyAreaMapper) {
		this.baseMapper = this.varietyAreaMapper = varietyAreaMapper;
	}
	/**
	 * 根据条件查询列表
	 * @author Senghor<br>
	 * @date 2017年11月26日 下午5:05:34
	 */
	@Override
	public ResponseDTO selectByPage(Map<String, Object> map) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> obj = varietyAreaMapper.selectByPage(map);
		doReturnResult(responseDTO, obj, locale);
		return responseDTO;
	}
	
	/**
	 * 添加综艺地区信息
	 * @author Senghor<br>
	 * @date 2017年11月26日 下午5:05:34
	 */
	@Override
	public ResponseDTO add(HttpServletRequest request) {
		ResponseDTO responseDTO = new ResponseDTO();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("areaName", request.getParameter("areaName"));
		map.put("sort", request.getParameter("sort"));
		map.put("createOn", System.currentTimeMillis());
		int count = varietyAreaMapper.insert(map);
		doReturnResult(responseDTO, count, locale);
		return responseDTO;
	}
	
	/**
	 * 修改综艺地区信息
	 * @author Senghor<br>
	 * @date 2017年11月26日 下午5:05:34
	 */
	@Override
	public ResponseDTO update(HttpServletRequest request) {
		ResponseDTO responseDTO = new ResponseDTO();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", request.getParameter("id"));
		map.put("areaName", request.getParameter("areaName"));
		map.put("sort", request.getParameter("sort"));
		map.put("createOn", System.currentTimeMillis());
		int count = varietyAreaMapper.update(map);
		doReturnResult(responseDTO, count, locale);
		return responseDTO;
	}
}
