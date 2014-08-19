package com.vidmind.training.provider;

import com.vidmind.training.commons.ProviderConst;
import com.vidmind.training.entities.Course;
import org.kohsuke.randname.RandomNameGenerator;
import org.testng.annotations.DataProvider;

import java.util.Random;

/**
 * Created by barcelona on 8/18/14.
 */
public class CourseDataProvider {

    final static int NUM_OF_COURSES = 1;
    private static RandomNameGenerator rnd = new RandomNameGenerator(0);

    final static int MAX_POINTS = 8;
    final static int MIN_POINTS = 1;


    @DataProvider(name= ProviderConst.COURSE_PROVIDER)
    public static Object[][] freeMovieDataProvider() {

        return getCourse();

    }

    private static Object[][] getCourse(){


        Object[][] studentsArray = new Object[NUM_OF_COURSES][];
        for(int i = 0 ; i < NUM_OF_COURSES; i++){
            Object[][] dataArray = {{i,getRandomCourse()}};
            studentsArray[i] = dataArray[0];
        }

        return studentsArray;
    }

    private static Course getRandomCourse(){

        Course course = new Course();

        String[] randNames = rnd.next().split("_");
        course.setCourseName(randNames[0]);
        Random randomGenerator = new Random();
        course.setPoints(randomGenerator.nextInt(MAX_POINTS - MIN_POINTS) + MIN_POINTS);

        return course;
    }
}
