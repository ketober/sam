package com.ai.sam.web;
import com.ai.sam.common.StaticValue;
import com.ai.sam.service.TSamUserAuthService;
import org.apache.microserv.skeleton.facade.BaseAction;
import org.apache.microserv.skeleton.facade.AiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ai.sam.service.TSamTenantInfoService;
import com.ai.sam.domain.TSamTenantInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@AiController(api = "tsamtenantinfo",urlpaths="/tsamtenantinfo")
public class  TSamTenantInfoController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamTenantInfoController.class);

	@Autowired
	private TSamTenantInfoService tsamtenantinfoservice;
	@Autowired
    private TSamUserAuthService tSamUserAuthService;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody TSamTenantInfo getById(@PathVariable("id") Integer id) throws Exception {

		logger.info("");
		logger.error("");
		return tsamtenantinfoservice.getById(id);

	}

	/**
	 * 新增租户
	 * @param tSamTenantInfo
	 * @return
	 */

	@RequestMapping("/addTenant")
	public Map<String, Object> addTenant(TSamTenantInfo tSamTenantInfo) {
		Map<String,Object> result = new HashMap<>();
		try {
			tsamtenantinfoservice.insert(tSamTenantInfo);
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
			result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
		}
		catch (DuplicateKeyException e)
        {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,"租户ID已存在！");
            return result;
        }
        catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,"新增失败！");
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * 更新租户
	 * @param tSamTenantInfo
	 * @return
	 */
	@RequestMapping("/updateTenant")
	public Map<String, Object> updateTenant(TSamTenantInfo tSamTenantInfo) {
		Map<String,Object> result = new HashMap<>();
		try {
			tsamtenantinfoservice.updateByPrimaryKey(tSamTenantInfo);
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
			result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
		} catch (Exception e) {
			result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
			result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_FAIL_MSG);
			e.printStackTrace();
		}
		return result;
	}

    /**
     * 更新租户
     * @param ids
     * @return
     */
    @RequestMapping("/delTenant")
    public Map<String, Object> delTenant(String ids) {
        Map<String,Object> result = new HashMap<>();
        try {

            tsamtenantinfoservice.deleteStaffInfo(ids);
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        } catch (Exception e) {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_FAIL_MSG);
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 更新租户
     * @param tSamTenantInfo
     * @return
     */
    @RequestMapping(value = "/qrytenantinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> qryTenantInfo(TSamTenantInfo tSamTenantInfo) {
        Map<String,Object> result = new HashMap<>();
		List<TSamTenantInfo> list = new ArrayList<TSamTenantInfo>();
		int total = 0;
        try {
			list = tsamtenantinfoservice.qryTenantInfoByExample(tSamTenantInfo);
			total = tsamtenantinfoservice.qryTenantInfoCountByExample(tSamTenantInfo);
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        } catch (Exception e) {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_FAIL_MSG);
            e.printStackTrace();
        }
		result.put("list",list);
		result.put("total",total);
        return result;
    }
	@ResponseBody
	@RequestMapping("/selectTenantTreeByStaffId")
	public String selectGroupTreeByStaffId(HttpServletRequest request){
		String staffId = request.getParameter("staffId");
		return tsamtenantinfoservice.selectTenantInfoTreeByStaffId(staffId);
	}
	//更新员工租户
    @ResponseBody
    @RequestMapping("/updateUserTenantAuth")
    public Map<String, Object> updateUserTenantAuth(HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        String staffId = request.getParameter("staffId");
        String tenantIds = request.getParameter("tenantIds");
        try {
            tSamUserAuthService.updateUserAuth(staffId,tenantIds);
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        } catch (Exception e) {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_FAIL_MSG);
            e.printStackTrace();
        }
        return result;
    }
}