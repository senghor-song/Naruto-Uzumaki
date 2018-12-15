package com.xiaoyi.ssm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.dto.FieldTemplateDto;
import com.xiaoyi.ssm.model.VenueLock;

public interface VenueLockMapper extends BaseMapper<VenueLock, String> {

	/**
	 * @Description: 根据管理员id查询不重复内容
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年9月1日 下午4:49:33
	 */
	List<String> selectContentByManager(String id);

	/**
	 * @Description: 根据日期和场地ID查询占用时段
	 * @author 宋高俊
	 * @param fieldTemplateDto
	 * @return
	 * @date 2018年9月4日 下午8:54:30
	 */
	List<VenueLock> selectByNowDate(FieldTemplateDto fieldTemplateDto);

	/**
	 * @Description: 根据选择时段判断该时段是否被占用
	 * @author 宋高俊
	 * @date 2018年8月20日 下午3:02:24
	 */
	Integer selectIsVenueLock(@Param("id") String id, @Param("time") String idtime, @Param("date") Date date);

	/**
	 * @Description: 场地锁定表逻辑删除
	 * @author 宋高俊
	 * @param venueid
	 * @param dateStr
	 * @return
	 * @date 2018年11月21日 下午8:49:33
	 */
	int updateByVenue(@Param("venueid")String venueid, @Param("dateStr")String dateStr);
}