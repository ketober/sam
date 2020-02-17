package com.ai.sam.service;

import com.ai.sam.domain.TSamCommonconfiginfo;
import com.ai.sam.domain.TSamDataauthconfig;
import com.ai.sam.domain.TSamUserdataauth;

import java.util.List;
import java.util.Map;

public interface TSamDataAuthElementService {

   String getDataAuthTree(String id);

   int insertIntoDataConfig(TSamDataauthconfig tSamDataauthconfig) throws Exception;
    List<Map<String, Object>> querytDataAuth(List<TSamDataauthconfig> list, String parentId);

    int addAuthData(TSamCommonconfiginfo tSamCommonconfiginfo) throws Exception;
    String getCommonAuthConfigInfoById(TSamCommonconfiginfo tSamCommonconfiginfo);

    int assignDataAuth(TSamUserdataauth tSamUserdataauth) throws Exception;

    List<Map<String,Object>> selectDataAuthAssignedStaff(Map<String,Object> param) throws Exception;

    void deleteDataAuthTree(String authTypeId) throws Exception;

    List<TSamDataauthconfig> selectDataAuth(TSamDataauthconfig tSamDataauthconfig);

    boolean checkAuthExistChild(String authTypeId);

    boolean checkRepeatRecordCommmonConfig(TSamCommonconfiginfo tSamCommonconfiginfo) throws Exception;

    void deleteCommonAuthData(TSamCommonconfiginfo tSamCommonconfiginfo) throws Exception;

    //用户是否拥有某个数据权限
    boolean checkUserDataAuth(String StaffId,String AuthId);
    //角色是否拥有某个权限
    boolean checkRoleDataAuth(String StaffId, String AuthId) ;
    String selectTSamRoleDataAuthTree(String sync, String authId);
    void updateRoleDataAuthService(String commonid,String authtypeid,String roleIds);
    int selectDataAuthAssignedStaffCount(Map<String,Object> param);
}

