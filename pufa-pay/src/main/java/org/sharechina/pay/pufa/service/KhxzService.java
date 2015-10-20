package org.sharechina.pay.pufa.service;

import java.security.SignatureException;

import org.sharechina.pay.pufa.common.HttpsService;
import org.sharechina.pay.pufa.common.SimpleHttpsService;
import org.sharechina.pay.pufa.protocal.RequestModel;
import org.sharechina.pay.pufa.protocal.ResponseModel;
import org.sharechina.pay.pufa.protocal.download.KhxzRequestData;
import org.sharechina.pay.pufa.protocal.download.KhxzRequestData.SetFType;
import org.springframework.stereotype.Service;

@Service
public class KhxzService {

	private HttpsService httpsService=new SimpleHttpsService();

	public void setHttpsService(HttpsService httpsService) {
		this.httpsService = httpsService;
	}
	
	/**
	 * 
	 * @param MercCode	商户号
	 * @param OSttdate	清算日期
	 * @param SetFType	清算文件类型
	 */
	public String khxz(String MercCode, String OSttDate, SetFType SetFType) throws SignatureException {
		KhxzRequestData data=new KhxzRequestData(MercCode, OSttDate, SetFType);
		RequestModel model=new RequestModel(data);
		String result=httpsService.postXml(RequestModel.PRODUCTION_URL, model.toXml());
		System.out.println(result);
		ResponseModel<String> response=ResponseModel.fromXml(result, String.class);
		return response.getData();
	}
}
