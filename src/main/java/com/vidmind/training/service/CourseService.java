package com.vidmind.training.service;

import com.vidmind.training.entities.Course;
import org.bson.types.ObjectId;

/**
 * Created by barcelona on 8/17/14.
 */
public interface CourseService {

    public void createNewCourse(Course course);
    public Course getExistingCourse(ObjectId courseId);
    public Course addDependedCourse(ObjectId courseId, ObjectId dependedCourseId);
    public void deleteCourse(ObjectId courseId);
}
