package com.lujunqiu.service;

import com.lujunqiu.dao.RedPacketDao;
import com.lujunqiu.pojo.RedPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author qiu
 */
@Service
public class RedPacketServiceImpl implements RedPacketService {
    @Autowired
    private RedPacketDao redPacketDao = null;
    @Autowired
    private RedisTemplate redisTemplate = null;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public RedPacket getRedPacket(int id) {
//        return redPacketDao.getRedPacketForUpdate(id);
        return redPacketDao.getRedPacket(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int decreaseRedPacket(int id) {
        return redPacketDao.decreaseRedPacket(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public List<RedPacket> getRedPackets() {
        return redPacketDao.getRedPackets();
    }

    /**
     * 插入红包信息,同时将红包信息插入Redis
     * @param redPacket
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void insertRedPacket(RedPacket redPacket) {
        redPacketDao.insertRedPacket(redPacket);
        Map<String, String> map = new HashMap<String, String>();
        map.put("stock", redPacket.getStock()+"");
        map.put("unit_amount", redPacket.getUnitAmount() + "");
        redisTemplate.opsForHash().putAll("red_packet_" + redPacket.getId(), map);
    }
}
