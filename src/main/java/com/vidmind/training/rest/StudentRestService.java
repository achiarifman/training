package com.vidmind.training.rest;

import com.mongodb.MongoClient;
import com.sun.jersey.api.core.InjectParam;
import com.vidmind.training.commons.PathParamConst;
import com.vidmind.training.commons.RestConst;
import com.vidmind.training.commons.RestParamsConst;
import com.vidmind.training.entities.Student;
import com.vidmind.training.exception.CouldNotUpdateException;
import com.vidmind.training.service.StudentService;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;


/**
 * Created by Achia.Rifman on 05/08/2014.
 */



@Path(RestConst.STUDENT)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRestService extends RestApplication{

    Logger LOGGER = LoggerFactory.getLogger(StudentRestService.class);


    @InjectParam
    StudentService studentService;

    @POST
    @Path(RestConst.NEW)
    public Student createStudent(Student student){


        studentService.createNewStudent(student);
        return student;

    }

    @DELETE
    @Path(RestConst.DELETE + PathParamConst.SEPERATOR + PathParamConst.PARAM_ID)
    public void deleteStudent(@PathParam(PathParamConst.ID) ObjectId assetId){

        studentService.deleteExistingStudent(assetId);
    }

/*    @PUT
    @Path(RestConst.UPDATE + PathParamConst.SEPERATOR + PathParamConst.PARAM_ID)
    public void updateStudent(Student student){

    }*/


    @PUT
    @Path(RestConst.ADD + PathParamConst.SEPERATOR + RestConst.COURSE + PathParamConst.SEPERATOR + PathParamConst.PARAM_ID)
    public Student addCourseToStudent(Map<String,String> requestJson,@PathParam(PathParamConst.ID) ObjectId studentId){

        ObjectId courseId = new ObjectId(requestJson.get(RestParamsConst.COURSE_ID));
        Student student = studentService.addCourseToStudent(studentId, courseId);
        return student;
    }

    @DELETE
    @Path(RestConst.REMOVE + PathParamConst.SEPERATOR + RestConst.COURSE + PathParamConst.SEPERATOR + PathParamConst.PARAM_ID)
    public Student removeCourseFromStudent(Map<String,String> requestJson,@PathParam(PathParamConst.ID) ObjectId studentId){

        ObjectId courseId = new ObjectId(requestJson.get(RestParamsConst.COURSE_ID));
        Student student = studentService.removeCourseFromStudent(studentId, courseId);
        return student;
    }

    @GET
    @Path(RestConst.DETAILS + PathParamConst.SEPERATOR + PathParamConst.PARAM_ID)
    public Student getStudentDetails(@PathParam(PathParamConst.ID) ObjectId studentId){

        return studentService.getStudentDetails(studentId);

    }



}
