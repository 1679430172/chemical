package com.hy.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Order;
import com.hy.service.OrderService;
import com.hy.util.ParseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api
@Controller
@RequestMapping("Order")
public class OrderController {
    @Autowired
    private OrderService service;
    @Autowired
    private ParseData parseData;

    @GetMapping("/")
    @ResponseBody
    public ParseData select(Integer page, Integer limit) throws Exception {
        Page page1=new Page(page,limit);
        IPage<Order> iPage=service.selectList(page1);
        parseData.setCode(0);
        parseData.setCount(Integer.parseInt(Long.toString(iPage.getTotal())));
        parseData.setMsg("");
        parseData.setData(iPage.getRecords());
        return parseData;
    }

    @PostMapping("/")
    @ResponseBody
    public Integer add(Order order) throws Exception {
        System.out.println(order);
        return 1;
    }
}
