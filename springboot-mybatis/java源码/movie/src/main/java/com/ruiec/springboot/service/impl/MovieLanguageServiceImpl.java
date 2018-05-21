package com.ruiec.springboot.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.springboot.dao.MovieLanguageMapper;
import com.ruiec.springboot.service.MovieLanguageService;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 电影语言管理服务接口
 * 
 * @author 陈靖原<br>
 * @date 2017年11月21日 上午11:18:00
 */
@Service("MovieLanguageServiceImpl")
public class MovieLanguageServiceImpl extends BaseServiceImpl implements MovieLanguageService {

	private Locale locale = Locale.SIMPLIFIED_CHINESE;
	private MovieLanguageMapper movieLanguageMapper;

	@Resource
	public void setMovieLanguageMapper(MovieLanguageMapper movieLanguageMapper) {
		this.baseMapper = this.movieLanguageMapper = movieLanguageMapper;
	}
	/**
	 * 条件分页查询
	 * @author 陈靖原<br>
	 * @date 2017年11月30日 下午10:19:40
	 */
	@Override
	public ResponseDTO selectByPage(Map<String, Object> map) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> obj = movieLanguageMapper.selectByPage(map);
		doReturnResult(responseDTO, obj, locale);
		return responseDTO;
	}
	
	/**
	 * 查询所有电影语言
	 * @author 陈靖原<br>
	 * @date 2017年11月30日 下午10:19:40
	 */
	@Override
	public ResponseDTO selectAll() {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> map = movieLanguageMapper.selectAll();
		doReturnResult(responseDTO, map, locale);
		return responseDTO;
	}
}
