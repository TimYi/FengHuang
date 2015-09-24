Welcome to the FengHuang wiki!


# 业务模型

* [用户](model_user)
* [权限](model_auth)
* [会员分组](model_user_group)
* [预约](model_appoint)
* [装修套餐](model_package)
* [套餐抢购](model_package_scramble)
* [订单支付](model_pay)
* [订单管理](model_order)
* [优惠券](model_coupons)
* [系统留言](model_message)
* [评论](model_comment)
* [文章](model_article)
* [装修案例](model_case)
* [家装进度](model_live)
* [收藏](model_collect)
* [个人家装信息](model_myhouse)
* [地区](model_area)
* [装修材料管理](model_meteria)
* [短信通知模板](model_shortMessages)
* [菜单管理](model_menu)
* [轮播图片管理](model_carousel)
* [体验馆](model_experience)
* [模板引擎](model_template)
* [订单退款](model_drawback)

# 接口说明

* [基本数据格式](dataModel)

## 公共接口

### 验证码接口

* [获取短信验证码【公用】](message)
* [获取图形验证码【公用】](captcha)

### 地区接口

* [按区域等级获取所有区域【公用】](areaByLevel)
* [按上级所选区域id获取下级区域列表【公用】](areaByUpper)

### 字典接口

* [字典列表【公用】](dics)

### 账户相关接口

* [新用户注册](regist)
* [用户登录](login)
* [qq登录](qqAuth)
* [修改密码](changePassword)

## 前端接口

### 用户相关接口

* [绑定手机号码](bindMobile)
* [获取个人信息](getProfile)
* [修改个人信息](editProfile)
* [获取我的未读信息数量](myinfoCounts)
* [上传头像接口](changeAvatar)
* [申请忘记密码](forgetPassword)
* [找回忘记密码](changeForgotPassword)

### 用户房屋信息接口

* [获取个人房屋信息列表](myhouses)
* [获取个人房屋信息](myhouse)
* [修改个人房屋信息](editmyhouse)
* [添加个人房屋信息](addmyhouse)
* [删除个人房屋信息](deletemyhouse)

### 预约接口
* [预约](appoint)
* [预约套餐](appointPackage)
* [我的预约列表](userAppoints)
* [个人预约详情](userAppoint)

### 订单接口
* [我的订单列表](orders)
* [我的订单详情](order)
* [取消订单](orderCancel)
* [申请退款](orderDrawback)

### 支付接口
* [浦发支付参数计算接口](pufapay)
* [支付回调参数](payrevoke)

### 评论接口
* [某个资源评论列表](comments)
* [发表评论](comment)
* [获取我的评论列表](mycomments)

### 收藏接口
* [收藏某种资源](collect)
* [我的收藏列表](mycollects)
* [查看我的收藏](mycollect)

### 优惠券接口
* [我的优惠券列表](coupons)
* [优惠券抢购信息](couponsScrambleInfo)
* [抢购优惠券](couponsScramble)

### 留言接口
* [获取我的留言列表](mymessages)
* [查看我的留言](mymessage)
* [删除我的留言](deletemymessage)

### 产品接口
* [套餐列表](packages)
* [套餐详情](package)
* [案例标签列表](caseTags)
* [装修案例列表](cases)
* [案例详情](case)
* [预约套餐](appointPackage)
* [抢购套餐](scrable)

### 直播接口
* [直播列表](lives)
* [直播详情](liveDetail)
* [直播详情分页查询](liveDetailPage)
* [我的直播列表](myLives)
* [我的直播详情](myLive)
* [我的直播详情分页查询](myLiveDetailPage)

### 文章接口
* [文章列表](articals)
* [文章详情](artical)
* [获取指定代码的文章](articalByCode)

### 体验馆接口

* [体验馆列表](museums)
* [体验馆详情](museum)
* [预约体验馆](appointMuseum)
* [我的体验馆预约列表](myMuseumAppoints)
* [我的体验馆预约详情](myMuseumAppoint)

## 前端页面动态化相关接口

### 主材管理接口

* [获取全部主材信息](meterias)
* [获取套餐用材信息](packageMeterias)
* [获取套餐品牌信息](packageBrands)

### 菜单和轮播

* [菜单列表接口](navigations)
* [轮播列表接口](carousels)
* [全部工艺流程](technologies)


### 活动

* [报名接口](signup)
* [报名查询](signupQuery)

## 后台管理接口
* [后台管理接口基本说明](adminOverview)
* [动态查询接口说明](dynamicSearch)
* [用户管理【动态】](adminUser)
* [栏目管理](adminMenu)
* [地区管理](adminArea)
* [套餐管理【动态】](adminPackage)
* [套餐详情管理【动态】](adminDecorateSpace)
* [案例管理【动态】](adminCase)
* [文章管理【动态】](adminArtical)
* [角色管理](adminRole)
* [优惠券管理【动态】](adminCoupons)
* [分发优惠券定义管理【动态】](adminCouponsDef)
* [评论集合管理【动态】](adminComment)
* [评论管理【动态】](adminCommentItem)
* [留言管理【动态】](adminMessage)
* [分组管理【动态】](adminUserGroup)
* [工人管理【动态】](adminWorker)
* [直播【动态】](adminLive)
* [每日直播详情【动态】](adminLiveDetail)
* [订单管理](adminOrder)
* [品牌管理【动态】](adminBrand)
* [品牌下产品管理【动态】](adminProduct)
* [产品下主材料管理【动态】](adminMeteria)
* [导航菜单管理](adminNavigation)
* [轮播图片管理](adminCarousel)
* [模板管理【动态】](adminTemplate)
* [退款管理](adminDrawback)
* [体验馆管理【动态】【新增】](adminExperienceMuseum)
* [体验馆预约管理【新增】](adminExperienceAppoint)
* [装修工艺管理【动态】【新增】](adminDecorateTechnology)
* [报名统计【新增】](adminSignup)

# 服务器配置

* [服务器配置记录](serverConfig)
* [数据初始化](serverInit)

# 微信公众号

* [菜单](底部菜单)

# 项目进度

* [针对最新改动的任务安排](schedule_newCommand)