package com.lujunqiu.pojo;

import java.util.Date;
import java.util.List;

/**
 * Created by qiu on 18-1-29.
 */
public class Weather {
    private Location location;
    private List<Day> daily;
    private Date last_update;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Day> getDaily() {
        return daily;
    }

    public void setDaily(List<Day> daily) {
        this.daily = daily;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }
}
