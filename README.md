# **NewsAlertsSystem**
This application sends news alerts to subscribed users. Since this is spring boot application, it can be launched by simply running NewsAlertsSystemApp.java

This application uses `Twilio SMS API` to send messages. This is paid API, but we can test application using testing credentials. For more information, please visit https://www.twilio.com/. API documentation can be found at https://www.twilio.com/docs/sms/quickstart/java. 

Set below 2 environment variables:

1. TWILIO_ACCOUNT_SID : your_twilio_account_SID
2. TWILIO_AUTH_TOKEN : your_twilio_auth_token

This application has 3 main processes like below:

1. User subscription:
    User can subscribe to news alerts by calling below REST service.
    
    
	URI: /NewsAlertsSystem/registeruser
	e.g: http://localhost:8080/NewsAlertsSystem/registeruser
	
This is POST request. We need to pass user details in request body. 

	e.g: {
         	"mobileNumber" : "9874563210",
         	"userName" : "shivaji.pote",
         	"subscriptionCategories" : ["Finance", "Banking"]
         }

2. Organization campaign registration:
    Organisations can publish their campaigns by calling below service.
    
	
	 URI :  /NewsAlertsSystem/registercampain
	 e.g: 	http://localhost:8080/NewsAlertsSystem/registercampain
 
This is POST request. We need to pass campaign details in request body
	
	e.g:
		{
        	"orgnisationName" : "Test Org",
        	"campaignTitle" : "Test title",
        	"campaignDescription" : "Test description",
        	"taggedCategories" : ["Tagged", "categories"]
        }

3. Send SMS to registered users:
    Once organisations publish their news, it will be saved in cassandra database and will be sent to registered users. 
    Also there is on scheduled job which runs once a day and sends news alerts to user if there are any news with status "New"
	
**Pleas note:** Before testing above services, user needs to start cassandra DB server and run cassandra DDL queries present in _cassandra/ddl.cql_