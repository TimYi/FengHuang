// .-----------------------------------------------------------------------------------
// | Filename: city_init.js
// | Description: 获取当前城市
// | Date: 2015-03-18
// | Site: http://www.to8to.com
// |-----------------------------------------------------------------------------------
// | Author: carl <carl.wu@corp.to8to.com>
// | Copyright (c) 2012-2014, http://www.to8to.com. All Rights Reserved.
// |-----------------------------------------------------------------------------------
//

;
(function($, window) {

    // 兼容老代码声明全局
    window.city_arr = {};  // 城市列表
    window.city_id = getCookie('townid', 1); // 当前城市
    var cityScriptUrl = 'http://www.to8to.com/get_city_name.php';

    // 黑名单城市
    window.no_showzwjCity = new Array(
        558,959,952,1991,1761,1446,1572,1531,2019,1699,74,2255,1106,1206,627,2226,1632,1563,814,248,255,20,229,
        1460,2763,1464,665,647,1262,3009,2994,2280,2939,1713,2735,2687,842,2405,2783,2448,2428,2179,1940,2342,
        1752,3118,218,940,2595,1949,1333,1306,1177,1386,1419,2386,1929,3001,1952,2986,3030,3043,1577,1162,2243,
        2575,176,1377,2392,1639,1350,2458,1501,951,637,1511,196,1278,2039,190,962,1798,1849,1964,653,2095,1976,
        2494,48,876,235,1243,2772,99,821,1983,2400,1741,725,2308,2510,2565,2518,2528,715,3061,3147,3286,3287
    );

    city_id = city_id ? city_id : 1130;

    // dom加载完后异步执行获取城市数据
    $(function() {
        getCity(function() {
            setCity();
        });
    });

    // 异步获取城市信息后初始化页面数据
    function setCity() {
        var current_city = city_arr[city_id];
        current_city = current_city ? current_city : '北京';
        $('#current_city').html(current_city);
        var current_tcode = getCookie('tcode', 1);
        current_tcode = current_tcode ? current_tcode : 'bj';
		var tcode = '';
		try{tcode =  php_tcode;}catch(e){tcode='bj';}
        $(".nav_fzlink").each(function () {
            var href = $(this).attr('href');
            if (href.indexOf(tcode) > 0) {
				var ereg = new RegExp(tcode, ['g']);
                var new_href = href.replace(ereg, current_tcode);
            } else if (href.indexOf("www") > 0) {
                var new_href = href.replace(/www/g, current_tcode);
            }
            $(this).attr("href", new_href);
        });

        if(to8toInArray(city_id,no_showzwjCity)) {
            jq("#nevzwjurl").hide();
        }
    }

    function to8toInArray(search,array){
        for(var i in array){
            if(array[i]==search){
                return true;
            }
        }
        return false;
    }

    function getCity(callback) {
        $.getScript(cityScriptUrl, function() {
            typeof callback === 'function' ? callback() : null;
        });
    }

})(jQuery, window);