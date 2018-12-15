package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MemberMapper;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.service.MemberService;

/**  
 * @Description: 用户业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月16日 下午4:35:09 
 */ 
@Service
public class MemberServiceImpl extends AbstractService<Member,String> implements MemberService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(memberMapper);
	}

	/**  
	 * @Description: 微信端用户登录接口
	 * @author 宋高俊  
	 * @date 2018年8月16日 下午4:34:06 
	 */ 
	@Override
	public Member login(Member member) {
		return memberMapper.login(member);
	}

	/**  
	 * @Description: 根据手机号查询用户
	 * @author 宋高俊  
	 * @date 2018年8月16日 下午5:06:01 
	 */ 
	@Override
	public Member selectByPhone(String phone) {
		return memberMapper.selectByPhone(phone);
	}
	
	/**  
	 * @Description: 根据手机号修改密码
	 * @author 宋高俊  
	 * @date 2018年8月16日 下午5:27:29 
	 */ 
	@Override
	public int updateByPhone(String phone, String password) {
		return memberMapper.updateByPhone(phone, password);
	}

	/**  
	 * @Description: 根据openid查询用户
	 * @author 宋高俊  
	 * @param openid
	 * @return 
	 * @date 2018年9月10日 下午8:40:45 
	 */ 
	@Override
	public Member selectByOpenid(String openid) {
		return memberMapper.selectByOpenid(openid);
	}

	/**  
	 * @Description: 根据Unionid查询用户
	 * @author 宋高俊  
	 * @date 2018年9月13日 上午11:36:49 
	 */ 
	@Override
	public Member selectByUnionid(String unionid) {
		return memberMapper.selectByUnionid(unionid);
	}

	/**  
	 * @Description: 根据AppIosOpenID查询用户
	 * @author 宋高俊  
	 * @param token
	 * @return 
	 * @date 2018年10月23日 上午11:41:32 
	 */ 
	@Override
	public Member selectByAppIosOpenID(String token) {
		return memberMapper.selectByAppIosOpenID(token);
	}

	/**  
	 * @Description: 查询在app登录过的用户
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年10月23日 上午11:57:56 
	 */ 
	@Override
	public List<Member> selectByApp() {
		return memberMapper.selectByApp();
	}

	/**  
	 * @Description: 根据用户ID删除openid
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年10月23日 上午11:57:56 
	 */ 
	@Override
	public int updateByMemberOpenID(String id) {
		return memberMapper.updateByMemberOpenID(id);
	}

	/**
	 * @Description: 根据用户编号查询用户
	 * @author 宋高俊
	 * @param memberno
	 * @return
	 * @date 2018年11月28日上午11:18:43
	 */
	@Override
	public Member selectByMemberno(Integer memberno) {
		return memberMapper.selectByMemberno(memberno);
	}
	
	/**
	 * @Description: 根据场馆ID查询机构回款人信息
	 * @author 宋高俊
	 * @param venueid
	 * @return
	 * @date 2018年11月30日下午5:01:25
	 */
	@Override
	public Member selectByVenueId(String venueId) {
		return memberMapper.selectByVenueId(venueId);
	}

	/**
	 * @Description: 根据日期查询回款用户
	 * @author 宋高俊
	 * @param date
	 * @return
	 * @date 2018年12月4日下午5:23:03
	 */
	@Override
	public List<Member> selectByDateOut(String date) {
		return memberMapper.selectByDateOut(date);
	}

	
}
