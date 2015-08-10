//页面初始化
$(function(){
	var g = {};
	g.isBind = true;
	g.token = Utils.getQueryString("token");
	g.page = Utils.getQueryString("p") - 0;
	g.httpTip = new Utils.httpTip({});

	var loginStatus = Utils.getUserInfo();
	if(!loginStatus){
		//未登录
		location.replace("login.html");
	}else{
		setCenterHtml();
	}

	$('#loginOut').bind('click',loginOut);

	function setCenterHtml(){
		//var comments = obj.comments || 0;
		//var coupons = obj.coupons || 0;
		//var messages = obj.messages || 0;
		//var appoints = obj.appoints || 0;
		//var collects = obj.collects || 0;

		var html = [];
		html.push('<ul class="am-avg-sm-3">');
		html.push('<li><div class="am-dropdown" data-am-dropdown>');
		html.push('<a href="u_info.html?token=' +g.token +'&p=1" ' + (g.page == 2 ? h : "") + '>');
		html.push('<div class="cbox"><i class="am-icon-user"></i><br /><span>个人资料</span></div></a></div></li>');
		html.push('<li><div class="am-dropdown" data-am-dropdown>');
		html.push('<a href="u_house.html?token=' +g.token +'&p=2" ' + (g.page == 2 ? h : "") + '>');
		html.push('<div class="cbox"><i class="am-icon-home"></i><br /><span>房屋信息</span></div></a></div></li>');
		html.push('<li><div class="am-dropdown" data-am-dropdown>');
		html.push('<a href="u_live.html?token=' +g.token +'&p=3" ' + (g.page == 3 ? h : "") + '>');
		html.push('<div class="cbox"><i class="am-icon-history"></i><br /><span>家装进度</span></div></a></div></li>');
		html.push('</ul>');
		html.push('<ul class="am-avg-sm-3">');
		html.push('<li><div class="am-dropdown" data-am-dropdown>');
		html.push('<a href="u_message.html?token=' +g.token +'&p=4" ' + (g.page == 4 ? h : "") + '>');
		html.push('<div class="cbox"><i class="am-icon-envelope-o"></i><br /><span>我的留言</span></div></a></div></li>');
		html.push('<li><div class="am-dropdown" data-am-dropdown>');
		html.push('<a href="u_sub.html?token=' +g.token +'&p=5" ' + (g.page == 5 ? h : "") + '>');
		html.push('<div class="cbox"><i class="am-icon-clock-o"></i><br /><span>我的预约</span></div></a></div></li>');
		html.push('<li><div class="am-dropdown" data-am-dropdown>');
		html.push('<a href="u_order.html?token=' +g.token +'&p=6" ' + (g.page == 6 ? h : "") + '>');
		html.push('<div class="cbox"><i class="am-icon-check-square-o"></i><br /><span>我的订单</span></div></a></div></li>');
		html.push('</ul>');
		html.push('<ul class="am-avg-sm-3">');
		html.push('<li><div class="am-dropdown" data-am-dropdown>');
		html.push('<a href="u_comment.html?token=' +g.token +'&p=7" ' + (g.page == 7 ? h : "") + '>');
		html.push('<div class="cbox"><i class="am-icon-comments-o"></i><br /><span>我的评论</span></div></a></div></li>');
		//html.push('<li style="display:none;"><div class="am-dropdown" data-am-dropdown>');
		//html.push('<a href="u_fav.html?token=' +g.token +'&p=8" ' + (g.page == 8 ? h : "") + '>');
		//html.push('<div class="cbox"><i class="am-icon-star-o"></i><br /><span>我的收藏</span></div></a></div></li>');
		html.push('<li><div class="am-dropdown" data-am-dropdown>');
		html.push('<a href="u_coupon.html?token=' +g.token +'&p=9" ' + (g.page == 9 ? h : "") + '>');
		html.push('<div class="cbox"><i class="am-icon-certificate"></i><br /><span>我的优惠券</span></div></a></div></li>');
		html.push('<li><div class="am-dropdown" data-am-dropdown>');
		html.push('<a href="javascript:loginOut();">');
		html.push('<div class="cbox"><i class="am-icon-sign-out"></i><br /><span>用户注销</span></div></a></div></li>');
		html.push('</ul>');
		$("#center").html(html.join(''));
	}
});