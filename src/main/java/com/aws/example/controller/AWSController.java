package com.aws.example.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aws.example.model.ServiceRespVO;
import com.aws.example.service.SendAWSSmsService;


@RestController
public class AWSController {
	
	@Autowired
	SendAWSSmsService sendAWSSmsService;
	
	
	@GetMapping("/sendSMS")
	public ServiceRespVO doPayment(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(required = false, value = "mobileNo") String mobileNo, 
			@RequestParam(required = false, value = "message") String message, @RequestParam(required = false, value = "region") String region, 
			@RequestParam(required = false, value = "sender") String sender, @RequestParam(required = false, value = "type") String type) {
		Map<String, String> responseSMS = sendAWSSmsService.sendSMS(mobileNo, message, region, sender, type);
		ServiceRespVO serviceResponse = new ServiceRespVO();
		serviceResponse.setResponseData(responseSMS);
		serviceResponse.setResponseCode(0);
		serviceResponse.setResponseMessage("Message Sent");
		return serviceResponse;
	}
		
}