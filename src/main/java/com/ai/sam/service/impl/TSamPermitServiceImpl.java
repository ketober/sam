package com.ai.sam.service.impl;

import com.ai.sam.common.StaticValue;
import com.ai.sam.dao.TSamStaffRoleMapper;
import com.ai.sam.domain.TSamPermit;
import com.ai.sam.domain.TSamStaffRole;
import com.ai.sam.service.TSamPermitService;
import com.ai.sam.dao.TSamPermitMapper;

//import com.ai.sam.utils.SequenceUtils;
import com.ai.sam.utils.SequenceUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.microserv.skeleton.facade.AiService;
import org.springframework.util.StringUtils;
import scala.annotation.meta.param;

import java.util.*;

@AiService
public class TSamPermitServiceImpl implements TSamPermitService {

   private static Logger logger = LoggerFactory.getLogger(TSamPermitServiceImpl.class);

    @Autowired
	private TSamPermitMapper tsampermitmapper;
    @Autowired
	private TSamStaffRoleMapper tsamstaffrolemapper;
    @Autowired
    private SequenceUtils sequenceUtils;

    @Override
	public  TSamPermit getById(Integer id) throws Exception  {
       return null;
    }

    @Override
    public List<Map<String, Object>> selectStaffByAuthId(Map<String, Object> params) throws Exception {

        List<Map<String, Object>> list = tsampermitmapper.selectStaffByAuthId(params);
        return  list;
    }

    @Override
    public List<Map<String, Object>> selectRoleByAuthId(Map<String, Object> params) throws Exception {
        return tsampermitmapper.selectRoleByAuthId(params);
    }
    @Override
    public List<Map<String, Object>> selectAuthByEntityId(Map<String, Object> params) throws Exception {
        return tsampermitmapper.selectAuthByEntityId(params);
    }

    @Override
    public Map<String, Object> updateEntityPermitAuth(List<TSamPermit> tSamPermitList) throws Exception {
        Map<String,Object> params = new HashMap<>();
        Map<String,Object> result = new HashMap<>();
        if(CollectionUtils.isNotEmpty(tSamPermitList)){
            params.put("entityId",tSamPermitList.get(0).getEntityId());
            params.put("permitType",tSamPermitList.get(0).getPermitType());
            //根据角色id删除关联关系
            tsampermitmapper.deleteEntityAuth(params);
            for (int j = 0; j < tSamPermitList.size(); j++) {
                long key = sequenceUtils.getSequence("t_sam_permit");
                tSamPermitList.get(j).setPermitId(String.valueOf(key));
                tSamPermitList.get(j).setTenantId("1");
            }
            tsampermitmapper.insertEntityAuthBatch(tSamPermitList);
        }
        result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
        result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        return result;
    }
    @Override
    public Map<String, Object> updateEntityPermitAuth(String entityId, String moduleId,String permitType, String authIds) throws Exception {
        Map<String,Object> params = new HashMap<>();
        Map<String,Object> result = new HashMap<>();
        params.put("entityId",entityId);
        params.put("moduleId",moduleId);
        params.put("tenantId",StaticValue.TENANT_ID);
        params.put("permitType", permitType);
        //根据staffId和moduleId删除staffId和authId映射关系
        int count = tsampermitmapper.deleteEntityAuth1(params);
        //新增staffId和authId映射关系
        String authArr[];
        authArr = authIds.split(",");
        if (authArr.length > 0) {
            for (int i = 0; i < authArr.length; i++) {
                if (!StringUtils.isEmpty(authArr[i])) {
                    long key = sequenceUtils.getSequence("t_sam_permit");
                    params.put("permitId",String.valueOf(key));
                    params.put("authId", authArr[i]);
                    tsampermitmapper.insertEntityAuth(params);
                }
            }
        }
        result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
        result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        return result;
    }

    @Override
    public Map<String, Object> updateAuthPermitEntity(String moduleId,String permitType,String subAuthIds,String parentAuthIds,String addEntityIds,String delEntityIds) throws Exception {
        Map<String,Object> params1 = new HashMap<>();
        Map<String,Object> params2 = new HashMap<>();
        Map<String,Object> result = new HashMap<>();
        params1.put("moduleId",moduleId);
        params1.put("tenantId",StaticValue.TENANT_ID);
        params1.put("permitType", permitType);
        params2.put("moduleId",moduleId);
        params2.put("permitType", permitType);
        //添加要新增的实体(人员或角色)和权限及父的映射
        String addEntityArr[],parentAuthArr[];
        addEntityArr = addEntityIds.split(",");
        parentAuthArr = parentAuthIds.split(",");
        if (addEntityArr.length > 0) {
            for (int i = 0; i < addEntityArr.length; i++) {
                for(int j = 0;j<parentAuthArr.length;j++) {
                    if (!StringUtils.isEmpty(addEntityArr[i])) {
                        long key = sequenceUtils.getSequence("t_sam_permit");
                        params1.put("permitId", String.valueOf(key));
                        params1.put("entityId", addEntityArr[i]);
                        params1.put("authId",parentAuthArr[j]);
                        int count =tsampermitmapper.isExistAuthEntity(params1);
                        if (count==0){
                            tsampermitmapper.insertEntityAuth(params1);
                        }
                    }
                }
            }
        }
        //删除要去掉的实体(人员或角色)和权限及子的映射
        String delEntityArr[],subAuthArr[];
        delEntityArr = delEntityIds.split(",");
        subAuthArr = subAuthIds.split(",");
        if (delEntityArr.length > 0) {
            for (int i = 0; i < delEntityArr.length; i++) {
                for(int j = 0;j<subAuthArr.length;j++) {
                    if (!StringUtils.isEmpty(delEntityArr[i])) {
                        params2.put("entityId", delEntityArr[i]);
                        params2.put("authId",subAuthArr[j]);
                        tsampermitmapper.deleteAuthEntity(params2);
                    }
                }
            }
        }
        result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
        result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        return result;
    }

    @Override
    public List<Map<String, Object>> selectAllRoleAuthByEntityId(String staffId) throws Exception {
        //查询所有角色By staffId
        Map<String,Object> params = new HashMap<>();
        params.put("staffId",staffId);
        List<TSamStaffRole> list = tsamstaffrolemapper.selectStaffRoleByStaffId(params);
        List<String> roleIds = new ArrayList<>();
        if (!list.isEmpty()){
            for (TSamStaffRole t:list) {
                roleIds.add(t.getRoleId());
            }
        }
        //查询角色所对应的所有权限，取并集
        params.put("roleIds",roleIds);
        params.put("permitType","2");
        List<Map<String, Object>> result = tsampermitmapper.selectAuthByRoleIds(params);
        return result;
    }

    @Override
    public Map<String, Object> deleteByAuth(String authIds) throws Exception {
        Map<String,Object> params = new HashMap<>();
        Map<String,Object> result = new HashMap<>();
        String authArr[];
        authArr = authIds.split(",");
        List<String> authIdsList = Arrays.asList(authArr);
        params.put("authIds",authIdsList);
        tsampermitmapper.deleteByAuthIds(params);
        result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
        result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        return result;
    }


}

