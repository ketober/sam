package com.ai.sam.service;

import com.ai.sam.domain.TSamServiceSystem;

import java.util.List;
import java.util.Map;

public interface TSamServiceSystemService {

   TSamServiceSystem getById(Integer id)throws Exception;

   List<Map<String,Object>> getServiceSystem(Map<String,Object> params)throws Exception;

   List<Map<String,Object>> getServiceSystem2(Map<String,Object> params)throws Exception;

   int getServiceSystemCount(Map<String,Object> params)throws Exception;

   Map<String,Object> updServiceSystem(Map<String,Object> params)throws Exception;

   Map<String,Object> createServiceSystem(Map<String,Object> params)throws Exception;

   void delServiceSystem(List<String> serviceIds)throws Exception;

}

