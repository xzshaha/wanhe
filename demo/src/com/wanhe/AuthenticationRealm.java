/*
 * 
 * 
 * 
 */
package com.wanhe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.session.HttpServletSession;
import org.apache.shiro.web.subject.WebSubject;
import org.apache.shiro.web.util.WebUtils;

import com.wanhe.Setting.AccountLockType;
import com.wanhe.entity.Admin;
import com.wanhe.service.AdminService;
import com.wanhe.service.CaptchaService;
import com.wanhe.util.SettingUtils;

/**
 * 权限认证
 * 
 * 
 * 
 */
public class AuthenticationRealm extends AuthorizingRealm {

	@Resource
	private CaptchaService captchaService;
	@Resource
	private AdminService adminService;
	
	/**
	 * 获取认证信息
	 * 
	 * @param token
	 *            令牌
	 * @return 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) {
		AuthenticationToken authenticationToken = (AuthenticationToken) token;
		String username = authenticationToken.getUsername();
		String password = new String(authenticationToken.getPassword());
		String ip = authenticationToken.getHost();
		
		if (username != null && password != null) {
			Admin admin = adminService.findByUsername(username);
			if (admin == null) {
				throw new UnknownAccountException();
			}
			if (!admin.getIsEnabled()) {
				throw new DisabledAccountException();
			}
			Setting setting = SettingUtils.get();
			if (admin.getIsLocked()) {
				if (ArrayUtils.contains(setting.getAccountLockTypes(), AccountLockType.admin)) {
					int loginFailureLockTime = setting.getAccountLockTime();
					if (loginFailureLockTime == 0) {
						throw new LockedAccountException();
					}
					Date lockedDate = admin.getLockedDate();
					Date unlockDate = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
					if (new Date().after(unlockDate)) {
						admin.setLoginFailureCount(0);
						admin.setIsLocked(false);
						admin.setLockedDate(null);
						adminService.update(admin);
					} else {
						throw new LockedAccountException();
					}
				} else {
					admin.setLoginFailureCount(0);
					admin.setIsLocked(false);
					admin.setLockedDate(null);
					adminService.update(admin);
				}
			}
			if (!DigestUtils.md5Hex(password).equals(admin.getPassword())) {
				int loginFailureCount = admin.getLoginFailureCount() + 1;
				if (loginFailureCount >= setting.getAccountLockCount()) {
					admin.setIsLocked(true);
					admin.setLockedDate(new Date());
				}
				admin.setLoginFailureCount(loginFailureCount);
				adminService.update(admin);
				throw new IncorrectCredentialsException();
			}

			// 将更新前的信息存入SESSION
			Admin temp = admin.clone();
			
			Session session = SecurityUtils.getSubject().getSession(true);
			session.setAttribute(Admin.SESSION_ADMIN, temp);

			ServletRequest request = ((WebSubject)SecurityUtils.getSubject()).getServletRequest();
			ip = request.getRemoteHost();

			// 更新登录时间与IP
			admin.setLoginIp(ip);
			admin.setLoginDate(new Date());
			admin.setLoginFailureCount(0);
			adminService.update(admin);
			return new SimpleAuthenticationInfo(new Principal(admin.getId(), username), password, getName());
		}
		throw new UnknownAccountException();
	}

	/**
	 * 获取授权信息
	 * 
	 * @param principals
	 *            principals
	 * @return 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
		if (principal != null) {
			List<String> authorities = adminService.findAuthorities(principal.getId());
			if (authorities != null) {
				SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
				authorizationInfo.addStringPermissions(authorities);
				return authorizationInfo;
			}
		}
		return null;
	}

	public Session getSession(SessionKey key) throws SessionException {
		if (!WebUtils.isHttp(key)) { //判断是不是http的key，否则抛异常 
			String msg = "SessionKey must be an HTTP compatible implementation.";
			throw new IllegalArgumentException(msg);
		}
		HttpServletRequest request = WebUtils.getHttpRequest(key); //通过工具类获得HttpServletRequest 这类是javax.servlet.http.HttpServletRequest;
		Session session = null;
		HttpSession httpSession = request.getSession(false);//先从request里获得本来存在的
		if (httpSession != null) {
			session = createSession(httpSession, request.getRemoteHost());//如果不为空，就创建一个封装了的，为空就不管它
		}
		return session;
	}

	protected Session createSession(HttpSession httpSession, String host) {
		return new HttpServletSession(httpSession, host);
	}

}