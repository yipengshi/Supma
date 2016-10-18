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
<title><%=CommonWenZi.SiteName %> - 新批发商注册</title>
<meta http-equiv="x-ua-compatible" content="ie=Edge" />
<meta name="keywords" content="会员登录" />
<meta name="description" content="<%=CommonWenZi.regist_meta_description %>" />

<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/lr.css" rel="stylesheet" type="text/css"  />

<script language="javaScript" src="js/md5.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript" src="js/jquery-imgslideshow.js"></script>
<script type="text/javascript" src="js/ks-switch.js"></script>
<script type="text/javascript" src="js/lib.js"></script>
  <script type="text/javascript">
        $(window).load(function() {
        	document.getElementById('viewfile').value=document.getElementById('user_notuser2').value;
        });
        $(window).load(function() {

        });
        //时间戳    
//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳    
function chgUrl(url){    
    var timestamp = (new Date()).valueOf();   
    urlurl = url.substring(0,15);
	if((url.indexOf("?")>=0)){    
        urlurl = url + timestamp;    
    }else{    
        urlurl = url + "?timestamp=" + timestamp;    
    }    
    return urlurl;    
}
 function changeImg(){    
    var imgSrc = $("#JD_Verification1");    
    var src = imgSrc.attr("src");    
    imgSrc.attr("src",chgUrl(src));    
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
    <jsp:include page="inc/title1.jsp" flush="true" />
	</div><!--s_hdw end-->

	

	
	<div id="s_bdw">
		<div id="s_bd">
			
			<div class="dl_zc">
				<div class="dl_zc_title">
					<h2 class="f_l">新批发商注册</h2>
					<div class="rt_bg f_r"></div>
				</div>
				<div class="dl-con" id="regist">
					<form id="formpersonal"  name ="formpersonal" method="post" onsubmit="return false;">
						<div class="form">
							<div class="tipinfo"><h2 class="f_l">填写账户信息，带“※”为必填项目</h2></div>
							<div class="item">
								<span class="label">※用户登录ID：</span>
		
								<div class="fl">
									<input type="text" id="username" name="username" class="text" tabindex="<%=tabindex++ %>" />
		
									<label id="username_succeed" class="blank"></label>
									<span class="clear"></span>
		
									<div id="username_error"></div>
								</div>
							</div>
							<!--item end-->
							
							<div id="o-password">
								<div class="item">
		
									<span class="label">※设置密码：</span>
		
									<div class="fl">
										<input type="password" id="pwd" name="pwd" class="text" tabindex="<%=tabindex++ %>" />
										<label id="pwd_succeed" class="blank"></label>
										<span class="clear"></span>
		
										<label class="hide" id="pwdstrength"><span class="fl">安全程度：</span><b></b></label>
										<label id="pwd_error"></label>
									</div>
								</div>
								<!--item end-->
								<div class="item">
									<span class="label">※确认密码：</span>
		
									<div class="fl">
										<input type="password" id="pwd2" name="pwd2" class="text" tabindex="<%=tabindex++ %>" />
										<label id="pwd2_succeed" class="blank"></label>
										<span class="clear"></span>
										<label id="pwd2_error"></label>
									</div>
								</div>
								<!--item end-->
							</div>

							<div class="item">
								<span class="label">※批发商全名：</span>
		
								<div class="fl">
									<input type="text" id="userallname" name="userallname" class="text"  style="width:320px;" tabindex="<%=tabindex++ %>" />
		
									<label id="userallname_succeed" class="blank"></label>
									<span class="clear"></span>
		
									<div id="userallname_error"></div>
								</div>
							</div>
							<!--item end-->
		
							<div class="item">
								<span class="label">※批发商公司地址：</span>
		
								<div class="fl">
									<input type="text"  id="user_address1" name="user_address1" class="text"  style="width:580px;" tabindex="<%=tabindex++ %>" />
		
									<label id="user_address1_succeed" class="blank"></label>
									<span class="clear"></span>
		
									<div id="user_address1_error"></div>
								</div>
							</div>
							<!--item end-->		
														
							<div class="item">
								<span class="label">※联系人手机号码：</span>
		
								<div class="fl">
									<input type="text" id="mobo" name="mobo" class="text" style="ime-mode:disabled;width:120px;"  tabindex="<%=tabindex++ %>" />
									<label id="mobo_succeed" class="blank"></label>
									<span class="clear"></span>
		
									<div id="mobo_error"></div>
								</div>
							</div>
							
							<div class="item">
								<span class="label">※固定电话号码：</span>
		
								<div class="fl">
									<input type="text" id="telno" name="telno" class="text"  style="width:120px;" tabindex="<%=tabindex++ %>" />
									<label id="tel_succeed" class="blank"></label>
									<span class="clear"></span>
		
									<div id="telno_error"></div>
								</div>
							</div>							
							<!--item end-->	
							
							<div class="item">
								<span class="label">※邮箱：</span>
		
								<div class="fl">
									<input type="text" id="mail" name="mail" class="text" tabindex="<%=tabindex++ %>" />
									<label id="mail_succeed" class="blank"></label>
									<span class="clear"></span>
		
									<div id="mail_error"></div>
								</div>
							</div>
							<!--item end-->

							<div class="item">
								<span class="label">※上传营业执照照片：</span>
		
								<div class="fl">
								
								    	<span class="span">
								        	<input name="viewfile" type="text" id="viewfile" readonly="readonly"  style="color:#8B7D6B;"  onmouseout="document.getElementById('user_notuser2').style.display='none';" class="inputstyle" />
								        </span>
								        <label for="user_notuser2" onmouseover="document.getElementById('user_notuser2').style.display='block';" class="file1"  tabindex="<%=tabindex++ %>">选择照片...</label>
								        <input type="file" onchange="document.getElementById('viewfile').value=this.value;this.style.display='none';document.getElementById('viewfile').focus();" class="file" id="user_notuser2"  name="user_notuser2"'    />
		
									<label id="viewfile_succeed" class="blank"></label>
									<span class="clear"></span>
		
									<div id="viewfile_error"></div>
								</div>
							</div>
							<!--item end-->		
							
							<div class="item2">
								<span class="label">※批发商可送货片区(可多选)：</span>
								<input type="hidden" name="u_area" id="u_area"  value=""/>
								<div class="fl">
								<label  onclick="JavaScript:window.open('userarea2.aspx','','directorys=no,toolbar=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=920,height=500,top=175,left=160');" class="file2"  tabindex="<%=tabindex++ %>">选择片区...</label>									
								<div id="u_area_error">&nbsp;</div>
									<div >
									<div id="regnousediv"><br /></div>
									<div class="item3">
									<span class="blank"><strong>您选择的可送货片区是</strong>(最多选100个，超过100个无效)<strong>：</strong><br /></span>
									<span class="blank2" id ="user_address2">您还未选择送货片区，请点"选择片区“按钮，认真选择您的可送货片区，以便超市用户能够搜索到您。</span>
									</div>
									</div>
									<label id="u_area_succeed" class="blank"></label>
									<span class="clear"></span>
								</div>
							</div>
							<!--item end-->
							
							<div class="item4">
								<span class="label">※批发商可配送商品种类(可多选)：</span>
								<input type="hidden" name="u_goodstype" id="u_goodstype"  value=""/>
								<div class="fl">
								<label  onclick="JavaScript:window.open('selectgoods.aspx','','directorys=no,toolbar=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=920,height=500,top=175,left=160');" class="file3"  tabindex="<%=tabindex++ %>">选择品类...</label>									
								<div id="u_goodstype_error">&nbsp;</div>
									<div >
									<div><br /></div>
									<div class="item5">
									<span class="blank"><strong>您选择的可送货商品种类是</strong>(最多选15个，超过15个无效)<strong>：</strong><br /></span>
									<span class="blank2" id ="user_notuser1">您还未选择可送货商品种类，请点"选择品类“按钮，认真选择您的可送货商品种类，以便超市用户能够搜索到您。</span>
									</div>
									</div>
									<label id="u_goodstype_succeed" class="blank"></label>
									<span class="clear"></span>
								</div>
							</div>
							<!--item end-->
														
				<div class="item">
								<span class="label">※验证码：</span>
		
								<div class="fl">
									<input type="text" id="authcode" name="authcode" class="text text-1" tabindex="<%=tabindex++ %>" 
										   autocomplete="off" MaxLength="4"/>
									<label class="img">
										<img id="JD_Verification1"  Ver_ColorOfNoisePoint="#888888"
											 src="validimage.goods"
											 alt="" style="cursor:pointer;width:80px;height:20px;border:10px;" onclick="changeImg();" />
									</label>
									<label>&nbsp;看不清？点击验证码图片更换</label>
									<label id="authcode_succeed" class="blank invisible"></label>
									<span class="clear"></span>
		
									<label id="authcode_error"></label>
								</div>
						</div>
						<!--item end-->
							
							<div class="item">
								<span class="label">&nbsp;</span>
		
								<div class="fl">
									<input type="checkbox" name="" id="xieyi" value=""  checked="checked"/><label class="blue" for="xieyi"  tabindex="<%=tabindex++ %>" >&nbsp;自愿遵守<%=supma.common.CommonWenZi.SiteName %>服务协议</label>
									<label id="xieyi_succeed" class="blank"></label>
									<span class="clear"></span>
									<div id="xieyi_error"></div>
								</div>
							</div>
							<!--item end-->
							
							<div class="item">
								<span class="label">&nbsp;</span>
								<input type="button" class="btnimg"   id="registsubmit" value="" tabindex="<%=tabindex++ %>" />
							</div>
							<!--item end-->
		
						</div>
					</form>
				</div><!--regist end-->
			</div><!--dl_zc end-->
			
			<script type="text/javascript" src="js/Validate_reg2.js"></script>
			<script type="text/javascript" src="js/Validate.personal_reg2.js"></script>

		</div><!--s_bd end-->
	</div><!--s_bdw end-->	
	
    <jsp:include page="inc/foot1.jsp" flush="true" />


</div>




</body>
</html>
