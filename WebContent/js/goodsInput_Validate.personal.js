$.extend(validateFunction, {
    FORM_validate:function() {
//		alert("aa");
//		alert(document.getElementById('viewfile').value);
//		document.getElementById('viewfile').value=document.getElementById('user_notuser2').value;
//		alert(document.getElementById('viewfile').value);
        $("#username").jdValidate(validatePrompt.username, validateFunction.username, true);
        $("#userallname").jdValidate(validatePrompt.userallname, validateFunction.userallname, true);
        $("#pwd").jdValidate(validatePrompt.pwd, validateFunction.pwd, true);
        $("#pwd2").jdValidate(validatePrompt.pwd2, validateFunction.pwd2, true);
        $("#mail").jdValidate(validatePrompt.mail, validateFunction.mail, true);
        $("#mobo").jdValidate(validatePrompt.mobo, validateFunction.mobo, true);
        $("#telno").jdValidate(validatePrompt.telno, validateFunction.telno, true);
        $("#u_area").jdValidate(validatePrompt.u_area, validateFunction.u_area, true);
        $("#user_address1").jdValidate(validatePrompt.user_address1, validateFunction.user_address1, true);
        $("#viewfile").jdValidate(validatePrompt.viewfile, validateFunction.viewfile, true);
        $("#authcode").jdValidate(validatePrompt.authcode, validateFunction.authcode, true); 
    }
});

//getvalidateStatus(function() {
//	return validateFunction.FORM_submit(["#username","#userallname","#pwd","#pwd2","#mail","#mobo","#telno","#u_area","#user_address1","#viewfile"]);
//});
//调用
setTimeout(function() {
    $("#username").get(0).focus();
}, 0);
$("#username").jdValidate(validatePrompt.username, validateFunction.username);
$("#userallname").jdValidate(validatePrompt.userallname, validateFunction.userallname);
$("#pwd").bind("keyup",
    function() {
        validateFunction.pwdstrength();
    }).jdValidate(validatePrompt.pwd, validateFunction.pwd)
$("#pwd2").jdValidate(validatePrompt.pwd2, validateFunction.pwd2);
$("#mail").jdValidate(validatePrompt.mail, validateFunction.mail);
$("#mobo").jdValidate(validatePrompt.mobo, validateFunction.mobo);
$("#telno").jdValidate(validatePrompt.telno, validateFunction.telno);
$("#u_area").jdValidate(validatePrompt.u_area, validateFunction.u_area);
$("#user_address1").jdValidate(validatePrompt.user_address1, validateFunction.user_address1);
$("#viewfile").jdValidate(validatePrompt.viewfile, validateFunction.viewfile);
$("#referrer").bind("keydown",
    function() {
        $(this).css({"color":"#333333","font-size":"14px"});
    }).bind("keyup",
    function() {
        if ($(this).val() == "" || $(this).val() == "可不填") {
            $(this).css({ "color": "#999999", "font-size": "12px" });
        }
    }).bind("blur", function() {
        if ($(this).val() == "" || $(this).val() == "可不填") {
            $(this).css({"color":"#999999","font-size":"12px"}).jdValidate(validatePrompt.referrer, validateFunction.referrer, "可不填");
        }
    })
$("#authcode").jdValidate(validatePrompt.authcode, validateFunction.authcode);
$("#viewpwd").bind("click", function() {
    if ($(this).attr("checked") == true) {
        validateFunction.showPassword("text");
        $("#o-password").addClass("pwdbg");
    } else {
        validateFunction.showPassword("password");
        $("#o-password").removeClass("pwdbg");
    }
});

function waitvalid(){
	//如果下面几个后台验证有一个没完事(false是没完事)，就返回，每隔50毫秒再重新执行本函数
	if(subformflag_username==true&& subformflag_usermail==true&& subformflag_usermobo==true&& subformflag_userviewfile==true&& subformflag_userauthcode==true){
        	
		//如果后台验证都完事了，就把这个重复执行去掉
		 window.clearInterval(myInterval2);
		 
    	var flag= validateFunction.FORM_submit(["#username","#userallname","#pwd","#pwd2","#mail","#mobo","#telno","#u_area","#user_address1","#viewfile","#authcode"]);
        if (flag) {
//            alert(flag);
            ////
            var sub_username=encodeURI($("#username").val());
            var md5pwd = encodeURI(hex_md5(hex_md5($("#pwd").val())));
            var sub_userallname=encodeURI($("#userallname").val());
            var sub_u_area=encodeURI($("#u_area").val());
            var sub_user_address1=encodeURI($("#user_address1").val());
            var sub_mobo=encodeURI($("#mobo").val());
            var sub_telno=encodeURI($("#telno").val());
            var sub_mail=encodeURI($("#mail").val());
            var sub_type="1";//1:超市,2:批发商,3,管理员
            var sub_authcode=encodeURI($("#authcode").val());
           
            $.ajaxFileUpload
            (
              {
                   url:'register.goods', //你处理上传文件的服务端
                   secureuri: false, //一般设置为false
                   fileElementId: '', // 这里submit时候不提交上传文件。因为在验证阶段做了
                   dataType: 'json',
                   data:{usertype:sub_type,username:sub_username,pwd:md5pwd,userallname:sub_userallname,u_area:sub_u_area,user_address1:sub_user_address1,mobo:sub_mobo,telno:sub_telno,mail:sub_mail,authcode:sub_authcode},
                   success: function (date) {
            	  		//提交表单的回调函数做迁移
                	   validatingflag=0;//
                	   if (date.success == 1) {//迁移到超市index
                		   location.href = "regsuccess.aspx";
                	   }else if(date.success == 2){//迁移到批发商index
                		   location.href = "regsuccess.aspx";
                	   }else if(date.success == 3){//迁移到管理员
                		   location.href = "regsuccess.aspx";
                		}else{
                			location.href = "error.aspx";//迁移错误页面
                		}
                	 }//end function
                	   
                    }
                )          
            ////
            //

            //
        }else{
        	$("#registsubmit").attr("disabled",false).attr({"value":""});
        	$("#registsubmit").removeClass("btnimg2");
        	$("#registsubmit").addClass("btnimg");
        }
	}
}
$("#registsubmit").click(function() {
    $(this).attr("disabled",true);
    $("#registsubmit").removeClass("btnimg");
    $("#registsubmit").addClass("btnimg2");
	//进行验证，包括后台验证
	validateFunction.FORM_validate();
    var continueflag = true;
    var vxieyi=document .getElementById ("xieyi");
    var errorxieyi=document .getElementById ("xieyi_error");
    if (vxieyi.checked== true) {
    	errorxieyi.innerHTML="";
    	$("#xieyi_error").addClass(validateSettings.succeed.style);
    } else {
    	errorxieyi.innerHTML="请阅读并同意《服务协议》,同意后选中方框才可注册。";
    	$("#xieyi_error").addClass(validateSettings.error.style);
    	continueflag=false;
    	$("#registsubmit").attr("disabled",false).attr({"value":""});
    	$("#registsubmit").removeClass("btnimg2");
    	$("#registsubmit").addClass("btnimg");
    }
    if(continueflag){
    	myInterval2=setInterval("waitvalid()",50);
    }
});


function verc() {
    $("#JD_Verification1").click();
}

$("#authcode").bind('keyup', function(event) {
    if (event.keyCode == 13) {
        $("#registsubmit").click();
    }
});

$("#protocol").click(function() {
    if ($("#protocol").attr("checked") != true) {
        $("#registsubmitframe").attr({ "disabled": "disabled" });
    }
    else {
        $("#registsubmitframe").removeAttr("disabled");

    }
});

//以下为goodsinput的function
function selectPrice(){//价格2,3与价格1相关。价格1为固定时候，价格2,3不可选

	if ($("#goods_pifashuliang1").val()==1){
//		$("#goods_pifashuliang2").val("5");
//		$("#goods_pifashuliang3").val("5");
//		$("#goods_pifajiage2").val("");
//		$("#goods_pifajiage3").val("");
		$("#goods_pifashuliang2").attr("disabled",true);
		$("#goods_pifashuliang3").attr("disabled",true);
		$("#goods_pifajiage2").attr("disabled",true);
		$("#goods_pifajiage3").attr("disabled",true);
	}else{
		$("#goods_pifashuliang2").attr("disabled",false);
		$("#goods_pifashuliang3").attr("disabled",false);
		$("#goods_pifajiage2").attr("disabled",false);
		$("#goods_pifajiage3").attr("disabled",false);
	}
}


var settings={
		url:"",
		lev1:null,
		lev2:null,
		lev3:null,
		nodata:null,
		required:true		
};
var select_prehtml;//被选择的项目
//初始化第一级商品类型列表
function init(){
	// 初始化第一级商品类型列表
	temp_html=select_prehtml;
	$.each(goodsTypeListData.citylist,function(i,prov){
		
		if(lv1selected==prov.p){
			temp_html+="<option value='"+prov.p+"' selected='selected' >"+prov.m+"</option>";
		}else{
			temp_html+="<option value='"+prov.p+"'>"+prov.m+"</option>";
		}
	});
	$("#goods_type_id_lv1").html(temp_html);

	// 若有传入省份与市级的值，则选中。（setTimeout为兼容IE6而设置）
	setTimeout(function(){
		level2Start();
		level3Start();
	},1);

	// 选择第一级列表发生事件
	$("#goods_type_id_lv1").bind("change",function(){
		level2Start();
	});

	// 选择第2级列表发生事件
	$("#goods_type_id_lv2").bind("change",function(){
		level3Start();
	});
}

//选择第一级列表发生事件
function level2Start(){
	var lev1_id=$("#goods_type_id_lv1").get(0).selectedIndex;
	$("#goods_type_id_lv2").empty().attr("disabled",true);
	$("#goods_type_id").empty().attr("disabled",true);

	if(lev1_id<0||typeof(goodsTypeListData.citylist[lev1_id].c)=="undefined"){
		$("#goods_type_id_lv2").css("display","none");
		$("#goods_type_id").css("display","none");
		return;
	};
	
	// 遍历赋值二级下拉列表
	temp_html=select_prehtml;
	$.each(goodsTypeListData.citylist[lev1_id].c,function(i,city){
		if(lv2selected==city.n){
			temp_html+="<option value='"+city.n+"' selected='selected' >"+city.x+"</option>";
		}else{
			temp_html+="<option value='"+city.n+"'>"+city.x+"</option>";
		}		
	});
	$("#goods_type_id_lv2").html(temp_html).attr("disabled",false).css({"display":"","visibility":""});
	level3Start();
}

// 遍历赋值三级下拉列表
function level3Start(){
	var lev1_id=$("#goods_type_id_lv1").get(0).selectedIndex;
	var lev2_id=$("#goods_type_id_lv2").get(0).selectedIndex;

	$("#goods_type_id").empty().attr("disabled",true);

	if(lev1_id<0||lev2_id<0||typeof(goodsTypeListData.citylist[lev1_id].c[lev2_id].a)=="undefined"){
		$("#goods_type_id").css("display","none");
		return;
	};
	
	// 遍历赋值市级下拉列表
	temp_html=select_prehtml;
	$.each(goodsTypeListData.citylist[lev1_id].c[lev2_id].a,function(i,dist){
		if(lv3selected==dist.s){
			temp_html+="<option value='"+dist.s+"' selected='selected' >"+dist.y+"</option>";
		}else{
			temp_html+="<option value='"+dist.s+"'>"+dist.y+"</option>";
		}				
	});
	$("#goods_type_id").html(temp_html).attr("disabled",false).css({"display":"","visibility":""});
}


function submitGoods(a){
	$("#copy_next").attr("disabled",true);
	$("#copy_next2").attr("disabled",true);
	$("#notcopy_next2").attr("disabled",true);
	$("#notcopy_next").attr("disabled",true);
	var urls = "";
	if(a==1){
		urls=encodeURI("inputgoods.goods","utf-8");
		$("#action").val("copy_next");
	}else if(a==2){
		urls=encodeURI("inputgoods.goods","utf-8");
		$("#action").val("notcopy_next");
	}
	    var flag = true;
	    if (flag) {
	       // $(this).attr({"disabled":"disabled"}).attr({"value":"提交中,请稍等"});
//	        $.ajax({
//	            type: "POST",
//	            url: urls,
//	            contentType: "application/x-www-form-urlencoded; charset=utf-8",
//	            data: $("#formpersonal").serialize(),
//	            dataType:"text",
//	            success: function(data) {
//	            	if (data == 1){
//	                    window.location = "inputgoods.aspx";
//	                }
//	            	
//	            	$("#copy_next").attr("disabled",false);
//	            	$("#copy_next2").attr("disabled",false);
//	            	$("#notcopy_next2").attr("disabled",false);
//	            	$("#notcopy_next").attr("disabled",false);
//	            	
//	            	
//	            }
//	        });
	    	document.formpersonal.action=urls;
	    	setTimeout("document.formpersonal.submit()",500);
	    	setTimeout("enabledInput()",500);
	    	
//	    	$("#formpersonal").attr("action","inputgoods.goods");
//	    	$("#formpersonal").submit();

	    }
}

function enabledInput(){
	$("#copy_next").attr("disabled",false);
	$("#copy_next2").attr("disabled",false);
	$("#notcopy_next2").attr("disabled",false);
	$("#notcopy_next").attr("disabled",false);
}

function openwin(a) {
    var obj = new Object();
    var str = new Object();
    obj.value = a;
    if(a=="2"){
        obj.radiovalue = $("#goods_dazeng2").val();
    }else{
    	obj.radiovalue = $("#goods_dazeng1").val();
    }
    str =window.showModalDialog("popdazeng.aspx",obj,"dialogWidth=920px;dialogHeight=500px;dialogLeft=160;dialogTop=175;help=no");
    if(str.error=="notlogin"){
    	window.location.href = "notlogin.aspx";
    	return;
    }
    if(a=="2"){
        	$("#goods_dazeng2").val(str.value);
        	$("#goods_dazeng2_name").html(str.name);
        	$("#goods_dazeng2_error").html(str.errorhtml);
        	if(str.value==""){
        		clearDaZeng("2");
        	}
    }else{
	    	$("#goods_dazeng1").val(str.value);
	    	$("#goods_dazeng1_name").html(str.name);
        	$("#goods_dazeng1_error").html(str.errorhtml);
        	if(str.value==""){
        		clearDaZeng("1");
        	}
    }
} 
function clearDaZeng(a) {
	if(a=="2"){
		$("#goods_dazeng_tiaojian2").val("");
		$("#goods_dazeng_count2").val("");
		$("#goods_dazeng_tiaojian2_succeed").html("");
		$("#goods_dazeng_tiaojian2_error").html("");
		$("#goods_dazeng_count2_succeed").html("");
		$("#goods_dazeng_count2_error").html("");
		$("#goods_dazeng_tflag2").val("N");
		$("#goods_dazeng_tflag2_succeed").html("");
		$("#goods_dazeng_tflag2_error").html("");
		$("#goods_dazeng_shuoming2").val("");
		$("#goods_dazeng_shuoming2_succeed").html("");
		$("#goods_dazeng_shuoming2_error").html("");
	}else{
		$("#goods_dazeng_tiaojian1").val("");
		$("#goods_dazeng_count1").val("");
		$("#goods_dazeng_tiaojian1_succeed").html("");
		$("#goods_dazeng_tiaojian1_error").html("");
		$("#goods_dazeng_count1_succeed").html("");
		$("#goods_dazeng_count1_error").html("");
		$("#goods_dazeng_tflag1").val("N");
		$("#goods_dazeng_tflag1_succeed").html("");
		$("#goods_dazeng_tflag1_error").html("");
		$("#goods_dazeng_shuoming1").val("");
		$("#goods_dazeng_shuoming1_succeed").html("");
		$("#goods_dazeng_shuoming1_error").html("");
	}
}

function opencuxiaoWin() {
    var obj = new Object();
    var str = new Object();
    obj.radiovalue = $("#goods_huodong").val();
    str =window.showModalDialog("cuxiao.aspx",obj,"dialogWidth=920px;dialogHeight=500px;dialogLeft=160;dialogTop=175;help=no");
    if(str.error=="notlogin"){
    	window.location.href = "notlogin.aspx";
    	return;
    }
    $("#goods_huodong").val(str.value);
	$("#goods_huodong_name").html(str.name);
	if(str.value==""){
		//clearDaZeng("2");
	}
} 
//
