package com.hy.web;

import com.hy.bean.Invoice;
import com.hy.service.InvoiceService;
import com.hy.util.ParseData;
import com.hy.util.Util;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Api
@Controller
@RequestMapping("Invoice")
public class InvoiceController  {
    @Autowired
    private InvoiceService service;

    @RequestMapping("/select")
    @ResponseBody
    public ParseData select() throws Exception {
        List<Invoice> list=service.list();
        return new ParseData(0,"",null,list);
    }

    @RequestMapping("/queryByCas.do")
    @ResponseBody
    public ParseData queryByCas(String cas,String name,Integer page,Integer limit){
        List<Invoice> list=service.queryByCas(cas,name);
        return new ParseData(0,"",null,list);
    }



    @ResponseBody
    @RequestMapping ("/add.do" )
    public String add(Invoice invoice){
        System.out.println("createTime="+invoice.getCreateTime());
        try {
            service.save(invoice);
        } catch (Exception e) {
            return Util.defact;
        }
        return Util.sueess;
    }

    @ResponseBody
    @RequestMapping ("/updateBySid.do" )
    public Integer update(Invoice invoice){
        return service.updateBySid(invoice);
    }

    @ResponseBody
    @RequestMapping ("/autoUpdate.do" )
    public Integer autoUpdate(Invoice invoice){
        return service.autoUpdateBySid(invoice);
    }
}
