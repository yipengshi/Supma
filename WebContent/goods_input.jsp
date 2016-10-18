<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="supma.db.*"%>
<%@page import="java.util.*"%>
<%@page import="supma.beans.*"%>
<%@page import="supma.util.*"%>
<%@page import="supma.common.*"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<jsp:useBean id="goodsTypeBean" scope="page" class="supma.beans.GoodsTypeBean" />
<jsp:useBean id="basicDataBase" scope="page" class="supma.db.BasicDatabase" />
<%userBean ufo = (userBean)session.getAttribute("loginUserInfo") ; //写log用的userBean%>
<%if(ufo==null || !userBean.isDisplay("goods_input.jsp",ufo)){
		response.sendRedirect("notlogin.aspx");
	}
 %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=CommonWenZi.SiteName %> - 批发商商品信息输入</title>
<meta http-equiv="x-ua-compatible" content="ie=Edge" />
<meta name="keywords" content="批发商商品信息输入" />
<meta name="description" content="<%=CommonWenZi.Index_meta_description %>" />

<link href="css/main.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="css/my.css" rel="stylesheet" />
<link href="css/lr.css" rel="stylesheet" type="text/css"  />

<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery-imgslideshow.js"></script>
<script type="text/javascript" src="js/ks-switch.js"></script>
<script type="text/javascript" src="js/lib.js"></script>
<script type="text/javascript" src="js/goodsInput_Validate.personal.js"></script>
<%//String goodsTypeListData = (String)request.getAttribute(CommonKey.goodsTypeListData) ; //商品类型三层下拉列表的数据
	LinkedHashMap<String,GoodsTypeBean> lsx1 = (LinkedHashMap<String,GoodsTypeBean>)request.getServletContext().getAttribute(CommonKey.goodsTypeListData_1);
	LinkedHashMap<String,GoodsTypeBean> lsx2 = (LinkedHashMap<String,GoodsTypeBean>)request.getServletContext().getAttribute(CommonKey.goodsTypeListData_2);
	LinkedHashMap<String,GoodsTypeBean> lsx3 = (LinkedHashMap<String,GoodsTypeBean>)request.getServletContext().getAttribute(CommonKey.goodsTypeListData_3);
	String goodsTypeListData =GoodsTypeBean.getGoodsTypeJosn(lsx1,lsx2,lsx3,ufo);
%>
<%
	//取得request的error信息和页面提交值
	GoodsBean uicontentGoods = (GoodsBean)request.getAttribute("uicontent")==null?new GoodsBean():(GoodsBean)request.getAttribute("uicontent");
	String lv1selected =StringUtils.isEmpty(uicontentGoods.goods_type_id_lv1)?"1":uicontentGoods.goods_type_id_lv1;
	String lv2selected =StringUtils.isEmpty(uicontentGoods.goods_type_id_lv2)?"1":uicontentGoods.goods_type_id_lv2;
	String lv3selected =StringUtils.isEmpty(uicontentGoods.goods_type_id)?"1":uicontentGoods.goods_type_id;
	LinkedHashMap<String,String> validateresult = (LinkedHashMap<String,String>)request.getAttribute(CommonKey.requestScopeerrorMessage);
	//取得request的成功信息
	String successMessageContent =  (String)request.getAttribute(CommonKey.submitsuccesskey)==null?"":(String)request.getAttribute(CommonKey.submitsuccesskey);
 %>

  <script type="text/javascript">
  		var lv1selected=1;
  		var lv2selected=1;
  		var lv3selected=1;
  		var goodsTypeListData;
          $(window).load(function() {
        	goodsTypeListData=<%=goodsTypeListData%>;
        	lv1selected =<%=lv1selected%>;
  			lv2selected =<%=lv2selected%>;
  			lv3selected =<%=lv3selected%>;
        	init();
        });
        $(window).load(function() {

        	selectPrice();
        });
        $(window).load(function() {
        	window.location.hash = "#regist";
        });        
</script>

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
					<%--<dd><a href="inputgoods.goods?action=init" class="current">商品录入</a></dd> --%>
					<dd><a href="inputgoods.aspx" class="current">商品录入</a></dd>
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

		
				<div class="dl_zc_title" >
					<h2 class="f_l">商品录入(带※为必填项目) </h2>
					<div class="rt_bg f_r"></div>
				</div>
	<form id="formpersonal"  name ="formpersonal" method="post" onsubmit="return false;">					
				<div class="f-r presonalinfo" id="regist">	
					<div class="ddtable">
						<table class="ddinfo" width="100%">
						
<%
		  	 if(validateresult!=null && validateresult.size()>0){
		  	 	   Iterator iter =validateresult.entrySet().iterator();
 %>
								<tr>
								<td  colspan="2">
<%
					while (iter.hasNext()) {
		  	 	   		Map.Entry entry = (Map.Entry) iter.next();
 %>
									<span ><img src='images/exclamation.png' width='16' height='16' />&nbsp;&nbsp;</span><span style="color:#FF3030"><%=(String)entry.getValue() %> </span>
<%
				}
 %>
								</td>
							</tr>
<%
	}
 %>
 
<%
	if(successMessageContent!=null && !"".equals(successMessageContent)){
 %>
								<tr>
								<td  colspan="2">
									<span ><img src='images/addicon.gif' width='16' height='16' />&nbsp;&nbsp;</span><span style="color:#FF3030"><%=successMessageContent %> </span>
								</td>
							</tr>
<%
	}
 %>
													
							<tr>
								<td ><span style="color:#FF1493"><strong><br /><br />以下带※为必填项目</strong></span></td>
								<td>
									<input type="button" class="copynextbutton"   id="copy_next" name="copy_next" onclick="submitGoods('1');"   tabindex="<%=tabindex++ %>" />
									<input type="button" class="nocopynextbutton"  onclick="submitGoods('2');"  id="notcopy_next"  name="notcopy_next"  tabindex="<%=tabindex++ %>" />
								</td>
							</tr>
			
							<tr>
								<td width="290">
									<label for="goods_name"  id="goods_name_lb"><strong>※商品名称：</strong><span style="color:#71C671;">(例：康师傅3+2饼干)</span></label>
								</td>
								<td >
									<input type="text" id="goods_name" name="goods_name"  class="text2"  size="45"  maxlength="40" value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_name) %>" tabindex="<%=tabindex++ %>" />
									<label id="goods_name_succeed" class="blank"></label>
									<span id="goods_name_error"  class="error"></span>
								</td>
							</tr>
							
							<tr>
								<td>
								 	<label for="goods_pinpai"  id="goods_pinpai_lb"><strong>※商品品牌：</strong><span style="color:#71C671;">(例：康师傅)</span></label>
								</td>
								<td>
									<input type="text" id="goods_pinpai" name="goods_pinpai"  class="text2"  size="20"  maxlength="10" value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_pinpai) %>"   tabindex="<%=tabindex++ %>" />
									<label id="goods_pinpai_succeed" class="blank"></label>
									<span id="goods_pinpai_error"  class="error"></span>
								</td>
							</tr>
							
							<tr>
								<td>
								 	<label for="goods_zhongliang" id="goods_zhongliang_lb"><strong>※包装重量(克)：</strong><span style="color:#71C671;">(例：1500)</span></label>
								</td>
								<td>
									<input type="text" id="goods_zhongliang" name="goods_zhongliang"  class="text2"  size="8"   maxlength="8"  value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_zhongliang) %>" tabindex="<%=tabindex++ %>" />
									<label id="goods_zhongliang_succeed" class="blank"></label>
									<span id="goods_zhongliang_error"  class="error"></span>									
								</td>
							</tr>
							
							<tr>
								<td>
								 	<label for="goods_kucunshu" id="goods_kucunshu_lb" ><strong>※库存数量(大于0)：</strong><span style="color:#71C671;">(例：100)</span></label>
								</td>
								<td>
									<input type="text" id="goods_kucunshu" name="goods_kucunshu"  class="text2"  size="8"  maxlength="8"  value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_kucunshu) %>" tabindex="<%=tabindex++ %>" />
									<label id="goods_kucunshu_succeed" class="blank"></label>
									<span id="goods_kucunshu_error"  class="error"></span>											
								</td>
							</tr>

							<tr>
								<td>
								 	<label for="goods_baozhi_date" id="goods_baozhi_date_lb" ><strong>※保质期：</strong><span style="color:#71C671;">(例：180)</span></label>
								</td>
								<td>
									<input type="text" id="goods_baozhi_date" name="goods_baozhi_date"  class="text2"  size="8"  maxlength="4"  value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_baozhi_date) %>" tabindex="<%=tabindex++ %>" />
									<label id="goods_baozhi_date_succeed" class="blank"></label>
									<span id="goods_baozhi_date_error"  class="error"></span>											
								</td>
							</tr>						

							<tr>
								<td>
								 	<label for="goods_pifajiage1" id="goods_pifajiage1_lb" ><strong>※商品批发价格1：</strong><span style="color:#71C671;">(批发价与订货数量无关时，选择“固定价”，并且只填这一个价格)</span></label>
								</td>
								<td>
									订货数量为：
									<select  id="goods_pifashuliang1" name="goods_pifashuliang1"  onchange="selectPrice();"    tabindex="<%=tabindex++ %>" >
<%
									for(int i=0;i<CommonKey.pifashuliang1Value.length;i++){
										String selected = "";
										if(CommonKey.pifashuliang1Value[i].equals(BasicHtmlUtil.replaceTag(uicontentGoods.goods_pifashuliang1))){
											selected="selected=\"selected\"";
										}
 %>									
										<option value="<%=CommonKey.pifashuliang1Value[i] %>"  <%=selected %> ><%=CommonKey.pifashuliang1Name[i] %></option>		
<%
									}
 %>																										
									</select>
									时，批发价：
									<input type="text" id="goods_pifajiage1" name="goods_pifajiage1"  class="text2"  size="10"  maxlength="8" value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_pifajiage1) %>" tabindex="<%=tabindex++ %>" />
									<label id="goods_pifajiage1_succeed" class="blank"></label>
									<span id="goods_pifajiage1_error"  class="error"></span>											
								</td>
							</tr>			
							

							<tr>
								<td>
								 	<label for="goods_pifajiage2" id="goods_pifajiage2_lb" >商品批发价格2：<span style="color:#71C671;">(可不填)</span></label>
								</td>
								<td>
									订货数量为：
									<select  id="goods_pifashuliang2" name="goods_pifashuliang2"   " tabindex="<%=tabindex++ %>" >
<%
									for(int i=0;i<CommonKey.pifashuliang2Value.length;i++){
										String selected = "";
										if(CommonKey.pifashuliang2Value[i].equals(BasicHtmlUtil.replaceTag(uicontentGoods.goods_pifashuliang2))){
											selected="selected=\"selected\"";
										}
 %>	
 										<option value="<%=CommonKey.pifashuliang2Value[i] %>"  <%=selected %> ><%=CommonKey.pifashuliang2Name[i] %></option>		
<%
									}
 %>			
									</select>
									时，批发价：
									<input type="text" id="goods_pifajiage2" name="goods_pifajiage2"  class="text2"  size="10"  maxlength="8"   value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_pifajiage2) %>"  tabindex="<%=tabindex++ %>" />
									<label id="goods_pifajiage2_succeed" class="blank"></label>
									<span id="goods_pifajiage2_error"  class="error"></span>											
								</td>
							</tr>	
	
							<tr>
								<td>
								 	<label for="goods_pifajiage3" id="goods_pifajiage3_lb" >商品批发价格3：<span style="color:#71C671;">(可不填)</span></label>
								</td>
								<td>
									订货数量为：
									<select  id="goods_pifashuliang3" name="goods_pifashuliang3"     tabindex="<%=tabindex++ %>" >
<%
									for(int i=0;i<CommonKey.pifashuliang3Value.length;i++){
										String selected = "";
										if(CommonKey.pifashuliang3Value[i].equals(BasicHtmlUtil.replaceTag(uicontentGoods.goods_pifashuliang3))){
											selected="selected=\"selected\"";
										}
 %>	
 										<option value="<%=CommonKey.pifashuliang3Value[i] %>"  <%=selected %> ><%=CommonKey.pifashuliang3Name[i] %></option>		
<%
									}
 %>			
									</select>
									时，批发价：
									<input type="text" id="goods_pifajiage3" name="goods_pifajiage3"  class="text2"  size="10"  maxlength="8"   value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_pifajiage3) %>"   tabindex="<%=tabindex++ %>" />
									<label id="goods_pifajiage3_succeed" class="blank"></label>
									<span id="goods_pifajiage3_error"  class="error"></span>											
								</td>
							</tr>					
							
							<tr>
								<td>
								 	<label for="goods_jiage" id="goods_jiage_lb" ><strong>※建议零售价：</strong><span style="color:#71C671;">(超市零售价。例：2.5)</span></label>
								</td>
								<td>
									<input type="text" id="goods_jiage" name="goods_jiage"  class="text2"  size="10"  maxlength="8"  value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_jiage) %>" tabindex="<%=tabindex++ %>" />
									<label id="goods_jiage_succeed" class="blank"></label>
									<span id="goods_jiage_error"  class="error"></span>											
								</td>
							</tr>														

							<tr>
								<td>
								 	<label for="goods_zuixiao_count" id="goods_zuixiao_count_lb" ><strong>※最小起批量：</strong><span style="color:#71C671;"></span></label>
								</td>
								<td>
									<input type="text" id="goods_zuixiao_count" name="goods_zuixiao_count"  class="text2"  size="4"  maxlength="4"   value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_zuixiao_count) %>" tabindex="<%=tabindex++ %>" />
									<label id="goods_zuixiao_count_succeed" class="blank"></label>
									<span id="goods_zuixiao_count_error"  class="error"></span>											
								</td>
							</tr>	
							
							<tr>
								<td>
								 	<label for="goods_type_id_lv1" id="goods_type_id_lv1_lb" ><strong>※商品类型：</strong><span style="color:#71C671;"></span></label>
								</td>
								<td>
									<select  id="goods_type_id_lv1" name="goods_type_id_lv1"   class="prov"   tabindex="<%=tabindex++ %>" ></select>
									<select  id="goods_type_id_lv2" name="goods_type_id_lv2"  class="city"    tabindex="<%=tabindex++ %>" ></select>
									<select  id="goods_type_id" name="goods_type_id"   class="dist"  tabindex="<%=tabindex++ %>" ></select>
									
									<label id="goods_type_id_lv1_succeed" class="blank"></label>
									<span id="goods_type_id_lv1_error"  class="error"></span>											
								</td>
							</tr>		

							<tr>
								<td>
								 	<label for="goods_notsale" id="goods_notsale_lb" ><strong>※只作为搭赠品：</strong><span style="color:#71C671;">(只作为搭赠品时，将不能够被订货)</span></label>
								</td>
								<td>
									<select  id="goods_notsale" name="goods_notsale"   tabindex="<%=tabindex++ %>" >
<%
									for(int i=0;i<CommonKey.goods_notsale_Value.length;i++){
										String selected = "";
										if(CommonKey.goods_notsale_Value[i].equals(BasicHtmlUtil.replaceTag(uicontentGoods.goods_notsale))){
											selected="selected=\"selected\"";
										}
 %>	
 										<option value="<%=CommonKey.goods_notsale_Value[i] %>"  <%=selected %> ><%=CommonKey.goods_notsale_Name[i] %></option>		
<%
									}
 %>									
									</select>
									<label id="goods_notsale_succeed" class="blank"></label>
									<span id="goods_notsale_error"  class="error"></span>											
								</td>
							</tr>		
														
							<tr>
								<td>
								 	<label for="goods_shangjia" id="goods_shangjia_lb" ><strong>※是否直接上架：</strong><span style="color:#71C671;">(默认直接上架)</span></label>
								</td>
								<td>
									<select  id="goods_shangjia" name="goods_shangjia"       tabindex="<%=tabindex++ %>" >
<%
									for(int i=0;i<CommonKey.goods_shangjia_Value.length;i++){
										String selected = "";
										if(CommonKey.goods_shangjia_Value[i].equals(BasicHtmlUtil.replaceTag(uicontentGoods.goods_shangjia))){
											selected="selected=\"selected\"";
										}
 %>	
 										<option value="<%=CommonKey.goods_shangjia_Value[i] %>"  <%=selected %> ><%=CommonKey.goods_shangjia_Name[i] %></option>		
<%
									}
 %>																				
									</select>
									<label id="goods_shangjia_succeed" class="blank"></label>
									<span id="goods_shangjia_error"  class="error"></span>											
								</td>
							</tr>	

							<tr>
								<td>
								 	<label for="goods_tejia" id="goods_tejia_lb" ><strong>※是否为特价商品：</strong><span style="color:#71C671;">(特价商品检索时用)</span></label>
								</td>
								<td>
									<select  id="goods_tejia" name="goods_tejia"    tabindex="<%=tabindex++ %>" >
<%
									for(int i=0;i<CommonKey.goods_tejia_Value.length;i++){
										String selected = "";
										if(CommonKey.goods_tejia_Value[i].equals(BasicHtmlUtil.replaceTag(uicontentGoods.goods_tejia))){
											selected="selected=\"selected\"";
										}
 %>	
 										<option value="<%=CommonKey.goods_tejia_Value[i] %>"  <%=selected %> ><%=CommonKey.goods_tejia_Name[i] %></option>		
<%
									}
 %>										
									</select>
									<label id="goods_tejia_succeed" class="blank"></label>
									<span id="goods_tejia_error"  class="error"></span>											
								</td>
							</tr>	

							<tr>
								<td>
								 	<label for="goods_big_pic1" id="goods_big_pic1_lb" ><strong>※上传商品主图照片：</strong><span style="color:#71C671;">(正面展示图,2M以内)</span></label>
								</td>
								<td>
									<input name="viewfile" type="text" id="viewfile" readonly="readonly"  value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_big_pic1) %>"   style="color:#8B7D6B;"  onmouseout="document.getElementById('goods_big_pic1').style.display='none';" class="inputstyle" />
									<label for="goods_big_pic1" onmouseover="document.getElementById('goods_big_pic1').style.display='block';" class="file1"  >选择照片...</label>
									<input type="file" onchange="document.getElementById('viewfile').value=this.value;this.style.display='none';document.getElementById('viewfile').focus();" class="file" id="goods_big_pic1"  name="goods_big_pic1"'  tabindex="<%=tabindex++ %>"  />
									<label id="viewfile_succeed" class="blank"></label>
									<span id="viewfile_error"  class="error"></span>											
								</td>
							</tr>	
	
								<tr>
								<td>
								 	<label for="goods_mid_pic1" id="goods_mid_pic1_lb" ><strong>※上传商品侧面照片：</strong><span style="color:#71C671;">(侧面展示图,2M以内)</span></label>
								</td>
								<td>
									<input name="viewfile2" type="text" id="viewfile2" readonly="readonly"  value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_mid_pic1) %>"   style="color:#8B7D6B;"  onmouseout="document.getElementById('goods_mid_pic1').style.display='none';" class="inputstyle" />
									<label for="goods_mid_pic1" onmouseover="document.getElementById('goods_mid_pic1').style.display='block';" class="file1"  >选择照片...</label>
									<input type="file" onchange="document.getElementById('viewfile2').value=this.value;this.style.display='none';document.getElementById('viewfile2').focus();" class="file" id="goods_mid_pic1"  name="goods_mid_pic1"'  tabindex="<%=tabindex++ %>"  />
									<label id="viewfile2_succeed" class="blank"></label>
									<span id="viewfile2_error"  class="error"></span>											
								</td>
							</tr>	
	
								<tr>
								<td>
								 	<label for="goods_small_pic1" id="goods_small_pic1_lb" ><strong>※上传商品其他照片：</strong><span style="color:#71C671;">(其他角度展示图,2M以内)</span></label>
								</td>
								<td>
									<input name="viewfile3" type="text" id="viewfile3" readonly="readonly"   value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_small_pic1) %>"  style="color:#8B7D6B;"  onmouseout="document.getElementById('goods_small_pic1').style.display='none';" class="inputstyle" />
									<label for="goods_small_pic1" onmouseover="document.getElementById('goods_small_pic1').style.display='block';" class="file1"  >选择照片...</label>
									<input type="file" onchange="document.getElementById('viewfile3').value=this.value;this.style.display='none';document.getElementById('viewfile3').focus();" class="file" id="goods_small_pic1"  name="goods_small_pic1"'  tabindex="<%=tabindex++ %>"  />
									<label id="viewfile3_succeed" class="blank"></label>
									<span id="viewfile3_error"  class="error"></span>											
								</td>
							</tr>
																																																
							<tr>
								<td colspan="2"><span style="color:#FF1493"><strong>以下为非必填项目。但是填写后有利于订货者更清楚了解本商品</strong></span></td>
							</tr>
							
							
							<tr>
								<td>
								 	<label for="goods_tiaoxingma" id="goods_tiaoxingma_lb" >条形码：<span style="color:#71C671;"></span></label>
								</td>
								<td>
									<input type="text" id="goods_tiaoxingma" name="goods_tiaoxingma"  class="text2"  size="13"  maxlength="13"  value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_tiaoxingma) %>"   tabindex="<%=tabindex++ %>" />
									<label id="goods_tiaoxingma_succeed" class="blank"></label>
									<span id="goods_tiaoxingma_error"  class="error"></span>											
								</td>
							</tr>
							
							<tr>
								<td>
								 	<label for="goods_shuoming1" id="goods_shuoming1_lb" >口味：<span style="color:#71C671;">(例：香蕉味，原味)</span></label>
								</td>
								<td>
									<input type="text" id="goods_shuoming1" name="goods_shuoming1"  class="text2"  size="20"  maxlength="25" value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_shuoming1) %>"   tabindex="<%=tabindex++ %>" />
									<label id="goods_shuoming1_succeed" class="blank"></label>
									<span id="goods_shuoming1_error"  class="error"></span>											
								</td>
							</tr>		
						
							<tr>
								<td>
								 	<label for="goods_guige" id="goods_guige_lb" >规格：<span style="color:#71C671;">(例：袋装，六枚装，精装)</span></label>
								</td>
								<td>
									<input type="text" id="goods_guige" name="goods_guige"  class="text2"  size="10"  maxlength="4"  value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_guige) %>"  tabindex="<%=tabindex++ %>" />
									<label id="goods_guige_succeed" class="blank"></label>
									<span id="goods_guige_error"  class="error"></span>											
								</td>
							</tr>	

							<tr>
								<td>
								 	<label for="goods_made_address" id="goods_made_address_lb" >产地：<span style="color:#71C671;"></span></label>
								</td>
								<td>
									<input type="text" id="goods_made_address" name="goods_made_address"  class="text2"  size="20"  maxlength="10"   value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_made_address) %>"   tabindex="<%=tabindex++ %>" />
									<label id="goods_made_address_succeed" class="blank"></label>
									<span id="goods_made_address_error"  class="error"></span>											
								</td>
							</tr>																										

							<tr>
								<td>
								 	<label for="goods_made_factory" id="goods_made_factory_lb" >生产厂家：<span style="color:#71C671;"></span></label>
								</td>
								<td>
									<input type="text" id="goods_made_factory" name="goods_made_factory"  class="text2"  size="40"  maxlength="25"  value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_made_factory) %>"   tabindex="<%=tabindex++ %>" />
									<label id="goods_made_factory_succeed" class="blank"></label>
									<span id="goods_made_factory_error"  class="error"></span>											
								</td>
							</tr>		

							
							<tr>
								<td>
								 	<label for="goods_shuoming3" id="goods_shuoming3_lb" >商品说明：<span style="color:#71C671;">(对商品特征，好处等的描述。400字以内)</span></label>
								</td>
								<td>
									<textarea id="goods_shuoming3" name="goods_shuoming3"  class="text3"  rows="6"  cols="70"  maxlength="400" tabindex="<%=tabindex++ %>" ><%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_shuoming3) %></textarea>
									<label id="goods_shuoming3_succeed" class="blank"></label>
									<span id="goods_shuoming3_error"  class="error"></span>										
								</td>
							</tr>									

							<tr>
								<td>
								 	<label for="goods_fuwuchengnuo" id="goods_fuwuchengnuo_lb" >特殊服务承诺：<span style="color:#71C671;">(填写不同于其他商品的特殊承诺。100字以内)</span></label>
								</td>
								<td>
									<textarea id="goods_fuwuchengnuo" name="goods_fuwuchengnuo"  class="text3"   class="text3"  rows="4" cols="70"  maxlength="100" tabindex="<%=tabindex++ %>" ><%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_fuwuchengnuo) %></textarea>
									<label id="goods_fuwuchengnuo_succeed" class="blank"></label>
									<span id="goods_fuwuchengnuo_error"  class="error"></span>										
								</td>
							</tr>		
							
							<tr>
								<td>
								 	<label for="goods_wenxintishi" id="goods_wenxintishi_lb" >温馨提示：<span style="color:#71C671;">(填写订购本商品的注意事项。50字以内)</span></label>
								</td>
								<td>
									<textarea id="goods_wenxintishi" name="goods_wenxintishi"  class="text3"   class="text3"  rows="4" cols="70"  maxlength="100" tabindex="<%=tabindex++ %>" ><%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_wenxintishi) %></textarea>
									<label id="goods_wenxintishi_succeed" class="blank"></label>
									<span id="goods_wenxintishi_error"  class="error"></span>										
								</td>
							</tr>	
							
							<tr>
								<td colspan="2"><span style="color:#FF1493"><strong>本商品有搭赠品时，填写下面的信息。搭赠品最多两种</strong></span></td><td>&nbsp;</td>
							</tr>	

							<tr>
								<td>
								 	<label for="goods_dazeng1_button" id="goods_dazeng1_lb" >搭赠品1：<span style="color:#71C671;"></span></label>
								 	<input type="hidden" name="goods_dazeng1" id="goods_dazeng1"  value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_dazeng1) %>"/>
								</td>
								<td>
									<label id="goods_dazeng1_button"  onclick="JavaScript:openwin('1');" class="file4"  tabindex="<%=tabindex++ %>">选择搭赠品...</label>
									<label id="goods_dazeng1_name" class="blank"></label>
									<label id="goods_dazeng1_succeed" class="blank"></label>
									<span id="goods_dazeng1_error"  class="error"></span>											
								</td>
							</tr>									

							<tr>
								<td>
								 	<label for="goods_dazeng_tiaojian1" id="goods_dazeng_tiaojian1_lb" >搭赠品1的搭赠方式：<span style="color:#71C671;"></span></label>
								</td>
								<td>
									购买
									<input type="text" id="goods_dazeng_tiaojian1" name="goods_dazeng_tiaojian1"  class="text2"  size="1"  maxlength="4"   value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_dazeng_tiaojian1) %>"  tabindex="<%=tabindex++ %>" />
									件本商品，搭赠
									<input type="text" id="goods_dazeng_count1" name="goods_dazeng_count1"  class="text2"  size="1"  maxlength="4"   value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_dazeng_count1) %>"   tabindex="<%=tabindex++ %>" />
									件搭赠品
									<label id="goods_dazeng_tiaojian1_succeed" class="blank"></label>
									<span id="goods_dazeng_tiaojian1_error"  class="error"></span>			
									<label id="goods_dazeng_count1_succeed" class="blank"></label>
									<span id="goods_dazeng_count1_error"  class="error"></span>										
								</td>
							</tr>
							
							<tr>
								<td>
								 	<label for="goods_dazeng_tflag1" id="goods_dazeng_tflag1_lb" >搭赠品1是否可退换：<span style="color:#71C671;"></span></label>
								</td>
								<td>
									<select  id="goods_dazeng_tflag1" name="goods_dazeng_tflag1"   tabindex="<%=tabindex++ %>" >
<%
									for(int i=0;i<CommonKey.tuihuan_Value.length;i++){
										String selected = "";
										if(CommonKey.tuihuan_Value[i].equals(BasicHtmlUtil.replaceTag(uicontentGoods.goods_dazeng_tflag1))){
											selected="selected=\"selected\"";
										}
 %>									
										<option value="<%=CommonKey.tuihuan_Value[i] %>"  <%=selected %> ><%=CommonKey.tuihuan_Name[i] %></option>		
<%
									}
 %>								
									</select>
									<label id="goods_dazeng_tflag1_succeed" class="blank"></label>
									<span id="goods_dazeng_tflag1_error"  class="error"></span>											
								</td>
							</tr>		

							<tr>
								<td>
								 	<label for="goods_dazeng_shuoming1" id="goods_dazeng_shuoming1_lb" >搭赠品1说明：<span style="color:#71C671;">(填写搭赠品注意事项。50字以内)</span></label>
								</td>
								<td>
									<textarea id="goods_dazeng_shuoming1" name="goods_dazeng_shuoming1"  class="text3"   class="text3"  rows="4" cols="70"  maxlength="100" tabindex="<%=tabindex++ %>" ><%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_dazeng_shuoming1) %></textarea>
									<label id="goods_dazeng_shuoming1_succeed" class="blank"></label>
									<span id="goods_dazeng_shuoming1_error"  class="error"></span>										
								</td>
							</tr>	
																					
							<tr>
								<td>
								 	<label for="goods_dazeng2_button" id="goods_dazeng1_lb" >搭赠品2：<span style="color:#71C671;"></span></label>
								 	<input type="hidden" name="goods_dazeng2" id="goods_dazeng2"  value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_dazeng2) %>"/>
								</td>
								<td>
									<label id="goods_dazeng2_button"  onclick="JavaScript:openwin('2');" class="file4"  tabindex="<%=tabindex++ %>">选择搭赠品...</label>
									<label id="goods_dazeng2_name" class="blank"></label>
									<label id="goods_dazeng2_succeed" class="blank"></label>
									<span id="goods_dazeng2_error"  class="error"></span>											
								</td>
							</tr>									

							<tr>
								<td>
								 	<label for="goods_dazeng_tiaojian2" id="goods_dazeng_tiaojian2_lb" >搭赠品2的搭赠方式：<span style="color:#71C671;"></span></label>
								</td>
								<td>
									购买
									<input type="text" id="goods_dazeng_tiaojian2" name="goods_dazeng_tiaojian2"  class="text2"  size="1"  maxlength="4"  value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_dazeng_tiaojian2) %>"  tabindex="<%=tabindex++ %>" />
									件本商品，搭赠
									<input type="text" id="goods_dazeng_count2" name="goods_dazeng_count2"  class="text2"  size="1"  maxlength="4"  value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_dazeng_count2) %>" tabindex="<%=tabindex++ %>" />
									件搭赠品
									<label id="goods_dazeng_tiaojian2_succeed" class="blank"></label>
									<span id="goods_dazeng_tiaojian2_error"  class="error"></span>			
									<label id="goods_dazeng_count2_succeed" class="blank"></label>
									<span id="goods_dazeng_count2_error"  class="error"></span>										
								</td>
							</tr>
							
							<tr>
								<td>
								 	<label for="goods_dazeng_tflag2" id="goods_dazeng_tflag2_lb" >搭赠品2是否可退换：<span style="color:#71C671;"></span></label>
								</td>
								<td>
									<select  id="goods_dazeng_tflag2" name="goods_dazeng_tflag2"   tabindex="<%=tabindex++ %>" >
<%
									for(int i=0;i<CommonKey.tuihuan_Value.length;i++){
										String selected = "";
										if(CommonKey.tuihuan_Value[i].equals(BasicHtmlUtil.replaceTag(uicontentGoods.goods_dazeng_tflag2))){
											selected="selected=\"selected\"";
										}
 %>									
										<option value="<%=CommonKey.tuihuan_Value[i] %>"  <%=selected %> ><%=CommonKey.tuihuan_Name[i] %></option>		
<%
									}
 %>															
									</select>
									<label id="goods_dazeng_tflag2_succeed" class="blank"></label>
									<span id="goods_dazeng_tflag2_error"  class="error"></span>											
								</td>
							</tr>		
							
							<tr>
								<td>
								 	<label for="goods_dazeng_shuoming2" id="goods_dazeng_shuoming2_lb" >搭赠品2说明：<span style="color:#71C671;">(填写搭赠品注意事项。50字以内)</span></label>
								</td>
								<td>
									<textarea id="goods_dazeng_shuoming2" name="goods_dazeng_shuoming2"  class="text3"   class="text3"  rows="4" cols="70"  maxlength="100" tabindex="<%=tabindex++ %>" ><%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_dazeng_shuoming2) %></textarea>
									<label id="goods_dazeng_shuoming2_succeed" class="blank"></label>
									<span id="goods_dazeng_shuoming2_error"  class="error"></span>										
								</td>
							</tr>			
							
							<tr>
								<td colspan="2"><span style="color:#FF1493"><strong>本商品参加<%=CommonWenZi.SiteName %>定期举办的促销活动时填写下面的信息(参加活动后，可在所参加活动页面展示本商品)</strong></span></td><td>&nbsp;</td>
							</tr>		
							
							<tr>
								<td>
								 	<label for="goods_huodong_label" id="goods_huodong_lb" >本商品参加的促销活动：<span style="color:#71C671;"></span></label>
								 	<input type="hidden" name="goods_huodong" id="goods_huodong"   value="<%=BasicHtmlUtil.replaceTag(uicontentGoods.goods_huodong) %>" />
								</td>
								<td>
								<label  id="goods_huodong_label" onclick="JavaScript:opencuxiaoWin();" class="file5"  tabindex="<%=tabindex++ %>">选择品类...</label>	
									<div class="item6">
									<span class="blank"><strong>您选择的参加的促销活动是</strong>(最多选5个，超过5个无效)<strong>：</strong><br /></span>
									<span class="blank2" id ="goods_huodong_name">您还未选择促销活动。</span>
									</div>															
								</td>
							</tr>																																														

																																																															
						</table>
						<br />
						<table width="100%">
							<tr>
								<td width="305"></td>
								<td>
									<input type="button" class="copynextbutton"  onclick='$("#copy_next").click();'  id="copy_next2"   name="copy_next2"  value="" tabindex="<%=tabindex++ %>" />
									<input type="button" class="nocopynextbutton"  onclick='$("#notcopy_next").click();'  id="notcopy_next2" name="notcopy_next2" value="" tabindex="<%=tabindex++ %>" />
									<input type="hidden" name="action" id="action"  value=""/>
								</td>
							</tr>
							</table>		
											
					</div>
				</div>
				</form>
			</div><!--dl_zc end-->
				
			
			<div class="clear"></div>
			
		</div><!--s_bd end-->
	</div><!--s_bdw end-->	

 <jsp:include page="inc/foot1.jsp" flush="true" />
 



</div>
</body>
</html>
