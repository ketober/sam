package com.ai.sam.dao;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamMenu;
import com.ai.sam.domain.TSamMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSamMenuMapper extends Mapper<TSamMenu>  {
    int countByExample(TSamMenuExample example);

    int deleteByExample(TSamMenuExample example);

    int deleteByPrimaryKey(String menuId);

    int insert(TSamMenu record);

    int insertSelective(TSamMenu record);

    List<TSamMenu> selectByExample(TSamMenuExample example);

    List<TSamMenu> selectByExample(TSamMenuExample example, RowBounds rowBounds);

    TSamMenu selectByPrimaryKey(String menuId);

    int updateByExampleSelective(@Param("record") TSamMenu record, @Param("example") TSamMenuExample example);

    int updateByExample(@Param("record") TSamMenu record, @Param("example") TSamMenuExample example);

    int updateByPrimaryKeySelective(TSamMenu record);

    int updateByPrimaryKey(TSamMenu record);

    int deleteByTenantId(String tenantId);
}
