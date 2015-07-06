$(function() {
    var g = {};
    g.phone = "";
    g.imgCodeId = "";
    g.sendCode = false;
    g.sendTime = 60;
    g.username = Base.userName;
    g.token = Utils.offLineStore.get("token", false);
    g.packageId = Utils.getQueryString("id");
    g.totalPage = 1;
    g.currentPage = 1;
    g.paseSize = 10;
    g.httpTip = new Utils.httpTip({});
    g.listdata = [];
    //验证登录状态
    g.loginStatus = Utils.getUserInfo();

    getPackageDetail();

    function getPackageDetail() {
        var condi = {};
        condi.id = g.packageId;

        sendGetPackageDetailsHttp(condi);
    }

    function sendGetPackageDetailsHttp(condi) {
        var url = Base.packageDetail + "/" + condi.id;
        g.httpTip.show();
        $.ajax({
            url: url,
            data: condi,
            type: "GET",
            dataType: "json",
            context: this,
            global: false,
            success: function(data) {
                console.log("sendGetPackageDetailsHttp", data);
                var status = data.status || "";
                if (status == "OK") {
                    changePackageDetailHtml(data.result);
                } else {
                    var msg = data.error || "";
                    alert("获取套餐详情错误:" + msg);
                }
                g.httpTip.hide();
            },
            error: function(data) {
                g.httpTip.hide();
            }
        });
    }

    function changePackageDetailHtml(data) {
        var html = [];
        var obj = data;
        var id = obj.id || "";
        var mainPic = obj.mainPic || "";
        if(mainPic){
            mainPic = mainPic.url;
        }else{
            mainPic = "http://www.talmd.cn/upload/upfiles/part/201401/113890851569826.jpg_870.jpg";
        }
        var price = obj.price || "";
        var deposit= obj.deposit;
        var decorate = obj.decorate || "";
        var description = obj.description || "";
        var status = obj.status || "";
        var inStock = obj.inStock - 0 || 0;
        var saleNumber = obj.saleNumber - 0 || 0;
        var scrambleStartTime = obj.scrambleStartTime || "";
        var scrambleEndTime = obj.scrambleEndTime || "";
        var hasAppointed = obj.hasAppointed || false;
        //status = "SCRAMBLE";

        $("#mainPic").attr("src",mainPic);
        $("#pName").text(description);
        $("#price").text(price);
        $("#decoType").text(decorate);
        $("#deposit").text(deposit);

        if(status == "PREPARE"){
            html.push('<p>抢购开始时间：'+ scrambleStartTime +'</p>');
            html.push('<p class="b-wrap"><a href="javascript:void(0)">即将开始</a></p>');
        }else if(status == "SCRAMBLE" && inStock > saleNumber){
            html.push('<p><span class="tit">已售数量：</span>' + saleNumber + '套 / 共' + inStock + '套</p>');
            html.push('<p>抢购结束时间：'+ scrambleEndTime +'</p>');
            html.push('<p class="b-wrap">');
            if(hasAppointed){
                html.push('<a href="javascript:miaoSha(\'' + id + '\')">');
            }else{
                if(g.loginStatus){
                    var page = 699 + ".html?id=" + id;
                    html.push('<a href="javascript:alert(\'你还没有预约\');location.href=\'' + page + '\'">');
                }else{
                    var page = "login.html";
                    html.push('<a href="javascript:alert(\'请先登录\');location.href=\'' + page + '\'">');
                }
            }
            html.push('立即购买</a></p>');
        }else{
            html.push('<p class="b-wrap"><a href="javascript:void(0)">已抢完</a></p>');
        }
        $("#packageStatus").html(html.join(''));
    }

});
