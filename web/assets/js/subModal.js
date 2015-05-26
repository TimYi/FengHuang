/**
 * SUBMODAL v1.6
 * Used for displaying DHTML only popups instead of using buggy modal windows.
 *
 * By Subimage LLC
 * http://www.subimage.com
 *
 * Contributions by:
 * 	Eric Angel - tab index code
 * 	Scott - hiding/showing selects for IE users
 *	Todd Huss - inserting modal dynamically and anchor classes
 *
 * Up to date code can be found at http://submodal.googlecode.com
 */

define = window.define ? window.define : function( name , callback ){
	callback = callback || name;
	callback( function(){} , window , window );
};

define( function( require , exports , module ){
	var jQuery = window.jQuery || require( 'jquery' );
	//document.domain = 'to8to.com';
	var gPopupMask = null;
	var gPopupContainer = null;
	var gPopFrame = null;
	var gReturnFunc;
	var gPopupIsShown = false;
	// var gDefaultPage = "/templates/start/popuploading.html";
	var gDefaultPage = "http://www.to8to.com/gb_js/popuploading.html";
	var gHideSelects = false;
	var gReturnVal = null;

	var gTabIndexes = new Array();
	// Pre-defined list of tags we want to disable/enable tabbing into
	var gTabbableTags = new Array("A","BUTTON","TEXTAREA","INPUT","IFRAME");
	var subModal_parent=false;

	// If using Mozilla or Firefox, use Tab-key trap.
	if (!document.all) {
		document.onkeypress = keyDownHandler;
	}

	/**
	 * Initializes popup code on load.
	 */
	function initPopUp() {
		// Add the HTML to the body
		theBody = document.getElementsByTagName('BODY')[0];
		popmask = document.createElement('div');
		popmask.id = 'popupMask';
		popcont = document.createElement('div');
		popcont.id = 'popupContainer';
		popcont.innerHTML = '' +
			'<div id="popupInner">' +
				'<div id="popupTitleBar">' +
					'<div id="popupTitle"></div>' +
					'<div id="popupControls">' +
						'<span onclick="hidePopWin(true);" id="popCloseBox" style="background:url(http://img.to8to.com/front_end/bg/new_yz_jl_bg.png) no-repeat 0 -297px;width:32px;height:32px;display:block;"></span> ' +
					'</div>' +
				'</div>' +
				'<iframe src="'+ gDefaultPage +'" style="width:100%;height:100%;background-color:transparent;" scrolling="auto" frameborder="0" allowtransparency="true" id="popupFrame" name="popupFrame" width="100%" height="100%"></iframe>' +
			'</div>';
		theBody.appendChild(popmask);
		theBody.appendChild(popcont);

		gPopupMask = document.getElementById("popupMask");
		gPopupContainer = document.getElementById("popupContainer");
		gPopFrame = document.getElementById("popupFrame");

		// check to see if this is IE version 6 or lower. hide select boxes if so
		// maybe they'll fix this in version 7?
		var brsVersion = parseInt(window.navigator.appVersion.charAt(0), 10);
		if (brsVersion <= 6 && window.navigator.userAgent.indexOf("MSIE") > -1) {
			gHideSelects = true;
		}

		// Add onclick handlers to 'a' elements of class submodal or submodal-width-height
		var elms = document.getElementsByTagName('a');
		for (i = 0; i < elms.length; i++) {
			if (elms[i].className.indexOf("submodal") == 0) {
				// var onclick = 'function (){showPopWin(\''+elms[i].href+'\','+width+', '+height+', null);return false;};';
				// elms[i].onclick = eval(onclick);
				elms[i].onclick = function(){
					// default width and height
					var width = 400;
					var height = 200;
					// Parse out optional width and height from className
					params = this.className.split('-');
					if (params.length == 3) {
						width = parseInt(params[1]);
						height = parseInt(params[2]);
					}
					showPopWin(this.href,width,height,null); return false;
				}
			}
		}
	}
	//addEvent(window, "load", initPopUp);
	jQuery(function() {
		initPopUp();
	});
	// window.seajs && initPopUp();

	 /**
		* @argument width - int in pixels
		* @argument height - int in pixels
		* @argument url - url to display
		* @argument returnFunc - function to call when returning true from the window.
		* @argument showCloseBox - show the close box - default true
		*/
	function showPopWin(url, width, height, returnFunc, showCloseBox) {
		if(!document.getElementById('popupContainer')){
			initPopUp();
		}
		// show or hide the window close widget
		if (showCloseBox == null || showCloseBox == true) {
			document.getElementById("popCloseBox").style.display = "block";
		} else {
			document.getElementById("popCloseBox").style.display = "none";
		}

		if(url.search('pop_login.php')>0){
			width=627;
			height=388;
		}
		if(url.search('reg_new.php')>0){
			width=630;
			height=560;
		}

		gPopupIsShown = true;
		disableTabIndexes();
		gPopupMask.style.display = "block";
		gPopupContainer.style.display = "block";
		// calculate where to place the window on screen
		centerPopWin(width, height);

		var titleBarHeight = parseInt(document.getElementById("popupTitleBar").offsetHeight, 10);


		gPopupContainer.style.width = width + "px";
		gPopupContainer.style.height = (height+titleBarHeight) + "px";

		setMaskSize();

		// need to set the width of the iframe to the title bar width because of the dropshadow
		// some oddness was occuring and causing the frame to poke outside the border in IE6
		gPopFrame.style.width = parseInt(document.getElementById("popupTitleBar").offsetWidth, 10) + "px";
		gPopFrame.style.height = (height) + "px";

		// set the url
		gPopFrame.src = url;

		gReturnFunc = returnFunc;
		// for IE
		if (gHideSelects == true) {
			hideSelectBoxes();
		}

		window.setTimeout("setPopTitle();", 600);
	}

	//
	var gi = 0;
	function centerPopWin(width, height) {
		if (gPopupIsShown == true) {
			if (width == null || isNaN(width)) {
				width = gPopupContainer.offsetWidth;
			}
			if (height == null) {
				height = gPopupContainer.offsetHeight;
			}

			//var theBody = document.documentElement;
			var theBody = document.getElementsByTagName("BODY")[0];
			//theBody.style.overflow = "hidden";
			var scTop = parseInt(getScrollTop(),10);
			var scLeft = parseInt(theBody.scrollLeft,10);

			setMaskSize();

			//window.status = gPopupMask.style.top + " " + gPopupMask.style.left + " " + gi++;

			var titleBarHeight = parseInt(document.getElementById("popupTitleBar").offsetHeight, 10);

			var fullHeight = getViewportHeight();
			var fullWidth = getViewportWidth();

			gPopupContainer.style.top = (scTop + ((fullHeight - (height+titleBarHeight)) / 2)) + "px";
			gPopupContainer.style.left =  (scLeft + ((fullWidth - width) / 2)) + "px";
			//alert(fullWidth + " " + width + " " + gPopupContainer.style.left);
		}
	}
	addEvent(window, "resize", centerPopWin);
	addEvent(window, "scroll", centerPopWin);
	window.onscroll = centerPopWin;


	/**
	 * Sets the size of the popup mask.
	 *
	 */
	function setMaskSize() {
		var theBody = document.getElementsByTagName("BODY")[0];

		var fullHeight = getViewportHeight();
		var fullWidth = getViewportWidth();

		// Determine what's bigger, scrollHeight or fullHeight / width
		if (fullHeight > theBody.scrollHeight) {
			popHeight = fullHeight;
		} else {
			popHeight = theBody.scrollHeight;
		}

		if (fullWidth > theBody.scrollWidth) {
			popWidth = fullWidth;
		} else {
			popWidth = theBody.scrollWidth;
		}

		gPopupMask.style.height = popHeight + "px";
		gPopupMask.style.width = "100%";
	}

	/**
	 * @argument callReturnFunc - bool - determines if we call the return function specified
	 * @argument returnVal - anything - return value
	 */
	function hidePopWin(callReturnFunc) {
		gPopupIsShown = false;
		var theBody = document.getElementsByTagName("BODY")[0];
		theBody.style.overflow = "";
		restoreTabIndexes();
		if (gPopupMask == null) {
			return;
		}
		gPopupMask.style.display = "none";
		gPopupContainer.style.display = "none";
		if (callReturnFunc == true && gReturnFunc != null) {
			// Set the return code to run in a timeout.
			// Was having issues using with an Ajax.Request();
			try{
				gReturnVal = window.frames["popupFrame"].returnVal;
			}catch(err){

			}
			window.setTimeout(function(){
				gReturnFunc(gReturnVal);
			}, 30);
		}
		gPopFrame.src = gDefaultPage;
		// display all select boxes
		if (gHideSelects == true) {
			displaySelectBoxes();
		}

		if(subModal_parent){
			subModal_parent=false;
			parent.location.reload();
		}
	/***********************/
	try{
	//			//设置uid 身份
	//			//var username=getCookie('to8to_username',0);
	//			var r=new RegExp("(\\b)to8to_username=([^;]*)(;|$)");
	//			var m=document.cookie.match(r);
	//			var username=decodeURIComponent(m[2]);
	//			if(typeof(username)!='undefined'&&username!=""&&username!="deleted"&&typeof(username)!="null"){
	//				var str_havel='';
	//				//用户身份
	//				var ind=getCookie('to8to_ind',0);
	//				//用户id
	//				var uid=getCookie('to8to_uid',0);
	//				if($('user_data')){
	//			   str_havel='<ul style="display:none;top:20px;left:87px;" id="username_sublist"><li class="b_bttm"><a  href="http://www.to8to.com/my/" id="userbar-myinfo" class="">后台中心</a></li>';
	//				   if(ind=='yz'){
	//						str_havel+='<li class="b_bttm"><a  href="http://www.to8to.com/my/yz_administration_self.php?act=1" id="userbar-myinfo" class="">帐号设置</a></li>';
	//					}else if(ind=='zs'){
	//						str_havel+='<li class="b_bttm"><a  href="http://www.to8to.com/zs/'+uid+'" >公司主页</a></li>';
	//						str_havel+='<li class="b_bttm"><a  href="http://www.to8to.com/my/gs_data.php" id="userbar-myinfo" class="">帐号设置</a></li>';
	//					}
	//					str_havel+='<li><a id="userbar-logout" href="http://www.to8to.com/logout.php?uid='+uid+'" class="">退出</a></li></ul><a style="float:right;" href="http://www.to8to.com/my/">'+username+'</a><span class="user_img"><img src="http://pic.to8to.com/user/'+(uid%100)+'/headphoto_'+uid+'.jpg" width="20px" height="20px"/></span>&nbsp;&nbsp;&nbsp;';
	//					$('user_data').innerHTML=str_havel;
	//				}
	//			}


				//username    = getCookie('to8to_username');
				/*var r=new RegExp("(\\b)to8to_username=([^;]*)(;|$)");
				var m=document.cookie.match(r);
				var username=decodeURIComponent(m[2]);
				//用户id
				uid         = getCookie('to8to_uid');
				if(uid>0&&typeof(username)!='null'&&typeof(username)!='undefined'&&username!=""&&username!="deleted"){
				    jq.ajax({
				        'url'       : 'http://www.to8to.com/api/get_msg_number.php?uid=' + uid,
				        'dataType'  : 'jsonp',
				        'success'   : function(ret) {
				    		createUserNavLogined(2, ret.num);
				        }
				    });
				}*/
				//设置uid 身份
	    var username    = getCookie('to8to_username');

	    if(typeof(username)!='undefined'&&username!=""&&username!="deleted"){
	        createUserNav();
	    };

	    function createUserNav() {
	        var str_havel='';
	        //用户身份
	        var ind = getCookie('to8to_ind');
	        var uid = getCookie('to8to_uid');

	        str_havel='<div rel="nofollow" class="col_l htr_username_box"><a href="javascript:;" class="htr_username"><p class="ect">'+username+'</p><i class="triangle_down"></i></a><ul class="user_memu" style="display:none"><li><a href="http://www.to8to.com/my/">个人中心</a></li>';

	        if(ind=='yz'){
	            str_havel += '<li><a href="http://www.to8to.com/my/yz_administration_self.php?act=1" id="userbar-myinfo" class="">帐号设置</a></li>';
	        }else if(ind=='zs'){
	            str_havel += '<li><a href="http://www.to8to.com/zs/'+uid+'">公司主页</a></li>';
	            str_havel += '<li><a href="http://www.to8to.com/my/gs_data.php" >帐号设置</a></li>';
	        };
	        str_havel += '<li><a href="http://www.to8to.com/logout.php?uid='+uid+'">退出</a></li></ul></div>';

	        jQuery.ajax({
	            async:true,
	            type:"GET",
	            dataType: 'jsonp',
	            url:"http://www.to8to.com/api/get_message_count.php",
	            data:{ind:ind,uid:uid},
	            async: false,
	            success:function(data) {
	                if(data.status=="1") {
	                    str_havel += data.message;
	                }
	                var labelObj = jQuery('#nav_user_data');
	                labelObj.html(str_havel);

	                labelObj.children('div').hover(function() {
	                    jQuery(this).toggleClass('on');
	                    jQuery(this).children('ul').toggle();
	                    jQuery(this).children('a').find('i.triangle_down').toggleClass('triangle_up');
	                });
	            }
	        });
	    };

	}catch(e){}

	/****************************/

	}

	/**
	 * Sets the popup title based on the title of the html document it contains.
	 * Uses a timeout to keep checking until the title is valid.
	 */
	function setPopTitle() {
		return;
		if (window.frames["popupFrame"].document.title == null) {
			window.setTimeout("setPopTitle();", 10);
		} else {
			document.getElementById("popupTitle").innerHTML = window.frames["popupFrame"].document.title;
		}
	}

	// Tab key trap. iff popup is shown and key was [TAB], suppress it.
	// @argument e - event - keyboard event that caused this function to be called.
	function keyDownHandler(e) {
	    if (gPopupIsShown && e.keyCode == 9)  return false;
	}

	// For IE.  Go through predefined tags and disable tabbing into them.
	function disableTabIndexes() {
		if (document.all) {
			var i = 0;
			for (var j = 0; j < gTabbableTags.length; j++) {
				var tagElements = document.getElementsByTagName(gTabbableTags[j]);
				for (var k = 0 ; k < tagElements.length; k++) {
					gTabIndexes[i] = tagElements[k].tabIndex;
					tagElements[k].tabIndex="-1";
					i++;
				}
			}
		}
	}

	// For IE. Restore tab-indexes.
	function restoreTabIndexes() {
		if (document.all) {
			var i = 0;
			for (var j = 0; j < gTabbableTags.length; j++) {
				var tagElements = document.getElementsByTagName(gTabbableTags[j]);
				for (var k = 0 ; k < tagElements.length; k++) {
					tagElements[k].tabIndex = gTabIndexes[i];
					tagElements[k].tabEnabled = true;
					i++;
				}
			}
		}
	}


	/**
	 * Hides all drop down form select boxes on the screen so they do not appear above the mask layer.
	 * IE has a problem with wanted select form tags to always be the topmost z-index or layer
	 *
	 * Thanks for the code Scott!
	 */
	function hideSelectBoxes() {
	  var x = document.getElementsByTagName("SELECT");

	  for (i=0;x && i < x.length; i++) {
	    x[i].style.visibility = "hidden";
	  }
	}

	/**
	 * Makes all drop down form select boxes on the screen visible so they do not
	 * reappear after the dialog is closed.
	 *
	 * IE has a problem with wanting select form tags to always be the
	 * topmost z-index or layer.
	 */
	function displaySelectBoxes() {
	  var x = document.getElementsByTagName("SELECT");

	  for (i=0;x && i < x.length; i++){
	    x[i].style.visibility = "visible";
	  }
	}
	function addEvent(obj, evType, fn){
	 if (obj.addEventListener){
	    obj.addEventListener(evType, fn, false);
	    return true;
	 } else if (obj.attachEvent){
	    var r = obj.attachEvent("on"+evType, fn);
	    return r;
	 } else {
	    return false;
	 }
	}
	function removeEvent(obj, evType, fn, useCapture){
	  if (obj.removeEventListener){
	    obj.removeEventListener(evType, fn, useCapture);
	    return true;
	  } else if (obj.detachEvent){
	    var r = obj.detachEvent("on"+evType, fn);
	    return r;
	  } else {
	    alert("Handler could not be removed");
	  }
	}
	function getViewportHeight() {
		if (window.innerHeight!=window.undefined) return window.innerHeight;
		if (document.compatMode=='CSS1Compat') return document.documentElement.clientHeight;
		if (document.body) return document.body.clientHeight;

		return window.undefined;
	}
	function getViewportWidth() {
		var offset = 17;
		var width = null;
		if (window.innerWidth!=window.undefined) return window.innerWidth;
		if (document.compatMode=='CSS1Compat') return document.documentElement.clientWidth;
		if (document.body) return document.body.clientWidth;
	}

	function getScrollTop() {
		if (self.pageYOffset)
		{
			return self.pageYOffset;
		}
		else if (document.documentElement && document.documentElement.scrollTop)
		{
			return document.documentElement.scrollTop;
		}
		else if (document.body)
		{
			return document.body.scrollTop;
		}
	}
	function getScrollLeft() {
		if (self.pageXOffset)
		{
			return self.pageXOffset;
		}
		else if (document.documentElement && document.documentElement.scrollLeft)
		{
			return document.documentElement.scrollLeft;
		}
		else if (document.body)
		{
			return document.body.scrollLeft;
		}
	}

	function createUserNavLogined(msg_type, num) {
		var r=new RegExp("(\\b)to8to_username=([^;]*)(;|$)");
		var m=document.cookie.match(r);
		var username=decodeURIComponent(m[2]);
	    var str_havel='';
	    msg_type    = msg_type  || 1;
	    num         = num       || 0;

	    var hidestr = 'style="display:none"';

	    //用户身份
	    ind = getCookie('to8to_ind');
		var uid = getCookie('to8to_uid');
	    if($('nav_user_data')){
	        str_havel='<div class="htr_username" rel="nofollow"><p>'+username+'</p><i class="down_arrow"></i><div class="user_memu"> <dl> <dt>'
	                   + "<a href=\"http://www.to8to.com/my/\" class=\"menu_username\"><p>"+username
	                   +"</p><i class=\"up_arrow\"></i></a></dt><dd> <a href=\"http://www.to8to.com/my/\" >个人中心</a>";
	        if(ind=='yz'){
	            str_havel+='<a href="http://www.to8to.com/my/yz_administration_self.php?act=1" id="userbar-myinfo" class="">帐号设置</a>';
	        }else if(ind=='zs'){
	            str_havel+='<a href="http://www.to8to.com/zs/'+uid+'" >公司主页</a>';
	            str_havel+='<a href="http://www.to8to.com/my/gs_data.php" >帐号设置</a>';
	        }
	        str_havel+= "<a href=\"http://www.to8to.com/logout.php?uid="+uid+"\">退出</a></dd></dl></div></div>";
			var message = '';
			jQuery.ajax({
				async:true,
				type:"GET",
				dataType: 'jsonp',
				url:"http://www.to8to.com/api/get_message_count.php",
				data:{ind:ind,uid:uid},
				async: false,
				success:function(data){
					if(data.status=="1"){
						message = data.message;
					}

				}
			});
			str_havel+=message;
	        jQuery('#nav_user_data').html(str_havel);
	        jQuery('.header_top_right > ul > li div.htr_username').bind("mouseenter", function(){
	                jQuery(this).find('div.user_memu').show();
	        });
	        jQuery('.header_top_right > ul > li div.htr_username > div.user_memu').bind("mouseleave", function(){
	                jQuery(this).hide();
	        });
	    }
	}


	exports.showPopWin = window.showPopWin = showPopWin;
	exports.hidePopWin = window.hidePopWin = hidePopWin;
	exports.createUserNavLogined = window.createUserNavLogined = createUserNavLogined;
	exports.setPopTitle = window.setPopTitle = setPopTitle;

});
