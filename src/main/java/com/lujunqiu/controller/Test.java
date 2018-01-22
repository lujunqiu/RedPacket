package com.lujunqiu.controller;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.lujunqiu.pojo.RedPacket;
import com.lujunqiu.pojo.UserRedPacket;
import com.lujunqiu.service.RedPacketService;
import com.lujunqiu.service.RedPacketServiceImpl;
import com.lujunqiu.service.SendSmsService;
import com.lujunqiu.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by qiu on 18-1-9.
 */
@Controller
@RequestMapping(value = "/test")
public class Test {
    @Autowired
    RedPacketService redPacketService = null;
    @Autowired
    UserRedPacketService userRedPacketService = null;
    @RequestMapping(value = "/hello")
    @ResponseBody
    public RedPacket fun(){
        RedPacket redPacket = new RedPacket();
        redPacket.setUserId(1);
        redPacket.setTotal(20000);
        redPacket.setNote("da");
        redPacket.setStock(200000);
        redPacket.setUnitAmount(10);
        redPacketService.insertRedPacket(redPacket);
        return redPacket;
    }

    /**
     * 测试短信验证功能
     * @param args
     * @throws ClientException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ClientException, InterruptedException {
        QuerySendDetailsResponse querySendDetailsResponse = SendSmsService.sendCode("13212778355");
        System.out.println(querySendDetailsResponse.getSmsSendDetailDTOs().get(0).getContent());
    }
}
