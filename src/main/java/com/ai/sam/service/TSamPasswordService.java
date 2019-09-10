package com.ai.sam.service;

import com.ai.sam.domain.TSamPassword;

import java.util.List;
import java.util.Map;

public interface TSamPasswordService {

   TSamPassword getById(Integer id)throws Exception;

   Map<String, Object> qryPassWordById(Map<String, Object> params)throws Exception;

   Map<String, Object> checkPassWord(Map<String, Object> params)throws Exception;

   Map<String, Object> createPassWord(Map<String, Object> params)throws Exception;

   Map<String, Object> updatePassWord(Map<String, Object> params)throws Exception;

   void resetPassWord(List<String> staffIds)throws Exception;

}

