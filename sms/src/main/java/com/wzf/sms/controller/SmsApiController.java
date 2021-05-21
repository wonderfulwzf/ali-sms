package com.wzf.sms.controller;

import com.wzf.sms.service.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin//支持跨域
public class SmsApiController {

    @Autowired
    private SendSms sendSms;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("/send/{phone}")
    public String code(@PathVariable("phone") String phone){
        //调用发送方法（模拟真实业务redis）
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return phone + ":" + code + "已存在";
        }
        //生成验证码并存储
        code = UUID.randomUUID().toString().substring(0, 6);
        HashMap<String,Object> map = new HashMap<>();
        map.put("code",code);
        boolean issend = false;
        try {
            issend = sendSms.send(phone, "往忆", "SMS_215070823", map);
        }catch (Exception e){
            System.out.println("发送短信验证码失败");
            return "发送短信验证码异常";
        }
        if(issend){
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return phone + ":" + code + "发送成功";
        }else {
            return "发送短信验证码失败";
        }

    }
}
