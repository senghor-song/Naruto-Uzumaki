package com.ruiec.springboot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ruiec.springboot.dao.VarietyResourcesMapper;
import com.ruiec.springboot.service.VarietyResourcesService;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 综艺资源接口服务实现类
 * @author Senghor<br>
 * @date 2017年11月20日 上午11:16:35
 */
@Service("varietyResourcesServiceImpl")
public class VarietyResourcesServiceImpl extends BaseServiceImpl implements VarietyResourcesService {

	private Locale locale = Locale.SIMPLIFIED_CHINESE;
	
	private VarietyResourcesMapper varietyResourcesMapper;
	
	@Resource
	public void setVarietyResourcesMapper(VarietyResourcesMapper varietyResourcesMapper) {
		this.baseMapper = this.varietyResourcesMapper = varietyResourcesMapper;
	}
	
	/**
	 * 根据条件查询列表
	 * @author Senghor<br>
	 * @date 2017年11月26日 下午5:05:34
	 */
	@Override
	public ResponseDTO selectByPage(Map<String, Object> map) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> obj = varietyResourcesMapper.selectByPage(map);
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
		map.put("varietyId", Integer.valueOf(request.getParameter("varietyId")));
		map.put("programName", request.getParameter("programName"));
		map.put("clarity", request.getParameter("clarity"));
		map.put("size", Integer.valueOf(request.getParameter("size")));
		if ("0".equals(request.getParameter("downlodMode"))) {
			map.put("downlodMode", false);
		}else {
			map.put("downlodMode", true);
		}
		map.put("downlodLink", request.getParameter("downlodLink"));
		map.put("createOn", System.currentTimeMillis());
		int count = varietyResourcesMapper.update(map);
		doReturnResult(responseDTO, count, locale);
		return responseDTO;
	}
}
