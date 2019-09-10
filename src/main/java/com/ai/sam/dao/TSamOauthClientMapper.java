package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamOauthClient;
import com.ai.sam.domain.TSamOauthClientExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TSamOauthClientMapper extends Mapper<TSamOauthClient>  {
    int countByExample(TSamOauthClientExample example);

    int deleteByExample(TSamOauthClientExample example);

    int deleteByPrimaryKey(String clientId);

    int insert(TSamOauthClient record);

    int insertSelective(TSamOauthClient record);

    List<TSamOauthClient> selectByExample(TSamOauthClientExample example);

    TSamOauthClient selectByPrimaryKey(String clientId);

    int updateByExampleSelective(@Param("record") TSamOauthClient record, @Param("example") TSamOauthClientExample example);

    int updateByExample(@Param("record") TSamOauthClient record, @Param("example") TSamOauthClientExample example);

    int updateByPrimaryKeySelective(TSamOauthClient record);

    int updateByPrimaryKey(TSamOauthClient record);

    List<Map<String,Object>> selectOauthClient(Map<String,Object> params);

    Map<String, Object> selectClientInfoById(Map<String,Object> params);

    List<Map<String,Object>> selectOauthClient2(Map<String,Object> params);

    int selectOauthClientCount(Map<String,Object> params);

    int updateOauthClient(Map<String,Object> params);

    int insertOauthClient(Map<String,Object> params);

    int deleteOauthClient(String clientId);
}
