package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamOrgaInfo;
import com.ai.sam.domain.TSamOrgaInfoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TSamOrgaInfoMapper extends Mapper<TSamOrgaInfo>  {
    int countByExample(TSamOrgaInfoExample example);

    int deleteByExample(TSamOrgaInfoExample example);

    int deleteByPrimaryKey(String orgaId);

    int insert(TSamOrgaInfo record);

    int insertSelective(TSamOrgaInfo record);

    List<TSamOrgaInfo> selectByExample(TSamOrgaInfoExample example);

    TSamOrgaInfo selectByPrimaryKey(String orgaId);

    int updateByExampleSelective(@Param("record") TSamOrgaInfo record, @Param("example") TSamOrgaInfoExample example);

    int updateByExample(@Param("record") TSamOrgaInfo record, @Param("example") TSamOrgaInfoExample example);

    int updateByPrimaryKeySelective(TSamOrgaInfo record);

    int updateByPrimaryKey(TSamOrgaInfo record);

    int selectByTSamOrgaInfoCountBySuperCode(TSamOrgaInfo record);

    List<TSamOrgaInfo> selectByTSamOrgaInfoBySuperCode(TSamOrgaInfo record);

    List<TSamOrgaInfo>  selectSuperNode(String opStaffId);

    List<TSamOrgaInfo>  selectSuperNodeByChildren(@Param("superOrgaCode")String superOrgaCode,@Param("opStaffId")String opStaffId);


    int selectRepeatOrgaName(Map<String,String> hashMap);

    int deleteByTenantId(String tenantId);

}
