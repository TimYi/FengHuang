package me.chanjar.weixin.pay;

public interface WeiXinPay {
	
	/**
	 * 计算生成H5页面调起支付服务所需参数
	 */
	void computeH5PayRequestParams();

	/**
	 * 统一下单服务，创建微信预订单
	 */
	void createUnifiedOrder();
	
	/**
	 * 调用查询订单接口
	 */
	void orderQuery();
	
	/**
	 * 关闭订单
	 * 应用场景：商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；
	 * 系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
	 */
	void closeOrder();
	
	/**
	 * 申请退款
	 */
	void refound();
	
	/**
	 * 查询退款
	 */
	void refoundQuery();
	
	/**
	 * 下载对账单
	 */
	void downloadBill();
	
	/**
	 * 支付结果通知
	 */
	void payResultNotice();
}
