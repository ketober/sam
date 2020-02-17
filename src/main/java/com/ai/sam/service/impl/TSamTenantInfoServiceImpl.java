package com.ai.sam.service.impl;

import com.ai.sam.dao.*;
import com.ai.sam.domain.*;
import com.ai.sam.service.TSamTenantInfoService;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.microserv.skeleton.facade.AiService;

import java.util.*;

@AiService
public class TSamTenantInfoServiceImpl implements TSamTenantInfoService {

   private static Logger logger = LoggerFactory.getLogger(TSamTenantInfoServiceImpl.class);

    @Autowired
	private TSamTenantInfoMapper tsamtenantinfomapper;
    @Autowired
    private TSamTenantauthMapper tSamTenantauthMapper;
    @Autowired
    private TSamStaffInfoMapper tsamstaffinfomapper;

    @Autowired
    private TSamPasswordMapper tsampasswordmapper;
    @Autowired
    private TSamGroupMemberMapper tsamgroupmembermapper;

    @Autowired
    private TSamRoleMapper tSamRoleMapper;
    @Autowired
    private TSamPermitMapper tsampermitmapper;
    @Autowired
    private TSamPlatformRelMapper tsamplatformrelmapper;
    @Autowired
    private TSamGroupInfoMapper tSamGroupInfoMapper;
    @Autowired
    private TSamOrgaInfoMapper tSamOrgaInfoMapper;
    @Autowired
    private TSamMenuMapper tSamMenuMapper;
    @Autowired
    private TSamModuleMapper tSamModuleMapper;

    @Override
	public  TSamTenantInfo getById(Integer id) throws Exception  {
       return null;
    }

    @Override
    public int insert(TSamTenantInfo tSamTenantInfo) throws Exception {
        Date date = new Date();
        tSamTenantInfo.setModfUserId(tSamTenantInfo.getCrtUserId());
        tSamTenantInfo.setCrtTime(date);
        tSamTenantInfo.setModfTime(date);
        return tsamtenantinfomapper.insert(tSamTenantInfo);
    }

    @Override
    public int updateByPrimaryKey(TSamTenantInfo tSamTenantInfo) throws Exception {
        tSamTenantInfo.setModfTime(new Date());
        //tSamTenantInfo.setModfUserId(tSamTenantInfo.getCrtUserId());
        return tsamtenantinfomapper.updateByPrimaryKeySelective(tSamTenantInfo);
    }

    @Override
    public void deleteStaffInfo(String tenantId) throws Exception {

        //删除人员信息
        tsamstaffinfomapper.deleteStaffInfoByTenantId(tenantId);
        //组织机构
        tSamOrgaInfoMapper.deleteByTenantId(tenantId);
        //角色
        tSamRoleMapper.deleteByTenantId(tenantId);
        //工作组
        tSamGroupInfoMapper.deleteByTenantId(tenantId);
        //菜单
        tSamMenuMapper.deleteByTenantId(tenantId);
        //模块
        tSamModuleMapper.deleteByTenantId(tenantId);
        //删除租户ID
        tsamtenantinfomapper.deleteByPrimaryKey(tenantId);

    }

    @Override
    public List<TSamTenantInfo> qryTenantInfoByExample(TSamTenantInfo tSamTenantInfo) {
        TSamTenantInfoExample example = new TSamTenantInfoExample();
        TSamTenantInfoExample.Criteria criteria =example.createCriteria();
        if(!StringUtils.isNullOrEmpty(tSamTenantInfo.getTenantId()))
        {
            criteria.andTenantIdEqualTo(tSamTenantInfo.getTenantId());
        }
        if(!StringUtils.isNullOrEmpty(tSamTenantInfo.getTenantName()))
        {
            criteria.andTenantNameLike("%"+tSamTenantInfo.getTenantName()+"%");
        }
        if(!StringUtils.isNullOrEmpty(tSamTenantInfo.getCrtUserId()))
        {
            criteria.andCrtUserIdLike("%"+tSamTenantInfo.getCrtUserId()+"%");
        }
        if (!StringUtils.isNullOrEmpty(tSamTenantInfo.getPageNum()) && !StringUtils.isNullOrEmpty(tSamTenantInfo.getStartIndex()))
        {
             example.setPageNum(tSamTenantInfo.getPageNum());
             example.setStartIndex(tSamTenantInfo.getStartIndex());
        }
        return tsamtenantinfomapper.selectByExample(example);
    }

    @Override
    public int qryTenantInfoCountByExample(TSamTenantInfo tSamTenantInfo) {
        TSamTenantInfoExample example = new TSamTenantInfoExample();
        TSamTenantInfoExample.Criteria criteria =example.createCriteria();
        if(!StringUtils.isNullOrEmpty(tSamTenantInfo.getTenantId()))
        {
            criteria.andTenantIdEqualTo(tSamTenantInfo.getTenantId());
        }
        if(!StringUtils.isNullOrEmpty(tSamTenantInfo.getTenantName()))
        {
            criteria.andTenantNameLike("%"+tSamTenantInfo.getTenantName()+"%");
        }
        if(!StringUtils.isNullOrEmpty(tSamTenantInfo.getCrtUserId()))
        {
            criteria.andCrtUserIdLike("%"+tSamTenantInfo.getCrtUserId()+"%");
        }
        return tsamtenantinfomapper.countByExample(example);
    }

    @Override
    public String selectTenantInfoTreeByStaffId(String staffId) {
        List<Map<String,Object>> treeList  =new ArrayList<Map<String,Object>>();
        //查询所有的租户
        List<TSamTenantInfo> list = tsamtenantinfomapper.selectAllTenantInfo();
        //查询当前账号所拥有的的租户权限
        Map<String,Object> params = new HashMap<>();
        params.put("staffId",staffId);
        List<TSamTenantauth> userTenantInfo = tSamTenantauthMapper.getTenantInfoByUser(staffId);
        List<String> listGroups = new ArrayList<>();
        if (!list.isEmpty()){
            for (TSamTenantauth t:userTenantInfo) {
                listGroups.add(t.getTenantId());
            }
        }
        for (TSamTenantInfo tenantInfo : list)
        {
            Map<String, Object> map = new HashMap();
             map.put("id",tenantInfo.getTenantId());
             map.put("name",tenantInfo.getTenantName());
            if (listGroups.contains(tenantInfo.getTenantId())){
                map.put("checked","true");
            }else{
                map.put("checked","false");
            }
            map.put("children",null);
            if (map != null)
                treeList.add(map);
        }
        return JSON.toJSONString(treeList);
    }


}

