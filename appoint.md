 
 **请求URL**  
     
    http://101.200.229.135:8080/api/user/appoint
    
 **请求方式**  
    
    POST  
 **请求参数**  
     
    token:用户凭据
    typeid:预约类别（字典类型名称：appoint）
    address:手动填写地址
    mobile:电话号码
    
 **返回示例**  

    {
        "result": {
            "totalCount": 0,
            "totalPages": 0,
            "page": 0,
            "size": 2,
            "result": []
        },
        "status": "OK"
    }