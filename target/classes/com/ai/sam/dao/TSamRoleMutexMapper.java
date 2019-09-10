package com.ai.sam.dao;
import com.ai.sam.domain.TSamGroupMember;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamRoleMutex;
import com.ai.sam.domain.TSamRoleMutexExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TSamRoleMutexMapper extends Mapper<TSamRoleMutex>  {
    int countByExample(TSamRoleMutexExample example);

    int deleteByExample(TSamRoleMutexExample example);

    int deleteByPrimaryKey(String mutexId);

    int insert(TSamRoleMutex record);

    int insertSelective(TSamRoleMutex record);

    List<TSamRoleMutex> selectByExample(TSamRoleMutexExample example);

    TSamRoleMutex selectByPrimaryKey(String mutexId);

    int updateByExampleSelective(@Param("record") TSamRoleMutex record, @Param("example") TSamRoleMutexExample example);

    int updateByExample(@Param("record") TSamRoleMutex record, @Param("example") TSamRoleMutexExample example);

    int updateByPrimaryKeySelective(TSamRoleMutex record);

    int updateByPrimaryKey(TSamRoleMutex record);

    List<String> selecMutexByRoleId(Map<String,Object> params);


    List<TSamRoleMutex> selecMutexByRoleIdS(String roleId);

    List<TSamRoleMutex> selecMutexByMutexRoleId(String roleId);




    int addRoleMutexBatch(@Param("tSamRoleMutexList") List<TSamRoleMutex> tSamRoleMutexList);

    int deleteByRoleId(String roleId);


    int selectStaffCountByRoleId(String roleId);

}
