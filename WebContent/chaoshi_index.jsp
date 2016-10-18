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
<%userBean ufo = (userBean)session.getAttribute("loginUserInfo") ; //写log用的userBean
if(ufo==null){
	ufo=new userBean();
	ufo.user_notuser11=BaseGetIP.getIpAddr(request);
}%>
<%//取得商品类型的数据
	LinkedHashMap<String,GoodsTypeBean> lsx1 = (LinkedHashMap<String,GoodsTypeBean>)request.getServletContext().getAttribute(CommonKey.goodsTypeListData_1);
	LinkedHashMap<String,GoodsTypeBean> lsx2 = (LinkedHashMap<String,GoodsTypeBean>)request.getServletContext().getAttribute(CommonKey.goodsTypeListData_2);
	LinkedHashMap<String,GoodsTypeBean> lsx3 = (LinkedHashMap<String,GoodsTypeBean>)request.getServletContext().getAttribute(CommonKey.goodsTypeListData_3);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=CommonWenZi.SiteName %></title>
<meta http-equiv="x-ua-compatible" content="ie=Edge" />
<meta name="keywords" content="<%=CommonWenZi.SiteName %>" />
<meta name="description" content="<%=CommonWenZi.Index_meta_description %>" />

<link href="css/main.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]> 
<link href="css/main.ie6.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if IE 7]> 
<link href="css/main.ie7.css" rel="stylesheet" type="text/css" />
<![endif]-->
<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery-imgslideshow.js"></script>
<script type="text/javascript" src="js/ks-switch.js"></script>
<script type="text/javascript" src="js/lib.js"></script>
<script type="text/javascript">
var timeout	= 500;
var closetimer	= 0;
var ddmenuitem	= 0;

$(document).ready(function(){
   	$('.cat_item').mousemove(function(){
		$(this).addClass('cat_item_on');
	});
	$('.cat_item').mouseleave(function(){	
		$(this).removeClass('cat_item_on');
	});
	$('#slideshow').imgSlideShow({itemclass: 'i'})
	$("#slide-qg").switchTab({titCell: "dt a", trigger: "mouseover", delayTime: 0});
	$("#s_cart_nums1").hover(function(){
		mcancelclosetime();
		if(ddmenuitem) ddmenuitem.hide();
		ddmenuitem = $(document).find("#s_cartbox");
		ddmenuitem.fadeIn();
	},function(){
		mclosetime();
	});
	$("#s_cart_nums2").hover(function(){
		mcancelclosetime();
		if(ddmenuitem) ddmenuitem.hide();
		ddmenuitem = $(document).find("#s_cartbox");
		ddmenuitem.fadeIn();
	},function(){
		mclosetime();
	});
	$("#s_cartbox").hover(function(){
		mcancelclosetime();
	},function(){
		mclosetime();
	});
	 var $cur = 1;
    var $i = 4;
    var $len = $('.hot_list>ul>li').length;
    var $pages = Math.ceil($len / $i);
    var $w = $('.hotp').width()-66;
	
    var $showbox = $('.hot_list');
    
    var $pre = $('div.left_icon');
    var $next = $('div.rgt_icon');
 	
    $pre.click(function(){
        if (!$showbox.is(':animated')) { 
            if ($cur == 1) {   
                $showbox.animate({
                    left: '-=' + $w * ($pages - 1)
                }, 500); 
                $cur = $pages; 
            }
            else { 
                $showbox.animate({
                    left: '+=' + $w
                }, 500); 
                $cur--;
            }
           
        }
    });

    $next.click(function(){
        if (!$showbox.is(':animated')) { 
            if ($cur == $pages) {  
                $showbox.animate({
                    left: 0
                }, 500); 
                $cur = 1; 
            }
            else {
                $showbox.animate({
                    left: '-=' + $w
                }, 500);
                $cur++; 
            }
            
        }
    });
    
});
function mclose()
{
	if(ddmenuitem) ddmenuitem.hide();
}
function mclosetime()
{
	closetimer = window.setTimeout(mclose, timeout);
}
function mcancelclosetime()
{
	if(closetimer)
	{
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}
</script>
</head>

<body>
<div id="doc">
	<div id="s_hdw">
	
    <jsp:include page="inc/title1.jsp" flush="true" />
		
		<div class="s_hd nav">
			<div id="s_logo"><a href="#"><img src="images/logo.jpg" border="0" /></a></div>
			<div id="s_nav">
				<ul>
					<li class="first cur"><a href="#">首页</a><span></span></li>
					<li><a href="#">网上订货介绍</a><span></span></li>
					<li><a href="#">超市入驻</a><span></span></li>
					<li class="last"><a href="#">批发商入驻</a><span></span></li>
				</ul>
			</div>
		</div><!--s_hd end-->
		
		<div class="mmenu">
			<div class="s_hd">
				<div id="s_search">
					<form action="" method="post"><input name="" type="text" class="search-input" /><input name="" type="image" src="images/btn_search.jpg" /></form>
				</div>
				
				<div id="s_keyword">
					<ul>
						<li><strong>热门搜索：</strong></li>
<%		GoodsBean goodsBean1 = new GoodsBean();
			ArrayList <GoodsBean>goodsBeanlist = goodsBean1.getShangPinList("",null,true,ufo);
			int tmpcount = goodsBeanlist.size()<=2?goodsBeanlist.size():2;
			for(int i=0;i<tmpcount;i++){
					GoodsBean listBeans = (GoodsBean)goodsBeanlist.get(i);
 %>						
						<li><a href="#" ><%=StringUtil.cutStringAddChar(listBeans.goods_pinpai,' ',5) %></a></li>
<%		} %>
					</ul>
				</div>
				
				<div id="s_cart">
					<ul>
						<li class="nums"><a href="" id="s_cart_nums1">购物车： <span>0</span>  件</a> <a href="" class="btn" id="s_cart_nums2"></a></li>
						<li class="checkout"><a href="#">去结算>></a></li>
					</ul>
				</div>
			
				<div id="s_cartbox" class="s_cartbox">您的购物车中暂无商品，赶快选择心爱的商品吧！</div>
			
				<div id="s_cats">
					<div class="cat_hd"><h3><a href="#">所有商品分类</a></h3></div>
					<div class="cat_bd" style="display:block;">
					<ul>
<% //goodsTypeBean.list
	//取得type1 list
		Iterator iter =	lsx1.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String)entry.getKey();
			GoodsTypeBean val = (GoodsTypeBean)entry.getValue();
%>
					<li class="cat_item">			
						<h4 class="cat_tit"><a href="#" class="cat_tit_link"><%=val.goods_type_name%></a><span class="s_arrow">></span></h4>
						<div class="cat_cont">
<% 		Iterator iter2 =lsx2.entrySet().iterator();
			while (iter2.hasNext()) {
				Map.Entry entry2 = (Map.Entry) iter2.next();
				String key2 = (String)entry2.getKey();
				GoodsTypeBean val2 = (GoodsTypeBean)entry2.getValue();	
				if(!val2.goods_type_notuser1.equals(key)){//第2层type的上层code不等于第1层的type code,这条忽略
					continue;
				}
%>
							<div class="cat_cont_lft">
							<dl class="cf">
								<dt><a href="#"><%=val2.goods_type_name%></a></dt>
								<dd>
									<ul>
<% 		Iterator iter3 =lsx3.entrySet().iterator();
			while (iter3.hasNext()) {
				Map.Entry entry3 = (Map.Entry) iter3.next();
				String key3 = (String)entry3.getKey();
				GoodsTypeBean val3 = (GoodsTypeBean)entry3.getValue();	
				if(!val3.goods_type_notuser1.equals(val2.goods_type_id)){//第3层type的上层code不等于第2层的type code,这条忽略
					continue;
				}
%>
										<li><a href="#"><%=val3.goods_type_name%></a></li>
<%
				}
 %>	
									</ul>
								</dd>
							</dl>

						</div>
<%
		}
 %>						

					</div>
				</li>
<%
}
%>


					</ul>
					<!--<div class="all_cats"><a href="#" class="more">全部商品分类</a></div>-->
					</div>
				</div>
			</div>
		</div><!--mmenu end-->
	
	</div><!--s_hdw end-->
	
	<div id="s_bdw">
		<div id="s_bd">
			<div class="cf">
				<div id="i_col_lft" class="i_col_lft">
					<div class="i_lads">
						<ul>
							<li><a href="#"><img src="images/lad1.jpg" /></a></li>
							<li><a href="#"><img src="images/lad2.jpg" /></a></li>
							<li><a href="#"><img src="images/lad3.jpg" /></a></li>
							<li><a href="#"><img src="images/lad1.jpg" /></a></li>
							<li><a href="#"><img src="images/lad1.jpg" /></a></li>
						</ul>
					</div>
				</div>
				<div id="i_col_rgt" class="i_col_rgt">
					<div class="i_col_rgt_box">
					
						<div class="i_slides" id="slideshow">
							<div class="i"><a href="#"><img src="images/slide-1.jpg" /></a></div>
							<div class="i"><a href="#"><img src="images/slide-2.jpg" /></a></div>
							<div class="i"><a href="#"><img src="images/slide-3.jpg" /></a></div>
							<div class="i"><a href="#"><img src="images/slide-4.jpg" /></a></div>
							<div class="i"><a href="#"><img src="images/slide-5.jpg" /></a></div>
						</div>
						
						<div class="pbt10"></div>
						
						<div class="lft">
							<div class="hotp">
								<div class="lft_icon"><a href="#"><span>pre</span></a></div>
								<div class="hot_list">
									<ul>
										<li><a href="#"><img src="images/f1.jpg" /></a></li>
										<li><a href="#"><img src="images/f2.jpg" /></a></li>
										<li><a href="#"><img src="images/f3.jpg" /></a></li>
										<li><a href="#"><img src="images/f4.jpg" /></a></li>
										<li><a href="#"><img src="images/f5.jpg" /></a></li>
										<li><a href="#"><img src="images/f6.jpg" /></a></li>
										<li><a href="#"><img src="images/f7.jpg" /></a></li>
										<li><a href="#"><img src="images/f8.jpg" /></a></li>
									</ul>
								</div>
								<div class="rgt_icon"><a href="#"><span>Nexr</span></a></div>
							</div>
						</div>
						
						<div class="rgt">
							<div class="rgt-box">
								<div class="loginbox">
									<div class="login_icon cf">
										<ul>
											<li><a href="#">免费注册</a></li>
											<li><a href="login.aspx">用户登陆</a></li>
										</ul>
									</div>
									
									<div class="announce_top cf"><h3>最新动态<span><a href="#">更多</a></span></h3></div>
								
									<div class="announce_cont">
										<ul>
											<li><a href="#">一号店迎国庆促销活动28号始</a></li>
											<li><a href="#">迎中秋，上品堂海参直营</a></li>
											<li><a href="#">一号店全南店盛大开幕</a></li>
											<li><a href="#">凡注册一号店网上商城的会员</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						
					</div>
					
				</div>
				
			</div>
			

			
			<div class="banner2 ptt10 cf"></div>
<%
	userBean bean1 = new  userBean();
	userBean loginuserbean1 = (userBean)session.getAttribute("loginUserInfo") ;//取得登录超市用户的片区
	String str1 = loginuserbean1==null?"":loginuserbean1.user_address2;
	ArrayList listpifa = bean1.getPifaShangList(str1.trim(),false,ufo);//取得可为登录超市片区送货的批发商	
	
	iter =	lsx1.entrySet().iterator();
	int n=0;
	while (iter.hasNext()) {
		n++;
		Map.Entry entry = (Map.Entry) iter.next();
		String key = (String)entry.getKey();
		GoodsTypeBean val = (GoodsTypeBean)entry.getValue();
		goodsBean1 = new GoodsBean();
		goodsBeanlist = goodsBean1.getShangPinList(val.goods_type_id,listpifa,false,ufo);
%>				
			<div id="channel_0" class="i_channels cf">
				<div class="i_channels_tit">
					<div class="i_channels_cat_title"><h3 class="cf<%=n%>"><%=val.goods_type_name%></h3></div>
					<div class="i_channels_brand">
						<ul>
						<li class="first"><strong>畅销品牌：</strong></li>
<% 
	int listcount3 = Integer.parseInt(CommonKey.index_show_pinpai);
	for(int i=0;i<(goodsBeanlist.size()>listcount3?listcount3:goodsBeanlist.size());i++){
		GoodsBean listBeans = (GoodsBean)goodsBeanlist.get(i);
%>						
							<li><a href="#"  title='<%=listBeans.goods_name %>'><%=StringUtil.cutStringAddChar(listBeans.goods_pinpai,' ',5) %></a></li>
<%} %>
							<li><a href="#"><strong>全部商品>></strong></a></li>
						</ul>
					</div>
				</div>
		
				<div class="i_channels_cont">
					<div class="channel_lft">
						<div class="big_ad_box">
									<div class="announce_top cf"><h3><%=val.goods_type_name%>供货商列表<span><a href="#">全部>></a></span></h3></div>
								
									<div class="announce_cont">
										<ul>
<% 			
				int listcount = Integer.parseInt(CommonKey.index_show_pifashang);
				for(int i=0;i<(listpifa.size()>listcount?listcount:listpifa.size());i++){
					userBean beanx = (userBean)listpifa.get(i);
					String tmpStr = CommonKey.jinghao+key.trim()+CommonKey.jinghao;//大类01,02等等
					if(beanx.user_notuser1.indexOf(tmpStr) !=-1){
					
%>
														
											<li><a href="#"  title='<%=beanx.user_name%>'><%=StringUtil.cutStringAddChar(beanx.user_name,' ',16) %></a></li>
<%
				   }
			 }
%>											

										</ul>
									</div>						
						</div>
					</div>
					
					<div class="channel_midx">
						<div class="cat_list_box">
							<ul>
<% 
	int listcount2 = Integer.parseInt(CommonKey.index_show_shangpin);
	for(int i=0;i<(goodsBeanlist.size()>listcount2?listcount2:goodsBeanlist.size());i++){
		GoodsBean listBeans = (GoodsBean)goodsBeanlist.get(i);
%>
								<li>
									<div class="cat_pd">
										<div class="pic"><a href="#"><img src="<%=listBeans.goods_mid_pic1 %>" border="0" /></a></div>
										<div class="ptitle"><a href="#" title='<%=listBeans.goods_name %>'><%=StringUtil.cutStringAddChar(listBeans.goods_name,' ', 20)%>\<%=StringUtil.toKG(listBeans.goods_zhongliang)%></a></div>
										<div class="price">批发价：<%=listBeans.goods_pifajiage3 %></div>
									</div>
								</li>
<%} %>
							</ul>
						</div>
					</div>
				

					
				</div>
				
			</div>
<%}
%>		

		</div>
	</div><!--s_bdw end-->	
	
 <jsp:include page="inc/foot1.jsp" flush="true" />

</div>
</body>
</html>
