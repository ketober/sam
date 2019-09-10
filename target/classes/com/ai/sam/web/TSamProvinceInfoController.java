package com.ai.sam.web;
import org.apache.microserv.skeleton.facade.BaseAction;
import org.apache.microserv.skeleton.facade.AiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ai.sam.service.TSamProvinceInfoService;
import com.ai.sam.domain.TSamProvinceInfo;



@AiController(api = "tsamprovinceinfo",urlpaths="/tsamprovinceinfo")
public class  TSamProvinceInfoController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamProvinceInfoController.class);

	@Autowired
	private TSamProvinceInfoService tsamprovinceinfoservice;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody TSamProvinceInfo getById(@PathVariable("id") Integer id) throws Exception {

		logger.info("");
		logger.error("");
		return tsamprovinceinfoservice.getById(id);

	}



}