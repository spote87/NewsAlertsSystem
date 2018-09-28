CREATE KEYSPACE IF NOT EXISTS NewsAlerts WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor' : 3};

CREATE TABLE IF NOT EXISTS NewsAlerts.user (
	user_name text,
	mobile_number bigint PRIMARY KEY,
	subscription_categories text
) WITH comment = 'User registrations';

CREATE TABLE IF NOT EXISTS NewsAlerts.Organisation_Campaign (
	id bigint primary key,
	organisation_name text,
	campaign_title text,
	campaign_description text,
	tagged_categories text
) WITH comment = 'Organization campaigns';