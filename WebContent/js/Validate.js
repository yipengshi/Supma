var validateRegExp = {
    decmal:"^([+-]?)\\d*\\.\\d+$",    //浮点数
    decmal1: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$",    //正浮点数
    decmal2: "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$",    //负浮点数
    decmal3: "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$",    //浮点数
    decmal4: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$",    //非负浮点数（正浮点数 + 0）
    decmal5: "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$",    //非正浮点数（负浮点数 + 0）
    intege: "^-?[1-9]\\d*$",    //整数
    intege1: "^[1-9]\\d*$",    //正整数
    intege2: "^-[1-9]\\d*$",    //负整数
    num: "^([+-]?)\\d*\\.?\\d+$",    //数字
    num1: "^[1-9]\\d*|0$",    //正数（正整数 + 0）
    num2: "^-[1-9]\\d*|0$",    //负数（负整数 + 0）
    ascii: "^[\\x00-\\xFF]+$",    //仅ACSII字符
    chinese: "^[\\u4e00-\\u9fa5]+$",    //仅中文
    color: "^[a-fA-F0-9]{6}$",    //颜色
    date: "^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$",    //日期
    email: "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$",    //邮件
    idcard: "^[1-9]([0-9]{14}|[0-9]{17})$",    //身份证
    ip4: "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$",    //ip地址
    letter: "^[A-Za-z]+$",    //字母
    letter_l: "^[a-z]+$",    //小写字母
    letter_u: "^[A-Z]+$",    //大写字母
    mobile: "(13|15|17|18)[0-9]{9}$",    //手机
    notempty: "^\\S+$",    //非空
    password: "^[A-Za-z0-9_-]+$",    //密码
    picture: "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$",    //图片
    picture2: "(.*)\\.(jpg|bmp|gif|jpeg|png|JPG|JPEG|BMP|GIF|PNG)$",    //图片
    qq: "^[1-9]*[1-9][0-9]*$",    //QQ号码
    rar: "(.*)\\.(rar|zip|7zip|tgz)$",    //压缩文件
    tel: "^[0-9\-()（）]{7,13}$",    //{7,18}电话号码的函数(包括验证国内区号,国际区号,分机号),{7,13}例:0519-88888888
    url: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$",    //url
    username: "^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$",    //用户名
    userallname: "^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$",    //用户全名
    deptname: "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$",    //单位名
    zipcode: "^\\d{6}$",    //邮编
    realname:"^[A-Za-z0-9\\u4e00-\\u9fa5]+$",  // 真实姓名
    companyname:"^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$",
    companyaddr:"^[A-Za-z0-9_()（）\\#\\-\\u4e00-\\u9fa5]+$",
    companysite:"^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&#=]*)?$"
};

//主函数
(function($) {
    $.fn.jdValidate = function(option, callback, def) {
        var ele = this;
        var id = ele.attr("id");
        var type = ele.attr("type");
        var rel = ele.attr("rel");
        var _onFocus = $("#" + id + validateSettings.onFocus.container);
        var _succeed = $("#" + id + validateSettings.succeed.container);
        var _isNull = $("#" + id + validateSettings.isNull.container);
        var _error = $("#" + id + validateSettings.error.container);
        if (def == true) {
            var str = ele.val();
            var tag = ele.attr("sta");
            if (str == "" || str == "-1") {
                validateSettings.isNull.run({
                    prompts: option,
                    element: ele,
                    isNullEle: _isNull,
                    succeedEle: _succeed
                }, option.isNull);
            } else if (tag == 1 || tag == 2) {
                return;
            } else {
                callback({
                    prompts: option,
                    element: ele,
                    value: str,
                    errorEle: _error,
                    succeedEle: _succeed
                });
            }
        } else {
            if (typeof def == "string") {
                ele.val(def);
            }
            if (type == "checkbox" || type == "radio") {
                if (ele.attr("checked") == true) {
                    ele.attr("sta", validateSettings.succeed.state);
                }
            }
            switch (type) {
                case "text":
                case "password":
                    ele.bind("focus", function() {
                        var str = ele.val();
                        if (str == def) {
                            ele.val("");
                        }
                        validateSettings.onFocus.run({
                            prompts: option,
                            element: ele,
                            value: str,
                            onFocusEle: _onFocus,
                            succeedEle: _succeed
                        }, option.onFocus);
                    })
                        .bind("blur", function() {
                            var str = ele.val();
                            if (str == "") {
                                ele.val(def);
                            }
                            if (validateRules.isNull(str)) {
                                validateSettings.isNull.run({
                                    prompts: option,
                                    element: ele,
                                    value: str,
                                    isNullEle: _isNull,
                                    succeedEle: _succeed
                                }, "");
                            } else {
                                callback({
                                    prompts: option,
                                    element: ele,
                                    value: str,
                                    errorEle: _error,
                                    isNullEle: _isNull,
                                    succeedEle: _succeed
                                });
                            }
                        });
                    break;
                default:
                    if (rel && rel == "select") {
                        ele.bind("change", function() {
                            var str = ele.val();
                            callback({
                                prompts: option,
                                element: ele,
                                value: str,
                                errorEle: _error,
                                isNullEle: _isNull,
                                succeedEle: _succeed
                            });
                        })
                    } else {
                        ele.bind("click", function() {
                            callback({
                                prompts: option,
                                element: ele,
                                errorEle: _error,
                                isNullEle: _isNull,
                                succeedEle: _succeed
                            });
                        })
                    }
                    break;
            }
        }
    }
})(jQuery);

//配置
var validateSettings = {
    onFocus: {
        state: null,
        container: "_error",
        style: "focus",
        run: function(option, str) {
            if (!validateRules.checkType(option.element)) {
                option.element.removeClass(validateSettings.INPUT_style2).addClass(validateSettings.INPUT_style1);
            }
            option.onFocusEle.removeClass().addClass(validateSettings.onFocus.style).html(str);
        }
    },
    isNull: {
        state: 0,
        container: "_error",
        style: "null",
        run: function(option, str) {
            option.element.attr("sta", 0);
            if (!validateRules.checkType(option.element)) {
                if (str != "") {
                    option.element.removeClass(validateSettings.INPUT_style1).addClass(validateSettings.INPUT_style2);
                } else {
                    option.element.removeClass(validateSettings.INPUT_style2).removeClass(validateSettings.INPUT_style1);
                }
            }
            option.succeedEle.removeClass(validateSettings.succeed.style);
            option.isNullEle.removeClass().addClass(validateSettings.isNull.style).html(str);
        }
    },
    error: {
        state: 1,
        container: "_error",
        style: "error",
        run: function(option, str) {
            option.element.attr("sta", 1);
            if (!validateRules.checkType(option.element)) {
                option.element.removeClass(validateSettings.INPUT_style1).addClass(validateSettings.INPUT_style2);
            }
            option.succeedEle.removeClass(validateSettings.succeed.style);
            option.errorEle.removeClass().addClass(validateSettings.error.style).html(str);
        }
    },
    succeed: {
        state: 2,
        container: "_succeed",
        style: "succeed",
        run: function(option) {
            option.element.attr("sta", 2);
            option.errorEle.empty();
            if (!validateRules.checkType(option.element)) {
                option.element.removeClass(validateSettings.INPUT_style1).removeClass(validateSettings.INPUT_style2);
            }
            if (option.element.attr("id") == "schoolinput" && $("#schoolid").val() == "") {
                return;
            }
            option.succeedEle.addClass(validateSettings.succeed.style);
        }
    },
    INPUT_style1: "highlight1",
    INPUT_style2: "highlight2"

};

//验证规则
var validateRules = {
    isNull:function(str) {
        return (str == "" || typeof str != "string");
    },
    betweenLength:function(str, _min, _max) {
        return (str.length >= _min && str.length <= _max);
    },
    isUid:function(str) {
        return new RegExp(validateRegExp.username).test(str);
    },
    isPwd:function(str) {
        return new RegExp(validateRegExp.password).test(str);
    },
    isPwd2:function(str1, str2) {
        return (str1 == str2);
    },
    isEmail:function(str) {
        return new RegExp(validateRegExp.email).test(str);
    },
    isTel:function(str) {
        return new RegExp(validateRegExp.tel).test(str);
    },
    isMobile:function(str) {
        return new RegExp(validateRegExp.mobile).test(str);
    },
    isPicture2:function(str) {
        return new RegExp(validateRegExp.picture2).test(str);
    },
    backM:function(str) {
    	if(str == 0){
    		return 0;
    	}
    	var intmb = eval(str/ 1024/1024).toFixed(0);
    	return intmb;
    },
    checkType:function(element) {
        return (element.attr("type") == "checkbox" || element.attr("type") == "radio" || element.attr("rel") == "select");
    },
    isChinese:function(str) {
        return new RegExp(validateRegExp.chinese).test(str);
    },
    isRealName:function(str) {
        return new RegExp(validateRegExp.realname).test(str);
    },
    isDeptname:function(str) {
        return new RegExp(validateRegExp.deptname).test(str);
    },
    isCompanyname:function(str) {
        return new RegExp(validateRegExp.companyname).test(str);
    },
    isCompanyaddr:function(str) {
        return new RegExp(validateRegExp.companyaddr).test(str);
    },
    isCompanysite:function(str) {
        return new RegExp(validateRegExp.companysite).test(str);
    }
};
//验证文本
var validatePrompt = {
    username:{
        onFocus:"4-20位字符，可由中文、英文、数字及“_”、“-”组成。请用纸笔牢记用户名。",
        succeed:"",
        isNull:"请输入用户登录ID。",
        error:{
            beUsed:"该用户登录ID已被使用，请使用其他ID注册。",
            badLength:"用户登录ID长度只能在4-20位字符之间。",
            badFormat:"用户登录ID只能由中文、英文、数字及“_”、“-”组成。"
        }
    },
    userallname:{
        onFocus:"40个字符以内，可由中文、英文、数字及“_”、“-”组成。",
        succeed:"",
        isNull:"请输入超市全名。",
        error:{
            beUsed:"该超市全名已被使用，请使用其他超市全名注册。",
            badLength:"超市全名长度只能40位以内字符。",
            badFormat:"超市全名只能由中文、英文、数字及“_”、“-”组成。"
        }
    },
    pwd:{
        onFocus:"6-16位字符，可由英文、数字及“_”、“-”组成。请用纸笔牢记密码。",
        succeed:"",
        isNull:"请输入密码。",
        error:{
            badLength:"密码长度只能在6-16位字符之间。",
            badFormat:"密码只能由英文、数字及“_”、“-”组成。"
        }
    },
    pwd2:{
        onFocus:"请再次输入密码。",
        succeed:"",
        isNull:"请输入密码。",
        error:{
            badLength:"密码长度只能在6-16位字符之间。",
            badFormat2:"两次输入密码不一致。",
            badFormat1:"密码只能由英文、数字及“_”、“-”组成。"
        }
    },
    mail:{
        onFocus:"请输入常用的邮箱，将用来找回密码、接收订单通知等。",
        succeed:"",
        isNull:"请输入邮箱。",
        error:{
            beUsed:"该邮箱已被使用，请更换其它邮箱。",
            badFormat:"您填写的邮箱格式不正确。",
            badLength:"您填写的邮箱过长，邮件地址只能在50个字符以内。"
        }
    },
    mobo:{
        onFocus:"请输入常用的手机号码，用于找回密码等",
        succeed:"",
        isNull:"请输入手机号码",
        error:{
            beUsed:"该手机号码已被使用，请更换其它号码。",
            badFormat:"您填写的手机格式不正确。",
            badLength:"您填写的手机号码不正确，手机号码只能11个数字。"
        }
    },
    telno:{
        onFocus:"请输入固定电话号码。例:020-89888888",
        succeed:"",
        isNull:"",
        error:{
            badFormat:"您填写的固定电话号码格式不正确。",
            badLength:"您填写的固定电话号码格式不正确。"
        }
    },
    u_area:{
        onFocus:"请选择您的超市所在片区",
        succeed:"",
        isNull:"请选择您的超市所在片区",
        error:""
    },
    user_address1:{
        onFocus:"80个字符以内，可由中文、英文、数字及“_”、“-”组成。",
        succeed:"",
        isNull:"请输入收货地址，以便供货商能正确配送货物。",
        error:{
            badLength:"收货地址长度只能80位以内字符。",
            badFormat:"收货地址只能由中文、英文、数字及“_”、“-”组成。"
        }
    },
    viewfile:{
        onFocus:"请上传您的营业执照照片，以便我们确认您的资质，谢谢。",
        succeed:"",
        isNull:"请上传您的营业执照照片，以便我们确认您的资质，谢谢。",
        error:{
        	beUsed:"请上传大小不超过2M的'.gif','.jpg','.jpeg','.png','.bmp'等格式的图片文件。",
            badFormat:"请上传大小不超过2M的'.gif','.jpg','.jpeg','.png','.bmp'等格式的图片文件。",
            badLength:"验证文件出错，麻烦您重新上传，谢谢。"
        }
    },
    authcode:{
        onFocus:"请输入图片中的字符，不区分大小写。",
        succeed:"",
        isNull:"请输入验证码。",
        error:{
            badFormat:"验证码错误。",
            badLength:"请输入验证码。"
        }
    },
    protocol:{
        onFocus:"",
        succeed:"",
        isNull:"请先阅读并同意《批发网用户协议》。",
        error:""
    },
    referrer:{
        onFocus:"如您注册并完成订单，推荐人有机会获得积分。",
        succeed:"",
        isNull:"",
        error:""
    },
    schoolinput: {
        onFocus: "您可以用简拼、全拼、中文进行校名模糊查找。",
        succeed: "",
        isNull: "请填选学校名称。",
        error: "请填选学校名称。"
    },
    empty:{
        onFocus:"",
        succeed:"",
        isNull:"",
        error:""
    }
};

var subformflag_username=false,subformflag_usermail=false,subformflag_usermobo=false,subformflag_userviewfile=false,subformflag_userauthcode=false;//提交form时候，验证是否这几项后台check完事。
var nameold,emailold,authcodeold,moboold,viewfileold;
var namestate = false,emailstate = false,authcodestate = false,mobostate = false,viewfilestate = false;
//回调函数
var validateFunction = {
    username:function(option) {
        var format = validateRules.isUid(option.value);
        var length = validateRules.betweenLength(option.value.replace(/[^\x00-\xff]/g, "**"), 4, 20);
        if (!length && format) {
            validateSettings.error.run(option, option.prompts.error.badLength);
        }
        else if (!length && !format) {
            validateSettings.error.run(option, option.prompts.error.badFormat);
        }
        else if (length && !format) {
            validateSettings.error.run(option, option.prompts.error.badFormat);
        }
        else {
            if (!namestate || nameold != option.value) {
                if (nameold != option.value) {
                    subformflag_username=false;// 后台验证前设置为false,如果form提交时候有改动，定会调用后台验证
                    nameold = option.value;
                    option.errorEle.html("<span style='color:#999'>检验中……</span>");
                    var sub_username=encodeURI($("#username").val(),"utf-8");
                    $.getJSON("registervalidate.goods?action=CheckUnicknme&uid="+sub_username, function(date) {
                        if (date.success == 0) {
                            validateSettings.succeed.run(option);
                            namestate = true;
                        } else {
                            validateSettings.error.run(option, option.prompts.error.beUsed.replace("{1}", option.value));
                            namestate = false;
                        }
                        subformflag_username=true;//用户名后台验证完事
                    })
                }
                else {
                    validateSettings.error.run(option, option.prompts.error.beUsed.replace("{1}", option.value));
                    namestate = false;
                }
            }
            else {
                validateSettings.succeed.run(option);
            }
        }
    },
    userallname:function(option) {
        var format = validateRules.isUid(option.value);
        var length = validateRules.betweenLength(option.value.replace(/[^\x00-\xff]/g, "**"),1, 40);
        if (!length && format) {
            validateSettings.error.run(option, option.prompts.error.badLength);
        }
        else if (!length && !format) {
            validateSettings.error.run(option, option.prompts.error.badFormat);
        }
        else if (length && !format) {
            validateSettings.error.run(option, option.prompts.error.badFormat);
        }
        else {
            validateSettings.succeed.run(option);
        }
    },
    pwd:function(option) {
        var str1 = option.value;
        var str2 = $("#pwd2").val();
        var format = validateRules.isPwd(option.value);
        var length = validateRules.betweenLength(option.value, 6, 16);
        $("#pwdstrength").hide();
        if (!length && format) {
            validateSettings.error.run(option, option.prompts.error.badLength);
        }
        else if (!length && !format) {
            validateSettings.error.run(option, option.prompts.error.badFormat);
        }
        else if (length && !format) {
            validateSettings.error.run(option, option.prompts.error.badFormat);
        }
        else {
            validateSettings.succeed.run(option);
            validateFunction.pwdstrength();
        }
        if (str2 != "") {
            $("#pwd2").jdValidate(validatePrompt.pwd2, validateFunction.pwd2, true);
        }
    },
    pwd2:function(option) {
        var str1 = option.value;
        var str2 = $("#pwd").val();
        var length = validateRules.betweenLength(option.value, 6, 16);
        var format2 = validateRules.isPwd2(str1, str2);
        var format1 = validateRules.isPwd(str1);
        if (!length) {
            validateSettings.error.run(option, option.prompts.error.badLength);
        } else {
            if (!format1) {
                validateSettings.error.run(option, option.prompts.error.badFormat1);
            } else {
                if (!format2) {
                    validateSettings.error.run(option, option.prompts.error.badFormat2);
                }
                else {
                    validateSettings.succeed.run(option);
                }
            }
        }
    },
    mail:function(option) {
        var format = validateRules.isEmail(option.value);
        var format2 = validateRules.betweenLength(option.value, 0, 50);
        if (!format) {
            validateSettings.error.run(option, option.prompts.error.badFormat);
        } else {
            if (!format2) {
                validateSettings.error.run(option, option.prompts.error.badLength);
            } else {
                if (!emailstate || emailold != option.value) {
                    if (emailold != option.value) {
                    	 subformflag_usermail=false; //后台验证前设置为false,如果form提交时候有改动，定会调用后台验证
                        emailold = option.value;
                        option.errorEle.html("<span style='color:#999'>检验中……</span>");
                        $.getJSON(unescape("registervalidate.goods?action=CheckUnickmail&mail=" + escape(option.value)), function(date) {
                            if (date.success == 0) {
                                validateSettings.succeed.run(option);
                                emailstate = true;
                            } else {
                                validateSettings.error.run(option, option.prompts.error.beUsed);
                                emailstate = false;
                            }
                            subformflag_usermail=true;//mail后台验证完事
                        })
                    }
                    else {
                        validateSettings.error.run(option, option.prompts.error.beUsed);
                        emailstate = false;
                    }
                }
                else {
                    validateSettings.succeed.run(option);
                }
            }
        }
    },
    mobo:function(option) {
        var format = validateRules.isMobile(option.value);
        var format2 = validateRules.betweenLength(option.value, 0, 11);
        if (!format) {
            validateSettings.error.run(option, option.prompts.error.badFormat);
        } else {
            if (!format2) {
                validateSettings.error.run(option, option.prompts.error.badLength);
            } else {
                if (!mobostate || moboold != option.value) {
                    if (moboold != option.value) {
                        subformflag_usermobo=false;//后台验证前设置为false,如果form提交时候有改动，定会调用后台验证
                    	moboold = option.value;
                        option.errorEle.html("<span style='color:#999'>检验中……</span>");
                        $.getJSON(unescape("registervalidate.goods?action=CheckUnickmobo&mobo=" + escape(option.value)), function(date) {
                            if (date.success == 0) {
                                validateSettings.succeed.run(option);
                                mobostate = true;
                            } else {
                                validateSettings.error.run(option, option.prompts.error.beUsed);
                                mobostate = false;
                            }
                            subformflag_usermobo=true;//手机号码后台验证完事
                        })
                    }
                    else {
                        validateSettings.error.run(option, option.prompts.error.beUsed);
                        mobostate = false;
                    }
                }
                else {
                    validateSettings.succeed.run(option);
                }
            }
        }
    },   
    referrer:function(option) {
        var bool = validateRules.isNull(option.value);
        if (bool) {
            option.element.val("可不填");
            return;
        } else {
            validateSettings.succeed.run(option);
        }
    },
    telno:function(option) {
        var bool = validateRules.isNull(option.value);
        var format = validateRules.isTel(option.value);
        if (bool) {
            return;
        } else {
        	if(!format){
                validateSettings.error.run(option, option.prompts.error.badFormat);	
        	}else{
                validateSettings.succeed.run(option);
        	}
        }
    },
    u_area:function(option) {
        var bool = validateRules.isNull(option.value);
        if (bool) {
        	 validateSettings.error.run(option, option.prompts.isNull);	
        } else {
        	validateSettings.succeed.run(option);
        }
    },
    user_address1:function(option) {
        var format = validateRules.isUid(option.value);
        var length = validateRules.betweenLength(option.value.replace(/[^\x00-\xff]/g, "**"),1, 80);
        if (!length && format) {
            validateSettings.error.run(option, option.prompts.error.badLength);
        }
        else if (!length && !format) {
            validateSettings.error.run(option, option.prompts.error.badFormat);
        }
        else if (length && !format) {
            validateSettings.error.run(option, option.prompts.error.badFormat);
        }
        else {
            validateSettings.succeed.run(option);
        }
    },
    viewfile:function(option) {
        var format = validateRules.isPicture2(option.value);
//        alert("callviewfile");
//        alert(option.value);
//        alert(format);
        if (!format) {
            validateSettings.error.run(option, option.prompts.error.badFormat);
        } else {

            if (!viewfilestate || viewfileold != option.value) {
                if (viewfileold != option.value) {
                	viewfileold = option.value;
                    var filesizeleng = document.getElementById("user_notuser2").files[0].size;//文件大小
                   if(validateRules.backM(filesizeleng) <=2){//文件小于等于2M
                       subformflag_userviewfile=false; //后台验证前设置为false,如果form提交时候有改动，定会调用后台验证
                       option.errorEle.html("<span style='color:#999'>检验中……</span>");
                       var filename = $("#user_notuser2").val();
                       $.ajaxFileUpload
                       (
                         {
                              url:'registervalidate.goods', //你处理上传文件的服务端
                              secureuri: false, //一般设置为false
                              fileElementId: 'user_notuser2', // 上传文件的id、name属性名,注意必须有name属性!!!
                              dataType: 'json',
                              data:{action:'CheckViewFile',filecount:'1'},
                              success: function (date) {
                           	   //alert(validatingflag);
   			                          if (date.success == 0) {
   			                              validateSettings.succeed.run(option);
   			                              viewfilestate = true;
   			                          } else if(date.success == 2){
   			                              validateSettings.error.run(option, option.prompts.error.beUsed);
   			                              viewfilestate = false;
   			                          }else if(date.success == 3){
   			                              validateSettings.error.run(option, option.prompts.error.badFormat);
   			                              viewfilestate = false;
   			                          }else{
   			                              validateSettings.error.run(option, option.prompts.error.beUsed);
   			                              viewfilestate = false;
   			                          }
   			                          subformflag_userviewfile=true;//上传文件后台验证完事
                                    }
                                 }
                           )
                   }else{
                       validateSettings.error.run(option, option.prompts.error.badFormat);
                       viewfilestate = false;
                   }
                }
                else {
                    validateSettings.error.run(option, option.prompts.error.badFormat);
                    viewfilestate = false;
                }
            }
        }
    },   
    schoolinput: function(option) {
        var bool = validateRules.isNull(option.value);
        if (bool) {
            validateSettings.error.run(option, option.prompts.error);
            return;
        } else {
            validateSettings.succeed.run(option);
        }
    },
    authcode:function(option) {
        if (!authcodestate || authcodeold != option.value) {
            if (authcodeold != option.value) {
                subformflag_userauthcode=false;//后台验证前设置为false,如果form提交时候有改动，定会调用后台验证
                authcodeold = option.value;
                option.errorEle.html("<span style='color:#999'>检验中……</span>");
                $.getJSON(unescape("registervalidate.goods?action=AuditCode&authcode=" + escape(option.value)), function(date) {
 
                    if (date.success == 0) {
                        validateSettings.succeed.run(option);
                        authcodestate = true;
                    } else if(date.success == 1){
                        validateSettings.error.run(option, option.prompts.error.badFormat);
                        authcodestate = false;
                    }else{
                        validateSettings.error.run(option, option.prompts.error.badLength);
                        authcodestate = false;
                    }
                    subformflag_userauthcode=true;//验证码后台验证完事
                })
            }
            else {
                validateSettings.error.run(option, option.prompts.error.badFormat);
                authcodestate = false;
            }
        }
        else {
            validateSettings.succeed.run(option);
        }
    },
    protocol:function(option) {
        if (option.element.attr("checked") == true) {
            option.element.attr("sta", validateSettings.succeed.state);
            option.errorEle.html("");
        } else {
            option.element.attr("sta", validateSettings.isNull.state);
            option.succeedEle.removeClass(validateSettings.succeed.style);
        }
    },
    pwdstrength:function() {
        var element = $("#pwdstrength");
        var value = $("#pwd").val();
        var strength = 0;
        if (value.length >= 6 && validateRules.isPwd(value)) {
            $("#pwd_error").empty();
            element.show();
            if (/\d/i.test(value)) {
                strength += 1;
            }
            if (/[a-z]/i.test(value)) {
                strength += 1;
            }
            if (/[-_]/i.test(value)) {
                strength += 1;
            }
            switch (strength) {
                case 1:
                    element.removeClass().addClass("strengthA");
                    break;
                case 2:
                    element.removeClass().addClass("strengthB");
                    break;
                case 3:
                    element.removeClass().addClass("strengthC");
                    break;
                default:
                    break;
            }
        } else {
            element.hide();
        }
    },
    checkGroup:function(elements) {
        for (var i = 0; i < elements.length; i++) {
            if (elements[i].checked) {
                return true;
            }
        }
        return false;
    },
    checkSelectGroup:function(elements) {
        for (var i = 0; i < elements.length; i++) {
            if (elements[i].value == -1) {
                return false;
            }
        }
        return true;
    },
    showPassword:function(type) {
        var v1 = $("#pwd").val(),s1 = $("#pwd").attr("sta"),c1 = document.getElementById("pwd").className,t1 = $("#pwd").attr("tabindex");
        var v2 = $("#pwd2").val(),s2 = $("#pwd2").attr("sta"),c2 = document.getElementById("pwd2").className,t2 = $("#pwd2").attr("tabindex");
        var P1 = $("<input type='" + type + "' value='" + v1 + "' sta='" + s1 + "' class='" + c1 + "' id='pwd' name='pwd' tabindex='" + t1 + "'/>");
        $("#pwd").after(P1).remove();
        $("#pwd").bind("keyup",
            function() {
                validateFunction.pwdstrength();
            }).jdValidate(validatePrompt.pwd, validateFunction.pwd)
        var P2 = $("<input type='" + type + "' value='" + v2 + "' sta='" + s2 + "' class='" + c2 + "' id='pwd2' name='pwd2' tabindex='" + t2 + "'/>");
        $("#pwd2").after(P2).remove();
        $("#pwd2").jdValidate(validatePrompt.pwd2, validateFunction.pwd2);
    },
    FORM_submit:function(elements) {
        var bool = true;
        for (var i = 0; i < elements.length; i++) {
            if ($(elements[i]).attr("sta") == 2) {
                bool = true;
            } else {
                bool = false;
                break;
            }
        }
        return bool;
    }
};
