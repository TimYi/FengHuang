$(function() {
    var g = {};
    g.phone = "";
    g.imgCodeId = "";
    g.sendCode = false;
    g.sendTime = 60;
    g.username = Base.userName;
    g.token = Utils.offLineStore.get("token", false);
    g.packageId = Utils.getQueryString("packageId");
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

    function changeDetailHtml(data){
        var obj = data || '';
        var $btn = $('#btn-buy');
        $('#mainPic').attr('src','images/tc/tc_'+g.packageId+'.jpg');
        if(obj.hasScrambled){
            $btn.find("div").text("已抢购");
        }else if(obj.hasAppointed){
            $btn.find("div").text("立刻抢购");
            $btn.attr('href','javascript:miaoSha(\'' + g.packageId + '\')');
        }else{
            $btn.find("div").text("立刻预约");
            $btn.attr("href","subcheck.html?id="+g.packageId);
        }

        var spaces = obj.spaces;
        var shtml = [];
        for(var i = 0,len=spaces.length;i<len;i++){
            var name = spaces[i].name || '';
            var p1 = spaces[i].pic1 || '';
            var p2 = spaces[i].pic2 || '';
            var p3 = spaces[i].pic3 || '';
            var pic1 = p1.url || '';
            var pic2 = p2.url || '';
            var pic3 = p3.url || '';
            shtml.push('<p style="font-size:14px;line-height:18px;color:#666">');
            shtml.push('<i class="am-icon-heart" style="font-size:14px;"></i>');
            shtml.push('<b>&nbsp;&nbsp;<span style="color:#000">'+ name +'</span></b></p>');
            shtml.push('<ul id="logoWrap" data-am-widget="gallery" class="am-gallery am-avg-sm-3 am-gallery-default" data-am-gallery="{ pureview: true }">');
            shtml.push('<li><div class="am-gallery-item"><a href="'+ pic1 +'" class="">');
            shtml.push('<img class="am-thumbnail" src="'+ pic1 +'" /></a></div></li>');
            shtml.push('<li><div class="am-gallery-item"><a href="'+ pic2 +'" class="">');
            shtml.push('<img class="am-thumbnail" src="'+ pic2 +'" /></a></div></li>');
            shtml.push('<li><div class="am-gallery-item"><a href="'+ pic3 +'" class="">');
            shtml.push('<img class="am-thumbnail" src="'+ pic3 +'" /></a></div></li>');
            shtml.push('</ul>');
            shtml.push('<form class="am-form">');
            shtml.push('<p style="font-size:14px;line-height:26px;color:#333;font-weight:800;">');
            shtml.push('<div class="carousel-inner"><div class="item active">');
            shtml.push('<table class="table u_ct_15" style="font-size:12px">');
            shtml.push('<thead>');
            shtml.push('<tr class="u_th" style="border-bottom:2px solid #ddd;border-top:2px solid #fff;">');
            shtml.push('<th>项目</th>');
            shtml.push('<th>品牌</th>');
            shtml.push('<th>数量</th>');
            shtml.push('<th>详细说明</th>');
            shtml.push('</tr></thead>');
            shtml.push('<tbody>');
            var items = spaces[i].items;
            for(var j=0,len2 = items.length;j<len2;j++){
                var brand = items[j].brand;
                var item_name = items[j].name;
                var num = items[j].number;
                var desc = items[j].description;
                shtml.push('<tr style="border-bottom:1px solid #ddd;">');
                shtml.push('<td>'+ item_name +'</td>');
                shtml.push('<td>'+ brand +'</td>');
                shtml.push('<td>'+ num +'</td>');
                shtml.push('<td>'+ desc +'</td>');
                shtml.push('</tr>');
            }
            shtml.push('</tbody></table>');
            shtml.push('</div></div></p></form>');
        }
        $('#spaceWrap').html(shtml.join(''));
    }

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
                    changeDetailHtml(data.result);
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
            html.push('<hr/><span style="color:#000;font-size:14px;font-weight:800">'+ brand +'</span> - <span style="color:#666;font-size:14px;"> '+ name +'</span>');
            html.push('<ul data-am-widget="gallery" class="am-gallery am-avg-sm-4 am-gallery-default" data-am-gallery="{ pureview: true }">');
            for(var i = 0,len=mete.length;i<len;i++){
                var pic = mete[i].pic || '';
                var picurl = pic.url || '';
                html.push('<li><div class="am-gallery-item">');
                html.push('<a href="'+ picurl +'" class="">');
                html.push('<img class="am-thumbnail" src="'+ picurl +'" style="margin-bottom:0;border:1px solid #eee;" />');
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
            var logo = obj[i].logo || '';
            var logourl = logo.url || ''; 
            var name = obj[i].name || '';
            logo_name.push(name);
            if(logourl){
                html.push('<li><div class="am-gallery-item"  style="height:65px">');
                html.push('<a href="'+ logourl +'" class="">');
                html.push('<img src="'+ logourl +'" alt="'+ name +'"/>');
                html.push('</a></div></li>');
            }
        }
        $('#logoWrap').html(html.join(''));
        $('#logo-all').html(logo_name.join('、'));
        $('#logo-num').html(len);
        $.AMUI.gallery.init();
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
