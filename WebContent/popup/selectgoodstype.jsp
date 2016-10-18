<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="supma.db.*,java.util.*,supma.beans.*,supma.common.*"%>
<jsp:useBean id="goodsTypeBean" scope="page" class="supma.beans.GoodsTypeBean" />
<jsp:useBean id="basicDataBase" scope="page" class="supma.db.BasicDatabase" />
<%userBean ufo = (userBean)session.getAttribute("loginUserInfo") ; //写log用的userBean%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=CommonWenZi.SiteName %> -选择配送货物类型</title>
<meta http-equiv="x-ua-compatible" content="ie=Edge" />
<meta name="keywords" content="<%=CommonWenZi.SiteName %> -选择配送货物类型" />
<meta name="description" content="<%=CommonWenZi.user_arr_meta_description %>" />


<link href="css/userarea.css" rel="stylesheet" type="text/css"  />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/lr.css" rel="stylesheet" type="text/css"  />

<script type="text/javascript">
function returnparhtml() {
var str="#";
var str2="";
var objs = document.getElementsByName("mybox");
var objs2 = document.getElementsByName("namehidden");
var countsum=1;//只能选择15个以内
 for(var i=0; i<objs.length; i++){
         if(objs[i].checked == true){
         	  if(countsum >= 15){
         	  	break;
         	  }
               str+=objs[i].value+"<%=CommonKey.jinghao%>";
			   str2+=objs2[i].value;
			   countsum++;
         }
 
 }
 	window.opener.document.formpersonal.u_goodstype.value=str;
	window.opener.document.getElementById('u_goodstype_error').innerHTML='&nbsp;';
 	if(str==""){
 		str2='您还未选择送货片区，请点"选择片区“按钮，认真选择您的可送货片区，以便超市用户能够搜索到您。';
  		//window.opener.document.getElementById('user_notuser1').style.color='#8B7D6B';		
 	}else{
 		//window.opener.document.getElementById('user_notuser1').style.color='#000000';
 	}
	window.opener.document.getElementById('user_notuser1').innerHTML=str2;
	window.close();
}

function clearselect() {
	var objs = document.getElementsByName("mybox");
 	for(var i=0; i<objs.length; i++){
		objs[i].checked = false;
 	}
}
function initcheckbox() {//初始化时候把checkbox的勾按照前页的值选上
	var parevalue=window.opener.document.formpersonal.u_goodstype.value;
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


<body onload="initcheckbox();">

<div class="ttab_zhengce">
				<div class="dl_zc_title">
					<h2 class="f_l">选择可配送货物品种</h2>（选择您可以送货的货物品种。)
					<div class="rt_bg f_r"></div>
				</div>
		
  <div >
  <table width="100%" border="0" cellspacing="10" cellpadding="10" class="table14 select_table">
    <%
		goodsTypeBean.initGoodsTypeBean(basicDataBase.executeSql("index.jsp","select",goodsTypeBean.getAllGoodsTypeSql(),null,null,null,null,null,ufo)); 
		if(basicDataBase.pstm !=null){
			basicDataBase.pstm.close();
			basicDataBase.pstm=null;
		}
		if(basicDataBase.conn != null){
			basicDataBase.conn.close();
			basicDataBase.conn=null;
		}
		Iterator iter =	goodsTypeBean.list1.entrySet().iterator();
		int tdcount=0;
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String)entry.getKey();
			GoodsTypeBean val = (GoodsTypeBean)entry.getValue();
  			if(tdcount==0){
%>
 	<tr>
 <%		} %>
      <td valign="top" width="50%"><label class="select_tab hand" ><input type="checkbox"  class="text" name="mybox" value='<%=val.goods_type_id %>' />&nbsp;&nbsp;<u><%=val.goods_type_name %></u></label>
      	<input type="hidden" name="namehidden"  value='<%=val.goods_type_name %>　▎'/>
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