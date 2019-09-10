package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamProvinceInfo;
import com.ai.sam.domain.TSamProvinceInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSamProvinceInfoMapper extends Mapper<TSamProvinceInfo>  {
    int countByExample(TSamProvinceInfoExample example);

    int deleteByExample(TSamProvinceInfoExample example);

    int deleteByPrimaryKey(String provinceId);

    int insert(TSamProvinceInfo record);

    int insertSelective(TSamProvinceInfo record);

    List<TSamProvinceInfo> selectByExample(TSamProvinceInfoExample example);

    TSamProvinceInfo selectByPrimaryKey(String provinceId);

    int updateByExampleSelective(@Param("record") TSamProvinceInfo record, @Param("example") TSamProvinceInfoExample example);

    int updateByExample(@Param("record") TSamProvinceInfo record, @Param("example") TSamProvinceInfoExample example);

    int updateByPrimaryKeySelective(TSamProvinceInfo record);

    int updateByPrimaryKey(TSamProvinceInfo record);
}
