// JavaScript Document
var getwxcodestatus = 0;
var weixinlogin = 0;
var user_time = 0;
var weixin_url = 'http://www.to8to.com/api/weixin/run.php';
!function(){
   var loginBegin = {
   	init:function(){
   		loginTabChange();//登录初始
   	}
   },
   otherLogin = {
    init:function(){
      otBinding();//绑定初始
    }
   },
   userReg = {
    init:function(){
      regDoc();//注册初始
    }
   };
   window.logBegin = loginBegin;
   window.bindBegin = otherLogin;
   window.regBegin = userReg;
}(jQuery);
function loginTabChange(){
	jq('.login_tab > ul > li').click(function(){
		var item = jq('.login_tab > ul > li'),
		    obj = jq(this),
		    objChangeValue = item.index(obj);
		item.removeClass('on');
		obj.addClass('on')
		jq('.login_wrap').hide();
		jq('.login_wrap').eq(objChangeValue).show();
		if(obj.attr('id')=='weixinlogin' && weixinlogin==0)
		{
			weixinlogin =1;
			getwxcode();
		}
	});
	jq('.login_select > span').click(function(){
		var obj = jq(this);
		obj.parent().find('input').focus();
	});
    jq('.login_select > input').keydown(function(){
    	jq(this).parent().find('span').hide();
    }).bind('blur',function(){
    	if(jq(this).val()==""){
    		jq(this).parent().find('span').show();
    	}
    }).focus(function() {
        jq(this).parent().removeClass('height_auto').find('.index_check_two').remove();
    });
    jq('#reg_form').keyup(function(e){
        if(e.keyCode == 9){
          var m = jq('#userNum').val();
          if(m!=""){
            jq('#userNum').parent().find('span').hide();
          }
        }
    })
    jq('.login_text').focus(function(){
        jq(this).hide();
        jq('#userNum').val(' ').val('').show().focus();
    })

}

function updatecode(){
    var str = '<img src="http://img.to8to.com/to8to_img/icon/loading.gif" class="loading_gif">'
    jq('#img_weixincode').html(str)
    //jq('#weixincode').renmove();
   // jq('#login_error').show();
		getwxcode();
}

function showhelp()
{
  jq('.wl_codeLayer').hide();
  jq(".wechat_help").show();
	/*jq(".wechat_login").hide();
	jq(".wechat_help").show();*/
}

function hidehelp(){
  jq('.wl_codeLayer').show();
  jq(".wechat_help").hide();
}

function showweixincode()
{
	getwxcode();
	jq(".wechat_help").hide();
	jq(".wechat_dengru").show();
	jq("#login_nomal").show();
}

function loginCheck(obj){
	try{
		clickStream.getCvParams('1_12_2_1');
	}catch(e){

	}
   jq('.bderror').remove();
   var a = jq('#userName').checkForm({className:"index_check",content:["请输入邮箱/手机号/用户名"],type:[1],checkFormType:obj, displayNum:true});
   var b = jq('#userNum').checkForm({className:"index_check",content:["密码不可以为空"],type:[1],checkFormType:obj, displayNum:true});

   var codeFlag = jq('#userCode').length == 0?false:true,
        c;
   if(codeFlag) {
        c = jq('#userCode').checkForm({className:"index_check",content:["验证码不可以为空"],type:[1],checkFormType:obj, displayNum:true});
   }
   // var c = jq('#userCode').checkForm({className:"index_check",content:["验证码不可以为空"],type:[1],checkFormType:obj, displayNum:true});
   if( a != 0 || b != 0 || (c != 0 && codeFlag) ){
	   return false;
   }
}

function otBinding(){
    window.onload = function() {
        jq('input[type="text"]').val('');
    };
  jq('.reg_form > dl > dd > span').click(function(){
    var obj = jq(this);
    obj.parent().find('input').focus();
  });
  jq('.reg_form > dl > dd > input').keydown(function(){

    jq(this).parent().find('span').hide();
  }).bind('blur',function(){
    if(jq(this).val()==""){
      jq(this).parent().find('span').show();
    }
  });
  jq('.otl_mm').focus(function(){
    jq(this).hide();
    jq('#userNum').val(' ').val('').show().focus();
    //jq('#userNum').show().focus();
    //jq(this).attr('type','password');

   })
  window.onload = function(){
    if(jq('.otl_left > dl > dd > input').val() !=""){
      jq('.otl_left > dl > dd > span').hide();
    }
  }
}

function checkOtform(obj){
	var loginway = jq("#loginway").val();
	try{
		if(loginway=='1')//qq
		{
			var ptag = '1_12_2_4';
		}
		if(loginway=='2')//weibo
		{
			var ptag = '1_12_2_3';
		}
		if(loginway=='4')//weixin
		{
			var ptag = '1_12_2_2';
		}
		clickStream.getCvParams(ptag);
	}catch(e){

	}

   jq('.bderror').remove();
   var a = jq('#userName').checkForm({className:"index_check",content:["请输入邮箱/手机号/用户名"],type:[1],checkFormType:obj, displayNum:true});
   var b = jq('#userNum').checkForm({className:"index_check",content:["密码不可以为空"],type:[1],checkFormType:obj, displayNum:true});
   if( a != 0 || b != 0){
	   return false;
   }
}


//获取二维码
function getwxcode()
{
	//报错,先return chenxy
	return;

	jq.ajax({
		async:true,
        type:"GET",
        dataType: 'jsonp',
        url:weixin_url,
        data:{action:'createQrcode',cookie_id:'test',data:'createWxCode',type:0},
        success:function(res){
        		if(res.code==0)
        		{
	              jq('#login_error').hide();
	              jq('.wl_font').show();
			//setCookie('qrcode_id',res.qrcode_id,90);
	              var heaimg = new Image();
	              heaimg.src = res.url;
	              heaimg.onload = function (){
	                var img = '<img src="'+res.url+'" id="weixincode" width="140" height="140"><i></i>';
	                jq("#img_weixincode").html(img);

	              }

	              qrcodeSession(res.qrcode_id);
        			jq("#weixincode").attr('src',res.url);
        			jq(".wechat_login_success").hide();
        			jq("#login_nomal").show();
        			getwxcodestatus = 0;
					    getwxstatus(res.qrcode_id);
        		}
        		else
        		{
        			alert(res.msg);
        		}

        }
    });

}

//将微信qrcode_id存入session加密
function qrcodeSession(qrcode_id)
{
	jq.ajax({
		async:true,
        type:"POST",
        dataType: 'jsonp',
        url:"http://www.to8to.com/new_login.php",
        data:{action:'addQrcodeSession',qrcode_id:qrcode_id}
    });
}

//获取微信扫码状态
function getwxstatus(qrcode_id)
{
		jq.ajax({
		async:true,
        type:"GET",
        dataType: 'jsonp',
        url:weixin_url,
        data:{action:'getScanState',cookie_id:'test',qrcode_id:qrcode_id},
        timeout:15000,     //ajax请求超时时间30秒
        success:function(data,textStatus){
        	if(data.code=="0"){
            jq("#login_nomal").hide();
            jq('.wl_font').hide();
        		jq(".wechat_login_success").hide();
        		jq("#login_success").show();
        		getwxuser(qrcode_id);
            }
        	if(data.code=='405')
        	{
        		getwxcodestatus = getwxcodestatus+1;
            	if(getwxcodestatus > 19)
            	{
            		jq("#login_nomal").hide();
            		jq(".wechat_login_success").hide();
            		jq("#login_error").show();
            	}else
            	{
            		getwxstatus(qrcode_id);
            	}
        	}
        },
        error:function(XMLHttpRequest,textStatus,errorThrown){
            if(textStatus=="timeout"){
            	getwxcodestatus = getwxcodestatus+1;
            	if(getwxcodestatus > 19)
            	{
            		jq(".wechat_login_success").hide();
            		jq("#login_error").show();
                jq('.wl_font').hide();
                jq('#login_nomal').hide();
            	}else
            	{
            		getwxstatus(qrcode_id);
            	}

            }
    	}
    });

}


//获取用户信息
function getwxuser(qrcode_id)
{
	jq.ajax({
		async:true,
        type:"GET",
        dataType: 'jsonp',
        url:weixin_url,
        data:{action:'getLoginState',cookie_id:'test',qrcode_id:qrcode_id},
        timeout:15000,     //ajax请求超时时间30秒
        success:function(res){
        	if(res.code=="405"){
        		if(user_time>7)
        		{
        			jq(".wechat_login_success").hide();
            		jq('.wl_font').hide();
            		jq("#login_error").show();
        		}
            	else
            	{
            		user_time = user_time+1;
            		getwxuser(qrcode_id);
            	}
            }
        	if(res.code=="0"){
        		window.location.href='/loginfromweixin/callback.php?user_name='+encodeURI(res.user.nickname)+'&open_id='+res.user.openid+'&qrcode_id='+res.user.qid+'&header_url='+res.user.pic_header_url+'&unionID='+res.user.unionID;
            }

        },
        error:function(XMLHttpRequest,textStatus,errorThrown){
            if(textStatus=="timeout"){
            	if(user_time>7)
        		{
            		jq(".wechat_login_success").hide();
            		jq('.wl_font').hide();
            		jq("#login_error").show();
        		}
            	else
            	{
            		user_time = user_time+1;
            		getwxuser(qrcode_id);
            	}

            }
    	}
    });

}


//验证组件
!function(){
	jq.fn.checkForm = function(settings){
		settings=jq.extend({},jq.checkForm.defaults,settings);
		if( settings.type.length == 0 ) {
            return false;
        }
		var cf = {};
		cf.fn = {};
		cf.fn.regType = [/^13[0-9]{1}[0-9]{8}$|14[0-9]{1}[0-9]{8}$|15[0-9]{1}[0-9]{8}$|18[0-9]{1}[0-9]{8}$|17[0-9]{1}[0-9]{8}$/, /^([0-9]+)$/,/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/,/^([0-9.]+)$/,/^[0-9]{5,}$/,/^((0\d{2,3})(-)?)?(\d{7,8})(-(\d{3,}))?$|^13[0-9]{1}[0-9]{8}$|14[0-9]{1}[0-9]{8}$|15[0-9]{1}[0-9]{8}$|18[0-9]{1}[0-9]{8}$|17[0-9]{1}[0-9]{8}$/,/^[1-9]\d{0,7}(\.\d{1,2})?$/,/^[1-9][0-9]{0,3}$/,/^\d{1,4}(\.\d{1})?$/,/^(([1-9]\d{0,6})|0)(\.\d{1})?$/,/^[A-Za-z0-9]{8,20}$/,/^((0\d{2,3})(-)?)?(\d{7,8})(-(\d{3,}))?$/,/^[a-z]([a-z0-9]*[-_]?[a-z0-9]+)*@([a-z0-9]*[-_]?[a-z0-9]+)+[\.][a-z]{2,3}([\.][a-z]{2})?$/i,/^[a-zA-Z0-9_\u4e00-\u9fa5]{4,15}$/, /^[a-zA-Z0-9_\-\(\)（）\u4e00-\u9fa5]{4,40}$/];//0:手机。1：纯数字。2：EMAIL。3：数字加.。4：QQ号。5:手机或固话。6.价格。7.面积（<=10000）8.预算（0.1万-9999.9万）。9.清单价格（0.1元-999.9万）。10.字母数字(8-20)。11.固定电话。12.邮件地址。13.中英文，数字及_。14.公司名称。
		cf.fn.obj = this;
		cf.fn.str = '';
		cf.fn.count = 0;
	    cf.fn.obj.focus(function(){
			cf.fn.str = '';
            jq.fn.prototype.removeWrongText(jq(this), settings.parCls, settings);
		});
		if(!settings.checkFormType){
			cf.fn.obj.blur(function() {
				jq.fn.prototype._check(cf,settings,jq(this));
			});
		}else if(settings.checkFormType && settings.checkType == "text"){
			var g = jq.fn.prototype._check(cf,settings,jq(this));
			return g;
		}else if(settings.checkFormType && settings.checkType == "select"){
            var g = jq.fn.prototype._check(cf,settings,jq(this));
            return g;
		}
	};
	jq.fn.prototype = {
		_check:function(cf,settings,blurObj){
			cf.fn.str = '';
            cf.fn.str += '<div class="'+settings.className+'"><'+settings.labl+' class="'+settings.lablClass+'"></'+settings.labl+'>';
			//blurObj.parent().find('div.'+settings.className+'').remove();
			if(blurObj.parent().find('div.'+settings.className+'').length != 0){return false;}
			var cfPos = [];
			if(settings.displayNum  && jq(settings.checkFormType).parent().find('div.'+settings.className+'').length == 1) {
               return;
			}
			for(var i = 0; i < settings.type.length ; i++){
				if(settings.type[i] == 1 && settings.checkType =="text" && settings.content[0] != "" && (blurObj.val() == "" || (blurObj[0].type == "select-one" && blurObj.val() == "0"))){
					cf.fn.str += ''+settings.content[0]+'</div>';
                    jq.fn.prototype.addWrongText(blurObj, settings.parCls, cf.fn.str, settings.checkType);
					cf.fn.count = 1;

				} else if(settings.type[i] == 2 && settings.checkType =="text" && blurObj.val() !=""){
					var result = false;
					if(typeof settings.reg == "object" && !settings.moreReg) {
						result = jq.fn.prototype.checkWordLen(settings.reg.len, settings.reg.range, blurObj.val());
					}else if(typeof settings.reg == "number" && !(blurObj.val().match(cf.fn.regType[settings.reg]) == null)){
                        result = true;
                    }else if(typeof settings.reg == "object" && settings.moreReg ){
                        var moreRegResult = false;
                        for(var i =0; i< settings.reg.length; i++){

                            if(blurObj.val().match(cf.fn.regType[settings.reg[''+i+'']]) != null){
                                moreRegResult = true;
                            }
                        }
                        if(moreRegResult == true){
                            result = true;
                        }

                    }

					if(!result) {
						cf.fn.str += ""+settings.content[1]+'</div>';
                        jq.fn.prototype.addWrongText(blurObj, settings.parCls, cf.fn.str, settings.checkType);
						cf.fn.count = 1;
					}
				}else if(settings.type[i] == 1 && settings.checkType == "select" && blurObj.find('option:selected').attr('value') == ""){
                    cf.fn.str += ""+settings.content[0]+'</div>';
                    jq.fn.prototype.addWrongText(blurObj, settings.parCls, cf.fn.str, settings.checkType);
					cf.fn.count = 1;
				};
			};
			if(cf.fn.count != 1) {
                return 0;
            }
		},
		checkWordLen:function(maxLen, range, val) {
        	var len;
			len = val.length;
        	if(!range) {
				if(maxLen < len) {
					return false;
				}
			} else {
				if(len > range.dmax || len < range.dmin) {
					return false;
				}
			}

        	return true;
        },
        addWrongText: function(obj, cls, str, chkType) {
            if(!cls) { //未传class
                obj.parent().addClass('height_auto');
                obj.parent().append(str);
            } else {
                obj.parents(cls).addClass('height_auto');
                obj.parents(cls).append(str);
            }

            if(chkType != 'select') {
                obj.css('border-color','#ff6767');
            }
        },
        removeWrongText: function(obj, cls, settings) {
            if(!cls) { //未传class
                obj.parent().removeClass('height_auto');
                obj.parent().find('div.'+settings.className+'').remove();
            } else {
                obj.parents(cls).removeClass('height_auto');
                obj.parents(cls).find('div.'+settings.className+'').remove();
            }
        }
	};
	// 默认值
	jq.checkForm={defaults:{
		calssName:"checkFormDefault", //报错字符串的class
        content:[], //报错内容数组
		type:[], //报错类型，1为空，2其他错
		reg:"", //报错类型2时候的正则表达式
        moreReg:false,
		checkType:"text",//检测类型
        labl:'em',//错误提示的标签
        lablClass:'',//错误提示的标签的类名
        parCls: ''//错误提示所加父元素的标示
    }};
}(jQuery);

function regDoc(){
	//判断如果是用户注册 on=注册
	var flag = jq('.reg_tab > ul > li').eq(1).hasClass('on');
	console.log(flag);
    jq('input.reg_text').focus(function() {
        //jq(this).hide().prev().hide();
        //jq(this).next().val('1').show().val('').focus().next().show();
    });
    if(!flag) {//个人注册
	 console.log("个人注册");
        window.onload = function(){
            //jq('input[type="text"]').val('').next('span').show();
            //jq('input[type="password"]').val('1').val('');
        }
    }
	jq('.reg_tab > ul > li').click(function(){
        var item = jq('.reg_tab > ul > li'),
			obj = jq(this),
			objChangeValue = item.index(obj);
		item.removeClass('on');
		obj.addClass('on');
		jq('.login_wrap').hide();
		jq('.login_wrap').eq(objChangeValue).show();
		jq('.bderror').remove();
	});

  	//jq('input[type="text"], input[type="password"]').placeholder({oLabel: 'span'});
    jq('input[type="text"]').focus(function() {
        jq(this).parent().removeClass('height_auto').find('.index_check').remove();
    });


  var radioSelect =  jq('.company_user > dl > dd.cu_layer_one > label');
  radioSelect.find('input').click(function(){
    var rsValue = radioSelect.index(jq(this).parent());
    radioSelect.find('input').attr('checked',false);
    jq(this).prop('checked',true);


  })
   jq('.company_user > dl > dd > label').click(function(){
    var obj = jq(this);
    obj.parent().find('input').focus();
  });
  jq('.company_user > dl > dd > input').keydown(function(){
    jq(this).parent().find('label').hide();
  }).bind('blur',function(){
    if(jq(this).val()==""){
      jq(this).parent().find('label').show();
    }
  });

    //显示隐藏验证码
    jq('.send_auto').click(function(){
        var authCode = jq('.auth_code'),
            email = jq('#emailAndPhone'),
            tip = email.parent().find('.index_check').length;

        if(authCode.is(':hidden')) {
            if(email.val() != '' && tip == 0) {
                authCode.show();
                jq('.authCodeText').focus();
            } else if(tip == 0) {
                addWrongTip({targ:'#emailAndPhone', tip: '请输入邮箱/手机号码'});
                //email.focus();
            }
        } else {
            authCode.hide();
        }
    });

    checkRegInfo();
    recordValueChg();


    //提交
	jq('.btn_login').on('click', function() {
		mitRegData();
	});

    //回车自动提交
    jq('#reg_form input').on('keydown', function(event) {
        var event = event || window.event,
            code = event.keyCode,
            targ = jq(event.target || event.srcElement);

        if(code == 13) {
            jq('.btn_login').trigger('click');
            targ.blur();
            if(targ.parent().find('.index_check').length != 0) {
                targ.css('border-color', '#ff6767');
            }
        }
    });
}

function mitRegData() {
	var rslt = checkNullFn();
	if(!rslt) {
		return false;
	}

	jq('#reg_form').find('input:focus').trigger('blur');
	if(jq('#reg_form').find('div.index_check').length == 0) {
		regObj.chgFlag = 1;
        checkRepeatAndWrong({num:0, val:1, mit: 1});
	}
}


var regObj = {chgFlag: 0};

//记录value变化的函数，防止重复验证
function recordValueChg() {
	jq('#emailAndPhone, #userName, #autoCode').on('change', function() {
		regObj.chgFlag = 1;
	});
}


function checkNullFn() {
	var obj = jq('#reg_form').find('.btn_login').parent();
	var a = jq('#emailAndPhone').checkForm({className:"index_check",content:["请输入邮箱/手机号"],type:[1], checkFormType: obj});
	var b = jq('#userName').checkForm({className:"index_check",content:["用户名不可以为空"],type:[1], checkFormType: obj});
	var c = jq('#useNum').checkForm({className:"index_check",content:["密码不可以为空"],type:[1], checkFormType: obj});
	var d = jq('#autoCode').checkForm({className:"index_check",content:["验证码不可以为空"],type:[1], checkFormType: obj});
    if(c != 0) {
        jq('#useNum').prev('input').css('border-color', '#ff6767');
    }
	if(a==0&&b==0&&c==0&&d==0) {
		return true;
	}

}

//检测重复与错误
function checkRepeatAndWrong(obj) {
	console.log(obj);
	if(obj.dom !== null && obj.num == 0){
		obj.dom.css({"border":"1px solid #ccc"});
	}
	//没有服务
	return true;

	if(obj.num == 0 && obj.val != '' && regObj.chgFlag == 1) {

		var myUrl = 'http://www.to8to.com/reg/reg_new.php?ajax_reg_check=1';
		myUrl += '&email_mobile='+ encodeURIComponent(jq('#emailAndPhone').val()) + '&username='+ encodeURIComponent(jq('#userName').val()) + '&yzm=' + encodeURIComponent(jq('#autoCode').val());

		jq.ajax({
			type: 'get',
			url: myUrl,
			dataType:'json',
			success: function(res) {
				var flag = true;
				if(res.email_moblie.data == -1) {//手机或邮箱失败
					if(jq('#emailAndPhone').siblings('.index_check').length == 0) {
						addWrongTip({targ: '#emailAndPhone', tip: '邮箱/手机号已被使用，请重新输入'});
					}
					flag = false;
				}

				if(res.username.data == -1) {//用户名失败
					if(jq('#userName').siblings('.index_check').length == 0) {
						addWrongTip({targ: '#userName', tip: '此用户名已被使用，请重新输入'});
					}
					flag = false;
				}

				if(res.yzm.data == -1) {//验证码输入错误
					if(jq('#autoCode').siblings('.index_check').length == 0) {
						addWrongTip({targ: '#autoCode', tip: '验证码错误，请重新输入'});
					}
					flag = false;
				}

				if(flag) {
					regObj.chgFlag = 0;
				}

				if(flag && obj.mit == 1) {//需要提交
					jq('#reg_form').submit();
				}
			}
		});
	}
}

function submitRegData() {
	jq.ajax({
		type: 'post',
		url: 'http://www.to8to.com/reg/reg_new.php',
		data: {mobile: jq('#emailAndPhone').val(), username: jq('#userName').val(), password1: jq('#useNum').val(), mobile_yzm: jq('#autoCode').val(), indentity: jq('#indentity').val()},
		dataType:'json',
		success: function(res) {
			window.location.reload();
		}
	});
}

//检测邮箱或手机重复
function checkMailRepeat(obj) {
	var f = jq('#emailAndPhone').checkForm({className:"index_check",content:["请输入邮箱/手机号码","邮箱或手机号码错误"],type:['',2],reg:[0,2],moreReg:true,checkFormType:obj});
	checkRepeatAndWrong({num:f, val: jq('#emailAndPhone').val(),dom:jq('#emailAndPhone')});
}

//检测用户名重复
function checkUserRepeat(obj) {
	var b = jq('#userName').checkForm({className:"index_check",content:["用户名不可以为空", '用户名由中英文、数字及"_"组成，4-15位字符'],type:['',2],reg: 13,checkFormType:obj});
	if(b === 0) {
		var e = jq('#userName').val().match(/^[0-9]+$/)?1:0;
		if(e == 1) {
			addWrongTip({targ: '#userName', tip: '用户名不能为纯数字'});
		}
		checkRepeatAndWrong({num:e, val: jq('#userName').val(),dom:jq('#userName')});
	}
}

//检测验证码
function checkAuthCode(obj) {
	var a = jq('#autoCode').checkForm({className:"index_check",content:["验证码不可以为空"],type:[''],checkFormType:obj});
	checkRepeatAndWrong({num:a, val: jq('#autoCode').val(),dom:jq('#autoCode')});
}

//注册检测
function checkRegInfo() {
	jq('#reg_form').find('div.index_check').remove();
	jq('#emailAndPhone').blur(function() {
		checkMailRepeat(this);
	});
	jq('#userName').blur(function() {
		checkUserRepeat(this);
	});

	var c = jq('#useNum').checkForm({className:"index_check",content:["密码不可以为空","密码太弱，请重新设定"],type:['',2],reg:{range:{dmin:6, dmax:30}}});

    // jq('#useNum').bind('blur', function() {
    //     var c = jq('#useNum').checkForm({className:"index_check",checkFormType:this,content:["密码不可以为空","密码太弱，请重新设定"],type:['',2],reg:{range:{dmin:6, dmax:30}}});
    //     if(c == 0) {
    //         var myFlag = /\s/.test(jq('#useNum').val());
    //         if(myFlag) {
    //             addWrongTip({targ:'#useNum', tip:'密码不能包含空格'});
    //         }
    //     }
    // });

	jq('#autoCode').blur(function() {
		checkAuthCode(this);
	});
	jq('#as_service').click(function() {
		var xx = jq(this).parents('.safe').next();
		if(jq(this).is(':checked')) {
			xx.click(function() {
				mitRegData();
			});
		} else {
			xx.unbind('click');
		}
		xx.toggleClass('btn_login_no');
	});
}

//添加错误提示
function addWrongTip(obj) {
	var str = '<div class="index_check"><em></em>'+obj.tip+'</div>';
	jq(obj.targ).css('border-color','#ff6767').parent().addClass('height_auto').find('.index_check').remove().end().append(str);
}

function companyRegCheck(obj){
  jq('#sl_service').bind('change', chkServicePro);
  var a = jq('#cu_company').checkForm({className:"index_check",content:["公司名称不可以为空", '公司名称由中英文、数字及"-"、"_"、"()"、"（）"组成的4-40位字符'],type:[1,2],reg: 14,checkFormType:obj, displayNum:true});
  var b = jq('#cu_username').checkForm({className:"index_check",content:["联系人不可以为空"],type:[1],checkFormType:obj, displayNum:true});
  var c = jq('#cu_phone').checkForm({className:"index_check",content:["手机号码不可以为空","手机号错误，请重新输入"],type:[1,2],reg:0,checkFormType:obj, displayNum:true});
  var d = jq('#cu_email').checkForm({className:"index_check",content:["邮箱不可以为空","邮箱错误，请重新输入"],type:[1,2],reg:2,checkFormType:obj, displayNum:true});
  var e = jq("#shen").checkForm({className:"index_check",content:["请选择所在城市"],type:[1],checkFormType:obj, displayNum:true,checkType:"select"});
  if(e == 0) {
    var f = jq("#city").checkForm({className:"index_check",content:["请选择所在城市"],type:[1],checkFormType:obj, displayNum:true,checkType:"select"});
  }

  if(a==0 && b==0 && c==0 && d==0 && e==0 && f==0 ){
    // var g = jq('#sl_service').prop('checked');
    // if(!g && jq('.ic_service').length != 1){
    //   var str = '<div class="index_check ic_service"><em></em>请阅读服务协议</div>'
    //   jq('.safe_login').after(str)
    // }else if(g){
    //   jq('.ic_service').remove();
    //   jq(".company_reg").submit();
    // }
    var myFlag = chkServicePro();
    if(myFlag) {
        jq(".company_reg").submit();
    }
  }
}

function chkServicePro() {
    var g = jq('#sl_service').prop('checked');
    if(!g && jq('.ic_service').length != 1){
      var str = '<div class="index_check ic_service"><em></em>请阅读服务协议</div>'
      jq('.safe_login').after(str)
    }else if(g){
      jq('.ic_service').remove();
      return true;
    }
}



function checkSubmitEmail(val){
	if(!val.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)){
	 return false;
	}
	return true;
}

function checkSubmitMobil(val){

	if(!val.match(/^1[3|4|5|7|8][0-9]\d{4,8}$/)){
	 return false;
	}
	return true;
}

//申请成功弹窗
function regSuccessBox() {
	 var successStr = '<div class="dlzc_box"><div class="mod_pagetip"><span class="mod_pagetip_ico"><em class="ico_tip_ok"></em></span><div class="mod_pagetip_bd"><div class="mod_pagetip_title">提交申请成功！</div><div class="mod_pagetip_info">土巴兔客服将于1-3个工作日与您联系</div></div></div></div>';
    jq('.window_box').windowBox({
      width:460,
      title:"提示",
      wbcStr:successStr
    });
}

function checkdata(obj,type){
	var value = obj.val();
	if(type=="email")
	{

	}else if(type=="mobile"){


	}else if(type=='username'){


	}
}