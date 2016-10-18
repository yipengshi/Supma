function loginsubmit()
{
	if (document.getElementById("loginname").value == "")			
	{
		document.getElementById("loginname_error").innerHTML = "<img src='images/exclamation.png' width='16' height='16' />&nbsp;请填写用户名！";
		document.getElementById("loginname_error").className="error";
		document.getElementById("loginname").className="text highlight2";
		document.getElementById("loginpwd_error").innerHTML = "";
		document.getElementById("loginpwd").className="text";
		//getObjValue("loginname").focus(); 		
		return false;						
	}
	if (document.getElementById("loginpwd").value == "")			
	{
		document.getElementById("loginpwd_error").innerHTML = "<img src='images/exclamation.png' width='16' height='16'  />&nbsp;请填写登录密码！";
		document.getElementById("loginpwd_error").className="error";
		document.getElementById("loginpwd").className="text highlight2";
		document.getElementById("loginname_error").innerHTML = "";
		document.getElementById("loginname").className="text";
//		getObjValue("loginpwd").focus(); 			
		return false;						
	}
	
	document.getElementById('registsubmit').disabled=true;//防止二次提交
	document.getElementById("loginname_error").innerHTML='<img src="images/gif-0022.gif" align="absmiddle" />  &nbsp;&nbsp; 正在登陆，请稍候…';
	isRun=0;
	myInterval=setInterval("loginsub()",50);
}
function loginsub()
{
	var admin_name,admin_pwd,url;
	
	admin_name=trim(getObjValue("loginname").value);
	admin_pwd=hex_md5(hex_md5(trim(getObjValue("loginpwd").value)));
    var postregurl = "login.goods?loginname="+encodeURI(admin_name,"UTF-8")+"&loginpwd="+admin_pwd+"&"+Math.random();

   if(isRun==0)
   {
	   
	   GetXmlHttp(postregurl);
	   isRun=1;
   }
   if(isChange==1)
   {
	   window.clearInterval(myInterval);
	   isRun=0;
	   if(result=="-1")
	   {
		   //document.getElementById("loginname").focus();
		   document.getElementById("loginpwd_error").innerHTML = "";
		   document.getElementById("loginname_error").className="error";
		   document.getElementById("loginname_error").innerHTML = "<img src='images/exclamation.png' width='16' height='16' class='textalginmiddle' /> &nbsp;用户名不存在！"; 
		   document.getElementById("loginname").className="text highlight2";
		   document.getElementById('registsubmit').disabled=false;//解除按钮失效
	   }
	   else if(result=="-2") 
	   {
		   //document.getElementById("loginpwd").focus();
		   document.getElementById("loginname_error").innerHTML = "";
		   document.getElementById("loginpwd_error").className=("error");
		   document.getElementById("loginpwd_error").innerHTML = "<img src='images/exclamation.png' width='16' height='16' class='textalginmiddle' /> &nbsp;密码不正确！"; 
		   document.getElementById("loginpwd").className="text highlight2";
		   document.getElementById('registsubmit').disabled=false;//解除按钮失效
	   }
	   else if(result=="-3")
	   {
		   //document.getElementById("loginname").focus(); 
		   document.getElementById("loginpwd_error").innerHTML = "";
		   document.getElementById("loginname_error").innerHTML = "<img src='images/exclamation.png' width='16' height='16' class='textalginmiddle' /> &nbsp;用户被锁，无法登录！!"; 
		   document.getElementById("loginname_error").className="error";
		   document.getElementById("loginname").className="text highlight2";
		   document.getElementById('registsubmit').disabled=false;//解除按钮失效
	   }
	   else if(result=="-5")
	   {
		  // document.getElementById("loginname").focus();
		   document.getElementById("loginpwd_error").innerHTML = "";
		   document.getElementById("loginname_error").innerHTML = "<img src='images/exclamation.png' width='16' height='16' class='textalginmiddle' /> &nbsp;资质审核中，审核通过方可登录！"; 
		   document.getElementById("loginname_error").className="error";
		   document.getElementById("loginname").className="text highlight2";
		   document.getElementById('registsubmit').disabled=false;//解除按钮失效
	   }	
	   else if(result=="-4")
	   {
		   //document.getElementById("loginname").focus(); 
		   document.getElementById("loginpwd_error").innerHTML = "";
		   document.getElementById("loginname_error").innerHTML = "<img src='images/exclamation.png' width='16' height='16' class='textalginmiddle' /> &nbsp系统发生异常,暂时无法登陆！"; 
		   document.getElementById("loginname_error").className="error";
		   document.getElementById("loginname").className="text highlight2";
		   document.getElementById('registsubmit').disabled=false;//解除按钮失效
	   }
		else if(result=="1") 
		   {
			   getObjValue("loginname").innerHTML = "<img src='images/accept.png' width='16' height='16' class='textalginmiddle' />&nbsp;｣登陆成功！</span>";
			   window.location="index.aspx"; 
			}	  
		else if(result=="11") 
		   {
			   getObjValue("loginname").innerHTML = "<img src='images/accept.png' width='16' height='16' class='textalginmiddle' />&nbsp;｣登陆成功！</span>";
			   window.location="index_pifa.aspx"; 
			}	   
	   else if(result=="2") 
	   {
		   //getObjValue("login").src="/images/login_button.gif";
		   //getObjValue("u_mobiephone").focus();   	
		   getObjValue("err_msg").innerHTML = "<img src='images/exclamation.png' width='16' height='16' class='textalginmiddle' /> <span class='red textalginmiddle'>账户余额不足，请续费！</span>";
		   window.location="/lock.asp?content="+"账户余额不足，请续费";
	   }
	   else if(result=="3") 
	   {
		   getObjValue("err_msg").innerHTML = "<img src='images/accept.png' width='16' height='16' class='textalginmiddle' /> <span class='green textalginmiddle'>登陆成功！</span>";
		   url=trim(getObjValue("url").value);
		   window.location="/ok.asp?url="+url+"&content="+"登陆成功"; 
		}
		else if(result=="4") 
	   {
		   getObjValue("err_msg").innerHTML = "<img src='images/accept.png' width='16' height='16' class='textalginmiddle' /> <span class='green textalginmiddle'>登陆成功！</span>";
		   window.location="/alarm.asp?content="+"登陆成功"; 
		}
		else
	   {
			document.getElementById("err_msg").innerHTML = "<img src='images/exclamation.png' width='16' height='16' class='textalginmiddle' /> <span class='red textalginmiddle'>系统发生异常,暂时无法登陆</span>";
			 document.getElementById('registsubmit').disabled=false;//解除按钮失效
		}

//	   if(result=="-1" || result=="-2"|| result=="-3"|| result=="-4"|| result=="-5"){
//		   document.getElementById('registsubmit').disabled=false;//解除按钮失效
//	   }
	   return true;
   }
}
