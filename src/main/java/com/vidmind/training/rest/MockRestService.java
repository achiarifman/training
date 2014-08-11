package com.vidmind.training.rest;

import com.sun.jersey.api.core.InjectParam;
import com.vidmind.training.bean.JsonBean;
import com.vidmind.training.bean.MyBeanParam;
import com.vidmind.training.exception.ExceptionMessage;
import com.vidmind.training.exception.StudentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Map;

/**
 * Created by Achia.Rifman on 06/08/2014.
 */
@Path("mock")
@Produces(MediaType.APPLICATION_JSON)

public class MockRestService extends RestApplication{

    Logger LOGGER = LoggerFactory.getLogger(StudentRestService.class);

    String name;

    @InjectParam
    MyBeanParam myBeanParam;

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void mockForm(@FormParam("username") String username, @FormParam("password") String password){

        LOGGER.info("Name is -> " + username);
        LOGGER.info("password is -> " + password);

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void mockJson(JsonBean jsonBean){

        LOGGER.info("Name is -> " + jsonBean.getName());
        LOGGER.info("Id is -> " + jsonBean.getId());

    }

    @GET
    public void mockContext(@Context Request request){
        /*MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        MultivaluedMap<String, String> pathParams = uriInfo.getPathParameters();*/
        LOGGER.info(myBeanParam.getQueryParam());
        myBeanParam.setQueryParam("app");
        throw new StudentNotFoundException(new ExceptionMessage("Could not found the student"));
    }



    @GET
    @Path("header")
    public void mockHeader(@Context HttpHeaders httpHeaders){
        MultivaluedMap<String, String> headerParams = httpHeaders.getRequestHeaders();
        Map<String, Cookie> pathParams = httpHeaders.getCookies();
        LOGGER.info(myBeanParam.getQueryParam());
    }

/*    @POST
    @Path("bean")
    public void mockBean(@BeanParam MyBeanParam myBeanParam){
        final String pathParam = myBeanParam.getQueryParam();
    }*/

    @GET
    @Path("name/{name}")
    public void mockName(@PathParam("name") String name){

        LOGGER.info(name);
        this.name = name;
        LOGGER.info(this.name);
    }

}
