package com.jack.gumballs.domain.request.base;

public class HttpRequest {
	private String data;
	private String token;
	private String from_device;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getUkey() {
		return token;
	}
	public void setUkey(String token) {
		this.token = token;
	}
	public String getFrom_device() {
		return from_device;
	}
	public void setFrom_device(String from_device) {
		this.from_device = from_device;
	}
	
}
