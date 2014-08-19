package com.vidmind.training;

import com.vidmind.training.commons.ProviderConst;
import com.vidmind.training.entities.Course;
import com.vidmind.training.provider.CourseDataProvider;
import com.vidmind.training.provider.StudentDataProvider;
import com.vidmind.training.service.CourseService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.fail;

/**
 * Created by barcelona on 8/18/14.
 */
public class CreateCoursesTest extends BaseTest{


    @Autowired
    CourseService courseService;

    public final static String CREATE_COURSE_GROUP = "createCourse";

    int idCounter = 0;

    @Test(groups = {CREATE_COURSE_GROUP}, threadPoolSize = 10, invocationCount = 100,  timeOut = 10000,dataProvider = ProviderConst.COURSE_PROVIDER, dataProviderClass = CourseDataProvider.class)
    public void testCreateCourse(int id, Course course){

        courseService.createNewCourse(course);
        if(courseService.getExistingCourse(course.getobjectId()) == null)
            fail("Could no create the course " + course.toString());

    }

    @Test(dependsOnGroups = CREATE_COURSE_GROUP,threadPoolSize = 10, invocationCount = 100,  timeOut = 10000,dataProvider = ProviderConst.COURSE_PROVIDER, dataProviderClass = CourseDataProvider.class)
    public void testCreateCourseWithDependedCourse(int id, Course course){

        List<Course> dependedCourses = courseService.getCourses(1,idCounter++);
        if(dependedCourses != null && dependedCourses.size() > 0){
            Set<ObjectId> dependedIds = new HashSet<ObjectId>();
            for(Course depCourse : dependedCourses){
                dependedIds.add(depCourse.getobjectId());
            }
            course.setDependedCourses(dependedIds);
            courseService.createNewCourse(course);
            if(courseService.getExistingCourse(course.getobjectId()) == null)
                fail("Could no create the course " + course.toString());
        }
        else {
            fail("Could not find a depended course to create the course " + course.toString());
        }
    }


}
