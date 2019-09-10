package com.ai.sam.web;
import com.ai.sam.domain.TSamAuthElement;
import com.ai.sam.domain.TSamStaffRole;
import com.ai.sam.service.TSamPermitService;
import com.alibaba.fastjson.JSON;
import org.apache.microserv.skeleton.facade.BaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ai.sam.service.TSamAuthElementService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ai.sam.utils.ServiceConstant;


@Controller
@RequestMapping("/Auth")
public class  TSamAuthElementController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamAuthElementController.class);

	@Autowired
	private TSamAuthElementService tsamauthelementservice;

	@Autowired
	private TSamPermitService tsampermitservice;


	@ResponseBody
	@RequestMapping("/qryAuth")
	public Map<String, Object> qryAuthElement(HttpServletRequest request){
		String authId = request.getParameter("clickedTreeId");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			TSamAuthElement data = tsamauthelementservice.qryAuth(authId);
			result.put("result",ServiceConstant.STATUS_SUCCESS);
			result.put("msg",ServiceConstant.MSG_SUCCESS);
			result.put("data", data);
			return result;
		}catch (Exception e){
			result.put("result",ServiceConstant.STATUS_SYSERROR);
			result.put("msg",e.getMessage());
			logger.error(e.toString(), e);
		}
		return null;
	}

	@ResponseBody
	@RequestMapping("/addAuth")
	public Map<String, Object> addAuthElement(HttpServletRequest request){
		String AuthElementStr = request.getParameter("authStr");
		String flag = request.getParameter("flag");
		System.out.println(flag+","+AuthElementStr);
		TSamAuthElement auth = JSON.parseObject(AuthElementStr,TSamAuthElement.class);

		Map<String, Object> result = new HashMap<String, Object>();
		if(auth!=null) {
			if ("0".equals(auth.getMenuFlag()) || "功能".equals(auth.getMenuFlag())) {
				auth.setMenuFlag("0");
			} else {
				auth.setMenuFlag("1");
			}
			try {
				if ("0".equals(flag)) {
					tsamauthelementservice.addAuth(auth);
				} else {
					tsamauthelementservice.updtAuth(auth);
				}
				result.put("result",ServiceConstant.STATUS_SUCCESS);
				result.put("msg",ServiceConstant.MSG_SUCCESS);
			}catch (Exception e){
				result.put("result",ServiceConstant.STATUS_SYSERROR);
				result.put("msg",e.getMessage());
			}
		}else{
			result.put("result",ServiceConstant.STATUS_EINVAL);
			result.put("msg","请求参数错误!");
		}
		return result;
	}


	@ResponseBody
	@RequestMapping("/delAuth")
	public Map<String, Object> delAuthElement(HttpServletRequest request){
		String authId = request.getParameter("clickedTreeId");
		Map<String, Object> result = new HashMap<String, Object>();
        result = tsamauthelementservice.delAuth(authId);
		return result;
	}
	@ResponseBody
	@RequestMapping("/qryAuthTree")
	public Map<String, Object> qryAuthElementTree(HttpServletRequest request){
		String root = request.getParameter("moduleId");
		String nodeId = request.getParameter("ELEMENTID");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<TSamAuthElement> auths = tsamauthelementservice.qryAuthTree(root,nodeId);
			result.put("result", ServiceConstant.STATUS_SUCCESS);
			result.put("msg", ServiceConstant.MSG_SUCCESS);
			result.put("auths", auths);
		}catch(Exception e){
			result.put("result",ServiceConstant.STATUS_SYSERROR);
			result.put("msg",e.getMessage());
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/qryAuthElementTreeByEntityId")
	public Map<String, Object> qryAuthElementTreeByEntityId(HttpServletRequest request){
		String entityId = request.getParameter("entityId");
		String root = request.getParameter("moduleId");
		String permitType = request.getParameter("permitType");
		String nodeId = request.getParameter("ELEMENTID");
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> params = new HashMap<>();
		params.put("moduleId",root);
		params.put("entityId", entityId);
		params.put("permitType", permitType);
		try {
			List<TSamAuthElement> auths = tsamauthelementservice.qryAuthTree(root,nodeId);
			if("000".equals(root)){
				//如果为根节点，查询所有权限
				params.put("moduleId",null);
			}
			List<Map<String, Object>> selected = tsampermitservice.selectAuthByEntityId(params);
			List<String> a = new ArrayList<>();
			for (Map<String, Object> select:selected) {
				a.add(String.valueOf(select.get("authObjId")));
			}
			boolean flag=false;
			for (TSamAuthElement tSamAuthElement :auths){
				Map<String, Object> resultTemp = new HashMap<>();
				resultTemp.put("ELEMENTID",tSamAuthElement.getElementId());
				resultTemp.put("ELEMENTNAME",tSamAuthElement.getElementName());
				resultTemp.put("ELEMENTCODE",tSamAuthElement.getElementCode());
				resultTemp.put("SUPERELEMENTCODE",tSamAuthElement.getSuperElementCode());
				resultTemp.put("MENUFLAG",tSamAuthElement.getMenuFlag());
				resultTemp.put("MODULEID",tSamAuthElement.getModuleId());
				if (a.contains(tSamAuthElement.getElementId())){
					resultTemp.put("checked","true");
					flag=true;
				}else{
					resultTemp.put("checked","false");
				}
				if ("000".equals(root)){
					resultTemp.put("chkDisabled","true");
				}else{
					resultTemp.put("chkDisabled","false");
				}
				if (flag&&("000".equals(tSamAuthElement.getElementId()))){
					resultTemp.put("checked","true");
				}
				list.add(resultTemp);
			}
			result.put("result", ServiceConstant.STATUS_SUCCESS);
			result.put("msg", ServiceConstant.MSG_SUCCESS);
			result.put("auths", list);
		}catch(Exception e){
			result.put("result",ServiceConstant.STATUS_SYSERROR);
			result.put("msg",e.getMessage());
		}
		return result;
	}

	/**
	 * 权限树展示所有角色的功能权限
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/qryAuthElementTreeByRoleAll")
	public Map<String, Object> qryAuthElementTreeByRoleAll(HttpServletRequest request){
		String entityId = request.getParameter("entityId");
		String root = request.getParameter("moduleId");
		String permitType = request.getParameter("permitType");
		String nodeId = request.getParameter("ELEMENTID");
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> params = new HashMap<>();
		params.put("moduleId",root);
		params.put("entityId", entityId);
		params.put("permitType", permitType);
		try {
			//查树
			List<TSamAuthElement> auths = tsamauthelementservice.qryAuthTree(root,nodeId);
			//查询当前人员的所有角色的所有权限
			List<Map<String, Object>> selected = tsampermitservice.selectAllRoleAuthByEntityId(entityId);
			List<String> a = new ArrayList<>();
			for (Map<String, Object> select:selected) {
				a.add(String.valueOf(select.get("authObjId")));
			}
			boolean flag=false;
			for (TSamAuthElement tSamAuthElement :auths){
				Map<String, Object> resultTemp = new HashMap<>();
				resultTemp.put("ELEMENTID",tSamAuthElement.getElementId());
				resultTemp.put("ELEMENTNAME",tSamAuthElement.getElementName());
				resultTemp.put("ELEMENTCODE",tSamAuthElement.getElementCode());
				resultTemp.put("SUPERELEMENTCODE",tSamAuthElement.getSuperElementCode());
				resultTemp.put("MENUFLAG",tSamAuthElement.getMenuFlag());
				resultTemp.put("MODULEID",tSamAuthElement.getModuleId());
				if (a.contains(tSamAuthElement.getElementId())){
					resultTemp.put("checked","true");
					flag=true;
				}else{
					resultTemp.put("checked","false");
				}
				if ("000".equals(root)){
					resultTemp.put("chkDisabled","true");
				}else{
					resultTemp.put("chkDisabled","false");
				}
				if (flag&&("000".equals(tSamAuthElement.getElementId()))){
					resultTemp.put("checked","true");
				}
				list.add(resultTemp);
			}
			result.put("result", ServiceConstant.STATUS_SUCCESS);
			result.put("msg", ServiceConstant.MSG_SUCCESS);
			result.put("auths", list);
		}catch(Exception e){
			result.put("result",ServiceConstant.STATUS_SYSERROR);
			result.put("msg",e.getMessage());
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/qryChild")
	public Map<String, String> qryChild(HttpServletRequest request){
		String id = request.getParameter("clickedTreeId");
		Map<String, String> result = new HashMap<String, String>();
		try{
			result = tsamauthelementservice.qryChild(id);
			result.put("result", ServiceConstant.STATUS_SUCCESS);
			result.put("msg", ServiceConstant.MSG_SUCCESS);
		}catch(Exception e) {
			result.put("result", ServiceConstant.STATUS_SYSERROR);
			result.put("msg", e.getMessage());
		}
		return result;
	}

}