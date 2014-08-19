package com.vidmind.training;

import org.testng.annotations.Factory;

/**
 * Created by barcelona on 8/19/14.
 */
public class TestFactory {


    @Factory
    public Object[] createInstances() {
        Object[] result = {new CreateCoursesTest(), new CreateStudentsTest()};
        return result;
    }

}
