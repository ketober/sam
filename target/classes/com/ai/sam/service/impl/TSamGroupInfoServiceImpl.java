package com.ai.sam.service.impl;

import com.ai.sam.common.StaticValue;
import com.ai.sam.dao.TSamGroupInfoMapper;
import com.ai.sam.dao.TSamGroupMemberMapper;
import com.ai.sam.domain.TSamGroupInfo;
import com.ai.sam.domain.TSamGroupMember;
import com.ai.sam.domain.TSamOrgaInfo;
import com.ai.sam.service.TSamGroupInfoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.microserv.skeleton.facade.AiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AiService
public class TSamGroupInfoServiceImpl implements TSamGroupInfoService {

   private static Logger logger = LoggerFactory.getLogger(TSamGroupInfoServiceImpl.class);

    @Autowired
	private TSamGroupInfoMapper tsamgroupinfomapper;

    @Autowired
	private TSamGroupMemberMapper tsamgroupmembermapper;

    @Override
	public TSamGroupInfo selectByPrimaryKey(String id) throws Exception  {
       return tsamgroupinfomapper.selectByPrimaryKey(id);
    }


    @Override
    public int insert(TSamGroupInfo tSamGroupInfo) throws Exception  {
        return tsamgroupinfomapper.insert(tSamGroupInfo);
    }




    @Override
    public int updateByPrimaryKey(TSamGroupInfo tSamGroupInfo) throws Exception {
        return tsamgroupinfomapper.updateByPrimaryKey(tSamGroupInfo);
    }

    @Override
    public Map<String, Object> selectGroupInfoByStaffId(String staffId) {
        Map hashMap = new HashMap<>();
        Map resultHashMap = new HashMap<>();
        List groupMemberArrayList = new ArrayList();
        hashMap.put("staffId",staffId);
        List<TSamGroupMember>  groupMemberList = tsamgroupmembermapper.selectGroupMember(hashMap);
        if(CollectionUtils.isNotEmpty(groupMemberList)){
            for(int i = 0;i<groupMemberList.size();i++){
                groupMemberArrayList.add(tsamgroupinfomapper.selectByPrimaryKey(groupMemberList.get(i).getGroupId()));
            }
        }
        resultHashMap.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
        resultHashMap.put(StaticValue.RESULT_MSG,groupMemberArrayList);
        return resultHashMap;
    }

    @Override
    public int selectBySamGroupCountBySuperCode(TSamGroupInfo tSamGroupInfod) {
        return tsamgroupinfomapper.selectBySamGroupCountBySuperCode(tSamGroupInfod);
    }

    @Override
    public List<TSamGroupInfo> selectBySamGroupBySuperCode(TSamGroupInfo tSamGroupInfo) {
        return tsamgroupinfomapper.selectBySamGroupBySuperCode(tSamGroupInfo);
    }

    @Override
    public Map<String, Object> deleteByPrimaryKey(String groupId) {
        Map<String, Object> result = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        String [] groupIds = groupId.split(",");
        TSamGroupInfo tSamGroupInfo;
        Map<String,String> hashMap;
        for(int i = 0;i<groupIds.length;i++){
            tSamGroupInfo =  new TSamGroupInfo();
            tSamGroupInfo.setGroupId(groupIds[i]);
            hashMap = new HashMap<>();
            hashMap.put("groupId",groupIds[i]);
            //查询用户组下是否存在用户
            List<TSamGroupMember> groupMemberList = tsamgroupmembermapper.selectStaffByGroupId(hashMap);

            int count =  tsamgroupinfomapper.selectBySamGroupCountBySuperCode(tSamGroupInfo);
            if(count == 0 && CollectionUtils.isEmpty(groupMemberList)){
                try {
                    tsamgroupinfomapper.deleteByPrimaryKey(groupIds[i]);
                } catch (Exception e) {
                    result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
                    result.put(StaticValue.RESULT_MSG,e.getMessage());
                    e.printStackTrace();
                }
            }else{
                TSamGroupInfo groupInfo =  tsamgroupinfomapper.selectByPrimaryKey(groupIds[i]);
                if(count !=0){
                    sb.append(groupInfo.getGroupName()+"节点存在数据,不予删除。");
                }
                if(CollectionUtils.isNotEmpty(groupMemberList)){
                    sb.append(groupInfo.getGroupName()+"节点存在用户,不予删除。");
                }

            }



        }
        result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
        result.put(StaticValue.RESULT_MSG,sb.toString());
        return result;
    }


    /**
     *
     * @param sync  true 同步  false 异步
     * @return
     */
    @Override
    public String selectGroupInfoTree(String sync,String pId,String groupName) {
        List<Map<String,Object>> treeList  =new ArrayList<Map<String,Object>>();
        //查询第一级分类，即：最顶层节点
        List<TSamGroupInfo> doseaseList = tsamgroupinfomapper.selectSuperNode();
        if(pId !=null && pId !=""){
            treeList = createTreeChildren(doseaseList, pId,sync,groupName);
        }else {
            for (int i = 0; i < doseaseList.size(); i++) {
                Map<String, Object> map = null;
                TSamGroupInfo disease = (TSamGroupInfo) doseaseList.get(i);
                if (disease.getSuperGroupCode().equals("0")) {
                    map = new HashMap<String, Object>();
                    map.put("id", disease.getGroupId());
                    map.put("name", disease.getGroupName());
                    map.put("pId",disease.getSuperGroupCode());
                    if ("true".equals(sync)) {
                        map.put("children", createTreeChildren(doseaseList, disease.getGroupId(), sync,groupName));
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
    public String selectGroupTreeByStaffId(String staffId) {
        List<Map<String,Object>> treeList  =new ArrayList<Map<String,Object>>();
        //查询第一级分类，即：最顶层节点
        List<TSamGroupInfo> doseaseList = tsamgroupinfomapper.selectSuperNode();
        //根据StaffId查询工作组信息
        Map<String,Object> params = new HashMap<>();
        params.put("staffId",staffId);
        List<TSamGroupMember> list = tsamgroupmembermapper.selectGroupMember(params);
        List<String> listGroups = new ArrayList<>();
        if (!list.isEmpty()){
            for (TSamGroupMember t:list) {
                listGroups.add(t.getGroupId());
            }
        }

        for (int i = 0; i < doseaseList.size(); i++) {
            Map<String, Object> map = null;
            TSamGroupInfo disease = (TSamGroupInfo) doseaseList.get(i);
            if (disease.getSuperGroupCode().equals("0")) {
                map = new HashMap<String, Object>();
                map.put("id", disease.getGroupId());
                map.put("name",disease.getGroupName());
                if (listGroups.contains(disease.getGroupId())){
                    map.put("checked","true");
                }else{
                    map.put("checked","false");
                }
                map.put("children", createTreeChildrenBystaffId(listGroups,doseaseList,disease.getGroupId()));
            }
            if (map != null)
                treeList.add(map);
        }
        return JSON.toJSONString(treeList);

    }

    /**
     *
     * @param list
     * @param fid
     * @return
     */
    private List<Map<String, Object>> createTreeChildren(List<TSamGroupInfo> list, String fid,String sync,String groupName) {
        Map<String, Object> pdata = new HashMap<String, Object>();
        pdata.put("id", fid);
        pdata.put("groupName", groupName);
        //根据父节点Id查询该父节点下所有的子节点
        List<TSamGroupInfo> childrenList = tsamgroupinfomapper.selectSuperNodeByChildren(pdata);
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < childrenList.size(); j++) {
            Map<String, Object> map = null;
            TSamGroupInfo treeChild = (TSamGroupInfo)childrenList.get(j);
            if (treeChild.getSuperGroupCode().equals(fid)) {
                map = new HashMap<String, Object>();
                map.put("id", treeChild.getGroupId());
                map.put("name",treeChild.getGroupName());
                map.put("pId",treeChild.getSuperGroupCode());
                if("true".equals(sync)){
                    map.put("children", createTreeChildren(childrenList, treeChild.getGroupId(),sync,groupName));
                }else{
                    if(createTreeChildren(childrenList, treeChild.getGroupId(),sync,groupName).size()>0){
                        map.put("isParent",true);
                    }
                }

            }
            if (map != null)
                childList.add(map);
        }
        return childList;
    }
    /**
     *
     * @param list
     * @param fid
     * @return
     */
    private List<Map<String, Object>> createTreeChildrenBystaffId(List<String> listGroups, List<TSamGroupInfo> list, String fid) {
        Map<String, Object> pdata = new HashMap<String, Object>();
        pdata.put("id", fid);
        //根据父节点Id查询该父节点下所有的子节点
        List<TSamGroupInfo> childrenList = tsamgroupinfomapper.selectSuperNodeByChildren(pdata);
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < childrenList.size(); j++) {
            Map<String, Object> map = null;
            TSamGroupInfo treeChild = (TSamGroupInfo)childrenList.get(j);
            if (treeChild.getSuperGroupCode().equals(fid)) {
                map = new HashMap<String, Object>();
                map.put("id", treeChild.getGroupId());
                if (listGroups.contains(treeChild.getGroupId())){
                    map.put("checked","true");
                }else{
                    map.put("checked","false");
                }
                map.put("name",treeChild.getGroupName());
                map.put("children", createTreeChildrenBystaffId(listGroups,childrenList, treeChild.getGroupId()));
            }
            if (map != null)
                childList.add(map);
        }
        return childList;
    }
}

