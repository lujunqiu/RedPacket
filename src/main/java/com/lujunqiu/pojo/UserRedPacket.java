package com.lujunqiu.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author qiu
 */
public class UserRedPacket implements Serializable {
    private static final long serialVersionUID = -7514354283945414261L;
    /**编号*/
    private int id;
    /**红包编号*/
    private int redPacketId;
    /**抢红包用户编号*/
    private int userId;
    /**抢红包金额*/
    private double amount;
    /**抢红包时间*/
    private Timestamp grabTime;
    /**备注*/
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(int redPacketId) {
        this.redPacketId = redPacketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getGrabTime() {
        return grabTime;
    }

    public void setGrabTime(Timestamp grabTime) {
        this.grabTime = grabTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
