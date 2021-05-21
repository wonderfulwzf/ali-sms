package com.wzf.sms.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.wzf.sms.service.SendSms;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SendSmsImpl implements SendSms {

    @Override
    public boolean send(String phoneNumbers, String signName, String templateCode, Map<String, Object> map) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = createClient("LTAI5****VMM4ZvkGxj", "6gDBA****Oy2x5rV");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phoneNumbers)
                //.setPhoneNumbers("18179951340")
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setTemplateParam(JSON.toJSONString(map));
        // 复制代码运行请自行打印 API 的返回值
        SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
        return "OK".equals(sendSmsResponse.getBody().code);
    }

    com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }
}
