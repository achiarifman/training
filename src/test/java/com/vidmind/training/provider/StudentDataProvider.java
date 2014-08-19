package com.vidmind.training.provider;

import com.vidmind.training.commons.ProviderConst;
import com.vidmind.training.entities.Student;
import org.kohsuke.randname.RandomNameGenerator;
import org.testng.annotations.DataProvider;

import java.util.Random;

/**
 * Created by barcelona on 8/18/14.
 */

public class StudentDataProvider {


    final static int NUM_OF_STUDENTS = 1;
    private static RandomNameGenerator rnd = new RandomNameGenerator(0);

    final static int MAX_AGE = 80;
    final static int MIN_AGE = 20;


    @DataProvider(name= ProviderConst.STUDENT_PROVIDER)
    public static Object[][] freeMovieDataProvider() {

        return getStudents();

    }

    public static Object[][] getStudents(){


        Object[][] studentsArray = new Object[NUM_OF_STUDENTS][];
        for(int i = 0 ; i < NUM_OF_STUDENTS; i++){
            Object[][] dataArray = {{i,getRandomStudent(i)}};
            studentsArray[i] = dataArray[0];
        }

        return studentsArray;
    }

    public static Student getRandomStudent(int i){

        Student student = new Student();

        String[] randNames = rnd.next().split("_");
        student.setFirstName(randNames[0]);
        student.setLastName(randNames[1] + System.currentTimeMillis());
        Random randomGenerator = new Random();
        student.setAge(randomGenerator.nextInt(MAX_AGE - MIN_AGE) + MIN_AGE);
        return student;
    }


}
