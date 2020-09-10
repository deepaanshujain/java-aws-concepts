package com.aws.example.service;

import com.amazonaws.AmazonWebServiceClient;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;

public class AwsClientProvider {
	@SuppressWarnings("deprecation")
	public static AmazonWebServiceClient provideAWSClient(String awsService,String awsAccessKeyId,String awsSecretAccessKey,String regionValue) throws Exception {
		AWSCredentials cred = null;

		AmazonWebServiceClient client = null;
		try {

			final Region region = Region.getRegion(Regions.fromName(regionValue));
			cred = new AWSCredentials() {
				@Override
				public String getAWSAccessKeyId() {
					return awsAccessKeyId;
				}

				@Override
				public String getAWSSecretKey() {
					return awsSecretAccessKey;
				}
			};

			if (awsService.equals("SNS") && region.isServiceSupported(AmazonSNS.ENDPOINT_PREFIX)) {
				client = new AmazonSNSClient(cred);
				client.setRegion(region);
			} else {
				client = new AmazonSNSClient(cred);
				client.setRegion(Region.getRegion(Regions.DEFAULT_REGION));
			}
		} catch (Exception e) {
			throw new Exception("Unknown Error Occurred, Please Try Again.");

		}
		return client;
	}
}
