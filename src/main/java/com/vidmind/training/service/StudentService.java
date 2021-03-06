package com.vidmind.training.service;

import com.vidmind.training.entities.Student;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by Achia.Rifman on 07/08/2014.
 */
public interface StudentService {

    public void createNewStudent(Student student);
    public void updateExistingStudent(Student student);
    public void deleteExistingStudent(ObjectId assetId);
    public Student getStudentDetails(ObjectId objectId);
    public Student addCourseToStudent(ObjectId studentId, ObjectId courseId);
    public Student removeCourseFromStudent(ObjectId studentId, ObjectId courseId);
    public List<Student> getAllStudents(int limit, int offset);
}
