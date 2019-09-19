package com.ai.sam.dao;

import com.ai.sam.domain.TSamTenantauth;
import com.ai.sam.domain.TSamTenantauthExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TSamTenantauthMapper {
    long countByExample(TSamTenantauthExample example);

    int deleteByExample(TSamTenantauthExample example);

    int deleteByPrimaryKey(String dblCenId);

    int deleteByStaffId(String staffId);
    int insert(TSamTenantauth record);

    int insertSelective(TSamTenantauth record);

    List<TSamTenantauth> selectByExample(TSamTenantauthExample example);

    TSamTenantauth selectByPrimaryKey(String dblCenId);

    int updateByExampleSelective(@Param("record") TSamTenantauth record, @Param("example") TSamTenantauthExample example);

    int updateByExample(@Param("record") TSamTenantauth record, @Param("example") TSamTenantauthExample example);

    int updateByPrimaryKeySelective(TSamTenantauth record);

    int updateByPrimaryKey(TSamTenantauth record);
    List<TSamTenantauth> getTenantInfoByUser(String staffId);
    int batchInsert(Map<String,Object> param);
}