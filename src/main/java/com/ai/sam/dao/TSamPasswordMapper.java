package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamPassword;
import com.ai.sam.domain.TSamPasswordExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TSamPasswordMapper extends Mapper<TSamPassword>  {
    int countByExample(TSamPasswordExample example);

    int deleteByExample(TSamPasswordExample example);

    int deleteByPrimaryKey(String staffId);

    int insert(TSamPassword record);

    int insertSelective(TSamPassword record);

    List<TSamPassword> selectByExample(TSamPasswordExample example);

    TSamPassword selectByPrimaryKey(String staffId);

    int updateByExampleSelective(@Param("record") TSamPassword record, @Param("example") TSamPasswordExample example);

    int updateByExample(@Param("record") TSamPassword record, @Param("example") TSamPasswordExample example);

    int updateByPrimaryKeySelective(TSamPassword record);

    int updateByPrimaryKey(TSamPassword record);

    int qryPassWordCount(Map<String, Object> params);

    int updatePassword(Map<String, Object> params);

    int insertPassword(Map<String, Object> params);

    Map<String, Object> qryPassWordById(Map<String, Object> params);
}
