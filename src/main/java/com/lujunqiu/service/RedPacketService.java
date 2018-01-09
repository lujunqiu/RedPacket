package com.lujunqiu.service;

import com.lujunqiu.pojo.RedPacket;

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
     * 扣减红包
     * @param id 红包编号
     * @return 影响条数
     */
    int decreaseRedPacket(int id);
}
