package com.ruiec.web.shiro;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.sql.JoinType;

import com.octo.captcha.service.image.ImageCaptchaService;
import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.web.util.RSAUtils;
import com.ruiec.server.common.entity.Admin;
import com.ruiec.server.common.entity.Role;
import com.ruiec.server.common.service.AdminService;
import com.ruiec.server.common.service.RoleService;
import com.ruiec.web.shiro.exception.CaptchaErrorException;
import com.ruiec.web.shiro.exception.LoginLockedException;

public class ShiroDbRealm extends AuthorizingRealm {
	
	@Resource
	private AdminService adminService;
	@Resource
	private ImageCaptchaService imageCaptchaService;
	@Resource
	private RoleService roleService;
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		ShiroToken localShiroToken = (ShiroToken)token;
		String username = localShiroToken.getUsername();
		char[] password = localShiroToken.getPassword();
		String captchaId = localShiroToken.getCaptchaId();
		String captcha = localShiroToken.getCaptcha();
	    if(StringUtils.isBlank(captcha) || !imageCaptchaService.validateResponseForID(captchaId, captcha.trim().toUpperCase())){
	    	throw new CaptchaErrorException();
	    }
		String decryptPassword = RSAUtils.decryptStringByJs(new String(password));
		Admin localAdmin = adminService.findByUsename(username);
		if(localAdmin == null){
			throw new UnknownAccountException();
		}else if(localAdmin.getLockedDate() != null && localAdmin.getLockedDate().after(new Date())){
			throw new LoginLockedException();
		}
		if(!localAdmin.getPassword().equals(decryptPassword)){
			if(localAdmin.getLoginFailureCount() < 3){
				localAdmin.setLoginFailureCount(localAdmin.getLoginFailureCount() + 1);
				adminService.updateIgnore(localAdmin, null, null);
			}else if(localAdmin.getLoginFailureCount() >= 3){
				localAdmin.setLockedDate(DateUtils.addMinutes(new Date(), 10));
				localAdmin.setLoginFailureCount(0);
				adminService.updateIgnore(localAdmin, null, null);
				throw new LoginLockedException();
			}
			throw new UnknownAccountException();
		}
		return new SimpleAuthenticationInfo(username, new String(password), getName());
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		String username = (String)principal.getPrimaryPrincipal();
		if(username == null || username.trim().equals("")){
			return null;
		}
//		Admin localAdmin = adminService.findByUsename(username);
		Admin localAdmin = adminService.get(Filter.build().add(Filter.eq("username", username)).build(), null,
											Fetch.alias("roles", "rolesAlias", JoinType.LEFT_OUTER_JOIN), Fetch.select("rolesAlias"), Fetch.alias("rolesAlias.authorities", "authoritiesAlias", JoinType.LEFT_OUTER_JOIN), Fetch.select("authoritiesAlias")
											);
		if(localAdmin == null){
			return null;
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		for(Role role: localAdmin.getRoles()){
			info.addRole(role.getName());
			info.addStringPermissions(role.getAuthorities());
		}
		return info;
	}

}
