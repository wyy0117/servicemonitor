package com.wyy.servicemonitor;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.taobao.api.ApiException;
import com.wyy.servicemonitor.config.Monitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.TimerTask;

/**
 * @Date: 20-4-3
 * @Author: wyy
 */
public class MonitorTask extends TimerTask {

    private Logger log = LoggerFactory.getLogger(MonitorTask.class);

    private Monitor monitor;

    private RestTemplate restTemplate;

    DingTalkClient client = null;

    public MonitorTask(Monitor monitor) {
        this.monitor = monitor;

        this.restTemplate = new RestTemplate();

        this.client = new DefaultDingTalkClient(monitor.getWebHook());
    }

    @Override
    public void run() {
        log.debug("monitor running for " + monitor.getUrl());
        HttpHeaders headers = new HttpHeaders();
        if (monitor.getContentType() != null) {
            headers.setContentType(new MediaType(monitor.getContentType()));
        }
        HttpEntity<String> entity = new HttpEntity<>(monitor.getBody(), headers);
        try {
            Class<?> returnType = Class.forName(monitor.getReturnType());
            ResponseEntity response = restTemplate.exchange(monitor.getUrl(), monitor.getMethod(), entity, returnType);
            if (response.getStatusCode() != HttpStatus.OK) {
                OapiRobotSendRequest oapiRobotSendRequest = monitor.getContent().buildRequest();
                try {
                    client.execute(oapiRobotSendRequest);
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ResourceAccessException | HttpServerErrorException e) {
            OapiRobotSendRequest oapiRobotSendRequest = monitor.getContent().buildRequest();
            try {
                client.execute(oapiRobotSendRequest);
            } catch (ApiException e1) {
                e1.printStackTrace();
            }
        }
    }
}
