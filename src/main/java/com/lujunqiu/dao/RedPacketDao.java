package com.lujunqiu.dao;

import com.lujunqiu.pojo.RedPacket;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by qiu on 18-1-8.
 */
@Repository
public interface RedPacketDao {
    /**
     * 获取红包的信息
     * @param id
     * @return 具体红包信息
     */
    RedPacket getRedPacket(int id);

    /**
     * 通过加行更新锁，获取红包信息
     * @param id
     * @return
     */
    RedPacket getRedPacketForUpdate(int id);

    /**
     * 扣减红包信息
     * @param id 红包编号
     * @return 更新记录条数
     */
    int decreaseRedPacket(int id);

    /**
     * CAS操作扣减红包库存，利用版本号version避免ABA问题
     * @param id
     * @return
     */
    int decreaseRedPacketForVersion(@Param("id") int id,@Param("version") long version);

}
