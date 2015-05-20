 
 **请求URL**  
     
    http://101.200.229.135:8080/api/regist?username=ytm&password=123456&mobile=18612444099&validater=3967
    
 **请求方式**  
    
    POST  
 **请求参数**  
   username:用户名，暂时加了个validate方法，限制只能手机号码注册。
   password:密码  
   mobile:手机号码，仍旧要求传，因为以后用户名不一定是手机号码
   validater:之前请求的短信验证码


 **返回参数** 
   result:里面放登录token，客户端需要保存，并在每次请求有限制的接口时附带此参数
    
 **返回示例**  

    {
        "result": "44a43137-4301-4f2b-b1f0-264908c7abc5",
        "status": "OK"
    }