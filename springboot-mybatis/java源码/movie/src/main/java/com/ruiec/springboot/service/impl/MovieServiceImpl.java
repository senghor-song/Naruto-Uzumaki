package com.ruiec.springboot.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.springboot.dao.MovieMapper;
import com.ruiec.springboot.service.MovieService;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 电影管理服务接口实现类
 * 
 * @author 陈靖原<br>
 * @date 2017年11月21日 上午11:18:46
 */
@Service("MovieServiceImpl")
public class MovieServiceImpl extends BaseServiceImpl implements MovieService {
	private Locale locale = Locale.SIMPLIFIED_CHINESE;

	private MovieMapper movieMapper;

	@Resource
	public void setMovieMapperr(MovieMapper movieMapper) {
		this.baseMapper = this.movieMapper = movieMapper;
	}
	/**
	 * 条件分页查询
	 * @author 陈靖原<br>
	 * @date 2017年11月30日 下午10:19:40
	 */
	@Override
	public ResponseDTO selectByPage(Map<String, Object> map) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> obj = movieMapper.selectByPage(map);
		doReturnResult(responseDTO, obj, locale);
		return responseDTO;
	}
	
	/**
	 * 查询所有电影
	 * @author 陈靖原<br>
	 * @date 2017年11月30日 下午10:19:40
	 */
	@Override
	public ResponseDTO selectAll() {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> map = movieMapper.selectAll();
		doReturnResult(responseDTO, map, locale);
		return responseDTO;
	}
}
