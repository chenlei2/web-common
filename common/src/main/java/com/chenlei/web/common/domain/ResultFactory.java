package com.chenlei.web.common.domain;

public class ResultFactory {
	
	public static <T> Result<T> createSuccessResult(T t){
		Result<T> result = new Result<T>(Code.SUCCESS, "成功", Boolean.TRUE);
		result.setBody(t);
		return result;
	}
	
	public static <T> Result<T> createFailOfServiceResult(T t){
		Result<T> result = new Result<T>(Code.SERVICE, "服务器异常", Boolean.FALSE);
		result.setBody(t);
		return result;
	}
	
	public static <T> Result<T> createFailOfClientResult(T t){
		Result<T> result = new Result<T>(Code.CLIENT, "请求参数异常", Boolean.FALSE);
		result.setBody(t);
		return result;
	}

}
