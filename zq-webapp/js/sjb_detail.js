$(function() {
    var g = {};
    g.phone = "";
    g.imgCodeId = "";
    g.sendCode = false;
    g.sendTime = 60;
    g.username = Base.userName;
    g.token = Utils.offLineStore.get("token", false);
    g.packageId = Utils.getQueryString("packageId");
    g.type = Utils.getQueryString("type");
    g.totalPage = 1;
    g.currentPage = 1;
    g.paseSize = 10;
    g.httpTip = new Utils.httpTip({});
    g.listdata = [];
    //验证登录状态
    g.loginStatus = Utils.getUserInfo();

    getPackageDetail();
    getPackageBrand();
    getPackageMeterial();

    function getPackageDetail(){
        var url = Base.packageDetail+'/'+g.packageId;
        var condi = {};
        condi.id = g.packageId;
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
                    $('#mainPic').attr('src','images/sjb/sjb_'+g.type+'.jpg');
                }else{
                    Utils.alert("套餐获取失败");
                }
                g.httpTip.hide();
            },
            error:function(data){
                g.httpTip.hide();
            }
        });
    }

    function getPackageMeterial(){
        var url = Base.serverUrl+'/api/product/package/'+ g.packageId +'/materials';
        var condi = {};
        condi.id = g.packageId;
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
                    changeMeterialHtml(data.result);
                }else{
                    Utils.alert("套餐主材获取失败");
                }
                g.httpTip.hide();
            },
            error:function(data){
                g.httpTip.hide();
            }
        });
    }

    function changeMeterialHtml(data){
        var obj = data || '';
        console.log(obj);
        var html = [];
        for(var key in obj){
            var brand = key;
            var mete = obj[key] || '';
            var product = mete[0].product || '';
            var name = product.name || '';
            html.push('<span style="color:#000;font-size:14px;">'+ brand +' ／ '+ name +'</span>');
            html.push('<ul data-am-widget="gallery" class="am-gallery am-avg-sm-4 am-gallery-default" data-am-gallery="{ pureview: true }">');
            for(var i = 0,len=mete.length;i<len;i++){
                var pic = mete[i].pic || '';
                var picurl = pic.url || '';
                html.push('<li><div class="am-gallery-item">');
                html.push('<a href="'+ picurl +'" class="">');
                html.push('<img class="am-thumbnail" src="'+ picurl +'" />');
                html.push('</a></div></li>');
            }
            html.push('</ul>');
        }
        $('#meterialWrap').html(html.join(''));
        $.AMUI.gallery.init();
    }

    function getPackageBrand(){
        var url = Base.serverUrl+'/api/product/package/'+ g.packageId +'/brands';
        var condi = {};
        condi.id = g.packageId;
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
                    changeBrandHtml(data.result);
                }else{
                    Utils.alert("套餐品牌获取失败");
                }
                g.httpTip.hide();
            },
            error:function(data){
                g.httpTip.hide();
            }
        });
    }

    function changeBrandHtml(data){
        var obj = data || '';
        var len = obj.length;
        var html = [];
        var logo_name = [];
        for(var i =0;i<len;i++){
            var logourl = obj[i].logo.url || '';
            var name = obj[i].name || '';
            logo_name.push(name);
            html.push('<li><div class="am-gallery-item">');
            html.push('<a href="'+ logourl +'" class="">');
            html.push('<img src="'+ logourl +'" alt="'+ name +'"/>');
            html.push('</a></div></li>');
        }
        $('#logoWrap').html(html.join(''));
        $('#logo-all').html(logo_name.join('、'));
        $('#logo-num').html(len);
        $.AMUI.gallery.init();
    }

});