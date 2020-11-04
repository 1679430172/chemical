package com.hy.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParseData {
    private Integer code;
    private String msg;
    private Integer count;
    private List data;

    public ParseData(Integer code, String msg, Integer count, List data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public ParseData() {

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List getData() {
        return data;
    }

    public void setData(List date) {
        this.data = date;
    }
}
