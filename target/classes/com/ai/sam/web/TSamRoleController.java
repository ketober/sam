package com.ai.sam.web;
import com.ai.sam.common.StaticValue;
import com.ai.sam.domain.TSamOrgaInfo;
import com.ai.sam.domain.TSamStaffRole;
import com.ai.sam.service.TSamStaffRoleService;
import com.ai.sam.utils.SequenceUtils;
import com.alibaba.fastjson.JSON;
import org.apache.microserv.skeleton.facade.BaseAction;
import org.apache.microserv.skeleton.facade.AiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ai.sam.service.TSamRoleService;
import com.ai.sam.domain.TSamRole;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * 角色维护
 */
@AiController(api = "tsamrole",urlpaths="/tsamrole")
public class  TSamRoleController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamRoleController.class);
	@Autowired
	private TSamRoleService tsamroleservice;
	@Autowired
	private SequenceUtils sequenceUtils;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody TSamRole getById(@PathVariable("id") String id) throws Exception {
		logger.info("");
		logger.error("");
		return tsamroleservice.getById(id);
	}


	/**
	 *  查询子集
	 * @param roleId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/selectSamRoleCountBySuperCode/{roleId}", method = RequestMethod.GET)
	public Map<String, Object> selectSamRoleCountBySuperCode(@PathVariable("roleId") String roleId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			TSamRole tSamRole = new TSamRole();
			tSamRole.setRoleCode(roleId);
			int count =tsamroleservice.selectByTSamRoleCountBySuperCode(tSamRole);
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
			result.put(StaticValue.RESULT_MSG,count);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;
	}



	@RequestMapping(value = "insterTSamRoleInfo", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String,String> insterTSamRoleInfo (@RequestBody TSamRole tSamRole) throws Exception {
		Map<String,String> hashMap = new HashMap<>();
		long key = sequenceUtils.getSequence("t_sam_role");
		tSamRole.setRoleId(String.valueOf(key));
		tSamRole.setRoleCode(String.valueOf(key));
		int flag = tsamroleservice.insert(tSamRole);
		hashMap.put("flag",String.valueOf(flag));
		hashMap.put("roleId",String.valueOf(key));
		return hashMap;
	}


	@RequestMapping(value = "updateTSamRoleInfo", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String,String> updateTSamRoleInfo (@RequestBody TSamRole tSamRole) throws Exception {
		return tsamroleservice.updateByPrimaryKey(tSamRole);
	}


	@RequestMapping(value = "selectTSamRoleTree")
	@ResponseBody
	public String selectSamOrgaTree (HttpServletRequest request)  {
		String pId = request.getParameter("id");
		String sync = request.getParameter("sync");
		return tsamroleservice.selectTSamRoleTree(pId,sync);
	}

	@RequestMapping(value = "selectTSamRoleTr")
	@ResponseBody
	public String selectTSamRoleTr (HttpServletRequest request){
        String sync = request.getParameter("sync");
        String moduleId = request.getParameter("moduleId");
        String authId = request.getParameter("authId");
        return tsamroleservice.selectTSamRoleTr(sync,moduleId,authId);
	}

	@RequestMapping(value = "selectTSamRoleTreeByStaffId")
	@ResponseBody
	public String selectTSamRoleTreeByStaffId (HttpServletRequest request)  {
		String pId = request.getParameter("id");
		String sync = request.getParameter("sync");
		String staffId = request.getParameter("staffId");
		String chkDisabled = request.getParameter("chkDisabled");
		return tsamroleservice.selectTSamRoleTreeByStaffId(pId,sync,staffId,chkDisabled);
	}




	@RequestMapping(value = "deleteSamRole")
	public Map<String, Object> deleteOrga(HttpServletRequest request) throws Exception {
		String roleIds = request.getParameter("roleIds");
		return tsamroleservice.deleteByPrimaryKey(roleIds);

	}


	@RequestMapping(value = "selectTSamRoleGrid", method = RequestMethod.GET)
	@ResponseBody
	public String selectTSamRoleGrid (HttpServletRequest request)  {
		String nodeId = request.getParameter("nodeId");
		String roleName = request.getParameter("roleName");
		String page = request.getParameter("page");
		String start = request.getParameter("start");
		Map<String,Object> hashMap = new HashMap<String,Object>();
		TSamRole tSamRole = new TSamRole();
		tSamRole.setRoleCode(nodeId);
		tSamRole.setRoleName(roleName);
		tSamRole.setPage(Integer.valueOf(page));
		tSamRole.setStart(Integer.valueOf(start)-1);
		List<TSamRole> tSamRoleList = tsamroleservice.selectByTSamRoleBySuperCode(tSamRole);
		hashMap.put("rows",tSamRoleList);
		hashMap.put("total",tsamroleservice.selectByTSamRoleCountBySuperCode(tSamRole));
		String jsonString = JSON.toJSONString(hashMap);
		return jsonString;
	}

	@RequestMapping(value = "mutuallyExclusiveRole", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> mutuallyExclusiveRole (HttpServletRequest request) throws Exception {
		String roleId = request.getParameter("roleId");
		Map<String, Object> resultMap= tsamroleservice.mutuallyExclusiveRole(roleId);
		return resultMap;
	}


	@RequestMapping(value = "mutuallyDoesexistRole", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> mutuallyDoesexistRole (@RequestBody List<TSamStaffRole> recordList)  {
		Map<String, Object> resultMap= tsamroleservice.mutuallyDoesexistRole(recordList);
		return resultMap;
	}



}