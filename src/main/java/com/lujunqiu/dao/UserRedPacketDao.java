package com.lujunqiu.dao;

import com.lujunqiu.pojo.UserRedPacket;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 得到所有的抢红包信息
     * @return
     */
    List<UserRedPacket> getUserRedPacket();
}
