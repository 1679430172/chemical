package com.hy.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "inventory")
public class Inventory {
    @TableId(value = "kid",type = IdType.AUTO)
    private Integer kid;
    private String number; //编号
    private String name;   //品名
    private String cas;    //cas号
    private String createTime; //创建时间
    private String updateTime; //更新时间
    private Double amount; //库存数量
    private String cf;
    private  String remark; //备注
    @TableField(exist = false)
    private String type;//当前登录权限
    @TableField(exist = false)
    private Integer xid;//自定义序号

    public Integer getXid() {
        return xid;
    }

    public void setXid(Integer xid) {
        this.xid = xid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getKid() {
        return kid;
    }

    public void setKid(Integer kid) {
        this.kid = kid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "kid=" + kid +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", cas='" + cas + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", amount=" + amount +
                ", cf='" + cf + '\'' +
                ", remark='" + remark + '\'' +
                ", type='" + type + '\'' +
                ", xid=" + xid +
                '}';
    }
}
