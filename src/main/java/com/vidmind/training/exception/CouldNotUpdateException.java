package com.vidmind.training.exception;

import com.sun.jersey.api.Responses;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by barcelona on 8/14/14.
 */
public class CouldNotUpdateException extends WebApplicationException {

    public CouldNotUpdateException(String exceptionMessage) {
        super(Response.status(Responses.NOT_FOUND).entity(exceptionMessage).type(MediaType.APPLICATION_JSON_TYPE).build());
    }
}
