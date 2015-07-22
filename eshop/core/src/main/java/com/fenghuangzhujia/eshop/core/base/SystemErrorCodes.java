package com.fenghuangzhujia.eshop.core.base;

import com.fenghuangzhujia.foundation.core.rest.ErrorCode;

/**
 * 系统级 @ErrorCode
 * 分配从1~100的code
 * @author pc
 *
 */
public class SystemErrorCodes {
	//code 0~100，为系统错误，每个domain有自己的错误号区间
	//将来系统庞大的时候，每个domain可以有一个自己的错误列表，在统一分配的错误号区间内自行定义
	/**
	 * 用户需要登录
	 */
	public static final ErrorCode UNAUTH;
	
	public static final ErrorCode TOKEN_ERROR;
	
	public static final ErrorCode REGIST_ERROR;
	
	public static final ErrorCode LOGIN_ERROR;
	
	public static final ErrorCode CHANGE_PASSWORD_ERROR;
	
	public static final ErrorCode CREDENTIAL_ERROR;
	
	public static final ErrorCode CAPTCHA_ERROR;
	/**
	 * 文件处理异常
	 */
	public static final ErrorCode FILE_ERROR;
	
	/**
	 * 数据库实体没有找到
	 */
	public static final ErrorCode ENTITY_NOT_FOUND;
	/**
	 * 非法参数
	 */
	public static final ErrorCode ILLEGAL_ARGUMENT;
	/**权限不足*/
	public static final ErrorCode ACCESS_DENIED;
	
	public static final ErrorCode MESSAGE_SEND_ERROR;
	
	//支付相关异常81~90
	public static final ErrorCode PAY_FAILED;
	public static final ErrorCode SIGNATURE_WRONG;
	public static final ErrorCode DRAWBACK_FAILED;
	
	//订单相关异常91~100
	public static final ErrorCode GOOD_CONVERTER_MISSING;
	/**
	 * 无货
	 */
	public static final ErrorCode NO_GOOD;
	/**
	 * 商品类型不匹配
	 */
	public static final ErrorCode GOOD_TYPE_DISMISS;
	/**
	 * 商品信息不完整
	 */
	public static final ErrorCode GOOD_INFO_MISSING;
	/**商品暂不销售*/
	public static final ErrorCode NOT_ON_SALE;
	
	
	//100 未知错误
	public static final ErrorCode UNKNOWN_ERROR;
	
	
	//Schedual相关，101~110
	/**
	 * schedual校验时发现有时间重叠
	 * code 101
	 */
	public static final ErrorCode SCHEDUAL_CONFILICT;
	/**
	 * 想要占用或者取消已经被预约的时间段
	 * code 102
	 */
	public static final ErrorCode SCHEDUAL_OCCUPIED;
	/**
	 * 活动自身不符合指定应用规则
	 */
	public static final ErrorCode ACTIVE_ILLEGAL;
	
	//Imd 订单生成相关，111~120
	/**
	 * 活动时间已经被占用
	 */
	public static final ErrorCode ACTIVITY_OCCUPIED;
	
	//短信验证相关，121~130
	
	public static final ErrorCode VALIDATE_ERROR;
	
	//预约相关异常131~140
	public static final ErrorCode APPOINT_CONSTRAINED;
	public static final ErrorCode NOT_APPOINTED;
	
	
	//其它错误，999
	/**
	 * 其它错误,999
	 */
	public static final ErrorCode OTHER;

	static {
		UNAUTH=new ErrorCode(1, "您需要登录");
		TOKEN_ERROR=new ErrorCode(2,"token错误！");
		REGIST_ERROR=new ErrorCode(3, "注册错误失败");
		LOGIN_ERROR=new ErrorCode(4, "登录失败");
		CHANGE_PASSWORD_ERROR=new ErrorCode(5, "修改密码错误");	
		ENTITY_NOT_FOUND=new ErrorCode(6, "实体未找到");
		ILLEGAL_ARGUMENT=new ErrorCode(7, "非法参数");
		CREDENTIAL_ERROR=new ErrorCode(8, "用户名错误");
		CAPTCHA_ERROR=new ErrorCode(9, "图形验证码错误");
		FILE_ERROR=new ErrorCode(10, "文件处理异常");
		ACCESS_DENIED=new ErrorCode(11, "您没有相应权限");
		MESSAGE_SEND_ERROR=new ErrorCode(12, "短信发送错误");
		
		
		
		
		
		PAY_FAILED=new ErrorCode(81, "支付失败");
		SIGNATURE_WRONG=new ErrorCode(82, "签名错误");
		DRAWBACK_FAILED=new ErrorCode(83, "退款失败");
		
		GOOD_CONVERTER_MISSING=new ErrorCode(91, "商品转换器缺失！");
		NO_GOOD=new ErrorCode(92, "无货");
		GOOD_TYPE_DISMISS=new ErrorCode(93,"商品类型不统一");
		GOOD_INFO_MISSING=new ErrorCode(94, "商品信息不完整");
		NOT_ON_SALE=new ErrorCode(95, "商品暂不销售");
		
		UNKNOWN_ERROR=new ErrorCode(100, "未知错误");
		
		SCHEDUAL_CONFILICT=new ErrorCode(101, "出现时间重叠，请重新选择时间！");
		SCHEDUAL_OCCUPIED=new ErrorCode(102, "此时间段已经被预约，请您重新选择！");
		ACTIVE_ILLEGAL=new ErrorCode(103, "活动时间不符合规则！");
		
		ACTIVITY_OCCUPIED=new ErrorCode(111, "您选择的活动时间已经被占用");
		
		VALIDATE_ERROR=new ErrorCode(121, "验证码相关异常");
		
		APPOINT_CONSTRAINED=new ErrorCode(131, "不满足预约限制条件");
		NOT_APPOINTED=new ErrorCode(132, "您没有预约成功");
		
		OTHER=new ErrorCode(999, "其它");
	}
}
