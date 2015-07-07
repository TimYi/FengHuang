/**
 * file:我的留言
 * author:chenxy
 * date:2015-06-05
 */
//页面初始化
$(function() {
    var g = {};
    g.phone = "";
    g.imgCodeId = "";
    g.sendCode = false;
    g.sendTime = 60;
    g.username = Base.userName;
    g.token = Utils.getQueryString("token");
    g.page = Utils.getQueryString("p") - 0;
    g.totalPage = 1;
    g.currentPage = 1;
    g.paseSize = 20;
    g.httpTip = new Utils.httpTip({});
    g.listdata = [];

    var loginStatus = Utils.getUserInfo();
    if (!loginStatus) {
        location.replace("login.html");
    } else {
        getIngList();
    }

    $("#houseselect").bind("change", changeLive);

    function getLiveList() {
        var condi = {};
        condi.token = g.token;
        sendGetLiveListHttp(condi);
    }

    function sendGetLiveListHttp(condi) {
        g.httpTip.show();
        var url = Base.userlivesUrl;
        $.ajax({
            url: url,
            data: condi,
            type: "GET",
            dataType: "json",
            context: this,
            global: false,
            success: function(data) {
                g.httpTip.hide();
                var status = data.status || "";
                if (status == "OK") {
                    changeSelectHtml("houseselect", data.result);
                } else {
                    var msg = data.error || "";
                    alert("我的直播数据错误:" + msg);
                }
            },
            error: function(data) {
                g.httpTip.hide();
            }
        });
    }

    function changeSelectHtml(domid, data) {
        var option = [];
        var fid = "";
        for (var i = 0, len = data.length; i < len; i++) {
            var id = data[i].id || "";
            if (i == 0) {
                fid = id;
            }
            var name = data[i].house || "";
            var village = data[i].village || "";
            name = village + "-" + name;
            option.push('<option value="' + id + '"' + (i == 0 ? "selected" : "") + '>' + name + '</option>');
        }
        $("#" + domid).html(option.join(''));

        getIngList(fid);
    }

    function changeLive() {
        var id = $(this).val();
        getIngList(id);
    }

    function getIngList(id) {
        var condi = {};
        condi.token = g.token;
        condi.id = id;
        sendGetIngListHttp(condi);
    }

    function sendGetIngListHttp(condi) {
        g.httpTip.show();
        var url = Base.userlivesUrl;
        $.ajax({
            url: url,
            data: condi,
            type: "GET",
            dataType: "json",
            context: this,
            global: false,
            success: function(data) {
                console.log("sendGetIngListHttp", data);
                g.httpTip.hide();
                var status = data.status || "";
                if (status == "OK") {
                    changeIngListHtml(data.result);
                } else {
                    var msg = data.error || "";
                    alert("获取我的家装进度:" + msg);
                    if (msg == "您需要登录") {
                        location.href = "login.html";
                    }
                }
            },
            error: function(data) {
                g.httpTip.hide();
            }
        });
    }

    function changeIngListHtml(data) {
        data = data || {};
        var lid = data.id;
        var obj = data.lives || [];

        g.listdata = obj;

        if (obj.length > 0) {
            var html = [];

            for (var i = 0, len = obj.length; i < len; i++) {
                var id = obj[i].id || "";
                var day = obj[i].day || "";
                var date = obj[i].date || "";
                var title = obj[i].title || "";
                //var time = obj[i].createTime || "2015-06-02 10:00";

                html.push('<ul class="am-avg-sm-2 house-list"><li class="h-left">');
                html.push('<div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="javascript:void(0)"><div><ul class="uhouse">');
                html.push('<li class="ubig">' + title + '</li>');
                html.push('<li class="usmall">' + day + ' / ' + date + '</li></ul></div></a></div></li>');
                html.push('<li class="h-right"><div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="javascript:void(0)">');
                html.push('<div><i class="am-icon-angle-right"></i></div></a></div></li></ul>');
            }

            $("#myLive").html(html.join(''));
        }
    }
});
