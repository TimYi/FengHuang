package org.sharechina.pay.pufa.service;

import java.security.SignatureException;

import org.sharechina.pay.pufa.common.SimpleHttpsService;
import org.sharechina.pay.pufa.common.HttpsService;
import org.sharechina.pay.pufa.protocal.RequestModel;
import org.sharechina.pay.pufa.protocal.ResponseModel;
import org.sharechina.pay.pufa.protocal.refund.RefundRequestData;
import org.sharechina.pay.pufa.protocal.refund.RefundResponseData;
import org.springframework.stereotype.Service;

/**
 * 跨行退货服务
 * @author pc
 *
 */
@Service
public class KhthService {

	private HttpsService httpsService=new SimpleHttpsService();

	public void setHttpsService(HttpsService httpsService) {
		this.httpsService = httpsService;
	}
	
	/**
	 * 
	 * @param masterId	企业客户号，企业客户支付时必须提供，个人客户支付时不需要
	 * @param termSsn	订单号，不能重复，与支付时的订单号不是同一个，需要新生成一个单号并记录
	 * @param osttDate	原交易的清算日期，从支付回调结果中记录
	 * @param oacqSsn	原交易的网关流水，从支付回调结果中记录
	 * @param mercCode	商户号，开户时获得
	 * @param termCode	终端号，可以全为0，已经提供了全为0的默认值
	 * @param tranAmt	交易金额，单位为元
	 * @param remark1	交易备注1，交易备注一，该字段将在对账文件中提供
	 * @param remark2	交易备注2，交易备注二，该字段将在对账文件中提供
	 */
	public ResponseModel<RefundResponseData> sendKhthRequest(String masterId, String termSsn, String osttDate, String oacqSsn, 
			String mercCode, String termCode, Double tranAmt, String remark1, String remark2)
			throws SignatureException {
		RefundRequestData data=new RefundRequestData(masterId, termSsn, osttDate, oacqSsn, mercCode, termCode,
				tranAmt, remark1, remark2);
		RequestModel model=new RequestModel(data);
		String result=httpsService.postXml(RequestModel.PRODUCTION_URL, model.toXml());
		ResponseModel<RefundResponseData> response=ResponseModel.fromXml(result, RefundResponseData.class);
		return response;
	}
}
