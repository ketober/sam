package com.ai.sam.service;

import com.ai.sam.domain.TSamModule;

import java.util.List;

public interface TSamModuleService {

   int addModule(TSamModule module);

   int updtModule(TSamModule module);

   int delModule(String id);

   List<TSamModule> qryModule();

}

