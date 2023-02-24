package com.container.loading.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
@ApplicationScoped
public class MongoDBClientConfig {
    @Inject
    MongoClient mongoClient;


    public MongoCollection getConatiners() {
        return mongoClient.getDatabase("CoderClans").getCollection("containers");
    }
}
