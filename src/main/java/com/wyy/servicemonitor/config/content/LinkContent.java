package com.wyy.servicemonitor.config.content;

import com.dingtalk.api.request.OapiRobotSendRequest;
import com.wyy.servicemonitor.config.MsgType;

/**
 * @Date: 20-4-3
 * @Author: wyy
 */
public class LinkContent extends AbstractContent {

    private String title;
    private String text;
    private String messageUrl;
    private String picUrl;

    public LinkContent() {
        super(MsgType.LINK);
    }

    @Override
    public String toString() {
        return "LinkContent{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", messageUrl='" + messageUrl + '\'' +
                ", picUrl='" + picUrl + '\'' +
                "} " + super.toString();
    }

    public String getTitle() {
        return title;
    }

    public LinkContent setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public LinkContent setText(String text) {
        this.text = text;
        return this;
    }

    public String getMessageUrl() {
        return messageUrl;
    }

    public LinkContent setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
        return this;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public LinkContent setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

    @Override
    public OapiRobotSendRequest buildRequest() {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(super.getMsgtype());
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl(this.messageUrl);
        link.setPicUrl(this.picUrl);
        link.setTitle(this.title);
        link.setText(this.text);
        request.setLink(link);
        return request;
    }
}
