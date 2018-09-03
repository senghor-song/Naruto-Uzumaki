package com.ruiec.web.template.jsp.function;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class Functions {

	
	/**
	 * 判断权限是否存在
	 * @param perms
	 * @return
	 */
	public static boolean hasPerms(String perms){
		Subject user = SecurityUtils.getSubject();
		return user.isPermitted(perms);
	}
	
	/**
	 * 判断公告是否已读
	 * @param perms
	 * @return
	 */
	public static boolean isRead(String id,String[] ids){
		if(null != ids) {
			for (int i = 0; i < ids.length; i++) {
				if(id.equals(ids[i])) {
					return true;
				}
			}
		}
		return false;
	}
	
}
