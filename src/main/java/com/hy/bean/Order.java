package com.hy.bean;

import com.baomidou.mybatisplus.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Objects;

@TableName(value = "`order`")
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer did;//订单id
    private Integer userId;//业务员id
    private Integer amount;//数量
    private Double price;//单价
    private Double costPrice;//成本
    private Double otherCost;//其他成本
    private String bill;//发票状态
    private String billInfo;//发票内容
    private Integer commodityId;//商品id
    private Integer invoiceId;//库存标号
    private String status;//是否出库
    private String userName;//客户名
    private String address;//客户地址
    private String phone;//客户电话
    private Date createTime;//创建时间
    private String remarks;//备注
    @TableField(exist = false)
    private String name;//商品名称
    @TableField(exist = false)
    private Integer suid;//商品业务员id
    @TableField(exist = false)
    private Double zcb;//总成本
    @TableField(exist = false)
    private Double sumPrice;//总价
    @TableField(exist = false)
    private Double royalties;
    @TableField(exist = false)
    private String id;//订单id
    @TableField(exist = false)
    private String type;//当前登录权限
    @TableField(exist = false)
    private Integer uid;//当前登录id
    @TableField(exist = false)
    private String number;//库存编号

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSumPrice() {
        sumPrice=price*amount;
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public String getRoyalties() {
        HttpSession session = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
        if(this.status.equals("2")){
            royalties=0.0;
        }else if(session.getAttribute("userType").equals(Authority.administrator)||session.getAttribute("userType").equals(Authority.authorizedSalesman)){
            royalties=((this.price*this.amount)-(this.costPrice*this.amount)-otherCost)*0.87*0.4;
        }else if(this.userId.equals(this.suid)){
            royalties=((this.price*this.amount)-(this.costPrice*this.amount)-otherCost)*0.87*0.4;
        }else if(session.getAttribute("userId").equals(this.userId)){
            royalties=((this.price*this.amount)-(this.costPrice*this.amount)-otherCost)*0.87*0.3;
        }else if(session.getAttribute("userId").equals(this.suid)){
            royalties=((this.price*this.amount)-(this.costPrice*this.amount)-otherCost)*0.87*0.1;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(royalties);
    }

    public void setRoyalties(Double royalties) {
        this.royalties = royalties;
    }

    public Double getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(Double otherCost) {
        this.otherCost = otherCost;
    }

    public String getBillInfo() {
        return billInfo;
    }

    public void setBillInfo(String billInfo) {
        this.billInfo = billInfo;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getSuid() {
        return suid;
    }

    public void setSuid(Integer suid) {
        this.suid = suid;
    }

    public Double getZcb() {
        zcb=costPrice*amount+otherCost;
        return zcb;
    }

    public void setZcb(Double zcb) {
        this.zcb = zcb;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Order{" +
                "did=" + did +
                ", userId=" + userId +
                ", amount=" + amount +
                ", price=" + price +
                ", costPrice=" + costPrice +
                ", otherCost=" + otherCost +
                ", bill='" + bill + '\'' +
                ", billInfo='" + billInfo + '\'' +
                ", commodityId=" + commodityId +
                ", invoiceId=" + invoiceId +
                ", status='" + status + '\'' +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", createTime=" + createTime +
                ", remarks='" + remarks + '\'' +
                ", name='" + name + '\'' +
                ", suid=" + suid +
                ", zcb=" + zcb +
                ", sumPrice=" + sumPrice +
                ", royalties=" + royalties +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", uid=" + uid +
                ", number='" + number + '\'' +
                '}';
    }
}
