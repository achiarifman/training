package com.vidmind.training.entities;

import com.vidmind.training.commons.CollectionConst;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Set;

/**
 * Created by Achia.Rifman on 06/08/2014.
 */
@Entity(CollectionConst.COURSES)
public class Course {

    @Id
    private ObjectId id;
    private String courseName;
    private int points;

    private Set<ObjectId> dependedCourses;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Set<ObjectId> getDependedCourses() {
        return dependedCourses;
    }

    public void setDependedCourses(Set<ObjectId> dependedCourses) {
        this.dependedCourses = dependedCourses;
    }
}
