package com.hy.web;

import com.hy.bean.Invoice;
import com.hy.bean.Order;
import com.hy.service.InvoiceService;
import com.hy.service.OrderService;
import com.hy.util.ParseData;
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
}
