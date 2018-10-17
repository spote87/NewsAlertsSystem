package com.alert.news;

import com.alert.news.repository.CassandraUtils;
import org.apache.thrift.transport.TTransportException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
abstract public class TestConfiguration {

    @BeforeClass
    public static void startCassandraServer() throws InterruptedException, IOException, TTransportException {
        CassandraUtils.startEmbeddedCassandraServer();
    }

    @AfterClass
    public static void stopCassandraServer() {
        CassandraUtils.stopEmbeddedCassandraServer();
    }
}
