package com.vidmind.training.exception;

import com.sun.jersey.api.Responses;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by barcelona on 8/18/14.
 */
public class DependedCourseException extends WebApplicationException {

    public DependedCourseException() {
        super(Responses.notFound().build());
    }

    public DependedCourseException(ExceptionMessage exceptionMessage) {
        super(Response.status(Responses.PRECONDITION_FAILED).entity(exceptionMessage).type(MediaType.APPLICATION_JSON_TYPE).build());
    }

    public DependedCourseException(String exceptionMessage) {
        super(Response.status(Responses.PRECONDITION_FAILED).entity(new ExceptionMessage(exceptionMessage)).type(MediaType.APPLICATION_JSON_TYPE).build());
    }
}
