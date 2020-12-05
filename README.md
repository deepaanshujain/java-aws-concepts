Send SMS using AWS SNS service by 2 methods :
- Through IAM Role permissions
- Through Credentials

Links to send SMS :

-	Through IAM Role :
	http://localhost:<PORT>/sendSMS?mobileNo=+91XXXXXXXXXX&message=<Your Message>&type=IAM_ROLE&region=<AWS Region>&sender=<Any String>

-	Through Credentials :
	http://localhost:<PORT>/sendSMS?mobileNo=+91XXXXXXXXXX&message=<Your Message>&type=CREDENTIALS&region=<AWS Region>&sender=<Any String>&accessKeyId=<Your Access Key>&secretAccessKey=<Your Secret Access>

