package com.hy.bean;

public class Commoditys {
    private Integer sid;
    private String name;
    private String cas;
    private Integer userId;
    private String priceInfo;
    private String commodityInfo;
    private String imgStatus;
    private String imgPath;
    private String fileStatus;
    private String filePath;
    private Integer supplierId;
    private String createTime;//开始时间
    private String createTimes;//结果时间
    private String updateTime;
    private String userName;//业务员名称
    private String amount;//库存数量
    private String ssid;
    private String remark;//备注
    private Double cost;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreateTimes() {
        return createTimes;
    }

    public void setCreateTimes(String createTimes) {
        this.createTimes = createTimes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(String priceInfo) {
        this.priceInfo = priceInfo;
    }

    public String getCommodityInfo() {
        return commodityInfo;
    }

    public void setCommodityInfo(String commodityInfo) {
        this.commodityInfo = commodityInfo;
    }

    public String getImgStatus() {
        return imgStatus;
    }

    public void setImgStatus(String imgStatus) {
        this.imgStatus = imgStatus;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
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

    @Override
    public String toString() {
        return "Commoditys{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", cas='" + cas + '\'' +
                ", userId=" + userId +
                ", priceInfo='" + priceInfo + '\'' +
                ", commodityInfo='" + commodityInfo + '\'' +
                ", imgStatus='" + imgStatus + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", fileStatus='" + fileStatus + '\'' +
                ", filePath='" + filePath + '\'' +
                ", supplierId=" + supplierId +
                ", createTime='" + createTime + '\'' +
                ", createTimes='" + createTimes + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
