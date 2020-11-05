package com.hy.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Order;
import com.hy.service.OrderService;
import com.hy.util.ParseData;
import io.swagger.annotations.Api;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Api
@Controller
@RequestMapping("Order")
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping("/")
    @ResponseBody
    public ParseData select(Integer page, Integer limit) throws Exception {
        Page page1=new Page(page,limit);
        return service.selectList(page1);
    }

    @PostMapping("/")
    @ResponseBody
    public Integer add(Order order) throws Exception {
        System.out.println(order);
        return 1;
    }

    @RequestMapping("/toAdd")
    @ResponseBody
    public ModelAndView toAdd(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("addOrder.html");
        return modelAndView;
    }

    @RequestMapping("/toOrder")
    @ResponseBody
    public ModelAndView toOrder(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("order.html");
        return modelAndView;
    }

    @PostMapping("/selectByStatus")
    @ResponseBody
    public ParseData selectByStatus(Integer page, Integer limit) throws Exception {
        Page page1=new Page(page,limit);
        return service.selectListByStatus(page1);
    }
}
