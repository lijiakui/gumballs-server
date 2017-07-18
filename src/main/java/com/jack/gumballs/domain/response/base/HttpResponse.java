package com.jack.gumballs.domain.response.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Response 基类
 * @Description:
 * @author  Li Jiakui
 * @date 2017年7月6日下午1:47:19
 * @company 湖南快乐淘宝文化传播有限公司(chuan)北京研发中心
 * @version  [Copyright (c) 2014 V001]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class HttpResponse {
	public static final int RESPONSE_CODE_ERROR=403;
	public static final int RESPONSE_CODE_SECCESS=200;
	public static final int RESPONSE_CODE_LOGIN_ERRO=0;
	
	private int code;
	private String message;
	private Map<String,Object> data=new HashMap<String,Object>();
	private String requestId;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public static HttpResponse success(String message){
		HttpResponse httpResponse=new HttpResponse();
		httpResponse.setCode(RESPONSE_CODE_SECCESS);
		httpResponse.setMessage(message);
		httpResponse.setRequestId("");
		return httpResponse;
	}
	
	public static HttpResponse error(String message){
		HttpResponse httpResponse=new HttpResponse();
		httpResponse.setCode(RESPONSE_CODE_ERROR);
		httpResponse.setMessage(message);
		httpResponse.setRequestId("");
		return httpResponse;
	}
	
	public static HttpResponse returnMsg(int code,String message){
		HttpResponse httpResponse=new HttpResponse();
		httpResponse.setCode(code);
		httpResponse.setMessage(message);
		httpResponse.setRequestId("");
		return httpResponse;
	}
	public static HttpResponse returnMsg(Map<String,Object> data,int code,String message){
		HttpResponse httpResponse=new HttpResponse();
		httpResponse.setCode(code);
		httpResponse.setMessage(message);
		httpResponse.setData(data);
		httpResponse.setRequestId("");
		return httpResponse;
	}
	
	public static HttpResponse returnData(Map<String,Object> data,String message){
		HttpResponse httpResponse=new HttpResponse();
		httpResponse.setCode(RESPONSE_CODE_SECCESS);
		httpResponse.setMessage(message);
		httpResponse.setData(data);
		httpResponse.setRequestId("");
		return httpResponse;
	}
}
