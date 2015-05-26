// JavaScript Document
var labelValueObj = jq(".default_value"); //label代替显示文本框的value值
var input_textObj = jq("input[type='password'] , input[type='text']"); //文本框和密码框
var accountNmObj = jq("input[name='username']");//账号
var mobileObj = jq("input[name='mobile']");//手机号码
var mobileCodeObj = jq("input[name='dxyzm']");//手机验证码
var passWordObj = jq("input[name='password']");//新密码
var repeatPassWordObj = jq("input[name='password2']");//确认密码
var mathCodeObj = jq("input[name='txt_mathcode2']");//安全验证码
var times_ = 0;
var timerc = 0;
//label代替input的value

//初始化
function form_init(){

    input_textObj.val("");
    labelValueObj.show();
    input_textObj.css("border","1px #ccc solid").nextAll(".message").hide();

    // clearInterval(timer);
    // jq('.post_btn').val('获取验证码').attr("disabled", false);
    // timerc = 180;
}

labelValueObj.click(function(){
    jq(this).hide();
    jq(this).next(jq("input[type='text'] , input[type='password']")).focus();

});

input_textObj.focus(function(){

    jq(this).prev(".default_value").hide();
    jq(this).css("border","1px #00b34b solid").nextAll(".message").removeClass(".msg_error , .msg_success ").hide();  //默认文本(密码)框获得焦点时边框变绿色
    if (jq(this).attr('name') === 'password1' && jq(this).parents(".user_input").siblings().find("input[type='password']").attr('name') === 'password2' )
    {
        repeatPassWordObj.nextAll(".message").hide();
        repeatPassWordObj.css("border","1px #ccc solid");
    }
})
    .blur(function(){

        jq(this).css("border","1px #ccc solid");	//失去焦点变为默认的灰色
        //文本框失去焦点时若无内容则显示提示信息
        var value = jq(this).val();
        if(value !== "")
        {

            jq(this).prevAll("label.default_value").hide();
        }
        else
        {
            jq(this).prevAll("label.default_value").show();
        }
    });
//用户账号
function check_accountNm(obj){

    if(obj.length <= 0){return true;}//不存在的节点直接返回true
    var value = jq.trim(obj.val());
    var errorObj = obj.nextAll(".message");

    if(value == "")
    {
        var infoMsg = "请输入邮箱/手机号码";
        showMsg(errorObj,infoMsg,1);
        return false;
    }

    else if(/^1[34578]\d{9}$/.test(value) || /^([a-zA-Z0-9-_.]){1,}@([a-zA-Z0-9-.]){1,}\.(?:com|cn)$/.test(value))
    {
        var infoMsg = "所填信息正确";
        showMsg(errorObj,infoMsg,2);
        return true;
    }
    else
    {
        var infoMsg = "输入有误，请重新输入";
        showMsg(errorObj,infoMsg,1);
        return false;
    }

}
//手机号码
function check_mobile(obj){

    if(obj.length <= 0){return true;}//不存在的节点直接返回true
    var value = jq.trim(obj.val());
    var errorObj = obj.nextAll(".message");

    if(value == "")
    {
        var infoMsg = "";
        showMsg(errorObj,infoMsg,1);
        return false;
    }

    else if(/^1[34578]\d{9}$/.test(value))
    {
        var infoMsg = "所填信息正确";
        showMsg(errorObj,infoMsg,2);
        return true;
    }
    else
    {
        var infoMsg = "输入有误，请重新输入";
        showMsg(errorObj,infoMsg,1);
        return false;
    }

}
//手机验证码
function check_mobile_code(obj){
    if(obj.length <= 0){return true;}//不存在的节点直接返回true
    var value = jq.trim(obj.val());
    var errorObj = obj.nextAll(".message");

    if(value == "")
    {

        var infoMsg = "请输入短信验证码";
        showMsg(errorObj,infoMsg,1);
        return false;
    }
    else if(/^[0-9]+$/.test(value))
    {
        var infoMsg = "所填信息正确";
        showMsg(errorObj,infoMsg,2);
        return true;
    }
    else
    {
        var infoMsg = "验证码错误，请重新输入";
        showMsg(errorObj,infoMsg,1);
        return false;
    }

}


//验证密码
function check_passwd(obj){
    if(obj.length <= 0){return true;}//不存在的节点直接返回true
    var value = jq.trim(obj.val());
    var errorObj = obj.nextAll(".message");
    if(value == "")
    {
        var infoMsg = "请填写密码";
        showMsg(errorObj,infoMsg,1);
        return false;
    }
    else if(value.length<6)
    {
        var infoMsg = "密码太弱，请重新设定";
        showMsg(errorObj,infoMsg,1);
        return false;
    }
    else if(value.length>20)
    {
        var infoMsg = "密码过长，6-20位字符";
        showMsg(errorObj,infoMsg,1);
        return false;
    }
    else
    {
        if (obj.attr('name') === 'password2')
        {
            if (value != jq.trim(passWordObj.val()))
            {
                var infoMsg = "密码不一致，请重新输入";
                showMsg(errorObj, infoMsg, 1);
                return false;
            }
            else
            {
                var infoMsg = "所填信息正确";
                showMsg(errorObj, infoMsg, 2);
                return true;
            }
        }
        else
        {
            var infoMsg = "所填信息正确";
            showMsg(errorObj, infoMsg, 2);
            return true;
        }
    }

}

jq(".post_btn").click(function() {
	if (check_accountNm(accountNmObj)) {
		jq(".auth_code").show();
		auth_code(mathCodeObj);
	}

	else {
		return false;
	}
});
function auth_code(obj){
    if(obj.length <= 0){return true;}//不存在的节点直接返回true
    var val = accountNmObj.val();
    jq(".autoCodeButton").click(function(){
        var rand_num = jq.trim(obj.val());
        if(rand_num == '') {
            obj.focus();
        }
        else
        {
            jq(".auth_code").hide();
            var val = accountNmObj.val();
            if(/^\w+@\w+\.\w+$/.test(val))   //用户输入的是邮箱
            {
                times_ = 180;
                timerc = times_;
                emailmobil = 1;
            }
            else       //用户输入的是手机号码
            {
                times_ = 120;
                timerc = times_;
                emailmobil = 0;
            }
            jq.ajax({
                type: "GET",
                url: "/my/get_moblie_yz.php",
                //data: "rand_num="+rand_num,
                data: {ajax:1,rand_num:rand_num},
                success:function(data){

                    if(data == 1){
                        jq(".auth_code").hide();
                        set_emailmoblie_yz(rand_num,val,emailmobil);
                    }
                    else{
                        jq('#txt_mathcode').focus();
                    }
                }
            });
        }
    });
}
function set_emailmoblie_yz(rand_num,val,emailmobil){
			jq('.auth_code').hide();
			jq('#txt_mathcode').val('');
			jq('.post_btn').attr('disabled','disabled');
			jq.ajax({
				type: "GET",
				url: "/my/get_moblie_yz.php?is_ajax=1",
				data: {moblie:val,rand_num:rand_num,emailmobil:emailmobil},
				success:function(data){
					if(data=='-1')
					{
					   alert('验证码申请太频繁，请稍后再试！');
					}else
					{
                                            post_code();
					}
				}
			});


}
//倒计时
function setTimeOut1(){

    if(timerc >= 1){
        jq(".post_btn").val(timerc+'后重新获取').attr("disabled", true); //写入秒数
        timerc--; //时间变量自减1

    }
    else
    {

        jq('.post_btn').val('获取验证码').attr("disabled", false);
        timerc = times_;
        return false;
    }
}

//发送验证码
var setTime='' ;
function post_code(){
    //点击发送验证码后按钮不可点并执行倒计时
   clearInterval(setTime);
    setTime = setInterval(function(){
        if(timerc == 0){
            clearInterval(setTime);
            jq('.post_btn').val('获取验证码').attr("disabled", false);
            timerc = times_;
            return;
        }else
        {
            jq(".post_btn").val(timerc+'秒后重新获取').attr("disabled", true);
            timerc --;
        }
    },1000);


    //根据输入的账户，显示对应的提示信息
    var value = jq.trim(accountNmObj.val());
    if (/^([a-zA-Z0-9-_.]){1,}@([a-zA-Z0-9-.]){1,}\.(?:com|cn)$/.test(value)) {
        jq(this).nextAll(".message").show().html("验证码已发送，请<a href='#' class='email_login'>登录邮箱</a>查收");
    }

}


function check_third_bind()
{

    if( check_passwd(passWordObj) && check_passwd(repeatPassWordObj) && check_accountNm(accountNmObj) && check_mobile_code(mobileCodeObj)){
        var url = "http://www.to8to.com/new_login.php";
        var data = {
            'del_ban':1,
            'uid':jq("#hidd_val").val(),
            'step':'jieban',
            'type':jq("#hidd_val").attr('tid'),
            'password1':jq("input[name=password]").val(),
            'password2':jq("input[name=password2]").val(),
            'yzm':jq("input[name=dxyzm]").val(),
            'val':jq("input[name=username]").val()
        };
        jq.post(url,data,function(message){
            message = eval('('+message+')');
            if(message['status'] == 0){
                if(message['error'] == 1){
                    var infoMsg = "邮箱或手机已绑定其他账号！";
                    jq("input[name='dxyzm']").nextAll(".message").removeClass(".msg_success").show().html("<span></span>"+infoMsg);
                    return false;
                }else{
                    var infoMsg = "输入错误，请重新输入";
                    jq("input[name='dxyzm']").nextAll(".message").removeClass(".msg_success").show().html("<span></span>"+infoMsg);
                    return false;
                }
            }else if(message['status'] == 1){
                jq(".popup_wrap ,.third_account_03").hide();
                unbindSuccess02(jq("#hidd_val").attr('tid'));
            }
        });
    }
    else
    {
        return false;
    }
}
function check_demand_list()
{
    if( check_mobile(mobileObj)){

        return true;

    }
    else
    {
        return false;
    }
}

//jq(document).keydown(function(event){
//    if(event.keyCode==13){
//        jq("form").submit();
//    }
//});

//默认的信息
function showMsg(obj,msg,error){
    var c = 'msg_info';

    switch(error){

        //正确消息
        case 0:
            c = 'msg_success';
            obj.html("<span></span>"+msg).removeClass("msg_error");
            break;

        //错误消息
        case 1:
            c = 'msg_error';
            obj.prevAll("input[type='text'],input[type='password']").css("border","1px #FF6769 solid");
            obj.html("<span></span>"+msg).show().removeClass("msg_success");
            break;

        //清空消息体
        case 2:
            c = 'msg_error';
            obj.addClass("msg_success").removeClass("msg_error").css('display','none'); //格式验证正确隐藏消息体
            break;

        //默认是普通消息
        default:
            c = 'msg_info';
            break;
    }

    obj.html("<span></span>"+msg).addClass(c);
}

//function next_step(){
//    var url = 'http://www.to8to.com/new_login.php';
//    var data = {
//        'del_ban':1,
//        'uid':{$to8to_uid},
//        'step':'panduan',
//        'type':3,
//    };
//    jq.post(url,data,function(message){
//        alert(message);
//        return false;
//    })
//}
