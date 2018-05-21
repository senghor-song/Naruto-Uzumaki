package com.ruiec.springboot.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.springboot.dao.TvMapper;
import com.ruiec.springboot.service.TvService;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 电视剧服务实现类
 * @author qinzhitian<br>
 * @date 2017年11月16日 上午11:28:56
 */
@Service("tvServiceImpl")
public class TvServiceImpl extends BaseServiceImpl implements TvService {

	private TvMapper tvMapper;
	
	@Resource
	public void setTvMapper(TvMapper tvMapper) {
		this.baseMapper = this.tvMapper = tvMapper;
	}
	
	/**
	 * 查询所有电视剧类型
	 * @author qinzhitian<br>
	 * @date 2017年11月28日 下午9:14:26
	 */
	@Override
	public ResponseDTO selectAll() {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> map = tvMapper.selectAll();
		doReturnResult(responseDTO, map, Locale.SIMPLIFIED_CHINESE);
		return responseDTO;
	}
}
