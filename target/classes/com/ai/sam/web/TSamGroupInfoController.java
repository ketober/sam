package com.ai.sam.web;
import com.ai.sam.common.StaticValue;
import com.ai.sam.utils.SequenceUtils;
import com.alibaba.fastjson.JSON;
import org.apache.microserv.skeleton.facade.BaseAction;
import org.apache.microserv.skeleton.facade.AiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ai.sam.service.TSamGroupInfoService;
import com.ai.sam.domain.TSamGroupInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 工作组维护
 */
@AiController(api = "tsamgroupinfo",urlpaths="/tsamgroupinfo")
public class  TSamGroupInfoController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamGroupInfoController.class);

	@Autowired
	private TSamGroupInfoService tsamgroupinfoservice;

	@Autowired
	private SequenceUtils sequenceUtils;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody TSamGroupInfo getById(@PathVariable("id") String id) throws Exception {
		logger.info("");
		logger.error("");
		return tsamgroupinfoservice.selectByPrimaryKey(id);

	}



	@ResponseBody
	@RequestMapping(value ="/deleteByPrimaryKey", method = RequestMethod.GET)
	public Map<String, Object> deleteStaffInfo(HttpServletRequest request) {
		String groupIds = request.getParameter("groupIds");
		return tsamgroupinfoservice.deleteByPrimaryKey(groupIds);
	}


	/**
	 *  查询子集
	 * @param groupId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/selectSamGroupCountBySuperCode/{groupId}", method = RequestMethod.GET)
	public Map<String, Object> selectSamGroupCountBySuperCode(@PathVariable("groupId") String groupId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			TSamGroupInfo tSamGroupInfo = new TSamGroupInfo();
			tSamGroupInfo.setGroupId(groupId);
			int count =tsamgroupinfoservice.selectBySamGroupCountBySuperCode(tSamGroupInfo);
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
			result.put(StaticValue.RESULT_MSG,count);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;
	}







	@RequestMapping(value = "insterGroupInfo",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> insterGroupInfo (@RequestBody TSamGroupInfo tSamGroupInfo) throws Exception {
		Map<String,String> hashMap = new HashMap<>();
		long key = sequenceUtils.getSequence("t_sam_group_info");
		tSamGroupInfo.setGroupId(String.valueOf(key));
		tSamGroupInfo.setGroupCode(String.valueOf(key));
		tSamGroupInfo.setTenantId("1");
		int flag = tsamgroupinfoservice.insert(tSamGroupInfo);
		hashMap.put("flag",String.valueOf(flag));
		hashMap.put("groupId",String.valueOf(key));
		return hashMap;
	}



	@RequestMapping(value = "updateGroupInfo", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public int updateGroupInfo (@RequestBody TSamGroupInfo tSamGroupInfo) throws Exception {
		return tsamgroupinfoservice.updateByPrimaryKey(tSamGroupInfo);
	}

	@RequestMapping(value = "selectGroupTree")
	@ResponseBody
	public String selectGroupTree (HttpServletRequest request)  {
		String sync = request.getParameter("sync");
		String pId = request.getParameter("id");
		String groupName = request.getParameter("groupName");
		return tsamgroupinfoservice.selectGroupInfoTree(sync,pId,groupName);
	}

	@ResponseBody
	@RequestMapping("/selectGroupTreeByStaffId")
	public String selectGroupTreeByStaffId(HttpServletRequest request){
		String staffId = request.getParameter("staffId");
		return tsamgroupinfoservice.selectGroupTreeByStaffId(staffId);
	}





	@RequestMapping("/selectGroupInfoByStaffId")
	public Map<String, Object> selectGroupInfoByStaffId(HttpServletRequest request){
		String staffId = request.getParameter("staffId");
		return tsamgroupinfoservice.selectGroupInfoByStaffId(staffId);
	}


	@RequestMapping(value = "selectGroupGrid", method = RequestMethod.GET)
	@ResponseBody
	public String selectGroupGrid (HttpServletRequest request)  {
		String nodeId = request.getParameter("nodeId");
		String page = request.getParameter("page");
		String start = request.getParameter("start");
		String groupName = request.getParameter("groupName");
		Map<String,Object> hashMap = new HashMap<String,Object>();
		TSamGroupInfo tSamGroupInfo = new TSamGroupInfo();
		tSamGroupInfo.setGroupId(nodeId);
		tSamGroupInfo.setGroupName(groupName);
		tSamGroupInfo.setPage(Integer.valueOf(page));
		tSamGroupInfo.setStart(Integer.valueOf(start)-1);
		List<TSamGroupInfo> tSamGroupInfoList= tsamgroupinfoservice.selectBySamGroupBySuperCode(tSamGroupInfo);
		int total = tsamgroupinfoservice.selectBySamGroupCountBySuperCode(tSamGroupInfo);
		hashMap.put("rows",tSamGroupInfoList);
		hashMap.put("total",total);
		String jsonString = JSON.toJSONString(hashMap);
		return jsonString;
	}



}