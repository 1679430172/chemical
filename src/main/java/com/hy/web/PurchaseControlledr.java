package com.hy.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hy.bean.Order;
import com.hy.bean.Purchase;
import com.hy.service.PurchaseService;
import com.hy.util.ParseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

    @Api
    @Controller
    @RequestMapping("purchase")
    public class PurchaseControlledr {
        @Autowired
        private PurchaseService purchaseService;

        @RequestMapping("/purchase.do")
        @ResponseBody
        public ParseData inventory(Integer page, Integer limit) {
            IPage<Purchase> iPage = purchaseService.iPage(page, limit);
            return new ParseData(0, "", Integer.parseInt(Long.toString(iPage.getTotal())), iPage.getRecords());

        }

        @PostMapping("/add.do")
        @ResponseBody
        public Integer add(Order order) throws Exception {
            System.out.println(order);
            return 1;
        }

    }