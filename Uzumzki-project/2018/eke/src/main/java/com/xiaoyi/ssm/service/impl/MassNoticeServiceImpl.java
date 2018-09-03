package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MassNoticeEmpMapper;
import com.xiaoyi.ssm.dao.MassNoticeMapper;
import com.xiaoyi.ssm.model.MassNotice;
import com.xiaoyi.ssm.model.MassNoticeEmp;
import com.xiaoyi.ssm.service.MassNoticeService;

/**  
 * @Description: 公告业务逻辑实现
 * @author 宋高俊  
 * @date 2018年6月26日 下午7:11:07 
 */ 
@Service
public class MassNoticeServiceImpl extends AbstractService<MassNotice,String> implements MassNoticeService{

	@Autowired
	private MassNoticeMapper massNoticeMapper;
	@Autowired
	private MassNoticeEmpMapper massNoticeEmpMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(massNoticeMapper);
	}
	
	/**  
	 * @Description: 查询5条经纪人的公告
	 * @author 宋高俊  
	 * @date 2018年6月26日 下午7:18:02 
	 */ 
	@Override
	public List<MassNotice> selectByEmp5(String empId) {
		return massNoticeMapper.selectByEmp5(empId);
	}

	/**  
	 * @Description: 根据经纪人和公告类型查询
	 * @author 宋高俊  
	 * @date 2018年6月27日 下午1:39:27 
	 */
	@Override
	public List<MassNotice> selectByEmpLIst(String empId, String noticeType) {
		return massNoticeMapper.selectByEmpLIst(empId, noticeType);
	}
	
	/**  
	 * @Description: 根据公告ID和经纪人ID检查是否已给改经纪人发送过
	 * @author 宋高俊  
	 * @date 2018年6月29日 下午3:16:59 
	 */ 
	@Override
	public List<MassNoticeEmp> selectByEmpAndNotices(String noticeid, String empid) {
		return massNoticeEmpMapper.selectByEmpAndNotices(noticeid, empid);
	}
	
	/**  
	 * @Description: 根据公告ID和经纪人ID获取公告发送记录
	 * @author 宋高俊  
	 * @date 2018年6月29日 下午3:17:39 
	 */ 
	@Override
	public MassNoticeEmp selectByEmpAndNotice(String noticeid, String empid) {
		return massNoticeEmpMapper.selectByEmpAndNotice(noticeid, empid);
	}

	/**  
	 * @Description: 根据公告ID和经纪人ID修改公告发送记录为已读
	 * @author 宋高俊  
	 * @date 2018年6月29日 下午3:18:30 
	 */ 
	@Override
	public int updateByEmpAndNotice(String noticeid, String empid) {
		return massNoticeEmpMapper.updateByEmpAndNotice(noticeid, empid);
	}

}
