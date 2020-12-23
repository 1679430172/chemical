package com.hy.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.Date;

public class Invoice {
    private Integer sid;
    private String name;
    private String cas;
    private Double number;
    private Double price;
    private String unit;
    private String createTime;
    @TableField(exist = false)
    private Integer xid;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getXid() {
        return xid;
    }

    public void setXid(Integer xid) {
        this.xid = xid;
    }

    public Integer getSid() { return sid; }

    public void setSid(Integer sid) { this.sid = sid; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCas() { return cas; }

    public void setCas(String cas) { this.cas = cas; }

    public Double getNumber() { return number; }

    public void setNumber(Double number) { this.number = number; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public String getCreateTime() {
        return createTime;
    }


    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", cas='" + cas + '\'' +
                ", number=" + number +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                ", createTime='" + createTime + '\'' +
                ", xid=" + xid +
                ", description='" + description + '\'' +
                '}';
    }
}
