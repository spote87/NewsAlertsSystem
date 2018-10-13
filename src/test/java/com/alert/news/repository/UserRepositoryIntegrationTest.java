package com.alert.news.repository;

import com.alert.news.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.CassandraInvalidQueryException;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author Shivaji Pote
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryIntegrationTest {

    private static final String USER_TABLE = "user";

    @Autowired
    private UserRepository userRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void startCassandraEmbedded() throws TTransportException, IOException, InterruptedException, ConfigurationException {
        CassandraUtils.startEmbeddedCassandraServer();
    }

    @AfterClass
    public static void stopCassandraEmbedded() {
        CassandraUtils.stopEmbeddedCassandraServer();
    }

    @Before
    public void createTables() {
        CassandraUtils.executeQuery(getCreateTableQuery());
    }

    private String getCreateTableQuery() {
        final StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE IF NOT EXISTS NewsAlerts.").append(USER_TABLE).append(" (user_name text,mobile_number bigint PRIMARY KEY,subscription_categories list<text>) WITH comment = 'User registrations';");
        return query.toString();
    }

    @After
    public void dropTable() {
        CassandraUtils.dropTable(USER_TABLE);
    }

    @Test
    public void testInsertUserIntoUserTableWhenUserIsValid() {
        final User user = new User(9310942802L, "shivaji", Arrays.asList("Finance", "LIC"));
        userRepository.insert(user);

        assertNotNull(userRepository.findById(9310942802L));
    }

    @Test
    public void testInsertUserIntoUserTableWhenMobileNoIsNotProvidedShouldThrowException()
            throws CassandraInvalidQueryException {
        final User user = new User();
        user.setSubscriptionCategories(Arrays.asList("Finance", "LIC"));
        user.setUserName("Shivaji");
        exception.expect(CassandraInvalidQueryException.class);
        exception.expectMessage("SessionCallback; CQL [INSERT INTO user (subscription_categories,user_name) VALUES (['Finance','LIC'],'Shivaji');]; Some partition key parts are missing: mobile_number; nested exception is com.datastax.driver.core.exceptions.InvalidQueryException: Some partition key parts are missing: mobile_number");
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
        user.setSubscriptionCategories(Arrays.asList("Finance", "LIC"));
        userRepository.insert(user);

        assertNotNull(userRepository.findById(9125463455L));
    }

}
