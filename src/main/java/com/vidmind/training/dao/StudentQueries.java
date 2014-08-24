package com.vidmind.training.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.vidmind.training.commons.CollectionConst;
import com.vidmind.training.entities.Course;
import com.vidmind.training.entities.Student;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.MorphiaIterator;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Achia.Rifman on 10/08/2014.
 */
@Component
public class StudentQueries extends BaseDao<Student>{


    public final static String COURSE_SET = "courseSet";

    @Autowired
    public StudentQueries(Datastore ds) {
        super(ds);
    }


    @PostConstruct
         public void inital(){
        setCollectionByName(CollectionConst.STUDENTS);
    }





    public boolean addCourseToStudent(ObjectId studentId, Course course){


        //UpdateOperations<Student> ops = ds.createUpdateOperations(Student.class).add(COURSE_SET,courseId);
        //UpdateResults updateResults = ds.update(queryFindStudentById(studentId), ops);

        UpdateResults updateResults = update(queryFindStudentById(studentId),createUpdateOperations().add(COURSE_SET,course));
        return updateResults.getUpdatedExisting();
    }

    public boolean removeCourseFromStudent(ObjectId studentId, ObjectId courseId){

        //UpdateOperations<Student> ops = ds.createUpdateOperations(Student.class).removeAll(COURSE_SET, courseId);
       // UpdateResults updateResults = ds.update(queryFindStudentById(studentId), ops);

        UpdateResults updateResults = update(queryFindStudentById(studentId), createUpdateOperations().removeAll(COURSE_SET, courseId));
        return updateResults.getUpdatedExisting();
    }

    public Query<Student> queryFindStudentById(ObjectId objectId){

        return createQuery().field(Mapper.ID_KEY).equal(objectId);

    }

/*    public Query<Student> queryFindStudentByContainedCourse(ObjectId objectId){

        List<Student> list = ds.createQuery(Student.class).field(COURSE_SET).hasThisElement(objectId).asList();
        return null;
    }*/

    public void removeCourseFromAllStudents(Course course){

        //UpdateOperations<Student> ops = ds.createUpdateOperations(Student.class).removeAll(COURSE_SET, courseId);

        List<Student> studentsToModify = createQuery().field(COURSE_SET).hasThisOne(course).disableValidation().asList();

        //List<Student> studentsToModify = ds.createQuery(Student.class).field(COURSE_SET).hasThisOne(courseId).asList();


        for(Student student : studentsToModify){
            //ds.update(queryFindStudentById(student.getObjectId()),createUpdateOperations().removeAll(COURSE_SET, courseId));
            update(queryFindStudentById(student.getObjectId()),createUpdateOperations().removeAll(COURSE_SET, course));
        }

    }

    public List<Student> getAllStudents(int limit, int offset){

        return createQuery().limit(limit).offset(offset).asList();
    }

    public void getSpecial(){


        //AggregationPipeline<Student, Student> pipeline = getDs().<Student,Student>createAggregation(Student.class).match(createQuery().filter("courseSet >",2));
        //<Student> studentList=  find(createQuery().where("courseSet").).asList(); //. field("courseSet.length").greaterThanOrEq(2)).asList();// filter("courseSet >",2)).asList();
        //MorphiaIterator<Student,Student> morphiaIterator = pipeline.out(Student.class);

        //getDs().<Student,Student>createAggregation(Student.class).match(createQuery().filter("courseSet >",2))
        DBCursor dbCursor = dbCollection.find(new BasicDBObject("courseSet.3", new BasicDBObject("$exists", Boolean.TRUE)));
        List<Student> studentList = createQuery().disableValidation().field("courseSet.3").exists().field("age").greaterThan(25).asList();

        while(dbCursor.hasNext()){

            System.out.println("||");
        }
    }
}
