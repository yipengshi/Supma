<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="supma.db.*,java.util.*,supma.beans.*,supma.common.*"%>
<%userBean ufo = (userBean)session.getAttribute("loginUserInfo") ; //写log用的userBean%>

<%
//注册页面防止回退
response.setHeader( "Pragma", "no-cache" );
response.addHeader( "Cache-Control", "must-revalidate" );
response.addHeader( "Cache-Control", "no-cache" );
response.addHeader( "Cache-Control", "no-store" );
response.setDateHeader("Expires", 0);
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=CommonWenZi.SiteName %> -系统错误</title>
<meta http-equiv="x-ua-compatible" content="ie=Edge" />
<meta name="keywords" content="<%=CommonWenZi.SiteName %> -错误页面" />
<meta name="description" content="<%=CommonWenZi.error_page_description %>" />

<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/lr.css" rel="stylesheet" type="text/css"  />

<script language="javaScript" src="js/md5.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript" src="js/jquery-imgslideshow.js"></script>
<script type="text/javascript" src="js/ks-switch.js"></script>
<script type="text/javascript" src="js/lib.js"></script>
  <script type="text/javascript">
          	var i = 10;
			var intervalid;
			intervalid = setInterval("fun()", 1000);
			function fun() {
				if (i == 0) {
					window.location.href = "login.aspx";
					clearInterval(intervalid);
					}
					document.getElementById("mes").innerHTML = i;
					i--;
			} 
    </script>

<style type="text/css">
*{margin-right:auto;margin-left:auto;font-size:12px;}
#layer {height: auto;width: 760px;height:600px;}
#left {float: left;height: auto;width: 200px;height:600px;background-color:#DAF4FE;}
#conter {float: left;height: 600px;width: 460px;background-color:#FDF1C1;}
#right {float: left;height: 600px;width: 100px;}
#right {background-color: #DBFECF;}
body {margin-top: 0px;margin-bottom: 0px;}
</style>

</head>
<%int tabindex=1; %>
<body onload="init();">
<div id="doc">

<div id="s_hdw">
    <jsp:include page="inc/title2.jsp" flush="true" />
	</div><!--s_hdw end-->

	

	
	<div id="s_bdw">
		<div id="s_bd">
			<div id="s_logo"><a href="#"><img src="images/logo.jpg" border="0" /></a></div>	
			<div class="dl_zc">
			
				<div class="dl_zc_title">
					<h2 class="f_l">系统出错</h2>
					<div class="rt_bg f_r"></div>
				</div>
				<div class="dl-con22" id="regist">	
				<img src="images/error.png" border="0" />
					<strong><span style="font-size: 14px;color:#FF3030">&nbsp;&nbsp;&nbsp;&nbsp;抱歉，系统出错了。请重新</span><u><a href="login.aspx"><span style="font-size: 16px;color:#DC143C">登录</span></a></u><span style="font-size: 14px;color:#FF3030">！给您带来不便，请谅解。谢谢！</span></strong>
					<br />
					将在 <span id="mes" style="font-size: 28px;color:#FF3030">10</span>秒后自动跳转到登录页面
				</div>
			</div><!--dl_zc end-->
			
			<script type="text/javascript" src="js/Validate.js"></script>
			<script type="text/javascript" src="js/Validate.personal.js"></script>

		</div><!--s_bd end-->
	</div><!--s_bdw end-->	
	
    <jsp:include page="inc/foot1.jsp" flush="true" />


</div>




</body>
</html>
