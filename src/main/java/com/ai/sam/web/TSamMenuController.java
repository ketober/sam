package com.ai.sam.web;
import com.ai.sam.service.TSamAuthElementService;
import com.ai.sam.utils.ServiceConstant;
import com.alibaba.fastjson.JSON;
import org.apache.microserv.skeleton.facade.BaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ai.sam.service.TSamMenuService;
import com.ai.sam.domain.TSamMenu;
import com.ai.sam.domain.TSamAuthElement;
import com.ai.sam.domain.TSamModule;
import com.ai.sam.service.TSamModuleService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@AiController(api = "tsammenu",urlpaths="/tsammenu")
@Controller
@RequestMapping("/Menu")
public class  TSamMenuController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger( TSamMenuController.class);

	@Autowired
	private TSamMenuService tsammenuservice;
	@Autowired
	private TSamAuthElementService tsamauthelementservice;
	@Autowired
	private TSamModuleService tSamModuleService;


	@ResponseBody
	@RequestMapping("/qryMenu")
	public Map<String, Object> qryMenu(HttpServletRequest request){
		String id = request.getParameter("id").trim();
		String name = request.getParameter("name").trim();
		String treeId = request.getParameter("treeId");
		int start = Integer.parseInt(request.getParameter("startIndex"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<TSamMenu> list = tsammenuservice.qryMenu(id, name,treeId, start-1, pageNum);
			int total = tsammenuservice.qryMenu(id, name, treeId,-1, -1).size();
            result.put("result",ServiceConstant.STATUS_SUCCESS);
            result.put("msg",ServiceConstant.MSG_SUCCESS);
			result.put("rows", list);
			result.put("total", total);
			return result;
		}catch (Exception e){
            result.put("result",ServiceConstant.STATUS_SYSERROR);
            result.put("msg",e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@ResponseBody
	@RequestMapping("/addMenu")
	public Map<String, Object> addMenu(HttpServletRequest request){
		String menuStr = request.getParameter("menuStr");
		String flag = request.getParameter("flag");
		TSamMenu menu = JSON.parseObject(menuStr,TSamMenu.class);

        Map<String, Object> result = new HashMap<String, Object>();
		if(menu!=null) {
			if ("Y".equals(menu.getOpenModule()) || "新窗口".equals(menu.getOpenModule())) {
				menu.setOpenModule("Y");
			} else {
				menu.setOpenModule("N");
			}

			//同时写到功能权限
			TSamAuthElement auth = new TSamAuthElement();
			auth.setMenuFlag("1");
			auth.setElementId(menu.getMenuId());
			auth.setElementCode(menu.getMenuId());
			auth.setElementName(menu.getMenuName());
			auth.setModuleId(menu.getModuleId());
			auth.setSuperElementCode(menu.getParentId());
			auth.setDescription(menu.getMenuDescription());
			auth.setElementUrl(menu.getMenuUrl());

			//如果增加了一级菜单，同时添加模块信息
			TSamModule module = new TSamModule();
			if("000".equals(menu.getParentId())){
				module.setModuleId(menu.getModuleId());
				module.setDisplayNo(menu.getDisplayNo());
				module.setModuleName(menu.getMenuName());
				module.setParentId(menu.getParentId());
				module.setTenantId(menu.getTenantId());


			}

			try {
				if ("-1".equals(flag)) {
					tsammenuservice.addMenu(menu);
					tsamauthelementservice.addAuth(auth);
					if("000".equals(menu.getParentId())){
						tSamModuleService.addModule(module);
					}
				} else {
					tsammenuservice.updateMenu(menu);
					tsamauthelementservice.updtAuth(auth);
					if("000".equals(menu.getParentId())){
						tSamModuleService.updtModule(module);
					}
				}
                result.put("result", ServiceConstant.STATUS_SUCCESS);
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
	@RequestMapping("/delMenu")
	public Map<String, Object> delMenu(HttpServletRequest request){
		String idStr = request.getParameter("ids");

		Map<String, Object> result = new HashMap<String, Object>();

		String[] ids = idStr.split(",");
		try {
			result = tsammenuservice.deleteMenu(ids);
        }catch (Exception e){
            result.put("result",ServiceConstant.STATUS_SYSERROR);
            result.put("msg",e.getMessage());
        }
        return result;
	}

	@ResponseBody
	@RequestMapping("/qryMenuTree")
	public Map<String, Object> qryMenuTree(HttpServletRequest request){
        String id = request.getParameter("id");
		List<TSamMenu> menus = tsammenuservice.qryMenuTree(id);
		Map<String, Object> result = new HashMap<String, Object>();
		try{
        result.put("result", ServiceConstant.STATUS_SUCCESS);
        result.put("msg", ServiceConstant.MSG_SUCCESS);
        result.put("menus",menus);
		}catch(Exception e) {
            result.put("result", ServiceConstant.STATUS_SYSERROR);
            result.put("msg", e.getMessage());
        }
		return result;
	}

	@ResponseBody
	@RequestMapping("/addRootMenu")
	public Map<String, Object> addRootMenu(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			tsammenuservice.addRootMenu();
			result.put("result", ServiceConstant.STATUS_SUCCESS);
			result.put("msg", ServiceConstant.MSG_SUCCESS);
		}catch (Exception e){
			result.put("result", ServiceConstant.STATUS_SYSERROR);
			result.put("msg", e.getMessage());
		}
		return  result;
	}
}