<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="supma.common.*"%>
<%@page import="supma.beans.*"%>
<%userBean loginuserbean = (userBean)session.getAttribute("loginUserInfo") ; %>

<div id="s_hdw">
	
		<div id="s_tbar">
			<div class="s_hd">
				<div class="tbar_lft">
<%if(loginuserbean==null){ %>				
				<a href="login.aspx">请登录</a>
<%}else{ 
				String nowusertype="";
				if(CommonKey.user_type1.equals(loginuserbean.user_type)){	
					nowusertype=CommonKey.user_type1_name;
				}else if(CommonKey.user_type2.equals(loginuserbean.user_type)){
					nowusertype=CommonKey.user_type2_name;
				}else if(CommonKey.user_type3.equals(loginuserbean.user_type)){
					nowusertype=CommonKey.user_type3_name;
				}
%>			
				欢迎您！&nbsp;<%=loginuserbean.user_id%> | <a href="login.goods?exituser='yes'">退出登录</a>
<%} %>				
				 | <a href="regist1.aspx">超市注册</a>| <a href="regist2.aspx">批发商注册</a></div>
				<div class="tbar_rgt">
					<ul>
						<li class="first"><a href="#">我的订单</a></li>
						<li><a href="#">我的<%=CommonWenZi.SiteName %></a></li>
						<li><a href="#">帮助中心</a></li>
						<li><a href="#">联系客服</a></li>
						<li><a href="#">加入收藏</a></li>
						<li class="s_tel_str">服务热线：</li>
						<li class="s_tel"><%=CommonWenZi.Fuwu_TEL %></li>
					</ul>
				</div>
			</div>
		</div><!--s_tbar end-->
	</div><!--s_hdw end-->
	