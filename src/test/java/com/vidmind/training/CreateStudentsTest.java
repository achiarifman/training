package com.vidmind.training;

import com.vidmind.training.commons.ProviderConst;
import com.vidmind.training.dao.CourseQueries;
import com.vidmind.training.entities.Course;
import com.vidmind.training.entities.Student;
import com.vidmind.training.provider.StudentDataProvider;
import com.vidmind.training.service.StudentService;
import com.vidmind.training.service.StudentServiceImpl;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.fail;

/**
 * Created by barcelona on 8/18/14.
 */
public class CreateStudentsTest extends BaseTest{

    Logger LOGGER = LoggerFactory.getLogger(CreateStudentsTest.class);

    @Autowired
    StudentService studentService;

    @Autowired
    CourseQueries courseQueries;


    public final static int MAX_COURSES = 5;
    public final static int MIN_COURSES = 1;
    int studentsNum = 0;

    @Test(threadPoolSize = 10, invocationCount = 100,  timeOut = 10000,dataProvider = ProviderConst.STUDENT_PROVIDER, dataProviderClass = StudentDataProvider.class)
    public void testCreatStudents(int id , Student student){


        studentService.createNewStudent(student);
        if(studentService.getStudentDetails(student.getObjectId()) == null)
            fail("Could not create the student " + student.toString());
    }

    @Test( dependsOnGroups = {CreateCoursesTest.CREATE_COURSE_GROUP},threadPoolSize = 10, invocationCount = 100,  timeOut = 10000,dataProvider = ProviderConst.STUDENT_PROVIDER, dataProviderClass = StudentDataProvider.class)
    public void testCreatStudentsWithCourses(int id , Student student){

        Random rnd = new Random();
        int courseNum = rnd.nextInt(MAX_COURSES - MIN_COURSES) + MIN_COURSES;
        Set<ObjectId> studentCourses = new HashSet<>();

        List<Course> courses = courseQueries.getCoursesByOffsetAndLimit(courseNum,getCounter());

        if(courseNum > MAX_COURSES || courses.size() > MAX_COURSES)
            System.out.println("Stop");
        for(Course  course : courses){
            if(course.getDependedCourses() != null){
                studentCourses.addAll(course.getDependedCourses());
            }
            studentCourses.add(course.getobjectId());
        }
        //student.setCourseSet(studentCourses);

        student.setCourseSet(new HashSet<Course>(courses));
        studentService.createNewStudent(student);
        if(studentService.getStudentDetails(student.getObjectId()) == null)
            fail("Could not create the student with courses " + student.toString());

    }

    public synchronized int getCounter(){
         return studentsNum++;
    }

}
