package com.wyy.servicemonitor.config.content;

import com.dingtalk.api.request.OapiRobotSendRequest;
import com.wyy.servicemonitor.config.MsgType;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 20-4-3
 * @Author: wyy
 */
public class TextContent extends AbstractContent {

    private String content;
    private List<String> atMobiles = new ArrayList<>();
    private boolean isAtAll = false;


    public TextContent() {
        super(MsgType.TEXT);
    }

    @Override
    public String toString() {
        return "TextContent{" +
                "content='" + content + '\'' +
                ", atMobiles=" + atMobiles +
                ", isAtAll=" + isAtAll +
                "} " + super.toString();
    }

    public String getContent() {
        return content;
    }

    public TextContent setContent(String content) {
        this.content = content;
        return this;
    }

    public List<String> getAtMobiles() {
        return atMobiles;
    }

    public TextContent setAtMobiles(List<String> atMobiles) {
        this.atMobiles = atMobiles;
        return this;
    }

    public boolean isAtAll() {
        return isAtAll;
    }

    public TextContent setAtAll(boolean atAll) {
        isAtAll = atAll;
        return this;
    }

    @Override
    public OapiRobotSendRequest buildRequest() {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(super.getMsgtype());
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(this.content);
        request.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(this.getAtMobiles());
        request.setAt(at);
        return request;
    }
}
