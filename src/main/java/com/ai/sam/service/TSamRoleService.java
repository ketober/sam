package com.ai.sam.service;

import com.ai.sam.domain.TSamRole;
import com.ai.sam.domain.TSamRoleExample;
import com.ai.sam.domain.TSamStaffRole;

import java.util.List;
import java.util.Map;

public interface TSamRoleService {

   TSamRole getById(String id)throws Exception;

   int insert(TSamRole record);

   Map<String,String> updateByPrimaryKey(TSamRole record);

   int selectByTSamRoleCountBySuperCode(TSamRole example);

   List<TSamRole> selectByTSamRoleBySuperCode(TSamRole example);

   String selectTSamRoleTree(String pId,String sync);

    String selectTSamRoleTr(String sync,String moduleId,String authId);

   String selectTSamRoleTreeByStaffId(String pId,String sync,String staffId,String chkDisabled);


   String selectTSamRoleTreeSync(String pId,String sync);

   Map<String, Object>  deleteByPrimaryKey(String roleId);

   /**
    * 互斥角色
    * @param roleId
    * @return
    */
   Map<String, Object> mutuallyExclusiveRole(String roleId) throws Exception;


   /**
    * 分配人员的时候判断 该分配的人员是否存在互斥角色里
    * @param roleId
    * @return
    */
   Map<String, Object>  mutuallyDoesexistRole(List<TSamStaffRole> recordList);

}

