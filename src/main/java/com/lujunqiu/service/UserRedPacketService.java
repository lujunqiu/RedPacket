package com.lujunqiu.service;

/**
 * Created by qiu on 18-1-8.
 */
public interface UserRedPacketService {
    /**
     * 保存抢红包信息
     * @param redPacketId 红包编号
     * @param userId 抢红包用户编号
     * @return 影响记录条数
     */
    int grapRedPacket(int redPacketId, int userId);

    /**
     * CAS实现抢红包
     * @param redPacketId
     * @param userId
     * @return
     */
    int grapRedPacketForVersion(int redPacketId, int userId);
}
