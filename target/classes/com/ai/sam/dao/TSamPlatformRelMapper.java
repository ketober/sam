package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamPlatformRel;
import com.ai.sam.domain.TSamPlatformRelExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TSamPlatformRelMapper extends Mapper<TSamPlatformRel>  {
    int countByExample(TSamPlatformRelExample example);

    int deleteByExample(TSamPlatformRelExample example);

    int deleteByPrimaryKey(String relId);

    int insert(TSamPlatformRel record);

    int insertSelective(TSamPlatformRel record);

    List<TSamPlatformRel> selectByExample(TSamPlatformRelExample example);

    TSamPlatformRel selectByPrimaryKey(String relId);

    int updateByExampleSelective(@Param("record") TSamPlatformRel record, @Param("example") TSamPlatformRelExample example);

    int updateByExample(@Param("record") TSamPlatformRel record, @Param("example") TSamPlatformRelExample example);

    int updateByPrimaryKeySelective(TSamPlatformRel record);

    int updateByPrimaryKey(TSamPlatformRel record);

    List<Map<String,Object>> getStaffPlatformRel(Map<String,Object> params);

    List<Map<String,Object>> qryNoDistriPlatform(Map<String, Object> params);

    int qryNoDistriPlatformCount(Map<String, Object> params);

    int saveStaffPlatform(Map<String, Object> params);

    int deleteStaffPlatform(Map<String, Object> params);
}
