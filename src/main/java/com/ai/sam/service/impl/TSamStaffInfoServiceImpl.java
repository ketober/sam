package com.ai.sam.service.impl;

import com.ai.sam.dao.*;
import com.ai.sam.domain.*;
import com.ai.sam.service.TSamStaffInfoService;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.microserv.skeleton.facade.AiService;

import java.util.*;

@AiService
public class TSamStaffInfoServiceImpl implements TSamStaffInfoService {
//    @Autowired
//    TSamStaffInfoMapper tSamStaffInfoMapper;

   private static Logger logger = LoggerFactory.getLogger(TSamStaffInfoServiceImpl.class);

    @Autowired
	private TSamStaffInfoMapper tsamstaffinfomapper;

    @Autowired
    private TSamPasswordMapper tsampasswordmapper;
    @Autowired
    private TSamGroupMemberMapper tsamgroupmembermapper;

    @Autowired
    private TSamStaffRoleMapper tsamstaffrolemapper;
    @Autowired
    private TSamPermitMapper tsampermitmapper;
    @Autowired
    private TSamPlatformRelMapper tsamplatformrelmapper;
    @Autowired
    private TSamGroupInfoMapper tSamGroupInfoMapper;

    @Override
	public  TSamStaffInfo getById(Integer id) throws Exception  {
       return null;
    }

    @Override
    public List<TSamStaffInfo> getStaffInfo(Map<String,Object> params) throws Exception {
        List<TSamStaffInfo> list =tsamstaffinfomapper.queryStaffInfo(params);
        return list;
    }
    @Override
    public List<Map<String,Object>> getStaffInfo2(Map<String,Object> params) throws Exception {
        List<Map<String,Object>> list =tsamstaffinfomapper.queryStaffInfo2(params);
        return list;
    }
    @Override
    public List<Map<String,Object>> qryStaffByOrgaIdRest(Map<String,Object> params) throws Exception {
        List<Map<String,Object>> list =tsamstaffinfomapper.qryStaffByOrgaIdRest(params);
        return list;
    }
    @Override
    public List<Map<String,Object>> getStaffInfoRest(Map<String,Object> params) throws Exception {
        List<Map<String,Object>> list =tsamstaffinfomapper.getStaffInfoRest(params);
        List<TSamStaffRole> rolelist = tsamstaffrolemapper.selectStaffRoleByStaffId(params);
        String roleCode="";
        String roleStr="";
        if(rolelist.size()>0){
            for (TSamStaffRole tSamStaffRole:rolelist) {
                roleCode=roleCode+tSamStaffRole.getRoleId()+",";
            }
            roleStr= roleCode.substring(0,roleCode.length()-1);
        }
        list.get(0).put("roleCode",roleStr);
        return list;
    }
    @Override
    public List<Map<String,Object>> getStaffInfoByParams(Map<String,Object> params) throws Exception {
        List<Map<String,Object>> list =tsamstaffinfomapper.getStaffInfoByParams(params);
        return list;
    }
    @Override
    public int getStaffInfoCount(Map<String, Object> params) throws Exception {
        int count= tsamstaffinfomapper.queryStaffInfoCount(params);
        return count;
    }
    @Override
    public int getStaffByOrgaIdCount(Map<String, Object> params) throws Exception {
        int count= tsamstaffinfomapper.qryStaffByOrgaIdCount(params);
        return count;
    }
    @Override
    public int createStaffInfo(TSamStaffInfo staffInfo) throws Exception {
        int count= tsamstaffinfomapper.insert(staffInfo);
        return count;
    }

    @Override
    public int updateStaffInfo(TSamStaffInfo staffInfo) throws Exception {
        int count= tsamstaffinfomapper.updateByPrimaryKeySelective(staffInfo);
        return count;
    }

    @Override
    public void deleteStaffInfo(List<String> staffIds) throws Exception {
        Map<String,Object> params =new HashMap<>();

        for (String staffId:staffIds ) {
            params.put("staffId",staffId);
            tsamstaffinfomapper.deleteByPrimaryKey(staffId);
            //删除用户密码
            tsampasswordmapper.deleteByPrimaryKey(staffId);
            //删除人员工作组
            tsamgroupmembermapper.deleteGroupMember(params);
            //删除人员角色
            tsamstaffrolemapper.deleteByStaffId(params);
            //删除人员权限
            tsampermitmapper.deleteByEntityId(params);
            //释放平台工号
            tsamplatformrelmapper.deleteStaffPlatform(params);

        }
    }
    @Override
    public void updateStaffStatus(List<String> staffIds) throws Exception {
        for (String staffId:staffIds ) {
            tsamstaffinfomapper.updateStaffStatus(staffId);
        }
    }

    @Override
    public List<TSamStaffInfo> selectByExample(TSamStaffInfoExample example) {
        return tsamstaffinfomapper.selectByExample(example);
    }

    @Override
    public List<TSamStaffInfo> selectStaffFreeRole(Map<String,Object> params) {
        return tsamstaffinfomapper.selectStaffFreeRole(params);
    }

    @Override
    public int selectStaffFreeRoleCount(Map<String,Object> params) {
        return tsamstaffinfomapper.selectStaffFreeRoleCount(params);
    }

    @Override
    public List<TSamStaffInfo> selectStaffIncludeRole(String roleId) {
        return tsamstaffinfomapper.selectStaffIncludeRole(roleId);
    }

    @Override
    public List<TSamStaffInfo> selectStaffFreeGroup(Map<String,Object> params) {
        return tsamstaffinfomapper.selectStaffFreeGroup(params);
    }

    @Override
    public int selectStaffFreeGroupCount(Map<String,Object> params) {
        return tsamstaffinfomapper.selectStaffFreeGroupCount(params);
    }

    @Override
    public List<TSamStaffInfo> selectStaffIncludeGroup(Map<String,Object> map) {
        return tsamstaffinfomapper.selectStaffIncludeGroup(map);
    }

    @Override
    public List<TSamStaffInfo> selectStaffIncludeOrderByGroup(Map<String, Object> map) {
        List<TSamStaffInfo> tSamStaffInfoList = tsamstaffinfomapper.selectStaffIncludeRelatedGroup(map);
        Map<String,Object> params;
        List<TSamGroupInfo> list;
        if(CollectionUtils.isNotEmpty(tSamStaffInfoList)){
            for(int i = 0;i<tSamStaffInfoList.size();i++){
                params = new HashMap<>();
                params.put("staffId",tSamStaffInfoList.get(i).getStaffId());
                //查询工作组和人员的中间表
                List<TSamGroupMember> groupMemberList =  tsamgroupmembermapper.selectGroupMember(params);
                if(CollectionUtils.isNotEmpty(groupMemberList)){
                    list= new ArrayList<>();
                    for(int j = 0;j<groupMemberList.size();j++){
                        TSamGroupInfo groupInfo = tSamGroupInfoMapper.selectByPrimaryKey(groupMemberList.get(j).getGroupId());
                        if(groupInfo !=null){
                            list.add(groupInfo);
                        }
                    }
                    if(list.size() !=0){
                        tSamStaffInfoList.get(i).setGroupInfoList(list);
                    }
                }
            }
        }
        return tSamStaffInfoList;
    }

    @Override
    public List<TSamStaffInfo> selectStaffIncludeGroupForPic(String groupId) {
        return tsamstaffinfomapper.selectStaffIncludeGroupForPic(groupId);
    }

    @Override
    public List<TSamStaffInfo> selectStaffForRoleId(String roleId) {
        return tsamstaffinfomapper.selectStaffForRoleId(roleId);
    }


}

