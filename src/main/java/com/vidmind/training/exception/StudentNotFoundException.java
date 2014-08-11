package com.vidmind.training.exception;

import com.sun.jersey.api.Responses;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.soap.AddressingFeature;

/**
 * Created by Achia.Rifman on 06/08/2014.
 */
public class StudentNotFoundException extends WebApplicationException{

    public StudentNotFoundException() {
        super(Responses.notFound().build());
    }

    public StudentNotFoundException(ExceptionMessage exceptionMessage) {
        super(Response.status(Responses.NOT_FOUND).entity(exceptionMessage).type(MediaType.APPLICATION_JSON_TYPE).build());
    }
}
