package com.lujunqiu.dao;

import com.lujunqiu.pojo.RedPacket;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**关于mybatis返回单一对象或对象列表的问题：
     * 1.返回数据类型由dao中的接口和*map.xml文件共同决定。另外，不论是返回单一对象还是对象列表，*map.xml中的配置都是一样的，都是resultMap="*Map"*或resultType=“* .* .*”类型.
     * 2.每一次mybatis从数据库中select数据之后，都会检查数据条数和dao中定义的返回值是否匹配。
     * 3.若返回一条数据，dao中定义的返回值是一个对象或对象的List列表，则可以正常匹配，将查询的数据按照dao中定义的返回值存放。
     * 4.若返回多条数据，dao中定义的返回值是一个对象，则无法将多条数据映射为一个对象，此时mybatis报错。
     * 获取所有的红包信息
     */

    List<RedPacket> getRedPackets();

    /**
     * 插入红包信息
     * @param redPacket
     * @return
     */
    void insertRedPacket(RedPacket redPacket);

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
     * @param version
     * @return
     */
    int decreaseRedPacketForVersion(@Param("id") int id,@Param("version") long version);

}
