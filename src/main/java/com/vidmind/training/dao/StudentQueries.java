package com.vidmind.training.dao;

import com.vidmind.training.entities.Student;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Achia.Rifman on 10/08/2014.
 */
@Component
public class StudentQueries {

    @Autowired
    Datastore datastore;

    public void saveNewStudent(Student student){
        datastore.save(student);
    }
}
