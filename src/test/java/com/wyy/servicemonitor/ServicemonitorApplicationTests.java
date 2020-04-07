package com.wyy.servicemonitor;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import com.wyy.servicemonitor.config.MonitorConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootTest
class ServicemonitorApplicationTests {

    @Autowired
    MonitorConfiguration monitorConfiguration;

    @Test
    void contextLoads() throws ApiException {
        System.out.println("monitorConfiguration = " + monitorConfiguration);
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=d436af10acf70d4ebde298611eb264cef02be1b4aafcf5c443e3865cb04c08ea&&timestamp=1585893720841&sign=sjvg0Vp3n2AWPG7r2xMA4BNixLezyRl5FKvEcWGPi3s%3D");
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("测试文本消息");
        request.setText(text);
        OapiRobotSendResponse response = client.execute(request);
    }

    @Test
    void testRest(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>("", headers);
        try {
            Class<?> returnType = Class.forName("[B");
            ResponseEntity response = restTemplate.exchange("http://bimrun2test.suitbim.com.cn/share/", HttpMethod.GET, entity, returnType);
            if (response.getStatusCode() != HttpStatus.OK) {

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ResourceAccessException e) {

        }catch (HttpServerErrorException e){
            e.printStackTrace();
        }
    }

}
