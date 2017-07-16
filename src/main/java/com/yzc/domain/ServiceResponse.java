package com.yzc.domain;

/**
 * 服务端向浏览器发送的消息类
 * 
 * @author yzc
 * @date 2017年5月5日 上午10:07:24
 * @version 1.0
 */
public class ServiceResponse {

	private String responseMessage;

	public ServiceResponse(String responseMessage) {

		this.responseMessage = responseMessage;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}
