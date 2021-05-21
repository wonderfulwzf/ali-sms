package com.wzf.sms;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmsApplicationTests {
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

    @Test
    void contextLoads() throws Exception {
            com.aliyun.dysmsapi20170525.Client client = createClient("LTAI5tE******ZvkGxj", "6gDBAuElu6*****aLOy2x5rV");
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers("15279620069")
                    //.setPhoneNumbers("18179951340")
                    .setSignName("往忆")
                    .setTemplateCode("SMS_215070823")
                    .setTemplateParam("{\"code\":\"666666\"}");
            // 复制代码运行请自行打印 API 的返回值
            client.sendSms(sendSmsRequest);
        }

    }


