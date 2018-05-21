package com.ruiec.springboot.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.springboot.dao.MovieResourcesMapper;
import com.ruiec.springboot.service.MovieResourcesService;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 电影下载资源管理服务接口实现类
 * 
 * @author 陈靖原<br>
 * @date 2017年11月21日 上午11:18:33
 */
@Service("MovieResourcesServiceImpl")
public class MovieResourcesServiceImpl extends BaseServiceImpl implements MovieResourcesService {
	private Locale locale = Locale.SIMPLIFIED_CHINESE;

	private MovieResourcesMapper movieResourcesMapper;

	@Resource
	public void setMovieResourcesMapper(MovieResourcesMapper movieResourcesMapper) {
		this.baseMapper = this.movieResourcesMapper = movieResourcesMapper;
	}
	/**
	 * 条件分页查询
	 * @author 陈靖原<br>
	 * @date 2017年11月30日 下午10:19:40
	 */
	@Override
	public ResponseDTO selectByPage(Map<String, Object> map) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> obj = movieResourcesMapper.selectByPage(map);
		doReturnResult(responseDTO, obj, locale);
		return responseDTO;
	}
	
	/**
	 * 查询所有电影资源
	 * @author 陈靖原<br>
	 * @date 2017年11月30日 下午10:19:40
	 */
	@Override
	public ResponseDTO selectAll() {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> map = movieResourcesMapper.selectAll();
		doReturnResult(responseDTO, map, locale);
		return responseDTO;
	}
}
