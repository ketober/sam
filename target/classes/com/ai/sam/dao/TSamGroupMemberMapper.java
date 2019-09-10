package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamGroupMember;
import com.ai.sam.domain.TSamGroupMemberExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TSamGroupMemberMapper extends Mapper<TSamGroupMember>  {
    int countByExample(TSamGroupMemberExample example);

    int deleteByExample(TSamGroupMemberExample example);

    int deleteByPrimaryKey(String memberId);

    int insert(TSamGroupMember record);

    int insertSelective(TSamGroupMember record);

    List<TSamGroupMember> selectByExample(TSamGroupMemberExample example);

    TSamGroupMember selectByPrimaryKey(String memberId);

    int updateByExampleSelective(@Param("record") TSamGroupMember record, @Param("example") TSamGroupMemberExample example);

    int updateByExample(@Param("record") TSamGroupMember record, @Param("example") TSamGroupMemberExample example);

    int updateByPrimaryKeySelective(TSamGroupMember record);

    int updateByPrimaryKey(TSamGroupMember record);

    int addStaffGroupBatch(@Param("groupList") List<TSamGroupMember> groupList);

    int deleteByGroupId(TSamGroupMember record);

    List<TSamGroupMember>  selectGroupMember(Map<String,Object> params);

    List<TSamGroupMember>  selectStaffByGroupId(Map<String,String> params);


    int deleteGroupMember(Map<String,Object> params);

    int insertGroupMember(Map<String,Object> params);
}
