package com.ai.sam.service;

import com.ai.sam.domain.TSamPermit;

import java.util.List;
import java.util.Map;

public interface TSamPermitService {

   TSamPermit getById(Integer id)throws Exception;

   /**
    * 根据权限查询已赋员工信息
    * @param params
    * @return
    * @throws Exception
    */
   List<Map<String,Object>> selectStaffByAuthId(Map<String,Object> params)throws Exception;

   /**
    * 根据权限查询已赋角色信息
    * @param params
    * @return
    * @throws Exception
    */
   List<Map<String,Object>> selectRoleByAuthId(Map<String,Object> params)throws Exception;

   /**
    * 根据员工或角色查询已赋权限
    * @param params
    * @return
    * @throws Exception
    */
   List<Map<String,Object>> selectAuthByEntityId(Map<String,Object> params)throws Exception;


   Map<String,Object> updateEntityPermitAuth(List<TSamPermit> tSamPermitList)throws Exception;

   /**
    * 修改权限的赋权人员或角色
    * @param entityId
    * @param moduleId
    * @param permitType
    * @param authIds
    * @return
    * @throws Exception
    */
   Map<String,Object> updateEntityPermitAuth(String entityId, String moduleId,String permitType, String authIds)throws Exception;


   /**
    * 修改人员或角色的权限信息
    * @param moduleId
    * @param permitType
    * @param subAuthIds
    * @param parentAuthIds
    * @param addEntityIds
    * @param delEntityIds
    * @return
    * @throws Exception
    */
   Map<String,Object> updateAuthPermitEntity(String moduleId,String permitType,String subAuthIds,String parentAuthIds,String addEntityIds,String delEntityIds)throws Exception;



   /**
    * 根据staffId查询所有角色的已赋权限
    * @param
    * @return
    * @throws Exception
    */
   List<Map<String,Object>> selectAllRoleAuthByEntityId(String staffId)throws Exception;
   /**
    * 根据staffId查询所有角色的已赋权限
    * @param
    * @return
    * @throws Exception
    */
   Map<String,Object> deleteByAuth(String authIds)throws Exception;

}

