<%@ page language="java" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Spring3 RESTful</title>
    <SCRIPT TYPE="text/javascript">
            function go(){
                //var url = "/simple/index/";   
                var HOST='http://101.200.229.135:8080/api/admin/';             
                var url = document.getElementById("uriTxt").value;
                var sn = '94366ad99d5b487ca867741b7707bbc7';
                alert(url);
                var request =  new XMLHttpRequest();
                request.open("GET", url, true);
                request.setRequestHeader("Content-Type","application/x-javascript;");
                request.onreadystatechange = function() {
                	//alert(request.readyState);
                    if (request.readyState == 4) {
                    	alert(request.status);
                        if (request.status == 0){
                            if (request.responseText) {
                                document.getElementById("text").innerHTML = request.responseText;
                            }
                        }
                    }
                };
                request.send(null);
            }
        </SCRIPT>
  </head>
  
  <body>
  	输入测试请求uri:<input type="text" size='100' id='uriTxt' > 
  	<input type="button" value="请求" onclick="go()">
  	<div id="text"></div>
    ${message}
  </body>
</html>
