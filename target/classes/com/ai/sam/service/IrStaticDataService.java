package com.ai.sam.service;

import com.ai.sam.domain.IrStaticData;

import java.util.List;

public interface IrStaticDataService {

   IrStaticData getById(Integer id)throws Exception;
   /**
    * 查询静态数据
    * @param codeType
    * @return
    * @throws Exception
    */
   List<IrStaticData> retrieveStaticData(String codeType) throws Exception;

}

