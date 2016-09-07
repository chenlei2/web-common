package com.chenlei.web.common.util.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
/**
 * httpclient 工具类
 * @author chenlei
 *
 */
public class HttpclientUtil {
	
	private static Log log = LogFactory.getLog(HttpclientUtil.class.getName());
	public static String doPost(String url, Map<String, String> params){
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();   
		for (Entry<String, String> en : params.entrySet()) {
			nvps.add(new BasicNameValuePair(en.getKey(), en.getValue()));
		}
		RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
		ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
		registryBuilder.register("http", plainSF);	
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {

				@Override
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}		 
		   };
		   ctx.init(null, new TrustManager[] { tm }, null);
		   @SuppressWarnings("deprecation")
		LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(ctx, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		   registryBuilder.register("https", sslSF);
		} catch (NoSuchAlgorithmException e) {
			log.error(e);
		} catch (KeyManagementException e) {
			log.error(e);
		}
       
		Registry<ConnectionSocketFactory> registry = registryBuilder.build();
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		cm.setMaxTotal(200);
		cm.setDefaultMaxPerRoute(20);
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
		HttpPost post = new HttpPost(url); 
		CloseableHttpResponse response = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps));
			response = httpClient.execute(post);
		    HttpEntity entity = response.getEntity();
		    return EntityUtils.toString(entity,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e);
		} catch (ClientProtocolException e) {	
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} finally {
            try {
				httpClient.close();
				if(response != null){
					response.close();
				}	
			} catch (IOException e) {
				log.error(e);
			}
        }	
		return null;
	}
}
