<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="supma.db.*,java.util.*,supma.beans.*,supma.common.*"%>
<%userBean ufo = (userBean)session.getAttribute("loginUserInfo") ; //写log用的userBean%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=CommonWenZi.SiteName %> -批发商可送货片区选择</title>
<meta http-equiv="x-ua-compatible" content="ie=Edge" />
<meta name="keywords" content="<%=CommonWenZi.SiteName %> -批发商可送货片区选择" />
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
function returnparhtml() {
var str="";
var str2="";
var objs = document.getElementsByName("mybox");
var objs2 = document.getElementsByName("namehidden");
var countsum=1;//只能选择100个以内
 for(var i=0; i<objs.length; i++){
         if(objs[i].checked == true){
         	  if(countsum >= 100){
         	  	break;
         	  }
               str+=objs[i].value+"<%=CommonKey.jinghao%>";
			   str2+=objs2[i].value;
			   countsum++;
         }
 
 }
 	window.opener.document.formpersonal.u_area.value=str;
	window.opener.document.getElementById('u_area_error').innerHTML='&nbsp;';
 	if(str==""){
 		str2='您还未选择送货片区，请点"选择片区“按钮，认真选择您的可送货片区，以便超市用户能够搜索到您。';
  		//window.opener.document.getElementById('user_address2').style.color='#8B7D6B';		
 	}else{
 		//window.opener.document.getElementById('user_address2').style.color='#000000';
 	}
	window.opener.document.getElementById('user_address2').innerHTML=str2;
	window.close();
}

function clearselect() {
	var objs = document.getElementsByName("mybox");
 	for(var i=0; i<objs.length; i++){
		objs[i].checked = false;
 	}
}
function initcheckbox() {//初始化时候把checkbox的勾按照前页的值选上
	var parevalue=window.opener.document.formpersonal.u_area.value;
	if(parevalue !=""){
		var strs= new Array();
		var objs = document.getElementsByName("mybox");
		strs=parevalue.split("#");
		for (i=0;i<strs.length ;i++ ){
			for(var j=0; j<objs.length; j++){
				if(objs[j].value==strs[i]){
					objs[j].checked=true;
				}
			}
		}
	}else{
		//
	}
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
<body onload='selectTab03Syn(1,<%=userareabean.arylist1.size()%>);initcheckbox();' >

		
<div class="ttab_zhengce">
				<div class="dl_zc_title">
					<h2 class="f_l">可送货片区选择</h2>（选择您可以送货的片区。最多选择100个。)
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
      <td valign="top" width="50%"><label class="select_tab hand" ><input type="checkbox"  class="text" name="mybox" value='<%=tempBean2.userarea_id %>' />&nbsp;&nbsp;<u><%=tempBean2.userarea_name %></u></label>
      	<input type="hidden" name="namehidden"  value='<%=tempBean.userarea_name %>：<%=tempBean2.userarea_name %>　▎'/>
      </td>
 
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
  					<br />
         			<button type="button" class="positive" name="inputnew" onclick="returnparhtml();">
        			<img src="./images/apply2.png" alt=""/> 
        			<b><font color="#104E8B">确定你的选择</font></b>
   				 </button>
   				 &nbsp;&nbsp;
   				  <button type="button" class="positive" name="inputnew"  onclick="clearselect();">
        			<img src="./images/cross.png" alt=""/> 
        			<b><font color="#104E8B">清除你的选择</font></b>
   				 </button>
   				 
</div>

</body>
</html>