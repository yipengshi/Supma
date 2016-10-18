<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="supma.db.*,java.util.*,supma.beans.*,supma.common.*"%>
<jsp:useBean id="goodsTypeBean" scope="page" class="supma.beans.GoodsTypeBean" />
<jsp:useBean id="basicDataBase" scope="page" class="supma.db.BasicDatabase" />
<%userBean ufo = (userBean)session.getAttribute("loginUserInfo") ; //写log用的userBean%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=CommonWenZi.SiteName %> -选择搭赠品</title>
<meta http-equiv="x-ua-compatible" content="ie=Edge" />
<meta name="keywords" content="<%=CommonWenZi.SiteName %> -选择搭赠品" />
<meta name="description" content="<%=CommonWenZi.user_arr_meta_description %>" />


<link href="css/userarea.css" rel="stylesheet" type="text/css"  />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/lr.css" rel="stylesheet" type="text/css"  />

<script type="text/javascript">
function setTab03Syn(i,all) {
  selectTab03Syn(i,all);
}
function selectTab03Syn(i,all) {
	for(var m=1;m<=all;m++){
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
 for(var i=0; i<objs.length; i++){
         if(objs[i].checked == true){
               str=objs[i].value;
			   str2=objs2[i].value;
			   break;
         }
 
 }
 	window.opener.document.formpersonal.goods_dazeng1.value=str;
	window.opener.document.getElementById('goods_dazeng1_error').innerHTML='&nbsp;';
 	if(str==""){
 		str2="";
  		//window.opener.document.getElementById('user_address2').style.color='#8B7D6B';		
 	}else{
 		//window.opener.document.getElementById('user_address2').style.color='#000000';
 	}
	window.opener.document.getElementById('goods_dazeng1_name').innerHTML="<strong>【&nbsp;&nbsp;"+str2+"&nbsp;&nbsp;】</strong>";
	window.close();
}

function clearselect() {
	var objs = document.getElementsByName("mybox");
 	for(var i=0; i<objs.length; i++){
		objs[i].checked = false;
 	}
}
function initcheckbox() {//初始化时候把checkbox的勾按照前页的值选上
	var parevalue=window.opener.document.formpersonal.goods_dazeng1.value;
	if(parevalue !=""){
		var objs = document.getElementsByName("mybox");
		for(var j=0; j<objs.length; j++){
			if(objs[j].value==parevalue){
				objs[j].checked=true;
			}
		}
	}else{
		//
	}
}
</script>


</head>

  <%
  //第1层级别的商品类型
  	LinkedHashMap<String,GoodsTypeBean> goodsTypeListData = (LinkedHashMap<String,GoodsTypeBean>)request.getServletContext().getAttribute(CommonKey.goodsTypeListData_1) ; 
	ArrayList <GoodsTypeBean> goodstypelist = userBean.getGoodsTypeName(goodsTypeListData,ufo);
	GoodsBean goods = new GoodsBean();
	ArrayList<GoodsBean> dazengList = goods.getDaZengPinList(ufo);
	ArrayList <GoodsTypeBean> newtypeList = goods.getGoodsTypes1(dazengList,goodstypelist);
  %>
<body onload='selectTab03Syn(1,<%=newtypeList.size()%>);initcheckbox();' >

		
<div class="ttab_zhengce">
				<div class="dl_zc_title">
					<h2 class="f_l">请选择搭赠品</h2>
					<div class="rt_bg f_r"></div>
				</div>
  <%
  	for (int i=0;i<newtypeList.size();i++){
  		GoodsTypeBean tempBean = newtypeList.get(i);
   %>
  <div id="ttab<%=i+1 %>" class="ttab_on" onclick="setTab03Syn(<%=i+1 %>,<%=newtypeList.size()%>)"><%=tempBean.goods_type_name %></div>
  <%} %>
  <div class="clearall"></div>
  <%
  	for (int i=0;i<newtypeList.size();i++){
  		 GoodsTypeBean tempBean = newtypeList.get(i);
   %>
  <div id="TabTab03Con<%=i+1 %>">
  <table width="100%" border="0" cellspacing="10" cellpadding="10" class="table14 select_table">
  <% int tdcount=0;
  		 for(int b =0;b<dazengList.size();b++){
  			GoodsBean tempBean2 = dazengList.get(b); 
  			if(tempBean2.goods_type_id_lv1.equals(tempBean.goods_type_id)){
  				if(tdcount==0){
  %>
  			
  <tr>
 <%			} %>
      <td valign="top" width="50%"><label class="select_tab hand" ><input type="radio"  class="text" name="mybox" value='<%=tempBean2.goods_id %>' />&nbsp;&nbsp;<u><%=tempBean2.goods_name %></u></label>
      	<input type="hidden" name="namehidden"  value='<%=tempBean.goods_type_name %>：<%=tempBean2.goods_name %>'/>
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
  
  <%
  		if(newtypeList==null || newtypeList.size()==0){
   %>
   <br /><br />
   	您还没有录入搭赠品。请在“商品输入”栏目录入搭赠商品后，重新选择搭赠品。
   	<br /><br />
   <%
   		}
    %>
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