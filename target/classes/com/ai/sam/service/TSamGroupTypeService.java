package com.ai.sam.service;

import com.ai.sam.domain.TSamGroupType;
import com.ai.sam.domain.TSamGroupTypeExample;

import java.util.List;

public interface TSamGroupTypeService {

   TSamGroupType getById(Integer id)throws Exception;

   List<TSamGroupType> selectByExample(TSamGroupTypeExample example);


}

