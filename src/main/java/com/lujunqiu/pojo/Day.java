package com.lujunqiu.pojo;

import java.util.Date;

/**
 * Created by qiu on 18-1-29.
 */
public class Day {
    // 日期
    private Date date;
    //白天天气现象文字
    private String text_day;
    //白天天气现象代码
    private int code_day;
    //晚间天气现象文字
    private String text_night;
    //晚间天气现象代码
    private int code_night;
    //当天最高温度
    private int high;
    //当天最低温度
    private int low;
    //降水概率，范围0~100，单位百分比
    private double precip;
    //风向文字
    private String wind_direction;
    //风向角度，范围0~360
    private double wind_direction_degree;
    //风速
    private double wind_speed;
    //风力等级
    private double wind_scale;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText_day() {
        return text_day;
    }

    public void setText_day(String text_day) {
        this.text_day = text_day;
    }

    public int getCode_day() {
        return code_day;
    }

    public void setCode_day(int code_day) {
        this.code_day = code_day;
    }

    public String getText_night() {
        return text_night;
    }

    public void setText_night(String text_night) {
        this.text_night = text_night;
    }

    public int getCode_night() {
        return code_night;
    }

    public void setCode_night(int code_night) {
        this.code_night = code_night;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public double getPrecip() {
        return precip;
    }

    public void setPrecip(double precip) {
        this.precip = precip;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public double getWind_direction_degree() {
        return wind_direction_degree;
    }

    public void setWind_direction_degree(double wind_direction_degree) {
        this.wind_direction_degree = wind_direction_degree;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public double getWind_scale() {
        return wind_scale;
    }

    public void setWind_scale(double wind_scale) {
        this.wind_scale = wind_scale;
    }
}
