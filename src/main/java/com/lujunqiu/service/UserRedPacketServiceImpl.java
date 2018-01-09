package com.lujunqiu.service;

import com.lujunqiu.dao.RedPacketDao;
import com.lujunqiu.dao.UserRedPacketDao;
import com.lujunqiu.pojo.RedPacket;
import com.lujunqiu.pojo.UserRedPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * @author qiu
 */
@Service
public class UserRedPacketServiceImpl implements UserRedPacketService {
    @Autowired
    private UserRedPacketDao userRedPacketDao = null;
    @Autowired
    private RedPacketDao redPacketDao = null;

    /**抢红包失败*/
    private final static int FAILED = 0;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int grapRedPacket(int redPacketId, int userId) {
        RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);

        System.out.println(redPacket.getStock()+"dsf"+redPacket.getUnitAmount());
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

}
