package com.lujunqiu.controller;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.lujunqiu.pojo.AjaxResponseMsg;
import com.lujunqiu.pojo.RedPacket;
import com.lujunqiu.pojo.UserRedPacket;
import com.lujunqiu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    WeatherService weatherService = null;


    @RequestMapping(value = "hello2", method = RequestMethod.GET)
    public String test() {
        return "getCode";
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public String fun1(String phone, Model model) throws ClientException, InterruptedException {
        String code = sendSmsService.randomNum();
        QuerySendDetailsResponse querySendDetailsResponse = sendSmsService.sendCode(phone, code);
        model.addAttribute("codeS", code);
        return "redirect:validator";
    }

    @RequestMapping(value = "/validator", method = RequestMethod.GET)
    public String getValidator() {
        return "validator";
    }

    @RequestMapping(value = "/validator", method = RequestMethod.POST)
    @ResponseBody
    public boolean fun2(String code, HttpSession httpSession) {

        return code.equals(httpSession.getAttribute("codeS"));
    }


    /*
    ajax的登录界面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/getcode.do",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponseMsg Sms(String mobile , HttpServletRequest request) throws ClientException, InterruptedException{
//        String code = sendSmsService.randomNum();
//        QuerySendDetailsResponse querySendDetailsResponse = sendSmsService.sendCode(mobile, code);
        AjaxResponseMsg responseMsg = new AjaxResponseMsg();
//        if (querySendDetailsResponse.getCode().equals("OK")) {
//            responseMsg.setErrcode(0);
//            responseMsg.setDetail("60");
//        } else {
//            responseMsg.setErrcode(1);
//        }
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(600);
        session.setAttribute("code","AAAA");
        System.out.println("getCode.do"+"AAAA");
        return responseMsg;
    }


    @RequestMapping(value = "/validate.do", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponseMsg validate(String mobile , String idcode , HttpServletRequest request) {
        AjaxResponseMsg responseMsg = new AjaxResponseMsg();
        String code = (String) request.getSession().getAttribute("code");
        System.out.println("validate:"+mobile);
        System.out.println("validate:"+idcode);
        System.out.println(code.equals(idcode));
        if (code.equals(idcode)) {
            responseMsg.setErrcode(0);
        } else {
            responseMsg.setErrcode(1);
            responseMsg.setDetail("验证码错误");
        }
        return responseMsg;
    }



}
