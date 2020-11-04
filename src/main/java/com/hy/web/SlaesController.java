package com.hy.web;

import com.hy.bean.Sales;
import com.hy.bean.User;
import com.hy.service.SalesService;
import com.hy.service.Userserves;
import com.hy.util.ParseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/Sales")
public class SlaesController {
    @Autowired
    private SalesService salesServices;

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
}
