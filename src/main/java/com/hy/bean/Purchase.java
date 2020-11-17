package com.hy.bean;


import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "Purchase")
public class Purchase {
     private Integer cid;
     private Integer userId;       //业务员id
     private String name;          //产品名称
     private String cas;           //cas号
     private Integer amount;       //数量
     private double price;         //单价
     private String priceStatus;   //0单价（含税），1单价（不含税）
     private double sumPrice;      //总价
     private Integer status;       //收获状态 0未收货 1确认收货
     private String userName;      //业务员名称
     private String supplierName;  //供应商名称
     private String supplierPhone; //供应商电话
     private String trackingNumber;//物流单号
     private String createTime;    //创建日期
     private  Integer ann; //是否收到发票
    private  String gid;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public  Integer getCid(){return cid;}
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(String priceStatus) {
        this.priceStatus = priceStatus;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public Integer getAnn() {
        return ann;
    }

    public void setAnn(Integer ann) {
        this.ann = ann;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "cid=" + cid +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", cas='" + cas + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", priceStatus='" + priceStatus + '\'' +
                ", sumPrice=" + sumPrice +
                ", status=" + status +
                ", userName='" + userName + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierPhone='" + supplierPhone + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", createTime='" + createTime + '\'' +
                ", ann=" + ann +
                ", gid='" + gid + '\'' +
                '}';
    }
}
