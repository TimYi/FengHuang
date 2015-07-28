$(function() {
    var g = {};
    g.phone = "";
    g.imgCodeId = "";
    g.sendCode = false;
    g.sendTime = 60;
    g.username = Base.userName;
    g.token = Utils.offLineStore.get("token", false) || "";
    g.liveId = Utils.getQueryString("id");
    g.totalPage = 1;
    g.currentPage = 1;
    g.paseSize = 2;
    g.httpTip = new Utils.httpTip({});
    g.listdata = [];
    var currentPage = 1;


    //验证登录状态
    g.loginStatus = Utils.getUserInfo();

    getLiveDetails();
    getLiveDayDetails();
    getComments();
    $("#issuebtn").bind("click",issueComment);

    function getLiveDetails() {
        var condi = {};
        condi.id = g.liveId;

        sendGetLiveDetailsHttp(condi);
    }

    function getLiveDayDetails() {
        var condi = {};
        condi.id = g.liveId;
        condi.page = g.currentPage;
        condi.size = g.paseSize;
        sendGetLiveDayDetailsHttp(condi);
    }

    function sendGetLiveDetailsHttp(condi) {
        var url = Base.live + "/" + condi.id;
        g.httpTip.show();
        $.ajax({
            url: url,
            data: condi,
            type: "GET",
            dataType: "json",
            context: this,
            global: false,
            success: function(data) {
                console.log("sendGetLiveDetailsHttp", data);
                var status = data.status || "";
                if (status == "OK") {
                    changeLiveDetailHtml(data.result);
                } else {
                    var msg = data.error || "";
                    alert("获取装修直播详情错误:" + msg);
                }
                g.httpTip.hide();
            },
            error: function(data) {
                g.httpTip.hide();
            }
        });
    }

    function sendGetLiveDayDetailsHttp(condi) {
        var url = Base.live + "/" + condi.id + "/details";
        g.httpTip.show();
        $.ajax({
            url: url,
            data: condi,
            type: "GET",
            dataType: "json",
            context: this,
            global: false,
            success: function(data) {
                console.log("sendGetLiveDayDetailsHttp", data);
                var status = data.status || "";
                if (status == "OK") {
                    changeLiveDayHtml(data.result);
                } else {
                    var msg = data.error || "";
                    alert("获取装修直播详情错误:" + msg);
                }
                g.httpTip.hide();
            },
            error: function(data) {
                g.httpTip.hide();
            }
        });
    }

    function changeLiveDetailHtml(data) {
        var obj = data || {};
        var id = obj.id;
        var name = obj.name || "";
        var village = obj.village || "";
        var area = obj.area || "";
        var house = obj.house || "";
        var startDate = obj.startDate || "";

        var titleStr = village + " / " + area + '㎡ / ' + house;
        $("#liveTitle").html(titleStr);
        $("#liveDistrict").html(village);
        $("#liveStart").html(startDate);
    }

    function changeLiveDayHtml(data) {
        var obj = data || {};
        var lives = obj.result || [];
        var html = [];
        for (var i = 0, len = lives.length; i < len; i++) {
            var d = lives[i];
            var day = d.day || "";
            var date = d.date || "";
            var title = d.title || "";
            var tips = d.tips || "";
            date = date.substring(0, 10);
            var year = "";
            var month = "";
            var myday = "";
            if (date.length >= 10) {
                var arr = date.split("-");
                year = arr[0];
                month = arr[1] - 0;
                myday = arr[2];
            }
            html.push('<div class="cd-timeline-block">');
            html.push('<div class="cd-timeline-img">' + day + '</div>');
            html.push('<div class="cd-timeline-content">');
            html.push('<h2>' + title + '</h2>');
            html.push('<p>温馨提示：' + tips + '</p>');
            html.push('<p class="tit">施工人员</p>');
            html.push('<p class="t-wrap">');
            var workers = d.workers || [];
            var typeid = {
                "404040e64dfbe06b014dfbe07c7c0001": "综合工",
                "404040e64dfbe06b014dfbe07caa0002": "水工",
                "404040e64dfbe06b014dfbe07cca0003": "电工"
            };
            for (var j = 0; j < workers.length; j++) {
                var name = workers[j].name || "";
                var tid = workers[j].type || "";
                if (tid !== "") {
                    type = typeid[tid.id] || "";
                }
                html.push(type + '：' + name + '  ');
            }
            html.push('</p>');
            html.push('<p class="tit">施工图片</p>')
            //图片轮播begin
            html.push('<ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-gallery-default" data-am-gallery="{ pureview: true }">');
            var pics = d.pics || [];
            for (var k = 0; k < pics.length; k++) {
                var path = pics[k].url || "http://s.amazeui.org/media/i/demos/bing-1.jpg";
                html.push('<li><div class="am-gallery-item">');
                html.push('<a href="'+path+'" class=""><img src="' + path + '" /></a></div></li>');
            }
            html.push('</ul>');
            //图片轮播end
            html.push('<p class="tit">微信互动</p>');
            html.push('<ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-gallery-default" data-am-gallery="{ pureview: true }">');
            var interactPics = d.interactPics || [];
            for (var n = 0; n < interactPics.length; n++) {
                var path = interactPics[n].url || "http://s.amazeui.org/media/i/demos/bing-1.jpg";
                html.push('<li><div class="am-gallery-item">');
                html.push('<a href="'+path+'" class=""><img src="' + path + '" /></a></div></li>');
            }
            html.push('</ul>');
            html.push('<span class="cd-date">' + year + '-' + month + '-' + myday + '</span>');
            html.push('</div>');
        }
        $("#cd-timeline").html(html.join(''));
		$.AMUI.gallery.init();
    }

    function getComments(){
        var condi = {};
        condi.sourceid = g.liveId;
        condi.page = currentPage++;
        condi.size = 5;
        sendGetCommentHttp(condi);
    }

    function sendGetCommentHttp(condi){
        var url = Base.comments;
        g.httpTip.show();
        $.ajax({
            url:url,
            data:condi,
            type:"GET",
            dataType:"json",
            context:this,
            global:false,
            success: function(data){
                var status = data.status || "";
                console.log(data);
                if(status == "OK"){
                    changeCommentsHtml(data.result);
                }
                else{
                    var msg = data.error || "";
                    alert("获取直播评论错误:" + msg);
                }
                g.httpTip.hide();
            },
            error:function(data){
                g.httpTip.hide();
            }
        });
    }

    function changeCommentsHtml(data){
        var obj = data.result || [];
        var page = data.page;
        var totalPages = data.totalPages;
        var totalCount = data.totalCount;
        var html = [];
        var data = '';
        var $commentList = $("#commentList");
        for(var i = obj.length-1; i >=0 ; i--){
            var column = obj[i].column || "";
            var createTime = obj[i].createTime || "";
            var content = obj[i].content || "";
            var realName = obj[i].user.realName || "";

            html.push('<li><p><i class="am-icon-user"></i>');
            html.push('<b>'+ realName +'</b></p>');
            html.push('<p>'+ content +'</p>');
            html.push('<p class="time">'+ createTime +'</p></li>');
        }
        var data = html.join('');
        var $data = $(data);
        $commentList.append($data);
        if(page<totalPages){
            $('.load-more').show().bind('click',getComments);
        }else{
            $('.load-more').hide();
        }
    }

    function issueComment(){
        /*
        column:栏目标题
        type:资源类型，为枚举值：ARTICAL,PACKAGE,CASE;
        token:用户凭据
        replyid:如果是回复某条评论，传被评论的id，否则不传
        url:当前地址url，可选
        content:回复的内容
        */
        var condi = {};
        condi.sourceid = g.liveId;
        condi.column = "栏目标题";
        condi.type = "LIVE";
        condi.token = g.token;
        condi.replyid = "";
        condi.url = location.href;
        condi.content = $("#message").val() || "";

        if(g.loginStatus){
            if(condi.content !==""){
                sendAddCommentHttp(condi);
            }
            else{
                Utils.alert("输入评论内容");
                $("#message").focus();
            }
        }
        else{
            alert("请先登录");
            location.href = "login.html";
        }
    }

    function sendAddCommentHttp(condi){
        var url = Base.commentUrl;
        g.httpTip.show();
        $.ajax({
            url:url,
            data:condi,
            type:"POST",
            dataType:"json",
            context:this,
            global:false,
            success: function(data){
                var status = data.status || "";
                if(status == "OK"){
                    alert("评论发表成功");
                    $("#message").val("");
                    //location.reload();
                    getComments();
                }else{
                    var msg = data.error || "";
                    alert("添加案例评论错误:" + msg);
                }
                g.httpTip.hide();
            },
            error:function(data){
                g.httpTip.hide();
            }
        });
    }

});
