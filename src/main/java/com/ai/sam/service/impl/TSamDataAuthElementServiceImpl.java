package com.ai.sam.service.impl;

import com.ai.sam.dao.*;
import com.ai.sam.domain.*;
import com.ai.sam.service.TSamDataAuthElementService;
import com.ai.sam.utils.SequenceUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import org.apache.microserv.skeleton.facade.AiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@AiService
public class TSamDataAuthElementServiceImpl implements TSamDataAuthElementService {

    private static Logger logger = LoggerFactory.getLogger(TSamDataAuthElementServiceImpl.class);

    @Autowired
    private TSamDataauthconfigMapper tSamDataauthconfigMapper;

    @Autowired
    private TSamCommonconfiginfoMapper tSamCommonconfiginfoMapper;

    @Autowired
    private TSamUserdataauthMapper tSamUserdataauthMapper;

    @Autowired
    private SequenceUtils sequenceUtils;
    @Autowired
    private TSamRoleMapper tsamrolemapper;
    @Autowired
    private TSamRoledataauthMapper tSamRoledataauthMapper;

    @Override
    public String getDataAuthTree(String id) {
        List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
        //查询第一级分类，即：最顶层节点
        TSamDataauthconfigExample tSamDataauthconfigExample = new TSamDataauthconfigExample();
        //TSamDataauthconfigExample.Criteria criteria = tSamDataauthconfigExample.createCriteria();
        List<TSamDataauthconfig> allDataAuthElementList = tSamDataauthconfigMapper.selectByExample(tSamDataauthconfigExample);
        Map<String, Object> root = new HashMap<String, Object>();
        String rootId = "001";
        root.put("id", rootId);
        root.put("name", "数据权限树");
        root.put("children", querytDataAuth(allDataAuthElementList, rootId));
        treeList.add(root);
        return JSON.toJSONString(treeList);
    }

    public List<Map<String, Object>> querytDataAuth(List<TSamDataauthconfig> list, String parentId) {
        List<Map<String, Object>> dataAuthInfo = new ArrayList<Map<String, Object>>();
        for (TSamDataauthconfig tSamDataauthconfig : list) {
            String pId = tSamDataauthconfig.getRootvalue();
            if (parentId.equals(pId)) {
                Map<String, Object> memus = new HashMap<String, Object>();
                String id = tSamDataauthconfig.getAuthtypeid();
                memus.put("id", tSamDataauthconfig.getAuthtypeid());
                memus.put("name", tSamDataauthconfig.getAuthtypename());
                memus.put("isParent", "tree");
//				dataAuthInfo.add(memus);

                //if (id.size() > 0) {
                List<Map<String, Object>> l = querytDataAuth(list, id);
                if (l != null && !l.isEmpty()) {
                    memus.put("children", l);
                }
                //}
                dataAuthInfo.add(memus);

            }
        }
        return dataAuthInfo;
    }

    @Override
    public int addAuthData(TSamCommonconfiginfo tSamCommonconfiginfo) throws Exception {
        tSamCommonconfiginfo.setId(sequenceUtils.getSequence("t_sam_commonconfiginfo").toString());
        return tSamCommonconfiginfoMapper.insert(tSamCommonconfiginfo);
    }

    @Override
    public String getCommonAuthConfigInfoById(TSamCommonconfiginfo tSamCommonconfiginfo) {

        TSamCommonconfiginfoExample example = new TSamCommonconfiginfoExample();
        TSamCommonconfiginfoExample.Criteria criteria = example.createCriteria();
        if(StringUtil.isNotEmpty(tSamCommonconfiginfo.getAuthconfigid()))
        {
            criteria.andAuthconfigidEqualTo(tSamCommonconfiginfo.getAuthconfigid());
        }
        if(StringUtil.isNotEmpty(tSamCommonconfiginfo.getCommonid()))
        {
            criteria.andCommonidEqualTo(tSamCommonconfiginfo.getCommonid());
        }
        if(StringUtil.isNotEmpty(tSamCommonconfiginfo.getCommonname()))
        {
            criteria.andCommonnameEqualTo(tSamCommonconfiginfo.getCommonname());
        }
        example.setStart(tSamCommonconfiginfo.getStart());
        example.setPage(tSamCommonconfiginfo.getPage());
        List<TSamCommonconfiginfo> list = tSamCommonconfiginfoMapper.selectByExample(example);
        return JSON.toJSONString(list);
    }

    @Override
    public int assignDataAuth(TSamUserdataauth tSamUserdataauth) throws Exception {
        String[] s = tSamUserdataauth.getStaffid().split(",");
        List<String> list = new ArrayList<String>();
        Collections.addAll(list, s);
        //先删除之前的权限
        TSamUserdataauthExample example = new TSamUserdataauthExample();
        TSamUserdataauthExample.Criteria criteria = example.createCriteria();
        criteria.andAuthtypeidEqualTo(tSamUserdataauth.getAuthtypeid());
        criteria.andAuthobjectidEqualTo(tSamUserdataauth.getAuthobjectid());
        tSamUserdataauthMapper.deleteByExample(example);
        //批量插入分配的权限
        List<TSamUserdataauth> userdataauthList = new ArrayList<TSamUserdataauth>();
        for(String staffId:list)
        {
            TSamUserdataauth tSamUserdataauthInsert = new TSamUserdataauth();
            tSamUserdataauthInsert.setStaffid(staffId);
            tSamUserdataauthInsert.setId(sequenceUtils.getSequence("t_sam_userdataauth").toString());
            tSamUserdataauthInsert.setAuthobjectid(tSamUserdataauth.getAuthobjectid());
            tSamUserdataauthInsert.setAuthtypeid(tSamUserdataauth.getAuthtypeid());
            tSamUserdataauthInsert.setAuthid(tSamUserdataauth.getAuthtypeid());
            userdataauthList.add(tSamUserdataauthInsert);
        }
        return tSamUserdataauthMapper.batchInsert(userdataauthList);
    }

    @Override
    public List<Map<String,Object>> selectDataAuthAssignedStaff(Map<String,Object> param) throws Exception {
        return tSamUserdataauthMapper.selectDataAuthAssignedStaff(param);
    }

    @Override
    public void deleteDataAuthTree(String authTypeId) throws Exception{
        //删除人员权限数据
        tSamUserdataauthMapper.deleteByAuthTypeId(authTypeId);
        //删除权限数据
        tSamCommonconfiginfoMapper.deleteByAuthConfigId(authTypeId);
        //删除树节点
        tSamDataauthconfigMapper.deleteByAuthTypeId(authTypeId);
    }

    @Override
    public List<TSamDataauthconfig> selectDataAuth(TSamDataauthconfig tSamDataauthconfig) {
        TSamDataauthconfigExample example = new TSamDataauthconfigExample();
        TSamDataauthconfigExample.Criteria criteria = example.createCriteria();
        if(StringUtil.isNotEmpty(tSamDataauthconfig.getAuthtypeid()))
        {
            criteria.andAuthtypeidEqualTo(tSamDataauthconfig.getAuthtypeid());
        }
        if(StringUtil.isNotEmpty(tSamDataauthconfig.getAuthtypename()))
        {
            criteria.andAuthtypenameEqualTo(tSamDataauthconfig.getAuthtypename());
        }
        return  tSamDataauthconfigMapper.selectByExample(example);
    }

    @Override
    public boolean checkAuthExistChild(String authTypeId) {
        TSamDataauthconfigExample example = new TSamDataauthconfigExample();
        TSamDataauthconfigExample.Criteria criteria = example.createCriteria();
        criteria.andRootvalueEqualTo(authTypeId);
        List<TSamDataauthconfig> list =tSamDataauthconfigMapper.selectByExample(example);
        return list.isEmpty()?false:true;
    }

    @Override
    public boolean checkRepeatRecordCommmonConfig(TSamCommonconfiginfo tSamCommonconfiginfo) throws Exception{
        TSamCommonconfiginfoExample example = new TSamCommonconfiginfoExample();
        TSamCommonconfiginfoExample.Criteria criteria = example.createCriteria();
        criteria.andAuthconfigidEqualTo(tSamCommonconfiginfo.getAuthconfigid());
        criteria.andCommonidEqualTo(tSamCommonconfiginfo.getCommonid());
        List<TSamCommonconfiginfo> list = tSamCommonconfiginfoMapper.selectByExample(example);
        return list.isEmpty()?false:true;
    }

    @Override
    public void deleteCommonAuthData(TSamCommonconfiginfo tSamCommonconfiginfo) throws Exception{
        String commonids = tSamCommonconfiginfo.getCommonid();
        String authconfigid = tSamCommonconfiginfo.getAuthconfigid();
        List<String> list = Arrays.asList(commonids.split(","));
        tSamCommonconfiginfoMapper.batchDeleteCommonconfiginfo(authconfigid,list);
    }

    @Override
    public boolean checkUserDataAuth(String StaffId, String AuthId) {
        TSamUserdataauthExample example = new TSamUserdataauthExample();
        TSamUserdataauthExample.Criteria criteria = example.createCriteria();
        criteria.andStaffidEqualTo(StaffId);
        criteria.andAuthobjectidEqualTo(AuthId);
        List<TSamUserdataauth> list = tSamUserdataauthMapper.selectByExample(example);
        return list.isEmpty()?false:true;
    }

    @Override
    public boolean checkRoleDataAuth(String roleId, String AuthId) {
        TSamRoledataauthExample example = new TSamRoledataauthExample();
        TSamRoledataauthExample.Criteria criteria = example.createCriteria();
        criteria.andRoleidEqualTo(roleId);
        criteria.andAuthobjectidEqualTo(AuthId);
        List<TSamRoledataauth> list = tSamRoledataauthMapper.selectByExample(example);
        return list.isEmpty()?false:true;
    }

    @Override
    public String selectTSamRoleDataAuthTree(String sync, String authId) {
        List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
        //查询第一级分类，即：最顶层节点
        List<TSamRole> doseaseList = tsamrolemapper.selectSuperNode();
        List<String> checked = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        //params.put("moduleId", moduleId);
        params.put("authid", authId);
        //角色权限表查询已分配角色;
        List<Map<String, Object>> list = tSamRoledataauthMapper.selectRoleByAuthId(params);
        if (!list.isEmpty()) {
            for (Map<String, Object> check : list) {
                checked.add(check.get("roleid").toString());
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
    public void updateRoleDataAuthService(String commonid, String authtypeid, String roleIds) {
        String roleIdArr[];
        roleIdArr=roleIds.split(",");
        List<String> pList = Arrays.asList(roleIdArr);
        List<TSamRoledataauth> list = new ArrayList<TSamRoledataauth>();
        for (String roleId:pList)
        {
            TSamRoledataauth tSamRoledataauth = new TSamRoledataauth();
            tSamRoledataauth.setRoleid(roleId);
            tSamRoledataauth.setAuthid(authtypeid);
            tSamRoledataauth.setAuthtypeid(authtypeid);
            tSamRoledataauth.setAuthobjectid(commonid);
            list.add(tSamRoledataauth);
        }
        //删除之前当前对象的角色
        TSamRoledataauthExample example = new TSamRoledataauthExample();
        TSamRoledataauthExample.Criteria criteria = example.createCriteria();
        criteria.andAuthtypeidEqualTo(authtypeid);
        criteria.andAuthobjectidEqualTo(commonid);
        tSamRoledataauthMapper.deleteByExample(example);
        //批量插入权限
        tSamRoledataauthMapper.batchUpdateRoleDataAuth(list);
    }

    @Override
    public int selectDataAuthAssignedStaffCount(Map<String, Object> param) {
        return tSamUserdataauthMapper.selectDataAuthAssignedStaffCount(param);
    }

    /**
     * @param checked
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
                System.out.println(treeChild.getRoleId());
                if (checked.contains(treeChild.getRoleId())) {
                    map.put("checked", true);
                } else {
                    map.put("checked", false);
                }
                if ("true".equals(sync)) {
                    map.put("children", createTreeChildrenByAuth(checked, treeChild.getRoleId(), sync));
                } else {
                    if (createTreeChildrenByAuth(checked, treeChild.getRoleId(), sync).size() > 0) {
                        map.put("isParent", true);
                    }
                }

            }
            if (map != null)
                childList.add(map);
        }
        return childList;
    }

    public int insertIntoDataConfig(TSamDataauthconfig tSamDataauthconfig) throws Exception
    {
        tSamDataauthconfig.setId(sequenceUtils.getSequence("t_sam_dataauthconfig").toString());
        return tSamDataauthconfigMapper.insert(tSamDataauthconfig);
    }


}

