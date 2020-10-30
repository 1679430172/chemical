package com.hy.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Order;
import com.hy.service.OrderService;
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

    @RequestMapping("/select")
    @ResponseBody
    public List<Order> select() throws Exception {
        return service.list();
    }
}
