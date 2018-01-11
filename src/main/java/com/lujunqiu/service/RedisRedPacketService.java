package com.lujunqiu.service;

/**
 * Created by qiu on 18-1-11.
 */
public interface RedisRedPacketService {
    /**
     * 用redis保持抢红包列表
     * @param redPacketId
     * @param unitAmount
     */
    void saveUserRedPacketByRedis(int redPacketId,double unitAmount);
}
