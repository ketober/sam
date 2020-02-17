package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamGroupInfo;
import com.ai.sam.domain.TSamGroupInfoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TSamGroupInfoMapper extends Mapper<TSamGroupInfo>  {
    int countByExample(TSamGroupInfoExample example);

    int deleteByExample(TSamGroupInfoExample example);

    int deleteByPrimaryKey(String groupId);

    int insert(TSamGroupInfo record);

    int insertSelective(TSamGroupInfo record);

    List<TSamGroupInfo> selectByExample(TSamGroupInfoExample example);



    List<TSamGroupInfo>  selectSuperNode();

    List<TSamGroupInfo>  selectSuperNodeByChildren(Map map);

    TSamGroupInfo selectByPrimaryKey(String groupId);

    int updateByExampleSelective(@Param("record") TSamGroupInfo record, @Param("example") TSamGroupInfoExample example);

    int updateByExample(@Param("record") TSamGroupInfo record, @Param("example") TSamGroupInfoExample example);

    int updateByPrimaryKeySelective(TSamGroupInfo record);

    int updateByPrimaryKey(TSamGroupInfo record);


    //根据点击的节点显示count
    int selectBySamGroupCountBySuperCode(TSamGroupInfo tSamGroupInfod);

    //根据点击的节点显示数据和子节点数据
    List<TSamGroupInfo> selectBySamGroupBySuperCode(TSamGroupInfo tSamGroupInfo);

    int deleteByTenantId(String tenantId);


}
