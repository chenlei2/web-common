package com.chenlei.web.common.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenlei.web.common.domain.Result;
import com.chenlei.web.common.domain.ResultFactory;
import com.chenlei.web.common.exception.ServiceException;

/**
 * 统一异常处理器
 * 
 * @author chenlei
 * 
 */
@ControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler(value={ServiceException.class, RuntimeException.class})
	public @ResponseBody <T> Result<String> resolveException(Exception ex) {
 
		if (IllegalArgumentException.class.isAssignableFrom(ex.getClass())) {
			return  ResultFactory.createFailOfClientResult(ex.getMessage());
		} else  {
			return  ResultFactory.createFailOfServiceResult(ex.getMessage());
		} 
	}

	
}
