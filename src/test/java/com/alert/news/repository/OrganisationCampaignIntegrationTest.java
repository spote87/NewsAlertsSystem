/**
 * 
 */
package com.alert.news.repository;

import java.io.IOException;

import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alert.news.model.OrganisationCampaign;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Shivaji Pote
 *
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrganisationCampaignIntegrationTest {
	
	private static final String KEYSPACE_CREATION_QUERY = "CREATE KEYSPACE IF NOT EXISTS NewsAlerts WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor' : 3};";

	private static final String KEYSPACE_ACTIVATE_QUERY = "USE NewsAlerts;";

	@Autowired
	private OrganisationCampaignRepository campaignRepository;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void startCassandraEmbedded() throws TTransportException, IOException, InterruptedException {
		EmbeddedCassandraServerHelper.startEmbeddedCassandra();
		final Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").withPort(9142).build();
		log.info("Cassandra server started at 127.0.0.1:9142..");
		final Session session = cluster.connect();
		session.execute(KEYSPACE_CREATION_QUERY);
		session.execute(KEYSPACE_ACTIVATE_QUERY);
		log.info("Keyspace {} created and activated", "NewsAlerts");
	}

	
	@Test
	public void testInsertCampaignDetailsIntoDatabase() {
		final OrganisationCampaign campaign = new OrganisationCampaign("Test Org", "Test campaign", "Test campaign description", "Finance, Policies");
		campaignRepository.insert(campaign);
		
		Assert.assertNotNull(campaignRepository.findById(Long.valueOf(1)));
	}
}
