package com.ai.sam.service.impl;

import com.ai.sam.domain.TSamModule;
import com.ai.sam.domain.TSamModuleExample;
import com.ai.sam.service.TSamModuleService;
import com.ai.sam.dao.TSamModuleMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.microserv.skeleton.facade.AiService;

import java.util.List;

@AiService
public class TSamModuleServiceImpl implements TSamModuleService {

   private static Logger logger = LoggerFactory.getLogger(TSamModuleServiceImpl.class);

    @Autowired
	private TSamModuleMapper tsammodulemapper;

    @Override
    public int addModule(TSamModule module){
        return tsammodulemapper.insert(module);
    }

    @Override
    public int updtModule(TSamModule module){
        return tsammodulemapper.updateByPrimaryKeySelective(module);
    }

    @Override
    public int delModule(String id){
        return tsammodulemapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<TSamModule> qryModule(){
        TSamModuleExample e = new TSamModuleExample();
        TSamModuleExample.Criteria c = e.createCriteria();
        c.andModuleIdIsNotNull();
        return tsammodulemapper.selectByExample(e);
    }


}

