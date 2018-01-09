package com.lujunqiu.controller;

import com.lujunqiu.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author qiu
 */
@Controller
@RequestMapping("/userRedPacket")
public class UserRedPacketController {

    @Autowired
    private UserRedPacketService userRedPacketService = null;

    @RequestMapping(value = "/grapRedPacket")
    @ResponseBody
    public Map<String,Object> grapRedPacket(int redPacketId ,int userId) {
        int result = userRedPacketService.grapRedPacket(redPacketId, userId);
        System.out.println(result+"dsafasdfasd");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        boolean flag = result > 0;
        System.out.println("Re");
        resultMap.put("success", flag);
        resultMap.put("message", flag ? "抢红包成功" : "抢红包失败");
        return resultMap;
    }


}
