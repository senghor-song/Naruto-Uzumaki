package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.InviteImage;

public interface InviteImageMapper extends BaseMapper<InviteImage, String>{

	/**  
	 * @Description: 根据约球ID获取封面图
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月13日 下午5:14:37 
	 */ 
	InviteImage selectByHeadID(String id);

	/**  
	 * @Description: 根据约球ID获取图片
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月13日 下午8:33:10 
	 */ 
	List<InviteImage> selectByInviteBallId(String id);

	/**  
	 * @Description: 根据约球ID删除图片
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月15日 下午3:36:20 
	 */ 
	Integer deleteByInviteBall(String id);
}