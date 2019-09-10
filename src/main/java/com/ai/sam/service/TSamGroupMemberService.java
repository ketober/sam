package com.ai.sam.service;

import com.ai.sam.domain.TSamGroupMember;

import java.util.List;
import java.util.Map;

public interface TSamGroupMemberService {

   TSamGroupMember getById(Integer id)throws Exception;

   Map<String,Object> addStaffGroupBatch(List<TSamGroupMember> groupList);

   int deleteByGroupId(TSamGroupMember record);

   Map<String,Object> updateStaffGroup(String staffId,String groupIds)throws Exception;

}

