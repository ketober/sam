package com.ai.sam.service.impl;

import com.ai.sam.common.StaticValue;
import com.ai.sam.domain.TSamGroupMember;
import com.ai.sam.service.TSamGroupMemberService;
import com.ai.sam.dao.TSamGroupMemberMapper;

import com.ai.sam.utils.Message;
import com.ai.sam.utils.SequenceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.microserv.skeleton.facade.AiService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.util.StringUtils;

@AiService
public class TSamGroupMemberServiceImpl implements TSamGroupMemberService {

   private static Logger logger = LoggerFactory.getLogger(TSamGroupMemberServiceImpl.class);

    @Autowired
	private TSamGroupMemberMapper tsamgroupmembermapper;
    @Autowired
    private SequenceUtils sequenceUtils;

    @Override
	public  TSamGroupMember getById(Integer id) throws Exception  {
       return tsamgroupmembermapper.selectByPrimaryKey(String.valueOf(id));
    }

    @Override
    public Map<String, Object> addStaffGroupBatch(List<TSamGroupMember> groupList) {
        Map<String,Object> hasMap = new HashMap<>();
        if(groupList.size()>0){
            for(int i = 0;i<groupList.size();i++){
                try {
                    long key = sequenceUtils.getSequence("t_sam_group_member");
                    groupList.get(i).setMemberId(String.valueOf(key));
                    if(groupList.get(i).getStaffId().equals("null")){
                        hasMap.put("message",new Message("1","success"));
                        return hasMap;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        try {
            tsamgroupmembermapper.addStaffGroupBatch(groupList);
            hasMap.put("message",new Message("1","success"));
        } catch (Exception e) {
            hasMap.put("message",new Message("-1",e.getMessage()));
            e.printStackTrace();
        }
        return hasMap;
    }

    @Override
    public int deleteByGroupId(TSamGroupMember record) {
        return tsamgroupmembermapper.deleteByGroupId(record);
    }

    @Override
    public Map<String, Object> updateStaffGroup(String staffId,String groupIds) throws Exception {
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> params = new HashMap<>();
        params.put("staffId",staffId);
        //删除
        tsamgroupmembermapper.deleteGroupMember(params);
        String groupIdArr[];
        groupIdArr=groupIds.split(",");
        if (groupIdArr.length>0){
            for (int i = 0; i <groupIdArr.length ; i++) {
                if (!StringUtils.isEmpty(groupIdArr[i])) {
                    long key = sequenceUtils.getSequence("t_sam_group_member");
                    params.put("memberId",String.valueOf(key));
                    params.put("groupId",groupIdArr[i]);
                    params.put("isprincipal","0");
                    //新增
                    tsamgroupmembermapper.insertGroupMember(params);
                }

            }
        }
        result.put(StaticValue.RESULT_VAL,StaticValue.RESULT_SUCCESS_VAL);
        result.put(StaticValue.RESULT_MSG,StaticValue.RESULT_SUCCESS_MSG);
        return result;
    }

}

