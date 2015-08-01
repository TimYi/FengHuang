//singup_sing
$(function() {
    var g = {};
    g.codeId = "";
    g.tout = null;
    g.httpTip = new Utils.httpTip({});

    $('#signupbtn').bind('click', sendSignupHttp);

    function sendSignupHttp() {
        var url = Base.serverUrl + '/api/signup';
        var posting = true;
        var condi = {};
        condi.username = $('#username').val();
        condi.telephone = $('#telephone').val();

        if (condi.username == '') {
            alert('请输入姓名');
            posting = false;
            return false;
        } else if (condi.telephone == '') {
            alert('请输入手机号');
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

})
