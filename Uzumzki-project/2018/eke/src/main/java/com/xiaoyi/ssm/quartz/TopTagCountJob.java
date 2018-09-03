package com.xiaoyi.ssm.quartz;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoyi.ssm.service.EmpStoreVerifyService;
import com.xiaoyi.ssm.service.EstateFindService;
import com.xiaoyi.ssm.service.ProposalService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.SpringUtils;

/**
 * @Description: 后台顶部统计数据定时任务
 * @author 宋高俊
 * @date 2018年8月1日 下午4:44:47
 */
public class TopTagCountJob {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	/**  
	 * @Description: 后台顶部统计数据定时任务
	 * @author 宋高俊  
	 * @date 2018年8月1日 下午4:46:24 
	 */ 
	public void topTagCount() {
		LOG.info("开始顶部统计数据----------------->");
		ProposalService proposalService = SpringUtils.getBean("proposalServiceImpl", ProposalService.class);
		EstateFindService estateFindService = SpringUtils.getBean("estateFindServiceImpl", EstateFindService.class);
		EmpStoreVerifyService empStoreVerifyService = SpringUtils.getBean("empStoreVerifyServiceImpl", EmpStoreVerifyService.class);
		Map<String, Object> map = new HashMap<>();
		map.put("proposalSum", proposalService.countByNoDispose());//建议未处理数据
		map.put("estateFindSum", estateFindService.countByNoDispose());//增加小区未处理数量
		map.put("orderSum", "0");//耗材订单未处理数量
		map.put("empStoreVerifySum", empStoreVerifyService.countByNoDispose());//合作续期未处理数量
		RedisUtil.addRedis(Global.REDIS_TOP_TAG_MAP, Global.REDIS_TOP_TAG_MAP, map);
		LOG.info("顶部统计数据完毕----------------->");
	}
}
