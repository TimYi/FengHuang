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

	var categoryUrl = serverUrl + "/api/category";
	var cityUrl = serverUrl + "/api/area/level";
	var subareasUrl = serverUrl + "/api/area/subareas";
	var getCodeUrl = serverUrl + "/api/message";
	var regUrl = serverUrl + "/api/regist";
	var loginUrl = serverUrl + "/api/login";
	var imgCodeUrl = serverUrl + "/api/captcha";
	var profileUrl = serverUrl + "/api/user/profile";
	var changePasswordUrl = serverUrl + "/api/changePassword";
	var messagesUrl = serverUrl + "/api/user/messages";
	var messageUrl = serverUrl + "/api/user/message";
	var appointsUrl = serverUrl + "/api/user/appoints";
	var appointUrl = serverUrl + "/api/user/appoint";
	var orderUrl = serverUrl + "/api/user/orders";
	var commentsUrl = serverUrl + "/api/user/comments";
	var commentUrl = serverUrl + "/api/user/comment";
	var couponsUrl = serverUrl + "/api/user/coupons";
	var collectsUrl = serverUrl + "/api/user/collects";
	var collectUrl = serverUrl + "/api/user/collect";
	var bindMobile = serverUrl + "/api/user/bindMobile";
	var unreads = serverUrl + "/api/user/unreads";
	var houses = serverUrl + "/api/user/houses";
	var house = serverUrl + "/api/user/house";
	var cases = serverUrl + "/api/product/cases";
	var casedetails = serverUrl + "/api/product/case";
	var lives = serverUrl + "/api/lives";
	var live = serverUrl + "/api/live";
	var packagesUrl = serverUrl + "/api/product/packages";
	var packageAppointUrl = serverUrl + "/api/user/packageAppoint";

	Base.userName = userName;
	Base.urlPort = urlPort;
	Base.maskTimeOut = maskTimeOut;

	Base.categoryUrl = categoryUrl;
	Base.cityUrl = cityUrl;
	Base.subareasUrl = subareasUrl;
	Base.getCodeUrl = getCodeUrl;
	Base.regUrl = regUrl;
	Base.loginUrl = loginUrl;
	Base.imgCodeUrl = imgCodeUrl;
	Base.profileUrl = profileUrl;
	Base.changePasswordUrl = changePasswordUrl;
	Base.messagesUrl = messagesUrl;
	Base.messageUrl = messageUrl;
	Base.appointsUrl = appointsUrl;
	Base.appointUrl = appointUrl;
	Base.orderUrl = orderUrl;
	Base.commentsUrl = commentsUrl;
	Base.commentUrl = commentUrl;
	Base.couponsUrl = couponsUrl;
	Base.collectsUrl = collectsUrl;
	Base.collectUrl = collectUrl;
	Base.bindMobile = bindMobile;
	Base.unreads = unreads;
	Base.houses = houses;
	Base.house = house;
	Base.cases = cases;
	Base.casedetails = casedetails;
	Base.lives = lives;
	Base.live = live;
	Base.packagesUrl = packagesUrl;
	Base.packageAppointUrl = packageAppointUrl;
}(window));












