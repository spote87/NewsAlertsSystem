# NewsAlertsSystem
This application sends news alerts to subscribed users. Since this is spring boot application, it can be launched by simply running NewsAlertsSystemApp.java

WIP: Application is not yet fully ready. Only user subscription part is ready which can be tested by calling registration REST service 

	URI: /NewsAlertsSystem/register
	e.g: http://localhost:8080/NewsAlertsSystem/register
	
This is POST request. We need to pass user details in request body. 

	e.g: {
			"mobileNumber" : "9370912801",
			"userName" : "shivaji.pote",
			"subscriptionCategories" : "Finance, Banking"
		}
Pleas note: before testing above service, user needs to run cassandra DDL queries present in cassandra/ddl.cql