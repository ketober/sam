package com.ai.sam.service.impl;

import com.ai.sam.domain.TSamTenantInfo;
import com.ai.sam.service.TSamTenantInfoService;
import com.ai.sam.dao.TSamTenantInfoMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.microserv.skeleton.facade.AiService;

@AiService
public class TSamTenantInfoServiceImpl implements TSamTenantInfoService {

   private static Logger logger = LoggerFactory.getLogger(TSamTenantInfoServiceImpl.class);

    @Autowired
	private TSamTenantInfoMapper tsamtenantinfomapper;

    @Override
	public  TSamTenantInfo getById(Integer id) throws Exception  {
       return null;
    }

}

