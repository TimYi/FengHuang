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

    getPackages();

    function changeBtn(data){
        var $buy699 = $("#buy699");
        var $buy799 = $("#buy799");
        var obj = data.result;
        if(obj[0].hasScrambled ){
            $buy699.find("div").text("已抢购");
            return false;
        }else if(obj[0].hasAppointed){
            $buy699.find("div").text("立刻抢购");
            $buy699.click(function(){
                miaoSha(obj[0].id);
            });
        }else{
            $buy699.find("div").text("立刻预约");
            $buy699.attr("href","subcheck.html?id="+obj[0]["id"]);
        }
    }

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
                var status = data.status || "";
                if(status == "OK"){
                    console.log(data);
                    changeBtn(data.result);
                }
                else{
                    Utils.alert("预约类别获取失败");
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
        g.httpTip.show();
        $.ajax({
            url:url,
            data:condi,
            type:"POST",
            dataType:"json",
            context:this,
            global:false,
            success: function(data){
                console.log("miaoSha",data);
                var status = data.status || "";
                if(status == "OK"){
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
