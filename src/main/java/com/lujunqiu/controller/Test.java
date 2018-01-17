package com.lujunqiu.controller;

import com.lujunqiu.pojo.RedPacket;
import com.lujunqiu.service.RedPacketService;
import com.lujunqiu.service.RedPacketServiceImpl;
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
    @RequestMapping(value = "/hello")
    @ResponseBody
    public List<RedPacket> fun(){
        RedPacket redPacket = new RedPacket();
        redPacket.setStock(20000);
        redPacket.setAmount(100000);
        redPacket.setNote("insert");
        redPacket.setTotal(5000);
        redPacket.setUserId(001);
        redPacketService.insertRedPacket(redPacket);
        return redPacketService.getRedPackets();
    }
}
