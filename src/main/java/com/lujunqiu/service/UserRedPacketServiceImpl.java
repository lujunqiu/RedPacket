package com.lujunqiu.service;

import com.lujunqiu.dao.RedPacketDao;
import com.lujunqiu.dao.UserRedPacketDao;
import com.lujunqiu.pojo.RedPacket;
import com.lujunqiu.pojo.UserRedPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author qiu
 */
@Service
public class UserRedPacketServiceImpl implements UserRedPacketService {
    @Autowired
    private UserRedPacketDao userRedPacketDao = null;
    @Autowired
    private RedPacketDao redPacketDao = null;
    @Autowired
    private RedisTemplate redisTemplate = null;
    @Autowired
    private RedisRedPacketService redisRedPacketService = null;

    //Lua脚本,在redis中是原子操作
    String script = "local listKey = 'red_packet_list_'..KEYS[1] \n" +
            "local redPacket = 'red_packet_'..KEYS[1] \n" +
            "local stock = tonumber(redis.call('hget',redPacket,'stock')) \n" +
            "if stock <= 0 then return 0 end \n" +
            "stock = stock - 1 \n" +
            "redis.call('hset',redPacket,'stock',tostring(stock))\n" +
            "redis.call('rpush',listKey,ARGV[1])\n" +
            "if stock == 2 then return 2 end \n"+
            "return 1 \n";
    //在redis中可以缓存lua脚本，返回一个32位的SHA1编码，下次直接通过这个SHA1编码调用lua脚本即可
    String sha1 = null;
    /**
     * 抢红包失败
     */
    private final static int FAILED = 0;

    /**
     * 抢红包实现
     * @param redPacketId 红包编号
     * @param userId      抢红包用户编号
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grapRedPacket(int redPacketId, int userId) {
        RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
        //当前红包库存大于0
        if (redPacket.getStock() > 0) {
            redPacketDao.decreaseRedPacket(redPacketId);

            UserRedPacket userRedPacket = new UserRedPacket();
            userRedPacket.setRedPacketId(redPacketId);

            userRedPacket.setUserId(userId);

            userRedPacket.setAmount(redPacket.getUnitAmount());

            userRedPacket.setNote("抢红包 " + redPacketId);


            int result = userRedPacketDao.grapRedPacket(userRedPacket);
            return result;
        }


        return FAILED;
    }

    /**
     * CAS操作抢红包实现
     * @param redPacketId
     * @param userId
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grapRedPacketForVersion(int redPacketId, int userId) {
        //CAS操作尝试3次
        for (int i = 0; i < 3; i++) {
            RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
            if (redPacket.getStock() > 0) {
                int update = redPacketDao.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());

                if (update == 0) {
                    continue;
                }
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);

                userRedPacket.setUserId(userId);

                userRedPacket.setAmount(redPacket.getUnitAmount());

                userRedPacket.setNote("抢红包 " + redPacketId);

                int result = userRedPacketDao.grapRedPacket(userRedPacket);
                return result;
            } else {
                return FAILED;
            }
        }
        return FAILED;
    }

    /**
     * 使用redis实现抢红包逻辑
     * @param redPacketId
     * @param userId
     * @return
     */
    @Override
    public long grapRedPacketByRedis(int redPacketId, int userId) {
        String args = userId + "-" + System.currentTimeMillis();
        long result = 0;
        //获取Jedis对象,直接操作Redis数据库
        Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();

        try {
            //脚本没有被加载过，就加载脚本，返回一个sha1编码
            if (sha1 == null) {
                sha1 = jedis.scriptLoad(script);
            }
            Object res = jedis.evalsha(sha1, 1, redPacketId + "", args);
            result = (Long) res;

            if (result == 2) {
                String unitAmountStr = jedis.hget("red_packet_" + redPacketId, "unit_amount");
                double unitAmount = Double.parseDouble(unitAmountStr);
                redisRedPacketService.saveUserRedPacketByRedis(redPacketId, unitAmount);
            }
        }
        finally {
            if (jedis != null && jedis.isConnected()) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 得到用户抢红包信息
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public List<UserRedPacket> getUserRedPacket() {
        return userRedPacketDao.getUserRedPacket();
    }
}

