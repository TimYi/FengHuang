package org.sharechina.pay.pufa.service;

import java.security.SignatureException;

import org.sharechina.pay.pufa.common.SimpleHttpsService;
import org.sharechina.pay.pufa.common.HttpsService;
import org.sharechina.pay.pufa.protocal.RequestModel;
import org.sharechina.pay.pufa.protocal.ResponseModel;
import org.sharechina.pay.pufa.protocal.TransName;
import org.sharechina.pay.pufa.protocal.query.QueryRequestData;
import org.springframework.stereotype.Service;

/**
 * 单笔查询服务
 * @author pc
 *
 */
@Service
public class KhcxService {

	private HttpsService httpsService=new SimpleHttpsService();

	public void setHttpsService(HttpsService httpsService) {
		this.httpsService = httpsService;
	}
	
	/**
	 * 
	 * @param mercCode	商户号，开户时获得
	 * @param OTranAbbr	原交易缩写
	 * @param termSsn	订单号，不能重复
	 */
	public ResponseModel<QueryRequestData> sendKhcxRequest(String mercCode, TransName OTranAbbr, String termSsn)
		throws SignatureException {
		QueryRequestData data=new QueryRequestData(mercCode, OTranAbbr, termSsn);
		RequestModel model=new RequestModel(data);
		String result=httpsService.postXml(RequestModel.PRODUCTION_URL, model.toXml());
		return ResponseModel.fromXml(result, QueryRequestData.class);
	}
}
