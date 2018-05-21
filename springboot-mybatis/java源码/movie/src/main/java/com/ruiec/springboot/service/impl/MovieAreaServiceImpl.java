package com.ruiec.springboot.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.springboot.dao.MovieAreaMapper;
import com.ruiec.springboot.service.MovieAreaService;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 电影地区接口服务实现类
 * 
 * @author 陈靖原<br>
 * @date 2017年11月21日 上午11:20:27
 */
@Service("movieAreaServiceImpl")
public class MovieAreaServiceImpl extends BaseServiceImpl implements MovieAreaService {
	private Locale locale = Locale.SIMPLIFIED_CHINESE;
	private MovieAreaMapper movieAreaMapper;

	@Resource
	public void setMovieAreaMapper(MovieAreaMapper movieAreaMapper) {
		this.baseMapper = this.movieAreaMapper = movieAreaMapper;
	}
	
	/**
	 * 条件分页查询
	 * @author 陈靖原<br>
	 * @date 2017年11月30日 下午10:19:40
	 */
	@Override
	public ResponseDTO selectByPage(Map<String, Object> map) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> obj = movieAreaMapper.selectByPage(map);
		doReturnResult(responseDTO, obj, locale);
		return responseDTO;
	}
	
	/**
	 * 查询所有电影地区
	 * @author 陈靖原<br>
	 * @date 2017年11月30日 下午10:19:40
	 */
	@Override
	public ResponseDTO selectAll() {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> map = movieAreaMapper.selectAll();
		doReturnResult(responseDTO, map, locale);
		return responseDTO;
	}
}
