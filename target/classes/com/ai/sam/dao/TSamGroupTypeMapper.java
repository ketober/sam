package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamGroupType;
import com.ai.sam.domain.TSamGroupTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSamGroupTypeMapper extends Mapper<TSamGroupType>  {
    int countByExample(TSamGroupTypeExample example);

    int deleteByExample(TSamGroupTypeExample example);

    int deleteByPrimaryKey(String groupTypeId);

    int insert(TSamGroupType record);

    int insertSelective(TSamGroupType record);

    List<TSamGroupType> selectByExample(TSamGroupTypeExample example);

    TSamGroupType selectByPrimaryKey(String groupTypeId);

    int updateByExampleSelective(@Param("record") TSamGroupType record, @Param("example") TSamGroupTypeExample example);

    int updateByExample(@Param("record") TSamGroupType record, @Param("example") TSamGroupTypeExample example);

    int updateByPrimaryKeySelective(TSamGroupType record);

    int updateByPrimaryKey(TSamGroupType record);
}
