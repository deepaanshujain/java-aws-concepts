# aws-concepts

Links to send SMS :

-	With IAM Role :
	http://localhost:8081/sendSMS?mobileNo=+91XXXXXXXXXX&message=<Your Message>&type=IAM_ROLE&region=<AWS Region>&sender=<Any String>

-	With Credentials :
	http://localhost:8081/sendSMS?mobileNo=+91XXXXXXXXXX&message=<Your Message>&type=CREDENTIALS&region=<AWS Region>&sender=<Any String>&accessKeyId=<Your Access Key>&secretAccessKey=<Your Secret Access>