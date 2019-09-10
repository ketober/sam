package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamStaffRole;
import com.ai.sam.domain.TSamStaffRoleExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TSamStaffRoleMapper extends Mapper<TSamStaffRole>  {
    int countByExample(TSamStaffRoleExample example);

    int deleteByExample(TSamStaffRoleExample example);

    int deleteByPrimaryKey(String relId);

    int insert(TSamStaffRole record);

    int insertSelective(TSamStaffRole record);

    List<TSamStaffRole> selectByExample(TSamStaffRoleExample example);

    TSamStaffRole selectByPrimaryKey(String relId);

    int updateByExampleSelective(@Param("record") TSamStaffRole record, @Param("example") TSamStaffRoleExample example);

    int updateByExample(@Param("record") TSamStaffRole record, @Param("example") TSamStaffRoleExample example);

    int updateByPrimaryKeySelective(TSamStaffRole record);

    int updateByPrimaryKey(TSamStaffRole record);

    int addStaffRoleBatch(@Param("recordList") List<TSamStaffRole> recordList);

    int deleteByRoleId(String roleId);

    List<TSamStaffRole> selectStaffRoleByStaffId(Map<String,Object> params);
    List<TSamStaffRole> selectStaffRoleByRoleId(Map<String,Object> params);


    int selectStaffCountByRoleId(String roleId);


    int deleteByStaffId(Map<String,Object> params);

    int insertStaffRole(Map<String,Object> params);

}
