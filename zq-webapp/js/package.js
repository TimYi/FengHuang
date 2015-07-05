$(function(){
    var g = {};
    g.phone = "";
    g.imgCodeId = "";
    g.sendCode = false;
    g.sendTime = 60;
    g.username = Base.userName;
    g.token = Utils.offLineStore.get("token",false);
    g.page = Utils.getQueryString("p") - 0;
    g.totalPage = 1;
    g.currentPage = 1;
    g.paseSize = 20;
    g.httpTip = new Utils.httpTip({});
    g.listdata = [];
    g.userprofile = Utils.offLineStore.get("login_userprofile",false) || "";
    //验证登录状态
    g.loginStatus = Utils.getUserInfo();

    getPackages();

    function getPackages(){
        var url = Base.packagesUrl;
        var condi = {};
        condi.token = g.token;
        condi.page = 1;
        condi.size = 10;
        g.httpTip.show();
        $.ajax({
            url:url,
            data:condi,
            type:"GET",
            dataType:"json",
            context:this,
            global:false,
            success: function(data){
                console.log("getPackages",data);
                var status = data.status || "";
                if(status == "OK"){
                    changePackageList(data.result.result);
                }
                else{
                    Utils.alert("套餐列表获取失败");
                }
                g.httpTip.hide();
            },
            error:function(data){
                g.httpTip.hide();
            }
        });
    }

    function changePackageList(data){
        var html = [];
        for(var i = 0,len = data.length; i < len; i++){
            var obj = data[i];
            var id = obj.id || "";
            var price = obj.price || "";
            var decorate = obj.decorate || "";
            var description = obj.description || "";
            var status = obj.status || "";
            var inStock = obj.inStock - 0 || 0;
            var saleNumber = obj.saleNumber - 0 || 0;
            var scrambleStartTime = obj.scrambleStartTime || "";
            var scrambleEndTime = obj.scrambleEndTime || "";
            var hasAppointed = obj.hasAppointed || false;
            //status = "SCRAMBLE";
        
            html.push('<li><a href="package_detail.html?id='+id+'"">');
            html.push('<div class="cbox"><i class="am-icon-gift"></i><br>');
            html.push('<span>'+ description +'</span></div></a></li>');
        }

        $("#packageList").html(html.join(''));
    }

});