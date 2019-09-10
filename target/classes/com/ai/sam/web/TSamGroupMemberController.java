package com.ai.sam.web;
import org.apache.microserv.skeleton.facade.BaseAction;
import org.apache.microserv.skeleton.facade.AiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ai.sam.service.TSamGroupMemberService;
import com.ai.sam.domain.TSamGroupMember;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@AiController(api = "tsamgroupmember",urlpaths="/tsamgroupmember")
public class  TSamGroupMemberController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamGroupMemberController.class);

	@Autowired
	private TSamGroupMemberService tsamgroupmemberservice;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody TSamGroupMember getById(@PathVariable("id") Integer id) throws Exception {

		logger.info("");
		logger.error("");
		return tsamgroupmemberservice.getById(id);

	}


	/**
	 * 为人员配置工作组
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateStaffGroup")
	public Map<String, Object> updateStaffGroup(HttpServletRequest request) {
		String staffId = request.getParameter("staffId");//一个实体
		String groupIds = request.getParameter("groupIds");//多个权限，逗号分隔
		Map<String,Object> result = new HashMap<>();
		try {
			result = tsamgroupmemberservice.updateStaffGroup(staffId,groupIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}





	//批量增加
	@RequestMapping(value = "addStaffGroupBatch", method = RequestMethod.POST)
	public Map<String, Object> addStaffGroupBatch (@RequestBody List<TSamGroupMember> staffList)  {
		TSamGroupMember tSamGroupMember = new TSamGroupMember();
		tSamGroupMember.setGroupId(staffList.get(0).getGroupId());
		tsamgroupmemberservice.deleteByGroupId(tSamGroupMember);
		return tsamgroupmemberservice.addStaffGroupBatch(staffList);
	}


}