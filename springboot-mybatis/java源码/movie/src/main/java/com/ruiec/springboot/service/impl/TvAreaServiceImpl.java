package com.ruiec.springboot.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.springboot.dao.TvAreaMapper;
import com.ruiec.springboot.service.TvAreaService;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 电视剧服务实现类
 * @author qinzhitian<br>
 * @date 2017年11月16日 上午11:28:56
 */
@Service("tvAreaServiceImpl")
public class TvAreaServiceImpl extends BaseServiceImpl implements TvAreaService {

	private TvAreaMapper tvAreaMapper;
	
	@Resource
	public void setTvAreaMapper(TvAreaMapper tvAreaMapper) {
		this.baseMapper = this.tvAreaMapper = tvAreaMapper;
	}
	
	/**
	 * 查询所有电视剧类型
	 * @author qinzhitian<br>
	 * @date 2017年11月28日 下午9:14:26
	 */
	@Override
	public ResponseDTO selectAll() {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> map = tvAreaMapper.selectAll();
		doReturnResult(responseDTO, map, Locale.SIMPLIFIED_CHINESE);
		return responseDTO;
	}
	
	/**
     * 插入记录（选择字段）
     * @author qinzhitian<br>
     * @date 2017年11月15日 下午8:45:20
     */
	@Override
	public ResponseDTO add(Map<String, Object> obj) {
		ResponseDTO responseDTO = new ResponseDTO();
		//验证地域是否已存在
		Integer count = this.tvAreaMapper.selectByName(obj);
		if (count > 0) {
			responseDTO.setCode(4001);
			responseDTO.setMessage("地域已存在！");
			responseDTO.setSuccess(false);
			return responseDTO;
		}
		int code = baseMapper.insert(obj);
		doReturnResult(responseDTO, code, Locale.SIMPLIFIED_CHINESE);
		return responseDTO;
	}
	
	/**
     * 更新记录（选择字段）
     * @author qinzhitian<br>
     * @date 2017年11月15日 下午8:46:12
     */
	@Override
	public ResponseDTO update(Map<String, Object> obj) {
		ResponseDTO responseDTO = new ResponseDTO();
		// 验证地域是否已存在
		Integer count  = this.tvAreaMapper.selectByName(obj);
		if (count > 0) {
			responseDTO.setCode(4001);
			responseDTO.setMessage("地域已存在！");
			responseDTO.setSuccess(false);
			return responseDTO;
		}
		int code = baseMapper.update(obj);
		doReturnResult(responseDTO, code, Locale.SIMPLIFIED_CHINESE);
		return responseDTO;
	}
}
