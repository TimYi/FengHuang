/*699套餐*/
function Buy_699()
{
	AddShoppingCart_699("buy");
}
/*799软装包*/
function Buy_799()
{
	AddShoppingCart_799("buy");
}
/*1299电器包*/
function Buy_1299()
{
	AddShoppingCart_1299("buy");
}
/*预约量房*/
function Buy_lf()
{
	AddShoppingCart_lf("buy");
}
/*预约验房*/
function Buy_yf()
{
	AddShoppingCart_yf("buy");
}
/*预约设计*/
function Buy_sj()
{
	AddShoppingCart_sj("buy");
}
/*预约凤凰管家*/
function Buy_fhgj()
{
	AddShoppingCart_fhgj("buy");
}
/*预约家装贷*/
function Buy_jzd()
{
	AddShoppingCart_jzd("buy");
}
/*预约体验馆*/
function Buy_tyg_beijing()
{
	AddShoppingCart_tyg_beijing("buy");
}
function Buy_tyg_xiamen()
{
	AddShoppingCart_tyg_xiamen("buy");
}




/* 加入购物车 */
function AddShoppingCart_699(a)
{
	$.ajax({
		url  : '4g.php?m=u_shoppingcart&a=addshopingcart',
		type : 'post',
		data : {'typeid':28, 'goodsid':1, 'buynum':1, 'attrid_1':1, 'attrid_2':2,},
		dataType:'html',
		//beforeSend:function(){},
		success:function(data){
			if(a == "buy"){
				location.href = "4g.php?m=u_shoppingcart&a=buynow";
			} else {
				alert("加入购物车成功！");
			}
		}
	});
}

function AddShoppingCart_799(a)
{
	$.ajax({
		url  : '4g.php?m=u_shoppingcart&a=addshopingcart',
		type : 'post',
		data : {'typeid':28, 'goodsid':2, 'buynum':1, 'attrid_1':1, 'attrid_2':2,},
		dataType:'html',
		//beforeSend:function(){},
		success:function(data){
			if(a == "buy"){
				location.href = "4g.php?m=u_shoppingcart&a=buynow";
			} else {
				alert("加入购物车成功！");
			}
		}
	});
}

function AddShoppingCart_1299(a)
{
	$.ajax({
		url  : '4g.php?m=u_shoppingcart&a=addshopingcart',
		type : 'post',
		data : {'typeid':28, 'goodsid':3, 'buynum':1, 'attrid_1':1, 'attrid_2':2,},
		dataType:'html',
		//beforeSend:function(){},
		success:function(data){
			if(a == "buy"){
				location.href = "4g.php?m=u_shoppingcart&a=buynow";
			} else {
				alert("加入购物车成功！");
			}
		}
	});
}
/*预约量房*/
function AddShoppingCart_lf(a)
{
	$.ajax({
		url  : '4g.php?m=u_shoppingcart&a=addshopingcart',
		type : 'post',
		data : {'typeid':28, 'goodsid':4, 'buynum':1, 'attrid_1':1, 'attrid_2':2,},
		dataType:'html',
		//beforeSend:function(){},
		success:function(data){
			if(a == "buy"){
				location.href = "4g.php?m=u_shoppingcart&a=buynow";
			} else {
				alert("加入购物车成功！");
			}
		}
	});
}
/*预约验房*/
function AddShoppingCart_yf(a)
{
	$.ajax({
		url  : '4g.php?m=u_shoppingcart&a=addshopingcart',
		type : 'post',
		data : {'typeid':28, 'goodsid':5, 'buynum':1, 'attrid_1':1, 'attrid_2':2,},
		dataType:'html',
		//beforeSend:function(){},
		success:function(data){
			if(a == "buy"){
				location.href = "4g.php?m=u_shoppingcart&a=buynow";
			} else {
				alert("加入购物车成功！");
			}
		}
	});
}
/*预约设计*/
function AddShoppingCart_sj(a)
{
	$.ajax({
		url  : '4g.php?m=u_shoppingcart&a=addshopingcart',
		type : 'post',
		data : {'typeid':28, 'goodsid':6, 'buynum':1, 'attrid_1':1, 'attrid_2':2,},
		dataType:'html',
		//beforeSend:function(){},
		success:function(data){
			if(a == "buy"){
				location.href = "4g.php?m=u_shoppingcart&a=buynow";
			} else {
				alert("加入购物车成功！");
			}
		}
	});
}
/*预约凤凰管家*/
function AddShoppingCart_fhgj(a)
{
	$.ajax({
		url  : '4g.php?m=u_shoppingcart&a=addshopingcart',
		type : 'post',
		data : {'typeid':28, 'goodsid':7, 'buynum':1, 'attrid_1':1, 'attrid_2':2,},
		dataType:'html',
		//beforeSend:function(){},
		success:function(data){
			if(a == "buy"){
				location.href = "4g.php?m=u_shoppingcart&a=buynow";
			} else {
				alert("加入购物车成功！");
			}
		}
	});
}
/*预约家装贷*/
function AddShoppingCart_jzd(a)
{
	$.ajax({
		url  : '4g.php?m=u_shoppingcart&a=addshopingcart',
		type : 'post',
		data : {'typeid':28, 'goodsid':8, 'buynum':1, 'attrid_1':1, 'attrid_2':2,},
		dataType:'html',
		//beforeSend:function(){},
		success:function(data){
			if(a == "buy"){
				location.href = "4g.php?m=u_shoppingcart&a=buynow";
			} else {
				alert("加入购物车成功！");
			}
		}
	});
}
/*预约北京体验馆*/
function AddShoppingCart_tyg_beijing(a)
{
	$.ajax({
		url  : '4g.php?m=u_shoppingcart&a=addshopingcart',
		type : 'post',
		data : {'typeid':28, 'goodsid':9, 'buynum':1, 'attrid_1':1, 'attrid_2':2,},
		dataType:'html',
		//beforeSend:function(){},
		success:function(data){
			if(a == "buy"){
				location.href = "4g.php?m=u_shoppingcart&a=buynow";
			} else {
				alert("加入购物车成功！");
			}
		}
	});
}

/*预约厦门体验馆*/
function AddShoppingCart_tyg_xiamen(a)
{
	$.ajax({
		url  : '4g.php?m=u_shoppingcart&a=addshopingcart',
		type : 'post',
		data : {'typeid':28, 'goodsid':25, 'buynum':1, 'attrid_1':1, 'attrid_2':2,},
		dataType:'html',
		//beforeSend:function(){},
		success:function(data){
			if(a == "buy"){
				location.href = "4g.php?m=u_shoppingcart&a=buynow";
			} else {
				alert("加入购物车成功！");
			}
		}
	});
}

function jzd_calc()
{
	var jzd_myhk = 0; /*每日还款*/
	var jzd_dkje = 0; /*贷款金额*/
	var jzd_dkzq = 0; /*贷款周期*/
	var jzd_bxhj = 0; /*本息合计*/

	dkje = $("#jzd_je").val(); 
	dkzq = $("#jzd_zq").val();
	
	$("#jzd_dkje").html(dkje);
	$("#jzd_dkzq").html(dkzq);
	


switch(dkzq)
{
	case "12":
		myhk = (dkje*1 + dkje*0.04) / dkzq*1;
		var result1 = Math.round(myhk*100)/100;  
		$("#jzd_myhk").html(result1);
		jzd_bxhj = dkzq*result1;
		var result2 = Math.round(jzd_bxhj*100)/100;  
		$("#jzd_bxhj").html(result2);
	  break;
	case "24":
		myhk = (dkje*1 + dkje*0.08) / dkzq*1;
		var result1 = Math.round(myhk*100)/100;  
		$("#jzd_myhk").html(result1);
		jzd_bxhj = dkzq*result1;
		var result2 = Math.round(jzd_bxhj*100)/100;  
		$("#jzd_bxhj").html(result2);	
	  break;
	case "36":
		myhk = (dkje*1 + dkje*0.12) / dkzq*1;
		var result1 = Math.round(myhk*100)/100;  
		$("#jzd_myhk").html(result1);
		jzd_bxhj = dkzq*result1;
		var result2 = Math.round(jzd_bxhj*100)/100;  
		$("#jzd_bxhj").html(result2);	
	  break;  
	  
	default:
		$("#jzd_myhk").html(0);
		$("#jzd_bxhj").html(0);	
}

}
