package com.ai.sam.utils;

import java.io.Serializable;

/**
 * 描述:error  success 信息
 *
 * @Author liu
 * @Date 2019/5/29 10:55
 * @Version 1.0
 **/
public class Message implements Serializable {

    //1 成功 -1失败
    private String flag;
    //异常信息
    private String message;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Message(String flag,String message) {
        this.flag = flag;
        this.message = message;
    }


}
