package com.ai.sam.service.impl;

import com.ai.sam.common.StaticValue;
import com.ai.sam.dao.*;
import com.ai.sam.domain.*;
import com.ai.sam.service.TSamOrgaInfoService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.microserv.skeleton.facade.AiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.ai.sam.service.TSamDataAuthElementService;
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

    @Autowired
    private TSamTenantauthMapper tSamTenantauthMapper;

    @Autowired
    private TSamPermitMapper tsampermitmapper;
    @Autowired
    private TSamUserdataauthMapper tSamUserdataauthMapper;

    @Autowired
    private TSamDataAuthElementService tSamDataAuthElementService;
    @Override
	public TSamOrgaInfo getById(String id) throws Exception  {
       return tsamorgainfomapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(TSamOrgaInfo tSamOrgaInfo) throws Exception {
       /* //根据当前操作人员的所属租户来添加租户信息
        TSamStaffInfo tSamStaffInfo = tSamStaffInfoMapper.selectByPrimaryKey(tSamOrgaInfo.getOpStaffId());
        tSamOrgaInfo.setTenantId(tSamStaffInfo.getTenantId());*/
        return tsamorgainfomapper.insert(tSamOrgaInfo);
    }

    @Override
    public int update(TSamOrgaInfo tSamOrgaInfo) throws Exception {
        //根据当前用户更新租户Id
        String opStaffId = tSamOrgaInfo.getOpStaffId();
        //当前操作人是否有组织机构权限

        if(!tSamDataAuthElementService.checkUserDataAuth(opStaffId,tSamOrgaInfo.getOrgaId()))
        {
            throw new Exception("当前登录工号没有该组织机构权限");
        }
        //当前操作人是否有租户权限
        if(!tSamDataAuthElementService.checkUserDataAuth(opStaffId,tSamOrgaInfo.getTenantId()))
        {
            throw new Exception("当前登录工号没有该租户权限");
        }
        return tsamorgainfomapper.updateByPrimaryKey(tSamOrgaInfo);
    }

    @Override
    public int selectByTSamOrgaInfoCountBySuperCode(TSamOrgaInfo record) {
        return tsamorgainfomapper.selectByTSamOrgaInfoCountBySuperCode(record);
    }


    @Override
    public String selectTSamOrgaTree(String pId,String opStaffId) {
        List<Map<String,Object>> treeList  =new ArrayList<Map<String,Object>>();
        //查询第一级分类，即：最顶层节点
        List<TSamOrgaInfo> doseaseList = tsamorgainfomapper.selectSuperNode(opStaffId);
        if(pId !=null && pId !=""){
            treeList = createTreeChildren(doseaseList, pId, opStaffId);
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
    public String selectTSamOrgaTreeForCombotree(String opStaffId) {
        List<Map<String,Object>> treeList  =new ArrayList<Map<String,Object>>();
        //查询第一级分类，即：最顶层节点
        List<TSamOrgaInfo> doseaseList = tsamorgainfomapper.selectSuperNode(opStaffId);
        for (int i = 0; i < doseaseList.size(); i++) {
            Map<String, Object> map = null;
            TSamOrgaInfo disease = (TSamOrgaInfo) doseaseList.get(i);
            if (disease.getSuperOrgaCode().equals("0")) {
                map = new HashMap<String, Object>();
                map.put("id", disease.getOrgaId());
                map.put("text",disease.getOrgaName());
                map.put("children", createTreeChildrenForComboTree(doseaseList,disease.getOrgaId(),opStaffId));
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
    private List<Map<String, Object>> createTreeChildren(List<TSamOrgaInfo> list, String fid, String opStaffId) {
        Map<String, Object> pdata = new HashMap<String, Object>();
        pdata.put("id", fid);
        //根据父节点Id查询该父节点下所有的子节点
        List<TSamOrgaInfo> childrenList = tsamorgainfomapper.selectSuperNodeByChildren(fid,opStaffId);
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < childrenList.size(); j++) {
            Map<String, Object> map = null;
            TSamOrgaInfo treeChild = (TSamOrgaInfo)childrenList.get(j);
            if (treeChild.getSuperOrgaCode().equals(fid)) {
                map = new HashMap<String, Object>();
                map.put("id", treeChild.getOrgaId());
                map.put("name",treeChild.getOrgaName());
                if(createTreeChildren(childrenList, treeChild.getOrgaId(),opStaffId).size()>0){
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
    private List<Map<String, Object>> createTreeChildrenForComboTree(List<TSamOrgaInfo> list, String fid,String opStaffId) {
        Map<String, Object> pdata = new HashMap<String, Object>();
        pdata.put("id", fid);
        //根据父节点Id查询该父节点下所有的子节点
        List<TSamOrgaInfo> childrenList = tsamorgainfomapper.selectSuperNodeByChildren(fid,opStaffId);
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < childrenList.size(); j++) {
            Map<String, Object> map = null;
            TSamOrgaInfo treeChild = (TSamOrgaInfo)childrenList.get(j);
            if (treeChild.getSuperOrgaCode().equals(fid)) {
                map = new HashMap<String, Object>();
                map.put("id", treeChild.getOrgaId());
                map.put("text",treeChild.getOrgaName());
                map.put("children", createTreeChildrenForComboTree(childrenList, treeChild.getOrgaId(),opStaffId));
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
    public Map<String, Object> deleteByPrimaryKey(String orgaId,String opStaffId) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        String [] orgaIds = orgaId.split(",");
        TSamOrgaInfo tSamOrgaInfo;
        //判断当前账号是否有操作数据权限

        for(int i = 0;i<orgaIds.length;i++){
            tSamOrgaInfo = new TSamOrgaInfo();
            tSamOrgaInfo.setOrgaCode(orgaIds[i]);
            //是否有该租户权限
            TSamOrgaInfo tSamOrgaInfo1 = tsamorgainfomapper.selectByPrimaryKey(orgaIds[i]);
            boolean tenantFlag = tSamDataAuthElementService.checkUserDataAuth(opStaffId,tSamOrgaInfo1.getTenantId());
            //是否有该组织权限
            boolean orgFlag = tSamDataAuthElementService.checkUserDataAuth(opStaffId,orgaIds[i]);
            //判断是否和人员关联
            List<TSamStaffInfo> staffInfoList= tSamStaffInfoMapper.selectStaffForOrgaId(orgaIds[i]);
            //判断是否有子数据
            int count = tsamorgainfomapper.selectByTSamOrgaInfoCountBySuperCode(tSamOrgaInfo);
            //是否有权限删除

            if(count == 0 && CollectionUtils.isEmpty(staffInfoList)&& tenantFlag && orgFlag){
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
                    sb.append(record.getOrgaName()+"：节点存在数据,不予删除。</br>");

                }
                if(CollectionUtils.isNotEmpty(staffInfoList)){
                    sb.append(record.getOrgaName()+"：节点下存在用户,不予删除。</br>");
                }
                if(!orgFlag)
                {
                    sb.append(record.getOrgaName()+"：操作人员没有该节点的组织机构权限,不予删除。</br>");
                }
                if(!tenantFlag)
                {
                    sb.append(record.getOrgaName()+"：操作人员没有该节点的租户权限,不予删除。</br>");
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

