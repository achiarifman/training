package com.vidmind.training.service;

import com.vidmind.training.entities.Student;
import org.bson.types.ObjectId;

/**
 * Created by Achia.Rifman on 07/08/2014.
 */
public interface StudentService {

    public void createNewStudent(Student student);
    public void updateExistingStudent(Student student);
    public void deleteExistingStudent(ObjectId assetId);
}
