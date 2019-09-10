package com.ai.sam.service.impl;

import com.ai.sam.domain.TSamProvinceInfo;
import com.ai.sam.service.TSamProvinceInfoService;
import com.ai.sam.dao.TSamProvinceInfoMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.microserv.skeleton.facade.AiService;

@AiService
public class TSamProvinceInfoServiceImpl implements TSamProvinceInfoService {

   private static Logger logger = LoggerFactory.getLogger(TSamProvinceInfoServiceImpl.class);

    @Autowired
	private TSamProvinceInfoMapper tsamprovinceinfomapper;

    @Override
	public  TSamProvinceInfo getById(Integer id) throws Exception  {
       return null;
    }

}

