package com.ai.sam.service.impl;


import com.ai.sam.dao.TSamTenantauthMapper;
import com.ai.sam.domain.TSamTenantauth;
import com.ai.sam.service.TSamUserAuthService;
import com.ai.sam.utils.SequenceUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.microserv.skeleton.facade.AiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@AiService
public class TSamUserAuthServiceImpl implements TSamUserAuthService {

   private static Logger logger = LoggerFactory.getLogger(TSamUserAuthServiceImpl.class);

    @Autowired
    private TSamTenantauthMapper tSamTenantauthMapper;
    @Autowired
    private SequenceUtils sequenceUtils;
    @Override
    public void updateUserAuth(String staffId, String tenantIds) throws Exception {
        Map<String,Object> params = new HashMap<>();
        List<Map<String,Object>> insertUserAuthList = new ArrayList<>();
          params.put("staffId",staffId);
          //删除之前的记录
        tSamTenantauthMapper.deleteByStaffId(staffId);
          List<String> IdList = new ArrayList<>();
        IdList.addAll(Arrays.asList(tenantIds.split(",")));
          for (String str:IdList)
          {
              Map<String,Object> insertUserAuth = new HashMap<>();
              long key = sequenceUtils.getSequence("t_sam_userauth");
              insertUserAuth.put("dblCenId",key);
              insertUserAuth.put("staffId",staffId);
              insertUserAuth.put("tenantId",str);
              insertUserAuthList.add(insertUserAuth);
          }
        Map<String,Object> insertParam = new HashMap<>();
        if(CollectionUtils.isNotEmpty(insertUserAuthList)) {
            insertParam.put("list", insertUserAuthList);
            tSamTenantauthMapper.batchInsert(insertParam);
        }

    }
}

