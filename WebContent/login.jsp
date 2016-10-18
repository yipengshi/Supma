<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="supma.db.*,java.util.*,supma.beans.*,supma.common.*"%>
<%userBean ufo = (userBean)session.getAttribute("loginUserInfo") ; //写log用的userBean%>

<%
//清空缓存
response.setHeader( "Pragma", "no-cache" );
response.addHeader( "Cache-Control", "must-revalidate" );
response.addHeader( "Cache-Control", "no-cache" );
response.addHeader( "Cache-Control", "no-store" );
response.setDateHeader("Expires", 0);
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=CommonWenZi.SiteName %> - 会员登录</title>
<meta http-equiv="x-ua-compatible" content="ie=Edge" />
<meta name="keywords" content="会员登录" />
<meta name="description" content="<%=CommonWenZi.Login_meta_description %>" />


<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/lr.css" rel="stylesheet" type="text/css"  />
<script language="javaScript" src="js/ajax.js" type="text/javascript"></script>
<script language="javaScript" src="js/login.js" type="text/javascript" charset=“UTF-8”></script>
<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery-imgslideshow.js"></script>
<script type="text/javascript" src="js/ks-switch.js"></script>
<script type="text/javascript" src="js/lib.js"></script>
<script language="javaScript" src="js/md5.js" type="text/javascript"></script>


</head>

<body>
<div id="doc">


<%--	<div id="s_hdw">
    <jsp:include page="inc/title1.jsp" flush="true" />
	</div><!--s_hdw end-->
--%>
	
	
	<div id="s_bdw">
		<div id="s_bd">
			
			<div class="dl_zc">
				<div class="dl_zc_title">
					<h2 class="f_l">超市/批发商/管理员  &nbsp;&nbsp;统一登录区</h2>
					<div class="rt_bg f_r"></div>
				</div>
				<div class="dl-con cf" id="entry">
					<form id="formlogin" method="post"   action="index.aspx"  onsubmit="changeMd5();">
		
						<div class="form" style="width:600px;">
							<div class="item">
								<span class="label">登录名：</span>
		
								<div class="fl">
									<input type="text" id="loginname" name="loginname" class="text" tabindex="1" value=""/>
									<label id="loginname_error"></label>
		
								</div>
							</div>
							<!--item end-->
							<div class="item">
								<span class="label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
		
								<div class="fl">
									<input type="password" id="loginpwd" name="loginpwd" class="text" tabindex="2"/>
									<label id="loginpwd_error"></label>
										
								</div>
							</div>
							<!--item end-->
<%--					<div class="item">
								<span class="label">&nbsp;</span>
								<div class="fl">
									<input type="checkbox" name="dl" id="jz" value="" /><label for="jz">记住用户名</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="dl" id="zd" value="" /><label for="zd">自动登录</label>
								</div>
							</div><!--item end-->
--%>
							<div class="item">
								<span class="label">&nbsp;</span>
								<input type="button" class="btnimg"  id="registsubmit"  value="" tabindex="8" onclick="loginsubmit();"/>
								<label>&nbsp;&nbsp;&nbsp;<a href="forgot-password.html" class="blue">忘记密码?</a></label>
							</div>
		
							<!--item end-->
						</div>
						<!--form end-->
						<div id="guide">
							<h5>还不是<%=CommonWenZi.SiteName %>用户？</h5>
		
							<div class="content">现在免费注册成为<%=CommonWenZi.SiteName %>用户，便能立刻享受便宜又放心的采购乐趣。</div>
							<a href="regist1.aspx" class="btn-personal">新超市注册</a>
							<a href="regist2.aspx" class="btn-personal">新批发商注册</a>
		
						</div>
						<!--guide end-->
						<div class="clear"></div>
					</form>
				</div><!--regist end-->
			</div> <!--dl_zc end-->
			
			<script type="text/javascript" src="js/Validate.js"></script>
			<script type="text/javascript" src="js/Validate.entry.js"></script>

		</div><!--s_bd end-->
	</div><!--s_bdw end-->	
	
    <jsp:include page="inc/foot1.jsp" flush="true" />

</div>
</body>
</html>