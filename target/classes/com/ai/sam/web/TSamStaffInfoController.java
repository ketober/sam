package com.ai.sam.web;
import com.ai.sam.common.StaticValue;
import com.ai.sam.domain.TSamStaffInfoExample;
import com.ai.sam.service.TSamPasswordService;
import com.ai.sam.service.TSamStaffRoleService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.microserv.skeleton.facade.BaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.ai.sam.service.TSamStaffInfoService;
import com.ai.sam.domain.TSamStaffInfo;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


//@AiController(api = "tsamstaffinfo",urlpaths="/tsamstaffinfo")
@Controller
@RequestMapping("/StaffInfo")
public class  TSamStaffInfoController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamStaffInfoController.class);

	@Autowired
	private TSamStaffInfoService tsamstaffinfoservice;
    @Autowired
    private TSamPasswordService tsampasswordservice;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody TSamStaffInfo getById(@PathVariable("id") Integer id) throws Exception {

		logger.info("");
		logger.error("");
		return tsamstaffinfoservice.getById(id);

	}

    @ResponseBody
    @RequestMapping("/qryStaffInfo")
    public Map<String, Object> qryStaffInfo(HttpServletRequest request) {
        String staffId = request.getParameter("staffId");
        String staffName = request.getParameter("staffName");
        String staffState = request.getParameter("staffState");
        String staffIdMin = request.getParameter("staffIdMin");
        String staffIdMax = request.getParameter("staffIdMax");
        String mobilePhone = request.getParameter("mobilePhone");
        String postId = request.getParameter("postId");
        String orgaId = request.getParameter("orgaId");
        String orgaCode = request.getParameter("orgaCode");
        if (StringUtils.isEmpty(orgaId)&&!("0".equals(orgaCode))){
            orgaId=orgaCode;
        }
        String channelId = request.getParameter("channelId");
        String staffIdStatus = request.getParameter("staffIdStatus");
        Map<String, Object> params = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(request.getParameter("startIndex"))){
            int startIndex = Integer.parseInt(request.getParameter("startIndex"));
            params.put("start",startIndex-1);
        }
        if (!StringUtils.isEmpty(request.getParameter("pageNum"))){
            int pageNum = Integer.parseInt(request.getParameter("pageNum"));
            params.put("limit",pageNum);
        }
        params.put("staffId",staffId);
        params.put("staffName",staffName);
        params.put("staffState",staffState);
        params.put("staffIdMin",staffIdMin);
        params.put("staffIdMax",staffIdMax);
        params.put("mobilePhone",mobilePhone);
        params.put("orgaId",orgaId);
        params.put("staffIdStatus",staffIdStatus);
        params.put("channelId",channelId);
        params.put("postId",postId);
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            //
//            List<TSamStaffInfo> list = tsamstaffinfoservice.getStaffInfo(params);
            List<Map<String,Object>> list = tsamstaffinfoservice.getStaffInfo2(params);
            int total = tsamstaffinfoservice.getStaffInfoCount(params);
            //查询
            result.put("list",list);
            result.put("total",total);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @ResponseBody
    @RequestMapping("/qryStaffInfoRest")
    public Map<String, Object> qryStaffInfoRest(HttpServletRequest request) {
//        StringBuffer str = new StringBuffer();
//        try { BufferedInputStream in = new BufferedInputStream(
//                request.getInputStream());
//            int i;
//            char c;
//            while ((i=in.read())!=-1) {
//                    c=(char)i;str.append(c);
//                }
//        }catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        String string = str.toString();
//        JSONObject jsonObject = JSONObject.parseObject(string);
//        String staffId= jsonObject.getString("staffId");
        String staffId = request.getParameter("staffId");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("staffId",staffId);
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            //
            List<Map<String,Object>> list = tsamstaffinfoservice.getStaffInfoRest(params);
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG,list);
        } catch (Exception e) {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,e.getMessage());
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/qryStaffByOrgaIdRest")
    public Map<String, Object> qryStaffByOrgaIdRest(HttpServletRequest request) {
        String orgaId = request.getParameter("orgaId");
        Map<String, Object> params = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(request.getParameter("startIndex"))){
            int startIndex = Integer.parseInt(request.getParameter("startIndex"));
            params.put("start",startIndex-1);
        }
        if (!StringUtils.isEmpty(request.getParameter("pageNum"))){
            int pageNum = Integer.parseInt(request.getParameter("pageNum"));
            params.put("limit",pageNum);
        }
        params.put("orgaId",orgaId);
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            List<Map<String,Object>> list = tsamstaffinfoservice.qryStaffByOrgaIdRest(params);
            int total = tsamstaffinfoservice.getStaffByOrgaIdCount(params);
            result.put("list",list);
            result.put("total",total);
//            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
//            result.put(StaticValue.RESULT_MSG,list);
            return result;
        } catch (Exception e) {
//            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
//            result.put(StaticValue.RESULT_MSG,e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/createStaffInfo")
    public Map<String, Object> createStaffInfo(HttpServletRequest request) {
        String staffId = request.getParameter("staffId");
        String staffName = request.getParameter("staffName");
        String staffState = request.getParameter("staffState");
        String mobilePhone = request.getParameter("mobilePhone");
        String orgaId = request.getParameter("orgaId");
        String joinDate = request.getParameter("joinDate");
        String emailAddress = request.getParameter("emailAddress");
        String idCardNo = request.getParameter("id_card_no");
        String postId = request.getParameter("postId");
        String channelId = request.getParameter("channelId");
        String prsn_chnl_type_cd = request.getParameter("prsn_chnl_type_cd");
        String default_service_type = request.getParameter("default_service_type");
        Map<String, Object> result = new HashMap<String, Object>();
        //参数校验
        //id不能重复
        //邮箱不能重复
        //电话号码不能重复
        //身份证不能重复
        Map<String,Object> params = new HashMap<>();
        params.put("staffId",staffId);
        params.put("mobilePhone",mobilePhone);
        params.put("emailAddress",emailAddress);
        params.put("id_card_no",idCardNo);
        try {
            List<Map<String,Object>> list = tsamstaffinfoservice.getStaffInfoByParams(params);
            if (!list.isEmpty()){
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(JSON.toJSONString(list));
                    TSamStaffInfo tsamstaffinfo= (TSamStaffInfo) list.get(i);
                    if (tsamstaffinfo.getStaffId().equals(staffId)){
                        result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
                        result.put(StaticValue.RESULT_MSG,"已存在相同人员账号！");
                        return result;
                    }
                    if (tsamstaffinfo.getMobilePhone().equals(mobilePhone)){
                        result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
                        result.put(StaticValue.RESULT_MSG,"已存在相同手机号码！");
                        return result;
                    }
                    if (tsamstaffinfo.getEmailAddress().equals(emailAddress)){
                        result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
                        result.put(StaticValue.RESULT_MSG,"已存在相同邮箱地址！");
                        return result;
                    }
                    if (tsamstaffinfo.getIdCardNo().equals(idCardNo)){
                        result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
                        result.put(StaticValue.RESULT_MSG,"已存在相同身份证号！");
                        return result;
                    }
                }
            }
        } catch (Exception e) {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,e.getMessage());
            return result;
        }
        TSamStaffInfo staffInfo = new TSamStaffInfo();
        staffInfo.setStaffId(staffId);
        staffInfo.setStaffName(staffName);
        staffInfo.setStaffState(staffState);
        staffInfo.setOrgaId(orgaId);
        staffInfo.setMobilePhone(mobilePhone);
        staffInfo.setEmailAddress(emailAddress);
        staffInfo.setIdCardNo(idCardNo);
        staffInfo.setPostId(postId);
        staffInfo.setChannelId(channelId);
        staffInfo.setPrsnChnlTypeCd(prsn_chnl_type_cd);
        staffInfo.setStaffIdStatus("01");
        staffInfo.setTenantId("1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            staffInfo.setJoinDate(sdf.parse(joinDate));
            staffInfo.setInsertTime(new Date());
            staffInfo.setUpdaTetime(new Date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        staffInfo.setDefaultServiceType(default_service_type);
        try {
            //保存人员信息
            int message = tsamstaffinfoservice.createStaffInfo(staffInfo);
            if (message==1){
                //保存密码信息
                params.put("password",idCardNo.substring(idCardNo.length()-6,idCardNo.length()));
                result = tsampasswordservice.createPassWord(params);
                return result;
            }
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_FAIL_MSG);
        } catch (Exception e) {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,e.getMessage());
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/updateStaffInfo")
    public Map<String, Object> updateStaffInfo(HttpServletRequest request) {
        String staffId = request.getParameter("staffId5");
        String staffName = request.getParameter("staffName5");
        String staffState = request.getParameter("staffState5");
        String mobilePhone = request.getParameter("mobilePhone5");
        String staffIdStatus = request.getParameter("staffIdStatus5");
        String orgaId = request.getParameter("orgaId5");
        String postId = request.getParameter("postId5");
        String emailAddress = request.getParameter("emailAddress5");
        String id_card_no = request.getParameter("idCardNo5");
        String channelId = request.getParameter("channelId5");
        String prsn_chnl_type_cd = request.getParameter("prsnchnltypecd5");
        String default_service_type = request.getParameter("defaultServiceType5");
        TSamStaffInfo staffInfo = new TSamStaffInfo();
        staffInfo.setStaffId(staffId);
        staffInfo.setStaffName(staffName);
        staffInfo.setStaffState(staffState);
        staffInfo.setOrgaId(orgaId);
        staffInfo.setMobilePhone(mobilePhone);
        staffInfo.setEmailAddress(emailAddress);
        staffInfo.setIdCardNo(id_card_no);
        staffInfo.setChannelId(channelId);
        staffInfo.setPrsnChnlTypeCd(prsn_chnl_type_cd);
        staffInfo.setStaffIdStatus(staffIdStatus);
        staffInfo.setUpdaTetime(new Date());
        staffInfo.setDefaultServiceType(default_service_type);
        staffInfo.setPostId(postId);
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            //
            int message = tsamstaffinfoservice.updateStaffInfo(staffInfo);
            if (message==1){
                result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
                result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
                return result;
            }
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_FAIL_MSG);
        } catch (Exception e) {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,e.getMessage());
        }
        return result;
    }
    //物理删除
	@ResponseBody
	@RequestMapping("/deleteStaffInfo")
	public Map<String, Object> deleteStaffInfo(HttpServletRequest request) {
		String ids_str = request.getParameter("ids");
		Map<String, Object> result = new HashMap<String, Object>();
		if (ids_str != null) {
			String[] s = ids_str.split(",");
			List<String> ids = new ArrayList<String>();
			Collections.addAll(ids, s);
			try {
				tsamstaffinfoservice.deleteStaffInfo(ids);
                result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
                result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
			} catch (Exception e) {
                result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
                result.put(StaticValue.RESULT_MSG,e.getMessage());
			}
		}
		return result;
	}

	//逻辑删除
	@ResponseBody
	@RequestMapping("/updateStaffStatus")
	public Map<String, Object> updateStaffStatus(HttpServletRequest request) {
		String ids_str = request.getParameter("ids");
		Map<String, Object> result = new HashMap<String, Object>();
		if (ids_str != null) {
			String[] s = ids_str.split(",");
			List<String> ids = new ArrayList<String>();
			Collections.addAll(ids, s);
			try {
				tsamstaffinfoservice.deleteStaffInfo(ids);
                result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
                result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
			} catch (Exception e) {
                result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
                result.put(StaticValue.RESULT_MSG,e.getMessage());
			}
		}
		return result;
	}


	/**
	 * 检索查询
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "selectStaffRole", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<TSamStaffInfo> selectStaffRole (@RequestBody TSamStaffInfoExample tSamStaffInfo) throws Exception {
		return tsamstaffinfoservice.selectByExample(tSamStaffInfo);
	}



    /**
     *角色关联的空闲用户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectStaffFreeRole", method = RequestMethod.GET)
    @ResponseBody
    public String selectStaffFreeRole (HttpServletRequest request) {



        String roleId = request.getParameter("roleId");
        String staffId = request.getParameter("staffId");
        String staffName = request.getParameter("staffName");
        String staffState = request.getParameter("staffState");
        String staffIdMin = request.getParameter("staffIdMin");
        String staffIdMax = request.getParameter("staffIdMax");
        String mobilePhone = request.getParameter("mobilePhone");
        String postId = request.getParameter("postId");
        String orgaId = request.getParameter("orgaId");
        String orgaCode = request.getParameter("orgaCode");
        if (StringUtils.isEmpty(orgaId)&&!("0".equals(orgaCode))){
            orgaId=orgaCode;
        }
        String channelId = request.getParameter("channelId");
        String staffIdStatus = request.getParameter("staffIdStatus");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("staffName",staffName);
        params.put("staffId",staffId);
        params.put("staffName",staffName);
        params.put("staffState",staffState);
        params.put("staffIdMin",staffIdMin);
        params.put("staffIdMax",staffIdMax);
        params.put("mobilePhone",mobilePhone);
        params.put("orgaId",orgaId);
        params.put("staffIdStatus",staffIdStatus);
        params.put("channelId",channelId);
        params.put("postId",postId);
        params.put("roleId",roleId);
        List<TSamStaffInfo> staffInfoList = tsamstaffinfoservice.selectStaffFreeRole(params);
        Map<String,Object> hashMap = new HashMap<String,Object>();
        hashMap.put("rows",staffInfoList);
        hashMap.put("total",tsamstaffinfoservice.selectStaffFreeRoleCount(params));
        String jsonString = JSON.toJSONString(hashMap);
        return jsonString;
    }



    /**
     *角色下关联的用户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectStaffIncludeRole", method = RequestMethod.GET)
    @ResponseBody
    public String selectStaffIncludeRole (HttpServletRequest request) {
        String nodeId = request.getParameter("roleId");
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        Map<String,Object> hashMap = new HashMap<String,Object>();
        List<TSamStaffInfo> staffInfoList = tsamstaffinfoservice.selectStaffIncludeRole(nodeId);
        hashMap.put("rows",staffInfoList);
        hashMap.put("total",staffInfoList.size());
        String jsonString = JSON.toJSONString(hashMap);
        return jsonString;
    }




    /**
     * 用户组空闲的用户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectStaffFreeGroup", method = RequestMethod.GET)
    @ResponseBody
    public String selectStaffFreeGroup (HttpServletRequest request) {
        String groupId = request.getParameter("groupId");
        String staffId = request.getParameter("staffId");
        String staffName = request.getParameter("staffName");
        String staffState = request.getParameter("staffState");
        String staffIdMin = request.getParameter("staffIdMin");
        String staffIdMax = request.getParameter("staffIdMax");
        String mobilePhone = request.getParameter("mobilePhone");
        String postId = request.getParameter("postId");
        String orgaId = request.getParameter("orgaId");
        String orgaCode = request.getParameter("orgaCode");
        if (StringUtils.isEmpty(orgaId)&&!("0".equals(orgaCode))){
            orgaId=orgaCode;
        }
        String channelId = request.getParameter("channelId");
        String staffIdStatus = request.getParameter("staffIdStatus");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("staffName",staffName);
        params.put("groupId",groupId);
        params.put("staffId",staffId);
        params.put("staffName",staffName);
        params.put("staffState",staffState);
        params.put("staffIdMin",staffIdMin);
        params.put("staffIdMax",staffIdMax);
        params.put("mobilePhone",mobilePhone);
        params.put("orgaId",orgaId);
        params.put("staffIdStatus",staffIdStatus);
        params.put("channelId",channelId);
        params.put("postId",postId);
        List<TSamStaffInfo> staffInfoList = tsamstaffinfoservice.selectStaffFreeGroup(params);
        Map<String,Object> hashMap = new HashMap<String,Object>();
        hashMap.put("rows",staffInfoList);
        hashMap.put("total",tsamstaffinfoservice.selectStaffFreeGroupCount(params));
        String jsonString = JSON.toJSONString(hashMap);
        return jsonString;
    }




    /**
     *工作组下关联的用户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectStaffIncludeGroup",  method = RequestMethod.GET)
    @ResponseBody
    public String selectStaffIncludeGroup (HttpServletRequest request) {
        String groupId = request.getParameter("groupId");
        String groupName = request.getParameter("groupName");
        Map<String,Object> parHashMap = new HashMap<String,Object>();
        parHashMap.put("groupId",groupId);
        parHashMap.put("groupName",groupName);
        Map<String,Object> hashMap = new HashMap<String,Object>();
        List<TSamStaffInfo>  staffInfoList = tsamstaffinfoservice.selectStaffIncludeGroup(parHashMap);
        hashMap.put("rows",staffInfoList);
        hashMap.put("total",staffInfoList.size());
        String jsonString = JSON.toJSONString(hashMap);
        return jsonString;
    }



    /**
	 *工作组下关联的用户 带分页的
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "selectStaffIncludeGroupPaging",  method = RequestMethod.GET)
	@ResponseBody
	public String selectStaffIncludeGroupPaging (HttpServletRequest request) {
        Map<String,Object> parHashMap = new HashMap<String,Object>();
        String groupId = request.getParameter("groupId");
        String staffId = request.getParameter("staffId");
        String staffName = request.getParameter("staffName");
        String page = request.getParameter("page");
        String start = request.getParameter("start");

        if (!StringUtils.isEmpty(start)){
            int intStart = Integer.parseInt(start);
            parHashMap.put("start",intStart-1);
        }
        if (!StringUtils.isEmpty(page)){
            int intPage = Integer.parseInt(page);
            parHashMap.put("page",intPage);
        }
        parHashMap.put("groupId",groupId);
        parHashMap.put("staffId",staffId);
        parHashMap.put("staffName",staffName);
        Map<String,Object> hashMap = new HashMap<String,Object>();
        List<TSamStaffInfo>  staffInfoList = tsamstaffinfoservice.selectStaffIncludeOrderByGroup(parHashMap);
        hashMap.put("rows",staffInfoList);
        hashMap.put("total",staffInfoList.size());
		return  JSON.toJSONString(hashMap, SerializerFeature.DisableCircularReferenceDetect);
	}


    /**
     *工作组下关联分配的责任人
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectStaffIncludeGroupForPic",  method = RequestMethod.GET)
    @ResponseBody
    public String selectStaffIncludeGroupForPic (HttpServletRequest request) {
        String groupId = request.getParameter("groupId");
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        Map<String,Object> hashMap = new HashMap<String,Object>();
        List<TSamStaffInfo>  staffInfoList = tsamstaffinfoservice.selectStaffIncludeGroupForPic(String.valueOf(groupId));
        hashMap.put("rows",staffInfoList);
        hashMap.put("total",staffInfoList.size());
        String jsonString = JSON.toJSONString(hashMap);
        return jsonString;
    }


    /**
     *角色关联的用户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectStaffForRoleId", method = RequestMethod.GET)
    @ResponseBody
    public String selectStaffForRoleId (HttpServletRequest request) {
        String roleId = request.getParameter("roleId");
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        List<TSamStaffInfo> staffInfoList = tsamstaffinfoservice.selectStaffForRoleId(roleId);
        Map<String,Object> hashMap = new HashMap<String,Object>();
        hashMap.put("rows",staffInfoList);
        hashMap.put("total",staffInfoList.size());
        String jsonString = JSON.toJSONString(hashMap);
        return jsonString;
    }




}