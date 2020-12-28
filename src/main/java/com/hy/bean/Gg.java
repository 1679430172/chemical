package com.hy.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.swing.*;

@TableName(value = "Gg")
public class Gg {
    private  int id;
    private  String bt;
    private  String nr;
    private  String fjr;
    private  int zt;
    private Spring sj;
    @TableField(exist = false)
    private String type;//当前登录权限

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Spring getSj() {
        return sj;
    }

    public void setSj(Spring sj) {
        this.sj = sj;
    }

    @Override
    public String toString() {
        return "Gg{" +
                "id=" + id +
                ", bt='" + bt + '\'' +
                ", nr='" + nr + '\'' +
                ", fjr='" + fjr + '\'' +
                ", zt=" + zt +
                ", sj=" + sj +
                '}';
    }
}
