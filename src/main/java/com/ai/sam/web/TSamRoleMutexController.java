package com.ai.sam.web;
import com.ai.sam.common.StaticValue;
import org.apache.microserv.skeleton.facade.BaseAction;
import org.apache.microserv.skeleton.facade.AiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ai.sam.service.TSamRoleMutexService;
import com.ai.sam.domain.TSamRoleMutex;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AiController(api = "tsamrolemutex",urlpaths="/tsamrolemutex")
public class  TSamRoleMutexController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamRoleMutexController.class);

	@Autowired
	private TSamRoleMutexService tsamrolemutexservice;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody TSamRoleMutex getById(@PathVariable("id") Integer id) throws Exception {

		logger.info("");
		logger.error("");
		return tsamrolemutexservice.getById(id);

	}

	@RequestMapping(value = "/selecMutexByRoleId", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> selecMutexByRoleId (HttpServletRequest request) throws Exception {
		String roleId = request.getParameter("roleId");
		Map<String,Object> params = new HashMap<>();
		params.put("roleId",roleId);
		Map<String,Object> result = new HashMap<>();
		try {
			List<String> list = tsamrolemutexservice.selecMutexByRoleId(params);
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
			result.put(StaticValue.RESULT_MSG,list);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;


	}



}