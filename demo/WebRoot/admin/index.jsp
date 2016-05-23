<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


%>
<!DOCTYPE html>
<!--
BeyondAdmin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.2.0
Version: 1.0.0
Purchase: http://wrapbootstrap.com
-->

<html xmlns="http://www.w3.org/1999/xhtml">
<!-- Head -->
<head>
    <meta charset="utf-8" />
    <title>青岛万和工程管理系统</title>

    <meta name="description" content="Dashboard" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="assets/img/favicon.png" type="image/x-icon">


    <!--Basic Styles-->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link id="bootstrap-rtl-link" href="" rel="stylesheet" />
    <link href="assets/css/font-awesome.min.css" rel="stylesheet" />
    <link href="assets/css/weather-icons.min.css" rel="stylesheet" />

    <!--Fonts-->
    <!--<link href="http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300" rel="stylesheet" type="text/css">-->

    <!--Beyond styles-->
    <link id="beyond-link" href="assets/css/beyond.min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/demo.min.css" rel="stylesheet" />
    <link href="assets/css/typicons.min.css" rel="stylesheet" />
    <link href="assets/css/animate.min.css" rel="stylesheet" />

    <!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
    <script src="assets/js/skins.min.js"></script>

    <script src="basedata/leftMenu.js"></script>

	<link href="assets/css/dataTables.bootstrap.css" rel="stylesheet">
    <link href="layer/skin/layer.css" />
    
	<style type="text/css">
		.font-nav p{line-height:45px;font-size:32px;color:#444;}
		.layui-layer-shade {opacity: 0.4;}
	</style>
</head>
<!-- /Head -->
<!-- Body -->
<body>
   <!--  顶部菜单开始 -->
<div class="loading-container">
    <div class="loading-progress">
        <div class="rotator">
            <div class="rotator">
                <div class="rotator colored">
                    <div class="rotator">
                        <div class="rotator colored">
                            <div class="rotator colored"></div>
                            <div class="rotator"></div>
                        </div>
                        <div class="rotator colored"></div>
                    </div>
                    <div class="rotator"></div>
                </div>
                <div class="rotator"></div>
            </div>
            <div class="rotator"></div>
        </div>
        <div class="rotator"></div>
    </div>
</div>




<!--  /Loading Container -->
<!-- Navbar -->
<div class="navbar">
    <div class="navbar-inner">
        <div class="navbar-container">
            <!-- Navbar Barnd -->
            <div class="navbar-header pull-left">
                <a href="#" class="navbar-brand">
                    <small>
                        <!-- <img src="assets/img/logo.png" alt="" /> -->
                        <p style="line-height: 40px;">青岛万和工程管理系统</p>
                    </small>
                </a>
            </div>
            <!-- /Navbar Barnd -->

            <!-- Sidebar Collapse -->
            <div class="sidebar-collapse" id="sidebar-collapse">
                <i class="collapse-icon fa fa-bars"></i>
            </div>
            <!-- /Sidebar Collapse -->
            <!-- Account Area and Settings --->
           <!--  <div class="navbar-header pull-right">
                <div class="navbar-account">
                    <ul class="account-area">
                        <li>
                            <a class="fullscreen" id="fullscreen-toggler" title="全屏" href="#">
                                <i class="icon fa fa-arrows-alt white"></i>
                            </a>

                        </li>
                    </ul>

                </div>
            </div>-->
        </div>
    </div>
</div>
    <!-- 顶部导航结束 -->
    <!-- 左菜单开始 -->
<div class="main-container container-fluid">
    <div class="page-container">
        <div class="page-sidebar" id="sidebar">
          <!--   <div class="sidebar-header-wrapper">
                <input type="text" class="searchinput" />
                <i class="searchicon fa fa-search"></i>
                <div class="searchhelper">请输入关键字搜素</div>
            </div> -->


            <ul class="nav sidebar-menu">
                 <li>
                  <a href="#" class="menu-dropdown">
                      <i class="menu-icon"><img src="img/mearchat.png"/></i>
                      <span class="menu-text">管理员管理 </span>

                      <i class="menu-expand"></i>
                  </a>

                  <ul class="submenu">
                      <li>
                          <a href="">
                              <span class="menu-text">后台人员管理</span>
                          </a>
                      </li>
                  </ul>
               </li>
               
            </ul>
        </div>
		<div class="page-content" id="main-content">
            <div class="page-body">
            	<div class="font-nav" style="width:300px;height: 45px;  margin: auto;margin-top: 300px;">
	            	<p>管理系统欢迎您！</p>
	            </div>
            </div>
        </div>
    </div>

</div>

	
   <!--Basic Scripts-->
   <script src="assets/js/jquery-2.0.3.min.js"></script>
   <script src="assets/js/bootstrap.min.js"></script>

   <!--Beyond Scripts-->
   <script src="assets/js/beyond.min.js"></script>

	<!-- 搜索引擎 Start-->
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
   <!--Page Related Scripts-->
   <!--Sparkline Charts Needed Scripts-->
   <script src="assets/js/charts/sparkline/jquery.sparkline.js"></script>
   <script src="assets/js/charts/sparkline/sparkline-init.js"></script>

   <!--Easy Pie Charts Needed Scripts-->
   <script src="assets/js/charts/easypiechart/jquery.easypiechart.js"></script>
   <script src="assets/js/charts/easypiechart/easypiechart-init.js"></script>
   <script src="js/controller/index.js"></script>
   <script type="text/javascript" src="layer/layer.js" ></script>
   <script type="text/javascript" src="js/ip.js" ></script>
   <script  type="text/javascript" src="js/jquery.min.js"></script>
   <script  type="text/javascript" src="js/jqPaginator.js"></script>
    <!--Google Analytics::Demo Only-->
    <script>


    </script>
</body>

</html>
