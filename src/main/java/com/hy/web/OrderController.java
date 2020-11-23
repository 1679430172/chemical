package com.hy.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Order;
import com.hy.service.OrderService;
import com.hy.util.ParseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

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
        return service.add(order);
    }

    @RequestMapping("/toAdd")
    @ResponseBody
    public ModelAndView toAdd(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("addOrder.html");
        return modelAndView;
    }

    @RequestMapping("/selectByStatus")
    @ResponseBody
    public ParseData selectByStatus(Integer page, Integer limit){
        Page page1=new Page(page,limit);
        return service.selectListByStatus(page1);
    }

    @PutMapping("/")
    @ResponseBody
    public Integer updStatus(Integer did){
        return service.updStatus(did);
    }

    @DeleteMapping("/")
    @ResponseBody
    public Integer del(Integer did){
        boolean f;
        f = service.removeById(did);
        if(f){
            return 1;
        }
        return 0;
    }

    @GetMapping("/time")
    @ResponseBody
    public ParseData select(String stadate,String enddate,Integer page, Integer limit) throws Exception {
        Page page1=new Page(page,limit);
        return service.selectListTime(stadate,enddate,page1);
    }
}
