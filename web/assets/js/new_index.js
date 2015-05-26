// JavaScript Document
/*************************************************
 *
 * js File,created by dean, on 08.29 2014
 *
 * 最后编辑人：dean  (每次修改本文件)
 *
 * 前端JS文件，开发的代码可先自行创建文件
 *
 **************************************************/
var companyData;
//notice();
(function(jq) {
  jq.fn.focusAndBlur = function(newClass) {
    jq(this).val("").click(function() {
      jq(this).css('border-color','#96d5b9');
    }).bind("keydown input",function() {
      jq(this).next("span").hide();
    }).blur(function() {
      if(jq(this).val() == "") {
        jq(this).next("span").show();
      }
      jq(this).css('border-color','#ccc');
    });
  }
})(jQuery);
!function(jq){
  //checkClientNum();

  addBanner('1');
  setInterval("addBanner()",20000);

  //家居建材
  jq('.index_jc_tab > a').click(function(){
    jq('.index_jc_tab > a').removeClass('on');
    jq(this).addClass('on');
    var n = jq('.index_jc_tab > a').index(jq(this));
    jq('.index_jc_show').hide();
    jq('.index_jc_show').eq(n).show();
  });
  //表单效果
  jq('.form_line > input').val("");
  jq('.form_line > label').click(function(){
    jq(this).parent().find('input').focus().trigger('click');
  });
  jq('.form_line > input').on('keydown',function(){
    jq(this).parent().find('label').hide();
  });
  jq('.form_line > input').blur(function(){
    if(jq(this).val() == '') jq(this).parent().find('label').show();
  });

  // jq(".index_form input[type=text]").each(function() {
  //     jq(this).focusAndBlur();
  // });

    //2015-3-3首页首屏改版 By windy
    //申请tab切换
    var sepcilText = jq('.sec_topr_form input:text');
    jq('ul.sec_topr_tab').on('click', 'li', function() {
        var leftArr = [31, 105, 178, 252],
            tip = jq('.sec_topr_form > .form_hd > p'),
            idx = jq(this).index() || 0;

        jq(this).addClass('on').siblings().removeClass('on');
        jq('i.index_ico_arrow').css('left', leftArr[idx] + 'px');
        tip.hide().eq(idx).show();
        sepcilText.eq(0).trigger('click').focus();
    });

    //text border颜色处理

    sepcilText.on('click focus',function() {
        //jq(this).parents('.index_form').find('input:text').not(this).css('border-color', '#ddd');
        jq(this).css('border-color', '#f25618');
    });

    sepcilText.eq(0).trigger('click').focus();

    //业主说和行业说hover
    jq('.sec_top_say_hd > span').hover(function() {
        var idx = jq(this).index() || 0,
            obj1 = jq('#yezhuSay'),
            obj2 = jq('#hangyeSay');
        jq(this).addClass('on').siblings('span').removeClass('on');
        if(idx == 0) {
            obj1.show();
            obj2.hide();
        } else {
            obj1.hide();
            obj2.show();
        }
    });
    //业主说滑动
    jq('#yezhuSay').slider({
        speed:false,
        moveSlider:true,
        opacity:false,
        markSlider:false,
        time:8000
    });
    //行业说滑动
    jq('#hangyeSay').slider({
        speed:false,
        moveSlider:true,
        opacity:false,
        markSlider:false,
        time:8000
    });

    //End add

}(jQuery);
function notice()
{
    var preview='';
    if(jq('#preview').length)
    {
        preview =jq('#preview').val();
    }
    if(preview==null || preview==0 || preview==undefined || preview=='')
    {
        preview=0;
    }
    ask_notice(100,preview);
}

// 检测客户数量
var clientData;
/*function checkClientNum(){
  // 业主免费服务特效
  jq.get('/api/getindexdata.php','',function(data){


  },'json');
};*/
function setNextClass(i,sClassName,sClassNameOn,sClassNameUpOn,climax){
   jq('.banner_hd > div.banner_hd_num  > div').eq(i).attr('class','').addClass(sClassNameUpOn).addClass(sClassName);
   jq('.banner_hd > div.banner_hd_num  > div').eq(i).find('span').eq(1).show();
   if(climax == 0){
       var p = i - 1;
       climaxAdd(p)
   }
   setTimeout("setNextClassTwo("+i+",'"+sClassName+"','"+sClassNameOn+"','"+sClassNameUpOn+"')",200);//上
};
function setNextClassTwo(i,sClassName,sClassNameOn,sClassNameUpOn){
  jq('.banner_hd > div.banner_hd_num > div').eq(i).find('span').eq(1).hide();
   jq('.banner_hd > div.banner_hd_num  > div').eq(i).removeClass(sClassNameUpOn);
};
function climaxAdd(p){
    var ca = jq('.banner_hd > div.banner_hd_num  > div').eq(p).attr('class').split('user_board_'),
        sClassN = 'user_board_',
        tn = Number(ca[1])+1;
    if(tn == 10 ) tn = 0;
    var sClassName = sClassN + tn,
        sClassNameOn = sClassName + "_on",
        sClassNameUpOn = sClassName +"_upOn";
    setNextClass(p,sClassName,sClassNameOn,sClassNameUpOn,tn)
}

//add by Kevin.yuan for 装修公司广告添加初始化 随机展示某个公司的广告 2014-9-20
var countComAd = jq('.zxgs_info > ul.logo_list > li.has_ad').length;
jq('.zxgs_info > ul.logo_list > li.has_ad').eq(GetRandomNum(1,countComAd)).addClass('on');
  //获取范围内随机数
function GetRandomNum(Min,Max)
{
  var Range = Max - Min;
  var Rand = Math.random();
  return(Min + Math.round(Rand * Range));
}


//深圳装修公司特效
function changeCompany(t){
   var obj = jq('.zxgs_info > ul.logo_list > li.has_ad'),
       liOn = obj.index(jq('.zxgs_info > ul.logo_list > li[class="has_ad on"]')) +1 ,
       liLen = obj.length;

   obj.removeClass('on');
   if(liOn != liLen){
     var coId = obj.eq(liOn).attr('v');
     obj.eq(liOn).addClass('on');
   }else{
     var coId = obj.first().attr('v');
     obj.first().addClass('on');
   };
   var  cd = jq('.zxgs_info > ul.logo_list > li[class="has_ad on"]').attr('v');
   if(t!="1"){
     addCompanyData(cd,"1");
   }else{
     addCompanyData(cd);
   }
};
function addCompanyData(id,t){
   if(t=="1"){
     jq.get('/api/getindexdata.php?type=com','',function(data){
        companyData = data;
        linkCompnayData(companyData,id);
     },'json');
   }else{
     linkCompnayData(companyData,id);
   };
};
function linkCompnayData(obj,id){

   var str = '',
       data = obj[''+id+''],
       bClass = "",
       dClass = "",
       cClass = "",
       sLiStr = '';
   str += '<div class="zxgs_detail_hd">';
   str += '<a href="/zs/'+id+'/" target="_blank"><img src="'+data.hp+'" width="150" height="150"></a>';
  /* str += '<div class="zxgs_score"><span>综合评分</span>';
   str += '<a href="javascript:void(0)" class="score_wrap"><span>'+data.zh+'</span><em></em></a>';
   str += '<div class="score_window"><dl>';
   if(data.zha['design']['sign'] == "低于")  bClass = "zsw_g";
   if(data.zha['construct']['sign'] == "低于")  cClass = "zsw_g";
   if(data.zha['service']['sign'] == "低于") dClass = "zsw_g";
   if(data.zha['service']['percent'] == "0.00")  sign = '';
   str += '<dd><em>设计:</em>'+data.zha['design']['score']+' <b class="'+bClass+'">'+data.zha['design']['sign']+'行业</b>'+'<em class="window_data">'+data.zha['design']['percent']+'</em></dd><dd><em>服务:</em>'+data.zha['service']['score']+' <b class="'+dClass+'">'+data.zha['service']['sign']+'行业</b>'+'<em class="window_data">'+data.zha['service']['percent']+'</em></dd><dd><em>施工:</em>'+data.zha['construct']['score']+' <b  class="'+cClass+'">'+data.zha['construct']['sign']+'行业</b>'+'<em class="window_data">'+data.zha['construct']['percent']+'</em></dd>';*/
   str += '<div class="zxgs_btn"><a href="javascript:void(0)" onClick="javascript:freeBooking(this)" class="zxgs_btn_book">免费预约</a></div>';
   str += '<div class="zxgs_zx">已有<span> '+data.nu+' </span>人咨询</div>';
   str += '</div></div>';
   str += '<div class="zxgs_koubei"><span>口碑值</span><em><a href="/zs/yz_dp.php?uid='+id+'" target="_blank">'+data.xy+'</a></em></div>';
   str += '<div class="zxgs_detail_bd">';
/*   str += '<div class="zxgs_btn"><div>已有<span>'+data.nu+'</span>人咨询</div><a href="javascript:void(0)" onClick="javascript:freeBooking(this)" class="zxgs_btn_book">免费预约</a></div>';
*/   str += '<div class="zxgs_name">';
   str += '<span><a href="/zs/'+id+'/" target="_blank">'+data.cn+'</a></span><em> '+data.ap+' </em>';

   /*var credit = data.xh;
   var  arrCredit = credit.split(''),
         arrCreditClass;
   for(var m = 0; m < arrCredit.length; m++){
      if (arrCredit[m] == 'd'){
        arrCreditClass = "ico_diamond";
      }else if(arrCredit[m] == 'h'){
        arrCreditClass = "ico_heart";
      }else{
        arrCreditClass = "ico_crown";
      }
      str += '<i class="'+arrCreditClass+'"></i>';
   }*/
   str += '</div>';
   str += '<div class="zxgs_level">';
   var iconTitle = ['营业执照已认证','推荐公司','认证公司','优惠信息','已缴纳保障金'];
   var icontj=0,stra='',strb='';

   for(var t = 0; t<5; t++){
      var len = t,
          nt = t + 1;
        if(nt==1 || nt==5 || nt==4)
        {
          if(nt==4 && data.iconarr[t] =="yes")
          {
            stra = '<a href="/zs/'+id+'/news-1-1.html" target="_blank" >';
            strb = '</a>'
          }
          if(nt==5)
          {
            iconTitle[len] +=data.cx+'.00元';
          }
          if(data.iconarr[t] =="yes"){
            str +=stra+'<i class="level_ico level_ico'+nt+'" title="'+iconTitle[len]+'"></i>'+strb;
            stra='',strb='';
          }else{
            str += '<i class="level_ico level_ico'+nt+'_no" ></i>';
          }
        }else if(nt==2 || nt==3)
        {
          if(icontj==0)
          {
            if(data.iconarr[t] =="yes"){
            str += '<i class="level_ico level_ico'+nt+'" title="'+iconTitle[len]+'"></i>';
            icontj=1;
            }else if(data.iconarr[t] =="no" && nt==3)
            {
               str += '<i class="level_ico level_ico3_no" ></i>';
               icontj=1;
            }
          }
        }
    }
   if(data.iconarr[4]=="yes"){
       str += '<em title="已缴纳保障金'+data.cx+'.00元">'+data.cx+'元</em>';
   }

   if(data.nc == 0){
      str+='<i class="level_ico level_ico6_no" title="设计方案"></i><em>0套</em>';
   }else{
      str+='<a href="/zs/case'+id+'/" target="_blank" title="设计方案" ><i class="level_ico level_ico6" ></i><em>'+data.nc+'套</em></a>';
   }
   if(data.ng == 0){
      str+='<i class="level_ico level_ico7_no" title="施工案例"></i><em>0个</em>';
   }else{
      str+='<a href="/zs/visit'+id+'/" target="_blank" title="施工案例"><i class="level_ico level_ico7" ></i><em>'+data.ng+'个</em></a>';
   }
   if(data.pl == 0){
      str+='<i class="level_ico level_ico8_no" title="评价次数"></i><em>0次</em>';
   }else{
      str+='<a href="/zs/yz_dp.php?uid='+id+'"  target="_blank" title="评价次数"><i class="level_ico level_ico8" ></i><em>'+data.pl+'次</em></a>';
   }
   str += '</div>';
   str += '<div class="zxgs_address"><em></em>'+data.ad+'</div>';
  /* str += '<ul class="zxgs_data clearfix">';
   if(data.nc == 0){
     str += '<li><div class="data_num">'+data.nc+'</div><div class="data_item">设计方案</div></li>';
   }else{
     str += '<li><a href="/zs/case'+id+'/" target="_blank"><div class="data_num">'+data.nc+'</div><div class="data_item">设计方案</div></a></li>';
   };
   if(data.ng == 0){
     str += '<li><div class="data_num">'+data.ng+'</div><div class="data_item">施工案例</div></li>';
   }else{
     str += '<li><a href="/zs/visit'+id+'/" target="_blank"><div class="data_num">'+data.ng+'</div><div class="data_item">施工案例</div></a></li>';
   };
   if(data.pl == 0){
     str += '<li><div class="data_num">'+data.pl+'</div><div class="data_item">评价人数</div></li>';
   }else{
     str += '<li><a href="/zs/yz_dp.php?uid='+id+'" target="_blank"><div class="data_num">'+data.pl+'</div><div class="data_item">评价人数</div></a></li>';
   };
   str += '</ul>';*/
   str += '<div class="zxgs_des">';
   str += '<div class="zxgs_font"><span>'+data.jj+'</span><a href="/zs/'+id+'/" target="_blank">更多</a></div>';
   str += '<div class="zxgs_img clearfix">';
   for(var i = 0; i < data.ca.length; i++){
     str += '<a href="'+data.ca[i]['target_url']+'" target="_blank">';
     str += '<img src="'+data.ca[i]['card_img_url']+'" width="150" height="105">';
     str += '<span class="zxgs_img_tit"><em>'+data.ca[i]['img_num']+'张</em><span>'+data.ca[i]['desc']+'</span></span>';
     str += '<span class="zxgx_imgie"></span>';
   }
   str += '</div></div></div>';
   jq('.zxgs_detail').html(str);
   jq('.score_wrap').mouseenter(function(){
      jq(this).next().show();
   });
   jq('.score_wrap').mouseleave(function(){
      jq(this).next().hide();
   });
}
var yuyue_apply_agin=0;
//表单验证
function checkFromLine(obj){
  var a =  jq(obj).parent().find('.text[name="yourname"]').checkForm({className:"index_check",content:["称呼不可为空","称呼最多12个字符"],type:[1,2],reg:{len:12},checkFormType:obj, displayNum:true});
  var b =   jq(obj).parent().find('.text[name="yourphone"]').checkForm({className:"index_check",content:["手机号码不可以为空","请填写正确的手机号码"],type:[1,2], reg:0,checkFormType:obj, displayNum:true});
  var c = jq(obj).parent().find('.select_l[name=User_Shen]').checkForm({className:"index_check",content:["请选择您的所在地"],type:[1],checkFormType:obj, displayNum:true,checkType:"select"});
   if(c === 0) {
		var d = jq(obj).parent().find('.select_r[name=User_City]').checkForm({className:"index_check",content:["请选择您的所在地"],type:[1],checkFormType:obj, displayNum:true,checkType:"select"});
   }
  if( a === 0 && b === 0 && c === 0 && d === 0){
      upLoadData(obj);
   }

}
function upLoadData(obj){

  var chenghu =  jq(obj).parent().find('.text[name="yourname"]').val();
  var phone   =  jq(obj).parent().find('.text[name="yourphone"]').val();
  //var price   =  jq(obj).parent().find('#select').val();
  var price = '';
  var shen    =  jq(obj).parent().find('select[name="User_Shen"]').val();
  var city    =  jq(obj).parent().find('select[name="User_City"]').val();
  var ptag;
  // if(jq(obj).parent().find('#select').length>0)
  // {
  //   clickStream.getCvParams('1_1_1_3');
  //   ptag='1_1_1_3';
  // }else
  // {
  //   clickStream.getCvParams('1_1_1_1');
  //   ptag='1_1_1_1';
  // }

  //2015-3-3首页首屏改版 By windy
    if(jq(obj).parents('.sec_topr_bd').length > 0) {
        var idx = jq('ul.sec_topr_tab > li.on').index() || 0,
        ptagArr = ['1_1_1_75', '1_1_1_76', '1_1_1_77', '1_1_1_78'];
        ptag = ptagArr[idx];
        clickStream.getCvParams(ptag);
    } else {
        clickStream.getCvParams('1_1_1_3');
        ptag='1_1_1_3';
    }
  //End add

  //price = price?price:'';
  pro_sourceid = 3;
  pro_s_sourceid = 0;
  forum_sourice = 14;
  operating_type = 1;
  device_src = 7;
  sourceid = 1;
  s_sourceid = 2;
  var url = "/zb/index.php";

  var _data = "phone=" + phone + "&chenghu=" + chenghu + "&pro_sourceid=" + pro_sourceid + "&pro_s_sourceid=" + pro_s_sourceid + "&forum_sourice=" + forum_sourice + "&operating_type=" + operating_type +  "&device_src=" + device_src + "&sourceid=" + sourceid + "&s_sourceid=" + s_sourceid +"&ptag="+ptag +"&zxys="+price+"&shen="+shen +"&city="+city;

/*******************************微信招标************************************/
		  var weixin_code = '';
		  var start_qrcode_id = '';
		  jq.ajax({
					async:true,
					type:"GET",
					dataType: 'jsonp',
					url:"http://www.to8to.com/api/weixin/run.php",
					data:{action:'createQrcode',cookie_id:'test',data:'createWxCode',type:1},
					success:function(res){
							if(res.code==0)
							{
								weixin_code = res.url;
								start_qrcode_id = res.qrcode_id;
/*******************************微信招标************************************/

						  jq.ajax({
							type: "POST",
							url: url,
							data: _data,
							beforeSend: function() {
							  var reg1 = /^((\(\d{2,3}\))|(\d{3}\-))?(13|15|17|18)\d{9}$/;
							  if (!reg1.test(phone)) {
								return false;
							  }
							  if (!chenghu || chenghu == "请填写您的姓名") {
								return false;
							  }
							  if (yuyue_apply_agin > 0) {
								return false;
							  } else {
								yuyue_apply_agin++;
							  }
							},
							success: function(result) {
							  var username=getCookie('username',true);


							  if (typeof(JSON) == "undefined") {
								var res = eval("(" + result + ")")
							  } else {
								var res = JSON.parse(result)
							  }

							  if (res.status == 1) {
								   if (!res.tmpYid)
								  {
									overFive();
									yuyue_apply_agin = 0;
									return;
								  }
								  if(!username)
								  {
									 var login = '</p><input type="button" value="登入" style="display:none" id="login_zb" onclick="zb_login('+res.tmpYid+','+res.phone+')">';
								  }
								  //更改部分，完善资料弹窗 2015-3-12 by windy
								  if(ptag == '1_1_1_78') {//验房不用完善资料
									    var successStr = zb_first_pop(weixin_code,res.tmpYid);
										jq('.window_box').windowBox({
										  width:560,
										  title:"提示",
										  wbcStr:successStr,
											closeFn:'stop_code_status'
										});
										zb_getwxstatus(start_qrcode_id,res.tmpYid);
								  } else {
									  indexSubZbStepOne(res,weixin_code);
								  }
								  /*var successStr = zb_first_pop(weixin_code,res.tmpYid);
									jq('.window_box').windowBox({
									  width:560,
									  title:"提示",
									  wbcStr:successStr,
								  		closeFn:'stop_code_status'
									});*/
									//indexSubZbStepOne(res,weixin_code);
									//End Modify
									//zb_getwxstatus(start_qrcode_id,res.tmpYid);
									yuyue_apply_agin = 0;
								return false;
							  }
							  else if(res.status == 5)
							  {
								 window_box_close();
								 indexYYFail(res.cityname);
								 yuyue_apply_agin = 0;
								 return false;
							  }
							  else
							  {
								var cityname = encodeURI(res.cityname);
								var tyid   = encodeURI(res.tmpid);
								showPopWin("http://www.to8to.com/zb/frame_global.php?msg="+cityname + "&tyid=" + tyid , 456, 254, null, true);
								yuyue_apply_agin = 0;
							  }

							}
						  })
/*******************************微信招标************************************/
									}
						else
						{
							alert(res.msg);
						}

					}
			  });
/*******************************微信招标************************************/ }
//招标登入
function zb_login(tmpYid,phone)
{
	window_box_close();
	setZero();
	showPopWin('http://www.to8to.com/pop_login.php?tmpyid='+tmpYid+'&phone='+phone, 500, 426, null, false);
}



  function pop_parent_submit (tmpyid,phone){
	  alert(tmpyid+'-----'+phone);return false;
	/*
	var aidsval = [];
		jq.ajax({
		  url:'/xgt_get_userinfo.php?' + Math.random(),
		  success:function(data){
			  if(data.status == true ){
			  jq('#imgids').attr('aids',data.aids);
			}else if( data.status == false){
			  jq('#imgids').attr('aids','false');
			}
		  },
		  async:false,
		  dataType:'json'
		});
		return aidsval;
	  }
	  */
  }


//清除标记
function setZero(){
	userWriteDiary = 0;
	userClickCollect=0;
	userClickCollectDiary=0;
	userClickDeleteComment=0;
	userClickDeleteDiary_did = 0;
	userClickDeleteDiary_sid = 0;
	userCommentDiary = 0;
	replayI = 0;
	replayName = 0;
	replayCid = 0;
	userSendContent = 0 ;
}
//申请成功
function indexYYSuccess() {
  var successStr = '<div class="apply_success"><span class="as_true"></span><strong>恭喜您，申请成功!</strong><em>土巴兔客服将于24小时内与您联系！</em></div>';
  jq('.window_box').windowBox({
    width:480,
    height:200,
    title:"提示",
    wbcStr:successStr,
    closeTime:5000
  });
}

//申请失败
function freeFail_one(obj){
  var failStr = '<div class="apply_fail"><span class="as_fail"></span><strong>非常抱歉,您当前的申请失败，请稍候再试！</strong></div>';
  jq('.window_box').windowBox({
    width:480,
    height:257,
    title:"提示",
    wbcStr:failStr,
       closeTime:3000

    })
};
//免费预约
function freeBooking(obj){
  var bookingStr = '';
  bookingStr += '<form id="yyForm">';
  bookingStr += '<input type="hidden" value="1" id="toid" name="source">';
  bookingStr += '<input type="hidden" id="s_source" value="2" name="s_source">';
  bookingStr += '<input type="hidden" id="ptag" value="1_1_1_2" name="ptag">';
  bookingStr += '<div class="free_booking"><ul>';
  bookingStr += '<li class="height_auto"><span class="fb_title">您的称呼</span><input type="text" name="chenghu" id="name" class="fq_text fq_text2"></li>';
  bookingStr += '<li><span class="fb_title">手机号码</span><input type="text" name="phone" id="phone" class="fq_text fq_text2"></li>';
  bookingStr += '<li><span class="fb_title">所在城市</span><select class="fb_province"  id="User_Shenfree" name="User_Shen" onchange="changeProvince(\'User_Shenfree\',\'User_Cityfree\',\'User_Townfree\');"><option>省/市</option></select><select class="fb_city"  id="User_Cityfree" name="User_City" onchange="changeTown(\'User_Shenfree\',\'User_Cityfree\',\'User_Townfree\');"><option value="">市/地区</option></select><div style="display: none;"><select class="langSelect" id="User_Townfree" name="User_Town"><option>县/区</option></select></div></li>';
  bookingStr += '</ul>';
  bookingStr += '<div class="fb_upload"><input type="button" id="saveYY" value="提交" onClick=javascript:checkFreeBooking(this)><em>全国免费热线:4006-900-282</em></div>';
  bookingStr += '<div class="fb_description"><b></b><em>预约需要土巴兔来审核，请耐心等待，我们会在收到您的信息之后第一时间和您取得联系！</em></div></div></form>';
  clickStream.getCvParams('1_1_1_2');
  jq('.window_box').windowBox({
    width:459,
    title:"免费预约装修公司",
    littleTitle:"装修立省30%",
    wbcStr:bookingStr
    });
  gpm.def_province = ["省/市", ""];
  gpm.def_city1 = ["市/地区", ""];
  gpm.initProvince($("User_Shenfree"));
 // gpm.initProvince($("User_Cityfree"));



};

function checkBookStylist() {
  jq('input[name="chenghu"]').checkForm({className:"fb_check",content:["称呼不可以为空"],type:[1]});
  jq('input[name="phone"]').checkForm({className:"fb_check",content:["手机号码不可以为空","请填写正确的手机号码"],type:[1,2], reg:0});
}


function checkFreeBooking(obj){//免费申请预约验证
  obj = jq(obj).parent()[0];
  var nNameValue = jq('input[name="chenghu"]').checkForm({className:"fb_check",content:["称呼不可以为空"],type:[1],checkFormType:obj,displayNum:true});
  var nPhoneValue = jq('input[name="phone"]').checkForm({className:"fb_check",content:["手机号码不可以为空","请填写正确的手机号码"],type:[1,2], reg:0,checkFormType:obj,displayNum:true})
  var sheng = jq('#User_Shenfree').checkForm({className:"fb_check",content:["请选择你的所在省份"],type:[1], reg:0,checkFormType:obj,checkType:"select",displayNum:true});
  if(sheng == 0) {
    var city = jq('#User_Cityfree').checkForm({className:"fb_check",content:["请选择你的所在地区"],type:[1], reg:0,checkFormType:obj,checkType:"select",displayNum:true});
  }

  if(nNameValue == 0 && nPhoneValue ==0 && sheng ==0 && city ==0){
	/*******************************微信招标************************************/
			  var weixin_code = '';
			  var start_qrcode_id = '';
			  jq.ajax({
						async:true,
						type:"GET",
						dataType: 'jsonp',
						url:"http://www.to8to.com/api/weixin/run.php",
						data:{action:'createQrcode',cookie_id:'test',data:'createWxCode',type:1},
						success:function(res){
								if(res.code==0)
								{
									weixin_code = res.url;
									start_qrcode_id = res.qrcode_id;
	/*******************************微信招标************************************/
      jq.ajax({
        url: '/zb/index.php',
        type: 'post',
        dataType: 'json',
        data: jq("#yyForm").serialize(),
        beforeSend: function() {
          if (yuyue_apply_agin > 0) {
            return false
          } else {
            yuyue_apply_agin++
          }
        },
        /*success: function(data){
            window_box_close();
            if(data.status == 1){
                 indexYYSuccess();
            } else if(data.status == 5){
               indexYYFail(data.cityname);
            }
        },
        error: function(){
            window_box_close();
        }*/

		success: function(res) {
			  window_box_close();
			  if (res.status == 1) {
				   if (!res.tmpYid)
				  {
						overFive();
						yuyue_apply_agin = 0;
						return;
				  }

				  //更改部分，完善资料弹窗 2015-3-12 by windy
				  /*var successStr = zb_first_pop(weixin_code,res.tmpYid);
					jq('.window_box').windowBox({
					  width:560,
					  title:"提示",
					  wbcStr:successStr,
						closeFn:'stop_code_status'
					});*/
					indexSubZbStepOne(res,weixin_code);
					//End Modify
					//zb_getwxstatus(start_qrcode_id,res.tmpYid);
				return false;
			  }
			  else if(res.status == 5)
			  {
				 window_box_close();
				 indexYYFail(res.cityname);
				 return false;
			  }
			  else
			  {
				var cityname = encodeURI(res.cityname);
				var tyid   = encodeURI(res.tmpid);
				showPopWin("http://www.to8to.com/zb/frame_global.php?msg="+cityname + "&tyid=" + tyid , 456, 254, null, true);
			  }
			  yuyue_apply_agin = 0
			}


     });
/*******************************微信招标************************************/
									}
						else
						{
							alert(res.msg);
						}

					}
			  });
/*******************************微信招标************************************/ }

};


//免费服务随机抽取
/*function isAppend(){
  return  Math.random() >= 0.5? true:false;
};
function randomArray(array){
  var resultArray = [];
  for(var count = 0; count< array.length; count++)
  {
      if(isAppend())
      {

          resultArray = array[count];
      }
  }
  return resultArray;
};
function creatService(obj){
   var randomI = randomArray(obj);
   clientData = randomI;
   jq('.banner_hdr').remove();
   jq('.banner_hd').append(randomI);

};*/

 //土巴兔装修榜特效
var srcPic,
    picPos;
    srcPic = 'bpic';
    picPos = [536,728,192,384];
jq('.index_rank_img > div').mouseenter(function(){
    var vLeftTwo = jq('.index_rank_img_2').position().left,
        vLeftThree = jq('.index_rank_img_3').position().left;
    if(jq(this).hasClass('index_rank_img_1') && vLeftTwo != ''+picPos[0]+''){
        jq('.index_rank_img_2').stop().animate({
            left:picPos[0]
        },500);
        jq('.index_rank_img_3').stop().animate({
            left:picPos[1]
        },500);
    }else if(jq(this).hasClass('index_rank_img_2') && vLeftTwo == ''+picPos[0]+''){
        jq('.index_rank_img_2').stop().animate({
            left:picPos[2]
        },500);
    }else if(jq(this).hasClass('index_rank_img_2') && vLeftTwo == ''+picPos[2]+'' && vLeftThree == ''+picPos[3]+''){
        jq('.index_rank_img_3').stop().animate({
            left:picPos[1]
        },500);
    }else if(jq(this).hasClass('index_rank_img_3') && (vLeftTwo >= ''+picPos[0]+'' || vLeftTwo >= ''+picPos[2]+'')){
        jq('.index_rank_img_2').stop().animate({
            left:picPos[2]
        },500);
        jq('.index_rank_img_3').stop().animate({
            left:picPos[3]
        },500);
    };

});
/* function totZxb(){
    var srcPic,
        picPos;
    if(jq('body').hasClass('narrow_1220')){
       srcPic = 'bpic';
       picPos = [536,728,192,384]
     }else{
       srcPic = 'spic';
       picPos = [396,538,142,284]
    }
    var clickArr = ['onclick="javascript:clickStream.getCvParams(\'1_1_1_9\')"',

                   ]
    jq.ajax({
        url: '/api/getindexdata.php?type='+srcPic+'',
        type: 'get',
        dataType: 'json',
        success:function(data){
          var str = '';
          for(var i = 0; i < data.length; i++){
             var n = i + 1;
             var N = i + 19;
             str += '<div class="index_rank_img_'+n+'"><a href="'+data[i]['l']+'" target="_blank" rel="nofollow" onclick="javascript:clickStream.getCvParams(\'1_1_1_'+N+'\') "><img src="'+data[i]['p']+'" alt="'+data[i]['a']+'" ></a></div>';
          };
          jq('.index_rank_img').html(str);
          jq('.index_rank_img > div').mouseenter(function(){
              var vLeftTwo = jq('.index_rank_img_2').position().left,
                  vLeftThree = jq('.index_rank_img_3').position().left;
             if(jq(this).hasClass('index_rank_img_1') && vLeftTwo != ''+picPos[0]+''){
               jq('.index_rank_img_2').stop().animate({
                left:picPos[0]
               },500);
               jq('.index_rank_img_3').stop().animate({
                left:picPos[1]
               },500);
             }else if(jq(this).hasClass('index_rank_img_2') && vLeftTwo == ''+picPos[0]+''){
               jq('.index_rank_img_2').stop().animate({
                left:picPos[2]
               },500);
             }else if(jq(this).hasClass('index_rank_img_2') && vLeftTwo == ''+picPos[2]+'' && vLeftThree == ''+picPos[3]+''){
               jq('.index_rank_img_3').stop().animate({
                left:picPos[1]
               },500);
             }else if(jq(this).hasClass('index_rank_img_3') && (vLeftTwo >= ''+picPos[0]+'' || vLeftTwo >= ''+picPos[2]+'')){
               jq('.index_rank_img_2').stop().animate({
                left:picPos[2]
               },500);
               jq('.index_rank_img_3').stop().animate({
                left:picPos[3]
               },500);
             };

          });
        }
    });

 };*/

 //延迟加载
jq(window).scroll(function(){
   checkScroolTop()
});
 var scrollCount = 0;
 function checkScroolTop(){
   var sScrollTop = document.documentElement.scrollTop || document.body.scrollTop,
       eventOne = jq('.index_module_header').offset().top;
   if((sScrollTop > eventOne) && scrollCount == 0){
      //totZxb();//装修榜加载
      changeCompany();//装修公司数据加载
      var changeC = setInterval('changeCompany("1")',20000);
      var clChangeC;
      jq('.zxgs_info > ul.logo_list > li.has_ad').click(function(){
         jq('.zxgs_info > ul.logo_list > li.has_ad').removeClass('on');
         jq(this).addClass("on");
         clearInterval(changeC);
         clearInterval(clChangeC);
         clChangeC  = setInterval('changeCompany("1")',20000);
      });
      jq('.zxgs_detail').hover(function(){
         clearInterval(changeC);
         clearInterval(clChangeC);
      },function(){
         clChangeC  = setInterval('changeCompany("1")',20000);
      });
      jq('.window_box ').hover(function(){
         clearInterval(changeC);
         clearInterval(clChangeC);
      },function(){
         clChangeC  = setInterval('changeCompany("1")',20000);
      });

      scrollCount = 1;
   }


 };
jq('.zxgs_hd > ul > li').click(function(){
    jq('.zxgs_hd > ul > li').removeClass('on');
    jq(this).addClass('on');
    var n = jq('.zxgs_hd > ul > li').index(jq(this));
    jq('.zxgs_bdl_div').hide();
    jq('.zxgs_bdl_div').eq(n).show();
});
//banner加载
function addBanner(typeNo){
   var str = '',
       webScreen = window.screen.width,
       sDataType,
       bannerWidth;
    sDataType = 'bannerb';
    bannerWidth = 900;


   //jq.ajax({
     //url:'/api/getindexdata.php',
     //dataType:"json",
     //cache:false,
     //type:'get',
     //data:{type:sDataType},
     //success:function(data){
        //var sFreenum = data.tnum,
           //arrFreePos = sFreenum.split(""),
           //sClassN = "user_board_",
           //sFreeStr = "",
           //sFreeNameStr ='',
           //sFNpos = [],
           //str = '',
           //sMtr = '',
           //bzLink = '';
       //if(arrFreePos.length == 0 ) return false;
       //if(jq('.banner_hd').attr('v') == undefined){
          //jq('.banner_hd').attr('v', sFreenum);

         //sFreeStr += '已有 ';
         //for(var i = 0; i<arrFreePos.length; i++){
            //var numClassName = sClassN+arrFreePos[i];
            //sFreeStr += '<div class="'+numClassName+'"><span class="ub_bottom"></span><span class="center_line"></span><span class="ub"></span></div> ';
         //};
         //sFreeStr += ' 位业主享受土巴兔免费服务！';

        //// sFreeStr += '<span class="banner_hdr"><em>'+data.newapply[0]['time']+'</em><em>'+data.newapply[0]['name']+'</em><em>'+data.newapply[0]['phone']+'</em>'+yuyuets+'<a href="'+bzLink+'" target="_blank">'+data.newapply[0]['type']+'服务</a></span>';

         //jq('.banner_hd_num').html(sFreeStr);

        //setInterval('bannerHdrListStart()',20000)
         ///*creatService(sFNpos)
         //window.setInterval(function(){
          //creatService(sFNpos)
         //},8000)*/

      //}else{
           //var sOldFreenum = jq('.banner_hd').attr('v'),
               //nAddFreeNum = Number(sFreenum) - Number(sOldFreenum),
               //sClassN = "user_board_",
               //arrFreenum = sFreenum.split("");

           //if(nAddFreeNum == 0 ||  Number(sOldFreenum) == Number(sFreenum)) return false;

           //var arrAddFreeNumPos = nAddFreeNum.toString().split(""),
               //nArea = arrFreenum.length - arrAddFreeNumPos.length;
           //for(var i = nArea ; i<arrFreenum.length; i++){
              //var sClassName = sClassN + arrFreenum[i],
                  //sClassNameOn = sClassName + "_on",
                  //sClassNameUpOn = sClassName +"_upOn";
              //jq('.banner_hd > div.banner_hd_num  > div').eq(i).addClass(sClassNameOn);
              //setTimeout("setNextClass("+i+",'"+sClassName+"','"+sClassNameOn+"','"+sClassNameUpOn+"','"+arrFreenum[i]+"')",200); //下
           //};
           ///*jq('.banner_hdr').attr('v','1');
           //jq('.banner_hdr[v="1"]').animate({
            //top:'-10px',
            //opacity:0
           //},1000);
           //var m = removeHdr();
           //if(data.newapply[0]['type'] == "免费验房"){
            //bzLink = '/zb/index6.html';
           //}else if(data.newapply[0]['type'] == "免费户型设计" || data.newapply[0]['type'] == '免费精准量房' || data.newapply[0]['type'] == '免费报价'){
              //bzLink = '/zb';
           //}else{
              //bzLink = '/company/zxb.php';
           //}
           //sFreeNameStr += '<span class="banner_hdr" style="opacity:0; top:20px"><em>'+data.newapply[0]['time']+'</em><em>'+data.newapply[0]['name']+'</em><em>'+data.newapply[0]['phone']+'</em>已经享受<a href="'+bzLink+'" target="_blank">'+data.newapply[0]['type']+'服务</a></span>';*/
             ////jq('.banner_hd').append(sFreeNameStr);
            ///* jq('.banner_hdr').animate({
              //top:'13px',
              //opacity:1
             //},1000);*/
           //jq('.banner_hd').attr('v', sFreenum);
           //var sUserAdd = ''
      //}

     //}

   //})
};
jq('.index_banner > ul > li').first().removeClass('on').css({display:"block",opacity:"1"});
jq('#index_slider').slider({
    speed:500,
    time:5000,
    addControl:true
},"index_slider");
function bannerHdrListStart(){
   jq('.bhl_one').animate({top:'-28px'},function(){
     jq(this).css('top','0px').find('p').first().appendTo(this);
   })
}

// //请求ceo面对面板块数据
// function getCeodata(obj){
//   if(jq(obj).attr('v') == undefined){
//     jq.ajax({
//       url:'/api/getindexdata.php',
//       dataType:"json",
//       post:'get',
//       cache:false,
//       data:{type:'ceo'},
//       success:function(data){
//         jq(obj).attr('v','1');
//         var str = '';
//         for(var i = 0 ; i<data.length; i++){
//           str += '<div class="zxgs_ceo_item">';
//           str += '<a class="zxgs_ceo_img" target="_blank" href="'+data[i]['link']+'" title="'+data[i]['page_keyword']+'"><img src="'+data[i]['img']+'"   width="136px" height="138px"  alt=""></a>';
//           str += '<div class="zxgs_ceo_txt">';
//           str += '<a class="zxgs_ceo_name" target="_blank" href="'+data[i]['link']+'">'+data[i]['page_keyword']+'</a>';
//           str += '<p class="zxgs_ceo_txt_title">'+data[i]['source']+'</p>';
//           var description = data[i]['page_description'];
//           if(window.screen.width < 1250){
//             description = data[i]['page_description'].slice('0',20) + "...";
//           }
//           str += '<p class="zxgs_ceo_txt_desc">'+description+'</p></div></div>';
//         };

//         jq('.zxgs_ceo').html(str);
//       }
//     })
//   }
// }

//装修效果图卖点
jq('.index_xgt>a').each(function(index, ele) {
    switch (index) {
    case 0:
        jq(this).on('click',
        function() {
           indexclickstream('1_1_1_25');
        });
        break;
    case 1:
        jq(this).on('click',
        function() {
           indexclickstream('1_1_1_26');
        });
        break;
    case 2:
        jq(this).on('click',
        function() {
           indexclickstream('1_1_1_27');
        });
        break;
    case 3:
        jq(this).on('click',
        function() {
           indexclickstream('1_1_1_28');
        });
        break;
    case 4:
        jq(this).on('click',
        function() {
            indexclickstream('1_1_1_29');
        });
        break;
    }
});

//装修攻略
jq('.diary_list>ul>li').click(function() {
    var curIndex = jq('.diary_list>ul>li').index(this);
    var upId = '';
    switch (curIndex) {
    case 0:
        upId = '1_1_1_30';
        break;
    case 1:
        upId = '1_1_1_31';
        break;
    case 2:
        upId = '1_1_1_32';
        break;
    case 3:
        upId = '1_1_1_33';
        break;
    }
    indexclickstream(upId);

});

//装修公司广告位卖点
jq('.zxgs_info.zxgs_bdl_div>ul>li').click(function() {
    var curIndex = jq('.zxgs_info.zxgs_bdl_div>ul>li').index(this);
    var upId = '';
    switch (curIndex) {
    case 0:
        upId = '1_1_1_34';
        break;
    case 1:
        upId = '1_1_1_35';
        break;
    case 2:
        upId = '1_1_1_36';
        break;
    case 3:
        upId = '1_1_1_37';
        break;
    case 4:
        upId = '1_1_1_38';
        break;
    case 5:
        upId = '1_1_1_39';
        break;
    case 6:
        upId = '1_1_1_40';
        break;
    case 7:
        upId = '1_1_1_41';
        break;
    case 8:
        upId = '1_1_1_42';
        break;
    case 9:
        upId = '1_1_1_43';
        break;
    case 10:
        upId = '1_1_1_44';
        break;
    case 11:
        upId = '1_1_1_45';
        break;
    }
    indexclickstream(upId);
});

//半通栏广告位
jq('.index_ad_box>a').each(function(index, ele) {
    switch (index) {
    case 0:
        jq(this).on('click',
        function() {
            indexclickstream('1_1_1_46');
        });
        break;
    case 1:
        jq(this).on('click',
        function() {
            indexclickstream('1_1_1_47');
        });
        break;
    case 2:
        jq(this).on('click',
        function() {
           indexclickstream('1_1_1_48');
        });
        break;
    case 3:
        jq(this).on('click',
        function() {
          indexclickstream('1_1_1_49');
        });
        break;
    case 4:
        jq(this).on('click',
        function() {
           indexclickstream('1_1_1_50');
        });
        break;
    case 5:
        jq(this).on('click',
        function() {
           indexclickstream('1_1_1_51');
        });
        break;
    }
});

//小区案例
jq('ul.index_villageCase_list>li').click(function() {
    var curIndex = jq('ul.index_villageCase_list>li').index(this);
    var upId = '';
    switch (curIndex) {
    case 0:
        upId = '1_1_1_52';
        break;
    case 1:
        upId = '1_1_1_53';
        break;
    case 2:
        upId = '1_1_1_54';
        break;
    }
    indexclickstream(upId);

});

//家居建材
jq('ul.index_jc_list>li').each(function(index, ele) {
    switch (index) {
    case 0:
        jq(this).on('click',
        function() {
            indexclickstream('1_1_1_55');
        });
        break;
    case 1:
        jq(this).on('click',
        function() {
            indexclickstream('1_1_1_56');
        });
        break;
    case 2:
        jq(this).on('click',
        function() {
           indexclickstream('1_1_1_57');
        });
        break;
    case 3:
        jq(this).on('click',
        function() {
           indexclickstream('1_1_1_58');
        });
        break;
    case 4:
        jq(this).on('click',
        function() {
            indexclickstream('1_1_1_59');
        });
        break;
    case 5:
        jq(this).on('click',
        function() {
           indexclickstream('1_1_1_60');
        });
        break;
    case 6:
        jq(this).on('click',
        function() {
            indexclickstream('1_1_1_61');
        });
        break;
    case 7:
        jq(this).on('click',
        function() {
           indexclickstream('1_1_1_62');
        });
        break;
    case 8:
        jq(this).on('click',
        function() {
            indexclickstream('1_1_1_63');
        });
        break;
    case 9:
        jq(this).on('click',
        function() {
           indexclickstream('1_1_1_64');
        });
        break;
    case 10:
        jq(this).on('click',
        function() {
           indexclickstream('1_1_1_65');
        });
        break;
    case 11:
        jq(this).on('click',
        function() {
            indexclickstream('1_1_1_66');
        });
        break;
    }
});

//展会专题
jq('ul#demo1>li').each(function(index, ele) {
    switch (index) {
    case 0:
        jq(this).on('click',
        function() {
            indexclickstream('1_1_1_67');
        });
        break;
    case 1:
        jq(this).on('click',
        function() {
            indexclickstream('1_1_1_68');
        });
        break;
    case 2:
        jq(this).on('click',
        function() {
            indexclickstream('1_1_1_69');
        });
        break;
    case 3:
        jq(this).on('click',
        function() {
            indexclickstream('1_1_1_70');
        });
        break;
    case 4:
        jq(this).on('click',
        function() {
            indexclickstream('1_1_1_71');
        });
        break;
    case 5:
        jq(this).on('click',
        function() {
           indexclickstream('1_1_1_72');
        });
        break;
    }
});
function indexclickstream(ptag)
{
   try {
       clickStream.getCvParams(ptag);
      } catch(e) {};
}
function ask_notice(type,nid)
{
    jq.ajax({
        type: "GET",
        url: 'notice.php',
        dataType:'json',
        data: {type:type,nid:nid},
        success:function(res){
        	if(res.title){
                var content=res.title;
                if(res.url)
                {
                    var content='<a  href="'+res.url+'"  target="_blank" rel="nofollow">'+content+'</a>';
                }
        		var html_head='<div class="mod_yeltip" id="mod_yeltip"><a class="yeltip_close" href="javascript:;" onclick="close_notice_100();"></a><div class="mod_yeltip_bd">'+content+'</div></div>';
	   			 if(jq('.header_center_main').length){
	   				 jq('.header_center_main').before(html_head);
	   			 }
        	}
        }
    });
}

//console.log();
function close_notice_100()
{
	  jq.ajax({
	        type: "GET",
	        url: 'notice.php',
	        dataType:'json',
	        data: {notice_close:1,type:100},
	        success:function(res){
	        	if(res){
	        		jq('#mod_yeltip').hide("slow");
	        	}
	        }
	    });
}





