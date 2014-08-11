package com.vidmind.training.service;

import com.vidmind.training.dao.StudentQueries;
import com.vidmind.training.entities.Student;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Achia.Rifman on 10/08/2014.
 */
@Service
public class StudentServiceImpl implements StudentService{


    @Autowired
    StudentQueries studentQueries;

    @Override
    public void createNewStudent(Student student) {

        studentQueries.saveNewStudent(student);
    }

    @Override
    public void updateExistingStudent(Student student) {

    }

    @Override
    public void deleteExistingStudent(ObjectId assetId) {

    }
}
