package com.vidmind.training.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.vidmind.training.commons.CollectionConst;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Achia.Rifman on 06/08/2014.
 */
@Entity(CollectionConst.COURSES)
public class Course {

    @Id
    private ObjectId objectId;
    private String courseName;
    private int points;

    private Set<ObjectId> dependedCourses;

    @JsonIgnore
    public ObjectId getobjectId() {
        return objectId;
    }

    @JsonGetter("objectId")
    public String getobjectIdAsString() {
        return objectId.toString();
    }

    public void setobjectId(ObjectId id) {
        this.objectId = id;
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

    @JsonIgnore
    public Set<ObjectId> getDependedCourses() {
        return dependedCourses;
    }

    @JsonGetter("dependedCourses")
    public Set<String> getDependedCoursesAsString() {

        Set<String> courses = new HashSet<String>();
        for(ObjectId courseId : dependedCourses){
            courses.add(courseId.toString());
        }
        return courses;
    }

    @JsonIgnore
    public void setDependedCourses(Set<ObjectId> dependedCourses) {
        this.dependedCourses = dependedCourses;
    }

    @JsonSetter("dependedCourses")
    public void setDependedCourses(List<String> dependedCoursesList) {

        this.dependedCourses = new HashSet<ObjectId>();
        for(String id : dependedCoursesList){
            dependedCourses.add(new ObjectId(id));
        }
    }
}
