package com.alert.news.repository;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;

import java.io.IOException;

@Slf4j
class CassandraUtils {

    private CassandraUtils() {
        throw new IllegalStateException("Cannot instantiate this class");
    }

    private static final String KEYSPACE_CREATION_QUERY = "CREATE KEYSPACE IF NOT EXISTS NewsAlerts WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor' : 3};";

    private static final String KEYSPACE_ACTIVATE_QUERY = "USE NewsAlerts;";

    private static Session session;

    static void startEmbeddedCassandraServer() throws InterruptedException, IOException, TTransportException, ConfigurationException {
        EmbeddedCassandraServerHelper.startEmbeddedCassandra();
        final Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").withPort(9142).withProtocolVersion(ProtocolVersion.V3).build();
        session = cluster.connect();
        log.info("Cassandra server started at 127.0.0.1:9042..");
        session.execute(KEYSPACE_CREATION_QUERY);
        session.execute(KEYSPACE_ACTIVATE_QUERY);
        log.info("Keyspace {} created and activated", "NewsAlerts");
        Thread.sleep(5000);
    }


    static void stopEmbeddedCassandraServer() {
        EmbeddedCassandraServerHelper.getSession();
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    }

    public static void executeQuery(final String query) {
       session.execute(query);
    }

    public static void dropTable(final String tableName) {
        final StringBuilder query = new StringBuilder();
        query.append("DROP TABLE NewsAlerts.").append(tableName);
        session.execute(query.toString());
    }
}
