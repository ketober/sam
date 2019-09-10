package com.ai.sam.service.impl;

import com.ai.sam.common.StaticValue;
import com.ai.sam.domain.TSamServiceSystem;
import com.ai.sam.service.TSamServiceSystemService;
import com.ai.sam.dao.TSamServiceSystemMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.microserv.skeleton.facade.AiService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AiService
public class TSamServiceSystemServiceImpl implements TSamServiceSystemService {

   private static Logger logger = LoggerFactory.getLogger(TSamServiceSystemServiceImpl.class);

    @Autowired
	private TSamServiceSystemMapper tsamservicesystemmapper;

    @Override
	public  TSamServiceSystem getById(Integer id) throws Exception  {
       return null;
    }

    @Override
    public List<Map<String, Object>> getServiceSystem(Map<String, Object> params) throws Exception {
        return tsamservicesystemmapper.selectServiceSystem(params);
    }
    @Override
    public List<Map<String, Object>> getServiceSystem2(Map<String, Object> params) throws Exception {
        return tsamservicesystemmapper.selectServiceSystem2(params);
    }

    @Override
    public int getServiceSystemCount(Map<String, Object> params) throws Exception {
        return tsamservicesystemmapper.selectServiceSystemCount(params);
    }

    @Override
    public Map<String, Object> updServiceSystem(Map<String, Object> params) throws Exception {
        Map<String, Object> result = new HashMap<>();
        int count = tsamservicesystemmapper.updateServiceSystem(params);
        if (count==1){
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        }else {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_FAIL_MSG);
        }
        return result;
    }

    @Override
    public Map<String, Object> createServiceSystem(Map<String, Object> params) throws Exception {
        Map<String, Object> result = new HashMap<>();
        int count = tsamservicesystemmapper.insertServiceSystem(params);
        if (count==1){
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        }else {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_FAIL_MSG);
        }
        return result;
    }

    @Override
    public void delServiceSystem(List<String> serviceIds) throws Exception {
        for (String serviceId:serviceIds ) {
            tsamservicesystemmapper.deleteServiceSystem(serviceId);
        }
    }

}

