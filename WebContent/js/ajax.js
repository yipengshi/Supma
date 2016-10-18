var xmlHttp,result,isChange,myInterval,url;
var isRun=0;

function $(id){ return document.getElementById(id);}
function CreateXMLHttpRequest()
{
	 if(window.XMLHttpRequest)
     {// Mozilla 浏览器
         xmlHttp = new XMLHttpRequest();  
         if(xmlHttp.overrideMimeType)   
         {xmlHttp.overrideMimeType("text/xml");}
     }
     else if(window.ActiveXObject) 
     {// IE浏览器
        try{xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");}   
         catch(e)
         {
            try{xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");}   
             catch (e){}
          }
     }
     if (!xmlHttp)   
     {window.alert("您的浏览器不支持ajax,请更换。");  
     return false;}
}
// ajax get方式//注意这里的GetXmlHttp区分大小写
function GetXmlHttp(url)
{
	CreateXMLHttpRequest();
	if(xmlHttp)
	{
		isChange=0;
		xmlHttp.open('Get',url,true);
		// xmlHttp.setRequestHeader("CONTENT-TYPE","application/x-www-form-urlencoded");
		xmlHttp.onreadystatechange=Dosome;
		xmlHttp.send(null);
	}
}
// ajax post方式//注意这里的PostXmlHttp区分大小写
function PostXmlHttp(url,posturl)
{
	CreateXMLHttpRequest();
	if(xmlHttp)
	{
		isChange=0;
		xmlHttp.open('post',url,true);
		// xmlHttp.setRequestHeader("Cache-Control","no-cache");
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlHttp.onreadystatechange=Dosome;
		xmlHttp.send(posturl);
	}
}
function Dosome()
{
   if(xmlHttp.readyState==4)
   {
	   if(xmlHttp.status==200)
	   {
		   result=xmlHttp.responseText;
		   isChange=1;
	       // alert(xmlHttp.responseText);
	   }else if (xmlHttp.status == 404) {
         alert("ajax请求的页面不存在！");
       } else {
         alert("ajax请求的页面返回http状态: " + xmlHttp.status);
       }
   }
}
function getObjValue(objName){
	if(document.getElementById){
		return eval('document.getElementById("' + objName + '")');
	}else{
		return eval('document.all.' + objName);
	}
}
// 取radio的值
function getRadio(oRadio)
{ 
var oRadioLength= oRadio.length; 
var oRadioValue = false; 
// alert("oRadioLength:["+oRadioLength+"]");
if (oRadioLength== undefined)
{       
if(oRadio.checked)
{        
oRadioValue = oRadio.value;       
} 
}
else
{       
for(i=0;i<oRadioLength;i++)
{        
// alert("oRadio["+i+"]:"+oRadio[i].checked+"/"+oRadio[i].value);
if (oRadio[i].checked)
{         
oRadioValue = oRadio[i].value;         
break;        
}       
} 
} 
return oRadioValue;
}
// 去掉空格
function trim(inputString) 
{
  if (typeof inputString != "string") { return inputString; }
  var retValue = inputString;
  var ch = retValue.substring(0, 1);
  while (ch == " ") { 
		retValue = retValue.substring(1, retValue.length);
		ch = retValue.substring(0, 1);
  }
  ch = retValue.substring(retValue.length-1, retValue.length);
  while (ch == " ") {
	 	retValue = retValue.substring(0, retValue.length-1);
	 	ch = retValue.substring(retValue.length-1, retValue.length);
  }
  while (retValue.indexOf("  ") != -1) { 
	 	retValue = retValue.substring(0, retValue.indexOf("  ")) + retValue.substring(retValue.indexOf("  ")+1, retValue.length); 
  }
  return retValue;
}  
// 控制只能输入数字
function IsNum(e) 
{
	var k = window.event ? e.keyCode : e.which;
	if (((k >= 48) && (k <= 57)) || k == 8 || k == 0) {
	} else {
		if (window.event) {

			window.event.returnValue = false;
		}
		else {
			e.preventDefault(); // for firefox
		}
	}
} 
/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式
 * 
 * @param num
 *            数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.45'
 * @type String
 */
function formatCurrency(num) {
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
    num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);

    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10)
    cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
    num = num.substring(0,num.length-(4*i+3))+','+
    num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + num + '.' + cents);
}
function fmoney(s, n)   
{   
   n = n > 0 && n <= 20 ? n : 2;   
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
   var l = s.split(".")[0].split("").reverse(),   
   r = s.split(".")[1];   
   t = "";   
   for(i = 0; i < l.length; i ++ )   
   {   
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
   }   
   return t.split("").reverse().join("") + "." + r;   
} 
// 获得短信验证码
function createCode()   
{    
 code = "";   
 var codeLength = 6;// 验证码的长度
 // var checkCode = getObjValue("checkCode");
 // var selectChar = new
	// Array(0,1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');//所有候选组成验证码的字符，当然也可以用中文的
 var selectChar = new Array(1,2,3,4,5,6,7,8,9); 
 for(var i=0;i<codeLength;i++)   
 {   
 // var charIndex = Math.floor(Math.random()*36);
 var charIndex = Math.floor(Math.random()*9);   
 code +=selectChar[charIndex];   
 }     
 return code;      
} 
// 刷新验证码
function getcode(url)
{
document.getElementById("safecodeimg").src=url+"?"+Math.random();}
// 检测密码强度
function checkStrong(sValue)
{
 var modes = 0;
 if (sValue.length < 1) return modes;
 if (/\d/.test(sValue)) modes++; // 数字
 if (/[a-z]/.test(sValue)) modes++; // 小写
 if (/[A-Z]/.test(sValue)) modes++; // 大写
 if (/\W/.test(sValue)) modes++; // 特殊字符
 switch (modes)
 {
  case 1:
   return 1;
   break;
  case 2:
   return 2;
  case 3:
  case 4:
   return sValue.length < 12 ? 3 : 4
   break;
 }
}