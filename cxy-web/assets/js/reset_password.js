// JavaScript Document
var labelValueObj = jq(".default_value , .default_value_code"); //label代替显示文本框的value值
var input_textObj = jq("input[type='password'] , input[type='text']"); //文本框和密码框
var accountNmObj = jq("input[name='username']");//账号
var questionCodeObj = jq("input[name='yzm']");//问题验证
var mobileCodeObj = jq("input[name='dxyzm']");//手机验证码
var passWordObj = jq("input[name='password1']");//新密码
var repeatPassWordObj = jq("input[name='password2']");//确认密码
var mobileNmObj = jq("input[name='mobileNumber']");//手机号
var emailObj = jq("input[name='email']");//邮箱

//label代替input的value

labelValueObj.click(function(){
	jq(this).hide().next().focus();

});

input_textObj.focus(function(){
    jq(this).siblings('label.default_value').hide();
    jq(this).siblings('label.default_value_code').hide();
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

			jq(this).prevAll("label.default_value , label.default_value_code").hide();
		}
		else
		{
			jq(this).prevAll("label.default_value , label.default_value_code").show();
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
		var infoMsg = "账户错误，请重新输入";
		showMsg(errorObj,infoMsg,1);
		return false;
	}

}

//问题验证
function check_question_code(obj){

	if(obj.length <= 0){return true;}//不存在的节点直接返回true
	var value = jq.trim(obj.val());
	var errorObj = obj.nextAll(".message");

	if(value == "")
	{

		var infoMsg = "请输入验证码";
		showMsg(errorObj,infoMsg,1);
		return false;
	}
	else if(/^[A-Za-z0-9]+$/.test(value)) {
		var infoMsg = "所填信息正确";
		showMsg(errorObj, infoMsg, 2);
		return true;
	}
	else
	{
		var infoMsg = "验证码错误";
		showMsg(errorObj,infoMsg,1);
		return false;
	}

}

//邮箱
function check_email(obj){

	if(obj.length <= 0){return true;}//不存在的节点直接返回true
	var value = jq.trim(obj.val());
	var errorObj = obj.nextAll(".message");

	if(value == "")
	{

		var infoMsg = "邮箱不能为空";
		showMsg(errorObj,infoMsg,1);
		return false;
	}
	else if(/^([a-zA-Z0-9-_.]){1,}@([a-zA-Z0-9-.]){1,}\.(?:com|cn)$/.test(value))
	{
		var infoMsg = "所填信息正确";
		showMsg(errorObj,infoMsg,2);
		return true;
	}
	else
	{
		var infoMsg = "邮箱错误，请重新输入";
		showMsg(errorObj,infoMsg,1);
		return false;
	}

}

//手机号
function check_mobile(obj){

	if(obj.length <= 0){return true;}//不存在的节点直接返回true
	var value = jq.trim(obj.val());
	var errorObj = obj.nextAll(".message");

	if(value == "")
	{

		var infoMsg = "手机号码不能为空";
		showMsg(errorObj,infoMsg,1);
		return false;
	}
	else
	{
		if(/^1[34578]\d{9}$/.test(value))
		{

			if(value.length!=11)
			{
				var infoMsg = "请输入正确的11位手机号码";
				showMsg(errorObj,infoMsg,1);
				return false;
			}
			else
			{
				var infoMsg = "所填信息正确";
				showMsg(errorObj,infoMsg,2);
				return true;
			}
		}
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



function check_account()
{
	if(check_accountNm(accountNmObj) && check_question_code(questionCodeObj)){
		return true;
	}
	else
	{
		return false;
	}
}
function check_mobileCode()
{
	if(check_mobile_code(mobileCodeObj)){
		return true;
	}
	else
	{
		return false;
	}
}
function check_passWord()
{
	if(check_passwd(passWordObj) && check_passwd(repeatPassWordObj)){
		return true;
	}
	else
	{
		return false;
	}

}
jq(document).keydown(function(event){
	if(event.keyCode==13){
		jq("form").submit();
	}
});
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

jq(".reset_content_email .repeat_post label").mouseover(function(){

	jq(this).css("color","#E7691E");
}).mouseout(function(){

	jq(this).css("color","#00b34b");

});