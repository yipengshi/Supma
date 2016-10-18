<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="supma.db.*"%>
<%@page import="java.util.*"%>
<%@page import="supma.beans.*"%>
<%@page import="supma.util.*"%>
<%@page import="supma.common.*"%>
<jsp:useBean id="goodsTypeBean" scope="page" class="supma.beans.GoodsTypeBean" />
<jsp:useBean id="basicDataBase" scope="page" class="supma.db.BasicDatabase" />
<%userBean ufo = (userBean)session.getAttribute("loginUserInfo") ; //写log用的userBean%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=CommonWenZi.SiteName %> - 批发商登录欢迎页</title>
<meta http-equiv="x-ua-compatible" content="ie=Edge" />
<meta name="keywords" content="批发商登录" />
<meta name="description" content="<%=CommonWenZi.Index_meta_description %>" />

<link href="css/main.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="css/my.css" rel="stylesheet" />
<link href="css/lr.css" rel="stylesheet" type="text/css"  />

<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery-imgslideshow.js"></script>
<script type="text/javascript" src="js/ks-switch.js"></script>
<script type="text/javascript" src="js/lib.js"></script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>

</head>

<%int tabindex=1; %>

<body>
<div id="doc">
	<div id="s_hdw">
	
    <jsp:include page="inc/title1.jsp" flush="true" />
    		<div class="s_hd nav">
			<div id="s_logo"><a href="index.aspx"><img src="images/logo.jpg" border="0" /></a></div>
		</div><!--s_hd end-->
	</div><!--s_hdw end-->
	
	<div id="s_bdw">
		<div id="s_bd">
			
			<div style="margin:10px 0 0 0;"></div>
			
			

			

			<div class="dl_zc">
			
			<div class="f-l presonalsort">
				<dl>
					<dt>订单管理</dt>
					<%-- <dd><a class="current" href="#">处理中订单</a></dd>--%>
					<dd><a  href="#">处理中订单</a></dd>
					<dd><a href="chaoshi_index.jsp">退换货订单</a></dd>
					<dd><a href="#">历史订单</a></dd>
				</dl>
				<dl>
					<dt>日常管理</dt>
					<dd><a href="#">商品管理</a></dd>
					<dd><a href="#" >商品录入</a></dd>
					<dd><a href="#">评价管理</a></dd>
				</dl>
				<dl>
					<dt>统计</dt>
					<dd><a href="#">我的客户</a></dd>
					<dd><a href="#">销售统计</a></dd>
					<dd><a href="#">营销费用统计</a></dd>
					<dd><a href="#">预付款余额查询</a></dd>
				</dl>
				<dl>
					<dt>设置</dt>
					<dd><a href="#">账户设置</a></dd>
					<dd><a href="#">消息中心</a></dd>
					<dd><a href="#">使用帮助</a></dd>
				</dl>
			</div><!--presonalsort end-->
			
				<div class="dl_zc_title">
					<h2 class="f_l">欢迎来到<%=CommonWenZi.SiteName %> </h2>
					<div class="rt_bg f_r"></div>
				</div>
				<div class="f-r presonalinfo" id="regist">	
					操作请选择左边导航条。
				</div>
			</div><!--dl_zc end-->
					
			
			<div class="clear"></div>
			
		</div><!--s_bd end-->
	</div><!--s_bdw end-->	
	
 <jsp:include page="inc/foot1.jsp" flush="true" />

</div>
</body>
</html>
