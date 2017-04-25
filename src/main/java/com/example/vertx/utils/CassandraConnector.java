package com.example.vertx.utils;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

/**
 *
 * Reference :: https://docs.datastax.com/en/developer/java-driver/3.2/manual/
 */
public class CassandraConnector {

    private Cluster cluster;

    private Session session;

    public CassandraConnector(){
        this.cluster = Cluster.builder()
                .addContactPoint("localhost")
                .withClusterName("test")
                .withCredentials("cassandra", "cassandra")
                .withPort(9042)
                .build();

        this.session = this.cluster.connect("pushkar");
    }

    public Session getSession() throws Exception {
        return this.session;
    }

    public void closeSession() throws Exception {

        if(!this.session.isClosed()) {
            this.session.close();
        }

        if(!this.cluster.isClosed()) {
            cluster.close();
        }
    }
}
