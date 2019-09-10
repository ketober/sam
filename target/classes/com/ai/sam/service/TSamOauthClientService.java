package com.ai.sam.service;

import com.ai.sam.domain.TSamOauthClient;

import java.util.List;
import java.util.Map;

public interface TSamOauthClientService {

   TSamOauthClient getById(Integer id)throws Exception;

   List<Map<String,Object>> getOauthClient(Map<String,Object> params)throws Exception;

   List<Map<String,Object>> getOauthClient2(Map<String,Object> params)throws Exception;

   int getOauthClientCount(Map<String,Object> params)throws Exception;

   Map<String,Object> updOauthClient(Map<String,Object> params)throws Exception;

   Map<String,Object> createOauthClient(Map<String,Object> params)throws Exception;

   void delOauthClient(List<String> clientIds)throws Exception;

   Map<String, Object> checkOauthClient(Map<String, Object> params)throws Exception;

   Map<String,Object> getClientInfoById(Map<String,Object> params)throws Exception;

}

