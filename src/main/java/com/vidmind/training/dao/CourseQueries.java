package com.vidmind.training.dao;

import com.vidmind.training.commons.CollectionConst;
import com.vidmind.training.entities.Course;
import com.vidmind.training.entities.Student;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.MorphiaIterator;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by barcelona on 8/17/14.
 */
@Component
public class CourseQueries extends BaseDao<Course>{

    public final static String DEPENDED_COURSES = "dependedCourses";

    @Autowired
    public CourseQueries(Datastore ds) {
        super(ds);
    }


    @PostConstruct
    public void inital(){
        setCollectionByName(CollectionConst.COURSES);
    }



    public boolean addDepndedCourse(ObjectId courseId, ObjectId depndedCourseId){

        UpdateOperations<Course> ops = ds.createUpdateOperations(Course.class).add(DEPENDED_COURSES,courseId);
        UpdateResults updateResults = ds.update(queryFindCourseById(courseId), ops);
        return updateResults.getUpdatedExisting();
    }

/*    public void deleteCourse(ObjectId courseId){

        deleteById()
        ds.findAndDelete(queryFindCourseById(courseId));
    }*/

    public List<Course> getCoursesByOffsetAndLimit(int limit, int offset){

        return find(createQuery().limit(limit).offset(offset)).asList();

        //return find(Course.class).limit(limit).offset(offset).asList();
    }

    public Query<Course> queryFindCourseById(ObjectId objectId){

        return createQuery().field(Mapper.ID_KEY).equal(objectId);
    }

    public List<Course> getCoursesByPointsGratterThan(int points){
        //db.courses.aggregate([{ $match: {points : {$gte : 3}}}, {$project : {_id : 1}}])
        List<Course> courses = createQuery().field("points").greaterThanOrEq(points).asList();
        return courses;
        //AggregationPipeline<Student, Student> pipeline = getDs().<Student,Student>createAggregation(Student.class).match(createQuery().field("courseSet").greaterThan(2)).match(createQuery().field("age").greaterThan(25));
        //MorphiaIterator<Student,Student> morphiaIterator = pipeline.out(Student.class);



    }

}
