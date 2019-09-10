package com.ai.sam.service.impl;

import com.ai.sam.common.StaticValue;
import com.ai.sam.domain.TSamOauthClient;
import com.ai.sam.service.TSamOauthClientService;
import com.ai.sam.dao.TSamOauthClientMapper;

import com.ai.sam.utils.BCryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.microserv.skeleton.facade.AiService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AiService
public class TSamOauthClientServiceImpl implements TSamOauthClientService {

   private static Logger logger = LoggerFactory.getLogger(TSamOauthClientServiceImpl.class);

    @Autowired
	private TSamOauthClientMapper tsamoauthclientmapper;

    @Override
	public  TSamOauthClient getById(Integer id) throws Exception  {
       return null;
    }

    @Override
    public List<Map<String, Object>> getOauthClient(Map<String, Object> params) throws Exception {
        return tsamoauthclientmapper.selectOauthClient(params);
    }

    @Override
    public List<Map<String, Object>> getOauthClient2(Map<String, Object> params) throws Exception {
        return tsamoauthclientmapper.selectOauthClient2(params);
    }

    @Override
    public int getOauthClientCount(Map<String, Object> params) throws Exception {
        return tsamoauthclientmapper.selectOauthClientCount(params);
    }

    @Override
    public Map<String, Object> updOauthClient(Map<String, Object> params) throws Exception {
        Map<String, Object> result = new HashMap<>();
        int count = tsamoauthclientmapper.updateOauthClient(params);
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
    public Map<String, Object> createOauthClient(Map<String, Object> params) throws Exception {
        Map<String, Object> result = new HashMap<>();
        int count = tsamoauthclientmapper.insertOauthClient(params);
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
    public void delOauthClient(List<String> clientIds) throws Exception {
        for (String clientId:clientIds ) {
            tsamoauthclientmapper.deleteOauthClient(clientId);
        }
    }

    @Override
    public Map<String, Object> checkOauthClient(Map<String, Object> params) throws Exception {
        Map<String, Object> result= new HashMap<>();
//        String clientSecret = (String)params.get("clientSecret");
//        //查询工号密码
//        Map<String, Object> map = tsamoauthclientmapper.qryPassWordById(params);
//        String orgiPassworBCY = (String) map.get("clientSecret");
//        boolean a = BCryptUtil.match(orgiPassword,orgiPassworBCY);
//        boolean a =true;//临时
//        if (a){
//            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
//            result.put(StaticValue.RESULT_MSG,"校验成功");
//        }else{
//            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
//            result.put(StaticValue.RESULT_MSG,"校验失败");
//        }
        return result;
    }

    @Override
    public Map<String, Object> getClientInfoById(Map<String, Object> params) throws Exception {
        //hashMap.put("redirectUri","http://10.21.57.160:8086/sam/index.html");
        return tsamoauthclientmapper.selectClientInfoById(params);
    }

}

