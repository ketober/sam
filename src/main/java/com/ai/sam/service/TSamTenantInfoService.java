package com.ai.sam.service;

import com.ai.sam.domain.TSamTenantInfo;

import java.util.List;

public interface TSamTenantInfoService {

   TSamTenantInfo getById(Integer id)throws Exception;
   //新增租户
   int insert(TSamTenantInfo tSamTenantInfo)throws Exception;
   //更新租户
   int updateByPrimaryKey(TSamTenantInfo tSamTenantInfo)throws Exception;
   //删除租户
   void deleteStaffInfo(List<String> staffIds)throws Exception;

   List<TSamTenantInfo> qryTenantInfoByExample(TSamTenantInfo tSamTenantInfo);

   int qryTenantInfoCountByExample(TSamTenantInfo tSamTenantInfo);

   String selectTenantInfoTreeByStaffId(String staffId);

}

