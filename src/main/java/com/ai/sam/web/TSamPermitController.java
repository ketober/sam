package com.ai.sam.web;
import com.ai.sam.common.StaticValue;
import org.apache.microserv.skeleton.facade.BaseAction;
import org.apache.microserv.skeleton.facade.AiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.ai.sam.service.TSamPermitService;
import com.ai.sam.domain.TSamPermit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AiController(api = "tsampermit",urlpaths="/tsampermit")
public class  TSamPermitController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamPermitController.class);

	@Autowired
	private TSamPermitService tsampermitservice;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody TSamPermit getById(@PathVariable("id") Integer id) throws Exception {

		logger.info("");
		logger.error("");
		return tsampermitservice.getById(id);

	}

	/**
	 * 查询实体信息By AuthId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryEntityByAuthId")
	public Map<String, Object> queryEntityByAuthId(HttpServletRequest request) {
		Map<String,Object> params = new HashMap<>();
        String authId = request.getParameter("authId");
		String moduleId = request.getParameter("moduleId");//模块Id
		String permitType = request.getParameter("permitType");//实体类型1人员2角色
		if (!StringUtils.isEmpty(request.getParameter("startIndex"))){
			int startIndex = Integer.parseInt(request.getParameter("startIndex"));
			params.put("start",startIndex);
		}
		if (!StringUtils.isEmpty(request.getParameter("pageNum"))){
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			params.put("limit",pageNum);
		}
		params.put("moduleId",moduleId);
		params.put("authId", authId);
		params.put("permitType", permitType);
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> list=new ArrayList<>();
			if (permitType.equals(StaticValue.PERMIT_TYPE_STAFF)){
				 list = tsampermitservice.selectStaffByAuthId(params);
			}else if(permitType.equals(StaticValue.PERMIT_TYPE_ROLE)){
				list = tsampermitservice.selectRoleByAuthId(params);
			}else{
				result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
				result.put(StaticValue.RESULT_MSG,"赋权类型错误");
			}
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
			result.put(StaticValue.RESULT_MSG,list);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;
	}
	/**
	 * 查询权限信息By EntityId
	 * @param request
	 * @return
	 */

	@ResponseBody
	@RequestMapping("/queryAuthByEntityId")
	public Map<String, Object> queryAuthByEntityId(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> params = new HashMap<>();
		Map<String,Object> result = new HashMap<>();
        String entityId = request.getParameter("entityId");
        if (StringUtils.isEmpty(entityId)){
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,"entityId不能为空！");
			return result;
		}
		String permitType = request.getParameter("permitType");//实体类型1人员2角色
		if (StringUtils.isEmpty(permitType)){
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,"permitType不能为空！");
			return result;
		}
		String keyWords=request.getParameter("keyWords");
		String level=request.getParameter("level");
		params.put("keyWords", keyWords);
		params.put("level", level);
		params.put("entityId", entityId);
		params.put("permitType", permitType);
		try {
			List<Map<String,Object>> list = tsampermitservice.selectAuthByEntityId(params);
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
			result.put(StaticValue.RESULT_MSG,list);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;
	}


	/**
	 * 实体权限配置接口(为一个实体赋多个权限)
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateEntityPermitAuth", method = RequestMethod.POST)
	public Map<String, Object> updateEntityPermitAuth(@RequestBody List<TSamPermit> tSamPermitList) {
		Map<String,Object> result = null;
		try {
			result = tsampermitservice.updateEntityPermitAuth(tSamPermitList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * 实体权限配置接口(为一个实体赋多个权限)
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateEntityPermitAuth1")
	public Map<String, Object> updateEntityPermitAuth1(HttpServletRequest request) {
		String entityId = request.getParameter("entityId");//一个实体
		String moduleId = request.getParameter("moduleId");//模块Id
		String authIds = request.getParameter("authIds");//多个权限，逗号分隔
		String permitType = request.getParameter("permitType");//实体类型1人员2角色
		Map<String,Object> result = null;
		try {
			result = tsampermitservice.updateEntityPermitAuth(entityId,moduleId,permitType,authIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 功能权限分配人员（角色）接口
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateAuthPermitEntity")
	public Map<String, Object> updateAuthPermitEntity(HttpServletRequest request) {
		String moduleId = request.getParameter("moduleId");//模块Id
		String permitType = request.getParameter("permitType");//实体类型1人员2角色
		String subAuthIds = request.getParameter("subAuthIds");//权限及子节点集合
		String parentAuthIds = request.getParameter("parentAuthIds");//权限及父节点集合
		String addEntityIds = request.getParameter("addEntityIds");//要新增的实体集合
		String delEntityIds = request.getParameter("delEntityIds");//要删除的实体集合
		String opStaffId = request.getParameter("opStaffId");//操作人员
		Map<String,Object> result = null;
		try {
			result = tsampermitservice.updateAuthPermitEntity(moduleId,permitType,subAuthIds,parentAuthIds,addEntityIds,delEntityIds,opStaffId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 批量删除权限映射 by  AuthIds
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteByAuth")
	public Map<String, Object> deleteByAuth(HttpServletRequest request) {
		String authIds = request.getParameter("authIds");//一个权限
		Map<String,Object> result = null;
		try {
			result = tsampermitservice.deleteByAuth(authIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}