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

    if(!g.loginStatus){
        location.href="login.html";
    }else{
        getCoupons();
    }
    $('.coupon-btn').bind('click',couponScram);

    function getCoupons(){
        var url = Base.couponScram;
        var condi = {};
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
                if(status == "OK"){
                    //g.id=data.result.id;
                }
                else{
                    Utils.alert("优惠券抢购获取失败");
                }
                g.httpTip.hide();
            },
            error:function(data){
                g.httpTip.hide();
            }
        });
    }

    function couponScram(){
        var url = Base.couponScram;
        var condi = {};
        condi.token = g.token;
        g.httpTip.show();
        $.ajax({
            url:url,
            data:condi,
            type:"POST",
            dataType:"json",
            context:this,
            global:false,
            success: function(data){
                console.log(data);
                if(data.status == 'OK'){
                    alert("优惠券抢购成功！")
                    location.href = "u_coupon.html?token="+g.token;
                }
                else{
                    var msg = data.errorDescription ;
                    alert(msg);
                }
                g.httpTip.hide();
            },
            error:function(data){
                g.httpTip.hide();
            }
        });
    }

    function miaoSha(id){
        var url = Base.scramble;
        var condi = {};
        condi.token = g.token;
        condi.id = id;
        condi.caseId = "";
        console.log(condi);
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
                    Utils.alert("抢购成功");
                    var orderId = data.result.id;
                    location.href = "paycheck.html?id=" + orderId;
                }
                else{
                    Utils.alert("抢购失败");
                }
                g.httpTip.hide();
            },
            error:function(data){
                g.httpTip.hide();
            }
        });
    }

    window.miaoSha = miaoSha;
});
