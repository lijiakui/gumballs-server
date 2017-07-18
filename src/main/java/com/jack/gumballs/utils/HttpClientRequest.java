package com.jack.gumballs.utils;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class HttpClientRequest {

	private static final Log log = LogFactory.getLog(HttpClientRequest.class);

	public static String getHttp(String param1, String param2) {
		String responseMsg = "";
		HttpClient httpClient = new HttpClient();
		String url = "http://localhost:8080/UpDown/httpServer?param1=" + param1
				+ "&param2=" + param2;
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		try {
			httpClient.executeMethod(getMethod);
			byte[] responseBody = getMethod.getResponseBody();
			responseMsg = new String(responseBody);
			log.info(responseMsg);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return responseMsg;
	}

	
	public static String postHttp(String url, Map<String,String> params) {
		String responseMsg = "";
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("utf-8");
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10 * 1000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10 * 1000);
		PostMethod postMethod = new PostMethod(url);
		
		Iterator<String> it = params.keySet().iterator();
		while(it.hasNext()){
			String key = (String) it.next();
			postMethod.addParameter(key, params.get(key));
		}
		try {
			httpClient.executeMethod(postMethod);
//			responseMsg = postMethod.getResponseBodyAsString().trim();
			InputStream inputStream = postMethod.getResponseBodyAsStream();  
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  
			StringBuffer stringBuffer = new StringBuffer();  
			String str= "";  
			while((str = br.readLine()) != null){  
			stringBuffer .append(str );  
			}  
			responseMsg=stringBuffer.toString();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return responseMsg;
	}
	public static String postData(String url, Map<String, String> params, String codePage) throws Exception {
		final HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30 * 1000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(30 * 1000);

		final PostMethod method = new PostMethod(url);
		if (params != null) {
			method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, codePage);
			method.setRequestBody(assembleRequestParams(params));
		}
		String result = "";
		try {
			httpClient.executeMethod(method);
			result = new String(method.getResponseBody(), codePage);
		} catch (final Exception e) {
			throw e;
		} finally {
			method.releaseConnection();
		}
		return result;
	}

	/**
	 * 组装http请求参数
	 * 
	 * @param
	 * @param
	 * @return
	 */
	private static NameValuePair[] assembleRequestParams(Map<String, String> data) {
		final List<NameValuePair> nameValueList = new ArrayList<NameValuePair>();

		Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			nameValueList.add(new NameValuePair((String) entry.getKey(), (String) entry.getValue()));
		}

		return nameValueList.toArray(new NameValuePair[nameValueList.size()]);
	}
}