package com.vidmind.training.service;

import com.mongodb.DBObject;
import com.vidmind.training.dao.BaseQueries;
import com.vidmind.training.dao.CourseQueries;
import com.vidmind.training.dao.StudentQueries;
import com.vidmind.training.dao.mapper.DBObjectToSpecialMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by barcelona on 8/18/14.
 */
@Service
public class ManagmentServiceImpl implements ManagmentService{



    @Autowired
    CourseQueries courseQueries;

    @Autowired
    StudentQueries studentQueries;

    @Autowired
    BaseQueries baseQueries;

    @Autowired
    DBObjectToSpecialMapper dbObjectToSpecialMapper;

    @Override
    public Map<ObjectId, List<String>> getSpecial() {


        List<DBObject> dbObjectList = baseQueries.special();
        //Map<ObjectId,List<ObjectId>> dbObjectsResponse = new HashMap<ObjectId,List<ObjectId>>();
        return dbObjectToSpecialMapper.dbObjectListToSpecialMap(dbObjectList);
    }
}
