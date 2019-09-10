package com.ai.sam.service.impl;

import com.ai.sam.common.StaticValue;
import com.ai.sam.dao.TSamStaffInfoMapper;
import com.ai.sam.domain.TSamPassword;
import com.ai.sam.service.TSamPasswordService;
import com.ai.sam.dao.TSamPasswordMapper;

import com.ai.sam.utils.BCryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.microserv.skeleton.facade.AiService;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@AiService
public class TSamPasswordServiceImpl implements TSamPasswordService {

   private static Logger logger = LoggerFactory.getLogger(TSamPasswordServiceImpl.class);

    @Autowired
	private TSamPasswordMapper tsampasswordmapper;
    @Autowired
	private TSamStaffInfoMapper tsamstaffinfomapper;
//    @Autowired
//	private BCryptUtil bcryptUtil;

    @Override
	public  TSamPassword getById(Integer id) throws Exception  {
       return null;
    }

    @Override
    public Map<String, Object> qryPassWordById(Map<String, Object> params) throws Exception {
        Map<String, Object> map= tsampasswordmapper.qryPassWordById(params);
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String password = passwordEncoder.encode((CharSequence) map.get("passWord"));
//            details.setClientSecret(password);    //spring boot 2.X
//		details.setClientSecret("secret");      //spring boot 1.5.X
//        map.put("passWord",password);
        return map;
    }
    @Override
    public Map<String, Object> checkPassWord(Map<String, Object> params) throws Exception {
        Map<String, Object> result= new HashMap<>();
        String orgiPassword = (String)params.get("passWord");
        //查询工号密码
        Map<String, Object> map = tsampasswordmapper.qryPassWordById(params);
        String orgiPassworBCY = (String) map.get("passWord");
        boolean a =BCryptUtil.match(orgiPassword,orgiPassworBCY);
        if (a){
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG,"校验成功");
        }else{
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,"校验失败");
        }
        return result;
    }

    @Override
    public Map<String, Object> createPassWord(Map<String, Object> params) throws Exception {
        Map<String, Object> result = new HashMap<>();
        String newPsd = (String) params.get("password");
        if (StringUtils.isEmpty(newPsd)){
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,"密码不能为空！");
            return result;
        }
        //加密
        String password =  BCryptUtil.encode(newPsd);
        params.put("password",password);
        Date date = new Date();
        //HH表示24小时制    如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String temp_str=sdf.format(date);
        params.put("beginDate",temp_str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1); // 加一个月
        Date newDate = calendar.getTime();
        params.put("endDate",sdf.format(newDate));

        int count =tsampasswordmapper.insertPassword(params);
        if (count==1){
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        }else {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,"保存密码失败");
        }
        return result;
    }

    @Override
    public Map<String, Object> updatePassWord(Map<String, Object> params) throws Exception {
        Map<String, Object> result = new HashMap<>();
        String newPsd = (String) params.get("password");
        if (StringUtils.isEmpty(newPsd)){
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,"密码不能为空！");
            return result;
        }
        //加密
        String password =  BCryptUtil.encode(newPsd);
        params.put("password",password);
        Date date = new Date();
        //HH表示24小时制    如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String temp_str=sdf.format(date);
        params.put("updateDate",temp_str);
        int count =tsampasswordmapper.updatePassword(params);
        if (count==1){
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
            result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        }else {
            result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_FAIL_VAL);
            result.put(StaticValue.RESULT_MSG,"修改密码失败");
        }
        return result;
    }

    @Override
    public void resetPassWord(List<String> staffIds) throws Exception {
        for (String staffId:staffIds ) {
            Map<String, Object> params = new HashMap<>();
            params.put("staffId",staffId);
            //TODO 生成初始密码
            //查询身份证后6位
            List<Map<String,Object>> list = tsamstaffinfomapper.queryStaffInfo2(params);
            String idCardNo = (String) list.get(0).get("idCardNo");
            String idNo = idCardNo.substring(idCardNo.length()-6,idCardNo.length());
            //加密
            String password =  BCryptUtil.encode(idNo);
            params.put("password",password);
            Date date = new Date();
            //HH表示24小时制    如果换成hh表示12小时制
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String temp_str=sdf.format(date);
            params.put("updateDate",temp_str);
            tsampasswordmapper.updatePassword(params);
        }
    }

}

