package com.ai.sam.web;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.microserv.skeleton.facade.BaseAction;
import org.apache.microserv.skeleton.facade.AiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ai.sam.service.TSamStaffRoleService;
import com.ai.sam.domain.TSamStaffRole;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 角色用户关联关系
 */
@AiController(api = "tsamstaffrole",urlpaths="/tsamstaffrole")
public class  TSamStaffRoleController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamStaffRoleController.class);

	@Autowired
	private TSamStaffRoleService tsamstaffroleservice;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody TSamStaffRole getById(@PathVariable("id") Integer id) throws Exception {

		logger.info("");
		logger.error("");
		return tsamstaffroleservice.getById(id);

	}


	//批量增加
	@RequestMapping(value = "addStaffRoleBatch", method = RequestMethod.POST)
	public Map<String, Object> selectTSamRoleGrid (@RequestBody List<TSamStaffRole> staffList)  {
		tsamstaffroleservice.deleteByRoleId(staffList.get(0).getRoleId());
		return tsamstaffroleservice.addStaffRoleBatch(staffList);
	}


	/**
	 * 为人员配置工作组
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateStaffRole")
	public Map<String, Object> updateStaffRole(HttpServletRequest request) {
		String staffId = request.getParameter("staffId");//一个实体
		String roleIds = request.getParameter("roleIds");//多个权限，逗号分隔
		Map<String,Object> result = new HashMap<>();
		try {
			result = tsamstaffroleservice.updateStaffRole(staffId,roleIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



}