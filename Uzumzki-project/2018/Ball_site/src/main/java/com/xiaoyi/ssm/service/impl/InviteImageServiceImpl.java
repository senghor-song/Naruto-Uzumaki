package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.InviteImageMapper;
import com.xiaoyi.ssm.model.InviteImage;
import com.xiaoyi.ssm.service.InviteImageService;

/**  
 * @Description: 约球业务逻辑层
 * @author 宋高俊  
 * @date 2018年9月13日 下午4:03:48 
 */ 
@Service
public class InviteImageServiceImpl extends AbstractService<InviteImage,String> implements InviteImageService{

	@Autowired
	private InviteImageMapper inviteImageMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(inviteImageMapper);
	}

	/**  
	 * @Description: 根据约球ID获取封面图
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月13日 下午5:14:37 
	 */ 
	@Override
	public InviteImage selectByHeadID(String id) {
		return inviteImageMapper.selectByHeadID(id);
	}

	
	/**  
	 * @Description: 根据约球ID获取图片
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月13日 下午8:33:10 
	 */ 
	@Override
	public List<InviteImage> selectByInviteBallId(String id) {
		return inviteImageMapper.selectByInviteBallId(id);
	}

	/**  
	 * @Description: 根据约球ID删除图片
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月15日 下午3:36:20 
	 */ 
	@Override
	public Integer deleteByInviteBall(String id) {
		return inviteImageMapper.deleteByInviteBall(id);
	}

}
