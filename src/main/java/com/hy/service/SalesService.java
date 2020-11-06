package com.hy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.bean.Order;
import com.hy.bean.Sales;
import com.hy.bean.SalesOrdet;
import com.hy.mapper.OrderMapper;
import com.hy.mapper.SalesMapper;
import com.hy.util.ParseData;
import com.hy.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;


@Service("SalesService")
public class SalesService extends ServiceImpl<SalesMapper, Sales> {
    @Autowired
    private SalesMapper salesMapper;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 验证业务员权限查询退货订单
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public ParseData getSalesbyuserId(String userId,Integer page,Integer limit) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        Integer  userId1 =  (Integer) session.getAttribute("userId");
        IPage<SalesOrdet> iPage=null;
        if(userId.equals("0") || userId.equals("2")){
            Page<SalesOrdet> page1 = new Page<SalesOrdet>(page,limit);
            iPage= salesMapper.selectSalestwo(page1);
        }else if(userId.equals("1")){
            Page<SalesOrdet> page1 = new Page<SalesOrdet>(page,limit);
             iPage=salesMapper.selectSales(page1,userId1);
        }
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),iPage.getRecords());
    }

    /**
     * 通过业务员id查询所有已经出货的订单
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public ParseData selectorder(String userId,Integer page,Integer limit){
        Page page1=new Page(page,limit);
        IPage<Order> iPage = null;
        iPage= orderMapper.selectListByUserId(Integer.parseInt(userId),page1);
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),iPage.getRecords());
    }

    /**
     * 通过订单编号查询订单
     * @param trackingNumber
     * @param page
     * @param limit
     * @return
     */
    public ParseData selectorderby(String trackingNumber,Integer page,Integer limit){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        String  userType =  (String) session.getAttribute("userType");
        Integer  userId =  (Integer) session.getAttribute("userId");
        IPage<SalesOrdet> iPage=null;
        if(userType.equals("0") || userType.equals("2")){
            Page<SalesOrdet> page1 = new Page<SalesOrdet>(page,limit);
             iPage=salesMapper.bytrackingNumberselect(page1,Integer.parseInt(trackingNumber));
        }else if(userType.equals("1")){
            Page<SalesOrdet> page1 = new Page<SalesOrdet>(page,limit);
            iPage=salesMapper.bytrackingNumberselecttwo(page1,Integer.parseInt(trackingNumber),userId);
        }
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),iPage.getRecords());
    }

    /**
     * 添加退货订单
     * @param a
     * @return
     */
    public String insertSales(boolean a){
        if(a==true){
            return Util.sueess;
        }
        return  Util.defact;
    }


    public String  updateOrder(Integer did){
       Integer b= orderMapper.updateOrder(did);
        if(b!=0){
            return Util.sueess;
        }
        return  Util.defact;
    }



}
