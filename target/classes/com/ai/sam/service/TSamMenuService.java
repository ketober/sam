package com.ai.sam.service;

import com.ai.sam.domain.TSamMenu;

import java.util.List;
import java.util.Map;

public interface TSamMenuService {

   List<TSamMenu> qryMenu(String id, String name,String treeId, int start, int pageNum);

   int addMenu(TSamMenu menu);

   int updateMenu(TSamMenu menu);

   Map<String, Object> deleteMenu(String[] ids);

   List<TSamMenu> qryMenuTree(String id);

   void addRootMenu();

}

