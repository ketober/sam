package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamDistrictConfig;
import com.ai.sam.domain.TSamDistrictConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSamDistrictConfigMapper extends Mapper<TSamDistrictConfig>  {
    int countByExample(TSamDistrictConfigExample example);

    int deleteByExample(TSamDistrictConfigExample example);

    int deleteByPrimaryKey(String regnId);

    int insert(TSamDistrictConfig record);

    int insertSelective(TSamDistrictConfig record);

    List<TSamDistrictConfig> selectByExample(TSamDistrictConfigExample example);

    TSamDistrictConfig selectByPrimaryKey(String regnId);

    int updateByExampleSelective(@Param("record") TSamDistrictConfig record, @Param("example") TSamDistrictConfigExample example);

    int updateByExample(@Param("record") TSamDistrictConfig record, @Param("example") TSamDistrictConfigExample example);

    int updateByPrimaryKeySelective(TSamDistrictConfig record);

    int updateByPrimaryKey(TSamDistrictConfig record);
}
