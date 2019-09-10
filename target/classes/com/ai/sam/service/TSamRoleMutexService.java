package com.ai.sam.service;

import com.ai.sam.domain.TSamRoleMutex;

import java.util.List;
import java.util.Map;

public interface TSamRoleMutexService {

   TSamRoleMutex getById(Integer id)throws Exception;

   int insert(TSamRoleMutex record);

   List<String> selecMutexByRoleId(Map<String,Object> params);



}

