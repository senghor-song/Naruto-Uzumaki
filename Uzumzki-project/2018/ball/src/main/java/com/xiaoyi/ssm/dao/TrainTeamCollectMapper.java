package com.xiaoyi.ssm.dao;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.TrainTeamCollect;

public interface TrainTeamCollectMapper extends BaseMapper<TrainTeamCollect, String>{
	/**  
	 * @Description: 删除收藏的培训机构
	 * @author 宋高俊  
	 * @param id
	 * @param memberid
	 * @return 
	 * @date 2018年10月23日 下午8:36:26 
	 */ 
	int deleteByIdAndMember(@Param("id")String id, @Param("memberid")String memberid);

	/**  
	 * @Description: 根据用户统计收藏的培训机构数量
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月25日 上午9:22:01 
	 */ 
	int countByMember(String id);

	/**  
	 * @Description: 查询该培训机构是否被用户收藏
	 * @author 宋高俊  
	 * @param id
	 * @param memberid
	 * @return 
	 * @date 2018年10月25日 下午3:23:09 
	 */
	TrainTeamCollect selectByMember(@Param("id")String id, @Param("memberid")String memberid);
}