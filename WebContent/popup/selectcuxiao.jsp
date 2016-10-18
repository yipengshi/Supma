<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="supma.db.*,java.util.*,supma.beans.*,supma.common.*"%>
<jsp:useBean id="goodsTypeBean" scope="page" class="supma.beans.GoodsTypeBean" />
<jsp:useBean id="basicDataBase" scope="page" class="supma.db.BasicDatabase" />
<%userBean ufo = (userBean)session.getAttribute("loginUserInfo") ; //写log用的userBean%>
<%if(ufo==null || !userBean.isDisplay("goods_input.jsp",ufo)){%>
<script type="text/javascript">
 	var nologinObj = new Object();
 	nologinObj.error="notlogin";
	window.returnValue=nologinObj;
	window.close();
</script>
<% 	}%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=CommonWenZi.SiteName %> -选择促销种类</title>
<meta http-equiv="x-ua-compatible" content="ie=Edge" />
<meta name="keywords" content="<%=CommonWenZi.SiteName %> -选择促销种类" />
<meta name="description" content="<%=CommonWenZi.user_arr_meta_description %>" />
<%-- 防止缓存--%>
<meta  http-equiv="pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">

<link href="css/userarea.css" rel="stylesheet" type="text/css"  />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/lr.css" rel="stylesheet" type="text/css"  />

<script type="text/javascript">
function returnparhtml() {
var str="";
var str2="";
var objs = document.getElementsByName("mybox");
var objs2 = document.getElementsByName("namehidden");
var countsum=1;//只能选择5个以内
 for(var i=0; i<objs.length; i++){
         if(objs[i].checked == true){
         	  if(countsum >= 5){
         	  	break;
         	  }
               str+=objs[i].value+"<%=CommonKey.jinghao%>";
			   str2+=objs2[i].value;
			   countsum++;
         }
 
 }
 
   	if(str==""){
 		str2="您还未选择促销活动。";
 	}else{
 		str2="<strong>【&nbsp;&nbsp;"+str2+"&nbsp;&nbsp;】</strong>";
 	}
  var returnObj = new Object();
 returnObj.value=str;
 returnObj.errorhtml="&nbsp;";
 returnObj.name=str2;
 window.returnValue = returnObj;
 self.close();
}

function clearselect() {
	var objs = document.getElementsByName("mybox");
 	for(var i=0; i<objs.length; i++){
		objs[i].checked = false;
 	}
}
function initcheckbox(parevalue) {//初始化时候把checkbox的勾按照前页的值选上
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
function getArguments() {
	var obj = window.dialogArguments;
	initcheckbox(obj.radiovalue);
}

function windowclose() {
//这个是直接关窗口×的方法，现在什么也不做<body onunload>
}
</script>


</head>


<body onload="getArguments();"  onunload="windowclose();">

<div class="ttab_zhengce">
				<div class="dl_zc_title">
					<h2 class="f_l">选择本商品参加的促销活动</h2>（可多选，最多选择5个。超过5个的部分无效)
					<div class="rt_bg f_r"></div>
				</div>
		
  <div >
  <table width="100%" border="0" cellspacing="10" cellpadding="10" class="table14 select_table">
    <%
	CuxiaoBean cuxiao = new CuxiaoBean();
	ArrayList<CuxiaoBean> cuxiaoList = cuxiao.getCuXiaoList(ufo);
		int tdcount=0;
		for(int i=0;i<cuxiaoList.size();i++){
			CuxiaoBean val = cuxiaoList.get(i);
  			if(tdcount==0){
%>
 	<tr>
 <%		} %>
      <td valign="top" width="50%"><label class="select_tab hand" ><input type="checkbox"  class="text" name="mybox" value='<%=val.getCuxiao_id() %>' />&nbsp;&nbsp;<u><%=val.getCuxiao_name() %></u></label>
      	<input type="hidden" name="namehidden"  value='<%=val.getCuxiao_name() %>　▎'/>
      </td>
 
 <%			if(tdcount==2){ %>
 	 </tr>
<%			} %>

  <%		tdcount++;
  				tdcount=tdcount==2?0:tdcount;
  		}//end while
   %>
 <%		if(tdcount==1){ %>
 			<td>&nbsp;</td></tr>
 <%		} %>		
  </table>
  </div>

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