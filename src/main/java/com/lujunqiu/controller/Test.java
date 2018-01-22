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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by qiu on 18-1-9.
 */
@Controller
@RequestMapping(value = "/test")
@SessionAttributes(names = "codeS")
public class Test {
    @Autowired
    RedPacketService redPacketService = null;
    @Autowired
    UserRedPacketService userRedPacketService = null;
    @Autowired
    SendSmsService sendSmsService = null;

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

    @RequestMapping(value = "/message",method = RequestMethod.POST)
    public String fun1(String phone, Model model) throws ClientException, InterruptedException {
        String code = sendSmsService.randomNum();
        QuerySendDetailsResponse querySendDetailsResponse = sendSmsService.sendCode(phone, code);
        model.addAttribute("codeS", code);
        System.out.println(code);
        return "redirect:/code";
    }
    @RequestMapping(value = "/validator")
    @ResponseBody
    public boolean fun2(String code , HttpSession httpSession){
        return code.equals(httpSession.getAttribute("codeS"));
    }

    /*
    测试ajax
     */
    @RequestMapping(value = "/sms")
    public String toAjax(){
        return "sms";
    }
    @RequestMapping(value = "/ajax")
    public void ajax(String name, HttpServletResponse response) {
        String result = "hello " + name;
        System.out.println(result);
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
