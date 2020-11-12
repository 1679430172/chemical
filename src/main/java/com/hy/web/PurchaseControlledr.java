package com.hy.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hy.bean.Purchase;
import com.hy.service.PurchaseService;
import com.hy.util.ParseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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



        @GetMapping ("/update.do")
        @ResponseBody
        public  void  updateAnn(String cid){
        purchaseService.updateAnn(cid);

        }

        @RequestMapping("/toAdd")
        @ResponseBody
        public ModelAndView toAdd(){
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("purchaseAdd.html");
            return modelAndView;
        }

    @PostMapping("/add.do")
    @ResponseBody
    public void addPurchase(Purchase purchase) throws Exception {
        purchaseService.save(purchase);
    }


    }