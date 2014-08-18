package com.vidmind.training.service;

import com.vidmind.training.dao.StudentQueries;
import com.vidmind.training.entities.Student;
import com.vidmind.training.exception.CouldNotUpdateException;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Achia.Rifman on 10/08/2014.
 */
@Service
public class StudentServiceImpl implements StudentService{


    @Autowired
    StudentQueries studentQueries;

    @Override
    public void createNewStudent(Student student) {

        studentQueries.saveNewStudent(student);
    }

    @Override
    public void updateExistingStudent(Student student) {

    }

    @Override
    public void deleteExistingStudent(ObjectId assetId) {

        //add a check if could not delete student
        studentQueries.removeStudent(assetId);
    }

    @Override
    public Student getStudentDetails(ObjectId objectId) {

        //add a check if student not found
        return studentQueries.getStudent(objectId);
    }

    @Override
    public Student addCourseToStudent(ObjectId studentId, ObjectId courseId) {

        if(studentQueries.addCourseToStudent(studentId,courseId)){
            return getStudentDetails(studentId);
        }
        throw new CouldNotUpdateException("Could not add the course " + courseId + " of student " + studentId);

    }

    @Override
    public Student removeCourseFromStudent(ObjectId studentId, ObjectId courseId) {

        if(studentQueries.removeCourseFromStudent(studentId,courseId)){
            return getStudentDetails(studentId);
        }
        throw new CouldNotUpdateException("Could not remove the course " + courseId + " from student " + studentId);
    }


}
