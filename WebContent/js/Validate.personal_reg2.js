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
        $("#u_goodstype").jdValidate(validatePrompt.u_goodstype, validateFunction.u_goodstype, true);
        return validateFunction.FORM_submit(["#username","#userallname","#pwd","#pwd2","#mail","#mobo","#telno","#u_area","#user_address1","#viewfile","#authcode","#u_goodstype"]);//check不需要时间的先返回结果
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
$("#u_goodstype").jdValidate(validatePrompt.u_goodstype, validateFunction.u_goodstype);
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
		 window.clearInterval(myInterval3);

	    	var flag= validateFunction.FORM_submit(["#username","#userallname","#pwd","#pwd2","#mail","#mobo","#telno","#u_area","#user_address1","#viewfile","#authcode","#u_goodstype"]);

	        if (flag) {
//	            alert(flag);
	            ////
	            var sub_username=encodeURI($("#username").val());
	            var md5pwd = encodeURI(hex_md5(hex_md5($("#pwd").val())));
	            var sub_userallname=encodeURI($("#userallname").val());
	            var sub_u_area=encodeURI($("#u_area").val());
	            var sub_user_address1=encodeURI($("#user_address1").val());
	            var sub_mobo=encodeURI($("#mobo").val());
	            var sub_telno=encodeURI($("#telno").val());
	            var sub_mail=encodeURI($("#mail").val());
	            var sub_type="2";//1:超市,2:批发商,3,管理员
	            var sub_authcode=encodeURI($("#authcode").val());
	            var sub_u_goodstype=encodeURI($("#u_goodstype").val());
	           
	            $.ajaxFileUpload
	            (
	              {
	                   url:'register.goods', //你处理上传文件的服务端
	                   secureuri: false, //一般设置为false
	                   fileElementId: '', // 这里submit时候不提交上传文件。因为在验证阶段做了
	                   dataType: 'json',
	                   data:{usertype:sub_type,username:sub_username,pwd:md5pwd,userallname:sub_userallname,u_area:sub_u_area,user_address1:sub_user_address1,mobo:sub_mobo,telno:sub_telno,mail:sub_mail,authcode:sub_authcode,u_goodstype:sub_u_goodstype},
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

	var uareaerrorhtml = document.getElementById('u_area_error').innerHTML;
	if(uareaerrorhtml == ""){
		document.getElementById('u_area_error').innerHTML='&nbsp;';
	}
	var ugoodstypeerrorhtml = document.getElementById('u_goodstype_error').innerHTML;
	if(ugoodstypeerrorhtml == ""){
		document.getElementById('u_goodstype_error').innerHTML='&nbsp;';
	}
    var continueflag = true;
    var vxieyi=document .getElementById ("xieyi");
    var errorxieyi=document .getElementById ("xieyi_error");
    if (vxieyi.checked== true) {
    	errorxieyi.innerHTML="";
    	$("#xieyi_error").addClass(validateSettings.succeed.style);
        var flagv =validateFunction.FORM_validate();
        if(!flagv){
        	continueflag=false;
        	$("#registsubmit").attr("disabled",false).attr({"value":""});
        	$("#registsubmit").removeClass("btnimg2");
        	$("#registsubmit").addClass("btnimg");
        }
    } else {
    	errorxieyi.innerHTML="请阅读并同意《服务协议》,同意后选中方框才可注册。";
    	$("#xieyi_error").addClass(validateSettings.error.style);
    	continueflag=false;
    	$("#registsubmit").attr("disabled",false).attr({"value":""});
    	$("#registsubmit").removeClass("btnimg2");
    	$("#registsubmit").addClass("btnimg");
    }
    if(continueflag){
    	myInterval3=setInterval("waitvalid()",50);
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
$("#registsubmitframe").click(function() {
    var flag = validateFunction.FORM_validate();
    if (flag) {
        $(this).attr({"disabled":"disabled"}).attr({"value":"提交中,请稍等"});
        $.ajax({
            type: "POST",
            url: "/RegistService.php?rtype=personal",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: $("#formpersonal").serialize(),
            success: function(result) {
                if (result == 1) {
                    window.location = "/vipmanage";
                }
            }
        });
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