package com.hy.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @GetMapping ("/updateTn.do")
    @ResponseBody
    public  void  updateTrackingNumber(String cid){
        purchaseService.updateTN(cid);

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
            System.out.println("名称："+purchase.getName() +"uid:"+purchase.getUserId());
        purchaseService.save(purchase);
    }


    @RequestMapping("/queryPurchase.do")
    @ResponseBody
    public ParseData queryPurchase(Integer page, Integer limit, Purchase purchase) {
        System.out.println("purchase:" + purchase);
        QueryWrapper<Purchase> queryWrapper=new QueryWrapper<>();
        if(purchase.getName() != null&&!"".equals(purchase.getName())){
            queryWrapper.like("name",purchase.getName());
        }
        if (purchase.getCas() != null&&!"".equals(purchase.getCas())){
            queryWrapper.eq("cas",purchase.getCas());
        }
        if (purchase.getSupplierName() != null&&!"".equals(purchase.getSupplierName())){
            queryWrapper.eq("number",purchase.getSupplierName());
        }
        IPage<Purchase> iPage=purchaseService.page(purchaseService.iPage(page,limit),queryWrapper);

        return new ParseData(0, "", Integer.parseInt(Long.toString(iPage.getTotal())), iPage.getRecords());

    }


    }