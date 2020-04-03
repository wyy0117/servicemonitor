package com.wyy.servicemonitor.config.content;

import com.dingtalk.api.request.OapiRobotSendRequest;

/**
 * @Date: 20-4-3
 * @Author: wyy
 */
public abstract class AbstractContent {

    private String msgtype;

    public AbstractContent(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public AbstractContent setMsgtype(String msgtype) {
        this.msgtype = msgtype;
        return this;
    }

    public abstract OapiRobotSendRequest buildRequest();
}
