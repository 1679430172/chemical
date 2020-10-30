package com.hy.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.service.OrderService;
import com.hy.util.ParseData;
import com.sun.org.apache.xpath.internal.operations.Or;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api
@Controller
@RequestMapping("Order")
public class OrderController {
    @Autowired
    private OrderService service;
    @Autowired
    private ParseData parseData;

    @RequestMapping("/select")
    @ResponseBody
    public ParseData select() throws Exception {
        List<Order> list=service.list();
        parseData.setCode(0);
        parseData.setCount(3);
        parseData.setMsg("");
        parseData.setData(list);
        return parseData;
    }
}
