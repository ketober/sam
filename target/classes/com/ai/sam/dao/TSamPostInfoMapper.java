package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamPostInfo;
import com.ai.sam.domain.TSamPostInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSamPostInfoMapper extends Mapper<TSamPostInfo>  {
    int countByExample(TSamPostInfoExample example);

    int deleteByExample(TSamPostInfoExample example);

    int deleteByPrimaryKey(String postId);

    int insert(TSamPostInfo record);

    int insertSelective(TSamPostInfo record);

    List<TSamPostInfo> selectByExample(TSamPostInfoExample example);

    TSamPostInfo selectByPrimaryKey(String postId);

    int updateByExampleSelective(@Param("record") TSamPostInfo record, @Param("example") TSamPostInfoExample example);

    int updateByExample(@Param("record") TSamPostInfo record, @Param("example") TSamPostInfoExample example);

    int updateByPrimaryKeySelective(TSamPostInfo record);

    int updateByPrimaryKey(TSamPostInfo record);
}
