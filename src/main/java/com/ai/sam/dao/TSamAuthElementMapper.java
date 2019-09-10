package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamAuthElement;
import com.ai.sam.domain.TSamAuthElementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSamAuthElementMapper extends Mapper<TSamAuthElement>  {
    int countByExample(TSamAuthElementExample example);

    int deleteByExample(TSamAuthElementExample example);

    int deleteByPrimaryKey(String elementId);

    int insert(TSamAuthElement record);

    int insertSelective(TSamAuthElement record);

    List<TSamAuthElement> selectByExample(TSamAuthElementExample example);

    TSamAuthElement selectByPrimaryKey(String elementId);

    int updateByExampleSelective(@Param("record") TSamAuthElement record, @Param("example") TSamAuthElementExample example);

    int updateByExample(@Param("record") TSamAuthElement record, @Param("example") TSamAuthElementExample example);

    int updateByPrimaryKeySelective(TSamAuthElement record);

    int updateByPrimaryKey(TSamAuthElement record);
}
