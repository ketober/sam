package com.ai.sam.web;

import com.ai.sam.common.StaticValue;
import com.ai.sam.domain.TSamAuthElement;
import com.ai.sam.domain.TSamCommonconfiginfo;
import com.ai.sam.domain.TSamDataauthconfig;
import com.ai.sam.domain.TSamUserdataauth;
import com.ai.sam.service.TSamAuthElementService;
import com.ai.sam.service.TSamDataAuthElementService;
import com.ai.sam.service.TSamPermitService;
import com.ai.sam.utils.ServiceConstant;
import com.alibaba.fastjson.JSON;
import org.apache.microserv.skeleton.facade.BaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/dataAuth")
public class TSamDataAuthElementController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamDataAuthElementController.class);

	@Autowired
    private TSamDataAuthElementService tSamDataAuthElementService;
	//树节点
	@ResponseBody
	@RequestMapping("/dataAuthTree")
	public String qryChild(HttpServletRequest request){
           return tSamDataAuthElementService.getDataAuthTree("001");
	}
	//新增权限树节点
	@ResponseBody
	@RequestMapping("/addAuthTreeNode")
	public Map<String, Object> addAuthTreeNode(TSamDataauthconfig tSamDataauthconfig){
        Map<String, Object> result = new HashMap<String, Object>();
        //检查权限ID是否重复
        TSamDataauthconfig config = new TSamDataauthconfig();
        config.setAuthtypeid(tSamDataauthconfig.getAuthtypeid());
        if(!tSamDataAuthElementService.selectDataAuth(config).isEmpty())
        {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,"数据权限ID重复");
            return result;
        }
        config.setAuthtypeid(null);
        config.setAuthtypename(tSamDataauthconfig.getAuthtypename());
        if(!tSamDataAuthElementService.selectDataAuth(config).isEmpty())
        {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,"数据名称重复");
            return result;
        }
        try {
            tSamDataAuthElementService.insertIntoDataConfig(tSamDataauthconfig);
            result.put(StaticValue.RESULT_VAL, StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG, StaticValue.RESULT_SUCCESS_MSG);

        } catch (Exception e) {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_FAIL_MSG);
            e.printStackTrace();
        }
        return result;
    }
    //新增权限数据
    @ResponseBody
    @RequestMapping(value="/addAuthData",method = RequestMethod.POST)
    public Map<String, Object> addAuthData( @RequestBody TSamCommonconfiginfo tSamCommonconfiginfo){
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            //查重
            if(tSamDataAuthElementService.checkRepeatRecordCommmonConfig(tSamCommonconfiginfo))
            {
                result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
                result.put(StaticValue.RESULT_MSG,"存在重复数据");
                return result;
            }
            tSamDataAuthElementService.addAuthData(tSamCommonconfiginfo);
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        } catch (Exception e) {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_FAIL_MSG);
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/commonAuthDataInfo")
    public String commonAuthDataInfo(TSamCommonconfiginfo tSamCommonconfiginfo)
    {
        return tSamDataAuthElementService.getCommonAuthConfigInfoById(tSamCommonconfiginfo);
    }
@ResponseBody
    @RequestMapping("/deleteCommonAuthData")
    public Map<String, Object> deleteCommonAuthData(TSamCommonconfiginfo tSamCommonconfiginfo)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            tSamDataAuthElementService.deleteCommonAuthData(tSamCommonconfiginfo);
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        } catch (Exception e) {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_FAIL_MSG);
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/assignDataAuth")
    public Map<String, Object> assignDataAuth(@RequestBody TSamUserdataauth tSamUserdataauth){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            tSamDataAuthElementService.assignDataAuth(tSamUserdataauth);
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        } catch (Exception e) {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_FAIL_MSG);
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/queryDataAuthAssignedStaff")
    public Map<String, Object> queryDataAuthAssignedStaff(HttpServletRequest request){
        Map<String, Object> result = new HashMap<String, Object>();
        String start = request.getParameter("start");
        String pageNum = request.getParameter("pageNum");
        String authobjectid = request.getParameter("authobjectid");
        String authtypeid = request.getParameter("authtypeid");
        Map<String,Object> param = new HashMap<>();
        param.put("start",start);
        param.put("pageNum",pageNum);
        param.put("authobjectid",authobjectid);
        param.put("authtypeid",authtypeid);
        try {
            List<Map<String,Object>> list = tSamDataAuthElementService.selectDataAuthAssignedStaff(param);
            int count = tSamDataAuthElementService.selectDataAuthAssignedStaffCount(param);
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
            result.put("list",list);
            result.put("total",count);
        } catch (Exception e) {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_FAIL_MSG);
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/deleteDataAuthTree")
    public Map<String, Object> deleteDataAuthTree(HttpServletRequest request){
	    String authTypeId = request.getParameter("authtypeid");
        Map<String, Object> result = new HashMap<String, Object>();
	    //检查是否存在子节点
        if(tSamDataAuthElementService.checkAuthExistChild(authTypeId))
        {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,"权限节点存在子节点不可删除！");
        }
        try {
            tSamDataAuthElementService.deleteDataAuthTree(authTypeId);
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        } catch (Exception e) {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_FAIL_MSG);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "selectTSamRoleTree")
    @ResponseBody
    public String selectTSamRoleTr (HttpServletRequest request){
        String sync = request.getParameter("sync");
        //String moduleId = request.getParameter("moduleId");
        String authId = request.getParameter("authId");
        return tSamDataAuthElementService.selectTSamRoleDataAuthTree(sync,authId);
    }
    //更新角色数据权限
    @RequestMapping(value = "updateRoleDataAuth")
    @ResponseBody
    public Map<String, Object> updateRoleDataAuth (HttpServletRequest request){
        String commonid = request.getParameter("commonid");
        String authtypeid = request.getParameter("authtypeid");
        String roleIds = request.getParameter("roleIds");
        Map<String, Object> result = new HashMap<String, Object>();
        try
        {
            tSamDataAuthElementService.updateRoleDataAuthService(commonid,authtypeid,roleIds);
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        }catch (Exception e)
        {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_FAIL_MSG);
        }
        return result;
    }

}