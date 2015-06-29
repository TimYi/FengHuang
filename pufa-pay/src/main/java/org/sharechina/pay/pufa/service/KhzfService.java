package org.sharechina.pay.pufa.service;

import java.net.URL;
import java.security.SignatureException;

import org.sharechina.pay.pufa.protocal.AccountType;
import org.sharechina.pay.pufa.protocal.PayBank;
import org.sharechina.pay.pufa.protocal.PayType;
import org.sharechina.pay.pufa.protocal.RequestModel;
import org.sharechina.pay.pufa.protocal.ResponseModel;
import org.sharechina.pay.pufa.protocal.pay.KhzfRequestData;
import org.sharechina.pay.pufa.protocal.pay.KhzfResponseData;
import org.springframework.stereotype.Service;

@Service
public class KhzfService {

	/**
	 * 将必要参数转化为能直接使用的支付参数
	 * @param MasterId		企业客户号，企业客户支付时必须提供，个人客户支付时不需要
	 * @param TermSsn		订单号，不能重复
	 * @param MercCode		商户号，开户时获得
	 * @param TermCode		终端号，可以全为0
	 * @param TranAmt		交易金额，单位为元
	 * @param PayBank		支付银行
	 * @param AccountType	账户类型
	 * @param PayType		支付类型
	 * @param Subject		商品名称，商品的标题/交易标题/订单标题/订单关键字等。该参数最长为128个汉字。
	 * @param Notice		商品描述，对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
	 * @param Remark1		交易备注1，交易备注一，该字段将在对账文件中提供
	 * @param Remark2		交易备注2，交易备注二，该字段将在对账文件中提供
	 * @param MercUrl		支付交易中，接收交易结果的url;如果该项为空，则交易结果会送到商户在柜面签约的时候填写的接收结果的url；否则就会送到该地址中。
	 * @param Ip			订单生产时的IP地址，如果该项为空，则不进行IP地址检查。
	 */
	public static RequestModel getKhzfRequestData(String MasterId, String TermSsn, String MercCode, String TermCode, Double TranAmt,
			PayBank PayBank, AccountType AccountType, PayType PayType, String Subject, String Notice,
			String Remark1, String Remark2, URL MercUrl, String Ip) {
		KhzfRequestData data=new KhzfRequestData(MasterId, TermSsn, MercCode, TermCode, TranAmt, PayBank, 
				AccountType, PayType, Subject, Notice, Remark1, Remark2, MercUrl, Ip);
		RequestModel model=new RequestModel(data);
		return model;
	}
	
	public static ResponseModel<KhzfResponseData> resolveKhzfResult(String xml) throws SignatureException {
		return ResponseModel.fromXml(xml, KhzfResponseData.class);
	}
}
