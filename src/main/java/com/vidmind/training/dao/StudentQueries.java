package com.vidmind.training.dao;

import com.vidmind.training.commons.CollectionConst;
import com.vidmind.training.dao.commons.MongoConst;
import com.vidmind.training.entities.Student;
import org.bson.types.ObjectId;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Achia.Rifman on 10/08/2014.
 */
@Component
public class StudentQueries extends BaseQueries{


    public final static String COURSE_SET = "courseSet";


    @PostConstruct
         public void inital(){
        setCollectionByName(CollectionConst.STUDENTS);
    }

    public void saveNewStudent(Student student){
        datastore.save(student);
    }

    public void removeStudent(ObjectId objectId){

        //datastore.delete(Student.class, objectId);
        datastore.findAndDelete(datastore.createQuery(Student.class).field(Mapper.ID_KEY).equal(objectId));
    }

    public boolean addCourseToStudent(ObjectId studentId, ObjectId courseId){

        UpdateOperations<Student> ops = datastore.createUpdateOperations(Student.class).add(COURSE_SET,courseId);
        UpdateResults updateResults = datastore.update(queryFindStudentById(studentId), ops);
        return updateResults.getUpdatedExisting();
    }

    public boolean removeCourseFromStudent(ObjectId studentId, ObjectId courseId){

        UpdateOperations<Student> ops = datastore.createUpdateOperations(Student.class).removeAll(COURSE_SET, courseId);
        UpdateResults updateResults = datastore.update(queryFindStudentById(studentId), ops);
        return updateResults.getUpdatedExisting();
    }

    public Student getStudent(ObjectId objectId){

        return datastore.get(Student.class, objectId);
    }

    public Query<Student> queryFindStudentById(ObjectId objectId){

        return datastore.createQuery(Student.class).field(Mapper.ID_KEY).equal(objectId);
    }

/*    public Query<Student> queryFindStudentByContainedCourse(ObjectId objectId){

        List<Student> list = datastore.createQuery(Student.class).field(COURSE_SET).hasThisElement(objectId).asList();
        return null;
    }*/

    public void removeCourseFromAllStudents(ObjectId courseId){

        UpdateOperations<Student> ops = datastore.createUpdateOperations(Student.class).removeAll(COURSE_SET, courseId);

        List<Student> studentsToModify = datastore.createQuery(Student.class).field(COURSE_SET).hasThisOne(courseId).asList();
        for(Student student : studentsToModify){
            datastore.update(queryFindStudentById(student.getObjectId()),ops);
        }

    }
}
