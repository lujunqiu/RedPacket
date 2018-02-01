package com.lujunqiu.pojo;

/**
 * Created by qiu on 18-2-1.
 */
public class AjaxResponseMsg {
    private int errcode;
    private String detail;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
