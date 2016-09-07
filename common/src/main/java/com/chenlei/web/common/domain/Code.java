package com.chenlei.web.common.domain;

public enum Code {
	
	SUCCESS(200),SERVICE(500),CLIENT(400);
	
	private int code;
	
	Code(int code){
		this.code = code;
	}

	public int getCode() {
		return code;
	}	
	
}
