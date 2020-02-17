package com.ai.sam.service.impl;

import com.ai.sam.common.StaticValue;
import com.ai.sam.dao.*;
import com.ai.sam.domain.*;
import com.ai.sam.service.TSamDataAuthElementService;
import com.ai.sam.service.TSamStaffInfoService;

import com.github.pagehelper.util.StringUtil;
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
    @Autowired
    private TSamOrgaInfoMapper tSamOrgaInfoMapper;
    @Autowired
    private TSamTenantauthMapper tSamTenantauthMapper;
    @Autowired
    private TSamDataAuthElementService tSamDataAuthElementService;
    @Override
	public  TSamStaffInfo getById(String id) throws Exception  {
       return tsamstaffinfomapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TSamStaffInfo> getStaffInfo(Map<String,Object> params) throws Exception {
        List<TSamStaffInfo> list =tsamstaffinfomapper.queryStaffInfo(params);
        return list;
    }
    @Override
    public List<Map<String,Object>> getStaffInfo2(Map<String,Object> params) throws Exception {
        TSamStaffInfo tSamStaffInfo = tsamstaffinfomapper.selectByPrimaryKey(params.get("opStaffId").toString());
        params.put("tenantId",tSamStaffInfo.getTenantId());
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
        TSamStaffInfo tSamStaffInfo = tsamstaffinfomapper.selectByPrimaryKey(params.get("opStaffId").toString());
        params.put("tenantId",tSamStaffInfo.getTenantId());
        int count= tsamstaffinfomapper.queryStaffInfoCount(params);
        return count;
    }

    @Override
    public int getStaffInfoAssignCount(Map<String, Object> params) throws Exception {
        int count= tsamstaffinfomapper.queryStaffInfoAssignCount(params);
        return count;
    }
    @Override
    public int getStaffByOrgaIdCount(Map<String, Object> params) throws Exception {
        int count= tsamstaffinfomapper.qryStaffByOrgaIdCount(params);
        return count;
    }
    @Override
    public int createStaffInfo(TSamStaffInfo staffInfo) throws Exception {
        //根据所属组织机构的租户权限给新增人员归属租户信息
        TSamOrgaInfo tSamOrgaInfo = tSamOrgaInfoMapper.selectByPrimaryKey(staffInfo.getOrgaId());
        staffInfo.setTenantId(tSamOrgaInfo.getTenantId());
        int count= tsamstaffinfomapper.insert(staffInfo);
        return count;
    }

    @Override
    public Map<String, Object> updateStaffInfo(String opStaffId,TSamStaffInfo staffInfo) throws Exception {
        Map<String, Object> result =  new HashMap<String, Object>();
        TSamOrgaInfo tSamOrgaInfo = tSamOrgaInfoMapper.selectByPrimaryKey(staffInfo.getOrgaId());
        staffInfo.setTenantId(tSamOrgaInfo.getTenantId());
        if(!tSamDataAuthElementService.checkUserDataAuth(opStaffId,staffInfo.getOrgaId()))
        {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,"当前登录工号没有该组织机构权限");
            return result;
        }
        //当前操作人是否有租户权限
        if(!tSamDataAuthElementService.checkUserDataAuth(opStaffId,staffInfo.getTenantId()))
        {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,"当前登录工号没有该租户权限");
            return result;
        }
        //选用当前组织机构的所属租户更新租户信息
        tsamstaffinfomapper.updateByPrimaryKeySelective(staffInfo);
        result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
        result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        return result;
    }

    @Override
    public Map<String, Object> deleteStaffInfo(List<String> staffIds,String opStaffId) throws Exception {
        Map<String,Object> params =new HashMap<>();
        Map<String, Object> result = new HashMap<String, Object>();
        String resultStr = new String("");
        for (String staffId:staffIds ) {
            TSamStaffInfo tSamStaffInfo = tsamstaffinfomapper.selectByPrimaryKey(staffId);
            if(!tSamDataAuthElementService.checkUserDataAuth(opStaffId,tSamStaffInfo.getOrgaId()))
            {
                resultStr += "工号： "+opStaffId+" 没有组织机构ID为："+tSamStaffInfo.getOrgaId()+"的权限</br>";
                continue;
            }
            if(!tSamDataAuthElementService.checkUserDataAuth(opStaffId,tSamStaffInfo.getTenantId()))
            {
                resultStr += "工号： "+opStaffId+" 没有租户ID为："+tSamStaffInfo.getTenantId()+"的权限</br>";
                continue;
            }
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
            //删除租户权限
            tSamTenantauthMapper.deleteByStaffId(staffId);

        }
        result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
        if(resultStr.isEmpty()) {
            result.put(StaticValue.RESULT_MSG, StaticValue.RESULT_SUCCESS_MSG);
        }
        else
        {
            result.put(StaticValue.RESULT_MSG, resultStr);
        }
        return result;
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

    @Override
    public String updateStaffOrgaInfo(String staffIds, String orgaId) throws Exception
    {
        String resultStr = new String("");
        String[] s = staffIds.split(",");
        List<String> list = new ArrayList<String>();
        List<String> listParam = new ArrayList<String>();
        Collections.addAll(list, s);
        Iterator<String> it = list.iterator();
        //查询组织机构的租户
        TSamOrgaInfo tSamOrgaInfo = tSamOrgaInfoMapper.selectByPrimaryKey(orgaId);
        String orgaTenantId = tSamOrgaInfo.getTenantId();
        while (it.hasNext())
        {
            String CurrentStaffId = it.next();
            //组织机构权限检查
            if(!tSamDataAuthElementService.checkUserDataAuth(CurrentStaffId,orgaId))
            {
                  resultStr += "工号： "+CurrentStaffId+" 没有组织机构ID为："+orgaId+"的权限</br>";
                  continue;
            }
            TSamStaffInfo tSamStaffInfo = tsamstaffinfomapper.selectByPrimaryKey(CurrentStaffId);
            //租户属性检查(当前分配人员的租户和组织机构是否是同一个租户)
            if(!orgaTenantId.equals(tSamStaffInfo.getTenantId()))
            {
                resultStr += "工号： "+CurrentStaffId+" 和组织机构ID为： "+orgaId+" 不属于同一个租户</br>";
                continue;
            }
            listParam.add(CurrentStaffId);
        }
        Map<String,Object> param = new HashMap();
        if(listParam.isEmpty())
        {
            return resultStr;
        }
        param.put("staffIds",listParam);
        param.put("orgaId",orgaId);

        //组织机构是否有人员的权限，租户是否有人员的权限
        tsamstaffinfomapper.batchUpdateByStaffIds(param);
        return resultStr;

    }

    //账号权限查询
    private boolean staffIdAuthSelect(String staffId,String authCode)
    {
        TSamPermitExample example = new TSamPermitExample();
        TSamPermitExample.Criteria criteria = example.createCriteria();
        criteria.andEntityIdEqualTo(staffId);
        criteria.andAuthObjIdEqualTo(authCode);
        List<TSamPermit> permitList = tsampermitmapper.selectByExample(example);
        return permitList.isEmpty()?false:true;
    }


}

