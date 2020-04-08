为方便运维人员在服务宕机后及时发现，现将服务监控与钉钉群机器人绑定在一起，当服务宕机后，群机器人会发消息并@相关人员。
#### 用法
1. 打包
    ```
    mvn clean package
    ```
1. 编写基础配置文件application-prod.yml
    ```
    service:
      monitors[0]: # 数组，可以有多个监控器
        webHook: "https://oapi.dingtalk.com/robot/send?access_token=" # 钉钉机器人的webHook
        secret: xxx # 钉钉机器人的secret
        url: http://localhost:9000 # 要监控的地址
        method: GET # 监控器检测服务的url
        period: 10000 #每隔多少毫秒检测一次
        returnType: "[B" # 检测时发送请求收到的响应的类型，使用RestTemplate发送请求
    ```   
1. 发送文本消息    
    ```
    service:
      monitors[0]:
        textContent:    
          content: nginx宕机,请及时恢复。 # 消息内容
          atMobiles: 155xxx # 要艾特的人的手机号
          isAtAll: false # 是否@所有人
    ```
1. 发送链接消息
    ```
    service:
      monitors[0]:
        linkContent:    
          title: nginx宕机。 # 消息标题
          text: xxx # 消息内容。如果太长只会部分展示
          messageUrl: xxx # 点击消息跳转的URL
          picUrl: xxx # 图片URL
    ```    
1. 发送markdown消息
    ```
    service:
      monitors[0]:
        markDownContent:    
          title: nginx宕机。 # 首屏会话透出的展示内容
          text: xxx # markdown格式的消息
          atMobiles: xxx # 被@人的手机号
          isAtAll: xxx # 是否@所有人
    ```  
1. 启动  
将jar包重命名为app.jar并和yml文件放在目标文件夹下，使用start.sh脚本启动
