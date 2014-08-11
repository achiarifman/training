package com.vidmind.training.rest;

import com.mongodb.MongoClient;
import com.sun.jersey.api.core.InjectParam;
import com.vidmind.training.commons.PathParamConst;
import com.vidmind.training.commons.RestConst;
import com.vidmind.training.entities.Student;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 * Created by Achia.Rifman on 05/08/2014.
 */



@Path(RestConst.STUDENT)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRestService extends RestApplication{

    Logger LOGGER = LoggerFactory.getLogger(StudentRestService.class);


    @POST
    @Path(RestConst.NEW)
    public Student createStudent(Student student){


        LOGGER.info(student.getFirstName());
        return student;

    }

    @DELETE
    @Path(RestConst.DELETE + PathParamConst.SEPERATOR + PathParamConst.PARAM_ID)
    public void deleteStudent(@PathParam(PathParamConst.ID) ObjectId assetId){

        LOGGER.info(assetId.toString());
    }

    @PUT
    @Path(RestConst.UPDATE + PathParamConst.SEPERATOR + PathParamConst.PARAM_ID)
    public void updateStudent(Student student){

    }


    @GET
    @Path(RestConst.DETAILS + PathParamConst.SEPERATOR + PathParamConst.PARAM_ID)
    public Student getStudentDetails(@PathParam(PathParamConst.ID) ObjectId assetId){


        return new Student();

    }



}
