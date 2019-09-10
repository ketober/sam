package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.TSamStaffInfo;
import com.ai.sam.domain.TSamStaffInfoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TSamStaffInfoMapper extends Mapper<TSamStaffInfo>  {
    int countByExample(TSamStaffInfoExample example);

    int deleteByExample(TSamStaffInfoExample example);

    int deleteByPrimaryKey(String staffId);

    int insert(TSamStaffInfo record);

    int insertSelective(TSamStaffInfo record);

    List<TSamStaffInfo> selectByExample(TSamStaffInfoExample example);

    TSamStaffInfo selectByPrimaryKey(String staffId);

    int updateByExampleSelective(@Param("record") TSamStaffInfo record, @Param("example") TSamStaffInfoExample example);

    int updateByExample(@Param("record") TSamStaffInfo record, @Param("example") TSamStaffInfoExample example);

    int updateByPrimaryKeySelective(TSamStaffInfo record);

    int updateByPrimaryKey(TSamStaffInfo record);

    /**
     * 修改账号状态
     * @param staffId
     * @return
     */
    int updateStaffStatus(String staffId);

    /**
     *  按条件查询人员信息（分页）
     * @return
     */
    List<TSamStaffInfo> queryStaffInfo(Map<String,Object> params);
    /**
     *  按条件查询人员信息（分页）
     * @return
     */
    List<Map<String,Object>> queryStaffInfo2(Map<String,Object> params);
    /**
     *  组织机构人员信息（分页）
     * @return
     */
    List<Map<String,Object>> qryStaffByOrgaIdRest(Map<String,Object> params);
    /**
     *  按条件查询人员信息
     * @return
     */
    List<Map<String,Object>> getStaffInfoRest(Map<String,Object> params);
    /**
     *  按条件参数校验
     * @return
     */
    List<Map<String,Object>> getStaffInfoByParams(Map<String,Object> params);
    /**
     *  按条件查询人员信息总数
     * @return
     */
    int queryStaffInfoCount(Map<String,Object> params);
    int qryStaffByOrgaIdCount(Map<String,Object> params);


    /*空闲的用户和角色下关联的用户*/
    List<TSamStaffInfo> selectStaffFreeRole(Map<String,Object> params);

    List<TSamStaffInfo> selectStaffIncludeRelatedGroup(Map<String,Object> params);



    int selectStaffFreeRoleCount(Map<String,Object> params);

    List<TSamStaffInfo> selectStaffIncludeRole(String roleId);

    /*空闲的用户和工作组下关联的用户*/
    List<TSamStaffInfo> selectStaffFreeGroup(Map<String,Object> params);
     int selectStaffFreeGroupCount(Map<String,Object> params);

    List<TSamStaffInfo> selectStaffIncludeGroup(Map<String,Object> map);


    List<TSamStaffInfo> selectStaffIncludeGroupForPic(String groupId);

    /*角色关联的用户*/
    List<TSamStaffInfo> selectStaffForRoleId(String roleId);

    /**
     * 组织机构下的人员
     * @param orgaId
     * @return
     */
    List<TSamStaffInfo> selectStaffForOrgaId(String orgaId);

}
