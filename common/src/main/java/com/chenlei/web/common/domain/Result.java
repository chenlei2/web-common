package com.chenlei.web.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author chenlei
 *
 * @param <T>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Result<T> {
	
	
	Result(Code code, String description, boolean isSuccess) {
		this.code = code;
		this.description = description;
		this.isSuccess = isSuccess;
	}
	/**
     * 结果码
     */
    @ApiModelProperty(value = "返回结果code",required = true)
    private Code code;

    /**
     * 描述
     */
    @ApiModelProperty(value = "错误描述")
    private String description;

    /**
     * 成功标志
     */
    @ApiModelProperty(value = "该次操作是否是成功，true为成功",required = true)
    private boolean isSuccess = true;
    /**
     * 具体数据
     */
    @ApiModelProperty(value = "返回结果的具体数据")
    private T body;
	public Integer getCode() {
		return code.getCode();
	}
	public String getDescription() {
		return description;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
    

}
