package com.ai.sam.web;
import com.ai.sam.common.StaticValue;
import com.ai.sam.domain.TSamStaffInfo;
import com.alibaba.fastjson.JSON;
import org.apache.microserv.skeleton.facade.BaseAction;
import org.apache.microserv.skeleton.facade.AiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ai.sam.service.TSamServiceSystemService;
import com.ai.sam.domain.TSamServiceSystem;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@AiController(api = "tsamservicesystem",urlpaths="/tsamservicesystem")
public class  TSamServiceSystemController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamServiceSystemController.class);

	@Autowired
	private TSamServiceSystemService tsamservicesystemservice;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody TSamServiceSystem getById(@PathVariable("id") Integer id) throws Exception {

		logger.info("");
		logger.error("");
		return tsamservicesystemservice.getById(id);

	}

	@ResponseBody
	@RequestMapping("/qryServiceSystem")
	public Map<String, Object> qryServiceSystem(HttpServletRequest request) {
		String serviceId = request.getParameter("serviceId");
		String serviceName = request.getParameter("serviceName");
		String state = request.getParameter("state");
		String clientId = request.getParameter("clientId");
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(request.getParameter("startIndex"))){
			int startIndex = Integer.parseInt(request.getParameter("startIndex"));
			params.put("start",startIndex-1);
		}
		if (!StringUtils.isEmpty(request.getParameter("pageNum"))){
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			params.put("limit",pageNum);
		}
		params.put("serviceId",serviceId);
		params.put("serviceName",serviceName);
		params.put("state",state);
		params.put("clientId",clientId);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> list = tsamservicesystemservice.getServiceSystem(params);
			int total = tsamservicesystemservice.getServiceSystemCount(params);
			//查询
			result.put("list",list);
			result.put("total",total);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/qryServiceSystemRest")
	public Map<String, Object> qryServiceSystemRest(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> list = tsamservicesystemservice.getServiceSystem(params);
			//查询
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
			result.put(StaticValue.RESULT_MSG,list);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/updServiceSystem")
	public Map<String, Object> updServiceSystem(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String serviceName = request.getParameter("serviceName");
		String serviceId = request.getParameter("serviceId");
		String state = request.getParameter("state");
		String clientId = request.getParameter("clientId");
		String serviceDesc = request.getParameter("serviceDesc");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("serviceName",serviceName);
//		try {
//			List<Map<String,Object>> list = tsamservicesystemservice.getServiceSystem2(params);
//			if (!list.isEmpty()){
//				for (int i = 0; i < list.size(); i++) {
//					if (list.get(i).get("serviceId").equals(serviceId)){
//						result.put(StaticValue.RESULT_VAL, StaticValue.RESULT_FAIL_VAL);
//						result.put(StaticValue.RESULT_MSG, "已存在相同系统ID相同系统名称的数据！");
//						return result;
//					}
//				}
//			}
//		} catch (Exception e) {
//			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
//			result.put(StaticValue.RESULT_MSG,e.getMessage());
//			return result;
//		}
		params.put("serviceId",serviceId);
		params.put("state",state);
		params.put("clientId",clientId);
		params.put("serviceDesc",serviceDesc);
		try {
			result = tsamservicesystemservice.updServiceSystem(params);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/createServiceSystem")
	public Map<String, Object> createServiceSystem(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String serviceId = request.getParameter("serviceId");
		String serviceName = request.getParameter("serviceName");
		String state = request.getParameter("state");
		String clientId = request.getParameter("clientId");
		String serviceDesc = request.getParameter("serviceDesc");
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> params1 = new HashMap<String, Object>();
		params.put("serviceId",serviceId);
		params1.put("serviceName",serviceName);
		try {
			List<Map<String,Object>> list = tsamservicesystemservice.getServiceSystem2(params);
			List<Map<String,Object>> list1 = tsamservicesystemservice.getServiceSystem2(params1);
			if (!list.isEmpty()){
				result.put(StaticValue.RESULT_VAL, StaticValue.RESULT_FAIL_VAL);
				result.put(StaticValue.RESULT_MSG, "已存在相同系统ID！");
				return result;
			}
			if (!list1.isEmpty()){
				result.put(StaticValue.RESULT_VAL, StaticValue.RESULT_FAIL_VAL);
				result.put(StaticValue.RESULT_MSG, "已存在相同系统名称！");
				return result;
			}
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
			return result;
		}
		params.put("serviceName",serviceName);
		params.put("state",state);
		params.put("clientId",clientId);
		params.put("serviceDesc",serviceDesc);
		try {
			result = tsamservicesystemservice.createServiceSystem(params);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/delServiceSystem")
	public Map<String, Object> delServiceSystem(HttpServletRequest request) {

		String ids_str = request.getParameter("ids");
		Map<String, Object> result = new HashMap<String, Object>();
		if (ids_str != null) {
			String[] s = ids_str.split(",");
			List<String> ids = new ArrayList<String>();
			Collections.addAll(ids, s);
			try {
				tsamservicesystemservice.delServiceSystem(ids);
				result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
				result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
			} catch (Exception e) {
				result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
				result.put(StaticValue.RESULT_MSG,e.getMessage());
			}
		}
		return result;
	}



}