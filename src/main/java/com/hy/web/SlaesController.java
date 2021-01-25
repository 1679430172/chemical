package com.hy.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hy.bean.Order;
import com.hy.bean.Sales;
import com.hy.mapper.InventoryMapper;
import com.hy.mapper.OrderMapper;
import com.hy.service.OrderService;
import com.hy.service.SalesService;
import com.hy.util.ParseData;
import com.hy.util.Util;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/Sales")
public class SlaesController {
    @Autowired
    private SalesService salesServices;

    @Autowired
    private OrderService service;
    @Autowired
    private InventoryMapper inventoryMapper;
    /**
     * 验证业务员权限查询订单
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/Saleslist")
    @ResponseBody
    public ParseData Login(String userId,Integer page ,Integer limit){
        return salesServices.getSalesbyuserId(userId,page,limit);
    }


    /**
     *
     * @param trackingNumber
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/SalesOrderbyid")
    @ResponseBody
    public ParseData selectorderby(String trackingNumber,String name,Integer page ,Integer limit){
        return salesServices.selectorderby(trackingNumber,name,page,limit);

    }

    /**
     * 退货添加
     * @param sales
     * @return
     * @throws Exception
     */
    @RequestMapping("/insertSales")
    @ResponseBody
    public String insertSales(Sales sales) throws Exception {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        Integer  userId =  (Integer) session.getAttribute("userId");
        sales.setUserId(userId);
        UpdateWrapper<Sales> select=new UpdateWrapper();
        select.eq("order_id",sales.getOrderId());
        Sales sales1=salesServices.getOne(select);
        if(sales1!=null){
            System.out.println(sales);
            UpdateWrapper<Sales> updateWrapper=new UpdateWrapper();
            updateWrapper.set("tracking_number",sales.getTrackingNumber());
            updateWrapper.set("tracking_name",sales.getTrackingName());
            updateWrapper.set("status","1");
            updateWrapper.eq("did",sales1.getDid());
            salesServices.update(updateWrapper);
            return Util.sueess;
        }
//        if(sales.getTrackingName().equals("") ||sales.getTrackingNumber().equals("")){
//            sales.setTrackingName("");
//            sales.setTrackingName("");
//            sales.setStatus("2");
//            salesServices.save(sales);
//            return salesServices.updateOrder(sales.getOrderId());
//        }
        sales.setStatus("0");
        salesServices.save(sales);
        return  salesServices.updateOrder(sales.getOrderId());
    }

    /**
     * 修改
     * @return
     * @throws Exception
     *
     */
    @RequestMapping("/updateInfo")
    @ResponseBody
    public boolean updateInfo(Integer did,String trackingNumber,String trackingName) throws Exception {
        UpdateWrapper<Sales> updateWrapper=new UpdateWrapper();
        updateWrapper.set("tracking_number",trackingNumber);
        updateWrapper.set("tracking_name",trackingName);
        updateWrapper.eq("did",did);
        return salesServices.update(updateWrapper);
    }

    /**
     * 修改订单状态为退货
     * @param did
     * @param trackingNumber
     * @return
     */
    @RequestMapping("/updateOrder")
    @ResponseBody
    public String updateOrder(@Param("did") Integer did, @Param("trackingNumber") String trackingNumber){
        return   salesServices.updateOrder(did,trackingNumber);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteSales(@Param("did") Integer did,String orderId){
       boolean b= salesServices.removeById(did);
        UpdateWrapper<Order> updateWrapper=new UpdateWrapper();
        updateWrapper.eq("did",orderId);
        service.remove(updateWrapper);
        if (b==true){
            return Util.sueess;
        }else{
            return Util.defact;
        }
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public String updateStatus(@Param("did") Integer did,String orderId){

        UpdateWrapper<Sales> updateWrapper = new UpdateWrapper();
        updateWrapper.set("status", "1");
        updateWrapper.eq("did", did);
        boolean b = salesServices.update(updateWrapper);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("did", orderId);
        Order order = service.getOne(queryWrapper);
        if (order != null) {
            inventoryMapper.autoUpdate(order.getAmount(), order.getInvoiceId());
        }
        if (b==true){
            return Util.sueess;
        }else{
            return Util.defact;
        }
    }
}
