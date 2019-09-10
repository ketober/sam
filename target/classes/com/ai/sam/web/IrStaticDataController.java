package com.ai.sam.web;
import org.apache.commons.lang.StringUtils;
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
import com.ai.sam.service.IrStaticDataService;
import com.ai.sam.domain.IrStaticData;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/StaticValueConfig")
public class  IrStaticDataController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( IrStaticDataController.class);

	@Autowired
	private IrStaticDataService irstaticdataservice;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody IrStaticData getById(@PathVariable("id") Integer id) throws Exception {

		logger.info("");
		logger.error("");
		return irstaticdataservice.getById(id);

	}

	/**
	 * 查询静态数据
	 */
	@ResponseBody
	@RequestMapping("/queryStaticValue")
	public Map<String, Object> queryStaticValue(HttpServletRequest request) {
		String codeType = request.getParameter("codeType");
		Map<String, Object> response = new HashMap<String, Object>();
		if (StringUtils.isBlank(codeType) || StringUtils.equals(codeType, "null")) {
			response.put("result", "fail");
			response.put("message", "parameter is not legal");
			return response;
		}
		try {
			List<IrStaticData> result = irstaticdataservice.retrieveStaticData(codeType);
			response.put("result", "success");
			response.put("message", result);
		} catch (Exception e) {
			response.put("result", "fail");
			response.put("message", e.getMessage());
		}
		return response;
	}

	/**
	 * 查询静态数据
	 */
	@ResponseBody
	@RequestMapping("/getParamValues")
	public Map<String, Object> getParamValues(HttpServletRequest request) {
		String code = request.getParameter("code");
		Map<String, Object> response = new HashMap<String, Object>();
		if (StringUtils.isBlank(code) || StringUtils.equals(code, "null")) {
			response.put("result", "fail");
			response.put("message", "parameter is not legal");
			return response;
		}
		try {
			List<IrStaticData> result = irstaticdataservice.retrieveStaticData(code);

			List<Map<String, Object>> array = new ArrayList<Map<String,Object>>();
			for (IrStaticData item : result) {
				Map<String, Object> object = new HashMap<String,Object>();
				object.put("value", item.getCodeValue());
				object.put("displayValue", item.getCodeName());
				array.add(object);
			}

			response.put("beans", array);
			response.put("retCode", "200");
			response.put("retMessage", "");

		} catch (Exception e) {
			response.put("beans", "");
			response.put("retCode", "500");
			response.put("retMessage", e.getMessage());
		}
		return response;
	}




}