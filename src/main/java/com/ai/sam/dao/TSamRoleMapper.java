package com.ai.sam.dao;
import com.ai.sam.domain.TSamOrgaInfo;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamRole;
import com.ai.sam.domain.TSamRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSamRoleMapper extends Mapper<TSamRole>  {
    int countByExample(TSamRoleExample example);

    int deleteByExample(TSamRoleExample example);

    int deleteByPrimaryKey(String roleId);

    int insert(TSamRole record);

    int insertSelective(TSamRole record);

    List<TSamRole> selectByExample(TSamRoleExample example);

    TSamRole selectByPrimaryKey(String roleId);

    int updateByExampleSelective(@Param("record") TSamRole record, @Param("example") TSamRoleExample example);

    int updateByExample(@Param("record") TSamRole record, @Param("example") TSamRoleExample example);

    int updateByPrimaryKeySelective(TSamRole record);

    int updateByPrimaryKey(TSamRole record);

    int selectByTSamRoleCountBySuperCode(TSamRole record);

    List<TSamRole>  selectByTSamRoleBySuperCode(TSamRole record);


    List<TSamRole>  selectSuperNode();

    List<TSamRole>  selectSuperNodeByChildren(String roleCode);

}
