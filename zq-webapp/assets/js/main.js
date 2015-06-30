var map = new BMap.Map("map");
var Util = {
  getLeaseNameByType: function(type) {
    var lease = {
  		'0' : "<b class=title2_g style=margin-left:-6px>【临时】</b>",
      '1' : "<b class=title2_g style=margin-left:-6px>【私人】</b>",
      '2' : "<b class=title2_g style=margin-left:-6px>【停车场】</b>",
      '3' : "<b class=title2_g style=margin-left:-6px>【其他】</b>"
    }
    return lease[type];
  }
}
function getdis(p1,p2){
	var p = new BMap.Point(p1, p2);
	var x = new BMap.Point(117.001279,36.617385);
	var juli = Math.ceil(map.getDistance(x,p));	
	return juli;					
}
!function(){
    var filterData = [
    {
      "name"  : "车位类型",
      "value" : "cls_car",
      "data"  : [
        {
          "name"  : "全部",
          "value" : "0,1"
        },            
        {
          "name"  : "公共临时",
          "value" : "0,0"
        },
        {
          "name"  : "私人闲置",
          "value" : "1,1"
        },
        {
          "name"  : "停车场",
          "value" : "2,2"
        },
        {
          "name"  : "其他",
          "value" : "3,3"
        }
      ]
    },
    {
      "name"  : "交易类型",
      "value" : "cls_exc",
      "data"  : [
        {
          "name"  : "全部",
          "value" : "0,3"
        },
        {
          "name"  : "单次交易",
          "value" : "1,1"
        },
        {
          "name"  : "短租交易",
          "value" : "2,2"
        },
        {
          "name"  : "长租交易",
          "value" : "3,3"
        }
      ]
    },        
    {
      "name"  : "支付类型",
      "value" : "cls_pay",
      "data"  : [
        {
          "name"  : "全部",
          "value" : "0,2"
        },
        {
          "name"  : "人民币",
          "value" : "1,1"
        },
        {
          "name"  : "虚拟币",
          "value" : "2,2"
        }
      ]
    },        
    {
      "name"  : "价格区间",
      "value" : "price",
      "data"  : [
        {
          "name"  : "全部",
          "value" : "0,10000"
        },
        {
          "name"  : "5元以下",
          "value" : "0,5"
        },
        {
          "name"  : "5-10元",
          "value" : "5,10"
        },
        {
          "name"  : "10-30元",
          "value" : "10,30"
        },
        {
          "name"  : "30-50元",
          "value" : "30,50"
        },
        {
          "name"  : "50-100元",
          "value" : "50,100"
        },
        {
          "name"  : "100-300元",
          "value" : "100,300"
        },
        {
          "name"  : "300元以上",
          "value" : "300,10000"
        }
      ]
    }
    ]
    for (var i in filterData) { //条件筛选的各个项
        var item = filterData[i],
            data = item.data,
            dl = $('<dl id="' + item.value + '" class="dl-horizontal" value="' + item.value + '"><dt>' + item.name + '</dt></dl>'),
            ul = $('<ul class="inline"></ul>');
        for(var j in data) { //各个项对应的各详细选项
            var subData = data[j];
            $('<li><a href="###" value = "' + subData.value + '">' + subData.name +'</a></li>').appendTo(ul);
        }
        ul.appendTo($('<dd></dd>')).appendTo(dl);
        dl.appendTo($('#filterBox'));
    }
    // 点击选择项的事件
    $('#filterBox li a').click(function(){
        var type = $(this).parents('dl').attr('value');
        $('#' + type + " li a").removeClass('activate');
        if (!$(this).hasClass('activate')) { //点击的不是当前的选项
            $(this).addClass('activate');
            $('#selectedValue div[type$=' + type + ']').remove(); //当前条件之前选择过的条件删除
            var item = $('<div class="span1" value="' + $(this).attr('value') + '" type="' + type + '"><span>' + $(this).html() + '</span></div>');
            //添加删除按钮
            var deleteBtn = $('<i class="icon-remove"></i>').click(function(){
                $(this).parent().remove();
                $('#' + type + " li a").removeClass('activate');
                keyword = $('#keyword').val();
                searchAction(keyword);
            });
            deleteBtn.appendTo(item); 
            item.appendTo('#selectedValue'); //添加当前的筛选条件
            keyword = $('#keyword').val();
            searchAction(keyword); 
        }
    });
    //条件筛选模块相关代码 end
    //检索模块相关代码
    var keyword     = "",   //检索关键词
        page        = 0,    //当前页码
        points      = [],   //存储检索出来的结果的坐标数组
        customLayer = null; //麻点图层
    customLayer=new BMap.CustomLayer(4392); //新建麻点图图层对象
    map.addTileLayer(customLayer); //将麻点图添加到地图当中
    customLayer.addEventListener('hotspotclick', hotspotclickCallback); //给麻点图添加点击麻点回调函数
    /**
     * 麻点图点击麻点的回调函数
     * @param 麻点图点击事件返回的单条数据
     */
    function hotspotclickCallback(e) {
        var customPoi = e.customPoi,
		    str = [];
		str.push("address = " + customPoi.address);
		str.push("phoneNumber = " + customPoi.phoneNumber);
        var content = '<p style="width:280px;margin:0;line-height:20px;">地址：' + customPoi.address + '</p>';
        //创建检索信息窗口对象
        var searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
            title: customPoi.title,  //标题
            width: 290,              //宽度
            height: 40,              //高度
            enableAutoPan : true,    //自动平移
            enableSendToPhone: true, //是否显示发送到手机按钮
            searchTypes :[
                BMAPLIB_TAB_SEARCH,   //周边检索
                BMAPLIB_TAB_TO_HERE,  //到这里去
                BMAPLIB_TAB_FROM_HERE //从这里出发
            ]
        });
        var point = new BMap.Point(customPoi.point.lng, customPoi.point.lat);
        searchInfoWindow.open(point); //打开信息窗口
    }

    //绑定检索按钮事件
    $('#searchBtn').bind('click', function(){
        keyword = $('#keyword').val();
        searchAction(keyword);
    });

    function searchAction(keyword, page) {
        page = page || 0;
        var filter = []; //过滤条件
        $.each($('#selectedValue div'), function(i, item){ //将选中的筛选条件添加到过滤条件参数中
            var type = $(item).attr('type'),
                value = $(item).attr('value');
            if (type == "location") {
                keyword = value + " " + keyword;
            } else {
                filter.push(type + ':' + value);
            }
        });
        
        /*附近搜索*/
				var geolocation = new BMap.Geolocation();
				geolocation.getCurrentPosition(function(r){
					if(this.getStatus() == BMAP_STATUS_SUCCESS){
					var point = r.point;
					p = r.point.lng+","+r.point.lat;
				    var banjing = '1000';
				    var url = "http://api.map.baidu.com/geosearch/v3/nearby?callback=?";
				    $.getJSON(url, {
				        'ak'         : '9Z4yvIoj9jnpKfPA4igxxbLP',	//'A4749739227af1618f7b0d1b588c0e85'  //用户ak
				        'geotable_id': 96420, 											//30960
				        'q'          : keyword, 										//检索关键字
				        'location'	 : p,			//中心点
				        'coord_type' : '3',  												//坐标系:百度坐标
				        'radius'     : banjing, 											//搜索半径
				        'filter'     : filter.join('|'),  					//过滤条件
				        'page_index' : page													//页码
				
				    },function(e) {
				        renderMap(e, page + 1);
				    });        
					}
					else {
						alert('failed'+this.getStatus());
					}
					return x;
				})
    }

    function renderMap(res, page) {
        var content = res.contents;
        $('#mapList').html('');
        map.clearOverlays();
        points.length = 0;

        if (content.length == 0) {
            $('#mapList').append($('<p style="padding-top:40px;text-align:center;text-align:center;font-size:14px;" class="text-warning">抱歉，没有找到您想要的信息，请重新查询</p>'));
            return;
        }
        $.each(content, function(i, item){
            var point = new BMap.Point(item.location[0], item.location[1]),
                marker = new BMap.Marker(point);
            points.push(point);
            var tr = $(""+
				      "<li class='am-g' style='border-top:0 none;box-shadow: inset 0 -1px 1px -1px #999;'>"+
				        "<a href='member.php?c=map&jd="+item.location[0]+"&wd="+item.location[1]+"&memo="+item.memo+"&posmemo="+item.title+"' class='am-list-item-hd'>"+
				        	"<div>"+
				        		"<span style='font-weight:bold;color:#000;'>"+item.memo+"</span><br>"+
				        		Util.getLeaseNameByType(item.cls_car)+"<b class='title2_g'>"+item.title+" / "+item.price+"元</b>"+
				        		"<div style='float:right;color:#f4a300'>"+getdis(item.location[0],item.location[1])+"米&nbsp;&nbsp;"+     //"<div style='float:right;color:#f4a300'>"+item.dayprice+" 元/天 "+
				        			"<span class='am-icon-chevron-right' style='font-size:12px;color:#999'></span>"+
				        		"</div>"+
				        	"</div>"+
				        "</a>"+
				      "</li>"            
            );
            $('#mapList').append(tr);;
        });

        var pagecount = Math.ceil(res.total / 10);
        $('#sum').html(res.total);        
        if (pagecount > 76) {
            pagecount = 76; //最大页数76页
        }
        function PageClick (pageclickednumber) {
            pageclickednumber = parseInt(pageclickednumber);
            $("#pager").pager({ pagenumber: pageclickednumber, pagecount: pagecount, showcount: 3, buttonClickCallback: PageClick });
            searchAction(keyword, pageclickednumber -1);
        }
        $("#mapPager").pager({ pagenumber: page, pagecount: pagecount, showcount:3, buttonClickCallback: PageClick });

        map.setViewport(points);
    };
    searchAction(keyword); 
}();

