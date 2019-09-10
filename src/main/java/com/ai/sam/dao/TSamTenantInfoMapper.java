package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamTenantInfo;
import com.ai.sam.domain.TSamTenantInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSamTenantInfoMapper extends Mapper<TSamTenantInfo>  {
    int countByExample(TSamTenantInfoExample example);

    int deleteByExample(TSamTenantInfoExample example);

    int deleteByPrimaryKey(String tenantId);

    int insert(TSamTenantInfo record);

    int insertSelective(TSamTenantInfo record);

    List<TSamTenantInfo> selectByExample(TSamTenantInfoExample example);

    TSamTenantInfo selectByPrimaryKey(String tenantId);

    int updateByExampleSelective(@Param("record") TSamTenantInfo record, @Param("example") TSamTenantInfoExample example);

    int updateByExample(@Param("record") TSamTenantInfo record, @Param("example") TSamTenantInfoExample example);

    int updateByPrimaryKeySelective(TSamTenantInfo record);

    int updateByPrimaryKey(TSamTenantInfo record);
}
