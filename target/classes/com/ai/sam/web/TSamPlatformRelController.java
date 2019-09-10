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
import com.ai.sam.service.TSamPlatformRelService;
import com.ai.sam.domain.TSamPlatformRel;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AiController(api = "tsamplatformrel",urlpaths="/tsamplatformrel")
public class  TSamPlatformRelController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamPlatformRelController.class);

	@Autowired
	private TSamPlatformRelService tsamplatformrelservice;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody TSamPlatformRel getById(@PathVariable("id") Integer id) throws Exception {

		logger.info("");
		logger.error("");
		return tsamplatformrelservice.getById(id);

	}

	@ResponseBody
	@RequestMapping("/qryStaffPlatform")
	public Map<String, Object> qryStaffPlatform(HttpServletRequest request) {
		String staffId = request.getParameter("staffId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffId",staffId);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			//
			List<Map<String,Object>> list = tsamplatformrelservice.qryStaffPlatform(params);
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
	@RequestMapping("/qryNoDistriPlatform")
	public Map<String, Object> qryNoDistriPlatform(HttpServletRequest request) {
		String platformId = request.getParameter("platformId");
		String accountName = request.getParameter("accountName");
		String platIdMin = request.getParameter("platIdMin");
		String platIdMax = request.getParameter("platIdMax");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("platformId",platformId);
		params.put("accountName",accountName);
		params.put("platIdMin",platIdMin);
		params.put("platIdMax",platIdMax);
		if (!StringUtils.isEmpty(request.getParameter("startIndex"))){
			int startIndex = Integer.parseInt(request.getParameter("startIndex"));
			params.put("start",startIndex-1);
		}
		if (!StringUtils.isEmpty(request.getParameter("pageNum"))){
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			params.put("limit",pageNum);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			//
			List<Map<String,Object>> list = tsamplatformrelservice.qryNoDistriPlatform(params);
			//查询
			int total = tsamplatformrelservice.qryNoDistriPlatformCount(params);
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
	@RequestMapping("/updateStaffPlatform")
	public Map<String, Object> updateStaffPlatform(HttpServletRequest request) {
		String platformId = request.getParameter("platformId");
		String staffId = request.getParameter("staffId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("platformId",platformId);
		params.put("staffId",staffId);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result= tsamplatformrelservice.updateStaffPlatform(params);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;
	}

}