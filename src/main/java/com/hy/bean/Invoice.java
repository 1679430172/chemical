package com.hy.bean;

import java.util.Date;

public class Invoice {
    private Integer sid;
    private String name;
    private String cas;
    private Integer number;
    private Double price;
    private Date create_time;

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

    public Date getCreate_time() { return create_time; }

    public void setCreate_time(Date create_time) { this.create_time = create_time; }

    @Override
    public String toString() {
        return "Invoice{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", cas='" + cas + '\'' +
                ", number=" + number +
                ", price=" + price +
                ", create_time=" + create_time +
                '}';
    }
}
