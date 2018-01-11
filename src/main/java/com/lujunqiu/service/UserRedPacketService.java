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

    /**
     * 通过redis实现抢红包
     * @param redPacketId
     * @param userId
     * @return
     * 0-没有库存，失败
     * 1-成功抢到红包，且不是最后一个红包
     * 2-成功抢到红包，且是最后一个红包
     */
    int grapRedPacketByRedis(int redPacketId, int userId);
}
