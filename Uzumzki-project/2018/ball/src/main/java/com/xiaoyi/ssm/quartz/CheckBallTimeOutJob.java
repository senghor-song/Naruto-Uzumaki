package com.xiaoyi.ssm.quartz;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xiaoyi.ssm.dao.InviteJoinMapper;
import com.xiaoyi.ssm.model.InviteBall;
import com.xiaoyi.ssm.model.InviteJoin;
import com.xiaoyi.ssm.model.InviteLog;
import com.xiaoyi.ssm.service.InviteBallService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.SpringUtils;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.WXConfig;
import com.xiaoyi.ssm.wxPay.WXPayUtil;

@Service
public class CheckBallTimeOutJob {

    private static Logger logger = Logger.getLogger(CheckBallTimeOutJob.class.getName());

	/**
	 * @Description: 定时任务检查是否有时间截止到期的约球
	 * @author 宋高俊
	 * @date 2018年9月20日 下午8:35:13
	 */
	@Scheduled(cron = "0 0/1 * * * ? ")
	public void checkBallTimeOutJob() {
		InviteBallService inviteBallService = SpringUtils.getBean("inviteBallServiceImpl", InviteBallService.class);
		InviteJoinMapper inviteJoinMapper = SpringUtils.getBean("inviteJoinMapper", InviteJoinMapper.class);
		List<InviteBall> list = inviteBallService.selectByTimeOut(new Date());
		if (list.size() != 0) {
			logger.info("查询到" + list.size() + "条已截止报名的约球");
			int flag = 0;
			for (InviteBall inviteBall : list) {

				List<InviteJoin> inviteJoins = inviteJoinMapper.selectByJoinMember(inviteBall.getId());
				if (inviteBall.getToRequire() == 2 && inviteBall.getMinBoy() > inviteJoins.size()) {
					// 未达到最小人数，活动取消
					inviteBall.setBallType(3);// 设置为活动取消
					flag += inviteBallService.updateByPrimaryKeySelective(inviteBall);

					// 记录日志
					InviteLog inviteLog = new InviteLog();
					inviteLog.setId(Utils.getUUID());
					inviteLog.setContent("人数不足，自动取消活动");
					inviteLog.setCreateTime(new Date());
					inviteLog.setInviteballId(inviteBall.getId());
					inviteLog.setType(1);
					inviteLog.setMemberId(inviteBall.getMemberId());
					// 活动取消后需发送微信公众号消息所有已加入的活动的人

					JSONObject datajson = new JSONObject();
					datajson.put("first", JSONObject.parseObject("{\"value\":\"你报名的活动已经取消\"}"));
					datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + inviteBall.getTitle() + "\"}"));
					datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"" + inviteBall.getVenueName() + "\"}"));
					datajson.put("keyword3",
							JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(inviteBall.getActiveTime(), "yyyy年MM月dd日 HH:mm") + "\"}"));
					datajson.put("remark", JSONObject.parseObject("{\"value\":\"由于人数不足成团要求自动取消活动，欢迎您下次参加活动\"}"));

					// 根据约球ID查询加入人员
					for (int i = 0; i < inviteJoins.size(); i++) {
						InviteJoin inviteJoin = inviteJoins.get(i);
						if (!StringUtil.isBank(inviteJoin.getMember().getOpenid())) {
							logger.info(WXPayUtil.sendWXappTemplate(inviteJoin.getMember().getOpenid(), WXConfig.templateId3, "/pages/index/index?id="
									+ inviteBall.getId(), datajson));
							if (inviteBall.getReceiveFlag() == 1) {
								// 如果已预收费用则退款
								inviteJoin.setPayType(3);
								inviteJoin.setRefundAmount(inviteJoin.getAmount());
								inviteJoin.setRefundFee(0);
								inviteJoin.setRefundFeeamount(0.0);

								// 发起人取消活动全额退款
								WXPayUtil.weiXinRefund(inviteJoin.getId(), inviteJoin.getAmount(), inviteJoin.getAmount(), "发起人取消活动", 1);

								JSONObject datajson2 = new JSONObject();
								datajson2.put("first", JSONObject.parseObject("{\"value\":\"您报名的活动已退款\"}"));
								datajson2.put("keyword1", JSONObject.parseObject("{\"value\":\"" + inviteJoin.getAmount() + "元\"}"));
								datajson2.put("keyword2",
										JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(inviteJoin.getPayTime(), "yyyy年MM月dd日 HH:mm") + "\"}"));
								datajson2.put("keyword3", JSONObject.parseObject("{\"value\":\"" + inviteJoin.getId() + "\"}"));
								datajson2.put("remark", JSONObject.parseObject("{\"value\":\"已完成退款\"}"));
								logger.info(WXPayUtil.sendWXappTemplate(inviteJoin.getMember().getOpenid(), WXConfig.templateId2, "/pages/index/index?id="
										+ inviteBall.getId(), datajson2));
							} else {
								inviteJoin.setPayType(2);
							}
							inviteJoinMapper.updateByPrimaryKeySelective(inviteJoin);
						}
					}
				} else {
					inviteBall.setBallType(1);// 设置为到期截止
					flag += inviteBallService.updateByPrimaryKeySelective(inviteBall);
				}
			}
			logger.info("已处理" + flag + "条已截止报名的约球");
		}
	}
}
