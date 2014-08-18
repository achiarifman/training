package com.vidmind.training.dao;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.net.UnknownHostException;

/**
 * Created by Achia.Rifman on 10/08/2014.
 */
@Configuration
public class MongoBootStrap {


    Logger LOGGER = LoggerFactory.getLogger(MongoBootStrap.class);

    @Value("${mongo.host.url}")
    String mongoHost;

    @Value("${mongo.db.name}")
    String mongoDB;

    public final static String ENTITIES_PACKAGE = "com.vidmind.training.entities";

    @Bean(name = "mongoClient")
    public MongoClient mongoClient(){

        try {
            return new MongoClient(mongoHost);
        } catch (UnknownHostException e) {
            LOGGER.error("Could not initialize mongoDB " + e.getStackTrace());
        }
        return null;
    }

    @Bean(name = "datastore")
    public Datastore datastore(){
        Morphia morphia = new Morphia();
        Datastore datastore = morphia.createDatastore(mongoClient(),mongoDB);
        morphia.mapPackage(ENTITIES_PACKAGE);
        return datastore;
    }


    @Bean(name = "db")
    public DB db(){
        return mongoClient().getDB(mongoDB);
    }





}
