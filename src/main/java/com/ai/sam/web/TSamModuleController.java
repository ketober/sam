package com.ai.sam.web;
import com.ai.sam.utils.ServiceConstant;
import org.apache.microserv.skeleton.facade.BaseAction;
import org.apache.microserv.skeleton.facade.AiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ai.sam.service.TSamModuleService;
import com.ai.sam.domain.TSamModule;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Module")
public class  TSamModuleController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamModuleController.class);

	@Autowired
	private TSamModuleService tsammoduleservice;

	@ResponseBody
	@RequestMapping("/qryModule")
	public Map<String, Object> qryModule(HttpServletRequest request){
		List<TSamModule> modules = tsammoduleservice.qryModule();
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