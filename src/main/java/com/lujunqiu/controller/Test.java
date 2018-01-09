package com.lujunqiu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by qiu on 18-1-9.
 */
@Controller
@RequestMapping(value = "/test")
public class Test {
    @RequestMapping(value = "/hello")
    public String fun(){
        return "Hello World";
    }
}
