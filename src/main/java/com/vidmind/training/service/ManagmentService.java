package com.vidmind.training.service;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

/**
 * Created by barcelona on 8/18/14.
 */
public interface ManagmentService {

    public Map<ObjectId, List<String>> getSpecial();
}
