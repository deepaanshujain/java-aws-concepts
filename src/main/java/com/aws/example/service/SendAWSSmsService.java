package com.aws.example.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;    

@Service
public class SendAWSSmsService {

	public Map<String, String> sendSMS(String mobileNo, String msg, String region, String sender, String type, String accessKeyId, 
			String secretAccessKey) {
		Map<String, String> response;
		JSONObject credentials=new JSONObject();  

		credentials.put("MSG", msg);
		credentials.put("MOBILE_NO", mobileNo);
		credentials.put("AWS_REGION_VALUE", region);
		credentials.put("AWS_SENDER_ID", sender);
		
		if(type.equals("IAM_ROLE")) {
			response = sendAwsSmsWithIAMRole(credentials);
		} else {
			credentials.put("AWS_ACCESS_KEY_ID", accessKeyId);
			credentials.put("AWS_SECRET_ACCESS_KEY", secretAccessKey);
			
			response = sendAwsSmsWithCredentials(credentials);
		}
		
		return response;
	}

	public static Map<String, String> sendAwsSmsWithIAMRole(JSONObject credentials) {
		PublishResult result = null;
		Map<String, String> response = new HashMap<>();
		try {
			AmazonSNS sns = AmazonSNSClientBuilder.standard().withRegion(credentials.getString("AWS_REGION_VALUE")).build();

			String message = credentials.getString("MSG");
			String phoneNumber = credentials.getString("MOBILE_NO");
			Map<String, MessageAttributeValue> smsAttributes = new HashMap<String, MessageAttributeValue>();
			
			smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue().withStringValue("Transactional")
					.withDataType("String"));
			smsAttributes.put("AWS.SNS.SMS.SenderID",
					new MessageAttributeValue().withStringValue(credentials.getString("AWS_SENDER_ID")) 
							.withDataType("String"));

			result = sns.publish(new PublishRequest().withMessage(message).withPhoneNumber(phoneNumber)
					.withMessageAttributes(smsAttributes));
			System.out.println(result);
			
			response.put("message", "SMS Sent");
			response.put("data", result.toString());
			

		} catch (Exception e) {
			response.put("message", "SMS Not Sent");
			response.put("data", e.toString());
		}
		
		return response;
	}
	
	public static Map<String, String>  sendAwsSmsWithCredentials(JSONObject credentials) {
		String result = null;
		Map<String, String> response = new HashMap<>();
		try {
			AmazonSNSClient amazonSNSClient = (AmazonSNSClient) AwsClientProvider.provideAWSClient("SNS",
					credentials.getString("AWS_ACCESS_KEY_ID"), credentials.getString("AWS_SECRET_ACCESS_KEY"),
					credentials.getString("AWS_REGION_VALUE"));
			Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
			// set SMS attributes
			smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue().withStringValue("Transactional")
					.withDataType("String"));
			smsAttributes.put("AWS.SNS.SMS.SenderID",
					new MessageAttributeValue().withStringValue(credentials.getString("AWS_SENDER_ID"))
							.withDataType("String"));

			result = amazonSNSClient
					.publish(new PublishRequest().withMessage(credentials.getString("MSG"))
							.withPhoneNumber(credentials.getString("MOBILE_NO")).withMessageAttributes(smsAttributes))
					.toString();
			
			response.put("message", "SMS Sent");
			response.put("data", result.toString());

		} catch (Exception e) {
			response.put("message", "SMS Not Sent");
			response.put("data", e.toString());
		} 
		return response;
	}

}


