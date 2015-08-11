//singup_sing
$(function() {
    var g = {};
    g.codeId = "";
    g.tout = null;
    g.httpTip = new Utils.httpTip({});

    $('#signupbtn').bind('click', sendSignupHttp);
    $('#querybtn').bind('click', sendQueryHttp);

    function sendSignupHttp() {
        var url = Base.serverUrl + '/api/signup';
        var posting = true;
        var condi = {};
        condi.name = $('#name').val();
        condi.telephone = $('#telephone').val();
        condi.name2 = $('#name2').val();
        condi.telephone2 = $('#telephone2').val();
        condi.relation = $('#relation').val();

        if (condi.name == '') {
            alert('请输入选手一姓名');
            posting = false;
            return false;
        } else if (condi.telephone == '') {
            alert('请输入选手一手机号');
            posting = false;
            return false;
        } else if (condi.name2 == '') {
            alert('请输入选手二姓名');
            posting = false;
            return false;
        } else if (condi.telephone2 == '') {
            alert('请输入选手二手机号');
            posting = false;
            return false;
        } else if (condi.relation == '') {
            alert('请输入选手关系');
            posting = false;
            return false;
        }

        g.httpTip.show();

        if (posting) {
            $.ajax({
                url: url,
                data: condi,
                type: "POST",
                dataType: "json",
                context: this,
                global: false,
                success: function(data) {
                    console.log("sendSignupHttp", data);
                    var status = data.status || "";
                    if (status == "OK") {
                        alert(data.result);
                        location.href = 'index.html';
                    } else {
                        alert('报名失败：' + data.result);
                    }

                    g.httpTip.hide();
                },
                error: function(data) {
                    g.httpTip.hide();
                }
            });
        }
    }

    function sendQueryHttp() {
        var url = Base.serverUrl + '/api/signup/query';
        var condi = {};
        condi.telephone = $('#queryText').val();
        $.ajax({
            url: url,
            data: condi,
            type: "GET",
            dataType: "json",
            context: this,
            global: false,
            success: function(data) {
                var status = data.status || "";
                if (status == "OK") {
                    changeResultHtml(data);
                } else {
                    alert('查询报名失败：' + data.result);
                }

                g.httpTip.hide();
            },
            error: function(data) {
                g.httpTip.hide();
            }
        });
    }

    function changeResultHtml(data) {
        var obj = data.result || '';
        var len = obj.length;
        var html = [];
        if (len > 0) {
            for (var i = 0; i < len; i++) {
                var name = obj[i].name || '';
                var name2 = obj[i].name2 || '';
                var telephone = obj[i].telephone || '';
                var telephone2 = obj[i].telephone2 || '';
                var time = obj[i].createTime || '';
                html.push('<p>选手一：' + name + '(' + telephone + ')<br>');
                html.push('选手二：' + name2 + '(' + telephone2 + ')<br>');
                html.push('报名时间：' + time + '</p>');
                html.push('<hr>');
            }
        } else {
            html.push('<p>没有搜索到该选手报名信息</p><hr>');
        }
        $('#result').html(html.join(''));
    }

})
