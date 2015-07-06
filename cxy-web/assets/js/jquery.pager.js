/*
* jQuery pager plugin
* Version 1.0 (12/22/2008)
* @requires jQuery v1.2.6 or later
*
* Example at: http://jonpauldavies.github.com/JQuery/Pager/PagerDemo.html
*
* Copyright (c) 2008-2009 Jon Paul Davies
* Dual licensed under the MIT and GPL licenses:
* http://www.opensource.org/licenses/mit-license.php
* http://www.gnu.org/licenses/gpl.html
* 
* Read the related blog post and contact the author at http://www.j-dee.com/2008/12/22/jquery-pager-plugin/
*
* This version is far from perfect and doesn't manage it's own state, therefore contributions are more than welcome!
*
* Usage: .pager({ pagenumber: 1, pagecount: 15, buttonClickCallback: PagerClickTest });
*
* Where pagenumber is the visible page number
*       pagecount is the total number of pages to display
*       buttonClickCallback is the method to fire when a pager button is clicked.
*       showcount is the total number of viewable pages
*
* buttonClickCallback signiture is PagerClickTest = function(pageclickednumber) 
* Where pageclickednumber is the number of the page clicked in the control.
*
* The included Pager.CSS file is a dependancy but can obviously tweaked to your wishes
* Tested in IE6 IE7 Firefox & Safari. Any browser strangeness, please report.
*/
(function($) {

    $.fn.pager = function(options) {

        var opts = $.extend({}, $.fn.pager.defaults, options);

        return this.each(function() {

        // empty out the destination element and then render out the pager with the supplied options
            $(this).empty().append(renderpager(parseInt(options.pagenumber), parseInt(options.pagecount), parseInt(options.showcount), options.buttonClickCallback));
            
            // specify correct cursor activity
            $('.pages li').mouseover(function() { document.body.style.cursor = "pointer"; }).mouseout(function() { document.body.style.cursor = "auto"; });
        });
    };

    // render and return the pager with the supplied options
    /*原始的
    function renderpager(pagenumber, pagecount, showcount, buttonClickCallback) {

        // setup $pager to hold render
        var $pager = $('<ul class="pages"></ul>');

        // add in the previous and next buttons
        $pager.append(renderButton('|<', pagenumber, pagecount, buttonClickCallback)).append(renderButton('<', pagenumber, pagecount, buttonClickCallback));

        // pager currently only handles 10 viewable pages ( could be easily parameterized, maybe in next version ) so handle edge cases
        var startPoint = 1;
        var endPoint = showcount;

        var mid = (showcount - 1) / 2;
        if (pagenumber > mid) {
            startPoint = pagenumber - mid;
            endPoint = pagenumber + mid;
        }

        if (endPoint > pagecount) {
            startPoint = pagecount - (showcount - 1);
            endPoint = pagecount;
        }

        if (startPoint < 1) {
            startPoint = 1;
        }

        // loop thru visible pages and render buttons
        for (var page = startPoint; page <= endPoint; page++) {

            var currentButton = $('<li class="page-number"><a href="###">' + (page) + '</a></li>');

            page == pagenumber ? currentButton.addClass('active') : currentButton.click(function() { buttonClickCallback(this.firstChild.firstChild.data); });
            currentButton.appendTo($pager);
        }

        // render in the next and last buttons before returning the whole rendered control back.
        $pager.append(renderButton('>', pagenumber, pagecount, buttonClickCallback)).append(renderButton('>|', pagenumber, pagecount, buttonClickCallback));

        return $pager;
    }
		*/
		//自定义的
    function renderpager(pagenumber, pagecount, showcount, buttonClickCallback) {

        // setup $pager to hold render
        var $pager = $('<ul class="am-avg-sm-5" style="background:#fff;z-index:1000;padding:5px 0 5px 0;" ></ul>');

        // add in the previous and next buttons
        $pager.append(renderButton('<span class="am-icon-step-backward" style="font-size:16px;color:#AAA"></span>', pagenumber, pagecount, buttonClickCallback)).append(renderButton('<span class="am-icon-backward" style="font-size:16px;color:#AAA"></span>', pagenumber, pagecount, buttonClickCallback));

        // pager currently only handles 10 viewable pages ( could be easily parameterized, maybe in next version ) so handle edge cases
        var startPoint = 1;
        var endPoint = showcount;

        var mid = (showcount - 1) / 2;
        if (pagenumber > mid) {
            startPoint = pagenumber - mid;
            endPoint = pagenumber + mid;
        }

        if (endPoint > pagecount) {
            startPoint = pagecount - (showcount - 1);
            endPoint = pagecount;
        }

        if (startPoint < 1) {
            startPoint = 1;
        }

        // loop thru visible pages and render buttons
        for (var page = startPoint; page <= endPoint; page++) {

            var currentButton = $('<li style="padding:0 12px;box-shadow:inset -1px 0 1px -1px #999;text-align:center;"><a href="###">' + (page)+'<span style="font-size:14px;color:#aaa">&nbsp;&nbsp;/&nbsp;&nbsp;</span>'+(pagecount) + '</a></li>');

            page == pagenumber ? currentButton.addClass('active') : currentButton.click(function() { buttonClickCallback(this.firstChild.firstChild.data); });
            currentButton.appendTo($pager);
        }

        // render in the next and last buttons before returning the whole rendered control back.
        $pager.append(renderButton('<span class="am-icon-forward" style="font-size:16px;color:#AAA">', pagenumber, pagecount, buttonClickCallback)).append(renderButton('<span class="am-icon-step-forward" style="font-size:16px;color:#AAA"></span>', pagenumber, pagecount, buttonClickCallback));

        return $pager;
    }		
		
    // renders and returns a 'specialized' button, ie 'next', 'previous' etc. rather than a page number button
    function renderButton(buttonLabel, pagenumber, pagecount, buttonClickCallback) {
        var $Button = $('<li style="box-shadow:inset -1px 0 1px -1px #999;text-align:center;"><a href="###">' + buttonLabel + '</a></li>');

        var destPage = 1;

        // work out destination page for required button type
        switch (buttonLabel) {
            case '<span class="am-icon-step-backward" style="font-size:16px;color:#AAA"></span>':
                destPage = 1;
                break;
            case '<span class="am-icon-backward" style="font-size:16px;color:#AAA"></span>':
                destPage = pagenumber - 1;
                break;
            case '<span class="am-icon-forward" style="font-size:16px;color:#AAA">':
                destPage = pagenumber + 1;
                break;
            case '<span class="am-icon-step-forward" style="font-size:16px;color:#AAA"></span>':
                destPage = pagecount;
                break;
        }

        // disable and 'grey' out buttons if not needed.
        if (buttonLabel == '<span class="am-icon-step-backward" style="font-size:16px;color:#AAA"></span>' || buttonLabel == '<span class="am-icon-backward" style="font-size:16px;color:#AAA"></span>') {
            pagenumber <= 1 ? $Button.addClass('disabled') : $Button.click(function() { buttonClickCallback(destPage); });
        }
        else {
            pagenumber >= pagecount ? $Button.addClass('disabled') : $Button.click(function() { buttonClickCallback(destPage); });
        }

        return $Button;
    }

    // pager defaults. hardly worth bothering with in this case but used as placeholder for expansion in the next version
    $.fn.pager.defaults = {
        pagenumber: 1,
        pagecount: 1,
        showcount: 9
    };

})(jQuery);





