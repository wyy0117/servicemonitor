package com.wyy.servicemonitor.config;

import com.wyy.servicemonitor.config.content.AbstractContent;
import com.wyy.servicemonitor.config.content.LinkContent;
import com.wyy.servicemonitor.config.content.MarkDownContent;
import com.wyy.servicemonitor.config.content.TextContent;
import org.springframework.http.HttpMethod;

/**
 * @Date: 20-4-3
 * @Author: wyy
 */
public class Monitor {
    private String webHook;
    /**
     * 监控的服务访问的url
     */
    private String url;
    /**
     * 请求时的方法
     */
    private HttpMethod method;
    /**
     * 请求的body
     */
    private String body;
    /**
     * 请求头上的body的类型
     */
    private String contentType;

    private LinkContent linkContent;

    private MarkDownContent markDownContent;

    private TextContent textContent;

    /**
     * 监控的url的返回类型，通过反射接收为该类型
     */
    private String returnType;

    /**
     * 定时任务时间间隔，毫秒
     */
    private long period;

    @Override
    public String toString() {
        return "Monitor{" +
                "webHook='" + webHook + '\'' +
                ", url='" + url + '\'' +
                ", method=" + method +
                ", body='" + body + '\'' +
                ", contentType='" + contentType + '\'' +
                ", linkContent=" + linkContent +
                ", markDownContent=" + markDownContent +
                ", textContent=" + textContent +
                ", returnType='" + returnType + '\'' +
                ", period=" + period +
                '}';
    }

    public String getReturnType() {
        return returnType;
    }

    public Monitor setReturnType(String returnType) {
        this.returnType = returnType;
        return this;
    }

    public long getPeriod() {
        return period;
    }

    public Monitor setPeriod(long period) {
        this.period = period;
        return this;
    }

    public String getWebHook() {
        return webHook;
    }

    public Monitor setWebHook(String webHook) {
        this.webHook = webHook;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Monitor setUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public Monitor setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public String getBody() {
        return body;
    }

    public Monitor setBody(String body) {
        this.body = body;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public Monitor setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public Monitor setLinkContent(LinkContent linkContent) {
        this.linkContent = linkContent;
        return this;
    }

    public Monitor setMarkDownContent(MarkDownContent markDownContent) {
        this.markDownContent = markDownContent;
        return this;
    }

    public Monitor setTextContent(TextContent textContent) {
        this.textContent = textContent;
        return this;
    }

    public AbstractContent getContent() {
        if (textContent != null) {
            return textContent;
        }
        if (linkContent != null) {
            return linkContent;
        }
        if (markDownContent != null) {
            return markDownContent;
        }
        return null;
    }
}
