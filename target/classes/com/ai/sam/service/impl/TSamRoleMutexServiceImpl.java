package com.ai.sam.service.impl;

import com.ai.sam.domain.TSamRoleMutex;
import com.ai.sam.service.TSamRoleMutexService;
import com.ai.sam.dao.TSamRoleMutexMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.microserv.skeleton.facade.AiService;

import java.util.List;
import java.util.Map;

@AiService
public class TSamRoleMutexServiceImpl implements TSamRoleMutexService {

   private static Logger logger = LoggerFactory.getLogger(TSamRoleMutexServiceImpl.class);

    @Autowired
	private TSamRoleMutexMapper tsamrolemutexmapper;

    @Override
	public  TSamRoleMutex getById(Integer id) throws Exception  {
       return null;
    }

    @Override
    public int insert(TSamRoleMutex record) {
        return tsamrolemutexmapper.insert(record);
    }

    @Override
    public List<String> selecMutexByRoleId(Map<String, Object> params) {
        return tsamrolemutexmapper.selecMutexByRoleId(params);
    }

}

