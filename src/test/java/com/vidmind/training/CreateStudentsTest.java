package com.vidmind.training;

import com.vidmind.training.commons.ProviderConst;
import com.vidmind.training.entities.Student;
import com.vidmind.training.provider.StudentDataProvider;
import com.vidmind.training.service.StudentService;
import com.vidmind.training.service.StudentServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * Created by barcelona on 8/18/14.
 */
public class CreateStudentsTest extends BaseTest{

    Logger LOGGER = LoggerFactory.getLogger(CreateStudentsTest.class);

    @Autowired
    StudentServiceImpl studentServiceImpl;


    @Test(threadPoolSize = 10, invocationCount = 100,  timeOut = 10000,dataProvider = ProviderConst.STUDENT_PROVIDER, dataProviderClass = StudentDataProvider.class)
    public void testCreatStudents(int id , Student student){


        studentServiceImpl.createNewStudent(student);
    }

}
