package com.hy.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value = "commodity")
public class Commodity {
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
    private String createTime;
    private Date updateTime;
    @TableField(exist = false)
    private Integer supplier;

    public Integer getSupplier() {
        return supplier;
    }

    public void setSupplier(Integer supplier) {
        this.supplier = supplier;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Commodity{" +
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
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
