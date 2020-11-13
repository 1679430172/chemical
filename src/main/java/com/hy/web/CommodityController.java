package com.hy.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hy.bean.Commodity;
import com.hy.bean.Commoditys;
import com.hy.service.CommodityService;
import com.hy.service.SupplierService;
import com.hy.util.ParseData;
import com.hy.util.Util;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http .HttpServletResponse;
import java.util.Date;
import java.util.List;

@Api
@Controller
@RequestMapping("Commodity")
public class CommodityController {
    @Autowired
    private CommodityService commodityService;

    @Autowired
    private SupplierService supplierService;

    @RequestMapping("/Commoditys.do")
    @ResponseBody
    public ParseData  Commoditys(Integer page, Integer limit, Commoditys commoditys){
        IPage<Commoditys> iPage=  commodityService.CommditysList(page,limit,commoditys);
        return new ParseData(0,"",Integer.parseInt(Long.toString(iPage.getTotal())),iPage.getRecords());
    }
    @RequestMapping("/commodityById.do")
    @ResponseBody
    public Commodity  CommodityById(String sid){
        System.out.println(sid);
        return  commodityService.byid(sid);
    }

    @RequestMapping("/pictures.do")
    @ResponseBody
    public String pictures(@RequestParam("fileUpload") MultipartFile pictureFile, String sid, HttpServletRequest req){
        try {
            commodityService.pictures(pictureFile,sid,req);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.fail;
        }
        return Util.succeed;
    }

    @RequestMapping("/file.do")
    @ResponseBody
    public String file(@RequestParam("fileUpload") MultipartFile pictureFile, String sid, HttpServletRequest req){
        try {
            commodityService.file(pictureFile,sid,req);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.fail;
        }
        return Util.succeed;
    }


    @RequestMapping("/save.do")
    @ResponseBody
    public String save(Commodity commodity){
         supplierService.getById(commodity.getSupplierId());
        boolean b= commodityService.save(commodity);
        if(b == true){
            return Util.succeed;
        }else {
            return Util.fail;
        }
    }

    @RequestMapping("/commoditiesList")
    @ResponseBody
    public List<Commodity> commoditiesList(){
        return commodityService.list();
    }

    @RequestMapping("/equals.do")
    @ResponseBody
    public String  equals(Commodity commodity){
        commodity.setUpdateTime(new Date());
        System.out.println(commodity.toString());
        commodityService.equals(commodity);
        return "1";
    }

    @RequestMapping("/supplierId.do")
    @ResponseBody
    public Integer Supplier(String  supplierId){
        return commodityService.suppliers(supplierId);
    }

    @RequestMapping("/download.do")
    @ResponseBody
    public String download(HttpServletRequest req,HttpServletResponse response, String sid){
        return commodityService.download(req,response,sid);
    }
}
