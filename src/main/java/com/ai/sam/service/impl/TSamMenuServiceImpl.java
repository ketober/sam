package com.ai.sam.service.impl;

import com.ai.sam.dao.TSamAuthElementMapper;
import com.ai.sam.dao.TSamPermitMapper;
import com.ai.sam.domain.TSamAuthElement;
import com.ai.sam.domain.TSamAuthElementExample;
import com.ai.sam.domain.TSamMenu;
import com.ai.sam.domain.TSamMenuExample;
import com.ai.sam.domain.TSamMenuExample.Criteria;
import com.ai.sam.service.TSamMenuService;
import com.ai.sam.service.TSamPermitService;
import com.ai.sam.dao.TSamMenuMapper;
import com.ai.sam.dao.TSamModuleMapper;

import com.ai.sam.utils.ServiceConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.microserv.skeleton.facade.AiService;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AiService
public class TSamMenuServiceImpl implements TSamMenuService {

   private static Logger logger = LoggerFactory.getLogger(TSamMenuServiceImpl.class);

    @Autowired
	private TSamMenuMapper tsammenumapper;
    @Autowired
    private TSamAuthElementMapper tsamauthelementmapper;
    @Autowired
    private TSamModuleMapper tSamModuleMapper;
    @Autowired
    private TSamPermitService tsampermitservice;
    @Autowired
    private TSamPermitMapper tsampermitmapper;

    @Override
    public List<TSamMenu> qryMenu(String id, String name,String treeId, int start, int pageNum){
        TSamMenuExample e = new TSamMenuExample();
        Criteria c= e.createCriteria();
        c.andParentIdEqualTo(treeId).andMenuIdLike("%" + id + "%").andMenuNameLike("%" + name + "%");
        if(start!=-1&&pageNum!=-1){
            RowBounds r = new RowBounds(start, pageNum);
            return tsammenumapper.selectByExample(e,r);
        }else{
            return tsammenumapper.selectByExample(e);
        }
    }

    @Override
    public int addMenu(TSamMenu menu){
        return tsammenumapper.insert(menu);
    }

    @Override
    public int updateMenu(TSamMenu menu){
        System.out.println("open==============="+menu.getOpenModule());
        return tsammenumapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public Map<String, Object> deleteMenu(String[] ids){
        Map<String, Object> result = new HashMap<String, Object>();
        List<String> pids = new ArrayList<>();
        List<String> aids = new ArrayList<>();
        List<String> sids = new ArrayList<>();
        int count = 0;

        for(String menuId:ids) {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("authObjId",menuId);
            data.put("permitType","2");

            TSamMenuExample e = new TSamMenuExample();
            Criteria c = e.createCriteria();
            c.andParentIdEqualTo(menuId);
            List<TSamMenu> menus = tsammenumapper.selectByExample(e);
            TSamAuthElementExample ae = new TSamAuthElementExample();
            TSamAuthElementExample.Criteria ac = ae.createCriteria();
            ac.andSuperElementCodeEqualTo(menuId);
            List<TSamAuthElement> auths = tsamauthelementmapper.selectByExample(ae);
            count = tsampermitmapper.selectPerMitByAuthObjId(data);
            if(menus !=null && menus.size()>0){
                pids.add(menuId);
            }else if(auths !=null && auths.size()>0){
                aids.add(menuId);
            }else if(count>0){
                sids.add(menuId);
            }else {
                tsammenumapper.deleteByPrimaryKey(menuId);
                tsamauthelementmapper.deleteByPrimaryKey(menuId);
                if(4<menuId.length() && menuId.length()<9){
                    tSamModuleMapper.deleteByPrimaryKey(menuId.substring(menuId.length()/2));
                }
                try {
                    tsampermitservice.deleteByAuth(menuId);
                }catch (Exception a){
                    a.printStackTrace();
                }
            }
        }
        result.put("result", ServiceConstant.STATUS_SUCCESS);
        result.put("msg",ServiceConstant.MSG_SUCCESS);
        result.put("total",pids.size());
        result.put("pids",pids);
        result.put("atotal",aids.size());
        result.put("aids",aids);
        result.put("stotal",sids.size());
        result.put("sids",sids);
        return result;
    }

    @Override
    public List<TSamMenu> qryMenuTree(String id){
        TSamMenuExample e = new TSamMenuExample();
        Criteria c= e.createCriteria();
        c.andMenuIdIsNotNull();
        List<TSamMenu> menus = tsammenumapper.selectByExample(e);
        List<TSamMenu> result = new ArrayList<>();

        if(id!=null && !"".equals(id)) {
            int len = id.length();
            for(TSamMenu menu:menus){
                if(menu.getMenuId().length()>len && menu.getMenuId().substring(0,len).equals(id)){
                    result.add(menu);
                }
            }
            System.out.println("子节点======"+result.size());
            return result;
        }else{
            return menus;
        }
    }

    @Override
    public void addRootMenu(){
        TSamMenu menu = new TSamMenu();
        menu.setMenuId("000");
        menu.setParentId(" ");
        menu.setMenuName("菜单项");
        Short a=0;
        menu.setDisplayNo(a);
        menu.setOpenModule("N");
        menu.setTenantId("1");
        tsammenumapper.insert(menu);
    }

}

