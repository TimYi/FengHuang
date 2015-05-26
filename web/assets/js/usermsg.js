jq(function($){
    /*tabmenus edit by lshclshc@163.com */
    jq('[href^="#tab-my"]').click(function(e){
        e.preventDefault();
        var targetId = jq(this).attr('href'),
        $toggleTarget =jq(targetId);
        $toggleTarget.siblings('div +').hide();
        /*jq(this).parent().toggleClass('actives');
        jq(this).parent("div").siblings().removeClass('actives')
        jq(this).parent("div").find("span").addClass('actives');*/

        //jq(this).toggleClass('actives');
        jq(this).addClass('actives').parent("li").siblings().find("a").removeClass('actives');
        $toggleTarget.fadeIn(200);
    });
    /*.user-myzx li div h1:hover{ color: red;}*/
    $('.tab-item-adel').click(function(){
        jq("p .sureBtns").attr('tid',jq(this).attr('tid'));
        $(".tipDelete").show().data('itemli', $(this).parents('li').eq(0)); 
        $(".delMask").show();
    });
    $(".sureBtns").on("click",function(){
         var url = 'message_list.php?ajax=1';
        jq.post(url,{'tid':jq(this).attr('tid')},function(message){
            if(message == 1){
                var item =  $(".tipDelete").data('itemli');
                $(".delMask").hide();
                if(item){
                    item.hide(300);
                    $('.tipDeleteMsg').show();
                    setTimeout(function(){
                        $('.tipDeleteMsg').hide();
                        location.reload(true);
                    },2000);
                }
                $(".tipDelete").hide();
            }
        });
    });

    $(".cancelBtns").on("click",function(){
        $(".tipDelete").hide();
        $(".delMask").hide();
    });
    jq("#tab-myzx li,#tab-myproject li").mouseenter(function(e){
        e.preventDefault();
        var h1=jq(this).find("h1");
        h1.css("color","red");
        var adel=jq(this).find("div h2 .tab-item-adel")
        if(adel.is(":hidden"))
        {
            adel.show(); 
        }
    })


   /* var deleteMsg=jq(".user-myzx li div h2 a");
    deleteMsg.on("click",function(){
        jq(".tipDelete").show()
    });*/
//
//    jq(".tipDelete").on("click",function(){
//
//    })

    jq("#tab-myzx li,#tab-myproject li").mouseleave(function(e) {
        e.preventDefault();
        var h1=jq(this).find("h1");
        h1.css("color","#666");
        var adel=jq(this).find("div h2 a");
        if (adel.is(":visible")){
            adel.hide()
        }
    })

    jq("#tab-myzx li").on("mouseenter",function(){
        var h1=jq(this).find("h1");
        h1.css("color","red");
    });
    jq("#showSetFrame").on("click",function(){
        jq(".tabmask").show();
    });
    jq(".headTitle span").on("click",function(){
        jq(".tabmask").hide()
    });

    jq("#tab-myzx li").on("mouseleave",function(){
        var h1=jq(this).find("h1");
        h1.css("color","#666");
    });



})