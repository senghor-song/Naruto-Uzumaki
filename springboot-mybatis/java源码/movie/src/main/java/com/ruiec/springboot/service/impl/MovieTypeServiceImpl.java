package com.ruiec.springboot.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.springboot.dao.MovieTypeMapper;
import com.ruiec.springboot.service.MovieResourcesService;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 电影类型管理服务接口实现类
 * 
 * @author 陈靖原<br>
 * @date 2017年11月21日 上午11:19:00
 */
@Service("MovieTypeServiceImpl")
public class MovieTypeServiceImpl extends BaseServiceImpl implements MovieResourcesService {
	private Locale locale = Locale.SIMPLIFIED_CHINESE;

	private MovieTypeMapper movieTypeMapper;

	@Resource
	public void setMovieTypeMapper(MovieTypeMapper movieTypeMapper) {
		this.baseMapper = this.movieTypeMapper = movieTypeMapper;
	}
	/**
	 * 条件分页查询
	 * @author 陈靖原<br>
	 * @date 2017年11月30日 下午10:19:40
	 */
	@Override
	public ResponseDTO selectByPage(Map<String, Object> map) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> obj = movieTypeMapper.selectByPage(map);
		doReturnResult(responseDTO, obj, locale);
		return responseDTO;
	}
	
	/**
	 * 查询所有电影类型
	 * @author 陈靖原<br>
	 * @date 2017年11月30日 下午10:19:40
	 */
	@Override
	public ResponseDTO selectAll() {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> map = movieTypeMapper.selectAll();
		doReturnResult(responseDTO, map, locale);
		return responseDTO;
	}
}
