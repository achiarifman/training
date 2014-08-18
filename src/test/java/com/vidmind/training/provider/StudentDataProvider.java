package com.vidmind.training.provider;

import com.vidmind.training.commons.ProviderConst;
import com.vidmind.training.entities.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.testng.annotations.DataProvider;

import java.util.Random;

/**
 * Created by barcelona on 8/18/14.
 */

public class StudentDataProvider {


    static int numOfStudents = 1;

    @DataProvider(name= ProviderConst.STUDENT_PROVIDER)
    public static Object[][] freeMovieDataProvider() {

        return getStudents();

    }

    public static Object[][] getStudents(){


        Object[][] studentsArray = new Object[numOfStudents][];
        for(int i = 0 ; i < numOfStudents ; i++){
            Object[][] dataArray = {{i,getRandomStudent()}};
            studentsArray[i] = dataArray[0];
        }

        return studentsArray;
    }

    public static Student getRandomStudent(){

        Student student = new Student();

        student.setFirstName("Auto");
        student.setLastName("Auto" + System.currentTimeMillis());
        Random randomGenerator = new Random();
        student.setAge(randomGenerator.nextInt(80 - 1));
        return student;
    }


}
