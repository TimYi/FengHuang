/*
 *    name: talmd
 *
 *    author: yao
 *
 *    date: 2013-12-10
 *
 *    version: 1.0
 *
 *    descript: 图迈一些组件js
 */

var mainJavascript = {
    deNum: -1,
    _designerid: 0,
    cDeNum: -1,
    // 主页，搜索元素
    navSearch: function(that) {

        var nowThis = $(that).children('.search_module');

        nowThis.show().animate({
            'width': '268px'
        }, 200, function() {

            $(this).children().eq(0).click(function(event) {
                $(this).css('background', 'white').parent().css('background', 'white');
            });

            $(this).mouseleave(function() {

                $(this).css({
                    'width': '0'
                });

                $(this).hide();

                $(this).children().eq(0).css('background', '#F1F0EF').val('').parent().css('background', '#F1F0EF');

            })

        });

    },
  //用户新消息 提醒
    msgAlert: function() {
    	  msgcount = $('em.msgalert').text();
    	  msgcount = parseInt(msgcount);
		  if(msgcount==0){
			 $('.msgalert').hide();
		  }else{
			 $('.msgalert').show();
			 $('.msgalert').text(msgcount);
		  }
    },
    // 登录下拉菜单
    loginDetail: function(that) {
        var that = $(that);

        that.addClass('right_user_shadow').css({
            'width': '120px',
            'padding': '2px 10px',
            'margin-top': '-2px'
        }).children().eq(4).show().end().eq(1).show();

        that.mouseleave(function() {
            $(this).removeClass('right_user_shadow').css({
                'width': '40px',
                'padding': '0',
                'margin-top': '0'
            }).children().eq(4).hide().end().eq(1).hide();
        });
    },
    //购物车gwc 提示数字
    gwcAlert: function() {

          var $gl = $('#gwc_list'),

          gNum = $gl.find('li').length;

		  if(gNum===0){
			 $gl.closest('.gwc_detail').siblings('.gwalert').hide();
			 $('.gwc_sum').hide();
		     $('#scrollbar1').hide();
		     $('#cart_no_item').show();
		  }else{
			 $gl.closest('.gwc_detail').siblings('.gwalert').show();
			 $('.gwc_sum').show();
		     $('#cart_no_item').hide();
		     $('#scrollbar1').show();
			 $gl.closest('.gwc_detail').siblings('.gwalert').text(gNum);
			 if(gNum<=5){
			 $('.viewport').css('height',gNum*60);
			 $('.overview').css('position','static')   
			 }else{$('.viewport').css('height',300); }
		  }


    },
    //购物车gwc 详情
    gwcDetail: function(that) {

        this.gwcAlert();

        var that = $(that),
        _this = this;

        //_this.gwcSum();

        that.addClass('right_user_shadow').css({
            'width': '60px',
            'padding-left': '10px',
            'margin-top': '-2px'
        }).children().eq(1).css('left', '40px').end().eq(2).css('left', '20px').end().eq(3).show().end().eq(4).show();

        $('#scrollbar1').tinyscrollbar({
            sizethumb: 80
        });

        that.children().eq(3).find('li').hover(function() {

            var __this = _this;

            $(this).children('.gwc_list_delete').show();

            /*$(this).children('.gwc_list_delete').unbind('click').click(function() {

                // alert($(this).attr('class'))

                var ___this = __this;

                $(this).parent('li').fadeOut('slow', function() {

                    $(this).remove();

                    ___this.gwcAlert();

                });
            });*/


        }, function() {

            $(this).children('.gwc_list_delete').hide();
        });

        that.mouseleave(function() {
            $(this).removeClass('right_user_shadow').css({
                'width': '40px',
                'padding-left': '0',
                'margin-top': '0'
            }).children().eq(1).css('left', '30px').end().eq(2).css('left', '10px').end().eq(3).hide().end().eq(4).hide();
        });

    },
    //装饰空间下拉菜单
    decorationMenu: function(that) {

        var that = $(that);

        that.addClass('decoration_space_shadow').children().eq(1).show().end().eq(2).show();

        that.find('.de_detail_menu ul a').mouseenter(function() {

            var menuNum = $(this).closest('.de_detail_menu').find('a').index(this),
                $dm = that.find('.de_detail_menu');

            $dm.find('a').removeClass('de_me_selected');

            $(this).addClass('de_me_selected');

            $dm.siblings().children().hide();

            $dm.siblings().children().eq(menuNum).show();
        });

        that.mouseleave(function() {

            that.removeClass('decoration_space_shadow').children().eq(1).hide().end().eq(2).hide();

        })

    },
    //显示全屏
    showScreen: function(showCon) {

        this.showLayer = showCon;

        $('body').append('<div id="mybg" style="width: 100%; height: 100%; position: fixed; top: 0px; left: 0px; z-index: 50000;overflow:hidden;"></div>').css({
            "margin-right": "17px",
            "overflow": "hidden",
            "-webkit-margin-right": "12px"
        });

        this.showLayer.show();

    },
    //关闭全屏      
    closeScreen: function() {

        this.showLayer.css("display", "none");

        $('#mybg').remove();

        $('body').css({
            "overflow": "visible",
            "margin-right": "0"
        });

    },
    //选择设计师
    selectDesign: function(that, Sobj, cId, showFloag) {

        var _this = this,
            $clS = $('#close_select_designer'),
            $clC = $('.select_design_confirm');
        if (showFloag != false) {
            this.showScreen(Sobj); //打开全屏
        }
        if (_this.deNum != -1)
            $clS.parent().siblings('.design_select_list').children().eq(_this.deNum).css('background', '#F1F0EF');

        _this.selectExecute(); //设计执行

        _this.designConfirm($clC, cId, that); //确认操作

        _this.closeAllS($clS) //关闭全屏

    },
    //设计师选择执行
    selectExecute: function() {
        var that = this;
        $('.design_select_list_each').click(function() {
            mainJavascript._designerid = parseInt($(this).attr('rel'));
            $(this).siblings().css('background', 'white');
            $(this).css('background', '#F1F0EF');
            that.cDeNum = $(this).parent().children().index(this);
        });

    },
    //设计师选择确定
    designConfirm: function(clC, cId, that) {

        var _this = this;

        clC.unbind('click').click(function() {

            _this.deNum = _this.cDeNum;

            if (_this.deNum != -1) {

                var $replaceSpan = $(this).parent().siblings('.design_select_list').children().eq(_this.deNum),
                    $reUrl = $replaceSpan.children('a').children('img').attr('src'),
                    $reName = $replaceSpan.find('i').text();
                if (cId === 'design_select1') {

                    $(that).hide();

                    $(that).siblings().css('display', 'inline-block');

                    $(that).siblings().find('img').attr('src', $reUrl)

                    $('#de_name').text($reName);

                } else {

                    $(that).find('img').attr('src', $reUrl).end().children('b').text($reName);

                }

            }
            if (mainJavascript._designerid > 0) {
                $('#CreateBookForm_designerid').val(mainJavascript._designerid);
            }
            _this.closeScreen();

        });

    },
    //设计师：关闭全屏操作
    closeAllS: function(clS) {

        var _this = this;

        clS.click(function() {

            $(this).parent().siblings('.design_select_list').children().css('background', 'white');

            _this.closeScreen();

        });

    },
    //预约定制：图片上传，远程图片，我喜欢的。弹框
    customUpload: function(that, cusObj, fullscreen) {
        //如果全屏参数设计为FALSE则不执行下面的代码
        if (fullscreen !== false) {
            this.showScreen(cusObj);
        }

        //选项卡tab

        this.customUpTab(that);

        //确定按钮操作

        this.confirmButton(that);

        //关闭操作
        var _this = this;

        $('#close_add_custom_img').click(function() {

            _this.closeScreen();

        });

    },
    //预约定制：选项卡tab
    customUpTab: function(that) {

        var tabLi = $(that).parent().siblings('#add_custom_img').find('#cus_tab_wrap').children();

        tabLi.click(function() {

            var tabLiEq = tabLi.index(this);

            $(this).siblings().removeClass('add_tab3_selected');

            $(this).addClass('add_tab3_selected');

            $(this).parent().parent().find('.add_tab3g').hide();

            $(this).parent().parent().find('.add_tab3g').eq(tabLiEq).show();

        });

        //我喜欢下的点击操作

        this.myLikeImg(tabLi);

        //本地上传
        //this.myLikeUpload(tabLi);

        //远程图片

        this.farImgUpload(tabLi);
    },
    //预约定制:我喜欢下的点击操作
    myLikeImg: function(that) {

        var $likeTab = that.parent().parent().find('.add_tab3_1').children('ul').children();

        $likeTab.off('click').click(function() {

            var spanLen = $(this).find('span').length;

            if (spanLen === 0) {

                $(this).addClass('selected_add_img');

                $(this).prepend('<span></span>');

            } else {

                $(this).removeClass('selected_add_img');

                $(this).children().eq(0).remove();

            }

        });

    },
    //预约定制：本地上传
    myLikeUpload: function(that) {
        $('#add_tab3_trigger>p').hide();
        $('#add_tab3_trigger').css('height', 1);
        $('#add_tab3_trigger').css('padding', 0);
        $('#add_tab3_loading').show();

        $('#add_tab3_loading').find('.close_upload_img').click(function() {
            $(this).parent().fadeOut('slow', function() {
                $(this).remove();
            });
        });

    },

    //预约定制：远程图片
    farImgUpload: function(that) {
        var $farObj = that.parent().parent().find('.add_tab3_3');

        $farObj.on('keyup', '#far_img_uri', function() {

            var farUri = $(this).val();

            $farObj.find('.img_preview >img').attr('src', farUri);
        });
    },
    //确定按钮操作
    confirmButton: function(that) {

        var _this = this;

        var $conf = $(that).parent().siblings('#add_custom_img'),
            $conf1 = $('#select_design_confirm'),
            $conf2 = $('#correct_upload_img_comfirm_btn'),
            $conf3 = $('#far_confirm');

        //远程图片确定按钮
        $conf3.off('click').click(function() {
            var url = $('#far_img_uri').val();
            if (url.indexOf('http://') || url.indexOf('https://') || url.indexOf('ftp://')) {
                var objHtml = '',
                    strHtml = '';
                $.ajax({
                    type: 'POST',
                    data: {
                        'url': $('#far_img_uri').val()
                    },
                    url: strCurlPath,
                    dataType: 'json',
                    success: function(data) {
                        if (typeof(data.filepath) != 'undefined') {
                            $('#far_img_uri').val('');
                            $('#img_prev_id').attr('src', '');
                            strHtml = $('#text-tpl').html();
                            var iDivLen = parseInt($('#space_key_id').attr('rel'));
                            if (iDivLen < 1) {
                                iDivLen = $('.image_list_div').length;
                            }
                            $('#space_key_id').attr('rel', iDivLen + 1)
                            iDivLen = iDivLen > 0 ? iDivLen : 0
                            strHtml = strHtml.tpl_replace(iDivLen);
                            objHtml = $(strHtml);
                            objHtml.find('img').attr('src', data.filepath);
                            objHtml.find('input').val(data.abspath);
                            $('#each_custom_after').before(objHtml)
                        } else {
                            if (typeof(data.msg) != 'undefined') {
                                alert(data.msg);
                            } else {
                                alert('远程图片上传失败！');
                            }
                        }
                    }
                });
                pubu.eachDiv = $('.each_custom');

                setTimeout(function() {
                    pubu.pubuMain($('.custom_list'))
                }, 500)

                _this.delEach();

                _this.closeScreen();
            } else {
                alert('图片链接不对！');
            }


        });

        //上传图片确定按钮添加图片到预约订单中
        $conf2.off('click').click(function() {

            var selImageLi = $('#uploaded_files>li');

            if (selImageLi.length > 0) {
                var __this = _this,
                    imgObj = selImageLi.find('img'),
                    imgArr = [];
                var iDivLen;
                var objHtml, strHtml = '';
                imgObj.each(function() {
                    strHtml = $('#text-tpl').html();
                    var iDivLen = parseInt($('#space_key_id').attr('rel'));
                    if (iDivLen < 1) {
                        iDivLen = $('.image_list_div').length;
                    }
                    $('#space_key_id').attr('rel', iDivLen + 1)
                    iDivLen = iDivLen > 0 ? iDivLen : 0
                    strHtml = strHtml.tpl_replace(iDivLen);
                    objHtml = $(strHtml);
                    objHtml.find('img').attr('src', $(this).attr('src'));
                    objHtml.find('input').val($(this).attr('rel'));
                    $('#each_custom_after').before(objHtml)
                    iDivLen = 0;
                })


                pubu.eachDiv = $('.each_custom');

                setTimeout(function() {
                    pubu.pubuMain($('.custom_list'))
                }, 500)

                _this.delEach();

                _this.closeScreen();
            }
        });

        $conf1.off('click').click(function() {

            var selSpanNum = $(this).closest('.add_tab3_1').find('span');

            if (selSpanNum != 0) {

                var __this = _this,
                    eachA = $(that).parent(),
                    imgObj = selSpanNum.siblings('img'),
                    imgArr = [],
                    i = 0;

                for (imgObjLen = imgObj.length; i < imgObjLen; i++) { 
                    imgArr[i] = new Array;
                    imgArr[i].src = imgObj.eq(i).attr('src').replace('_100x100.jpg', '_270x270.jpg');
                    imgArr[i].rel = imgObj.eq(i).attr('rel');
                }

                __this.updateHomeImg(eachA, selSpanNum, imgArr);

            }
        });

    },
    //预约定制：插入图片操作
    updateHomeImg: function(eachA, iLen, imgArr) {

        var clearO = iLen,
            i = 0;
        var iDivLen;
        var objHtml, strHtml = '';
        for (iLen = iLen.length; i < iLen; i++) {
            strHtml = $('#text-tpl').html();
            var iDivLen = parseInt($('#space_key_id').attr('rel'));
            if (iDivLen < 1) {
                iDivLen = $('.image_list_div').length;
            }
            $('#space_key_id').attr('rel', iDivLen + 1)
            iDivLen = iDivLen > 0 ? iDivLen : 0
            strHtml = strHtml.tpl_replace(iDivLen);
            objHtml = $(strHtml);
            objHtml.find('img').attr('src', imgArr[i].src);
            objHtml.find('input').val(imgArr[i].rel);
            $('#each_custom_after').before(objHtml)
            iDivLen = 0;
        }

        pubu.eachDiv = $('.each_custom');

        setTimeout(function() {
            pubu.pubuMain($('.custom_list'))
        }, 500)

        clearO.parent().removeClass('selected_add_img').end().remove();

        imgArr = [];

        this.delEach();

        this.closeScreen();


    },
    //预约定制：删除图片操作
    delEach: function() {

        var $delEach = $('.del_each_custom');

        $delEach.click(function() {

            $(this).closest('.each_custom').remove();

            pubu.eachDiv = $('.each_custom');

            setTimeout(function() {
                pubu.pubuMain($('.custom_list'))
            }, 500);

        });

    },
    //整装详情：前端添加标签
    overallInsertData: function(data, id) {

        var imgJosn = data,

            dataId = id;

        orderText = JSON.stringify(imgJosn);

        imgJosnCopy = $.parseJSON(orderText);

        $.each(imgJosnCopy, function(key) {

            var $dataId = $('#' + dataId);

            $dataId.append('<div class="product product' + (key + 1) + '"><div class="mouseover_control"><div class="detail"></div></div></div>');

            $dataId.children('.product' + (key + 1)).css({
                'top': this.y + 'px',
                'left': this.x + 'px'
            });

            $dataId.children('.product' + (key + 1)).find('.detail').append('<div class="pro_content"><i></i><a href="' + this.url + '" target="_blank"><img src=' + this.title_img + ' /></a></div>')

            var strPrice = '<div class="pro_content_de"><a href="' + this.url + '" target="_blank"><h4>' + this.title + '</h4></a>'; 
            if(this.pstate == 1)
            {
                strPrice += '<p>￥' + this.money + '</p>';
            }            
            strPrice += '</div>';            
            $dataId.children('.product' + (key + 1)).find('.detail').append(strPrice);
        });

        this.clickPlus();

    },
    //整装详情：划过标签
    clickPlus: function() {

        $('.product').hover(function() {

            var parnW = $(this).parent().width() / 2 | 0;

			var nowHeight = parseInt($('#part_main').css('height'))-6,
			
			  detail = $(this).find('.detail'),

			  mover = $(this).find('.mouseover_control'),
	   
			  thisLocal = parseInt($(this).css('left'));

            if (thisLocal < parnW) {

                $(this).css('background-position', 'bottom');

                $(this).children('.mouseover_control').css('display', 'block');

                $(this).find('.detail').fadeIn();

				//直接用数字25，因为鼠标多次移在上面的时候，获取的detail.css('top'）在变化 
				var judgeTop = parseInt($(this).css('top'))+parseInt(detail.outerHeight())-25;

				if(judgeTop>=nowHeight){
		  
				  var endTop = parseInt($(this).css('top')) - (nowHeight - parseInt(detail.outerHeight())-5)

				  mover.css('top',-endTop+'px')
				  detail.css('top','0px')
				  detail.find('i').css('top',endTop-15+'px')
				}


            } else {

                $(this).css('background-position', 'bottom');

                $(this).children('.mouseover_control').css({
                    'display': 'block',
                    'left': '-205px',
                    'top': '-25px'
                });

                $(this).find('.detail').css({
                    'left': '0',
                    'top': '0'
                }).find('i').css({
                    'background': 'url(/images/overall_tags_detail_title_angle_r.png)',
                    'left': '176px'
                });

                $(this).find('.detail').fadeIn();

				//直接用数字25，因为鼠标多次移在上面的时候，获取的detail.css('top'）在变化
				var judgeTop = parseInt($(this).css('top'))+parseInt(detail.outerHeight())-25;

				if(judgeTop>=nowHeight){
		  
				  var endTop = parseInt($(this).css('top')) - (nowHeight - parseInt(detail.outerHeight())-5)

				  mover.css('top',-endTop+'px')
				  detail.css('top','0px')
				  detail.find('i').css('top',endTop-15+'px')
				}

            }
        }, function() {

            $(this).css('background-position', 'top');

            $(this).children('.mouseover_control').hide();

            $(this).find('.detail').fadeOut();

        });

    },
    //整装详情：滚动效果
    overallScroll: function(thisObj) {

        var $thisObj = $(thisObj),
            elementDistance = $thisObj.offset().top - 50,
            $eachObj = $('.overall_list_left').children('.overall_list_each'),
            $clickObj = $('#overall_list_right_scroll').children('ul').find('a');

        $(window).scroll(function() {

            var thisObj = $thisObj,
                scrollDistance = $(window).scrollTop() + 350,
                i = 0;

            for (eachLen = $eachObj.length; i < eachLen; i++) {

                var eachDis = $eachObj[i].offsetTop,
                    lastDis = $eachObj[eachLen - 1].offsetTop

                    ii = scrollDistance > lastDis ? 0 : 1;

                if (ii) {

                    if (scrollDistance >= eachDis && scrollDistance < $eachObj[i + ii].offsetTop) {
                        $clickObj.removeClass('scroll_selected');
                        $clickObj.eq(i).addClass('scroll_selected');
                    }

                } else {

                    $clickObj.removeClass('scroll_selected');

                    $clickObj.eq(eachLen - 1).addClass('scroll_selected');

                }

            }

            if (scrollDistance - 350 <= elementDistance) {

                thisObj.removeClass('scrollstyle');

            } else {

                thisObj.addClass('scrollstyle');

            }

        });
    },
    //整装详情：滚动控制
    clickScroll: function(that, thatParent) {

        var $thisObj = $(that),
            clickIndex = thatParent.index($thisObj),
            $eachObj = $('.overall_list_left').children('.overall_list_each');

        $eachObjIndexDistance = $eachObj.eq(clickIndex).offset().top;

        $(document.body).animate({
            scrollTop: $eachObjIndexDistance
        }, 'slow', function() {

            thatParent.removeClass('scroll_selected');

            $thisObj.addClass('scroll_selected');

        });

    },
    //装饰详情：切换标签图片
    decorationTabImg: function(that, thisObj) {

        var _this = $(that);

        thisObj.find('a').removeClass('change_tagslayer_selected');

        _this.parent().addClass('change_tagslayer_selected');

        var $changeObj = thisObj.parent().siblings('.main_decoration_right'),
            bigUri = _this.attr('alt');

        $changeObj.children('img').attr('src', bigUri);

    },
    //设计师主页：上面三个滑动效果
    designUpslide: function() {

        var $slideObj = $('.designer_index_title').children('ul').children('li'),

            $mouseObj = $('.mouseover_layer').children('li');

        $slideObj.hover(function() {
            $(this).addClass('designer_click_title').children('i').show().siblings().show();
        }, function() {
            $(this).removeClass('designer_click_title').children('i').hide().siblings().hide();
        });

        $mouseObj.hover(function() {

            var mouseTitle = $(this).find('img').attr('alt'),

                imgUri = $(this).find('img').attr('longdesc');

            layerStr = "<div class='black_bg'><a href='" + imgUri + "'>" + mouseTitle + "</a></div>";

            $(this).prepend(layerStr);
        }, function() {
            $(this).children('div').remove();
        });

    },
    //家居文化，最后滚动效果
    homeCul: function() {

        var _visible = 6;
        var $pagers = $('#pager a');
        var _onBefore = function() {
            $(this).find('img').stop().fadeTo(300, 1);
            $pagers.removeClass('selected');
        };
        $('#carousel').carouFredSel({
            items: _visible,
            width: '100%',
            auto: false,
            scroll: {
                duration: 750
            },
            prev: {
                button: '#prev',
                items: 2,
                onBefore: _onBefore
            },
            next: {
                button: '#next',
                items: 2,
                onBefore: _onBefore
            },
        });

        $pagers.click(function(e) {
            e.preventDefault();

            var group = $(this).attr('href').slice(1);
            var slides = $('#carousel div.' + group);
            var deviation = Math.floor((_visible - slides.length) / 2);
            if (deviation < 0) {
                deviation = 0;
            }

            $('#carousel').trigger('slideTo', [$('#' + group), -deviation]);
            $('#carousel div img').stop().fadeTo(300, 0.3);
            slides.find('img').stop().fadeTo(300, 1);

            $(this).addClass('selected');
        });
    },
    //色板tab
    colorBoardTab: function() {

        var $tabLi = $('#cus_tab_wrap').children();

        $tabLi.click(function() {

            var tabLiEq = $tabLi.index(this);

            $(this).siblings().removeClass('add_tab3_selected');

            $(this).addClass('add_tab3_selected');

            $(this).parent().parent().find('.add_taball').hide();

            $(this).parent().parent().find('.add_taball').eq(tabLiEq).show();

        });

    },

    //颜色选择图集
    imggatherColorSelect: function(that) {

        $(that).siblings().removeClass('selected');
        $(that).addClass('selected');

    },

}