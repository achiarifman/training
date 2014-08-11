package com.vidmind.training.bean;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



/**
 * Created by Achia.Rifman on 06/08/2014.
 */
@Component
@Scope("prototype")
public class MyBeanParam {



    private String queryParam;

    public MyBeanParam() {
        queryParam = "param";

    }

    public void setQueryParam(String queryParam) {
        this.queryParam = queryParam;
    }

    public String getQueryParam() {
        return queryParam;
    }
}
