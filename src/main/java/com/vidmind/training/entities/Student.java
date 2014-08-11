package com.vidmind.training.entities;

import com.vidmind.training.commons.CollectionConst;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Set;

/**
 * Created by Achia.Rifman on 06/08/2014.
 */
@Entity(CollectionConst.STUDENTS)
public class Student {



    @Id
    private ObjectId objectId;
    private String firstName;
    private String lastName;
    private int age;

    private Set<ObjectId> courseSet;

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<ObjectId> getCourseSet() {
        return courseSet;
    }

    public void setCourseSet(Set<ObjectId> courseSet) {
        this.courseSet = courseSet;
    }
}
