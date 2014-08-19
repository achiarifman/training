package com.vidmind.training.service;

import com.vidmind.training.entities.Course;
import com.vidmind.training.entities.Student;

import java.util.List;
import java.util.Map;

/**
 * Created by barcelona on 8/18/14.
 */
public interface ManagmentService {

    public Map<Course,List<Student>> getSpecial();
}
