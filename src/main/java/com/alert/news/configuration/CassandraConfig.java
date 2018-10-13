package com.alert.news.configuration;

import javax.annotation.PostConstruct;

import com.datastax.driver.core.ProtocolVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/**
 * @author Shivaji Pote
 */
@Configuration
@EnableCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration {

    /**
     * Constant String for Keyspace
     */
    private static final String KEYSPACE = "cassandra.keyspace";
    /**
     * Constant String for ContactPoints
     */
    private static final String CONTACT_POINTS = "cassandra.contactpoints";
    /**
     * Constant String for Port
     */
    private static final String PORT_NO = "cassandra.port";

    private static final String CASSANDRA_BASE_PACKAGES = "cassandra.basePackages";

    @Autowired
    private Environment environment;

    private String contactPoints;

    private int port;

    private String keySpaceName;

    private String basePackages;

    @PostConstruct
    public void intialiseProperties() {
        contactPoints = environment.getProperty(CONTACT_POINTS);
        port = Integer.parseInt(environment.getProperty(PORT_NO));
        keySpaceName = environment.getProperty(KEYSPACE);
        basePackages = environment.getProperty(CASSANDRA_BASE_PACKAGES);
    }

    /**
     * @return the contactPoints
     */
    @Override
    protected String getContactPoints() {
        return contactPoints;
    }

    /**
     * @return the port
     */
    @Override
    protected int getPort() {
        return port;
    }

    @Override
    protected String getKeyspaceName() {
        return keySpaceName;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{basePackages};
    }


    @Override
    protected ProtocolVersion getProtocolVersion() {
        return ProtocolVersion.V3;
    }

}
