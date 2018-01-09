package com.lujunqiu.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by qiu on 18-1-8.
 */
public class RedPacket implements Serializable {
    private static final long serialVersionUID = -366034112713678621L;
    /**红包编号*/
    private int id;
    /**发红包用户编号*/
    private int userId;
    /**红包金额*/
    private double amount;
    /**发红包时间*/
    private Timestamp sendDate;
    /**红包总个数*/
    private int total;
    /**单个红包金额*/
    private double unitAmount;
    /**剩余红包数目*/
    private int stock;
    /**版本*/
    private long version;
    /**备注*/
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(double unitAmount) {
        this.unitAmount = unitAmount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
