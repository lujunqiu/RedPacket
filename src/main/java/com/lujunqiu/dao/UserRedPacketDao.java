package com.lujunqiu.dao;

import com.lujunqiu.pojo.UserRedPacket;
import org.springframework.stereotype.Repository;

/**
 * Created by qiu on 18-1-8.
 */
@Repository
public interface UserRedPacketDao {

    /**
     * 插入抢红包信息
     * @param userRedPacket 抢红包信息
     * @return 影响记录条数
     */
    int grapRedPacket(UserRedPacket userRedPacket);
}
