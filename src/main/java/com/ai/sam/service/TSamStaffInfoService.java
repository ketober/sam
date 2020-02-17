package com.ai.sam.service;

import com.ai.sam.domain.TSamStaffInfo;
import com.ai.sam.domain.TSamStaffInfoExample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TSamStaffInfoService {

   TSamStaffInfo getById(String staffId)throws Exception;

   List<TSamStaffInfo> getStaffInfo(Map<String,Object> params)throws Exception;
   List<Map<String,Object>> getStaffInfo2(Map<String,Object> params)throws Exception;
   List<Map<String,Object>> qryStaffByOrgaIdRest(Map<String,Object> params) throws Exception;
   List<Map<String,Object>> getStaffInfoRest(Map<String,Object> params)throws Exception;
   //参数校验
   List<Map<String,Object>> getStaffInfoByParams(Map<String,Object> params)throws Exception;

   int getStaffInfoCount(Map<String,Object> params)throws Exception;
   int getStaffInfoAssignCount(Map<String,Object> params)throws Exception;
   int getStaffByOrgaIdCount(Map<String, Object> params) throws Exception;

   int createStaffInfo(TSamStaffInfo staffInfo)throws Exception;

   Map<String, Object> updateStaffInfo(String opUserId,TSamStaffInfo staffInfo)throws Exception;

   Map<String, Object> deleteStaffInfo(List<String> staffIds, String opStaffId)throws Exception;

   void updateStaffStatus(List<String> staffIds)throws Exception;

   List<TSamStaffInfo> selectByExample(TSamStaffInfoExample example);


   /*空闲的用户和角色下关联的用户*/
   List<TSamStaffInfo> selectStaffFreeRole(Map<String,Object> params);
   int selectStaffFreeRoleCount(Map<String,Object> params);




   List<TSamStaffInfo> selectStaffIncludeRole(String roleId);

   /*空闲的用户和工作组下关联的用户*/
   List<TSamStaffInfo> selectStaffFreeGroup(Map<String,Object> params);
   int selectStaffFreeGroupCount(Map<String,Object> params);


   List<TSamStaffInfo> selectStaffIncludeGroup(Map<String,Object> map);

   List<TSamStaffInfo> selectStaffIncludeOrderByGroup(Map<String,Object> map);


   List<TSamStaffInfo> selectStaffIncludeGroupForPic(String groupId);


   /*角色关联的用户*/
   List<TSamStaffInfo> selectStaffForRoleId(String roleId);

   String updateStaffOrgaInfo(String staffIds,String orgaId) throws Exception;



}

