package com.vidmind.training;

import com.vidmind.training.listener.TestListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;

/**
 * Created by Achia.Rifman on 07/08/2014.
 */
//@Listeners({TestListener.class})
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BaseTest extends AbstractTestNGSpringContextTests {


}
