package com.ruiec.springboot.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.springboot.dao.TvResourcesMapper;
import com.ruiec.springboot.service.TvResourcesService;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 电视剧服务实现类
 * @author qinzhitian<br>
 * @date 2017年11月16日 上午11:28:56
 */
@Service("tvResourcesServiceImpl")
public class TvResourcesServiceImpl extends BaseServiceImpl implements TvResourcesService {

	private TvResourcesMapper tvResourcesMapper;
	
	@Resource
	public void setTvResourcesMapper(TvResourcesMapper tvResourcesMapper) {
		this.baseMapper = this.tvResourcesMapper = tvResourcesMapper;
	}
	
	/**
	 * 查询所有电视剧类型
	 * @author qinzhitian<br>
	 * @date 2017年11月28日 下午9:14:26
	 */
	@Override
	public ResponseDTO selectAll() {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> map = tvResourcesMapper.selectAll();
		doReturnResult(responseDTO, map, Locale.SIMPLIFIED_CHINESE);
		return responseDTO;
	}
}
