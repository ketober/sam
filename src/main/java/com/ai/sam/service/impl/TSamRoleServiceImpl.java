package com.ai.sam.service.impl;

import com.ai.sam.common.StaticValue;
import com.ai.sam.dao.*;
import com.ai.sam.domain.*;
import com.ai.sam.service.TSamRoleService;
import com.ai.sam.utils.SequenceUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.microserv.skeleton.facade.AiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import com.ai.sam.domain.TSamRoleExample;
import com.ai.sam.domain.TSamRoleExample.Criteria;
import org.springframework.transaction.annotation.Transactional;

@AiService
public class TSamRoleServiceImpl implements TSamRoleService {

    private static Logger logger = LoggerFactory.getLogger(TSamRoleServiceImpl.class);

    @Autowired
    private TSamRoleMapper tsamrolemapper;
    @Autowired
    private TSamStaffRoleMapper tsamstaffrolemapper;

    @Autowired
    private TSamStaffInfoMapper tSamStaffInfoMapper;
    @Autowired
    private TSamRoleMutexMapper tsamrolemutexmapper;

    @Autowired
    private TSamModuleMapper tSamModuleMapper;
    @Autowired
    private TSamRoleMutexMapper tSamRoleMutexMapper;

    @Autowired
    private SequenceUtils sequenceUtils;

    @Autowired
    private TSamPermitMapper tsampermitmapper;


    @Override
    public TSamRole getById(String id) throws Exception {
        return tsamrolemapper.selectByPrimaryKey(id);
    }


    @Override
    public int insert(TSamRole record) {
        return tsamrolemapper.insert(record);
    }

    @Override
    public Map<String, String> updateByPrimaryKey(TSamRole record) {
        Map<String, String> hashMap = new HashMap<>();
        try {
            tsamrolemapper.updateByPrimaryKeySelective(record);
            hashMap.put(StaticValue.RESULT_VAL, StaticValue.RESULT_SUCCESS_VAL);
            hashMap.put(StaticValue.RESULT_MSG, StaticValue.RESULT_SUCCESS_MSG);
        } catch (Exception e) {
            hashMap.put(StaticValue.RESULT_VAL, StaticValue.RESULT_FAIL_VAL);
            hashMap.put(StaticValue.RESULT_MSG, e.getMessage());
        }
        return hashMap;
    }

    @Override
    public int selectByTSamRoleCountBySuperCode(TSamRole record) {
        return tsamrolemapper.selectByTSamRoleCountBySuperCode(record);
    }

    @Override
    public List<TSamRole> selectByTSamRoleBySuperCode(TSamRole record) {
        List<TSamRole> tSamRoleList = tsamrolemapper.selectByTSamRoleBySuperCode(record);
        if (tSamRoleList.size() > 0) {
            for (int j = 0; j < tSamRoleList.size(); j++) {
                int count = tsamstaffrolemapper.selectStaffCountByRoleId(tSamRoleList.get(j).getRoleId());
                tSamRoleList.get(j).setCount(String.valueOf(count));
                TSamModule module = tSamModuleMapper.selectByPrimaryKey(tSamRoleList.get(j).getModuleId());
                if (module != null) {
                    tSamRoleList.get(j).setModuleName(module.getModuleName());
                } else {
                    tSamRoleList.get(j).setModuleName("");

                }
            }
        }
        return tSamRoleList;
    }


    /**
     * @param pId
     * @return sync true 同步  false 异步
     */
    @Override
    public String selectTSamRoleTree(String pId, String sync) {
        List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
        //查询第一级分类，即：最顶层节点
        List<TSamRole> doseaseList = tsamrolemapper.selectSuperNode();
        if (pId != null && pId != "") {
            treeList = createTreeChildren(doseaseList, pId, sync);
        } else {
            for (int i = 0; i < doseaseList.size(); i++) {
                Map<String, Object> map = null;
                TSamRole disease = (TSamRole) doseaseList.get(i);
                if (disease.getSuperCode().equals("0")) {
                    map = new HashMap<String, Object>();
                    map.put("id", disease.getRoleId());
                    map.put("name", disease.getRoleName());
                    if ("true".equals(sync)) {
                        map.put("children", createTreeChildren(doseaseList, disease.getRoleId(), sync));
                    } else {
                        map.put("isParent", true);
                    }
                }
                if (map != null)
                    treeList.add(map);
            }
        }

        return JSON.toJSONString(treeList);

    }

    @Override
    public String selectTSamRoleTr(String sync, String moduleId, String authId) {
        List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
        //查询第一级分类，即：最顶层节点
        List<TSamRole> doseaseList = tsamrolemapper.selectSuperNode();
        List<String> checked = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("moduleId", moduleId);
        params.put("authId", authId);
        params.put("permitType", "2");
        List<Map<String, Object>> list = tsampermitmapper.selectRoleByAuthId(params);
        if (!list.isEmpty()) {
            for (Map<String, Object> check : list) {
                checked.add(check.get("entityId").toString());
            }
        }

        for (int i = 0; i < doseaseList.size(); i++) {
            Map<String, Object> map = null;
            TSamRole disease = (TSamRole) doseaseList.get(i);
            if (disease.getSuperCode().equals("0")) {
                map = new HashMap<String, Object>();
                map.put("id", disease.getRoleId());
                map.put("name", disease.getRoleName());
                if (checked.contains(disease.getRoleId())) {
                    map.put("checked", "true");
                } else {
                    map.put("checked", "false");
                }
                if ("true".equals(sync)) {
                    map.put("children", createTreeChildrenByAuth(checked, disease.getRoleId(), sync));
                } else {
                    map.put("isParent", true);
                }
            }
            if (map != null)
                treeList.add(map);
        }

        return JSON.toJSONString(treeList);

    }

    @Override
    public String selectTSamRoleTreeByStaffId(String pId, String sync, String staffId, String chkDisabled) {
        List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
        //查询第一级分类，即：最顶层节点
        List<TSamRole> doseaseList = tsamrolemapper.selectSuperNode();
        //根据StaffId查询角色信息
        Map<String, Object> params = new HashMap<>();
        params.put("staffId", staffId);
        List<TSamStaffRole> list = tsamstaffrolemapper.selectStaffRoleByStaffId(params);
        List<String> roleIds = new ArrayList<>();
        if (!list.isEmpty()) {
            for (TSamStaffRole t : list) {
                roleIds.add(t.getRoleId());
            }
        }
        if (pId != null && pId != "") {
            treeList = createTreeChildrenByStaffId(roleIds, chkDisabled, doseaseList, pId, sync);
        } else {
            for (int i = 0; i < doseaseList.size(); i++) {
                Map<String, Object> map = null;
                TSamRole disease = (TSamRole) doseaseList.get(i);
                if (disease.getSuperCode().equals("0")) {
                    map = new HashMap<String, Object>();
                    map.put("id", disease.getRoleId());
                    map.put("name", disease.getRoleName());
                    if (roleIds.contains(disease.getRoleId())) {
                        map.put("checked", "true");
                    } else {
                        map.put("checked", "false");
                    }
                    if ("true".equals(chkDisabled)) {
                        map.put("chkDisabled", "true");
                    } else {
                        map.put("chkDisabled", "false");
                    }
                    if ("true".equals(sync)) {
                        map.put("children", createTreeChildrenByStaffId(roleIds, chkDisabled, doseaseList, disease.getRoleId(), sync));
                    } else {
                        map.put("isParent", true);
                    }
                }
                if (map != null)
                    treeList.add(map);
            }
        }

        return JSON.toJSONString(treeList);
    }

    //同步
    @Override
    public String selectTSamRoleTreeSync(String pId, String sync) {
        List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
        //查询第一级分类，即：最顶层节点
        List<TSamRole> doseaseList = tsamrolemapper.selectSuperNode();
        if (pId != null && pId != "") {
            treeList = createTreeChildren(doseaseList, pId, sync);
        } else {
            for (int i = 0; i < doseaseList.size(); i++) {
                Map<String, Object> map = null;
                TSamRole disease = (TSamRole) doseaseList.get(i);
                if (disease.getSuperCode().equals("0")) {
                    map = new HashMap<String, Object>();
                    map.put("id", disease.getRoleId());
                    map.put("name", disease.getRoleName());
                    map.put("children", createTreeChildren(doseaseList, disease.getRoleId(), sync));
                }
                if (map != null)
                    treeList.add(map);
            }
        }
        return JSON.toJSONString(treeList);
    }

    @Override
    public Map<String, Object> deleteByPrimaryKey(String roleId) {
        Map<String, Object> result = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        String[] roleIds = roleId.split(",");
        TSamRole tSamRole;
        for (int i = 0; i < roleIds.length; i++) {
            tSamRole = new TSamRole();
            tSamRole.setRoleCode(roleIds[i]);
            int count = tsamrolemapper.selectByTSamRoleCountBySuperCode(tSamRole);
            if (count == 0) {
                try {
                    tsamstaffrolemapper.deleteByRoleId(roleIds[i]);
                    tsamrolemapper.deleteByPrimaryKey(roleIds[i]);
                    //删除互斥中间表
                    tsamrolemutexmapper.deleteByPrimaryKey(roleIds[i]);
                    tsamrolemutexmapper.deleteByRoleId(roleIds[i]);
                } catch (Exception e) {
                    result.put(StaticValue.RESULT_VAL, StaticValue.RESULT_FAIL_VAL);
                    result.put(StaticValue.RESULT_MSG, e.getMessage());
                    e.printStackTrace();
                }
            } else {
                TSamRole roleInfo = tsamrolemapper.selectByPrimaryKey(roleIds[i]);
                sb.append(roleInfo.getRoleName() + "节点存在数据,不予删除。");

            }
        }
        result.put(StaticValue.RESULT_VAL, StaticValue.RESULT_SUCCESS_VAL);
        result.put(StaticValue.RESULT_MSG, sb.toString());
        return result;
    }


    /**
     * @param list
     * @param fid
     * @return sync true 同步  false 异步
     */
    private List<Map<String, Object>> createTreeChildren(List<TSamRole> list, String fid, String sync) {
        Map<String, Object> pdata = new HashMap<String, Object>();
        pdata.put("id", fid);
        //根据父节点Id查询该父节点下所有的子节点
        List<TSamRole> childrenList = tsamrolemapper.selectSuperNodeByChildren(fid);
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < childrenList.size(); j++) {
            Map<String, Object> map = null;
            TSamRole treeChild = (TSamRole) childrenList.get(j);
            if (treeChild.getSuperCode().equals(fid)) {
                map = new HashMap<String, Object>();
                map.put("id", treeChild.getRoleId());
                map.put("name", treeChild.getRoleName());
                if ("true".equals(sync)) {
                    map.put("children", createTreeChildren(childrenList, treeChild.getRoleId(), sync));
                } else {
                    if (createTreeChildren(childrenList, treeChild.getRoleId(), sync).size() > 0) {
                        map.put("isParent", true);
                    }
                }

            }
            if (map != null)
                childList.add(map);
        }
        return childList;
    }

    /**
     * @param list
     * @param fid
     * @return sync true 同步  false 异步
     */
    private List<Map<String, Object>> createTreeChildrenByAuth(List<String> checked, String fid, String sync) {
        Map<String, Object> pdata = new HashMap<String, Object>();
        pdata.put("id", fid);
        //根据父节点Id查询该父节点下所有的子节点
        List<TSamRole> childrenList = tsamrolemapper.selectSuperNodeByChildren(fid);
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < childrenList.size(); j++) {
            Map<String, Object> map = null;
            TSamRole treeChild = (TSamRole) childrenList.get(j);
            if (treeChild.getSuperCode().equals(fid)) {
                map = new HashMap<String, Object>();
                map.put("id", treeChild.getRoleId());
                map.put("name", treeChild.getRoleName());
                if (checked.contains(treeChild.getRoleId())) {
                    map.put("checked", true);
                } else {
                    map.put("checked", false);
                }
                if ("true".equals(sync)) {
                    map.put("children", createTreeChildren(childrenList, treeChild.getRoleId(), sync));
                } else {
                    if (createTreeChildren(childrenList, treeChild.getRoleId(), sync).size() > 0) {
                        map.put("isParent", true);
                    }
                }

            }
            if (map != null)
                childList.add(map);
        }
        return childList;
    }

    /**
     * @param list
     * @param fid
     * @return sync true 同步  false 异步
     */
    private List<Map<String, Object>> createTreeChildrenByStaffId(List<String> roleIds, String chkDisabled, List<TSamRole> list, String fid, String sync) {
        Map<String, Object> pdata = new HashMap<String, Object>();
        pdata.put("id", fid);
        //根据父节点Id查询该父节点下所有的子节点
        List<TSamRole> childrenList = tsamrolemapper.selectSuperNodeByChildren(fid);
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < childrenList.size(); j++) {
            Map<String, Object> map = null;
            TSamRole treeChild = (TSamRole) childrenList.get(j);
            if (treeChild.getSuperCode().equals(fid)) {
                map = new HashMap<String, Object>();
                map.put("id", treeChild.getRoleId());
                if (roleIds.contains(treeChild.getRoleId())) {
                    map.put("checked", "true");
                } else {
                    map.put("checked", "false");
                }
                if ("true".equals(chkDisabled)) {
                    map.put("chkDisabled", "true");
                } else {
                    map.put("chkDisabled", "false");
                }
                map.put("name", treeChild.getRoleName());
                if ("true".equals(sync)) {
                    map.put("children", createTreeChildrenByStaffId(roleIds, chkDisabled, childrenList, treeChild.getRoleId(), sync));
                } else {
                    if (createTreeChildrenByStaffId(roleIds, chkDisabled, childrenList, treeChild.getRoleId(), sync).size() > 0) {
                        map.put("isParent", true);
                    }
                }
            }
            if (map != null)
                childList.add(map);
        }
        return childList;
    }


    /**
     * 互斥角色
     *
     * @param roleId
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> mutuallyExclusiveRole(String roleId) throws Exception {
        //[1,2,3] -->1和2,3比对
        StringBuffer sb = new StringBuffer();
        String[] roles = roleId.split(",");
        Map<String, List<TSamStaffInfo>> map = new HashMap<String, List<TSamStaffInfo>>();
        for (int i = 0; i < roles.length; i++) {
            List<TSamStaffInfo> staffInfoList = tSamStaffInfoMapper.selectStaffForRoleId(roles[i]);
            map.put(roles[i], staffInfoList);
        }
        List<TSamStaffInfo> firstStaffInfoMap = map.get(roles[0]);
        //没有勾选不做比对
        if (map.size() >= 2) {
            //删除 [1,2,3]  -->1和2,3比对,删除1
            map.remove(roles[0]);
            //第一条数据 不存在关联的人不需要做比对,直接入库。
            if (CollectionUtils.isNotEmpty(firstStaffInfoMap)) {
                if (map.values() != null) {
                    for (Map.Entry<String, List<TSamStaffInfo>> entry : map.entrySet()) {
                        String id = entry.getKey();
                        List<TSamStaffInfo> staffInfos = entry.getValue();
                        for (TSamStaffInfo staffRole : firstStaffInfoMap) {
                            for (TSamStaffInfo Infos : staffInfos) {
                                if (staffRole.getStaffId().equals(Infos.getStaffId())) {
                                    TSamRole role = tsamrolemapper.selectByPrimaryKey(id);
                                    sb.append("您勾选的角色[" + role.getRoleName() + "]下关联的用户[" + Infos.getStaffName() + "],和当前角色存在相同的人员。<br>");
                                }
                            }
                        }
                    }
                }
            }
        }
        Map<String, Object> result = new HashMap<String, Object>();
        if (!sb.toString().equals("")) {
            result.put(StaticValue.RESULT_VAL, StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG, sb.toString());
        }

        //没勾选时 只删除不做新增
        if (roles.length >= 1 && sb.toString().equals("")) {
            String mutexRoleId = roles[0];
            tsamrolemutexmapper.deleteByRoleId(mutexRoleId);
        }
        if (roles.length >= 2 && sb.toString().equals("")) {
            String mutexRoleId = roles[0];
            List<TSamRoleMutex> mutexList = new ArrayList<>();
            for (int i = 1; i < roles.length; i++) {
                TSamRoleMutex mutex = new TSamRoleMutex();
                long key = sequenceUtils.getSequence("t_sam_role");
                mutex.setMutexId(String.valueOf(key));
                mutex.setRoleid(mutexRoleId);
                mutex.setMutexRoleId(roles[i]);
                mutexList.add(mutex);
            }
            try {
                tsamrolemutexmapper.addRoleMutexBatch(mutexList);
                result.put(StaticValue.RESULT_VAL, StaticValue.RESULT_SUCCESS_VAL);
                result.put(StaticValue.RESULT_MSG, "保存成功。");
            } catch (Exception e) {
                result.put(StaticValue.RESULT_VAL, StaticValue.RESULT_FAIL_VAL);
                result.put(StaticValue.RESULT_MSG, e.getMessage());
                throw  new RuntimeException("保存异常,事物回滚。");

            }
        }
        return result;
    }

    @Override
    public Map<String, Object> mutuallyDoesexistRole(List<TSamStaffRole> recordList) {
        //hashMap  key是当前roleId   value 是勾选的staffId
        StringBuffer sb = new StringBuffer();
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> staffRoleParamMap;
        try {
            //查询出关联的互斥关系 add -->正向和 反向都要查询
            List<TSamRoleMutex> list = tsamrolemutexmapper.selecMutexByRoleIdS(recordList.get(0).getRoleId());
            if (CollectionUtils.isNotEmpty(list)) {
                for (int j = 0; j < list.size(); j++) {
                    TSamRoleMutex mutex = list.get(j);
                    staffRoleParamMap = new HashMap<>();
                    staffRoleParamMap.put("roleId", mutex.getMutexRoleId());
                    //查询互斥关系的用户
                    List<TSamStaffRole> tSamStaffRoleList = tsamstaffrolemapper.selectStaffRoleByRoleId(staffRoleParamMap);
                    if (CollectionUtils.isNotEmpty(tSamStaffRoleList)) {
                        for (TSamStaffRole staffRole : tSamStaffRoleList) {
                            for (TSamStaffRole roleObj : recordList) {
                                if (staffRole.getStaffId().equals(roleObj.getStaffId())) {
                                    //查询用户
                                    TSamStaffInfo staffInfo = tSamStaffInfoMapper.selectByPrimaryKey(staffRole.getStaffId());
                                    //查询角色名称
                                    TSamRole tSamRole = tsamrolemapper.selectByPrimaryKey(staffRole.getRoleId());
                                    sb.append("[" + staffInfo.getStaffName() + "]用户和当前角色存在互斥关系,该用户存在[" + tSamRole.getRoleName() + "]角色下。<br>");
                                }
                            }
                        }
                    }
                }
            }

            List<TSamRoleMutex> mutexRolelist = tsamrolemutexmapper.selecMutexByMutexRoleId(recordList.get(0).getRoleId());
            if (CollectionUtils.isNotEmpty(mutexRolelist)) {
                for (int j = 0; j < mutexRolelist.size(); j++) {
                    TSamRoleMutex mutex = mutexRolelist.get(j);
                    staffRoleParamMap = new HashMap<>();
                    staffRoleParamMap.put("roleId", mutex.getRoleid());
                    //查询互斥关系的用户
                    List<TSamStaffRole> tSamStaffRoleList = tsamstaffrolemapper.selectStaffRoleByRoleId(staffRoleParamMap);
                    if (CollectionUtils.isNotEmpty(tSamStaffRoleList)) {
                        for (TSamStaffRole staffRole : tSamStaffRoleList) {
                            for (TSamStaffRole roleObj : recordList) {
                                if (staffRole.getStaffId().equals(roleObj.getStaffId())) {
                                    //查询用户
                                    TSamStaffInfo staffInfo = tSamStaffInfoMapper.selectByPrimaryKey(staffRole.getStaffId());
                                    //查询角色名称
                                    TSamRole tSamRole = tsamrolemapper.selectByPrimaryKey(staffRole.getRoleId());
                                    sb.append("[" + staffInfo.getStaffName() + "]用户和当前角色存在互斥关系,该用户存在[" + tSamRole.getRoleName() + "]角色下。<br>");
                                }
                            }
                        }
                    }
                }
            }

            result.put(StaticValue.RESULT_VAL, StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG, sb.toString());
        } catch (Exception e) {
            result.put(StaticValue.RESULT_VAL, StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG, e.toString());
            e.printStackTrace();
        }

        return result;
    }


}

