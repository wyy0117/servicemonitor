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

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.TimerTask;

/**
 * @Date: 20-4-3
 * @Author: wyy
 */
public class MonitorTask extends TimerTask {

    private Logger log = LoggerFactory.getLogger(MonitorTask.class);

    private Monitor monitor;

    private RestTemplate restTemplate;


    public MonitorTask(Monitor monitor) {
        this.monitor = monitor;
        this.restTemplate = new RestTemplate();
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
                sendMessage(monitor);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ResourceAccessException | HttpServerErrorException e) {
            try {
                sendMessage(monitor);
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            } catch (InvalidKeyException ex) {
                ex.printStackTrace();
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            } catch (ApiException ex) {
                ex.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(Monitor monitor) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, ApiException {
        DingTalkClient client = new DefaultDingTalkClient(monitor.getWebHook());
        OapiRobotSendRequest oapiRobotSendRequest = monitor.getContent().buildRequest();
        client.execute(oapiRobotSendRequest);
    }
}
