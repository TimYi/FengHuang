
var html = [];
var info = Utils.offLineStore.get("userinfo",false) || "";
if(info !== ""){
	var obj = JSON.parse(info) || {};
	var userName = obj.cnname || "";
	if(userName === ""){
		userName = obj.username || "";
	}

	Base.userName = userName;
	//已登录
	var html = [];
	var token = Utils.offLineStore.get("token",false) || "";
	var str = "";
	if(token !== ""){
		str = "?token=" + token + "&p=0";
	}

	html.push('<li style="padding-right:10px;"><i class="fa fa-user hui"></i>&nbsp&nbsp');
	html.push('<span style="color:#aaa">' + userName + ',&nbsp您好！</span>');
	html.push('<b style="border-right:1px solid #ddd;padding:0 10px;font-weight:normal"><a href="javascript:Utils.gotoCenter();" style="width:60px">会员中心</a></b>');
	html.push('<b style="padding:0 10px;font-weight:normal"><a href="javascript:Utils.loginOut();" style="width:30px">退出</a></b></li>');

}
else{
	html.push('<a href="http://ifhzj.com/api/qq/auth" target="_self" title="QQ登录" style="margin-right: 20px;"><img src="http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/img/Connect_logo_7.png" alt="QQ登录" border="0"></a>');
	html.push('<li style="padding-right:10px;">');
	html.push('<i class="fa fa-sign-in hui"></i>');
	html.push('<b style="border-right:1px solid #ddd;padding:0 10px;font-weight:normal">');
	html.push('<a href="center/reg.html" style="width:30px">注册</a></b><b style="padding:0 10px;font-weight:normal">');
	html.push('<a href="center/login.html" style="width:30px">登录</a>');
	html.push('</b>');
	html.push('</li>');
}
document.write(html.join(''));