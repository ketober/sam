package com.ai.sam.service.impl;

import com.ai.sam.common.StaticValue;
import com.ai.sam.domain.TSamPlatformRel;
import com.ai.sam.service.TSamPlatformRelService;
import com.ai.sam.dao.TSamPlatformRelMapper;

import com.ai.sam.utils.SequenceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.microserv.skeleton.facade.AiService;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AiService
public class TSamPlatformRelServiceImpl implements TSamPlatformRelService {

   private static Logger logger = LoggerFactory.getLogger(TSamPlatformRelServiceImpl.class);

    @Autowired
	private TSamPlatformRelMapper tsamplatformrelmapper;
    @Autowired
    private SequenceUtils sequenceUtils;

    @Override
	public  TSamPlatformRel getById(Integer id) throws Exception  {
       return null;
    }

    @Override
    public List<Map<String,Object>>  qryStaffPlatform(Map<String, Object> params) throws Exception {
        List<Map<String,Object>> list =tsamplatformrelmapper.getStaffPlatformRel(params);
        return list;
    }

    @Override
    public List<Map<String, Object>> qryNoDistriPlatform(Map<String, Object> params) throws Exception {
        List<Map<String,Object>> list =tsamplatformrelmapper.qryNoDistriPlatform(params);
        return list;
    }
    @Override
    public int qryNoDistriPlatformCount(Map<String, Object> params) throws Exception {
        int count =tsamplatformrelmapper.qryNoDistriPlatformCount(params);
        return count;
    }
    @Override
    public Map<String, Object> updateStaffPlatform(Map<String, Object> params) throws Exception {
        Map<String, Object> result =new HashMap<>();
        int count1 =tsamplatformrelmapper.deleteStaffPlatform(params);
        if (!StringUtils.isEmpty(params.get("platformId"))){
            long key = sequenceUtils.getSequence("t_sam_platform_rel");
            params.put("relId",String.valueOf(key));
            int count2 =tsamplatformrelmapper.saveStaffPlatform(params);
            if (count2==1){
                result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
                result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
                return result;
            }
        }
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
//        result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
//        result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_FAIL_MSG);
        return result;
    }

}

