<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@page import="java.util.UUID"%>
<%@page import="java.security.interfaces.RSAPublicKey"%>
<%@page import="org.apache.commons.lang.ArrayUtils"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.wanhe.Setting"%>
<%@page import="com.wanhe.util.SettingUtils"%>
<%@page import="com.wanhe.util.SpringUtils"%>
<%@page import="com.wanhe.Setting.CaptchaType"%>
<%@page import="com.wanhe.Setting.AccountLockType"%>
<%@page import="com.wanhe.service.RSAService"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String captchaId = UUID.randomUUID().toString();
%>
<%
	String base = request.getContextPath();
	ApplicationContext applicationContext = SpringUtils.getApplicationContext();
	Setting setting = SettingUtils.get();
	//System.out.print(request.getAttribute("session_admin"));
if (applicationContext != null) {
%>
<shiro:authenticated>
<%
 response.sendRedirect(base + "/admin/index.jsp"); 
%>
</shiro:authenticated>
<%
}
%>
<!DOCTYPE html>
<!--
Magic Beans Admin Template
Version: 1.0.0
-->

<html xmlns="http://www.w3.org/1999/xhtml">
<!--Head-->
<head>
    <meta charset="utf-8" />
    
    <%
String message = null;
String modulus = null;
String exponent = null;
if (applicationContext != null) {
	RSAService rsaService = SpringUtils.getBean("rsaServiceImpl", RSAService.class);
	RSAPublicKey publicKey = rsaService.generateKey(request);
	modulus = Base64.encodeBase64String(publicKey.getModulus().toByteArray());
	exponent = Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray());
	
	String loginFailure = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);

	if (loginFailure != null) {
		if (loginFailure.equals("org.apache.shiro.authc.pam.UnsupportedTokenException")) {
			message = "admin.captcha.invalid";
		} else if (loginFailure.equals("org.apache.shiro.authc.UnknownAccountException")) {
			message = "admin.login.unknownAccount";
		} else if (loginFailure.equals("org.apache.shiro.authc.DisabledAccountException")) {
			message = "admin.login.disabledAccount";
		} else if (loginFailure.equals("org.apache.shiro.authc.LockedAccountException")) {
			message = "admin.login.lockedAccount";
		} else if (loginFailure.equals("org.apache.shiro.authc.IncorrectCredentialsException")) {
			if (ArrayUtils.contains(setting.getAccountLockTypes(), AccountLockType.admin)) {
				message = "admin.login.accountLockCount";
			} else {
				message = "admin.login.incorrectCredentials";
			}
		} else if (loginFailure.equals("org.apache.shiro.authc.AuthenticationException")) {
			message = "admin.login.authentication";
		}
		System.out.println("message : " + message);
	}
}
%>
    <title>登陆</title>

    <meta name="description" content="login page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="<%=basePath %>admin/assets/img/favicon.png" type="image/x-icon">

    <!--Basic Styles-->
    <link href="<%=basePath %>admin/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link id="bootstrap-rtl-link" href="" rel="stylesheet" />
    <link href="<%=basePath %>admin/assets/css/font-awesome.min.css" rel="stylesheet" />

    <!--Fonts-->
    <!--<link href="http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300" rel="stylesheet" type="text/css">-->

    <!--Beyond styles-->
    <link id="beyond-link" href="<%=basePath %>admin/assets/css/beyond.min.css" rel="stylesheet" />
    <link href="<%=basePath %>admin/assets/css/demo.min.css" rel="stylesheet" />
    <link href="<%=basePath %>admin/assets/css/animate.min.css" rel="stylesheet" />
    <link id="skin-link" href="" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" href="<%=basePath %>admin/css/basestyle.css"/>

    <!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
   <%--  <script src="<%=basePath %>resources/assets/js/skins.min.js"></script> --%>

</head>
<!--Head Ends-->
<!--Body-->
<body class="bg-login">
    <div class="login-container animated fadeInDown login-bg">
        <div class="loginbox bg-white">
            <div class="loginbox-title">青岛万和工程管理系统</div>
            <div class="loginbox-or">
                <div class="or-line"></div>
                <div class="or">OR</div>
            </div>
            <form id="loginForm" name="loginForm" action="<%=base %>/admin/login.jsp" method="post">
            	 <div class="loginbox-textbox">
	                <div class="form-group">
	                    <span class="input-icon icon-right">
	                        <input type="text" class="form-control" id="username" name="username"  placeholder="请输入用户名">
	                        <i class="fa fa-user darkorange"></i>
	                    </span>
	                </div>
            	</div>

	            <div class="loginbox-textbox">
	                <div class="form-group">
	                    <span class="input-icon icon-right">
	                        <input type="password" class="form-control" id="password" name="password"  placeholder="请输入密码">
	                        <input type="hidden" id="enPassword" name="enPassword" />
	                        <i class="fa fa-unlock-alt darkorange"></i>
	                    </span>
	                </div>
	            </div>
	            <div class="loginbox-submit">
	                <input type="submit" class="btn btn-success btn-block" value="登陆">
	        	</div>
            </form>
           
    </div>

    <!--Basic Scripts-->
    <script src="<%=basePath %>admin/assets/js/jquery-2.0.3.min.js"></script>
    <script src="<%=basePath %>admin/assets/js/bootstrap.min.js"></script>

    <!--Beyond Scripts-->
    <script src="<%=basePath %>admin/assets/js/beyond.js"></script>

    <script src="<%=basePath %>admin/js/common.js"></script>
    <script src="<%=basePath %>admin/js/jsbn.js"></script>
    <script src="<%=basePath %>admin/js/prng4.js"></script>
    <script src="<%=basePath %>admin/js/rng.js"></script>
    <script src="<%=basePath %>admin/js/base64.js"></script>
    <script src="<%=basePath %>admin/js/rsa.js"></script>
    
    <!--Google Analytics::Demo Only-->
    <script>
        (function (i, s, o, g, r, a, m) {
            i['GoogleAnalyticsObject'] = r; i[r] = i[r] || function () {
                (i[r].q = i[r].q || []).push(arguments)
            }, i[r].l = 1 * new Date(); a = s.createElement(o),
            m = s.getElementsByTagName(o)[0]; a.async = 1; a.src = g; m.parentNode.insertBefore(a, m)
        })(window, document, 'script', 'http://www.google-analytics.com/analytics.js', 'ga');

        ga('create', 'UA-52103994-1', 'auto');
        ga('send', 'pageview');

    </script>

        <script src="<%=basePath %>admin/js/commons.js"></script>
        <script>
            $.bar({
                iconClass:"blue",
                ButtonClass:"btn-primary"
            });
            
			$().ready( function() {
				var $loginForm = $("#loginForm");
				var $enPassword = $("#enPassword");
				var $username = $("#username");
				var $password = $("#password");
				/* var $captcha = $("#captcha");
 				var $captchaImage = $("#captchaImage");
				var $isRememberUsername = $("#isRememberUsername");
				 */
				/* // 记住用户名
				if(getCookie("adminUsername") != null) {
					$isRememberUsername.prop("checked", true);
					$username.val(getCookie("adminUsername"));
					$password.focus();
				} else {
					$isRememberUsername.prop("checked", false);
					$username.focus();
				} */
				
				// 表单验证、记住用户名
				$loginForm.submit( function() {
 					if ($username.val() == "") {
 						$.message("warn", "<%=SpringUtils.getMessage("admin.login.usernameRequired")%>");
 						return false;
 					}
 					if ($password.val() == "") {
 						$.message("warn", "<%=SpringUtils.getMessage("admin.login.passwordRequired")%>");
 						return false;
 					}
					try {
						var rsaKey = new RSAKey();
						rsaKey.setPublic(b64tohex("<%=modulus%>"), b64tohex("<%=exponent%>"));
						var enPassword = hex2b64(rsaKey.encrypt($password.val()));
						$enPassword.val(enPassword);
					} catch (e) {
						console.log(e);
					}
				});
				
			});
        </script>

</body>
<!--Body Ends-->
</html>

