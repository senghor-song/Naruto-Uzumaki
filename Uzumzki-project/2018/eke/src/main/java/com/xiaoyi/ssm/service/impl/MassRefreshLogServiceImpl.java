package com.xiaoyi.ssm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MassRefreshLogMapper;
import com.xiaoyi.ssm.dto.MassRefreshDto;
import com.xiaoyi.ssm.model.MassRefreshLog;
import com.xiaoyi.ssm.service.MassRefreshLogService;
import com.xiaoyi.ssm.util.DateUtil;

/**  
 * @Description: 刷新日志业务逻辑实现类
 * @author 宋高俊  
 * @date 2018年7月4日 下午3:02:59 
 */ 
@Service
public class MassRefreshLogServiceImpl extends AbstractService<MassRefreshLog,String> implements MassRefreshLogService{

	@Autowired
	private MassRefreshLogMapper massRefreshLogMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(massRefreshLogMapper);
	}

	/**  
	 * @Description: 获取所有的刷新日志数据
	 * @author 宋高俊  
	 * @date 2018年7月4日 下午3:01:48 
	 */ 
	@Override
	public List<MassRefreshLog> selectAll(MassRefreshDto massRefreshDto) {
		if (massRefreshDto.getDate() != null) {
			Date newDate = new Date();
			if ("7".equals(massRefreshDto.getDate())) {
				Date startDate = DateUtil.getPreTime(newDate, 3, -7);
				massRefreshDto.setStartDate(DateUtil.getFormat(DateUtil.getWeeHours(startDate, 0),"yyyy-MM-dd HH:mm:ss"));
				massRefreshDto.setEndDate(DateUtil.getFormat(DateUtil.getWeeHours(newDate, 1),"yyyy-MM-dd HH:mm:ss"));
			}else {
				Date date = DateUtil.getPreTime(newDate, 3, -Integer.valueOf(massRefreshDto.getDate()));
				massRefreshDto.setStartDate(DateUtil.getFormat(DateUtil.getWeeHours(date, 0),"yyyy-MM-dd HH:mm:ss"));
				massRefreshDto.setEndDate(DateUtil.getFormat(DateUtil.getWeeHours(date, 1),"yyyy-MM-dd HH:mm:ss"));
			}
		}
		return massRefreshLogMapper.selectAll(massRefreshDto);
	}

}
