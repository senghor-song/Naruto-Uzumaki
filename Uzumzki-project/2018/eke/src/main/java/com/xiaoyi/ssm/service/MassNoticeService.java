package com.xiaoyi.ssm.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.MassNotice;
import com.xiaoyi.ssm.model.MassNoticeEmp;

/**  
 * @Description: 公告业务逻辑接口
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:15:42 
 */ 
public interface MassNoticeService extends BaseService<MassNotice, String> {
	
	/**  
	 * @Description: 查询5条经纪人的公告
	 * @author 宋高俊  
	 * @date 2018年6月26日 下午7:18:02 
	 */ 
	List<MassNotice> selectByEmp5(String empId);
	
	/**  
	 * @Description: 根据经纪人和公告类型查询
	 * @author 宋高俊  
	 * @date 2018年6月27日 下午1:39:27 
	 */ 
	List<MassNotice> selectByEmpLIst(String empId,String noticeType);
	
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
}
