# NewsAlertsSystem
This application sends news alerts to subscribed users. Since this is spring boot application, it can be launched by simply running NewsAlertsSystemApp.java

WIP: Application is not yet fully ready. Only below 2 services are implemented :
1. User subscription which can be tested by calling registration REST service 

	URI: /NewsAlertsSystem/registeruser
	e.g: http://localhost:8080/NewsAlertsSystem/registeruser
	
This is POST request. We need to pass user details in request body. 

	e.g: {
			"mobileNumber" : "9370912801",
			"userName" : "shivaji.pote",
			"subscriptionCategories" : "Finance, Banking"
		}

2. Organization campaign service which can be tested by calling below REST URI
	
	 URI :  /NewsAlertsSystem/registercampain
	 e.g: 	http://localhost:8080/NewsAlertsSystem/registercampain
 
This is POST request. We need to pass campaign details in request body
	
	e.g:
		{
			"orgnisationName" : "ABC org",
			"campaignTitle" : "LIC introduced new policy",
			"campaignDescription" : "This is term plan with sum assured 2cr",
			"taggedCategories" : "policies"
		}

	
Pleas note: before testing above services, user needs to run cassandra DDL queries present in cassandra/ddl.cql