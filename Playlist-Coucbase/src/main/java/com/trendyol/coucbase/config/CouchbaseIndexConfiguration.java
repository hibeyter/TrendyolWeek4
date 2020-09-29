package com.trendyol.coucbase.config;

import com.couchbase.client.java.Cluster;
import com.trendyol.coucbase.common.Queries;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class CouchbaseIndexConfiguration {

    private final Cluster couchbaseCluster;

    public CouchbaseIndexConfiguration(Cluster couchbaseCluster) {
        this.couchbaseCluster = couchbaseCluster;
    }

    @Bean
    public void createIndexes() {
        try{
            couchbaseCluster.query(Queries.createIndexPlayListId);
            couchbaseCluster.query(Queries.createIndexUserId);
        }catch (Exception e){
            // TODO: maybe Index already exists
        }
    }
}