package com.xiaoyi.ssm.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.MassNoticeEmp;

/**  
 * @Description: 公告发送记录数据访问接口
 * @author 宋高俊  
 * @date 2018年6月26日 下午7:07:36 
 */ 
public interface MassNoticeEmpMapper extends BaseMapper<MassNoticeEmp, String>{
	
	/**  
	 * @Description: 根据公告ID和经纪人ID检查是否已给改经纪人发送过
	 * @author 宋高俊  
	 * @date 2018年6月29日 下午3:16:59 
	 */ 
	List<MassNoticeEmp> selectByEmpAndNotices(@Param("noticeid")String noticeid, @Param("empid")String empid);
	
	/**  
	 * @Description: 根据公告ID和经纪人ID获取公告发送记录
	 * @author 宋高俊  
	 * @date 2018年6月29日 下午3:17:39 
	 */ 
	MassNoticeEmp selectByEmpAndNotice(@Param("noticeid")String noticeid, @Param("empid")String empid);
	
	/**  
	 * @Description: 根据公告ID和经纪人ID修改公告发送记录为已读
	 * @author 宋高俊  
	 * @date 2018年6月29日 下午3:18:30 
	 */ 
	int updateByEmpAndNotice(@Param("noticeid")String noticeid, @Param("empid")String empid);

	/**  
	 * @Description: 根据公告统计分发次数
	 * @author 宋高俊  
	 * @date 2018年7月30日 上午9:49:12 
	 */ 
	Integer countEmpByNoice(String massNoticeID);
	
	/**  
	 * @Description: 根据公告统计已读次数
	 * @author 宋高俊  
	 * @date 2018年7月30日 上午9:49:12 
	 */ 
	Integer countEmpByNoiceRead(String massNoticeID);
}