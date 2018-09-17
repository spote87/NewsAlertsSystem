CREATE KEYSPACE IF NOT EXISTS NewsAlerts WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor' : 3};

CREATE TABLE IF NOT EXISTS NewsAlerts.user (
	user_name text,
	mobile_number bigint PRIMARY KEY,
	subscription_categories text
) WITH comment = 'User registrations';