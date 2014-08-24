package com.vidmind.training.service;

import com.vidmind.training.dao.CourseQueries;
import com.vidmind.training.dao.StudentQueries;
import com.vidmind.training.entities.Course;
import com.vidmind.training.entities.Student;
import com.vidmind.training.exception.CouldNotUpdateException;
import com.vidmind.training.exception.DependedCourseException;
import com.vidmind.training.exception.ExceptionMessage;
import com.vidmind.training.exception.StudentNotFoundException;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Achia.Rifman on 10/08/2014.
 */
@Service
public class StudentServiceImpl implements StudentService{


    @Autowired
    StudentQueries studentQueries;

    @Autowired
    CourseQueries courseQueries;

    @Override
    public void createNewStudent(Student student) {

        if(student.getCourseSet() != null && student.getCourseSet().size() > 0){
            Set<Course> courses = student.getCourseSet();
            Set<Course> persistCourses = new HashSet<>();
            for(Course course : courses){
                persistCourses.add(courseQueries.get(course.getobjectId()));
            }
            student.setCourseSet(persistCourses);
        }
        studentQueries.save(student);
    }

    @Override
    public void updateExistingStudent(Student student) {

    }

    @Override
    public void deleteExistingStudent(ObjectId assetId) {

        //add a check if could not delete student
        studentQueries.deleteById(assetId);
    }

    @Override
    public Student getStudentDetails(ObjectId objectId) {

        //add a check if student not found
        Student student = studentQueries.get(objectId);
        if(student == null){
            throw new StudentNotFoundException("Could not find the student id " + objectId);
        }
        return student;
    }

    @Override
    public Student addCourseToStudent(ObjectId studentId, ObjectId courseId) {

        Course course = courseQueries.get(courseId);
        Set<ObjectId> depndedCourses = course.getDependedCourses();
        if( depndedCourses!= null && depndedCourses.size() > 0){
              if(!isStudentHasDependedCourses(studentId, depndedCourses)){
                  throw new DependedCourseException(depndedCourses.toString());
              }
        }
        if(studentQueries.addCourseToStudent(studentId,courseQueries.get(courseId))){
            return getStudentDetails(studentId);
        }
        throw new CouldNotUpdateException("Could not add the course " + courseId + " of student " + studentId);

    }

    @Override
    public Student removeCourseFromStudent(ObjectId studentId, ObjectId courseId) {

        if(studentQueries.removeCourseFromStudent(studentId, courseId)){
            return getStudentDetails(studentId);
        }
        throw new CouldNotUpdateException("Could not remove the course " + courseId + " from student " + studentId);
    }

    @Override
    public List<Student> getAllStudents(int limit, int offset){
        return studentQueries.getAllStudents(limit,offset);
    }

    public boolean isStudentHasDependedCourses(ObjectId studentId,Set<ObjectId> depndedCourses){

        Student student = getStudentDetails(studentId);
        return student.getCourseSet().contains(depndedCourses);
    }



}
