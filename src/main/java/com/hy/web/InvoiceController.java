package com.hy.web;

import com.hy.bean.Invoice;
import com.hy.service.InvoiceService;
import com.hy.util.ParseData;
import com.hy.util.Util;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Api
@Controller
@RequestMapping("Invoice")
public class InvoiceController  {
    @Autowired
    private InvoiceService service;
    @Autowired
    private ParseData parseData;

    @RequestMapping("/select")
    @ResponseBody
    public ParseData select() throws Exception {
        List<Invoice> list=service.list();
        parseData.setCode(0);
        parseData.setCount(3);
        parseData.setMsg("");
        parseData.setData(list);
        return parseData;
    }

    @RequestMapping("/queryByCas.do")
    @ResponseBody
    public ParseData queryByCas(String cas,Integer page,Integer limit){
        List<Invoice> list=service.queryByCas(cas);
        parseData.setCode(0);
        parseData.setCount(1);
        parseData.setMsg("");
        parseData.setData(list);
        return parseData;

    }
    @ResponseBody
    @PostMapping ("/add.do" )
    public String add(Invoice invoice){
        try {
            service.save(invoice);
        } catch (Exception e) {
            return Util.defact;
        }
        return Util.sueess;
    }


}
