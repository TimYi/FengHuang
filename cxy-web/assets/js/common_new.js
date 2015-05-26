// JavaScript Document
//console.log处理
if (typeof console == "undefined") {
    this.console = { log: function (msg) { alert(msg); } };
};

// common.js
var server_host = '/';
var rankhelp_doc = '/help/rankhelp.html';
var isIE = navigator.userAgent.indexOf("compatible") > -1 && navigator.userAgent.indexOf("MSIE") > -1 && (navigator.appName !== "Oprea");
var isIE7 = (isIE && window.XMLHttpRequest) ? true: false;
var isIE6 = (isIE && !window.XMLHttpRequest && window.ActiveXObject) ? true: false;
var isFirefox = navigator.userAgent.indexOf('Firefox') == -1 ? false: true;
var userAgent = navigator.userAgent.toLowerCase();
var is_opera = userAgent.indexOf('opera') != -1 && opera.version();
var is_moz = (navigator.product == 'Gecko') && userAgent.substr(userAgent.indexOf('firefox') + 8, 3);
var is_ie = (userAgent.indexOf('msie') != -1 && !is_opera) && userAgent.substr(userAgent.indexOf('msie') + 5, 3);
var isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");
var to8to_uid = getCookie('uid', 1);
var to8to_ind = getCookie('ind', 1);
var divTop,
divLeft,
divWidth,
divHeight,
docHeight,
docWidth,
objTimer,
secI;
if ((window.location.href.indexOf(".to8to.com") != -1))
 {
    server_host = "http://www.to8to.com/";
}
//查询数组大小
if (!Array.prototype.push) {
    Array.prototype.push = function() {
        var startLength = this.length;
        for (var i = 0; i < arguments.length; i++)
        this[startLength + i] = arguments[i];
        return this.length;
    }
}
//封装类
function $()
 {
    var obj = new Array();
    for (var i = 0, j = arguments.length; i < j; i++)
    {
        ele = arguments[i];
        if (typeof ele == 'object')
        return ele;
        if (typeof ele == 'string')
        ele = document.getElementById(ele) ? document.getElementById(ele) : document.getElementsByTagName(ele).length > 0 ? document.getElementsByTagName(ele) : false;
        if (j == 1)
        return ele;
        obj.push(ele);
    }
    return obj;
}
//判断是否冒泡
function doane(event) {
    e = event ? event: window.event;
    if (is_ie) {
        e.returnValue = false;
        e.cancelBubble = true;
    } else if (e) {
        e.stopPropagation();
        e.preventDefault();
    }
}
function doane_but_a(event) {
    e = event ? event: window.event;
    if (is_ie) {
        e.cancelBubble = true;
    } else if (e) {
        e.stopPropagation();
    }
}
function addNodes(o, O, d)
 {
    if (!O)
    return;
    d = parseInt(d);
    if (d < 0)
    {
        o.appendChild(O);
    }
    else if (d == 0)
    {
        if (o.childNodes.length != 0)
        o.insertBefore(O, o.firstChild);
        else
        o.appendChild(O);
    }
    else
    {
        if (o.childNodes.length - 1 < d)
        o.appendChild(O);
        else
        o.insertBefore(O, o.childNodes[d]);
    }
}
Object.extend = function(oFrom, oTo)
 {
    for (property in oFrom)
    {
        oTo[property] = oFrom[property];
    };
    return oTo;
};
var Events = new Object();
Events.addEvent = function(oTarget, sEventType, fnLister)
 {
    if (oTarget.addEventListener)
    {
        oTarget.addEventListener(sEventType, fnLister, false);
    }
    else if (oTarget.attachEvent)
    {
        oTarget.attachEvent("on" + sEventType, fnLister);
    }
    else
    {
        oTarget["on" + sEventType] = fnLister;
    };
};
Events.removeEvent = function(oTarget, sEventType, fnLister)
 {
    if (oTarget.removeEventListener)
    {
        oTarget.removeEventListener(sEventType, fnLister, false);
    }
    else if (oTarget.detachEvent)
    {
        oTarget.detachEvent("on" + sEventType, fnLister);
    }
    else
    {
        oTarget["on" + sEventType] = null;
    };
};
Events.formatEvent = function(oEvent){
    if (isIE && isWin){
        oEvent.charCode = (oEvent.type == "keypress") ? oEvent.keyCode: 0;
        oEvent.eventPhase = 2;
        oEvent.isChar = (oEvent.charCode > 0);
        oEvent.pageX = oEvent.cleintX + (document.body.scrollLeft || document.documentElement.scrollLeft);
        oEvent.pageY = oEvent.cleintY + (document.body.scrollTop || document.documentElement.scrollTop);
        oEvent.preventDefalt = function() {
            this.returnValue = false;
        };
        if (this.type == "mouseout")
        {
            oEvent.relatedTarget = oEvent.toElement;
        }
        else if (this.type == "mouseover")
        {
            oEvent.relatedTarget = oEvent.fromElement;
        };
        oEvent.target = oEvent.srcElement;
        oEvent.time = (new Date()).getTime();
    };
    return oEvent;
};
Events.getEvent = function()
 {
    if (window.event){
      return this.formatEvent(window.event);
    }else{
      return Event.getEvent.caller.arguments[0];
    };
};
function autoSize(obj, w, h)
 {
    var oIMG = new Image();
    oIMG.onload = function()
    {
        var oW = this.width;
        var oH = this.height;
        var tax = 1;
        if (oW > w || oH > h)
        tax = (oW / oH) > (w / h) ? (w / oW) : (h / oH);
        obj.style.marginLeft = (w - Math.floor(oW * tax)) / 2 + "px";
        obj.style.marginTop = (h - Math.floor(oH * tax)) / 2 + "px";
        obj.width = oW * tax;
        obj.height = oH * tax;
    };
    oIMG.src = obj.src;
};
//限定宽度，高度自适应
function autoSize_w(obj, w)
 {
    var oIMG = new Image();
    oIMG.onload = function()
    {
        var oW = this.width;
        if (oW > w)
        {
            obj.width = w;
            obj.height = Math.floor((this.height) * (w / this.width));

        };

    };
    oIMG.src = obj.src;

};
function autoSize_cut(obj, w, h) {
    var oIMG = new Image();
    oIMG.onload = function() {
        var oW = this.width;
        var oH = this.height;
        var tax = 1;
        if (oW > w || oH > h){
          tax = (oW / oH) < (w / h) ? (w / oW) : (h / oH);
        }
        /*obj.style.marginLeft=(w-Math.floor(oW*tax))/2+"px";
        obj.style.marginTop=(h-Math.floor(oH*tax))/2+"px";*/
        obj.style.marginTop = -(Math.floor(oH * tax) - h) / 2 + "px";
        obj.width = oW * tax;
        obj.height = oH * tax;

    };
    oIMG.src = obj.src;

};


function makeCode()
 {
    var color = Array("#069", "#966", "#639", "#F00", "#303", "#F00", "#B4FF00", "#369");
    var code = "";
    var out = "";
    for (var i = 0; i < 4; i++) {
        var str = Math.floor(Math.random() * 10);
        code += str;
        out += "<b style='color:" + color[Math.floor(Math.random() * 8 + 1)] + ";font-size:18px;'>" + str + "</b>&nbsp;";
    }
    $("checkcode").innerHTML = out;
    $("checkcode").style.backgroundColor = '#FFF';
    if (!document.all)
    $("checkcode").style.padding = "1px";
    $("checkcodevalue").value = code;
};
function newverifypic() {
    var A = new Date().getTime();
    if ($('passport')) {
        $('passport').src = 'http://www.to8to.com/passport.php?t=' + A;
    };
};
String.prototype.trim = function()
 {
    var res = /^\s*/;
    var value = this;
    value = value.replace(res, '');
    res = /\s*$/;
    return value.replace(res, '');
};
function drag(o, m)
 {
    var x;
    var y;
    o.onmousedown = MouseDown;
    if (o.firstChild){
        o.firstChild.onmousedown = function() {
            return false;
        };
    };
    var oP = o.parentNode;
    var r = new Array();
    function MouseDown(evt)
    {
        var evt = evt ? evt: window.event;
        if (o.setCapture){
          o.setCapture();
        }else if(!isFirefox && window.captureEvents){
          window.captureEvents(evt.mousemove | evt.mouseup);
        };
        if (m)
        {
            r[0] = oP.layerLeft ? oP.layerLeft: oP.offsetLeft;
            r[1] = r[0] + oP.offsetWidth ? oP.offsetWidth: oP.layerWidth;
            r[2] = oP.layerTop ? oP.layerTop: oP.offsetTop;
            r[3] = r[2] + oP.offsetHeight ? oP.offsetHeight: oP.layerHeight;
        };
        x = evt.layerX ? evt.layerX: evt.offsetX;
        y = evt.layerY ? evt.layerY: evt.offsetY;
        document.onmousemove = MouseMove;
        document.onmouseup = MouseUp;
        stopEvent(evt);
        return false;
        function MouseMove(evt)
        {
            var evt = evt ? evt: window.event;
            var Tx = evt.pageX ? evt.pageX: evt.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft);
            var Ty = evt.pageY ? evt.pageY: evt.clientY + (document.documentElement.scrollTop || document.body.scrollTop);
            if (m)
            {
                Tx = Tx - r[0];
                Ty = Ty - r[2];
            };
            o.style.left = parseInt(Tx - x);
            o.style.top = parseInt(Ty - y);
            document.body.style.cursor = "move";
            stopEvent(evt);
            return false;
        };
        function MouseUp(evt)
        {
            evt = evt || window.event;
            if (o.releaseCapture)
            o.releaseCapture();
            else if (!isFirefox && window.releaseEvents)
            window.releaseEvents(evt.mousemove | evt.mouseup);
            document.onmousemove = null;
            document.onmouseup = null;
            document.body.style.cursor = "";
            stopEvent(evt);
            return false;
        };
        function stopEvent(evt)
        {
            if (evt.preventDefault)
            {
                evt.stopPropagation();
                evt.preventDefault();
            }
            else
            {
                evt.returnValue = false;
                evt.cancelBubble = true;
            };
        };
    };
};
function scroll2top(o) {
    var top = 0;
    if (typeof o == 'string')
    {
        var node = $(o);
    }
    else if (typeof o == 'object')
    {
        var node = o;
    }
    else if (typeof o == 'number')
    {
        top = o;
    }
    if (node) {
        top += node.offsetTop;
    }
    window.scrollTo(0, top);
};
function get_content(oEle, nMax)
 {
    var nNum = string_bytes(oEle.value);
    if (nNum > nMax)
    {
        var maxwords = getbybytes(oEle.value, nMax);
        oEle.value = oEle.value.substring(0, maxwords);
    }
};
function string_bytes(sStr)
 {
    if (typeof(sStr) != 'string')
    {
        sStr = sStr.value;
    }
    var nLen = 0;
    for (var i = 0; i < sStr.length; i++)
    {
        if (sStr.charCodeAt(i) > 127)
        {
            nLen++
        }
        nLen++
    }
    return nLen;
};
function getbybytes(sStr, nNum) {
    var sWords = 0;
    var nBytes = 0;
    for (var i = 0; i < sStr.length; i++) {
        if (nBytes < nNum - 1) {
            if (sStr.charCodeAt(i) > 127) {
                sWords++;
                nBytes = nBytes + 2;
            } else {
                sWords++;
                nBytes++;
            };
        } else if (nBytes == nNum - 1) {
            if (sStr.charCodeAt(i) > 127) return sWords;
            else {
                sWords++;
                nBytes++
            }
        } else{
            return sWords;
        };
    };
};

function pic_type(sUrl)
 {
    var sType = sUrl.substr(sUrl.lastIndexOf('.') + 1);
    var j = 0;
    var arr = new Array('jpg', 'gif', 'bmp', 'png', 'jpeg', 'pjpeg');
    for (var i = 0; i < arr.length; i++)
    {
        if (arr[i] != sType.toLowerCase()){
          j++;
        };
    };
    if (j == arr.length){
        return false;
    }else{
        return true;
    };
};

function setCookie(name, value, expire, pre)
 {
    if (!expire){
        expire = 5000;
    };
    
    if (pre){
        name = 'to8to_' + name;
    };
    
    var expiry = new Date();
    expiry.setTime(expiry.getTime() + expire);
    document.cookie = name + '=' + value + ';expires=' + expiry.toGMTString() + ';path=/;domain=.to8to.com';
};
function getCookie(name, pre)
 {
    if (pre)
    name = 'to8to_' + name;
    var r = new RegExp("(\\b)" + name + "=([^;]*)(;|$)");
    var m = document.cookie.match(r);
    var res = !m ? "": decodeURIComponent(m[2]);
    var result = stripscript(res);
    return result;

};

/****************XSS过滤********************/
function stripscript(s)
 {
    var pattern = new RegExp("[%--`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
    //格式 RegExp("[在中间定义特殊过滤字符]")
    var rs = "";
    for (var i = 0; i < s.length; i++) {
        rs = rs + s.substr(i, 1).replace(pattern, '');

    }
    return rs;

};

/**********************************************/
function check_point(sValue)
 {
    var re = /^[\s0-9a-zA-Z\u0391-\uFFE5]+$/gi;
    if (!re.test(sValue))
    return false;
    else
    return true;
};
function show_error(sIdName)
 {
    if (sIdName)
    var oObj = $(sIdName);
    oObj.style.display = "block";
};
function hide_error(sIdName)
 {
    if (sIdName)
    var oObj = $(sIdName);
    oObj.style.display = "none";
};
function show_cat_err(sStr, sIdName)
 {
    var oObj = $(sIdName);
    show_error(sIdName);
    oObj.innerHTML = sStr;
};
jsPage = function(iNums, iPrePage, iCurpage, fnCallBack, sInnerId)
 {
    _this = this;
    this.iNums = Math.ceil(iNums);
    this.iPrePage = Math.ceil(iPrePage);
    this.iCurPage = Math.ceil(iCurpage);
    this.fnCallBack = fnCallBack;
    this.sInnerId = sInnerId;
    this.sPageDivClass = 'pages';
    this.sPrevClass = 'prev';
    this.sNextClass = 'next';
    this.sFirstClass = 'first';
    this.sLastClass = 'last';
    if (this.iNums <= this.iPrePage)
    {
        return false;
    };
    this.setPageDivClass = function(css)
    {
        this.sPageDivClass = css;
    };
    this.setPrevClass = function(css)
    {
        this.sPrevClass = css;
    };
    this.setNextClass = function(css)
    {
        this.sNextClass = css;
    };
    this.multi = function(i)
    {
        if (i)
        this.iCurPage = Math.ceil(i);
        var sHtmlPage = '';
        if (this.iNums < this.iPrePage)
        sHtmlPage = '';
        else
        {
            var iPages = Math.ceil(this.iNums / this.iPrePage);
            if (!this.iCurPage || this.iCurPage < 1)
            this.iCurPage = 1;
            if (this.iCurPage > iPages)
            this.iCurPage = iPages;
            var iFrom = 1;
            var iTo = 1;
            if (iPages < 10)
            {
                iFrom = 1;
                iTo = iPages;
            }
            else
            {
                iFrom = this.iCurPage - 4;
                iTo = iFrom + 10 - 1;
                if (iFrom < 1)
                {
                    iTo = this.iCurPage - iFrom + 1;
                    iFrom = 1;
                    if (iTo - iFrom < 10)
                    iTo = 10;
                }
                else if (iTo > iPages)
                {
                    iFrom = iPages - 10 + 1;
                    iTo = iPages;
                }
            };
            sHtmlPage = this.iCurPage - 4 > 1 && iPages > 10 ? '<a href="#" class="' + this.sFirstClass + '" onclick="_this.fnCallBack(1);_this.multi(1);return false;">1 ...</a>': '';
            sHtmlPage += this.iCurPage > 1 ? '<a href="void(0)" class="' + this.sPrevClass + '" onclick="_this.fnCallBack(' + (this.iCurPage - 1) + ');_this.multi(' + (this.iCurPage - 1) + ');return false;">&lsaquo;&lsaquo;</a>': '';
            for (var i = iFrom; i <= iTo; i++)
            {
                sHtmlPage += i == this.iCurPage ? '<strong>' + i + '</strong>': '<a href="#" onclick="_this.fnCallBack(' + i + ');_this.multi(' + i + ');return false;">' + i + '</a>';
            };
            sHtmlPage += this.iCurPage < iPages ? '<a href="#" class="' + this.sNextClass + '" onclick="_this.fnCallBack(' + (this.iCurPage + 1) + ');_this.multi(' + (this.iCurPage + 1) + ');return false;">&rsaquo;&rsaquo;</a>': '';
            sHtmlPage += iTo < iPages ? '<a href="#" class="' + this.sLastClass + '" onclick="_this.fnCallBack(' + iPages + ');_this.multi(' + iPages + ');return false;">... ' + iPages + '</a>': '';
            sHtmlPage = sHtmlPage ? '<div class="' + this.sPageDivClass + '"><em>&nbsp;' + this.iNums + '&nbsp;</em>' + sHtmlPage + '</div>': '';
        };
        if (this.sInnerId && document.getElementById(sInnerId))
        document.getElementById(sInnerId).innerHTML = sHtmlPage;
        else
        return sHtmlPage;
    };
};
function jsSelectItem(arr, itemValue, mod, selectName, attribute, echo, defaultValue)
 {
    if (!attribute)
    attribute = '';
    var js = '<select id="' + selectName + '" name="' + selectName + '" ' + attribute + '>';
    if (defaultValue)
    js += '<option>' + defaultValue + '</option>';
    if (arr)
    {
        if ('K-V' == mod)
        {
            for (var i in arr)
            {
                if (typeof arr[i] == 'function')
                continue;
                js += '<option  value="' + (parseInt(i)) + '"';
                if (parseInt(i) == itemValue)
                {
                    js += 'selected="selected"';
                }
                js += '>' + arr[i] + '</option>';
            }
        }
        else if ('V-V' == mod)
        {
            for (var i = 0, j = arr.length; i < j; i++)
            {
                js += '<option  value="' + arr[i] + '"';
                if (arr[i] == itemValue)
                {
                    js += 'selected="selected"';
                }
                if (selectName == "User_Shen")
                js += '>' + GP_EN[i] + '</option>';
                else
                js += '>' + arr[i] + '</option>';
            }
        }
    }
    js += '</select>';
    if (echo)
    document.write(js);
    else
    return js;
};
function in_array(value, arr)
 {
    if (!arr || arr.length == 0)
    return false;
    var flag = false;
    for (var i = 0, j = arr.length; i < j; i++)
    {
        if (arr[i] == value)
        flag = true;
    }
    return flag;
};
function middle(o)
 {
    if (!o)
    return false;
    o = $(o);
    o.style.position = 'absolute';
    if (o.offsetWidth == 0)
    o.offsetWidth = parseInt(o.style.width);
    if (o.offsetHeight == 0)
    o.offsetHeight = parseInt(o.style.height);
    var sClientWidth = document.body.clientWidth || document.documentElement.clientWidth;
    var sClientHeight = window.screen.height;
    var iLeft = (document.body.clientWidth / 2) - (o.offsetWidth / 2);
    var sScrollTop = document.body.scrollTop || document.documentElement.scrollTop;
    var iTop = -80 + (sClientHeight / 2 + sScrollTop) - (o.offsetHeight / 2);
    iTop = iTop > 0 ? iTop: (sClientHeight / 2 + sScrollTop) - (oDialog.offsetHeight / 2);
    o.style.left = iLeft + 'px';
    o.style.top = iTop + 'px';
};
function insertScript(id, url) {
    var oScript = $(id);
    if (oScript)
    oScript.parentNode.removeChild(oScript);
    oScript = document.createElement('script');
    oScript.setAttribute('id', id);
    oScript.setAttribute('src', url);
    oScript.setAttribute('type', 'text/javascript');
    oScript.setAttribute('language', 'javascript');
    var header = $('head').item(0);
    header.appendChild(oScript);

};
function jsLoader()
 {
    this.load = function(f)
    {
        var oTags = document.getElementsByTagName('script');
        for (i = oTags.length - 1; i >= 0; i--)
        {
            var src = oTags[i].src;
            if (src && src.indexOf(f) > -1)
            {
                this.onsuccess();
                return;
            };
        };
        var s = document.createElement('script');
        var header = document.getElementsByTagName('head').item(0);
        s.setAttribute('src', f);
        s.setAttribute('type', 'text/javascript');
        s.setAttribute('language', 'javascript');
        header.appendChild(s);
        var _self = this;
        s.onload = s.onreadystatechange = function()
        {
            if (this.readyState && this.readyState == "loading")
            return;
            _self.onsuccess();
        };
        s.onerror = function()
        {
            header.removeChild(s);
            _self.onfailure();
        };
    };
    this.onfailure = function() {};
    this.onsuccess = function() {};
};

function zoompic(obj, zimg)
 {
    var oJsLoader = new jsLoader();
    oJsLoader.onsuccess = function() {
        zoom(obj, zimg)
    };
    oJsLoader.load('http://www.to8to.com/gb_js/zoom.js');
};
function zoompic2(obj, zimg)
 {
    var oJsLoader = new jsLoader();
    oJsLoader.onsuccess = function() {
        zoom(obj, zimg);
    };
    oJsLoader.load('http://www.to8to.com/gb_js/zoom2.js');
};

function addFriends(u, f, t, w, h)
 {
    var oJsLoader = new jsLoader();
    var uid = getCookie('uid', 1);
    if (!uid)
    {
        oJsLoader.onsuccess = function() {
            editPhotoCat('/pop_login.php', '登陆', 360, 250);
        };
        oJsLoader.load('/gb_js/popup.js');
        return false;
    }
    u = uid;
    var url = '/friend_modify.php?uid=' + u + '&type=' + encodeURIComponent(t);
    if (f)
    url = url.concat('&fid=').concat(f);
    oJsLoader.onsuccess = function() {
        editPhotoCat(url, t, w, h);
    };
    oJsLoader.load('/gb_js/popup.js');
};
function SendMsg(act, toid)
 {
    var openurl = 'http://bbs.to8to.com/home.php?mod=spacecp&ac=pm&op=showmsg&handlekey=showmsg_' + toid + '&touid=' + toid + '&daterange=2&msg_from_to8to=2&inajax=1';
    var uid = getCookie('uid', true);
    if (!uid) {
        openurl = '/pop_login.php';
    };
    var sToid = "";
    if (toid) sToid = "&toid=" + toid;
    var sAct = "";
    sAct = act + sToid;
    var oJsLoader = new jsLoader();
    oJsLoader.onsuccess = function() {
        editPhotoCat(openurl, '发短消息', 400, 480);
    };
    oJsLoader.load('http://www.to8to.com/gb_js/popup.js');
    return false;
};
function showSingleLogin(n)
 {
    var goUrl = 'http://www.to8to.com/pop_login.php';
    if (n && parseInt(n)) 
    goUrl = 'http://www.to8to.com/pop_login.php?id=' + parseInt(n);
    var oJsLoader = new jsLoader();
    oJsLoader.onsuccess = function() {
        editPhotoCat(goUrl, '登陆', 360, 250);
    };
    oJsLoader.load('http://www.to8to.com/gb_js/popup.js');
    return false;
};

function noLogin_button()
 {
    var uid = getCookie('uid', true);
    if (!uid)
    return showSingleLogin();
    return true;
};
function isDigit(num)
 {
    var regs = /^\d+$/;
    if (regs.test(num)) {
        return true;
    }
    else
    {
        return false;
    };
};
function getRadioValue(name) {
    var radioes = document.getElementsByName(name);
    for (var i = 0; i < radioes.length; i++)
    {
        if (radioes[i].checked) {
            return radioes[i].value;
        };
    };
    return false;
};

function Upload_clear(id, i)
 {
    var up = (typeof id == "string") ? document.getElementById(id) : id;
    if (typeof up != "object") return null;
    var tt = document.createElement("span");
    tt.id = "__tt__";
    up.parentNode.insertBefore(tt, up);
    var tf = document.createElement("form");
    tf.appendChild(up);
    document.getElementsByTagName("body")[0].appendChild(tf);
    tf.reset();
    tt.parentNode.insertBefore(up, tt);
    tt.parentNode.removeChild(tt);
    tt = null;
    tf.parentNode.removeChild(tf);
    if ($("view_del_" + i))
        $("view_del_" + i).style.display = 'none';
    if ($("view_text_" + i))
        $("view_text_" + i).style.display = 'none';
    if ($("view_textarea_" + i))
        $("view_textarea_" + i).style.display = 'none';
    if ($("view_message_" + i))
        $("view_message_" + i).innerHTML = '';
    if ($("pic" + i))
        $('pic' + i).style.display = 'none';
    if ($("view_img_" + i))
        $("view_img_" + i).style.display = 'none';
    if (isIE6 || isIE7) {
        if ($("p_view_img_" + i)) {
            $("p_view_img_" + i).style.display = 'block';
            $("p_view_img_" + i).src = '/img/css/view.gif';
        };
    }else{
        if ($("p_view_img_" + i))
           $("p_view_img_" + i).style.display = 'none';
    };
};
function checkImageFileNone(i)
 {
    if ($("view_img_" + i)) $("view_img_" + i).style.display = 'none';
    if ($("p_view_img_" + i)) {
        $("p_view_img_" + i).style.display = 'block';
        $("p_view_img_" + i).src = '/img/css/view.gif';
    };
    $("pic" + i).style.display = 'none';
};
function checkImageFile(i, obj, w, h, z)
 {
    $("view_message_" + i).innerHTML = '';
    $("view_del_" + i).style.display = 'block';
    checkImageFileNone(i);
    var dFile = $(obj.id);
    if ($('view_img_' + i)) {
        $('view_img_' + i).style.display = 'block';
        var dImg = $('view_img_' + i);
    };
    if ($('p_view_img_' + i))
        var dImg = $('p_view_img_' + i);
    if (!dFile.value.match(/.jpg|.gif|.png|.bmp/i))
    {
        $("view_message_" + i).innerHTML = '抱歉图片格式错误,请阅读上传说明';
        checkImageFileNone(i);
        return false;
    };
    if (dFile.files) {
        dImg.src = dFile.files[0].getAsDataURL();
    }
    else
    {
        if (isIE6) {
            var img = new Image();
            img.onload = function()
            {
                var size = Math.round(this.fileSize / 1024);
                if (size > (z * 1024))
                {
                    $("view_message_" + i).innerHTML = '图片<span style="color:#00F">' + size + 'KB</span>,超过最大允许限制';
                    checkImageFileNone(i);
                    return false;
                };
                if (img.height < h && img.width < w)
                {
                    $("view_message_" + i).innerHTML = '图片宽高不能小于<span style="color:#00F;float:none;">' + w + '*' + h + '</span>,请重新上传！';
                    checkImageFileNone(i);
                };
                if ($('view_img_' + i)) {
                    $('view_img_' + i).style.display = 'block';
                    $('view_img_' + i).firstChild.src = obj.value;
                };
                if ($('p_view_img_' + i)) $('p_view_img_' + i).src = obj.value;
                $("pic" + i).style.display = 'none';
                dImg.style.display = 'block';
            };
            img.src = dFile.value;
        };
        if (isIE7 || (img.height == 30 && img.width == 28) || (img.height == 0 && img.width == 0))
        {
            var newPreview = $('pic' + i);
            newPreview.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dFile.value;
            $("pic" + i).style.display = 'block';
            dImg.style.display = 'none';
        };
        if (isIE7)
        {
            $('img_hidden' + i).filters.item("DXImageTransform.Microsoft.AlphaImageLoader").sizingMethod = 'image';
            $('img_hidden' + i).style.minHeight = h + 'px';
            $('img_hidden' + i).style.minWidth = w + 'px';
            try
            {
                $('img_hidden' + i).filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dFile.value;
                imgwidth = $('img_hidden' + i).offsetWidth;
                imgheight = $('img_hidden' + i).offsetHeight;
                if (imgheight < h && imgwidth < w)
                {
                    $("view_message_" + i).innerHTML = '图片宽高不能小于<span style="color:#00F;float:none;">' + w + '*' + h + '</span>,请重新上传！';
                    checkImageFileNone(i);
                    return false;
                };
            } catch(e) {
                alert('无效的图片文件。');
                w = 0;
                h = 0;
                return;
            };
        };
    };
    if ($('view_text_' + i))
        $('view_text_' + i).style.display = 'block';
    if ($('view_textarea_' + i))
    {
        $('view_textarea_' + i).style.display = 'block';
        if ($('view_textarea_' + i).getElementsByTagName('textarea')[0])
        {
            $('view_textarea_' + i).getElementsByTagName('textarea')[0].focus();
            $('view_textarea_' + i).getElementsByTagName('textarea')[0].onblur = function() {
                if (!$('view_textarea_' + i).getElementsByTagName('textarea')[0].value) $('view_textarea_' + i).style.display = 'none';
            };
        };
    };
};
function DelHtml(str)
 {
    str = str.trim();
    str = str.replace(/<\/?[^>]*>/g, '');
    str = str.replace(/<iframe/g, '');
    return (str);
};
function greet_text() {
    var text = "";
    var dt = new Date();
    var hours = dt.getHours();
    var username = getCookie('username', 1);
    if (typeof(username) == 'undefined' || username == "") username = '欢迎来到土巴兔！';
    if (hours <= 7) text = "早上好";
    else if (hours > 7 && hours <= 11) text = "上午好";
    else if (hours > 11 && hours <= 13) text = "中午好";
    else if (hours > 13 && hours <= 19) text = "下午好";
    else if (hours > 19 && hours <= 23) text = "晚上好";
    var uid = getCookie('uid', 1);
    if (uid) $("xb_greet").innerHTML = username;
    else $("xb_greet").innerHTML = text + "，" + username
};
function to8toyx()
 {
    url = window.location.href;
    if (null == url || url.indexOf("?") == -1)
    {
        return null;
    };
    var argsUrl = url.split("?")[1];
    if (argsUrl.indexOf("=") == -1)
    {
        return null;
    };
    if (argsUrl.indexOf("welcome=") != -1)
    {
        href = "http://www.to8to.com/getuserdata.php?pos=to8toyx&" + argsUrl;
        href += '&s=' + Math.random(5);
        insertScript('sInsertScript', href);
    }
    else
    {
        return null;
    };
};

function uicheck() {
    var fullpath = "";
    username = getCookie('username', 1) ;
    if ((window.location.href.indexOf(".to8to.com") != -1)) {
        fullpath = "http://www.to8to.com";
    };
    if (typeof(username) != 'undefined' && username != "" && username != "deleted" && username != null) {
        ind = getCookie('ind', 1);
        uid = getCookie('uid', 1);
        mysite = fullpath + '/my/';
        if ($('loginchg')) 
            $('loginchg').innerHTML = '您好，<a href="' + mysite + '" target="_blank">' + username + '</a>　[<a href="' + mysite + '" target="_blank" class="f60">我的管理中心</a>] [<a href="' + fullpath + '/logout.php?uid=' + uid + '" target="_self">安全退出</a>]';
    }
    if (document.referrer != "" && document.referrer.indexOf("to8to.com") == -1) {
        if (window.location.href.indexOf("to8to.com") != -1) {
            smallwindow();
            getMsg();
        };
    };
    to8toyx();
};

function tyb_uicheck() {
    username = getCookie('username', 1);
     if (typeof(username) != 'undefined' && username != "" && username != "deleted") {
        ind = getCookie('ind', 1);
        uid = getCookie('uid', 1);
        mysite = '/my/';
        if ($('loginchg'))
            $('loginchg').innerHTML = '您好,<a href="' + mysite + '" target="_blank">' + username + '</a>|<a href="' + mysite + '" target="_blank" class="f30">我的管理中心</a>|<a href="/logout.php?uid=' + uid + '" target="_self">安全退出</a>';
    };
};
function smallwindow() {};
function blogcheck() {
    username = getCookie('username', 1);
     if (typeof(username) != 'undefined' && username != "" && username != "deleted" && username != null) {
        ind = getCookie('ind', 1);
        mysite = "/" + ind + "/" + to8to_uid + '/';
        var In_jiaoyi_center = false;
        if (typeof(jiaoyi_center) != 'undefined' && jiaoyi_center == 1) In_jiaoyi_center = true;
        var loginhtml = '';
        if (whoid != to8to_uid || In_jiaoyi_center) {
            loginhtml += '<a href="' + mysite + '" class="return">返回我的网站</a>';
        } else {
            loginhtml += '<a href="/my/" class="return">我的管理中心</a>';
        };
        if (whoid == to8to_uid) {
            if (ind == 'sj')
                loginhtml += '<code class="b"> | </code><a href="/' + ind + '/goods.php?act=upload_first&uid=' + to8to_uid + '" class="bg4" target="_blank">上传产品</a>';
            else if (!In_jiaoyi_center)
                loginhtml += '<code class="b"> | </code><a href="#" class="bg4" onclick="changeStyle();return false;">切换风格</a>';
            if (ind == 'yz')
                loginhtml += '<code class="b"> | </code><a href="/zb/weituo.php" class="bg4" target="_blank">发布招标</a>';
        };
        if ($('nav_act')) {
            $('nav_act').innerHTML = loginhtml;
            $('nav_login').innerHTML = '<code class="tui">[<a href="/logout.php" class="bg2">退出</a>]<span class="b">|</span></code>';
        };
    };
};
function getMsg() {
    try {
        secI = 0;
        divTop = parseInt(document.getElementById("eMeng").style.top, 10) ;
        divLeft = parseInt(document.getElementById("eMeng").style.left, 10);
        divHeight = parseInt(document.getElementById("eMeng").offsetHeight, 10);
        divWidth = parseInt(document.getElementById("eMeng").offsetWidth, 10);
        docWidth = document.documentElement.clientWidth;
        docHeight = document.documentElement.clientHeight;
        document.getElementById("eMeng").style.display = "block";
        document.getElementById("eMeng").style.top = parseInt(document.documentElement.scrollTop, 10) + docHeight + 10;
        document.getElementById("eMeng").style.left = parseInt(document.documentElement.scrollLeft, 10) + docWidth - divWidth;
        document.getElementById("eMeng").style.visibility = "visible";
        objTimer = window.setInterval("moveDiv()", 10)
    } catch (e) {};
};
function resizeDiv() {
    if (typeof(noclose) == 'undefined') {
        secI += 1;
        if (secI > 2500)
            closeDiv();
    };
    try {
        divHeight = parseInt(document.getElementById("eMeng").offsetHeight, 10);
        divWidth = parseInt(document.getElementById("eMeng").offsetWidth, 10);
        docWidth = document.documentElement.clientWidth;
        docHeight = document.documentElement.clientHeight;
        document.getElementById("eMeng").style.display = "block";
        document.getElementById("eMeng").style.top = docHeight - divHeight + parseInt(document.documentElement.scrollTop, 10);
        document.getElementById("eMeng").style.left = docWidth - divWidth + parseInt(document.documentElement.scrollLeft, 10)
    } catch (e) {};
};
function moveDiv() {
    try {
        if (parseInt(document.getElementById("eMeng").style.top, 10) <= (docHeight - divHeight + parseInt(document.documentElement.scrollTop, 10))) {
            window.clearInterval(objTimer) ;
            objTimer = window.setInterval("resizeDiv()", 1)
        };
        divTop = parseInt(document.getElementById("eMeng").style.top, 10) ;
        document.getElementById("eMeng").style.top = divTop - 1;
    } catch (e) {};
};
function closeDiv() {
    document.getElementById('eMeng').style.visibility = 'hidden';
    if (objTimer)
        window.clearInterval(objTimer);
};
function UpdateStaticInfo() {
    $('myrank').innerHTML = '等级：<a href=' + rankhelp_doc + ' title="目前等级' + creditsinfo.rank + '级,离下一次升级还差' + creditsinfo.nextrankdiff + '分" target="_blank" >' + creditsinfo.rankicon + '</a>';
    $('tj_info').innerHTML = '<li>日访问：<em>' + visitorinfo.d_vnum + '</em>人次</li><li>周访问：<em>' + visitorinfo.w_vnum + '</em>人次</li><li>总访问：<em>' + visitorinfo.vnum + '</em>人次</li>';
    if (issingle == 0) {
        $('tj_info').innerHTML += '<li>可使用积分：<em>' + creditsinfo.usablecredits + '</em>分</li>'
    };
    var l = visitmember.length > 9 ? 9 : visitmember.length;
    var fk_html = "";
    for (i = 0; i < l; i++) {
        if (visitmember[i].ind == 'yz') {
            homepage = 'http://bbs.to8to.com/space-uid-' + visitmember[i].vid + '.html';
        } else {
            homepage = "http://www.to8to.com/" + visitmember[i].ind + "/" + visitmember[i].vid + "/";
        }
        if (who_ind == 0)
            fk_html += '<li><a href="' + homepage + '" target="_blank" title="' + visitmember[i].nick + '" rel="nofollow">' + visitmember[i].nick + '</a><span class="visit_shijian">' + visitmember[i].time + '</span></li>';
        else
            fk_html += '<li><a href="' + homepage + '" target="_blank" title="' + visitmember[i].nick + '" rel="nofollow">' + visitmember[i].nick + '</a></li>';
    }
    $('fk_info').innerHTML = fk_html;
    newprompt = 0;
    try {
        newprompt = u_newprompt ? u_newprompt : 0;
    } catch (e) {};
    if (issingle == 0 && to8to_uid == whoid && (comm_messagenum > 0 || sys_messagenum > 0 || qiuzhu_messagenum > 0) && !newprompt) {
        $('mycds').innerHTML = comm_messagenum;
        pms = "";
        if (sys_messagenum > 0)
            pms = "<br>" + "<a href='#' onclick='closeDiv();SendMsg(8);return false;'>" + sys_messagenum + "条系统通知，点击查看" + "</a>";
        if (comm_messagenum > 0)
            pms += "<br>" + "<a href='#' onclick='closeDiv();SendMsg(1);return false;'>" + comm_messagenum + "条站内短信，点击查看" + "</a>";
        if (qiuzhu_messagenum > 0)
            pms = "<br>" + "<a href='#' onclick='closeDiv();SendMsg(9);return false;'>" + qiuzhu_messagenum + "条问吧求助信息，点击查看" + "</a>";
        if (pmsound == 1)
            soundhtml = '<a href="#"  onclick="opensound(0);return false;">关闭声音提醒</a>';
        else
            soundhtml = '<a href="#"  onclick="opensound(1);return false;">打开声音提醒</a>';
        $('messagebox').innerHTML = '<DIV id=eMeng style="width:222px;overflow:hidden;font-size:12px;z-index:999999;position:absolute;bottom:0;right:0;height:148px;background:url(/img/front_end/bg/index_bg.gif);text-align:center;display:none;"><p style="font-weight:bold;color:#30577e;padding:8px 0 0 8px;text-align:left;"> 消息提示：<span title=关闭 style="cursor:pointer;color:red;margin:0px 0px 0 0;float:right;position:absolute;right:5px;" onclick=closeDiv() >&#215;</span></p><div style="padding:38px 13px 13px;_width:100%;color:#1f336b;"><b style="background:url(/img/front_end/icon/icn_shortMessage.gif) no-repeat left;padding-left:22px;font-weight:normal">您有&nbsp;</b><font color=#FF0000><span id="new_message_num">' + pms + '</span><BR></div><span id="sound">' + soundhtml + '</span></DIV> ';
        if (pmsound == 1)
            $('bgmusic').src = "../../xinxitishi.wma";
        getMsg();
    };
};

function objoper(ind, obj)
 {
    var uid = getCookie('uid', true);
    if (!uid)
    {
        showSingleLogin();
        return false;
    }
    else
    {
        var to8to_ind = getCookie('ind', true);
        if (to8to_ind != ind)
        {
            var str_ind;
            switch (ind)
            {
            case 'sjs':
                str_ind = '设计师';
                break;
            case 'yz':
                str_ind = '业主';
                break;
            case 'zs':
                str_ind = '装饰公司';
                break;
            case 'sj':
                str_ind = '商家';
                break;
            }
            alert('对不起只有' + str_ind + '才能进行此项操作！');
            return false;
        }
        else
        {
            var href = obj.href;
            var temStr = 'uid=' + uid;
            if (href.indexOf('uid') > -1 && href.indexOf(temStr) == -1)
            {
                obj.href = href.replace('uid=', temStr);
            }
        };
    };
};
function copyToClipboard(txt, str) {
    if (window.clipboardData) {
        window.clipboardData.clearData();
        window.clipboardData.setData("Text", txt);
    } else if (navigator.userAgent.indexOf("Opera") != -1) {
        window.location = txt;
    } else if (window.netscape) {
        try {
            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
        } catch(e) {
            alert("你使用的FF浏览器,复制功能被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
        }
        var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
        if (!clip)
        return;
        var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
        if (!trans)
        return;
        trans.addDataFlavor('text/unicode');
        var str = new Object();
        var len = new Object();
        var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
        var copytext = txt;
        str.data = copytext;
        trans.setTransferData("text/unicode", str, copytext.length * 2);
        var clipid = Components.interfaces.nsIClipboard;
        if (!clip)
        return false;
        clip.setData(trans, null, clipid.kGlobalClipboard);
    }
    str = str ? str: '招聘地址';
    alert(str + "已经复制到粘贴板，您可以直接点粘贴发给您的好友！");
};
function SetHome(obj, url) {
    try {
        obj.style.behavior = 'url(#default#homepage)';
        obj.setHomePage(url);
    } catch(e) {
        if (window.netscape) {
            try {
                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
            } catch(e) {
                alert("抱歉，此操作被浏览器拒绝！\n\n请在浏览器地址栏输入“about:config”并回车然后将[signed.applets.codebase_principal_support]设置为'true'");
            };
        } else {
            alert("抱歉，您所使用的浏览器无法完成此操作。\n\n您需要手动将'" + url + "'设置为首页。");
        };
    };
};
function addfavorite(url, title)
 {
    try
    {
        window.external.addFavorite(url, title);
    }
    catch(e)
    {
        try
        {
            window.sidebar.addPanel(title, url, "");
        }
        catch(e)
        {
            alert("加入收藏失败，请使用Ctrl+D进行添加");
        }
    };
};
function pageKeyDown(e)
 {
    if (document.all) e = window.event;
    if (e.keyCode == 39)
    {
        if ($('nextpageid'))
        window.location = $('nextpageid').href;
        else
        {
            alert('已达到最后一页');
            return false;
        };
    }
    else if (e.keyCode == 37)
    {
        if ($('prepageid'))
        window.location = $('prepageid').href;
        else
        {
            alert('已达到第一页');
            return false;
        };
    };
};
Object.extend(Array.prototype, {
    shift: function() {
        var result = this[0];
        for (var i = 0; i < this.length - 1; i++)
        this[i] = this[i + 1];
        this.length--;
        return result;
    }
});
function mb_strlen(str)
 {
    var len = 0;
    for (var i = 0; i < str.length; i++) {
        len += str.charCodeAt(i) < 0 || str.charCodeAt(i) > 255 ? (charset == 'utf-8' ? 3: 2) : 1;
    };
    return len;
};

function goods_tg(placeid, oid)
 {
    var uid = getCookie('uid', 1);
    var ind = getCookie('ind', 1);
    if (uid)
    {
        if (ind == 'sj')
        {
            if (oid)
            {
                var oJsLoader = new jsLoader();
                oJsLoader.onsuccess = function() {
                    editPhotoCat('/goods_tg_win.php?placeid=' + placeid + '&oid=' + oid, '商品推广', 300, 100);
                };
                oJsLoader.load('/gb_js/popup.js');
            }
            else
            {
                var href = server_host + 'getuserdata.php?pos=sj_tg&placeid=' + placeid + '&s=' + Math.random(5);
                insertScript('sInsertScript_sj_tg', href);
            };
        }
        else
         alert('对不起，只有商家才能进行此项操作！');
    }
    else
     showSingleLogin();
    return false;
};
function slideLine(ul, delay, speed, lh, stepvalue) {
    var slideBox = (typeof ul == 'string') ? document.getElementById(ul) : ul;
    var slideBox2 = (typeof ul == 'string') ? document.getElementById(ul) : ul;
    for (var i = 0; i < slideBox2.childNodes.length; i++) {
        if (slideBox2.childNodes[i].nodeType == 1) {
            if (slideBox2.childNodes[i].tagName == "UL")
            slideBox2 = slideBox2.childNodes[i];
            break;
        }
    };
    var delay = delay || 1000,
    speed = speed || 0,
    lh = lh || 1,
    stepvalue = stepvalue || 2;
    var tid = null,
    pause = false;
    var start = function() {
        tid = setInterval(slide, speed);
    };
    function slide()
    {
        if (pause) return;
        slideBox.scrollTop += stepvalue;
        if (slideBox.scrollTop % lh == 0) {
            clearInterval(tid);
            slideBox2.appendChild(slideBox2.getElementsByTagName('li')[0]);
            slideBox.scrollTop = 0;
            setTimeout(start, delay);
        };
    };
    slideBox.onmouseover = function() {
        pause = true;
    };
    slideBox.onmouseout = function() {
        pause = false;
    };
    setTimeout(start, delay);
};
function getCookieVal(offset) {
    var endstr = document.cookie.indexOf(";", offset);
    if (endstr == -1) endstr = document.cookie.length;
    return unescape(document.cookie.substring(offset, endstr));
};
function get_historyCookie(name) {
    var arg = name + "=";
    var alen = arg.length;
    var clen = document.cookie.length;
    var i = 0;
    while (i < clen) {
        var j = i + alen;
        if (document.cookie.substring(i, j) == arg) return getCookieVal(j);
        i = document.cookie.indexOf(" ", i) + 1;
        if (i == 0) break;
    }
    return null;
};
function set_historyCookie(name, value, _T) {
    var exp = new Date();
    exp.setTime(exp.getTime() + 3600000000);
    var path = "/";
    var domain = ".to8to.com";
    if (_T) exp.setTime(exp.getTime() - 3600000000); {
        var curCookie = name + "=" + value + "; expires=" + exp.toGMTString() + "; path=" + path + "; domain=" + domain + ";";
        document.cookie = curCookie;
    };
};
function glog(evt) {
    try {
        while (evt)
        {
            wlink = evt + ",";
            old_info = get_historyCookie("history_info");
            var insert = true;
            if (old_info == null) {
                insert = true;
            }
            else {
                var old_link = old_info.split(",");
                for (var j = 0; j <= 10; j++)
                {
                    if (old_link[j] == "null")
                    break;
                };
            };
            if (insert) {
                wlink += get_historyCookie("history_info");
                var wlink = wlink.split(",");
                for (var i = 0; i < wlink.length; i++)
                {
                    for (var j = wlink.length - 1; j > i; j--)
                    {
                        if (wlink[j] == wlink[i])
                        {
                            wlink.splice(j, 1);
                        }
                    }
                };
                var wlinks = '';
                for (var k = 0; k < wlink.length; k++)
                {
                    if (k < 10)
                    {
                        if (wlink[k] != 'null')
                        {
                            if (wlinks == '')
                            wlinks = wlink[k] + ',';
                            else
                            wlinks = wlinks + wlink[k] + ',';
                        }
                    }
                };
                if (wlinks != '')
                wlink = wlinks + 'null';
                set_historyCookie("history_info", wlink);
                history_show().reload();
                break;
            };
            evt = evt.parentNode;
        };
    }
    catch(e) {};
    return true;
};
function clearHistoty() {
    wlink = null;
    set_historyCookie("history_info", wlink, 1);
    $("history").innerHTML = "暂无浏览纪录！";
    $("history").className = "now_none";
    $("clshistoty").style.display = "none";
};
function loadPng(o)
 {
    if (isIE6)
    {
        try {
            var img = o;
            var imgName = o.src.toUpperCase();
            if (imgName.substring(imgName.length - 3, imgName.length) == "PNG")
            {
                var imgID = (img.id) ? "id='" + img.id + "' ": "";
                var imgClass = (img.className) ? "class='" + img.className + "' ": "";
                var imgTitle = (img.title) ? "title='" + img.title + "' ": "title='" + img.alt + "' ";
                var imgStyle = "display:inline-block;" + img.style.cssText;
                if (img.align == "left") imgStyle = "float:left;" + imgStyle;
                if (img.align == "right") imgStyle = "float:right;" + imgStyle;
                if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle;
                var strNewHTML = "<span " + imgID + imgClass + imgTitle + " style=\"" + imgStyle + ";" + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader" + "(src=\'" + img.src + "\', sizingMethod='image');width:1px;\"></span>";
                img.outerHTML = strNewHTML;
            };
        }
        catch(e) {};
    };
};
function toNew(id, mod)
 {
    var strtname = '深圳';
    if (getCookie('townid', 1))
    {
        if (mod != 'my')
        {
            var strtname = decodeURIComponent(getCookie('tname', 1)).substring(0, 2);
            oload_online_zx(getCookie('townid', 1), strtname);
        };
    };
    if (mod == 'jc')
    {
        var tag = $('header').getElementsByTagName('h1');
        var oSpan = document.createElement('span');
        oSpan.innerHTML = '[' + strtname + ']';
        tag[0].appendChild(oSpan);
    };
};
function updateDiv_SC(id)
 {
    var oA = $('city' + id).parentNode.getElementsByTagName('a');
    for (var i = 0, j = oA.length; i < j; i++)
    {
        oA[i].className = '';
    };
    $('city' + id).className = 'on';
    var oJsLoader = new jsLoader();
    oJsLoader.load("http://www.to8to.com/getuserdata.php?pos=seltown&id=" + id + "&s=" + Math.random(5));
    return false;
};
function closeDiv_SC()
 {
    $("select_city").style.display = "none";
    if (isIE6)
    {
        var oSelect = document.getElementsByTagName('select');
        for (var i = 0, j = oSelect.length; i < j; i++)
        {
            oSelect[i].style.visibility = 'visible';
        };
    };
};
function load_SC(id)
 {
    if (!$('select_city'))
    {
        var obj = document.createElement('div');
        obj.id = 'select_city';
        obj.className = 'select_city';
        obj.innerHTML = '<div class="border"><a onclick="closeDiv_SC()" title="close" class="close"></a><p class="tit" id="cur_title">当前所在范围：</p>   <div class="citys"><p id="select_town"><span>请选择您所在的区域：</span></p ></div><div class="citys2"><p class="title">您还可以去以下城市的装修网：</p><p><a href="#" onclick="updateDiv_SC(102)" id="city102">南昌</a><a href="#" onclick="updateDiv_SC(103)" id="city103">景德镇</a><a href="#" onclick="updateDiv_SC(104)" id="city104">萍乡</a><a href="#" onclick="updateDiv_SC(105)" id="city105">新余</a><a href="#" onclick="updateDiv_SC(106)" id="city106">九江</a><a href="#" onclick="updateDiv_SC(107)" id="city107">鹰潭</a><a href="#" onclick="updateDiv_SC(108)" id="city108">赣州</a><a href="#" onclick="updateDiv_SC(109)" id="city109">吉安</a><a href="#" onclick="updateDiv_SC(110)" id="city110">宜春</a><a href="#" onclick="updateDiv_SC(111)" id="city111">抚州</a><a href="#" onclick="updateDiv_SC(112)" id="city112">上饶</a></p ></div></div>';
        addNodes(document.getElementsByTagName('body')[0], obj, -1);
    };
    $("select_city").style.display = "block";
    middle($("select_city"));
    if (isIE6)
    {
        var oSelect = document.getElementsByTagName('select');
        for (var i = 0, j = oSelect.length; i < j; i++)
        {
            oSelect[i].style.visibility = 'hidden';
        };
    };
    if (!id)
        id = getCookie('cityid', 1);
    updateDiv_SC(id);
};
function oload_online_zx(townid, strtname)
 {
    var aCity_zx = new Array({
        'uid': 131662,
        'cname': '黎川川之居装饰',
        'headphoto': 'lccj.gif',
        'qq': '77825230',
        'phone': '13607942485'
    },
    {
        'uid': 151285,
        'cname': '南丰华庭装饰',
        'headphoto': 'huating.jpg',
        'qq': '441066828',
        'phone': '13607942485'
    },
    {
        'uid': 128498,
        'cname': '宜春华宜装饰',
        'headphoto': 'huayi.jpg',
        'qq': '178901579',
        'phone': '0795-3585333'
    },
    {
        'uid': 150842,
        'cname': '南城文业装饰设计',
        'headphoto': 'wenya.jpg',
        'qq': '402780519',
        'phone': '0794-7388299'
    });
    var aIndex = {
        959: 0,
        952: 1,
        940: 2,
        954: 3
    };
    var tcode = getCookie('tcode', 1);
    if (aIndex[townid] > -1 && aCity_zx[aIndex[townid]].uid)
    {
        window.onload = function() {
            if (!$('ionline_zx'))
            {
                var obj = document.createElement('div');
                obj.id = 'ionline_zx';
                obj.className = 'ifloat_online_zx';
                obj.innerHTML = '<div><table><tr><td><p id="izhixun_bar" onmouseover="online_zx_oper(\'over\')" class="bar">装修问题免费咨询</p><div id="izhixun_con" class="sidefloat" style="display:none;" onmouseout="online_zx_oper(\'out\')"><p class="tit">在线装修专家</p><p class="c_logo"><a href="http://www.to8to.com/zs/' + aCity_zx[aIndex[townid]].uid + '/" target="_blank"><img src="http://www.to8to.com/img/front_end/bg/' + aCity_zx[aIndex[townid]].headphoto + '" /></a></p><div class="info"><p class="nick"><a href="http://www.to8to.com/zs/' + aCity_zx[aIndex[townid]].uid + '/" target="_blank">' + aCity_zx[aIndex[townid]].cname + '</a></p><p class="demo">业主可以向我免费咨询装修设计上的疑难问题</p></div><p class="lxfs"><span class="s1"><a href="tencent://message/?uin=' + aCity_zx[aIndex[townid]].qq + '&Menu=yes;">' + aCity_zx[aIndex[townid]].qq + '</a></span><span class="s2">' + aCity_zx[aIndex[townid]].phone + '</span></p><p class="site">' + strtname + '装修网 http://' + tcode + '.to8to.com</p></td></tr></table></div></div>';
                addNodes(document.getElementsByTagName('body')[0], obj, -1);
            };
            $('ionline_zx').style.position = 'absolute';
            $('ionline_zx').style.zIndex = 100;
            $('ionline_zx').style.right = 0;
            var stmnBASE = document.documentElement.clientHeight - parseInt($("ionline_zx").offsetHeight, 10) - 100;
            $('ionline_zx').style.top = document.documentElement.scrollTop + stmnBASE;
            load_online_zx();
        };
    };
};
function load_online_zx()
 {
    var stmnGAP1 = document.documentElement.clientHeight - parseInt($("ionline_zx").offsetHeight, 10) - 100;
    var stmnGAP2 = document.documentElement.clientHeight - parseInt($("ionline_zx").offsetHeight, 10);
    var stmnActivateSpeed = 200;
    var stmnScrollSpeed = 10;
    var stmnTimer;
    var stmnStartPoint,
    stmnEndPoint,
    stmnRefreshTimer;
    stmnStartPoint = parseInt($('ionline_zx').style.top, 10);
    stmnEndPoint = document.documentElement.scrollTop + stmnGAP2;
    if (stmnEndPoint < stmnGAP1)
    {
        stmnEndPoint = stmnGAP1;
        stmnRefreshTimer = stmnActivateSpeed;
    };
    if (stmnStartPoint != stmnEndPoint)
    {
        stmnScrollAmount = Math.ceil(Math.abs(stmnEndPoint - stmnStartPoint) / 15);
        $('ionline_zx').style.top = parseInt($('ionline_zx').style.top, 10) + ((stmnEndPoint < stmnStartPoint) ? -stmnScrollAmount: stmnScrollAmount);
        stmnRefreshTimer = stmnScrollSpeed;
    };
    stmnTimer = setTimeout("load_online_zx();", stmnRefreshTimer);
};
function online_zx_oper(str)
 {
    if (str == 'over')
    {
        $('izhixun_con').style.display = '';
        $('izhixun_bar').style.display = 'none';
    }
    else if (str == 'out')
    {
        if (!$('izhixun_con').contains(event.toElement))
        {
            $('izhixun_con').style.display = 'none';
            $('izhixun_bar').style.display = '';
        }
    };
};
function yuyue_apply(id)
 {
    id = id ? id: 0;
    var oJsLoader = new jsLoader();
    oJsLoader.onsuccess = function() {
        editPhotoCat("/yuyue_apply.html?id=" + id, '免费量房/免费做预算/免费出平面图/立即申请', 620, 520);
    };
    oJsLoader.load('/gb_js/popup.js');
    return false;
};
function Integral2money(num)
 {
    return num;
};

function goTopEx()
 {
    var createDiv = document.createElement("div");
    createDiv.id = "goTopBtn";
    createDiv.style.display = "none";
    createDiv.innerHTML = '<a href="http://www.to8to.com/about/feedback.php" target="_blank" class="feed"></a> <a href="javascript:" id="goTopBtna" style="width:50px;margin-top:121px;height:50px; float:left;"></a>';
    document.body.appendChild(createDiv);
    var obj = document.getElementById("goTopBtn");
    function getScrollTop() {
        return document.documentElement.scrollTop + document.body.scrollTop;
    };
    function setScrollTop(value) {
        document.body.scrollTop = document.documentElement.scrollTop = value;
    };
    window.onscroll = function() {
        getScrollTop() > 0 ? obj.style.display = "": obj.style.display = "none";
        var top = document.documentElement.scrollTop + document.body.scrollTop;
        if (isIE6) obj.style.top = top + 77 + "px";
    };
    $('goTopBtna').onclick = function() {
        //var goTop=setInterval(scrollMove,2); function scrollMove(){setScrollTop(getScrollTop()/2.1);if(getScrollTop()<1)clearInterval(goTop);}
        document.documentElement.scrollTop = document.body.scrollTop = 0;

    };

};
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;

};

var return_ad = '';

function getad(adid) {
    townid = getCookie("townid", 1);
    preview_com = getQueryString("preview_com");
    //获取url传递的参数
    preview_ad = getQueryString("preview_ad");
    if (preview_com) {
        if (preview_ad == adid) {
            ad_parameter = "adid=" + preview_ad + "&preview=" + preview_com;

        } else {
            ad_parameter = "adid=" + adid + "&preview=" + preview_com;

        };

    } else {
        ad_parameter = "adid=" + adid + "&townid=" + townid;

    };

    host = getCookie('tcode', 1);
    if (window.location.host == "xiaoguotu.to8to.com") {
        host_url = 'http://xiaoguotu.to8to.com/';

    } else if (window.location.host == "www.to8to.com") {
        host_url = 'http://www.to8to.com/company/';

    } else {
        host_url = 'http://' + host + '.to8to.com/company/';

    };
    var xmlhttp;
    if (window.XMLHttpRequest) {
        // code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();

    } else {
        // code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");

    };
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            //try{
            $("to8to_credits_ad_" + adid).innerHTML = xmlhttp.responseText;
            //}catch(e){}

        }

    };
    xmlhttp.open("GET", host_url + "ad_online.php?callback=ad_callback&" + ad_parameter, true);
    xmlhttp.send();

};


//头部底部初始化
!(function(){
    var a  = {
        init:function(){
            hfDocReady();//header and footer docReadyFunction
        }
    };
       window.searchtage = '';
        var tcode = getCookie("tcode",true);
            tcode = tcode?tcode:'sz',
            searchclicktage = '', searchput='keyword', sHref='',sText=''
            tRight = 0;
    function initsearch(sName)
        {
           sName = jq.trim(sName);
           switch(sName)
           {
                case '效果图': 
                   searchtage='1_8_3_1';
                   searchclicktage='1_8_2_1'; 
                   sHref= 'http://xiaoguotu.to8to.com/search.php';
                   sText ='海量精美效果图任你选'; 
                   tRight = 70;
                break;   
                case '装修公司':
                   searchtage='1_8_3_2';
                   searchclicktage='1_8_2_2';
                   sHref= "http://"+tcode+".to8to.com/company/";
                   sText ='挑选您心仪的装修公司';
                   tRight = 58;
                break;   
                case '小区':
                    searchtage='1_8_3_3';
                    searchclicktage='1_8_2_3';
                    sHref=  "http://"+tcode+".to8to.com/zwj/";
                    sText ='找找您家小区的装修案例';
                    tRight = 82;
                    break;   
                case '文章': searchtage='1_8_3_4';searchclicktage='1_8_2_4';
                    sHref= 'http://www.to8to.com/yezhu/xzx_search.php';
                    sText ='了解装修相关的资讯知识';
                    tRight = 82;
                    break;   
                case '问答': 
                    searchtage='1_8_3_5';
                    searchclicktage='1_8_2_5';    
                    sHref= 'http://www.to8to.com/ask/search.php';
                    sText ='解决您的装修疑问';
                    tRight = 82;
                    break;   
                case '建材': 
                    searchtage='1_8_3_6';
                    searchclicktage='1_8_2_6';
                    sHref= 'http://jiaju.to8to.com/search_product.html';
                    sText ='挑选优质家居建材'; 
                    tRight = 82;
                    break;    
           }
           jq('.header_search_submit').attr('searchtage',searchtage);
           jq('.header_search_input_text').attr('v',tRight).css("right",''+tRight+'px');
           if(sName=='全站'||sName=='文章' || sName=='小区')
           {
                searchput ='keyword_zh';
           }
           else if(sName=='建材')
           {
                searchput ='q';   
           }
           else
           {
              searchput='keyword';
           }
            jq('.header_search_input').attr('name',searchput);
            jq('#searchform').attr('action',sHref);
            jq('.header_search_input_text').html(sText);
        }
    
    function hfDocReady(){
        
        var doc = {};
        doc.hs = jq('.header_select');
        doc.si =  jq('.header_search_input');
        doc.si.val("");
        doc.hs.on('mouseenter', function(){
          jq(this).addClass('on');
          jq(this).find('ul').show();
        });
        doc.hs.on('mouseleave', function(){
          jq(this).removeClass('on');
          jq(this).find('ul').hide();
        });
        var currentTxt = doc.hs .find('a >span>em').text();
        initsearch(currentTxt);
        doc.hs.find('ul > li').on("click", function(){
            var sName = jq(this).find('a').text(),
                siWidth = doc.si.width();
                hsWidth = jq('.header_select').width();
            jq('.header_select_sort').find('span > em').text(sName);
            initsearch(sName);
            try{clickStream.getCvParams(searchclicktage);}catch(e){}
            newHsWidth = jq('.header_select').width();
            doc.si.width(siWidth - (newHsWidth-hsWidth));
            var rm = jq('.header_search_input_text').attr('v');
            if(rm == undefined) rm = tRight;

            var rightMargin = rm-(newHsWidth-hsWidth);
            jq('.header_search_input_text').css('right', ''+rightMargin+'px').attr('v',''+rightMargin+'');
            jq(this).parent().hide();
            doc.si.focus();
        });
        jq('.header_search_input_text').on("click", function(){
            doc.si.focus();
        });
        doc.si.on("keydown", function(){
            jq('.header_search_input_text').hide();
        });
        doc.si.blur(function(){
            if(jq(this).val() =="" )  jq('.header_search_input_text').show();
        });
        jq('.header_menu >ul > li').on("mouseenter", function(){
            jq(this).addClass('menu_hover');
        });
        jq('.header_menu >ul > li').on("mouseleave", function(){
            jq(this).removeClass('menu_hover');
        });
        jq('.header_search_submit').on('click',function(){
            var searchtage=jq('.header_search_submit').attr('searchtage');
            if(searchtage>0)
            {
                try{clickStream.getCvParams(searchtage);}catch(e){} 
            }
        });
        doc.ftc = jq('.footer_top_container');
        doc.ftc.li =  doc.ftc.find('div.ftc_left > ul > li');
        doc.ftc.li.on("click", function(){
          doc.ftc.li.removeClass('on');
          jq(this).addClass('on');
          var n = doc.ftc.li.index(jq(this));
          jq('.ftclt_content').removeClass('on');
          jq('.ftclt_content').eq(n).addClass('on');
        });
        jq('.pop_wechat').on("mouseenter", function(){
          jq(this).find('div.wechat_bg').show();
        });
        jq('.pop_wechat').on("mouseleave", function(){
          jq(this).find('div.wechat_bg').hide();
        });
        jq('.q_code').on("mouseenter",function(){
          jq(this).find('div.q_code_layer').show();
          clickStream.getCvParams('1_7_1_1');
        });
        jq('.q_code').on("mouseleave",function(){
          jq(this).find('div.q_code_layer').hide();
        });
        jq('.header_bottom > .header_menu > ul > li.has_homeIcon').mouseenter(function(){
          jq(this).find('em').show();
        });
        jq('.header_bottom > .header_menu > ul > li.has_homeIcon').mouseleave(function(){
          jq(this).find('em').hide();
        });
        jq('.gpm_name').mouseenter(function(){
          jq('.gpm_content').show();
        });
        jq('.gpm_content').mouseleave(function(){
          jq(this).hide();
        });
        };
        window.headerFooter = a;
    
})(jQuery);

//置顶scrollTop.init();
(function(jq) {
    var scrollTop = {init:function(qqArr,qqShow,editShow) {
            gotoTop(qqArr,qqShow,editShow);
            ctrolGotop();
            ctrlLeft();//初始化置顶的LEFT值
        }};
    
    function gotoTop(qqArr,qqShow,editShow) {
        var str = '<div class="nav_top"><ul class="qq_list">',
            obj = {};
            
        if(qqShow && qqArr.length != 0) {   
            for(var i=0,len=qqArr.length;i < len;i++) {
                str += '<li><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin='+qqArr[i].qq+'&site=qq&menu=yes"><em></em><span>'+qqArr[i].manname+'</span></a></li>';
            }
        }
        str += '</ul><div class="nav_top_list"><a href="javascript:;" class="ico_qq ico_qq_act"><i></i>'+editShow+'</a><a href="javascript:;" class="ico_server" style="display:none;"><i></i><span>在线咨询</span></a><a target="_blank" href="http://www.to8to.com/about/feedback.php" rel="nofollow" class="ico_feedback"><i></i><span>意见反馈</span></a><a href="javascript:;" class="ico_top"><i></i><span>回到顶部</span></a></div></div>';
        
        jq("body").append(jq(str));
        
        if(qqShow) {
            jq(".nav_top > .nav_top_list > .ico_qq").css("display","block");
            jq(".nav_top > ul.qq_list").css("display","block");
            jq(".nav_top > .nav_top_list > .ico_server").hide();    
        };
        jq(".nav_top > .nav_top_list > .ico_server").bind("click",function() {
            window.open('http://dzt.twos.net.cn/LR/Chatpre.aspx?id=DZT39460052&lng=cn', '_blank',"height=500,width=750");   
        });
        obj = getBodyType();
        jq(".nav_top").css({"left":obj.left+"px","margin-right":0});
        if(jq(".nav_top").find("ul.qq_list > li").length == 1) {
            jq(this).find("ul.qq_list > li").addClass("one");   
        };
        /*jq(".nav_top").find(".nav_top_list > .ico_qq").hover(function() {
            jq(this).addClass("ico_qq_act").parent().prev().show(); 
        },function() {
            jq(this).removeClass("ico_qq_act").parent().prev().hide();
        });
        
        jq(".nav_top").find(".qq_list").hover(function() {
            jq(this).next().find(".ico_qq").addClass("ico_qq_act");
            jq(this).show();    
        },function() {
            jq(this).next().find(".ico_qq").removeClass("ico_qq_act");
            jq(this).hide();    
        });*/
        jq(".nav_top").find(".nav_top_list > .ico_server").hover(function() {
            jq(this).find("i").hide().end().find("span").css("display","block");    
        },function() {
            jq(this).find("span").hide().end().find("i").css("display","block");
        });
        jq(".nav_top").find(".nav_top_list > .ico_feedback").hover(function() {
            jq(this).find("i").hide().end().find("span").css("display","block");    
        },function() {
            jq(this).find("span").hide().end().find("i").css("display","block");
        });
        jq(".nav_top").find(".nav_top_list > .ico_top").hover(function() {
            jq(this).find("i").hide().end().find("span").css("display","block");    
        },function() {
            jq(this).find("span").hide().end().find("i").css("display","block");
        }).click(function() {
            jq(window).scrollTop("0");  
        });     
    };
    function getBodyType() {
        var type = "wide";
        if(jq("body").hasClass("narrow_980")) {
            left = (jq("body").width() - 980)/2 + 1000;
            type = "narrow";    
        } else {
            left = (jq("body").width() - 1220)/2 + 1240;    
        }
        return {"left":left,"type":type};   
    };
    
    function ctrPositionForIe6() {
        var bwObj = checkBrowser();
        if(bwObj.name == "msie" && bwObj.version == 6) { //IE6下控制TOP值
            var sH = document.documentElement.scrollTop || document.body.scrollTop,
                cH = document.documentElement.clientHeight || document.body.clientHeight,
                topForIe6 = sH + cH - jq(".nav_top").height() - 100;
            //jq("body").css("position","relative");     
            jq(".nav_top").css("top",topForIe6+"px");   
        }   
    };
    
    function ctrolGotop() {
        var h = jq(window).height()/2,
            obj = jq(".nav_top > .nav_top_list > .ico_top");
        ctrPositionForIe6();    
        if(jq(window).scrollTop() >= h) {
            obj.css("display","block"); 
        } else {
            obj.hide();     
        }   
    };
    
    function ctrlLeft() {
        var obj = getBodyType(),
            w = jq(window).width();

        if((obj.type == "wide" && w <= 1363) || (obj.type == "narrow" && w <= 1050)) {
            jq(".nav_top").css({"left":"auto","right":"0"});    
        } else {
            jq(".nav_top").css({"left":obj.left+"px","margin-right":0});    
        }   
    };
    
    jq(window).bind("scroll",function() {
        ctrolGotop();   
    }); 
    
    jq(window).bind("resize",function() {
        ctrlLeft();
        ctrPositionForIe6();
    });
    
    
    window.scrollTop = scrollTop;
})(jQuery,window);
(function(jq) {
    var goTopInit = function(settings) {
        jq(".nav_top").remove();
        var defaults = {
            topShow:true,//true显示置顶 false不显示
            editShow:"",//<em>编辑</em>显示编辑 为空不显示
            qqShow:false,//true显示企鹅 false不显示
            //wxShow:false,//true显示微信  false不显示
            qqArr:[]//QQ号数组
        };
        settings = jq.extend(defaults,settings);
        if(settings.topShow) {
            jq(function() {
                scrollTop.init(settings.qqArr,settings.qqShow,settings.editShow);   
            });
        } else {
            jq(".nav_top").remove();    
        }
    };  
    window.goTopInit = goTopInit;
})(jQuery,window);

//window_box  弹窗
var windowBoxOriginHight;
!function(){
    jq.fn.windowBox = function(settings){
        settings=jq.extend({},jq.windowBox.defaults,settings);
        var bType = checkBrowser();
         var box = {};
         box.fn = {};
         box.fn.con =  jq('.window_box');
         box.fn.con.length = box.fn.con.length;
         if(box.fn.con.length == 1)
            return false;
         box.fn.windowType = checkBrowser();
         box.fn.wbcStr =  settings.wbcStr;
         box.fn.title = settings.title;
         box.fn.littleTitle = settings.littleTitle;
         
        if(settings.cancleBtn && settings.confirmBtn){
             box.fn.btn = '<div class="window_box_btn"><input onclick=saveCollection() type="button" value="保存" class="window_box_btn_save"><input onclick=window_box_close(this) type="button" value="取消"   class="window_box_btn_cancle"></div>';
         }else if(settings.cancleBtn && !settings.confirmBtn){
              box.fn.btn = '<div class="window_box_btn"><input onclick=window_box_close(this) type="button" value="取消"  class="window_box_btn_cancle"></div>';
        }else if(!settings.cancleBtn && settings.confirmBtn){
              box.fn.btn = '<div class="window_box_btn"><input onclick=saveCollection() type="button" value="保存" class="window_box_btn_save"></div>';
        }else{
             box.fn.btn = '';
        };
        //box.fn.confirmStr = '<div class="window_box " style="left:'+box.fn.style.l+'px; top:'+box.fn.style.t+'px;position:absolute; z-index:9999; height:'+box.fn.style.h+'px; width:'+box.fn.style.w+'px;overflow:hidden"><div class="window_box_title" style="display:'+settings.showTitle+'"><span>'+box.fn.title+'</span><em>'+box.fn.littleTitle+'</em><a href="javascript:void(0)" class="window_box_close" onClick="javascript:'+settings.closeFn+'(this)"></a></div><div class="window_box_container '+settings.moreClass+'">'+box.fn.wbcStr+''+box.fn.btn+'</div></div><div class="translucence_layer" style="_height:'+box.fn.wHeight+'px; "><iframe style="position:absolute;top:0;left:0;width:100%;height:100%;filter:alpha(opacity=0);"></iframe></div>';
        box.fn.confirmStr = '<div class="window_box " style="position:absolute; z-index:9999;overflow:hidden"><div class="window_box_title" style="display:'+settings.showTitle+'"><span>'+box.fn.title+'</span><em>'+box.fn.littleTitle+'</em><a href="javascript:void(0)" class="window_box_close" onClick="javascript:'+settings.closeFn+'(this)"></a></div><div class="window_box_container '+settings.moreClass+'">'+box.fn.wbcStr+''+box.fn.btn+'</div></div><div class="translucence_layer" ><iframe style="position:absolute;top:0;left:0;width:100%;height:100%;filter:alpha(opacity=0);"></iframe></div>';
        jq('body').append(box.fn.confirmStr);
        windowBoxOriginHight = 0;
       resizeWindowBox(); 

        box.fn.pos = [];
        var oldDocWidth = document.documentElement.clientWidth;
        box.fn.pos.push(oldDocWidth);
   
        if(bType.version == "6"  ){

            jq("html").css("overflow-y","visible");
            jq("body").css("overflow-y","hidden");
        }else if(bType.version == "7" || bType.version == "8"){
            jq("html").css("overflow-y","hidden");
            jq("body").css("overflow-y","hidden");
        }else{
            jq("body").css("overflow-y","hidden");
        };
         
        jq("body").css({"height":"100%"});
        var newDocWidth = document.documentElement.clientWidth;
        box.fn.pos.push(newDocWidth);
        var box_left = box.fn.pos[1] - box.fn.pos[0];

        jq('body').css('margin-right',''+box_left+'px');
        if(bType.name == "msie" && bType.version == "6"){
        var h = jq(window).scrollTop();
        jq('.translucence_layer').css({"top":h + "px", "height" : box.fn.wHeight + "px"});
        };
        jq('.window_box_btn_cancle').click(function(){
            jq('.window_box').remove();
            jq('.translucence_layer').remove();
            var cb =  checkBrowser();
            if(cb.version == "6"){
               jq("html").css("overflow-y","scroll");
               jq("body").css("overflow-y","visible");
            }else if(cb.version == "7" && jq('#st_pid').length != 1){
               jq("html").css("overflow-y","scroll");
               jq("body").css("overflow-y","visible");
            }else if(cb.version == "8" && jq('#st_pid').length != 1){
                jq("html").css("overflow-y","scroll");
                jq('body').css('overflow-y','visible');
            }else{
               jq("body").css("overflow-y","inherit"); 
            };
            
            jq('body').css('margin-right','0');
            
        });
        if(typeof(settings.callback) == "function")
            settings.callback();
        if(settings.closeTime != "") {
            //setTimeout('window_box_close()',settings.closeTime);
            setTimeout(function() {
                var myfn = eval(settings.closeFn)
                myfn();
            }, settings.closeTime);
        }
        //自适应处理
         jq(window).bind("resize",function() {
            resizeWindowBox(); 
        });

        /*高度自适应*/
        function resizeWindowBox() {
            box.fn.wHeight = document.documentElement.clientHeight;
            box.fn.wWidth = document.documentElement.clientWidth;
            box.fn.wScrollHeight = document.documentElement.scrollTop || document.body.scrollTop;
            box.fn.wScrollWidth = document.documentElement.scrollLeft || document.body.scrollLeft;
            if (settings.width != "auto") {
                box.fn.left = box.fn.wScrollWidth + (box.fn.wWidth - settings.width) / 2;
                box.fn.width = settings.width;
            } else {
                box.fn.width = box.fn.con.width();
                box.fn.left =  box.fn.wScrollWidth + (box.fn.wWidth - box.fn.width) / 2;
            };
            if (settings.height != "auto") {
                box.fn.top = box.fn.wScrollHeight + ((box.fn.wHeight - settings.height) / 2);
                box.fn.height = settings.height;
            } else {
                box.fn.height = null;
                box.fn.top = box.fn.wScrollHeight + ((box.fn.wHeight - box.fn.height) / 2);
            };
            box.fn.style = {
                l: box.fn.left,
                t: box.fn.top,
                w: box.fn.width,
                h: box.fn.height
            };
            jq('.window_box').css({
                left: box.fn.style.l,
                top: box.fn.style.t,
                width: box.fn.style.w,
                height: box.fn.style.h
            });
            if (box.fn.height == null) {
                box.fn.height = jq('.window_box').height();
                box.fn.top = box.fn.wScrollHeight + ( box.fn.wHeight > box.fn.height ? ( box.fn.wHeight - box.fn.height):0) / 2;
                jq('.window_box').css({
                    "height": "auto",
                    "top": box.fn.top
                });
            };
            windowBoxOriginHight = windowBoxOriginHight ? windowBoxOriginHight : jq('.window_box').height();
            var wbcHeight,wbcStyle;
            wbcHeight = windowBoxOriginHight > box.fn.wHeight ? box.fn.wHeight: windowBoxOriginHight;
            if(settings.showTitle === "block"){
                wbcHeight = wbcHeight - jq('.window_box_title').height();
            }
            wbcStyle = 'height:' + wbcHeight + 'px;';
            wbcStyle += windowBoxOriginHight > box.fn.wHeight ? 'overflow-y:scroll;':'';
            jq('.window_box_container').attr('style', wbcStyle);  
        }
    };
   
    // 默认值
        jq.windowBox={defaults:{
            width:'auto',
            height:'auto',
            transLayer:true,//是否出现透明背景层
            type:"window_box",//弹框类型
            wbcStr:"",//字符串
            title:"弹窗",//大标题
            littleTitle:"",//小标题
            closeTime:"",//自动关闭时间
            callback:"",
            closeFn:"window_box_close",//点击关闭执行的函数
            showTitle:"block",//显示title
            moreClass:""//container上另加的class
        }}; 
}(jQuery);
function window_box_close(obj){
        jq('.window_box').remove();
        jq('.translucence_layer').remove();
        var cb =  checkBrowser();
         if(cb.version == "6"){
               jq("html").css("overflow-y","scroll");
               jq("body").css("overflow-y","visible");
            }else if(cb.version == "7" && jq('#st_pid').length != 1){
               jq("html").css("overflow-y","scroll");
               jq("body").css("overflow-y","visible");
            }else if(cb.version == "8" && jq('#st_pid').length != 1){
                jq("html").css("overflow-y","scroll");
                jq('body').css('overflow-y','visible');
            }else{
               jq("body").css("overflow-y","inherit"); 
            };
        jq('body').css('margin-right','0');
        windowBoxOriginHight = 0;
};

//验证组件
!function(){
    jq.fn.checkForm = function(settings){
        settings=jq.extend({},jq.checkForm.defaults,settings);
        if( settings.type.length == 0 ) {
            return false;
        }  
        var cf = {};
        cf.fn = {};
        cf.fn.regType = [/^13[0-9]{1}[0-9]{8}$|14[0-9]{1}[0-9]{8}$|15[0-9]{1}[0-9]{8}$|18[0-9]{1}[0-9]{8}$|17[0-9]{1}[0-9]{8}$/, /^([0-9]+)$/,/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/,/^([0-9.]+)$/,/^[0-9]{5,}$/,/^((0\d{2,3})(-)?)?(\d{7,8})(-(\d{3,}))?$|^13[0-9]{1}[0-9]{8}$|14[0-9]{1}[0-9]{8}$|15[0-9]{1}[0-9]{8}$|18[0-9]{1}[0-9]{8}$|17[0-9]{1}[0-9]{8}$/,/^[1-9]\d{0,7}(\.\d{1,2})?$/,/^[1-9][0-9]{0,3}$/,/^\d{1,4}(\.\d{1})?$/,/^(([1-9]\d{0,6})|0)(\.\d{1})?$/,/^[A-Za-z0-9]{8,20}$/,/^((0\d{2,3})(-)?)?(\d{7,8})(-(\d{3,}))?$/,/^[a-z0-9]([a-z0-9]*[-_]?[a-z0-9]+)*@([a-z0-9]*[-_]?[a-z0-9]+)+[\.][a-z]{2,3}([\.][a-z]{2})?$/i,/^[a-zA-Z0-9_\u4e00-\u9fa5]{4,20}$/];//0:手机。1：纯数字。2：EMAIL。3：数字加.。4：QQ号。5:手机或固话。6.价格。7.面积（<=10000）8.预算（0.1万-9999.9万）。9.清单价格（0.1元-999.9万）。10.字母数字(8-20)。11.固定电话。12.邮件地址。13.中英文，数字及_。
        cf.fn.obj = this;
        cf.fn.str = '';
        cf.fn.count = 0;
        cf.fn.obj.focus(function(){
            cf.fn.str = '';
            jq.fn.prototype.removeWrongText(jq(this), settings.parCls, settings);
        });
        if(!settings.checkFormType){
            cf.fn.obj.blur(function() {
                jq.fn.prototype._check(cf,settings,jq(this));
            });
        }else if(settings.checkFormType && settings.checkType == "text"){
            var g = jq.fn.prototype._check(cf,settings,jq(this));
            return g;
        }else if(settings.checkFormType && settings.checkType == "select"){
            var g = jq.fn.prototype._check(cf,settings,jq(this));
            return g;
        }
    };
    jq.fn.prototype = {
        _check:function(cf,settings,blurObj){
            cf.fn.str = '';
            cf.fn.str += '<div class="'+settings.className+'"><'+settings.labl+' class="'+settings.lablClass+'"></'+settings.labl+'>';
            blurObj.find('div.'+settings.className+'').remove();
            var cfPos = [];
            if(settings.displayNum  && jq(settings.checkFormType).parent().find('div.'+settings.className+'').length == 1) {
               return;
            }
            for(var i = 0; i < settings.type.length ; i++){
                if(settings.type[i] == 1 && settings.checkType =="text" && settings.content[0] != "" && (blurObj.val() == "" || (blurObj[0].type == "select-one" && blurObj.val() == "0"))){
                    cf.fn.str += ''+settings.content[0]+'</div>';
                    jq.fn.prototype.addWrongText(blurObj, settings.parCls, cf.fn.str, settings.checkType);
                    cf.fn.count = 1;

                } else if(settings.type[i] == 2 && settings.checkType =="text" && blurObj.val() !=""){
                    var result = false;
                    if(typeof settings.reg == "object" && !settings.moreReg) {
                        result = jq.fn.prototype.checkWordLen(settings.reg.len, settings.reg.range, blurObj.val()); 
                    }else if(typeof settings.reg == "number" && !(blurObj.val().match(cf.fn.regType[settings.reg]) == null)){
                        result = true;  
                    }else if(typeof settings.reg == "object" && settings.moreReg ){
                        var moreRegResult = false;
                        for(var i =0; i< settings.reg.length; i++){
                            
                            if(blurObj.val().match(cf.fn.regType[settings.reg[''+i+'']]) != null){
                                moreRegResult = true;
                            }
                        }
                        if(moreRegResult == true){
                            result = true;
                        }

                    } 

                    if(!result) {
                        cf.fn.str += ""+settings.content[1]+'</div>';
                        jq.fn.prototype.addWrongText(blurObj, settings.parCls, cf.fn.str, settings.checkType);  
                        cf.fn.count = 1;    
                    }
                }else if(settings.type[i] == 1 && settings.checkType == "select" && blurObj.find('option:selected').attr('value') == ""){
                    cf.fn.str += ""+settings.content[0]+'</div>';
                    jq.fn.prototype.addWrongText(blurObj, settings.parCls, cf.fn.str, settings.checkType);
                    cf.fn.count = 1;
                };
            };
            if(cf.fn.count != 1) {
                 return 0;
            }
        },
        checkWordLen:function(maxLen, range, val) {
            var len;
            /*if(null == val.match(/[\u4e00-\u9fa5]/g)) {
                len = val.length;   
            } else if(null == val.match(/[^\u4e00-\u9fa5]/g)) {
                len = val.length * 2;   
            } else {
                len = val.match(/[^\u4e00-\u9fa5]/g).length + val.match(/[\u4e00-\u9fa5]/g).length * 2;     
            }*/ 
            len = val.length;
            if(!range) {
                if(maxLen < len) {
                    return false;   
                }   
            } else {
                if(len > range.dmax || len < range.dmin) {
                    return false;   
                }   
            }
                
            return true;
        },
        addWrongText: function(obj, cls, str, chkType) {
            if(!cls) { //未传class
                obj.parent().addClass('height_auto');
                obj.parent().append(str);
            } else {
                obj.parents(cls).addClass('height_auto');
                obj.parents(cls).append(str);
            }

            if(chkType != 'select') {
                obj.css('border-color','#ff6767');
            }
        },
        removeWrongText: function(obj, cls, settings) {
            if(!cls) { //未传class
                obj.parent().removeClass('height_auto');
                obj.parent().find('div.'+settings.className+'').remove();
            } else {
                obj.parents(cls).removeClass('height_auto');
                obj.parents(cls).find('div.'+settings.className+'').remove();
            }
            obj.css('border-color','#ccc');
        }
    };
    // 默认值
    jq.checkForm={defaults:{
        calssName:"checkFormDefault", //报错字符串的class
        content:[], //报错内容数组
        type:[], //报错类型，1为空，2其他错
        reg:"", //报错类型2时候的正则表达式
        moreReg:false,
        checkType:"text",//检测类型
        labl:'em',//错误提示的标签
        lablClass:'ico_tip_warn_s',//错误提示的标签的类名
        parCls: ''//错误提示所加父元素的标示
    }}; 
}(jQuery);

//验证组件2
(function(){
    jq.fn.checkForm2 = function(settings){
        var defaults = {
            calssName:"add_wrong", //报错字符串的class
            content:[], //报错内容数组
            checkType:"text",//检测类型
            labl:'i',//错误提示的标签
            lablClass:'ico_error',//错误提示的标签的类名
            parCls: '',//错误提示所加父元素的标示
            callback: '',//检测成功后执行的函数
            info: [],
            displayNum: true,//单个提示
            parentTip: 'div',//单个提示范围
            blurChk: false,//blur时检测提示错误
            chkType: '',//检测类型，radio单独处理
            callback: false//验证成功后执行的函数，必须是全局变量
        };


        var settings = jq.extend({}, defaults, settings),
            cf = {};
        cf.fn = {};
        if(settings.info.length == 0 ) {
            return false;
        }  
        cf.fn.regType = [/\S/];//0:非空字符串

        jq(this).focus(function() {//获取焦点是移除错误
            removeWrongText2(jq(this), settings.parCls, settings);
        });
        if(settings.blurChk){//blur时检测提示错误
            jq(this).blur(function() {
                _check2(cf, settings, jq(this));
            });
        }else if(!settings.blurChk) {//提交时检测提示错误
            var g = _check2(cf, settings, jq(this));
            return g;
        }


        function _check2(cf, settings, blurObj) {
            var strTip = '<div class="'+settings.className+'"><'+settings.labl+' class="'+settings.lablClass+'"></'+settings.labl+'>',//错误提示字符串
                myVal = '',//待验证的value值
                info = '',//验证规则及提示对象
                chkFlag = true,//验证的状态
                reg = [];//验证规则数组
            if(settings.displayNum  && blurObj.parents(settings.parentTip).find('div.'+settings.className+'').length >= 1) {
               return;
            }
            //获取value
            if(settings.checkType =="text") {//text,textarea...
                myVal = blurObj.val();
            } else {//select
                myVal = blurObj.find('option:selected').attr('value');
            }
            //处理radio
            if(settings.chkType == 'radio' && blurObj.filter(':checked').length == 0) {
                strTip += ""+settings.info[0]['tip']+'</div>';
                addWrongText2(blurObj, settings.parCls, strTip, settings.checkType);  
                chkFlag = false;
            }

            for(var i = 0; i < settings.info.length; i++){
                info = jq.extend({}, {negate: false}, settings.info[i]);
                reg = [];
                if(!chkFlag) {//验证失败不做以后的验证
                    break;
                }
                if(info.reg && info.tip) {
                    //获取reg
                    for(var j = 0, len = info.reg.length;j < len;j++) {
                        if(typeof info.reg[j] == 'number') {
                            reg.push(cf.fn.regType[info.reg[j]]);
                        } else {
                            reg.push(info.reg[j]);
                        }
                    }

                    //验证
                    for(var k = 0;k < reg.length;k++) {
                        var regRslt = reg[k].test(myVal);
                        if(!regRslt && !info.negate) {//匹配不成功，添加错误提示
                            strTip += ""+info.tip+'</div>';
                            addWrongText2(blurObj, settings.parCls, strTip, settings.checkType);  
                            chkFlag = false;
                            break;  
                        } else if(regRslt && info.negate) {
                            strTip += ""+info.tip+'</div>';
                            addWrongText2(blurObj, settings.parCls, strTip, settings.checkType);  
                            chkFlag = false;
                            break;
                        }
                    }
                }

            }
            if(chkFlag) {
                if(settings.callback) {
                   chkFlag = settings.callback(); 
                }
                return chkFlag;//成功
            }
        }

        function checkWordLen2(maxLen, range, val) {
            var len = val.length;
            if(!range) {
                if(maxLen < len) {
                    return false;   
                }   
            } else {
                if(len > range.dmax || len < range.dmin) {
                    return false;   
                }   
            }
                
            return true;
        }


        function addWrongText2(obj, cls, str, chkType) {
            if(!cls) { //未传class
                obj.parent().addClass('height_auto');
                obj.parent().append(str);
            } else {
                obj.parents(cls).addClass('height_auto');
                obj.parents(cls).append(str);
            }

            if(chkType != 'select') {
                obj.css('border-color','#ff6767');
            }
        }


        function removeWrongText2(obj, cls, settings) {
            if(!cls) { //未传class
                obj.parent().removeClass('height_auto');
                obj.parent().find('div.'+settings.className+'').remove();
            } else {
                obj.parents(cls).removeClass('height_auto');
                obj.parents(cls).find('div.'+settings.className+'').remove();
            }
            obj.css('border-color','#ccc');
        }

    };
})(jQuery);

function simplifyCheck2(chkArr) {
        var defaults = {
            info: [],
            parCls: '',//错误加的地方,默认表单元素的父元素
            require: true,//必填,若是需要满足一定条件检测的需传入condition（true:执行检测，false:不执行检测）
            className: 'form_error',//错误提示类名
            labl: 'i',//X的html的标签
            lablClass: 'ico_error',//X的html的标签的类名
            callback: '',
            parentTip: 'form',
            chkType:''
        },
        settings = {},
        a = 0;

    for(var i = 0, len = chkArr.length;i < len;i++) {
        settings = {};

        settings = jq.extend({},defaults,chkArr[i]);
        if(settings.hasOwnProperty('condition')) {
            settings.require = false;
        }

        if(settings.require || (!settings.require && settings.condition)) {//必须，条件成立
            a = jq(settings.id).checkForm2({className:settings.className, info:settings.info, labl:settings.labl, lablClass: settings.lablClass, parCls: settings.parCls, callback: settings.callback, parentTip: settings.parentTip, chkType: settings.chkType});
        }
        // if(!a && settings.condition) {
        //     break;
        // }
        if(!a) {
            break;
        }
    }

    if(a) {
        return true;
    } else {
        return false;
    }       

}


//公司列表，滚动
(function() {
    jq.fn.scrollList = function(settings) {
        var defaults = {
            child:"li",//要滚动的元素
            num:3,//小于这个数不滚动
            time:1000,//滚动一行的时间
            interval:3000,//滚动间隔
            direct:"down"   //滚动方向
        },
        settings = jq.extend(defaults,settings),
        obj = jq(this),myScroll;
        
        if(obj.find(settings.child).length > settings.num) {
            obj.hover(function() {
                clearInterval(myScroll);    
            }, function() {
                if(settings.direct == "up") {
                    myScroll = setInterval(function() {
                        var h1 = obj.find(settings.child+":first").height();
                        obj.animate({"margin-top":-h1+"px"},settings.time,function() {
                            jq(this).css("margin-top",0).find(settings.child+":first").appendTo(this);
                        });  
                    },settings.interval);   
                } else {
                    myScroll = setInterval(function() {
                        var h1 = obj.find(settings.child+":last").height();
                        obj.animate({"margin-bottom":-h1+"px"},settings.time,function() {
                            jq(this).css("margin-bottom",0).find(settings.child+":last").insertBefore(jq(this).find(settings.child+":first"));
                        }); 
                    },settings.interval);
                }
            }).trigger("mouseleave");   
        }
    };
        
})(jQuery);
 // 轮换图组件
!function(jq){
    jq.fn.slider=function(settings,t){
        if(!this.length){returnFalse()};
        settings=jq.extend({},jq.slider.defaults,settings);
        var obj=this;
        var scroller={};
        scroller.fn={};
        scroller.li = obj.find('li');
        scroller.sliderName = jq('.'+settings.sliderName+'');
        scroller.onNum = 0;
        scroller.auto = settings.auto;
        scroller.itemSum = scroller.li.length;
        scroller.bLeftBtn= obj.parent('div').find('a.bLeft');
        scroller.bRightBtn=obj.parent('div').find('a.bRight');
        scroller.bLeftBtnPer = settings.bLeft;
        scroller.bRightBtnPer = settings.bRight;
        scroller.moveSlider = settings.moveSlider;
        scroller.times = settings.time;
        scroller.opacity = settings.opacity;
        scroller.colorCout = 0;
        
        if(settings.fontLi) {
          scroller.font = jq('.slider_font');
          scroller.fontLi  =jq('.slider_font').find('li');
          scroller.font.find('li[class="'+settings.play+'"]').css("opacity","1");
        };
        /*if(!scroller.opacity && scroller.moveSlider){
            obj.css('left','-'+scroller.li.width()+'px')
          }*/
          if(settings.bgColor != "" && settings.bgLayer !="" && settings.bgColor.length == scroller.itemSum ){
                jq('.'+settings.bgLayer+'').css('background',''+settings.bgColor[0]+'');
              
          };
         // 方法：开始
        scroller.fn.on=function(){

          //alert("342")
          scroller.fn.off();
          scroller.fn.removeControl();
          scroller.fn.addControl();

          
          if(!scroller.auto){return;};
          scroller.run=setTimeout(function(){
            scroller.fn.goto(settings.direction);
          },scroller.times);
        };
        // 方法：停止
        scroller.fn.off=function(){
          if(typeof(scroller.run)!=="undefined"){clearTimeout(scroller.run);};
        };
        // 方法：增加控制
        scroller.fn.addControl=function(){
          if(scroller.bLeftBtnPer&&scroller.bLeftBtn.length){
            scroller.bLeftBtn.bind("click",function(){
              scroller.fn.goto("bLeft");
            });
          };
          if(scroller.bRightBtnPer&&scroller.bRightBtn.length){
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

        //有轮播标记
        if(settings.markSlider && !scroller.moveSlider) {
          scroller.markLi  =obj.siblings('.'+settings.markClass+'').find('li');
          // 方法：点击轮播标记切换
          scroller.markLi.mouseenter(function(){
              scroller.fn.off();
              scroller.markNum = scroller.markLi.index(jq(this));
              scroller.li.addClass(''+settings.play+'').stop(1,1).css({
                opacity:"1",
                filter:"alpha(opacity=100)",
                display:"none"
              },settings.speed);
              scroller.li.eq(scroller.markNum-1).stop(1,1).css("opacity",'0.5').addClass(''+settings.play+'').css("display",'block').animate({opacity:"0"},settings.speed,function(){
                jq(this).css('display','none');
              }); 
              scroller.li.eq(scroller.markNum).stop(1,1).css('opacity','0.5').removeClass(''+settings.play+'').css("display",'block').animate({opacity:"1"},settings.speed); 
              scroller.markLi.removeClass(''+settings.markLiClass+'');
              jq(this).addClass(''+settings.markLiClass+'');
              scroller.fn.on();
          });    
        }else if(settings.markSlider && scroller.moveSlider){
          scroller.markLi  =obj.siblings('.'+settings.markClass+'').find('li');
          scroller.markLi.on(settings.markEvent,function() {
                scroller.markNum = scroller.markLi.index(jq(this));
                scroller.li.removeClass(''+settings.play+'');
                scroller.li.eq(scroller.markNum+1).addClass(''+settings.play+'');
                scroller.markLi.removeClass(''+settings.markLiClass+'');
                obj.animate({
                    left:'-'+(scroller.markNum+1)*scroller.li.width()+'px'
                });
                jq(this).addClass(''+settings.markLiClass+'');  
          });   
         
          /*scroller.markLi.click(function(){
            scroller.markNum = scroller.markLi.index(jq(this));
            scroller.li.removeClass(''+settings.play+'');
            scroller.li.eq(scroller.markNum+1).addClass(''+settings.play+'');
            scroller.markLi.removeClass(''+settings.markLiClass+'');
            obj.animate({
                left:'-'+(scroller.markNum+1)*scroller.li.width()+'px'
            });
            jq(this).addClass(''+settings.markLiClass+'');

          });*/
        };
        scroller.li.hover(function(){
                scroller.fn.off();
        },function(){
            scroller.fn.on();
            scroller.colorCout == 1
        });
        // 方法：滚动
        scroller.fn.goto=function(d){
          scroller.fn.off();
          if(settings.bLeft && settings.bRight){
            scroller.fn.removeControl();
          };
          
          obj.stop(true);
          if(!scroller.moveSlider){
             scroller.onCurNum = scroller.li.index(obj.find('li[class=""]'))  ;//play 位置
          }else{
             scroller.onCurNum = scroller.li.index(obj.find('li[class="'+settings.play+'"]'))  ;//play 位置   
          };
         
          if(scroller.opacity && !scroller.moveSlider){
            scroller.li.eq(scroller.onCurNum).addClass(''+settings.play+'').stop(1, 1).animate({
              opacity:"0",
              filter:"alpha(opacity=0)"
            },settings.speed,function(){
                jq(this).css({display:"none",opacity:"1"});
            });
            //console.log(scroller.onCurNum)
          
           /*scroller.li.eq(scroller.onCurNum).css("opacity",'0.5').removeClass(''+settings.play+'').animate({
              opacity:"0",
              filter:"alpha(opacity=0)"
            },settings.speed,function(){
                jq(this).css("display","none");
            });*/
          };
          
          if(settings.fontLi){
            scroller.fontLi.addClass(''+settings.play+'').stop(1, 1).animate({
              opacity:"0",
              filter:"alpha(opacity=0)"
            },0,function(){
                jq(this).css({display:"none",opacity:"1"});
            });;
          };
          switch(d){

            case "bRight":
            //滑动
            if(scroller.moveSlider && (scroller.onCurNum+1) == scroller.itemSum){//5
                scroller.totalWidth = scroller.itemSum * scroller.li.width();
                obj.css('left','-'+scroller.li.width()+'px');
                obj.animate({left:'-'+2*scroller.li.width()+'px'});
                scroller.li.removeClass(''+settings.play+'');
                scroller.li.eq(2).addClass(''+settings.play+'');
                if(settings.markSlider){scroller.markLi.removeClass(''+settings.markLiClass+'').eq(1).addClass(''+settings.markLiClass+'');}

            }else if(scroller.moveSlider && scroller.onCurNum == 1){
                obj.animate({left:'-'+2*scroller.li.width()+'px'});
                scroller.li.removeClass(''+settings.play+'');
                scroller.li.eq(2).addClass(''+settings.play+'');
                if(settings.markSlider){scroller.markLi.removeClass(''+settings.markLiClass+'').eq(1).addClass(''+settings.markLiClass+'');}
            }else if(scroller.moveSlider && scroller.onCurNum != scroller.itemSum){//1-4
                obj.animate({
                 left:'-'+(scroller.onCurNum+1)*scroller.li.width()+'px'
                });
                scroller.li.removeClass(''+settings.play+'');
                scroller.li.eq(scroller.onCurNum+1).addClass(''+settings.play+'');
                
                /*if(settings.markSlider && (scroller.onCurNum+2) == scroller.itemSum){
                    scroller.markLi.removeClass(''+settings.markLiClass+'').eq(0).addClass(''+settings.markLiClass+'');
                }else{
                    scroller.markLi.removeClass(''+settings.markLiClass+'').eq(scroller.onCurNum).addClass(''+settings.markLiClass+'');
                }*/
                if(settings.markSlider) {
                    if((scroller.onCurNum+2) == scroller.itemSum){
                        scroller.markLi.removeClass(''+settings.markLiClass+'').eq(0).addClass(''+settings.markLiClass+'');
                    }else{
                        scroller.markLi.removeClass(''+settings.markLiClass+'').eq(scroller.onCurNum).addClass(''+settings.markLiClass+'');
                    }   
                }
            }
            //渐隐
            if(((scroller.onCurNum+1) == scroller.itemSum) && !scroller.moveSlider ){
                if(settings.bgColor != "" && settings.bgLayer !="" && settings.bgColor.length == scroller.itemSum && settings.bgColor != false){
                    jq('.'+settings.bgLayer+'').css('background',''+settings.bgColor[0]+'');
                };
                jq('.'+settings.numClass+'').html("<em>1</em> / "  + scroller.itemSum);
                scroller.li.eq(0).stop(1, 1).css('opacity','0.5').removeClass(''+settings.play+'').css("display",'block').animate({opacity:"1"},settings.speed); 
                if(settings.fontLi) scroller.fontLi.eq(0).removeClass(''+settings.play+'').css("display",'block').animate({opacity:"1"},settings.speed); 

                //sisi
                if(settings.markSlider){scroller.markLi.removeClass(''+settings.markLiClass+'').eq(0).addClass(''+settings.markLiClass+'');}
                //sisi
                

            }else if(((scroller.onCurNum+1) != scroller.itemSum) && !scroller.moveSlider ){
                if(settings.bgColor != "" && settings.bgLayer !="" && settings.bgColor.length == scroller.itemSum && settings.bgColor != false){
                    jq('.'+settings.bgLayer+'').css('background',''+settings.bgColor[scroller.onCurNum+1]+'');
                }; 
                jq('.'+settings.numClass+'').html("<em>"+( scroller.onCurNum + 2)+"</em> / "  + scroller.itemSum);

                scroller.li.eq(scroller.onCurNum+1).stop(1, 1).css('opacity','0.5').removeClass(''+settings.play+'').css("display",'block').animate({opacity:"1"},settings.speed);


                if(settings.fontLi) scroller.fontLi.eq(scroller.onCurNum+1).removeClass(''+settings.play+'').css("opacity",'1').animate({opacity:"1"},settings.speed,function(){
                    jq(this).css('display','block');
                
            });

            //sisi
            if(settings.markSlider){scroller.markLi.removeClass(''+settings.markLiClass+'').eq(scroller.onCurNum+1).addClass(''+settings.markLiClass+'');}  
            //sisi
            };
            break;
            case "bLeft":
            //滑动
            if(scroller.moveSlider && scroller.onCurNum == 0){//0
                scroller.totalWidth = scroller.itemSum * scroller.li.width();
                obj.css('left','-'+(scroller.itemSum-2)*scroller.li.width()+'px');
                obj.animate({left:'-'+(scroller.itemSum-3)*scroller.li.width()+'px'});
                scroller.li.removeClass(''+settings.play+'');
                scroller.li.eq(scroller.onCurNum-3).addClass(''+settings.play+'');
                if(settings.markSlider){scroller.markLi.removeClass(''+settings.markLiClass+'').eq(scroller.onCurNum-2).addClass(''+settings.markLiClass+'');};
                
            }else if(scroller.moveSlider && scroller.onCurNum == 1){
                obj.animate({left:'0px'});
                scroller.li.removeClass(''+settings.play+'');
                scroller.li.eq(0).addClass(''+settings.play+'');
                if(settings.markSlider){scroller.markLi.removeClass(''+settings.markLiClass+'').eq(scroller.onCurNum+2).addClass(''+settings.markLiClass+'');};
                
            }else if(scroller.moveSlider && scroller.onCurNum != scroller.itemSum){//1-4
                obj.animate({
                 left:'-'+(scroller.onCurNum-1)*scroller.li.width()+'px'
                });
                scroller.li.removeClass(''+settings.play+'');
                scroller.li.eq(scroller.onCurNum-1).addClass(''+settings.play+'');
                if(settings.markSlider){scroller.markLi.removeClass(''+settings.markLiClass+'').eq(scroller.onCurNum-2).addClass(''+settings.markLiClass+'');}
                
            };
            //渐隐
            if(scroller.onCurNum == 0  && !scroller.moveSlider ){
                if(settings.bgColor != "" && settings.bgLayer !="" && settings.bgColor.length == scroller.itemSum && settings.bgColor != false){
                      jq('.'+settings.bgLayer+'').css('background',''+settings.bgColor[scroller.itemSum-1]+'');
                  };
                scroller.li.eq(scroller.itemSum-1).stop(1, 1).css('opacity','0.5').removeClass(''+settings.play+'').css("display",'block').animate({opacity:"1"},settings.speed,function(){
                    jq(this).css('display','block');
                });

                if(settings.fontLi) scroller.li.eq(scroller.itemSum-1).removeClass(''+settings.play+'').css("display",'block').animate({opacity:"1"},settings.speed); 
                jq('.'+settings.numClass+'').html("<em>"+scroller.itemSum+"</em> / "  + scroller.itemSum);

                //sisi
                if(settings.markSlider){scroller.markLi.removeClass(''+settings.markLiClass+'').eq(scroller.itemSum-1).addClass(''+settings.markLiClass+'');};
                //sisi
                

            }else if(scroller.onCurNum != 0  && !scroller.moveSlider ){
                if(settings.bgColor != "" && settings.bgLayer !="" && settings.bgColor.length == scroller.itemSum && settings.bgColor != false){
                    jq('.'+settings.bgLayer+'').css('background',''+settings.bgColor[scroller.onCurNum-1]+'');
                };
                scroller.li.eq(scroller.onCurNum-1).stop(1, 1).css('opacity','0.5').removeClass(''+settings.play+'').css("display",'block').animate({opacity:"1"},settings.speed);

                if(settings.fontLi) scroller.fontLi.eq(scroller.onCurNum-1).removeClass(''+settings.play+'').css("display",'block').animate({opacity:"1"},settings.speed);  
                jq('.'+settings.numClass+'').html("<em>"+( scroller.onCurNum )+"</em> / "  + scroller.itemSum);

                //sisi
                if(settings.markSlider){scroller.markLi.removeClass(''+settings.markLiClass+'').eq(scroller.onCurNum-1).addClass(''+settings.markLiClass+'');};
                //sisi
                
            }
            break;

          }
          obj.queue(function(){
            if(settings.bLeft && settings.bRight ){
                 scroller.fn.removeControl();
                 scroller.fn.addControl();
            };
            if(scroller.auto){
                scroller.run=setTimeout(function(){
                     scroller.fn.goto(settings.direction);
               },scroller.times);
            };
           
            
            jq(this).dequeue();
          });
        };
            
        scroller.fn.on();
  };

  // 默认值
  jq.slider={defaults:{
      speed:800,      // 滚动速度
      time:4000,      // 自动滚动间隔时间
      play:"on",         //选中样式
      num:true,        //是否出现总数
      numClass:"slider_num" ,    // 总数显示区域
      auto:true,
      bLeft:true,                 //左控
      bRight:true ,            //右控
      direction:"bRight",  // 顺序
      fontLi:true,             //是否开启描述
      addControl:false,
      moveSlider:false,
      opacity:true,
      bgColor:false,
      //sisi
      markSlider:true,           //是否有轮播标记
      markClass:"slider_mark",       //轮播结构
      markLiClass:"mark_dot_on",        //轮播当前态class
      markEvent:"click"//点击跳转
      //sisi
  }};
}(jQuery);
function returnFalse(){
    return false;
};
// 浏览器判断
function checkBrowser(){
   var u = window.navigator.userAgent.toLocaleLowerCase(),
    msie = /(msie) ([\d.]+)/,
    chrome = /(chrome)\/([\d.]+)/,
    firefox = /(firefox)\/([\d.]+)/,
    safari = /(safari)\/([\d.]+)/,
    opera = /(opera)\/([\d.]+)/,
    ie11 = /(trident)\/([\d.]+)/,
    b = u.match(msie)||u.match(chrome)||u.match(firefox)||u.match(safari)||u.match(opera)||u.match(ie11);
    return {name: b[1], version: parseInt(b[2])};

};

//标签切换组件
!function(jq){
    jq.fn.tabSelect = function(settings){
       if(!this.length){returnFalse();};
       settings=jq.extend({},jq.slider.defaults,settings);
       var tabS = {},
           obj = this;
       tabS.fn = {};
       tabS.fn.curr = settings.play;
       obj.find('li').mouseenter(function(){
          jq(this).parent().find('li').removeClass(''+tabS.fn.curr+'');
          jq(this).addClass(''+tabS.fn.curr+'');
          var n = jq(this).parent().find('li').index(jq(this));
          jq(this).parent().siblings('div').hide();
          jq(this).parent().siblings('div').eq(n).show();
          if(jq(this).parent().attr('v') != '1'){
              jq("img.lazy1").lazyload({
                    placeholder : "http://img.to8to.com/front_end/bg/grey.gif",effect : "fadeIn",failurelimit : 54
              });
          };
          jq(this).parent().attr('v','1');

        });
    };
    jq.tabSelect = {defaults:{
       play:"on"  //选中样式

    }};
}(jQuery);

//判断是否安装Flash插件
function IsFlashEnabled() {
   var obj = checkBrowser(),
       re = false;
   if(obj.name == "msie" && obj.version == 6) {
       try{
            //IE
            var swf1 = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
       }catch(e){
           try{
                //FireFox,Chrome
                var swf2=navigator.plugins["Shockwave Flash"];
                if(swf2==undefined){
                    re = true;
                };
            }catch(ee){
               re = true;
            }
        };
  };
  return re;//false启用 true未启用
} 

jq(function() {
    jq("body").on("focus","input[type=text],input[type=password],textarea",function() {
        if (!jq(this).hasClass('outcontrol')) {
            jq(this).css("border-color","#96d5b9");
        }
    });
    jq("body").on("blur","input[type=text],input[type=password],textarea,select",function() {
        if (!jq(this).hasClass('outcontrolblur')) {
            jq(this).css("border-color","#ddd");
        }
    });
});

//设置初始UL宽度
(function(jq) {
    jq.fn.setWidth = function(margin) {
        var ul = jq(this),
            len = ul.find("li").length,
            w = ul.find("li").width() + margin,
            wAll = len*w;
                
        ul.css("width",wAll+"px");
    };
})(jQuery);

//图片切换
(function(jq) {
    jq.fn.slideTxq = function(settings) {
        var defaults = {
            derect:"left",//默认方向
            margin:13,
            leftBtn:".slide_l",
            rightBtn:".slide_r",
            time:500,//滑动一张时间
            num:5,//大于这个数滑动
            btnWrap:false//btn位置
        };
        settings = jq.extend(defaults,settings);
        var par = jq(this).parent(),
            ul = par.find("ul:eq(0)"),
            len = ul.find("li").length,
            w = ul.find("li").width() + settings.margin,
            btnL = par.nextAll(settings.leftBtn),
            btnR = par.nextAll(settings.rightBtn),
            that = jq(this),
            on_index = 0;
            
            
        if(settings.btnWrap) {
            btnL = par.find(settings.leftBtn);
            btnR = par.find(settings.rightBtn); 
        };

        jq(this).setWidth(settings.margin);
        if(settings.num < len) {
            jq(btnR).click(function() {
                if(!jq(that).is(":animated")) {
                    ul.animate({"margin-left":"-"+w+"px"},settings.time,function() {
                        ul.find("li:first").appendTo(ul);
                        ul.css("margin-left",0);
                    });
                };
            });
            jq(btnL).click(function() {
                if(!jq(that).is(":animated")) {
                    ul.find("li:last").prependTo(ul).parent().css("margin-left",-w+"px");   
                    ul.animate({"margin-left":"+="+w+"px"},settings.time);  
                };
            }); 
        };
    };
})(jQuery);

//大的TAB切换
(function(jq) {
    jq.fn.tabToggle = function(settings) {
        var defaults = {
            target1: 'li',
            togClass: 'on',
            toggleDiv: false,
            fold: false,
            togDivObj: ''
        };

        var settings = jq.fn.extend({}, defaults, settings),
            li = jq(this).find(settings.target1),
            len = li.length,
            that = jq(this);

        li.click(function() {           
            if(!settings.fold) {
                jq(this).addClass(settings.togClass).siblings().removeClass(settings.togClass);
                if(settings.toggleDiv) {//需要切换DIV
                    var idx = li.index(jq(this)),
                        div = jq(settings.togDivObj);
                    div.hide().eq(idx).show();
                }
            } else {
                jq(this).toggleClass(settings.togClass).siblings('dd').toggle();
            }
        });
    };
})(jQuery);

//占位符处理
(function(jq) {
    jq.fn.placeholder = function(settings) {
        var defaults = {
            oLabel: 'em',
            derc: 'next',
            eType: 'keydown'
        };

        var settings = jq.fn.extend({}, defaults, settings);
        if(!(settings.derc == 'next'&&jq(this).next(settings.oLabel).is(':hidden') || settings.derc != 'next'&&jq(this).prev(settings.oLabel).is(':hidden'))) {//占位符隐藏时不清空
            jq(this).val('');
        } 
        
        jq(this).on(settings.eType + ' input', function() {
            var derc = settings.derc,
                labl = settings.oLabel;
            if(derc == 'next') {
                jq(this).next(labl).hide();
            } else {
                jq(this).prev(labl).hide();
            }
        });

        jq(this).on('blur', function() {
            var val = jq(this).val(),
                derc = settings.derc,
                labl = settings.oLabel;
            if(val == '') {
                if(derc == 'next') {
                    jq(this).next(labl).show();
                } else {
                    jq(this).prev(labl).show();
                }
            }
        });

        if(settings.derc == 'next') {
            jq(this).next(settings.oLabel).on('click', function() {
                jq(this).prev().focus();
            });
        } else {
            jq(this).prev(settings.oLabel).on('click', function() {
                jq(this).next().focus();
            });
        }
    };
})(jQuery);


//完善招标资料
function indexSubZbStepOne(res,weixin_code, txtObj){
    var titleStr = '恭喜您申请成功！',
        successTitleStr = '恭喜您成功完善资料！',
        showStyleStr = '',
        paddTop = 0;

    if(txtObj) {
        titleStr = txtObj['titleStr'];
        successTitleStr = txtObj['successTitleStr'];
        showStyleStr = txtObj['showStyleStr'];
        paddTop = txtObj['padding-top'];
    }
    if(res.status==1) {
        window_box_close();

        var str = '<div class="mod_fbbox">'+
            '<div class="fbbox_s2">'+
                '<h3 class="fbbox_s2_t">'+titleStr+'</h3>'+
                '<p class="fbbox_s2_text">客服将在24小时之内联系您，请保持手机通畅。现在请您完善详细资料，以便我们尽快为您安排服务。</p>'+
                '<div class="clear">';
                 if(res.sendmobiletime==''||res.sendmobiletime==0||typeof(res.sendmobiletime)=='undefined'||res.sendmobiletime==null){
                     str +='<div class="s2_line">'+
                            '<label for="" class="label"><span>*</span>&nbsp;量房时间</label>'+
                            '<div class="s2_element">'+
                              '<div>'+
                                '<select class="select" name="sendmobiletime" id="sendmobiletime" style="border-color: #940b13;">'+
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
                                '<input type="text" class="text" name="oarea" id="oarea" maxlength="4" style="border-color: #940b13;"><em class="text_uni">㎡</em>'+
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
                                '<select class="select" name="zxtime" id="zxtime" style="border-color: #940b13;">'+
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
                                '<select class="select" name="hometype" id="hometype" style="border-color: #940b13;">'+
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
                                '<select class="select" name="zxys" id="zxys" style="border-color: #940b13;">'+
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
                                '<select class="select" name="zxtype" id="zxtype" style="border-color: #940b13;">'+
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
                                '<input class="text" type="text" style="border-color: #940b13;" name="address" id="address">'+
                              '</div>'+
                          '</div>'+
                        '</div>';
                }
                        str += '<input type="hidden" id="User_City_1" value="'+res.city+'" name="User_City"><input type="hidden" value="'+res.tmpYid+'" name="tyid" id="tyid"><input type="button" value="提交" class="mod_fbbox_btn" onclick="selectConfirmZbOver(\''+successTitleStr+'\', \''+showStyleStr+'\', \''+paddTop+'\');">'+
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

var zb_wxstart_msg = '扫描二维码，实时获取装修进度';
var zb_wxinfo_msg = '去完善更多资料吧，我们将优先为您免费服务！';
//招标完善资料最后一步
function selectConfirmZbOver(successTitleStr, showStyleStr, paddTop){
    var zb_wxover_msg = '<p class="pb">想省心省钱不被坑 来装修学堂就够了。</p><a target="_blank" class="mod_fbbox_btn btn_01af63" href="http://www.to8to.com/huodong/tuangou.php?id=126&ptag=5_6_1">免费学装修</a>';
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
    /*      status_request.abort();
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
          url: "/zb/index.php",
          data: {invite:2,User_City:User_City,tyid:tyid,oarea:oarea,zxys:zxys,zxtype_two:zxtype,zxtime:zxtime,sendmobiletime:sendmobiletime,hometype:hometype,address:address},
          success:function(result){
              jq ('.window_box').remove();
              jq ('.translucence_layer').remove();
              if (typeof(JSON) == "undefined") {
                var res = eval("(" + result + ")")
              } else {
                var res = JSON.parse(result)
              };
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
                  if(res.cityname != "深圳" && res.cityname != "南京" && res.cityname != "广州") {
					  zb_wxover_msg = "您的申请正在加速处理中...";
				  }
				  var successStr ='<div class="mod_fbbox">'+
                                      '<div class="fbbox_s3">'+
                                          '<div class="mod_pagetip">'+
                                              '<span class="mod_pagetip_ico"><em class="ico_tip_ok"></em></span>'+
                                              '<div class="mod_pagetip_bd compatibility" style="padding-top:'+paddTop+'">'+
                                                  '<div class="mod_pagetip_title">'+successTitleStr+'</div>'+
                                                  '<div class="mod_pagetip_info" style="display:'+showStyleStr+'">'+zb_wxover_msg+'</div>'+
                                              '</div>'+
                                          '</div>'+
                                      '</div>'+
                                  '</div>';
                stop_code_status();//关闭微信扫码状态请求
                
                jq('.window_box').windowBox({
                  width:480,
                  title:"提示",
                  wbcStr:successStr
              });

              };
          }
    });

/*******************************微信招标************************************/
/*                                  }
                        else
                        {
                            alert(res.msg);
                        }

                    }
              });
/*******************************微信招标************************************/ };
//未开通城市是失败
function indexYYFail(cityname) {
      var failStr = '<div class="apply_fail"><span class="as_fail"></span><strong>非常抱歉,您当前的城市'+cityname+'尚未开通<br />装修服务，敬请期待！</strong></div>';
      jq('.window_box').windowBox({
        width:480,
        height:200,
        title:"提示",
        wbcStr:failStr,
        closeTime:6000
      }); 
    };

//lazyload frame
(function($,window){var $window=$(window);$.fn.lazyload=function(options){var elements=this;var $container;var settings={threshold:0,failure_limit:0,event:"scroll",effect:"show",container:window,data_attribute:"original",skip_invisible:true,appear:null,load:null};function update(){var counter=0;elements.each(function(){var $this=$(this);if(settings.skip_invisible&&!$this.is(":visible")){return};if($.abovethetop(this,settings)||$.leftofbegin(this,settings)){}else if(!$.belowthefold(this,settings)&&!$.rightoffold(this,settings)){$this.trigger("appear")}else{if(++counter>settings.failure_limit){return false}}})};if(options){if(undefined!==options.failurelimit){options.failure_limit=options.failurelimit;delete options.failurelimit};if(undefined!==options.effectspeed){options.effect_speed=options.effectspeed;delete options.effectspeed};$.extend(settings,options)};$container=(settings.container===undefined||settings.container===window)?$window:$(settings.container);if(0===settings.event.indexOf("scroll")){$container.bind(settings.event,function(event){return update()})};this.each(function(){var self=this;var $self=$(self);self.loaded=false;$self.one("appear",function(){if(!this.loaded){if(settings.appear){var elements_left=elements.length;settings.appear.call(self,elements_left,settings)};$("<img />").bind("load",function(){$self.hide().attr("src",$self.data(settings.data_attribute))[settings.effect](settings.effect_speed);self.loaded=true;var temp=$.grep(elements,function(element){return!element.loaded});elements=$(temp);if(settings.load){var elements_left=elements.length;settings.load.call(self,elements_left,settings)}}).attr("src",$self.data(settings.data_attribute))}});if(0!==settings.event.indexOf("scroll")){$self.bind(settings.event,function(event){if(!self.loaded){$self.trigger("appear")}})}});$window.bind("resize",function(event){update()});update();return this};$.belowthefold=function(element,settings){var fold;if(settings.container===undefined||settings.container===window){fold=$window.height()+$window.scrollTop()}else{fold=$(settings.container).offset().top+$(settings.container).height()};return fold<=$(element).offset().top-settings.threshold};$.rightoffold=function(element,settings){var fold;if(settings.container===undefined||settings.container===window){fold=$window.width()+$window.scrollLeft()}else{fold=$(settings.container).offset().left+$(settings.container).width()};return fold<=$(element).offset().left-settings.threshold};$.abovethetop=function(element,settings){var fold;if(settings.container===undefined||settings.container===window){fold=$window.scrollTop()}else{fold=$(settings.container).offset().top};return fold>=$(element).offset().top+settings.threshold+$(element).height()};$.leftofbegin=function(element,settings){var fold;if(settings.container===undefined||settings.container===window){fold=$window.scrollLeft()}else{fold=$(settings.container).offset().left};return fold>=$(element).offset().left+settings.threshold+$(element).width()};$.inviewport=function(element,settings){return!$.rightofscreen(element,settings)&&!$.leftofscreen(element,settings)&&!$.belowthefold(element,settings)&&!$.abovethetop(element,settings)};$.extend($.expr[':'],{"below-the-fold":function(a){return $.belowthefold(a,{threshold:0})},"above-the-top":function(a){return!$.belowthefold(a,{threshold:0})},"right-of-screen":function(a){return $.rightoffold(a,{threshold:0})},"left-of-screen":function(a){return!$.rightoffold(a,{threshold:0})},"in-viewport":function(a){return!$.inviewport(a,{threshold:0})},"above-the-fold":function(a){return!$.belowthefold(a,{threshold:0})},"right-of-fold":function(a){return $.rightoffold(a,{threshold:0})},"left-of-fold":function(a){return!$.rightoffold(a,{threshold:0})}})})(jQuery,window);


jq(function() {
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

        jq.ajax({ 
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
                var labelObj = jq('#nav_user_data');
                labelObj.html(str_havel);

                labelObj.children('div').hover(function() {
                    jq(this).toggleClass('on');
                    jq(this).children('ul').toggle();
                    jq(this).children('a').find('i.triangle_down').toggleClass('triangle_up');
                });    
            }    
        });        
    };
});

//申请次数超过五次

function overFive(){
    var str = '<span style="float:left; width:100%; height:14px;line-height:14px;margin:20px 0;text-align:center;*padding-bottom:20px">申请次数超过五次</span>';
    jq('.window_box').remove();
    jq('.translucence_layer').remove();
    jq('.window_box').windowBox({
            width:480,
            title:"提示",
            wbcStr:str
    });
}

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
                        status_num=0
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
                        status_num=0
                    } 
                }      
            } 
        });      
        
    }
    
    
function getnewcode(tmpYid){
    
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

//ie6兼容position:fixed
//使用注意：在需要固定的元素上加上 tag='floatNavigator' 属性
function fixedPositionCompatibility() {
//判断是否ie6浏览器
if (isIE6) {
  var navigators = jq("[tag='floatNavigator']");
  if (!navigators.length) return;
  //判断每个浮层是靠顶部固定还是底部固定
  jq.each(navigators, function() {
    this.top = jq(this).css("top");
    this.bottom = jq(this).css("bottom");
    this.isTop = this.top == "auto" ? false : true;
    if(!this.isTop){
      this.bottom = 0;
    }
  });

  jq(window).bind("scroll", function() {
    var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
    jq.each(navigators, function() {
      var value = this.isTop ? scrollTop + parseInt(this.top) + "px" : scrollTop + jq(window).height() - jq(this).outerHeight() - parseInt(this.bottom) + "px";
      jq(this).css("top", value);
    });
  });
}
}

var weChatQrcode = {
    init: function() {

        var _this = this;
        _this.destroy();
        if ( isIE6 ) {
            return false;
        }

        //需要剔除的页面
        if(!_this.validPage()){
            return;
        }
        jq(function() {
            var wechat_barcode = jq('#wechat_barcode'),
                wechat_broadside = jq('#wechat_broadside');
            if ( !wechat_barcode.length || !wechat_broadside.length ) {
                try {
                    _this.createQrcodeTmp();
                } catch (e) {
                    return;
                }
            }

            _this.initPosition();
            _this.bindQrcodeEvents();
        })
    },
    validPage: function(){
        var removeReg = /^http:\/\/xiaoguotu.to8to.com\/[A-Za-z0-9]+.html$/;
        var url = window.location.href;
        if(removeReg.test(url)){
            return false;
        }else{
            return true;
        }
    },
    destroy: function() {
        try {
            var wechat_barcode = jq('#wechat_barcode'),
                wechat_broadside = jq('#wechat_broadside');
            wechat_barcode.remove();
            wechat_broadside.remove();
        } catch (e) {
            return;
        }
    },
    checkScreen: function() {
        var midCW = jq('.narrow_980').length ? 980 : 1220;

        return (jq('body').width() - midCW) / 2 > 120;
    },
    createQrcodeTmp: function() {
        this.destroy();
        var qrcodeTmp = '<div class="wechat_barcode" id="wechat_barcode">\
                            <div class="we_inwrap">\
                            <a target="_blank" href="http://www.to8to.com/apps/index.php?act=apps_wechat">\
                            <i class="my_wechat_bar"></i></a>\
                            <i class="my_wechat_cancel" id="cancel_wechat_qrcode"></i>\
                        </div>\
                            </div>\
                        <div class="wechat_broadside" id="wechat_broadside">\
                            <i class="my_wechat_broadside"></i>\
                        </div>';
        jq('body').append(qrcodeTmp);
    },
    showQrcode: function () {
        if(!this.checkScreen()) {
            jq(".wechat_barcode").css({"left":"auto","right":"0"}).show();
        }else{
            jq(".wechat_barcode").show();
        }
    },
    bindQrcodeEvents: function() {
        var wechat_barcode = jq('#wechat_barcode'),
            wechat_broadside = jq('#wechat_broadside'),
            closeBtn = jq('#cancel_wechat_qrcode'),
            _this = this,
            _offsetTop_bar = wechat_barcode.offset().top,
            _offsetTop_bro = wechat_broadside.offset().top;

        // IE6 下fix 问题
        if ( isIE6 ) {
            jq(window).bind('scroll', function() {
                var _topWin = jq(window).scrollTop();
                wechat_barcode.css({ top: (_topWin + _offsetTop_bar) + 'px'  });
                wechat_broadside.css({ top: (_topWin + _offsetTop_bro) + 'px'  } );
            })
        }

        closeBtn.bind('click', function () {
            wechat_barcode.hide();
            wechat_broadside.show().stop().animate({
                width: '41px'
            }, 500)
        });
        /*jq("body").on('click','#cancel_wechat_qrcode',function () {
         wechat_barcode.hide();
         wechat_broadside.show().animate({
         width: '41px'
         }, 500)
         });*/
        wechat_broadside.bind('click', function () {
            wechat_broadside.stop().animate({
                width: '0px'
            }, 500, function() {
                wechat_broadside.hide();
            })
            _this.showQrcode();
        });
        /*jq("body").on('click', "#wechat_broadside",function () {
         wechat_broadside.animate({
         width: '0px'
         }, 500, function() {
         wechat_broadside.hide();
         })
         _this.showQrcode();
         });*/
    },
    initPosition: function() {
        var wechat_barcode = jq('#wechat_barcode'),
            wechat_broadside = jq('#wechat_broadside'),
            _this = this;
        if(!this.checkScreen()){
            wechat_barcode.hide();
            wechat_broadside.show().css({'width':'41px'});
        }else{
            _this.showQrcode();
            wechat_broadside.hide().css({'width': '0px'});
        }
    }
}

//头部品宣位埋点js
jq(function(){
    jq("#side_slider li").click(function(){
        try{clickStream.getCvParams(jq(this).attr("j_data"));}catch(e){}
    });
});

function stop_code_status(){
    if(!(typeof status_request == 'undefined')) {
        status_request.abort();
    }
    status_num=0;
    window_box_close();
}


function zb_first_pop(weixin_code,yid){
    var successStr ='<div class="mod_fbbox mod_fbbox_wxservice">'+
                    '<div class="fbbox_s1">'+
                    
                        '<div class="s1_hd">恭喜您申请成功!</div>'+
                        '<div class="s1_hd_sub">土巴兔客服将于24小时内联系您，请您保持手机畅通。</div>'+
                        '<div class="service_img">'+
                            '<img src="'+weixin_code+'" alt="" id="weixin_img">'+
                            '<div class="mod_pagetip mod_pagetip_s mod_pagetips_noinfo" id="status_success" style="display:none" >'+
                              '<span class="mod_pagetip_ico"><em class="ico_tip_ok_s"></em></span>'+
                              '<div class="mod_pagetip_bd">'+
                                  '<div class="mod_pagetip_title">扫描成功</div>'+
                              '</div>'+
                            '</div>'+
                            '<div class="mod_pagetip mod_pagetip_s" style="display:none" id="status_fail"> <!-- 二维码失效 -->'+
                              '<span class="mod_pagetip_ico"><em class="ico_tip_warn_s"></em></span>'+
                              '<div class="mod_pagetip_bd">'+
                                  '<div class="mod_pagetip_title">二维码失效</div>'+
                                  '<div class="mod_pagetip_info">请点击<a href="javascript:;" onclick="getnewcode('+yid+')">刷新二维码</a></div>'+
                              '</div>'+
                            '</div>'+
                        '</div>'+
                        
                    '</div>'+
                '</div>'
                ;
    return successStr;
}
