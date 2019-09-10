package com.ai.sam.service;

import com.ai.sam.domain.TSamOrgaInfo;

import java.util.List;
import java.util.Map;

public interface TSamOrgaInfoService {

   TSamOrgaInfo getById(String id)throws Exception;
   int insert(TSamOrgaInfo tSamOrgaInfo)throws Exception;
   int update(TSamOrgaInfo tSamOrgaInfo)throws Exception;

   //根据点击的节点显示count
   int selectByTSamOrgaInfoCountBySuperCode(TSamOrgaInfo record);
   String selectTSamOrgaTree(String pId);
   String selectTSamOrgaTreeForCombotree();

   //根据点击的节点显示数据和子节点数据
   List<TSamOrgaInfo> selectByTSamOrgaInfoBySuperCode(TSamOrgaInfo record);

   Map<String, Object> deleteByPrimaryKey(String orgaId);
   int selectRepeatOrgaName(Map<String,String> hashMap);


}

