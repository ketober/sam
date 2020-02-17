package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamModule;
import com.ai.sam.domain.TSamModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSamModuleMapper extends Mapper<TSamModule>  {
    int countByExample(TSamModuleExample example);

    int deleteByExample(TSamModuleExample example);

    int deleteByPrimaryKey(String moduleId);

    int insert(TSamModule record);

    int insertSelective(TSamModule record);

    List<TSamModule> selectByExample(TSamModuleExample example);

    TSamModule selectByPrimaryKey(String moduleId);

    int updateByExampleSelective(@Param("record") TSamModule record, @Param("example") TSamModuleExample example);

    int updateByExample(@Param("record") TSamModule record, @Param("example") TSamModuleExample example);

    int updateByPrimaryKeySelective(TSamModule record);

    int updateByPrimaryKey(TSamModule record);

    int deleteByTenantId(String tenantId);
}
