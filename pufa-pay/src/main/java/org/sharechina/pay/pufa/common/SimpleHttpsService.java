package org.sharechina.pay.pufa.common;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.X509TrustManager;

/**
 * 发送post请求
 * @author pc
 *
 */
public class SimpleHttpsService implements HttpsService {
	
	private static final CloseableHttpClient HTTPS_CLIENT;
	static {
		try {
			SSLContext sslContext = SSLContext.getInstance("SSL");
			//不做https证书检验
			//TODO 完善证书验证过程
			sslContext.init(null, new TrustManager[] { new X509TrustManager() {
		         public X509Certificate[] getAcceptedIssuers() {
		                 return null;
		         }
		
		         public void checkClientTrusted(X509Certificate[] certs,
		                         String authType) {
		         }
		
		         public void checkServerTrusted(X509Certificate[] certs,
		                         String authType) {
		         }
			}},new SecureRandom());
			SSLConnectionSocketFactory socketFactory=new SSLConnectionSocketFactory(sslContext);
			HTTPS_CLIENT = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 发送xml post请求
	 * @param url
	 * @param xml
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String postXml(String url, String xml) {
		try {
			//System.out.println(xml);
			HttpPost httpPost = new HttpPost(url);
			//得指明使用GBK编码，否则到API服务器XML的中文不能被成功识别
		    StringEntity postEntity = new StringEntity(xml, "GBK");
	        httpPost.addHeader("Content-Type", "text/xml");
	        httpPost.setEntity(postEntity);
	        HttpResponse response = HTTPS_CLIENT.execute(httpPost);
	        HttpEntity entity = response.getEntity();
	        String result = EntityUtils.toString(entity, "GBK");
	        return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
