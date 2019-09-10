package com.ai.sam.service.impl;

import com.ai.sam.common.StaticValue;
import com.ai.sam.dao.TSamOrgaInfoMapper;
import com.ai.sam.dao.TSamStaffInfoMapper;
import com.ai.sam.domain.TSamOrgaInfo;
import com.ai.sam.domain.TSamStaffInfo;
import com.ai.sam.service.TSamOrgaInfoService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.microserv.skeleton.facade.AiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AiService
public class TSamOrgaInfoServiceImpl implements TSamOrgaInfoService {

   private static Logger logger = LoggerFactory.getLogger(TSamOrgaInfoServiceImpl.class);

    @Autowired
	private TSamOrgaInfoMapper tsamorgainfomapper;


    @Autowired
    private TSamStaffInfoMapper tSamStaffInfoMapper;

    @Override
	public TSamOrgaInfo getById(String id) throws Exception  {
       return tsamorgainfomapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(TSamOrgaInfo tSamOrgaInfo) throws Exception {
        return tsamorgainfomapper.insert(tSamOrgaInfo);
    }

    @Override
    public int update(TSamOrgaInfo tSamOrgaInfo) throws Exception {
        return tsamorgainfomapper.updateByPrimaryKey(tSamOrgaInfo);
    }

    @Override
    public int selectByTSamOrgaInfoCountBySuperCode(TSamOrgaInfo record) {
        return tsamorgainfomapper.selectByTSamOrgaInfoCountBySuperCode(record);
    }


    @Override
    public String selectTSamOrgaTree(String pId) {
        List<Map<String,Object>> treeList  =new ArrayList<Map<String,Object>>();
        //查询第一级分类，即：最顶层节点
        List<TSamOrgaInfo> doseaseList = tsamorgainfomapper.selectSuperNode();
        if(pId !=null && pId !=""){
            treeList = createTreeChildren(doseaseList, pId);
        }else{
            for (int i = 0; i < doseaseList.size(); i++) {
                Map<String, Object> map = null;
                TSamOrgaInfo disease = (TSamOrgaInfo) doseaseList.get(i);
                if (disease.getSuperOrgaCode().equals("0")) {
                    map = new HashMap<String, Object>();
                    map.put("id", disease.getOrgaId());
                    map.put("name",disease.getOrgaName());
                    map.put("isParent",true);

                }
                if (map != null)
                    treeList.add(map);
            }
        }
        return JSON.toJSONString(treeList);
    }

    @Override
    public String selectTSamOrgaTreeForCombotree() {
        List<Map<String,Object>> treeList  =new ArrayList<Map<String,Object>>();
        //查询第一级分类，即：最顶层节点
        List<TSamOrgaInfo> doseaseList = tsamorgainfomapper.selectSuperNode();
        for (int i = 0; i < doseaseList.size(); i++) {
            Map<String, Object> map = null;
            TSamOrgaInfo disease = (TSamOrgaInfo) doseaseList.get(i);
            if (disease.getSuperOrgaCode().equals("0")) {
                map = new HashMap<String, Object>();
                map.put("id", disease.getOrgaId());
                map.put("text",disease.getOrgaName());
                map.put("children", createTreeChildrenForComboTree(doseaseList,disease.getOrgaId()));
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
    private List<Map<String, Object>> createTreeChildren(List<TSamOrgaInfo> list, String fid) {
        Map<String, Object> pdata = new HashMap<String, Object>();
        pdata.put("id", fid);
        //根据父节点Id查询该父节点下所有的子节点
        List<TSamOrgaInfo> childrenList = tsamorgainfomapper.selectSuperNodeByChildren(fid);
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < childrenList.size(); j++) {
            Map<String, Object> map = null;
            TSamOrgaInfo treeChild = (TSamOrgaInfo)childrenList.get(j);
            if (treeChild.getSuperOrgaCode().equals(fid)) {
                map = new HashMap<String, Object>();
                map.put("id", treeChild.getOrgaId());
                map.put("name",treeChild.getOrgaName());
                if(createTreeChildren(childrenList, treeChild.getOrgaId()).size()>0){
                    map.put("isParent",true);
                }
                //map.put("children", createTreeChildren(childrenList, treeChild.getOrgaId()));
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
    private List<Map<String, Object>> createTreeChildrenForComboTree(List<TSamOrgaInfo> list, String fid) {
        Map<String, Object> pdata = new HashMap<String, Object>();
        pdata.put("id", fid);
        //根据父节点Id查询该父节点下所有的子节点
        List<TSamOrgaInfo> childrenList = tsamorgainfomapper.selectSuperNodeByChildren(fid);
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < childrenList.size(); j++) {
            Map<String, Object> map = null;
            TSamOrgaInfo treeChild = (TSamOrgaInfo)childrenList.get(j);
            if (treeChild.getSuperOrgaCode().equals(fid)) {
                map = new HashMap<String, Object>();
                map.put("id", treeChild.getOrgaId());
                map.put("text",treeChild.getOrgaName());
                map.put("children", createTreeChildrenForComboTree(childrenList, treeChild.getOrgaId()));
            }
            if (map != null)
                childList.add(map);
        }
        return childList;
    }






    @Override
    public List<TSamOrgaInfo> selectByTSamOrgaInfoBySuperCode(TSamOrgaInfo record) {
        return tsamorgainfomapper.selectByTSamOrgaInfoBySuperCode(record);
    }

    @Override
    public Map<String, Object> deleteByPrimaryKey(String orgaId) {
        Map<String, Object> result = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        String [] orgaIds = orgaId.split(",");
        TSamOrgaInfo tSamOrgaInfo;
        for(int i = 0;i<orgaIds.length;i++){
            tSamOrgaInfo = new TSamOrgaInfo();
            tSamOrgaInfo.setOrgaCode(orgaIds[i]);
            //判断是否和人员关联
            List<TSamStaffInfo> staffInfoList= tSamStaffInfoMapper.selectStaffForOrgaId(orgaIds[i]);
            //判断是否有子数据
            int count = tsamorgainfomapper.selectByTSamOrgaInfoCountBySuperCode(tSamOrgaInfo);
            if(count == 0 && CollectionUtils.isEmpty(staffInfoList)){
                try {
                    tsamorgainfomapper.deleteByPrimaryKey(orgaIds[i]);
                } catch (Exception e) {
                    result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
                    result.put(StaticValue.RESULT_MSG,e.getMessage());
                    e.printStackTrace();
                }
            }else{
                TSamOrgaInfo record = tsamorgainfomapper.selectByPrimaryKey(orgaIds[i]);
                if(count != 0){
                    sb.append(record.getOrgaName()+"节点存在数据,不予删除。");

                }
                if(CollectionUtils.isNotEmpty(staffInfoList)){
                    sb.append(record.getOrgaName()+"节点下存在用户,不予删除。");
                }
            }
        }
        result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
        result.put(StaticValue.RESULT_MSG,sb.toString());
        return result;
    }

    @Override
    public int selectRepeatOrgaName(Map<String,String> hashMap) {
        return tsamorgainfomapper.selectRepeatOrgaName(hashMap);
    }

}

