(function() {

    var popupLayer = jq('.popup_wrap'), popupLayerClone, popupLc = jq('.pooup_login_box');
    if ( popupLayer.length ) {
        popupLayerClone = popupLayer.clone();
        popupLayer.remove();
        jq('body').prepend(popupLayerClone);
        jq('.popup_wrap').css('height', jq('body').height());

        if ( isIE6 && popupLc.length ) {
            var _top = parseInt(popupLc.css('top'));
            jq(window).scroll(function() {
                var nextTop = (_top + jq(window).scrollTop()) + 'px';
                popupLc.css({
                    top: nextTop
                });
            })
        }
    }

})();
function menuShrink(){

    jq('.parent_menu').children('a').click(function(){
        var dataStatus = jq(this).parent('.parent_menu').attr('data_status');
        if(dataStatus=='hide'){
            jq(this).parent('.parent_menu').find('.child_menu').show();
            jq(this).parent('.parent_menu').attr('data_status','show');
            jq(this).find('i').removeClass('pm_icon_close').addClass('pm_icon_open');
        }
        else{
            jq(this).parent('.parent_menu').find('.child_menu').hide();
            jq(this).parent('.parent_menu').attr('data_status','hide');
            jq(this).find('i').removeClass('pm_icon_open').addClass('pm_icon_close');
        }
    });
    jq('.child_menu li').click(function(){
        jq('.child_menu li').removeClass('child_menu_on');
        jq(this).addClass('child_menu_on');
    })
}
 function selectType(){
     jq('.pooup_login_box .content .box_2 ul li').click(function(){
         if(jq(this).attr('class')=="cur"){
             jq(this).removeClass('cur');
             return;
         }
         jq(this).addClass('cur');
     });
 }
function tal_change(){
	jq('.iisb_title a').click(function(){
		var index = jq(this).index();
		jq(this).addClass("iisb_on").siblings().removeClass("iisb_on");
		jq(this).parent().siblings().find('.iisbc_item:eq('+index+')').show().siblings().hide();
		jq(this).parent().siblings().find('.iisbc_item:eq('+index+') .iisbc_steps ul li:eq(0)').addClass("steps_on").siblings().removeClass("steps_on");
		jq(this).parent().siblings().find('.iisbc_item:eq('+index+')').find('.tc_main_content>.tcmc_item:eq(0)').show().siblings().hide();
	});

	jq('.iisbc_steps ul li').click(function(){
		jq(this).addClass('steps_on').siblings().removeClass('steps_on');
		var index = jq(this).index();

		jq(this).parents(".iisbc_item").find('.tcmc_item:eq('+index+')').show();
		jq(this).parents(".iisbc_item").find('.tcmc_item:eq('+index+')').siblings().hide();
	});
}
function change_detail(){
	if(jq("#noDiary").is(":visible"))
	{
		jq("#Diary , .project_list , #user_comment , .project h2").hide();
	}
	else
	{
	}
}
//关闭登录弹窗
jq(".update_msg_btn  , .list_new , .pic_list_update , .pic_update , .feedback_btn").click(function(){
    jq("body,html").css({"overflow":"hidden"});
    popup_center(jq('.pooup_login_box'));
	var cur_dom = jq(this).attr("class");

	if(cur_dom == "pic_list_update")
	{
		var html = jq(this).parent().siblings('.control_content').find('p').html();

		jq(".input_txt").val(html);

		jq(".column_name").html("编辑专辑");

		jq(".popup_wrap , .pooup_login_box").show();



	}
	else if(cur_dom == "list_new")
	{
		jq(".column_name").html("创建新专辑");
		jq(".input_txt").val("最多可输入10个字");
		jq(".popup_wrap , .pooup_login_box").show();
	}
	else
	{
		jq(".popup_wrap , .pooup_login_box").show();
	}
});

jq(".close_btn , .btn_unclick").click(function(){
	jq(".popup_wrap , .pooup_login_box").hide();
	jq("body,html").removeAttr("style");
});

function show_popup(btnObj , popupObj){
	btnObj.bind('click',function(){
		jq(".popup_wrap").show();
		popupObj.show();
		popup_center(popupObj);
		jq("body,html").css({"overflow":"hidden"});
	});

}
function stop_overflow(){

    jq("body").css("overflow","hidden");
}
//跳过次步骤
jq(".skipNav").click(function(){
	jq(".popup_approval").show();
});
jq(".popup_approval .close_btn").click(function(){
	jq(".popup_approval").hide();
});

function get_id (cur_id){

		jq("#rec_xcid").val(cur_id);

}
function get_id_s (cur_id,hidden_text_id){

		jq(hidden_text_id).val(cur_id);

}

//登录弹窗自适应居中
function popup_center(popupObj){

	var windows_width = jq(window).width();
	var windows_height = jq(window).height();
	var windows_scrollHeight = jq(window).scrollTop();
	var popup_width = popupObj.width();
	var popup_height = popupObj.height();
	var popup_paddingLeft = popupObj.css("paddingLeft").replace(/px/," ");
	var popup_paddingRight = popupObj.css("paddingRight").replace(/px/," ");
	var popup_paddingTop= popupObj.css("paddingTop").replace(/px/," ");
	var popup_paddingBottom= popupObj.css("paddingBottom").replace(/px/," ");
	var margin_left = (windows_width - popup_width - popup_paddingLeft - popup_paddingRight)/2;
	var margin_top = (windows_height - popup_height - popup_paddingTop - popup_paddingBottom)/2+windows_scrollHeight;
	popupObj.css({"left":margin_left,"top":margin_top});
}



//我的收藏 -效果图的修改删除层的隐藏显示
function pic_control() {

	var liObj = jq("ul.pic_list li");
	//var pic_controlObj = jq("ul.pic_list li .pic_control");
	var pic_controlAllObj = jq(".mask_layer_top , ul.pic_list li .pic_control");
	pic_controlAllObj.hide();

	liObj.each(function(){
		var This = jq(this);
		This.mouseover(function(){
			var cur_li = This.index();
			jq("ul.pic_list li:eq("+cur_li+") .mask_layer_top , ul.pic_list li:eq("+cur_li+") .pic_control").show();
			var img_tag = jq("ul.pic_list li:eq("+cur_li+") .pic_tag").text() ;
			jq(".pic_list_new .content .img_tag").val(img_tag);
		}).mouseout(function(){
			jq("ul.pic_list li .mask_layer_top , ul.pic_list li .pic_control ").hide();
		});
	});
}

//任务系统的选项卡
function mission_tab(){

	jq(".tab_column ul li").eq(0).addClass("cur");
	jq(".mission_content ul").eq(1).hide();
	jq(".tab_column ul li").click(function(){
		var cur_li = jq(this).index();
		jq(".tab_column ul li").eq(cur_li).addClass("cur").siblings().removeClass("cur");
		jq(".mission_content ul").eq(cur_li).show().siblings().hide();
	});

	jq(".mission_content ul li .mission_msg").hide();
	jq(".mission_content ul li .mission_list").click(function(){
		jq(this).children().find("em").removeClass("cur");
		jq(this).next(".mission_msg").toggle();
		jq(this).next(".mission_msg").parent().siblings().find('.mission_msg').hide();
		jq(this).children().find("em").addClass("cur").parents('li').siblings().find('em').removeClass('cur');
		if(jq(this).next(".mission_msg").is(":hidden"))
		{
			jq(this).children().find("em").removeClass("cur");
		}
		else
		{
			jq(this).children().find("em").addClass("cur");
		}

	});
}




//跳过此步骤  确认是否需要水电/泥木/竣工的验收步骤
function jump_step(){

	jq(".ii_color_01af69").click(function(){
		var cur_index = jq(this).parents('.iisbc_item').index()+1;
		if(cur_index == 2)
		{
			jq(".popup_approval p").html("请确认是否不需要监理进行水电验收");
		}
		else if(cur_index == 3)
		{
			jq(".popup_approval p").html("请确认是否不需要监理进行泥木验收");
		}
		else if(cur_index == 4)
		{
			jq(".popup_approval p").html("请确认是否不需要监理进行油漆验收");
		}
		else if(cur_index == 5)
		{
			jq(".popup_approval p").html("请确认是否不需要监理进行竣工验收");
		}
		else{
			return;
		}
		jq(".popup_approval").show();
	});

}

//积分兑换模块-确认收货信息
function change_address(){
	jq(".check_address .msg_list").children(".mask_layer").eq(0).show().parent().siblings().find(".mask_layer").hide();
	jq(".check_address .msg_list").click(function(){

		jq(this).parent().find(".mask_layer").hide();
		jq(this).children(".mask_layer").show();

	});
}

//文字过长,超出八个文字显示省略号
function cut_string(string_length,stringObj){

	stringObj.each(function(i,v){
		var objstring = jq(v).text();
		if(objstring.length >string_length){
			jq(v).attr("title",objstring);
			jq(v).text(objstring.substring(0,string_length) + "...");
		}
	});
}
//个人资料 输入具体地址时显示一个10条数据的下拉框
function address_select(){
	jq(".personal_info .city .addr_info ul , .decorate_rights_detail .box ul").hide();
	jq("input[name='address']").focus(function(){

		//jq(".personal_info .city .addr_info ul , .decorate_rights_detail .box ul").show();

	});
	jq(".personal_info .city .addr_info ul li , .decorate_rights_detail .box ul li").mouseover(function(){

		jq(this).addClass("cur").siblings().removeClass("cur");
	});

	jq(".personal_info .city .addr_info ul li ,.decorate_rights_detail .box ul li").click(function(){

		var cur_li_msg = jq(this).html();
		jq("input[name='address']").val(cur_li_msg);
		jq(".personal_info .city .addr_info ul , .decorate_rights_detail .box ul").hide();
	});

	document.onclick = function (event)
	{
		var e = event || window.event;
		var elem = e.srcElement||e.target;
		while(elem)
		{
			if(elem.className =="user_input_address" || elem.className == 'address_list' )
			{
				return;
			}
			elem = elem.parentNode;
		}
		jQuery('.personal_info .city .addr_info ul ,.decorate_rights_detail .box ul').hide();
	}

}

//首页  突击检查弹窗  选择不同的时间段提示不同的信息
function surprise_inspection_msg(){

	jq(".surprise_inspection .time ul").hide();
	jq(".cur_time").click(function(){

		jq(this).next("ul").toggle();
	});
	jq(".surprise_inspection .time ul li").removeClass("cur");
	jq(".surprise_inspection .time ul li").click(function(){

		var cur_li_msg = jq(this).html();
		jq(this).parent().siblings().find("p").html(cur_li_msg);
		jq(this).parent().hide();

		var time_class =  jq(this).parent().parent().attr("class");
		if(time_class.indexOf("time2") >=0)
		{
			var cur_li_id = jq(this).index();
			if(cur_li_id == 0)
			{
				jq(".msg .msg_01").hide().siblings().show();
			}
			else
			{
				jq(".msg .msg_01").show().siblings().hide();
			}
		}
	});
}

function inspection_success(){

	jq(".surprise_inspection .btn .btn_click").click(function(){

		var time1 = jq(".surprise_inspection .time1 .cur_time p").html();
		var time2 = jq(".surprise_inspection .time2 .cur_time p").html();
		jq(".surprise_inspection").hide();
		jq(".inspection_success").show();
		jq(".inspection_success .time_content .time_content_01").html(time1);
		jq(".inspection_success .time_content .time_content_02").html(time2);


	});

}

function change_progress_content(){

	jq(".apply_approval").click(function(){
		var cur_index = jq(this).parents('.iisbc_item').index()+1;
		if(cur_index == 1)
		{
			jq(".surprise_inspection .column_name").html("申请材料验收");
			jq(".surprise_inspection .msg_01").hide();
		}
		else if(cur_index == 2)
		{
			jq(".surprise_inspection .column_name").html("申请水电验收");
			jq(".surprise_inspection .msg_01").hide();
		}
		else if(cur_index == 3)
		{
			jq(".surprise_inspection .column_name").html("申请泥木验收");
			jq(".surprise_inspection .msg_01").hide();
		}
		else if(cur_index == 4)
		{
			jq(".surprise_inspection .column_name").html("申请油漆验收");
			jq(".surprise_inspection .msg_01").hide();
		}
		else if(cur_index == 5)
		{
			jq(".surprise_inspection .column_name").html("申请竣工验收");
			jq(".surprise_inspection .msg_01").hide();
		}
		else{
			jq(".surprise_inspection .column_name").html("申请监理突击检查");
			jq(".surprise_inspection .msg_01").show();
		}
	});
}
//第三方解绑弹窗
function unbindHaveAccount(type){
	var MaskObj = jq('.popup_wrap');
	var popupObj = jq(".third_account_01");
	var typeStr = '';
	if(type == 1)
	{
		typeStr = 'QQ';
	}
	else if(type == 2)
	{
		typeStr = '微博';
	}
	else if(type == 3)
	{
		typeStr = '微信';
	}

    var popupStr = ' <div class="pooup_login_box third_account_01" style="display: block" >'
		+ '<div class="top"> <span class="close_btn" onclick=close_window(jq(".third_account_01"))></span></div>'
		+ '<div class="content"> <p>确认解除'+typeStr+'绑定</p>'
		+ '<div class="box"> <div class="btn"> <input type="button" value="确认" class="btn_click" onclick=unbindSuccess01('+type+')> <input type="button" value="否" class="btn_unclick" onclick=close_window(jq(".third_account_01"))> </div> </div></div>';
	jq('body').append(popupStr);
	MaskObj.show();
	popupObj.show();
	jq("body,html").css({"overflow":"hidden"});
	popup_center(jq(".third_account_01"));

}
function hidePop(){
	parent.window.jq(".window_box").remove();
	parent.window.jq(".popup_mask").remove();
}
function unbindSuccess01(type){
	jq('.third_account_01').hide();
	var MaskObj = jq('.popup_wrap');
	var popupObj = jq(".third_account_02");
	var typeStr = '';
	if(type == 1)
	{
		typeStr = 'QQ';
	}
	else if(type == 2)
	{
		typeStr = '微博';
	}
	else if(type == 3)
	{
		typeStr = '微信';
	}
        var url = 'http://www.to8to.com/new_login.php';
        var data = {
            "del_ban":1,
            "uid":jq("#hidd_val").val(),
            "step":"jieban",
            "type":jq('#hidd_val').attr('tid')
        };
        jq.post(url,data,function(message){
            message = eval('('+message+')');
            if(message['status'] == 1){
                var popupStr = ' <div class="pooup_login_box third_account_02" style="display: block" >'
		+ '<div class="top"> <span class="close_btn" onclick=close_window_shuaxin(jq(".third_account_02"))></span></div>'
		+ '<div class="content"> <div class="box"><div class="img"></div><div class="status_msg">'+typeStr+'解绑成功！</div> </div>'
		+ '</div>';
                jq('body').append(popupStr);
                MaskObj.show();
                popupObj.show();
                jq("body,html").css({"overflow":"hidden"});
                popup_center(jq(".third_account_02"));
            }
        })
}
function unbindSuccess02(type) {
	jq('.third_account_04').hide();
	var MaskObj = jq('.popup_wrap');
	var popupObj = jq(".third_account_04");
	var typeStr = '';
	if(type == 1)
	{
		typeStr = 'QQ';
	}
	else if(type == 2)
	{
		typeStr = '微博';
	}
	else if(type == 3)
	{
		typeStr = '微信';
	}

	var popupStr = ' <div class="pooup_login_box third_account_04" style="display: block" >'
		+ '<div class="top"> <span class="close_btn" onclick=close_window_shuaxin(jq(".third_account_04"))></span></div>'
		+ '<div class="content"> <div class="box"><div class="img"></div><div class="status_msg">'+typeStr+'解绑成功！</div> </div>'
		+ '<div class="box"><p>解绑后您可以使用填写的手机号码/邮箱、密码登录土巴兔。</p></div>'
		+ '<div class="box"><div class="btn"><input type="button" value="知道了" class="btn_click" onclick=close_window_shuaxin(jq(".third_account_04"))></div></div>'
		+ '</div>';
	jq('body').append(popupStr);
	MaskObj.show();
	popupObj.show();
	jq("body,html").css({"overflow": "hidden"});
	popup_center(jq(".third_account_04"));
}
function close_window(obj){
	var MaskObj = jq('.popup_wrap');
	obj.remove();
	MaskObj.hide();
    jq("body,html").removeAttr("style");
}
function close_window_shuaxin(obj){
    var MaskObj = jq('.popup_wrap');
	obj.hide();
	MaskObj.hide();
        window.location.href = window.location.href;
}
//选择合作装修公司-装修保和非装修保用户的选择
jq(".cooper_compeny input[id='cooper_type_02']").click(function(){

	jq(".box_upload").show();
});
jq(".cooper_compeny input[id='cooper_type_01']").click(function(){

	jq(".box_upload").hide();
});

jq(".refresh").mouseover(function(){

	jq(this).css("color","#ec6941");
}).mouseout(function(){

	jq(this).css("color","#39c472");

});

//同意支付开工款
function operatingSection(yid){
    var popupStr = ' <form action="yezhu_comments.php" method="post"><div class="pooup_login_box operatingSection" style="display: block" >'
        + '<div class="top"> <span class="close_btn" onclick=close_window(jq(".operatingSection"))></span></div>'
        + '<div class="content"> <p>是否确认已开工，同意支付开工款项？</p>'
        + '<input type="hidden" value="'+yid+'" name="yid">'
        + '<input type="hidden" value="zj_kgk" name="act">'
        + '<div class="box"> <div class="btn"> <input type="submit" value="确认" class="btn_click"> <input type="button" value="取消" class="btn_unclick" onclick=close_window(jq(".operatingSection"))> </div> </div></div></form>';
    var MaskObj = jq('.popup_wrap');
    var popupObj = jq(".operatingSection");
    jq('body').append(popupStr);
    MaskObj.show();
    popupObj.show();
    jq("body,html").css({"overflow": "hidden"});
    popup_center(jq(".operatingSection"));

}
//分期贷 - 招联支付
function zl_payment(yid,orderNo){
    var popupStr = '<div class="popup_wrap"></div>'
        +'<div class="pooup_login_box popup_payment">'
        +'<form method="get" onSubmit="">'
        +'<div class="top"><span class="title">支付反馈</span><span class="close_btn" onclick=close_window(jq(".popup_payment"))></span></div>'
        +'<div class="content"><p class="msg">请你在新打开的招联支付页面进行支付，支付完成后选择：</p>'
        +'<div class="box payment_status"><em class="icon icon_success"></em><p>若你支付成功：</p><a href="/my/yezhu_counter.php?yid='+yid+'&orderNo='+"'"+orderNo+"'"+'" class="ckeck">请查看</a></div>'
        +'<div class="box payment_status"><em class="icon icon_false"></em><p>若你支付失败：</p><a href="javascript:void(0)" class="repeat" onclick=close_window(jq(".popup_payment"))>重新支付</a></div>'
        +'</div></form></div>';
    jq('body').append(popupStr);
    var MaskObj = jq('.popup_wrap');
    var popupObj = jq(".popup_payment");
    MaskObj.show();
    popupObj.show();
    jq("body,html").css({"overflow": "hidden"});
    popup_center(jq(".popup_payment"));

}

function zl_payment_checkbox(){
    obj = jq('.content_cashier ul li');
    obj.each(function(){
        if(jq(this).is('.paid')){
          jq(this).find('input').attr({disabled:'disabled'});
        }
    });

}
//---------------公告start
//消息与公告弹窗
function announcementBox(title,content,type) {
    var str = '<div class="window_box_announcement ovh" style="display:none"><div class="window_box_announcement_title"><p>公告</p><a class="zxgsbg_ico_msg_close" onclick="window_box_close(this);close_notice('+type+');" href="javascript:void(0)"></a></div><div class="announcement_base"><p>'+title+'</p><span>'+content+'</span></div></div>';

    var boxObj = jq('div.window_box_announcement'),
        closeObj = null;
    //if(boxObj.length != 0) {
    boxObj.remove();
    jq('body').append(str);
    boxObj = jq('div.window_box_announcement');
    closeObj = boxObj.find('.zxgsbg_ico_msg_close');
    boxObj.slideDown();
    closeObj.click(function() {
        boxObj.slideUp(function() {
            boxObj.remove();
        });
    });
    //}
}

function ask_notice(type,nid)
{
    jq.ajax({
        type: "GET",
        url: '/notice.php',
        dataType:'json',
        data: {type:type,nid:nid},
        success:function(res){
            if(!res)
            {
                return;
            }
            if(type==400 && res.title)
            {
                var htm=res.title;
                if(res.url)
                {
                    htm='<a href="'+res.url+'" target="_blank" title="" rel="nofollow">'+res.title+'</a>';
                }
                var hm='<p class="info_tips" id="notice_400"  ><em class="sprite_img1 close_tips" onclick="close_notice('+type+');" ></em>'+htm+'</p>';
               // var hm='<div id="notice_400" class="zxgsbg_msg">'+htm+'<a href="javascript:;" onclick="close_notice('+type+');" class="zxgsbg_ico_msg_close"></a></div>';
                if(jq('.main_container').length){
                    jq('.main_container').before(hm);
                }
            }
            else if(type==200 && res.content && res.title)
            {
                noteCheatInspect(res.title,res.content,type);
            }
            else if(type==300 && res.content && res.title)
            {
                announcementBox(res.title,res.content,type);
            }
        }
    });
}

function close_notice(type)
{
    jq.ajax({
        type: "GET",
        url: '/notice.php',
        dataType:'json',
        data: {notice_close:1,type:type},
        success:function(res){
            if(res){
                if(type==400){
                    jq('#notice_400').hide();
                }else if(type==300){
                    return;
                }else if(type==200){
                    return;
                }
            }
        }
    });
}

//请警惕假风投欺骗性调查
function noteCheatInspect(title,content,type){
    var successStr = '<div class="window_box_title"><p>'+title+'</p><a class="window_box_close" onclick="window_box_close(this);close_notice('+type+')" href="javascript:void(0)"></a></div><div class="company_base zxgsbg_pop_text">'+content+'</div>';
    jq('.window_box').windowBox({
        width:475,
        wbcStr:successStr,
        showTitle:'none'
    });
}
