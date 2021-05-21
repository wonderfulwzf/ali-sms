package com.wzf.sms.service;

import java.util.Map;

/**
 * @auther: 王智芳
 * @Description 发送短信接口
 * @date: 2021/5/21 12:20
 */
public interface SendSms {
    boolean send(String phoneNumbers, String signName, String templateCode, Map<String,Object> map) throws Exception;

}
