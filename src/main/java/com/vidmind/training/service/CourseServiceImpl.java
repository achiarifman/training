package com.vidmind.training.service;

import com.vidmind.training.dao.CourseQueries;
import com.vidmind.training.dao.StudentQueries;
import com.vidmind.training.entities.Course;
import com.vidmind.training.exception.CouldNotUpdateException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by barcelona on 8/17/14.
 */
@Service
public class CourseServiceImpl implements CourseService{



    @Autowired
    CourseQueries courseQueries;

    @Autowired
    StudentQueries studentQueries;

    @Override
    public void createNewCourse(Course course) {

        courseQueries.saveNewCourse(course);
    }

    @Override
    public Course getExistingCourse(ObjectId courseId) {

        return courseQueries.getExistingCourse(courseId);
    }

    @Override
    public Course addDependedCourse(ObjectId courseId, ObjectId dependedCourseId) {

        Course dependedCourse = courseQueries.getExistingCourse(dependedCourseId);
        if(courseQueries.addDepndedCourse(courseId,dependedCourseId)){
            return getExistingCourse(courseId);
        }
        throw new CouldNotUpdateException("Could not add depended course " + dependedCourseId + " to course " + courseId);
    }

    @Override
    public void deleteCourse(ObjectId courseId) {

        courseQueries.deleteCourse(courseId);
        studentQueries.removeCourseFromAllStudents(courseId);
    }
}
