package com.vidmind.training.rest;

import com.vidmind.training.commons.RestConst;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by barcelona on 8/18/14.
 */
@Path(RestConst.MANAGMENT)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ManagmentRestService {

    @GET
    public void getSpecialRequest(){

    }
}
