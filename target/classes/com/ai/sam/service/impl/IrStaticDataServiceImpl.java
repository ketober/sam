package com.ai.sam.service.impl;

import com.ai.sam.domain.IrStaticData;
import com.ai.sam.service.IrStaticDataService;
import com.ai.sam.dao.IrStaticDataMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.microserv.skeleton.facade.AiService;

import java.util.List;

@AiService
public class IrStaticDataServiceImpl implements IrStaticDataService {

   private static Logger logger = LoggerFactory.getLogger(IrStaticDataServiceImpl.class);

    @Autowired
	private IrStaticDataMapper irstaticdatamapper;

    @Override
	public  IrStaticData getById(Integer id) throws Exception  {
       return null;
    }

    /**
     * 查询静态数据
     */
    @Override
    public List<IrStaticData> retrieveStaticData(String codeType) throws Exception {
        return irstaticdatamapper.retrieveStaticData(codeType);
    }

}

