package com.xiaoyi.ssm.quartz;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.model.InviteBall;
import com.xiaoyi.ssm.model.InviteBallAA;
import com.xiaoyi.ssm.model.InviteBallOneDay;
import com.xiaoyi.ssm.model.InviteJoin;
import com.xiaoyi.ssm.service.InviteBallAAService;
import com.xiaoyi.ssm.service.InviteBallOneDayService;
import com.xiaoyi.ssm.service.InviteBallService;
import com.xiaoyi.ssm.service.InviteJoinService;
import com.xiaoyi.ssm.util.Arith;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.SpringUtils;
import com.xiaoyi.ssm.util.Utils;

@Service
public class OneDayCountAmountJob {

	private static Logger logger = Logger.getLogger(OneDayCountAmountJob.class.getName());

	/**
	 * 定时任务，每天0:00:01执行统计金额任务
	 */
	@Scheduled(cron = "1 0 0 1/1 * ? ")
//	 @Scheduled(cron = "0/5 * * * * ? ")
	public void countOneDayAmount() {
		logger.info("开始执行昨天的收费活动并统计收取费用结算给发起人------------------->");

		InviteBallService inviteBallService = SpringUtils.getBean("inviteBallServiceImpl", InviteBallService.class);
		InviteJoinService inviteJoinService = SpringUtils.getBean("inviteJoinServiceImpl", InviteJoinService.class);
		InviteBallAAService inviteBallAAService = SpringUtils.getBean("inviteBallAAServiceImpl", InviteBallAAService.class);
		InviteBallOneDayService inviteBallOneDayService = SpringUtils.getBean("inviteBallOneDayServiceImpl", InviteBallOneDayService.class);
		Date date = new Date();
		// date = DateUtil.getPreTime(date, 3, -1);
		Date startDate = DateUtil.getWeeHours(date, 0);
		// Date endDate = DateUtil.getWeeHours(date, 1);
		// 查询过去的收费活动
		List<InviteBall> list = inviteBallService.selectByBallTimeOut(startDate);
		for (int i = 0; i < list.size(); i++) {
			// 本次约球活动
			InviteBall inviteBall = list.get(i);
			// 本次约球的报名总收入
			double amountCollect = 0;
			// 本次约球的AA退款总额
			double amountAa = 0;
			// 本次约球的平台收取手续费
			double amountFee = 0;
			// 本次约球的实际付款
			double amountOut = 0;

			// 保存流水记录
			InviteBallOneDay inviteBallOneDay = new InviteBallOneDay();
			inviteBallOneDay.setId(Utils.getUUID());
			inviteBallOneDay.setCreateTime(new Date());
			inviteBallOneDay.setModifyTime(new Date());
			inviteBallOneDay.setInviteBallId(inviteBall.getId());
			inviteBallOneDay.setMemberId(inviteBall.getMemberId());

			int applySum = 0;// 已报名人数
			int applyOutSum = 0;// 退出报名人数
			double applyAmount = 0;// 报名预收总额
			double applyFeeAmount = 0;// 退出报名退费总额

			// 获取本次约球的报名状态
			List<InviteJoin> joinList = inviteJoinService.selectByJoinDetails(inviteBall.getId());
			for (int j = 0; j < joinList.size(); j++) {
				InviteJoin inviteJoin = joinList.get(j);
				if (inviteJoin.getRefundFlag() == 0) {
					// 未退出的人员
					applySum++;
					amountCollect = Arith.add(amountCollect, inviteJoin.getAmount());
					applyAmount = Arith.add(applyAmount, inviteJoin.getAmount());
				} else if (inviteJoin.getRefundFlag() == 1) {
					// 已退出的人员
					applyOutSum++;
					amountCollect = Arith.add(amountCollect, inviteJoin.getRefundFeeamount());
					applyFeeAmount = Arith.add(applyFeeAmount, inviteJoin.getRefundFeeamount());
				} else {
					// 如都不符合，则该加入数据不正常
					continue;
				}
			}

			String content = "报名总收入:" + applySum + "人报名" + applyAmount + "元," + applyOutSum + "人退出手续费" + applyFeeAmount + "元,共计" + amountCollect + "元;";

			// 该约球是否发起过AA退款
			if (inviteBall.getAaflag() == 1) {
				// 如发起过AA则查询AA退款记录
				InviteBallAA inviteBallAA = inviteBallAAService.selectByInviteBallId(inviteBall.getId());
				if (inviteBallAA != null) {
					amountAa = inviteBallAA.getOughtMemberRefund().doubleValue();
					content += "AA退款总额:预收" + amountCollect + "元，实际消费" + inviteBallAA.getAmountOut() + "元,手续费总额" + inviteBallAA.getAmountFee() + "元，应退金额"
							+ amountAa + "元 ;";
				}
			}
			
			// 报名总收入:2人报名200元,1人退出手续费10元,共计210元;
			inviteBallOneDay.setAmountCollect(BigDecimal.valueOf(amountCollect));
			// AA退款总额:预收210元，实际消费150元,手续费总额2.1元，应退金额57.9元 ;
			inviteBallOneDay.setAmountAa(BigDecimal.valueOf(amountAa));
			// 平台收取手续费:210元*1%=2.1元;
			amountFee = Arith.round(Arith.mul(amountCollect, 0.01), 2);
			inviteBallOneDay.setAmountFee(BigDecimal.valueOf(amountFee));
			// 实际付款:210元-57.9元-2.1元=150.0元
			amountOut = Arith.round(Arith.sub(Arith.sub(amountCollect, amountFee), amountAa), 2);
			inviteBallOneDay.setAmountOut(BigDecimal.valueOf(amountOut));

			content += "平台收取手续费:" + amountCollect + "元*1%=" + amountFee + "元;" + "实际付款:" + amountCollect + "元-" + amountAa + "元-" + amountFee + "元="
					+ amountOut + "元;";
			
			if (inviteBall.getMember().getOpenid() != null) {
//				String wxCompanyPaymentId = WXPayUtil.wxCompanyPayment(inviteBallOneDay.getId(), inviteBall.getMember().getOpenid(), amountOut, inviteBall.getTitle() + "活动的结算金额");
				String wxCompanyPaymentId = Utils.getUUID();
				inviteBallOneDay.setPayType(1);
				inviteBallOneDay.setWxcompanypayment(wxCompanyPaymentId);

				inviteBall.setCountFlag(1);
				inviteBall.setArriveAmount(amountOut);
				inviteBall.setArriveTime(new Date());
				inviteBallService.updateByPrimaryKeySelective(inviteBall);
			}else {
				content+="该发起人没有关注公众号";
			}
			inviteBallOneDay.setContent(content);
			inviteBallOneDayService.insertSelective(inviteBallOneDay);
		}
		logger.info("结束执行昨天的收费活动并统计收取费用结算给发起人------------------->");
	}
}
