package com.ai.sam.service.impl;

import com.ai.sam.domain.TSamPostInfo;
import com.ai.sam.service.TSamPostInfoService;
import com.ai.sam.dao.TSamPostInfoMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.microserv.skeleton.facade.AiService;

@AiService
public class TSamPostInfoServiceImpl implements TSamPostInfoService {

   private static Logger logger = LoggerFactory.getLogger(TSamPostInfoServiceImpl.class);

    @Autowired
	private TSamPostInfoMapper tsampostinfomapper;

    @Override
	public  TSamPostInfo getById(Integer id) throws Exception  {
       return null;
    }

}

