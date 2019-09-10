package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamPermit;
import com.ai.sam.domain.TSamPermitExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TSamPermitMapper extends Mapper<TSamPermit>  {
    int countByExample(TSamPermitExample example);

    int deleteByExample(TSamPermitExample example);

    int deleteByPrimaryKey(String permitId);

    int insert(TSamPermit record);

    int insertSelective(TSamPermit record);

    List<TSamPermit> selectByExample(TSamPermitExample example);

    TSamPermit selectByPrimaryKey(String permitId);

    int updateByExampleSelective(@Param("record") TSamPermit record, @Param("example") TSamPermitExample example);

    int updateByExample(@Param("record") TSamPermit record, @Param("example") TSamPermitExample example);

    int updateByPrimaryKeySelective(TSamPermit record);

    int updateByPrimaryKey(TSamPermit record);

    List<Map<String,Object>> selectStaffByAuthId(Map<String,Object> params);

    List<Map<String,Object>> selectRoleByAuthId(Map<String,Object> params);

    List<Map<String,Object>> selectAuthByEntityId(Map<String,Object> params);

    int deleteEntityAuth(Map<String,Object> params);
    int deleteEntityAuth1(Map<String,Object> params);

    int deleteAuthEntity(Map<String,Object> params);
    int deleteByEntityId(Map<String,Object> params);

    int isExistAuthEntity(Map<String,Object> params);

    int insertEntityAuth(Map<String,Object> params);


    int insertEntityAuthBatch(@Param("tSamPermitList") List<TSamPermit> tSamPermitList);

    List<Map<String,Object>> selectAuthByRoleIds(Map<String,Object> params);

    int deleteByAuthIds(Map<String,Object> params);

    int selectPerMitByAuthObjId(Map<String,Object> params);

}
