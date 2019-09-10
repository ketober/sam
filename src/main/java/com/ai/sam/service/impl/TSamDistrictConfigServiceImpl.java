package com.ai.sam.service.impl;

import com.ai.sam.domain.TSamDistrictConfig;
import com.ai.sam.service.TSamDistrictConfigService;
import com.ai.sam.dao.TSamDistrictConfigMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.microserv.skeleton.facade.AiService;

@AiService
public class TSamDistrictConfigServiceImpl implements TSamDistrictConfigService {

   private static Logger logger = LoggerFactory.getLogger(TSamDistrictConfigServiceImpl.class);

    @Autowired
	private TSamDistrictConfigMapper tsamdistrictconfigmapper;

    @Override
	public  TSamDistrictConfig getById(Integer id) throws Exception  {
       return null;
    }

}

