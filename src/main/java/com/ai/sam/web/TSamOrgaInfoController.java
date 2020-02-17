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
import com.ai.sam.service.TSamOrgaInfoService;
import com.ai.sam.domain.TSamOrgaInfo;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 组织机构维护
 */
@AiController(api = "tsamorgainfo",urlpaths="/tsamorgainfo")
public class  TSamOrgaInfoController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamOrgaInfoController.class);

	@Autowired
	private TSamOrgaInfoService tsamorgainfoservice;

	@Autowired
	private SequenceUtils sequenceUtils;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody TSamOrgaInfo getById(@PathVariable("id") String id) throws Exception {
		logger.info("");
		logger.error("");
		return tsamorgainfoservice.getById(id);

	}



	@ResponseBody
	@RequestMapping(value ="/deleteByPrimaryKey", method = RequestMethod.GET)
	public Map<String, Object> deleteStaffInfo(HttpServletRequest request) {
		String orgaId = request.getParameter("orgaId");
        String opStaffId = request.getParameter("opStaffId");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			tsamorgainfoservice.deleteByPrimaryKey(orgaId,opStaffId);
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
			result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;
	}





	@ResponseBody
	@RequestMapping(value ="/selectRepeatOrgaName")
	public Map<String, Object> selectRepeatOrgaName(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String orgaName = request.getParameter("orgaName");
			String nodeId = request.getParameter("nodeId");
			Map<String,String> hashMap = new HashMap<>();
			hashMap.put("orgaName",orgaName);
			hashMap.put("nodeId",nodeId);
			int count =tsamorgainfoservice.selectRepeatOrgaName(hashMap);
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
			result.put(StaticValue.RESULT_MSG,count);
		} catch (Exception e) {
			e.printStackTrace();
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;
	}
	/**
	 *  查询子集
	 * @param orgaId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/selectSamOrgaCountBySuperCode/{orgaId}", method = RequestMethod.GET)
	public Map<String, Object> selectSamGroupCountBySuperCode(@PathVariable("orgaId") String orgaId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			TSamOrgaInfo tSamOrgaInfo = new TSamOrgaInfo();
			tSamOrgaInfo.setOrgaCode(orgaId);
			int count =tsamorgainfoservice.selectByTSamOrgaInfoCountBySuperCode(tSamOrgaInfo);
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
			result.put(StaticValue.RESULT_MSG,count);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
		}
		return result;
	}



	@RequestMapping(value = "insterTSamOrgaInfo", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String,String> insterTSamOrgaInfo (@RequestBody TSamOrgaInfo tSamOrgaInfo) throws Exception {
		Map<String,String> hashMap = new HashMap<>();
		long key = sequenceUtils.getSequence("t_sam_orga_info");
		tSamOrgaInfo.setOrgaId(String.valueOf(key));
        tSamOrgaInfo.setOrgaCode(String.valueOf(key));
		int flag = tsamorgainfoservice.insert(tSamOrgaInfo);
		hashMap.put("flag",String.valueOf(flag));
		hashMap.put("orgaId",String.valueOf(key));
		return hashMap;
	}




	@RequestMapping(value = "updateTSamOrgaInfo", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> updateTSamOrgaInfo (@RequestBody TSamOrgaInfo tSamOrgaInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			tsamorgainfoservice.update(tSamOrgaInfo);
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
			result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,e.getMessage());
			e.printStackTrace();
		}
		return result;
	}


	@RequestMapping(value = "selectSamOrgaTree")
	@ResponseBody
	public String selectSamOrgaTree (HttpServletRequest request)  {
		String id = request.getParameter("id");
		String opStaffId = request.getParameter("opStaffId");
		return tsamorgainfoservice.selectTSamOrgaTree(id,opStaffId);
	}


	@RequestMapping(value = "selectSamOrgaTreeForCombotree")
	@ResponseBody
	public String selectSamOrgaTreeForCombotree (HttpServletRequest request)  {
        String opStaffId = request.getParameter("opStaffId");
		return tsamorgainfoservice.selectTSamOrgaTreeForCombotree(opStaffId);
	}

    @ResponseBody
	@RequestMapping(value = "deleteOrga")
	public Map<String, Object> deleteOrga(HttpServletRequest request) {
		String orgaIds = request.getParameter("orgaIds");
		String opStaffId = request.getParameter("opStaffId");
        Map<String, Object> result = new HashMap<String, Object>();
        try {
			result = tsamorgainfoservice.deleteByPrimaryKey(orgaIds,opStaffId);
        } catch (Exception e) {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


	@RequestMapping(value = "selectTSamOrgaGrid", method = RequestMethod.GET)
	@ResponseBody
	public String selectTSamOrgaGrid (HttpServletRequest request)  {
		String orgaName = request.getParameter("orgaName");
		String tenantName = request.getParameter("tenantName");
		String nodeId = request.getParameter("nodeId");
		String page = request.getParameter("page");
		String start = request.getParameter("start");
		Map<String,Object> hashMap = new HashMap<String,Object>();
		TSamOrgaInfo tSamOrgaInfo = new TSamOrgaInfo();
		tSamOrgaInfo.setOrgaCode(nodeId);
		tSamOrgaInfo.setTenantName(tenantName);
		tSamOrgaInfo.setOrgaName(orgaName);
		tSamOrgaInfo.setPage(Integer.valueOf(page));
		tSamOrgaInfo.setStart(Integer.valueOf(start)-1);
		List<TSamOrgaInfo> tSamOrgaInfoList= tsamorgainfoservice.selectByTSamOrgaInfoBySuperCode(tSamOrgaInfo);
		int total = tsamorgainfoservice.selectByTSamOrgaInfoCountBySuperCode(tSamOrgaInfo);
		hashMap.put("rows",tSamOrgaInfoList);
		hashMap.put("total",total);
		String jsonString = JSON.toJSONString(hashMap);
		return jsonString;
	}



}