package com.vidmind.training.dao;

import com.vidmind.training.commons.CollectionConst;
import com.vidmind.training.entities.Course;
import com.vidmind.training.entities.Student;
import org.bson.types.ObjectId;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by barcelona on 8/17/14.
 */
@Component
public class CourseQueries extends BaseQueries{

    public final static String DEPENDED_COURSES = "dependedCourses";


    @PostConstruct
    public void inital(){
        setCollectionByName(CollectionConst.COURSES);
    }

    public void saveNewCourse(Course course){

        datastore.save(course);
    }

    public Course getExistingCourse(ObjectId courseId){

        return datastore.get(Course.class, courseId);
    }

    public boolean addDepndedCourse(ObjectId courseId, ObjectId depndedCourseId){

        UpdateOperations<Course> ops = datastore.createUpdateOperations(Course.class).add(DEPENDED_COURSES,courseId);
        UpdateResults updateResults = datastore.update(queryFindCourseById(courseId), ops);
        return updateResults.getUpdatedExisting();
    }

    public void deleteCourse(ObjectId courseId){

        datastore.findAndDelete(queryFindCourseById(courseId));
    }

    public List<Course> getCoursesByOffsetAndLimit(int limit, int offset){

        return datastore.find(Course.class).limit(limit).offset(offset).asList();
    }

    public Query<Course> queryFindCourseById(ObjectId objectId){

        return datastore.createQuery(Course.class).field(Mapper.ID_KEY).equal(objectId);
    }

}
