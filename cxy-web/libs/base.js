/**
 * file:全局变量
 * author:ToT
 * date:2014-08-17
*/

(function(window) {
	if (typeof Base === "undefined") {
		Base = {};
	}
	//正式URL端口号为21290,测试URL端口号为8008
	var urlPort = 21290;
	//蒙版效果等待时间
	var maskTimeOut = 1000;
	//跳转延迟
	var eventDelay = 100;
	//用户名
	var userName = "";

	//请求服务地址
	var serverUrl = "http://101.200.229.135:8080";
	var getCodeUrl = serverUrl + "/api/message";
	var regUrl = serverUrl + "/api/regist";
	var loginUrl = serverUrl + "/api/login";
	var imgCodeUrl = serverUrl + "/api/captcha";
	var profileUrl = serverUrl + "/api/user/profile";
	var changePasswordUrl = serverUrl + "/api/changePassword";
	var messagesUrl = serverUrl + "/api/user/messages";
	var appointsUrl = serverUrl + "/api/user/appoints";
	var orderUrl = serverUrl + "/api/user/orders";
	var commentUrl = serverUrl + "/api/comments";
	var couponsUrl = serverUrl + "/api/user/coupons";
	var collectsUrl = serverUrl + "/api/user/collects";
	var bindMobile = serverUrl + "/api/user/bindMobile";

	Base.userName = userName;
	Base.urlPort = urlPort;
	Base.maskTimeOut = maskTimeOut;
	Base.getCodeUrl = getCodeUrl;
	Base.regUrl = regUrl;
	Base.loginUrl = loginUrl;
	Base.imgCodeUrl = imgCodeUrl;
	Base.profileUrl = profileUrl;
	Base.changePasswordUrl = changePasswordUrl;
	Base.messagesUrl = messagesUrl;
	Base.appointsUrl = appointsUrl;
	Base.orderUrl = orderUrl;
	Base.commentUrl = commentUrl;
	Base.couponsUrl = couponsUrl;
	Base.collectsUrl = collectsUrl;
	Base.bindMobile = bindMobile;
}(window));












