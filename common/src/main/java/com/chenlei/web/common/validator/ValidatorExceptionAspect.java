package com.chenlei.web.common.validator;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.alibaba.fastjson.JSONObject;

/**
 * 表单验证失败抛出异常的切面
 * @author chenlei
 *
 */

@Aspect
@Component 
public class ValidatorExceptionAspect {
		
	@Pointcut("@within(org.springframework.web.bind.annotation.RestController) && args(.., result)")
	public void pointcut (BindingResult result){}
	
	@Before("pointcut(result)")
	public void throwServiceException(JoinPoint jp, BindingResult result){
		if(!result.hasErrors()){
			return;
		}
		List<FieldError> fes = result.getFieldErrors();
		JSONObject jo = new JSONObject();
		for(FieldError fe : fes){
			jo.put(fe.getField(), fe.getDefaultMessage());
		}
		throw new  IllegalArgumentException(jo.toJSONString());
	}
	
}
