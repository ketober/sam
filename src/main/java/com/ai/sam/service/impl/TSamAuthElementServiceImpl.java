package com.ai.sam.service.impl;

import com.ai.sam.dao.TSamPermitMapper;
import com.ai.sam.domain.TSamAuthElement;
import com.ai.sam.domain.TSamAuthElementExample;
import com.ai.sam.domain.TSamAuthElementExample.Criteria;
import com.ai.sam.service.TSamAuthElementService;
import com.ai.sam.dao.TSamAuthElementMapper;

import com.ai.sam.service.TSamPermitService;
import com.ai.sam.utils.ServiceConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.microserv.skeleton.facade.AiService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AiService
public class TSamAuthElementServiceImpl implements TSamAuthElementService {

   private static Logger logger = LoggerFactory.getLogger(TSamAuthElementServiceImpl.class);

    @Autowired
	private TSamAuthElementMapper tsamauthelementmapper;
    @Autowired
    private TSamPermitService tsampermitservice;
    @Autowired
    private TSamPermitMapper tsampermitmapper;

    @Override
    public TSamAuthElement qryAuth(String id){
        TSamAuthElementExample e = new TSamAuthElementExample();
        Criteria c =e.createCriteria();
        c.andElementIdEqualTo(id);
        List<TSamAuthElement> auths = tsamauthelementmapper.selectByExample(e);
        if(auths !=null && auths.size()>0){
            return auths.get(0);
        }
        return null;
    }

    @Override
    public void addAuth(TSamAuthElement auth){
        tsamauthelementmapper.insertSelective(auth);
    }

    @Override
    public void updtAuth(TSamAuthElement auth){
        tsamauthelementmapper.updateByPrimaryKeySelective(auth);
    }

    @Override
    public Map<String, Object> delAuth(String authId){
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("authObjId",authId);
        data.put("permitType","2");
        int i = tsampermitmapper.selectPerMitByAuthObjId(data);

        Map<String, Object> result = new HashMap<String, Object>();
        if(i>0){
            result.put("result", ServiceConstant.STATUS_SYSERROR);
            result.put("msg", "所选的功能已分配给人员或角色，无法删除!");
        }else {
            try {
                tsamauthelementmapper.deleteByPrimaryKey(authId);
                tsampermitservice.deleteByAuth(authId);
                result.put("result", ServiceConstant.STATUS_SUCCESS);
                result.put("msg", ServiceConstant.MSG_SUCCESS);
            } catch (Exception e) {
                result.put("result", ServiceConstant.STATUS_SYSERROR);
                result.put("msg", e.getMessage());
            }
        }
        return result;
    }

    @Override
    public List<TSamAuthElement> qryAuthTree(String topId,String nodeId){
        TSamAuthElementExample e = new TSamAuthElementExample();
        Criteria c =e.createCriteria();
        if("000".equals(topId)){
            c.andElementIdIsNotNull();
            List<TSamAuthElement> auths = tsamauthelementmapper.selectByExample(e);
            TSamAuthElement rootData= new  TSamAuthElement();
            rootData.setElementId("000");
            rootData.setElementName("功能权限");
            auths.add(rootData);
            return auths;
        }else {
            c.andModuleIdEqualTo(topId);
            List<TSamAuthElement> auths = tsamauthelementmapper.selectByExample(e);
            List<TSamAuthElement> result = new ArrayList<>();
            if (nodeId == null || "".equals(nodeId)) {
                if (auths != null && auths.size() > 0) {
                    return auths;
                }
            } else {
                int len = nodeId.length();
                for (TSamAuthElement auth : auths) {
                    if (auth.getElementId().length() > len && auth.getElementId().substring(0, len).equals(nodeId)) {
                        result.add(auth);
                    }
                }
                return result;
            }
        }
        return null;
    }

    @Override
    public List<TSamAuthElement> qryModule(){
        TSamAuthElementExample e = new TSamAuthElementExample();
        Criteria c =e.createCriteria();
        c.andSuperElementCodeEqualTo("000");
        List<TSamAuthElement> auths = tsamauthelementmapper.selectByExample(e);
        if(auths !=null && auths.size()>0){
            return auths;
        }
        return null;
    }

    @Override
    public Map<String, String> qryChild(String id){
        Map<String, String> result = new HashMap<String, String>();
        TSamAuthElementExample e = new TSamAuthElementExample();
        Criteria c =e.createCriteria();
        c.andSuperElementCodeEqualTo(id);
        List<TSamAuthElement> auths = tsamauthelementmapper.selectByExample(e);
        result.put("total",String.valueOf(auths.size()+1));
        String maxChild = id+"000";
        for(TSamAuthElement auth:auths){
            if(maxChild.compareTo(auth.getElementId())<0){
                maxChild = auth.getElementId();
            }
        }
        int length = maxChild.length();
        long numMaxChild = Long.parseLong(maxChild) + 1;
        maxChild = String.format("%0" + length + "d", numMaxChild);
        result.put("newId", maxChild);
        System.out.println("id======================"+id);
        System.out.println("maxChild======================"+maxChild);
        if("000".equals(id)){
            result.put("module", maxChild.substring(3));
        }else {
            result.put("module", id.substring(3, 6));
        }
        return result;
    }
}

