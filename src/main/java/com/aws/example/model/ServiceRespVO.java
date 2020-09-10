package com.aws.example.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ServiceRespVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int responseCode;
	private String responseMessage;
	private Object responseData;

	public ServiceRespVO(int responseCode, String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public ServiceRespVO(){}

	public ServiceRespVO(int responseCode, Object responseData){
		this.responseCode = responseCode;
		this.responseData=responseData;
	}

	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public Object getResponseData() {
		return responseData;
	}
	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}

	@Override
	public String toString() {
		return "{ServiceRespVO:{responseCode:" + responseCode + ", responseMessage:" + responseMessage
				+ ", responseData:" + responseData + "}}\n\n";
	}



	

}