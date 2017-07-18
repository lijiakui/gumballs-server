package com.jack.gumballs.base.constant;

public class ReturnMessage {
	private Integer code;
	private String message;
	
	public ReturnMessage(Integer code,String message){
		this.code=code;
		this.message=message;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
