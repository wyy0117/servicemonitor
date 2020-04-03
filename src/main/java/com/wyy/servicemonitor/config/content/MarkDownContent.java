package com.wyy.servicemonitor.config.content;

import com.dingtalk.api.request.OapiRobotSendRequest;
import com.wyy.servicemonitor.config.MsgType;

import java.util.Arrays;

/**
 * @Date: 20-4-3
 * @Author: wyy
 */
public class MarkDownContent extends AbstractContent {
    private String title;
    private String text;
    private String[] atMobiles;
    private boolean isAtAll;

    public MarkDownContent() {
        super(MsgType.MARKDOWN);
    }

    @Override
    public String toString() {
        return "MarkDownContent{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", atMobiles=" + Arrays.toString(atMobiles) +
                ", isAtAll=" + isAtAll +
                "} " + super.toString();
    }

    public String getTitle() {
        return title;
    }

    public MarkDownContent setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public MarkDownContent setText(String text) {
        this.text = text;
        return this;
    }

    public String[] getAtMobiles() {
        return atMobiles;
    }

    public MarkDownContent setAtMobiles(String[] atMobiles) {
        this.atMobiles = atMobiles;
        return this;
    }

    public boolean isAtAll() {
        return isAtAll;
    }

    public MarkDownContent setAtAll(boolean atAll) {
        isAtAll = atAll;
        return this;
    }

    @Override
    public OapiRobotSendRequest buildRequest() {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(super.getMsgtype());
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle(this.title);
        markdown.setText(this.text);
        request.setMarkdown(markdown);
        return request;
    }
}
