<?php	require_once(dirname(__FILE__).'/include/config.inc.php');

/*
**************************
(C)2010-2014 phpMyWind.com
update: 2013-4-27 16:57:34
person: Adu
**************************
*/


//定义入口常量
define('IN_MOBILE', TRUE);


//初始化参数
$m   = isset($m)   ? $m   : 'index'; 			   //控制显示模型
$c   = isset($c)   ? $c   : '';          //控制显示形式
$a   = isset($a)   ? $a   : '';          //控制执行操作
$cid = isset($cid) ? intval($cid) : 0;   //显示栏目ID
$id  = isset($id)  ? intval($id)  : 0;   //显示内容ID



//首页
if($m == 'index')
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/index.php');
	exit();
}

//二级页
else if($m == 'info' or $m == 'list' or $m == 'img')
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/page.php');
	exit();
}

//详细页 
else if($m == 'show')
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/show.php');
	exit();
}

//家装套餐 
else if($m == 'u_jztc') 
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/jztc.php');
	exit();
}

//免费服务 
else if($m == 'u_mffw') 
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/mffw.php');
	exit();
}

//体验馆 
else if($m == 'u_tyg') 
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/tyg.php');
	exit();
}

//家装贷 
else if($m == 'u_jzd') 
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/jzd.php');
	exit();
}

//凤凰管家 
else if($m == 'u_fhgj') 
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/fhgj.php');
	exit();
}

//案例直播 
else if($m == 'u_alzb') 
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/alzb.php');
	exit();
}

//服务展示 
else if($m == 'u_fwzs') 
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/fwzs.php');
	exit();
}

//会员中心 
else if($m == 'u_hyzx') 
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/hyzx.php');
	exit();
}

/*********************************************************************/

//案例详情
else if($m == 'u_alxq') 
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/alxq.php');
	exit();
}

//直播详情
else if($m == 'u_zbxq') 
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/zbxq.php');
	exit();
}

//个人资料
else if($m == 'u_grzl') 
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/center/grzl.php');
	exit();
}

//我的留言
else if($m == 'u_wdly') 
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/center/wdly.php');
	exit();
}

//家装进度
else if($m == 'u_jzjd') 
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/center/jzjd.php');
	exit();
}

//我的订单
else if($m == 'u_wddd') 
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/center/wddd.php');
	exit();
}

//我的收藏
else if($m == 'u_wdsc') 
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/center/wdsc.php');
	exit();
}

//我的评论
else if($m == 'u_wdpl') 
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/center/wdpl.php');
	exit();
}

/*
else if($m == 'findpark') //找车位
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/findpark.php');
	exit();
}
else if($m == 'pubpark') //发布车位
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/pubpark.php');
	exit();
}
else if($m == 'map')
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/map.php');
	exit();
}
else if($m == 'map_get')
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/map_get.php');
	exit();
}
else if($m == 'login')
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/login.php');
	exit();
}
else if($m == 'reg')
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/reg.php');
	exit();
}
else if($m == 'default')
{
	require_once(PHPMYWIND_TEMP.'/default/mobile/default.php');
	exit();
}
*/
?>