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
        var obj = data.result;
        var $buy599 = $('#buy599');
        var $buy699 = $('#buy699');

        for(var i = 0,len=obj.length;i<len;i++){
            if(obj[i].id == "599"){
                if(obj[i].hasScrambled){
                    $buy599.find("div").text("已抢购");
                }else if(obj[i].hasAppointed){
                    $buy599.find("div").text("立刻抢购");
                    $buy599.attr('href','javascript:miaoSha(599)');
                }else{
                    $buy599.find("div").text("立刻预约");
                    $buy599.attr("href","subcheck.html?id="+obj[i]["id"]);
                }
            }else if(obj[i].id == "699"){
                console.log(222);
                if(obj[i].hasScrambled){
                    $buy699.find("div").text("已抢购");
                }else if(obj[i].hasAppointed){
                    $buy699.find("div").text("立刻抢购");
                    console.log(222);
                    $buy699.attr("href","javascript:miaoSha(699)");
                }else{
                    $buy699.find("div").text("立刻预约");
                    $buy699.attr("href","subcheck.html?id="+obj[i]["id"]);
                }
            }
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
                    changeBtn(data.result);
                }else{
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
