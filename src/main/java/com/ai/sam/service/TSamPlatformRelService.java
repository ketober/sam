package com.ai.sam.service;

import com.ai.sam.domain.TSamPlatformRel;

import java.util.List;
import java.util.Map;

public interface TSamPlatformRelService {

   TSamPlatformRel getById(Integer id)throws Exception;

   List<Map<String,Object>> qryStaffPlatform(Map<String, Object> params)throws Exception;

   List<Map<String,Object>> qryNoDistriPlatform(Map<String, Object> params)throws Exception;

   int qryNoDistriPlatformCount(Map<String, Object> params)throws Exception;

   Map<String, Object> updateStaffPlatform(Map<String, Object> params)throws Exception;

}

