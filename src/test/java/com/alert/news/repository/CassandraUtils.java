package com.alert.news.repository;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import lombok.extern.slf4j.Slf4j;
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

    static void startEmbeddedCassandraServer() throws InterruptedException, IOException, TTransportException {
        EmbeddedCassandraServerHelper.startEmbeddedCassandra();
        final Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").withPort(9142).build();
        log.info("Cassandra server started at 127.0.0.1:9142..");
        final Session session = cluster.connect();
        session.execute(KEYSPACE_CREATION_QUERY);
        session.execute(KEYSPACE_ACTIVATE_QUERY);
        log.info("Keyspace {} created and activated", "NewsAlerts");
    }

}
