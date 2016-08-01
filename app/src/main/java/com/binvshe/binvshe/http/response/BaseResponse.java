package com.binvshe.binvshe.http.response;


/**
 * 基础返回信息类
 * @author WeiQi
 * @email 57890940@qq.com
 * @date 2014-9-23
 */
public class BaseResponse {
	
	/** 结果状态码 **/
	private Integer code;
	/** 错误提示信息 **/
	private String message;

	public static final int CODE_SUCCESS = 0;
	

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

}
