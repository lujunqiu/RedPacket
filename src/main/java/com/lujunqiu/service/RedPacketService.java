package com.lujunqiu.service;

import com.lujunqiu.pojo.RedPacket;
import com.sun.org.apache.regexp.internal.RE;

import java.util.List;

/**
 * Created by qiu on 18-1-8.
 */
public interface RedPacketService {
    /**
     * 获取红包信息
     * @param id 红包编号
     * @return 红包信息
     */
    RedPacket getRedPacket(int id);

    /**
     * 获取所有红包信息
     * @return
     */
    List<RedPacket> getRedPackets();
    /**
     * 扣减红包
     * @param id 红包编号
     * @return 影响条数
     */
    int decreaseRedPacket(int id);

    /**
     * 插入红包信息
     * @param redPacket
     */
    void insertRedPacket(RedPacket redPacket);
}
