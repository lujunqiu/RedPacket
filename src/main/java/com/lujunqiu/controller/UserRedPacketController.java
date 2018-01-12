package com.lujunqiu.controller;

import com.lujunqiu.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.Map;

/**
 * 抢红包
 * @author qiu
 */
@Controller
@RequestMapping("/userRedPacket")
public class UserRedPacketController {

    @Autowired
    private UserRedPacketService userRedPacketService = null;

    /**
     * 直接抢红包,会有超发现象
     * @param redPacketId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/grapRedPacket")
    @ResponseBody
    public Map<String,Object> grapRedPacket(int redPacketId ,int userId) {
        int result = userRedPacketService.grapRedPacket(redPacketId, userId);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        boolean flag = result > 0;
        resultMap.put("success", flag);
        resultMap.put("message", flag ? "抢红包成功" : "抢红包失败");
        return resultMap;
    }

    /**
     * CAS抢红包
     * @param redPacketId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/grapRedPacketForVersion")
    @ResponseBody
    public Map<String,Object> grapRedPacketForVersion(int redPacketId ,int userId) {
        int result = userRedPacketService.grapRedPacketForVersion(redPacketId, userId);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        boolean flag = result > 0;
        resultMap.put("success", flag);
        resultMap.put("message", flag ? "抢红包成功" : "抢红包失败");
        return resultMap;
    }

    /**
     * Redis抢红包实现
     * @param redPacketId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/grapRedPacketByRedis")
    @ResponseBody
    public Map<String, Object> grapRedPacketByRedis(int redPacketId, int userId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        long result = userRedPacketService.grapRedPacketByRedis(redPacketId, userId);
        boolean flag = result > 0;
        resultMap.put("result", flag);
        resultMap.put("message", flag ? "抢红包成功" : "抢红包失败");
        return resultMap;
    }
}
