package com.vidmind.training.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;
import com.sun.jersey.api.core.InjectParam;
import com.vidmind.training.commons.RestConst;
import com.vidmind.training.service.ManagmentService;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Created by barcelona on 8/18/14.
 */
@Path(RestConst.MANAGMENT)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ManagmentRestService {

    @InjectParam
    ManagmentService managmentService;

    @GET
    public Map<ObjectId, List<String>> getSpecialRequest(){

        Map<ObjectId, List<String>> objectIdListMap = managmentService.getSpecial();
        return objectIdListMap;
    }
}
