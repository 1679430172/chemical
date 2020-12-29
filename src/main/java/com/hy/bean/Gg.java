package com.hy.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.swing.*;

@TableName(value = "gg")
public class Gg {
    private  int id;
    private  String bt;
    private  String nr;
    private  String fjr;
    private  int zt;
    @TableId("create_time")
    private Spring createTime;
    @TableField(exist = false)
    private String type;//当前登录权限

    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public Spring getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Spring createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getFjr() {
        return fjr;
    }

    public void setFjr(String fjr) {
        this.fjr = fjr;
    }

    public int getZt() {
        return zt;
    }

    public void setZt(int zt) {
        this.zt = zt;
    }

    @Override
    public String toString() {
        return "Gg{" +
                "id=" + id +
                ", bt='" + bt + '\'' +
                ", nr='" + nr + '\'' +
                ", fjr='" + fjr + '\'' +
                ", zt=" + zt +
                ", createTime=" + createTime +
                ", type='" + type + '\'' +
                '}';
    }
}
