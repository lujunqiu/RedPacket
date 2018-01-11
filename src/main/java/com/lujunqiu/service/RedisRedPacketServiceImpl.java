package com.lujunqiu.service;

import com.lujunqiu.pojo.UserRedPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiu on 18-1-11.
 */
@Service
public class RedisRedPacketServiceImpl implements RedisRedPacketService {
    private static final String PREFIX = "red_packet_list_";
    //每次取出1000条数据
    private static final int TIME_SIZE = 1000;

    @Autowired
    RedisTemplate redisTemplate = null;

    @Autowired
    DataSource dataSource = null;

    @Override
    //异步调用
    @Async
    public void saveUserRedPacketByRedis(int redPacketId, double unitAmount) {
        System.out.println("开始保持数据");
        long start = System.currentTimeMillis();
        //得到操控redis列表的对象,操作的列表的key是"PREFIX + redPacketId"
        BoundListOperations ops = redisTemplate.boundListOps(PREFIX + redPacketId);

        long size = ops.size();
        long times = size % TIME_SIZE == 0 ? size / TIME_SIZE : size / TIME_SIZE + 1;
        int count = 0;
        List<UserRedPacket> userRedPacketList = new ArrayList<UserRedPacket>(TIME_SIZE);

        for (int i = 0; i < times; i++) {
            List userIdList = null;
            if (i == 0) {
                userIdList = ops.range(0, TIME_SIZE);
            } else {
                userIdList = ops.range(i * TIME_SIZE + 1, (i + 1) * TIME_SIZE);
            }
            userRedPacketList.clear();
            for (int j = 0; j < userIdList.size(); j++) {
                String args = userIdList.get(j).toString();
                String[] arr = args.split("-");
                String userIdStr = arr[0];
                String timeStr = arr[1];
                int userId = Integer.parseInt(userIdStr);
                long time = Long.parseLong(timeStr);

                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setUserId(userId);
                userRedPacket.setAmount(unitAmount);
                userRedPacket.setGrabTime(new Timestamp(time));
                userRedPacket.setNote("抢红包 " + redPacketId);

                userRedPacketList.add(userRedPacket);
            }
            //将红包信息插入Mysql中
            count += executeBatch(userRedPacketList);
        }
        redisTemplate.delete(PREFIX + redPacketId);
        long end = System.currentTimeMillis();
        System.out.println("保存数据结束，耗时：" + (end - start) / 1000 + "秒，共 " + count + "条数据被保持");
    }

    /**
     * JDBC批量处理Redis缓存的数据，存入Mysql
     * @param userRedPacketList
     * @return
     */
    private int executeBatch(List<UserRedPacket> userRedPacketList) {
        Connection conn = null;
        Statement stmt = null;
        int[] count = null;

        try {
            conn = dataSource.getConnection();
            //取消自动提交事务，手动提交事务
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            for (UserRedPacket userRedPacket : userRedPacketList) {
                String sql = "update T_RED_PACKET set stock = stock - 1 where id = " + userRedPacket.getRedPacketId();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                String sql2 = "insert into T_USER_RED_PACKET(red_packet_id,user_id," + "amount,grab_time,note)"
                        + "values(" + userRedPacket.getRedPacketId() + "," + userRedPacket.getUserId() + "," + userRedPacket.getAmount() + ","
                        + "'" + df.format(userRedPacket.getGrabTime()) + "'," + "'" + userRedPacket.getNote() + "')";
                stmt.addBatch(sql);
                stmt.addBatch(sql2);
            }
            count = stmt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("抢红包批量执行程序错误");
        }finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count.length / 2;
    }
}
