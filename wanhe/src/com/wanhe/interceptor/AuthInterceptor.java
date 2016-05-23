package com.wanhe.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wanhe.entity.Admin;

/**
 * Interceptor - 权限验证
 * @author Lucifer
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter{
	
	public String PREFIX_FILTER = "/admin/";
	public String SUFFIX_FILTER = ".do";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		String path = request.getServletPath();
//		if(path.contains(SUFFIX_FILTER)){
//			path = path.replace(PREFIX_FILTER, "").replace(SUFFIX_FILTER, "").replace("/", ":");
//			
//			//shiro管理的session
//			Subject currentUser = SecurityUtils.getSubject();  
//			Session session = currentUser.getSession();
//			Admin admin = (Admin)session.getAttribute(Admin.SESSION_ADMIN);
//			if(admin != null){
//				if(admin.getAuthorities() != null){
//					if(!admin.getAuthorities().contains(path)){
//						JSONObject json = new JSONObject();
//						json.put("status", 1);
//						json.put("message", "对不起，您没有权限对此操作");
//						response.setHeader("Content-type", "text/html;charset=UTF-8");
//						response.getWriter().write(json.toString());
//						return false;
//					}
//					return true;
//				} else {
//					return false;
//				}
//			}else{
//				//登陆过滤
////				response.sendRedirect(request.getContextPath() + Const.LOGIN);
//				return false;		
//			}
//		}else{
			return true;
//		}
	}
}
