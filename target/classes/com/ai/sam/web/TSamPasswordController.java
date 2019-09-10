package com.ai.sam.web;
import com.ai.sam.common.StaticValue;
import com.ai.sam.utils.BCryptUtil;
import org.apache.microserv.skeleton.facade.BaseAction;
import org.apache.microserv.skeleton.facade.AiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ai.sam.service.TSamPasswordService;
import com.ai.sam.domain.TSamPassword;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@AiController(api = "tsampassword",urlpaths="/tsampassword")
public class  TSamPasswordController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamPasswordController.class);

	@Autowired
	private TSamPasswordService tsampasswordservice;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody TSamPassword getById(@PathVariable("id") Integer id) throws Exception {

		logger.info("");
		logger.error("");
		return tsampasswordservice.getById(id);

	}

	@ResponseBody
	@RequestMapping("/qryPassWord")
	public Map<String, Object> qryPassWordById(HttpServletRequest request) {
		String staffId = request.getParameter("staffId");
		Map<String, Object> result = new HashMap<String, Object>();
		//参数校验
		if (StringUtils.isEmpty(staffId)){
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,"工号不能为空");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffId",staffId);
		try {
			Map<String, Object> map = tsampasswordservice.qryPassWordById(params);
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
			result.put(StaticValue.RESULT_MSG,map);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/checkPassWord")
	public Map<String, Object> checkPassWord(HttpServletRequest request) {
		String staffId = request.getParameter("staffId");
		String passWord = request.getParameter("passWord");
		Map<String, Object> result = new HashMap<String, Object>();
		//参数校验
		if (StringUtils.isEmpty(staffId)){
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,"工号不能为空");
			return result;
		}
		if (StringUtils.isEmpty(passWord)){
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,"密码不能为空");
			return result;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffId",staffId);
		params.put("passWord",passWord);
		try {
			result = tsampasswordservice.checkPassWord(params);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
			return result;
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/resetPassWord")
	public Map<String, Object> resetPassWord(HttpServletRequest request) {
		String ids_str = request.getParameter("ids");
		Map<String, Object> result = new HashMap<String, Object>();
		if (ids_str != null) {
			String[] s = ids_str.split(",");
			List<String> ids = new ArrayList<String>();
			Collections.addAll(ids, s);
			try {
				tsampasswordservice.resetPassWord(ids);
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
	@RequestMapping("/updatePassWord")
	public Map<String, Object> updatePassWord(HttpServletRequest request) {
		String staffId = request.getParameter("staffId");
		String orgiPassword = request.getParameter("orgiPassword");
		String newPassword = request.getParameter("newPassword");
		Map<String, Object> result = new HashMap<String, Object>();
		//参数校验
		if (StringUtils.isEmpty(staffId)){
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,"工号不能为空");
			return result;
		}
		if (StringUtils.isEmpty(orgiPassword)){
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,"原始密码不能为空");
			return result;
		}
		if (StringUtils.isEmpty(newPassword)){
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,"新密码不能为空");
			return result;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffId",staffId);
		try {
		//查询旧密码
		Map<String, Object> map = tsampasswordservice.qryPassWordById(params);
		String orgiPassworBCY = (String) map.get("passWord");
		boolean a =BCryptUtil.match(orgiPassword,orgiPassworBCY);
		if (!a){
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,"初始密码错误");
			return result;
		}
		params.put("password",newPassword);
		result = tsampasswordservice.updatePassWord(params);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
			return result;
		}
		return result;
	}


}