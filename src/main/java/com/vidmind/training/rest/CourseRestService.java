package com.vidmind.training.rest;

import com.vidmind.training.commons.PathParamConst;
import com.vidmind.training.commons.RestConst;
import com.vidmind.training.entities.Course;
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
public class CourseRestService {


    Logger LOGGER = LoggerFactory.getLogger(StudentRestService.class);

    @POST
    @Path(RestConst.NEW)
    public void createCourse(Course course){

        LOGGER.info(course.getCourseName());
    }

    @DELETE
    @Path(RestConst.DELETE + PathParamConst.SEPERATOR + PathParamConst.PARAM_ID)
    public void deleteCourse(@PathParam(PathParamConst.ID) ObjectId assetId){

    }

    @PUT
    @Path(RestConst.UPDATE)
    public void updateCourse(){

    }

    @GET
    @Path(RestConst.DETAILS + PathParamConst.SEPERATOR + PathParamConst.PARAM_ID)
    public void getCourse(@PathParam(PathParamConst.ID) ObjectId assetId){


    }

    @POST
    @Path(RestConst.ADD_DEPENDE + PathParamConst.SEPERATOR + PathParamConst.PARAM_ID)
    public void addCourseDependency(Map<String,String> addCourses){

        LOGGER.info(addCourses.keySet().toString());

    }
}
