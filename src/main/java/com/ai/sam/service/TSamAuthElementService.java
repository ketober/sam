package com.ai.sam.service;

import com.ai.sam.domain.TSamAuthElement;

import java.util.List;
import java.util.Map;

public interface TSamAuthElementService {

   TSamAuthElement qryAuth(String id);

   void addAuth(TSamAuthElement auth);

   void updtAuth(TSamAuthElement auth);

    Map<String, Object> delAuth(String authId);

   List<TSamAuthElement> qryAuthTree(String topId,String nodeId);

   List<TSamAuthElement> qryModule();

   Map<String, String> qryChild(String id);
}

