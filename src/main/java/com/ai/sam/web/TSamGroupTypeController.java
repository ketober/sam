package com.ai.sam.web;
import com.ai.sam.domain.TSamGroupTypeExample;
import com.ai.sam.domain.TSamModule;
import com.ai.sam.utils.ServiceConstant;
import org.apache.microserv.skeleton.facade.BaseAction;
import org.apache.microserv.skeleton.facade.AiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ai.sam.service.TSamGroupTypeService;
import com.ai.sam.domain.TSamGroupType;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AiController(api = "tsamgrouptype",urlpaths="/tsamgrouptype")
public class  TSamGroupTypeController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamGroupTypeController.class);

	@Autowired
	private TSamGroupTypeService tsamgrouptypeservice;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody TSamGroupType getById(@PathVariable("id") Integer id) throws Exception {

		logger.info("");
		logger.error("");
		return tsamgrouptypeservice.getById(id);

	}



	@ResponseBody
	@RequestMapping(value = "/queryGroupType",produces = "application/json", method = RequestMethod.GET)
	public Map<String, Object> queryGroupType(){
		TSamGroupTypeExample example = new TSamGroupTypeExample();
		List<TSamGroupType> modules = tsamgrouptypeservice.selectByExample(example);
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			result.put("result", ServiceConstant.STATUS_SUCCESS);
			result.put("msg", ServiceConstant.MSG_SUCCESS);
			result.put("modules",modules);
		}catch(Exception e) {
			result.put("result", ServiceConstant.STATUS_SYSERROR);
			result.put("msg", e.toString());
		}
		return result;
	}



}