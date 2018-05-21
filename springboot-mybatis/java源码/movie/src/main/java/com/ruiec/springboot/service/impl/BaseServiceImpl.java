package com.ruiec.springboot.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.ruiec.springboot.dao.BaseMapper;
import com.ruiec.springboot.service.BaseService;
import com.ruiec.springboot.util.ResponseDTO;


/**
 * 基础服务实现类
 * @author qinzhitian<br>
 * @date 2017年11月16日 上午11:29:38
 */
public class BaseServiceImpl implements BaseService{

	@Autowired
	private MessageSource messageSource;
	
	protected BaseMapper baseMapper;
	
	private Locale locale = Locale.SIMPLIFIED_CHINESE;
	 
	/**
     * 插入记录（选择字段）
     * @author qinzhitian<br>
     * @date 2017年11月15日 下午8:45:20
     */
	@Override
	public ResponseDTO add(Map<String, Object> obj) {
		ResponseDTO responseDTO = new ResponseDTO();
		int code = baseMapper.insert(obj);
		doReturnResult(responseDTO, code, locale);
		return responseDTO;
	}
	
	/**
     * 根据ID删除记录
     * @author qinzhitian<br>
     * @date 2017年11月15日 下午8:45:35
     */
	@Override
	public ResponseDTO delete(String[] ids) {
		ResponseDTO responseDTO = new ResponseDTO();
		int code = baseMapper.delete(ids);
		doReturnResult(responseDTO, code, locale);
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
		int code = baseMapper.update(obj);
		doReturnResult(responseDTO, code, locale);
		return responseDTO;
	}

	/**
     * 根据ID获取记录
     * @author qinzhitian<br>
     * @date 2017年11月15日 下午8:46:17
     */
	@Override
	public ResponseDTO selectById(Long id) {
		ResponseDTO responseDTO = new ResponseDTO();
		Map<String, Object> obj = baseMapper.selectById(id);
		doReturnResult(responseDTO, obj, locale);
		return responseDTO;
	}
	
	/**
	 * 获取输出信息
	 * @author qinzhitian<br>
	 * @date 2017年11月16日 下午3:18:22
	 */
	protected String getMessage(int code, Locale locale) {
		String className = this.getClass().getName();
		String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
		String key = String.format("%s.%s.%s", className, methodName, code);
		return this.messageSource.getMessage(key, null, "", locale);
	}
	
	/**
	 * 返回结果1
	 * @author qinzhitian<br>
	 * @date 2017年11月16日 下午3:18:22
	 */
	protected void doReturnResult(ResponseDTO responseDTO, int code, Locale locale) {
		if (code == 1) {
			code = 200;
		}else if(code == 0){
			code = 4000;
		}
		String message = getMessage(code, locale);
		if(code==200){
			responseDTO.setSuccess(true);
		}else{
			responseDTO.setSuccess(false);
		}
		responseDTO.setCode(code);
		responseDTO.setMessage(message);
	}

	/**
	 * 返回结果2
	 * @author qinzhitian<br>
	 * @date 2017年11月16日 下午3:18:22
	 */
	protected <T> void doReturnResult(ResponseDTO responseDTO, T data, Locale locale) {
		int code = 4000;
		responseDTO.setSuccess(false);
		if (data != null) {
			code = 200;
			responseDTO.setSuccess(true);
			responseDTO.setData(data);
		}
		responseDTO.setMessage(getMessage(code, locale));
	}

	/**
	 * 查询所有数据
	 * @author qinzhitian<br>
	 * @date 2017年11月28日 下午9:14:26
	 */
	@Override
	public ResponseDTO selectAll() {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> map = baseMapper.selectAll();
		doReturnResult(responseDTO, map, locale);
		return responseDTO;
	}

	/**
     * 分页查询记录
     * @author qinzhitian<br>
     * @date 2017年11月15日 下午8:46:17
     */
	@Override
	public ResponseDTO selectPaged(Map<String, Object> obj) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Map<String, Object>> map = baseMapper.selectPaged(obj);
		doReturnResult(responseDTO, map, locale);
		return responseDTO;
	}

}
