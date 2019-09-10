package com.ai.sam.service;

import com.ai.sam.domain.TSamGroupInfo;

import java.util.List;
import java.util.Map;

public interface TSamGroupInfoService {

   TSamGroupInfo selectByPrimaryKey(String id)throws Exception;


   int insert(TSamGroupInfo tSamGroupInfo)throws Exception;

   String selectGroupInfoTree(String sync,String pId,String groupName);
   String selectGroupTreeByStaffId(String staffId);

   int updateByPrimaryKey(TSamGroupInfo tSamGroupInfo)throws Exception;

   Map<String,Object> selectGroupInfoByStaffId(String staffId);
   //根据点击的节点显示count
   int selectBySamGroupCountBySuperCode(TSamGroupInfo tSamGroupInfo);

   //根据点击的节点显示数据和子节点数据
   List<TSamGroupInfo> selectBySamGroupBySuperCode(TSamGroupInfo tSamGroupInfo);
   Map<String, Object> deleteByPrimaryKey(String groupId);


}

