package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.MassNotice;

/**  
 * @Description: 公告数据访问接口
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:21:47 
 */ 
public interface MassNoticeMapper extends BaseMapper<MassNotice, String>{
	
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
	List<MassNotice> selectByEmpLIst(@Param("empId") String empId,@Param("noticeType") String noticeType);
}