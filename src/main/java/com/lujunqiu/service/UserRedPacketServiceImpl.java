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

/**
 * @author qiu
 */
@Service
public class UserRedPacketServiceImpl implements UserRedPacketService {
    @Autowired
    private UserRedPacketDao userRedPacketDao = null;
    @Autowired
    private RedPacketDao redPacketDao = null;

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
}

