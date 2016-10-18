<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="supma.db.*,java.util.*,supma.beans.*,supma.common.*"%>
<%userBean ufo = (userBean)session.getAttribute("loginUserInfo") ; //写log用的userBean%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=CommonWenZi.SiteName %> - 用户片区选择</title>
<meta http-equiv="x-ua-compatible" content="ie=Edge" />
<meta name="keywords" content="<%=CommonWenZi.SiteName %> - 用户片区选择" />
<meta name="description" content="<%=CommonWenZi.user_arr_meta_description %>" />


<link href="css/userarea.css" rel="stylesheet" type="text/css"  />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/lr.css" rel="stylesheet" type="text/css"  />

<script type="text/javascript">
function setTab03Syn(i,all) {
  selectTab03Syn(i,all);
}
function selectTab03Syn(i,all) {
	for(m=1;m<=all;m++){
		if(m==i){
				document.getElementById("TabTab03Con"+m).style.display = "block";
				document.getElementById("ttab"+m).className = "ttab_on";
		}else{
				document.getElementById("TabTab03Con"+m).style.display = "none";
				document.getElementById("ttab"+m).className = "ttab_off";		
		}
	}
}
function returnparhtml(aid,aname) {
	window.opener.document.formpersonal.u_area.value=aid+"<%=CommonKey.jinghao%>";
	window.opener.document.getElementById('u_area_error').innerHTML='';
	window.opener.document.getElementById('user_address2').style.color='#000000';
	window.opener.document.formpersonal.user_address2.value=aname;
	window.close();
}
</script>


</head>

  <%
  BasicDatabase basic = new BasicDatabase();
  UserAreaBean userareabean = new UserAreaBean();
  userareabean.initUserareaBean(userareabean.getAlluserArea(basic,ufo));
	if(basic.pstm !=null){
		basic.pstm.close();
		basic.pstm=null;
	}
	if(basic.conn != null){
		basic.conn.close();
		basic.conn=null;
	}
  %>
<body onload="selectTab03Syn(1,<%=userareabean.arylist1.size()%>);">

		
<div class="ttab_zhengce">
				<div class="dl_zc_title">
					<h2 class="f_l">选择您的超市所在片区</h2>（请认真选择，以便为您准确送货)
					<div class="rt_bg f_r"></div>
				</div>
				
  <%
  	for (int i=0;i<userareabean.arylist1.size();i++){
  		UserAreaBean tempBean = userareabean.arylist1.get(i);
   %>
  <div id="ttab<%=i+1 %>" class="ttab_on" onclick="setTab03Syn(<%=i+1 %>,<%=userareabean.arylist1.size()%>)"><%=tempBean.userarea_name %></div>
  <%} %>
  <div class="clearall"></div>
  <%
  	for (int i=0;i<userareabean.arylist1.size();i++){
  		UserAreaBean tempBean = userareabean.arylist1.get(i);
   %>
  <div id="TabTab03Con<%=i+1 %>">
  <table width="100%" border="0" cellspacing="10" cellpadding="10" class="table14 select_table">
  <% int tdcount=0;
  		 for(int b =0;b<userareabean.arylist2.size();b++){
  			UserAreaBean tempBean2 = userareabean.arylist2.get(b); 
  			if(tempBean2.userarea_notuser1.equals(tempBean.userarea_id)){
  				if(tdcount==0){		
  %>
  			
  <tr>
 <%			} %>
      <td valign="top" width="50%"><label class="select_tab hand" id="<%=tempBean2.userarea_id %>" onClick="returnparhtml('<%=tempBean2.userarea_id %>','<%=tempBean.userarea_name %>：<%=tempBean2.userarea_name %>')"><u><%=tempBean2.userarea_name %></u></label></td>
 
 <%			if(tdcount==1){ %>
  </tr>
<%			} %>

  <%		tdcount++;
  				tdcount=tdcount==2?0:tdcount;	
  			} %>
  <%} %>
 <%		if(tdcount==1){ %>
 			<td>&nbsp;</td></tr>
 <%		} %>			
  </table>
  </div>
  
  <%} %>
</div>

</body>
</html>