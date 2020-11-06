package com.hy.web;

import com.hy.bean.Order;
import com.hy.bean.Sales;
import com.hy.mapper.OrderMapper;
import com.hy.service.OrderService;
import com.hy.service.SalesService;
import com.hy.util.ParseData;
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
    public ParseData selectorderby(String trackingNumber,Integer page ,Integer limit){
        return salesServices.selectorderby(trackingNumber,page,limit);

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
        boolean a=   salesServices.save(sales);
        return salesServices.insertSales(a);

    }
    @RequestMapping("/updateOrder")
    @ResponseBody
    public String updateOrder(Order order){
        service.updateById(order);
        return "";
    }



}
