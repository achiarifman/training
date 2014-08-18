package com.vidmind.training.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by barcelona on 8/14/14.
 */
@Component
public class BaseQueries {

    @Autowired
    DB db;

    @Autowired
    Datastore datastore;

    DBCollection dbCollection;

    public void setCollectionByName(String collectionName){

        dbCollection = db.getCollection(collectionName);
    }

}
