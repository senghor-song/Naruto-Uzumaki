package com.ruiec.web.template.jsp.function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.ruiec.web.util.RuiecDateUtils;

public class Functions {

	/**
	 * 判断权限是否存在
	 * 
	 * @param perms
	 * @return
	 */
	public static boolean hasPerms(String perms) {
		Subject user = SecurityUtils.getSubject();
		return user.isPermitted(perms);
	}

	/**
	 * 判断公告是否已读
	 * 
	 * @param perms
	 * @return
	 */
	public static boolean isRead(String id, String[] ids) {
		if (null != ids) {
			for (int i = 0; i < ids.length; i++) {
				if (id.equals(ids[i])) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 布控剩余天数
	 * 
	 * @param perms
	 * @return
	 * @throws ParseException
	 */
	public static String leftDay(Integer status, Integer isRevoke, String time, Integer duration)
			throws ParseException {
		if (isRevoke == null || (status != 1 && isRevoke != 1) && (status != 2 && isRevoke != 0)) {
			return "暂无";
		}
		if (StringUtils.isBlank(time)) {
			return "暂无";
		}
		if (duration == null || 0 == duration) {
			return "暂无";
		}
		String[] times = time.split(",");
		String a = times[times.length - 1];
		// 审批时间
		Date pDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(a);
		// 过期时间
		Date overDate = RuiecDateUtils.getNDaysTime(pDate, duration);
		// 过期天数
		long day = RuiecDateUtils.getTimeDifference(new Date(), overDate, 3);
		if (day < 0) {
			return "过期";
		}
		day = day + 1;
		String overDay = String.valueOf(day);
		return overDay;
	}
}
