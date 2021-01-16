package com.hy.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.bean.Commodity;
import com.hy.bean.Inventory;
import com.hy.bean.Invoice;
import com.hy.service.InvoiceService;
import com.hy.util.ParseData;
import com.hy.util.Util;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api
@Controller
@RequestMapping("Invoice")
public class InvoiceController  {
    @Autowired
    private InvoiceService service;

    @RequestMapping("/select")
    @ResponseBody
    public ParseData select(Integer page,Integer limit) throws Exception {
        Page page1=new Page(page,limit);
        IPage<Invoice> iPage=service.queryByCas("","",page1);
        List<Invoice> list = iPage.getRecords();
        int size = (page - 1) * limit;
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setXid(size + (i + 1));
        }
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),list);
    }

    @RequestMapping("/queryByCas.do")
    @ResponseBody
    public ParseData queryByCas(String cas,String name,Integer page,Integer limit){
        Page page1=new Page(page,limit);

        IPage<Invoice> iPage=service.queryByCas(cas,name,page1);
        List<Invoice> list = iPage.getRecords();
        int size = (page - 1) * limit;
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setXid(size + (i + 1));
        }
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),list);
    }

    @RequestMapping("/queryBysid.do")
    @ResponseBody
    public Invoice queryBysid(Integer sid){
        return service.queryBysid(sid);
    }


    @ResponseBody
    @RequestMapping ("/add.do" )
    public String add(Invoice invoice){
        String time= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        invoice.setCreateTime(time);
        try {
            service.save(invoice);
        } catch (Exception e) {
            return Util.defact;
        }
        return Util.sueess;
    }

    @ResponseBody
    @RequestMapping ("/updateBySid.do" )
    public Integer update(Invoice invoice){ return service.updateBySid(invoice); }

    @ResponseBody
    @RequestMapping ("/autoUpdate.do" )
    public Integer autoUpdate(Invoice invoice){
        return service.autoUpdateBySid(invoice);
    }

    @ResponseBody
    @RequestMapping ("/updateSid.do" )
    public Integer updateSid(Invoice invoice){
        return service.updatesid(invoice);
    }

    /**
     * 删除
     * @param req
     * @param response
     * @param sid
     * @return
     */
    @RequestMapping("/deleteById.do")
    @ResponseBody
    public boolean deleteById(HttpServletRequest req, HttpServletResponse response, String sid){
        QueryWrapper<Invoice> updateById=new QueryWrapper();
        updateById.eq("sid",sid);
        return service.remove(updateById);
    }
}
