package com.vidmind.training.dao;

import com.mongodb.*;
import com.vidmind.training.commons.CollectionConst;
import com.vidmind.training.entities.Course;
import com.vidmind.training.entities.Student;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by barcelona on 8/14/14.
 */
@Component
public class BaseQueries {

    @Autowired
    DB db;

    @Autowired
    Datastore ds;

    DBCollection dbCollection;

    public void setCollectionByName(String collectionName){

        dbCollection = db.getCollection(collectionName);
    }

    public List<DBObject> special(){
        //List<Student> studentList = ds.createQuery(Student.class).disableValidation().field("courseSet.3").exists().field("age").greaterThan(25).asList();
        //AggregationPipeline<Course,Object> aggregationPipelineMatchCourse = ds.createAggregation(Course.class).match(ds.createQuery(Course.class).field("points").greaterThanOrEq(3));

        //AggregationPipeline<Student,Object> aggregationPipelineMatchStudent = ds.createAggregation(Student.class).match(ds.createQuery(Student.class).disableValidation().field("courseSet.3").exists().field("age").greaterThan(25));

        setCollectionByName(CollectionConst.STUDENTS);

        DBObject matchOne =  new BasicDBObject("$match" ,new BasicDBObject("age", new BasicDBObject("$gt",25)));//.append("courseSet.3", new BasicDBObject("$exists", Boolean.TRUE));
        DBObject matchTwo = new BasicDBObject("$match",new BasicDBObject("courseSet.3", new BasicDBObject("$exists", Boolean.TRUE)));
        DBObject matchThree = new BasicDBObject("$match",new BasicDBObject("courseSet", new BasicDBObject("$elemMatch", new BasicDBObject("points", new BasicDBObject("$gte" , 3)))));
        DBObject unwined = new BasicDBObject("$unwind","$courseSet");
        DBObject fields = new BasicDBObject("studentId","$_id");
        fields.put("_id", "$courseSet._id");
        DBObject project = new BasicDBObject("$project",fields);
        DBObject groupFields = new BasicDBObject( "_id", "$_id").append("studentsList",new BasicDBObject("$push" ,"$studentId"));
        DBObject group = new BasicDBObject("$group", groupFields);
        //groupFields.put("points", new BasicDBObject("$gte",3));

        List<DBObject> pipeline = Arrays.asList(matchOne, matchTwo,matchThree,unwined,project,group);
        AggregationOutput output = dbCollection.aggregate(pipeline);
        List<DBObject> aggregarionResults = new ArrayList<DBObject>();
        for (DBObject result : output.results()) {
            System.out.println(result.get("studentsList"));
            aggregarionResults.add(result);
        }
        return aggregarionResults;



    }

}
