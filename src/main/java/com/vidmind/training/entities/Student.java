package com.vidmind.training.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.vidmind.training.commons.CollectionConst;
import org.bson.types.ObjectId;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


import java.util.*;

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

    @JsonIgnore
    public ObjectId getObjectId() {
        return objectId;
    }

    @JsonGetter("objectId")
    public String getObjectIdAsString(){

        return objectId.toString();
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

    @JsonIgnore
    public Set<ObjectId> getCourseSet() {
        return courseSet;
    }

    @JsonGetter("courseSet")
    public Set<String> getCourseSetAsString() {

        Set<String> courses = new HashSet<String>();
        for(ObjectId courseId : courseSet){
            courses.add(courseId.toString());
        }
        return courses;
    }

    @JsonIgnore
    public void setCourseSet(Set<ObjectId> courseSet) {
        this.courseSet = courseSet;
    }

    @JsonSetter("courseSet")
    public void setCourseSet(List<String> courseArray) {

        this.courseSet = new HashSet<ObjectId>();
        for(String id : courseArray){
            courseSet.add(new ObjectId(id));
        }
    }
}
