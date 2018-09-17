/**
 * 
 */
package com.alert.news.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.CassandraInvalidQueryException;
import org.springframework.test.context.junit4.SpringRunner;

import com.alert.news.model.User;
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
public class UserRepositoryIntegrationTest {

	private static final String KEYSPACE_CREATION_QUERY = "CREATE KEYSPACE IF NOT EXISTS NewsAlerts WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor' : 3};";

	private static final String KEYSPACE_ACTIVATE_QUERY = "USE NewsAlerts;";

	@Autowired
	private UserRepository userRepository;

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
	public void testInsertUserIntoUserTableWhenUserIsValid() {
		final User user = new User(9310942802L, "shivaji", "Finance");
		userRepository.insert(user);

		assertNotNull(userRepository.findById(9310942802L));
	}

	@Test
	public void testInsertUserIntoUserTableWhenMobileNoIsNotProvidedShouldThrowException()
			throws CassandraInvalidQueryException {
		final User user = new User();
		user.setSubscriptionCategories("Finance");
		user.setUserName("Shivaji");
		exception.expect(CassandraInvalidQueryException.class);
		exception.expectMessage("Some partition key parts are missing: mobile_number");
		userRepository.insert(user);

		assertNull(userRepository.findById(9310942803L));
	}

	@Test
	public void testInsertUserIntoUserTableWhenSubscriptionCategoriesIsNotProvidedShouldWork() {
		final User user = new User();
		user.setMobileNumber(9125463454L);
		user.setUserName("Shivaji");
		userRepository.insert(user);

		assertNotNull(userRepository.findById(9125463454L));
	}

	@Test
	public void testInsertUserIntoUserTableWhenUserNameIsNotProvidedShouldWork() {
		final User user = new User();
		user.setMobileNumber(9125463455L);
		user.setSubscriptionCategories("Finance");
		userRepository.insert(user);

		assertNotNull(userRepository.findById(9125463455L));
	}

}
