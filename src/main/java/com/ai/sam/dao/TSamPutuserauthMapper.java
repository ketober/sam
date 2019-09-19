package com.ai.sam.dao;

import com.ai.sam.domain.TSamPutuserauth;
import com.ai.sam.domain.TSamPutuserauthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSamPutuserauthMapper {
    long countByExample(TSamPutuserauthExample example);

    int deleteByExample(TSamPutuserauthExample example);

    int deleteByPrimaryKey(String dblCenId);

    int insert(TSamPutuserauth record);

    int insertSelective(TSamPutuserauth record);

    List<TSamPutuserauth> selectByExample(TSamPutuserauthExample example);

    TSamPutuserauth selectByPrimaryKey(String dblCenId);

    int updateByExampleSelective(@Param("record") TSamPutuserauth record, @Param("example") TSamPutuserauthExample example);

    int updateByExample(@Param("record") TSamPutuserauth record, @Param("example") TSamPutuserauthExample example);

    int updateByPrimaryKeySelective(TSamPutuserauth record);

    int updateByPrimaryKey(TSamPutuserauth record);
}