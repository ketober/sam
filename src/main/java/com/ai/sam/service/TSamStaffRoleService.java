package com.ai.sam.service;

import com.ai.sam.domain.TSamStaffRole;

import java.util.List;
import java.util.Map;

public interface TSamStaffRoleService {

   TSamStaffRole getById(Integer id)throws Exception;

   Map<String,Object> addStaffRoleBatch(List<TSamStaffRole> recordList);

   int deleteByRoleId(String roleId);

   Map<String,Object> updateStaffRole(String staffId,String roleIds)throws Exception;

}

