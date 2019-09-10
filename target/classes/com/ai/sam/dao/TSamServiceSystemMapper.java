package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamServiceSystem;
import com.ai.sam.domain.TSamServiceSystemExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TSamServiceSystemMapper extends Mapper<TSamServiceSystem>  {
    int countByExample(TSamServiceSystemExample example);

    int deleteByExample(TSamServiceSystemExample example);

    int insert(TSamServiceSystem record);

    int insertSelective(TSamServiceSystem record);

    List<TSamServiceSystem> selectByExample(TSamServiceSystemExample example);

    int updateByExampleSelective(@Param("record") TSamServiceSystem record, @Param("example") TSamServiceSystemExample example);

    int updateByExample(@Param("record") TSamServiceSystem record, @Param("example") TSamServiceSystemExample example);

    List<Map<String,Object>> selectServiceSystem(Map<String,Object> params);

    List<Map<String,Object>> selectServiceSystem2(Map<String,Object> params);

    int selectServiceSystemCount(Map<String,Object> params);

    int updateServiceSystem(Map<String,Object> params);

    int insertServiceSystem(Map<String,Object> params);

    int deleteServiceSystem(String serviceId);
}
