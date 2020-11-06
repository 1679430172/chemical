package com.hy.bean;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.Date;

public class Invoice {
    private Integer sid;
    private String name;
    private String cas;
    private Integer number;
    private Double price;
    private String createTime;

    public Integer getSid() { return sid; }

    public void setSid(Integer sid) { this.sid = sid; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCas() { return cas; }

    public void setCas(String cas) { this.cas = cas; }

    public Integer getNumber() { return number; }

    public void setNumber(Integer number) { this.number = number; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", cas='" + cas + '\'' +
                ", number=" + number +
                ", price=" + price +
                ", create_time=" + createTime +
                '}';
    }
}
