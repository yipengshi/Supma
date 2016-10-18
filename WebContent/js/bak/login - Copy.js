function loginsubmit()
{
	if (getObjValue("loginname").value == "")			
	{
		getObjValue("loginname_error").innerHTML = "<img src='images/exclamation.png' width='16' height='16' />&nbsp;请填写用户名！";
		getObjValue("loginname_error").addClass("error");
		getObjValue("loginname").addClass("text highlight2");
		getObjValue("loginname").focus(); 			
		return false;						
	}
	if (getObjValue("loginpwd").value == "")			
	{
		getObjValue("loginpwd_error").innerHTML = "<img src='images/exclamation.png' width='16' height='16'  />&nbsp;请填写登录密码！";
		getObjValue("loginpwd_error").addClass("error");
		getObjValue("loginpwd").addClass("text highlight2");
		getObjValue("loginpwd").focus(); 					
		return false;						
	}
	
	getObjValue("loginname_error").innerHTML='<img src="images/gif-0022.gif" align="absmiddle" />  &nbsp;&nbsp; 正在登陆，请稍候…';
	isRun=0;
	myInterval=setInterval("loginsub()",50);
}
function loginsub()
{
	var admin_name,admin_pwd,url;
	
	admin_name=trim(getObjValue("loginname").value);
	admin_pwd=hex_md5(hex_md5(trim(getObjValue("loginpwd").value)));
    var postregurl = "login.goods?loginname="+admin_name+"&loginpwd="+admin_pwd;

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
		   getObjValue("loginpwd_error").innerHTML = "";
		   getObjValue("loginname_error").innerHTML = "<img src='images/exclamation.png' width='16' height='16' class='textalginmiddle' /> &nbsp;用户名不存在！"; 
		   document.getElementById("loginname_error").className="error";
		   getObjValue("loginname").addClass("text highlight2");
		   getObjValue("loginname").focus();
		 
		   
	   }
	   else if(result=="-2") 
	   {
		   getObjValue("loginname_error").innerHTML = "";
		   getObjValue("loginpwd_error").innerHTML = "<img src='images/exclamation.png' width='16' height='16' class='textalginmiddle' /> &nbsp;密码不正确！"; 
		   getObjValue("loginpwd_error").addClass("error");
		   getObjValue("loginpwd").addClass("text highlight2");
		   getObjValue("loginpwd").focus();
	   }
	   else if(result=="-3")
	   {
		   getObjValue("loginpwd_error").innerHTML = "";
		   getObjValue("loginname_error").innerHTML = "<img src='images/exclamation.png' width='16' height='16' class='textalginmiddle' /> &nbsp;用户被锁，无法登录2！"; 
		   document.getElementById("loginname_error").className="error";
		   getObjValue("loginname").addClass("text highlight2");
		   getObjValue("loginname").focus(); 
	   }	
	   else if(result=="-4")
	   {
		   getObjValue("loginpwd_error").innerHTML = "";
		   getObjValue("loginname_error").innerHTML = "<img src='images/exclamation.png' width='16' height='16' class='textalginmiddle' /> &nbsp系统发生异常,暂时无法登陆！"; 
		   document.getElementById("loginname_error").className="error";
		   getObjValue("loginname").addClass("text highlight2");
		   getObjValue("loginname").focus(); 
	   }	   
		else if(result=="1") 
		   {
			   getObjValue("loginname").innerHTML = "<img src='images/accept.png' width='16' height='16' class='textalginmiddle' />&nbsp;！登陆成功</span>";
			   window.location="index.aspx"; 
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
		   getObjValue("err_msg").innerHTML = "<img src='images/accept.png' width='16' height='16' class='textalginmiddle' /> <span class='green textalginmiddle'>登陆成功</span>";
		   window.location="/alarm.asp?content="+"登陆成功"; 
		}
		else
	   {
			getObjValue("err_msg").innerHTML = "<img src='images/exclamation.png' width='16' height='16' class='textalginmiddle' /> <span class='red textalginmiddle'>系统发生异常,暂时无法登陆</span>";
		}
	   return true;
   }
}
