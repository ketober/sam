package com.ai.sam.service.impl;

import com.ai.sam.common.StaticValue;
import com.ai.sam.dao.TSamRoleMapper;
import com.ai.sam.dao.TSamRoleMutexMapper;
import com.ai.sam.domain.TSamRole;
import com.ai.sam.domain.TSamStaffRole;
import com.ai.sam.service.TSamStaffRoleService;
import com.ai.sam.dao.TSamStaffRoleMapper;

import com.ai.sam.utils.Message;
import com.ai.sam.utils.SequenceUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.microserv.skeleton.facade.AiService;
import org.springframework.util.StringUtils;

import java.util.*;

import static java.util.stream.Collectors.toList;

@AiService
public class TSamStaffRoleServiceImpl implements TSamStaffRoleService {

   private static Logger logger = LoggerFactory.getLogger(TSamStaffRoleServiceImpl.class);

    @Autowired
	private TSamStaffRoleMapper tsamstaffrolemapper;
    @Autowired
	private TSamRoleMutexMapper tsamrolemutexmapper;
    @Autowired
	private TSamRoleMapper tsamrolemapper;
    @Autowired
    private SequenceUtils sequenceUtils;

    @Override
	public  TSamStaffRole getById(Integer id) throws Exception  {
       return null;
    }

    @Override
    public Map<String,Object> addStaffRoleBatch(List<TSamStaffRole> recordList) {
        Map<String,Object> hasMap = new HashMap<>();
        if(recordList.size()>0){
            for(int i = 0;i<recordList.size();i++){
                try {
                    long key = sequenceUtils.getSequence("t_sam_staff_role");
                    recordList.get(i).setRelId(String.valueOf(key));
                    if(recordList.get(i).getStaffId().equals("null")){
                        hasMap.put("message",new Message("1","success"));
                        return hasMap;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        try {
            tsamstaffrolemapper.addStaffRoleBatch(recordList);
            hasMap.put("message",new Message("1","success"));
        } catch (Exception e) {
            hasMap.put("message",new Message("-1",e.getMessage()));
            e.printStackTrace();
        }
        return hasMap;
    }



    @Override
    public int deleteByRoleId(String roleId) {
        return tsamstaffrolemapper.deleteByRoleId(roleId);
    }

    @Override
    public Map<String, Object> updateStaffRole(String staffId, String roleIds) throws Exception {
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> params = new HashMap<>();
        String roleIdArr[];
        roleIdArr=roleIds.split(",");
        List<String> pList = Arrays.asList(roleIdArr);
        //查询所选角色是否存在互斥角色
        if (roleIdArr.length>0){
            for (int i = 0; i <roleIdArr.length ; i++) {
                String roleId = roleIdArr[i];
                Map<String,Object> param1 = new HashMap<>();
                param1.put("roleId",roleId);
                List<String> mlist =tsamrolemutexmapper.selecMutexByRoleId(param1);
                List<String> intersection = pList.stream().filter(item -> mlist.contains(item)).collect(toList());
                if (!intersection.isEmpty()){
                    //查询下角色名称并返回
                    TSamRole role = tsamrolemapper.selectByPrimaryKey(roleId);
                    TSamRole mutexRole = tsamrolemapper.selectByPrimaryKey(intersection.get(0));
                    result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
                    result.put(StaticValue.RESULT_MSG,"角色"+role.getRoleName()+"和"+ mutexRole.getRoleName()+"存在互斥关系！");
                    return result;
                }
            }
        }
        params.put("staffId",staffId);
        //删除
        tsamstaffrolemapper.deleteByStaffId(params);
        if (roleIdArr.length>0){
            for (int i = 0; i <roleIdArr.length ; i++) {
                if (!StringUtils.isEmpty(roleIdArr[i])) {
                    long key = sequenceUtils.getSequence("t_sam_staff_role");
                    params.put("relId",String.valueOf(key));
                    params.put("roleId",roleIdArr[i]);
                    //新增
                    tsamstaffrolemapper.insertStaffRole(params);
                }
            }
        }
        result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
        result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        return result;
    }

}

