package com.hy.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Invoice;
import com.hy.bean.Purchase;
import com.hy.bean.Supplier;
import com.hy.bean.SupplierUsers;
import com.hy.service.PurchaseService;
import com.hy.util.ParseData;
import com.hy.util.Util;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Api
    @Controller
    @RequestMapping("purchase")
    public class PurchaseControlledr {
        @Autowired
        private PurchaseService purchaseService;

        @RequestMapping("/purchase.do")
        @ResponseBody
        public ParseData purchase(Integer page, Integer limit,Purchase purchase) {
            Page page1=new Page(page,limit);
            return purchaseService.selectList(page1);
        }
    @RequestMapping("/purchase1.do")
    @ResponseBody
    public ParseData purchase1(Integer page, Integer limit,Purchase purchase) {
        IPage<Purchase> iPage = purchaseService.iPage(page, limit,purchase);
        return new ParseData(0, "", Integer.parseInt(Long.toString(iPage.getTotal())), iPage.getRecords());
    }

/*        @RequestMapping("/queryPurchase.do")
        @ResponseBody
        public ParseData queryPurchase(Integer page, Integer limit, Purchase purchase) {
            IPage<Purchase> iPage=purchaseService.page(purchaseService.iPage(page,limit),purchase);

            return new ParseData(0, "", Integer.parseInt(Long.toString(iPage.getTotal())), iPage.getRecords());

        }*/

        @GetMapping ("/update.do")
        @ResponseBody
        public  void  updateAnn(String cid){
        purchaseService.updateAnn(cid);


    }
    @RequestMapping("/updateTn.do")
    @ResponseBody
    public String  updateTn(@Param("cid") Integer cid,@Param("trackingNumber") String trackingNumber){
        Purchase purchase = new Purchase();
        purchase.setCid(cid);
        purchase.setTrackingNumber(trackingNumber);
        System.out.println(purchase.toString());
        purchaseService.equals(purchase);
        return "1";
    }
    @ResponseBody
    @RequestMapping ("/updateCid.do" )
    public Integer updateSid(Purchase purchase){
        return purchaseService.updatecid(purchase);

        }
        @RequestMapping("/toAdd")
        @ResponseBody
        public ModelAndView toAdd(){
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("purchaseAdd.html");
            return modelAndView;
        }

    @RequestMapping("/querybyCid.do")
    @ResponseBody
    public Purchase querybyCid(Integer cid){
        return purchaseService.queryBycid(cid);
    }

    @RequestMapping("/add.do")
    @ResponseBody
    public  String addPurchase(Purchase purchase) throws Exception {
        purchase.setSumPrice(purchase.getAmount()*purchase.getPrice());
        System.out.println(purchase.getSumPrice());
        purchase.setUserId(purchaseService.wwww());
        try {
            purchaseService.save(purchase);
        } catch (Exception e) {
            return Util.fail;
        }
        return Util.succeed;
    }
    @RequestMapping("/toPurchase")
    @ResponseBody
    public ModelAndView toPurchase(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("purchase.html");
        return modelAndView;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String detelep(String cid){
        return purchaseService.detelep(cid);
    }



    }