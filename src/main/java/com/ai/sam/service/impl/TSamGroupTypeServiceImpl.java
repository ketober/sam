package com.ai.sam.service.impl;

import com.ai.sam.domain.TSamGroupType;
import com.ai.sam.domain.TSamGroupTypeExample;
import com.ai.sam.service.TSamGroupTypeService;
import com.ai.sam.dao.TSamGroupTypeMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.microserv.skeleton.facade.AiService;

import java.util.List;

@AiService
public class TSamGroupTypeServiceImpl implements TSamGroupTypeService {

   private static Logger logger = LoggerFactory.getLogger(TSamGroupTypeServiceImpl.class);

    @Autowired
	private TSamGroupTypeMapper tsamgrouptypemapper;

    @Override
	public  TSamGroupType getById(Integer id) throws Exception  {
       return null;
    }

    @Override
    public List<TSamGroupType> selectByExample(TSamGroupTypeExample example) {
        return tsamgrouptypemapper.selectByExample(example);
    }

}

