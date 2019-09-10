package com.ai.sam.web;
import com.ai.sam.common.StaticValue;
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
import com.ai.sam.service.TSamOauthClientService;
import com.ai.sam.domain.TSamOauthClient;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@AiController(api = "tsamoauthclient",urlpaths="/tsamoauthclient")
public class  TSamOauthClientController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamOauthClientController.class);

	@Autowired
	private TSamOauthClientService tsamoauthclientservice;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody TSamOauthClient getById(@PathVariable("id") Integer id) throws Exception {

		logger.info("");
		logger.error("");
		return tsamoauthclientservice.getById(id);

	}

	@ResponseBody
	@RequestMapping("/qryOauthClient")
	public Map<String, Object> qryOauthClient(HttpServletRequest request) {
		String clientId = request.getParameter("clientId");
		String clientName = request.getParameter("clientName");
		String state = request.getParameter("state");
		String redirectUri = request.getParameter("redirectUri");
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(request.getParameter("startIndex"))){
			int startIndex = Integer.parseInt(request.getParameter("startIndex"));
			params.put("start",startIndex-1);
		}
		if (!StringUtils.isEmpty(request.getParameter("pageNum"))){
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			params.put("limit",pageNum);
		}
		params.put("clientId",clientId);
		params.put("clientName",clientName);
		params.put("state",state);
		params.put("redirectUri",redirectUri);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> list = tsamoauthclientservice.getOauthClient(params);
			int total = tsamoauthclientservice.getOauthClientCount(params);
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
	@RequestMapping("/updOauthClient")
	public Map<String, Object> updOauthClient(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String clientName = request.getParameter("clientName");
		String clientId = request.getParameter("clientId");
		String state = request.getParameter("state");
		String redirectUri = request.getParameter("redirectUri");
		String clientDesc = request.getParameter("clientDesc");
		String accessTokenValidity = request.getParameter("accessTokenValidity");
		String refreshTokenValidity = request.getParameter("refreshTokenValidity");
		String grantTypes = request.getParameter("grantTypes");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clientName",clientName);
//		try {
//			List<Map<String,Object>> list = tsamservicesystemservice.getServiceSystem2(params);
//			if (!list.isEmpty()){
//				for (int i = 0; i < list.size(); i++) {
//					if (list.get(i).get("clientId").equals(clientId)){
//						result.put(StaticValue.RESULT_VAL, StaticValue.RESULT_FAIL_VAL);
//						result.put(StaticValue.RESULT_MSG, "已存在相同客户端ID相同客户端名称的数据！");
//						return result;
//					}
//				}
//			}
//		} catch (Exception e) {
//			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
//			result.put(StaticValue.RESULT_MSG,e.getMessage());
//			return result;
//		}
		params.put("clientId",clientId);
		params.put("state",state);
		params.put("redirectUri",redirectUri);
		params.put("clientDesc",clientDesc);
		params.put("accessTokenValidity",accessTokenValidity);
		params.put("refreshTokenValidity",refreshTokenValidity);
		params.put("grantTypes",grantTypes);
		try {
			result = tsamoauthclientservice.updOauthClient(params);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/createOauthClient")
	public Map<String, Object> createOauthClient(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String clientId = request.getParameter("clientId");
		String clientName = request.getParameter("clientName");
		String state = request.getParameter("state");
		String redirectUri = request.getParameter("redirectUri");
		String accessTokenValidity = request.getParameter("accessTokenValidity");
		String refreshTokenValidity = request.getParameter("refreshTokenValidity");
		String grantTypes = request.getParameter("grantTypes");
		String clientDesc = request.getParameter("clientDesc");
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> params1 = new HashMap<String, Object>();
		params.put("clientId",clientId);
		params1.put("clientName",clientName);
		try {
			List<Map<String,Object>> list = tsamoauthclientservice.getOauthClient2(params);
			if (!list.isEmpty()){
				result.put(StaticValue.RESULT_VAL, StaticValue.RESULT_FAIL_VAL);
				result.put(StaticValue.RESULT_MSG, "已存在相同客户端ID！");
				return result;
			}
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
			return result;
		}
		params.put("clientName",clientName);
		params.put("state",state);
		params.put("redirectUri",redirectUri);
		params.put("clientDesc",clientDesc);
		params.put("accessTokenValidity",accessTokenValidity);
		params.put("refreshTokenValidity",refreshTokenValidity);
		params.put("grantTypes",grantTypes);
		//TODO 密码目前写死的
		params.put("clientSecret","$2a$10$OukB5hpwXoZKlkJGDR.LueGI3KmMzz5gyxUa76u1r1Jw0BuGdYBnS");
		try {
			result = tsamoauthclientservice.createOauthClient(params);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/delOauthClient")
	public Map<String, Object> delOauthClient(HttpServletRequest request) {

		String ids_str = request.getParameter("ids");
		Map<String, Object> result = new HashMap<String, Object>();
		if (ids_str != null) {
			String[] s = ids_str.split(",");
			List<String> ids = new ArrayList<String>();
			Collections.addAll(ids, s);
			try {
				tsamoauthclientservice.delOauthClient(ids);
				result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
				result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
			} catch (Exception e) {
				result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
				result.put(StaticValue.RESULT_MSG,e.getMessage());
			}
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/checkOauthClient")
	public Map<String, Object> checkOauthClient(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String clientId = request.getParameter("clientId");
		String clientSecret = request.getParameter("clientSecret");
		//参数校验
		if (StringUtils.isEmpty(clientId)){
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,"clientId不能为空");
			return result;
		}
		if (StringUtils.isEmpty(clientSecret)){
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,"clientSecret不能为空");
			return result;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clientId",clientId);
		params.put("clientSecret",clientSecret);
		try {
			result = tsamoauthclientservice.checkOauthClient(params);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/qryClientInfoById")
	public Map<String, Object> qryClientInfoById(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String clientId = request.getParameter("clientId");
		//参数校验
		if (StringUtils.isEmpty(clientId)){
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,"clientId不能为空");
			return  result;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clientId",clientId);
		try {
			Map<String,Object> list = tsamoauthclientservice.getClientInfoById(params);
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
			result.put(StaticValue.RESULT_MSG,list);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;
	}



}