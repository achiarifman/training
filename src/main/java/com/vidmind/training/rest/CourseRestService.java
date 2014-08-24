package com.vidmind.training.rest;

import com.sun.jersey.api.core.InjectParam;
import com.vidmind.training.commons.PathParamConst;
import com.vidmind.training.commons.RestConst;
import com.vidmind.training.commons.RestParamsConst;
import com.vidmind.training.entities.Course;
import com.vidmind.training.service.CourseService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Created by Achia.Rifman on 06/08/2014.
 */
@Path(RestConst.COURSE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseRestService {


    Logger LOGGER = LoggerFactory.getLogger(StudentRestService.class);


    @InjectParam
    CourseService courseService;

    @POST
    @Path(RestConst.NEW)
    public Course createCourse(Course course){

        courseService.createNewCourse(course);
        return course;

    }

    @DELETE
    @Path(RestConst.DELETE + PathParamConst.SEPERATOR + PathParamConst.PARAM_ID)
    public void deleteCourse(@PathParam(PathParamConst.ID) ObjectId courseId){

        courseService.deleteCourse(courseId);
    }

    @PUT
    @Path(RestConst.UPDATE)
    public void updateCourse(){

    }

    @GET
    @Path(RestConst.DETAILS + PathParamConst.SEPERATOR + PathParamConst.PARAM_ID)
    public Course getCourse(@PathParam(PathParamConst.ID) ObjectId courseId){

        return courseService.getExistingCourse(courseId);
    }

    @PUT
    @Path(RestConst.ADD_DEPENDED + PathParamConst.SEPERATOR + PathParamConst.PARAM_ID)
    public Course addCourseDependency(@PathParam(PathParamConst.ID) ObjectId courseId ,Map<String,String> addCourses){

        ObjectId dependedCourse = new ObjectId(addCourses.get(RestParamsConst.DEPENDED_ID));
        return courseService.addDependedCourse(courseId,dependedCourse);

    }
}
